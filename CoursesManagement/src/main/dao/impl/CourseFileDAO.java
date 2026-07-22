package dao.impl;

import dao.CourseDAO;
import exception.DataAccessException;
import model.Course;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Cai dat CourseDAO, luu du lieu vao file text.
 * Moi dong trong file la 1 course, cac truong cach nhau boi dau '|'
 * Vi du: PRJ101|Core Java|3
 *
 * Duong dan file duoc truyen tu ben ngoai vao qua constructor chu khong hard-code nua,
 * nhu vay muon doi cho luu file chi can sua o Main, va khi viet unit test co the
 * tra vao 1 file tam de test khong dung vao du lieu that.
 *
 * Toan bo hieu biet ve dinh dang file (dau '|') nam gon trong lop nay,
 * o 2 ham toLine() va parseLine() dat canh nhau.
 */
public class CourseFileDAO implements CourseDAO {

    private final String filePath;

    public CourseFileDAO(String filePath) {
        this.filePath = filePath;

        // neu chua co file du lieu thi tao moi kem vai course mau
        File file = new File(filePath);
        if (!file.exists()) {
            createDefaultFile(file);
        }
    }

    private void createDefaultFile(File file) {
        try {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            file.createNewFile();
        } catch (IOException e) {
            throw new DataAccessException("Khong tao duoc file du lieu: " + filePath, e);
        }

        List<Course> sample = new ArrayList<>();
        sample.add(new Course("PRJ101", "Core Java", 3));
        sample.add(new Course("PRN292", ".Net and C#", 3));
        sample.add(new Course("ENI201", "Business English 1", 4));
        writeAll(sample);
    }

    @Override
    public List<Course> findAll() {
        List<Course> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Course course = parseLine(line);
                if (course != null) {
                    list.add(course);
                }
            }
        } catch (IOException e) {
            // khong nuot loi nua: nem len de tang UI bao cho nguoi dung biet,
            // neu tra ve list rong thi nguoi dung tuong la chua co course nao
            throw new DataAccessException("Loi doc file du lieu: " + filePath, e);
        }
        return list;
    }

    @Override
    public Course findByCode(String code) {
        for (Course c : findAll()) {
            if (c.getCode().equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void save(Course course) {
        // ghi them 1 dong moi vao cuoi file, khong doi cac dong cu
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(toLine(course));
            bw.newLine();
        } catch (IOException e) {
            throw new DataAccessException("Loi ghi file du lieu: " + filePath, e);
        }
    }

    // ghi de toan bo danh sach xuong file (chi dung luc tao file mau ban dau)
    private void writeAll(List<Course> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Course c : list) {
                bw.write(toLine(c));
                bw.newLine();
            }
        } catch (IOException e) {
            throw new DataAccessException("Loi ghi file du lieu: " + filePath, e);
        }
    }

    // doi 1 Course thanh 1 dong text de ghi xuong file
    private String toLine(Course course) {
        return course.getCode() + "|" + course.getName() + "|" + course.getCredit();
    }

    // doi 1 dong text doc len thanh Course, dong nao bi loi thi tra ve null de bo qua
    private Course parseLine(String line) {
        line = line.trim();
        if (line.isEmpty()) {
            return null;
        }
        String[] parts = line.split("\\|");
        if (parts.length < 3) {
            return null;
        }
        try {
            int credit = Integer.parseInt(parts[2].trim());
            return new Course(parts[0].trim(), parts[1].trim(), credit);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

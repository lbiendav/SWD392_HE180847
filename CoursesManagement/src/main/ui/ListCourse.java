package ui;

import exception.DataAccessException;
import model.Course;
import service.CourseService;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

/**
 * Man hinh hien thi toan bo danh sach course, sap xep tang dan theo credit.
 */
public class ListCourse extends JFrame {

    private CourseService courseService;
    private JTextArea txtAreaList;

    public ListCourse(CourseService courseService) {
        this.courseService = courseService;
        initComponents();
        loadData();
    }

    private void initComponents() {
        setTitle("List of all courses (order by Credit)");
        setSize(420, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("List of all courses (order by Credit)", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lblTitle, BorderLayout.NORTH);

        txtAreaList = new JTextArea();
        txtAreaList.setEditable(false);
        txtAreaList.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(txtAreaList);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadData() {
        try {
            List<Course> list = courseService.getAllCoursesOrderByCredit();
            StringBuilder sb = new StringBuilder();
            for (Course c : list) {
                sb.append(c.getCode()).append(" | ").append(c.getName())
                        .append(" | ").append(c.getCredit()).append("\n");
            }
            txtAreaList.setText(sb.toString());
        } catch (DataAccessException ex) {
            // doc file loi -> bao loi that, khong hien danh sach rong
            // vi nguoi dung se tuong la chua co course nao
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
        }
    }
}

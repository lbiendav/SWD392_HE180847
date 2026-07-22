package service.impl;

import dao.CourseDAO;
import exception.ValidationException;
import model.Course;
import service.CourseService;
import service.validator.CourseValidator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Cai dat CourseService.
 * Chiu trach nhiem chay cac luat kiem tra roi goi CourseDAO de doc/ghi du lieu,
 * va sap xep khi lay danh sach.
 *
 * Cac luat kiem tra khong con viet cung trong lop nay nua ma duoc truyen tu ngoai vao
 * (danh sach CourseValidator), nen them hay bot luat deu khong phai sua lop nay.
 */
public class CourseServiceImpl implements CourseService {

    private final CourseDAO courseDAO;
    private final List<CourseValidator> validators;

    public CourseServiceImpl(CourseDAO courseDAO, List<CourseValidator> validators) {
        this.courseDAO = courseDAO;
        this.validators = validators;
    }

    @Override
    public List<Course> getAllCoursesOrderByCredit() {
        List<Course> list = courseDAO.findAll();
        Collections.sort(list, new Comparator<Course>() {
            @Override
            public int compare(Course c1, Course c2) {
                return Integer.compare(c1.getCredit(), c2.getCredit());
            }
        });
        return list;
    }

    @Override
    public Course searchByCode(String code) {
        return courseDAO.findByCode(code);
    }

    @Override
    public void addCourse(Course course) throws ValidationException {
        if (course == null) {
            throw new ValidationException("Khong co du lieu course de them!");
        }

        // chay lan luot tat ca cac luat, gap luat nao sai la nem loi ra ngay
        for (CourseValidator validator : validators) {
            validator.validate(course);
        }

        // cat khoang trang thua roi moi luu xuong
        Course clean = new Course(course.getCode().trim(), course.getName().trim(), course.getCredit());
        courseDAO.save(clean);
    }
}

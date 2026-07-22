package service;

import exception.ValidationException;
import model.Course;

import java.util.List;

/**
 * Interface tang Service - xu ly nghiep vu (kiem tra du lieu, sap xep...)
 * truoc khi lam viec voi tang DAO.
 *
 * addCourse() nhan vao 1 Course chu khong nhan 3 chuoi lay tu JTextField nhu truoc:
 * viec doi chuoi nguoi dung go vao thanh so la viec cua tang UI, tang Service
 * chi lo luat nghiep vu. Nho vay sau nay lam them CLI hay web thi van goi duoc
 * ham nay ma khong phai gia vo doi credit ve String.
 */
public interface CourseService {

    // lay danh sach course, sap xep tang dan theo credit
    List<Course> getAllCoursesOrderByCredit();

    // tim course theo code
    Course searchByCode(String code);

    // them 1 course moi, neu vi pham luat nghiep vu thi nem ValidationException
    void addCourse(Course course) throws ValidationException;
}

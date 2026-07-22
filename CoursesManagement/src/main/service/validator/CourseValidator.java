package service.validator;

import exception.ValidationException;
import model.Course;

/**
 * Interface cho 1 luat kiem tra du lieu Course.
 *
 * Truoc day tat ca cac luat nam chung trong 1 chuoi if o CourseServiceImpl.addCourse(),
 * moi lan them luat moi la phai sua vao ham do. Gio moi luat la 1 lop rieng:
 * muon them luat chi can viet them 1 lop implements CourseValidator roi add vao
 * danh sach o Main, khong phai dong vao CourseServiceImpl nua (nguyen ly Open/Closed).
 */
public interface CourseValidator {

    // du lieu sai thi nem ValidationException, dung thi khong lam gi ca
    void validate(Course course) throws ValidationException;
}

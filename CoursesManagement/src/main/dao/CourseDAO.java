package dao;

import model.Course;

import java.util.List;

/**
 * Interface tang Data Access - quy dinh cac thao tac lam viec voi du lieu Course.
 * Tang Service chi lam viec voi interface nay, khong can biet du lieu duoc luu o dau
 * (file, database,...) -> sau nay muon doi cach luu chi can viet lop impl khac.
 *
 * Interface chi giu dung 3 thao tac can thiet (da bo existsByCode vi no chi la
 * findByCode() != null, lop impl nao cung phai viet lai cho thua).
 */
public interface CourseDAO {

    // lay toan bo danh sach course
    List<Course> findAll();

    // tim course theo code, khong tim thay tra ve null
    Course findByCode(String code);

    // luu (them moi) 1 course
    void save(Course course);
}

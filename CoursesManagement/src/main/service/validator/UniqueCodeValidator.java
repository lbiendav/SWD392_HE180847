package service.validator;

import dao.CourseDAO;
import exception.ValidationException;
import model.Course;

/**
 * Luat 3: code khong duoc trung voi course da co trong du lieu.
 * Luat nay can hoi xuong DAO nen nhan CourseDAO qua constructor
 * (van la interface chu khong phai lop impl cu the).
 */
public class UniqueCodeValidator implements CourseValidator {

    private final CourseDAO courseDAO;

    public UniqueCodeValidator(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public void validate(Course course) throws ValidationException {
        // code rong da co RequiredFieldValidator lo, o day khong kiem tra lai
        // (viet vay de khong phu thuoc vao thu tu chay cua cac validator)
        if (course.getCode() == null || course.getCode().trim().isEmpty()) {
            return;
        }

        String code = course.getCode().trim();
        if (courseDAO.findByCode(code) != null) {
            throw new ValidationException("Code \"" + code + "\" da ton tai!");
        }
    }
}

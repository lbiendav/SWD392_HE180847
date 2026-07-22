package service.validator;

import exception.ValidationException;
import model.Course;

/**
 * Luat 1: code va name khong duoc de trong.
 */
public class RequiredFieldValidator implements CourseValidator {

    @Override
    public void validate(Course course) throws ValidationException {
        if (course.getCode() == null || course.getCode().trim().isEmpty()) {
            throw new ValidationException("Code khong duoc de trong!");
        }
        if (course.getName() == null || course.getName().trim().isEmpty()) {
            throw new ValidationException("Name khong duoc de trong!");
        }
    }
}

package service.validator;

import exception.ValidationException;
import model.Course;

/**
 * Luat 2: credit phai la so duong va nho hon hoac bang 33.
 */
public class CreditRangeValidator implements CourseValidator {

    private static final int MAX_CREDIT = 33;

    @Override
    public void validate(Course course) throws ValidationException {
        if (course.getCredit() <= 0) {
            throw new ValidationException("Credit phai la so duong!");
        }
        if (course.getCredit() > MAX_CREDIT) {
            throw new ValidationException("Credit phai nho hon hoac bang " + MAX_CREDIT + "!");
        }
    }
}

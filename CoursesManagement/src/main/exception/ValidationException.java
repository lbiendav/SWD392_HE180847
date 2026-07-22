package exception;

/**
 * Exception rieng dung khi du lieu nguoi dung nhap vao khong hop le
 * (de trong, sai dinh dang, trung ma course...)
 */
public class ValidationException extends Exception {

    public ValidationException(String message) {
        super(message);
    }
}

package exception;

/**
 * Exception dung khi tang DAO khong doc/ghi duoc du lieu
 * (file bi loi, khong co quyen ghi, o dia day...).
 *
 * Truoc day DAO bat IOException roi System.out.println -> chuong trinh Swing khong co
 * console nen nguoi dung khong thay gi, va them course that bai van bao "thanh cong".
 * Gio DAO nem exception nay len de tang UI biet ma bao loi bang JOptionPane.
 *
 * De la RuntimeException (unchecked) cho gon: tang Service khong phai khai bao throws,
 * chi tang UI can catch o cho hien thi.
 */
public class DataAccessException extends RuntimeException {

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}

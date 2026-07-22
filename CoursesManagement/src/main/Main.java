import dao.CourseDAO;
import dao.impl.CourseFileDAO;
import exception.DataAccessException;
import service.CourseService;
import service.impl.CourseServiceImpl;
import service.validator.CourseValidator;
import service.validator.CreditRangeValidator;
import service.validator.RequiredFieldValidator;
import service.validator.UniqueCodeValidator;
import ui.CourseManagement;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Diem bat dau chuong trinh.
 * Day la noi duy nhat noi cac tang lai voi nhau: DAO -> Validator -> Service -> UI.
 *
 * - Muon doi cach luu du lieu (vd sang database): viet lop moi implements CourseDAO
 *   roi doi dong khoi tao courseDAO ben duoi, cac tang khac khong doi.
 * - Muon them 1 luat kiem tra moi (vd code phai dung 6 ky tu): viet them 1 lop
 *   implements CourseValidator roi add vao danh sach validators ben duoi,
 *   khong phai sua CourseServiceImpl.
 * - Muon doi cho luu file: doi hang so DATA_FILE ben duoi.
 */
public class Main {

    private static final String DATA_FILE = "data" + File.separator + "courses.txt";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    CourseDAO courseDAO = new CourseFileDAO(DATA_FILE);

                    List<CourseValidator> validators = new ArrayList<>();
                    validators.add(new RequiredFieldValidator());
                    validators.add(new CreditRangeValidator());
                    validators.add(new UniqueCodeValidator(courseDAO));

                    CourseService courseService = new CourseServiceImpl(courseDAO, validators);

                    CourseManagement frame = new CourseManagement(courseService);
                    frame.setVisible(true);
                } catch (DataAccessException e) {
                    // khong tao/doc duoc file du lieu ngay tu dau thi bao roi thoat luon
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }
            }
        });
    }
}

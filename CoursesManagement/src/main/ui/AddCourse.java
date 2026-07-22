package ui;

import exception.DataAccessException;
import exception.ValidationException;
import model.Course;
import service.CourseService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Man hinh them 1 course moi.
 * Requirement: code/name/credit khong duoc rong, credit phai la so duong va <= 33.
 */
public class AddCourse extends JFrame {

    private CourseService courseService;

    private JTextField txtCode;
    private JTextField txtName;
    private JTextField txtCredit;
    private JButton btnAdd;
    private JButton btnClear;

    public AddCourse(CourseService courseService) {
        this.courseService = courseService;
        initComponents();
        registerEvents();
    }

    private void initComponents() {
        setTitle("Add a new Course");
        setSize(360, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        JLabel lblCode = new JLabel("Code");
        lblCode.setBounds(30, 25, 80, 25);
        add(lblCode);

        txtCode = new JTextField();
        txtCode.setBounds(120, 25, 180, 25);
        add(txtCode);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(30, 70, 80, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(120, 70, 180, 25);
        add(txtName);

        JLabel lblCredit = new JLabel("Credit");
        lblCredit.setBounds(30, 115, 80, 25);
        add(lblCredit);

        txtCredit = new JTextField();
        txtCredit.setBounds(120, 115, 180, 25);
        add(txtCredit);

        btnAdd = new JButton("Add");
        btnAdd.setBounds(70, 165, 90, 30);
        add(btnAdd);

        btnClear = new JButton("Clear");
        btnClear.setBounds(190, 165, 90, 30);
        add(btnClear);
    }

    private void registerEvents() {
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCourse();
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
    }

    private void addCourse() {
        String code = txtCode.getText();
        String name = txtName.getText();
        String creditStr = txtCredit.getText().trim();

        // doi chuoi nguoi dung go vao thanh so la viec cua tang UI,
        // tang Service chi nhan Course da co credit dung kieu int
        if (creditStr.isEmpty()) {
            showError("Credit khong duoc de trong!");
            return;
        }

        int credit;
        try {
            credit = Integer.parseInt(creditStr);
        } catch (NumberFormatException ex) {
            showError("Credit phai la so nguyen!");
            return;
        }

        try {
            courseService.addCourse(new Course(code, name, credit));
            JOptionPane.showMessageDialog(this, "Them course thanh cong!");
            clearForm();
        } catch (ValidationException ex) {
            // du lieu nhap sai luat nghiep vu
            showError(ex.getMessage());
        } catch (DataAccessException ex) {
            // ghi file that bai -> phai bao that, khong duoc bao "thanh cong"
            showError(ex.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Loi", JOptionPane.ERROR_MESSAGE);
    }

    private void clearForm() {
        txtCode.setText("");
        txtName.setText("");
        txtCredit.setText("");
        txtCode.requestFocus();
    }
}

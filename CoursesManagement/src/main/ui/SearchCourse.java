package ui;

import exception.DataAccessException;
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
 * Man hinh tim course theo code.
 * Course name va Credit chi hien thi ket qua (read only), khong cho sua.
 */
public class SearchCourse extends JFrame {

    private CourseService courseService;

    private JTextField txtCode;
    private JButton btnSearch;
    private JTextField txtName;
    private JTextField txtCredit;

    public SearchCourse(CourseService courseService) {
        this.courseService = courseService;
        initComponents();
        registerEvents();
    }

    private void initComponents() {
        setTitle("Search Course by Code");
        setSize(380, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        JLabel lblCode = new JLabel("Enter code");
        lblCode.setBounds(30, 25, 90, 25);
        add(lblCode);

        txtCode = new JTextField();
        txtCode.setBounds(130, 25, 110, 25);
        add(txtCode);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(250, 24, 80, 27);
        add(btnSearch);

        JLabel lblName = new JLabel("Course name");
        lblName.setBounds(30, 80, 90, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(130, 80, 200, 25);
        txtName.setEditable(false);
        add(txtName);

        JLabel lblCredit = new JLabel("Credit");
        lblCredit.setBounds(30, 125, 90, 25);
        add(lblCredit);

        txtCredit = new JTextField();
        txtCredit.setBounds(130, 125, 200, 25);
        txtCredit.setEditable(false);
        add(txtCredit);
    }

    private void registerEvents() {
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCourse();
            }
        });
    }

    private void searchCourse() {
        String code = txtCode.getText().trim();

        if (code.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhap code de tim kiem!", "Loi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Course course = courseService.searchByCode(code);
            if (course == null) {
                JOptionPane.showMessageDialog(this, "Khong tim thay course co code: " + code);
                txtName.setText("");
                txtCredit.setText("");
            } else {
                txtName.setText(course.getName());
                txtCredit.setText(String.valueOf(course.getCredit()));
            }
        } catch (DataAccessException ex) {
            // doc file loi -> bao loi that, khong bao "khong tim thay"
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
        }
    }
}

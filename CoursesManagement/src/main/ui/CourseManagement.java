package ui;

import service.CourseService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Man hinh chinh (main frame) cua chuong trinh Courses Management.
 * Tu day nguoi dung chon 1 trong 4 chuc nang.
 */
public class CourseManagement extends JFrame {

    private CourseService courseService;

    private JButton btnAdd;
    private JButton btnDisplay;
    private JButton btnSearch;
    private JButton btnExit;

    public CourseManagement(CourseService courseService) {
        this.courseService = courseService;
        initComponents();
        registerEvents();
    }

    private void initComponents() {
        setTitle("Courses Management");
        setSize(340, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        JLabel lblTitle = new JLabel("Courses Management", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitle.setBounds(20, 15, 280, 25);
        add(lblTitle);

        btnAdd = new JButton("Add a new Course");
        btnAdd.setBounds(40, 60, 250, 30);
        add(btnAdd);

        btnDisplay = new JButton("Display all Courses");
        btnDisplay.setBounds(40, 100, 250, 30);
        add(btnDisplay);

        btnSearch = new JButton("Search Course by Course Code");
        btnSearch.setBounds(40, 140, 250, 30);
        add(btnSearch);

        btnExit = new JButton("Exit Application");
        btnExit.setBounds(40, 180, 250, 30);
        add(btnExit);
    }

    private void registerEvents() {
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCourse addCourse = new AddCourse(courseService);
                addCourse.setVisible(true);
            }
        });

        btnDisplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListCourse listCourse = new ListCourse(courseService);
                listCourse.setVisible(true);
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchCourse searchCourse = new SearchCourse(courseService);
                searchCourse.setVisible(true);
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        CourseManagement.this,
                        "Ban co chac muon thoat chuong trinh?",
                        "Xac nhan",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }
}

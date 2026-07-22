package model;

import java.io.Serializable;

/**
 * Course - lop du lieu (POJO) mo ta thong tin 1 khoa hoc
 * Gom 3 thuoc tinh: code, name, credit
 *
 * Lop nay chi giu du lieu, khong biet gi ve chuyen luu o dau hay luu kieu gi
 * (truoc day co ham toLine() ghi ra dinh dang file text -> da chuyen xuong CourseFileDAO,
 * vi do la viec cua tang DAO chu khong phai cua model).
 */
public class Course implements Serializable {

    private String code;
    private String name;
    private int credit;

    public Course() {
    }

    public Course(String code, String name, int credit) {
        this.code = code;
        this.name = name;
        this.credit = credit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return code + " | " + name + " | " + credit;
    }
}

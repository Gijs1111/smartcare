package com.example.gijsdewert.smartcare.Domains;

/**
 * Created by gijsdewert on 13-12-17.
 */

public class EmployeeItem {

    private int resIdThumbnail;
    private String employeeName;

    public EmployeeItem(int resIdThumbnail, String employeeName) {
        this.resIdThumbnail = resIdThumbnail;
        this.employeeName = employeeName;
    }

    public int getResIdThumbnail() {
        return resIdThumbnail;
    }

    public void setResIdThumbnail(int resIdThumbnail) {
        this.resIdThumbnail = resIdThumbnail;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}

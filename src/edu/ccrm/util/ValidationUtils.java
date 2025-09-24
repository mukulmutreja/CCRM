package ccrm.domain;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String regNo;
    private List<Enrollment> enrollments;

    public Student(String id, String regNo, String fullName, String email) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.enrollments = new ArrayList<>();
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void addEnrollment(Enrollment e) {
        enrollments.add(e);
    }

    public void removeEnrollment(Enrollment e) {
        enrollments.remove(e);
    }

    public double calculateGPA() {
        double totalPoints = 0;
        int totalCredits = 0;

        for (Enrollment e : enrollments) {
            if (e.getGrade() != null) {
                totalPoints += e.getGrade().getPoints() * e.getCourse().getCredits();
                totalCredits += e.getCourse().getCredits();
            }
        }

        if (totalCredits == 0) {
            return 0;
        }
        return totalPoints / totalCredits;
    }

    @Override
    public String getDetails() {
        return fullName + " (" + regNo + ") GPA: " + String.format("%.2f", calculateGPA());
    }

    @Override
    public String toString() {
        return "Student[ID: " + id + ", Reg: " + regNo + ", Name: " + fullName + ", Email: " + email +
               ", Active: " + (active ? "Yes" : "No") + "]";
    }
}

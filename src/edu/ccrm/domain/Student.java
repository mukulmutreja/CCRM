package ccrm.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student extends Person {
    private String regNo;
    private List<Enrollment> enrollments;

    public Student(String id, String regNo, String fullName, String email) {
        super(id, fullName, email);
        this.regNo = Objects.requireNonNull(regNo, "Registration number cannot be null");
        this.enrollments = new ArrayList<Enrollment>();
    }

    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }

    public List<Enrollment> getEnrollments() { return enrollments; }

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public void removeEnrollment(Enrollment enrollment) {
        enrollments.remove(enrollment);
    }

    public double calculateGPA() {
        if (enrollments.size() == 0) {
            return 0.0;
        }

        double totalPoints = 0.0;
        int totalCredits = 0;

        for (int i = 0; i < enrollments.size(); i++) {
            Enrollment e = enrollments.get(i);
            if (e.getGrade() != null) {
                totalPoints += e.getGrade().getPoints() * e.getCourse().getCredits();
                totalCredits += e.getCourse().getCredits();
            }
        }

        if (totalCredits > 0) {
            return totalPoints / totalCredits;
        } else {
            return 0.0;
        }
    }

    @Override
    public String getDetails() {
        return "Student: " + fullName + " (Reg: " + regNo + "), GPA: " + String.format("%.2f", calculateGPA());
    }

    @Override
    public String toString() {
        String status = active ? "Yes" : "No";
        return "Student[ID: " + id + ", Reg: " + regNo + ", Name: " + fullName + ", Email: " + email + ", Active: " + status + "]";
    }
}

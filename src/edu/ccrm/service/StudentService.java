package ccrm.service;

import ccrm.domain.Student;
import ccrm.domain.Enrollment;
import ccrm.domain.Course;
import ccrm.domain.Grade;
import ccrm.util.ValidationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class StudentService implements Searchable<Student> {
    private List<Student> students = new ArrayList<>();
    private static final int MAX_CREDITS_PER_SEMESTER = 18;

    public void addStudent(Student student) {
        ValidationUtils.validateNotNull(student, "Student cannot be null");

        if (findById(student.getId()).isPresent()) {
            throw new IllegalArgumentException("Student with ID " + student.getId() + " already exists");
        }

        students.add(student);
    }

    public void updateStudent(String id, String fullName, String email) {
        ValidationUtils.validateNotNull(id, "Student ID cannot be null");

        Student student = findById(id).orElse(null);
        if (student == null) {
            throw new IllegalArgumentException("Student with ID " + id + " not found");
        }

        if (fullName != null && fullName.trim().length() > 0) {
            student.setFullName(fullName);
        }

        if (email != null && email.trim().length() > 0) {
            if (ValidationUtils.isValidEmail(email)) {
                student.setEmail(email);
            } else {
                throw new IllegalArgumentException("Invalid email format");
            }
        }
    }

    public void deactivateStudent(String id) {
        Student student = findById(id).orElse(null);
        if (student == null) {
            throw new IllegalArgumentException("Student with ID " + id + " not found");
        }
        student.setActive(false);
    }

    public void enrollInCourse(String studentId, Course course) {
        ValidationUtils.validateNotNull(studentId, "Student ID cannot be null");
        ValidationUtils.validateNotNull(course, "Course cannot be null");

        Student student = findById(studentId).orElse(null);
        if (student == null) {
            throw new IllegalArgumentException("Student with ID " + studentId + " not found");
        }

        boolean already = false;
        for (Enrollment e : student.getEnrollments()) {
            if (e.getCourse().equals(course)) {
                already = true;
                break;
            }
        }
        if (already) {
            throw new IllegalStateException("Student is already enrolled in this course");
        }

        int currentCredits = 0;
        for (Enrollment e : student.getEnrollments()) {
            currentCredits += e.getCourse().getCredits();
        }

        if (currentCredits + course.getCredits() > MAX_CREDITS_PER_SEMESTER) {
            throw new IllegalStateException("Credit limit exceeded. Maximum allowed: " + MAX_CREDITS_PER_SEMESTER);
        }

        Enrollment enrollment = new Enrollment(student, course);
        student.addEnrollment(enrollment);
    }

    public void recordGrade(String studentId, String courseCode, Grade grade) {
        ValidationUtils.validateNotNull(studentId, "Student ID cannot be null");
        ValidationUtils.validateNotNull(courseCode, "Course code cannot be null");
        ValidationUtils.validateNotNull(grade, "Grade cannot be null");

        Student student = findById(studentId).orElse(null);
        if (student == null) {
            throw new IllegalArgumentException("Student with ID " + studentId + " not found");
        }

        Enrollment found = null;
        for (Enrollment e : student.getEnrollments()) {
            if (e.getCourse().getCode().equals(courseCode)) {
                found = e;
                break;
            }
        }

        if (found != null) {
            found.setGrade(grade);
        } else {
            throw new IllegalArgumentException("Student is not enrolled in course " + courseCode);
        }
    }

    public String generateTranscript(String studentId) {
        Student student = findById(studentId).orElse(null);
        if (student == null) {
            throw new IllegalArgumentException("Student with ID " + studentId + " not found");
        }

        StringBuilder text = new StringBuilder();
        text.append("Transcript for: ").append(student.getFullName()).append("\n");
        text.append("Registration No: ").append(student.getRegNo()).append("\n");
        text.append("GPA: ").append(String.format("%.2f", student.calculateGPA())).append("\n\n");
        text.append("Courses:\n");

        for (Enrollment e : student.getEnrollments()) {
            text.append("- ")
                .append(e.getCourse().getCode())
                .append(": ")
                .append(e.getCourse().getTitle())
                .append(" (")
                .append(e.getCourse().getCredits())
                .append(" credits) - ")
                .append(e.getGrade() != null ? e.getGrade() : "Not graded")
                .append("\n");
        }

        return text.toString();
    }

    @Override
    public List<Student> search(Predicate<Student> predicate) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (predicate.test(s)) {
                result.add(s);
            }
        }
        return result;
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    @Override
    public Optional<Student> findById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    public Optional<Student> findByRegNo(String regNo) {
        for (Student s : students) {
            if (s.getRegNo().equals(regNo)) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }
}

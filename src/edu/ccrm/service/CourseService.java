package ccrm.service;

import ccrm.domain.Course;
import ccrm.domain.Semester;
import ccrm.util.ValidationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CourseService implements Searchable<Course> {
    private List<Course> courses;

    public CourseService() {
        courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        ValidationUtils.validateNotNull(course, "Course cannot be null");
        if (findById(course.getCode()).isPresent()) {
            throw new IllegalArgumentException("Course with code " + course.getCode() + " already exists");
        }
        courses.add(course);
    }

    public void updateCourse(String code, String title, Integer credits, String instructor, Semester semester, String department) {
        ValidationUtils.validateNotNull(code, "Course code cannot be null");

        Course c = findById(code).orElseThrow(() -> new IllegalArgumentException("Course with code " + code + " not found"));

        if (title != null && !title.trim().isEmpty()) {
            c.setTitle(title);
        }
        if (credits != null && credits > 0) {
            c.setCredits(credits);
        }
        if (instructor != null) {
            c.setInstructor(instructor);
        }
        if (semester != null) {
            c.setSemester(semester);
        }
        if (department != null && !department.trim().isEmpty()) {
            c.setDepartment(department);
        }
    }

    public void deactivateCourse(String code) {
        Course c = findById(code).orElseThrow(() -> new IllegalArgumentException("Course with code " + code + " not found"));
        c.setActive(false);
    }

    public List<Course> findByInstructor(String instructor) {
        return search(c -> c.getInstructor() != null && c.getInstructor().equalsIgnoreCase(instructor));
    }

    public List<Course> findByDepartment(String dept) {
        return search(c -> c.getDepartment() != null && c.getDepartment().equalsIgnoreCase(dept));
    }

    public List<Course> findBySemester(Semester sem) {
        return search(c -> c.getSemester() == sem);
    }

    @Override
    public List<Course> search(Predicate<Course> p) {
        return courses.stream().filter(p).collect(Collectors.toList());
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(courses);
    }

    @Override
    public Optional<Course> findById(String code) {
        return courses.stream().filter(c -> c.getCode().equals(code)).findFirst();
    }
}

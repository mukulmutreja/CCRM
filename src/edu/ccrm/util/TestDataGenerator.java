package ccrm.util;

import ccrm.domain.Student;
import ccrm.domain.Course;
import ccrm.domain.Semester;
import ccrm.service.StudentService;
import ccrm.service.CourseService;

public class TestDataGenerator {

    public static void generateTestData(StudentService studentService, CourseService courseService) {
        // students
        Student s1 = new Student("S001", "2023001", "John Doe", "john.doe@university.edu");
        Student s2 = new Student("S002", "2023002", "Jane Smith", "jane.smith@university.edu");
        Student s3 = new Student("S003", "2023003", "Bob Johnson", "bob.johnson@university.edu");

        studentService.addStudent(s1);
        studentService.addStudent(s2);
        studentService.addStudent(s3);

        // courses
        Course c1 = new Course.Builder("CS101", "Introduction to Programming")
                .credits(3)
                .instructor("Dr. Alice Brown")
                .semester(Semester.FALL)
                .department("Computer Science")
                .build();

        Course c2 = new Course.Builder("MATH201", "Calculus I")
                .credits(4)
                .instructor("Prof. Charlie Davis")
                .semester(Semester.FALL)
                .department("Mathematics")
                .build();

        Course c3 = new Course.Builder("ENG101", "English Composition")
                .credits(3)
                .instructor("Dr. Eva Wilson")
                .semester(Semester.SPRING)
                .department("English")
                .build();

        courseService.addCourse(c1);
        courseService.addCourse(c2);
        courseService.addCourse(c3);
    }
}

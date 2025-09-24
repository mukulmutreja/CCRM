package ccrm.io;

import ccrm.domain.Student;
import ccrm.domain.Course;
import ccrm.domain.Semester;
import ccrm.service.StudentService;
import ccrm.service.CourseService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class ImportExportService {
    private StudentService studentService;
    private CourseService courseService;

    public ImportExportService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public void importStudentsFromCSV(Path filePath) throws IOException {
        try (Stream<String> stream = Files.lines(filePath)) {
            stream.skip(1).forEach(line -> {
                String[] arr = line.split(",");
                if (arr.length >= 4) {
                    String id = arr[0].trim();
                    String regNo = arr[1].trim();
                    String name = arr[2].trim();
                    String email = arr[3].trim();
                    Student s = new Student(id, regNo, name, email);
                    studentService.addStudent(s);
                }
            });
        }
    }

    public void importCoursesFromCSV(Path filePath) throws IOException {
        try (Stream<String> stream = Files.lines(filePath)) {
            stream.skip(1).forEach(line -> {
                String[] arr = line.split(",");
                if (arr.length >= 6) {
                    String code = arr[0].trim();
                    String title = arr[1].trim();
                    int credits = Integer.parseInt(arr[2].trim());
                    String instructor = arr[3].trim();
                    Semester sem = Semester.valueOf(arr[4].trim().toUpperCase());
                    String dept = arr[5].trim();

                    Course c = new Course.Builder(code, title)
                            .credits(credits)
                            .instructor(instructor)
                            .semester(sem)
                            .department(dept)
                            .build();

                    courseService.addCourse(c);
                }
            });
        }
    }

    public void exportStudentsToCSV(Path filePath) throws IOException {
        List<String> lines = studentService.findAll().stream()
                .map(s -> s.getId() + "," +
                        s.getRegNo() + "," +
                        s.getFullName() + "," +
                        s.getEmail() + "," +
                        (s.isActive() ? "Active" : "Inactive"))
                .collect(Collectors.toList());

        lines.add(0, "ID,RegistrationNo,FullName,Email,Status");

        Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void exportCoursesToCSV(Path filePath) throws IOException {
        List<String> lines = courseService.findAll().stream()
                .map(c -> c.getCode() + "," +
                        c.getTitle() + "," +
                        c.getCredits() + "," +
                        c.getInstructor() + "," +
                        c.getSemester() + "," +
                        c.getDepartment() + "," +
                        (c.isActive() ? "Active" : "Inactive"))
                .collect(Collectors.toList());

        lines.add(0, "Code,Title,Credits,Instructor,Semester,Department,Status");

        Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void exportEnrollmentsToCSV(Path filePath) throws IOException {
        List<String> lines = studentService.findAll().stream()
                .flatMap(s -> s.getEnrollments().stream()
                        .map(e -> s.getId() + "," +
                                e.getCourse().getCode() + "," +
                                e.getEnrollmentDate() + "," +
                                (e.getGrade() != null ? e.getGrade() : "Not graded") + "," +
                                (e.getGrade() != null ? e.getGrade().getPoints() : "0.0")))
                .collect(Collectors.toList());

        lines.add(0, "StudentID,CourseCode,EnrollmentDate,Grade,GradePoints");

        Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}

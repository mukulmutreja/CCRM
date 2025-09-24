package ccrm;

import ccrm.cli.CLI;
import ccrm.config.AppConfig;
import ccrm.service.StudentService;
import ccrm.service.CourseService;
import ccrm.util.TestDataGenerator;

public class Main {
    public static void main(String[] args) {

        System.out.println("Campus Course & Records Manager");
        System.out.println("==============================");

        // Load configuration using singleton
        AppConfig config = AppConfig.getInstance();

        // Initialize service classes
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();

        // Generate some test data
        TestDataGenerator.generateTestData(studentService, courseService);

        // Create CLI object and start program
        CLI cli = new CLI(studentService, courseService);
        cli.start();
    }
}

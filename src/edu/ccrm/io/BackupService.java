package ccrm.io;

import ccrm.config.AppConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class BackupService {
    private AppConfig config;
    private ImportExportService importExportService;

    public BackupService(ImportExportService importExportService) {
        this.config = AppConfig.getInstance();
        this.importExportService = importExportService;
    }

    public Path createBackup() throws IOException {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path backupDir = config.getBackupDirectory().resolve("backup_" + time);

        Files.createDirectories(backupDir);

        Path studentsFile = backupDir.resolve("students.csv");
        Path coursesFile = backupDir.resolve("courses.csv");
        Path enrollmentsFile = backupDir.resolve("enrollments.csv");

        importExportService.exportStudentsToCSV(studentsFile);
        importExportService.exportCoursesToCSV(coursesFile);
        importExportService.exportEnrollmentsToCSV(enrollmentsFile);

        return backupDir;
    }

    public long getBackupSize(Path directory) throws IOException {
        try (Stream<Path> stream = Files.walk(directory)) {
            return stream
                    .filter(path -> Files.isRegularFile(path))
                    .mapToLong(this::getFileSize)
                    .sum();
        }
    }

    private long getFileSize(Path path) {
        try {
            return Files.size(path);
        } catch (IOException e) {
            System.out.println("Error reading file size: " + path);
            return 0;
        }
    }

    public void recursiveListFiles(Path directory, int depth) throws IOException {
        try (Stream<Path> stream = Files.walk(directory, depth)) {
            stream.forEach(path -> {
                if (Files.isRegularFile(path)) {
                    try {
                        long s = Files.size(path);
                        System.out.println(path.toString() + " - " + s + " bytes");
                    } catch (IOException e) {
                        System.out.println("Error with file: " + path.toString());
                    }
                }
            });
        }
    }
}

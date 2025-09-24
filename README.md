# Campus Course & Records Manager (CCRM)🎓

This is a Complete Java console project designed to handle basic student, course, enrollment, and grade records for a college or university.

---

## ✨ Main Things It Can Do

* **Student Management:** Add, update, remove, and search for student records.
* **Course Management:** Add, update, remove, and search for available courses.
* **Enrollment System:** Enroll students in courses, enforcing a maximum credit limit.
* **Grading:** Save student grades for courses and automatically calculate GPA.
* **Data Handling:** Import and export system data from/to CSV files.
* **Backup & Restore:** Create backups of the current data and restore from a backup file.
* **Reporting:** Generate various small reports, like student transcripts.

---

## 🛠️ Java Stuff Used

This project demonstrates a wide range of modern and core Java features:

* **Object-Oriented Programming (OOP):** Core concepts like classes, objects, inheritance, and polymorphism are used throughout.
* **Exception Handling:** Robust error management using `try-catch` blocks and custom exceptions.
* **File I/O:** Modern file reading and writing operations using Java NIO.2.
* **Java Streams API:** Efficiently process collections of data for filtering and transformations.
* **Date/Time API:** Manages dates and times using the `java.time` package.
* **Advanced Java Constructs:**
    * Interfaces and abstract classes for defining contracts and base behaviors.
    * Nested classes, including the use of the **Builder pattern**.
    * Enums with values and constructors for type-safe constants.
    * Lambda functions and functional interfaces for concise code.
* **Design Patterns:** Implements patterns like **Singleton** and **Builder** for robust and scalable architecture.
* **Recursion:** Utilized in specific parts of the application for solving complex problems.

---

## 📜 A Small History of Java

* **1995:** Java 1.0 is first released by Sun Microsystems.
* **1997:** Java 1.1 adds key features like inner classes and JDBC for database connectivity.
* **2004:** Java 5 (J2SE 5.0) is a major release, adding generics, annotations, and autoboxing.
* **2014:** Java 8 revolutionizes the language with lambdas, the Streams API, and a new Date/Time API.
* **2017:** Java 9 introduces the Java Platform Module System (JPMS) and the JShell REPL.
* **2021:** Java 17 (LTS) brings sealed classes and pattern matching for `instanceof`.

---

## 🚀 Java Setup

### 1. Install the JDK (Windows)
1.  Download the Java Development Kit (JDK) from the official Oracle website.
2.  Follow the installation wizard's step-by-step instructions.
3.  Set the `JAVA_HOME` environment variable to your JDK installation directory.
4.  Add the JDK's `bin` folder to your system's `PATH` variable.

### 2. Install Eclipse IDE
1.  Download the **Eclipse IDE for Java Developers** package.
2.  Extract the downloaded zip file and run `eclipse.exe`.
3.  Create a new Java project.
4.  Ensure the project's build path is configured to use the installed JDK (Java 8 or higher).
5.  Create your packages and classes within the project structure.

---

## ▶️ Running the Program

1.  Clone or download this repository to your local machine.
2.  Open the project folder in Eclipse or another Java IDE.
3.  Verify that the project is configured to use **Java 8 or a higher version**.
4.  Locate and run the `Main.java` file to start the application.

---

## 📝 Example Use

Once the program is running, you will see a console menu. From there, you can perform various actions by selecting the corresponding option:

* Add new students and courses to the system.
* Register existing students into available courses.
* Enter grades for enrolled students and check their GPA.
* Print a student's academic transcript.
* Export data to a CSV file or import data from one.

* Perform a system-wide data backup or restore from a previous backup.

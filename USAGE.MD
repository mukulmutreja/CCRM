***

# Campus Course & Records Manager – User Guide 📘

This guide provides instructions on how to use the Student and Course Record System from the command line interface.

---

## 🚀 Starting the Program

1.  Open the project in your Java IDE (e.g., Eclipse).
2.  Locate and run the `Main.java` file.
3.  A text-based menu will appear in the console.
4.  Enter the number corresponding to the action you wish to perform and press `Enter`.

---

## 📖 Main Features

### 👨‍🎓 Student Options

* **Add a Student:** Create a new student record. You must provide a unique ID and registration number.
* **Edit Student Info:** Update details for an existing student, such as their name or email address.
* **Deactivate a Student:** Mark a student as inactive (this is a soft delete and the record is not permanently removed).
* **Search Students:** Find students using various filters like name, ID, or registration number.
* **View Transcript:** Display a specific student's academic transcript, including their courses, grades, and calculated GPA.

### 📚 Course Options

* **Add a Course:** Create a new course with details like course code, title, credits, teacher, semester, and department.
* **Change Course Details:** Modify the information for an existing course.
* **Deactivate a Course:** Mark a course as inactive so it's not available for new enrollments.
* **Look Up Courses:** Search for courses by filtering by teacher, department, or semester.

### 📝 Enrollment Options

* **Register a Student:** Enroll a student into a course. The system enforces a rule that a student cannot be enrolled in more than **18 credits** in a single semester.
* **Remove a Student from a Course:** Withdraw a student from a course they are currently enrolled in.
* **Show All Enrollments:** Display a comprehensive list of all current enrollments.

### 💯 Grade Options

* **Enter a Grade:** Assign a grade to a student for a specific course they have completed.
* **Display Grade Statistics:** View statistics and the distribution of grades for a course or across the system.

---

## 📂 Working with Files

### 📥 Import Data

1.  Place your `students.csv` or `courses.csv` files inside the `data/` folder in the project directory.
2.  From the main menu, choose the import option for students or courses.
3.  The data from the CSV files will be loaded into the system.
4.  **Note:** The CSV file must be formatted correctly, following the structure of the sample files provided.

### 📤 Export Data

* You can save the current state of students, courses, and enrollments to new CSV files.
* This feature is useful for creating manual backups or for analyzing the data in a spreadsheet application like Microsoft Excel or Google Sheets.

### 💾 Backup Data

* The system can create a timestamped backup of all current data.
* These backup files are saved inside the `data/backups/` directory.
* From the menu, you can also view a list of all existing backups and check their file sizes.

---

## 📊 Reports You Can Generate

The system can generate several useful reports, including:

* A list of students ranked by their GPA.
* A report showing the total enrollment numbers for each course.
* Statistical summaries for each academic department.

---

## 💡 Example Dataset

To help you get started, the program comes pre-loaded with a sample dataset:

* **3 sample students**
* **3 sample courses**

You can immediately start interacting with this data, or you can add more records using the menu options or by importing your own CSV files.
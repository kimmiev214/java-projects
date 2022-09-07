import java.util.List;

/**
 * Created by Kimberly Villatoro on 9/5/2022
 * This class is used to keep track of a school with multiple teachers and students.
 *
 */

public class School {

    private List<Teacher> teachers;
    private List<Student> students;

    private double totalMoneyEarned, totalMoneySpent;


    /**
     * New school is created.
     * @param teachers list of teachers in school.
     * @param students list of students in school.
     */
    public School(List<Teacher> teachers, List<Student> students) {
        totalMoneyEarned = 0;
        totalMoneySpent = 0;

        this.teachers = teachers;
        this.students = students;
    }


    /**
     * @return list of students in the school.
     */
    public List<Student> getStudents() {
        return students;
    }


    /**
     * @return list of teachers in the school.
     */
    public List<Teacher> getTeachers() {
        return teachers;
    }

    /**
     * @return amount of total money earned by the school.
     */
    public double getTotalMoneyEarned() {
        return totalMoneyEarned;
    }


    /**
     * @return amount of total money spent by the school.
     */
    public double getTotalMoneySpent() {
        return totalMoneySpent;
    }


    /**
     * @param students add the students in the school.
     */
    public void addStudents(Student students) {
        this.students.add(students);
    }


    /**
     * @param teachers add the teachers in the school.
     */
    public void addTeachers(Teacher teachers) {
        this.teachers.add(teachers);
    }


    /**
     * Adds the total money earned by the school.
     * @param totalMoneyEarned
     */
    public void updateTotalMoneyEarned(double totalMoneyEarned) {
        this.totalMoneyEarned += totalMoneyEarned;
    }


    /**
     * Adds the total money spent by the school. Determined by the amount spent on teachers salaries.
     * @param totalMoneySpent
     */
    public void updateTotalMoneySpent(double totalMoneySpent) {
        this.totalMoneySpent = totalMoneyEarned - totalMoneySpent;
    }
} //end School class

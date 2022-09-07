/**
 * Created by Kimberly Villatoro on 9/5/2022
 * This class is used to keep track of the students in a school system.
 *
 */

public class Student {

    private String name, id;
    private int gradeLevel;
    private double tuitionPaid, tuitionTotal;


    /**
     * To create a new student by initializing.
     * Tuition cost for school is a base amount of $30,000 per school year.
     * Tuition paid is initially 0.
     * @param name name of the student.
     * @param id id for a student: unique.
     * @param gradeLevel grade of the student.
     */
    public Student(String name, String id, int gradeLevel) {
        tuitionPaid = 0;
        tuitionTotal = 30000;

        this.name = name;
        this.id = id;
        this.gradeLevel = gradeLevel;
    }

    public void setTuitionPaid(double tuitionPaid) {
        this.tuitionPaid = tuitionPaid;
    }

    /**
     * Updates the amount of tuition that is left after updating total minus tuition already paid.
     */
    public void updateTuitionTotal() {
        tuitionTotal -= tuitionPaid;
    }

    /**
     * Used to update student's grade level.
     * @param gradeLevel student's current grade level.
     */
    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    /**
     * @return name of student.
     */
    public String getName() {
        return name;
    }

    /**
     * @return student id.
     */
    public String getId() {
        return id;
    }


    /**
     * @return student's current grade level
     */
    public int getGradeLevel() {
        return gradeLevel;
    }


    /**
     * @return amount of tuition student has paid.
     */
    public double getTuitionPaid() {
        return tuitionPaid;
    }


    /**
     * @return tuition amount of student
     */
    public double getTuitionTotal() {
        return tuitionTotal;
    }
} //end Student class

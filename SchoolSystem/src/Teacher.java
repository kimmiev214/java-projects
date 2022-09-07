/**
 * Created by Kimberly Villatoro on 9/5/2022
 * This class is used to keep track of the teachers in a school system.
 *
 */

public class Teacher {
    private String name, id;
    private double salary;


    /**
     * To create a new teacher by initializing.
     * @param name teacher's name.
     * @param id teacher's unique id.
     * @param salary teacher's annual salary.
     */
    public Teacher(String name, String id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;

    }


    /**
     * Set teacher's annual salary.
     * @param salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }


    /**
     * @return the name.
     */
    public String getName() {
        return name;
    }


    /**
     * @return id.
     */
    public String getId() {
        return id;
    }


    /**
     * @return salary.
     */
    public double getSalary() {
        return salary;
    }
} //end Teacher class

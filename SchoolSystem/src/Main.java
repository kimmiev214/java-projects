import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kimberly Villatoro on 09/05/2022
 * This is the test class for a school management system.
 */
public class Main {

    public static void main(String[] args) {
        Teacher wolfgang = new Teacher("Wolfgang", "5643", 27800);
        Teacher foulkes = new Teacher("Foulkes", "8890", 42000);
        Teacher vaughn = new Teacher("Vaughn", "6019", 38000);

        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(wolfgang);
        teacherList.add(foulkes);
        teacherList.add(vaughn);

        Student kimberly = new Student("Kimberly", "2854", 16);
        Student marquis = new Student("Marquis", "9865", 16);
        Student longo = new Student("Longo", "7890", 16);

        List<Student> studentList = new ArrayList<>();
        studentList.add(kimberly);
        studentList.add(marquis);
        studentList.add(longo);

        School vmu = new School(teacherList, studentList);

        kimberly.setTuitionPaid(5000);
        kimberly.updateTuitionTotal();

        System.out.println(kimberly.getTuitionTotal());

        //System.out.println(vmu.getTotalMoneyEarned());


    }
}

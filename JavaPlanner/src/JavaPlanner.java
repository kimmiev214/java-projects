import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * PROJECT: JavaPlanner.java
 * AUTHOR: Kimberly Villatoro
 * DATE: 01/26/2023
 * DESCRIPTION:
 * This project is a text-based planner. Functionalities include adding task or events, viewing schedule, viewing schedule on a date, and deleting your schedule. 
 * The menu is displayed and the user must select an option (a number between 0 and 4).
 * The action corresponding to the selection is performed, then the menu is displayed again and the user can choose another option.
 *
 */
public class JavaPlanner {
    File file = new File("calendar.txt");
    PlannerEvents events;
    List<PlannerEvents> listOfEvents = new ArrayList<>();
    List<PlannerEvents> eventsList;
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public static void main(String[] args) {
        JavaPlanner runJavaPlanner = new JavaPlanner();
        runJavaPlanner.menu();
    }//end main


    public void menu() {
        Scanner in = new Scanner(System.in);
        System.out.println("\n--------- Welcome to Java Planner ---------");
        System.out.println("\nMENU OPTIONS");
        System.out.println("0 - Exit");
        System.out.println("1 - Create new task or event");
        System.out.println("2 - Check your tasks or events");
        System.out.println("3 - Check your schedule for a specific day");
        System.out.println("4 - Delete schedule");

        int optionSelected = in.nextInt();

        switch (optionSelected) {
            case 0:
                System.exit(0);//option ends the program
            case 1:
                addEvent();
                menu();
            case 2:
                readEvent();
                menu();
            case 3:
                printTodayPlans();
                menu();
            case 4:
                safeDelete();
                menu();
            case 5:
                //removeEvent();
                menu();
            case 6:
                menu();
            case 7:
                menu();
            case 8:
                menu();
        }
    }


    public void printTodayPlans() {
        String todaysSchedule = "";

        Scanner in = new Scanner(System.in);

        System.out.println("Enter date you would like to view (e.g 2023-02-22)");
        LocalDate date = LocalDate.parse(in.next(), DateTimeFormatter.ISO_LOCAL_DATE);

        try {
            fileExists(file);
            if(file.length() != 0) {
                fileInputStream = new FileInputStream(file);
                objectInputStream = new ObjectInputStream(fileInputStream);
                eventsList = (List<PlannerEvents>) objectInputStream.readObject();
                objectInputStream.close();

                for (PlannerEvents plannerEvents : eventsList) {
                    if (date.equals(plannerEvents.getDate())) {
                        todaysSchedule = plannerEvents.toString();
                        if (!todaysSchedule.equals("")) {
                            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("MY SCHEDULE : ");
                            System.out.println(todaysSchedule);
                            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                        }
                    }
                }
                if (todaysSchedule.equals("")) {
                    System.out.println("No task or event found for that date");
                }
            } else {
                System.out.println("Your schedule is empty. Try adding tasks or events.");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Need to add error handling if file does not exist
     */
    public void readEvent() {

        try {
            fileExists(file);
            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
            eventsList = (List<PlannerEvents>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (PlannerEvents plannerEvents: eventsList) {
            System.out.println(plannerEvents);
        }
    }


    /**
     * CREATE EVENT
     */
    public void addEvent() {

        try {
            fileExists(file);
            Scanner in = new Scanner(System.in);
            System.out.println("Insert a description for event. (e.g go to gym) \n");
            String description = in.nextLine();
            System.out.println("Insert date for event in yyyy-MM-dd format. (e.g 2023-02-22) \n");
            LocalDate date = LocalDate.parse(in.next(), DateTimeFormatter.ISO_LOCAL_DATE);
            System.out.println("Insert a time for event HH:MM format. (e.g 14:00) \n");
            LocalTime time = LocalTime.parse(in.next(), DateTimeFormatter.ISO_TIME);

            events = new PlannerEvents(date, time, description);
            listOfEvents.add(events);

            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(listOfEvents);
            objectOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Checks if file exists, creates it if not.
     */
    public void fileExists(File fileName) {
        File file = fileName;
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Safe delete method, option prompts the user for a filename and deletes that file from the selected directory.
     */
    public void safeDelete() {
        try {
            new FileOutputStream(file).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    }//end class
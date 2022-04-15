
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String [] args) throws IOException{

        String data;

        ArrayList<Polynomial> polyList = new ArrayList<>(); // ArrayList of Polynomial type objects.
        ArrayList<String> dataList = new ArrayList<>(); // ArrayList to hold data from input file

        BufferedReader inputStream = null; // Instantiate BufferedReader to read though input file

        JFileChooser fc = new JFileChooser(); // Declare JFileChooser to allow chooser to select file
        fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fc.setFileFilter(new FileNameExtensionFilter("TEXT FILES", "txt", "text"));
        Polynomial poly; // Instantiate Polynomial object poly

        /**
         *  Referenced "Java Swing How to - Display the Contents of a text file in a JTextArea"
         *  Referenced "How can I read only text files in a directory?" on StackOverflow
         */
        int response = fc.showOpenDialog(null); // Opens a file window and displays system's directory
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            // Checks file input is valid with a text file
            if(file.getName().endsWith(".txt")) {

                try{
                    inputStream = new BufferedReader(new FileReader(file));
                    while ((data = inputStream.readLine()) != null)    {
                        dataList.add(data);
                    }
                } finally {
                    if (inputStream != null) { inputStream.close(); }
                }
                // Displays error message if non-txt file is chosen
            } else { JOptionPane.showMessageDialog(null, "Selection not valid. Only text files are accepted."); }
        }
        //Test for loop for file input
        // Iterates through dataList and declares Polynomial object poly
        // Iterates and calls toString method from Polynomial class
        for (String i : dataList)  {
            poly = new Polynomial(i);
            poly.toString(i);
            polyList.add(poly);

        }

        System.out.println("Strong Ordered: " + OrderedList.checkSorted(polyList));
        /* Check for Weak order (exponents only) */
        System.out.println("Weak Ordered: " + checkWeakOrder(polyList));



    } //main class

    public static boolean checkWeakOrder( List<Polynomial> polyList){
        boolean isWeakOrder = true;
        Polynomial previous = polyList.get(polyList.size()-1);
        for(int i = polyList.size()-2; i > 0; i--){

            if (previous.compare(polyList.get(i)) < 0){
                isWeakOrder = false;
            }
        }
        return isWeakOrder;
    }

} //end class





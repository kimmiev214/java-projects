import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainMethod {

    private static List<Poly> polyList = new ArrayList<>();


    public static ArrayList<String> fileIO() throws IOException {

        ArrayList<String> expressions = new ArrayList<>();
        BufferedReader inputStream = null;
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int response = fc.showOpenDialog(null);
        if (response == 0) {
            File file = fc.getSelectedFile();
            if (file.getName().endsWith(".txt")) {
                try {
                    inputStream = new BufferedReader(new FileReader(file));

                    while ((inputStream.readLine()) != null) {
                        String expression = inputStream.readLine();
                        expressions.add(expression);
                    }
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }

                }
            } else {
                JOptionPane.showMessageDialog((null), "Selection not valid. Only text files are accepted.");
            }
        }


        return expressions;
    }

    public static boolean checkWeakOrder( List<Poly> polyList){
        boolean isWeakOrder = true;
        Poly previous = polyList.get(polyList.size()-1);
        for(int i = polyList.size()-2; i > 0; i--){

            if (previous.compareExponents(polyList.get(i)) < 0){
                isWeakOrder = false;
            }
        }
        return isWeakOrder;
    }
    //===============================================================================================
// method: processPolyList / returns: void / catches: InvalidPolySyntax
// description: calls fromFile to fill a list with Poly objects and checks list order
//===============================================================================================
    public static void processPolyList() throws IOException{
        try {
            ArrayList<String> a = fileIO();
            for (String element : a) {
                Poly p = new Poly(element);
                System.out.println(p);
                polyList.add(p);
            }
        }catch (InvalidPolynomialSyntax ex){
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),ex.getMessage());
        }
        /* Call to check sorted for the Strong order check */
        System.out.println("Strong Ordered: " + OrderedList.checkSorted(polyList));
        /* Check for Weak order (exponents only) */
        System.out.println("Weak Ordered: " + checkWeakOrder(polyList));
    }


        public static void main(String[] args) throws IOException {
            processPolyList();
        }


    }






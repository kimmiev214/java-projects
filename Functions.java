import java.util.Scanner;
/**
 * @author kimberlyvillatoro
 * @version 01/13/2021
 * Program: Given n-value and g-value as input, this program compares two functions with different growth rates.
 * Function f(n): 200n^2 + 25n + 150
 * Function g(n): n^3
 * Once g(n) overtakes f(n) the program ends.
 * Output: gVal and print statement: "Value (gVal) for g(n) overtakes f(n)"
 */

public class Functions {
    public static void main(String[] args)  {
        double nVal;
        double gVal;
        double functionFNVal;
        double functionGNVal;
        do {
            Scanner myNVal = new Scanner(System.in);
            System.out.println("\nEnter a n-value for function f(n): ");
            nVal = myNVal.nextDouble();
            double functionFN = 200 * Math.pow(nVal, 2) + 25 * nVal + 150;
            functionFNVal = functionFN;
            System.out.print(functionFNVal);

            Scanner myGVal = new Scanner(System.in);
            System.out.println("\nEnter previous number for g-value in function g(n): ");
            gVal = myGVal.nextDouble();
            double functionGN = Math.pow(gVal, 3);
            functionGNVal = functionGN;
            System.out.print(functionGNVal);
        }
            while(functionGNVal < functionFNVal);

        System.out.format("\n Value %.0f for function g(n) overtakes function f(n)", gVal);

        }



    }


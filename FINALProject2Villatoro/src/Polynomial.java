import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @description
 * The Polynomial class examines a file of polynomials. Using two different methods of comparison,the program determines
 * whether the polynomials are strictly in ascending order. Each line of the input file will contain one polynomial.
 * Each term will be represented as a pair of values. The first element of that pair is a real value (coefficient).
 * The second is an integer value (exponent). Example: 5.6 3 4 1 8.3 0 will be represented as 5.6x^3 4x + 8.3
 * The Polynomial class implements both the Iterable and Comparable interfaces.
 *
 * @author kimberlyvillatoro
 * @version 02/18/2021
 */

public class Polynomial implements Iterable<Polynomial.Node>, Comparable<Polynomial> {

    Comparator<Polynomial> compare;
    private Node head = null;
    private StringBuilder buildPolynomial;

    /**
     *
     * @param fileIO
     * @throws InvalidPolynomialSyntax
     */
    public Polynomial(String fileIO) throws InvalidPolynomialSyntax {
        Scanner key = new Scanner(fileIO);


        this.buildPolynomial = new StringBuilder();

        while (key.hasNext()) {
            //String currentVal = key.next();

            addNode(key.nextDouble(), key.nextInt());
            //this.buildPolynomial.append(currentVal);
        }

        //System.out.print(this.buildPolynomial + "\n");
        //throw new InvalidPolynomialSyntax(("Incorrect syntax. Try new input."));

    }

    public void addNode(double co, int pow) {
        Polynomial.Node node = new Polynomial.Node();
        node.coefficient = co;
        node.power = pow;
        node.next = null;
        if (this.head == null) {
            this.head = node;
        }

    }

    public int compareTo(Polynomial p) {
        return 0;
    }

    public Iterator<Node> iterator() {
        return null;
    }

    public String toString(String fileIO) {
        Scanner key = new Scanner(fileIO);
        this.buildPolynomial = new StringBuilder();

        for(boolean isOnSecond = false; key.hasNext(); isOnSecond = !isOnSecond) {
            String currentVal = key.next();
            if (isOnSecond) {
                this.buildPolynomial.append("x^" + currentVal);
                if (key.hasNext()) {
                    this.buildPolynomial.append(" + ");
                }
            } else {
                this.buildPolynomial.append(currentVal);
            }
        }

        System.out.print(this.buildPolynomial + "\n");
        return this.buildPolynomial.toString();
    }

    static class Node {
        double coefficient;
        int power;
        Polynomial.Node next;

        Node() {
        }

        private int getExponent(){
            return this.power;
        }
        private double getCoefficient(){
            return this.coefficient;
        }
        private Node getNext(){
            return next;
        }
    }

    public int compare(Polynomial poly2) {
        Node thisPolyTerm = this.head;
        Node otherPolyTerm = poly2.head;
        while(thisPolyTerm != null && otherPolyTerm != null) {
            if (thisPolyTerm.getExponent() != otherPolyTerm.getExponent()) {
                return thisPolyTerm.getExponent() - otherPolyTerm.getExponent();
            }else {
                thisPolyTerm = thisPolyTerm.getNext();
                otherPolyTerm = otherPolyTerm.getNext();
            }
        }if(thisPolyTerm == null && otherPolyTerm == null){
            return 0;
        }
        if (otherPolyTerm == null){
            return +1;
        }else {
            return -1;
        }
    }
}
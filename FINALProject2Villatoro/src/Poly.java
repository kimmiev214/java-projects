import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;


public class Poly implements Iterable<Poly.Term>, Comparable<Poly> {

    Comparator<Poly> compare;
    private Term head;

//===============================================================================================
// method: Polynomial constructor/ arguments: String of space delimited coeffs and exponents
// description: splits the String into individual term nodes and creates a linked list of them
//===============================================================================================

    public Poly(String fromFile) {
        head = null; // explicitly stating for clarity
        Scanner termScanner = new Scanner(fromFile);
        try{
            while(termScanner.hasNext()){
                addTerm(termScanner.nextDouble(), termScanner.nextInt());
            }
        } catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
            throw new InvalidPolynomialSyntax("Incorrect Syntax, please check inputs!");
        }
    }

    //===============================================================================================
// method: addTerm / arguments: double coefficient and integer exponent
// description: if head is null, new term is null, Otherwise sets new term to next null term.next
//===============================================================================================
    public void addTerm(double coefficient, int exponent ){
        if (exponent < 0){
            throw new InvalidPolynomialSyntax("No negative exponents, please check inputs!");
        }
        Term current = head;
        if(current == null){ // then Polynomial is empty
            head = new Term(coefficient, exponent);
            head.next = null;
        } else { //find end by looping to null next link
            while(current.next != null){
                current = current.next;
            }
            current.next = new Term(coefficient, exponent);
        }

    }

    //===============================================================================================
// method: overridden compareTo via Comparable / Arguments: Another Polynomial object
// description: First compares exponents, then coefficients. A positive value indicate this is
// larger, a negative indicates the argument polynomial is larger.
//===============================================================================================
    @Override
    public int compareTo(Poly otherPoly) {
        Term thisCurrent = this.head;
        Term otherCurrent = otherPoly.head;

        while (thisCurrent != null && otherCurrent != null){
//positive if this is larger, negative otherwise
            if (thisCurrent.getExponent() != otherCurrent.getExponent()){
                return thisCurrent.getExponent() - otherCurrent.getExponent();
//casting to an int truncates, so simple checking for larger
            }else if(thisCurrent.getCoefficient() != otherCurrent.getCoefficient()) {
                if(otherCurrent.getCoefficient()> thisCurrent.getCoefficient()){
                    return -1;
                }else if(otherCurrent.getCoefficient()< thisCurrent.getCoefficient()){
                    return +1;
                }
            }// resetting the values outside of the loop
            thisCurrent = thisCurrent.getNext();
            otherCurrent = otherCurrent.getNext();
        }//if both are null, and at this point, they are equal, ret zero
        if (thisCurrent == null && otherCurrent == null){
            return 0;
        }//this would be the case of one with more terms than other
        if (thisCurrent == null){
            return -1;
        }else {
            return +1;
        }
    }
    //===============================================================================================
// method: compareExponents / Arguments: Another Polynomial object
// description: compares exponents in two polynomials, is called to check weak order
//===============================================================================================
    public int compareExponents(Poly poly2) {
        Term thisPolyTerm = this.head;
        Term otherPolyTerm = poly2.head;
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
    //could not find a way to correctly implement this in time :(
    public Poly() { compare = (Poly poly1, Poly poly2) -> poly1.compareExponents(poly2); }
    public Poly(Comparator<Poly> compare){ this.compare = compare; }

    //===============================================================================================
// method: overridden Iterator via Iterable / Implemented methods hasNext and next
// description: produces an iterator that can traverse terms of a polynomial
//===============================================================================================
    @Override
    public Iterator<Term> iterator() {
        return new Iterator() {

            private Term current = getHead();

            @Override
            public boolean hasNext() {
                return current != null && current.getNext() != null;
            }

            @Override
            public Term next() {
                Term data = current;
                current = current.next;
                return data;
            }
        };
    }

    //===============================================================================================
// method: overridden toString / Description: using StringBuilder, traverses the the expression
// adding the the string value of the term. Adds the operator based off coefficient sign.
//===============================================================================================
    @Override
    public String toString() {
        StringBuilder expressionBuilder = new StringBuilder();
//first check head to avoid +1x^3 +3x^2
        if (head.coefficient > 0){
            expressionBuilder.append(head.toString());
        }else{
            expressionBuilder.append(" - ").append(head.toString());
        }// then check the other nodes if they are not null
        for(Term tmp = head.next; tmp != null; tmp = tmp.next) {
            if (tmp.coefficient < 0) {
                expressionBuilder.append(" - ").append(tmp.toString());
            } else {
                expressionBuilder.append(" + ").append(tmp.toString());
            }
        }
        return expressionBuilder.toString();

    }

//===============================================================================================
// Nested Class: Term / arguments: double coefficient and int exponent / overridden toString
// description: nodes of the polynomial objects which cary the reference to the next node
//===============================================================================================

    static class Term{
        private double coefficient;
        private int exponent;
        private Term next;

        private Term(double c, int e) {
            coefficient = c;
            exponent = e;
            next = null; // explicitly setting to null
        }

        private int getExponent(){
            return this.exponent;
        }
        private double getCoefficient(){
            return this.coefficient;
        }
        private Term getNext(){
            return next;
        }

        @Override
        public String toString(){
            String termString = String.format("%.1f", Math.abs(coefficient));
            if (exponent == 0) { //no variable
                return termString;
            }else if(exponent == 1){ // do not display exponent
                return termString + "x";
            } else{ // display exponent after variable
                return termString + "x^" + exponent;
            }
        }
    }

    private Term getHead() {
        return head;
    }
}


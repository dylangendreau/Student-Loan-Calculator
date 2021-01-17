/*
 * Program Name: D_G_NegativeValueException.java
 * Purpose: An exception class that checks to see whether a value is negative or not.
 * Name: Dylan Gendreau
 * Date: Apr 13, 2020
 */

public class D_G_NegativeValueException extends Exception {
    // Data Members
    private double negativeValue;

    // Constructor
    D_G_NegativeValueException(double negativeValue) {
        super(negativeValue + " cannot be a negative.");
        this.negativeValue = negativeValue;
    }

    // Getter Method
    public double getNegativeValueOne() {
        return negativeValue;
    }
}//end class
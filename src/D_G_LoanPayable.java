/*
 * Program Name: D_G_LoanPayable.java
 * Purpose: An interface that holds a double for the annual rate and one abstract method called calculateLoanPayment(). 
 * 			Any class that implements this interface must fulfill the contract and implement the method.
 * Name: Dylan Gendreau
 * Date: Apr 13, 2020
 */

public interface D_G_LoanPayable {
    double ANNUAL_RATE_TO_MONTHLY_RATE = (double)1/1200;

    // Abstract Method
    double calculateLoanPayment(double loanAmount, double interestRate, int amortizationPeriod) throws D_G_NegativeValueException;
}//end interface
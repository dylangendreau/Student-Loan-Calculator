/*
 * Program Name: Student.java
 * Purpose: An aggregate class that holds objects that are instantiated in the StudentLoanApp class.
 * 			These objects are stored in an ArrayList structure. 
 * 			This is done to keep a list of all of the inputed students information.
 * Name: Dylan Gendreau
 * Date: Apr 13, 2020
 */

import java.text.DecimalFormat;
public class Student {
    // Data Members
    private String studentID, surname, middleName, firstName, aptNumber,
            streetNumber, streetName, city, province, postalCode;
    private double cslLoanAmount, oslLoanAmount;
    private DecimalFormat df2 = new DecimalFormat("#,##0.00");

    // Constructor
    public Student(String studentID, String surname, String middleName, String firstName, String aptNumber, String streetNumber,
                   String streetName, String city, String province, String postalCode, double cslLoanAmount, double oslLoanAmount) throws D_G_NegativeValueException {
        this.studentID = studentID;
        this.surname = surname;
        this.middleName = middleName;
        this.firstName = firstName;
        this.aptNumber = aptNumber;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        if (cslLoanAmount < 0)
            throw new D_G_NegativeValueException(cslLoanAmount);
        else
            this.cslLoanAmount = cslLoanAmount;
        if (oslLoanAmount < 0)
            throw new D_G_NegativeValueException(oslLoanAmount);
        else
            this.oslLoanAmount = oslLoanAmount;
    }

    // Getters and Setters
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAptNumber() {
        return aptNumber;
    }

    public void setAptNumber(String aptNumber) {
        this.aptNumber = aptNumber;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public double getCslLoanAmount() {
        return cslLoanAmount;
    }

    public void setCslLoanAmount(double cslLoanAmount) {
        this.cslLoanAmount = cslLoanAmount;
    }

    public double getOslLoanAmount() {
        return oslLoanAmount;
    }

    public void setOslLoanAmount(double oslLoanAmount) {
        this.oslLoanAmount = oslLoanAmount;
    }

    public String getStudentID() {
        return studentID;
    }

    /*
     * Method Name: toString()
     * Purpose: An overridden method that describes the object that calls this method
     * Accepts: Nothing.
     * Returns: A String representation of the object.
     */
    public String toString() {
        return "Student Name: " + surname + ", " + firstName + " " + middleName +
                "\nStudent Number: " + studentID +
                "\nCSL Amount is $" + df2.format(cslLoanAmount) +
                "\nOSL Amount is $" + df2.format(oslLoanAmount);
    }
}//end class
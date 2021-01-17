/*
  Program Name: StudentLoanApp.java
  Purpose: A GUI application that is used to calculate a students monthly loan payments, overall payments and total interest.
  			This is used to see how modifying payment schedules will affect the loan costs.
  Name: Dylan Gendreau
  Date: Apr 13, 2020
 */

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;
public class StudentLoanApp extends JFrame {
    // Class wide scope data members
    Container thisContentPane;
    private final ArrayList<Student>studentInfo = new ArrayList<>();
    private JPanel studentPnl, studentRepaymentFormPnl;
    private JButton submitBtn, clearBtn, previousBtn, nextBtn, deleteBtn, deleteAllBtn, calculateBtn;
    private JComboBox<String>provinceBox;
    private JSpinner intRateSpinner, amortPeriodSpinner;
    private JTextArea studentForm;
    private final DecimalFormat df2 = new DecimalFormat("#,##0.00");
    private int studentIndex = 0;
    private JTextField studentNumTxtFld, firstNameTxtFld, middleNameTxtFld, surnameTxtFld, aptNumTxtFld, streetNumTxtFld,
            streetNameTxtFld, cityTxtFld, postalCodeTxtFld, cslTxtFld, oslTxtFld, cslPaymentTxtFld,
            oslPaymentTxtFld, totalMonthlyPaymentTxtFld, totalBorrowedTxtFld, totalPaymentTxtFld, totalIntTxtFld;
    private final String[] provinces = {"Alberta", "British Columbia", "Manitoba", "New Brunswick",
            "Newfoundland and Labrador", "Northwest Territories", "Nova Scotia", "Nunavut",
            "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan", "Yukon"};

    // Constructor
    public StudentLoanApp() {
        // Title of JFrame aka "Courtesy Call"
        super("Dylan Gendreau's Student Loan Application [Student ID 0937665]");

        // JFrame settings aka "Boiler Plate Code"
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(675, 550);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        // Student Loan Application Information
        JLabel appInfoLbl = new JLabel("This is Dylan Gendreau's Student Loan Calculator", SwingConstants.CENTER);
        appInfoLbl.setFont(new Font("Sans-Serif", Font.PLAIN, 16));
        this.add(appInfoLbl, BorderLayout.NORTH);

        // Call Build Methods for Student Information and Student Data + Monthly Repayment Form Panels
        buildStudentPanel();
        buildStudentAndRepaymentFormPanel();

        // Add Panels to JFrame
        this.add(studentPnl, BorderLayout.WEST);
        this.add(studentRepaymentFormPnl, BorderLayout.CENTER);

        // Action Listeners for Button Handling
        ButtonHandler listener = new ButtonHandler();
        submitBtn.addActionListener(listener);
        clearBtn.addActionListener(listener);
        nextBtn.addActionListener(listener);
        previousBtn.addActionListener(listener);
        deleteBtn.addActionListener(listener);
        deleteAllBtn.addActionListener(listener);
        calculateBtn.addActionListener(listener);

        // Set the frame to visible
        this.setVisible(true);
    }

    /*
     * Method Name: buildStudentPanel()
     * Purpose: To build the student panel for the students information. This is done with GridBagLayout().
     * Accepts: Nothing.
     * Returns: Nothing! Void method that performs a service and builds a JPanel.
     */
    public void buildStudentPanel() {
        // Instantiate Student Panel
        studentPnl = new JPanel();
        studentPnl.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8,10,0,5);

        // Add Title Text
        JLabel studentInfoLbl = new JLabel("Student Information");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        studentPnl.add(studentInfoLbl, c);

        // Create and Add in Labels, Text Fields and Province Combobox
        // Student Number
        JLabel studentNumLbl = new JLabel("Student Number ", SwingConstants.RIGHT);
        c.gridwidth = 2;
        c.ipady = 3;
        c.gridx = 0;
        c.gridy = 1;
        studentPnl.add(studentNumLbl, c);
        studentNumTxtFld = new JTextField();
        c.gridx = 1;
        c.gridy = 1;
        studentPnl.add(studentNumTxtFld, c);

        // First Name
        JLabel firstNameLbl = new JLabel("First Name ", SwingConstants.RIGHT);
        c.gridx = 0;
        c.gridy = 2;
        studentPnl.add(firstNameLbl, c);
        firstNameTxtFld = new JTextField();
        c.gridx = 1;
        c.gridy = 2;
        studentPnl.add(firstNameTxtFld, c);

        // Middle Name
        JLabel middleNameLbl = new JLabel("Middle Name ", SwingConstants.RIGHT);
        c.gridx = 0;
        c.gridy = 3;
        studentPnl.add(middleNameLbl, c);
        middleNameTxtFld = new JTextField();
        c.gridx = 1;
        c.gridy = 3;
        studentPnl.add(middleNameTxtFld, c);

        // Last Name
        JLabel surnameLbl = new JLabel("Last Name ", SwingConstants.RIGHT);
        c.gridx = 0;
        c.gridy = 4;
        studentPnl.add(surnameLbl, c);
        surnameTxtFld = new JTextField();
        c.gridx = 1;
        c.gridy = 4;
        studentPnl.add(surnameTxtFld, c);

        // Apartment Number
        JLabel aptNumLbl = new JLabel("Apt. Number ", SwingConstants.RIGHT);
        c.gridx = 0;
        c.gridy = 5;
        studentPnl.add(aptNumLbl, c);
        aptNumTxtFld = new JTextField();
        c.gridx = 1;
        c.gridy = 5;
        studentPnl.add(aptNumTxtFld, c);

        // Street Number
        JLabel streetNumLbl = new JLabel("Street Number ", SwingConstants.RIGHT);
        c.gridx = 0;
        c.gridy = 6;
        studentPnl.add(streetNumLbl, c);
        streetNumTxtFld = new JTextField();
        c.gridx = 1;
        c.gridy = 6;
        studentPnl.add(streetNumTxtFld, c);

        // Street Name
        JLabel streetNameLbl = new JLabel("Street Name ", SwingConstants.RIGHT);
        c.gridx = 0;
        c.gridy = 7;
        studentPnl.add(streetNameLbl, c);
        streetNameTxtFld = new JTextField();
        c.gridx = 1;
        c.gridy = 7;
        studentPnl.add(streetNameTxtFld, c);

        // City
        JLabel cityLbl = new JLabel("City ", SwingConstants.RIGHT);
        c.gridx = 0;
        c.gridy = 8;
        studentPnl.add(cityLbl, c);
        cityTxtFld = new JTextField();
        c.gridx = 1;
        c.gridy = 8;
        studentPnl.add(cityTxtFld, c);

        // Province
        JLabel provinceLbl = new JLabel("Province ", SwingConstants.RIGHT);
        c.gridx = 0;
        c.gridy = 9;
        studentPnl.add(provinceLbl, c);
        provinceBox = new JComboBox<>(provinces);
        c.gridx = 1;
        c.gridy = 9;
        studentPnl.add(provinceBox, c);

        // Postal Code
        JLabel postalCodeLbl = new JLabel("Postal Code ", SwingConstants.RIGHT);
        c.gridx = 0;
        c.gridy = 10;
        studentPnl.add(postalCodeLbl, c);
        postalCodeTxtFld = new JTextField();
        c.gridx = 1;
        c.gridy = 10;
        studentPnl.add(postalCodeTxtFld, c);

        // CSL Loan Amount
        JLabel cslLbl = new JLabel("CSL Loan Amount ", SwingConstants.RIGHT);
        c.gridx = 0;
        c.gridy = 11;
        studentPnl.add(cslLbl, c);
        cslTxtFld = new JTextField();
        c.gridx = 1;
        c.gridy = 11;
        studentPnl.add(cslTxtFld, c);

        // OSL Loan Amount
        JLabel oslLbl = new JLabel("OSL Loan Amount ", SwingConstants.RIGHT);
        c.gridx = 0;
        c.gridy = 12;
        studentPnl.add(oslLbl, c);
        oslTxtFld = new JTextField();
        c.gridx = 1;
        c.gridy = 12;
        studentPnl.add(oslTxtFld, c);

        // Clear and Submit Button
        clearBtn = new JButton("Clear");
        c.gridx = 0;
        c.gridy = 13;
        studentPnl.add(clearBtn, c);
        submitBtn = new JButton("Submit");
        c.gridx = 1;
        c.gridy = 13;
        studentPnl.add(submitBtn, c);
    }

    /*
     * Method Name: buildStudentAndRepaymentFormPanel()
     * Purpose: To build the student form and monthly repayment form. This is done with GridBagLayout().
     * Accepts: Nothing.
     * Returns: Nothing! Void method that performs a service and builds a JPanel.
     */
    public void buildStudentAndRepaymentFormPanel() {
        // Instantiate Student and Repayment Form Panel
        studentRepaymentFormPnl = new JPanel();
        studentRepaymentFormPnl.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8,10,0,0);

        // Add Student Form Title Text
        JLabel studentListFormLbl = new JLabel("Student Form ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        studentRepaymentFormPnl.add(studentListFormLbl, c);

        // Text Area for Current Student Information
        studentForm = new JTextArea("", 4, 28);
        studentForm.setLineWrap(true);
        studentForm.setWrapStyleWord(true);
        studentForm.setEditable(false);
        studentForm.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        c.gridwidth = 0;
        c.gridx = 0;
        c.gridy = 1;
        studentRepaymentFormPnl.add(studentForm, c);

        // Next, Previous, Delete and Delete All Buttons
        previousBtn = new JButton("Previous");
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        studentRepaymentFormPnl.add(previousBtn, c);
        deleteBtn = new JButton("Delete Current");
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        studentRepaymentFormPnl.add(deleteBtn, c);
        nextBtn = new JButton("Next");
        c.gridx = 0;
        c.gridy = 3;
        studentRepaymentFormPnl.add(nextBtn, c);
        deleteAllBtn = new JButton("Delete All");
        c.gridx = 1;
        c.gridy = 3;
        studentRepaymentFormPnl.add(deleteAllBtn, c);

        // Add Monthly Repayment Form Title Text
        JLabel repaymentFormLbl = new JLabel("Monthly Repayment Form ");
        c.gridwidth = 0;
        c.gridx = 0;
        c.gridy = 4;
        studentRepaymentFormPnl.add(repaymentFormLbl, c);

        // Interest Rate Label and Spinner
        JLabel intRateLbl = new JLabel("Prime Interest Rate %");
        c.gridwidth = 1;
        c.ipady = 3;
        c.gridx = 0;
        c.gridy = 5;
        studentRepaymentFormPnl.add(intRateLbl, c);
        SpinnerModel intRate = new SpinnerNumberModel(4.25, 0, 100, 0.25);
        intRateSpinner = new JSpinner(intRate);
        c.gridx = 1;
        c.gridy = 5;
        studentRepaymentFormPnl.add(intRateSpinner, c);

        // Amortization Period Label and Spinner
        JLabel amortPeriodLbl = new JLabel("Amortization Period (Months)");
        c.gridx = 0;
        c.gridy = 6;
        studentRepaymentFormPnl.add(amortPeriodLbl, c);
        SpinnerModel amortPeriod = new SpinnerNumberModel(0, 0, 1200, 6);
        amortPeriodSpinner = new JSpinner(amortPeriod);
        c.gridx = 1;
        c.gridy = 6;
        studentRepaymentFormPnl.add(amortPeriodSpinner, c);

        // CSL Payment Label and Text Field
        JLabel cslPaymentLbl = new JLabel("CSL Monthly Payment ");
        c.gridx = 0;
        c.gridy = 7;
        studentRepaymentFormPnl.add(cslPaymentLbl, c);
        cslPaymentTxtFld = new JTextField("");
        cslPaymentTxtFld.setBackground(Color.WHITE);
        cslPaymentTxtFld.setEditable(false);
        c.gridx = 1;
        c.gridy = 7;
        studentRepaymentFormPnl.add(cslPaymentTxtFld, c);

        // OSL Payment Label and Text Field
        JLabel oslPaymentLbl = new JLabel("OSL Monthly Payment ");
        c.gridx = 0;
        c.gridy = 8;
        studentRepaymentFormPnl.add(oslPaymentLbl, c);
        oslPaymentTxtFld = new JTextField("");
        oslPaymentTxtFld.setBackground(Color.WHITE);
        oslPaymentTxtFld.setEditable(false);
        c.gridx = 1;
        c.gridy = 8;
        studentRepaymentFormPnl.add(oslPaymentTxtFld, c);

        // Total Monthly Payment Label and Text Field
        JLabel totalMonthlyPaymentLbl = new JLabel("Total Monthly Payment ");
        c.gridx = 0;
        c.gridy = 9;
        studentRepaymentFormPnl.add(totalMonthlyPaymentLbl, c);
        totalMonthlyPaymentTxtFld = new JTextField("");
        totalMonthlyPaymentTxtFld.setBackground(Color.WHITE);
        totalMonthlyPaymentTxtFld.setEditable(false);
        c.gridx = 1;
        c.gridy = 9;
        studentRepaymentFormPnl.add(totalMonthlyPaymentTxtFld, c);

        // Total Amount Borrowed Label and Text Field
        JLabel totalBorrowedLbl = new JLabel("Total Amount Borrowed ");
        c.gridx = 0;
        c.gridy = 10;
        studentRepaymentFormPnl.add(totalBorrowedLbl, c);
        totalBorrowedTxtFld = new JTextField("");
        totalBorrowedTxtFld.setBackground(Color.WHITE);
        totalBorrowedTxtFld.setEditable(false);
        c.gridx = 1;
        c.gridy = 10;
        studentRepaymentFormPnl.add(totalBorrowedTxtFld, c);

        // Total Interest Label and Text Field
        JLabel totalIntLbl = new JLabel("Total Interest ");
        c.gridx = 0;
        c.gridy = 11;
        studentRepaymentFormPnl.add(totalIntLbl, c);
        totalIntTxtFld = new JTextField("");
        totalIntTxtFld.setBackground(Color.WHITE);
        totalIntTxtFld.setEditable(false);
        c.gridx = 1;
        c.gridy = 11;
        studentRepaymentFormPnl.add(totalIntTxtFld, c);

        // Total Payment Label and Text Field
        JLabel totalPaymentLbl = new JLabel("Total Payment ");
        c.gridx = 0;
        c.gridy = 12;
        studentRepaymentFormPnl.add(totalPaymentLbl, c);
        totalPaymentTxtFld = new JTextField("");
        totalPaymentTxtFld.setBackground(Color.WHITE);
        totalPaymentTxtFld.setEditable(false);
        c.gridx = 1;
        c.gridy = 12;
        studentRepaymentFormPnl.add(totalPaymentTxtFld, c);

        // Calculate Button
        calculateBtn = new JButton("Calculate");
        c.gridwidth = 0;
        c.gridx = 0;
        c.gridy = 13;
        studentRepaymentFormPnl.add(calculateBtn, c);
    }

    public static void main(String[] args) {
        new StudentLoanApp();
    }//end main

    // Inner class for event handling
    private class ButtonHandler implements ActionListener, D_G_LoanPayable {
        public void actionPerformed(ActionEvent ev) {
            Student student = null;
            boolean duplicate = false;
            final double CSL_INT_RATE = 2.5;
            final double OSL_INT_RATE = 1.0;
            switch (ev.getActionCommand()) {
                // Submit Button
                case "Submit":
					/*Checking to see if student is already in the system based on student ID and name.
					  If student already exists, warn user and ask if they would like to replace the student.
					  This is in place so there are no duplicate students and CSL and OSL loan amounts can be updated easily.*/
                    if (studentInfo.size() > 0) {
                        for (int i = 0; i < studentInfo.size(); i++) {
                            if (studentNumTxtFld.getText().equals(studentInfo.get(i).getStudentID())) {
                                int result = JOptionPane.showConfirmDialog(thisContentPane,
                                        "You are about to replace this student, do you want to proceed?",
                                        "Duplicate Student",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.WARNING_MESSAGE);
                                if (result == JOptionPane.YES_OPTION)
                                    studentInfo.remove(i);
                                else
                                    duplicate = true;
                                break;
                            }
                        }
                    }

                    // Break out of switch if no is clicked on duplicate user option
                    if (duplicate)
                        break;

                    // Checking to see if student ID is a number
                    try {
                        Double.parseDouble(studentNumTxtFld.getText());
                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(thisContentPane,
                                "Student ID must be a number.",
                                "Invalid Student ID",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }

                    // Making sure student ID is 7 numbers
                    if (studentNumTxtFld.getText().length() != 7) {
                        JOptionPane.showMessageDialog(thisContentPane,
                                "Student ID Number must be exactly 7 numbers.",
                                "Invalid Student ID",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }

                    if (firstNameTxtFld.getText().isEmpty() || surnameTxtFld.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(thisContentPane,
                                "First name or last name fields can't be empty.",
                                "Empty Field",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }

                    // Storing student information into student object
                    try {
                        student = new Student(studentNumTxtFld.getText(), firstNameTxtFld.getText(), middleNameTxtFld.getText(),
                                surnameTxtFld.getText(), aptNumTxtFld.getText(), streetNumTxtFld.getText(),
                                streetNameTxtFld.getText(), cityTxtFld.getText(), Objects.requireNonNull(provinceBox.getSelectedItem()).toString(),
                                postalCodeTxtFld.getText(), Double.parseDouble(cslTxtFld.getText()), Double.parseDouble(oslTxtFld.getText()));
                    }
                    // Making sure CSL and OSL inputs are numbers
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(thisContentPane,
                                "CSL and OSL loan amounts require a number.",
                                "Invalid Input",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    // Making sure CSL and OSL loan amounts are a positive number
                    catch (D_G_NegativeValueException ex) {
                        if (Double.parseDouble(cslTxtFld.getText()) < 0.0 || Double.parseDouble(oslTxtFld.getText()) < 0.0) {
                            JOptionPane.showMessageDialog(thisContentPane,
                                    "CSL or OSL loan amount can't be a negative value.",
                                    "Negative Value",
                                    JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    }

                    // Setting student index, adding student to arraylist and setting the student form text
                    studentIndex = studentInfo.size();
                    studentInfo.add(student);
                    studentForm.setText(studentInfo.get(studentIndex).toString());
                    break;

                // Clear Button
                case "Clear":
                    eraseStudentInfo();
                    break;

                // Next Button
                case "Next":
                    // Checking to see if there are students in the arraylist to look at next
                    if (studentInfo.isEmpty()) {
                        JOptionPane.showMessageDialog(thisContentPane,
                                "There are no students in the system to go through.",
                                "Empty Database",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }

                    // Proceed if arraylist isn't empty
                    studentIndex++;
                    try {
                        setStudentInfo(studentIndex);
                    }
                    catch (IndexOutOfBoundsException ex) {
                        studentIndex = 0;
                        setStudentInfo(studentIndex);
                    }
                    break;

                // Previous Button
                case "Previous":
                    // Checking to see if there are students in the arraylist to look at previously
                    if (studentInfo.isEmpty()) {
                        JOptionPane.showMessageDialog(thisContentPane,
                                "There are no students in the system to go through.",
                                "Empty Database",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }

                    // Proceed if arraylist isn't empty
                    studentIndex--;
                    try {
                        setStudentInfo(studentIndex);
                    }
                    catch (IndexOutOfBoundsException ex) {
                        studentIndex = studentInfo.size() - 1;
                        setStudentInfo(studentIndex);
                    }
                    break;

                // Delete Current Student Button
                case "Delete Current":
                    // Checking to see if there are any students to remove from the arraylist
                    if (studentInfo.isEmpty()) {
                        JOptionPane.showMessageDialog(thisContentPane,
                                "There are no students in the system to delete.",
                                "Empty Database",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    else {
                        int result = JOptionPane.showConfirmDialog(thisContentPane,
                                "You are about to delete this student, do you want to proceed?",
                                "Delete Student",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                        if (result == JOptionPane.NO_OPTION)
                            break;
                    }

                    // Proceed to remove if there are students in the arraylist
                    if (studentIndex != 0) {
                        studentForm.setText(studentInfo.get(studentIndex - 1).toString());
                        setStudentInfo(studentIndex - 1);
                        erasePaymentInfo();
                        studentInfo.remove(studentIndex);
                    }
                    else if (studentInfo.size() == 1) {
                        studentForm.setText("");
                        eraseStudentInfo();
                        erasePaymentInfo();
                        studentInfo.remove(studentIndex);
                    }
                    else {
                        studentForm.setText(studentInfo.get(studentIndex + 1).toString());
                        setStudentInfo(studentIndex + 1);
                        erasePaymentInfo();
                        studentInfo.remove(studentIndex);
                    }

                    // Adjusting student index to number after delete
                    studentIndex = studentInfo.size() - 1;
                    break;

                // Delete All Students Button
                case "Delete All":
                    // Checking to see if there are any students to remove from the arraylist
                    if (studentInfo.isEmpty()) {
                        JOptionPane.showMessageDialog(thisContentPane,
                                "There are no students in the system to delete.",
                                "Empty Database",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    else {
                        int result = JOptionPane.showConfirmDialog(thisContentPane,
                                "You are about to delete ALL students, do you want to proceed?",
                                "Delete All Students",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                        if (result == JOptionPane.NO_OPTION)
                            break;
                    }

                    // Proceed to remove all students
                    studentInfo.clear();
                    studentForm.setText("");
                    studentIndex = studentInfo.size();
                    eraseStudentInfo();
                    erasePaymentInfo();
                    break;

                // Calculate Button
                case "Calculate":
                    // Checking to see if ArrayList is empty before calculating
                    if (studentInfo.isEmpty()) {
                        JOptionPane.showMessageDialog(thisContentPane,
                                "Sorry, there are no students in the system. Add a student then calculate the payment.",
                                "No Students",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }

                    // Spinner Values
                    int amortPeriod = (int) amortPeriodSpinner.getValue();
                    double intRate = (double) intRateSpinner.getValue();

                    // CSL Monthly Payment
                    double cslLoan = studentInfo.get(studentIndex).getCslLoanAmount();
                    double monthlyIntRate = (intRate + CSL_INT_RATE) * (ANNUAL_RATE_TO_MONTHLY_RATE);
                    double cslPayment = calculateLoanPayment(cslLoan, monthlyIntRate, amortPeriod);
                    cslPaymentTxtFld.setText("$" + df2.format(cslPayment));

                    // OSL Monthly Payment
                    double oslLoan = studentInfo.get(studentIndex).getOslLoanAmount();
                    monthlyIntRate = (intRate + OSL_INT_RATE) * (ANNUAL_RATE_TO_MONTHLY_RATE);
                    double oslPayment = calculateLoanPayment(oslLoan, monthlyIntRate, amortPeriod);
                    oslPaymentTxtFld.setText("$" + df2.format(oslPayment));

                    // Total Monthly Payment
                    double totalMonthlyPayment = cslPayment + oslPayment;
                    totalMonthlyPaymentTxtFld.setText("$" + df2.format(totalMonthlyPayment));

                    // Total Amount Borrowed
                    double totalBorrowed = (cslLoan + oslLoan);
                    totalBorrowedTxtFld.setText("$" + df2.format(totalBorrowed));

                    // Total Payment
                    double totalPayment = (cslPayment + oslPayment) * amortPeriod;
                    totalPaymentTxtFld.setText("$" + df2.format(totalPayment));

                    // Total Interest Paid
                    double totalIntPaid = totalPayment - (cslLoan + oslLoan);
                    totalIntTxtFld.setText("$" + df2.format(totalIntPaid));

                    // Set Student Information -- Done just in case clear was clicked
                    setStudentInfo(studentIndex);
                    break;
            }
        }

        /*
         * Method Name: setStudentInfo()
         * Purpose: To set the current selected students information in the student information panel.
         * Accepts: An int value that is the index of the current student in the arraylist.
         * Returns: Nothing! Void method.
         */
        public void setStudentInfo(int index) {
            studentForm.setText(studentInfo.get(index).toString());
            studentNumTxtFld.setText(studentInfo.get(index).getStudentID());
            firstNameTxtFld.setText(studentInfo.get(index).getFirstName());
            middleNameTxtFld.setText(studentInfo.get(index).getMiddleName());
            surnameTxtFld.setText(studentInfo.get(index).getSurname());
            aptNumTxtFld.setText(studentInfo.get(index).getAptNumber());
            streetNumTxtFld.setText(studentInfo.get(index).getStreetNumber());
            streetNameTxtFld.setText(studentInfo.get(index).getStreetName());
            cityTxtFld.setText(studentInfo.get(index).getCity());
            provinceBox.setSelectedItem(studentInfo.get(index).getProvince());
            postalCodeTxtFld.setText(studentInfo.get(index).getPostalCode());
            cslTxtFld.setText(String.valueOf(studentInfo.get(index).getCslLoanAmount()));
            oslTxtFld.setText(String.valueOf(studentInfo.get(index).getOslLoanAmount()));
        }

        /*
         * Method Name: eraseStudentInfo()
         * Purpose: To erase the students information in the student information panel.
         * Accepts: Nothing.
         * Returns: Nothing! Void method.
         */
        public void eraseStudentInfo() {
            studentNumTxtFld.setText("");
            firstNameTxtFld.setText("");
            middleNameTxtFld.setText("");
            surnameTxtFld.setText("");
            aptNumTxtFld.setText("");
            streetNumTxtFld.setText("");
            streetNameTxtFld.setText("");
            cityTxtFld.setText("");
            postalCodeTxtFld.setText("");
            cslTxtFld.setText("");
            oslTxtFld.setText("");
        }

        /*
         * Method Name: erasePaymentInfo()
         * Purpose: To erase the payment information in the monthly repayment form.
         * Accepts: Nothing.
         * Returns: Nothing! Void method.
         */
        public void erasePaymentInfo() {
            cslPaymentTxtFld.setText("");
            oslPaymentTxtFld.setText("");
            totalMonthlyPaymentTxtFld.setText("");
            totalBorrowedTxtFld.setText("");
            totalPaymentTxtFld.setText("");
            totalIntTxtFld.setText("");
        }

        /*
         * Method Name: calculateLoanPayment()
         * Purpose: To calculate the monthly loan payments based on provided
         * 			CSL or OSL loan amounts, interest rate, and amortization period.
         * Accepts: Two double values that are the loan amount and interest rate,
         * 			and an int value that is the amortization period.
         * Returns: A double that is the total monthly payment.
         */
        public double calculateLoanPayment(double loanAmount, double interestRate, int amortizationPeriod) {
            return (loanAmount * interestRate * Math.pow((1 + interestRate), amortizationPeriod)) /
                    (Math.pow((1 + interestRate), amortizationPeriod) - 1);
        }
    }//end inner class

    /*
     * Method Name: toString()
     * Purpose: An overridden method that describes the object that calls this method.
     * 			This is used in the student form area of the GUI application.
     * Accepts: Nothing.
     * Returns: A String representation of the object.
     */
    public String toString() {
        return super.toString();
    }
}//end class
import java.sql.*;
import java.util.Scanner;

public class Loan {
    static Scanner sc = new Scanner(System.in);
    static Connection con = DbConnection.connect();

    void loan() {
        while (true) {
            System.out.println("\n===== Bank Loan Management System =====");
            System.out.println("1. Apply for Loan");
            System.out.println("2. View Loan Details");
            System.out.println("3. Repay Loan");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    applyLoan();
                    break;
                case 2:
                    viewLoanDetails();
                    break;
                case 3:
                    repayLoan();
                    break;
                case 4:
                    System.out.println("Exiting... Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void applyLoan() {
//        System.out.print("Enter User ID: ");
       int userId = NewAccount.getSc();
       String name ="";
       int amount =0;
      
        try{
        	PreparedStatement p=con.prepareStatement("SELECT * FROM users");
        	ResultSet rs=p.executeQuery();
        	while(rs.next()){
        		name=rs.getString(2);
        		amount=rs.getInt(4);
        	}
        	System.out.println("you have a "+(amount*5)+" loan give want this loan (0/1)");
        	int ch=sc.nextInt();
        	
        	if(ch==1){
        		
        	System.out.print("Enter Proof (Aadhar, PAN, Voter): ");
            String proof = sc.next();

        	String sql = "INSERT INTO loans (id, name, amount, status, proof) VALUES (?, ?, ?, 'Active', ?)";
        	PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, userId);
            pst.setString(2, name);
            pst.setDouble(3, amount);
            pst.setString(4, proof);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Loan Applied Successfully! Status: Active.");
            } else {
                System.out.println("Loan Application Failed!");
            }}
        } catch (SQLException e) {
            System.out.println("Error while applying for a loan: " + e.getMessage());
        }
        
    }

    static void viewLoanDetails() {
        System.out.print("Enter User ID: ");
        int userId = sc.nextInt();

        String sql = "SELECT * FROM loans WHERE id=?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("\nLoan ID: " + rs.getInt("loan_id"));
                System.out.println("User Name: " + rs.getString("name"));
                System.out.println("Loan Amount: " + rs.getDouble("amount"));
                System.out.println("Proof: " + rs.getString("proof"));
                System.out.println("Loan Status: " + rs.getString("status"));
            }

            if (!found) {
                System.out.println("No Loan Found for this User ID!");
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching loan details: " + e.getMessage());
        }
    }

    static void repayLoan() {
        System.out.print("Enter Loan ID to Repay: ");
        int loanId = sc.nextInt();

        try {
            String checkSql = "SELECT id, amount, status FROM loans WHERE loan_id=?";
            PreparedStatement checkPst = con.prepareStatement(checkSql);
            checkPst.setInt(1, loanId);
            ResultSet rs = checkPst.executeQuery();

            if (!rs.next()) {
                System.out.println("No Loan Found with this ID!");
                return;
            }

            int userId = rs.getInt("id");
            double loanAmount = rs.getDouble("amount");
            String status = rs.getString("status");

            if (!status.equalsIgnoreCase("Active")) {
                System.out.println("Loan is not active. Please check loan status.");
                return;
            }

            String balanceSql = "SELECT balance FROM users WHERE id=?";
            PreparedStatement balancePst = con.prepareStatement(balanceSql);
            balancePst.setInt(1, userId);
            ResultSet balanceRs = balancePst.executeQuery();

            if (!balanceRs.next()) {
                System.out.println("User's bank account not found!");
                return;
            }

            double balance = balanceRs.getDouble("balance");

            if (balance < loanAmount) {
                System.out.println("Insufficient balance to repay the loan.");
                return;
            }

            String updateBalanceSql = "UPDATE users SET balance = balance - ? WHERE id=?";
            PreparedStatement updateBalancePst = con.prepareStatement(updateBalanceSql);
            updateBalancePst.setDouble(1, loanAmount);
            updateBalancePst.setInt(2, userId);
            int balanceUpdate = updateBalancePst.executeUpdate();

            if (balanceUpdate > 0) {
                String updateLoanSql = "UPDATE loans SET status='Repaid', amount=0 WHERE loan_id=?";
                PreparedStatement updateLoanPst = con.prepareStatement(updateLoanSql);
                updateLoanPst.setInt(1, loanId);
                int loanUpdate = updateLoanPst.executeUpdate();

                if (loanUpdate > 0) {
                    System.out.println("Loan Repaid Successfully! Amount deducted from balance.");
                } else {
                    System.out.println("Loan repayment failed!");
                }
            } else {
                System.out.println("Failed to update user balance!");
            }

        } catch (SQLException e) {
            System.out.println("Error while repaying loan: " + e.getMessage());
        }
    }
}

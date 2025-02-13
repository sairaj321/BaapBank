import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertUser {

    void add() {
        Connection con = DbConnection.connect(); 
        Scanner sc = new Scanner(System.in);

        try {
            String name, password;
            int bal;

            while (true) {
                System.out.println("Enter your Name:");
                name = sc.nextLine();
                
                System.out.println("Enter your Password:");
                password = sc.nextLine();
                
                System.out.println("Enter your account Balance:");
                bal = sc.nextInt();
                sc.nextLine(); 
                
               
                String checkQuery = "SELECT COUNT(*) FROM users WHERE name = ?";
                PreparedStatement checkStmt = con.prepareStatement(checkQuery);
                checkStmt.setString(1, name);
                ResultSet rs = checkStmt.executeQuery();
                
                rs.next();
                if (rs.getInt(1) > 0) { 
                    System.out.println("Duplicate Record! User with this name already exists.");
                    continue; 
                }
                
               
                String insertQuery = "INSERT INTO users VALUES (?, ?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(insertQuery);
                
                pstmt.setInt(1, 0); 
                pstmt.setString(2, name);
                pstmt.setString(3, password);
                pstmt.setInt(4, bal);
                
                int i = pstmt.executeUpdate();
                
                if (i > 0) {
                    System.out.println("User inserted successfully!");
                }

                
            
                System.out.println("Do you want to add another user? (1/0)");
                String choice = sc.nextLine();
                if (!choice.equals("1")) {
                    break;
                }
            }
            
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

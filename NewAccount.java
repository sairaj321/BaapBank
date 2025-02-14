import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class NewAccount {
	Scanner sc = new Scanner(System.in);
	Connection con = DbConnection.connect();
	static int id;
	
	public static int getSc() {
		return id;
	}

	void already(){
		
		System.out.println("Enter your ID :");
	 	 id=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the Your Password :");
		String pass=sc.nextLine();
		String sql="select * from users WHERE id=? AND password=?";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			
			pst.setInt(1, id);
			pst.setString(2, pass);
			
			ResultSet rs=pst.executeQuery();
			
			if(rs.next()){
				System.out.println("Login Successfully...");
				
				Bank.login();
			}else{
				System.out.println("Invalid ID or Password.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	 void add() {
	        
	        

	        try {
	            String name, password;
	            int bal,id;

	            while (true) {
	            	System.out.println("Enter your New ID:");
	                id = sc.nextInt();
	            	
	                System.out.println("Enter your Name:");
	                name = sc.next();
	                
	                System.out.println("Enter your Password:");
	                password = sc.next();
	                
	                System.out.println("Enter your account Balance:");
	                bal = sc.nextInt();
	                
	                
	               
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
	                
	                pstmt.setInt(1, id); 
	                pstmt.setString(2, name);
	                pstmt.setString(3, password);
	                pstmt.setInt(4, bal);
	                
	                int i = pstmt.executeUpdate();
	                
	                if (i > 0) {
	                    System.out.println("User inserted successfully!");
	                }

	                
	            
	                System.out.println("Do you want to add another user? (1/0)");
	                String choice = sc.next();
	                if (!choice.equals("1")) {
	                	Bank.login();
	                    break;
	                }
	                
	            }
	            
	            

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}

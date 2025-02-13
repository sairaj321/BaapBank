import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Deposit {

	void add(){
		Scanner sc=new Scanner(System.in);
		Connection con=DbConnection.connect();
		
		try {
			System.out.println("Enter the ID.");
			int str=sc.nextInt();
			
			System.out.println("Enter the Amount to Deposit your account ");
			int str1=sc.nextInt();
			
			String query="UPDATE users SET balance=balance+? WHERE id = ?";
			PreparedStatement pst=con.prepareStatement(query);
			
			pst.setInt(1, str1);
			pst.setInt(2, str);
			
			int i=pst.executeUpdate();
			if(i>0){
				System.out.println("Deposit successfully!");
			}else{
				System.out.println("No user found with the given ID");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

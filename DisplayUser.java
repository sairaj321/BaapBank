import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DisplayUser {

	void display(){
		
		Connection con=DbConnection.connect();
//	Scanner sc=new Scanner(System.in);
//		System.out.println("Enter which recode you can see:");  // it use to that only one recode print use
//		String str=sc.nextLine();
		
		try {
			PreparedStatement pstmt=con.prepareStatement("SELECT * FROM users;");
			
			
//			PreparedStatement pstmt=con.prepareStatement("SELECT * FROM users WHERE name =? LIMIT 1;");
//			pstmt.setString(1, str);//only one recode display only use 
			
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()){
				String name=rs.getString(2);
				int balance=rs.getInt(4);
				
				System.out.println("=======================================");
				System.out.println("Name :"+name+"    "+"Balance :"+balance);
				System.out.println("=======================================");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

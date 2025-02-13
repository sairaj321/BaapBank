import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateRecode {
	static Scanner sc=new Scanner(System.in);
	static Connection con=DbConnection.connect();
	
	public static void pass(){
		try {
			System.out.println("Enter the name you want to update that password");
			String str=sc.nextLine();
			System.out.println("Enter the new password ");
			String str1=sc.nextLine();
			
            String query = "UPDATE users SET password = ? WHERE name = ?";

			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1,str1);
			pst.setString(2, str);
			
			int i=pst.executeUpdate();
			if(i>0){
				System.out.println("Update Done....");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void bal(){
		try {
			System.out.println("Enter the name you want to update that Balance");
			String str=sc.next();
			
			System.out.println("Enter the Amount to add you account ");
			int str1=sc.nextInt();
			
            String query = "UPDATE users SET balance=balance+? WHERE name = ?";

			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1,str1);
			pst.setString(2, str);
			
			int i=pst.executeUpdate();
			if(i>0){
				System.out.println("Balance updated successfully!");
			}else{
				System.out.println("No user found with the given name.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	void update()
	{
		DisplayUser obj2= new DisplayUser();
		System.out.println("1. Update Password ");
		System.out.println("2. Update Balance ");
		int ch=sc.nextInt();
		
		switch(ch){
		case 1:pass();
				break;
				
		case 2:bal();
				break;
		case 3:obj2.display();
				break;
		}
		
	}
}

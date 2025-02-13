import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteUser {

	void delete(){
		Scanner c=new Scanner(System.in);
		Connection con=DbConnection.connect();
		
		
		try {
			System.out.println("Enter the name we want to delete that recode..");
			String str=c.next();
					
			String sql="DELETE FROM users WHERE name=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, str);
			
			int i=pst.executeUpdate();
			
			if(i>0){
				System.out.println("Delete Record successfully....");
			}else{
				System.out.println("That record not found");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

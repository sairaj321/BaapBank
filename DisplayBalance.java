import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayBalance {

	
	void Display(){
		Connection con=DbConnection.connect();
		int id=NewAccount.getSc();
		String sql="SELECT * FROM users WHERE id = ?";
		
		 try {
			PreparedStatement pat=con.prepareStatement(sql);
			pat.setInt(1, id);
			
			ResultSet rs= pat.executeQuery();
			if(rs.next()){
				
				System.out.println("ID :"+rs.getInt(1));
				System.out.println("Name :"+rs.getString(2));
				System.out.println("ID :"+rs.getInt(4));
				
			}else{
				System.out.println("Uers ID not found");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

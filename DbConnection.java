import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	public static Connection connect(){
		
		Connection con=null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver load");
			
			String URL="jdbc:mysql://localhost:3306/baapbank";
			
			con=DriverManager.getConnection(URL,"root","");
			System.out.println("Conncetion Established");
			 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
	}
}

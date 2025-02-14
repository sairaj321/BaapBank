import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Withdraw {
	
	void withdraw(){
		Scanner sc=new Scanner(System.in);
		
		int id=NewAccount.getSc();
		
		System.out.println("Enter amount to you want Withdraw :");
		int amount=sc.nextInt();
		
		Connection con=DbConnection.connect();
		String sql="select * from users Where id=?";
		
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			
			if(rs.next()){
				int balance=rs.getInt(4);
				System.out.println("Current Balance: " + balance);
				
				if(5000>balance){
					System.out.println("Insufficient Balance!");
				}
				else{
					int newbal=balance-amount;
					
					String sql1="update users set balance=? where id=?";
					PreparedStatement pstp=con.prepareStatement(sql1);
					pstp.setInt(1, newbal);
					pstp.setInt(2, id);
					
					int an=pstp.executeUpdate();
					
					if(an>0){
						System.out.println("Withdrawal Successful! New Balance: " + newbal);
					}else{
						System.out.println("Withdrawal Failed.");
					}
				}
			}else{
				System.out.println("User ID not found.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

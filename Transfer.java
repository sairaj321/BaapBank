import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Transfer {

	
	void transfer(){
		
		Connection con=DbConnection.connect();
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter your ID:");
		int id=sc.nextInt();
		
		
			try {
				String sql="select balance from users Where id=?";
				PreparedStatement pst=con.prepareStatement(sql);
				pst.setInt(1, id);
				ResultSet rs=pst.executeQuery();
				if(rs.next()){
					int ids=rs.getInt("balance");
				
						System.out.println("Enter the ID from which you want to transfer money:");
						int res=sc.nextInt();
						
						String sql2="select balance from users Where id=?";
						PreparedStatement pstt=con.prepareStatement(sql2);
						pstt.setInt(1, res);
						ResultSet rs1=pstt.executeQuery();
						
						if(rs1.next()){
							System.out.println("Enter the amount to transfer:");
		                    int amount = sc.nextInt();
		                    
		                    if (amount > ids) {
		                        System.out.println("Insufficient balance.");
		                    } else {
		                    	 // Perform transaction
		                    	int recipientBalance = rs1.getInt("balance");
		                    	int newSenderBalance =ids-amount;
		                    	int newRecipientBalance =recipientBalance+amount;
		                    	
		                    	 // Update balances
		                    	String updateSender = "UPDATE users SET balance = ? WHERE id = ?";
		                        PreparedStatement pstUpdateSender = con.prepareStatement(updateSender);
		                        pstUpdateSender.setInt(1, newSenderBalance);
		                        pstUpdateSender.setInt(2, id);
		                        pstUpdateSender.executeUpdate();
		                    	
		                        
		                        String updateRecipient = "UPDATE users SET balance = ? WHERE id = ?";
		                        PreparedStatement pstUpdateRecipient = con.prepareStatement(updateRecipient);
		                        pstUpdateRecipient.setInt(1, newRecipientBalance);
		                        pstUpdateRecipient.setInt(2, res);
		                        pstUpdateRecipient.executeUpdate();
		                        
		                        System.out.println("Transaction successful! ₹" + amount + " transferred to ID: " + res);
		                    }
		                    
							}else{
								System.out.println("Recipient does not have an account.");
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

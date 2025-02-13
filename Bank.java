import java.util.Scanner;
public class Bank {

	
		static Scanner sc=new Scanner(System.in);
		public static void login(){
			
			while(true){
			
			System.out.println("1: WithDraw Amount");
			System.out.println("2: Deposit Amount");
			System.out.println("3: Transfer the Money");
			System.out.println("4: Personal Loan");
			System.out.println("5: Exit");
			System.out.println("Enter your choice");
			int a=sc.nextInt();
			
			switch(a){
			
			case 1:Withdraw wd=new Withdraw();
					wd.withdraw();
					break;
			case 2:Deposit dp= new Deposit();
					dp.add();
					break;
			case 3:Transfer tr=new Transfer();
					tr.transfer();
					break;
			case 4:Loan lo=new Loan();
					lo.loan();
					break;
			case 5:System.exit(a);
					break;
			}
			}
			}

		public static void main(String[] args) {
			
			
		
			System.out.println("Welcome to the BAAP Bank ");
			System.out.println("============================");
			System.out.println("1. create a new account ");
			System.out.println("2. I have already Account ");
			System.out.println("Enter your choice");
			int ch=sc.nextInt();
			NewAccount na=new NewAccount();
			
			switch(ch){
				case 1:na.add();
						break;
					
				case 2:na.already();
						break;
			}
			
			
			
			
			
			
		}

	}



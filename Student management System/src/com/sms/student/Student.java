package com.sms.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Student {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner s=new Scanner(System.in);
		intro();

		Class.forName("com.mysql.cj.jdbc.Driver");
		while(true) {
		System.out.println("entere your option");
		String option=s.nextLine();
		switch (option) {
		case"1":{
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.println("                                 INSERT                                ");
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

			insert();
			break;
		}
		case "2":{
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.println("                                EDIT                                ");
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

			edit();
			break;
		
			
		}
		case "3":{
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.println("                                VIEW                                ");
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

			view();
			break;
			
		}
		case "4":{
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.println("                                 DELETE                               ");
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

			delete();
			break;
		}
		case "5":
			return;
		default :System.out.println("invalid choice,Try again!!!!!!");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		}
		}
		
	}
	
	
	
		public static void insert() throws SQLException {
			Scanner sc=new Scanner(System.in);
			String url="jdbc:mysql://localhost/sms_db";
			String user="root";
			String pass="vani@2004";
			Connection con=DriverManager.getConnection(url,user,pass);
			System.out.println("enter your name");
			String n=sc.nextLine();
			System.out.println("enter your father name");
			String f=sc.nextLine();
			System.out.println("enter your class");
			String c=sc.nextLine();
			System.out.println("enter your mobile no");
			String m=sc.nextLine();
			System.out.println("enter your date of birth");
			String d=sc.nextLine();
			
			String query="insert into student(name,fname,class,mbno,date)" + "value(?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,n);//1 is first place holder
			ps.setString(2,f);//second placeholder
			ps.setString(3,c);
			ps.setString(4,m);
			ps.setString(5,d);
			
			ps.executeUpdate();
			System.out.println("data inserted successfully");
			
		}
		private static void edit()throws SQLException  {
			String url="jdbc:mysql://localhost/sms_db";
			String user="root";
			String pass="vani@2004";
			Connection d1=DriverManager.getConnection(url,user,pass);
			view();
			System.out.println("Chose the ID to Edit: ");
			Scanner i1=new Scanner(System.in);
			int u=i1.nextInt();
			
			System.out.println("enter your name");
			i1.nextLine();//consumes the newline character left in the input buffer.
			String n=i1.nextLine();
			System.out.println("enter your father name");
			String f=i1.nextLine();
			System.out.println("enter your class");
			String c=i1.nextLine();
			System.out.println("enter your mobile no");
			String m=i1.nextLine();
			System.out.println("enter your date of birth");
			String d=i1.nextLine();
			
			String query="UPDATE student SET name=?, fname=?,class=?,mbno=?,date=? WHERE (usn=?)";
			
			PreparedStatement s=d1.prepareStatement(query);

			s.setString(1,n);
			s.setString(2,f);
			s.setString(3,c);
			s.setString(4,m);
			s.setString(5,d);
			s.setInt(6,u);

			s.executeUpdate();
			System.out.println("data edited successfully");
			
		}
		public static void view() throws SQLException {
			String url="jdbc:mysql://localhost/sms_db";
			String user="root";
			String pass="vani@2004";
			Connection v=DriverManager.getConnection(url,user,pass);
			Statement st=v.createStatement();
			
			//Step :4 ->Execute the query
			
			ResultSet rs=st.executeQuery("select * from student");
			System.out.println("|usn  | name   |fname   |class   |mbno        |date    |");
			System.out.println("--------------------------------------------------------");
			
			while(rs.next()) {
				System.out.println("|"+rs.getInt(1)+"   |"+rs.getString(2)+"   |"+rs.getString(3)+"   |"+rs.getInt(4)+"  | "+rs.getString(5)+"  | "+rs.getString(6)+"|");
			}
			
		}
		public static  void delete() throws SQLException {
			String url="jdbc:mysql://localhost/sms_db";
			String user="root";
			String pass="vani@2004";
			Connection d=DriverManager.getConnection(url,user,pass);
			view();
			System.out.println("enter the usn u want to delete");
			Scanner i=new Scanner(System.in);
			int u=i.nextInt();
			
			
			String query="Delete from student where usn=?";
			PreparedStatement s=d.prepareStatement(query);

			s.setInt(1,u);

			s.executeUpdate();
			System.out.println("data deleted successfully");
		}
		public static void intro() {
		System.out.println("############################################################");
		System.out.println("                      STUDENTS MODULE                       ");
		System.out.println("############################################################");
		System.out.println("\n 1.Insert");
		System.out.println("2.Edit");
		System.out.println("3.View");
		System.out.println("4.Delete");
		System.out.println("5.exit");

		

	}

}

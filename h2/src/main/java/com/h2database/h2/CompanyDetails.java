package com.h2database.h2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompanyDetails {
	
	private static String URL="jdbc:h2:~/STARTUP",className="org.h2.Driver";
	private static Connection con;
	private static Statement stmt;
	private static ResultSet rs;
	private static PreparedStatement ins_comp=null,ins_founder=null;
	
	private static String ins_comp_query="INSERT INTO COMPANY VALUES(default,?,?,?,?,?,?,?)";
	private static String ins_founder_query="INSERT INTO FOUNDERS VALUES(default,?,?,?,?,?)";
	
	
	public int insertCompany(String c_name,String city,String email,String linkedin,
			String facebook,String site,String blog) throws SQLException
	{
		ins_comp=con.prepareStatement(ins_comp_query);
		ins_comp.setString(1,c_name);
		ins_comp.setString(2,city);
		ins_comp.setString(3,email);
		ins_comp.setString(4,linkedin);
		ins_comp.setString(5,facebook);
		ins_comp.setString(6,site);
		ins_comp.setString(7,blog);
		return ins_comp.executeUpdate();
	}
	
	public int insertFounder(String fname,int cid,String f_lnkdin,String twitter,String f_fblink)throws SQLException
	{	
		ins_founder=con.prepareStatement(ins_founder_query);
		ins_founder.setString(1,fname);
		ins_founder.setInt(2,cid);
		ins_founder.setString(3,f_lnkdin);
		ins_founder.setString(4,twitter);
		ins_founder.setString(5,f_fblink);
		 return ins_founder.executeUpdate();		
	}
	
	public void create() throws SQLException
	{
		stmt.executeUpdate("CREATE TABLE COMPANY(ID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,NAME VARCHAR(50) NOT NULL," + 
				"CITY VARCHAR(30) NOT NULL, EMAIL VARCHAR(100), LINKEDIN_LINK VARCHAR(200),FACEBOOK_LINK VARCHAR(200)," + 
				"OFFICIAL_SITE VARCHAR(200),BLOG_LINK VARCHAR(200))");	
		
		stmt.executeUpdate("CREATE TABLE FOUNDERS(ID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,"
						+ "NAME VARCHAR(30) NOT NULL, COMPANY_ID INTEGER NOT NULL, LINKEDIN_LINK VARCHAR(200),"
						+ "TWITTER_LINK VARCHAR(200), FACEBOOK_LINK VARCHAR(200))");
		
		stmt.executeUpdate("ALTER TABLE FOUNDERS ADD FOREIGN KEY(COMPANY_ID) REFERENCES COMPANY(ID)");
		
	}
	
	public void printCompany() throws SQLException
	{
		rs=stmt.executeQuery("select * from COMPANY");
		System.out.println("COMPANY table:");
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)
			+"\t"+rs.getString(5)+"\t"+rs.getString(6)+"\t"+rs.getString(7)+"\t"+rs.getString(8));
		}
		rs.close();
	}
	
	public void printFounder()throws SQLException
	{
		rs=stmt.executeQuery("select * from FOUNDERS");
		System.out.println("FOUNDERS table");
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getString(4)
			+"\t"+rs.getString(5)+"\t"+rs.getString(6));
		}
		
		rs.close();
	}

	public static void main(String[] args) {
		
		CompanyDetails cd=new CompanyDetails();
		int n=0;
		
		try {
			Class.forName(className).newInstance();
			con=DriverManager.getConnection(URL,"sa","");
			stmt=con.createStatement();
			
			//creating company,founders table
			cd.create();			
			
			//inserting company records
			n+=cd.insertCompany("9stacks","New Delhi","care@9stacks.com","https://www.linkedin.com/company/9stacks/","https://facebook.com/9stacksGaming",
					"https://www.9stacks.com","https://www.9stacks.in/");
			
			n+=cd.insertCompany("doodleblue innovations","Chennai","genius@doodleblue.com","https://www.linkedin.com/company/doodleblue","htttps://www.facebook.com/",
					"https://www.doodleblue.com","https://www.doodleblue.com/blog.php");
			
			n+=cd.insertCompany("Karomi Smart Solutions","Chennai","support@karomi.com","https://www.linkedin.com/company/karomi-technology",
					"https://www.facebook.com/karomi","http://www.karomi.com","http://karomi.com/blog/");
			
			n+=cd.insertCompany("Firstcry","Pune","customercare@firstcry.com","https://in.linkedin.com/company/firstcry","https://facebook.com/FirstCryIndia",
					"http://www.firstcry.com","https://blog.firstcry.com");
			
			n+=cd.insertCompany("Callhealth","Hyderabad","companysecretary@callhealth.com","https://www.linkedin.com/company/call-health",
					"https://www.facebook.com/callhealthofficial","http://callhealth.com","https://blog.callhealth.com");
			
			System.out.println(n+" records inserted in Company table");
			n=0;
			
			n+=cd.insertFounder("Sudhir Kamath",1,"https://www.linkedin.com/in/kamathsudhir","https://twitter.com/sudhirkamath",
					"https://www.facebook.com/kamathsudhir");
			
			n+=cd.insertFounder("Atishe Chordia",2,"https://www.linkedin.com/in/atishe-chordia-567a8723",
					"https://www.twitter.com/atishechordia","https://www.facebook.com/atishe");
					
			n+=cd.insertFounder("Vilva Natarajan",3,"https://www.linkedin.com/in/vilva-natarajan-805917",
					"https://www.twitter.com/vivlva_karomi","https://www.facebook.com/vilva.natarajan");
					
			n+=cd.insertFounder("Amitava Saha",4,"https://www.linkedin.com/in/amitavasaha73",
					"NULL","https://www.facebook.com/amitava.saha.39");
					
			n+=cd.insertFounder("Hari Thalapalli",5,"https://www.linkedin.com/in/hari-thalapalli-1bb45112",
					"https://twitter.com/h_thalapalli?lang=en","https://www.facebook.com/hari.thalapalli.5");
			
			System.out.println(n+"records inserted in Founders table");
			
			//retrieving data
			cd.printCompany();
			
			cd.printFounder();
			
			stmt.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}

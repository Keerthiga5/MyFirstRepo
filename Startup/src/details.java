import java.sql.*;
public class details {

	public static void main(String[] args) {
		String URL="jdbc:h2:~/STARTUPS",className="org.h2.Driver";
		Connection con;
		Statement stmt;
		ResultSet rs;
		String sql;
		int n=0,x;
		
		try {
			Class.forName(className).newInstance();
			con=DriverManager.getConnection(URL,"sa","");
			stmt=con.createStatement();
						
			//creating table company
			sql="CREATE TABLE COMPANY(ID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,NAME VARCHAR(50) NOT NULL,"
					+ "CITY VARCHAR(30) NOT NULL, EMAIL VARCHAR(100), LINKEDIN_LINK VARCHAR(200),FACEBOOK_LINK VARCHAR(200),"
					+ "OFFICIAL_SITE VARCHAR(200),BLOG_LINK VARCHAR(200))";
			x=stmt.executeUpdate(sql);
			if(x>=0)
				System.out.println("Table created!");
					
			//insert values into table company
			
			n+=stmt.executeUpdate("INSERT INTO COMPANY VALUES(default,'9stacks','New Delhi','care@9stacks.com',"
					+ "'https://www.linkedin.com/company/9stacks/','https://facebook.com/9stacksGaming',"
					+ "'https://www.9stacks.com','https://www.9stacks.in/')");
			
			n+=stmt.executeUpdate("INSERT INTO COMPANY VALUES(default,'doodleblue innovations','Chennai',"
					+ "'genius@doodleblue.com','https://www.linkedin.com/company/doodleblue','htttps://www.facebook.com/',"
					+ "'https://www.doodleblue.com','https://www.doodleblue.com/blog.php')");
			
			n+=stmt.executeUpdate("INSERT INTO COMPANY VALUES(default,'Karomi Smart Solutions','Chennai',"
					+ "'support@karomi.com','https://www.linkedin.com/company/karomi-technology','https://www.facebook.com/karomi',"
					+ "'http://www.karomi.com','http://karomi.com/blog/')");
			
			n+=stmt.executeUpdate("INSERT INTO COMPANY VALUES(default,'Firstcry','Pune',"
					+ "'customercare@firstcry.com','https://in.linkedin.com/company/firstcry','https://facebook.com/FirstCryIndia',"
					+ "'http://www.firstcry.com','https://blog.firstcry.com')");
			
			n+=stmt.executeUpdate("INSERT INTO COMPANY VALUES(default,'Callhealth','Hyderabad',"
					+ "'companysecretary@callhealth.com','https://www.linkedin.com/company/call-health','https://www.facebook.com/callhealthofficial',"
					+ "'http://callhealth.com','https://blog.callhealth.com/')");
			
			System.out.println(n+" records inserted");
			
			//retriving data
			rs=stmt.executeQuery("select * from COMPANY");
			System.out.println("COMPANY table:");
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)
				+"\t"+rs.getString(5)+"\t"+rs.getString(6)+"\t"+rs.getString(7)+"\t"+rs.getString(8));
			}
			
			
			//creating table founders
				
				sql="CREATE TABLE FOUNDERS(ID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,"
						+ "NAME VARCHAR(30) NOT NULL, COMPANY_ID INTEGER NOT NULL, LINKEDIN_LINK VARCHAR(200),"
						+ "TWITTER_LINK VARCHAR(200), FACEBOOK_LINK VARCHAR(200))";
				int y=stmt.executeUpdate(sql);
				if(y>=0)
					System.out.println("Table FOUNDERS created successfully");
				sql="ALTER TABLE FOUNDERS ADD FOREIGN KEY(COMPANY_ID) REFERENCES COMPANY(ID)";
				int g=stmt.executeUpdate(sql);
				if(g>=0)
					System.out.println("Foreign key added");
				
				//inserting records
				n=0;
				n+=stmt.executeUpdate("INSERT INTO FOUNDERS VALUES(DEFAULT,'Sudhir Kamath','1',"
						+ "'https://www.linkedin.com/in/kamathsudhir','https://twitter.com/sudhirkamath',"
						+ "'https://www.facebook.com/kamathsudhir')");
				
				n+=stmt.executeUpdate("INSERT INTO FOUNDERS VALUES(DEFAULT,'Atishe Chordia','2',"
						+ "'https://www.linkedin.com/in/atishe-chordia-567a8723','https://www.twitter.com/atishechordia',"
						+ "'https://www.facebook.com/atishe')");
				
				n+=stmt.executeUpdate("INSERT INTO FOUNDERS VALUES(DEFAULT,'Vilva Natarajan','3',"
						+ "'https://www.linkedin.com/in/vilva-natarajan-805917','https://www.twitter.com/vivlva_karomi',"
						+ "'https://www.facebook.com/vilva.natarajan')");
				
				n+=stmt.executeUpdate("INSERT INTO FOUNDERS VALUES(DEFAULT,'Amitava Saha','4',"
						+ "'https://www.linkedin.com/in/amitavasaha73','NULL',"
						+ "'https://www.facebook.com/amitava.saha.39')");
				
				n+=stmt.executeUpdate("INSERT INTO FOUNDERS VALUES(DEFAULT,'Hari Thalapalli','5',"
						+ "'https://www.linkedin.com/in/hari-thalapalli-1bb45112','https://twitter.com/h_thalapalli?lang=en',"
						+"'https://www.facebook.com/hari.thalapalli.5')");
				
				System.out.println(n+"records inserted");
				
				//retriving data from founders
				rs=stmt.executeQuery("select * from FOUNDERS");
				System.out.println("FOUNDERS table:");
				while(rs.next())
				{
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getString(4)
					+"\t"+rs.getString(5)+"\t"+rs.getString(6));
				}
				
	
			rs.close();
			stmt.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
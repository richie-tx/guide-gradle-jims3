package ui.supervision.administersupervisee;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administersupervisee.GetSuperviseePhotoEvent;
import messaging.administersupervisee.reply.SuperviseePhotoResponseEvent;

/**
 * Servlet implementation class DisplayBlobServlet
 */
public class UIGetSuperviseePhotoHelper {
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UIGetSuperviseePhotoHelper() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public SuperviseePhotoResponseEvent doGet( GetSuperviseePhotoEvent request ) throws ServletException, IOException {

	    Statement stmt = null;
	    Connection con = null;
	    ResultSet rs = null;
	    // Default spn
	   // String spn = "02391303";
	    String spn = "";

	    spn = request.getSpn();
	    SuperviseePhotoResponseEvent myResponse = null;
	    									    
	    try {
	    	
	    	 con = getMySqlConnection();
			
			 stmt = con.createStatement();
			
			    rs = stmt.executeQuery( "Select AF_Date_Created, AF_Image From vw_Offenders Where AF_SPN =" + spn );
				if (rs.next()) {
					
					Blob test=rs.getBlob("AF_Image");
					Date date = rs.getDate("AF_DATE_CREATED");
					InputStream x=test.getBinaryStream();
					int size=x.available();
					byte b[]= new byte[size];
					x.read(b);
					myResponse = new SuperviseePhotoResponseEvent();
					myResponse.setSuperviseePhoto( b );
					myResponse.setDateCreated( date );
						
				}
		}
		catch(Exception e){
			
			System.out.println("Exception :"+e);
		}
		finally{
		
				try{
				stmt.close();
				con.close();
				}
		catch(Exception e){
		
			System.out.println(e);
		}
	}
		return myResponse;
	    
	}
	
	 public static Connection getMySqlConnection() throws Exception {
		    String driver = "net.sourceforge.jtds.jdbc.Driver";
		    String url = "jdbc:jtds:sqlserver://10.1.27.211:1433/AsureCltData";
		    String username = "JIMS2-AsureCltData";
		    String password = "ReadData";

		    Class.forName( driver );
		    Connection conn = DriverManager.getConnection(url, username, password);
		    return conn;
		  }

}


/**
 * 
 */
package pd.supervision.administersupervisee.transactions;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import pd.common.util.StringUtil;
import pd.supervision.administercaseload.SuperviseePhoto;
import messaging.administersupervisee.GetSuperviseePhotoEvent;
import messaging.administersupervisee.reply.SuperviseePhotoResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

/**
 * @author cc_cwalters
 *
 */
public class GetSuperviseePhotoCommand implements ICommand {

	public SuperviseePhotoResponseEvent
				convertSuperviseePhoto2ResponseEvent(
						SuperviseePhoto superviseePhoto)
	{
		SuperviseePhotoResponseEvent 
			response_event = new SuperviseePhotoResponseEvent();
		
		response_event.setDateCreated(superviseePhoto.getDateCreated());
		response_event.setDateIssued(superviseePhoto.getDateIssued());
		response_event.setDateModified(superviseePhoto.getDateModified());
		response_event.setDatePrinted(superviseePhoto.getDatePrinted());
		response_event.setDateReplaced(superviseePhoto.getDateReplaced());
		response_event.setFirstName(superviseePhoto.getFirstName());
		response_event.setLastName(superviseePhoto.getLastName());
		response_event.setSpn(superviseePhoto.getSpn());
		response_event.setSuperviseePhoto(superviseePhoto.getSuperviseePhoto());
		response_event.setSuperviseeThumbnail(superviseePhoto.getSuperviseeThumbnail());		
		
		return response_event;
	}
	
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) 
	{
		GetSuperviseePhotoEvent get_photo_event = (GetSuperviseePhotoEvent)event;
		
		if (!StringUtil.isEmpty(get_photo_event.getSpn()))
		{

		    Statement stmt = null;
		    Connection con = null;
		    ResultSet rs = null;
		    SuperviseePhoto superviseePhoto = null;
		    									    
		    try {
		    	
		    	 con = getMySqlConnection();
				 stmt = con.createStatement();
				
			    rs = stmt.executeQuery( "Select AF_Date_Created, AF_Image,AF_FirstName,AF_LastName From vw_Offenders Where AF_SPN =" + get_photo_event.getSpn() );
				if (rs.next()) { 
					
					Blob test=rs.getBlob("AF_Image");
					Date date = rs.getDate("AF_DATE_CREATED");
					InputStream x=test.getBinaryStream();
					int size=x.available();
					byte b[]= new byte[size];
					x.read(b);
					superviseePhoto = new SuperviseePhoto();
					superviseePhoto.setSuperviseePhoto( b );
					superviseePhoto.setDateCreated( date );
					
					stmt.close();
					con.close();
					
					//convert to response event
					SuperviseePhotoResponseEvent 
						response_event = 
							convertSuperviseePhoto2ResponseEvent( superviseePhoto );
					
					//send response
					MessageUtil.postReply(response_event);
				}
			}
			catch(Exception e){
				
				System.out.println("Exception :"+e);
			}
		  }
		}
	
	 private static Connection getMySqlConnection() throws Exception {
	    String driver = "net.sourceforge.jtds.jdbc.Driver";
	    String url = "jdbc:jtds:sqlserver://10.1.27.211:1433/AsureCltData";
	    String username = "JIMS2-AsureCltData";
	    String password = "ReadData";

	    Class.forName( driver );
	    Connection conn = DriverManager.getConnection(url, username, password);
	    return conn;
	 }

}

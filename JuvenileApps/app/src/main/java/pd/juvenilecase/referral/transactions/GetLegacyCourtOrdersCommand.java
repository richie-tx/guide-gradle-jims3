package pd.juvenilecase.referral.transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import messaging.referral.GetLegacyCourtOrdersEvent;
import messaging.referral.reply.LegacyCourtOrderResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;

public class GetLegacyCourtOrdersCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		GetLegacyCourtOrdersEvent event = (GetLegacyCourtOrdersEvent) anEvent;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		String juvId = event.getJuvenileNum();
		String petitionNum = petitionStrip(event.getPetitionNum());
		
		if (juvId!=null && petitionNum!=null){
			
			Statement stmt = null;
			Connection con = null;
			ResultSet rs = null;

			String query = 	"SELECT [CourtOrder_Id], [CourtDate], [Court], [Type], [RespondentAttorney], "
				+ "[Offense1] FROM [CourtOrderDB].[dbo].[dbo_tblHistorical] " +
				"where [JuvenileNumber]="+juvId+" and "
				+ "[Cause]="+petitionNum+";";
			
			
//			String query = 	"SELECT [CourtOrder_Id], [CourtDate], [Court], [Type], [RespondentAttorney], "
//				+ "[Offense1] FROM [CourtOrderDB].[dbo].[dbo_tblHistorical] " +
//				"where [JuvenileNumber]=337100 and "
//				+ "[Cause]=999999996;";
			
			
			System.err.println(query);
			
			try {

				con = getMySqlConnection();

				stmt = con.createStatement();

				rs = stmt.executeQuery(query);
				
				while (rs.next()) {

					LegacyCourtOrderResponseEvent response = new LegacyCourtOrderResponseEvent();
					
					response.setCourtOrderID(rs.getString("CourtOrder_ID"));
					response.setCourtDate(DateUtil.stringToDate(
							rs.getString("CourtDate"),"yyyyMMdd"));
					response.setJuvenileCourt(rs.getString("Court"));
					response.setHearingTypeDescription(rs.getString("Type"));
					response.setRespondentAttorneyName(rs.getString("RespondentAttorney"));
					response.setJuvenileOffenseCodeDescription(rs.getString("Offense1"));
					response.setPetitionNum(petitionNum);
		
					dispatch.postEvent(response);
					
				}
			} catch (Exception e) {

				System.out.println("Exception :" + e);
			} finally {

				try {
					stmt.close();
					con.close();
				} catch (Exception e) {

					System.out.println(e);
				}
			}

			
		}
		
		
	}

	public static Connection getMySqlConnection() throws Exception {
		String driver = "net.sourceforge.jtds.jdbc.Driver";
		String url = "jdbc:jtds:sqlserver://10.4.22.90:1433/CourtOrderDb";
		String username = "crtordjims2";
		String password = "crtorderjims2p2";
		Connection conn = null;
		
		try{
		Class.forName(driver);
		conn = DriverManager.getConnection(url, username, password);
		}catch(Exception e){
		e.printStackTrace();	
		}
		
		return conn;
		
	}
	
	/**This method removes the trailing "J" from petition numbers used in JIMS2 **/
	public String petitionStrip(String petitionNum)
	{
		
		   StringBuffer strBuff = new StringBuffer();
		    char c;
		    
		    for (int i = 0; i < petitionNum.length() ; i++) {
		        c = petitionNum.charAt(i);
		        
		        if (Character.isDigit(c)) {
		            strBuff.append(c);
		        }
		    }
		    return strBuff.toString();
		
	}
	
	public void onRegister(IEvent anEvent)
	{
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}
	
}

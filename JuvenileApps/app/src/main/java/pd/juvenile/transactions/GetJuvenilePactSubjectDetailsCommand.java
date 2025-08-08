package pd.juvenile.transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import messaging.juvenile.GetJuvenilePactSubjectDetailsEvent;
import messaging.juvenile.reply.JuvenilePactDetailResponseEvent;
import mojo.km.config.AppProperties;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * 
 * @author sthyagarajan
 * added for task #43956
 */
public class GetJuvenilePactSubjectDetailsCommand implements ICommand{

	@Override
	public void execute(IEvent event) throws Exception {
		
		GetJuvenilePactSubjectDetailsEvent juvPactSubjectEvt = (GetJuvenilePactSubjectDetailsEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		JuvenilePactDetailResponseEvent respEvent = null;
		String juvNum = juvPactSubjectEvt.getJuvenileId();
		
		//pact value # task 44099 . U.S #41378
		String pactDatabaseName = AppProperties.getInstance().getProperty("PactDatabaseName");
		String pactSchemaName =  AppProperties.getInstance().getProperty("PactSchemaName");
		if(juvNum!=null)
		{
		   Statement stmt = null;
	 	   Connection con = null;
	 	   ResultSet rs   = null;
	 	   respEvent = new JuvenilePactDetailResponseEvent();
	 	   try 
	 	   {
	 		   //	"jdbc:jtds:sqlserver://SVQPACTSQL/NSGCMS_TX_Harris_Juvenile_Test"
	 		     String connectionStr = "jdbc:jtds:sqlserver://"+pactDatabaseName+"/"+pactSchemaName;
	 	    	 con = getMySqlConnection(connectionStr);
	 			 stmt = con.createStatement();
   			 	 rs = stmt.executeQuery( "SELECT * From dbo.VW_NSG_REPORTING__SUBJECT where EID='"+juvNum+"'" );
				 while (rs.next()) 
				 {
					 respEvent.setJuvenileId(rs.getString( "EID" ));
					 respEvent.setGuid(rs.getString("UID"));
					 respEvent.setLastName(rs.getString("LAST_NAME"));
					 respEvent.setFirstName(rs.getString("FIRST_NAME"));
					 respEvent.setZipCode(rs.getString("CURRENT_ADDRESS__ZIPCODE")) ;
					 respEvent.setCity(rs.getString("CURRENT_ADDRESS__CITY"));
					 respEvent.setCounty(rs.getString("CURRENT_ADDRESS__COUNTY"));
					 respEvent.setDateOfBirth(rs.getDate("DOB"));
					 respEvent.setGender(rs.getString("GENDER"));
					 respEvent.setRaceId(rs.getString("RACE_ID_FK"));
					 respEvent.setRaceDesc(rs.getString("RACE_DESCRIPTION"));
				     respEvent.setAge(rs.getString("AGE_AS_OF_TODAY" ));
					 respEvent.setRiskLevel(rs.getString( "RISK_LEVEL" ));
					 //respEvent.setRiskLevelOverride(rs.getString( "RISK_LEVEL_OVERRIDE" ));
					 respEvent.setRiskLevelOverrideDate(rs.getDate( "RISK_LEVEL_OVERRIDE_DATE" ));
					 respEvent.setPactLastDate(rs.getDate( "DATE_OF_LAST_PACT" ));
					 respEvent.setOnaLastDate(rs.getDate( "DATE_OF_LAST_ONA" ));
					 respEvent.setrPactLastDate(rs.getDate("DATE_OF_LAST_RPACT")) ;
					 respEvent.setSraLastDate(rs.getDate( "DATE_OF_LAST_SRA" ));
 					 respEvent.setCasePlanLastDate(rs.getDate( "DATE_OF_LAST_CASE_PLAN" ));
				}
				 //US 55593
				 rs = stmt.executeQuery( "SELECT * From dbo.VW_NSG_REPORTING__ASSESSED_RISK_LEVEL where SUBJECT_EID='"+juvNum+"' and SCORE_TYPE='SOCIAL HISTORY'");
				 while (rs.next()) 
				 {
					 respEvent.setJuvenileId(rs.getString( "SUBJECT_EID" ));
					 double raw = rs.getBigDecimal("RAW_SCORE").doubleValue();
					 if(raw >=0 && raw<=5)
						 respEvent.setRiskLevelOverride("Low");
					 else if(raw >=6 && raw<=9)
						 respEvent.setRiskLevelOverride("Moderate");
					 else if( raw >= 10)
						 respEvent.setRiskLevelOverride("High");
				 }
				    dispatch.postEvent(respEvent);
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("Exception :" + e.getMessage());
	 		}
	 		finally
	 		{
 				try{
 						stmt.close();
 						con.close();
	 				}
	 				catch(Exception e)
	 				{
	 					System.out.println(e);
	 				}
	 		}
			
	/*		Iterator<JuvenilePactSubject> juvenilePactSubjectItr =  JuvenilePactSubject.findAll(juvPactSubjectEvt);
			while(juvenilePactSubjectItr.hasNext()){
				JuvenilePactSubject juvPactSubj = juvenilePactSubjectItr.next();
				if(juvPactSubj!=null){
					respEvent = new JuvenilePactDetailResponseEvent();
					respEvent.setAge(juvPactSubj.getAge());
					respEvent.setCasePlanLastDate(juvPactSubj.getCasePlanLastDate());
					respEvent.setCity(juvPactSubj.getCity());
					respEvent.setZipCode(juvPactSubj.getZipCode()) ;
					respEvent.setCounty(juvPactSubj.getCounty());
					respEvent.setDateOfBirth(juvPactSubj.getDateOfBirth());
					respEvent.setFirstName(juvPactSubj.getFirstName());
					respEvent.setGender(juvPactSubj.getGender());
					respEvent.setGuid(juvPactSubj.getGuid());
					respEvent.setJuvenileId(juvPactSubj.getJuvenileId());
					respEvent.setLastName(juvPactSubj.getLastName());
					respEvent.setOnaLastDate(juvPactSubj.getOnaLastDate());
					respEvent.setPactLastDate(juvPactSubj.getPactLastDate());
					respEvent.setRaceDesc(juvPactSubj.getRaceDesc());
					respEvent.setRaceId(juvPactSubj.getRaceId());
					respEvent.setRiskLevel(juvPactSubj.getRiskLevel());
					respEvent.setRiskLevelOverride(juvPactSubj.getRiskLevelOverride());
					respEvent.setRiskLevelOverrideDate(juvPactSubj.getRiskLevelOverrideDate());
					respEvent.setSraLastDate(juvPactSubj.getSraLastDate());
					respEvent.setrPactLastDate(juvPactSubj.getrPactLastDate());
				}
				
				dispatch.postEvent(respEvent);
			}*/
		}
	}
	
	
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getMySqlConnection( String url ) throws Exception {
	    String driver = "net.sourceforge.jtds.jdbc.Driver";
	    String username = "jims2user";
	    String password = "jims2userpassword";

	    Class.forName( driver );
	    Connection conn = DriverManager.getConnection(url, username, password);
	    return conn;
	  }
}

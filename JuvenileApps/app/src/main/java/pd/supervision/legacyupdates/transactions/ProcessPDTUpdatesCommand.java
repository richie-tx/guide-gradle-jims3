/*
 * Created on Dec 7, 2011
 *
 */
package pd.supervision.legacyupdates.transactions;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import messaging.legacyupdates.ProcessPDTUpdatesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import naming.LegacyUpdateConstants;
import pd.supervision.legacyupdates.LegacyUpdateLog;

/**
 * @author ryoung
 *
 */
public class ProcessPDTUpdatesCommand implements ICommand {

	//private static 	final String t3Xview = "vwT34_ELM";
	// Do not load these: ,"vwT34_Correction"!!
	private static final String[] t3Xviews = {"vwT33","vwT34","vwT33_ELM","vwT34_ELM","vwT33_TAIP","vwT34_TAIP"};
	/**
	 *  Service for processing PDT referrals
	 */
    public void execute(IEvent event) {
    	
    	ProcessPDTUpdatesEvent pEvent = (ProcessPDTUpdatesEvent) event;
    	
    	Statement stmt = null;
 	    Connection con = null;
 	    ResultSet rs   = null;
 	    StringBuffer sb = new StringBuffer();
 	    int ctr =0;
 	    String t3Xview = "";
	    
 	   /* String[] t34views = {"vwT34","vwT34_ELM","vwT34_TAIP"};
 	    String[] t33view = {"vwT33"};*/
	 	
 	   for ( int cntr = 0; cntr < t3Xviews.length; cntr ++ ){
 	    	
 		 t3Xview = t3Xviews[cntr];	
    	sb = new StringBuffer("jdbc:jtds:sqlserver://CSC-S54:1433/CJAD_ProgramReferral/");
    	sb.append( t3Xview );
	    	
	    	try {
	 	    		    		
	 	    	 con = getMySqlConnection( sb.toString() );
	 			
	 			 stmt = con.createStatement();
	 			 	StringBuffer query = new StringBuffer();
	 			 	
	 			 	query.append("SELECT spn,RECTYPE,RECSTATUS,SOURCEOID,CRIMINALCASE_ID,RECDATA,IMPORT_T3X From ");
	 			 	query.append( t3Xview );
	 			 	
	 			 //	query.append("Select spn,RECTYPE,RECSTATUS,SOURCEOID,CRIMINALCASE_ID,RECDATA,IMPORT_T3X From ").append(t34views[z])
	 			 //	.append(" where SOURCEOID = 'P4130'");
	 			 	
	 			 	rs = stmt.executeQuery( query.toString() );
				    while ( rs.next() ) {
				    
				    		
		    		ProcessPDTUpdatesCommand.createLegacy( rs.getString( "spn" ), rs.getString("RECTYPE"),
		    			rs.getString("SOURCEOID") , rs.getString("CRIMINALCASE_ID"), "", rs.getString("RECDATA"));

				    	ctr++;
				    	
					}
	 			}
	 			catch(Exception e){
	 				
	 				System.out.println("Exception :" + e.getMessage() );
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
	 			
 	    }
 	    
 	// Update records to Imported
 		if ( ctr > 0 ){
 			System.out.println(ctr + " " + " Records processed !");
 			this.update( );
 			
 		}
 	}
    
   
    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event) {
 
    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event) {
    }

    /**
     * 
     * @param pdtList
     */
    public void update( ) {
    	
    	Statement stmt = null;
 	    Connection con = null;
 	    StringBuffer sb = new StringBuffer();
	    
	    java.util.Calendar cal = java.util.Calendar.getInstance();
	    java.util.Date utilDate = cal.getTime();
	    Date sqlDate = new Date(utilDate.getTime());
	 	
	    for ( int cntr = 0; cntr < t3Xviews.length; cntr ++ ){
	    	
	    	sb = new StringBuffer("jdbc:jtds:sqlserver://CSC-S54:1433/CJAD_ProgramReferral/");
	    	sb.append( t3Xviews[cntr] );
	    	
	    	try {
	 	    	
	 	    	 con = getMySqlConnection( sb.toString() );	 			
	 			 stmt = con.createStatement();
	 			 //Import_T3X_Date
	 			 stmt.execute( "UPDATE " + t3Xviews[cntr] + " set IMPORT_T3X = 1,Import_T3X_Date ='" + sqlDate +"' ;");
	 			 
	 			// stmt.execute( "UPDATE " + t3Xview + " set IMPORT_T3X = 1,Import_T3X_Date ='" + sqlDate +"'  where SOURCEOID = '" + newObj.getSOURCEOID() +"';");
	 			 
			}
			catch(Exception e){
				
				System.out.println("Exception :" + e.getMessage() );
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
	    }
    }
    
    /**
     * 
     * @param spn
     * @param recordTypeId
     * @param sourceId
     * @param caseId
     * @param logonId
     * @param recordData
     */
	protected static void createLegacy(String spn, String recordTypeId, 
			String sourceId, String caseId, String logonId, String recordData) {

	StringBuffer padSpn = new StringBuffer( spn );
	// Padding spn because they are storing it as an INT
	while ( padSpn.length() < 8 ){
		padSpn.insert( 0, "0" );
		}
		LegacyUpdateLog uLog = new LegacyUpdateLog();
		uLog.setSpn( padSpn.toString() );
		uLog.setRecordTypeId( recordTypeId );
		uLog.setSourceId( sourceId ); //Coming from SOURCEOID
		uLog.setStatusId( LegacyUpdateConstants.STATUS_PENDING_UPDATE );
		uLog.setCriminalCaseId( caseId );
		uLog.setRecordData( recordData );
		uLog.bind();
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getMySqlConnection( String url ) throws Exception {
	    String driver = "net.sourceforge.jtds.jdbc.Driver";
	    String username = "JIMS2-CJAD_ProgramReferral";
	    String password = "UpdateData";

	    Class.forName( driver );
	    Connection conn = DriverManager.getConnection(url, username, password);
	    return conn;
	  }
}
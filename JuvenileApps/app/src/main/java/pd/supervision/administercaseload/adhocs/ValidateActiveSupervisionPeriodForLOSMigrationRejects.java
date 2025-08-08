package pd.supervision.administercaseload.adhocs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.supervisionorder.GetActiveSupervisionPeriodEvent;
import mojo.km.config.ConnectionProperties;
import mojo.km.config.MojoProperties;
import mojo.km.transaction.multidatasource.TransactionManager;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;

import pd.supervision.manageworkgroup.WorkGroup;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionPeriod;


public class ValidateActiveSupervisionPeriodForLOSMigrationRejects {
	public static void main(String[] args) {
 			ValidateActiveSupervisionPeriodForLOSMigrationRejects ladm = new ValidateActiveSupervisionPeriodForLOSMigrationRejects();
		    try {
				ladm.execute();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   }
		private static final String SQL1 = "SELECT * FROM JIMS2.CSSPVNORDERPER WHERE AGENCY_ID='CSC' AND SPRVISIONENDDATE IS NULL AND DEFENDANT_ID='";
		private static final String SQL2 = "' FOR READ ONLY";
		private void execute() throws Exception {
	    FileReader badLosRecordsFileReader = null;
	    BufferedReader badLosRecordsBufferedReader = null;
		BufferedWriter errorFile = null;
	    ConnectionProperties cProps = MojoProperties.getInstance().getConnectionProperties("JDBC");

	    System.out.println("Load driver: "+cProps.getDriver());
	    Class.forName(cProps.getDriver());
	    String conUrl = cProps.getTestUrl();
	 
		Connection readConnection = DriverManager.getConnection(conUrl, cProps.getUserID(), cProps.getPassword());
	       
		String spn = null;
		String prevSpn = "";
		int recordsRead = 0;
		int badRecords = 0;
		String losRec = null;
		int readCount = 0;
		
		try {
	        File badLosRecords = new File("C:\\JIMS.PR.BAD.LOS");
		    badLosRecordsFileReader = new FileReader(badLosRecords);
			badLosRecordsBufferedReader = new BufferedReader(badLosRecordsFileReader);
			
			errorFile = createFile("C:\\LOS.ERRORS", errorFile);
			boolean firstRec = true;
			while (badLosRecordsFileReader.read() > -1) {
				losRec = badLosRecordsBufferedReader.readLine();
				recordsRead++;
				if (losRec.length() < 8){
					errorFile.write("BAD INPUT RECORD AFTER SPN " + spn + " BAD LOS REC=" + losRec);
					errorFile.write('\n');
					badRecords++;
					continue;
				}
				if (firstRec){
					spn = losRec.substring(0,7);
					spn = "0" + spn;
					firstRec = false;
				} else {
					spn = losRec.substring(0,8);
				}
				if (spn.startsWith("*")){
					break;
				} /* else if (!spn.startsWith("0")){
					//spn = losRec.substring(2,10);
					errorFile.write("BAD RECORD DOESN'T START WITH 0 - " + losRec + " LENGTH=" + losRec.length());
					errorFile.write('\n');
					badRecords++;
					continue;
				} */
				if (spn.equals(prevSpn)){
					continue;
				} else {
					prevSpn = spn;
				}
//				GetActiveSupervisionPeriodEvent reqEvent = new GetActiveSupervisionPeriodEvent();
//				reqEvent.setAgencyId("CSC");
//				reqEvent.setSpn(spn);
				// Iterator iter = SupervisionPeriod.findAll(reqEvent);
				/*List aList = CollectionUtil.iteratorToList(iter);
				
				if (aList.size() > 0){
					errorFile.write("SPN ON ACTIVE SUPERVSION WITH BAD LOS - " + spn);
					errorFile.write('\n');
					badRecords++;
				} */

				if (readCount > 500){
					readConnection.commit();
					readCount= 0;
				} 
		        StringBuffer sql = new StringBuffer(SQL1);
		        sql.append(spn);
		        sql.append(SQL2);

		        ResultSet rs =  null;
		        Statement statement = null;
		        try {
		            statement = readConnection.createStatement();
		            rs = statement.executeQuery(sql.toString());
					readCount++;
		            if (rs != null && rs.next()){
						errorFile.write("SPN ON ACTIVE SUPERVISION WITH BAD LOS - " + losRec);
						errorFile.write('\n');
						badRecords++;
		            } 
		        } catch (SQLException e) {
		           e.printStackTrace();
		        } finally {
		            try {
		                if (rs != null){
		                	rs.close();
		                }
		                statement.close();
		            } catch (SQLException e1) {
		                e1.printStackTrace();
		            }
		        }
			}
			badLosRecordsBufferedReader.close();
			badLosRecordsFileReader.close();
						
		} catch (Exception e) {
			try {
				errorFile.write("SPN " + spn + " LOS RECORD=" + losRec );
				errorFile.write('\n');
				errorFile.write(" Exception thrown: " + e.getMessage());
				errorFile.write('\n');
				badLosRecordsBufferedReader.close();
				badLosRecordsFileReader.close();
			} catch (Exception e1) {
				System.out.println("SPN " + spn + " LOS RECORD=" + losRec + " Exception thrown: " + e1.getMessage());
			}
		} finally {
			try {
				errorFile.write("TOTAL RECORDS READ=" + recordsRead);
				errorFile.write('\n');
				errorFile.write("TOTAL BAD RECORDS=" + badRecords);
				errorFile.write('\n');
				errorFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
	}
    public static BufferedWriter createFile(String fileName, BufferedWriter bufferedWriter){
        StringBuffer sb = new StringBuffer(fileName);
        sb.append(".");
        sb.append(DateUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
        sb.append(".txt");
        File file = new File(sb.toString());
        bufferedWriter = null;
        try {
            FileWriter fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }    
        return bufferedWriter;
    }

}

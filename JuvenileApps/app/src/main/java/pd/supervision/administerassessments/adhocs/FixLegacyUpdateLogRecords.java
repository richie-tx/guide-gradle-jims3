package pd.supervision.administerassessments.adhocs;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import messaging.legacyupdates.ProcessLegacyLogCorrectionsEvent;
import mojo.km.config.ConnectionProperties;
import mojo.km.config.MojoProperties;
import mojo.km.transaction.multidatasource.TransactionManager;
import naming.LegacyUpdateConstants;
import naming.PDConstants;

import pd.contact.user.UserProfile;
import pd.supervision.administerassessments.datamigration.AssessmentDataMigrationHelper;
import pd.supervision.legacyupdates.LegacyUpdateLog;

public class FixLegacyUpdateLogRecords {
	private static boolean isUpdate;
	public FixLegacyUpdateLogRecords(String configFileName, String runType) {
	    super();
	    System.setProperty("mojo.config", configFileName);
	    System.out.println("CONFIGURATION FILE="+configFileName);
	    System.out.println("RUN TYPE=" + runType);
		mojo.km.context.ContextManager.currentContext();
		if (runType.equals("UPDATE")){
			isUpdate = true;
		} else {
			isUpdate = false;
		}
	}

	private static final int READ_MAX = 3900;
	private int readConnectionCount = 0;

	public static void main (String args[]){
        if (!(args != null && args.length == 2)){
		    System.out.println("!!!!! INVALID PARMS !!!!!");
		} else {
			FixLegacyUpdateLogRecords fix = new FixLegacyUpdateLogRecords(args[0],args[1]);
			fix.execute();
		}



	}
	private void execute(){
		BufferedWriter outputFile = null;
		StringBuffer runTime = new StringBuffer("STARTED at " + new Date());
	    outputFile = AssessmentDataMigrationHelper.createFile("C:\\JDSACSA\\OPID.ERRORS", outputFile);
		Connection readConnection;
		try {
			readConnection = getConnection();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			return;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return;
		}

		//List logList = LegacyUpdateLog.findAll("statusId","P");
	    //SELECT * FROM JIMS2.CSLEGACYUPDATLOG WHERE OPID IS NULL AND RECTYPE IN ('T21','T22') FOR READ ONLY;
	    List logList = LegacyUpdateLog.findAll(new ProcessLegacyLogCorrectionsEvent());//This event mapping is hijacked!!!
		LegacyUpdateLog log = null;
		String caseNum = null;
		String opId = null;
		boolean isChanged = false;
		int readCount = 0;
		int updateCount = 0;
		Map userMap = new HashMap();
		for (int i = 0; i < logList.size(); i++) {
			if (readCount == READ_MAX){
				break;
			}
			readCount++;
			log = (LegacyUpdateLog) logList.get(i);
			caseNum = null;
			opId = null;
			isChanged = false;
			if (!log.getRecordTypeId().equals(T21)
					&& !log.getRecordTypeId().equals(T22)){
				continue;
			}
			if (log.getOpId() !=  null && !log.getOpId().equals(PDConstants.BLANK)){
				//If there's something in these fields there's no need to fix it
				continue;
			}
			String msg = printIt(log, PDConstants.BLANK);
			if (log.getCriminalCaseId() == null ||
				log.getCriminalCaseId().equals(PDConstants.BLANK)){
				//caseNum = AssessmentHelper.getCaseNumberForCSTSReporting(log.getSpn());
				caseNum = getCaseNum(readConnection,log.getSpn());
				if (caseNum != null){//May no longer be on supervision.
					log.setCriminalCaseId(caseNum);
					isChanged = true;
				} else {
					log.setCriminalCaseId(PDConstants.BLANK);
				}
			}
			if (log.getOpId() == null 
					|| log.getOpId().equals(PDConstants.BLANK)){
				opId = (String)userMap.get(log.getCreateUserID());
				if (opId == null){
					UserProfile userProfile = UserProfile.find(log.getCreateUserID());
					if (userProfile != null){
						userMap.put(log.getCreateUserID(), userProfile.getOperatorId());
						opId = userProfile.getOperatorId();
					}
				}
				if (opId != null){
					log.setOpId(opId);
					isChanged = true;
				}
			}
			if (isChanged){
				//Change XML
				updateCount++;
				processXML(log);
				//System.out.println(printIt(log, msg).toString());
				try {
					outputFile.write(printIt(log, msg).toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (isUpdate){
					log.bind();
				} else {
					TransactionManager tm = TransactionManager.getInstance();
					tm.removeUpdated(log);
				}
			}
		}
		try {
			outputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		if (isUpdate){
			System.out.println("UPDATE RUN");
		} else {
			System.out.println("EDIT RUN");
		}
		System.out.println("RECORDS PROCESSED=" + readCount);
		System.out.println("RECORDS UPDATED=" + updateCount);

		runTime.append(" FINISHED at " + new Date());
		System.out.println(runTime.toString());

	}
	private static String printIt(LegacyUpdateLog log, String aString) {
		StringBuffer sb = new StringBuffer(aString);
		if (aString.equals(PDConstants.BLANK)){
			sb.append("BEFORE: ");
		} else {
			sb.append(" AFTER: ");
		}
		sb.append("OID=");
		sb.append(log.getOID());
		sb.append(" SPN=");
		sb.append(log.getSpn());
		sb.append(" CRIMINALCASE_ID=");
		sb.append(log.getCriminalCaseId());
		sb.append(" OPID=");
		sb.append(log.getOpId());
		sb.append(" RECDATA=");
		sb.append(log.getRecordData());
		return sb.toString();
	}
	private static final String T21="T21";
	private static final String T22 = "T22";
	private static LegacyUpdateLog processXML(LegacyUpdateLog log) {
		if (log.getRecordTypeId().equals(T21)){
			processT21(log);
		} else if (log.getRecordTypeId().equals(T22)){
			processT22(log);
		}
		return log;
	}
	private static LegacyUpdateLog processT22(LegacyUpdateLog log) {
		String recordData = log.getRecordData();
		try {
			Element element = getXMLElement(recordData, LegacyUpdateConstants.XML_SCSDATA);
			if (element != null)
		    {
				Element root = new Element(LegacyUpdateConstants.XML_ASSESSMENTDATA);
				Element child = new Element(LegacyUpdateConstants.XML_SCSDATA);
				child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ACTION,getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ACTION));
				child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTCODE,getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTCODE));
				child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTDATE, getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTDATE));
				child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTCLASSIFICATION, getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTCLASSIFICATION));
				if (log.getCriminalCaseId().length() == 15){
					child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CASENUM, log.getCriminalCaseId().substring(3));
				} else {
					child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CASENUM, log.getCriminalCaseId());
				}
				root.addContent(child);	
				String newRecData = writeToXML(root);
				log.setRecordData(newRecData);
		    }
			return log;

		} catch (JDOMException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		
	}
	private static LegacyUpdateLog processT21(LegacyUpdateLog log) {
		String recordData = log.getRecordData();
		try {
			Element element = getXMLElement(recordData, LegacyUpdateConstants.XML_RISKNEEDSSCORE);
			if (element != null)
		    {
				Element root = new Element(LegacyUpdateConstants.XML_ASSESSMENTDATA);
				Element child = new Element(LegacyUpdateConstants.XML_RISKNEEDSSCORE);
				child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ACTION,getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ACTION));
				child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTTYPE,getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTTYPE));
				child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTDATE, getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTDATE));
				child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_NEEDSSCORE, getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_NEEDSSCORE));
				child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_RISKSCORE, getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_RISKSCORE));
				if (log.getCriminalCaseId().length() == 15){
					child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CASENUM, log.getCriminalCaseId().substring(3));
				} else {
					child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CASENUM, log.getCriminalCaseId());
				}
				root.addContent(child);	
				String newRecData = writeToXML(root);
				log.setRecordData(newRecData);
		    }
			return log;

		} catch (JDOMException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	protected static String getAttributeVal(Element element, String attributeName){
		Attribute attr = element.getAttribute(attributeName);
		if(attr != null){
		    return attr.getValue();  
	    }else{
	    	return "";
	    }
	}
	protected static Element getXMLElement(String recordData, String elementName) throws JDOMException {
        SAXBuilder saxReader = new SAXBuilder();
    	ByteArrayInputStream bais = new ByteArrayInputStream(recordData.trim().getBytes());
        InputStream is = new BufferedInputStream(bais);
        Document document = saxReader.build(is);
	    Element root = document.getRootElement();
	    if(root != null){
	        return root.getChild(elementName);	
	    }
	    return null;
	}
	public static String writeToXML(Element root) throws IOException {		
		Document document = new Document(root);		
		XMLOutputter outputter = new XMLOutputter();
		outputter.setOmitDeclaration(true);
		OutputStream out = new ByteArrayOutputStream();
		outputter.output(document, out);
		return out.toString();	
	}	
	private static String JDBC = "JDBC";
    public Connection getConnection() throws ClassNotFoundException, SQLException
    {
        ConnectionProperties cProps = MojoProperties.getInstance().getConnectionProperties(JDBC);

        System.out.println("Load driver: "+cProps.getDriver());
        Class.forName(cProps.getDriver());
        String conUrl = cProps.getTestUrl();
        Connection connection = DriverManager.getConnection(conUrl, cProps.getUserID(), cProps.getPassword());
        connection.setReadOnly(true);
        return connection;
    }
	private static final String SELECT_SUPERVISION_PERIOD = "SELECT * FROM JIMS2.CSSPVNORDERPER WHERE DEFENDANT_ID='";
	private static final String SELECT_SUPERVISION_PERIOD2 = " AND AGENCY_ID = 'CSC' AND SPRVISIONENDDATE IS NULL";
	private static final String CRIMINALCASE_ID = "CRIMINALCASE_ID";
    private static final String QUOTE = "'";
    private static final String FOR_READ_ONLY = " FOR READ ONLY";

	public String getCaseNum(Connection connection, String spn) 
	{
		// find active supervision period
		//FROM JIMS2.CSSPVNORDERPER WHERE DEFENDANT_ID = ? and AGENCY_ID = ? AND SPRVISIONENDDATE IS NULL
        StringBuffer sql = new StringBuffer(SELECT_SUPERVISION_PERIOD);
        sql.append(spn);
        sql.append(QUOTE);
        sql.append(SELECT_SUPERVISION_PERIOD2);
        sql.append(FOR_READ_ONLY);
        Statement statement = null;
        ResultSet rs = null;
        String aString = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql.toString());
            if (rs.next()){
            	aString = rs.getString(CRIMINALCASE_ID);
            }
        } catch (SQLException e) {
			System.out.println(sql.toString() + " " + e.getMessage());  	
        } finally {
            try {
				statement.close();
				rs.close();
			} catch (SQLException e) {
			}
        }
        readConnectionCount++;
	        if (readConnectionCount > 300){
	        	readConnectionCount = 0;
	        	try {
				connection.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        }

    	return aString;
    }
}

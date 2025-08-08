package pd.supervision.legacyupdates.adhocs;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.lowagie.text.xml.XmlWriter;

import naming.CaseloadConstants;
import naming.LegacyUpdateConstants;
import naming.PDConstants;

import messaging.legacyupdates.GetLegacyUpdateRecordsToFixEvent;
import messaging.legacyupdates.UpdateAssignmentDataEvent;
import mojo.km.context.multidatasource.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import pd.supervision.administercaseload.CaseAssignment;
import pd.supervision.administercaseload.datamigration.LegacyCaseAssignmentExtract;
import pd.supervision.legacyupdates.HandlerConfig;
import pd.supervision.legacyupdates.ILegacyUpdateHandler;
import pd.supervision.legacyupdates.ILegacyUpdates;
import pd.supervision.legacyupdates.LegacyUpdateLog;
import pd.supervision.legacyupdates.LegacyUpdatesImpl;
import pd.supervision.legacyupdates.handlers.CSTSProbationOfficerUpdateHandler;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrganizationStaffPosition;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffProfile;

public class FixT24Adhoc {

	public FixT24Adhoc() {
		super();
		System.setProperty("mojo.config", "SRVPJ1P.xml");
		//System.setProperty("mojo.config", "multisource.xml");
		mojo.km.context.ContextManager.currentContext();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FixT24Adhoc fixit = new FixT24Adhoc();
		
		GetLegacyUpdateRecordsToFixEvent gEvent = new GetLegacyUpdateRecordsToFixEvent();
		gEvent.setRecType("T24");
		//Find all T24 records with:
		//RECSTATUS='E' AND RECTYPE = ? AND (CSTSSEQNO IS NULL OR CSTSSEQNO='') AND (RECDATA IS NULL OR RECDATA='')
		List list = LegacyUpdateLog.findAll(gEvent);
		LegacyUpdateLog log = null;
		CaseAssignment caseAssignment = null;
		CSCDOrganizationStaffPosition staffPosition = null;
		UpdateAssignmentDataEvent reqEvent = null;
		String recData = null;
		IHome home = new Home();
		BufferedWriter outputfile = null;
		outputfile = createFile("C:\\LegacyUpdateLogCorrections.FixT24Adhoc", outputfile);
	
		for (int i = 0; i < list.size(); i++) {
			log = (LegacyUpdateLog) list.get(i);
			List caseList = CollectionUtil.iteratorToList(CaseAssignment.findAll("criminalCaseId", log.getCriminalCaseId()));

			if (caseList != null && caseList.size() > 0){
				caseAssignment = (CaseAssignment) caseList.get(0);
				if (caseAssignment.getOID().equals(log.getSourceId())){
					if (caseAssignment.getCaseState().equals(CaseloadConstants.OFFICER_ASSIGNED)
							|| caseAssignment.getCaseState().equals(CaseloadConstants.OFFICER_ACKNOWLEDGED)){
						List staffList = CollectionUtil.iteratorToList
							(CSCDOrganizationStaffPosition.findAll("staffPositionId", caseAssignment.getAssignedStaffPositionId()));
						if (staffList != null && staffList.size() > 0){
							staffPosition = (CSCDOrganizationStaffPosition) staffList.get(0);
							reqEvent = new UpdateAssignmentDataEvent();
							reqEvent.setAssignmentDate(caseAssignment.getOfficerAssignDate());
							reqEvent.setCjadNum(staffPosition.getCjadNum());
							reqEvent.setProbationOfficerInd(staffPosition.getProbationOfficerInd());
							try {
								recData = xmlWriter(reqEvent);
								if (recData != null && !recData.equals(PDConstants.BLANK)){
									log.setProcMessage(PDConstants.BLANK);
									log.setStatusId("A");
									log.setRecordData(recData);
									home.bind(log);
									writeRec(true, log, outputfile);
								}
							} catch (IOException e) {
								e.printStackTrace();
								return;
							}
						}
					} else {
						writeRec(false, log, outputfile);
					}
				}
			}
		
		}
		try {
			outputfile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private static final char CARRIAGE_RETURN = '\n';
	private static void writeRec(boolean processed, LegacyUpdateLog log, BufferedWriter bufferedWriter){
	     try {
	       	if (processed){
	            StringBuffer sb = new StringBuffer();
	            sb.append("LEGACYUPDATLOG_ID=" + log.getOID());
	            sb.append(" SPN=" + log.getSpn());
	            sb.append(" RECTYPE=" + log.getRecordTypeId());
	            sb.append(" SOURCEOID=" + log.getSourceId());
	            sb.append(" CRIMINALCASE_ID=" + log.getCriminalCaseId());
	            sb.append(" CSTSSEQNO=" + log.getCstsSeqNo());
	            sb.append(" OPID=" + log.getOpId());
	            sb.append(" RECDATA=" + log.getRecordData());
	            bufferedWriter.write(sb.toString());
	       	} else {
				StringBuffer sb = new StringBuffer();
				sb.append("REJECTED-LEGACYUPDATLOG_ID=" + log.getOID());
				sb.append(" SPN=" + log.getSpn());
				sb.append(" RECTYPE=" + log.getRecordTypeId());
				sb.append(" SOURCEOID=" + log.getSourceId());
				sb.append(" CRIMINALCASE_ID=" + log.getCriminalCaseId());
				sb.append(" CSTSSEQNO=" + log.getCstsSeqNo());
				sb.append(" OPID=" + log.getOpId());
				sb.append(" RECORD NOT PROCESSED - NOT IN OFFICER ASSIGNED STATE");
				sb.append(CARRIAGE_RETURN);
				bufferedWriter.write(sb.toString());
	       	}
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
    private static BufferedWriter createFile(String fileName, BufferedWriter bufferedWriter){
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
	protected static final String DATE_FORMAT = "yyyyMMdd";
	private static String xmlWriter(UpdateAssignmentDataEvent reqEvent) throws IOException{
		Element root = new Element(LegacyUpdateConstants.XML_ASSIGNMENTTDATA);
		Element child = new Element(LegacyUpdateConstants.XML_SUPERVISINGOFFICERINFO);
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CJADNUMBER,reqEvent.getCjadNum());
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_POI,reqEvent.getProbationOfficerInd());		
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSIGNMENTDATE,(reqEvent.getAssignmentDate() == null)?"":DateUtil.dateToString(reqEvent.getAssignmentDate(), DATE_FORMAT));

		root.addContent(child);	
		return writeToXML(root);
	}
	private static String writeToXML(Element root) throws IOException {		
		Document document = new Document(root);		
		XMLOutputter outputter = new XMLOutputter();
		outputter.setOmitDeclaration(true);
		OutputStream out = new ByteArrayOutputStream();
		outputter.output(document, out);
		return out.toString();	}	

}

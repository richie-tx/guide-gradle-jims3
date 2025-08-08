package pd.supervision.legacyupdates.adhocs;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import messaging.legacyupdates.GetLegacyCAS1RecordsToFixEvent;
import messaging.legacyupdates.UpdateAssignmentDataEvent;
import mojo.km.context.multidatasource.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import naming.CaseloadConstants;
import naming.LegacyUpdateConstants;
import naming.PDConstants;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import pd.supervision.administercaseload.CaseAssignment;
import pd.supervision.legacyupdates.LegacyUpdateLog;
import pd.supervision.supervisionstaff.Organization;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrganizationStaffPosition;

public class FixFascasAdHoc {

	public FixFascasAdHoc() {
		super();
		System.setProperty("mojo.config", "SRVPJ1P.xml");
		//System.setProperty("mojo.config", "multisource.xml");
		mojo.km.context.ContextManager.currentContext();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FixFascasAdHoc fixit = new FixFascasAdHoc();
			
		GetLegacyCAS1RecordsToFixEvent gEvent = new GetLegacyCAS1RecordsToFixEvent();
			//Find all CAS1 records with:
			//RECSTATUS='E' AND RECTYPE = 'CAS1' AND (FASSEQNO IS NULL OR FASSEQNO='') AND (RECDATA IS NULL OR RECDATA='')
			List list = LegacyUpdateLog.findAll(gEvent);
			LegacyUpdateLog log = null;
			CaseAssignment caseAssignment = null;
			CSCDOrganizationStaffPosition staffPosition = null;
			Organization org = null;
			UpdateAssignmentDataEvent reqEvent = null;
			String recData = null;
			IHome home = new Home();
			BufferedWriter outputfile = null;
			outputfile = createFile("C:\\LegacyUpdateLogCorrections.FixCAS1Adhoc", outputfile);
		
			for (int i = 0; i < list.size(); i++) {
				log = (LegacyUpdateLog) list.get(i);
				List caseList = CollectionUtil.iteratorToList(CaseAssignment.findAll("criminalCaseId", log.getCriminalCaseId()));

				if (caseList != null && caseList.size() > 0){
					caseAssignment = (CaseAssignment) caseList.get(0);
					if (caseAssignment.getOID().equals(log.getSourceId())){
						if (caseAssignment.getCaseState().equals(CaseloadConstants.OFFICER_ASSIGNED)){
							List staffList = CollectionUtil.iteratorToList
								(CSCDOrganizationStaffPosition.findAll("staffPositionId", caseAssignment.getAssignedStaffPositionId()));
							if (staffList != null && staffList.size() > 0){
								staffPosition = (CSCDOrganizationStaffPosition) staffList.get(0);
								reqEvent = new UpdateAssignmentDataEvent();
								reqEvent.setCjadNum(staffPosition.getCjadNum());
								reqEvent.setProbationOfficerInd(staffPosition.getProbationOfficerInd());
								reqEvent.setAssignmentDate(caseAssignment.getOfficerAssignDate());
								try {
									recData = xmlWriter(reqEvent);
									if (recData != null && !recData.equals(PDConstants.BLANK)){
										log.setProcMessage(PDConstants.BLANK);
										log.setStatusId("C");
										log.setRecordData(recData);
										home.bind(log);
										writeRec(true, log, outputfile);
									}
								} catch (IOException e) {
									e.printStackTrace();
									return;
								}
							}
						} else if (caseAssignment.getCaseState().equals(CaseloadConstants.PROGRAM_UNIT_ASSIGNED)){
							org = Organization.find(caseAssignment.getProgramUnitId());
							if (org != null){
								reqEvent = new UpdateAssignmentDataEvent();
								reqEvent.setProbationOfficerInd(org.getProbationOfficerInd());
								reqEvent.setAssignmentDate(caseAssignment.getProgramUnitAssignDate());
								try {
									recData = xmlWriter(reqEvent);
									if (recData != null && !recData.equals(PDConstants.BLANK)){
										log.setProcMessage(PDConstants.BLANK);
										log.setStatusId("C");
										log.setRecordData(recData);
										home.bind(log);
										writeRec(true, log, outputfile);
									}
								} catch (IOException e) {
									e.printStackTrace();
									return;
								}
								
							}

						} else if (caseAssignment.getCaseState().equals(CaseloadConstants.CASE_CLOSED)){
							reqEvent = new UpdateAssignmentDataEvent();
							reqEvent.setProbationOfficerInd("**");
							reqEvent.setTransactionDate(caseAssignment.getTerminationDate());
							try {
								recData = xmlWriter(reqEvent);
								if (recData != null && !recData.equals(PDConstants.BLANK)){
									log.setProcMessage(PDConstants.BLANK);
									log.setStatusId("C");
									log.setRecordData(recData);
									home.bind(log);
									writeRec(true, log, outputfile);
								}
							} catch (IOException e) {
								e.printStackTrace();
								return;
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
		            sb.append(" FASSEQNO=" + log.getFasSeqNo());
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
					sb.append(" FASSEQNO=" + log.getFasSeqNo());
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
			Element child = new Element(LegacyUpdateConstants.XML_ADULTPROBATIONFEE);
			if (reqEvent.getCjadNum() == null){
				child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CJADNUMBER,PDConstants.BLANK);
			} else {
				child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CJADNUMBER,reqEvent.getCjadNum());
			}
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

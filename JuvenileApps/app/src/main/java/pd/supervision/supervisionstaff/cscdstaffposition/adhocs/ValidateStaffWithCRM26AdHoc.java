package pd.supervision.supervisionstaff.cscdstaffposition.adhocs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import naming.PDConstants;

import pd.contact.user.UserProfile;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrganizationStaffPosition;
import pd.supervision.supervisionstaff.legacycscdstaff.LegacySupervisionStaff;

import messaging.contact.domintf.IName;
import mojo.km.context.multidatasource.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;

public class ValidateStaffWithCRM26AdHoc {

	public ValidateStaffWithCRM26AdHoc(String name) {
		super();
		System.setProperty("mojo.config", "SRVPJ1P.xml");
		mojo.km.context.ContextManager.currentContext();
	}
	public static void main(String[] args) {
		ValidateStaffWithCRM26AdHoc aTest = new ValidateStaffWithCRM26AdHoc("testing");
		aTest.testValidateStaff();
   }
	public void setUp() throws Exception {
	}
	private final static String SEVEN_ZEROES = "0000000";
	private final static String EIGHT_ZEROES = "00000000";
	private final static String ONE_ZERO = "0";
	public void testValidateStaff(){
		char NEWLINE = '\n';
		try {
		    File file = new File("C:\\STAFFPOSITION.ERRORS.TXT");
	        FileWriter fileWriter = new FileWriter(file);
		    BufferedWriter errorFile = new BufferedWriter(fileWriter);
			IHome home = new Home();
			Iterator iter = home.findAll(CSCDOrganizationStaffPosition.class);
			List staffList = CollectionUtil.iteratorToList(iter);
			CSCDOrganizationStaffPosition staffPos = null;
			LegacySupervisionStaff legacyStaff = null;
			StringBuffer errorLog = null;
			UserProfile userProfile = null;
			for (int i = 0; i < staffList.size(); i++) {
				staffPos = (CSCDOrganizationStaffPosition) staffList.get(i);
				if (!staffPos.getPositionStatusCode().equals("A")){
					continue;
				}
				errorLog = new StringBuffer();
				if (staffPos.getProbationOfficerInd() != null
						&& !staffPos.getProbationOfficerInd().equals("")){
					legacyStaff = LegacySupervisionStaff.find(staffPos.getProbationOfficerInd());
					StringBuffer posNameMsg = new StringBuffer();
					posNameMsg.append("Position Name="+staffPos.getPositionName()+" ");
					if (legacyStaff == null){
						errorLog.append(posNameMsg).append("POI not found in CRM26:"+staffPos.getProbationOfficerInd());
						errorFile.write(NEWLINE);
						errorFile.write(errorLog.toString());
						continue;
					} 
					if (staffPos.getUserProfileId() != null){
						userProfile = staffPos.getUserProfile();
						if (userProfile != null){
							IName name = userProfile.getName();
							if (legacyStaff.getFirstName() == null){
								legacyStaff.setFirstName(PDConstants.BLANK);
							}
							if (legacyStaff.getMiddleName() == null){
								legacyStaff.setMiddleName(PDConstants.BLANK);
							}
							if (legacyStaff.getLastName() == null){
								legacyStaff.setLastName(PDConstants.BLANK);
							}
							if (name.getFirstName() == null){
								name.setFirstName(PDConstants.BLANK);
							}
							if (name.getMiddleName() == null){
								name.setMiddleName(PDConstants.BLANK);
							}
							if (name.getLastName() == null){
								name.setLastName(PDConstants.BLANK);
							}
//							if (!(name.getFirstName().equals(legacyStaff.getFirstName())
//								&& name.getLastName().equals(legacyStaff.getLastName()))){
							if (!name.getLastName().equals(legacyStaff.getLastName())){
								errorLog=new StringBuffer(posNameMsg);
								errorLog.append("CRM26 NAME="+legacyStaff.getFullName()
									+ " DOES NOT MATCH POSITION USER="+name.getFormattedName());
								errorFile.write(NEWLINE);
								errorFile.write(errorLog.toString());
							}
						} else{
							errorLog = new StringBuffer(posNameMsg);
							errorLog.append(" USER PROFILE NOT FOUND-" + staffPos.getUserProfileId());
							errorFile.write(NEWLINE);
							errorFile.write(errorLog.toString());
						}
					}
					if (staffPos.getCjadNum() == null
							|| staffPos.getCjadNum().equals(SEVEN_ZEROES)){
						staffPos.setCjadNum(PDConstants.BLANK);
					}
					if (legacyStaff.getCjadNum().equals(SEVEN_ZEROES)
							|| legacyStaff.getCjadNum().equals(ONE_ZERO)
							|| legacyStaff.getCjadNum().equals(EIGHT_ZEROES)){
						legacyStaff.setCjadNum(PDConstants.BLANK);
					}
					if (!staffPos.getCjadNum().equals(legacyStaff.getCjadNum())){
						errorLog = new StringBuffer(posNameMsg);
						errorLog.append("CRM26 CJAD=" + legacyStaff.getCjadNum()
								+ " DOES NOT MATCH POSITION CJAD="+staffPos.getCjadNum());
						errorFile.write(NEWLINE);
						errorFile.write(errorLog.toString());
					}
					String staffPhoneNum = null;
					if (staffPos.getPhoneNum() == null){
						staffPhoneNum = PDConstants.BLANK;
					}
					if (staffPos.getPhoneNum().length() > 3){
						staffPhoneNum = staffPos.getPhoneNum().substring(3);
					} else {
						staffPhoneNum = PDConstants.BLANK;
					}
					if (legacyStaff.getPhoneNum() == null){
						legacyStaff.setPhoneNum(PDConstants.BLANK);
					}
					if (!staffPhoneNum.equals(legacyStaff.getPhoneNum())){
						errorLog = new StringBuffer(posNameMsg);
						errorLog.append("CRM26 PHONE="+ legacyStaff.getPhoneNum()
								+ "DID NOT MATCH POSITION PHONE="+staffPos.getPhoneNum()+ " WAS CORRECTED");
						errorFile.write(NEWLINE);
						errorFile.write(errorLog.toString());
						legacyStaff.setPhoneNum(staffPhoneNum);
						legacyStaff.bind();
					}
					if (staffPos.getEffectiveDate() != null && legacyStaff.getEffectiveDate() != null){
						if (!staffPos.getEffectiveDate().equals(legacyStaff.getEffectiveDate())){
							errorLog = new StringBuffer(posNameMsg);
							errorLog.append("CRM26 EFFECTIVE DATE="
								+DateUtil.dateToString(legacyStaff.getEffectiveDate(), DateUtil.DATE_FMT_1)
								+ "DOES NOT MATCH POSITION EFFECTIVE DATE="
								+DateUtil.dateToString(staffPos.getEffectiveDate(), DateUtil.DATE_FMT_1));
							errorFile.write(NEWLINE);
							errorFile.write(errorLog.toString());
						}
					}
				}
			}
			System.out.println(errorLog.toString());
			errorFile.write(errorLog.toString());
			errorFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

package pd.supervision.supervisionstaff.cscdstaffposition.adhocs;

import java.util.List;

import pd.contact.user.UserProfile;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrganizationStaffPosition;
import pd.supervision.supervisionstaff.legacycscdstaff.LegacySupervisionStaff;

import mojo.km.utilities.CollectionUtil;

public class AssignLegacyStaffPositionAdHoc {

	public AssignLegacyStaffPositionAdHoc() {
		super();
		System.setProperty("mojo.config", "SRVPJ1P.xml");
		//System.setProperty("mojo.config", "multisource.xml");
		mojo.km.context.ContextManager.currentContext();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AssignLegacyStaffPositionAdHoc adHoc = new AssignLegacyStaffPositionAdHoc();
		String positionName = "YMAC AFT/RF - CSO 01";
		List staffList = CollectionUtil.iteratorToList(CSCDOrganizationStaffPosition.findAll("positionName", positionName));
		if (staffList.size() > 1){
			System.out.println("RETRIEVAL BY POSITION NAME RETURNED MULTIPLES");
			return;
		}
		CSCDOrganizationStaffPosition staffPos = (CSCDOrganizationStaffPosition) staffList.get(0);
		
		LegacySupervisionStaff legacyStaff = LegacySupervisionStaff.find(staffPos.getProbationOfficerInd());
		System.out.println("CRM26 POSITION TO BE ASSIGNED: "+positionName);
		printIt(legacyStaff, new StringBuffer(" BEFORE"));
		createLegacyStaff(legacyStaff, staffPos);
		
	}

	private static void createLegacyStaff(LegacySupervisionStaff legacyStaff, CSCDOrganizationStaffPosition staffPos) {
		LegacySupervisionStaff newStaff = new LegacySupervisionStaff();
		newStaff.setCjadNum(staffPos.getCjadNum());
		newStaff.setCourtNum(legacyStaff.getCourtNum());
		newStaff.setEffectiveDate(staffPos.getEffectiveDate());
		
		UserProfile userProfile = staffPos.getUserProfile();
		newStaff.setLastName(userProfile.getLastName());
		newStaff.setMiddleName(userProfile.getMiddleName());
		newStaff.setFirstName(userProfile.getFirstName());
		
		newStaff.setLocation(legacyStaff.getLocation());
		newStaff.setPhoneNum(legacyStaff.getPhoneNum());
		newStaff.setProbationOfficerInd(legacyStaff.getProbationOfficerInd());
		newStaff.setUnit(legacyStaff.getUnit());
		printIt(newStaff, new StringBuffer("AFTER"));
		newStaff.bind();
	}
	private static char NEWLINE = '\n';
	private static void printIt(LegacySupervisionStaff legacyStaff, StringBuffer sb){
		sb.append(NEWLINE);
		sb.append(" cjad=" +legacyStaff.getCjadNum());
		sb.append(NEWLINE);
		sb.append(" courtNum=" + legacyStaff.getCourtNum());
		sb.append(NEWLINE);
		sb.append(" effectiveDate="+legacyStaff.getEffectiveDate());
		sb.append(NEWLINE);
		sb.append(" location=" + legacyStaff.getLocation());
		sb.append(NEWLINE);
		sb.append(" phoneNum=" + legacyStaff.getPhoneNum());
		sb.append(NEWLINE);
		sb.append(" POI="+legacyStaff.getProbationOfficerInd());
		sb.append(NEWLINE);
		sb.append(" officerName=" +legacyStaff.getLastName());
		System.out.println(sb.toString());

	}
}

package pd.supervision.supervisionstaff.cscdstaffposition.adhocs;

import java.util.List;

import mojo.km.utilities.CollectionUtil;
import pd.contact.user.UserProfile;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrganizationStaffPosition;
import pd.supervision.supervisionstaff.legacycscdstaff.LegacySupervisionStaff;

public class UpdateLegacyStaffPositionAdHoc {
	public UpdateLegacyStaffPositionAdHoc() {
		super();
		System.setProperty("mojo.config", "SRVPJ1P.xml");
		mojo.km.context.ContextManager.currentContext();

	}
	public static void main(String[] args) {
		AssignLegacyStaffPositionAdHoc adHoc = new AssignLegacyStaffPositionAdHoc();
		String positionName = "NORTH - CSO 29";
		List staffList = CollectionUtil.iteratorToList(CSCDOrganizationStaffPosition.findAll("positionName", positionName));
		if (staffList.size() > 1){
			System.out.println("RETRIEVAL BY POSITION NAME RETURNED MULTIPLES");
			return;
		}
		CSCDOrganizationStaffPosition staffPos = (CSCDOrganizationStaffPosition) staffList.get(0);
		
		LegacySupervisionStaff legacyStaff = LegacySupervisionStaff.find(staffPos.getProbationOfficerInd());
		System.out.println("CRM26 POSITION TO BE ASSIGNED: "+positionName);
		printIt(legacyStaff, new StringBuffer(" BEFORE"));
		updateLegacyStaff(legacyStaff, staffPos);
		printIt(legacyStaff, new StringBuffer(" AFTER"));
	}
	private static void updateLegacyStaff(LegacySupervisionStaff legacyStaff,
			CSCDOrganizationStaffPosition staffPos) {
		UserProfile userProfile = staffPos.getUserProfile();
		if (userProfile != null){
			legacyStaff.setLastName(userProfile.getLastName());
			legacyStaff.setMiddleName(userProfile.getMiddleName());
			legacyStaff.setFirstName(userProfile.getFirstName());
			legacyStaff.bind();
		}

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

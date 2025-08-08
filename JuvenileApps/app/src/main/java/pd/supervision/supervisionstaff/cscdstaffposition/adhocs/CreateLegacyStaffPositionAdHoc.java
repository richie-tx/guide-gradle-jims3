package pd.supervision.supervisionstaff.cscdstaffposition.adhocs;

import java.util.Date;
import java.util.List;

import pd.contact.user.UserProfile;
import pd.supervision.administerserviceprovider.administerlocation.Location;
import pd.supervision.supervisionstaff.Organization;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrganizationStaffPosition;
import pd.supervision.supervisionstaff.legacycscdstaff.LegacySupervisionStaff;

import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;

public class CreateLegacyStaffPositionAdHoc {

	public CreateLegacyStaffPositionAdHoc() {
		super();
		System.setProperty("mojo.config", "SRVPJ1P.xml");
		//System.setProperty("mojo.config", "multisource.xml");
		mojo.km.context.ContextManager.currentContext();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CreateLegacyStaffPositionAdHoc adHoc = new CreateLegacyStaffPositionAdHoc();
		String positionName = "WHO AFT/R7 - CSO 04";
		List staffList = CollectionUtil.iteratorToList(CSCDOrganizationStaffPosition.findAll("positionName", positionName));
		if (staffList.size() > 1){
			System.out.println("RETRIEVAL BY POSITION NAME RETURNED MULTIPLES");
			return;
		}
		CSCDOrganizationStaffPosition staffPos = (CSCDOrganizationStaffPosition) staffList.get(0);
		
		createLegacyStaff(staffPos);
		
	}

	private static void createLegacyStaff(CSCDOrganizationStaffPosition staffPos) {
		LegacySupervisionStaff newStaff = new LegacySupervisionStaff();
		newStaff.setCjadNum(staffPos.getCjadNum());
		//newStaff.setEffectiveDate(staffPos.getEffectiveDate());
		Date aDate = DateUtil.stringToDate("06/01/2010", DateUtil.DATE_FMT_1);
		newStaff.setEffectiveDate(aDate);
		newStaff.setLastName("NO OFFICER ASSIGNED");
		/* UserProfile userProfile = staffPos.getUserProfile();
		newStaff.setLastName(userProfile.getLastName());
		newStaff.setMiddleName(userProfile.getMiddleName());
		newStaff.setFirstName(userProfile.getFirstName());*/
		
		if (staffPos.getPhoneNum() != null){
			if (staffPos.getPhoneNum().length() > 7){
				newStaff.setPhoneNum(staffPos.getPhoneNum().substring(3));
			} else {
				newStaff.setPhoneNum(staffPos.getPhoneNum());
			}
		} else {
			newStaff.setPhoneNum("");
		}

		Location location = staffPos.getLocation();
		newStaff.setLocation(location.getLocationName());
		//newStaff.setPhoneNum(legacyStaff.getPhoneNum());
		newStaff.setProbationOfficerInd(staffPos.getProbationOfficerInd());
       	Organization programUnitOrg = staffPos.getProgramUnit();
    	if (programUnitOrg != null){
    		newStaff.setUnit(programUnitOrg.getLegacyProgramUnit());
    	}
		
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
		sb.append(NEWLINE);
		sb.append(" unit=" +legacyStaff.getUnit());
		
		System.out.println(sb.toString());

	}
}

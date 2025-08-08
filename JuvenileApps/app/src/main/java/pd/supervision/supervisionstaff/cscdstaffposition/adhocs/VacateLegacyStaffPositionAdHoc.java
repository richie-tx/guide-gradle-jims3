package pd.supervision.supervisionstaff.cscdstaffposition.adhocs;

import java.util.Date;
import java.util.List;

import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;
import pd.supervision.supervisionstaff.legacycscdstaff.LegacySupervisionStaff;

import messaging.legacycscdstaff.UpdateLegacyStaffAssignmentEvent;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;

public class VacateLegacyStaffPositionAdHoc {

	public VacateLegacyStaffPositionAdHoc() {
		super();
		System.setProperty("mojo.config", "SRVPJ1P.xml");
		//System.setProperty("mojo.config", "multisource.xml");
		mojo.km.context.ContextManager.currentContext();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VacateLegacyStaffPositionAdHoc adHoc = new VacateLegacyStaffPositionAdHoc();
		//String positionName = "ANOTHER OFFICER 20080916";
		/* String positionName = "YMAC AFT/RH - CSO 02";
		List staffList = CollectionUtil.iteratorToList(CSCDStaffPosition.findAll("positionName", positionName));
		if (staffList.size() > 1){
			System.out.println("RETRIEVAL BY POSITION NAME RETURNED MULTIPLES");
			return;
		}
		CSCDStaffPosition staffPos = (CSCDStaffPosition) staffList.get(0);*/
		
		LegacySupervisionStaff legacyStaff = LegacySupervisionStaff.find("JM");
		System.out.println("CRM26 POSITION TO BE VACATED: "+legacyStaff.getProbationOfficerInd());
		printIt(legacyStaff, new StringBuffer(" BEFORE"));
		createLegacyStaff(legacyStaff, null);
		
	}

	private static void createLegacyStaff(LegacySupervisionStaff legacyStaff, CSCDStaffPosition staffPos) {
		LegacySupervisionStaff newStaff = new LegacySupervisionStaff();
		newStaff.setCjadNum("");
		newStaff.setCourtNum(legacyStaff.getCourtNum());
		//newStaff.setEffectiveDate(staffPos.getEffectiveDate());
		Date aDate = DateUtil.stringToDate("06/01/2010", DateUtil.DATE_FMT_1);
		newStaff.setEffectiveDate(aDate);
		newStaff.setLastName("NO OFFICER ASSIGNED");
		newStaff.setLocation(legacyStaff.getLocation());
		newStaff.setPhoneNum(legacyStaff.getPhoneNum());
		newStaff.setProbationOfficerInd(legacyStaff.getProbationOfficerInd());
		newStaff.setUnit("RAMH");
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

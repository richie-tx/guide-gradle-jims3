package pd.supervision.supervisionstaff.cscdstaffposition.adhocs;

import java.util.Date;

import mojo.km.utilities.DateUtil;
import pd.supervision.supervisionstaff.legacycscdstaff.LegacySupervisionStaff;

public class UpdateLegacyStaffPositionForProgramUnitAdHoc {

	public UpdateLegacyStaffPositionForProgramUnitAdHoc() {
		super();
		//System.setProperty("mojo.config", "SRVPJ1P.xml");
		System.setProperty("mojo.config", "WASJPT.xml");
		//System.setProperty("mojo.config", "multisource.xml");
		mojo.km.context.ContextManager.currentContext();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UpdateLegacyStaffPositionForProgramUnitAdHoc adHoc = new UpdateLegacyStaffPositionForProgramUnitAdHoc();
		
		LegacySupervisionStaff legacyStaff = LegacySupervisionStaff.find("BY");

//		Date aDate = DateUtil.stringToDate("06/01/2010", DateUtil.DATE_FMT_1);
//		legacyStaff.setEffectiveDate(aDate);
		//legacyStaff.setEffectiveDate(legacyStaff.getEffectiveDate());
		legacyStaff.setLastName("NEW CASES YMAC AFTERCARE");
		legacyStaff.setFirstName("");
		legacyStaff.setMiddleName("");
		//legacyStaff.setProbationOfficerInd(legacyStaff.getProbationOfficerInd());
		//legacyStaff.setUnit("YAFT");
		//legacyStaff.setLocation("CSCD-NORTH 2ND FLOOR");
		printIt(legacyStaff, new StringBuffer("AFTER"));
		legacyStaff.bind();
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

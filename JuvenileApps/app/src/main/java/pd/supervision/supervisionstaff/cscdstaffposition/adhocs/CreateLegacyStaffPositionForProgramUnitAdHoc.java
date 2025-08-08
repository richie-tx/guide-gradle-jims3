package pd.supervision.supervisionstaff.cscdstaffposition.adhocs;

import java.util.Calendar;
import java.util.Date;

import mojo.km.utilities.DateUtil;
import pd.supervision.supervisionstaff.legacycscdstaff.LegacySupervisionStaff;

public class CreateLegacyStaffPositionForProgramUnitAdHoc {

	public CreateLegacyStaffPositionForProgramUnitAdHoc() {
		super();
		System.setProperty("mojo.config", "WASJPT.xml");
		//System.setProperty("mojo.config", "multisource.xml");
		mojo.km.context.ContextManager.currentContext();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CreateLegacyStaffPositionForProgramUnitAdHoc adHoc = new CreateLegacyStaffPositionForProgramUnitAdHoc();
		
		
		
/*		
		LegacySupervisionStaff newStaff = new LegacySupervisionStaff();
		newStaff.setProbationOfficerInd("BX");
		newStaff.setLastName("NEW CASES WHO AFTERCARE");
		newStaff.setLocation("");
		newStaff.setPhoneNum("");
		Date aDate = DateUtil.stringToDate("20090901", "yyyyMMdd");
		//newStaff.setEffectiveDate(new Date());
		newStaff.setEffectiveDate(aDate);
    	newStaff.setUnit("WAFT");
    	
		printIt(newStaff, new StringBuffer("AFTER"));
		newStaff.bind();*/
		
		LegacySupervisionStaff newStaff = new LegacySupervisionStaff();
		newStaff.setProbationOfficerInd("BY");
		newStaff.setLastName("NEW CASES YMAC AFTERCARE");
		newStaff.setLocation("");
		newStaff.setPhoneNum("");
		Date aDate = DateUtil.stringToDate("20090901", "yyyyMMdd");
		//newStaff.setEffectiveDate(new Date());
		newStaff.setEffectiveDate(aDate);
    	newStaff.setUnit("YAFT");
    	
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

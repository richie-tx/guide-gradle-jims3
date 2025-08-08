package pd.juvenilewarrant.adhoc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.ujac.util.BeanComparator;

import pd.codetable.person.JuvenileSchoolDistrictCode;
import pd.juvenile.JuvenileSchoolHistory;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilewarrant.JuvenileWarrant;
import messaging.juvenile.GetJuvenileSchoolEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantsListEvent;
import mojo.km.context.multidatasource.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.DateUtil;

public class FindJuvenileWarrantsWithBadSchoolCodesAdHocUPDATE {
	private static final String ZEROES = "000000";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		FindJuvenileWarrantsWithBadSchoolCodesAdHocUPDATE adHoc = new FindJuvenileWarrantsWithBadSchoolCodesAdHocUPDATE();
		BufferedWriter badSchoolCodes = null;
		BufferedWriter badWarrantSchoolNoJJs = null;
        try {
        	Date currDate = new Date();
            StringBuffer sb = new StringBuffer("C:\\JUV_BAD_SCHOOL_CODES_UPDATE");
            sb.append(".");
            sb.append(DateUtil.dateToString(currDate, "yyyyMMddHHmmss"));
            sb.append(".txt");
            File file = new File(sb.toString());
            FileWriter fileWriter = new FileWriter(file);
            badSchoolCodes = new BufferedWriter(fileWriter);
            sb = new StringBuffer("C:\\JUV_BAD_SCHOOL_CODES_NO_JJS_UPDATE");
            sb.append(".");
            sb.append(DateUtil.dateToString(currDate, "yyyyMMddHHmmss"));
            sb.append(".txt");
            File file2 = new File(sb.toString());
            FileWriter fileWriter2 = new FileWriter(file2);
            badWarrantSchoolNoJJs = new BufferedWriter(fileWriter2);

        } catch (IOException e) {
            e.printStackTrace();
        }    
		
		GetJuvenileWarrantsListEvent reqEvent = new GetJuvenileWarrantsListEvent();
		List <JuvenileWarrant> warrantList = CollectionUtil.iteratorToList(JuvenileWarrant.findAll(reqEvent));
		JuvenileWarrant warrant = null;
		JJSJuvenile juvenile = null;
		String msg = null;
		int count = 0;
		int missingJJsCount = 0;
		Map schoolIdMap = new HashMap();
		Map schoolOidMap = new HashMap();
		IHome home = new Home();
		for (int i = 0; i < warrantList.size(); i++) {
			
			warrant = warrantList.get(i);
			juvenile = JJSJuvenile.find(warrant.getJuvenileNum().toString());
			if (juvenile != null){
	            String district = pd.km.util.Formatter.pad(juvenile.getSchoolDistrictId(), 3, '0', true);
	            String schoolCode = pd.km.util.Formatter.pad(juvenile.getSchoolId(), 3, '0', true);
	            StringBuffer sb = new StringBuffer(district);
	            sb.append(schoolCode);
		        if (!sb.toString().equals(ZEROES) && !sb.toString().equals(warrant.getSchoolCodeId())){
		        	if (warrant.getSchoolCodeId().length() < 6){
			        	count=count+1;
			        	msg = ("Juvenile="+ 
				        			juvenile.getJuvenileNum()+
				        			", warrantNumber=" +
				        			warrant.getOID() + 
				        			", dateOfIssue=" +
				        			DateUtil.dateToString(warrant.getDateOfIssue(),DateUtil.DATE_FMT_1) +
				        			", jjsSchool="+sb.toString()+
				        			", warrantSchool="+warrant.getSchoolCodeId() +
				        			", warrantSchoolChangedTo=" + sb.toString());
			        	warrant.setSchoolCodeId(sb.toString());
			        	home.bind(warrant);
		        	} else {
		        		continue;
		        	}
		    		try {
		    			badSchoolCodes.write(msg.toString());
		    			badSchoolCodes.write('\n');
		    		} catch (IOException e) {
		    			e.printStackTrace();
		    		}
	
		        } else if (warrant.getSchoolCodeId() != null && warrant.getSchoolCodeId().length() != 6){
		        	missingJJsCount=missingJJsCount+1;
		        	int startPos = warrant.getSchoolCodeId().length() - 3;
		        	List <JuvenileSchoolDistrictCode> schoolList = null;
		        	schoolList = (List) schoolIdMap.get(warrant.getSchoolCodeId().substring(startPos));
		        	if (schoolList == null){
		        		schoolList = CollectionUtil.iteratorToList(JuvenileSchoolDistrictCode.findAll("schoolCode", warrant.getSchoolCodeId().substring(startPos)));
		        		schoolIdMap.put(warrant.getSchoolCodeId().substring(startPos), schoolList);
		        	}
		        	if (schoolList.size() > 1){
		        		String theCodeOid = findSchoolCode(juvenile.getJuvenileNum(), warrant.getSchoolCodeId().substring(startPos), schoolList, schoolOidMap);
			        	if (theCodeOid != null){
			        		msg = ("Juvenile="+ 
				        			juvenile.getJuvenileNum()+
				        			", warrantNumber=" +
				        			warrant.getOID() + 
				        			", dateOfIssue=" +
				        			DateUtil.dateToString(warrant.getDateOfIssue(),DateUtil.DATE_FMT_1) +
				        			", jjsSchool="+sb.toString()+
				        			", warrantSchool="+warrant.getSchoolCodeId() +
				        			", warrantSchoolChangedTo=" + theCodeOid + 
				        			" - DISTRICT DERIVED FROM SCHOOL HISTORY RECORD");
				        	warrant.setSchoolCodeId(theCodeOid);
				        	home.bind(warrant);

			        	} else {
			        		msg = ("Juvenile="+ 
				        			juvenile.getJuvenileNum()+
				        			", warrantNumber=" +
				        			warrant.getOID() + 
				        			", dateOfIssue=" +
				        			DateUtil.dateToString(warrant.getDateOfIssue(),DateUtil.DATE_FMT_1) +
				        			", jjsSchool="+sb.toString()+
				        			", warrantSchool="+warrant.getSchoolCodeId() +
				        			", warrantSchoolChangedTo=" + "BLANK - MULTIPLE MATCHING SCHOOLS  - UNABLE TO DERIVE FROM HIST WITH SCHOOLCODE=" + warrant.getSchoolCodeId().substring(startPos));
				        	warrant.setSchoolCodeId(null);
				        	home.bind(warrant);
			        	}
		        	} else if (schoolList.size() == 1) {
			            JuvenileSchoolDistrictCode jsdc = schoolList.get(0);
			            schoolOidMap.put(jsdc.getOID(), jsdc);
			            district = pd.km.util.Formatter.pad(jsdc.getDistrictCode(), 3, '0', true);
			            schoolCode = pd.km.util.Formatter.pad(jsdc.getSchoolCode(), 3, '0', true);
			            sb = new StringBuffer(district);
			            sb.append(schoolCode);
			        	msg = ("Juvenile="+ 
			        			juvenile.getJuvenileNum()+
			        			", warrantNumber=" +
			        			warrant.getOID() + 
			        			", dateOfIssue=" +
			        			DateUtil.dateToString(warrant.getDateOfIssue(),DateUtil.DATE_FMT_1) +
			        			", jjsSchool="+sb.toString()+
			        			", warrantSchool="+warrant.getSchoolCodeId() +
			        			", warrantSchoolChangedTo=" + sb.toString());
			        	warrant.setSchoolCodeId(sb.toString());
			        	home.bind(warrant);
		        	} else {
			        	msg = ("Juvenile="+ 
			        			juvenile.getJuvenileNum()+
			        			", warrantNumber=" +
			        			warrant.getOID() + 
			        			", dateOfIssue=" +
			        			DateUtil.dateToString(warrant.getDateOfIssue(),DateUtil.DATE_FMT_1) +
			        			", jjsSchool="+sb.toString()+
			        			", warrantSchool="+warrant.getSchoolCodeId() +
			        			", warrantSchoolChangedTo=" + "BLANK");
			        	warrant.setSchoolCodeId(null);
			        	home.bind(warrant);
		        	}
		    		try {
		    			badWarrantSchoolNoJJs.write(msg.toString());
		    			badWarrantSchoolNoJJs.write('\n');
		    		} catch (IOException e) {
		    			e.printStackTrace();
		    		}
		        }
			}
			
			
		}
		try {
			System.out.println("TOTAL RECORDS PROCESSED=" + warrantList.size() + 
					" TOTAL SCHOOL CODE ERRORS=" + count + " TOTAL MISSING JJS SCHOOL=" + missingJJsCount);
			//errors.write("TOTAL ERRORS=" + count);
			badSchoolCodes.close();
			badWarrantSchoolNoJJs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String findSchoolCode(String juvenileNum, String schoolCode,
			List<JuvenileSchoolDistrictCode> schoolList, Map schoolOidMap) {
		GetJuvenileSchoolEvent reqEvent = new GetJuvenileSchoolEvent();
		reqEvent.setJuvenileNum(juvenileNum);
		List <JuvenileSchoolHistory> juvSchoolHistList = 
			CollectionUtil.iteratorToList(JuvenileSchoolHistory.findJuvenileSchoolHistory(reqEvent));
		//Sort by LASTATTENDEDDATE descending
		if (juvSchoolHistList.size() > 1){
			List sortedList = new ArrayList(juvSchoolHistList);
			ArrayList sortFields = new ArrayList();
			sortFields.add(new ReverseComparator(new BeanComparator("lastAttendedDate")));
			ComparatorChain sort = new ComparatorChain(sortFields);
			Collections.sort(sortedList, sort);
			juvSchoolHistList = sortedList;
		}
		
		JuvenileSchoolHistory schoolHist = null;
		JuvenileSchoolDistrictCode theCode = null;
		IHome home = new Home();
		String theOid = null;
		for (int i = 0; i < juvSchoolHistList.size(); i++) {
			schoolHist = juvSchoolHistList.get(i);
			theOid = schoolHist.getSchoolDistrictId() + schoolCode;
			theCode = (JuvenileSchoolDistrictCode) schoolOidMap.get(theOid);
			if (theCode == null){
				theCode = (JuvenileSchoolDistrictCode)home.find(theOid, JuvenileSchoolDistrictCode.class);
				if (theCode != null){
					schoolOidMap.put(theCode.getOID(), theCode);
				}
			}
			if (theCode != null){
				return theCode.getOID();
			}
		}
		
		return null;
	}

	public FindJuvenileWarrantsWithBadSchoolCodesAdHocUPDATE() {
		super();
		System.setProperty("mojo.config", "SRVPJ1P.xml");
		//System.setProperty("mojo.config", "multisource.xml");
		mojo.km.context.ContextManager.currentContext();	}

}

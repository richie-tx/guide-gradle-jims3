/*
 * Created on Oct 14, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mapping.juvenileFamily;

import java.util.Date;

import naming.PDConstants;
import mapping.WhereClauseHelper;
import messaging.family.GetFamilyMembersAdvancedEvent;
import messaging.family.GetFamilyMembersEvent;
import messaging.family.GetSuspiciousFamilyMembersEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetFamilyMemberClause
{
	private static final String STRING = "String";
	private static final String FIRSTNAME = "FIRSTNAME";
	private static final String LASTNAME = "LASTNAME";
	private static final String MIDDLENAME = "MIDDLENAME";
	private static final String SSN = "SSN";
	private static final String DATE = "Date";
	private static final String SEXCD = "SEXCD";
	private static final String DATEOFBIRTH = "DATEOFBIRTH";
	private static final String FAMMEMBER_ID = "FAMMEMBER_ID";
	private static final String FMEMMATCHVAL_CLAUSE = "FMEMMATCHVAL_ID IS NOT NULL";
	private static final String JUVENILENULL_CLAUSE = "JUVENILE_ID IS NOT NULL";
	private static final String DRIVING_LICENSE = "IDNUMBER";
	/**
	 * 
	 */
	public GetFamilyMemberClause()
	{
		super();
	}

	public String getClause(IEvent anEvent)
	{
		GetFamilyMembersEvent event = (GetFamilyMembersEvent) anEvent;
		StringBuffer buf = new StringBuffer();

		String firstName = event.getMemberFirstName();
		if (firstName != null && !firstName.equals(PDConstants.BLANK)) {	
			firstName = this.formatApostrophe(firstName, true);
		}
		String subClause = WhereClauseHelper.buildBuildClauseWithoutCoalesce(STRING, true, true, false, firstName, FIRSTNAME);
		buf = addToClause(buf, subClause);

		String lastName = event.getMemberLastName();
		if (lastName != null && !lastName.equals(PDConstants.BLANK)) {	
			lastName = this.formatApostrophe(lastName, true);
		}
		subClause = WhereClauseHelper.buildBuildClauseWithoutCoalesce(STRING, true, true, false, lastName, LASTNAME);
		buf = addToClause(buf, subClause);

		String middleName = event.getMemberMiddleName();
		if (middleName != null && !middleName.equals(PDConstants.BLANK)) {			
			middleName = this.formatApostrophe(middleName, true);
		}
		subClause = WhereClauseHelper.buildBuildClauseWithoutCoalesce(STRING, true, true, false, middleName, MIDDLENAME);
		buf = addToClause(buf, subClause);

		String sex = event.getMemberSexId();
		subClause = WhereClauseHelper.buildBuildClauseWithoutCoalesce(STRING, true, false, false, sex, SEXCD);
		buf = addToClause(buf, subClause);

		Date deathOfBirth = event.getMemberDateOfBirth();
		subClause = WhereClauseHelper.buildDateBuildClauseWithoutCoalesce(DATE, false, false, false, deathOfBirth, DATEOFBIRTH);
		buf = addToClause(buf, subClause);

		String ssn = event.getMemberSsn();
		subClause = WhereClauseHelper.buildBuildClauseWithoutCoalesce(STRING, false, false, true, ssn, SSN);
		buf = addToClause(buf, subClause);

		String juvenileNum = event.getJuvenileNumber();
		if(juvenileNum != null)
		{
			StringBuffer subBuf = new StringBuffer();
			subBuf.append(" FAMMEMBER_ID in (SELECT FAMMEMBER_ID from JIMS2.JCCONSRELATION as A,");
			subBuf.append(" JIMS2.JCCONSTELLTION as B");
			subBuf.append(" where a.CONSTELLTION_ID=b.CONSTELLTION_ID and b.JUVENILE_ID='");
			subBuf.append(juvenileNum);
			subBuf.append("')");
			buf = addToClause(buf, subBuf.toString());
		}

		return buf.toString();
	}
	public String getSuspiciousMembersClause(IEvent anEvent)
	{
		GetSuspiciousFamilyMembersEvent event = (GetSuspiciousFamilyMembersEvent) anEvent;
		StringBuffer buf = new StringBuffer();
		String subClause = PDConstants.BLANK;
		
		String firstName = event.getMemberFirstName();
		if (firstName != null && !firstName.equals(PDConstants.BLANK)) {	
			firstName = this.formatApostrophe(firstName, true);
			subClause = WhereClauseHelper.buildBuildClauseWithoutCoalesce(STRING, true, true, false, firstName, FIRSTNAME);
			buf = addToClause(buf, subClause);
		}

		String lastName = event.getMemberLastName();
		if (lastName != null && !lastName.equals(PDConstants.BLANK)) {	
			lastName = this.formatApostrophe(lastName, true);
			subClause = WhereClauseHelper.buildBuildClauseWithoutCoalesce(STRING, true, true, false, lastName, LASTNAME);
			buf = addToClause(buf, subClause);
		}

		String ssn = event.getMemberSsn();
		if (ssn != null && !ssn.equals(PDConstants.BLANK)){
			subClause = WhereClauseHelper.buildBuildClauseWithoutCoalesce(STRING, false, false, true, ssn, SSN);
			buf = addToClause(buf, subClause);
		}
		
		String familyMemId = event.getFamMemberId();
		if (familyMemId != null && !familyMemId.equals(PDConstants.BLANK)){
			subClause = WhereClauseHelper.buildBuildClauseWithoutCoalesce(STRING, false, false, true, familyMemId, FAMMEMBER_ID);
			buf = addToClause(buf, subClause);
		}
		
		buf = addToClause(buf, FMEMMATCHVAL_CLAUSE);
		buf = addToClause(buf, JUVENILENULL_CLAUSE);

		return buf.toString();
	}
	/**
	 * @param buf
	 * @param firstName
	 * @return
	 */
	private StringBuffer addToClause(StringBuffer buf, String subClause)
	{
		if (subClause.length() > 0)
		{
			if (buf.toString().length() > 0)
			{
				buf.append(" AND ");
			}
			buf.append(subClause);
		}
		return buf;
	}
	
	private String formatApostrophe(String value, boolean formatString)
	{
		if (formatString)
		{
			if (value.indexOf("'") >= 0)
			{
				value = value.replace("'", "''");
			}
		}

		return value;
	}
	
	public String searchFamilyAdvanced(IEvent anEvent)
	{
	    GetFamilyMembersAdvancedEvent event = (GetFamilyMembersAdvancedEvent) anEvent;
		StringBuffer buf = new StringBuffer();

		String firstName = event.getFirstName();
		if (firstName != null && !firstName.equals(PDConstants.BLANK)) {	
			firstName = this.formatApostrophe(firstName, true);
		}
		String subClause = WhereClauseHelper.buildBuildClauseWithoutCoalesce(STRING, true, true, false, firstName, FIRSTNAME);
		buf = addToClause(buf, subClause);

		String lastName = event.getLastName();
		if (lastName != null && !lastName.equals(PDConstants.BLANK)) {	
			lastName = this.formatApostrophe(lastName, true);
		}
		subClause = WhereClauseHelper.buildBuildClauseWithoutCoalesce(STRING, true, true, false, lastName, LASTNAME);
		buf = addToClause(buf, subClause);

		String middleName = event.getMiddleName();
		if (middleName != null && !middleName.equals(PDConstants.BLANK)) {			
			middleName = this.formatApostrophe(middleName, true);
		}
		subClause = WhereClauseHelper.buildBuildClauseWithoutCoalesce(STRING, true, true, false, middleName, MIDDLENAME);
		buf = addToClause(buf, subClause);

		String sex = event.getSex();
		subClause = WhereClauseHelper.buildBuildClauseWithoutCoalesce(STRING, true, false, false, sex, "SEX");
		buf = addToClause(buf, subClause);

		Date deathOfBirth = DateUtil.stringToDate( event.getDateOfBirth(),DateUtil.DATE_FMT_1);
		subClause = WhereClauseHelper.buildDateBuildClause(DATE, false, false, false, deathOfBirth, DATEOFBIRTH);
		buf = addToClause(buf, subClause);

		String ssn = event.getSsn();
		subClause = WhereClauseHelper.buildBuildClauseWithoutCoalesce(STRING, false, false, true, ssn, SSN);
		buf = addToClause(buf, subClause);
		
		String oid = event.getSearchById();
		subClause = WhereClauseHelper.buildBuildClauseWithoutCoalesce(STRING, false, false, true, oid, FAMMEMBER_ID);
		buf = addToClause(buf, subClause);
		
		
		String drivingLicense = event.getDriverLicenseNum();
		subClause = WhereClauseHelper.buildBuildClauseWithoutCoalesce(STRING, false, false, true, drivingLicense, DRIVING_LICENSE);
		buf = addToClause(buf, subClause);

		return buf.toString();
	}

}

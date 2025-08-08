/*
 * Created on Jul 19, 2005
 *
 */
package pd.juvenilewarrant;

import naming.PDJuvenileWarrantConstants;
import messaging.juvenilewarrant.reply.JuvenileJusticeSystemResponseEvent;
import pd.codetable.person.JuvenileSchoolDistrictCode;
import pd.pattern.IBuilder;

/**
 * @author jfisher
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileWarrantJJSBuilder implements IBuilder
{
	private JuvenileJusticeSystemResponseEvent responseEvent;
	private JuvenileJusticeSystem jjs;
	/**
	 * 
	 */
	public JuvenileWarrantJJSBuilder(JuvenileJusticeSystem jjs)
	{
		this.jjs = jjs;
		this.responseEvent = new JuvenileJusticeSystemResponseEvent();
	}
	

	/* (non-Javadoc)
	 * @see pd.pattern.IBuilder#build()
	 */
	public void build()
	{
		responseEvent.setTopic(PDJuvenileWarrantConstants.JUVENILE_JUSTICE_SYSTEM_EVENT_TOPIC);
		responseEvent.setJuvenileNumber(jjs.getJuvenileNum());

		responseEvent.setJuvenileFirstName(jjs.getFirstName());
		responseEvent.setJuvenileLastName(jjs.getLastName());
		responseEvent.setJuvenileMiddleName(jjs.getMiddleName());
		responseEvent.setAliasName(jjs.getAlias());
		responseEvent.setJuvenileTitle(jjs.getTitle());
		responseEvent.setJuvenileSSN(jjs.getSsn());

		responseEvent.setDateOfBirth(jjs.getDateOfBirth());
		
		// data conversion from 3 byte code to userId
		if (jjs.getProbationOfficerOfRecordId() != null && !jjs.getProbationOfficerOfRecordId().equals(""))
		{		
		    String jpoId = jjs.getProbationOfficerOfRecordId();
		    
		    if(jpoId.length() == 3)
		    {
		        jpoId = "UV" + jpoId;
		    }
		    
		    responseEvent.setProbationOfficerOfRecordId(jpoId);
		}

		JuvenileSchoolDistrictCode schoolCode = jjs.getSchoolDistrict();
		if (schoolCode != null)
		{
			responseEvent.setSchoolDistrictId(schoolCode.getDistrictCode());
			responseEvent.setSchoolDistrict(schoolCode.getDistrictDescription());
			responseEvent.setSchoolCodeId(schoolCode.getSchoolCode());
			responseEvent.setSchoolName(schoolCode.getSchoolDescription());
		}

		responseEvent.setSexId(jjs.getSexId());
		responseEvent.setRaceId(jjs.getRaceId());
						
		responseEvent.setFathersFirstName(jjs.getFathersFirstName());
		responseEvent.setFathersLastName(jjs.getFathersLastName());
		responseEvent.setFathersMiddleName(jjs.getFathersMiddleName());
		responseEvent.setMothersFirstName(jjs.getMothersFirstName());
		responseEvent.setMothersLastName(jjs.getMothersLastName());
		responseEvent.setMothersMiddleName(jjs.getMothersMiddleName());
		responseEvent.setOthersFirstName(jjs.getOtherFirstName());
		responseEvent.setOthersLastName(jjs.getOtherLastName());
		responseEvent.setOthersMiddleName(jjs.getOtherMiddleName());
	}

	/* (non-Javadoc)
	 * @see pd.pattern.IBuilder#getResult()
	 */
	public Object getResult()
	{
		// TODO Auto-generated method stub
		return this.responseEvent;
	}

}

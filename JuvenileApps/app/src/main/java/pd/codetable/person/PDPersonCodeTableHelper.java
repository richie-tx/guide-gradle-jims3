/*
 * Created on Feb 7, 2005
 */
package pd.codetable.person;

import java.util.Iterator;

import messaging.codetable.person.reply.JuvenileSchoolDistrictCodeResponseEvent;
import mojo.km.dispatch.IDispatch;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import naming.PDCodeTableConstants;

/**
 * @author dnikolis
 */
public final class PDPersonCodeTableHelper
{
	/**
	 * Constructor
	 */
	private PDPersonCodeTableHelper()
	{
		super();
	}

	/**
	 * Creates a JuvenileSchoolDistrictCodeResponseEvent from a JuvenileSchoolDistrictCode entity
	 * @param schoolCode
	 * @return JuvenileSchoolDistrictCode response events
	 */
	public static JuvenileSchoolDistrictCodeResponseEvent getJuvenileSchoolDistrictCodeResponseEvent(JuvenileSchoolDistrictCode schoolCode)
	{
		JuvenileSchoolDistrictCodeResponseEvent schoolReply = new JuvenileSchoolDistrictCodeResponseEvent();
		//String oid = (String) schoolCode.getOID();
		//String district = oid.substring(0, 3).trim();
		//String number = oid.substring(3).trim();
		schoolReply.setDistrictCode(schoolCode.getDistrictCode());
		schoolReply.setDistrictDescription(schoolCode.getDistrictDescription());
		schoolReply.setInactiveInd(schoolCode.getInactiveInd());
		schoolReply.setSchoolCode(schoolCode.getSchoolCode());
		schoolReply.setSchoolDescription(schoolCode.getSchoolDescription());
		schoolReply.setTeaCode(schoolCode.getTeaCode());
		schoolReply.setOid( schoolCode.getOID( ));
		schoolReply.setTopic(PDCodeTableConstants.SCHOOL_CODE);
		schoolReply.setInstructionType(schoolCode.getInstructionType());
		schoolReply.setCharterType(schoolCode.getCharterType());
		schoolReply.setStreetName(schoolCode.getStreetName());
		schoolReply.setCity(schoolCode.getCity());
		schoolReply.setState(schoolCode.getState());
		schoolReply.setZip(schoolCode.getZip());
		schoolReply.setPhoneNum(schoolCode.getPhoneNum());
		schoolReply.setSubGroupInd(schoolCode.getSubGroupInd());
		return schoolReply;
	}

	public static void sendJuvenileSchoolDistricts(IDispatch dispatch)
	{
		IHome home = new Home();
		Iterator i = home.findAll(JuvenileSchoolDistrictCode.class);

		while (i.hasNext())
		{
			JuvenileSchoolDistrictCode jsdc = (JuvenileSchoolDistrictCode) i.next();

			JuvenileSchoolDistrictCodeResponseEvent event =
				PDPersonCodeTableHelper.getJuvenileSchoolDistrictCodeResponseEvent(jsdc);
			dispatch.postEvent(event);
		}

	}

}

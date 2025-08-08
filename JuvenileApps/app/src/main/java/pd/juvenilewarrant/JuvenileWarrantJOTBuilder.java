/*
 * Created on Jul 19, 2005
 *
 */
package pd.juvenilewarrant;

import java.util.Arrays;
import java.util.List;

import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingResponseEvent;
import naming.PDJuvenileWarrantConstants;

import org.apache.commons.lang.StringUtils;

import pd.codetable.person.JuvenileSchoolDistrictCode;
import pd.pattern.IBuilder;

/**
 * @author dgibler
 * Builds JuvenileOffenderTrackingResponseEvent using the GOF builder pattern.
 */
public class JuvenileWarrantJOTBuilder implements IBuilder
{
	private JuvenileOffenderTrackingResponseEvent responseEvent;
	private JuvenileOffenderTracking jot;
	/**
	 * 
	 */
	public JuvenileWarrantJOTBuilder(JuvenileOffenderTracking jot)
	{
		this.jot = jot;
		this.responseEvent = new JuvenileOffenderTrackingResponseEvent();
	}

	/* (non-Javadoc)
	 * @see pd.pattern.IBuilder#build()
	 */
	public void build()
	{
		responseEvent.setTopic(PDJuvenileWarrantConstants.JUVENILE_OFFENDER_TRACKING_EVENT_TOPIC);
		this.setJOTValues();
		this.setSchoolValues();
		this.setFamilyValues();

	}

	/**
	 * 
	 */
	private void setFamilyValues()
	{
		responseEvent.setMothersEmployer(jot.getMothersEmployer());
		responseEvent.setMothersEmployerPhoneNum(jot.getMothersEmployerPhoneNum());
		responseEvent.setMothersFirstName(jot.getMothersFirstName());
		responseEvent.setMothersLastName(jot.getMothersLastName());
		responseEvent.setMothersMiddleName(jot.getMothersMiddleName());
		responseEvent.setMothersPhoneNum(jot.getMothersPhoneNum());
		responseEvent.setMothersSsn(jot.getMothersSsn());
		responseEvent.setOtherEmployer(jot.getOtherEmployer());
		responseEvent.setOtherEmployerPhoneNum(jot.getOtherEmployerPhoneNum());
		responseEvent.setOtherFirstName(jot.getOtherFirstName());
		responseEvent.setOtherLastName(jot.getOtherLastName());
		responseEvent.setOtherMiddleName(jot.getOtherMiddleName());
		responseEvent.setOtherPhoneNum(jot.getOtherPhoneNum());
		responseEvent.setOtherSsn(jot.getOtherSsn());
		responseEvent.setPhoneNum(jot.getPhoneNum());
		responseEvent.setFathersEmployer(jot.getFathersEmployer());
		responseEvent.setFathersEmployerPhoneNum(jot.getFathersEmployerPhoneNum());
		responseEvent.setFathersFirstName(jot.getFathersFirstName());
		responseEvent.setFathersLastName(jot.getFathersLastName());
		responseEvent.setFathersMiddleName(jot.getFathersMiddleName());
		responseEvent.setFathersPhoneNum(jot.getFathersPhoneNum());
		responseEvent.setFathersSsn(jot.getFathersSsn());

	}

	/**
	 * 
	 */
	private void setSchoolValues()
	{
		String oid = jot.getSchoolCodeId();
		if ((oid != null) && (oid.length() > 2))
		{
			JuvenileSchoolDistrictCode schoolCode = jot.getSchoolName();

			//String district = oid.substring(0, 2).trim();
			//String schoolId = oid.substring(2).trim();
			if (schoolCode != null)
			{
				responseEvent.setSchoolCodeId(schoolCode.getSchoolCode());
				responseEvent.setSchoolName(schoolCode.getSchoolDescription());
				responseEvent.setSchoolDistrictId(schoolCode.getDistrictCode());
				responseEvent.setSchoolDistrict(schoolCode.getDistrictDescription());
			}
		}
	}

	/**
	 * 
	 */
	private void setJOTValues()
	{
		responseEvent.setAliasName(jot.getAliasName());
		responseEvent.setHeight(jot.getHeight());
		if ("".equals(jot.getJuvenileNum()) == false)
		{
			responseEvent.setJuvenileNum(jot.getJuvenileNum());
		}
		if( StringUtils.isNotEmpty( jot.getLastName())){
		    List<String> nameList = Arrays.asList(jot.getLastName().split(",")); 
		    
		    responseEvent.setLastName(nameList.get(0));
		    responseEvent.setFirstName(nameList.get(1));
		    responseEvent.setMiddleName(jot.getMiddleName());
		}
		
		responseEvent.setFbiNum(jot.getFbiNum());
		responseEvent.setSid(jot.getSidNum());
		responseEvent.setSsn(jot.getSsn());
		responseEvent.setTransactionNum(jot.getTransactionNum());
		responseEvent.setWeight(jot.getWeight());
		responseEvent.setDaLogNum(jot.getDaLogNum());
		responseEvent.setDateOfBirth(jot.getDateOfBirth());
		responseEvent.setSexId(jot.getSexId());
		responseEvent.setBuildId(jot.getBuildId());
		responseEvent.setComplexionId(jot.getComplexionId());
		responseEvent.setEyeColorId(jot.getEyeColorId());
		responseEvent.setHairColorId(jot.getHairColorId());
		responseEvent.setRaceId(jot.getRaceId());
		responseEvent.setDaDateOut(jot.getDaDateOut());
		responseEvent.setLogStatus( getStatusDescripton(jot.getLogStatus()) );
		if ("J".equalsIgnoreCase( jot.getLogStatus() )
			&& "U".equalsIgnoreCase( jot.getSentToDaInd() )) {
		    responseEvent.setLogStatus("UNSENT");
		}
		responseEvent.setCjisNum(jot.getCjisNum());
		responseEvent.setSentToDaInd(jot.getSentToDaInd());
		
	}
	
	
	public String getStatusDescripton(String status){
	    String description="";
	    switch ( status ){
	    case "A":
		description = "DA ACCEPT";
		break;
	    case "C":
		description = "PRINTED";
		break;
	    case "F":
		description = "REFER TO FIRST PROGRAM";
		break;
	    case "J":
		description = "JUVENILE IDENTIFIED";
		break;
	    case "M":
		description = "MENTAL HEALTH HOLD";
		break;
	    case "D":
		description = "DISTRICT CLERK";
		break;
	    case "O":
		description = "DIVERSION 180";
		break;
	    case "P":
		description = "REFERRED TO JUV PROB DP90";
		break;
	    case "Q":
		description = "REFERRED TO JUV PROB DP180";
		break;
	    case "R":
		description = "REJECTED";
		break;
	    case "S":
		description = "SECOND CHANCE PROGRAM";
		break;
	    case "V":
		description = "VOP/PUNISHMENT";
		break;
	    case "W":
		description = "5TH WARD PROGRAM";
		break;
	    default:
		description = "";
		break;
	    
	    }
	    
	    return description;
	}

	/* (non-Javadoc)
	 * @see pd.pattern.IBuilder#getResult()
	 */
	public Object getResult()
	{
		return responseEvent;
	}

}

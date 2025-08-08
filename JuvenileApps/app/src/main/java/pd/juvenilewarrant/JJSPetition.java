package pd.juvenilewarrant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import messaging.codetable.reply.ICode;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.referral.GetJJSReferralEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDJuvenileWarrantConstants;
import pd.codetable.criminal.JuvenileCourt;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.referral.JJSReferral;

/**
 * @author dnikolis
 *
 */
public class JJSPetition extends PersistentObject 
{
	private Date petitionDate;
	private String sequenceNum;
	private String sequenceNumber;
	private Date terminationDate;
	/**
	* Properties for offenseCode
	* @referencedType pd.codetable.criminal.JuvenileOffenseCode
	* @detailerDoNotGenerate true 
	*/
	private JuvenileOffenseCode offenseCode = null;
	private String status; //petition status
	private String referralNum;
	private String amend; //petition Amendment
	private String courtId;
	private String type; //petitionType
	/**
	* Properties for court
	* @referencedType pd.codetable.criminal.JuvenileCourt
	* @detailerDoNotGenerate true 
	*/
	private JuvenileCourt court = null;
	private String offenseCodeId;
	private String petitionNum; //petitionNum
	private String severity;
	private String juvenileNum;
	private String petitionAllegation;
	
	private String recType;
	private String tjpcSeqNum;
	
	private Date lcDate;
	private String lcUser;
	private Date lcTime;
	private String CJISNumber;
	private Date terminationCreateDate;
	private String DPSCode;
	

	
	/**
	* @roseuid 41B480E70264
	*/
	public JJSPetition()
	{
	}
	
        public PetitionResponseEvent valueObject()
        {	
        	PetitionResponseEvent petitionEvent = new PetitionResponseEvent();
        	petitionEvent.setTopic(PDJuvenileWarrantConstants.PETITION_EVENT_TOPIC);
        	petitionEvent.setJuvenileNum(this.getJuvenileNum());
        	petitionEvent.setSeverity(this.getSeverity());
        	petitionEvent.setOffenseCodeId(this.getOffenseCodeId()); // petition Allegation.
        
        	if (this.offenseCodeId != null && !this.offenseCodeId.equals(""))
        	{
        	    JuvenileOffenseCode juvOffCode = this.getOffenseCode();
        	    if (juvOffCode != null)
        	    {
        		petitionEvent.setOffense(juvOffCode.getLongDescription());
        	    	petitionEvent.setOffenseShortDesc(juvOffCode.getShortDescription());
        	    }
        	}
        	if (this.getAmend() != null ) {
        	    petitionEvent.setAmend(this.getAmend());
        	} else {
        	    petitionEvent.setAmend("");
        	}
        	petitionEvent.setPetitionNum(this.getPetitionNum());
        	petitionEvent.setPetitionDate(this.getPetitionDate());
        	petitionEvent.setReferralNum(this.getReferralNum());
        	petitionEvent.setSequenceNum(this.getSequenceNum());
        	petitionEvent.setPetitionType(this.getType());
        	petitionEvent.setPetitionStatus(this.getStatus());
        	if (this.getStatus() != null)
        	    petitionEvent.setPetitionStatusDescription(ui.common.CodeHelper.getCodeDescription("PETITION_STATUS", this.getStatus()).toLowerCase());
        	petitionEvent.setRecType(this.getRecType());
        	petitionEvent.setTjpcSeqNum(this.getTjpcSeqNum());
        	petitionEvent.setOID(this.getOID());
        	petitionEvent.setPetitionAllegation(this.petitionAllegation);
        	if( this.petitionDate!= null)
        	    petitionEvent.setPetition_Date(formatDate(this.petitionDate));
        	if( this.lcDate!= null){
            	    petitionEvent.setLast_Change_Date(formatDate(this.lcDate));
        	}
        	petitionEvent.setLastChangeUser(this.lcUser);
        	
      
        	ICode court = this.getCourt();
        	if (court != null)
        	{
        	    petitionEvent.setCourtId(court.getCode());
        	    petitionEvent.setCourt(court.getDescription());
        	}
        	petitionEvent.setTerminationDate(this.getTerminationDate());
        	if(this.terminationDate != null)
        	{
        	    petitionEvent.setTermination_Date(formatDate(this.terminationDate));
        	}
        	//Termination CreateDate
        	petitionEvent.setTerminationCreateDate(this.getTerminationCreateDate());
        	if(this.terminationCreateDate != null){
        	    petitionEvent.setTermination_CreateDate(formatDate(this.terminationCreateDate));
        	}
        	
        	
        	if (this.CJISNumber != null)
        	    petitionEvent.setPetCJISNum(this.CJISNumber);
        	return petitionEvent;
        }

	/**
	* Access method for the amend property.
	* @return the current value of the amend property
	*/
	public String getAmend()
	{
		fetch();
		return amend;
	}
	/**
	* Sets the value of the amend property.
	* @param aAmend the new value of the amend property
	*/
	public void setAmend(String aAmend)
	{
		if (this.amend == null || !this.amend.equals(aAmend))
		{
			markModified();
		}
		amend = aAmend;
	}
	/**
	* Access method for the referralNum property.
	* @return the current value of the referralNum property
	*/
	public String getReferralNum()
	{
		fetch();
		return referralNum;
	}
	/**
	* Sets the value of the referralNum property.
	* @param aReferralNum the new value of the referralNum property
	*/
	public void setReferralNum(String aReferralNum)
	{
		if (this.referralNum == null || !this.referralNum.equals(aReferralNum))
		{
			markModified();
		}
		referralNum = aReferralNum;
	}
	/**
	* Access method for the petitionNum property.
	* @return the current value of the petitionNum property
	*/
	public String getPetitionNum()
	{
		fetch();
		return petitionNum;
	}
	/**
	* Sets the value of the petitionNum property.
	* @param aPetitionNum the new value of the petitionNum property
	*/
	public void setPetitionNum(String aPetitionNum)
	{
		if (this.petitionNum == null || !this.petitionNum.equals(aPetitionNum))
		{
			markModified();
		}
		petitionNum = aPetitionNum;
	}
	/**
	* Access method for the juvenileNum property.
	* @return the current value of the juvenileNum property
	*/
	public String getJuvenileNum()
	{
		fetch();
		return juvenileNum;
	}
	/**
	* Sets the value of the juvenileNum property.
	* @param aJuvenileNum the new value of the juvenileNum property
	*/
	public void setJuvenileNum(String aJuvenileNum)
	{
		if (this.juvenileNum == null || !this.juvenileNum.equals(aJuvenileNum))
		{
			markModified();
		}
		juvenileNum = aJuvenileNum;
	}
	/**
	* Access method for the status property.
	* @return the current value of the status property
	*/
	public String getStatus()
	{
		fetch();
		return status;
	}
	/**
	* Sets the value of the status property.
	* @param aStatus the new value of the status property
	*/
	public void setStatus(String aStatus)
	{
		if (this.status == null || !this.status.equals(aStatus))
		{
			markModified();
		}
		status = aStatus;
	}
	/**
	* Access method for the type property.
	* @return the current value of the type property
	*/
	public String getType()
	{
		fetch();
		return type;
	}
	/**
	* Sets the value of the type property.
	* @param aType the new value of the type property
	*/
	public void setType(String aType)
	{
		if (this.type == null || !this.type.equals(aType))
		{
			markModified();
		}
		type = aType;
	}
	/**
	* Access method for the severity property.
	* @return the current value of the severity property
	*/
	public String getSeverity()
	{
		fetch();
		return severity;
	}
	/**
	* Sets the value of the severity property.
	* @param aSeverity the new value of the severity property
	*/
	public void setSeverity(String aSeverity)
	{
		if (this.severity == null || !this.severity.equals(aSeverity))
		{
			markModified();
		}
		severity = aSeverity;
	}
	/**
	* Access method for the sequenceNum property.
	* @return the current value of the sequenceNum property
	*/
	public String getSequenceNum()
	{
		fetch();
		return sequenceNum;
	}
	/**
	* Sets the value of the sequenceNum property.
	* @param aSequenceNum the new value of the sequenceNum property
	*/
	public void setSequenceNum(String aSequenceNum)
	{
		if (this.sequenceNum == null || !this.sequenceNum.equals(aSequenceNum))
		{
			markModified();
		}
		sequenceNum = aSequenceNum;
	}
	/**
	* Access method for the petitionDate property.
	* @return the current value of the petitionDate property
	*/
	public Date getPetitionDate()
	{
		fetch();
		return petitionDate;
	}
	/**
	* Sets the value of the petitionDate property.
	* @param aPetitionDate the new value of the petitionDate property
	*/
	public void setPetitionDate(Date aPetitionDate)
	{
		if (this.petitionDate == null || !this.petitionDate.equals(aPetitionDate))
		{
			markModified();
		}
		petitionDate = aPetitionDate;
	}
	/**
	 * 
	 * @param petitionNum
	 * @param referralNum
	 * @return JJSPetition jjsp
	 */
	static public JJSPetition find(String petitionNum, String referralNum)
	{
		JJSPetition jjsp = null;
		IHome home = new Home();
		jjsp = (JJSPetition) home.find(petitionNum + referralNum, JJSPetition.class);
		return jjsp;
	}
	
	static public JJSPetition findById(String OID)
	{
		JJSPetition jjsp = null;
		IHome home = new Home();
		jjsp = (JJSPetition) home.find("OID",OID, JJSPetition.class);
		return jjsp;
	}

	/**
	 * Finds all Petitions by Event
	 * @param event
	 * @return iterator
	 */
	public static Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return (Iterator) home.findAll(event, JJSPetition.class);
	}
	
	public static JJSPetition findByLastChangeDate(IEvent event)
	{
		IHome home = new Home();
		return (JJSPetition) home.findAll(event, JJSPetition.class);
	}
	
	/**
	 * @param attributeName
	 * @param attributeValue
	 * @return Iterator
	 */
	public static Iterator<JJSPetition> findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		return home.findAll(attributeName,attributeValue,JJSPetition.class);
	}
	
	
	/**
	 * 
	 * @param petitionNum
	 * @return Iterator jjsp
	 */
	static public Iterator<JJSPetition> find(String petitionNum)
	{
		IHome home = new Home();
		Iterator<JJSPetition> jjsp = home.findAll("petitionNum", petitionNum, JJSPetition.class);
		return jjsp;
	}
	/**
	 * Set the reference value to class :: pd.codetable.criminal.JuvenileOffenseCode
	 * @param aoffenseCodeId
	 */
	public void setOffenseCodeId(String aoffenseCodeId)
	{
		if (this.offenseCodeId == null || !this.offenseCodeId.equals(aoffenseCodeId))
		{
			markModified();
		}
		offenseCode = null;
		this.offenseCodeId = aoffenseCodeId;
	}
	/**
	 * Get the reference value to class :: pd.codetable.criminal.JuvenileOffenseCode
	 * @return String offenseCodeId
	 */
	public String getOffenseCodeId()
	{
		fetch();
		return offenseCodeId;
	}
	/**
	* Initialize class relationship to class pd.codetable.criminal.JuvenileOffenseCode
	*/
	private void initOffenseCode()
	{
		if (offenseCode == null)
		{
		    offenseCode =
				(JuvenileOffenseCode)
				JuvenileOffenseCode.find("offenseCode", offenseCodeId);
		}
	}
	/**
	 * Gets referenced type pd.codetable.criminal.JuvenileOffenseCode
	 * @return offenseCode
	 */
	public JuvenileOffenseCode getOffenseCode()
	{
		initOffenseCode();
		return offenseCode;
	}
	/**
	 * set the type reference for class member offenseCode
	 * @param offenseCode
	 */
	public void setOffenseCode(JuvenileOffenseCode aoffenseCode)
	{
		if (this.offenseCode == null || !this.offenseCode.equals(aoffenseCode))
		{
			markModified();
		}
		setOffenseCodeId("" + aoffenseCode.getOID());
		this.offenseCode =
			(JuvenileOffenseCode) new mojo.km.persistence.Reference(aoffenseCode).getObject();
	}
	/**
	 * Set the reference value to class :: pd.codetable.criminal.JuvenileCourt
	 * @param courtCodeId
	 */
	public void setCourtId(String courtCodeId)
	{
		if (this.courtId == null || !this.courtId.equals(courtCodeId))
		{
			markModified();
		}
		court = null;
		this.courtId = courtCodeId;
	}
	
	/**
	 * Get the reference value to class :: pd.codetable.criminal.JuvenileCourt
	 * @return String courtId
	 */
	public String getCourtId()
	{
	    JJSReferral referral = null;
		GetJJSReferralEvent refEvent = new GetJJSReferralEvent();
		refEvent.setJuvenileNum(juvenileNum);
		refEvent.setReferralNum(referralNum);
		Iterator<JJSReferral>referralRespItr = JJSReferral.findAll(refEvent);
		if(referralRespItr.hasNext()){
		    referral = referralRespItr.next();
		    if(referral!=null){
			this.courtId = referral.getCourtId();
		      }
		}
		return courtId;
	}
	/**
	* Initialize class relationship to class pd.codetable.criminal.JuvenileCourt
	*/
	private void initCourt()
	{
	    JJSReferral referral = null;
		GetJJSReferralEvent refEvent = new GetJJSReferralEvent();
		refEvent.setJuvenileNum(juvenileNum);
		refEvent.setReferralNum(referralNum);
		Iterator<JJSReferral>referralRespItr = JJSReferral.findAll(refEvent);
		if(referralRespItr.hasNext()){
		    referral = referralRespItr.next();
		    if(referral!=null){
			this.courtId = referral.getCourtId();
		      }
		}
		
        	if (court == null)
        	{
        	    court = (JuvenileCourt) new mojo.km.persistence.Reference(courtId, JuvenileCourt.class).getObject();
        	}
	}
	/**
	 * Gets referenced type pd.codetable.criminal.JuvenileCourt
	 * @return JuvenileCourt court
	 */
	public JuvenileCourt getCourt()
	{
		initCourt();
		return court;
	}
	/**
	 * set the type reference for class member court
	 * @param courtCode
	 */
	public void setCourt(JuvenileCourt courtCode)
	{
		if (this.court == null || !this.court.equals(courtCode))
		{
			markModified();
		}
		setCourtId("" + courtCode.getOID());
		this.court = (JuvenileCourt) new mojo.km.persistence.Reference(courtCode).getObject();
	}

	public String getTjpcSeqNum()
	{
	    return tjpcSeqNum;
	}

	public void setTjpcSeqNum(String tjpcSeqNum)
	{
	    this.tjpcSeqNum = tjpcSeqNum;
	}

	public String getRecType()
	{
	    fetch();
	    return recType;
	}

	public void setRecType(String recType)
	{
	    if (this.recType == null || !this.recType.equals(recType))
		{
			markModified();
		}
	    this.recType = recType;
	}

	public Date getLcTime()
	{
	    return lcTime;
	}

	public void setLcTime(Date lcTime)
	{
	    if (this.lcTime == null || !this.lcTime.equals(lcTime))
		{
			markModified();
		}
	    this.lcTime = lcTime;
	}

	public String getLcUser()
	{
	    return lcUser;
	}

	public void setLcUser(String lcUser)
	{
	    if (this.lcUser == null || !this.lcUser.equals(lcUser))
		{
			markModified();
		}
	    this.lcUser = lcUser;
	}

	public Date getLcDate()
	{
	    return lcDate;
	}

	public void setLcDate(Date lcDate)
	{
	    if (this.lcDate == null || !this.lcDate.equals(lcDate))
		{
			markModified();
		}
	    this.lcDate = lcDate;
	}

	public String getPetitionAllegation()
	{
	    return petitionAllegation;
	}

	public void setPetitionAllegation(String petitionAllegation)
	{
	    this.petitionAllegation = petitionAllegation;
	}
	
	public  String formatDate(Date date){
	   	return new SimpleDateFormat("MM/dd/yyyy").format(date);
	    }

	public String getSequenceNumber()
	{
	    return sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber)
	{
	    this.sequenceNumber = sequenceNumber;
	}

	public Date getTerminationDate()
	{
	    fetch();
	    return terminationDate;
	}

	public void setTerminationDate(Date terminationDate)
	{
	    if (this.terminationDate == null || !this.terminationDate.equals(terminationDate))
		{
			markModified();
		}
	    this.terminationDate = terminationDate;
	}
	
	public Date getTerminationCreateDate()
	{
	    fetch();
	    return this.terminationCreateDate;
	}

	public void setTerminationCreateDate(Date terminationCreateDate)
	{
	    if (this.terminationCreateDate == null || !this.terminationCreateDate.equals(terminationCreateDate))
		{
			markModified();
		}
	    this.terminationCreateDate = terminationCreateDate;
	}
	
	public String getCJISNumber()
	{
	    fetch();
	    return CJISNumber;
	}

	public void setCJISNumber(String cJISNumber)
	{
	    if (this.CJISNumber == null || !this.CJISNumber.equals(cJISNumber))
		{
			markModified();
		}
	    this.CJISNumber = cJISNumber;
	}
	public String getDPSCode()
	{
	    fetch();
	    return DPSCode;
	}

	public void setDPSCode(String dPSCode)
	{
	    if (this.DPSCode == null || !this.DPSCode.equals(dPSCode))
		{
			markModified();
		}
	    this.DPSCode = dPSCode;
	}

}

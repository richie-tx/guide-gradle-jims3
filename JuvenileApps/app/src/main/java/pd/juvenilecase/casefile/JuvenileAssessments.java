package pd.juvenilecase.casefile;

import java.util.Date;
import java.util.Iterator;

import naming.PDCodeTableConstants;

import messaging.casefile.reply.AssessmentReferralResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

public class JuvenileAssessments extends PersistentObject{

	private String juvenileId;
	private String juvenileName;//added for US 175505
	private Date entryDate;
	private String gangNameId;
	private String cliqueSetId;
	private String placementFacilityId;
	private String reasonForReferralId;
	private String lvlOfGangInvolvementId;
	private String recommendationsId;
	private String assessmentReferralTypeId;
	
	private String acceptedStatus;
	private String assessmentConclusion;
	private String assessmentComments;
	private String rejectionReason;
	private String personMakingReferral;

	//Hidden field for other,on selection of other,show other text box.
	private String otherGangName;
	private String otherCliqueSet;
	private String otherReasonforReferral;
	
	//Hidden field for describe hybrid on selection of hybrid as the gang name;
	private String descHybrid;
	private String assessmentStatus;
	private String parentNotified;
	private String parentNotifiedGangAssReq;

	/**
	 * Properties for associationType
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey PLACEMENT_FACILITY
	 * @detailerDoNotGenerate false
	 */
	private Code placementFacility=null;
	/**
	 * Properties for gangName
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey GANG_NAME
	 * @detailerDoNotGenerate false
	 */
	private Code gangName=null;
	/**
	 * Properties for cliqueSet
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey GANG_CLIQUE
	 * @detailerDoNotGenerate false
	 */
	private Code cliqueSet=null;
	/**
	 * Properties for reasonForReferral
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey GANG_REASONFORREFFERAL
	 * @detailerDoNotGenerate false
	 */
	private Code reasonForReferral=null;
	/**
	 * Properties for lvlOfGangInvolvement
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey GANG_LEVELOFGANGINVOLVEMENT
	 * @detailerDoNotGenerate false
	 */
	private Code lvlOfGangInvolvement=null;
	/**
	 * Properties for recommendations
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey RECOMMENDATIONS
	 * @detailerDoNotGenerate false
	 */
	
	private Code recommendations=null;
	
	private Code assessmentReferralType=null;
	
	
	/**
	 * Default Constructor.
	 */
	public JuvenileAssessments()
	{
	}

	/**
	 * @return JuvenileGangs
	 * @param gangNameId
	 */
	static public JuvenileAssessments find(String assessmentsId)
	{
		IHome home = new Home();
		JuvenileAssessments assessment = (JuvenileAssessments) home.find(assessmentsId, JuvenileAssessments.class);
		return assessment;
	}

	/**
	 * Finds juvenile gangs list by an event
	 * 
	 * @return Iterator of drugs list
	 * @param event
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator assessment = home.findAll(event, JuvenileAssessments.class);
		return assessment;
	}

	/**
	 * @return Iterator gang
	 * @param attrName
	 *            name fo the attribute for where clause
	 * @param attrValue
	 *            value to be checked in the where clause
	 */
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator assessment = home.findAll(attrName, attrValue, JuvenileAssessments.class);
		return assessment;
	}
	/**
	 * Save Gang.
	 * @param saveEvent
	 */
	public AssessmentReferralResponseEvent getResponseEvent(){
		AssessmentReferralResponseEvent responseEvent=new AssessmentReferralResponseEvent();
		   //Create assessment
		   responseEvent.setAssessmentIDNumber(this.getOID());
		   responseEvent.setReferralDate(this.getEntryDate());
		   responseEvent.setPlacementFacilityId(this.getPlacementFacilityId());
		   responseEvent.setGangNameId(this.getGangNameId());
		   responseEvent.setCliqueSetId(this.getCliqueSetId());
		   responseEvent.setAssessmentTypeId(this.getAssessmentReferralTypeId());
		   responseEvent.setOtherGangName(this.getOtherGangName());
		   responseEvent.setOtherCliqueSet(this.getOtherCliqueSet());
		   responseEvent.setDescHybrid(this.getDescHybrid());
		   responseEvent.setLvlOfGangInvolvementId(this.getLvlOfGangInvolvementId());
		   responseEvent.setOtherReasonForReferral(this.getOtherReasonforReferral());
		   responseEvent.setComments(this.getAssessmentComments());
		   responseEvent.setReasonForReferralId(this.getReasonForReferralId());
		   responseEvent.setPersonMakingReferral(this.getPersonMakingReferral());
		   responseEvent.setJuvenileName(this.getJuvenileName());
		 
		   //Update Assessment	
		   //bug 172247
		   if(this.getAcceptedStatus()!=null)
		   {
		       if(this.getAcceptedStatus().equalsIgnoreCase("UNABLE"))
			 responseEvent.setAcceptedStatus("Unable to Assess Youth");
		       /*else
			 responseEvent.setAcceptedStatus(this.getAcceptedStatus());*/	
		       if(this.getAcceptedStatus().equalsIgnoreCase("ACCEPTED"))
				 responseEvent.setAcceptedStatus("Accepted");
		       if(this.getAcceptedStatus().equalsIgnoreCase("REJECTED"))
				 responseEvent.setAcceptedStatus("Rejected");
		   }
		   
		   responseEvent.setRejectionReason(this.getRejectionReason());
		   responseEvent.setRecommendationsId(this.getRecommendationsId());
		   responseEvent.setConclusion(this.getAssessmentConclusion());
		   responseEvent.setJuvenileNum(this.getJuvenileId());
		   responseEvent.setStatus(this.getAssessmentStatus());
		   responseEvent.setUserId(this.getCreateUserID());
		   //add parent notified
		   responseEvent.setParentNotified(this.getParentNotified());
		   responseEvent.setParentNotifiedGangAssReq(this.getParentNotifiedGangAssReq());
		return responseEvent;
	}
	
	/**
	 *binds the attribute with the property. 
	 */
	public void bind()
	{
		markModified();
	}
	
	

	/**
	 * Set the reference value to class :: pd.juvenile.Juvenile
	 */
	public void setJuvenileId(String juvenileId)
	{
		if (this.juvenileId == null || !this.juvenileId.equals(juvenileId))
		{
			markModified();
		}
		this.juvenileId = juvenileId;
	}

	/**
	 * Get the reference value to class :: pd.juvenile.Juvenile
	 */
	public String getJuvenileId()
	{
		fetch();
		return juvenileId;
	}
	
	/**
	 * Access method for the referralDate property.
	 * 
	 * @return the current value of the referralDate property
	 */
	public Date getEntryDate()
	{
		fetch();
		return entryDate;
	}

	/**
	 * Sets the value of the entryDate property.
	 * 
	 * @param referralDate
	 *            the new value of the referralDate property
	 */
	public void setEntryDate(Date entryDate)
	{
		if (this.entryDate == null || !this.entryDate.equals(entryDate))
		{
			markModified();
		}
		this.entryDate = entryDate;
	}
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setGangNameId(String gangNameId)
	{
		if (this.gangNameId == null || !this.gangNameId.equals(gangNameId))
		{
			markModified();
		}
		this.gangNameId = gangNameId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getGangNameId()
	{
		fetch();
		return gangNameId;
	}
	
	
	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initGangName()
	{
		if (gangName == null)
		{
			try
			{
				gangName = (Code) new mojo.km.persistence.Reference(gangNameId, Code.class,
						"GANG_NAME").getObject();
			}
			catch (Throwable t)
			{
				
			}
		}
	}
	
	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getGangName()
	{
		fetch();
		initGangName();
		return gangName;
	}

	/**
	 * set the type reference for class member gangName
	 */
	public void setGangName(Code gangName)
	{
		if (this.gangName == null || !this.gangName.equals(gangName))
		{
			markModified();
		}
		if (gangName.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(gangName);
		}
		setGangNameId("" + gangName.getOID());
		this.gangName = (Code) new mojo.km.persistence.Reference(gangName).getObject();
	}
	
	
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setCliqueSetId(String cliqueSetId)
	{
		if (this.cliqueSetId == null || !this.cliqueSetId.equals(cliqueSetId))
		{
			markModified();
		}
		this.cliqueSetId = cliqueSetId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getCliqueSetId()
	{
		fetch();
		return cliqueSetId;
	}
	
	
	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initCliqueSet()
	{
		if (cliqueSet == null)
		{
			try
			{
				cliqueSet = (Code) new mojo.km.persistence.Reference(cliqueSetId, Code.class,
						"GANG_CLIQUE").getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}
	
	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getCliqueSet()
	{
		fetch();
		initCliqueSet();
		return cliqueSet;
	}

	/**
	 * set the type reference for class member cliqueSet
	 */
	public void setCliqueSet(Code cliqueSet)
	{
		if (this.cliqueSet == null || !this.cliqueSet.equals(cliqueSet))
		{
			markModified();
		}
		if (cliqueSet.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(cliqueSet);
		}
		setCliqueSetId("" + cliqueSet.getOID());
		this.cliqueSet = (Code) new mojo.km.persistence.Reference(cliqueSet).getObject();
	}
	
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setReasonForReferralId(String reasonForReferralId)
	{
		if (this.reasonForReferralId == null || !this.reasonForReferralId.equals(reasonForReferralId))
		{
			markModified();
		}
		this.reasonForReferralId = reasonForReferralId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
		 */
	public String getReasonForReferralId()
	{
		fetch();
		return reasonForReferralId;
	}
	
	
	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initReasonForReferral()
	{
		if (reasonForReferral == null)
		{
			try
			{
				reasonForReferral = (Code) new mojo.km.persistence.Reference(reasonForReferralId, Code.class,
						"GANG_ASSMNT_REASONFORREFFERAL").getObject();
			}
			catch (Throwable t)
			{
				
			}
		}
	}
	
	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getReasonForReferral()
	{
		fetch();
		initReasonForReferral();
		return reasonForReferral;
	}

	/**
	 * set the type reference for class member reasonForReferral
	 */
	public void setReasonForReferral(Code reasonForReferral)
	{
		if (this.reasonForReferral == null || !this.reasonForReferral.equals(reasonForReferral))
		{
			markModified();
		}
		if (reasonForReferral.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(reasonForReferral);
		}
		setReasonForReferralId("" + reasonForReferral.getOID());
		this.reasonForReferral = (Code) new  mojo.km.persistence.Reference(reasonForReferral).getObject();
	}
	
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setPlacementFacilityId(String placementFacilityId)
	{
		if (this.placementFacilityId == null || !this.placementFacilityId.equals(placementFacilityId))
		{
			markModified();
		}
		this.placementFacilityId = placementFacilityId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getPlacementFacilityId()
	{
		fetch();
		return placementFacilityId;
	}
	
	
	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initPlacementFacility()
	{
		if (placementFacility == null)
		{
			try
			{
				placementFacility = (Code) new mojo.km.persistence.Reference(placementFacilityId, Code.class,
					PDCodeTableConstants.JUVENILE_DETENTION_FACILITY).getObject();
			}
			catch (Throwable t)
			{
				
			}
		}
	}
	
	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getPlacementFacility()
	{
		fetch();
		initPlacementFacility();
		return placementFacility;
	}

	/**
	 * set the type reference for class member placementFacility
	 */
	public void setPlacementFacility(Code placementFacility)
	{
		if (this.placementFacility == null || !this.placementFacility.equals(placementFacility))
		{
			markModified();
		}
		if (placementFacility.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(placementFacility);
		}
		setPlacementFacilityId("" + placementFacility.getOID());
		this.placementFacility = (Code) new  mojo.km.persistence.Reference(placementFacility).getObject();
	}


	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setLvlOfGangInvolvementId(String lvlOfGangInvolvementId)
	{
		if (this.lvlOfGangInvolvementId == null || !this.lvlOfGangInvolvementId.equals(lvlOfGangInvolvementId))
		{
			markModified();
		}
		this.lvlOfGangInvolvementId = lvlOfGangInvolvementId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getLvlOfGangInvolvementId()
	{
		fetch();
		return lvlOfGangInvolvementId;
	}
	
	
	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initLvlOfGangInvolvement()
	{
		if (lvlOfGangInvolvement == null)
		{
			try
			{
				lvlOfGangInvolvement = (Code) new mojo.km.persistence.Reference(lvlOfGangInvolvementId, Code.class,
						"GANG_ASSMNT_LVLOFGNGINVMNT").getObject();
			}
			catch (Throwable t)
			{
				
			}
		}
	}
	
	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getLvlOfGangInvolvement()
	{
		fetch();
		initLvlOfGangInvolvement();
		return lvlOfGangInvolvement;
	}

	/**
	 * set the type reference for class member lvlOfGangInvolvement
	 */
	public void setLvlOfGangInvolvement(Code lvlOfGangInvolvement)
	{
		if (this.lvlOfGangInvolvement == null || !this.lvlOfGangInvolvement.equals(lvlOfGangInvolvement))
		{
			markModified();
		}
		if (lvlOfGangInvolvement.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(lvlOfGangInvolvement);
		}
		setLvlOfGangInvolvementId("" + lvlOfGangInvolvement.getOID());
		this.lvlOfGangInvolvement = (Code) new  mojo.km.persistence.Reference(lvlOfGangInvolvement).getObject();
	}
	
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setRecommendationsId(String recommendationsId)
	{
		if (this.recommendationsId == null || !this.recommendationsId.equals(recommendationsId))
		{
			markModified();
		}
		this.recommendationsId = recommendationsId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getRecommendationsId()
	{
		fetch();
		return recommendationsId;
	}
	
	
	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initRecommendations()
	{
		if (recommendations == null)
		{
			try
			{
				recommendations = (Code) new mojo.km.persistence.Reference(recommendationsId, Code.class,
						"RECOMMENDATIONS").getObject();
			}
			catch (Throwable t)
			{
				
			}
		}
	}
	
	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getRecommendations()
	{
		fetch();
		initRecommendations();
		return recommendations;
	}
	
	/**
	 * set the type reference for class member recommendations
	 */
	public void setRecommendations(Code recommendations)
	{
		if (this.recommendations == null || !this.recommendations.equals(recommendations))
		{
			markModified();
		}
		if (recommendations.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(recommendations);
		}
		setRecommendationsId("" + recommendations.getOID());
		this.recommendations = (Code) new mojo.km.persistence.Reference(recommendations).getObject();
	}
	
	
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setAssessmentReferralTypeId(String assessmentReferralTypeId)
	{
		if (this.assessmentReferralTypeId == null || !this.assessmentReferralTypeId.equals(assessmentReferralTypeId))
		{
			markModified();
		}
		this.assessmentReferralTypeId = assessmentReferralTypeId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getAssessmentReferralTypeId()
	{
		fetch();
		return assessmentReferralTypeId;
	}
	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initAssessmentReferralType()
	{
		if (assessmentReferralType == null)
		{
			try
			{
				assessmentReferralType = (Code) new mojo.km.persistence.Reference(assessmentReferralTypeId, Code.class,
						"ASSESSMENT_REFERRAL_TYPE").getObject();
			}
			catch (Throwable t)
			{
				
			}
		}
	}
	
	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getAssessmentReferralType()
	{
		fetch();
		initAssessmentReferralType();
		return assessmentReferralType;
	}

	/**
	 * set the type reference for class member AssessmentType
	 */
	public void setAssessmentReferralType(Code assessmentReferralType)
	{
		if (this.assessmentReferralType == null || !this.assessmentReferralType.equals(assessmentReferralType))
		{
			markModified();
		}
		if (assessmentReferralType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(assessmentReferralType);
		}
		setAssessmentReferralTypeId("" + assessmentReferralType.getOID());
		this.assessmentReferralType = (Code) new mojo.km.persistence.Reference(assessmentReferralType).getObject();
	}
	
	/**
	 * @return Returns the acceptedStatus.
	 */
	public String getAcceptedStatus()
	{
		fetch();
		return acceptedStatus;
	}

	/**
	 * acceptedStatus is set.
	 * @param acceptedStatus 
	 */
	public void setAcceptedStatus(String acceptedStatus)
	{
		if (this.acceptedStatus == null || !this.acceptedStatus.equals(acceptedStatus))
		{
			markModified();
		}
		this.acceptedStatus = acceptedStatus;
	}

	/**
	 * @return Returns the assessmentConclusion.
	 */
	public String getAssessmentConclusion()
	{
		fetch();
		return assessmentConclusion;
	}

	/**
	 * assessmentConclusion is set.
	 * @param assessmentConclusion 
	 */
	public void setAssessmentConclusion(String assessmentConclusion)
	{
		if (this.assessmentConclusion == null || !this.assessmentConclusion.equals(assessmentConclusion))
		{
			markModified();
		}
		this.assessmentConclusion = assessmentConclusion;
	}
	
	/**
	 * @return Returns the assessmentComments.
	 */
	public String getAssessmentComments()
	{
		fetch();
		return assessmentComments;
	}

	/**
	 * assessmentComments is set.
	 * @param assessmentComments 
	 */
	public void setAssessmentComments(String assessmentComments)
	{
		if (this.assessmentComments == null || !this.assessmentComments.equals(assessmentComments))
		{
			markModified();
		}
		this.assessmentComments = assessmentComments;
	}
	
	/**
	 * @return the OtherGangName.
	 */
	public String getOtherGangName()
	{
		fetch();
		return otherGangName;
	}

	/**
	 * Other gang name is set.
	 * @param otherGangName 
	 */
	public void setOtherGangName(String otherGangName)
	{
		if (this.otherGangName == null || !this.otherGangName.equals(otherGangName))
		{
			markModified();
		}
		this.otherGangName = otherGangName;
	}
	
	
	/**
	 * @return otherCliqueSet.
	 */
	public String getOtherCliqueSet()
	{
		fetch();
		return otherCliqueSet;
	}

	/**
	 * sets the otherCliqueSet
	 * @param otherCliqueSet 
	 */
	public void setOtherCliqueSet(String otherCliqueSet)
	{
		if (this.otherCliqueSet == null || !this.otherCliqueSet.equals(otherCliqueSet))
		{
			markModified();
		}
		this.otherCliqueSet = otherCliqueSet;
	}
	

	/**
	 * get the desc hybrid.
	 * @return Returns the descHybrid.
	 */
	public String getDescHybrid()
	{
		fetch();
		return descHybrid;
	}

	/**
	 * Sets the descHybrid.
	 * @param descHybrid 
	 */
	public void setDescHybrid(String descHybrid)
	{
		if (this.descHybrid == null || !this.descHybrid.equals(descHybrid))
		{
			markModified();
		}
		this.descHybrid = descHybrid;
	}

	/**
	 * get the otherReasonforReferral.
	 * @return Returns the otherReasonforReferral.
	 */
	public String getOtherReasonforReferral()
	{
		fetch();
		return otherReasonforReferral;
	}

	/**
	 * Sets the otherReasonforReferral.
	 * @param otherReasonforReferral 
	 */
	public void setOtherReasonforReferral(String otherReasonforReferral)
	{
		if (this.otherReasonforReferral == null || !this.otherReasonforReferral.equals(otherReasonforReferral))
		{
			markModified();
		}
		this.otherReasonforReferral = otherReasonforReferral;
	}

		/**
		 * get the rejectionReason.
		 * @return Returns the rejectionReason.
		 */
		public String getRejectionReason()
		{
			fetch();
			return rejectionReason;
		}

		/**
		 * Sets the rejectionReason.
		 * @param rejectionReason 
		 */
		public void setRejectionReason(String rejectionReason)
		{
			if (this.rejectionReason == null || !this.rejectionReason.equals(rejectionReason))
			{
				markModified();
			}
			this.rejectionReason = rejectionReason;
		}
	
		
		/**
		 * @return the PersonMakingReferral.
		 */
		public String getPersonMakingReferral()
		{
			fetch();
			return personMakingReferral;
		}

		/**
		 * PersonMakingReferral name is set.
		 * @param PersonMakingReferral 
		 */
		public void setPersonMakingReferral(String personMakingReferral)
		{
			if (this.personMakingReferral == null || !this.personMakingReferral.equals(personMakingReferral))
			{
				markModified();
			}
			this.personMakingReferral = personMakingReferral;
		}
		
		/**
		 * @return Returns the assessmentStatus.
		 */
		public String getAssessmentStatus()
		{
			fetch();
			return assessmentStatus;
		}

		/**
		 * assessmentStatus is set.
		 * @param assessmentStatus 
		 */
		public void setAssessmentStatus(String assessmentStatus)
		{
			if (this.assessmentStatus == null || !this.assessmentStatus.equals(assessmentStatus))
			{
				markModified();
			}
			this.assessmentStatus = assessmentStatus;
		}
		public String getParentNotified()
		{
		    fetch();
		    return parentNotified;
		}

		public void setParentNotified(String parentNotified)
		{
		    if (this.parentNotified == null || !this.parentNotified.equals(parentNotified))
		    {
				markModified();
		    }
		    this.parentNotified = parentNotified;
		}
		
		public String getParentNotifiedGangAssReq()
		{
		    fetch();
		    return this.parentNotifiedGangAssReq;
		}

		public void setParentNotifiedGangAssReq(String parentNotifiedGangAssReq)
		{
		    if (this.parentNotifiedGangAssReq == null || !this.parentNotifiedGangAssReq.equals(parentNotifiedGangAssReq))
		    {
				markModified();
		    }
		    this.parentNotifiedGangAssReq = parentNotifiedGangAssReq;
		}

		public String getJuvenileName()
		{
		    return juvenileName;
		}

		public void setJuvenileName(String juvenileName)
		{
		    this.juvenileName = juvenileName;
		}
}

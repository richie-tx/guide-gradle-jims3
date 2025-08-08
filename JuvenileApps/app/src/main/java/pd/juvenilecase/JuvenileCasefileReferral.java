/*
 * Created on Sep 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.juvenilecase.reply.JuvenileCasefileReferralResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import pd.codetable.Code;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.contact.officer.OfficerProfile;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSTransferredOffenseReferral;
import pd.km.util.Name;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JuvenileCasefileReferral extends PersistentObject implements Comparable{
	private JuvenileCasefile caseFile = null;
	private String caseFileId;
	private String referralNumber;
	private String caseStatusCd;
	private String supervisionTypeCd;
	private String supervisionCat;

	//Added for US 60035, Court Conversion
	private Date assignmentDate;
	private String officerID;
	private String officerCode;
	
	//added for facility
	private String supervisionType;
	
	//US 71177
	private String assignmentType;
	
	//BUG 86562
	private String juvSeqNum;
	private String refSeqNum;  //89766 User-story
	
	// added for new view rry
	private String offenseCodeId;
	private String inactiveInd;
	private String severitySubtype;
	private String category;
	private String intakeDecisionId;
	private Date intakeDate;
	private String intakeJPO;
	private String ctAssignJPOId;
	private String petitionNum;
	private String dispositionCd;
	private Date decisionDate;
	private Date referralDate;
	private Date closeDate;
	private String juvenileNum;
	private String recType;
	private String controllingReferralId;
	private String referralSource;
	private String courtId;
	private String petitionAllegation;
	private String courtResultId;
	private String investigationNumber;
	private Date offenseDate;
	
	// Fixing performance rry
	private String officerFirstName;
	private String officerMiddleName;
	private String officerLastName;
	private String transOffenseCd;
	private String supervisionDesc;
	private Date probationStartDate;
	private Date probationEndDate;
	private String petitionStatus;
	private String courtDispositionId;
	private String referraltypeInd;
	
	


	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator casefiles = home.findAll(event, JuvenileCasefileReferral.class);
		return casefiles;
	}
	
	
	/**
	 * @return Returns the caseStatusCd.
	 */
	public String getCaseStatusCd() {
		fetch();
		return caseStatusCd;
	}
	/**
	 * @param caseStatusCd The caseStatusCd to set.
	 */
	public void setCaseStatusCd(String aCaseStatusCd) {
		this.caseStatusCd = aCaseStatusCd;
	}
	
	
	
	/**
	* Access method for the referralNumber property.
	* @return the current value of the referralNumber property
	*/
	public String getReferralNumber()
	{
		fetch();
		return referralNumber;
	}
	/**
	* Sets the value of the referralNumber property.
	* @param aReferralNumber the new value of the referralNumber property
	*/
	public void setReferralNumber(String aReferralNumber)
	{
		this.referralNumber = aReferralNumber;
	}
	/**
	* Access method for the caseFile property.
	* @return the current value of the caseFile property
	*/
	public JuvenileCasefile getCaseFile()
	{
		fetch();
		initCaseFile();
		return caseFile;
	}
	
	/**
	* Initialize class relationship to class pd.juvenilecase.CaseFile
	*/
	private void initCaseFile()
	{
		if (caseFile == null)
		{
			try
			{
				caseFile =
					(JuvenileCasefile) new mojo
						.km
						.persistence
						.Reference(caseFileId, JuvenileCasefile.class)
						.getObject();
			}
			catch (Throwable t)
			{
				caseFile = null;
			}
		}
	}
	/**
	* set the type reference for class member caseFile
	*/
	public void setCaseFile(JuvenileCasefile theCaseFile)
	{
		setCaseFileId("" + theCaseFile.getOID());
		this.caseFile = (JuvenileCasefile) new mojo.km.persistence.Reference(theCaseFile).getObject();
	}
	
	/**
	* @return 
	*/
	public String getCaseFileId()
	{
		fetch();
		return caseFileId;
	}
	/**
	* @param string
	*/
	public void setCaseFileId(String theCaseFileId)
	{
		this.caseFileId = theCaseFileId;
	}


	public void setSupervisionTypeCd(String supervisionTypeCd) {
		this.supervisionTypeCd = supervisionTypeCd;
	}


	public String getSupervisionTypeCd() {
		fetch();
		return supervisionTypeCd;
	}


	public void setSupervisionCat(String supervisionCat) {
		this.supervisionCat = supervisionCat;
	}


	public String getSupervisionCat() {
	    	fetch();
		return supervisionCat;
	}
	
	//added for Juvenile Facility Admit
	
	public int compareTo(Object obj)
	{
		if(obj==null)
			return -1;
		JuvenileCasefileReferral caseRef = (JuvenileCasefileReferral)obj;
		return caseFileId.compareToIgnoreCase(caseRef.getCaseFileId());
	}


	public String getSupervisionType() {
		return supervisionType;
	}


	public void setSupervisionType(String supervisionType) {
		this.supervisionType = supervisionType;
	}

	// New setters a getters
	public String getOffenseCodeId()
	{
	    fetch();
	    return offenseCodeId;
	}


	public void setOffenseCodeId(String offenseCodeId)
	{
	    this.offenseCodeId = offenseCodeId;
	}


	public String getCategory()
	{
	    fetch();
	    return category;
	}


	public void setCategory(String category)
	{
	    this.category = category;
	}


	public String getIntakeDecisionId()
	{
	    fetch();
	    return intakeDecisionId;
	}


	public void setIntakeDecisionId(String intakeDecisionId)
	{
	    this.intakeDecisionId = intakeDecisionId;
	}


	public Date getIntakeDate()
	{
	    fetch();
	    return intakeDate;
	}


	public void setIntakeDate(Date intakeDate)
	{
	    this.intakeDate = intakeDate;
	}


	public String getIntakeJPO()
	{
	    fetch();
	    return intakeJPO;
	}


	public void setIntakeJPO(String intakeJPO)
	{
	    this.intakeJPO = intakeJPO;
	}


	public String getPetitionNum()
	{
	    fetch();
	    return petitionNum;
	}


	public void setPetitionNum(String petitionNum)
	{
	    this.petitionNum = petitionNum;
	}


	public String getDispositionCd()
	{
	    fetch();
	    return dispositionCd;
	}


	public void setDispositionCd(String dispositionCd)
	{
	    this.dispositionCd = dispositionCd;
	}


	public Date getDecisionDate()
	{
	    fetch();
	    return decisionDate;
	}


	public void setDecisionDate(Date decisionDate)
	{
	    this.decisionDate = decisionDate;
	}


	public String getCtAssignJPOId()
	{
	    return ctAssignJPOId;
	}


	public void setCtAssignJPOId(String ctAssignJPOId)
	{
	    this.ctAssignJPOId = ctAssignJPOId;
	}


	public String getCourtId()
	{
	    return courtId;
	}


	public void setCourtId(String courtId)
	{
	    this.courtId = courtId;
	}


	public String getPetitionAllegation()
	{
	    return petitionAllegation;
	}


	public void setPetitionAllegation(String petitionAllegation)
	{
	    this.petitionAllegation = petitionAllegation;
	}


	public String getCourtResultId()
	{
	    return courtResultId;
	}


	public void setCourtResultId(String courtResultId)
	{
	    this.courtResultId = courtResultId;
	}


	public String getInvestigationNumber()
	{
	    return investigationNumber;
	}


	public void setInvestigationNumber(String investigationNumber)
	{
	    this.investigationNumber = investigationNumber;
	}


	public Date getOffenseDate()
	{
	    return offenseDate;
	}


	public void setOffenseDate(Date offenseDate)
	{
	    this.offenseDate = offenseDate;
	}

	/**
	 * getValueObject
	 * @return JuvenileCasefileReferralResponseEvent
	 */
	public JuvenileCasefileReferralResponseEvent getValueObject() {
		JuvenileCasefileReferralResponseEvent casefileRespEvt = new JuvenileCasefileReferralResponseEvent();
		casefileRespEvt.setCaseFile(this.getCaseFile());
		casefileRespEvt.setCaseFileId(this.getCaseFileId());
		casefileRespEvt.setCaseStatusCd(this.getCaseStatusCd());
		casefileRespEvt.setReferralNumber(this.getReferralNumber());
		casefileRespEvt.setSupervisionCat(this.getSupervisionCat());
		casefileRespEvt.setSupervisionTypeCd(this.getSupervisionTypeCd());
		casefileRespEvt.setSupervisionType(this.getSupervisionType());
		return casefileRespEvt;
	}
	
	public JuvenileProfileReferralListResponseEvent getValueObjectExt() {
	    
	    JuvenileProfileReferralListResponseEvent casefileRespEvt = new JuvenileProfileReferralListResponseEvent();
		casefileRespEvt.setCasefileId(this.getCaseFileId());
		casefileRespEvt.setAssignmentDate(DateUtil.dateToString(this.getAssignmentDate(),DateUtil.DATE_FMT_1));
		casefileRespEvt.setCaseStatus(this.getCaseStatusCd());
		casefileRespEvt.setFinalDisposition(this.getDispositionCd());
		casefileRespEvt.setCourtDate(this.getDecisionDate());
		casefileRespEvt.setOffense(this.getOffenseCodeId());
		casefileRespEvt.setOffenseCategory(this.getCategory());
		casefileRespEvt.setInactiveInd(this.getInactiveInd());
		casefileRespEvt.setSeveritySubtype(this.getSeveritySubtype());
		casefileRespEvt.setPetitionNumber( this.getPetitionNum());
		casefileRespEvt.setReferralNumber(this.getReferralNumber());
		casefileRespEvt.setReferralDate(this.getReferralDate());
		casefileRespEvt.setReferralOID(this.getOID());
		casefileRespEvt.setRefSeqNum(this.getRefSeqNum());
		casefileRespEvt.setReferralSource(this.getReferralSource());
		
		if( this.getIntakeJPO() == null && this.getOfficerID()!= null ){
		     OfficerProfile profile = OfficerProfile.find(this.getOfficerID());
			if ( profile != null ){
			    
				Name fullName = new Name(profile.getFirstName(),profile.getMiddleName(),profile.getLastName());
				casefileRespEvt.setJpo( fullName.getFormattedName());
				casefileRespEvt.setJpoId(profile.getLogonId());
			    }			    
		    
		} else {
		    
			casefileRespEvt.setJpoId(this.getIntakeJPO());
			Iterator<OfficerProfile> iter = OfficerProfile.findAll("logonId", this.getIntakeJPO());
			if (iter.hasNext()){
			    
			    OfficerProfile officerProfile = (OfficerProfile) iter.next();
			    if( officerProfile!= null){
				Name fullName = new Name(officerProfile.getFirstName(),officerProfile.getMiddleName(),officerProfile.getLastName());
				casefileRespEvt.setJpo( fullName.getFormattedName());
			    }			    
			}
		}
		
		if (this.getCloseDate() != null){
		    casefileRespEvt.setReferralStatus("CLOSED");
		    casefileRespEvt.setCloseDate(this.getCloseDate());
		}	    	
		else{
		    casefileRespEvt.setReferralStatus("ACTIVE");
		}
			
		casefileRespEvt.setSupervisionCategoryId(this.getSupervisionCat());
		 Code code = Code.find(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, this.getSupervisionCat());
		 if (code != null)
		   {
		     casefileRespEvt.setSupervisionCategory( code.getDescription() ); //need to get description here(code.getDescription());
		   }
		
		casefileRespEvt.setSupervisionTypeId(this.getSupervisionTypeCd());
		Code typeCD = Code.find(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE, this.getSupervisionTypeCd());
		  if (typeCD != null)
		   {
		      casefileRespEvt.setSupervisionType( typeCD.getDescription() ); //need to get description here(code.getDescription());
		   }
		casefileRespEvt.setIntakeDecDate(DateUtil.dateToString(this.getIntakeDate(),DateUtil.DATE_FMT_1));
		casefileRespEvt.setIntakeDecisionId( this.getIntakeDecisionId());
		Code intake = Code.find("REFERRAL.DECISION", this.getIntakeDecisionId());
		  if (intake != null)
		   {
		      casefileRespEvt.setIntakeDecision( intake.getDescription() ); //need to get description here(code.getDescription());
		   }
		casefileRespEvt.setAssignmentType(this.getAssignmentType());
		casefileRespEvt.setRecType(this.getRecType());
		casefileRespEvt.setControllingReferralId(this.getControllingReferralId());
		
		Iterator referIter = JJSTransferredOffenseReferral.findAll("juvenileNumber", this.getJuvenileNum() );
		 		
		GetJuvenileCasefileOffensesEvent event = new GetJuvenileCasefileOffensesEvent();
		event.setJuvenileNum(this.getJuvenileNum());
		event.setReferralNum( this.getReferralNumber() );
		
		List<JJSOffense> list = new ArrayList<JJSOffense>();
		Iterator<JJSOffense> iter = JJSOffense.findAll(event);
		while (iter.hasNext())
		{
			JJSOffense offense = iter.next();
			if (this.getReferralNumber().equals(offense.getReferralNum() ) ) {
			    casefileRespEvt.setOffenseDesc(offense.getOffenseDescription());
			}
			list.add(offense);
			
			if ( "TRNDSP".equals(offense.getOffenseCode()) 
					   || "TRNSIN".equals(offense.getOffenseCode())
					   || "REGION".equals(offense.getOffenseCode())
				           || "ISCOIN".equals(offense.getOffenseCode())
				       ){
			    while( referIter.hasNext()){
				     
				     JJSTransferredOffenseReferral transferredOffense = (JJSTransferredOffenseReferral) referIter.next();
					   if ( this.getReferralNumber().equals(transferredOffense.getReferralNumber()) ) {
					       JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode",transferredOffense.getOffenseCode());
					       casefileRespEvt.setOffenseDesc( offenseCode.getShortDescription() + "-" + offenseCode.getCategory() );
					       break;
					   }
				       }
				  
				   } 

		}
		casefileRespEvt.setOffenses(list);
		
		return casefileRespEvt;
	}
	/**
	 * 
	 * @return
	 */
	public JuvenileProfileReferralListResponseEvent getReportValueObject() {
	    
	    JuvenileProfileReferralListResponseEvent casefileRespEvt = new JuvenileProfileReferralListResponseEvent();
		casefileRespEvt.setCasefileId(this.getCaseFileId());
		casefileRespEvt.setAssignmentDate(DateUtil.dateToString(this.getAssignmentDate(),DateUtil.DATE_FMT_1));
		casefileRespEvt.setCaseStatus(this.getCaseStatusCd());
		casefileRespEvt.setFinalDisposition(this.getDispositionCd());
		casefileRespEvt.setCourtDate(this.getDecisionDate());
		casefileRespEvt.setOffense(this.getOffenseCodeId());
		casefileRespEvt.setOffenseCategory(this.getCategory());
		casefileRespEvt.setInactiveInd(this.getInactiveInd());
		casefileRespEvt.setSeveritySubtype(this.getSeveritySubtype());
		casefileRespEvt.setPetitionNumber( this.getPetitionNum());
		casefileRespEvt.setReferralNumber(this.getReferralNumber());
		casefileRespEvt.setReferralDate(this.getReferralDate());
		casefileRespEvt.setReferralOID(this.getOID());
		casefileRespEvt.setRefSeqNum(this.getRefSeqNum());
		casefileRespEvt.setReferralSourceDesc(this.getReferralSource());
		casefileRespEvt.setSupervisionCategoryId(this.getSupervisionCat());
		casefileRespEvt.setSupervisionTypeId(this.getSupervisionTypeCd());
		casefileRespEvt.setAssignmentType(this.getAssignmentType());
		casefileRespEvt.setRecType(this.getRecType());
		casefileRespEvt.setControllingReferralId(this.getControllingReferralId());
		casefileRespEvt.setIntakeDecDate(DateUtil.dateToString(this.getIntakeDate(),DateUtil.DATE_FMT_1));
		casefileRespEvt.setIntakeDecisionId( this.getIntakeDecisionId());
		casefileRespEvt.setCourtId(this.getCourtId());
		casefileRespEvt.setPetitionAllegation(this.getPetitionAllegation());
		casefileRespEvt.setCtAssignJpoId(this.getCtAssignJPOId());
		casefileRespEvt.setCourtResult(this.getCourtResultId());
		casefileRespEvt.setOffenseInvestNum(this.getInvestigationNumber());
		casefileRespEvt.setOffenseDate(this.getOffenseDate());
		if (this.getCloseDate() != null){
		    casefileRespEvt.setReferralStatus("CLOSED");
		    casefileRespEvt.setCloseDate(this.getCloseDate());
		}	    	
		else{
		    casefileRespEvt.setReferralStatus("ACTIVE");
		}
		if( this.getIntakeJPO() != null ){
		    
		    casefileRespEvt.setJpoId(this.getIntakeJPO());
		}else{
		    
		    casefileRespEvt.setJpoId(this.getCtAssignJPOId());
		}
		
		return casefileRespEvt;
		
	}


	/**
	 * @return the assignmentDate
	 */
	public Date getAssignmentDate() {
		return assignmentDate;
	}


	/**
	 * @param assignmentDate the assignmentDate to set
	 */
	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}


	/**
	 * @return the officerID
	 */
	public String getOfficerID() {
		return officerID;
	}


	/**
	 * @param officerID the officerID to set
	 */
	public void setOfficerID(String officerID) {
		this.officerID = officerID;
	}


	/**
	 * @return the assignmentType
	 */
	public String getAssignmentType()
	{
	    return assignmentType;
	}


	/**
	 * @param assignmentType the assignmentType to set
	 */
	public void setAssignmentType(String assignmentType)
	{
	    this.assignmentType = assignmentType;
	}


	public String getJuvSeqNum()
	{
	    return juvSeqNum;
	}


	public void setJuvSeqNum(String juvSeqNum)
	{
	    this.juvSeqNum = juvSeqNum;
	}


	/**
	 * @return the refSeqNum
	 */
	public String getRefSeqNum()
	{
	    fetch();
	    return refSeqNum;
	}


	/**
	 * @param refSeqNum the refSeqNum to set
	 */
	public void setRefSeqNum(String refSeqNum)
	{
	    this.refSeqNum = refSeqNum;
	}


	public Date getReferralDate()
	{
	    fetch();
	    return referralDate;
	}


	public void setReferralDate(Date referralDate)
	{
	    this.referralDate = referralDate;
	}


	public Date getCloseDate()
	{
	    fetch();
	    return closeDate;
	}


	public void setCloseDate(Date closedDate)
	{
	    this.closeDate = closedDate;
	}


	public String getRecType()
	{
	    fetch();
	    return recType;
	}


	public void setRecType(String recType)
	{
	    this.recType = recType;
	}


	public String getJuvenileNum()
	{
	    fetch();
	    return juvenileNum;
	}


	public void setJuvenileNum(String juvenileNum)
	{
	    this.juvenileNum = juvenileNum;
	}


	public String getControllingReferralId()
	{
	    return controllingReferralId;
	}


	public void setControllingReferralId(String controllingReferralId)
	{
	    this.controllingReferralId = controllingReferralId;
	}


	public String getReferralSource()
	{
	    fetch();
	    return referralSource;
	}


	public void setReferralSource(String referralSource)
	{
	    this.referralSource = referralSource;
	}


	public String getInactiveInd()
	{
	    fetch();
	    return inactiveInd;
	}


	public void setInactiveInd(String inactiveInd)
	{
	    this.inactiveInd = inactiveInd;
	}


	public String getSeveritySubtype()
	{
	    fetch();
	    return severitySubtype;
	}


	public void setSeveritySubtype(String severitySubtype)
	{
	    this.severitySubtype = severitySubtype;
	}


	public String getOfficerCode()
	{
	    fetch();
	    return officerCode;
	}


	public void setOfficerCode(String officerCode)
	{
	    this.officerCode = officerCode;
	}


	public String getOfficerFirstName()
	{
	    return officerFirstName;
	}


	public void setOfficerFirstName(String officerFirstName)
	{
	    this.officerFirstName = officerFirstName;
	}


	public String getOfficerMiddleName()
	{
	    return officerMiddleName;
	}


	public void setOfficerMiddleName(String officerMiddleName)
	{
	    this.officerMiddleName = officerMiddleName;
	}


	public String getOfficerLastName()
	{
	    return officerLastName;
	}


	public void setOfficerLastName(String officerLastName)
	{
	    this.officerLastName = officerLastName;
	}


	public String getTransOffenseCd()
	{
	    return transOffenseCd;
	}


	public void setTransOffenseCd(String transOffenseCd)
	{
	    this.transOffenseCd = transOffenseCd;
	}


	public String getSupervisionDesc()
	{
	    return supervisionDesc;
	}


	public void setSupervisionDesc(String supervisionDesc)
	{
	    this.supervisionDesc = supervisionDesc;
	}


	public Date getProbationStartDate()
	{
	    return probationStartDate;
	}


	public void setProbationStartDate(Date probationStartDate)
	{
	    this.probationStartDate = probationStartDate;
	}


	public Date getProbationEndDate()
	{
	    return probationEndDate;
	}


	public void setProbationEndDate(Date probationEndDate)
	{
	    this.probationEndDate = probationEndDate;
	}


	public String getPetitionStatus()
	{
	    return petitionStatus;
	}


	public void setPetitionStatus(String petitionStatus)
	{
	    this.petitionStatus = petitionStatus;
	}


	public String getCourtDispositionId()
	{
	    return courtDispositionId;
	}


	public void setCourtDispositionId(String courtDispositionId)
	{
	    this.courtDispositionId = courtDispositionId;
	}	
	public String getReferraltypeInd()
	{
	    fetch();
	    return referraltypeInd;
	}


	public void setReferraltypeInd(String referraltypeInd)
	{
	    this.referraltypeInd = referraltypeInd;
	}
}

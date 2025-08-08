package pd.supervision.administerassessments;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import messaging.administerassessments.CSCAnswer;
import messaging.administerassessments.UpdateCSAssessmentEvent;
import messaging.administerassessments.reply.AssessmentSummaryResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.CollectionUtil;
import naming.CSCAssessmentConstants;
import naming.PDConstants;
import pd.codetable.Code;
import pd.common.util.StringUtil;

/**
 * Date on which changes were made to assessment.
 */
public class Assessment extends PersistentObject
{
	private Date assessmentDate;
	private Date migratedAssessmentDate; //Set on migrated assessments only.

	/**
	 * Properties for assessmentQuestionAnswers
	 * @referencedType pd.supervision.administerassessments.AssessmentQuestionAnswer
	 */
	private java.util.Collection assessmentQuestionAnswers = null;
	/**
	 * Properties for assessmentType
	 */
	private Code assessmentType = null;
	private String assessmentTypeId;
	private String defendantId;
	/**
	 * Properties for masterAssessment
	 */
	private Assessment masterAssessment;
	private String masterAssessmentId;
	/**
	 * Date on which changes were made to assessment.
	 */
	private Date transactionDate;
	private int versionNum;
	private boolean initial;
	private String statusCd;

	
	/**
	 * 
	 * @roseuid 47ACD6C60159
	 */
	public Assessment()
	{
	}

	/**
	 * 
	 * @roseuid 47A9DB1C02CD
	 */
	private void bind()
	{
		IHome home = new Home();
		home.bind(this);
	}

	/**
	 * Clears all pd.supervision.administerassessments.AssessmentQuestionAnswer from class relationship collection.
	 */
	public void clearAssessmentQuestionAnswers()
	{
		initAssessmentQuestionAnswers();
		assessmentQuestionAnswers.clear();
	}

	/**
	 * 
	 * @roseuid 47AB42E900DD
	 * @return List
	 * @param anEvent
	 */
	static public List findAll(IEvent anEvent)
	{
		Home home = new Home();
		Iterator iter = home.findAll(anEvent, Assessment.class);
		return CollectionUtil.iteratorToList(iter);
	}

	/**
	 * returns a collection of pd.supervision.administerassessments.AssessmentQuestionAnswer
	 */
	public java.util.Collection getAssessmentQuestionAnswers()
	{
		fetch();
		initAssessmentQuestionAnswers();
		return assessmentQuestionAnswers;
	}

	public boolean isInitial() {
		fetch();
		return initial;
	}

	public void setInitial(boolean initial) {
		if (this.initial != initial)
		{
			markModified();
		}
		this.initial = initial;
	}
	
	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getAssessmentType()
	{
		fetch();
		initAssessmentType();
		return assessmentType;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getAssessmentTypeId()
	{
		fetch();
		return assessmentTypeId;
	}
	
	public String getSpecificAssessmentId()
	{
		String oid= "";
		List list = null;
        
		if(CSCAssessmentConstants.ASSESSMENTTYPE_WISCONSIN.equalsIgnoreCase(this.getAssessmentTypeId())){
			list = Wisconsin.findAll(CSCAssessmentConstants.ASSESMENTID, this.getOID());
			if(list != null && !list.isEmpty()){
				Wisconsin win = (Wisconsin) list.get(0);
				oid = win.getOID();
			}
		}else if(CSCAssessmentConstants.ASSESSMENTTYPE_FORCEFIELD.equalsIgnoreCase(this.getAssessmentTypeId())){
			list = ForceFieldAnalysis.findAll(CSCAssessmentConstants.ASSESMENTID, this.getOID());
			if(list != null && !list.isEmpty()){
				ForceFieldAnalysis ffa = (ForceFieldAnalysis) list.get(0);
				oid = ffa.getOID();
			}
		}else if(CSCAssessmentConstants.ASSESSMENTTYPE_LSIR.equalsIgnoreCase(this.getAssessmentTypeId())){
			list = LSIR.findAll(CSCAssessmentConstants.ASSESMENTID, this.getOID());
			if(list != null && !list.isEmpty()){
				LSIR lsir = (LSIR) list.get(0);
				oid = lsir.getOID();
			}
		}else if(CSCAssessmentConstants.ASSESSMENTTYPE_SCS.equalsIgnoreCase(this.getAssessmentTypeId())){
			list = Assessments.findAll("assessmentId", this.getOID());
			if(list != null && !list.isEmpty()){
				Assessments assmt = (Assessments) list.get(0);
				StrategiesForCaseScorer scs = StrategiesForCaseScorer.find("" + assmt.getScsId());
				if(scs != null){
					oid = scs.getOID();
				}
			}
		}
		return oid;
	}

	/**
	 * Gets referenced type pd.supervision.administerassessments.Assessment
	 */
	public Assessment getMasterAssessment()
	{
		fetch();
		initMasterAssessment();
		return masterAssessment;
	}

	/**
	 * 
	 * @return Returns the masterAssessmentId.
	 */
	public String getMasterAssessmentId()
	{
		fetch();
		return masterAssessmentId;
	}

	/**
	 * Initialize class relationship implementation for pd.supervision.administerassessments.AssessmentQuestionAnswer
	 */
	private void initAssessmentQuestionAnswers()
	{
		if (assessmentQuestionAnswers == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			assessmentQuestionAnswers = new mojo.km.persistence.ArrayList(
					AssessmentQuestionAnswer.class, "assessmentId", "" + getOID());
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initAssessmentType()
	{
		if (assessmentType == null)
		{
			assessmentType = (Code) new mojo.km.persistence.Reference(assessmentTypeId,
					Code.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.administerassessments.Assessment
	 */
	private void initMasterAssessment()
	{
		if (masterAssessmentId == null)
		{
			masterAssessment = (Assessment) new mojo.km.persistence.Reference(
					masterAssessmentId, Assessment.class).getObject();
		}
	}

	/**
	 * insert a pd.supervision.administerassessments.AssessmentQuestionAnswer into class relationship collection.
	 */
	public void insertAssessmentQuestionAnswers(AssessmentQuestionAnswer anObject)
	{
		initAssessmentQuestionAnswers();
		assessmentQuestionAnswers.add(anObject);
	}

	/**
	 * Removes a pd.supervision.administerassessments.AssessmentQuestionAnswer from class relationship collection.
	 */
	public void removeAssessmentQuestionAnswers(AssessmentQuestionAnswer anObject)
	{
		initAssessmentQuestionAnswers();
		assessmentQuestionAnswers.remove(anObject);
	}

	/**
	 * set the type reference for class member assessmentType
	 */
	public void setAssessmentType(Code assessmentType)
	{
		if (this.assessmentType == null || !this.assessmentType.equals(assessmentType))
		{
			markModified();
		}
		if (assessmentType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(assessmentType);
		}
		setAssessmentTypeId("" + assessmentType.getOID());
		this.assessmentType = (Code) new mojo.km.persistence.Reference(assessmentType).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setAssessmentTypeId(String assessmentTypeId)
	{
		if (this.assessmentTypeId == null || !this.assessmentTypeId.equals(assessmentTypeId))
		{
			markModified();
		}
		assessmentType = null;
		this.assessmentTypeId = assessmentTypeId;
	}

	/**
	 * 
	 * @param masterAssessment The masterAssessment to set.
	 */
	public void setMasterAssessment(Assessment masterAssessment)
	{
		if (this.masterAssessment == null || !this.masterAssessment.equals(masterAssessment))
		{
			markModified();
		}
		if (masterAssessment.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(masterAssessment);
		}
		setMasterAssessmentId("" + masterAssessment.getOID());
		this.masterAssessment = masterAssessment;
	}

	/**
	 * 
	 * @param masterAssessmentId The masterAssessmentId to set.
	 */
	public void setMasterAssessmentId(String masterAssessmentId)
	{
		if (this.masterAssessmentId == null || !this.masterAssessmentId.equals(masterAssessmentId))
		{
			markModified();
		}
		masterAssessment = null;
		this.masterAssessmentId = masterAssessmentId;
	}

	/**
	 * 
	 * @return Returns the assessmentDate.
	 */
	public Date getAssessmentDate()
	{
		fetch();
		return assessmentDate;
	}

	/**
	 * 
	 * @param assessmentDate The assessmentDate to set.
	 */
	public void setAssessmentDate(Date assessmentDate)
	{
		if (this.assessmentDate == null || !this.assessmentDate.equals(assessmentDate))
		{
			markModified();
		}
		this.assessmentDate = assessmentDate;
	}
	
	public Date getMigratedAssessmentDate() {
		fetch();
		return migratedAssessmentDate;
	}

	public void setMigratedAssessmentDate(Date migratedAssessmentDate) {
		if (this.migratedAssessmentDate == null || !this.migratedAssessmentDate.equals(migratedAssessmentDate))
		{
			markModified();
		}
		this.migratedAssessmentDate = migratedAssessmentDate;
	}

	/**
	 * 
	 * @return Returns the defendantId.
	 */
	public String getDefendantId()
	{
		fetch();
		return defendantId;
	}

	/**
	 * 
	 * @param defendantId The defendantId to set.
	 */
	public void setDefendantId(String defendantId)
	{
		if (this.defendantId == null || !this.defendantId.equals(defendantId))
		{
			markModified();
		}
		this.defendantId = defendantId;
	}

	/**
	 * 
	 * @return Returns the transactionDate.
	 */
	public Date getTransactionDate()
	{
		fetch();
		return transactionDate;
	}

	/**
	 * 
	 * @param transactionDate The transactionDate to set.
	 */
	public void setTransactionDate(Date transactionDate)
	{
		if (this.transactionDate == null || !this.transactionDate.equals(transactionDate))
		{
			markModified();
		}
		this.transactionDate = transactionDate;
	}

	/**
	 * 
	 * @return Returns the versionNum.
	 */
	public int getVersionNum()
	{
		fetch();
		return versionNum;
	}

	/**
	 * 
	 * @param versionNum The versionNum to set.
	 */
	public void setVersionNum(int versionNum)
	{
		if (this.versionNum != versionNum)
		{
			markModified();
		}
		this.versionNum = versionNum;
	}
	
	/**
	 * @return the statusCd
	 */
	public String getStatusCd() {
		fetch();
		return statusCd;
	}

	/**
	 * @param statusCd the statusCd to set
	 */
	public void setStatusCd(String statusCd) {
		if (this.statusCd == null || !this.statusCd.equals(statusCd))
		{
			markModified();
		}
		this.statusCd = statusCd;
	}
	

	/**
	 * 
	 * @return 
	 * @param updateCSAssessmentEvent
	 */
	static public Assessment create(UpdateCSAssessmentEvent updateCSAssessmentEvent)
	{
		Assessment assessment = null;
		HashMap reqQuestionAnsMap = new HashMap();
		
//		create a map of QuestionId and Answers Event
		if (updateCSAssessmentEvent.getQuestionAnswers() != null
				&& updateCSAssessmentEvent.getQuestionAnswers().size() > 0)
		{
			Iterator reqAnsIter = updateCSAssessmentEvent.getQuestionAnswers().iterator();
			while(reqAnsIter.hasNext())
			{
				CSCAnswer answerEvent = (CSCAnswer)reqAnsIter.next();
				String questionId = answerEvent.getQuestionId();
				reqQuestionAnsMap.put(questionId, answerEvent);
			}
		}
		
		if(updateCSAssessmentEvent.isAssessmentIncomplete())
		{
			if(StringUtil.isEmpty(updateCSAssessmentEvent.getMasterAssessmentId()))
			{
				assessment = new Assessment();
				assessment.setAssessmentDate(updateCSAssessmentEvent.getAssessmentDate());
				assessment.setAssessmentTypeId(updateCSAssessmentEvent.getAssessmentTypeCd());
				assessment.setDefendantId(updateCSAssessmentEvent.getDefendantId());
				assessment.setTransactionDate(new Date());
				assessment.setStatusCd(CSCAssessmentConstants.ASSESSMENT_INCOMPLETE);
				assessment.bind(); //Need OID for child objects/answers
				assessment.setMasterAssessmentId(assessment.getOID());
				
//				insert the Q&A for the assessment
				if (updateCSAssessmentEvent.getQuestionAnswers() != null
						&& updateCSAssessmentEvent.getQuestionAnswers().size() > 0)
				{
					List reqAnswerList = CollectionUtil.iteratorToList(updateCSAssessmentEvent.getQuestionAnswers().iterator());
					CSCAnswer answerEvent = null;
					AssessmentQuestionAnswer assessmentQA = null;
					for (int i = 0; i < reqAnswerList.size(); i++)
					{
						answerEvent = (CSCAnswer) reqAnswerList.get(i);
						assessmentQA = new AssessmentQuestionAnswer();
						assessmentQA.setAssessmentId(assessment.getOID());
						assessmentQA.setAnswerId(answerEvent.getResponseId());
						assessmentQA.setAnswerText(answerEvent.getResponseText());
						assessmentQA.setQuestionGroupId(answerEvent.getQuestionGroupId());
						assessmentQA.setQuestionId(answerEvent.getQuestionId());
						assessment.insertAssessmentQuestionAnswers(assessmentQA);
					}
				}
			}
			else
			{
				String assessmentId = updateCSAssessmentEvent.getMasterAssessmentId();
				assessment = Assessment.find(assessmentId);
				if(assessment != null)
				{
					assessment.setAssessmentDate(updateCSAssessmentEvent.getAssessmentDate());
					assessment.setTransactionDate(new Date());
				}
				
//				update the previous Q&A list
				List assessQAList = AssessmentQuestionAnswer.findAll("assessmentId", assessmentId);
				Iterator assessIter = assessQAList.iterator();
				while(assessIter.hasNext())
				{
					AssessmentQuestionAnswer assessmentQA = (AssessmentQuestionAnswer)assessIter.next();
					
					CSCAnswer answerEvent = (CSCAnswer)reqQuestionAnsMap.get(assessmentQA.getQuestionId());
					if (answerEvent != null) {
						assessmentQA.setAnswerId(answerEvent.getResponseId());
						assessmentQA.setAnswerText(answerEvent.getResponseText());
					}
				}
			}
		}
//		If the assessment is "COMPLETE"
		else
		{
			String assessmentId = updateCSAssessmentEvent.getMasterAssessmentId();
			assessment = Assessment.find(assessmentId);
			
			if((assessment!=null) && (assessment.getStatusCd()!=null) && (assessment.getStatusCd().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_INCOMPLETE)))
			{
				assessment.setAssessmentDate(updateCSAssessmentEvent.getAssessmentDate());
				assessment.setStatusCd(CSCAssessmentConstants.ASSESSMENT_COMPLETE);
				assessment.setTransactionDate(new Date());
				assessment.setVersionNum(1);
				
//				update the previous Q&A list
				List assessQAList = AssessmentQuestionAnswer.findAll("assessmentId", assessmentId);
				Iterator assessIter = assessQAList.iterator();
				while(assessIter.hasNext())
				{
					AssessmentQuestionAnswer assessmentQA = (AssessmentQuestionAnswer)assessIter.next();
					
					CSCAnswer answerEvent = (CSCAnswer)reqQuestionAnsMap.get(assessmentQA.getQuestionId());
					assessmentQA.setAnswerId(answerEvent.getResponseId());
					assessmentQA.setAnswerText(answerEvent.getResponseText());
				}
			}
			else
			{
				int newVersionNum = AssessmentHelper.getNextVersionNum(updateCSAssessmentEvent.getMasterAssessmentId());
				assessment = new Assessment();
				assessment.setAssessmentDate(updateCSAssessmentEvent.getAssessmentDate());
				assessment.setAssessmentTypeId(updateCSAssessmentEvent.getAssessmentTypeCd());
				assessment.setDefendantId(updateCSAssessmentEvent.getDefendantId());
				assessment.setMasterAssessmentId(updateCSAssessmentEvent.getMasterAssessmentId());
				assessment.setTransactionDate(new Date());
				assessment.setVersionNum(newVersionNum);
				assessment.setStatusCd(CSCAssessmentConstants.ASSESSMENT_COMPLETE);
				assessment.bind(); //Need OID for child objects/answers
				if (updateCSAssessmentEvent.getMasterAssessmentId() == null || PDConstants.BLANK.equals(updateCSAssessmentEvent.getMasterAssessmentId())){
				    assessment.setMasterAssessmentId(assessment.getOID());
				}
				
//				insert the Q&A for the assessment
				if (updateCSAssessmentEvent.getQuestionAnswers() != null
						&& updateCSAssessmentEvent.getQuestionAnswers().size() > 0)
				{
					List reqAnswerList = CollectionUtil.iteratorToList(updateCSAssessmentEvent.getQuestionAnswers().iterator());
					CSCAnswer answerEvent = null;
					AssessmentQuestionAnswer assessmentQA = null;
					for (int i = 0; i < reqAnswerList.size(); i++)
					{
						answerEvent = (CSCAnswer) reqAnswerList.get(i);
						assessmentQA = new AssessmentQuestionAnswer();
						assessmentQA.setAssessmentId(assessment.getOID());
						assessmentQA.setAnswerId(answerEvent.getResponseId());
						assessmentQA.setAnswerText(answerEvent.getResponseText());
						assessmentQA.setQuestionGroupId(answerEvent.getQuestionGroupId());
						assessmentQA.setQuestionId(answerEvent.getQuestionId());
						assessment.insertAssessmentQuestionAnswers(assessmentQA);
					}
				}
			}
		}
		return assessment;
	}

	/**
	 * @param attrName
	 * @param attrValue
	 * @return 
	 */
	static public List findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(attrName, attrValue, Assessment.class);
		return CollectionUtil.iteratorToList(iter);
	}

	/**
	 * @param oid
	 * @return 
	 */
	static public Assessment find(String oid)
	{
		IHome home = new Home();
		return (Assessment) home.find(oid, Assessment.class);
	}
	/**
	 * @param origAssessment
	 * @param newAssessment
	 */
	static public void copyAnswers(Assessment origAssessment, Assessment newAssessment){
	       List qaList = CollectionUtil.iteratorToList(origAssessment.getAssessmentQuestionAnswers().iterator());
	       AssessmentQuestionAnswer aQa = null;
	       AssessmentQuestionAnswer newQa =  null;
	       
	       for (int i = 0; i < qaList.size(); i++) {
	           aQa = (AssessmentQuestionAnswer) qaList.get(i);
	           newQa = new AssessmentQuestionAnswer();
	           newQa.setAnswerId(aQa.getAnswerId());
	           newQa.setAnswerText(aQa.getAnswerText());
	           newQa.setQuestionGroupId(aQa.getQuestionGroupId());
	           newQa.setQuestionId(aQa.getQuestionId());
	           newAssessment.insertAssessmentQuestionAnswers(newQa);
	       }
	}
	
	public AssessmentSummaryResponseEvent getResponseEvent(){
		AssessmentSummaryResponseEvent resp = new AssessmentSummaryResponseEvent();
		resp.setAssessmentDate(this.getAssessmentDate());
		resp.setAssessmentId(this.getOID());
		resp.setAssessmentTypeId(this.getAssessmentTypeId());
		resp.setInitial(this.isInitial());
		resp.setMasterAssessmentId(this.getMasterAssessmentId());
		resp.setSpecificTypeAssessmentId(this.getSpecificAssessmentId());
		return resp;
	}
}

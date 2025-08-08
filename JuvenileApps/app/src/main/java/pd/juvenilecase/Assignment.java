package pd.juvenilecase;

import java.util.Date;
import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.person.JuvenileLevelUnitCode;
import pd.km.util.Formatter;

/**
* @author asrvastava
This entity represents the assignment record that is fetched from M204 and
gets added into a case file.
*/
public class Assignment extends PersistentObject
{
	/**
	* @referencedType pd.juvenilecase.CaseFile
	* @detailerDoNotGenerate false
	*/
	private JuvenileCasefile caseFile = null;
	private String serviceUnitId;

	private String caseFileId;
	/**
	* @referencedType pd.codetable.person.JuvenileLevelUnitCode
	* @detailerDoNotGenerate false
	*/
	private JuvenileLevelUnitCode serviceUnit = null;
	private String assignmentLevelId;
	private String referralNumber;
	/**
	* @referencedType pd.codetable.person.JuvenileLevelUnitCode
	* @detailerDoNotGenerate false
	*/
	private JuvenileLevelUnitCode assignmentLevel = null;
	private Date assignmentAddDate;
	private String assignByUserId;
	
	//US 71173
	private String assignmentType;
	
	//US 71181
	private String overrideReason;
	private String overrideOtherComments;
	private String seqNum;//U.S 88316//89766
	/**
	* @roseuid 4276853503B9
	*/
	public Assignment()
	{
	}
	/**
	* @return Assignment
	* @param assignmentId
	*/
	static public Assignment find(String assignmentId)
	{
		IHome home = new Home();
		Assignment assignment = (Assignment) home.find(assignmentId, Assignment.class);
		return assignment;
	}
	/**
	* @return Iterator assignments
	* @param attrName name fo the attribute for where clause
	* @param attrValue value to be checked in the where clause
	*/
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator assignments = home.findAll(attrName, attrValue, Assignment.class);
		return assignments;
	}
	/**
	* Access method for the assignmentAddDate property.
	* @return the current value of the assignmentAddDate property
	*/
	public Date getAssignmentAddDate()
	{
		fetch();
		return assignmentAddDate;
	}
	/**
	* Sets the value of the assignmentAddDate property.
	* @param aAssignmentAddDate the new value of the assignmentAddDate property
	*/
	public void setAssignmentAddDate(Date aAssignmentAddDate)
	{
		if (this.assignmentAddDate == null || !this.assignmentAddDate.equals(aAssignmentAddDate))
		{
			markModified();
		}
		assignmentAddDate = aAssignmentAddDate;
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
		if (this.referralNumber == null || !this.referralNumber.equals(aReferralNumber))
		{
			markModified();
		}
		referralNumber = aReferralNumber;
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
	* Access method for the assignmentLevel property.
	* @return the current value of the assignmentLevel property
	*/
	public JuvenileLevelUnitCode getAssignmentLevel()
	{
		fetch();
		initAssignmentLevel();
		return assignmentLevel;
	}
	/**
	* Access method for the serviceUnit property.
	* @return the current value of the serviceUnit property
	*/
	public JuvenileLevelUnitCode getServiceUnit()
	{
		fetch();
		initServiceUnit();
		return serviceUnit;
	}
	/**
	* @roseuid 427136DA0030
	*/
	public void bind()
	{
		markModified();
	}
	/**
	* Set the reference value to class :: pd.codetable.person.JuvenileLevelUnitCode
	*/
	public void setAssignmentLevelId(String theAssignmentLevelId)
	{
		if (this.assignmentLevelId == null || !this.assignmentLevelId.equals(theAssignmentLevelId))
		{
			markModified();
		}
		assignmentLevel = null;
		this.assignmentLevelId = theAssignmentLevelId;
	}
	/**
	* Get the reference value to class :: pd.codetable.person.JuvenileLevelUnitCode
	*/
	public String getAssignmentLevelId()
	{
		fetch();
		return assignmentLevelId;
	}
	/**
	* Initialize class relationship to class pd.codetable.person.JuvenileLevelUnitCode
	*/
	private void initAssignmentLevel()
	{
		if (assignmentLevel == null)
		{
			if (assignmentLevelId != null)
			{
				try
				{
					StringBuffer derivedOid = new StringBuffer("");
					derivedOid.append(assignmentLevelId).append(serviceUnitId);
					assignmentLevel =  JuvenileLevelUnitCode.find("levelUnitCD", derivedOid.toString());
						/*(pd.codetable.person.JuvenileLevelUnitCode) new mojo
							.km
							.persistence
							.Reference(derivedOid.toString(), pd.codetable.person.JuvenileLevelUnitCode.class)
							.getObject();*/
				}
				catch (Throwable t)
				{
					assignmentLevelId = null;
				}
			}
		}
	}
	/**
	* set the type reference for class member assignmentLevel
	*/
	public void setAssignmentLevel(JuvenileLevelUnitCode theAssignmentLevel)
	{
		if (this.assignmentLevel == null || !this.assignmentLevel.equals(theAssignmentLevel))
		{
			markModified();
		}
		if (theAssignmentLevel.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(theAssignmentLevel);
		}
		setAssignmentLevelId("" + theAssignmentLevel.getOID());
		this.assignmentLevel =
			(JuvenileLevelUnitCode) new mojo
				.km
				.persistence
				.Reference(theAssignmentLevel)
				.getObject();
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
		if (this.caseFile == null || !this.caseFile.equals(theCaseFile))
		{
			markModified();
		}
		if (theCaseFile.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(theCaseFile);
		}
		setCaseFileId("" + theCaseFile.getOID());
		this.caseFile = (JuvenileCasefile) new mojo.km.persistence.Reference(theCaseFile).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.person.JuvenileLevelUnitCode
	*/
	public void setServiceUnitId(String theServiceUnitId)
	{
		if (this.serviceUnitId == null || !this.serviceUnitId.equals(theServiceUnitId))
		{
			markModified();
		}
		serviceUnit = null;
		this.serviceUnitId = theServiceUnitId;
	}
	/**
	* Get the reference value to class :: pd.codetable.person.JuvenileLevelUnitCode
	*/
	public String getServiceUnitId()
	{
		fetch();
		return serviceUnitId;
	}
	/**
	* Initialize class relationship to class pd.codetable.person.JuvenileLevelUnitCode
	*/
	private void initServiceUnit()
	{
		if (serviceUnit == null)
		{
			if (serviceUnitId != null)
			{
				try
				{
					StringBuffer derivedOid = new StringBuffer("");
					derivedOid.append(assignmentLevelId).append(serviceUnitId);
					serviceUnit = JuvenileLevelUnitCode.find("levelUnitCD", derivedOid.toString());
					//derivedOid.append(Formatter.pad(assignmentLevelId, 4, '0', true));
					
					/*if (serviceUnitId != null)
					{
						derivedOid.append(Formatter.pad(serviceUnitId, 4, '0', true));
					}*/
					
						/*new mojo
							.km
							.persistence
							.Reference(derivedOid.toString(), pd.codetable.person.JuvenileLevelUnitCode.class)
							.getObject();*/
				}
				catch (Throwable t)
				{
					serviceUnitId = null;
				}
			}
		}
	}
	/**
	* set the type reference for class member serviceUnit
	*/
	public void setServiceUnit(JuvenileLevelUnitCode theServiceUnit)
	{
		if (this.serviceUnit == null || !this.serviceUnit.equals(theServiceUnit))
		{
			markModified();
		}
		if (theServiceUnit.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(theServiceUnit);
		}
		setServiceUnitId("" + theServiceUnit.getOID());
		this.serviceUnit =
			(JuvenileLevelUnitCode) new mojo.km.persistence.Reference(theServiceUnit).getObject();
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
		if (this.caseFileId == null || !this.caseFileId.equals(theCaseFileId))
		{
			markModified();
		}
		this.caseFileId = theCaseFileId;
	}
	public String getAssignByUserId() {
		fetch();
		return assignByUserId;
	}
	public void setAssignByUserId(String assignByUserId) {
		if (this.assignByUserId == null || !this.assignByUserId.equals(assignByUserId))
		{
			markModified();
		}
		this.assignByUserId = assignByUserId;
	}
	
	public String getAssignmentType() {
		fetch();
		return assignmentType;
	}
	public void setAssignmentType(String assignmentType) {
		if (this.assignmentType == null || !this.assignmentType.equals(assignmentType))
		{
			markModified();
		}
		this.assignmentType = assignmentType;
	}
	public String getOverrideReason()
	{
	    return overrideReason;
	}
	public void setOverrideReason(String overrideReason)
	{
	    this.overrideReason = overrideReason;
	}
	public String getOverrideOtherComments()
	{
	    return overrideOtherComments;
	}
	public void setOverrideOtherComments(String overrideOtherComments)
	{
	    this.overrideOtherComments = overrideOtherComments;
	}
	/**
	 * @return the seqNum
	 */
	public String getSeqNum()
	{
	    fetch();
	    return seqNum;
	}
	/**
	 * @param seqNum the seqNum to set
	 */
    public void setSeqNum(String seqNum)
    {
	if (this.seqNum == null || !this.seqNum.equals(seqNum))
	{
	    markModified();
	}
	this.seqNum = seqNum;
    }
}

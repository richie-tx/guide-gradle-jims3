/*
 * Created on Aug 1, 2006
 *
 */
package ui.supervision;

import java.util.Collection;
import java.util.Date;

import naming.PDCodeTableConstants;
import naming.UIConstants;

import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.security.SecurityUIHelper;

import messaging.contact.domintf.IName;
import mojo.km.utilities.DateUtil;

/**
 * @author jjose
 *  
 */
public class Casenote implements Comparable {

//    private static Collection casenoteStatusList;
//
//    private static Collection casenoteSubjectList;
//
//    private static Collection casenoteTypeList;
//
//    private static Collection collateralList;
//
    private static Collection conditionStatusList;
//
//    private static Collection contactMethodList;
//
//    private static Collection contactRelationshipList;
//
//    private static Collection generatedByList;
    private boolean allowDelete=false;
    
    private boolean allowEdit=false;

    private String[] associateIds;

    private String associates;

    private String caseId;

    private Date casenoteDate;

    private String casenoteDateAsString;

    private String casenoteId;

    private String casenoteStatus;

    private String casenoteStatusId;

    private String casenoteText;

    private String casenoteTime;

    private String casenoteType;

    private String casenoteTypeId;

    private String collateral;

    private String collateralId;
    
    private String collateralInfo;

    private String conditionDetails;
    
    private String conditionId;

    private String conditionName;

    private String conditionStatus;

    private String conditionStatusId;

    private String contactId;

    private String contactMethod;

    private String contactMethodId;

    private IName contactName;

    private String contactRelationship;

    private String contactRelationshipId;

    private String contextType;
    
    private Date createDate;
       
	private IName createdByName;
    private String createdById;
    private String migrateCreator;

    public String getCreatedById() {
		return createdById;
	}

	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}

	private String generatedBy;

    private String generatedById;
    private boolean isAutoSaved=false;

    private String[] subjectIds;

    private String subjects;

    private String superviseeId;
    

    public Casenote() {
        setAllLists();
    }

    public void clearAll() {

    	allowDelete=false;
    	allowEdit=false;
        casenoteDate = new Date();
        contactMethodId = "";
        contactMethod = "";
        associateIds = null;
        associates = "";
        subjectIds = null;
        subjects = "";
        collateralId = "";
        collateral = "";
        collateralInfo = "";
        casenoteText = "";
        createdByName = new Name();
        generatedById = "";
        generatedBy = "";
        casenoteTypeId = "";
        casenoteType = "";
        casenoteStatusId = "";
        casenoteStatus = "";
        conditionId = "";
        conditionName = "";
        conditionStatusId = "";
        conditionStatus = "";
        conditionDetails = "";
        contactId = "";
        contactName = new Name();
        contactRelationshipId = "";
        contactRelationship = "";
        superviseeId = "";
        contextType = "";
        casenoteId = "";
        caseId = "";
        isAutoSaved=false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object aCompareTo) {
        Date eventDateA = this.getCasenoteDate();
        Date eventDateB = ((Casenote) aCompareTo).getCasenoteDate();
        int result;
        if (eventDateA == null && eventDateB == null) {
            result = 0;
        } else if (eventDateA == null) {
            result = -1;
        } else if (eventDateB == null) {
            result = 1;
        } else {
            result = eventDateA.compareTo(eventDateB);
        }

        result = -result;
        return result;
    }

    public String[] getAssociateIds() {
        return associateIds;
    }

    public String getAssociates() {
        return associates;
    }

    public String getCaseId() {
        return caseId;
    }

    /**
     * @return Returns the casenoteDate.
     */
    public Date getCasenoteDate() {
        return casenoteDate;
    }

    public String getCasenoteDateAsString() {
        if (casenoteDate == null)
            return "";
        //return UIUtil.getStringFromDate(casenoteDate,null);
        return DateUtil.dateToString(casenoteDate, UIConstants.DATE_FMT_1);
    }

    /**
     * @return Returns the casenoteId.
     */
    public String getCasenoteId() {
        return casenoteId;
    }

    /**
     * @return Returns the casenoteStatus.
     */
    public String getCasenoteStatus() {
        return casenoteStatus;
    }

    /**
     * @return Returns the casenoteStatusId.
     */
    public String getCasenoteStatusId() {
        return casenoteStatusId;
    }

    /**
     * @return Returns the casenoteStatusList.
     */
//    public Collection getCasenoteStatusList() {
//        if (casenoteStatusList == null)
//            setAllLists();
//        return casenoteStatusList;
//    }

    /**
     * @return Returns the casenoteSubjectList.
     */
//    public Collection getCasenoteSubjectList() {
//        if (casenoteSubjectList == null) {
//            setAllLists();
//        }
//        return casenoteSubjectList;
//    }

    /**
     * @return Returns the casenoteText.
     */
    public String getCasenoteText() {
        return casenoteText;
    }

    /**
     * @return Returns the casenoteTime.
     */
    public String getCasenoteTime() {
        return casenoteTime;
    }

    /**
     * @return Returns the casenoteType.
     */
    public String getCasenoteType() {
        return casenoteType;
    }

    /**
     * @return Returns the casenoteTypeId.
     */
    public String getCasenoteTypeId() {
        return casenoteTypeId;
    }

    /**
     * @return Returns the casenoteTypeList.
     */
//    public Collection getCasenoteTypeList() {
//        if (casenoteTypeList == null)
//            setAllLists();
//        return casenoteTypeList;
//    }

    /**
     * @return Returns the collateral.
     */
    public String getCollateral() {
        return collateral;
    }

    /**
     * @return Returns the collateralId.
     */
    public String getCollateralId() {
        return collateralId;
    }
    /**
     * @return Returns the collateralInfo.
     */
    public String getCollateralInfo() {
        return collateralInfo;
    }

    /**
     * @return Returns the collateralList.
     */
//    public Collection getCollateralList() {
//        if (collateralList == null)
//            setAllLists();
//        return collateralList;
//    }

    /**
     * @return Returns the conditionDetails.
     */
    public String getConditionDetails() {
        return conditionDetails;
    }

    /**
     * @return Returns the conditionId.
     */
    public String getConditionId() {
        return conditionId;
    }

    /**
     * @return Returns the conditionName.
     */
    public String getConditionName() {
        return conditionName;
    }

    /**
     * @return Returns the conditionStatus.
     */
    public String getConditionStatus() {
        return conditionStatus;
    }

    /**
     * @return Returns the conditionStatusId.
     */
    public String getConditionStatusId() {
        return conditionStatusId;
    }

    /**
     * @return Returns the conditionStatusList.
     */
    public Collection getConditionStatusList() {
        if (conditionStatusList == null)
            setAllLists();
        return conditionStatusList;
    }

    /**
     * @return Returns the contactId.
     */
    public String getContactId() {
        return contactId;
    }

    /**
     * @return Returns the contactMethod.
     */
    public String getContactMethod() {
        return contactMethod;
    }

    /**
     * @return Returns the contactMethodId.
     */
    public String getContactMethodId() {
        return contactMethodId;
    }

    /**
     * @return Returns the contactMethodList.
     */
//    public Collection getContactMethodList() {
//        if (contactMethodList == null)
//            setAllLists();
//        return contactMethodList;
//    }

    /**
     * @return Returns the contactName.
     */
    public IName getContactName() {
    	if(createdByName==null){
			createdByName=new Name();
		}
        return contactName;
    }

    /**
     * @return Returns the contactRelationship.
     */
    public String getContactRelationship() {
        return contactRelationship;
    }

    /**
     * @return Returns the contactRelationshipId.
     */
    public String getContactRelationshipId() {
        return contactRelationshipId;
    }

    /**
     * @return Returns the contactRelationshipList.
     */
//    public Collection getContactRelationshipList() {
//        if (contactRelationshipList == null)
//            setAllLists();
//        return contactRelationshipList;
//    }

    /**
     * @return Returns the contextId.
     */
    public String getContextType() {
        return contextType;
    }

    /**
     * @return Returns the createdByName.
     */
    public IName getCreatedByName() {
    	if(createdByName==null){
			createdByName=new Name();
		}
        return createdByName;
    }

    /**
     * @return Returns the generatedBy.
     */
    public String getGeneratedBy() {
        return generatedBy;
    }

    /**
     * @return Returns the generatedById.
     */
    public String getGeneratedById() {
        return generatedById;
    }

    /**
     * @return Returns the generatedByList.
     */
//    public Collection getGeneratedByList() {
//        if (generatedByList == null)
//            setAllLists();
//        return generatedByList;
//    }

    /**
     * @return Returns the subjectIds.
     */
    public String[] getSubjectIds() {
        return subjectIds;
    }

    /**
     * @return Returns the subjects.
     */
    public String getSubjects() {
        return subjects;
    }

    /**
     * @return Returns the superviseeId.
     */
    public String getSuperviseeId() {
        return superviseeId;
    }
    
	/**
	 * @return Returns the allowDelete.
	 */
	public boolean isAllowDelete() {
		return allowDelete;
	}
	/**
	 * @return Returns the allowEdit.
	 */
	public boolean isAllowEdit() {
		return allowEdit;
	}
	/**
	 * @return Returns the isAutoSaved.
	 */
	public boolean isAutoSaved() {
		return isAutoSaved;
	}

    private void setAllLists() {
        //UICasenotesLoadCoadTables.getInstance().setCasenote(this);
//        String agencyId = SecurityUIHelper.getUserAgencyId();
//
//        contactMethodList = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.CONTACT_METHOD);
//        casenoteSubjectList = ComplexCodeTableHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.CASENOTE_SUBJECT);
//        casenoteTypeList = ComplexCodeTableHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.CASENOTE_TYPE);
//        collateralList = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.CONTACT_WITH);
//        generatedByList = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.HOW_GENERATED);
    }
	/**
	 * @param allowDelete The allowDelete to set.
	 */
	public void setAllowDelete(boolean allowDelete) {
		this.allowDelete = allowDelete;
	}
	/**
	 * @param allowEdit The allowEdit to set.
	 */
	public void setAllowEdit(boolean allowEdit) {
		this.allowEdit = allowEdit;
	}

    public void setAssociateIds(String[] associateIds, Collection collateralList) {
        this.associateIds = associateIds;
        if (associateIds != null && associateIds.length > 0) {
            StringBuffer myStrBuffer = new StringBuffer();
            for (int loopX = 0; loopX < associateIds.length; loopX++) {
                String associateItem = associateIds[loopX];
                if (collateralList != null && collateralList.size() > 0) {
                    String subjDesc = CodeHelper.getCodeDescriptionByCode(collateralList, associateItem);
                	if (myStrBuffer.length() > 0) {
                        myStrBuffer.append(", ");
                    }
                    myStrBuffer.append(subjDesc);
                }

            }
            associates = myStrBuffer.toString();
        }
    }

    public void setAssociates(String associate) {
        this.associates = associate;
    }
	/**
	 * @param isAutoSaved The isAutoSaved to set.
	 */
	public void setAutoSaved(boolean isAutoSaved) {
		this.isAutoSaved = isAutoSaved;
	}

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }
    
    /**
     * @param casenoteSubjectList
     *            The casenoteSubjectList to set.
     */
//    public void setCasenoteAssociateList(Collection casenoteSubjectList) {
//       Casenote.casenoteSubjectList = casenoteSubjectList;
//    }

    /**
     * @param casenoteDate
     *            The casenoteDate to set.
     */
    public void setCasenoteDate(Date casenoteDate) {
        this.casenoteDate = casenoteDate;
    }

    /**
     * @param casenoteDateAsString
     *            The casenoteDateAsString to set.
     */
    public void setCasenoteDateAsString(String aCasenoteDateAsString) {
        this.casenoteDate = DateUtil.stringToDate(aCasenoteDateAsString, UIConstants.DATE_FMT_1);
    }

    /**
     * @param casenoteId
     *            The casenoteId to set.
     */
    public void setCasenoteId(String casenoteId) {
        this.casenoteId = casenoteId;
    }

    /**
     * @param casenoteStatus
     *            The casenoteStatus to set.
     */
    public void setCasenoteStatus(String casenoteStatus) {
        this.casenoteStatus = casenoteStatus;
    }

    /**
     * @param casenoteStatusId
     *            The casenoteStatusId to set.
     */
//    public void setCasenoteStatusId(String casenoteStatusId, Collection casenoteStatusList) {
//        this.casenoteStatusId = casenoteStatusId;
//        if (this.casenoteStatusId == null || this.casenoteStatusId.equals("")) {
//            this.casenoteStatus = "";
//            return;
//        }
//
//        if (casenoteStatusList != null && casenoteStatusList.size() > 0) {
//            this.casenoteStatus = CodeHelper.getCodeDescriptionByCode(casenoteStatusList, this.casenoteStatusId);
//        }
//    }
    public void setCasenoteStatusId(String casenoteStatusId) {
        this.casenoteStatusId = casenoteStatusId;
    }
    /**
     * @param casenoteStatusList
     *            The casenoteStatusList to set.
     */
//    public void setCasenoteStatusList(Collection aCasenoteStatusList) {
//        casenoteStatusList = aCasenoteStatusList;
//    }

    /**
     * @param casenoteSubjectList
     *            The casenoteSubjectList to set.
     */
//    public void setCasenoteSubjectList(Collection casenoteSubjectList) {
//        Casenote.casenoteSubjectList = casenoteSubjectList;
//    }

    /**
     * @param casenoteText
     *            The casenoteText to set.
     */
    public void setCasenoteText(String casenoteText) {
        this.casenoteText = casenoteText;
    }

    /**
     * @param casenoteTime
     *            The casenoteTime to set.
     */
    public void setCasenoteTime(String casenoteTime) {
        this.casenoteTime = casenoteTime;
    }

    /**
     * @param casenoteType
     *            The casenoteType to set.
     */
    public void setCasenoteType(String casenoteType) {
        this.casenoteType = casenoteType;
    }

    /**
     * @param casenoteTypeId
     *            The casenoteTypeId to set.
     */
    public void setCasenoteTypeId(String casenoteTypeId, Collection typeList) {
        this.casenoteTypeId = casenoteTypeId;
        if (this.casenoteTypeId == null || this.casenoteTypeId.equals("")) {
            this.casenoteType = "";
            return;
        }

        if (typeList != null && typeList.size() > 0) {
            this.casenoteType = CodeHelper.getCodeDescriptionByCode(typeList, this.casenoteTypeId);
        }
    }

    /**
     * @param casenoteTypeList
     *            The casenoteTypeList to set.
     */
//    public void setCasenoteTypeList(Collection aCasenoteTypeList) {
//        casenoteTypeList = aCasenoteTypeList;
//    }

    /**
     * @param collateral
     *            The collateral to set.
     */
    public void setCollateral(String collateral) {
        this.collateral = collateral;
    }

    /**
     * @param collateralId
     *            The collateralId to set.
     */
    public void setCollateralId(String collateralId) {
        this.collateralId = collateralId;
        if (this.collateralId == null || this.collateralId.equals("")) {
            this.collateral = "";
            return;
        }

//        if (getCollateralList() != null && getCollateralList().size() > 0) {
//            this.collateral = CodeHelper.getCodeDescriptionByCode(getCollateralList(), this.collateralId);
//        }
    }
    /**
     * @param collateralInfo The collateralInfo to set.
     */
    public void setCollateralInfo(String collateralInfo) {
        this.collateralInfo = collateralInfo;
    }

    /**
     * @param collateralList
     *            The collateralList to set.
     */
//    public void setCollateralList(Collection aCollateralList) {
//        collateralList = aCollateralList;
//    }

    /**
     * @param conditionDetails
     *            The conditionDetails to set.
     */
    public void setConditionDetails(String conditionDetails) {
        this.conditionDetails = conditionDetails;
    }

    /**
     * @param conditionId
     *            The conditionId to set.
     */
    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    /**
     * @param conditionName
     *            The conditionName to set.
     */
    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    /**
     * @param conditionStatus
     *            The conditionStatus to set.
     */
    public void setConditionStatus(String conditionStatus) {
        this.conditionStatus = conditionStatus;
    }

    /**
     * @param conditionStatusId
     *            The conditionStatusId to set.
     */
    public void setConditionStatusId(String conditionStatusId) {
        this.conditionStatusId = conditionStatusId;
        if (this.conditionStatusId == null || this.conditionStatusId.equals("")) {
            this.conditionStatus = "";
            return;
        }

        if (getConditionStatusList() != null && getConditionStatusList().size() > 0) {
            this.conditionStatus = CodeHelper
                    .getCodeDescriptionByCode(getConditionStatusList(), this.conditionStatusId);
        }
    }

    /**
     * @param conditionStatusList
     *            The conditionStatusList to set.
     */
//    public void setConditionStatusList(Collection aConditionStatusList) {
//        conditionStatusList = aConditionStatusList;
//    }

    /**
     * @param contactId
     *            The contactId to set.
     */
    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    /**
     * @param contactMethod
     *            The contactMethod to set.
     */
    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }

    /**
     * @param contactMethodId
     *            The contactMethodId to set.
     */
    public void setContactMethodId(String contactMethodId, Collection contactMethodList) {
        this.contactMethodId = contactMethodId;
        if (this.contactMethodId == null || this.contactMethodId.equals("")) {
            this.contactMethod = "";
            return;
        }

        if (contactMethodList != null && contactMethodList.size() > 0) {
            this.contactMethod = CodeHelper.getCodeDescriptionByCode(contactMethodList, this.contactMethodId);
        }
    }

    /**
     * @param contactMethodList
     *            The contactMethodList to set.
     */
//    public void setContactMethodList(Collection aContactMethodList) {
//        contactMethodList = aContactMethodList;
//    }

    /**
     * @param contactName
     *            The contactName to set.
     */
    public void setContactName(IName contactName) {
        this.contactName = contactName;
    }

    /**
     * @param contactRelationship
     *            The contactRelationship to set.
     */
    public void setContactRelationship(String contactRelationship) {
        this.contactRelationship = contactRelationship;
    }

    /**
     * @param contactRelationshipId
     *            The contactRelationshipId to set.
     */
    public void setContactRelationshipId(String contactRelationshipId) {
        this.contactRelationshipId = contactRelationshipId;
        if (this.contactRelationshipId == null || this.contactRelationshipId.equals("")) {
            this.contactRelationship = "";
            return;
        }

//        if (getContactRelationshipList() != null && getContactRelationshipList().size() > 0) {
//            this.contactRelationship = CodeHelper.getCodeDescriptionByCode(getContactRelationshipList(),
//                    this.contactRelationshipId);
//        }
    }

    /**
     * @param contactRelationshipList
     *            The contactRelationshipList to set.
     */
//    public void setContactRelationshipList(Collection aContactRelationshipList) {
//        contactRelationshipList = aContactRelationshipList;
//    }

    /**
     * @param contextId
     *            The contextId to set.
     */
    public void setContextType(String contextId) {
        this.contextType = contextId;
    }

    /**
     * @param createdByName
     *            The createdByName to set.
     */
    public void setCreatedByName(IName createdByName) {
        this.createdByName = createdByName;
    }

    /**
     * @param generatedBy
     *            The generatedBy to set.
     */
    public void setGeneratedBy(String generatedBy) {
        this.generatedBy = generatedBy;
    }

    /**
     * @param generatedById
     *            The generatedById to set.
     */
    public void setGeneratedById(String generatedById) {
        this.generatedById = generatedById;
        if (this.generatedById == null || this.generatedById.equals("")) {
            this.generatedBy = "";
            return;
        }

//        if (getGeneratedByList() != null && getGeneratedByList().size() > 0) {
//            this.generatedBy = CodeHelper.getCodeDescriptionByCode(getGeneratedByList(), this.generatedById);
//        }
    }

    /**
     * @param generatedByList
     *            The generatedByList to set.
     */
//    public void setGeneratedByList(Collection aGeneratedByList) {
//        generatedByList = aGeneratedByList;
//    }

    /**
     * @param subjectIds
     *            The subjectIds to set.
     */
    public void setSubjectIds(String[] subjectIds, Collection cnSubjCodes) {

        this.subjectIds = subjectIds;
        if (subjectIds != null && subjectIds.length > 0) {
            StringBuffer myStrBuffer = new StringBuffer();
            for (int loopX = 0; loopX < subjectIds.length; loopX++) {
                String subjectItem = subjectIds[loopX];

                if (cnSubjCodes != null && cnSubjCodes.size() > 0) {
                    String subjDesc = CodeHelper.getCodeDescriptionByCode(cnSubjCodes, subjectItem);
                    if (myStrBuffer.length() > 0) {
                        myStrBuffer.append(", ");
                    }
                    myStrBuffer.append(subjDesc);
                }

            }
            subjects = myStrBuffer.toString();
        }
    }

    /**
     * @param subjects
     *            The subjects to set.
     */
    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    /**
     * @param superviseeId
     *            The superviseeId to set.
     */
    public void setSuperviseeId(String superviseeId) {
        this.superviseeId = superviseeId;
    }

	public String getMigrateCreator() {
		return migrateCreator;
	}

	public void setMigrateCreator(String migrateCreator) {
		this.migrateCreator = migrateCreator;
	}
	
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
    
}// END CLASS

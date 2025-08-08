/*
 * Created on Nov 18, 2005
 */
package ui.supervision.supervisionorder.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionoptions.reply.VariableElementTypeResponseEvent;
import messaging.supervisionorder.SupervisionOrderConditionEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;
import messaging.task.domintf.ITask;
import messaging.task.domintf.ITaskState;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.SupervisionConstants;
import naming.TaskWorkflowConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.UIUtil;
import ui.security.SecurityUIHelper;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.supervisionorder.SupervisionOrderListHelper;
import ui.supervision.supervisionorder.UISupervisionOrderHelper;
import ui.task.ITaskRestorable;

/**
 * @author dgibler
 */
/**
 * @author dwilliamson
 *
 */
public class SupervisionOrderForm extends ActionForm implements ITaskRestorable{

    public final static String NON_STANDARD = "Non-Standard";
	public final static String STANDARD = "Standard";
	private String action = "";
	//	Fields
    private String activityInd;
	private String agencyId;
	private Collection allOrderTitleList;
	private Date caseFileDate;
	private String casenotes;
	private String caseNum;

    private Date caseSupervisionBeginDate; //dag 01/19/07
    private Date caseSupervisionEndDate;//dag 01/19/07
    
    private String cdi;
    private String comments;
    private boolean compared;
    private String compareToPrevousVersion;
    private String conditionId;

    // for PASO Printing functionality --
    // Kiran

    private String conditionLiteral;
    private String conditionLiteralPreview;
    private String conditionName;
    private String conditionNotes;
    
    private Collection conditionResultList;
    private Collection conditionSelectedList;
    private String confinementLengthDays;//dag 01/19/07
    private String confinementLengthMonths;//dag 01/19/07
    private String confinementLengthYears;//dag 01/19/07
    private String connectionId;
    private Collection connectionList; //Drop Down Lists
    private String courtCategory; //Added by Kiran
    private String courtId;
    private String courtNum;
    private String currentCourtCategory;
    private String currentCourtId;
    private String currentCourtNum;
    private SupervisionOrderDetailResponseEvent currentOrder;
    private int currImpactedOrderIndex;
    private String defendantId;
    private String defendantSignature;
    private String deferredSupervisionLength;
    private boolean delete = false;
    private Collection detailDictionary;
    
    private HashMap detailDictionaryNameIdHashMap;

    private boolean disallowVersionTypeChange;
    
    private Collection displayConditionList;
    
    public String dispositionType="";

    private String dispositionTypeId;//dag 01/19/07

    public Collection dispositionTypes;

    private String fineAmountTotal;

    private String group1;

    private String group1Id;

    private Collection group1List; //Drop Down Lists

    private String group1Name;

    private String group2;
    
    private String group2Id;

    private String group3;

    private String group3Id;

    private String groupId;

    private Collection groups;

    private SupervisionOrderDetailResponseEvent impactedOrder;

    private Collection impactedOrderList;

    private Collection inputConditionList;

    private boolean isPretrialInterventionOrder;

    private boolean isMigratedOrder;

    private String jailTime;
    
    private String judgeFirstName;

    private String judgeLastName;

    private String judgeSelectId;

    private Collection judgeSelectList; //Drop Down Lists

    private String juvCourtId;

    private Collection juvCourts;

    private Date juvSupervisionBeginDate;
    
    private String juvSupervisionLengthDays; 
    
    private String juvSupervisionLengthMonths;

    private String juvSupervisionLengthYears;  

    private boolean likeConditionInd = false;

    private boolean limitedSupervisonPeriod = false;

    private boolean listsSet;

    private String magFirstName;

    private String magistrateSelectId;

    private Collection magistrateSelectList; //Drop Down Lists

    private String magLastName;

    private CSCDSupervisionStaffResponseEvent myStaffPos=null;

    private String name;

    private String offense;

    private String offenseDesc;
    
    private String offenseId;
    
    private String offenseLevel;

    private boolean order;

    private Date orderActivateDate;

    private int orderChainNum;

    private Set orderConditionSet = new HashSet();

    private Date orderFileDate;

    private String orderId;

    private String orderStatus;

    private String orderStatusId;

    private Collection orderStatusList; //Drop Down Lists

    private String orderTitle;

    private String orderTitleId;

    private Collection orderTitleList; //Drop Down Lists -- will also be used

    //All the order titles for the Agency irrespective of Court Category. check
    // GLTEMPLATES TABLE
    private String orderVersion;

    private Collection orderVersionList;

    private String originalJudgeFirstName;

    private String originalJudgeLastName;

    // Defect #JIMS200040159 - Modified Order Title Missing
    private String originalOrderTitleId;

    private boolean outOfCountyCase=false;
   
    private Collection outputConditionList;
    
    private String partyName="";

	private String plea;   // this is a description field

    private String pleaId;

    private Collection pleas;

    private String presentedBy;

    private String presentedByFirstName;

    private String presentedById;

    private String presentedByLastName;

    private Collection presentedByList; //Drop Down Lists

    private Collection previewConditionSelectedList;

    private SupervisionOrderDetailResponseEvent previousOrderVersion;

    private String primaryCaseOrderKey;

    private String printAction = "";
    
    private String printedName;
    
    private String printedOffenseDesc;

    private boolean printSpanish = false;
    
	private String probationEndDate;

    private String probationStartDate;

    private String reason;
    
    private String reasonId;

    private Map referenceVariableMap;

    private Collection referenceVariables;

    private Collection reinstateReasonList; //Drop Down Lists

    private String resequencedOrderValue;

    private String scenario;

    private boolean searchSuperCondPerformed = false;

    private String secondaryAction = "";

    private String[] selectedConditionIds;  //cws 07/22/09

    private String selectedOrderId; //rry 07/22/2008

    private SupervisionOrderDetailResponseEvent selectedOrderVersion;

    private boolean showOrigJudgeInput = false;
    
    private String selectedValue = "";

    private Date signedByDefendantDate;
    
    private Date signedByJudgeDate;

    private Date signedDate;

    private String specialCourt;   // this is a description field

    private String specialCourtCd;

    private Collection specialCourtCds;

    private String spn;

    private boolean standard = false;

    private String standardSearchCriteria; //it can be true, false, or

    private Date statusChangeDate;

    private Collection subGroups;

    private String suggestedOrderId;

    private Collection suggestedOrderList;

    private String summaryOfChanges;

    private Date supervisionBeginDate;

    private Date supervisionEndDate;
    
    private String supervisionLengthDays;//dag 01/19/07
    
    private String supervisionLengthMonths;//dag 01/19/07

    private String supervisionLengthYears;//dag 01/19/07

    private String supervisionOrderId;

    private Date supOrderRevisionDate;

    // unspecified.

    private String taskCaseNum;

    private String taskCdi;

    private String taskId;

    private String taskOrderId;

    //for PASO Printing
    private String templateName;
    
    private boolean update = false;
    
    private boolean userClo=false;
    
    private boolean userCso=false;
    
    private String userId;

    private Map values = new HashMap();
    
    private int versionNum;

    private String versionType;

    private boolean versionTypeChangeAllowed;

    private Collection versionTypeDropDownList; //Drop down Lists - Excludes

    private String versionTypeId;

    private Collection versionTypeList; //Drop Down Lists
    
    private String whoSignedOrder;

    private Collection withdrawReasonList; //Drop Down Lists

    // Original

    private String workflowFrom;

    private String workflowInd;

    public SupervisionOrderForm() {
        clear();
        //UISupervisionOrderLoadCodeTables.getInstance().setSupervisionOrderForm(this);
        boolean sort = true;
		judgeSelectList = SupervisionOrderListHelper.getJudgeList();
		magistrateSelectList = CodeHelper.getMagistrateCodes(sort);
		orderStatusList = CodeHelper.getCodes(PDCodeTableConstants.ORDER_STATUS, sort);
		versionTypeList = CodeHelper.getCodes(PDCodeTableConstants.VERSION_TYPE, sort);
		versionTypeDropDownList = this.createVersionTypeDropDown();
	    withdrawReasonList = CodeHelper.getCodes(PDCodeTableConstants.WITHDRAW_REASON, sort);
    }

    /**
     * @param collection
     */
    public void addConditionSelectedList(ConditionDetailResponseEvent conditionEvent) {
        if (conditionEvent != null) {
            ArrayList tempColl = new ArrayList();
            tempColl.add(conditionEvent);
            UISupervisionOrderHelper.setPreviewSample(this, tempColl, true);
        }

        conditionSelectedList.add(conditionEvent);

    }
    
    /**
     * @param map
     */
    public void addOrderCondition(SupervisionOrderConditionEvent conditionOrderEvent) {
        orderConditionSet.add(conditionOrderEvent);
    }
    
    public void clear() {
        // Never clear the action
        //		action = "";
        values = new HashMap();
        resequencedOrderValue = "";
        printAction = "";
        caseFileDate = null;
        casenotes = "";
        caseNum = "";
        caseSupervisionBeginDate=null;
        caseSupervisionEndDate=null;

        cdi = "";
        outOfCountyCase=false;
        comments = "";
        compareToPrevousVersion = "";
        conditionId = "";
        conditionLiteral = "";
        conditionLiteralPreview = "";
        conditionName = "";
        conditionNotes = "";
        //conditionStatus = "";
        //	conditionStatusId = "";
        confinementLengthDays="";
        confinementLengthMonths="";
        confinementLengthYears="";
        connectionId = "";
        courtId = "";
        courtNum = "";
        courtCategory = "";
        currentOrder = null;
        currImpactedOrderIndex = 0;
        defendantId = "";
        defendantSignature = "";
        deferredSupervisionLength = "";
        dispositionTypeId="";
        fineAmountTotal = "";
        group1 = "";
        group2 = "";
        group3 = "";
        groupId = "";
        group1Id = "";
        group2Id = "";
        group3Id = "";
        groupId = "";
        impactedOrder = null;
        jailTime = "";
        judgeFirstName = "";
        judgeLastName = "";
        judgeSelectId = "";
        juvCourtId = "";
        juvSupervisionBeginDate = null;
        juvSupervisionLengthDays="";
        juvSupervisionLengthMonths="";
        juvSupervisionLengthYears="";

        magFirstName = "";
        magLastName = "";
        magistrateSelectId = "";
        name = "";
        //name = new Name();
        offense = "";
        offenseId = "";
        offenseDesc = "";
        offenseLevel = null;
        orderActivateDate = null;
        orderFileDate = null;
        orderId = "";
        orderStatus = "";
        orderStatusId = "";
        orderStatus = "";
        orderTitle = "";
        orderTitleId = "";
        orderVersion = "";
        orderChainNum=1;
        originalJudgeFirstName="";
        originalJudgeLastName="";
        partyName="";
        plea = "";
        pleaId = "";
        presentedById = "";
        presentedBy = "";
        presentedByFirstName = "";
        presentedByLastName = "";
        presentedByList = null; /*
                                 * need to clear out since list changes based on
                                 * person signed on
                                 */
        previousOrderVersion = null;
        printAction = "";
        printedOffenseDesc = "";
        printedName = "";
        probationEndDate = "";
        probationStartDate = "";
        reason = "";
        reasonId = "";
        //primaryCaseOrderKey = ""; Do not clear out!
        referenceVariableMap = new HashMap();
        searchSuperCondPerformed = false;
        secondaryAction = "";
        selectedOrderVersion = null;
        selectedValue = "";
        showOrigJudgeInput = false;
        signedByDefendantDate = null;
        signedByJudgeDate = null;
        signedDate = null;
        specialCourtCd = "";
        spn = "";
        statusChangeDate = null;
        //suggestedOrderId = ""; Do not clear out!
        summaryOfChanges = "";
        supervisionBeginDate = null;
        supervisionEndDate = null; 
        supervisionLengthDays="";
        supervisionLengthMonths="";
        supervisionLengthYears="";
        supOrderRevisionDate = null;
        userId = "";
        versionNum = 0;
        versionTypeId = "";
        versionType = "";
        whoSignedOrder = "";
        workflowFrom = null;
        this.supervisionOrderId = "";

        // booleans
         compared = false;
        isPretrialInterventionOrder = false;
        isMigratedOrder = false;
        order = false;
        printSpanish = false;
        likeConditionInd = false;
        //	limitedSupervisonPeriod = false;
        standard = false;
        standardSearchCriteria = null;
        update = false;
        delete = false;
        versionTypeChangeAllowed = false;
        
        //	collections
        conditionResultList = new ArrayList();
        conditionSelectedList = new ArrayList();
        groups = new ArrayList();
        group1List = new ArrayList();
        impactedOrderList = new ArrayList();
        inputConditionList = new ArrayList();
        orderVersionList = new ArrayList();
        outputConditionList = new ArrayList();
        previewConditionSelectedList = new ArrayList();
        suggestedOrderList = new ArrayList();

    }
    
    public void clearDeleteBox(){
    	if(this.getConditionSelectedList()!=null && conditionSelectedList.size()>0){
    		Iterator iterCond=getConditionSelectedList().iterator();
    		while(iterCond.hasNext()){
    			ConditionDetailResponseEvent myResp=(ConditionDetailResponseEvent)iterCond.next();
    			myResp.setDeleted(false);
    		}
    	}
    }
    
    public void clearValueMap() {
        values = new HashMap();
    }
    
    /**
	 * @return
	 */
	private Collection createVersionTypeDropDown()
	{
		Collection aList = new ArrayList();

		Iterator iter = versionTypeList.iterator();
		while (iter.hasNext())
		{
			CodeResponseEvent cre = (CodeResponseEvent) iter.next();
			String version_type = cre.getCode();
			if (!version_type.equals(PDCodeTableConstants.VERSION_TYPE_ID_ORIGINAL))		
			{
				if ((isOutOfCountyCase() && 
						!version_type.equals(PDCodeTableConstants.VERSION_TYPE_ID_MODIFIED))
					|| !isOutOfCountyCase())
				{
					aList.add(cre);
				}							
			}
		}
		return aList;
	}
    
    /**
     * @param string
     * @return
     */
    /* private Collection filterSupervisionStaff(String courtNum, Collection presentedByList) {
        Iterator iter = presentedByList.iterator();
        SupervisionStaffResponseEvent sre = null;
        Collection filteredStaff = new ArrayList();
        final String CLO = "CLO";
        final String ALL = "ALL";
        StringBuffer orderCourtNum = new StringBuffer(courtNum);
        if (courtNum != null && courtNum.length() < 3) {
            while (orderCourtNum.length() < 3) {
                orderCourtNum.insert(0, "0");
            }
        }
        while (iter.hasNext()) {
            sre = (SupervisionStaffResponseEvent) iter.next();
            if (sre.getCourtNum() == null
                    || (CLO.equals(sre.getUnit()) && (ALL.equals(sre.getCourtNum()) || sre.getCourtNum().equals(
                            orderCourtNum.toString())))) {
                filteredStaff.add(sre);
            }
        }
        return filteredStaff;
    } */
    
    /**
     * @return
     */
    public String getAction() {
        return action;
    }
    
    /**
     * @return Returns the activityInd.
     */
    public String getActivityInd()
    {
        return activityInd;
    }
    
    /**
     * @return
     */
    public String getAgencyId() {
        if (agencyId == null || agencyId.equals("")) {
            agencyId = SecurityUIHelper.getUserAgencyId();
        }
        return agencyId;
    }
    
	/**
     * @return
     */
    public Collection getAllOrderTitleList() {
        if(allOrderTitleList == null)
        {
            allOrderTitleList = CodeHelper.getPrintTemplatesDetails(SecurityUIHelper.getUserAgencyId());   
        }
            
        return allOrderTitleList;
    }
	
	/**
     * @return
     */
    public Date getCaseFileDate() {
        return caseFileDate;
    }
	/**
     * @param event
     */
    public String getCaseId() {
        StringBuffer sb = new StringBuffer(getCdi());
        sb.append(getCaseNum());
        return sb.toString();
    }

    /**
     * @return
     */
    public String getCasenotes() {
        return casenotes;
    }

    /**
     * @return
     */
    public String getCaseNum() {
        return caseNum;
    }

    /**
     * @return Returns the caseSupervisionBeginDate.
     */
    public Date getCaseSupervisionBeginDate() {
        return caseSupervisionBeginDate;
    }

    /**
     * @return
     */
    public String getCaseSupervisionBeginDateAsString() {
        if (caseSupervisionBeginDate == null) {
            return "";
        } else {
            return DateUtil.dateToString(caseSupervisionBeginDate, UIConstants.DATE_FMT_1);
        }
    }

    /**
     * @return Returns the caseSupervisionEndDate.
     */
    public Date getCaseSupervisionEndDate() {
        return caseSupervisionEndDate;
    }

    /**
     * @return
     */
    public String getCaseSupervisionEndDateAsString() {
        if (caseSupervisionEndDate == null) {
            return "";
        } else {
            return DateUtil.dateToString(caseSupervisionEndDate, UIConstants.DATE_FMT_1);
        }
    }

    /**
     * @return
     */
    public String getCdi() {
        return cdi;
    }

    public String getComments() {
		return comments;
	}

    /**
     * @return
     */
    public String getCompareToPrevousVersion() {
        return compareToPrevousVersion;
    }

    /**
     * @return
     */
    public String getConditionId() {
        return conditionId;
    }

    /**
     * @return
     */
    public String getConditionLiteral() {
        return conditionLiteral;
    }

    /*
     * This is to get different Label display depending on which agency the user
     * belongs to
     */
    public String getConditionLiteralCaption() {
        this.getAgencyId();
        if (agencyId.equalsIgnoreCase(UIConstants.JUV)) {
            return "prompt.literal";
        }

        return "prompt.condition";
    }
    
    /**
     * @return
     */
    public String getConditionLiteralPreview() {
        return conditionLiteralPreview;
    }
    /**
     * @return
     */
    public String getConditionName() {
        return conditionName;
    }

    /**
     * @return
     */
    public String getConditionNotes() {
        return conditionNotes;
    }

    /**
     * @return
     */
    public Collection getConditionResultList() {
        return conditionResultList;
    }

    /**
     * @return
     */
    public Collection getConditionSelectedList() {
        return conditionSelectedList;
    }

    /**
     * @return Returns the confinementLengthDays.
     */
    public String getConfinementLengthDays() {
        return confinementLengthDays;
    }

    /**
     * @return Returns the confinementLengthMonths.
     */
    public String getConfinementLengthMonths() {
        return confinementLengthMonths;
    }

    /**
     * @return Returns the confinementLengthYears.
     */
    public String getConfinementLengthYears() {
        return confinementLengthYears;
    }

    /**
     * @return
     */
    public String getConnectionId() {
        return connectionId;
    }

    /**
     * @return
     */
    public Collection getConnectionList() {
        return connectionList;
    }

    /**
     * @return
     */
    public String getCourtCategory() {
        return courtCategory;
    }

    /**
     * @return
     */
    public String getCourtId() {
        return courtId;
    }
    /**
     * @return
     */
    public String getCourtNum() {
        return courtNum;
    }
    /**
     * @param primaryCaseOrderKey
     * @return
     */
    public String getCriminalCaseId(String primaryCaseOrderKey) {
    	String criminalCaseId = primaryCaseOrderKey;
    	if (primaryCaseOrderKey != null && primaryCaseOrderKey.length() > 15){
    		criminalCaseId = primaryCaseOrderKey.substring(0, 15);
    	}
        return criminalCaseId;
    }
    /**
     * @return
     */
    public SupervisionOrderDetailResponseEvent getCurrentOrder() {
        return currentOrder;
    }

    /**
     * @return
     */
    public int getCurrImpactedOrderIndex() {
        return currImpactedOrderIndex;
    }

    /**
     * @return
     */
    public String getDefendantId() {
        return defendantId;
    }

    /**
     * @return
     */
    public String getDefendantSignature() {
        return defendantSignature;
    }

    /**
     * @return
     */
    public String getDefendantSignedDateAsString() {
        if (signedByDefendantDate == null) {
            return "";
        } else {
            return DateUtil.dateToString(signedByDefendantDate, UIConstants.DATE_FMT_1);
        }
    }
    public String getJudgeSignedDateAsString() {
        if (signedByJudgeDate == null) {
            return "";
        } else {
            return DateUtil.dateToString(signedByJudgeDate, UIConstants.DATE_FMT_1);
        }
    }

    /**
     * @return
     */
    public String getDeferredSupervisionLength() {
        return deferredSupervisionLength;
    }

    /**
     * @return
     */
    public Collection getDetailDictionary() {
        if (detailDictionary == null || detailDictionary.size() == 0) {
            // get detail dictionary
            detailDictionary = UISupervisionOptionHelper.fetchDetailDictionary(getAgencyId());
        }
        return detailDictionary;
    }

    /**
     * @return
     */
    public HashMap getDetailDictionaryNameIdHashMap() {
        return detailDictionaryNameIdHashMap;
    }

    public Collection getDisplayConditionList() {
		return displayConditionList;
	}

    /**
	 * @return Returns the dispositionType.
	 */
	public String getDispositionType() {
		if(dispositionTypeId==null || dispositionTypeId.equals("")){
			return "";
		}
		Collection disps=getDispositionTypes();
		return CodeHelper.getCodeDescriptionByCode(disps,dispositionTypeId);
	}

    /**
     * @return Returns the dispositionTypeId.
     */
    public String getDispositionTypeId() {
        return dispositionTypeId;
    }

    /**
	 * @return Returns the dispositionTypes.
	 */
	public Collection getDispositionTypes() {
		if(dispositionTypes==null){
			dispositionTypes=CodeHelper.getCodes(PDCodeTableConstants.SUPERVISION_DISPOSITION_SUBSET,true);
		}
		if(dispositionTypes==null){
			return new ArrayList();
		}
		return dispositionTypes;
	}

    /**
     * @return
     */
    public String getFineAmountTotal() {
        return fineAmountTotal;
    }

    /**
     * @return
     */
    public String getGroup1() {
        return group1;
    }
    /*
     * This is to get different Label display depending on which agency the user
     * belongs to
     */
    public String getGroup1Caption() {
        this.getAgencyId();
        if (agencyId.equalsIgnoreCase(UIConstants.JUV)) {
            return "prompt.category";
        } else if (agencyId.equalsIgnoreCase(UIConstants.PTR)) {
            return "prompt.conditionType";
        }

        return "prompt.group1";
    }

    /**
     * @return
     */
    public String getGroup1Id() {
        return group1Id;
    }

    /**
     * @return
     */
    public Collection getGroup1List() {
        if (group1List == null || group1List.size() == 0) {
            // get groups
            group1List = UISupervisionOptionHelper.fetchGroups(getAgencyId());
        }
        return group1List;
    }

    /**
     * @return
     */
    public String getGroup1Name() {
        return group1Name;
    }

    /**
     * @return
     */
    public String getGroup2() {
        return group2;
    }

    public String getGroup2Caption() {
        this.getAgencyId();
        if (agencyId.equalsIgnoreCase(UIConstants.JUV)) {
            return "prompt.type";
        } else if (agencyId.equalsIgnoreCase(UIConstants.PTR)) {
            return "prompt.conditionSubType";
        }

        return "prompt.group2";
    }

    /**
     * @return
     */
    public String getGroup2Id() {
        return group2Id;
    }

    /**
     * @return
     */
    public String getGroup3() {
        return group3;
    }

    public String getGroup3Caption() {
        this.getAgencyId();
        if (agencyId.equalsIgnoreCase(UIConstants.JUV)) {
            return "prompt.subtype";
        } else if (agencyId.equalsIgnoreCase(UIConstants.PTR)) {
            return "prompt.subTypeDetail";
        }

        return "prompt.group3";
    }

    /**
     * @return
     */
    public String getGroup3Id() {
        return group3Id;
    }

    /**
     * @return
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * @return
     */
    public Collection getGroups() {
        return groups;
    }

    /**
     * @return
     */
    public SupervisionOrderDetailResponseEvent getImpactedOrder() {
        return impactedOrder;
    }

    /**
     * @return
     */
    public Collection getImpactedOrderList() {
        return impactedOrderList;
    }

    public Collection getInputConditionList() {
		return inputConditionList;
	}

    /**
     * @return Returns the isPretrialInterventionOrder.
     */
    public boolean getIsPretrialInterventionOrder() {
        return isPretrialInterventionOrder;
    }

    public boolean getIsMigratedOrder() {
		return isMigratedOrder;
	}

    /**
     * @return
     */
    public String getJailTime() {
        return jailTime;
    }

    /**
     * @return
     */
    public String getJudge() {
        return SupervisionOrderListHelper.getJudge(this.judgeSelectList, this.judgeSelectId);
    }

    /**
     * @return
     */
    public String getJudgeFirstName() {
        return judgeFirstName;
    }

    /**
     * @return
     */
    public String getJudgeLastName() {
        return judgeLastName;
    }

    /**
     * @return
     */
    public String getJudgeSelectId() {
        return judgeSelectId;
    }

    /**
     * @return
     */
    public Collection getJudgeSelectList() {
        return judgeSelectList;
    }

    /**
	 * @return the juvCourtId
	 */
	public String getJuvCourtId() {
		return juvCourtId;
	}

    /**
	 * @return the juvCourts
	 */
	public Collection getJuvCourts() {
		return juvCourts;
	}

    /**
     * @return Returns the juvSupervisionBeginDate.
     */
    public Date getJuvSupervisionBeginDate() {
        return juvSupervisionBeginDate;
    }

    /**
     * @return
     */
    public String getJuvSupervisionBeginDateAsString() {
        if (juvSupervisionBeginDate == null) {
            return "";
        } else {
            return DateUtil.dateToString(juvSupervisionBeginDate, UIConstants.DATE_FMT_1);
        }
    }
    /**
	 * @return the juvSupervisionLengthDays
	 */
	public String getJuvSupervisionLengthDays() {
		return juvSupervisionLengthDays;
	}

	/**
	 * @return the juvSupervisionLengthMonths
	 */
	public String getJuvSupervisionLengthMonths() {
		return juvSupervisionLengthMonths;
	}

	/**
	 * @return the juvSupervisionLengthYears
	 */
	public String getJuvSupervisionLengthYears() {
		return juvSupervisionLengthYears;
	}

	public String getLimitedSupervisonPeriodCaption() {
        this.getAgencyId();
        if (agencyId.equalsIgnoreCase(UIConstants.PTR)) {
            return "prompt.limitedSupervisionPeriodOrder";
        } else if (agencyId.equalsIgnoreCase(UIConstants.CSC)) {
            return "prompt.pretrialInterventionDisposition";
        }

        return "prompt.pretrialInterventionDisposition";
    }

    /**
     * @return
     */
    public String getMagFirstName() {
        return magFirstName;
    }

    /**
     * @return
     */
    public String getMagistrate() {
        return SupervisionOrderListHelper.getMagistrate(this.magistrateSelectList, this.magistrateSelectId);
    }

    /**
     * @return
     */
    public String getMagistrateSelectId() {
        return magistrateSelectId;
    }

    /**
     * @return
     */
    public Collection getMagistrateSelectList() {
        return magistrateSelectList;
    }

    /**
     * @return
     */
    public String getMagLastName() {
        return magLastName;
    }

    /**
	 * @return Returns the myStaffPos.
	 */
	public CSCDSupervisionStaffResponseEvent getMyStaffPos() {
		
		return myStaffPos;
	}

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @return
     */
    public String getOffense() {
		String descr = "";
		if (offenseId != null && !offenseId.equals("")){
		    String tempOffenseId = UIUtil.stripZeroes(offenseId);
		    if (tempOffenseId != null && !tempOffenseId.equals(""))
		    {
		        descr = CodeHelper.getOffenseCodeDescription(tempOffenseId);
		    }
		}
		setOffense(descr);
		setOffenseDesc(descr);
		return descr;
    }

    /**
     * @return Returns the offenseDesc.
     */
    public String getOffenseDesc() {
        return offenseDesc;
    }

     /**
     * @return
     */
    public String getOffenseId() {
        return offenseId;
    }

    /**
     * @return Returns the offenseLevel.
     */
    public String getOffenseLevel() {
        return offenseLevel;
    }

    /**
     * @return
     */
    public Date getOrderActivateDate() {
        return orderActivateDate;
    }

    /**
	 * @return Returns the orderChainNum.
	 */
	public int getOrderChainNum() {
		return orderChainNum;
	}

    /**
     * @return
     */
    public Set getOrderConditionSet() {
        return orderConditionSet;
    }

    /**
     * @return
     */
    public Date getOrderFileDate() {
        return orderFileDate;
    }

    /**
     * @return
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @return
     */
    public String getOrderStatus() {
        return SupervisionOrderListHelper.getCodeDescription(this.orderStatusList, this.orderStatusId);
    }

    /**
     * @return
     */
    public String getOrderStatusId() {
        return orderStatusId;
    }

    /**
     * @return
     */
    public Collection getOrderStatusList() {
        return orderStatusList;
    }

    /**
     * @return
     */
    public String getOrderTitle() {
        return SupervisionOrderListHelper.getOrderTitleName(getAllOrderTitleList(), orderTitleId, versionTypeId);
    }

    /**
     * @return
     */
    public String getOrderTitleId() {
        return orderTitleId;
    }

    /**
     * @return
     */
    public Collection getOrderTitleList() {
        return orderTitleList;
    }
    
    /**
     * this method will be used by the orderPresentation.jsp to dynamically
     * change the options for Order Titles
     * 
     * @return
     */
    public Collection getOrderTitleListModifiedVersion() {
        return SupervisionOrderListHelper.groupOrderTitleList(getAllOrderTitleList(), getCourtCategory(), getCourtNum(), "Modified");
    }
	
	/**
     * @return
     */
    public String getOrderVersion() {
        StringBuffer versionLit = new StringBuffer();
        if (this.versionTypeId != null && !this.versionTypeId.equals("")) {
            String desc = this.getVersionType();

            if (desc != null) {
                versionLit.append(desc);
            } else {
                versionLit.append(this.versionTypeId);
            }
            if (!this.versionTypeId.equals(SupervisionConstants.ORIGINAL) && this.versionNum > 0) {
                versionLit.append(" v. ");
                versionLit.append(this.versionNum);
            }
            this.orderVersion = versionLit.toString();
        }
        return this.orderVersion;
    }

    /**
     * @return
     */
    public Collection getOrderVersionList() {
        return orderVersionList;
    }

    public String getOriginalJudgeFirstName() {
		return originalJudgeFirstName;
	}

    public String getOriginalJudgeLastName() {
		return originalJudgeLastName;
	}

    /**
     * @return Returns the originalOrderTitleId.
     */
    public String getOriginalOrderTitleId()
    {
        return originalOrderTitleId;
    }

    public Collection getOutputConditionList() {
		return outputConditionList;
	}

    /**
	 * @return Returns the partyName.
	 */
	public String getPartyName() {
		return partyName;
	}

    /**
     * @return
     */
    public String getPlea() {
        return plea;
    }

    /**
	 * @return the pleaId
	 */
	public String getPleaId() {
		return pleaId;
	}

    /**
	 * @return the pleas
	 */
	public Collection getPleas() {
		return pleas;
	}

    /**
     * @return
     */
    public String getPresentedBy() {
        String presentedByName = null;
        if (this.presentedById != null && !this.presentedById.equals("OTHER")) {
            presentedByName = SupervisionOrderListHelper.getSupervisionStaff(this.presentedByList, this.presentedById);
        } else {
            presentedByName = this.presentedBy;
        }
        return presentedByName;
    }

    /**
     * @return
     */
    public String getPresentedByFirstName() {
        return presentedByFirstName;
    }

    /**
     * @return
     */
    public String getPresentedById() {
        return presentedById;
    }

    /**
     * @return
     */
    public String getPresentedByLastName() {
        return presentedByLastName;
    }

    /**
     * @return
     */
    public Collection getPresentedByList() {
        if (presentedByList == null || presentedByList.size() == 0) {
            presentedByList = CodeHelper.getAllSupervisionStaff(true, this.courtId,false);
        }
        return presentedByList;
    }

    /**
     * @return Returns the previewConditionSelectedList.
     */
    public Collection getPreviewConditionSelectedList() {
        return previewConditionSelectedList;
    }

    /**
     * @return
     */
    public SupervisionOrderDetailResponseEvent getPreviousOrderVersion() {
        return previousOrderVersion;
    }

    /**
     * @return
     */
    public String getPrimaryCaseOrderKey() {
        return primaryCaseOrderKey;
    }

    /**
     * @return
     */
    public String getPrintAction() {
        return printAction;
    }

    /**
	 * @return Returns the printedName.
	 */
	public String getPrintedName() {
		return printedName;
	}

    /**
	 * @return Returns the printedOffenseDesc.
	 */
	public String getPrintedOffenseDesc() {
		return printedOffenseDesc;
	}

    /**
     * @return
     */
    public String getProbationEndDate() {
        return probationEndDate;
    }

    /**
     * @return
     */
    public String getProbationStartDate() {
        return probationStartDate;
    }

    /**
     * @return
     */
    public String getReason() {
        if (this.action.equals("withdraw") || this.action.equals("confirmWithdraw")) {
            return SupervisionOrderListHelper.getCodeDescription(this.withdrawReasonList, this.reasonId);
        } else {
            return ComplexCodeTableHelper.getDescrBySupervisionCode((List)getReinstateReasonList(), this.reasonId);
        }
    }

    /**
     * @return
     */
    public String getReasonId() {
        return reasonId;
    }

    /**
     * Returns map of reference values.
     * 
     * @return
     */
    public Map getReferenceVariableMap() {
    	if (this.getAction().equals(UIConstants.DELETE)){
    		return new HashMap();
    	}
        if (referenceVariableMap == null || referenceVariableMap.size() == 0) {
            List varElementTypes = (List) this.getReferenceVariablesFromDetailDictionary();
            Collection varElementNames = new ArrayList();
            VariableElementTypeResponseEvent vetr = null;
            //Iterator iter = varElementTypes.iterator();
//            if (iter != null && iter.hasNext()) {
//                while (iter.hasNext()) {
            for (int i = 0; i < varElementTypes.size(); i++) {
            	vetr = (VariableElementTypeResponseEvent) varElementTypes.get(i);
                varElementNames.add(vetr.getName());
            }
//            }
            boolean refreshable = this.isVersionTypeChangeAllowed();
            if (PDCodeTableConstants.STATUS_INACTIVE_ID.equals(this.orderStatusId)){
                refreshable = true;
            }
            referenceVariableMap = UISupervisionOrderHelper.getReferenceVariableMap(varElementNames, this.getOrderId(),
                    this.getCriminalCaseId(this.getPrimaryCaseOrderKey()), refreshable);

        }

        return referenceVariableMap;
    }
    
    /**
     * Returns collection of VariableElementTypeResponseEvents which are
     * reference variables.
     * 
     * @return
     */
    public Collection getReferenceVariablesFromDetailDictionary() {

            detailDictionary = this.getDetailDictionary();
            List aList = CollectionUtil.iteratorToList(detailDictionary.iterator());
            VariableElementTypeResponseEvent vetr = null;
            referenceVariables = new ArrayList();
            for (int i = 0; i < aList.size(); i++) {
            	vetr = (VariableElementTypeResponseEvent) aList.get(i);
                if (vetr.getIsReference()) {
                    referenceVariables.add(vetr);
                }
            }

        return referenceVariables;
    }
    

    /**
     * Returns a collection of reference variables of type
     * VariableElementTypeResponseEvents that are refreshable.
     * 
     * @return
     */
    public Collection getRefreshableReferenceVariablesFromDetailDictionary() {
        List allRefVars = (List) this.getReferenceVariablesFromDetailDictionary();
        Collection refreshableReferenceVariables = new ArrayList();
        VariableElementTypeResponseEvent vetr = null;

        for (int i = 0; i < allRefVars.size(); i++) {
			
             vetr = (VariableElementTypeResponseEvent) allRefVars.get(i);
             if (vetr.getIsVolatile()) {
                 refreshableReferenceVariables.add(vetr.getName());
             }
        }
        return refreshableReferenceVariables;
    }

    /**
     * @return
     */
    public Collection getReinstateReasonList() {
    	 return ComplexCodeTableHelper.getSupervisionCodes(getAgencyId(),PDCodeTableConstants.REINSTATEMENT_REASON);
    	
 
    }

    /**
     * @return Returns the resequencedOrderValue.
     */
    public String getResequencedOrderValue() {
        return resequencedOrderValue;
    }

    /**
     * @return Returns the scenario.
     */
    public String getScenario()
    {
        return scenario;
    }

    /**
     * @return
     */
    public String getSecondaryAction() {
        return secondaryAction;
    }

    public String[] getSelectedConditionIds() {
		return selectedConditionIds;
	}

    public String getSelectedOrderId() {
		return selectedOrderId;
	}

    /**
     * @return
     */
    public SupervisionOrderDetailResponseEvent getSelectedOrderVersion() {
        return selectedOrderVersion;
    }

    /**
     * @return
     */
    public String getSelectedValue() {
        return selectedValue;
    }

    /**
     * @return Returns the signedByDefendantDate.
     */
    public Date getSignedByDefendantDate() {
        return signedByDefendantDate;
    }

    /**
     * @return
     */
    public Date getSignedDate() {
        return signedDate;
    }
    
    /**
     * @return
     */
    public String getSignedDateAsString() {
        if (signedDate == null) {
            return "";
        } else {
            return DateUtil.dateToString(signedDate, UIConstants.DATE_FMT_1);
        }
    }

    public String getSpecialCourt() {
		return specialCourt;
	}
    public String getSpecialCourtCd() {
		return specialCourtCd;
	}
    public Collection getSpecialCourtCds() {
		return specialCourtCds;
	}
    /**
     * @return
     */
    public String getSpn() {
        return spn;
    }


    public Collection getStandardOptions() {
        Collection col = new ArrayList();

        CodeResponseEvent cr = new CodeResponseEvent();
        cr.setCode("");
        cr.setDescription("All");
        col.add(cr);

        cr = new CodeResponseEvent();
        cr.setCode("true");
        cr.setDescription(STANDARD);
        col.add(cr);

        cr = new CodeResponseEvent();
        cr.setCode("false");
        cr.setDescription(NON_STANDARD);
        col.add(cr);
        return col;
    }

    /**
     * @return
     */
    public String getStandardSearchCriteria() {
        return standardSearchCriteria;
    }

    /**
     * @return
     */
    public Date getStatusChangeDate() {
        return statusChangeDate;
    }

    /**
     * @return
     */
    public String getStatusChangeDateAsString() {
        if (statusChangeDate == null) {
            return "";
        } else {
            return DateUtil.dateToString(statusChangeDate, UIConstants.DATE_FMT_1);
        }
    }

    /**
     * @return
     */
    public Collection getSubGroups() {
        return subGroups;
    }

    /**
     * @return
     */
    public String getSuggestedOrderId() {
        return suggestedOrderId;
    }

    /**
     * @return
     */
    public Collection getSuggestedOrderList() {
        return suggestedOrderList;
    }

    /**
     * @return
     */
    public String getSummaryOfChanges() {
        return summaryOfChanges;
    }

    /**
     * @return
     */
    public Date getSupervisionBeginDate() {
        return supervisionBeginDate;
    }

    /**
     * @return
     */
    public String getSupervisionBeginDateAsString() {
        if (supervisionBeginDate == null) {
            return "";
        } else {
            return DateUtil.dateToString(supervisionBeginDate, UIConstants.DATE_FMT_1);
        }
    }

    /**
     * @return
     */
    public Date getSupervisionEndDate() {
        return supervisionEndDate;
    }

    /**
     * @return
     */
    public String getSupervisionEndDateAsString() {
        if (supervisionEndDate == null) {
            return "";
        } else {
            return DateUtil.dateToString(supervisionEndDate, UIConstants.DATE_FMT_1);
        }
    }

    /**
     * @return Returns the supervisionLengthDays.
     */
    public String getSupervisionLengthDays() {
        return supervisionLengthDays;
    }

    /**
     * @return Returns the supervisionLengthMonths.
     */
    public String getSupervisionLengthMonths() {
        return supervisionLengthMonths;
    }

    /**
     * @return Returns the supervisionLengthYears.
     */
    public String getSupervisionLengthYears() {
        return supervisionLengthYears;
    }

    /**
     * @return
     */
    public String getSupervisionOrderId() {
        return supervisionOrderId;
    }

    /**
     * @return Returns the supOrderRevisionDate.
     */
    public Date getSupOrderRevisionDate() {
        return supOrderRevisionDate;
    }

    /**
	 * @return Returns the taskCaseNum.
	 */
	public String getTaskCaseNum() {
		return taskCaseNum;
	}

    /**
	 * @return Returns the taskCdi.
	 */
	public String getTaskCdi() {
		return taskCdi;
	}

    /**
	 * @return Returns the taskId.
	 */
	public String getTaskId() {
		return taskId;
	}

    /**
	 * @return Returns the taskOrderId.
	 */
	public String getTaskOrderId() {
		return taskOrderId;
	}

    /**
     * @return
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * @return
     */
    public String getUserId() {
        return userId;
    }

    public Object getValue(String key) {
        return values.get(key);
    }

    /**
     * @return
     */
    public Map getValues() {
        return values;
    }

    /**
     * @return
     */
    public int getVersionNum() {
        return versionNum;
    }
    /**
     * @return
     */
    public String getVersionType() {
        return SupervisionOrderListHelper.getCodeDescription(this.versionTypeList, this.versionTypeId);
    }

    /**
     * @return This method returns list of all versionTypes excluding the
     *         Original. Original is never selected by the user.
     */
    public Collection getVersionTypeDropDownList() {
    	this.versionTypeDropDownList = this.createVersionTypeDropDown();
    	return this.versionTypeDropDownList;
    }
    
    /**
     * @return
     */
    public String getVersionTypeId() {
        return versionTypeId;
    }

    /**
     * @return
     */
    public Collection getVersionTypeList() {
        return versionTypeList;
    }

    /**
     * @return
     */
    public String getWhoSignedOrder() {
        return whoSignedOrder;
    }

    /**
     * @return
     */
    public Collection getWithdrawReasonList() {
        return withdrawReasonList;
    }

    /**
     * @return
     */
    public Collection getWithDrawReasonList() {
        return withdrawReasonList;
    }

    public String getWorkflowFrom() {
		return workflowFrom;
	}

    /**
     * @return Returns the workflowInd.
     */
    public String getWorkflowInd()
    {
        return workflowInd;
    }
    /**
     * @return
     */
    public boolean isCompared() {
        return compared;
    }
    /**
     * @return
     */
    public boolean isDelete() {
        return delete;
    }

    public boolean isDisallowVersionTypeChange() {
		return (!versionTypeChangeAllowed) || outOfCountyCase;
	}

    public boolean isLastImpactedOrder() {
        // get size of impacted order list
        int size = impactedOrderList.size();
        // check if current index is the last one in the collection
        if (currImpactedOrderIndex < (size - 1)) {
            return false;
        }
        return true;
    }

    /**
     * @return
     */
    public boolean isLikeConditionInd() {
        return likeConditionInd;
    }

    /**
     * @return
     */
    public boolean isLimitedSupervisonPeriod() {
        return limitedSupervisonPeriod;
    }

    /**
     * @return
     */
    public boolean isListsSet() {
        return listsSet;
    }

    /**
     * @return Returns the order.
     */
    public boolean isOrder() {
        return order;
    }

    /**
	 * @return Returns the outOfCountyCase.
	 */
	public boolean isOutOfCountyCase() {
		return outOfCountyCase;
	}

    /**
     * @return
     */
    public boolean isPrintSpanish() {
        return printSpanish;
    }

    /**
     * @return
     */
    public boolean isRefreshRefVarsAllowed() {
        
        boolean refreshable = false;
// added pending status for defect 62256 correction        
    	if (PDCodeTableConstants.STATUS_DRAFT_ID.equals(this.getOrderStatusId()) 
        		|| PDCodeTableConstants.STATUS_INCOMPLETE_ID.equals(this.getOrderStatusId())
        		|| PDCodeTableConstants.STATUS_PENDING_ID.equals(this.getOrderStatusId()) ) {
            refreshable = true;
        } else {
        	refreshable = this.isVersionTypeChangeAllowed();
        }
        return refreshable;
    }

 	/**
     * @return the searchSuperCondPerformed
     */
    public boolean isSearchSuperCondPerformed() {
        return searchSuperCondPerformed;
    }
    /**
	 * @return the showOrigJudgeInput
	 */
	public boolean isShowOrigJudgeInput() {
		return showOrigJudgeInput;
	}

	/**
	 * @param showOrigJudgeInput the showOrigJudgeInput to set
	 */
	public void setShowOrigJudgeInput(boolean showOrigJudgeInput) {
		this.showOrigJudgeInput = showOrigJudgeInput;
	}

    /**
     * @return the standard
     */
    public boolean isStandard() {
        return standard;
    }
    /**
     * @return the update
     */
    public boolean isUpdate() {
        return update;
    }
    /**
	 * @return Returns the userClo.
	 */
	public boolean isUserClo() {
		return UICommonSupervisionHelper.isUserCLO(this.getMyStaffPos());
	}

     /**
     * @return
     */
    public boolean isVersionTypeChangeAllowed() {
        
        if (PDCodeTableConstants.STATUS_ACTIVE_ID.equals(this.getOrderStatusId())) {
            versionTypeChangeAllowed = true;
        } else {
            versionTypeChangeAllowed = false;
        }
        return versionTypeChangeAllowed;
    }

    /**
     * @param aRequest
     */
    public void reset(ActionMapping aMapping, HttpServletRequest aRequest) {
        String field = aRequest.getParameter("limitedSupervisionPeriodParameter");
        printAction = "";

        if (field != null) {
            limitedSupervisonPeriod = false;
        }
        String checkCheckBoxes = aRequest.getParameter("conditionBoxes");
        if(checkCheckBoxes!=null){
        	clearDeleteBox();
        }
    }

    /* (non-Javadoc)
     * @see ui.task.ITaskRestorable#restore(messaging.task.domintf.ITask)
     */
    public void restore(ITask task)
    {
        ITaskState taskState = task.getTaskState();

        this.setScenario((String) taskState.get(TaskWorkflowConstants.SCENARIO));
        this.setActivityInd((String) taskState.get(TaskWorkflowConstants.ACTIVITY_IND));
        this.setWorkflowInd((String) taskState.get(TaskWorkflowConstants.WORKFLOW_IND));
        this.setConditionId((String) taskState.get(TaskWorkflowConstants.SUPERVISION_ORDER_ID));
    }

    /**
     * 
     */
    public void revertOrderTitleId()
    {
        this.orderTitleId = this.originalOrderTitleId;
    }

    /**
     * @param string
     */
    public void setAction(String string) {
        action = string;
    }

    /**
     * @param activityInd The activityInd to set.
     */
    public void setActivityInd(String activityInd)
    {
        this.activityInd = activityInd;
    }

    /**
     * @param string
     */
    public void setAgencyId(String string) {
        agencyId = string;
    }

    /**
     * @param collection
     */
    public void setAllOrderTitleList(Collection collection) {
        allOrderTitleList = collection;
    }

    /**
     * @param aDate
     */
    public void setCaseFileDate(Date aDate) {
        caseFileDate = aDate;
    }

    /**
     * @param string
     */
    public void setCasenotes(String string) {
        casenotes = string;
    }

    /**
     * @param string
     */
    public void setCaseNum(String string) {
        caseNum = string;
    }

    /**
     * @param caseSupervisionBeginDate The caseSupervisionBeginDate to set.
     */
    public void setCaseSupervisionBeginDate(Date caseSupervisionBeginDate) {
        this.caseSupervisionBeginDate = caseSupervisionBeginDate;
    }

    /**
     * @param string
     */
    public void setCaseSupervisionBeginDateAsString(String string) {
        if (string == null || string.trim().equals("")) {
            caseSupervisionBeginDate = null;
        } else {
        	caseSupervisionBeginDate = DateUtil.stringToDate(string, UIConstants.DATE_FMT_1);
        }
    }
    /**
     * @param caseSupervisionEndDate The caseSupervisionEndDate to set.
     */
    public void setCaseSupervisionEndDate(Date caseSupervisionEndDate) {
        this.caseSupervisionEndDate = caseSupervisionEndDate;
    }

    /**
     * @param string
     */
    public void setCaseSupervisionEndDateAsString(String string) {
        if (string == null || string.trim().equals("")) {
            caseSupervisionEndDate = null;
        } else {
        	caseSupervisionEndDate = DateUtil.stringToDate(string, UIConstants.DATE_FMT_1);
        }
    }

    /**
     * @param string
     */
    public void setCdi(String string) {
        cdi = string;
    }

    public void setComments(String comments) {
		this.comments = comments;
	}

    /**
     * @param b
     */
    public void setCompared(boolean b) {
        compared = b;
    }

    /**
     * @param string
     */
    public void setCompareToPrevousVersion(String string) {
        compareToPrevousVersion = string;
    }

    /**
     * @param string
     */
    public void setConditionId(String string) {
        conditionId = string;
    }

    /**
     * @param string
     */
    public void setConditionLiteral(String string) {
        if (string != null)
            string = UIUtil.removeStarting_BR_P_XMLtags(string);
        conditionLiteral = string;
    }

    /**
     * @param string
     */
    public void setConditionLiteralPreview(String string) {
        conditionLiteralPreview = string;
    }

    /**
     * @param string
     */
    public void setConditionName(String string) {
        conditionName = string;
    }

    /**
     * @param string
     */
    public void setConditionNotes(String string) {
        conditionNotes = string;
    }

    /**
     * @param collection
     */
    public void setConditionResultList(Collection collection) {
        if (collection != null && collection.size() > 1) {
            Collections.sort((List) collection);
            if (collection != null && collection.size() > 0)
                UISupervisionOrderHelper.setPreviewSample(this, collection, false);
        }

        conditionResultList = collection;
    }

    /**
     * @param collection
     */
    public void setConditionSelectedList(Collection collection) {
        if (collection != null && collection.size() > 0)
            UISupervisionOrderHelper.setPreviewSample(this, collection, true);
        conditionSelectedList = collection;
    }

    /**
     * @param confinementLengthDays The confinementLengthDays to set.
     */
    public void setConfinementLengthDays(String confinementLengthDays) {
        this.confinementLengthDays = confinementLengthDays;
        while(this.confinementLengthDays!=null && this.confinementLengthDays.trim().length() < 3){
        	this.confinementLengthDays = "0" + this.confinementLengthDays.trim();
        }
    }

    /**
     * @param confinementLengthMonths The confinementLengthMonths to set.
     */
    public void setConfinementLengthMonths(String confinementLengthMonths) {
        this.confinementLengthMonths = confinementLengthMonths;
        while(this.confinementLengthMonths!=null && this.confinementLengthMonths.trim().length() < 2){
        	this.confinementLengthMonths = "0" + this.confinementLengthMonths.trim();
        }
    }

    /**
     * @param confinementLengthYears The confinementLengthYears to set.
     */
    public void setConfinementLengthYears(String confinementLengthYears) {
        this.confinementLengthYears = confinementLengthYears;
        while(this.confinementLengthYears!=null && this.confinementLengthYears.trim().length() < 2){
        	this.confinementLengthYears = "0" + this.confinementLengthYears.trim();
        }
    }

    /**
     * @param string
     */
    public void setConnectionId(String string) {
        connectionId = string;
    }

    /**
     * @param collection
     */
    public void setConnectionList(Collection collection) {
        connectionList = collection;
    }

    /**
     * @param string
     */
    public void setCourtCategory(String string) {
        courtCategory = string;
    }

    /**
     * @param string
     */
    public void setCourtId(String string) {
        courtId = string;
    }

    /**
     * @param string
     */
    public void setCourtNum(String string) {
        courtNum = string;
    }

    /**
     * @param event
     */
    public void setCurrentOrder(SupervisionOrderDetailResponseEvent event) {
        currentOrder = event;
    }

    /**
     * @param i
     */
    public void setCurrImpactedOrderIndex(int i) {
        currImpactedOrderIndex = i;
    }

    /**
     * @param string
     */
    public void setDefendantId(String string) {
        defendantId = string;
    }
    
    /**
     * @param string
     */
    public void setDefendantSignature(String string) {
        defendantSignature = string;
    }

    /**
     * @param string
     */
    public void setDefendantSignedDateAsString(String string) {
        if (string == null || string.trim().equals("")) {
        	signedByDefendantDate = null;
        } else {
        	signedByDefendantDate = DateUtil.stringToDate(string, UIConstants.DATE_FMT_1);
        }
    }
    public void setJudgeSignedDateAsString(String string) {
        if (string == null || string.trim().equals("")) {
        	signedByJudgeDate = null;
        } else {
        	signedByJudgeDate = DateUtil.stringToDate(string, UIConstants.DATE_FMT_1);
        }
    }

    /**
     * @param string
     */
    public void setDeferredSupervisionLength(String string) {
        deferredSupervisionLength = string;
    }

    /**
     * @param b
     */
    public void setDelete(boolean b) {
        delete = b;
    }

    /**
     * @param collection
     */
    public void setDetailDictionary(Collection collection) {
        detailDictionary = collection;
    }

    /**
     * @param map
     */
    public void setDetailDictionaryNameIdHashMap(HashMap map) {
        detailDictionaryNameIdHashMap = map;
    }

    public void setDisplayConditionList(Collection displayConditionList) {
		this.displayConditionList = displayConditionList;
	}

    /**
	 * @param dispositionType The dispositionType to set.
	 */
	public void setDispositionType(String dispositionType) {
		this.dispositionType = dispositionType;
	}

    /**
     * @param dispositionTypeId The dispositionTypeId to set.
     */
    public void setDispositionTypeId(String dispositionTypeId) {
        this.dispositionTypeId = dispositionTypeId;
    }

    /**
	 * @param dispositionTypes The dispositionTypes to set.
	 */
	public void setDispositionTypes(Collection dispositionTypes) {
		this.dispositionTypes = dispositionTypes;
	}

    /**
     * @param string
     */
    public void setFineAmountTotal(String string) {
        fineAmountTotal = string;
    }

    /**
     * @param string
     */
    public void setGroup1(String string) {
        group1 = string;
    }

    /**
     * @param string
     */
    public void setGroup1Id(String string) {
        group1Id = string;
    }

    /**
     * @param collection
     */
    public void setGroup1List(Collection collection) {
        group1List = collection;
    }

    /**
     * @param string
     */
    public void setGroup1Name(String string) {
        group1Name = string;
    }

    /**
     * @param string
     */
    public void setGroup2(String string) {
        group2 = string;
    }

    /**
     * @param string
     */
    public void setGroup2Id(String string) {
        group2Id = string;
    }

    /**
     * @param string
     */
    public void setGroup3(String string) {
        group3 = string;
    }

    /**
     * @param string
     */
    public void setGroup3Id(String string) {
        group3Id = string;
    }

    /**
     * @param string
     */
    public void setGroupId(String string) {
        groupId = string;
    }

    /**
     * @param aCollection
     */
    public void setGroups(Collection aCollection) {
        groups = aCollection;
    }

    /**
     * @param event
     */
    public void setImpactedOrder(SupervisionOrderDetailResponseEvent event) {
        impactedOrder = event;
    }

    /**
     * @param collection
     */
    public void setImpactedOrderList(Collection collection) {
        impactedOrderList = collection;
    }

    public void setInputConditionList(Collection inputConditionList) {
		this.inputConditionList = inputConditionList;
	}

    /**
     * @param isPretrialInterventionOrder The isPretrialInterventionOrder to set.
     */
    public void setIsPretrialInterventionOrder(boolean isPretrialInterventionOrder) {
        this.isPretrialInterventionOrder = isPretrialInterventionOrder;
    }

    public void setIsMigratedOrder(boolean isMigratedOrder) {
		this.isMigratedOrder = isMigratedOrder;
	}

    /**
     * @param string
     */
    public void setJailTime(String string) {
        jailTime = string;
    }

    /**
     * @param string
     */
    public void setJudgeFirstName(String string) {
        judgeFirstName = string;
    }

    /**
     * @param string
     */
    public void setJudgeLastName(String string) {
        judgeLastName = string;
    }

    /**
     * @param string
     */
    public void setJudgeSelectId(String string) {
        judgeSelectId = string;
    }

    /**
     * @param collection
     */
    public void setJudgeSelectList(Collection collection) {
        judgeSelectList = collection;
    }

    /**
	 * @param juvCourtId the juvCourtId to set
	 */
	public void setJuvCourtId(String juvCourtId) {
		this.juvCourtId = juvCourtId;
	}

    /**
	 * @param juvCourts the juvCourts to set
	 */
	public void setJuvCourts(Collection juvCourts) {
		this.juvCourts = juvCourts;
	}

    /**
     * @param juvSupervisionBeginDate The juvSupervisionBeginDate to set.
     */
    public void setJuvSupervisionBeginDate(Date juvSupervisionBeginDate) {
        this.juvSupervisionBeginDate = juvSupervisionBeginDate;
    }
	/**
     * @param string
     */
    public void setJuvSupervisionBeginDateAsString(String string) {
        if (string == null || string.trim().equals("")) {
            juvSupervisionBeginDate = null;
        } else {
            juvSupervisionBeginDate = DateUtil.stringToDate(string, UIConstants.DATE_FMT_1);
        }
    }
	
    /**
	 * @param juvSupervisionLengthDays the juvSupervisionLengthDays to set
	 */
	public void setJuvSupervisionLengthDays(String juvSupervisionLengthDays) {
		this.juvSupervisionLengthDays = juvSupervisionLengthDays;
	}

	/**
	 * @param juvSupervisionLengthMonths the juvSupervisionLengthMonths to set
	 */
	public void setJuvSupervisionLengthMonths(String juvSupervisionLengthMonths) {
		this.juvSupervisionLengthMonths = juvSupervisionLengthMonths;
	}

	/**
	 * @param juvSupervisionLengthYears the juvSupervisionLengthYears to set
	 */
	public void setJuvSupervisionLengthYears(String juvSupervisionLengthYears) {
		this.juvSupervisionLengthYears = juvSupervisionLengthYears;
	}

	/**
     * @param b
     */
    public void setLikeConditionInd(boolean b) {
        likeConditionInd = b;
    }

    /**
     * @param b
     */
    public void setLimitedSupervisonPeriod(boolean b) {
        limitedSupervisonPeriod = b;
    }

    /**
     * @param b
     */
    public void setListsSet(boolean b) {
        listsSet = b;
    }

    /**
     * @param string
     */
    public void setMagFirstName(String string) {
        magFirstName = string;
    }

    /**
     * @param string
     */
    public void setMagistrateSelectId(String string) {
        magistrateSelectId = string;
    }

    /**
     * @param collection
     */
    public void setMagistrateSelectList(Collection collection) {
        magistrateSelectList = collection;
    }

    /**
     * @param string
     */
    public void setMagLastName(String string) {
        magLastName = string;
    }

    /**
	 * @param myStaffPos The myStaffPos to set.
	 */
	public void setMyStaffPos(CSCDSupervisionStaffResponseEvent myStaffPos) {
		this.myStaffPos = myStaffPos;
	}

    /**
     * @param string
     */
    public void setName(String string) {
        name = string;
    }

    /**
     * @param string
     */
    public void setOffense(String string) {
        offense = string;
    }

    /**
     * @param offenseDesc The offenseDesc to set.
     */
    public void setOffenseDesc(String offenseDesc) {
        this.offenseDesc = offenseDesc;
    }

    /**
     * @param string
     */
    public void setOffenseId(String string) {
        offenseId = string;
    }

    /**
     * @param offenseLevel
     *            The offenseLevel to set.
     */
    public void setOffenseLevel(String offenseLevel) {
        this.offenseLevel = offenseLevel;
    }

    /**
     * @param order
     *            The order to set.
     */
    public void setOrder(boolean order) {
        this.order = order;
    }

    /**
     * @param aDate
     */
    public void setOrderActivateDate(Date aDate) {
        orderActivateDate = aDate;
    }

    /**
	 * @param orderChainNum The orderChainNum to set.
	 */
	public void setOrderChainNum(int orderChainNum) {
		this.orderChainNum = orderChainNum;
	}

    /**
     * @param set
     */
    public void setOrderConditionSet(Set set) {
        orderConditionSet = set;
    }
    
    /**
     * @param aDate
     */
    public void setOrderFileDate(Date aDate) {
        orderFileDate = aDate;
    }

    /**
     * @param string
     */
    public void setOrderId(String string) {
        orderId = string;
    }

    /**
     * @param string
     */
    public void setOrderStatus(String string) {
        orderStatus = string;
    }

    /**
     * @param string
     */
    public void setOrderStatusId(String string) {
        orderStatusId = string;
    }

    /**
     * @param collection
     */
    public void setOrderStatusList(Collection collection) {
        orderStatusList = collection;
    }

    /**
     * @param string
     */
    public void setOrderTitle(String string) {
        orderTitle = string;
    }

    /**
     * @param string
     */
    public void setOrderTitleId(String string) 
    {
        orderTitleId = string;
    }

    /**
     * @param collection
     */
    public void setOrderTitleList(Collection collection) {
        orderTitleList = collection;
    }

    /**
     * @param string
     */
    public void setOrderVersion(String string) {
        orderVersion = string;
    }

    /**
     * @param collection
     */
    public void setOrderVersionList(Collection collection) {
        orderVersionList = collection;
    }
    /**
     * @param string
     */
    public void setOriginalJudgeFirstName(String originalJudgeFirstName) {
		this.originalJudgeFirstName = originalJudgeFirstName;
	}
    /**
     * @param string
     */
    public void setOriginalJudgeLastName(String originalJudgeLastName) {
		this.originalJudgeLastName = originalJudgeLastName;
	}
    /**
     * @param originalOrderTitleId The originalOrderTitleId to set.
     */
    public void setOriginalOrderTitleId(String originalOrderTitleId)
    {
        this.originalOrderTitleId = originalOrderTitleId;
    }

     /**
	 * @param outOfCountyCase The outOfCountyCase to set.
	 */
	public void setOutOfCountyCase(boolean outOfCountyCase) {
		this.outOfCountyCase = outOfCountyCase;
	}
    /**
     * @param collection
     */
    public void setOutputConditionList(Collection outputConditionList) {
		this.outputConditionList = outputConditionList;
	}

    /**
	 * @param partyName The partyName to set.
	 */
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

    /**
     * @param string
     */
    public void setPlea(String string) {
        plea = string;
    }

    /**
	 * @param pleaId the pleaId to set
	 */
	public void setPleaId(String pleaId) {
		this.pleaId = pleaId;
	}

    /**
	 * @param pleas the pleas to set
	 */
	public void setPleas(Collection pleas) {
		this.pleas = pleas;
	}

    /**
     * @param string
     */
    public void setPresentedBy(String string) {
        presentedBy = string;
    }

    /**
     * @param string
     */
    public void setPresentedByFirstName(String string) {
        presentedByFirstName = string;
    }

    /**
     * @param string
     */
    public void setPresentedById(String string) {
        presentedById = string;
    }

    /**
     * @param string
     */
    public void setPresentedByLastName(String string) {
        presentedByLastName = string;
    }

    /**
     * @param collection
     */
    public void setPresentedByList(Collection collection) {
        presentedByList = collection;
    }

    /**
     * @param previewConditionSelectedList
     *            The previewConditionSelectedList to set.
     */
    public void setPreviewConditionSelectedList(Collection previewConditionSelectedList) {
        this.previewConditionSelectedList = previewConditionSelectedList;
    }

    /**
     * @param event
     */
    public void setPreviousOrderVersion(SupervisionOrderDetailResponseEvent event) {
        previousOrderVersion = event;
    }

    /**
     * @param string
     */
    public void setPrimaryCaseOrderKey(String string) {
        primaryCaseOrderKey = string;
    }

    /**
     * @param string
     */
    public void setPrintAction(String string) {
        printAction = string;
    }
	/**
	 * @param printedName The printedName to set.
	 */
	public void setPrintedName(String printedName) {
		this.printedName = printedName;
	}
	/**
	 * @param printedOffenseDesc The printedOffenseDesc to set.
	 */
	public void setPrintedOffenseDesc(String printedOffenseDesc) {
		this.printedOffenseDesc = printedOffenseDesc;
	}
	/**
     * @param b
     */
    public void setPrintSpanish(boolean b) {
        printSpanish = b;
    }
	/**
     * @param string
     */
    public void setProbationEndDate(String string) {
        probationEndDate = string;
    }
    /**
     * @param string
     */
    public void setProbationStartDate(String string) {
        probationStartDate = string;
    }
    /**
     * @param string
     */
    public void setReason(String string) {
        reason = string;
    }
    
	/**
     * @param string
     */
    public void setReasonId(String string) {
        reasonId = string;
    }
	/**
     * @param map
     */
    public void setReferenceVariableMap(Map map) {
        referenceVariableMap = map;
    }

    /**
     * @param collection
     */
    public void setReinstateReasonList(Collection collection) {
        reinstateReasonList = collection;
    }
    /**
     * @param resequencedOrderValue
     *            The resequencedOrderValue to set.
     */
    public void setResequencedOrderValue(String resequencedOrderValue) {
        this.resequencedOrderValue = resequencedOrderValue;
    }
    /**
     * @param scenario The scenario to set.
     */
    public void setScenario(String scenario)
    {
        this.scenario = scenario;
    }
    /**
     * @param b
     */
    public void setSearchSuperCondPerformed(boolean b) {
        searchSuperCondPerformed = b;
    }
    /**
     * @param string
     */
    public void setSecondaryAction(String string) {
        secondaryAction = string;
    }

    public void setSelectedConditionIds(String[] selectedConditionIds) {
		this.selectedConditionIds = selectedConditionIds;
	}
    public void setSelectedOrderId(String selectedOrderId) {
		this.selectedOrderId = selectedOrderId;
	}
    /**
     * @param event
     */
    public void setSelectedOrderVersion(SupervisionOrderDetailResponseEvent event) {
        selectedOrderVersion = event;
    }
    /**
     * @param string
     */
    public void setSelectedValue(String string) {
        selectedValue = string;
    }
    /**
     * @param signedByDefendantDate
     *            The signedByDefendantDate to set.
     */
    public void setSignedByDefendantDate(Date defSignatureAcquiredDate) {
        this.signedByDefendantDate = defSignatureAcquiredDate;
    }
	public void setSignedByJudgeDate(Date signedByJudgeDate) {
		this.signedByJudgeDate = signedByJudgeDate;
	}

	public Date getSignedByJudgeDate() {
		return signedByJudgeDate;
	}

	/**
     * @param date
     */
    public void setSignedDate(Date aDate) {
        signedDate = aDate;
    }
	/**
     * @param string
     */
    public void setSignedDateAsString(String string) {
        if (string == null || string.trim().equals("")) {
            signedDate = null;
        } else {
            signedDate = DateUtil.stringToDate(string, UIConstants.DATE_FMT_1);
        }
    }
	/**
     * @param string
     */
	public void setSpecialCourt(String specialCourt) {
		this.specialCourt = specialCourt;
	}
	/**
     * @param string
     */
	public void setSpecialCourtCd(String specialCourtCd) {
		this.specialCourtCd = specialCourtCd;
	}
	/**
     * @param string
     */
	public void setSpecialCourtCds(Collection specialCourtCds) {
		this.specialCourtCds = specialCourtCds;
	}
	/**
     * @param string
     */
    public void setSpn(String string) {
        spn = string;
    }
	/**
     * @param b
     */
    public void setStandard(boolean b) {
        standard = b;
    }
	/**
     * @param string
     */
    public void setStandardSearchCriteria(String string) {
        standardSearchCriteria = string;
    }
	/**
     * @param date
     */
    public void setStatusChangeDate(Date aDate) {
        statusChangeDate = aDate;
    }
	/**
     * @param string
     */
    public void setStatusChangeDateAsString(String string) {
        if (string == null || string.trim().equals("")) {
            statusChangeDate = null;
        } else {
            statusChangeDate = DateUtil.stringToDate(string, UIConstants.DATE_FMT_1);
        }
    }
	/**
     * @param aCollection
     */
    public void setSubGroups(Collection aCollection) {
        subGroups = aCollection;
    }
	/**
     * @param string
     */
    public void setSuggestedOrderId(String string) {
        suggestedOrderId = string;
    }

	/**
     * @param collection
     */
    public void setSuggestedOrderList(Collection collection) {
        suggestedOrderList = collection;
    }
	
	/**
     * @param string
     */
    public void setSummaryOfChanges(String string) {
        summaryOfChanges = string;
    }
	
	/**
     * @param aDate
     */
    public void setSupervisionBeginDate(Date aDate) {
        supervisionBeginDate = aDate;
    }
	
	/**
     * @param string
     */
    public void setSupervisionBeginDateAsString(String string) {
        if (string == null || string.trim().equals("")) {
            supervisionBeginDate = null;
        } else {
            supervisionBeginDate = DateUtil.stringToDate(string, UIConstants.DATE_FMT_1);
        }
    }
	
	/**
     * @param aDate
     */
    public void setSupervisionEndDate(Date aDate) {
        supervisionEndDate = aDate;
    }
	/**
     * @param string
     */
    public void setSupervisionEndDateAsString(String string) {
        if (string == null || string.trim().equals("")) {
            supervisionEndDate = null;
        } else {
            supervisionEndDate = DateUtil.stringToDate(string, UIConstants.DATE_FMT_1);
        }
    }
	/**
     * @param supervisionLengthDays The supervisionLengthDays to set.
     */
    public void setSupervisionLengthDays(String supervisionLengthDays) {
        this.supervisionLengthDays = supervisionLengthDays;
        while(this.supervisionLengthDays!=null && this.supervisionLengthDays.trim().length() < 3){
        	this.supervisionLengthDays = "0" + this.supervisionLengthDays.trim();
        }
    }
	/**
     * @param supervisionLengthMonths The supervisionLengthMonths to set.
     */
    public void setSupervisionLengthMonths(String supervisionLengthMonths) {
        this.supervisionLengthMonths = supervisionLengthMonths;
        while(this.supervisionLengthMonths!=null && this.supervisionLengthMonths.trim().length() < 2){
        	this.supervisionLengthMonths = "0" + this.supervisionLengthMonths.trim();
        }
    }
	/**
     * @param supervisionLengthYears The supervisionLengthYears to set.
     */
    public void setSupervisionLengthYears(String supervisionLengthYears) {
        this.supervisionLengthYears = supervisionLengthYears;
        while(this.supervisionLengthYears!=null && this.supervisionLengthYears.trim().length() < 2){
        	this.supervisionLengthYears = "0" + this.supervisionLengthYears.trim();
        }
    }
	/**
     * @param string
     */
    public void setSupervisionOrderId(String string) {
        supervisionOrderId = string;
    }
	/**
     * @param supOrderRevisionDate
     *            The supOrderRevisionDate to set.
     */
    public void setSupOrderRevisionDate(Date supOrderRevisionDate) {
        this.supOrderRevisionDate = supOrderRevisionDate;
    }
	/**
	 * @param taskCaseNum The taskCaseNum to set.
	 */
	public void setTaskCaseNum(String taskCaseNum) {
		this.taskCaseNum = taskCaseNum;
	}
	/**
	 * @param taskCdi The taskCdi to set.
	 */
	public void setTaskCdi(String taskCdi) {
		this.taskCdi = taskCdi;
	}
	/**
	 * @param taskId The taskId to set.
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * @param taskOrderId The taskOrderId to set.
	 */
	public void setTaskOrderId(String taskOrderId) {
		this.taskOrderId = taskOrderId;
	}
	/**
     * @param string
     */
    public void setTemplateName(String string) {
        templateName = string;
    }
	/**
     * @param b
     */
    public void setUpdate(boolean b) {
        update = b;
    }
	/**
	 * @param userClo The userClo to set.
	 */
	public void setUserClo(boolean userClo) {
		this.userClo = userClo;
	}
	/**
     * @param string
     */
    public void setUserId(String string) {
        userId = string;
    }
	/**
     * @param string
     */	
	public void setValue(String key, Object value) {
        values.put(key, value);
    }
	/**
     * @param i
     */
    public void setVersionNum(int i) {
        versionNum = i;
    }
	/**
     * @param string
     */
    public void setVersionType(String string) {
        this.versionType = string;

    }
	/**
     * @param b
     */
    public void setVersionTypeChangeAllowed(boolean b) {
        versionTypeChangeAllowed = b;
    }
	/**
     * @param collection
     */
    public void setVersionTypeDropDownList(Collection collection) {
        versionTypeDropDownList = collection;
    }
	/**
     * @param string
     */
    public void setVersionTypeId(String string) {
        versionTypeId = string;
    }
	/**
     * @param collection 
     */
    public void setVersionTypeList(Collection collection) {
        versionTypeList = collection;
    }
	/**
     * @param string
     */
    public void setWhoSignedOrder(String string) {
        whoSignedOrder = string;
    }
	/**
     * @param collection
     */
    public void setWithdrawReasonList(Collection collection) {
        withdrawReasonList = collection;
    }
	public void setWorkflowFrom(String workflowFrom) {
		this.workflowFrom = workflowFrom;
	}
	/**
     * @param workflowInd The workflowInd to set.
     */
    public void setWorkflowInd(String workflowInd)
    {
        this.workflowInd = workflowInd;
    }
	/**
     * @return
     */
    public Map updateReferenceVariableMap() {

        boolean refreshable = this.isRefreshRefVarsAllowed();
        if (PDCodeTableConstants.STATUS_INACTIVE_ID.equals(this.orderStatusId)){
            refreshable = true;
        }
        Map updatedValuesMap = UISupervisionOrderHelper.getReferenceVariableMap(this
                .getRefreshableReferenceVariablesFromDetailDictionary(), this.getOrderId(), this.getCriminalCaseId(this
                .getPrimaryCaseOrderKey()), refreshable);

        referenceVariableMap = UISupervisionOrderHelper.updateReferenceVariableMap(updatedValuesMap,
                referenceVariableMap);
        
        updatedValuesMap = null;
        return referenceVariableMap;
    }

	public String getCurrentCourtCategory() {
		return currentCourtCategory;
	}

	public void setCurrentCourtCategory(String currentCourtCategory) {
		this.currentCourtCategory = currentCourtCategory;
	}

	public String getCurrentCourtId() {
		return currentCourtId;
	}

	public void setCurrentCourtId(String currentCourtId) {
		this.currentCourtId = currentCourtId;
	}

	public String getCurrentCourtNum() {
		return currentCourtNum;
	}

	public void setCurrentCourtNum(String currentCourtNum) {
		this.currentCourtNum = currentCourtNum;
	}
	
}
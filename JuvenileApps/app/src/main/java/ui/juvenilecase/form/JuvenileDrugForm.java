/*
 * Created on Jun 21, 2005
 *
 */
package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletRequest;

import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.codetable.drug.reply.DrugTestResultCodeResponseEvent;
import messaging.codetable.drug.reply.DrugTypeCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.juvenile.JuvenileDrugRequestEvent;
import messaging.juvenile.reply.DrugTestingResponseEvent;
import messaging.juvenile.reply.SubstanceAbuseResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.UIUtil;

/**
 * @author asrvastava
 * 
 */
public class JuvenileDrugForm extends ActionForm
{
	private String juvenileNum;
	private String referralNum;

	private String degree = null;
	private String degreeId;

	private String amountSpent = null;
	private String amountSpentId;

	private String frequency = null;
	private String frequencyId;

	private Collection drugsList;
	private String drugType = null;
	private String drugTypeId;

	private String onsetAge;
	private String action;

	private String locationOfUseId;
	private String locationOfUse = null;

	// maintains the insert index which will be the key for the map..
	private int index = 0;
	private ArrayList newDrugInfoList = new ArrayList();
	private String[] removeIndices;
	
	//User story 162443
	private String associateCasefile;
	private String testDate;
	private String testTime;
	private String testAdministered;
	private String testAdministeredDescr;
	private String substanceTested;
	private String substanceTestedDescr;
	private String drugTestResult;
	private String drugTestResultDescr;
	private String testLocation;
	private String testLocationDescr;
	private String administeredBy;
	private String comments;
	private String msg;
	private String activityCd;
	private String substanceAbuse;
	private String substanceAbuseDesc;
	private String substanceType;
	private String substanceTypeDesc;
	private List<DrugTestResultCodeResponseEvent>drugTestResults;
	private List<CodeResponseEvent>drugTestAdmins;
	private List<CodeResponseEvent>drugTypes;
	private List<JuvenileCasefileResponseEvent>juvenileCasfileResps;
	private List<LocationResponseEvent>locationCodes;
	private List<DrugTestingResponseEvent>drugTestingInfoList;
	private List<DrugTypeCodeResponseEvent>drugTypeCodes;
	private String [] substancesTested;
	private String [] substancesType;
	private List<CodeResponseEvent>tjjdSubstanceAbuseCodes;
	private List<SubstanceAbuseResponseEvent>substanceAbuseInfoList;
	private String treatmentLocation;
	private String juvenileMasterStatus;
	

	/*
	 * 
	 */
	public void clear()
	{
		removeIndices = null;
		newDrugInfoList.clear();
		associateCasefile = null;
		testDate = null;
		testTime = null;
		testAdministered = null;
		substanceTested = null;
		drugTestResult = null;
		testLocation = null;
		testAdministeredDescr = null;
		substanceTestedDescr = null;
		drugTestResultDescr = null;
		testLocationDescr = null;
		administeredBy = null;
		comments = null;
		msg = null;
		activityCd = null;
		substanceAbuse = null;
		substanceAbuseDesc = null;
		substanceType = null;
		substanceTypeDesc = null;
		drugType = null;
		drugTestResults = new ArrayList<>();
		drugTestAdmins = new ArrayList<>();
		juvenileCasfileResps = new ArrayList<>();
		drugTestingInfoList = new ArrayList<>();
		substancesTested = null;
		substancesType = null;
		locationCodes = new ArrayList<>();
		drugTypeCodes = new ArrayList<>();
		tjjdSubstanceAbuseCodes = new ArrayList<>();
		substanceAbuseInfoList = new ArrayList<>();
		treatmentLocation = "";
		juvenileMasterStatus = "";
	}

	/**
	 * @see ActionForm#reset(ActionMapping,
	 *      ServletRequest)
	 */
	public void reset(ActionMapping aMapping, ServletRequest aRequest)
	{
		super.reset(aMapping, aRequest);
		clear();
	}

	/**
	 * @return
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @return
	 */
	public String getDegree()
	{
		return degree;
	}

	/**
	 * @return
	 */
	public String getDrugType()
	{
		return drugType;
	}

	/**
	 * @return
	 */
	public String getFrequency()
	{
		return frequency;
	}

	/**
	 * @return
	 */
	public String getFrequencyId()
	{
		return frequencyId;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @return
	 */
	public String getOnsetAge()
	{
		return onsetAge;
	}

	/**
	 * @return
	 */
	public String getAmountSpent()
	{
		return amountSpent;
	}

	/**
	 * @param string
	 */
	public void setAction(String string)
	{
		action = string;
	}

	/**
	 * @param string
	 */
	public void setDegree(String string)
	{
		degree = string;
	}

	/**
	 * @param string
	 */
	public void setDrugType(String string)
	{
		drugType = string;
	}

	/**
	 * @param string
	 */
	public void setFrequency(String string)
	{
		frequency = string;
	}

	/**
	 * @param string
	 */
	public void setFrequencyId(String string)
	{
		frequencyId = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @param i
	 */
	public void setOnsetAge(String i)
	{
		onsetAge = i;
	}

	/**
	 * @param string
	 */
	public void setAmountSpent(String string)
	{
		amountSpent = string;
	}

	/**
	 * @return
	 */
	public String getDrugTypeId()
	{
		return drugTypeId;
	}

	/**
	 * @return
	 */
	public Collection getDrugsList()
	{
		return drugsList;
	}

	/**
	 * @param string
	 */
	public void setDrugTypeId(String string)
	{
		drugTypeId = string;
	}

	/**
	 * @param collection
	 */
	public void setDrugsList(Collection collection)
	{
		drugsList = collection;
	}

	/**
	 * @return
	 */
	public String getDegreeId()
	{
		return degreeId;
	}

	/**
	 * @param string
	 */
	public void setDegreeId(String string)
	{
		degreeId = string;
	}

	/**
	 * @return
	 */
	public Iterator getNewDrugInfoList()
	{
		return newDrugInfoList.iterator();
	}

	/*
	 * 
	 */
	public void addNewDrugInfo(JuvenileDrugRequestEvent req)
	{
		req.setTemporaryId(Integer.toString(newDrugInfoList.size()));
		// get the drug type description from the id
		CodeResponseEvent evt = UIUtil.findCodeResponseEvent(
					getDrugTypes().iterator(), req.getDrugType());
		if( evt != null )
		{
			req.setDrugTypeDesc(evt.getDescription());
		}
		
		// get the frequency description from the id
		evt = UIUtil.findCodeResponseEvent(
				getFrequencies().iterator(), req.getFrequency());
		if( evt != null )
		{
			req.setFrequencyDesc(evt.getDescription());
		}
		
		// get the frequency description from the id
		req.setAmountSpentDesc(req.getAmountSpent());

		// get the frequency description from the id
		evt = UIUtil.findCodeResponseEvent(getDegrees().iterator(), req.getDegree());
		if( evt != null )
		{
			req.setDegreeDesc(evt.getDescription());
		}
		
		if( getLocationOfUses() != null )
		{
			evt = UIUtil.findCodeResponseEvent(
					getLocationOfUses().iterator(), req.getLocationOfUse());
			if( evt != null )
			{
				req.setLocationOfUseDesc(evt.getDescription());
			}
		}
		else
		{
			req.setLocationOfUseDesc("");
		}
		newDrugInfoList.add(req);
	}

	/**
	 * @param collection
	 */
	public void setNewDrugInfoList(ArrayList collection)
	{
		newDrugInfoList = collection;
	}

	/**
	 * @return
	 */
	public String getAmountSpentId()
	{
		return amountSpentId;
	}

	/**
	 * @param string
	 */
	public void setAmountSpentId(String string)
	{
		amountSpentId = string;
	}

	public Collection getDrugTypes()
	{
		return CodeHelper.getDrugTypes();
	}

	public Collection getFrequencies()
	{
		return CodeHelper.getFrequencies();
	}

	public Collection getDegrees()
	{
		return CodeHelper.getDegrees();
	}

	public Collection getLocationOfUses()
	{
		return CodeHelper.getLocationOfUses(true);
	}

	public void clearDrugCreateInfo()
	{
		onsetAge = "";
		drugTypeId = "";
		frequencyId = "";
		amountSpentId = "";
		degreeId = "";
		locationOfUseId = "";
	}

	/*
	 * 
	 */
	public void removeSelectedDrugs()
	{
		int selIndex, drugListSize = newDrugInfoList.size() ;
		JuvenileDrugRequestEvent evt;
		
		for( int i = 0; i < removeIndices.length; i++ )
		{
			try
			{
				selIndex = Integer.parseInt(removeIndices[i]);
				if( selIndex < drugListSize )
				{
					evt = (JuvenileDrugRequestEvent)newDrugInfoList.get(selIndex);
					evt.markForDeletion();
				}
			}
			catch( NumberFormatException nfe )
			{				
			}
		} // for

		Iterator ite = newDrugInfoList.iterator();
		ArrayList newList = new ArrayList();
		int newIndex = 0;
		while( ite.hasNext() )
		{
			evt = (JuvenileDrugRequestEvent)ite.next();
			if( !evt.isMarkedForDeletion() )
			{
				evt.setTemporaryId( Integer.toString(newIndex++) );
				newList.add(evt);
			}
		}
		removeIndices = null;
		newDrugInfoList = newList;
	}

	/**
	 * @return
	 */
	public String[] getRemoveIndices()
	{
		return removeIndices;
	}

	/**
	 * @param strings
	 */
	public void setRemoveIndices(String[] strings)
	{
		removeIndices = strings;
	}

	/**
	 * @return
	 */
	public String getLocationOfUse()
	{
		return locationOfUse;
	}

	/**
	 * @return
	 */
	public String getLocationOfUseId()
	{
		return locationOfUseId;
	}

	/**
	 * @param string
	 */
	public void setLocationOfUse(String string)
	{
		locationOfUse = string;
	}

	/**
	 * @param string
	 */
	public void setLocationOfUseId(String string)
	{
		locationOfUseId = string;
	}

	public int getIndex()
	{
	    return index;
	}

	public void setIndex(int index)
	{
	    this.index = index;
	}

	public String getAssociateCasefile()
	{
	    return associateCasefile;
	}

	public void setAssociateCasefile(String associateCasefile)
	{
	    this.associateCasefile = associateCasefile;
	}

	public String getTestDate()
	{
	    return testDate;
	}

	public void setTestDate(String testDate)
	{
	    this.testDate = testDate;
	}

	public String getTestTime()
	{
	    return testTime;
	}

	public void setTestTime(String testTime)
	{
	    this.testTime = testTime;
	}

	public String getTestAdministered()
	{
	    return testAdministered;
	}

	public void setTestAdministered(String testAdministered)
	{
	    this.testAdministered = testAdministered;
	}

	public String getSubstanceTested()
	{
	    return substanceTested;
	}

	public void setSubstanceTested(String substanceTested)
	{
	    this.substanceTested = substanceTested;
	}

	public String getDrugTestResult()
	{
	    return drugTestResult;
	}

	public void setDrugTestResult(String drugTestResult)
	{
	    this.drugTestResult = drugTestResult;
	}

	public String getTestLocation()
	{
	    return testLocation;
	}

	public void setTestLocation(String testLocation)
	{
	    this.testLocation = testLocation;
	}

	public String getAdministeredBy()
	{
	    return administeredBy;
	}

	public void setAdministeredBy(String administeredBy)
	{
	    this.administeredBy = administeredBy;
	}

	public String getComments()
	{
	    return comments;
	}

	public void setComments(String comments)
	{
	    this.comments = comments;
	}

	
	public List<DrugTestResultCodeResponseEvent> getDrugTestResults()
	{
	    return drugTestResults;
	}

	public void setDrugTestResults(List<DrugTestResultCodeResponseEvent> drugTestResults)
	{
	    this.drugTestResults = drugTestResults;
	}

	public List<CodeResponseEvent> getDrugTestAdmins()
	{
	    return drugTestAdmins;
	}

	public void setDrugTestAdmins(List<CodeResponseEvent> drugTestAdmins)
	{
	    this.drugTestAdmins = drugTestAdmins;
	}

	public void setDrugTypes(List<CodeResponseEvent> drugTypes)
	{
	    this.drugTypes = drugTypes;
	}

	public List<JuvenileCasefileResponseEvent> getJuvenileCasfileResps()
	{
	    return juvenileCasfileResps;
	}

	public void setJuvenileCasfileResps(List<JuvenileCasefileResponseEvent> juvenileCasfileResps)
	{
	    this.juvenileCasfileResps = juvenileCasfileResps;
	}
	
	public Collection getWorkDays()
	{
		//Work days code table has to be sorted by ID, not just string comparison.
		return CodeHelper.getWorkDayCodes();
	}

	public List<LocationResponseEvent> getLocationCodes()
	{
	    return locationCodes;
	}

	public void setLocationCodes(List<LocationResponseEvent> locationCodes)
	{
	    this.locationCodes = locationCodes;
	}

	public String getTestAdministeredDescr()
	{
	    return testAdministeredDescr;
	}

	public void setTestAdministeredDescr(String testAdministeredDescr)
	{
	    this.testAdministeredDescr = testAdministeredDescr;
	}

	public String getSubstanceTestedDescr()
	{
	    return substanceTestedDescr;
	}

	public void setSubstanceTestedDescr(String substanceTestedDescr)
	{
	    this.substanceTestedDescr = substanceTestedDescr;
	}

	public String getDrugTestResultDescr()
	{
	    return drugTestResultDescr;
	}

	public void setDrugTestResultDescr(String drugTestResultDescr)
	{
	    this.drugTestResultDescr = drugTestResultDescr;
	}

	public String getTestLocationDescr()
	{
	    return testLocationDescr;
	}

	public void setTestLocationDescr(String testLocationDescr)
	{
	    this.testLocationDescr = testLocationDescr;
	}

	public String getMsg()
	{
	    return msg;
	}

	public void setMsg(String msg)
	{
	    this.msg = msg;
	}

	public List<DrugTestingResponseEvent> getDrugTestingInfoList()
	{
	    return drugTestingInfoList;
	}

	public void setDrugTestingInfoList(List<DrugTestingResponseEvent> drugTestingInfoList)
	{
	    this.drugTestingInfoList = drugTestingInfoList;
	}

	public List<DrugTypeCodeResponseEvent> getDrugTypeCodes()
	{
	    return drugTypeCodes;
	}

	public void setDrugTypeCodes(List<DrugTypeCodeResponseEvent> drugTypeCodes)
	{
	    this.drugTypeCodes = drugTypeCodes;
	}

	public String getActivityCd()
	{
	    return activityCd;
	}

	public void setActivityCd(String activityCd)
	{
	    this.activityCd = activityCd;
	}

	public String[] getSubstancesTested()
	{
	    return substancesTested;
	}

	public void setSubstancesTested(String[] substancesTested)
	{
	    this.substancesTested = substancesTested;
	}

	public List<CodeResponseEvent> getTjjdSubstanceAbuseCodes()
	{
	    return tjjdSubstanceAbuseCodes;
	}

	public void setTjjdSubstanceAbuseCodes(List<CodeResponseEvent> tjjdSubstanceAbuseCodes)
	{
	    this.tjjdSubstanceAbuseCodes = tjjdSubstanceAbuseCodes;
	}

	public String getSubstanceAbuse()
	{
	    return substanceAbuse;
	}

	public void setSubstanceAbuse(String substanceAbuse)
	{
	    this.substanceAbuse = substanceAbuse;
	}

	public String getSubstanceType()
	{
	    return substanceType;
	}

	public void setSubstanceType(String substanceType)
	{
	    this.substanceType = substanceType;
	}

	public String getReferralNum()
	{
	    return referralNum;
	}

	public void setReferralNum(String referralNum)
	{
	    this.referralNum = referralNum;
	}

	public String getSubstanceAbuseDesc()
	{
	    return substanceAbuseDesc;
	}

	public void setSubstanceAbuseDesc(String substanceAbuseDesc)
	{
	    this.substanceAbuseDesc = substanceAbuseDesc;
	}

	public String getSubstanceTypeDesc()
	{
	    return substanceTypeDesc;
	}

	public void setSubstanceTypeDesc(String substanceTypeDesc)
	{
	    this.substanceTypeDesc = substanceTypeDesc;
	}

	public String[] getSubstancesType()
	{
	    return substancesType;
	}

	public void setSubstancesType(String[] substancesType)
	{
	    this.substancesType = substancesType;
	}

	public List<SubstanceAbuseResponseEvent> getSubstanceAbuseInfoList()
	{
	    return substanceAbuseInfoList;
	}

	public void setSubstanceAbuseInfoList(List<SubstanceAbuseResponseEvent> substanceAbuseInfoList)
	{
	    this.substanceAbuseInfoList = substanceAbuseInfoList;
	}

	public String getTreatmentLocation()
	{
	    return treatmentLocation;
	}

	public void setTreatmentLocation(String treatmentLocation)
	{
	    this.treatmentLocation = treatmentLocation;
	}

	public String getJuvenileMasterStatus()
	{
	    return juvenileMasterStatus;
	}

	public void setJuvenileMasterStatus(String juvenileMasterStatus)
	{
	    this.juvenileMasterStatus = juvenileMasterStatus;
	}
	
	
	

}

package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletRequest;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.juvenile.JuvenileGangRequestEvent;
import messaging.juvenile.reply.JuvenileGangsResponseEvent;
import mojo.km.utilities.DateUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.UIUtil;

/**
 * @author sthyagarajan
 * Gangs FORM
 */

public class JuvenileGangsForm extends ActionForm {
	
	private String juvenileNum;
	private Date entryDate=new Date();
	
	private String gangName=null;
	private String gangNameId;
	
	private String cliqueSet=null;
	private String cliqueSetId;
	
	private String currentStatus=null;
	private String currentStatusId;
	
	private String associationType=null;
	private String associationTypeId;
	
	
	private String aliasMoniker="";
	private String rank="";
	
	private String action;

	
	//Hidden field for other,on selection of other,show other text box.
	private String otherGangName="";
	private String otherCliqueSet="";
	
	//Hidden field for describe hybrid on selection of hybrid as the gang name;
	private String descHybrid="";
	//used for getting the index for removing the gang info.
	String selectedValue = "";
	
	private ArrayList <String> gangsInfoList = new ArrayList<String>();
	private Collection<JuvenileGangsResponseEvent> gangsList = new ArrayList<JuvenileGangsResponseEvent>();
	private Collection<JuvenileGangRequestEvent> newGangsInfoList = new ArrayList<JuvenileGangRequestEvent>();
	
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
	 * resets the list and flag attributes.
	 */
	public void clear()
	{
		gangsInfoList.clear();
		newGangsInfoList.clear();
		selectedValue="";
		clearGangCreateInfo();
	}
	
	/**
	 * resets the gang info form values.
	 */
	public void clearGangCreateInfo()
	{
		gangNameId = "";
		cliqueSetId = "";
		currentStatusId="";
		associationTypeId = "";
		aliasMoniker="";
		rank="";
		entryDate=new Date();
		otherGangName="";
		otherCliqueSet="";
		descHybrid="";
	}
	/**
	 * Add new gang info in the list.
	 * @param JuvenileGangRequestEvent req
	 */
	public void addNewGangInfo(JuvenileGangRequestEvent req)
	{
		CodeResponseEvent evt = null;
		
		// get the Gang Name description from the id
		if(!req.getGangName().equalsIgnoreCase("OTHR"))
		{
			if(getGangNames()!=null){
				evt = UIUtil.findCodeResponseEvent(
							getGangNames().iterator(), req.getGangName());
				}
				if( evt != null )
				{
					req.setGangNameDesc(evt.getDescription());
				}
		}else
		{
			req.setGangNameDesc(getOtherGangName().toUpperCase());
		}
		
		// get the CliqueSet description from the id
		if(!req.getCliqueSet().equalsIgnoreCase("OTHR"))
		{
			if(getCliqueSets()!=null){
			evt = UIUtil.findCodeResponseEvent(
					getCliqueSets().iterator(), req.getCliqueSet());
			}
			if( evt != null )
			{
				req.setCliqueSetDesc(evt.getDescription());
			}
		}
		else
		{
			req.setCliqueSetDesc(getOtherCliqueSet().toUpperCase());
		}
		
		// get the CurrentStatus description from the id
		if(getStatus()!=null)
		{
			evt = UIUtil.findCodeResponseEvent(getStatus().iterator(), req.getCurrentStatus());
			if( evt != null )
			{
				req.setCurrentStatusDesc(evt.getDescription());
			}
		}
		// get the AssociationType description from the id
		if( getAssociationTypes() != null )
		{
			evt = UIUtil.findCodeResponseEvent(
					getAssociationTypes().iterator(), req.getAssociationType());
			if( evt != null )
			{
				req.setAssociationTypeDesc(evt.getDescription());
			}
		}
		newGangsInfoList.add(req);
	}
	
	/**
	 * @return
	 */
	public Collection getGangNames()
	{
		return CodeHelper.getGangNameCodes();
	}

	/**
	 * @return
	 */
	public Collection getCliqueSets()
	{
		return CodeHelper.getGangCliqueCodes();
	}

	/**
	 * @return
	 */
	public Collection getStatus()
	{
		return CodeHelper.getGangStatusCodes();
	}

	/**
	 * @return
	 */
	public Collection getAssociationTypes()
	{
		return CodeHelper.getGangAssociationTypeCodes();
	}
	
	/**
	 * @return
	 */
	public String getSelectedValue() {
		return selectedValue;
	}

	/**
	 * @param selectedValue
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}	
	
	
	/**
	 * @return
	 */
	public Collection<JuvenileGangsResponseEvent> getGangsList() {
		return gangsList;
	}

	/**
	 * @param gangsList
	 */
	public void setGangsList(Collection<JuvenileGangsResponseEvent> gangsList) {
		this.gangsList = gangsList;
	}

	/**
	 * @return
	 */
	public ArrayList<String> getGangsInfoList() {
		return gangsInfoList;
	}

	/**
	 * @param gangsInfoList
	 */
	public void setGangsInfoList(ArrayList<String> gangsInfoList) {
		this.gangsInfoList = gangsInfoList;
	}

	/**
	 * @return
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	
	
	/**
	 * @return entryDate
	 */
	public String getEntryDate() {
		return DateUtil.dateToString(entryDate, DateUtil.DATE_FMT_1);
	}

	/**
	 * @param entryDate
	 */
	public void setEntryDate(String entryDate) {
		Date entDate = DateUtil.stringToDate(entryDate, DateUtil.DATE_FMT_1);
		this.entryDate = entDate;
	}

	/**
	 * @param entryDate
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	/**
	 * @return
	 */
	public String getGangName() {
		return gangName;
	}
	/**
	 * @param gangName
	 */
	public void setGangName(String gangName) {
		this.gangName = gangName;
	}
	/**
	 * @return
	 */
	public String getClique() {
		return cliqueSet;
	}
	/**
	 * @param clique
	 */
	public void setClique(String clique) {
		this.cliqueSet = clique;
	}
	/**
	 * @return
	 */
	public String getCurrentStatus() {
		return currentStatus;
	}
	/**
	 * @param currentStatus
	 */
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	/**
	 * @return
	 */
	public String getAssociationType() {
		return associationType;
	}
	/**
	 * @param associationType
	 */
	public void setAssociationType(String associationType) {
		this.associationType = associationType;
	}

	/**
	 * @return the moniker
	 */
	public String getAliasMoniker() {
		return aliasMoniker;
	}

	/**
	 * @param moniker the moniker to set
	 */
	public void setAliasMoniker(String aliasMoniker) {
		this.aliasMoniker = aliasMoniker;
	}

	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}


	
	public Collection<JuvenileGangRequestEvent> getNewGangsInfoList() {
		return newGangsInfoList;
	}

	public void setNewGangsInfoList(
			Collection<JuvenileGangRequestEvent> newGangsInfoList) {
		this.newGangsInfoList = newGangsInfoList;
	}

	/**
	 * @param gangNameId the gangNameId to set
	 */
	public void setGangNameId(String gangNameId) {
		this.gangNameId = gangNameId;
	}

	/**
	 * @return the gangNameId
	 */
	public String getGangNameId() {
		return gangNameId;
	}

	/**
	 * @param cliqueSetId the cliqueSetId to set
	 */
	public void setCliqueSetId(String cliqueSetId) {
		this.cliqueSetId = cliqueSetId;
	}

	/**
	 * @return the cliqueSetId
	 */
	public String getCliqueSetId() {
		return cliqueSetId;
	}

	/**
	 * @param associationTypeId the associationTypeId to set
	 */
	public void setAssociationTypeId(String associationTypeId) {
		this.associationTypeId = associationTypeId;
	}

	/**
	 * @return the associationTypeId
	 */
	public String getAssociationTypeId() {
		return associationTypeId;
	}

	/**
	 * @param currentStatusId the currentStatusId to set
	 */
	public void setCurrentStatusId(String currentStatusId) {
		this.currentStatusId = currentStatusId;
	}

	/**
	 * @return
	 */
	public String getCliqueSet() {
		return cliqueSet;
	}

	/**
	 * @param cliqueSet
	 */
	public void setCliqueSet(String cliqueSet) {
		this.cliqueSet = cliqueSet;
	}

	/**
	 * @return the currentStatusId
	 */
	public String getCurrentStatusId() {
		return currentStatusId;
	}

	/**
	 * @param otherGangName
	 */
	public void setOtherGangName(String otherGangName) {
		this.otherGangName = otherGangName;
	}

	public String getOtherGangName() {
		return otherGangName;
	}

	/**
	 * @param otherCliqueSet the otherCliqueSet to set
	 */
	public void setOtherCliqueSet(String otherCliqueSet) {
		this.otherCliqueSet = otherCliqueSet;
	}

	/**
	 * @return the otherCliqueSet
	 */
	public String getOtherCliqueSet() {
		return otherCliqueSet;
	}

	/**
	 * @param descHybrid the descHybrid to set
	 */
	public void setDescHybrid(String descHybrid) {
		this.descHybrid = descHybrid;
	}

	/**
	 * @return the descHybrid
	 */
	public String getDescHybrid() {
		return descHybrid;
	}

}


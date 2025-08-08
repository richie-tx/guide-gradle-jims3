/*
 * Created on Mar 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.adminstaff.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import messaging.supervisionoptions.reply.CourtResponseEvent;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ui.common.User;
import ui.supervision.Casenote;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.SupervisionOptions.form.CourtBean;
import ui.supervision.adminstaff.Position;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminStaffForm extends ActionForm {
	
	// Default Elements in all forms
	private static Collection emptyColl = new ArrayList();
	private String action = "";
	private String secondaryAction = "";
	private boolean update = false;
	private boolean delete = false;
	private String selectedValue = "";
	
	
	// FORM SPECIFIC VALUES
	private User reassignedUser=null;
	private Position updatedSupervisor=new Position();
	private Position position=new Position();
	private ArrayList availableSupervisors=new ArrayList();  // List of Position Objects
	private Casenote casenote=new Casenote();
	private boolean allCourtsSelected=false;
	private Collection courts=null;
	private Collection selectedCourts=new ArrayList();
	
	
	
	
	
	
	public void setCourtsFromForm(HttpServletRequest aRequest){
//		 create a CourtRespEvent map to make search fast for the selected courts
		//			Map courtMap = createCourtMap(form.getCourts());
		ArrayList selectedCourts = new ArrayList();
		this.setSelectedCourts(selectedCourts);
		if(this.getPosition()!=null)
			this.getPosition().setSelectedCourts(selectedCourts);
		Collection courtBeans = this.getCourts();
		Map courtMap=UISupervisionOptionHelper.createCourtMap(courtBeans);
		if (courtBeans != null)
		{
			Iterator it = courtBeans.iterator();
			while (it.hasNext())
			{
				CourtBean courtBean = (CourtBean) it.next();
				String[] selCourts = aRequest.getParameterValues(courtBean.getCategory());
				if (selCourts != null)
				{
					 CourtBean selCourtBean = new CourtBean();
					 selCourtBean.setCategory(courtBean.getCategory());
					 selCourtBean.setCategoryDesc(courtBean.getCategoryDesc());
					 for(int i = 0; i < selCourts.length; i++){
						 //get the selected CourtResponseEvent
						 CourtResponseEvent cre = (CourtResponseEvent)courtMap.get(selCourts[i]); 
						 selCourtBean.insertCourt(cre);
					 }
					 selectedCourts.add(selCourtBean);
				}
			}
		}
	}
	
	public void clearDefaultFormValues(){
		action = "";
		secondaryAction = "";
		update = false;
		delete = false;
		selectedValue = "";
	}
	
	
	public void reset(ActionMapping aMapping, HttpServletRequest aRequest)
	{
		Object obj = aRequest.getParameter("clearSelectedCourts");
		if (obj != null)
		{
			String clearSelectedCourts = (String) obj;
			if (clearSelectedCourts.equals("true"));
			{
				if(position!=null){
					position.setSelectedCourts(new ArrayList());
					setCourtsFromForm(aRequest);
				}
			}
		}
	}

	public void clear(){
		reassignedUser=new User();
		updatedSupervisor=new Position();
		position=new Position();
		availableSupervisors=new ArrayList();
		casenote=new Casenote();
		
	}
	
	public void clearAll(){
		clearDefaultFormValues();
		clear();
	}
	
	
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return Returns the availableSupervisors.
	 */
	public ArrayList getAvailableSupervisors() {
		return availableSupervisors;
	}
	/**
	 * @param availableSupervisors The availableSupervisors to set.
	 */
	public void setAvailableSupervisors(ArrayList availableSupervisors) {
		this.availableSupervisors = availableSupervisors;
	}
	/**
	 * @return Returns the casenote.
	 */
	public Casenote getCasenote() {
		return casenote;
	}
	/**
	 * @param casenote The casenote to set.
	 */
	public void setCasenote(Casenote casenote) {
		this.casenote = casenote;
	}
	/**
	 * @return Returns the delete.
	 */
	public boolean isDelete() {
		return delete;
	}
	/**
	 * @param delete The delete to set.
	 */
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	/**
	 * @return Returns the position.
	 */
	public Position getPosition() {
		return position;
	}
	/**
	 * @param position The position to set.
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
	/**
	 * @return Returns the reassignedUser.
	 */
	public User getReassignedUser() {
		return reassignedUser;
	}
	/**
	 * @param reassignedUser The reassignedUser to set.
	 */
	public void setReassignedUser(User reassignedUser) {
		this.reassignedUser = reassignedUser;
	}
	/**
	 * @return Returns the secondaryAction.
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}
	/**
	 * @param secondaryAction The secondaryAction to set.
	 */
	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}
	/**
	 * @return Returns the selectedValue.
	 */
	public String getSelectedValue() {
		return selectedValue;
	}
	/**
	 * @param selectedValue The selectedValue to set.
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}
	/**
	 * @return Returns the update.
	 */
	public boolean isUpdate() {
		return update;
	}
	/**
	 * @param update The update to set.
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}
	/**
	 * @return Returns the updatedSupervisor.
	 */
	public Position getUpdatedSupervisor() {
		return updatedSupervisor;
	}
	/**
	 * @param updatedSupervisor The updatedSupervisor to set.
	 */
	public void setUpdatedSupervisor(Position updatedSupervisor) {
		this.updatedSupervisor = updatedSupervisor;
	}
	/**
	 * @return Returns the allCourtsSelected.
	 */
	public boolean isAllCourtsSelected() {
		return allCourtsSelected;
	}
	/**
	 * @param allCourtsSelected The allCourtsSelected to set.
	 */
	public void setAllCourtsSelected(boolean allCourtsSelected) {
		this.allCourtsSelected = allCourtsSelected;
	}
	/**
	 * @return Returns the courts.
	 */
	public Collection getCourts() {
		return getPosition().getCourts();
	}
	/**
	 * @param courts The courts to set.
	 */
	public void setCourts(Collection courts) {
		this.getPosition().setCourts(courts);
	}
	/**
	 * @return Returns the selectedCourts.
	 */
	public Collection getSelectedCourts() {
		if(selectedCourts==null || selectedCourts.size()<1){
			return null;
		}
		else
			return selectedCourts;
	}
	/**
	 * @param selectedCourts The selectedCourts to set.
	 */
	public void setSelectedCourts(Collection selectedCourts) {
		this.selectedCourts = selectedCourts;
	}
}// END CLASS


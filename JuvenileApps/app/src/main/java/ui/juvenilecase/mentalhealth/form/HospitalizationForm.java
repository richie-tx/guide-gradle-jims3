/*
 * Created on Jan 26, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.mentalhealth.form;

import org.apache.struts.action.ActionForm;

import java.util.Collection;
import java.util.ArrayList;

import messaging.contact.domintf.IPhoneNumber;
import ui.common.PhoneNumber;


/**
 * @author ugopinath
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class HospitalizationForm extends ActionForm
{
	private String hospId = "";
	private String juvNum = "";
	private String actionType = "";
	private String selectedValue = "";
	private HospitalizationRecord hospRec = new HospitalizationRecord();
	
	//Collections
	private Collection hospitalizationList = new ArrayList();
	private Collection admissionTypes = new ArrayList();
	
	public void clear()
	{
		hospId ="";
		//juvNum="";
		actionType="";
		hospRec = new HospitalizationRecord();
	}
	
	
	
	/**
	 * @return Returns the hospId.
	 */
	public String getHospId() {
		return hospId;
	}
	/**
	 * @param hospId The hospId to set.
	 */
	public void setHospId(String hospId) {
		this.hospId = hospId;
	}
	/**
	 * @return Returns the juvNum.
	 */
	public String getJuvNum() {
		return juvNum;
	}
	/**
	 * @param juvNum The juvNum to set.
	 */
	public void setJuvNum(String juvNum) {
		this.juvNum = juvNum;
	}
	/**
	 * @return Returns the hospitalizationList.
	 */
	public Collection getHospitalizationList() {
		return hospitalizationList;
	}
	/**
	 * @param hospitalizationList The hospitalizationList to set.
	 */
	public void setHospitalizationList(Collection hospitalizationList) {
		this.hospitalizationList = hospitalizationList;
	}
	/**
	 * @return Returns the actionType.
	 */
	public String getActionType() {
		return actionType;
	}
	/**
	 * @param actionType The actionType to set.
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
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
	 * @return Returns the admissionTypes.
	 */
	public Collection getAdmissionTypes() {
		return admissionTypes;
	}
	/**
	 * @param admissionTypes The admissionTypes to set.
	 */
	public void setAdmissionTypes(Collection admissionTypes) {
		this.admissionTypes = admissionTypes;
	}
	/**
	 * @return Returns the hospRec.
	 */
	public HospitalizationRecord getHospRec() {
		return hospRec;
	}
	/**
	 * @param hospRec The hospRec to set.
	 */
	public void setHospRec(HospitalizationRecord hospRec) {
		this.hospRec = hospRec;
	}
	public static class HospitalizationRecord{
		private String hospId ="";
		private String facilityName="";
		private String admissionDate="";
		private String admissionType="";
		private String admissionTypeCd="";
		private String releaseDate="";
		private IPhoneNumber physicianPhone= new PhoneNumber("");
		private String physicianPhoneStr = "";
		private String hospitalizationReason="";
		private String physicianLastName="";
		private String physicianFirstName="";
		private String physicianMiddleName="";
		private String physicianNameStr="";
		
		/**
		 * @return Returns the hospId.
		 */
		public String getHospId() {
			return hospId;
		}
		/**
		 * @param hospId The hospId to set.
		 */
		public void setHospId(String hospId) {
			this.hospId = hospId;
		}
		/**
		 * @return Returns the facilityName.
		 */
		public String getFacilityName() {
			return facilityName;
		}
		/**
		 * @param facilityName The facilityName to set.
		 */
		public void setFacilityName(String facilityName) {
			this.facilityName = facilityName;
		}
		/**
		 * @return Returns the admissionDate.
		 */
		public String getAdmissionDate() {
			return admissionDate;
		}
		/**
		 * @param admissionDate The admissionDate to set.
		 */
		public void setAdmissionDate(String admissionDate) {
			this.admissionDate = admissionDate;
		}
		/**
		 * @return Returns the releaseDate.
		 */
		public String getReleaseDate() {
			return releaseDate;
		}
		/**
		 * @param releaseDate The releaseDate to set.
		 */
		public void setReleaseDate(String releaseDate) {
			this.releaseDate = releaseDate;
		}
		/**
		 * @return Returns the hospitalizationReason.
		 */
		public String getHospitalizationReason() {
			return hospitalizationReason;
		}
		/**
		 * @param hospitalizationReason The hospitalizationReason to set.
		 */
		public void setHospitalizationReason(String hospitalizationReason) {
			this.hospitalizationReason = hospitalizationReason;
		}
		/**
		 * @return Returns the physicianFirstName.
		 */
		public String getPhysicianFirstName() {
			return physicianFirstName;
		}
		/**
		 * @param physicianFirstName The physicianFirstName to set.
		 */
		public void setPhysicianFirstName(String physicianFirstName) {
			this.physicianFirstName = physicianFirstName;
		}
		/**
		 * @return Returns the physicianLastName.
		 */
		public String getPhysicianLastName() {
			return physicianLastName;
		}
		/**
		 * @param physicianLastName The physicianLastName to set.
		 */
		public void setPhysicianLastName(String physicianLastName) {
			this.physicianLastName = physicianLastName;
		}
		/**
		 * @return Returns the physicianMiddleName.
		 */
		public String getPhysicianMiddleName() {
			return physicianMiddleName;
		}
		/**
		 * @param physicianMiddleName The physicianMiddleName to set.
		 */
		public void setPhysicianMiddleName(String physicianMiddleName) {
			this.physicianMiddleName = physicianMiddleName;
		}
		
		/**
		 * @return Returns the physicianPhone.
		 */
		public IPhoneNumber getPhysicianPhone() {
			return physicianPhone;
		}
		/**
		 * @param physicianPhone The physicianPhone to set.
		 */
		public void setPhysicianPhone(IPhoneNumber physicianPhone) {
			this.physicianPhone = physicianPhone;
		}
		/**
		 * @return Returns the admissionTypeCd.
		 */
		public String getAdmissionTypeCd() {
			return admissionTypeCd;
		}
		/**
		 * @param admissionTypeCd The admissionTypeCd to set.
		 */
		public void setAdmissionTypeCd(String admissionTypeCd) {
			this.admissionTypeCd = admissionTypeCd;
			if(admissionTypeCd != null || !"".equals(admissionTypeCd))
			{
				
			}
		}
		/**
		 * @return Returns the admissionType.
		 */
		public String getAdmissionType() {
			return admissionType;
		}
		/**
		 * @param admissionType The admissionType to set.
		 */
		public void setAdmissionType(String admissionType) {
			this.admissionType = admissionType;
		}
		/**
		 * @return Returns the physicianPhoneStr.
		 */
		public String getPhysicianPhoneStr() {
			return physicianPhoneStr;
		}
		/**
		 * @param physicianPhoneStr The physicianPhoneStr to set.
		 */
		public void setPhysicianPhoneStr(String physicianPhoneStr) {
			this.physicianPhoneStr = physicianPhoneStr;
		}
		/**
		 * @return Returns the physicianNameStr.
		 */
		public String getPhysicianNameStr() {
			return physicianNameStr;
		}
		/**
		 * @param physicianNameStr The physicianNameStr to set.
		 */
		public void setPhysicianNameStr(String physicianNameStr) {
			this.physicianNameStr = physicianNameStr;
		}
	}
	
	
}

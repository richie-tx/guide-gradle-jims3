package pd.supervision.cscdcalendar;

import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * @generated "UML to Java
 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 * @stereotype entity
 */
public class CSEventsReport extends mojo.km.persistence.PersistentObject {
	/**
	 * Properties for csevent
	 * @referencedType pd.supervision.cscdcalendar.CSEvent
	 * @detailerDoNotGenerate false
	 */
	private CSEvent csevent;
	
	/**
	 * Properties for cscdstaffposition
	 * @referencedType pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 * @detailerDoNotGenerate false
	 */
	private CSCDStaffPosition cscdstaffposition;
	
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated "UML to Java
	 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String csEventId;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated "UML to Java
	 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String eventTypeId;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated "UML to Java
	 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Date eventDate;
	
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated "UML to Java
	 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Date markedForDeleteOn;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated "UML to Java
	 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String outcomeCd;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated "UML to Java
	 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String positionId;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated "UML to Java
	 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String superviseeId;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated "UML to Java
	 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String resultUserId;
	
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated "UML to Java
	 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String resultPositionId;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated "UML to Java
	 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String resultPositionName;
	
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated "UML to Java
	 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String positionName;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated "UML to Java
	 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String eventTypeDesc;

	private String fvPurpose;
	
	private String fvType;
	
	private String sexOffenderType;
	
	private String contactMethod;
	
	private Date endTime;
	
	private Date startTime;
	
	private String status;
	
	/**
	 * Initialize class relationship to class
	 * pd.supervision.cscdcalendar.CSEvent
	 */
	private void initCsevent() {
		if (csevent == null) {
			csevent = (CSEvent) new mojo.km.persistence.Reference(
					csEventId, CSEvent.class)
					.getObject();
		}
	}

	/**
	 * Gets referenced type pd.supervision.cscdcalendar.CSEvent
	 */
	public CSEvent getCsevent() {
		fetch();
		initCsevent();
		return csevent;
	}

	/**
	 * set the type reference for class member csevent
	 */
	public void setCsevent(CSEvent csevent) {
		if (this.csevent == null || !this.csevent.equals(csevent)) {
			markModified();
		}
		if (csevent.getOID() == null) {
			new mojo.km.persistence.Home().bind(csevent);
		}
		setCsEventId(csevent.getOID());
		this.csevent = (CSEvent) new mojo.km.persistence.Reference(
				csevent).getObject();
	}
	
	/**
	 * Initialize class relationship to class
	 * pd.supervision.cscdcalendar.CSEvent
	 */
	private void initCscdstaffposition() {
		if (cscdstaffposition == null) {
			cscdstaffposition = (CSCDStaffPosition) new mojo.km.persistence.Reference(
					positionId, CSCDStaffPosition.class).getObject();
		}
	}

	
	/**
	 * 
	 * @return the cscdstaffposition
	 */
	public CSCDStaffPosition getCscdstaffposition() {
		fetch();
		initCscdstaffposition();
		return cscdstaffposition;
	}

	/**
	 * 
	 * @param cscdstaffposition the cscdstaffposition to set
	 */
	public void setCscdstaffposition(CSCDStaffPosition cscdstaffposition) {
		if (this.cscdstaffposition == null
				|| !this.cscdstaffposition.equals(cscdstaffposition)) {
			markModified();
		}
		
		if(cscdstaffposition.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(cscdstaffposition);
		}
		setPositionId(cscdstaffposition.getOID());
		
		this.cscdstaffposition = (CSCDStaffPosition)new mojo.km.persistence.Reference(cscdstaffposition).getObject();
	}
	
	/**
	 * @return the csEventId
	 */
	public String getCsEventId() {
		fetch();
		return csEventId;
	}

	/**
	 * @param csEventId
	 the csEventId to set
	 */
	public void setCsEventId(String csEventId) {
		if (this.csEventId == null || !this.csEventId.equals(csEventId)) {
			markModified();
		}
		this.csEventId = csEventId;
	}

	/**
	 * @return the eventTypeId
	 */
	public String getEventTypeId() {
		fetch();
		return eventTypeId;
	}

	/**
	 * @param eventTypeId the eventTypeId to set
	 */
	public void setEventTypeId(String eventTypeId) {
		if(this.eventTypeId == null || !this.eventTypeId.equals(eventTypeId))
		{
			markModified();
		}
		this.eventTypeId = eventTypeId;
	}
	
	/**
	 * @return the eventDate
	 */
	public Date getEventDate() {
		fetch();
		return eventDate;
	}

	/**
	 * @param eventDate
	 the eventDate to set
	 */
	public void setEventDate(Date eventDate) {
		if (this.eventDate == null || !this.eventDate.equals(eventDate)) {
			markModified();
		}
		this.eventDate = eventDate;
	}
	
	/**
	 * @return the markedForDeleteOn
	 */
	public Date getMarkedForDeleteOn() {
		fetch();
		return markedForDeleteOn;
	}

	/**
	 * @param markedForDeleteOn
	 the markedForDeleteOn to set
	 */
	public void setMarkedForDeleteOn(Date markedForDeleteOn) {
		if (this.markedForDeleteOn == null
				|| !this.markedForDeleteOn.equals(markedForDeleteOn)) {
			markModified();
		}
		this.markedForDeleteOn = markedForDeleteOn;
	}
	
	/**
	 * @return the outcome
	 */
	public String getOutcomeCd() {
		fetch();
		return outcomeCd;
	}

	/**
	 * @param outcome
	 the outcome to set
	 */
	public void setOutcomeCd(String outcomeCd) {
		if (this.outcomeCd == null || !this.outcomeCd.equals(outcomeCd)) {
			markModified();
		}
		this.outcomeCd = outcomeCd;
	}

	/**
	 * @return the positionId
	 */
	public String getPositionId() {
		fetch();
		return positionId;
	}
	
	/**
	 * @param positionId
	 the positionId to set
	 */
	public void setPositionId(String positionId) {
		if (this.positionId == null || !this.positionId.equals(positionId)) {
			markModified();
		}
		this.positionId = positionId;
	}
	
	/**
	 * @return the superviseeId
	 */
	public String getSuperviseeId() {
		fetch();
		return superviseeId;
	}

	/**
	 * @param superviseeId the superviseeId to set
	 */
	public void setSuperviseeId(String superviseeId) {
		if (this.superviseeId == null
				|| !this.superviseeId.equals(superviseeId)) {
			markModified();
		}
		this.superviseeId = superviseeId;
	}
	
	/**
	 * @return the resultUserId
	 */
	public String getresultUserId() {
		fetch();
		return resultUserId;
	}

	/**
	 * @param resultUserId
	 the resultUserId to set
	 */
	public void setresultUserId(String resultUserId) {
		if (this.resultUserId == null || !this.resultUserId.equals(resultUserId)) {
			markModified();
		}
		this.resultUserId = resultUserId;
	}
	
	/**
	 * @return the resultPositionId
	 */
	public String getResultPositionId() {
		fetch();
		return resultPositionId;
	}

	/**
	 * @param resultPositionId
	 the resultPositionId to set
	 */
	public void setResultPositionId(String resultPositionId) {
		if (this.resultPositionId == null
				|| !this.resultPositionId.equals(resultPositionId)) {
			markModified();
		}
		this.resultPositionId = resultPositionId;
	}
	
	/**
	 * @return the resultPositionName
	 */
	public String getResultPositionName() {
		fetch();
		return resultPositionName;
	}

	/**
	 * @param resultPositionName
	 the resultPositionName to set
	 */
	public void setResultPositionName(String resultPositionName) {
		if (this.resultPositionName == null
				|| !this.resultPositionName.equals(resultPositionName)) {
			markModified();
		}
		this.resultPositionName = resultPositionName;
	}
	
	/**
	 * @return the positionName
	 */
	public String getPositionName() {
		fetch();
		return positionName;
	}

	/**
	 * @param positionName
	 the positionName to set
	 */
	public void setPositionName(String positionName) {
		if (this.positionName == null
				|| !this.positionName.equals(positionName)) {
			markModified();
		}
		this.positionName = positionName;
	}
	
	/**
	 * @return the eventTypeDesc
	 */
	public String getEventTypeDesc() {
		fetch();
		return eventTypeDesc;
	}

	/**
	 * @param eventTypeDesc
	 the eventTypeDesc to set
	 */
	public void setEventTypeDesc(String eventTypeDesc) {
		if (this.eventTypeDesc == null
				|| !this.eventTypeDesc.equals(eventTypeDesc)) {
			markModified();
		}
		this.eventTypeDesc = eventTypeDesc;
	}
	
	/**
	 * @return the fvPurpose
	 */
	public String getFvPurpose() {
		return fvPurpose;
	}

	/**
	 * @param fvPurpose the fvPurpose to set
	 */
	public void setFvPurpose(String fvPurpose) {
		this.fvPurpose = fvPurpose;
	}

	/**
	 * @return the fvType
	 */
	public String getFvType() {
		return fvType;
	}

	/**
	 * @param fvType the fvType to set
	 */
	public void setFvType(String fvType) {
		this.fvType = fvType;
	}

	/**
	 * @return the sexOffenderType
	 */
	public String getSexOffenderType() {
		return sexOffenderType;
	}

	/**
	 * @param sexOffenderType the sexOffenderType to set
	 */
	public void setSexOffenderType(String sexOffenderType) {
		this.sexOffenderType = sexOffenderType;
	}

	/**
	 * @return the contactMethod
	 */
	public String getContactMethod() {
		return contactMethod;
	}
	
	/**
	 * @param contactMethod the contactMethod to set
	 */
	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
     * @return java.util.Iterator
     * @param attrName, attrValue
     * @roseuid 
     */
    static public Iterator findAll(String attrName, String attrValue) {
        IHome home = new Home();
        return home.findAll(attrName, attrValue, CSEventsReport.class);
    }

    /**
     * @return java.util.Iterator
     * @param event
     * @roseuid 
     */
    static public Iterator findAll(IEvent event) {
        IHome home = new Home();
        return (Iterator) home.findAll(event, CSEventsReport.class);
    }

    /**
     * @return CSEventReport
     * @param eventId
     * @roseuid 
     */
    static public CSEventsReport find(String eventId) {
        IHome home = new Home();
        return (CSEventsReport) home.find(eventId, CSEventsReport.class);
    }

    /**
     * 
     */
	public static Comparator CSEventsComparator = new Comparator() {
		public int compare(Object startDate, Object otherStartDate) {
			
		  int result = 0;
		  Date bStartDate = ((CSEventsReport)startDate).getEventDate();
		  Date bOtherStartDate = ((CSEventsReport)otherStartDate).getEventDate();
		  
		  if(bStartDate == null)
		  {
			  result = -1;
		  }
		  else if(bOtherStartDate == null)
		  {
			  result = -1;
		  }
		  else 
		  {
			  result = bStartDate.compareTo(bOtherStartDate);
		  }
		  return result;
		}	
	};
}

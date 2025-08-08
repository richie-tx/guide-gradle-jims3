package pd.supervision.administercasenotes;

import mojo.km.persistence.IHome;
import naming.PDCodeTableConstants;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.DateUtil;
import java.util.Collections;
import pd.supervision.supervisionorder.SupervisionOrder;
import mojo.km.messaging.EventFactory;
import mojo.km.dispatch.EventManager;
import messaging.administercasenotes.UpdateCasenoteEvent;
import naming.SupervisionConstants;
import messaging.administercasenotes.UpdateCasenoteStatusEvent;
import java.util.Calendar;
import java.util.List;
import mojo.km.utilities.Name;
import pd.supervision.supervisionorder.SupervisionPeriod;
import mojo.km.persistence.Home;
import naming.CasenoteControllerServiceNames;
import naming.CasenoteConstants;
import naming.PDConstants;
import pd.supervision.manageassociate.SuperviseeAssociate;
import java.util.Map;
import messaging.administercasenotes.domintf.ICasenote;
import java.util.HashMap;
import pd.security.PDSecurityHelper;
import java.util.Date;
import pd.contact.party.Party;
import messaging.administercasenotes.DeleteCasenoteEvent;
import pd.supervision.supervisionorder.SupervisionPeriodRedirect;
import messaging.scheduling.RegisterTaskEvent;
import messaging.supervisionorder.GetActiveSupervisionPeriodEvent;
import mojo.km.dispatch.IDispatch;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import pd.codetable.Code;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import mojo.km.messaging.IEvent;

/**
 * 
 * @return Casenote
 * @param orderId
 * @param caseNoteType
 * @param notes
 */
public class Casenote extends PersistentObject implements Comparable
{
	static private String DASH = "-";

	/**
	 * 
	 * @return Casenote
	 * @param orderId
	 * @param caseNoteType
	 * @param notes
	 */
	static public Casenote createOrderCasenote(String agencyId, String orderId, String superviseeId,
			String supervisionPeriodId, String notes)
	{
		Casenote casenote = new Casenote();
		ICasenote casenoteInput = new UpdateCasenoteEvent();
		//casenoteInput.setContextType(CasenoteContext.ORDER);
		casenoteInput.setContextType(PDCodeTableConstants.CASENOTE_TYPE_ID_ORDER_MODIFICATION);
		casenoteInput.setSupervisionOrderId(orderId);
		casenoteInput.setNotes(notes);
		casenoteInput.setEntryDate(DateUtil.getCurrentDate());
		casenoteInput.setHowGeneratedId(SupervisionConstants.HOW_GENERATED_BY_SYSTEM);
		Collection subjects = new ArrayList();
		SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(agencyId, PDCodeTableConstants.CASENOTE_SUBJECT,PDCodeTableConstants.CASENOTE_SUBJECT_UNSPECIFIED);
		if (aCode != null){
			subjects.add((String) aCode.getOID());
		}
		casenoteInput.setContactMethodId(PDCodeTableConstants.CASENOTE_CONTACT_METHOD_NONE);
		casenoteInput.setSubjects(subjects);
		casenoteInput.setNotes(notes);
		casenoteInput.setSuperviseeId(SpnCasenotesHandler.padSpn(superviseeId));
		casenoteInput.setSupervisionPeriodId(supervisionPeriodId);
		casenoteInput.setSupervisionOrderId(orderId);
		// this will set the fields for the new casenote
		//casenote.updateCasenote(casenoteInput);
		casenote.updateCasenote((UpdateCasenoteEvent) casenoteInput);
		return casenote;
	}

	/**
	 * 
	 * @return 
	 * @param theOid
	 */
	static public Casenote find(String theOid)
	{
		IHome home = new Home();
		return (Casenote) home.find(theOid, Casenote.class);
	}

	/**
	 * 
	 * @roseuid 44D79FFF0028
	 * @return java.util.Iterator
	 */
	static public Iterator findAll()
	{
		return new Home().findAll(Casenote.class);
	}

	/**
	 * 
	 * @roseuid 451840210342
	 * @return java.util.Iterator
	 * @param event
	 */
	static public Iterator findAll(IEvent event)
	{
		return new Home().findAll(event, Casenote.class);
	}

	/**
	 * @return 
	 * @param attributeName
	 * @param attributeValue
	 */
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		return home.findAll(attributeName, attributeValue, Casenote.class);
	}

	/**
	 * 
	 * @return 
	 * @param iter
	 */
	static public Iterator sortByEntryDate(Iterator iter)
	{
		// sorted return list descending by Entry Date
		List list = new ArrayList();
		while (iter.hasNext())
		{
			list.add(iter.next());
		}
		if (list.size() > 0)
		{
			Collections.sort(list);
		}
		return list.iterator();
	}
	
	/**
	 * Properties for associates
	 * @detailerDoNotGenerate true
	 * @referencedType pd.supervision.administercasenotes.SuperviseeAssociate
	 * @contextKey associates
	 * @associationType simple
	 */
	private Collection associates;
	private boolean autoSaved;
	private String casenoteStatusId;
	private String casenoteTypeCode;//This is not stored in the persistent object
	private String casenoteTypeId;
	/**
	 * Properties for collateral
	 */
	private Code collateral = null;

	private String collateralId;
	/**
	 * Properties for conditions
	 * @detailerDoNotGenerate true
	 * @referencedType pd.supervision.administercasenotes.SupervisionOrderCondition
	 * @contextKey conditions
	 * @associationType simple
	 */
	private Collection conditions;
	private String contactMethodId;
	private Date entryDate;
	private String howGeneratedId;
	private String notes;
	private String csEventId;
	private String programReferralId;
	private String migrateCreator;
	private Date createDate;

	/**
	 * Properties for subjects
	 * @detailerDoNotGenerate true
	 * @referencedType pd.codetable.supervision.SupervisionCode
	 * @contextKey subjects
	 * @associationType simple
	 */
	private Collection subjects;
	/**
	 * Properties for supervisee
	 * @useParent true
	 * @detailerDoNotGenerate true
	 */
	private String superviseeId;
	/**
	 * Properties for supervisionOrder
	 * @useParent true
	 * @detailerDoNotGenerate true private
	 */
	private SupervisionOrder supervisionOrder = null;
	private String supervisionOrderId;
	/**
	 * Properties for supervisionPeriod
	 * @useParent true
	 * @detailerDoNotGenerate true
	 */
	private SupervisionPeriod supervisionPeriod;
	private String supervisionPeriodId;
	/**
	 * 
	 * @roseuid 44EF1F0D0163
	 */
	public Casenote()
	{
	}
	/**
	 * Bind entity to database thus creating an OID
	 *
	 */
    public void bind()
    {
        IHome home = new Home();
        home.bind(this);
    }//end of bind()
	
	/**
	 * 
	 * @return 
	 * @param associateColl
	 */
	private Map buildAssociatesMap(Collection associateColl)
	{
		//markModified();
		/*
		 * Map map = new HashMap(); if (associateColl != null) { Iterator iter =
		 * associateColl.iterator(); SuperviseeAssociate associate = null; while
		 * (iter.hasNext()) { associate = (SuperviseeAssociate) iter.next();
		 * map.put(associate.getAssociateId(), associate.getAssociateId()); } }
		 * return map;
		 */
		Map map = new HashMap();
		if (associateColl != null)
		{
			Iterator iter = associateColl.iterator();
			CasenoteSuperviseeAssociates cnSupAssoc = null;
			Object obj = null;
			while (iter.hasNext())
			{
				obj = (Object) iter.next();
				if (obj instanceof CasenoteSuperviseeAssociates)
				{
					cnSupAssoc = (CasenoteSuperviseeAssociates) obj;
					map.put(cnSupAssoc.getChildId(), cnSupAssoc.getChildId());
				}
				else
				{
					map.put(obj.toString(), obj.toString());
				}
			}
		}
		return map;
	}
	
	/**
	 * 
	 * @return 
	 * @param subjectColl
	 */
	private Map buildSubjectsMap(Collection subjectColl)
	{
		//markModified();
		Map map = new HashMap();
		if (subjectColl != null)
		{
			Iterator iter = subjectColl.iterator();
			CasenoteSubjects cnSubj = null;
			Object obj = null;
			while (iter.hasNext())
			{
				obj = (Object) iter.next();
				if (obj instanceof CasenoteSubjects)
				{
					cnSubj = (CasenoteSubjects) obj;
					map.put(cnSubj.getChildId(), cnSubj.getChildId());
				}
				else
				{
					map.put(obj.toString(), obj.toString());
				}
			}
		}
		return map;
	}

	public int compareTo(Object arg0)
	{
		//markModified();
		Casenote cn = (Casenote) arg0;
		return cn.getEntryDate().compareTo(entryDate);
	}

	public Casenote copy(){
		Casenote casenote = new Casenote();
		casenote.setAutoSaved(this.getAutoSaved());
	    casenote.setCasenoteStatusId(this.getCasenoteStatusId());
	    casenote.setCasenoteTypeId(this.getCasenoteTypeId());
	    casenote.setCollateralId(this.getCollateralId());
	    casenote.setContactMethodId(this.getContactMethodId());
	    casenote.setEntryDate(this.getEntryDate());
	    casenote.setHowGeneratedId(this.getHowGeneratedId());
	    casenote.setNotes(this.getNotes());
	    casenote.setProgramReferralId(this.getprogramReferralId());
	    casenote.setCsEventId( this.getCsEventId() );
	    casenote.setSubjects(this.getSubjects());
	    casenote.setSuperviseeId(this.getSuperviseeId());
	    casenote.setSupervisionOrderId(this.getSupervisionOrderId());
	    casenote.setSupervisionPeriodId(this.getSupervisionPeriodId());
		return casenote;		
	}

	/**
	 */
	private String createDraftCasenoteMessage(Date aDate)
	{
		//markModified();
		Party party = Party.find(this.getSuperviseeId());
		Name superviseeName = new Name();
		if (party != null)
		{
			superviseeName.setFirstName(party.getFirstName());
			superviseeName.setLastName(party.getLastName());
			superviseeName.setMiddleName(party.getMiddleName());
		}
		StringBuffer sb = new StringBuffer("Draft casenote created on ");
		sb.append(DateUtil.dateToString(aDate, DateUtil.DATE_FMT_1));
		sb.append(" at ");
		sb.append(DateUtil.dateToString(aDate, DateUtil.TIME24_FMT_1));
		sb.append(" for ");
		sb.append(superviseeName.getFormattedName());
		sb.append(", ");
		sb.append(this.getSuperviseeId());
		sb.append(".  ");
		sb.append("If 24 hours has passed you will need to create a new casenote to add additional information.");
		return sb.toString();
	}

	/**
	 * 
	 * @param deleteEvent
	 */
	public void delete(DeleteCasenoteEvent deleteEvent)
	{
		//markModified();
		if (casenoteStatusId.equals(CasenoteConstants.STATUS_INACTIVE) || casenoteStatusId.equals(CasenoteConstants.STATUS_DRAFT))
		{
			this.delete();
		}
		else
		{
			// the casenote will be set to 'inactive' and then removed from the
			// system after 90 days.
			// set the status to 'Inactive'
			setCasenoteStatusId(CasenoteConstants.STATUS_INACTIVE);
			// now, shedule an Event to physically delete the casenote after 90
			// days
			RegisterTaskEvent rtEvent = new RegisterTaskEvent();
			rtEvent.setScheduleClassName(mojo.naming.CalendarConstants.ONCE_SCHEDULE_CLASS);
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, 90);
			Date deleteDate = calendar.getTime();
			// now set the date to do the physical delete
			rtEvent.setFirstNotificationDate(deleteDate);
			rtEvent.setNextNotificationDate(deleteDate);
			//rtEvent.setTaskName(deleteEvent.getClass().getName() +
			// deleteEvent.getCasenoteId() + deleteDate);
			StringBuffer taskName = new StringBuffer(deleteEvent.getClass().getName());
			taskName.append(DASH);
			taskName.append(deleteEvent.getCasenoteId());
			rtEvent.setTaskName(taskName.toString());
			rtEvent.setNotificationEvent(deleteEvent);
			EventManager.getSharedInstance(EventManager.REQUEST).postEvent(rtEvent);
		}
	}

	/**
	 * 
	 * @param aSubject
	 */
	public void deleteSubject(Code aSubject)
	{
		//markModified();
		initSubjects();
		try
		{
			mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
			assocEvent.setChildId((String) aSubject.getOID());
			assocEvent.setParentId((String) this.getOID());
			CasenoteSubjects actual = (CasenoteSubjects) new mojo.km.persistence.Reference(assocEvent,
					CasenoteSubjects.class).getObject();
			this.subjects.remove(actual);
		}
		catch (Throwable t)
		{
		}
	}

	/**
	 * 
	 * @roseuid 45183CF30008
	 * @param caseNote
	 */
	public void fillCasenote(ICasenote casenote)
	{
		casenote.setCasenoteId(getOID().toString());
		casenote.setCasenoteStatusId(getCasenoteStatusId());
		casenote.setCasenoteTypeId(getCasenoteTypeId());
		if (getCasenoteTypeCode() != null && !getCasenoteTypeCode().equals("")){
			casenote.setCasenoteTypeCodeId(getCasenoteTypeCode());
		} else if(getCasenoteTypeId() != null && !getCasenoteTypeId().equals("")){
			SupervisionCode code = SupervisionCode.find(getCasenoteTypeId());
			if(code != null){
				casenote.setCasenoteTypeCodeId(code.getCode());
			}
		}
		casenote.setCollateralId(getCollateralId());
		casenote.setContactMethodId(getContactMethodId());
		casenote.setEntryDate(getEntryDate());
		casenote.setMigrateCreator(getMigrateCreator());
		casenote.setHowGeneratedId(getHowGeneratedId());
		casenote.setCreatorId(getCreateUserID());
		casenote.setCreateDate(getCreateDate());
		casenote.setSuperviseeId(getSuperviseeId());
		casenote.setSupervisionPeriodId(getSupervisionPeriodId());
		casenote.setSupervisionOrderId(getSupervisionOrderId());
		// set all subjects for the casenote
		Collection subjects = new ArrayList();
		Iterator iter = getSubjects().iterator();
		if (iter != null && iter.hasNext())
		{
			while (iter.hasNext())
				
			{
				CasenoteSubjects aSubject = (CasenoteSubjects) iter.next();
				subjects.add(aSubject.getChildId());
			}
		}
		casenote.setSubjects(subjects);
		//      set all associates for the casenote
		Collection associatesList = new ArrayList();
		Iterator iterAssoc = getAssociates().iterator();
		StringBuffer sb = null;
		Name aName = null;
		SuperviseeAssociate associate = null;
		final String CONTACT = "Contact: ";
		final String RELATIONSHIP = " Relationship: ";
		final String BR = "<br/>";
		Code code = null;
		if (iterAssoc != null && iterAssoc.hasNext())
		{
			StringBuffer notesSb = new StringBuffer();
			while (iterAssoc.hasNext())
			{
				CasenoteSuperviseeAssociates aSupAssoc = (CasenoteSuperviseeAssociates) iterAssoc.next();
				associatesList.add(aSupAssoc.getChildId());
				sb = new StringBuffer(CONTACT);
				associate = aSupAssoc.getChild();
				if (associate != null)
				{
					aName = new Name();
					aName.setFirstName(associate.getFirstName());
					aName.setLastName(associate.getLastName());
					aName.setMiddleName(associate.getMiddleName());
					sb.append(aName.getFormattedName());
					sb.append(RELATIONSHIP);
					code = associate.getRelationshipType();
					if (code != null)
					{
						sb.append(code.getDescription());
					}
					sb.append(BR);
					notesSb.append(sb.toString());
				}
			}
			casenote.setCollateralInfo(notesSb.toString());
		}
		else if (this.getCollateralId() != null && !this.getCollateralId().equals(""))
		{
			code = this.getCollateral();
			sb = new StringBuffer(CONTACT);
			if (code != null){
			    sb.append(code.getDescription());
			} 
		    sb.append(BR);
		    casenote.setCollateralInfo(sb.toString());
		}
		casenote.setNotes(getNotes());
		casenote.setAssociates(associatesList);
		casenote.setAutoSaved(this.getAutoSaved());
	}
	
	/**
	 * 
	 * @roseuid 45183CF30008
	 * @param caseNote
	 */
	public void fillCasenote(ICasenote casenote, List subjects, List associateList)
	{
		casenote.setCasenoteId(getOID().toString());
		casenote.setCasenoteStatusId(getCasenoteStatusId());
		casenote.setCasenoteTypeId(getCasenoteTypeId());
		if (getCasenoteTypeCode() != null && !getCasenoteTypeCode().equals("")){
			casenote.setCasenoteTypeCodeId(getCasenoteTypeCode());
		} else if(getCasenoteTypeId() != null && !getCasenoteTypeId().equals("")){
			SupervisionCode code = SupervisionCode.find(getCasenoteTypeId());
			if(code != null){
				casenote.setCasenoteTypeCodeId(code.getCode());
			}
		}
		casenote.setCollateralId(getCollateralId());
		casenote.setContactMethodId(getContactMethodId());
		casenote.setEntryDate(getEntryDate());
		casenote.setHowGeneratedId(getHowGeneratedId());	
	    casenote.setMigrateCreator(getMigrateCreator());		
		casenote.setCreatorId(getCreateUserID());
		casenote.setCreateDate(getCreateDate());
		casenote.setSuperviseeId(getSuperviseeId());
		casenote.setSupervisionPeriodId(getSupervisionPeriodId());
		casenote.setSupervisionOrderId(getSupervisionOrderId());
		// set all subjects for the casenote
		casenote.setSubjects(subjects);
		StringBuffer sb = null;
		Name aName = null;
		SuperviseeAssociate associate = null;
		final String CONTACT = "Contact: ";
		final String RELATIONSHIP = " Relationship: ";
		final String BR = "<br/>";
		Code code = null;
		// set all associates for the casenote
		if(associateList == null){
			associateList = new ArrayList();
		}
		
		if (associateList != null && !associateList.isEmpty())
		{
			StringBuffer notesSb = new StringBuffer();
			//while (associates.iterator().hasNext())
			for(int i=0;i<associateList.size();i++){
				//CasenoteSuperviseeAssociates aSupAssoc = (CasenoteSuperviseeAssociates) associateList.get(i);
				String associateId = (String) associateList.get(i);
				//associates.add(aSupAssoc.getChildId());
				sb = new StringBuffer(CONTACT);
				//associate = aSupAssoc.getChild();
				
				associate = SuperviseeAssociate.find(associateId);
				
				if (associate != null)
				{
					aName = new Name();
					aName.setFirstName(associate.getFirstName());
					aName.setLastName(associate.getLastName());
					aName.setMiddleName(associate.getMiddleName());
					sb.append(aName.getFormattedName());
					sb.append(RELATIONSHIP);
					code = associate.getRelationshipType();
					if (code != null)
					{
						sb.append(code.getDescription());
					}
					sb.append(BR);					
					notesSb.append(sb.toString());
				}
			}
			casenote.setCollateralInfo(notesSb.toString());
		}
		else if (this.getCollateralId() != null && !this.getCollateralId().equals(""))
		{
			code = this.getCollateral();
			sb = new StringBuffer(CONTACT);
			if (code != null){
			    sb.append(code.getDescription());
			} 
		    sb.append(BR);
		    casenote.setCollateralInfo(sb.toString());
		}
		casenote.setNotes(getNotes());
		casenote.setAssociates(associateList);
		casenote.setAutoSaved(this.getAutoSaved());
	}
	
	

	public String getMigrateCreator() {
		fetch();
		return migrateCreator;
	}

	public void setMigrateCreator(String migrateCreator) {
		if (this.migrateCreator == null || !this.migrateCreator.equals(migrateCreator))
		{
			markModified();
		}
		this.migrateCreator = migrateCreator;
	}

	/**
	 * 
	 * @roseuid 4518402003D7
	 * @return Returns the collection of associates.
	 */
	public Collection getAssociates()
	{
		fetch();
		initAssociates();
		return associates;
	}

	/**
	 * 
	 * @return Returns the autoSaved.
	 */
	public boolean getAutoSaved()
	{
		fetch();
		return autoSaved;
	}

	/**
	 * 
	 * @roseuid 4507323102DC
	 * @return Returns the casenoteStatusId.
	 */
	public String getCasenoteStatusId()
	{
		fetch();
		return casenoteStatusId;
	}

	public String getCasenoteTypeCode() {
		return casenoteTypeCode;
	}

	/**
	 * 
	 * @roseuid 4507323102E6
	 * @return Returns the caseNoteTypeId.
	 */
	public String getCasenoteTypeId()
	{
		fetch();
		return casenoteTypeId;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getCollateral()
	{
		initCollateral();
		return collateral;
	}

	/**
	 * 
	 * @roseuid 4507323102F0
	 * @return Returns the collateralId.
	 */
	public String getCollateralId()
	{
		fetch();
		return collateralId;
	}

	/**
	 * 
	 * @roseuid 45184021003F
	 * @return Returns the collection of Conditions.
	 */
	public Collection getConditions()
	{
		Collection retVal = new ArrayList();
		Iterator i = CasenoteConditions.findAllByNumericParam("casenoteId", this.getOID());
		while (i.hasNext())
		{
			CasenoteConditions cc = (CasenoteConditions) i.next();
			retVal.add(cc);
		}
		return retVal;
	}

	/**
	 * 
	 * @roseuid 4507323102FA
	 * @return Returns the contactMethodId.
	 */
	public String getContactMethodId()
	{
		fetch();
		return contactMethodId;
	}

	/**
	 * 
	 * @roseuid 450732310318
	 * @return Returns the entryDate.
	 */
	public Date getEntryDate()
	{
		fetch();
		return entryDate;
	}
	
	/**
	 * 
	 * @return Returns the entryDate.
	 */
	public Date getCreateDate() {
		fetch();
		return createDate;
	}

	
	/**
	 * 
	 * @roseuid 450732310340
	 * @return Returns the howGeneratedid.
	 */
	public String getHowGeneratedId()
	{
		fetch();
		return howGeneratedId;
	}

	/**
	 * 
	 * @roseuid 45073231034A
	 * @return Returns the notes.
	 */
	public String getNotes()
	{
		fetch();
		return notes;
	}

	/**
	 * 
	 * @return
	 */
	public String getprogramReferralId()
	{
		fetch();
		return programReferralId;
	}

	/**
	 * 
	 * @return
	 */
	public String getCsEventId() {
		fetch();
		return csEventId;
	}
	/**
	 * 
	 * @param csEventId
	 */
	public void setCsEventId( String csEventId) {
		
		if (this.csEventId == null || !this.csEventId.equals(csEventId))
		{
			markModified();
		}
		this.csEventId = csEventId;
	}

	/**
	 * 
	 * @roseuid 451840200390
	 * @return Returns the subjectId.
	 */
	public Collection getSubjects()
	{
		fetch();
		initSubjects();
		//        Collection retVal = new java.util.ArrayList();
		//        java.util.Iterator i = subjects.iterator();
		//        while (i.hasNext()) {
		//            pd.supervision.administercasenotes.CasenoteSubjects actual =
		// (pd.supervision.administercasenotes.CasenoteSubjects) i
		//                    .next();
		//            retVal.add(actual.getChild());
		//        }
		//        return retVal;
		return subjects;
	}

	/**
	 * 
	 * @roseuid 45073231028B
	 * @return Returns the superviseeId.
	 */
	public String getSuperviseeId()
	{
		fetch();
		return superviseeId;
	}
	/**
	 * 
	 * @return Returns the supervisionOrder.
	 */
	public SupervisionOrder getSupervisionOrder()
	{
		fetch();
		initSupervisionOrder();
		return supervisionOrder;
	}
	
	/**
	 * 
	 * @return Returns the supervisionOrderId.
	 */
	public String getSupervisionOrderId()
	{
		fetch();
		return supervisionOrderId;
	}

	/**
	 * 
	 * @return Returns the supervisionPeriod.
	 */
	public SupervisionPeriod getSupervisionPeriod()
	{
		fetch();
		// this SupervisionPeriod needs to point to the modified
		// SupervisionPeriod (target) in SupervisionPeriodRedirect.
		// Whenever a reinstatement occurs and SupervisionPeriod is not current,
		// target in SupervisionPeriodRdirect is updated.
		SupervisionPeriodRedirect spRedirect = SupervisionPeriodRedirect.findBySourcePeriod(this.supervisionPeriodId);
		String targetSupervisionPeriodId = spRedirect.getTargetSupervisionPeriodId();
		if (supervisionPeriodId.equals(targetSupervisionPeriodId))
		{
			supervisionPeriod = (SupervisionPeriod) new mojo.km.persistence.Reference(
					supervisionPeriodId, SupervisionPeriod.class).getObject();
		}
		else
		{
			supervisionPeriod = SupervisionPeriod.find(targetSupervisionPeriodId);
		}
		return supervisionPeriod;
	}

	/**
	 * 
	 * @roseuid 450732310295
	 * @return Returns the supervisionPeriodId.
	 */
	public String getSupervisionPeriodId()
	{
		fetch();
		return supervisionPeriodId;
	}

	/**
	 * 
	 * @roseuid 4507323102C8
	 */
	private void initAssociates()
	{
		if (associates == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			associates = new mojo.km.persistence.ArrayList(
					CasenoteSuperviseeAssociates.class, "parentId", "" + getOID());
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initCollateral()
	{
		if (collateral == null)
		{
			collateral = (Code) new mojo.km.persistence.Reference(collateralId, Code.class, PDCodeTableConstants.CONTACT_WITH)
					.getObject();
		}
	}

	/**
	 * 
	 * @roseuid 45183CF2007F
	 */
	private void initConditions()
	{
		if (conditions == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			conditions = new mojo.km.persistence.ArrayList(
					pd.supervision.supervisionorder.SupervisionOrderCondition.class, "parentId", "" + getOID());
		}
	}

	/**
	 * 
	 * @roseuid 450732310281
	 */
	private void initSubjects()
	{
		if (subjects == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			subjects = new mojo.km.persistence.ArrayList(CasenoteSubjects.class,
					"parentId", "" + getOID());
		}
	}

	/**
	 * initializes the reference to
	 * pd.supervision.supervisionorder.SupervisionOrder
	 */
	private void initSupervisionOrder()
	{
		if (supervisionOrder == null)
		{
			supervisionOrder = (SupervisionOrder) new mojo.km.persistence.Reference(
					supervisionOrderId, SupervisionOrder.class,
					(mojo.km.persistence.PersistentObject) this, "supervisionOrder").getObject();
		}
	}

	/**
	 * 
	 * @roseuid 45183CF201BF
	 * @param anAssociate
	 The associate to add to the collection.
	 */
	public void insertAssociate(CasenoteSuperviseeAssociates anObject)
	{
		initAssociates();
		associates.add(anObject);
	}

	/**
	 * 
	 * @roseuid 4507323103A4
	 * @param aSubject
	 The subject to add.
	 */
	public void insertSubject(CasenoteSubjects anObject)
	{
		initSubjects();
		subjects.add(anObject);
	}

	private boolean isChanged(Object oldValue, Object newValue)
	{
		fetch();
		if ((oldValue == null && newValue != null) || (oldValue != null && !oldValue.equals(newValue)))
		{
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param casenote
	 */
	private void registerDraftCasenoteTasks()
	{
		//markModified();
		//Schedule an Event to physically change the status to complete after
		// 24 hours.
		RegisterTaskEvent rtEvent = new RegisterTaskEvent();
		rtEvent.setScheduleClassName(mojo.naming.CalendarConstants.ONCE_SCHEDULE_CLASS);
		UpdateCasenoteStatusEvent updateCasenoteStatusEvent = (UpdateCasenoteStatusEvent) EventFactory
				.getInstance(CasenoteControllerServiceNames.UPDATECASENOTESTATUS);
		String casenoteId = this.getOID().toString();
		updateCasenoteStatusEvent.setCasenoteId(casenoteId);
		updateCasenoteStatusEvent.setStatusId(CasenoteConstants.STATUS_COMPLETE);
		updateCasenoteStatusEvent.setAutoSaveAsDraft(true);
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date dueDate = calendar.getTime();
		rtEvent.setFirstNotificationDate(dueDate);
		rtEvent.setNextNotificationDate(dueDate);
		StringBuffer taskName = new StringBuffer(updateCasenoteStatusEvent.getClass().getName());
		taskName.append(DASH);
		taskName.append(casenoteId);
		taskName.append(DASH);
		taskName.append(CasenoteConstants.STATUS_COMPLETE);
		taskName.append(DASH);
		taskName.append(today);
		rtEvent.setTaskName(taskName.toString());
		rtEvent.setNotificationEvent(updateCasenoteStatusEvent);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(rtEvent);
		/*//Create Task to be viewed in task browser
		String logonId = PDSecurityHelper.getLogonId();
		HashMap map = new HashMap();
		map.put("submitAction", "Link");
		map.put("superviseeId", this.superviseeId);
		map.put("supervisionPeriodId", this.getSupervisionPeriodId());
		map.put("casenoteId", casenoteId);
		try
		{
			Task.createTask(logonId, CasenoteConstants.TASKDEF_UPDATE_DRAFT_CASENOTE, this
					.createDraftCasenoteMessage(today), map);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}*/
	}

	/**
	 * Removes a pd.supervision.administercasenotes.CasenoteSuperviseeAssociates
	 * from class relationship collection.
	 */
	public void removeAssociates(CasenoteSuperviseeAssociates anObject)
	{
		initAssociates();
		associates.remove(anObject);
	}

	/**
	 * Removes a pd.supervision.administercasenotes.CasenoteSubjects from class
	 * relationship collection.
	 */
	public void removeSubjects(CasenoteSubjects anObject)
	{
		initSubjects();
		subjects.remove(anObject);
	}

	/**
	 * 
	 * @return Sets the collection of associates.
	 */
	public void setAssociates(Collection associateCollection)
	{
		if (this.associates == null || !this.associates.equals(associateCollection))
		{
			markModified();
		}
		this.associates = associateCollection;
	}

	/**
	 * 
	 * @param autoSaved
	 The autoSaved to set.
	 */
	public void setAutoSaved(boolean b)
	{
		if (this.autoSaved != b)
		{
			markModified();
		}
		this.autoSaved = b;
	}

	/**
	 * 
	 * @roseuid 45073232008E
	 * @param aCasenoteStatusId
	 The caseNoteTypeId to set.
	 */
	public void setCasenoteStatusId(String aCasenoteStatusId)
	{
		if (isChanged(this.casenoteStatusId, aCasenoteStatusId))
		{
			markModified();
		}
		this.casenoteStatusId = aCasenoteStatusId;
	}

	public void setCasenoteTypeCode(String casenoteTypeCode) {
		this.casenoteTypeCode = casenoteTypeCode;
	}

	/**
	 * 
	 * @roseuid 4507323200B6
	 * @param aCasenoteTypeId
	 The casenoteTypeId to set.
	 */
	public void setCasenoteTypeId(String aCasenoteTypeId)
	{
		if (isChanged(this.casenoteTypeId, aCasenoteTypeId))
		{
			markModified();
		}
		this.casenoteTypeId = aCasenoteTypeId;
	}

	/**
	 * set the type reference for class member collateral
	 */
	public void setCollateral(Code collateral)
	{
		if (this.collateral == null || !this.collateral.equals(collateral))
		{
			markModified();
		}
		if (collateral.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(collateral);
		}
		setCollateralId("" + collateral.getOID());
		this.collateral = (Code) new mojo.km.persistence.Reference(collateral).getObject();
	}

	/**
	 * 
	 * @roseuid 4507323200D4
	 * @param aCollateralId
	 The collateralId to set.
	 */
	public void setCollateralId(String aCollateralId)
	{
		if (isChanged(this.collateralId, aCollateralId))
		{
			markModified();
		}
		this.collateralId = aCollateralId;
	}

	/**
	 * 
	 * @roseuid 4507323200F2
	 * @param aContactMethodId
	 The contactMethodId to set.
	 */
	public void setContactMethodId(String aContactMethodId)
	{
		if (isChanged(this.contactMethodId, aContactMethodId))
		{
			markModified();
		}
		this.contactMethodId = aContactMethodId;
	}

	/**
	 * 
	 * @roseuid 45073232012E
	 * @param entryDate
	 The entryDate to set.
	 */
	public void setEntryDate(Date entryDate)
	{
		if (isChanged(this.entryDate, entryDate))
		{
			markModified();
		}
		this.entryDate = entryDate;
	}
	
	/**
	 * 
	 * @roseuid 45073232012F
	 * @param createDate
	 The createDate to set.
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 
	 * @roseuid 45073232016B
	 * @param aHowGeneratedId
	 The howGeneratedid to set.
	 */
	public void setHowGeneratedId(String aHowGeneratedId)
	{
		if (isChanged(this.howGeneratedId, aHowGeneratedId))
		{
			markModified();
		}
		this.howGeneratedId = aHowGeneratedId;
	}

	/**
	 * 
	 * @roseuid 450732320189
	 * @param aNotes
	 The notes to set.
	 */
	public void setNotes(String aNotes)
	{
		if (isChanged(this.notes, aNotes))
		{
			markModified();
		}
		this.notes = aNotes;
	}

	/**
	 * 
	 * @param aProgramReferralId
	 */
	public void setProgramReferralId(String aProgramReferralId)
	{
		if (isChanged(this.programReferralId, aProgramReferralId))
		{
			markModified();
		}
		this.programReferralId = aProgramReferralId;
	}

//	/**
//	 * set the type reference for class member supervisionOrder
//	 */
//	public void setSupervisionOrder(pd.supervision.supervisionorder.SupervisionOrder supervisionOrder)
//	{
//		if (this.supervisionOrder == null || !this.supervisionOrder.equals(supervisionOrder))
//		{
//			markModified();
//		}
//		if (supervisionOrder.getOID() == null)
//		{
//			new mojo.km.persistence.Home().bind(supervisionOrder);
//		}
//		setSupervisionOrderId("" + supervisionOrder.getOID());
//		supervisionOrder.setContext(this, "supervisionOrder");
//		this.supervisionOrder = (pd.supervision.supervisionorder.SupervisionOrder) new mojo.km.persistence.Reference(
//				supervisionOrder).getObject();
//	}

	/**
	 * 
	 * @roseuid 45073231035E
	 * @param aSubjectCollection
	 The collection of subjectIds to set.
	 */
	public void setSubjects(Collection aSubjectCollection)
	{
		if (isChanged(this.subjects, aSubjectCollection))
		{
			markModified();
		}
		this.subjects = aSubjectCollection;
	}

	/**
	 * 
	 * @roseuid 450732320020
	 * @param superviseeId
	 The superviseeId to set.
	 * @param aSuperviseeId
	 */
	public void setSuperviseeId(String aSuperviseeId)
	{
		if (isChanged(this.superviseeId, aSuperviseeId))
		{
			markModified();
		}
		this.superviseeId = aSuperviseeId;
	}

	/**
	 * 
	 * @param supervisionOrder
	 The supervisionOrder to set.
	 */
	public void setSupervisionOrder(SupervisionOrder aSupervisionOrder)
	{
		if (isChanged(this.supervisionOrder, aSupervisionOrder))
		{
			markModified();
		}
		this.supervisionOrder = aSupervisionOrder;
		this.supervisionOrderId = aSupervisionOrder.getOID().toString();
	}
	
	/**
	 * 
	 * @param aSupervisionOrderId
	 The supervisionOrderId to set.
	 */
	public void setSupervisionOrderId(String aSupervisionOrderId)
	{
		if (isChanged(this.supervisionOrderId, aSupervisionOrderId))
		{
			markModified();
		}
		this.supervisionOrder = null;
		this.supervisionOrderId = aSupervisionOrderId;
	}
	
	/**
	 * 
	 * @roseuid 45073232003E
	 * @param aSupervisionPeriodId
	 The supervisionPeriodId to set.
	 */
	public void setSupervisionPeriodId(String aSupervisionPeriodId)
	{
		if (isChanged(this.supervisionPeriodId, aSupervisionPeriodId))
		{
			markModified();
		}
		this.supervisionPeriodId = aSupervisionPeriodId;
	}

	/**
	 * 
	 * @param associateColl
	 */
	private void updateAssociates(Collection associateColl)
	{
		Map existingSupAssocsMap = this.buildAssociatesMap(this.getAssociates());
		Map newSupAssocsMap = this.buildAssociatesMap(associateColl);
		Iterator iter = null;
		CasenoteSuperviseeAssociates cnSupAssoc = null;
		if (associateColl != null)
		{
			iter = associateColl.iterator();
			while (iter.hasNext())
			{
				//Add new associates
				String associateId = (String) iter.next();
				if (existingSupAssocsMap.get(associateId) == null)
				{
					cnSupAssoc = new CasenoteSuperviseeAssociates();
					cnSupAssoc.setChildId(associateId);
					this.insertAssociate(cnSupAssoc);
				}
			}
		}
		Map supAssocsToBeDeletedMap = new HashMap();
		if (this.getAssociates() != null && this.getAssociates().size() > 0)
		{
			iter = this.getAssociates().iterator();
			while (iter.hasNext())
			{
				//Removed associatees no longer selected
				cnSupAssoc = (CasenoteSuperviseeAssociates) iter.next();
				if (newSupAssocsMap.get(cnSupAssoc.getChildId()) == null)
				{
					supAssocsToBeDeletedMap.put(cnSupAssoc.getChildId(), cnSupAssoc);
				}
			}
		}
		Collection associatesToBeDeleted = supAssocsToBeDeletedMap.values();
		iter = associatesToBeDeleted.iterator();
		while (iter.hasNext())
		{
			cnSupAssoc = (CasenoteSuperviseeAssociates) iter.next();
			this.removeAssociates(cnSupAssoc);
		}
	}

	/**
	 * 
	 * @roseuid 44EE113A022F
	 * @param caseNote
	 */
	public void updateCasenote(UpdateCasenoteEvent updateCasenoteEvent)
	{
		setCollateralId(updateCasenoteEvent.getCollateralId());
		setContactMethodId(updateCasenoteEvent.getContactMethodId());
		setEntryDate(updateCasenoteEvent.getEntryDate());
		setHowGeneratedId(updateCasenoteEvent.getHowGeneratedId());
		setNotes( updateCasenoteEvent.getNotes() );
		setSuperviseeId(SpnCasenotesHandler.padSpn(updateCasenoteEvent.getSuperviseeId()));
		setSupervisionOrderId(updateCasenoteEvent.getSupervisionOrderId());
		if(updateCasenoteEvent.getSupervisionPeriodId() == null || "".equals(updateCasenoteEvent.getSupervisionPeriodId())){
			GetActiveSupervisionPeriodEvent gEvent = new GetActiveSupervisionPeriodEvent();
			gEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
			gEvent.setSpn(updateCasenoteEvent.getSuperviseeId());
			Iterator iterator = SupervisionPeriod.findAll(gEvent);
			if(iterator.hasNext()){
				SupervisionPeriod sp = (SupervisionPeriod) iterator.next();
				if(sp != null){
					updateCasenoteEvent.setSupervisionPeriodId(sp.getOID());
				}
			}
		}
		setSupervisionPeriodId(updateCasenoteEvent.getSupervisionPeriodId());
		setProgramReferralId(updateCasenoteEvent.getProgramReferralId());
		setCsEventId( updateCasenoteEvent.getCsEventId() );
		String casenoteId = updateCasenoteEvent.getCasenoteId();
		if (casenoteId == null || casenoteId.equals(""))
		{
			// new casenote; set type, context
			//String agencyId = updateCasenoteEvent.getAgencyId();
		    String agencyId = updateCasenoteEvent.getAgencyId();
		    if (agencyId == null || agencyId.equals(PDConstants.BLANK)){
		        agencyId = PDSecurityHelper.getUserAgencyId();
		    }
		    SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(agencyId, PDCodeTableConstants.CASENOTE_TYPE, updateCasenoteEvent.getContextType());
		    if(aCode != null){
		    	setCasenoteTypeId((String)aCode.getOID());
		    }else if(StringUtils.isNotEmpty(updateCasenoteEvent.getCasenoteTypeCodeId()) ){
				setCasenoteTypeId(updateCasenoteEvent.getCasenoteTypeCodeId());
		    }
		}
		if (updateCasenoteEvent.isSaveAsDraft())
		{
			setCasenoteStatusId(CasenoteConstants.STATUS_DRAFT);
		}
		else
		{
			setCasenoteStatusId(CasenoteConstants.STATUS_COMPLETE);
		}
		this.updateSubjects(updateCasenoteEvent.getSubjects());
		this.updateAssociates(updateCasenoteEvent.getAssociates());

		if (updateCasenoteEvent.isSaveAsDraft())
		{
			this.registerDraftCasenoteTasks();
		}
		setCreateDate(updateCasenoteEvent.getCreateDate());
	}
	
	/**
	 * 
	 * @param subjectColl
	 */
	private void updateSubjects(Collection subjectColl)
	{
		//Casenote OID is generated when child objects are inserted.
		//Subjects are required so we're safe to not do a hard bind.
		Map existingSubjectsMap = this.buildSubjectsMap(this.getSubjects());
		Map newSubjectsMap = this.buildSubjectsMap(subjectColl);
		Iterator iter = null;
		CasenoteSubjects cnSubject = null;
		if (subjectColl != null)
		{
			iter = subjectColl.iterator();
			while (iter.hasNext())
			{
				//Add new subjects
				String subjectId = (String) iter.next();
				if (existingSubjectsMap.get(subjectId) == null)
				{
					cnSubject = new CasenoteSubjects();
					cnSubject.setChildId(subjectId);
					this.insertSubject(cnSubject);
				}
			}
		}
		Map subjectsToBeDeletedMap = new HashMap();
		if (this.getSubjects() != null && this.getSubjects().size() > 0)
		{
			iter = this.getSubjects().iterator();
			while (iter.hasNext())
			{
				//Removed subjects no longer selected
				cnSubject = (CasenoteSubjects) iter.next();
				if (newSubjectsMap.get(newSubjectsMap.get(cnSubject.getChildId())) == null)
				{
					subjectsToBeDeletedMap.put(cnSubject.getChildId(), cnSubject);
				}
			}
		}
		Collection subjectsToBeDeleted = subjectsToBeDeletedMap.values();
		iter = subjectsToBeDeleted.iterator();
		while (iter.hasNext())
		{
			cnSubject = (CasenoteSubjects) iter.next();
			this.removeSubjects(cnSubject);
		}
	}
	
}

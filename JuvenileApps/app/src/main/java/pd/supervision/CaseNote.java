package pd.supervision;

import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.supervision.supervisionorder.SupervisionOrder;

/**
 * @deprecated  Use pd.supervision.administercasenotes.Casenote class instead.
 * @roseuid 4421A78A030A
 */
public class CaseNote extends PersistentObject
{
	private String supervisionOrderId;
	private String notes;
	/**
	* Properties for caseNoteType
	*/
	private Code caseNoteType = null;
	/**
	* Properties for supervisionOrder
	*/
	private SupervisionOrder supervisionOrder = null;
	private String caseNoteTypeId;
	/**
	* @roseuid 4421A78A030A
	*/
	public CaseNote()
	{
	}
	/**
	* @roseuid 442071B602F6
	*/
	public void bind()
	{
		markModified();
	}
	/**
	* @return 
	*/
	public Code getCaseNoteType()
	{
		initCaseNoteType();
		return caseNoteType;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getCaseNoteTypeId()
	{
		fetch();
		return caseNoteTypeId;
	}
	/**
	* @return 
	*/
	public String getNotes()
	{
		fetch();
		return notes;
	}
	/**
	* @return 
	*/
	public SupervisionOrder getSupervisionOrder()
	{
		initSupervisionOrder();
		return supervisionOrder;
	}
	/**
	* Get the reference value to class :: pd.supervision.supervisionorder.SupervisionOrder
	*/
	public String getSupervisionOrderId()
	{
		fetch();
		return supervisionOrderId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initCaseNoteType()
	{
		if (caseNoteType == null)
		{
			caseNoteType =
				(Code) new mojo
					.km
					.persistence
					.Reference(caseNoteTypeId, Code.class)
					.getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.supervision.supervisionorder.SupervisionOrder
	*/
	private void initSupervisionOrder()
	{
		if (supervisionOrder == null)
		{
			supervisionOrder =
				(SupervisionOrder) new mojo
					.km
					.persistence
					.Reference(supervisionOrderId, SupervisionOrder.class)
					.getObject();
		}
	}
	/**
	* set the type reference for class member caseNoteType
	*/
	public void setCaseNoteType(Code caseNoteType)
	{
		if (this.caseNoteType == null || !this.caseNoteType.equals(caseNoteType))
		{
			markModified();
		}
		if (caseNoteType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(caseNoteType);
		}
		setCaseNoteTypeId("" + caseNoteType.getOID());
		this.caseNoteType = (Code) new mojo.km.persistence.Reference(caseNoteType).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setCaseNoteTypeId(String caseNoteTypeId)
	{
		if (this.caseNoteTypeId == null || !this.caseNoteTypeId.equals(caseNoteTypeId))
		{
			markModified();
		}
		caseNoteType = null;
		this.caseNoteTypeId = caseNoteTypeId;
	}
	/**
	* @param string
	*/
	public void setNotes(String string)
	{
		if (this.notes == null || !this.notes.equals(string))
		{
			markModified();
		}
		notes = string;
	}
	/**
	* set the type reference for class member supervisionOrder
	*/
	public void setSupervisionOrder(SupervisionOrder supervisionOrder)
	{
		if (this.supervisionOrder == null || !this.supervisionOrder.equals(supervisionOrder))
		{
			markModified();
		}
		if (supervisionOrder.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(supervisionOrder);
		}
		setSupervisionOrderId("" + supervisionOrder.getOID());
		this.supervisionOrder =
			(SupervisionOrder) new mojo
				.km
				.persistence
				.Reference(supervisionOrder)
				.getObject();
	}
	/**
	* Set the reference value to class :: pd.supervision.supervisionorder.SupervisionOrder
	*/
	public void setSupervisionOrderId(String supervisionOrderId)
	{
		if (this.supervisionOrderId == null || !this.supervisionOrderId.equals(supervisionOrderId))
		{
			markModified();
		}
		supervisionOrder = null;
		this.supervisionOrderId = supervisionOrderId;
	}
	/**
	 * @param orderId
	 * @param caseNoteType
	 * @param notes
	 * @return
	 */
	public static CaseNote create(String orderId, String caseNoteType, String notes)
	{
		CaseNote caseNote = new CaseNote();
		caseNote.setCaseNoteTypeId(caseNoteType);
		caseNote.setNotes(notes);
		caseNote.setSupervisionOrderId(orderId);
		return caseNote;
	}

}

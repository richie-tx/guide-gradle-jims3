/*
 * Created on Sep 11, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.codetable.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import messaging.codetable.reply.CodetableAttributeResponseEvent;
import messaging.codetable.reply.CodetableCompoundListResponseEvent;
import messaging.codetable.reply.CodetableDataResponseEvent;
import messaging.codetable.reply.CodetableRecordResponseEvent;
import messaging.codetable.reply.CodetableRegistrationAttributesAndTypesResponseEvent;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ui.security.form.GenericSecurityForm;

/**
 * @author cc_nnagaraju
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class CodetableRegistrationForm extends GenericSecurityForm
{

	private String promptCodetableRegisterName;

	private String searchResultsCount;

	// opType possible values: create, update, view
	// opType, paired with opStatus (in Request) will create Page Title in JSP
	private String opType;

	private String opStatus;

	private String codetableRegId;

	private String codetableType;

	private String codetableEntityName;

	private String codetableContextKey;

	private String codetableName;

	private String codetableDescription;

	private String codetableTypeOrig;

	private String codetableEntityNameOrig;

	private String codetableContextKeyOrig;

	private String codetableNameOrig;

	private String codetableDescriptionOrig;

	private Collection codetables;

	private Collection codetableAttributes;

	private Collection codetableAttributesOriginal;

	private Collection codetableTypeList;

	private Collection codetableContextOrEntityList;

	private String selectedContextOrEntity;

	private String searchType;

	private boolean showAttributes = true;

	private boolean attributesUpdated = false;

	private Collection addAttributeList;

	private Collection addAttrDataTypeList;

	private CodetableRegistrationAttributesAndTypesResponseEvent attributeTypeSelected;

	private String attributeSelected;

	private String editAttribute;

	private CodetableAttributeResponseEvent editAttributeEvent;

	private String attributeName;

	private String attrDisplayName;

	private String attributeDataType;

	private int attrMaxLength;

	private int attrMinLength;

	private String attrCodetableType;

	private String compoundAttrID;

	private String compoundAttrName;

	private String compoundAttrContextKey;

	private String compoundAttrEntityName;

	private boolean attrRequired = false;

	private boolean attrAudit = false;

	private boolean attrUnique = false;

	private boolean attrUpdatable = false;

	private boolean attrCompound = false;

	private boolean attrVerification = false;

	private boolean showAddAttributes = true;

	private boolean attrNumeric = false;

	private boolean showResequenceButton = false;

	private String resequencedOrderValue;

	private String attrFinishPage;

	private String action;

	private String agencyName;

	private String agencySearchResultSize;

	private String agencyCode;

	private String agencyId;

	private String[] selectedAgencies;

	public String[] getSelectedAgencies()
	{
		return selectedAgencies;
	}

	public void setSelectedAgencies(String[] selectedAgencies)
	{
		this.selectedAgencies = selectedAgencies;
	}

	public String getAgencyId()
	{
		return agencyId;
	}

	public void setAgencyId(String agencyId)
	{
		this.agencyId = agencyId;
	}

	/**
	 * @return Returns the showAttributes.
	 */
	public boolean isShowAttributes()
	{
		return showAttributes;
	}

	/**
	 * @return Returns the opStatus.
	 */
	public String getOpStatus()
	{
		return opStatus;
	}

	/**
	 * @param opStatus
	 *            The opStatus to set.
	 */
	public void setOpStatus(String opStatus)
	{
		this.opStatus = opStatus;
	}

	/**
	 * @param showAttributes
	 *            The showAttributes to set.
	 */
	public void setShowAttributes(boolean showAttributes)
	{
		this.showAttributes = showAttributes;
	}

	/**
	 * @return Returns the searchType.
	 */
	public String getSearchType()
	{
		return searchType;
	}

	/**
	 * @param searchType
	 *            The searchType to set.
	 */
	public void setSearchType(String searchType)
	{
		this.searchType = searchType;
	}

	/**
	 * @return Returns the selectedContextOrEntity.
	 */
	public String getSelectedContextOrEntity()
	{
		return selectedContextOrEntity;
	}

	/**
	 * @param selectedContextOrEntity
	 *            The selectedContextOrEntity to set.
	 */
	public void setSelectedContextOrEntity(String selectedContextOrEntity)
	{
		this.selectedContextOrEntity = selectedContextOrEntity;
	}

	/**
	 * @return Returns the codetableContextKeyOrig.
	 */
	public String getCodetableContextKeyOrig()
	{
		return codetableContextKeyOrig;
	}

	/**
	 * @param codetableContextKeyOrig
	 *            The codetableContextKeyOrig to set.
	 */
	public void setCodetableContextKeyOrig(String codetableContextKeyOrig)
	{
		this.codetableContextKeyOrig = codetableContextKeyOrig;
	}

	/**
	 * @return Returns the codetableContextOrEntityList.
	 */
	public Collection getCodetableContextOrEntityList()
	{
		return codetableContextOrEntityList;
	}

	/**
	 * @param codetableContextOrEntityList
	 *            The codetableContextOrEntityList to set.
	 */
	public void setCodetableContextOrEntityList(Collection codetableContextOrEntityList)
	{
		this.codetableContextOrEntityList = codetableContextOrEntityList;
	}

	/**
	 * @return Returns the opStaus.
	 */

	private boolean contextKeyListDisabled;

	private boolean entityListDisabled;

	public void clear()
	{
		this.promptCodetableRegisterName = "";
		this.searchResultsCount = "";
		this.codetableRegId = "";
		this.codetableDescription = "";
		this.codetableType = "";
		this.codetableEntityName = "";
		this.codetableContextKey = "";
		this.codetableName = "";
		this.codetableDescriptionOrig = "";
		this.codetableTypeOrig = "";
		this.codetableEntityNameOrig = "";
		this.codetableContextKeyOrig = "";
		this.codetableNameOrig = "";
		this.opType = "";
		this.opStatus = "";
		this.contextKeyListDisabled = true;
		this.entityListDisabled = true;
		this.agencyCode = null;
		this.agencyId = null;

		Collection emptyCol = new ArrayList();
		this.codetableAttributes = emptyCol;
		this.codetables = emptyCol;
		this.codetableTypeList = emptyCol;

	}

	public void resetForUpdate()
	{
		this.codetableName = this.codetableNameOrig;
		this.codetableDescription = this.codetableDescriptionOrig;
		this.codetableType = this.codetableTypeOrig;
		this.codetableEntityName = this.codetableEntityNameOrig;
		this.codetableContextKey = this.codetableContextKeyOrig;
		this.opType = "update";
		this.attributesUpdated = false;
	}

	public void resetForCreate()
	{
		this.codetableNameOrig = "";
		this.codetableDescriptionOrig = "";
		this.codetableTypeOrig = "";
		this.codetableEntityNameOrig = "";
		this.codetableContextKeyOrig = "";
		this.codetableName = "";
		this.codetableDescription = "";
		this.codetableType = "";
		this.codetableEntityName = "";
		this.codetableContextKey = "";
		this.opType = "create";
		this.attributesUpdated = false;
	}

	public void reset(ActionMapping aMapping, HttpServletRequest aRequest)
	{
		super.reset(aMapping, aRequest);
		this.setAttrAudit(false);
		this.setAttrRequired(false);
		this.setAttrUpdatable(false);
		// this.setAttrNumeric(false);
		this.setAttrCompound(false);
		this.setAttrUnique(false);
		this.setAttrVerification(false);
		this.setAttrCompound(false);
	}

	public void resetForAttributeUpdate()
	{
		this.setAttrDisplayName(null);
		this.setAttributeSelected(null);
		this.setAttrAudit(false);
		this.setAttrRequired(false);
		this.setCompoundAttrID(null);
		this.setCompoundAttrName(null);
		this.setCompoundAttrEntityName(null);
		this.setCompoundAttrContextKey(null);
		this.setAttrUpdatable(false);
		this.setAttrCodetableType(null);
		this.setAttrNumeric(false);
		this.setAttrMaxLength(1);
		this.setAttrMinLength(1);
		this.setAttrUnique(false);
		this.setAttrVerification(false);
	}

	/**
	 * @return Returns the codetableAttributes.
	 */
	public Collection getCodetableAttributes()
	{
		return codetableAttributes;
	}

	/**
	 * @param codetableAttributes
	 *            The codetableAttributes to set.
	 */
	public void setCodetableAttributes(Collection codetableAttributes)
	{
		this.codetableAttributes = codetableAttributes;
	}

	/**
	 * @return Returns the codetableContextKey.
	 */
	public String getCodetableContextKey()
	{
		return codetableContextKey;
	}

	/**
	 * @param codetableContextKey
	 *            The codetableContextKey to set.
	 */
	public void setCodetableContextKey(String codetableContextKey)
	{
		this.codetableContextKey = codetableContextKey;
	}

	/**
	 * @return Returns the codetableEntityName.
	 */
	public String getCodetableEntityName()
	{
		return codetableEntityName;
	}

	/**
	 * @param codetableEntityName
	 *            The codetableEntityName to set.
	 */
	public void setCodetableEntityName(String codetableEntityName)
	{
		this.codetableEntityName = codetableEntityName;
	}

	/**
	 * @return Returns the codetableRegId.
	 */
	public String getCodetableRegId()
	{
		return codetableRegId;
	}

	/**
	 * @param codetableRegId
	 *            The codetableRegId to set.
	 */
	public void setCodetableRegId(String codetableRegId)
	{
		this.codetableRegId = codetableRegId;
	}

	/**
	 * @return Returns the codetableName.
	 */
	public String getCodetableName()
	{
		return codetableName;
	}

	/**
	 * @param codetableRegisterName
	 *            The codetableName to set.
	 */
	public void setCodetableName(String codetableName)
	{
		this.codetableName = codetableName;
	}

	/**
	 * @return Returns the codetables.
	 */
	public Collection getCodetables()
	{
		return codetables;
	}

	/**
	 * @param codetables
	 *            The codetables to set.
	 */
	public void setCodetables(Collection codetables)
	{
		this.codetables = codetables;
	}

	/**
	 * @return Returns the codetableType.
	 */
	public String getCodetableType()
	{
		return codetableType;
	}

	/**
	 * @param codetableType
	 *            The codetableType to set.
	 */
	public void setCodetableType(String codetableType)
	{
		this.codetableType = codetableType;
	}

	/**
	 * @return Returns the promptCodetableRegisterName.
	 */
	public String getPromptCodetableRegisterName()
	{
		return promptCodetableRegisterName;
	}

	/**
	 * @param promptCodetableRegisterName
	 *            The promptCodetableRegisterName to set.
	 */
	public void setPromptCodetableRegisterName(String promptCodetableRegisterName)
	{
		this.promptCodetableRegisterName = promptCodetableRegisterName;
	}

	/**
	 * @return Returns the searchResultsCount.
	 */
	public String getSearchResultsCount()
	{
		return searchResultsCount;
	}

	/**
	 * @param searchResultsCount
	 *            The searchResultsCount to set.
	 */
	public void setSearchResultsCount(String searchResultsCount)
	{
		this.searchResultsCount = searchResultsCount;
	}

	/**
	 * @return Returns the codetableDescription.
	 */
	public String getCodetableDescription()
	{
		return codetableDescription;
	}

	/**
	 * @param codetableDescription
	 *            The codetableDescription to set.
	 */
	public void setCodetableDescription(String codetableDescription)
	{
		this.codetableDescription = codetableDescription;
	}

	/**
	 * @return Returns the opType.
	 */
	public String getOpType()
	{
		return opType;
	}

	/**
	 * @param opType
	 *            The opType to set.
	 */
	public void setOpType(String opType)
	{
		this.opType = opType;
	}

	/**
	 * @return Returns the codetableTypeList.
	 */
	public Collection getCodetableTypeList()
	{
		return codetableTypeList;
	}

	/**
	 * @param codetableTypeList
	 *            The codetableTypeList to set.
	 */
	public void setCodetableTypeList(Collection codetableTypeList)
	{
		this.codetableTypeList = codetableTypeList;
	}

	/**
	 * @return Returns the contextKeyListDisabled.
	 */
	public boolean isContextKeyListDisabled()
	{
		return contextKeyListDisabled;
	}

	/**
	 * @param contextKeyListDisabled
	 *            The contextKeyListDisabled to set.
	 */
	public void setContextKeyListDisabled(boolean contextKeyListDisabled)
	{
		this.contextKeyListDisabled = contextKeyListDisabled;
	}

	/**
	 * @return Returns the codetableDescriptionOrig.
	 */
	public String getCodetableDescriptionOrig()
	{
		return codetableDescriptionOrig;
	}

	/**
	 * @param codetableDescriptionOrig
	 *            The codetableDescriptionOrig to set.
	 */
	public void setCodetableDescriptionOrig(String codetableDescriptionOrig)
	{
		this.codetableDescriptionOrig = codetableDescriptionOrig;
	}

	/**
	 * @return Returns the codetableEntityNameOrig.
	 */
	public String getCodetableEntityNameOrig()
	{
		return codetableEntityNameOrig;
	}

	/**
	 * @param codetableEntityNameOrig
	 *            The codetableEntityNameOrig to set.
	 */
	public void setCodetableEntityNameOrig(String codetableEntityNameOrig)
	{
		this.codetableEntityNameOrig = codetableEntityNameOrig;
	}

	/**
	 * @return Returns the codetableNameOrig.
	 */
	public String getCodetableNameOrig()
	{
		return codetableNameOrig;
	}

	/**
	 * @param codetableNameOrig
	 *            The codetableNameOrig to set.
	 */
	public void setCodetableNameOrig(String codetableNameOrig)
	{
		this.codetableNameOrig = codetableNameOrig;
	}

	/**
	 * @return Returns the codetableTypeOrig.
	 */
	public String getCodetableTypeOrig()
	{
		return codetableTypeOrig;
	}

	/**
	 * @param codetableTypeOrig
	 *            The codetableTypeOrig to set.
	 */
	public void setCodetableTypeOrig(String codetableTypeOrig)
	{
		this.codetableTypeOrig = codetableTypeOrig;
	}

	/**
	 * @return Returns the entityListDisabled.
	 */
	public boolean isEntityListDisabled()
	{
		return entityListDisabled;
	}

	/**
	 * @param entityListDisabled
	 *            The entityListDisabled to set.
	 */
	public void setEntityListDisabled(boolean entityListDisabled)
	{
		this.entityListDisabled = entityListDisabled;
	}

	/**
	 * @return Returns the attributesUpdated.
	 */
	public boolean isAttributesUpdated()
	{
		return attributesUpdated;
	}

	/**
	 * @param attributesUpdated
	 *            The attributesUpdated to set.
	 */
	public void setAttributesUpdated(boolean attributesUpdated)
	{
		this.attributesUpdated = attributesUpdated;
	}

	/**
	 * @return Returns the addAttrDataTypeList.
	 */
	public Collection getAddAttrDataTypeList()
	{
		return addAttrDataTypeList;
	}

	/**
	 * @param addAttrDataTypeList
	 *            The addAttrDataTypeList to set.
	 */
	public void setAddAttrDataTypeList(Collection addAttrDataTypeList)
	{
		this.addAttrDataTypeList = addAttrDataTypeList;
	}

	/**
	 * @return Returns the addAttributeList.
	 */
	public Collection getAddAttributeList()
	{
		return addAttributeList;
	}

	/**
	 * @param addAttributeList
	 *            The addAttributeList to set.
	 */
	public void setAddAttributeList(Collection addAttributeList)
	{
		this.addAttributeList = addAttributeList;
	}

	/**
	 * @return Returns the attrAudit.
	 */
	public boolean isAttrAudit()
	{
		return attrAudit;
	}

	/**
	 * @param attrAudit
	 *            The attrAudit to set.
	 */
	public void setAttrAudit(boolean attrAudit)
	{
		this.attrAudit = attrAudit;
	}

	/**
	 * @return Returns the attrCodetableType.
	 */
	public String getAttrCodetableType()
	{
		return attrCodetableType;
	}

	/**
	 * @param attrCodetableType
	 *            The attrCodetableType to set.
	 */
	public void setAttrCodetableType(String attrCodetableType)
	{
		this.attrCodetableType = attrCodetableType;
	}

	/**
	 * @return Returns the attrCompound.
	 */
	public boolean isAttrCompound()
	{
		return attrCompound;
	}

	/**
	 * @param attrCompound
	 *            The attrCompound to set.
	 */
	public void setAttrCompound(boolean attrCompound)
	{
		this.attrCompound = attrCompound;
	}

	/**
	 * @return Returns the attrDisplayName.
	 */
	public String getAttrDisplayName()
	{
		return attrDisplayName;
	}

	/**
	 * @param attrDisplayName
	 *            The attrDisplayName to set.
	 */
	public void setAttrDisplayName(String attrDisplayName)
	{
		this.attrDisplayName = attrDisplayName;
	}

	/**
	 * @return Returns the attributeDataType.
	 */
	public String getAttributeDataType()
	{
		return attributeDataType;
	}

	/**
	 * @param attributeDataType
	 *            The attributeDataType to set.
	 */
	public void setAttributeDataType(String attributeDataType)
	{
		this.attributeDataType = attributeDataType;
	}

	/**
	 * @return Returns the attributeName.
	 */
	public String getAttributeName()
	{
		return attributeName;
	}

	/**
	 * @param attributeName
	 *            The attributeName to set.
	 */
	public void setAttributeName(String attributeName)
	{
		this.attributeName = attributeName;
	}

	/**
	 * @return Returns the attrMaxength.
	 */
	public int getAttrMaxLength()
	{
		return attrMaxLength;
	}

	/**
	 * @param attrMaxength
	 *            The attrMaxength to set.
	 */
	public void setAttrMaxLength(int attrMaxLength)
	{
		this.attrMaxLength = attrMaxLength;
	}

	/**
	 * @return Returns the attrMinLength.
	 */
	public int getAttrMinLength()
	{
		return attrMinLength;
	}

	/**
	 * @param attrMinLength
	 *            The attrMinLength to set.
	 */
	public void setAttrMinLength(int attrMinLength)
	{
		this.attrMinLength = attrMinLength;
	}

	/**
	 * @return Returns the attrRequired.
	 */
	public boolean isAttrRequired()
	{
		return attrRequired;
	}

	/**
	 * @param attrRequired
	 *            The attrRequired to set.
	 */
	public void setAttrRequired(boolean attrRequired)
	{
		this.attrRequired = attrRequired;
	}

	/**
	 * @return Returns the attrUnique.
	 */
	public boolean isAttrUnique()
	{
		return attrUnique;
	}

	/**
	 * @param attrUnique
	 *            The attrUnique to set.
	 */
	public void setAttrUnique(boolean attrUnique)
	{
		this.attrUnique = attrUnique;
	}

	/**
	 * @return Returns the attrUpdatable.
	 */
	public boolean isAttrUpdatable()
	{
		return attrUpdatable;
	}

	/**
	 * @param attrUpdatable
	 *            The attrUpdatable to set.
	 */
	public void setAttrUpdatable(boolean attrUpdatable)
	{
		this.attrUpdatable = attrUpdatable;
	}

	/**
	 * @return Returns the attrVerification.
	 */
	public boolean isAttrVerification()
	{
		return attrVerification;
	}

	/**
	 * @param attrVerification
	 *            The attrVerification to set.
	 */
	public void setAttrVerification(boolean attrVerification)
	{
		this.attrVerification = attrVerification;
	}

	/**
	 * @return Returns the compoundAttrContextKey.
	 */
	public String getCompoundAttrContextKey()
	{
		return compoundAttrContextKey;
	}

	/**
	 * @param compoundAttrContextKey
	 *            The compoundAttrContextKey to set.
	 */
	public void setCompoundAttrContextKey(String compoundAttrContextKey)
	{
		this.compoundAttrContextKey = compoundAttrContextKey;
	}

	/**
	 * @return Returns the compoundAttrEntityName.
	 */
	public String getCompoundAttrEntityName()
	{
		return compoundAttrEntityName;
	}

	/**
	 * @param compoundAttrEntityName
	 *            The compoundAttrEntityName to set.
	 */
	public void setCompoundAttrEntityName(String compoundAttrEntityName)
	{
		this.compoundAttrEntityName = compoundAttrEntityName;
	}

	/**
	 * @return Returns the compoundAttrID.
	 */
	public String getCompoundAttrID()
	{
		return compoundAttrID;
	}

	/**
	 * @param compoundAttrID
	 *            The compoundAttrID to set.
	 */
	public void setCompoundAttrID(String compoundAttrID)
	{
		this.compoundAttrID = compoundAttrID;
	}

	/**
	 * @return Returns the compoundAttrName.
	 */
	public String getCompoundAttrName()
	{
		return compoundAttrName;
	}

	/**
	 * @param compoundAttrName
	 *            The compoundAttrName to set.
	 */
	public void setCompoundAttrName(String compoundAttrName)
	{
		this.compoundAttrName = compoundAttrName;
	}

	/**
	 * @return Returns the attributeSelect.
	 */
	public String getAttributeSelected()
	{
		return attributeSelected;
	}

	/**
	 * @param attributeSelect
	 *            The attributeSelect to set.
	 */
	public void setAttributeSelected(String attrSelected)
	{
		this.attributeSelected = attrSelected;
		if (addAttributeList != null && !addAttributeList.isEmpty())
		{
			Iterator addIte = addAttributeList.iterator();
			while (addIte.hasNext())
			{
				CodetableRegistrationAttributesAndTypesResponseEvent typeEvent = (CodetableRegistrationAttributesAndTypesResponseEvent) addIte
						.next();
				if (attrSelected != null && attrSelected.equalsIgnoreCase(typeEvent.toString()))
				{
					this.attributeTypeSelected = typeEvent;
					break;
				}
				this.attributeTypeSelected = null;
			}
		}
	}

	/**
	 * @return Returns the showAddAttributes.
	 */
	public boolean isShowAddAttributes()
	{
		return showAddAttributes;
	}

	/**
	 * @param showAddAttributes
	 *            The showAddAttributes to set.
	 */
	public void setShowAddAttributes(boolean showAddAttributes)
	{
		this.showAddAttributes = showAddAttributes;
	}

	/**
	 * @return Returns the attributeTypeSelected.
	 */
	public CodetableRegistrationAttributesAndTypesResponseEvent getAttributeTypeSelected()
	{
		return attributeTypeSelected;
	}

	/**
	 * @param attributeTypeSelected
	 *            The attributeTypeSelected to set.
	 */
	public void setAttributeTypeSelected(CodetableRegistrationAttributesAndTypesResponseEvent attributeTypeSelected)
	{
		this.attributeTypeSelected = attributeTypeSelected;
	}

	/**
	 * @return Returns the attrNumeric.
	 */
	public boolean isAttrNumeric()
	{
		return attrNumeric;
	}

	/**
	 * @param attrNumeric
	 *            The attrNumeric to set.
	 */
	public void setAttrNumeric(boolean attrNumeric)
	{
		this.attrNumeric = attrNumeric;
	}

	/**
	 * @return Returns the editAttribute.
	 */
	public String getEditAttribute()
	{
		return editAttribute;
	}

	/**
	 * @param editAttribute
	 *            The editAttribute to set.
	 */
	public void setEditAttribute(String editAttribute)
	{
		this.editAttribute = editAttribute;
		if (codetableAttributes != null && !codetableAttributes.isEmpty())
		{
			Iterator editIte = codetableAttributes.iterator();
			while (editIte.hasNext())
			{
				CodetableAttributeResponseEvent editEvent = (CodetableAttributeResponseEvent) editIte.next();
				if (editAttribute != null && editAttribute.equalsIgnoreCase(editEvent.getDbColumn()))
				{
					this.editAttributeEvent = editEvent;
					break;
				}
				this.editAttributeEvent = null;
			}
		}
	}

	/**
	 * @return Returns the editAttributeEvent.
	 */
	public CodetableAttributeResponseEvent getEditAttributeEvent()
	{
		return editAttributeEvent;
	}

	/**
	 * @param editAttributeEvent
	 *            The editAttributeEvent to set.
	 */
	public void setEditAttributeEvent(CodetableAttributeResponseEvent editAttributeEvent)
	{
		this.editAttributeEvent = editAttributeEvent;
	}

	/**
	 * @return Returns the codetableAttributesOriginal.
	 */
	public Collection getCodetableAttributesOriginal()
	{
		return codetableAttributesOriginal;
	}

	/**
	 * @param codetableAttributesOriginal
	 *            The codetableAttributesOriginal to set.
	 */
	public void setCodetableAttributesOriginal(Collection codetableAttributesOriginal)
	{
		this.codetableAttributesOriginal = codetableAttributesOriginal;
	}

	/**
	 * @return Returns the showResequenceButton.
	 */
	public boolean isShowResequenceButton()
	{
		return showResequenceButton;
	}

	/**
	 * @param showResequenceButton
	 *            The showResequenceButton to set.
	 */
	public void setShowResequenceButton(boolean showResequenceButton)
	{
		this.showResequenceButton = showResequenceButton;
	}

	/**
	 * @return Returns the attrFinishPage.
	 */
	public String getAttrFinishPage()
	{
		return attrFinishPage;
	}

	/**
	 * @param attrFinishPage
	 *            The attrFinishPage to set.
	 */
	public void setAttrFinishPage(String attrFinishPage)
	{
		this.attrFinishPage = attrFinishPage;
	}

	/**
	 * @return Returns the resequencedOrderValue.
	 */
	public String getResequencedOrderValue()
	{
		return resequencedOrderValue;
	}

	/**
	 * @param resequencedOrderValue
	 *            The resequencedOrderValue to set.
	 */
	public void setResequencedOrderValue(String resequencedOrderValue)
	{
		this.resequencedOrderValue = resequencedOrderValue;
	}

	public String getAgencyCode()
	{
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode)
	{
		this.agencyCode = agencyCode;
	}

	public String getAgencyName()
	{
		return agencyName;
	}

	public void setAgencyName(String agencyName)
	{
		this.agencyName = agencyName;
	}

	public String getAgencySearchResultSize()
	{
		return agencySearchResultSize;
	}

	public void setAgencySearchResultSize(String agencySearchResultSize)
	{
		this.agencySearchResultSize = agencySearchResultSize;
	}

	public String getAction()
	{
		return action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}
}

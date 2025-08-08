/*
 * Created on Sep 17, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.codetable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import messaging.codetable.GetCodetableRegistrationAttributesAndTypesEvent;
import messaging.codetable.GetCodetableRegistrationAttributesEvent;
import messaging.codetable.UpdateCodetableRegistrationAttributesEvent;
import messaging.codetable.UpdateCodetableRegistrationEvent;
import messaging.codetable.reply.CodetableAttributeResponseEvent;
import messaging.codetable.reply.CodetableRecordResponseEvent;
import messaging.codetable.reply.CodetableRegistrationAttributesAndTypesResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.Feature;
import naming.PDCodeTableConstants;
import naming.ResponseLocatorConstants;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.contact.agency.Agency;
import pd.exception.CodetableException;
import pd.exception.ReflectionException;

/**
 * @author Nagalla
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class CodetableRegCreatorImpl implements ICodetableRegCreator
{

	/**
	 * @roseuid 45B9521B01BF
	 */
	public CodetableRegCreatorImpl()
	{
	}

	/**
	 * @param event
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws CodetableException
	 * @roseuid 45B94F5000C2
	 */
	public void retrieve(IEvent event) throws SecurityException, IllegalArgumentException, InstantiationException,
			ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException,
			CodetableException
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		GetCodetableRegistrationAttributesEvent gEvent = (GetCodetableRegistrationAttributesEvent) event;
		ResponseContextFactory respFac = new ResponseContextFactory();
		ResponseCreator aCreator = null;
		try
		{
			aCreator = (ResponseCreator) respFac.lookup(ResponseLocatorConstants.CODETABLEATTRIBUTE_RESPONSE_LOCATOR);
		}
		catch (ReflectionException e)
		{
			e.printStackTrace();
		}

		CodetableReg reg = CodetableReg.find(gEvent.getCodetableRegId());

		if (reg != null)
		{
			Iterator regAttrIter = reg.getCodetableRegAttributes().iterator();
			HashMap hearderMap = new HashMap();
			while (regAttrIter.hasNext())
			{
				CodetableRegAttribute regAttr = (CodetableRegAttribute) regAttrIter.next();
				if (regAttr != null)
				{
					CodetableAttributeResponseEvent resp = (CodetableAttributeResponseEvent) aCreator.create(regAttr);
					hearderMap.put("" + regAttr.getDisplayOrder(), regAttr.getDbcolumn());
					dispatch.postEvent(resp);
				}
			}
		}
	}

	/**
	 * @param event
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws CodetableException
	 * @roseuid 45B94F5000C2
	 */
	public void retrieveAttributeAndTypes(IEvent event) throws SecurityException, IllegalArgumentException,
			InstantiationException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
			InvocationTargetException, CodetableException
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		GetCodetableRegistrationAttributesAndTypesEvent gEvent = (GetCodetableRegistrationAttributesAndTypesEvent) event;

		List codeRegAttrsAlreadySetInReg = new ArrayList();
		boolean postedAttributes = false;
		CodetableReg reg = CodetableReg.find(gEvent.getCodetableRegId());
		if (reg != null)
		{
			Iterator regAttrIter = reg.getCodetableRegAttributes().iterator();
			HashMap hearderMap = new HashMap();
			while (regAttrIter.hasNext())
			{
				CodetableRegAttribute regAttr = (CodetableRegAttribute) regAttrIter.next();
				if (regAttr != null && regAttr.getDbcolumn() != null)
				{
					codeRegAttrsAlreadySetInReg.add(regAttr.getDbcolumn().trim());
				}
			}

			int displayOrder = reg.getCodetableRegAttributes().size() + 1;
			MojoProperties props = MojoProperties.getInstance();
			Iterator i = props.getEntityMaps();
			if (reg.getCodetableEntityName().equals(PDCodeTableConstants.CONTEXTS_CLASS))
			{
				while (i.hasNext())
				{
					EntityMappingProperties eProps = (EntityMappingProperties) i.next();
					if (eProps.getEntity().equalsIgnoreCase(PDCodeTableConstants.CONTEXTS_CLASS))
					{
						String contextKey = eProps.getContextKey().toLowerCase().trim();
						String searchKey = reg.getCodetableContextKey().toLowerCase().trim();
						if (contextKey.equalsIgnoreCase(searchKey))
						{
							if (eProps.getSaveCallbackProperties() != null)
							{
								List fields = eProps.getSaveCallbackProperties().getFields();
								if (fields != null && !fields.isEmpty())
								{
									Iterator fieldsIte = fields.iterator();
									while (fieldsIte.hasNext())
									{
										FieldMappingProperties fmp = (FieldMappingProperties) fieldsIte.next();
										String dbItemName = fmp.getDataItemName();
										if ((!fmp.getName().equalsIgnoreCase("OID")) && dbItemName != null)
										{
											dbItemName = dbItemName.trim();
											if (!codeRegAttrsAlreadySetInReg.contains(dbItemName))
											{
												CodetableRegistrationAttributesAndTypesResponseEvent resEvent = new CodetableRegistrationAttributesAndTypesResponseEvent();
												resEvent.setDbItemName(dbItemName);
												resEvent.setType(fmp.getPropertyType());
												resEvent.setDipslayOrder(displayOrder++ + "");
												dispatch.postEvent(resEvent);
												postedAttributes = true;
											}

										}
									}
								}
							}
							else if (eProps.getSaveCallbackProperties() == null)
							{
								ErrorResponseEvent errRespEvt = new ErrorResponseEvent();
								errRespEvt.setMessage("No CallBacks Found.");
								dispatch.postEvent(errRespEvt);
								return;
							}
							break;
						}
					}
				}
			}
			else
			{
				while (i.hasNext())
				{
					EntityMappingProperties eProps = (EntityMappingProperties) i.next();
					if (!eProps.getEntity().equalsIgnoreCase(PDCodeTableConstants.CONTEXTS_CLASS))
					{
						String contextKey = eProps.getContextKey().toLowerCase().trim();
						String searchName = reg.getCodetableEntityName().toLowerCase().trim();
						if (contextKey.equalsIgnoreCase(searchName))
						{
							if (eProps.getSaveCallbackProperties() != null)
							{
								List fields = eProps.getSaveCallbackProperties().getFields();
								if (fields != null && !fields.isEmpty())
								{
									Iterator fieldsIte = fields.iterator();
									while (fieldsIte.hasNext())
									{
										FieldMappingProperties fmp = (FieldMappingProperties) fieldsIte.next();
										String dbItemName = fmp.getDataItemName();
										if ((!fmp.getName().equalsIgnoreCase("OID")) && dbItemName != null)
										{
											dbItemName = dbItemName.trim();
											if (!codeRegAttrsAlreadySetInReg.contains(dbItemName))
											{
												CodetableRegistrationAttributesAndTypesResponseEvent resEvent = new CodetableRegistrationAttributesAndTypesResponseEvent();
												resEvent.setDbItemName(dbItemName);
												resEvent.setType(fmp.getPropertyType());
												resEvent.setDipslayOrder(displayOrder++ + "");
												dispatch.postEvent(resEvent);
												postedAttributes = true;
											}

										}
									}
								}
							}
							else if (eProps.getSaveCallbackProperties() == null)
							{
								ErrorResponseEvent errRespEvt = new ErrorResponseEvent();
								errRespEvt.setMessage("No CallBacks Found.");
								dispatch.postEvent(errRespEvt);
								return;
							}
							break;
						}
					}
				}

			}
		}
		if (!postedAttributes)
		{
			ErrorResponseEvent errRespEvt = new ErrorResponseEvent();
			errRespEvt.setMessage("There are no New Attributes to add.");
			dispatch.postEvent(errRespEvt);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pd.codetable.transactions.ICodetableRegCreator#save(mojo.km.messaging.IEvent)
	 */
	public void save(IEvent event) throws SecurityException, IllegalArgumentException, InstantiationException,
			ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException,
			CodetableException
	{
		UpdateCodetableRegistrationEvent uEvent = (UpdateCodetableRegistrationEvent) event;
		CodetableReg codeReg = null;
		if (uEvent.getCodeRegId() == null)
		{
			codeReg = new CodetableReg();
		}
		else
		{
			codeReg = CodetableReg.find(uEvent.getCodeRegId());
		}
		codeReg.setCodetableName(uEvent.getName());
		// codeReg.setContextKey("pd.codetable.CodetableReg");
		// codeReg = (CodetableReg) new mojo.km.persistence.Home().bind(codeReg);

		codeReg.setCodetableDescription(uEvent.getDescription());
		codeReg.setType(uEvent.getType());
		codeReg.setCodetableContextKey(uEvent.getContextKey());
		codeReg.setCodetableEntityName(uEvent.getEntityName());

		// codeReg.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
		// codeReg.setCreateUserID(PDSecurityHelper.getLogonId());

		if (uEvent.getCodeRegId() == null)
		{
			codeReg = (CodetableReg) new mojo.km.persistence.Home().bind(codeReg);
			Collection agenciesId = uEvent.getAgenciesList();
			if (agenciesId != null && agenciesId.size() > 0)
			{
				Iterator itAgencies = agenciesId.iterator();
				while (itAgencies.hasNext())
				{
					String agencyId = (String) itAgencies.next();
					Agency agency = Agency.find(agencyId);
					codeReg.insertAgencies(agency);
				}
			}
		}
		else
		{
			HashMap map = new HashMap();
			Iterator existingIter = codeReg.getAgencies().iterator();
			while (existingIter.hasNext())
			{
				Agency existingAgency = (Agency) existingIter.next();
				if (!map.containsKey(existingAgency.getAgencyId()))
				{
					map.put(existingAgency.getAgencyId(), existingAgency);
				}
			}

			Collection wantedAgencyIds = uEvent.getAgenciesList();

			Iterator iter = wantedAgencyIds.iterator();
			while (iter.hasNext())
			{
				String wantedAgencyId = (String) iter.next();
				if (map != null && map.containsKey(wantedAgencyId))
				{
					// user want this Feature, Since this is already in database, so do nothing and
					// remove from the map
					map.remove(wantedAgencyId);
				}
				else
				{
					// these are new Features user want
					Agency wantedAgency = Agency.find(wantedAgencyId);
					if (wantedAgency != null)
					{
						codeReg.insertAgencies(wantedAgency);
					}
				}
			}

			// at this point whatever is left in the map is undesirable
			if (map != null && map.size() > 0)
			{
				Iterator unWantedAgenciesIterator = map.values().iterator();
				while (unWantedAgenciesIterator.hasNext())
				{
					Agency unWantedAgency = (Agency) unWantedAgenciesIterator.next();
					codeReg.removeAgencies(unWantedAgency);
				}
			}

		}

		ResponseContextFactory respFac = new ResponseContextFactory();
		ResponseCreator aCreator = null;
		try
		{
			aCreator = (ResponseCreator) respFac.lookup(ResponseLocatorConstants.CODETABLERECORD_RESPONSE_LOCATOR);
		}
		catch (ReflectionException e)
		{
			e.printStackTrace();
		}
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		if (codeReg != null)
		{
			CodetableRecordResponseEvent resp = (CodetableRecordResponseEvent) aCreator.create(codeReg);
			dispatch.postEvent(resp);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pd.codetable.transactions.ICodetableRegCreator#save(mojo.km.messaging.IEvent)
	 */
	public void saveAttirbutes(IEvent event) throws SecurityException, IllegalArgumentException,
			InstantiationException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
			InvocationTargetException, CodetableException
	{
		UpdateCodetableRegistrationAttributesEvent uEvent = (UpdateCodetableRegistrationAttributesEvent) event;
		CodetableReg codeReg = null;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		if (uEvent.getCodeRegId() == null)
		{
			ErrorResponseEvent errRespEvt = new ErrorResponseEvent();
			errRespEvt.setMessage("Please specify the Codetable Registration ID.");
			dispatch.postEvent(errRespEvt);
			return;
		}
		else
		{
			codeReg = CodetableReg.find(uEvent.getCodeRegId());
		}

		if (uEvent.getUpdateAttributes() != null && !(uEvent.getUpdateAttributes().isEmpty()))
		{
			Iterator updateAttributes = uEvent.getUpdateAttributes().iterator();
			CodetableRegAttribute regAttr;
			while (updateAttributes.hasNext())
			{
				CodetableAttributeResponseEvent updateAttribute = (CodetableAttributeResponseEvent) updateAttributes
						.next();
				if (updateAttribute.getCodetableRegAttributeId() == null
						|| updateAttribute.getCodetableRegAttributeId().trim().length() == 0)
				{
					// Non-member record. See if it's been filled in.
					regAttr = new CodetableRegAttribute();
					regAttr.setDisplayName(updateAttribute.getDisplayName());
					regAttr.setDisplayOrder(updateAttribute.getDisplayOrder());
					regAttr.setDbcolumn(updateAttribute.getDbColumn());
					regAttr.setType(updateAttribute.getType());
					regAttr.setAudit(updateAttribute.isAudit());
					regAttr.setRequired(updateAttribute.isRequired());
					regAttr.setCompoundAttributeId(updateAttribute.getCompoundId());
					regAttr.setCompoundAttributeName(updateAttribute.getCompoundName());
					regAttr.setCompEntityName(updateAttribute.getEntityName());
					regAttr.setCompContextKey(updateAttribute.getContextKey());
					regAttr.setUpdatable(updateAttribute.isUpdatable());
					regAttr.setCompoundType(updateAttribute.getCompundType());
					regAttr.setNumeric(updateAttribute.isNumeric());
					regAttr.setMaxLength(updateAttribute.getMaxLength());
					regAttr.setMinLength(updateAttribute.getMinLength());
					regAttr.setUnique(updateAttribute.isUnique());
					regAttr.setValidCheckRequired(updateAttribute.isValidCheckRequired());
					codeReg.insertCodetableRegAttributes(regAttr);

				}
				else
				{
					// Member record.
					regAttr = CodetableRegAttribute.find(updateAttribute.getCodetableRegAttributeId());
					regAttr.setDisplayName(updateAttribute.getDisplayName());
					regAttr.setDisplayOrder(updateAttribute.getDisplayOrder());
					regAttr.setDbcolumn(updateAttribute.getDbColumn());
					regAttr.setType(updateAttribute.getType());
					regAttr.setAudit(updateAttribute.isAudit());
					regAttr.setRequired(updateAttribute.isRequired());
					regAttr.setCompoundAttributeId(updateAttribute.getCompoundId());
					regAttr.setCompoundAttributeName(updateAttribute.getCompoundName());
					regAttr.setCompEntityName(updateAttribute.getEntityName());
					regAttr.setCompContextKey(updateAttribute.getContextKey());
					regAttr.setUpdatable(updateAttribute.isUpdatable());
					regAttr.setCompoundType(updateAttribute.getCompundType());
					regAttr.setNumeric(updateAttribute.isNumeric());
					regAttr.setMaxLength(updateAttribute.getMaxLength());
					regAttr.setMinLength(updateAttribute.getMinLength());
					regAttr.setUnique(updateAttribute.isUnique());
					regAttr.setValidCheckRequired(updateAttribute.isValidCheckRequired());
				}
			}
		}

		if (uEvent.getRemoveAttributes() != null && !(uEvent.getRemoveAttributes().isEmpty()))
		{
			Iterator removeAttributes = uEvent.getRemoveAttributes().iterator();
			CodetableRegAttribute regAttr;
			while (removeAttributes.hasNext())
			{
				CodetableAttributeResponseEvent removeAttribute = (CodetableAttributeResponseEvent) removeAttributes
						.next();
				if (removeAttribute.getCodetableRegAttributeId() != null
						|| removeAttribute.getCodetableRegAttributeId().trim().length() > 0)
				{
					regAttr = CodetableRegAttribute.find(removeAttribute.getCodetableRegAttributeId());
					codeReg.removeCodetableRegAttributes(regAttr);
					// regAttr.setDeleted();
				}
			}
		}
	}
}

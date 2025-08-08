/*
 * Created on Dec 7, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.supervision.Factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import naming.PDJuvenileCaseConstants;
import pd.supervision.Group;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionoptions.CourtSupervisionOption;
import pd.supervision.supervisionoptions.VariableElement;
import pd.supervision.supervisionoptions.VariableElementType;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SupervisionOptionsFactory implements ISupervisionOptionsFactory
{

	/**
	 * 
	 */
	public SupervisionOptionsFactory()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see pd.supervision.Factory.ISupervisionOptionsFactory#getConditionResponseEvent(pd.supervision.supervisionoptions.Condition)
	 */
	public ConditionDetailResponseEvent getConditionResponseEvent(Condition condition)
	{
		ConditionDetailResponseEvent reply = new ConditionDetailResponseEvent();

		reply.setConditionId(condition.getOID().toString());
		reply.setAgencyId(condition.getAgencyId());
		reply.setName(condition.getName());

		Group[] groups = condition.getGroup().getGroupList();
		if (groups[0] != null)
		{
			reply.setGroup1Id(groups[0].getOID().toString());
			reply.setGroup1Name(groups[0].getGroupName());
		}
		if (groups[1] != null)
		{
			reply.setGroup2Id(groups[1].getOID().toString());
			reply.setGroup2Name(groups[1].getGroupName());
		}
		if (groups[2] != null)
		{
			reply.setGroup3Id(groups[2].getOID().toString());
			reply.setGroup3Name(groups[2].getGroupName());
		}
		reply.setStandard(condition.getIsStandard());
		reply.setDescription(condition.getDescription());
		
		reply.setTopic(PDJuvenileCaseConstants.CONDITION_DETAIL_TOPIC);
		return reply;
	}

	/* (non-Javadoc)
	 * @see pd.supervision.Factory.ISupervisionOptionsFactory#getVariableElementResponses(pd.supervision.supervisionoptions.CourtSupervisionOption)
	 */
	public Collection getVariableElementResponses(CourtSupervisionOption option)
	{
		Collection variableElementResponses = new ArrayList();

		if (option.getVariableElements().isEmpty())
		{
			VariableElementResponseEvent varElemEvent = new VariableElementResponseEvent();

			varElemEvent.setCourtId(option.getCourtId());
			varElemEvent.setExceptionCourt(option.getIsExceptionCourt());
			variableElementResponses.add(varElemEvent);
		}
		else
		{
			Iterator varElemIter = option.getVariableElements().iterator();
			while (varElemIter.hasNext())
			{
				VariableElement varElem = (VariableElement) varElemIter.next();

				if (varElem != null)
				{
					VariableElementResponseEvent varElemEvent = new VariableElementResponseEvent();
					VariableElementType veType = varElem.getVariableElementType();

					varElemEvent.setVariableElementTypeId(varElem.getVariableElementTypeId());
					varElemEvent.setVariableElementId(varElem.getOID().toString());
					varElemEvent.setCourtId(option.getCourtId());
					varElemEvent.setFixed(varElem.getIsFixed());
					varElemEvent.setReference(veType.isReference());
					varElemEvent.setExceptionCourt(option.getIsExceptionCourt());
					varElemEvent.setValue(varElem.getValue());

					if (veType != null)
					{
						varElemEvent.setName(veType.getName());

						if (veType.isEnumeration())
						{
							varElemEvent.setEnumeration(true);
							// if type is enumaration ; get codetable name
//							VariableElementTypeEnumerationInfo codeTable = veType.getVariableElementTypeCodeTable();
//							varElemEvent.setCodeTableName(codeTable.getElementCodeTableId());
//							varElemEvent.setEnumerationTypeId(codeTable.getEnumerationTypeId());
							varElemEvent.setCodeTableName(veType.getElementCodeTableId());
						}
						varElemEvent.setEnumerationTypeId(veType.getEnumerationTypeId());
						varElemEvent.setValueType(veType.getType());
					}
					variableElementResponses.add(varElemEvent);
				}
			}
		}
		return variableElementResponses;
	}

}

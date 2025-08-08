//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\GetDetailDictionaryCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Iterator;

import pd.supervision.supervisionoptions.VariableElementType;

import messaging.supervisionoptions.GetDetailDictionaryEvent;
import messaging.supervisionoptions.reply.VariableElementTypeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetDetailDictionaryCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C4440128
    */
   public GetDetailDictionaryCommand() 
   {
   }
   
   /**
    * @param event
    * @roseuid 42F7997C00FA
    */
   public void execute(IEvent event) 
   {
		GetDetailDictionaryEvent groupsEvent = (GetDetailDictionaryEvent)event;
		String agencyId = groupsEvent.getAgencyId();
	
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	
		Iterator iter = VariableElementType.findAll( groupsEvent );
		while ( iter.hasNext() )
		{
			VariableElementType type = (VariableElementType)iter.next();
			VariableElementTypeResponseEvent evt = new VariableElementTypeResponseEvent();
			
			evt.setVariableElementTypeId( type.getOID() );
			evt.setAgencyId( agencyId );
			evt.setName( type.getName() );
			evt.setDescription( type.getDescription() );
			evt.setIsReference( type.isReference() );
			evt.setIsVolatile( type.getIsVolatile());
			evt.setSampleValue( type.getSampleValue() );
			evt.setType(type.getType());
			evt.setValueType(type.getEnumerationTypeId());
			evt.setIsCalculated( type.getIsCalculated() );
			
			// set UI control type from VariableElementTypeEnumerationInfo
			if(type.isEnumeration()){
				evt.setEnumration(type.isEnumeration());
				// set UI Control type for enumeration like Radio/List
//				VariableElementTypeEnumerationInfo varElemEnumInfo = type.getVariableElementTypeCodeTable();
//				evt.setValueType(varElemEnumInfo.getEnumerationTypeId());
//				// set code tqable name to get the codes
//				evt.setCodeTableName(varElemEnumInfo.getElementCodeTableId());
				evt.setCodeTableName(type.getElementCodeTableId());
			}
			dispatch.postEvent( evt );
		}
   }
   
   /**
    * @param event
    * @roseuid 42F7997C00FC
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F7997C00FE
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F7997C0100
    */
   public void update(Object anObject) 
   {
    
   }
   
}

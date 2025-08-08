package mojo.ui.common;

import mojo.km.messaging.IEvent;

/**
 * <B>SymbolChangeEvent</B> is an OESEvent that is used to notify a gui 
 * component that the symbol should be changed. This event will be sent to 
 * any component that can be linked to another component, from the market 
 * maker and market minder to the order entry, and from the multi quote to 
 * the market maker.
 * @modelguid {46EC7B53-1453-463D-8320-A727B28D5FB3}
 */
public class ValueChangeEvent implements IEvent
{
  /**
   * This is the only defined constructor for this class.
   * @param groupName names the group to which this event is sent.
   * @param symbol is the name of the new symbol the component should use.
   * @modelguid {A689403C-F8F8-4783-B0DC-E923205AD3B9}
   */
  public ValueChangeEvent(String groupName, String topic)
  {
    this.groupName = groupName;
    this.topic = topic;
    server = groupName;
    StringBuffer str = new StringBuffer();
    str.append(groupName).append("::").append(this.getClass().getName()).toString();
    mHashKey = str.toString();
  }
  
  /**
   * Get hashkey use to filter on event listener.
   * @return the event hashkey.
   * @modelguid {4BF6E02E-C3AC-4E00-B81D-56D84215874E}
   */
  public String hashKey()
  {
    return mHashKey;
  }
    
  /**
   * Returns the service event is set to.
   * @return the group name for the service.
   * @modelguid {B5BAF49D-6857-4EFD-BF73-7F411451C1D1}
   */
  public String getTopic( ) 
  {
        return topic;
    }
    
  /**
   * Set the service the event is to be associated with.
   * @param service is the new service name for this event.
   * @modelguid {DB17DF4F-E1E9-4229-B52C-1A996B9ABB5B}
   */
  public void setTopic(String topic) 
  {
        this.topic = topic;
    }
 
  /**
   * This method is used to retrieve the group this event belongs to.
   * @return the event group name.
   * @modelguid {306E661E-E02A-49F3-910F-99D179FBA897}
   */
  public String getGroupName()
  {
    return groupName;
  }
  
  /**
   * This method is used to retrieve the symbol of the event. This is the only
   * real data of this event.
   * @return the event symbol.
   * @modelguid {0E33080F-0B19-4B0E-A883-784C849A483B}
   */
  public String getValue() 
  {
        return value;
    }

	/** @modelguid {D5FD9360-F564-446C-8C02-2A18C0D12085} */
  public String getServer(){
          return server;
      }

	/** @modelguid {01FF4266-AA83-44DA-B82F-75D2391CB0BB} */
  public void setServer(String server){
          this.server = server;
      }
  
  /**
   * This holds the event group name.
   * @modelguid {2BE1DA6C-91BE-49C5-92DE-630D1F546293}
   */
  private String groupName;
  
  /**
   * This holds the event service name. Unless setService() is called, the
   * service is initialized to the group name.
   * @modelguid {985827A7-8A25-43C2-BA2D-611D40CB4E79}
   */
  private String topic;
  
  /**
   * This holds the event symbol.
   * @modelguid {9D2C8A4B-535D-44EA-B44A-C4CD64349F5D}
   */
  private String value;
  
  /**
   * This holds the event hashkey.
   * @modelguid {029B5501-BE68-4ABD-A28C-1BDE5762B608}
   */
  private String mHashKey;
	/** @modelguid {F327B394-04DC-4910-B7D1-7284B9B1B5B4} */
  private String server;
}
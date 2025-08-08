   package mojo.km.messaging;

   import java.util.*;
import java.io.Serializable;
/**
 *    MessageContainer is designed to hold JMS message types
 *    Creation date: (5/17/00 9:53:00 AM)
 *    @author: D. Marple
 * @modelguid {A41CD53D-00C4-4B84-9387-A9F0A4BE48C0}
 */
   public class MessageContainer implements IMessageContainer, Serializable {
      /**
       * @associates IMapMessage 
       * @modelguid {1012FCB5-9119-4C7A-B5D7-17E24CACCBB3}
       */
	  private Hashtable mapMessages = null;

      /**
       * @associates ITextMessage 
       * @modelguid {61C29461-5F7F-49B6-80A5-03F097371EDE}
       */
	  private Hashtable textMessages = null;

      /**
       * @associates IMessageContainer 
       * @modelguid {B4C2FACA-AD45-4F1A-8B0D-80CEB17EC82F}
       */
	  private Hashtable messageContainers = null;
   /**
 * 	 MessageContainer constructor.
 * @modelguid {D8717248-7D41-4840-9452-C82F931B9990}
 */
	  public MessageContainer() {
		 mapMessages = new Hashtable();
		 messageContainers = new Hashtable();
		 textMessages = new Hashtable();
	  }
   /**
 * 	 addMapMessage method.
 * @param messageIdentifier
 * @param msg
 * @modelguid {E400C0F0-135A-4EBF-8990-A71395F2350F}
 */
	  public void addMapMessage(String messageIdentifier, IMapMessage msg) {
		 mapMessages.put(messageIdentifier, msg);	
	  }
   /**
 * 	 addMessageContainer method.
 * @param name
 * @param msgC
 * @modelguid {771E9DF8-0C01-4D4C-BD96-245A5E9BC661}
 */
	  public void addMessageContainer(String name, IMessageContainer msgC) {
		 messageContainers.put(name, msgC);	
	  }
   /**
 * 	 addTextMessage method.
 * @param messageIdentifier
 * @param msg
 * @modelguid {A419E0DD-D7FB-4B32-B6BF-BB0BF56D4C30}
 */
	  public void addTextMessage(String messageIdentifier, ITextMessage msg) {
		 textMessages.put(messageIdentifier, msg);	
	  }
   /**
 * 	 clearMessageHolder method.
 * @modelguid {E16F54E0-55E9-4A2F-8AD7-5EC6DB54AFE1}
 */
	  public void clearMessageContainer() {
		 mapMessages = new Hashtable();
		 messageContainers = new Hashtable();
		 textMessages = new Hashtable();	
	  }
   /**
 * 	 getMessages method.
 * @return  
 * @modelguid {256BAD42-FA7D-489C-ADEC-BD463F11FADB}
 */
	  public Vector getMapMessages() {
		 Enumeration e = mapMessages.elements();
		 Vector rVector = new Vector();
		 while(e.hasMoreElements()) {
			rVector.addElement(e.nextElement());
		 }
	  
		 return rVector;	
	  }
   /**
 * 	 getMessageContainers method.
 * 	 Creation date: (5/17/00 10:04:51 AM)
 * 	 @return <{Vector}>
 * @modelguid {D2BCD3A3-86BB-4629-B9FC-0287AE29D02B}
 */
	  public Vector getMessageContainers() {
		 Enumeration e = messageContainers.elements();
		 Vector rVector = new Vector();
		 while(e.hasMoreElements()) {
			rVector.addElement(e.nextElement());
		 }
	  
		 return rVector;	
	  }
   /**
 * 	 getRootMapMessage method.
 * @return  
 * @modelguid {A8614445-5935-4ECA-9A17-604E85EE52E6}
 */
	  public IMapMessage getRootMapMessage() {
		 return (IMapMessage)mapMessages.get("ROOT");
	  }
   /**
 * 	 getTextMessage method.
 * 	 Creation date: (5/17/00 10:04:33 AM)
 * 	 @return <{Vector}>
 * @modelguid {853C7A67-607A-4BF9-A88E-E65016A0ACE8}
 */
	  public Vector getTextMessages() {
		 Enumeration e = textMessages.elements();
		 Vector rVector = new Vector();
		 while(e.hasMoreElements()) {
			rVector.addElement(e.nextElement());
		 }
	  
		 return rVector;	
	  }
   /**
 * 	 removeMessage method.
 * @param messageIdentifier
 * @modelguid {A5F9DDB3-903F-4133-9E7E-6DA30DDF574D}
 */
	  public void removeMapMessage(String messageIdentifier) {
		 mapMessages.remove(messageIdentifier);	
	  }
   /**
 * 	 removeMessage method.
 * @param messageIdentifier
 * @modelguid {52B32D49-AC05-44D3-AEA5-B4D8E743D1AF}
 */
	  public void removeMessageContainer(String messageIdentifier) {
		 messageContainers.remove(messageIdentifier);	
	  }
   /**
 * 	 removeMessage method.
 * @param messageIdentifier
 * @modelguid {63BFE985-AF85-4307-B434-D410B1A8FCC7}
 */
	  public void removeTextMessage(String messageIdentifier) {
		 textMessages.remove(messageIdentifier);	
	  }
   /**
 * 	 requestMapMessage method.
 * @param messageIdentifier
 * @return  
 * @modelguid {9698CA56-FC0A-44DC-9237-FBB6D43D5D6D}
 */
	  public IMapMessage requestMapMessage(String messageIdentifier) {
		 return (IMapMessage)mapMessages.get(messageIdentifier);
	  }
   /**
 * 	 requestMessageContainer method.
 * @param name
 * @return  
 * @modelguid {A4E2E3A1-0306-4ECA-9FAE-BF61AD5FD4E0}
 */
	  public IMessageContainer requestMessageContainer(String name) {
		 return (IMessageContainer)messageContainers.get(name);
	  }
   /**
 * 	 requestTextMessage method.
 * @param messageIdentifier
 * @return  
 * @modelguid {0BDBB9EB-A585-4B1A-B52A-2C6698EA5D29}
 */
	  public ITextMessage requestTextMessage(String messageIdentifier) {
		 return (ITextMessage)textMessages.get(messageIdentifier);
	  }
   /**
 * 	 toSting methodfor this class.
 * 	 Creation date: (6/1/00 3:28:42 PM)
 * 	 @return <{String}>
 * @modelguid {4478871E-5BF7-4CC1-873E-D30CB58FA923}
 */
	  public String toString() {
		 String rString = "";
		 Enumeration keys = messageContainers.keys();
		 String kString = null;
		 IMessageContainer msgC = null;
		 IMapMessage mmsg = null;
		 ITextMessage tmsg = null;
		 while(keys.hasMoreElements()) {
			kString = (String)keys.nextElement();
			msgC = (IMessageContainer)messageContainers.get(kString);
			rString += "Sub Message Container " + kString + '\n' + "--------------------------------------------" + '\n';
			rString += msgC.toString();
		 }
	  
		 keys = mapMessages.keys();
		 while(keys.hasMoreElements()) {
			kString = (String)keys.nextElement();
			mmsg = (IMapMessage)mapMessages.get(kString);
			rString += "Map Message " + kString + '\n' + "--------------------------------------------" + '\n';
			rString += mmsg.toString();
		 }
	  
		 keys = textMessages.keys();
		 while(keys.hasMoreElements()) {
			kString = (String)keys.nextElement();
			tmsg = (ITextMessage)textMessages.get(kString);
			rString += "Text Message " + kString + '\n' + "--------------------------------------------" + '\n';
			rString += tmsg.toString();
		 }
	  
		 return rString;
	  }
   }
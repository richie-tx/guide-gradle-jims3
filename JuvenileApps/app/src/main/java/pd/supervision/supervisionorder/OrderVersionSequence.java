package pd.supervision.supervisionorder;

import java.util.Iterator;

import messaging.supervisionorder.FindOrderVersionSequenceEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.criminalcase.CriminalCase;

/**
 * @author dgibler
 *
* @roseuid 43B2E6670280
*/
public class OrderVersionSequence extends PersistentObject
{
	private static int seqNum;
	/**
	* Properties for theCase
	*/
	private CriminalCase theCase = null;
	private String theCaseId;
	/**
	* Properties for versionType
	* @referencedType pd.codetable.Code
	* @contextKey NEW_CONTEXT_KEY_NEEDED
	* @detailerDoNotGenerate true
	*/
	private Code versionType = null;
	private String versionTypeId;
	private int orderChainNum;
	/**
	* @roseuid 43B2E6670280
	*/
	public OrderVersionSequence()
	{
	}

	/**
	* @return OrderVersionSequence
	* @param OrderVersionSequenceId
	*/
	static public OrderVersionSequence find(String orderVersionSequenceId)
	{
		IHome home = new Home();
		OrderVersionSequence orderVersionSequence =
			(OrderVersionSequence) home.find(orderVersionSequenceId, OrderVersionSequence.class);
		return orderVersionSequence;
	}
	/**
	* Finds a collection of OrderVersionSequence by an event
	* @return Iterator of orderVersionSequences
	* @param event
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator orderVersionSequences = home.findAll(event, OrderVersionSequence.class);
		return orderVersionSequences;
	}

	/**
	* Finds a OrderVersionSequence by an event. This method will return the first object
	* of the Iterator that is returned by findAll.
	* @return OrderVersionSequences
	* @param event
	*/
	static public OrderVersionSequence find(String caseNum, String versionType, int aOrderChain)
	{
		IHome home = new Home();
		FindOrderVersionSequenceEvent event = new FindOrderVersionSequenceEvent();
		event.setCaseNum(caseNum);
		event.setVersionType(versionType);
		event.setOrderChainNum(aOrderChain);
		// search by this event
		Iterator orderVersionSequences = home.findAll(event, OrderVersionSequence.class);
		OrderVersionSequence orderVersionSeq = null;
		if (orderVersionSequences.hasNext())
		{
			orderVersionSeq = (OrderVersionSequence) orderVersionSequences.next();
		}
		else
		{ // create a new instance if its not there
			orderVersionSeq = new OrderVersionSequence();
			orderVersionSeq.setTheCaseId(caseNum);
			orderVersionSeq.setVersionTypeId(versionType);
			orderVersionSeq.setOrderChainNum(event.getOrderChainNum());
			orderVersionSeq.setSeqNum(0);
		}

		return orderVersionSeq;
	}

	/**
	* Finds all orderVersionSequences by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		Iterator orderVersionSequences = home.findAll(attributeName, attributeValue, OrderVersionSequence.class);
		return orderVersionSequences;
	}

	/**
	 * USE CASE: Based on the case number and version type in a specific order chain, the routine shall
	 * increment the sequence number upon the activation of an A-Amended or M-Modified document, but not 
	 * on an O-Original (Original Original or New Original Order) document.
	 * 
	 * BUSINESS RULE: The sequence number shall increment for subsequent instances of an A-Amended or M-Modified
	 * document within the same Order-Chain. Version-Type O-Original shall be filtered and shall not increment
	 * as there shall only be one(1) Version-Type O-Original in any given Order-Chain.
	 * 
	 * CHANGE LOG:
	 * 05/23/2012 TSVines 
	 * 
	 * @param caseNum
	 * @param versionType
	 * @param orderChain
	 * @return
	 */
	public static int next(String aCaseNum, String aVersionType, int aOrderChain)
	{
		OrderVersionSequence versionSequence = OrderVersionSequence.find(aCaseNum, aVersionType, aOrderChain);
		
		int seqNum = OrderVersionSequence.seqNum;
		
		final String versionTypeOriginal = "O" ;
      
        // If Version-Type is "O-Original" no change to sequence number; else increment sequence number
      
        if (versionTypeOriginal.equalsIgnoreCase(aVersionType)) {

             seqNum = OrderVersionSequence.seqNum;

        } else if (versionSequence != null && !versionTypeOriginal.equalsIgnoreCase(aVersionType))
			{
				seqNum = versionSequence.getSeqNum();
				
				// Incremented and set sequence number on OrderVersionSequence entity
				
				seqNum++;
				
				versionSequence.setSeqNum(seqNum);
			}
		return seqNum;
	}
	
	
	/**
	 * Used to set the sequence number for historical orders
	 * 
	 * @param aCaseNum
	 * @param aVersionType
	 * @param aSeqNum
	 * @param aOrderChain
	 * @return
	 */
	public static int next(String aCaseNum, String aVersionType, int aSeqNum,  int aOrderChain){
		
		Home lHome = new Home();
		
		OrderVersionSequence versionSequence = OrderVersionSequence.find(aCaseNum, aVersionType, aOrderChain);
		
		if (versionSequence != null)
		{
			versionSequence.setSeqNum(aSeqNum);
		}
		return aSeqNum;
	}


	/**
	 * USE CASE: Based on the case number and version type in a specific order chain, the routine shall
	 * decrement the sequence number upon the deletion of an A-Amended or M-Modified document, but not 
	 * on an O-Original (Original Original or New Original Order) document.
	 * 
	 * BUSINESS RULE: The sequence number shall decrement for subsequent instances of an A-Amended or M-Modified
	 * document deleted within the same Order-Chain. Version-Type O-Original shall be filtered and shall not decrement
	 * as there shall only be one(1) Version-Type O-Original in any given Order-Chain.
	 * 
	 * CHANGE LOG:
	 * 05/23/2012 TSVines 
	 *  
	 * @param aCaseNum
	 * @param aVersionType
	 * @param aOrderChain
	 * @return
	 */
	public static int prev(String aCaseNum, String aVersionType, int aOrderChain)
	{
		OrderVersionSequence versionSequence = OrderVersionSequence.find(aCaseNum, aVersionType, aOrderChain);
		
		int seqNum = OrderVersionSequence.seqNum;
		
        final String versionTypeOriginal = "O" ;
      
        // If Version-Type "O-Original" no change in sequence number; else decrement sequence number
      
        if (versionTypeOriginal.equalsIgnoreCase(aVersionType)) {

        	seqNum = OrderVersionSequence.seqNum;

       } else if (versionSequence != null && !versionTypeOriginal.equalsIgnoreCase(aVersionType)){
            
             seqNum = versionSequence.getSeqNum();
            
              // Decremented sequence number and set in OrderVersionSequence entity
            
             seqNum--;
             
             versionSequence.setSeqNum(seqNum);
       }
        return seqNum;
 }

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public int getOrderChainNum()
	{
		fetch();
		return orderChainNum;
	}
	
	/**
	 * @param aOrderChainNum
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setOrderChainNum(int aOrderChainNum)
	{
		if(aOrderChainNum==0)  // default value for orderr chain num is 1
			aOrderChainNum=1;
		if (this.orderChainNum != aOrderChainNum)
		{
			markModified();
		}
		orderChainNum = aOrderChainNum;
	}
	
	/**
	* @return 
	*/
	public int getSeqNum()
	{
		fetch();
		return seqNum;
	}
	/**
	* Gets referenced type pd.criminalcase.CriminalCase
	*/
	public CriminalCase getTheCase()
	{
		fetch();
		initTheCase();
		return theCase;
	}
	
	
	/**
	* Get the reference value to class :: pd.criminalcase.CriminalCase
	*/
	public String getTheCaseId()
	{
		fetch();
		return theCaseId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getVersionType()
	{
		fetch();
		initVersionType();
		return versionType;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getVersionTypeId()
	{
		fetch();
		return versionTypeId;
	}
	/**
	* Initialize class relationship to class pd.criminalcase.CriminalCase
	*/
	private void initTheCase()
	{
		if (theCase == null)
		{
			theCase =
				(CriminalCase) new mojo
					.km
					.persistence
					.Reference(theCaseId, CriminalCase.class)
					.getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initVersionType()
	{
		if (versionType == null)
		{
			versionType =
				(Code) new mojo
					.km
					.persistence
					.Reference(versionTypeId, Code.class)
					.getObject();
		}
	}
	/**
	* @param i
	*/
	public void setSeqNum(int i)
	{
		if (this.seqNum != i)
		{
			markModified();
		}
		seqNum = i;
	}
	/**
	* set the type reference for class member theCase
	*/
	public void setTheCase(CriminalCase aCase)
	{
		if (this.theCase == null || !this.theCase.equals(aCase))
		{
			markModified();
		}
		if (aCase.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aCase);
		}
		setTheCaseId("" + aCase.getOID());
		this.theCase = (CriminalCase) new mojo.km.persistence.Reference(aCase).getObject();
	}
	/**
	* Set the reference value to class :: pd.criminalcase.CriminalCase
	*/
	public void setTheCaseId(String aCaseId)
	{
		if (this.theCaseId == null || !this.theCaseId.equals(aCaseId))
		{
			markModified();
		}
		theCase = null;
		this.theCaseId = aCaseId;
	}
	/**
	* set the type reference for class member versionType
	*/
	public void setVersionType(Code aVersionType)
	{
		if (this.versionType == null || !this.versionType.equals(aVersionType))
		{
			markModified();
		}
		if (aVersionType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aVersionType);
		}
		setVersionTypeId("" + aVersionType.getOID());
		this.versionType = (Code) new mojo.km.persistence.Reference(aVersionType).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setVersionTypeId(String aVersionTypeId)
	{
		if (this.versionTypeId == null || !this.versionTypeId.equals(aVersionTypeId))
		{
			markModified();
		}
		versionType = null;
		this.versionTypeId = aVersionTypeId;
	}
}

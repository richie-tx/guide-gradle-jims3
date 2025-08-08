/*
 * Created on Sep 8, 2006
 */
package pd.supervision.administercasenotes;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author jmcnabb
 *
 */
public class CasenoteConditions extends PersistentObject
{

   private int casenoteId;
   private int sprOrderConditionId;
   private String caseNumber;
   private int conditionId;
   private int orderChainNumber;
   
   // These thre are for the view CSORDCONDCNOTE
   private String defendantId;
   private String criminalCaseId;
   private String supervisionOrderId;
  
   public String getDefendantId() {
	   fetch();
	   return defendantId;
   }

	public void setDefendantId(String defendantId) {
		if (this.defendantId == null || !this.defendantId.equals(defendantId))
		{
			markModified();
		}
		this.defendantId = defendantId;
	}
	
	public String getCriminalCaseId() {
		fetch();
		return criminalCaseId;
	}
	
	public void setCriminalCaseId(String criminalCaseId) {
		if (this.criminalCaseId == null || !this.criminalCaseId.equals(criminalCaseId))
		{
			markModified();
		}
		this.criminalCaseId = criminalCaseId;
	}
	
	public String getSupervisionOrderId() {
		fetch();
		return supervisionOrderId;
	}
	
	public void setSupervisionOrderId(String supervisionOrderId) {
		if (this.supervisionOrderId == null || !this.supervisionOrderId.equals(supervisionOrderId))
		{
			markModified();
		}
		this.supervisionOrderId = supervisionOrderId;
	}

   /**
	 * @return Returns the caseNumber.
	 */
	public String getCaseNumber() {
		fetch();
		return caseNumber;
	}
	
	/**
	 * @param caseNumber The caseNumber to set.
	 */
	public void setCaseNumber(String caseNumber) {
		if (this.caseNumber == null || !this.caseNumber.equals(caseNumber))
		{
			markModified();
		}
		this.caseNumber = caseNumber;
	}
	/**
	 * @return Returns the conditionId.
	 */
	public int getConditionId() {
		fetch();
		return conditionId;
	}
	/**
	 * @param conditionId The conditionId to set.
	 */
	public void setConditionId(int conditionId) {
		if (this.conditionId != conditionId)
   	    {
		    markModified();
	    }
   	    this.conditionId = conditionId;
	}
	/**
	 * @return Returns the orderChainNumber.
	 */
	public int getOrderChainNumber() {
		fetch();
		return orderChainNumber;
	}
	/**
	 * @param orderChainNumber The orderChainNumber to set.
	 */
	public void setOrderChainNumber(int orderChainNumber) {
		if (this.orderChainNumber != orderChainNumber)
   	    {
		    markModified();
	    }
		this.orderChainNumber = orderChainNumber;
	}
   	
   /**
    * @return Returns the casenoteId.
    */
   public int getCasenoteId() {
   	   fetch();
   	   return this.casenoteId;
   }
   /**
    * @param casenoteId The casenoteId to set.
    */
   public void setCasenoteId(int casenoteId) {
   	   if (this.casenoteId != casenoteId)
   	   {
		   markModified();
	   }
   	   this.casenoteId = casenoteId;
   }
   /**
    * @return Returns the sprOrderConditionId.
    */
   public int getSprOrderConditionId() {
   	   fetch();
   	   return this.sprOrderConditionId;
   }
   /**
    * @param sprOrderConditionId The sprOrderConditionId to set.
    */
   public void setSprOrderConditionId(int sprOrderConditionId) {
   	   if (this.sprOrderConditionId != sprOrderConditionId)
	   {
		   markModified();
	   }
	   this.sprOrderConditionId = sprOrderConditionId;
   }
   
   public static Iterator findAllByNumericParam(String attributeName, String attributeValue){
	   return new Home().findAll(attributeName, new Integer(attributeValue), CasenoteConditions.class);
   }

	/**
	 * @param event
	 * @return
	 */
	public static Iterator findAll(IEvent event) {
		return new Home().findAll(event, CasenoteConditions.class);
	}
	
    public void bind()
    {
        IHome home = new Home();
        home.bind(this);
    }//end of bind()
}

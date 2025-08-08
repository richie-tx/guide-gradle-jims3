/*
 * Created on Nov 20, 2008
 */
package pd.supervision.administercasenotes;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/**
 * @author mchowdhury
 *
 */
public class CasenoteNCConditions extends PersistentObject
{

   private String caseNumber;
   private int conditionId;
   private int casenoteId;
   private String defendantId;   
   private int sprOrderConditionId;   
   private int supervisionOrderId;
   private int nonCompEventId;
   private int nonCompCaseId;
   
   public int getNonCompCaseId() {
	   fetch();
	   return nonCompCaseId;
   }

	public void setNonCompCaseId(int nonCompCaseId) {
		if (this.nonCompCaseId != nonCompCaseId)
		{
			markModified();
		}
		this.nonCompCaseId = nonCompCaseId;
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
	public String getDefendantId() {
		fetch();
		return defendantId;
	}
	/**
	 * @param orderChainNumber The orderChainNumber to set.
	 */
	public void setDefendantId(String defendantId) {
		if(this.defendantId == null || !this.defendantId.equals(defendantId)){
		    markModified();
	    }
		this.defendantId = defendantId;
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
	   return new Home().findAll(attributeName, new Integer(attributeValue), CasenoteNCConditions.class);
   }

	/**
	 * @param event
	 * @return
	 */
	public static Iterator findAll(IEvent event) {
		return new Home().findAll(event, CasenoteNCConditions.class);
	}

	public int getSupervisionOrderId() {
		fetch();
		return supervisionOrderId;
	}

	public void setSupervisionOrderId(int supervisionOrderId) {
		if (this.supervisionOrderId != supervisionOrderId)
	    {
		   markModified();
	    }
		this.supervisionOrderId = supervisionOrderId;
	}

	public int getNonCompEventId() {
		fetch();
		return nonCompEventId;
	}

	public void setNonCompEventId(int nonCompEventId) {
		if (this.nonCompEventId != nonCompEventId)
	    {
		   markModified();
	    }
		this.nonCompEventId = nonCompEventId;
	}
}

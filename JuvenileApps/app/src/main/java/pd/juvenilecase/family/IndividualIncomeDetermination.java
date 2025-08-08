package pd.juvenilecase.family;

import mojo.km.persistence.PersistentObject;

public class IndividualIncomeDetermination extends PersistentObject 
{
	/**
	 * Composition to BenefitsAssessment.
	 */
	private String benefitsAssessmentId;
	
	/**
	 * FamilyMember id if this was a family member at the time the 
	 * assessment was made, otherwise null.
	 */
    private String memberId;
    
    private String name;
    
    private int age;

	/**
	 * Reference to relationship code table.
	 */    
    private String relationshipToJuvenileId;

	/**
	 * Relationship to juvenile decription.
	 * 
	 * todo: NOT MAPPED TO DATABASE. 
	 */
	private String relationshipToJuvenile;

   
    private String comments;
    
    private String incomeSourceId;
    
	private String incomeSource;
	
    private int grossMonthyIncome;
   
   /**
    * @roseuid 4371001F03BC
    */
   public IndividualIncomeDetermination() 
   {
   }
   
   /**
    * Access method for the name property.
    * 
    * @return   the current value of the name property
    */
   public String getName()
   {
   		fetch();
      	return name;    
   }
   
   /**
    * Sets the value of the name property.
    * 
    * @param aName the new value of the name property
    */
   public void setName(String aName)
   {
   		markModified();
      	name = aName;    
   }
   
   /**
    * Access method for the age property.
    * 
    * @return   the current value of the age property
    */
   public int getAge() 
   {
		fetch();
      	return age;    
   }
   
   /**
    * Sets the value of the age property.
    * 
    * @param aAge the new value of the age property
    */
   public void setAge(int aAge) 
   {
		markModified();
      	age = aAge;    
   }
   
   /**
    * Access method for the relationshipToJuvenile property.
    * 
    * @return   the current value of the relationshipToJuvenile property
    */
   public String getRelationshipToJuvenileId()
   {
		fetch();
      	return relationshipToJuvenileId;    
   }
   
   /**
    * Sets the value of the relationshipToJuvenile property.
    * 
    * @param aRelationshipToJuvenile the new value of the relationshipToJuvenile property
    */
   public void setRelationshipToJuvenileId(String aRelationshipToJuvenile)
   {
		markModified();
      	relationshipToJuvenileId = aRelationshipToJuvenile;    
   }
   
   /**
    * Access method for the comments property.
    * 
    * @return   the current value of the comments property
    */
   public String getComments()
   {
		fetch();
      	return comments;    
   }
   
   /**
    * Sets the value of the comments property.
    * 
    * @param aComments the new value of the comments property
    */
   public void setComments(String aComments)
   {
		markModified();
      	comments = aComments;    
   }
   
   /**
    * Access method for the incomeSource property.
    * 
    * @return   the current value of the incomeSource property
    */
   public String getIncomeSourceId()
   {
		fetch();
      	return incomeSourceId;    
   }
   
   /**
    * Sets the value of the incomeSource property.
    * 
    * @param aIncomeSource the new value of the incomeSource property
    */
   public void setIncomeSourceId(String aIncomeSource)
   {
		markModified();
      	incomeSourceId = aIncomeSource;    
   }
   
   /**
    * Access method for the grossMonthyIncome property.
    * 
    * @return   the current value of the grossMonthyIncome property
    */
   public int getGrossMonthyIncome() 
   {
		fetch();
      	return grossMonthyIncome;    
   }
   
   /**
    * Sets the value of the grossMonthyIncome property.
    * 
    * @param aGrossMonthyIncome the new value of the grossMonthyIncome property
    */
   public void setGrossMonthyIncome(int aGrossMonthyIncome) 
   {
		markModified();
 		grossMonthyIncome = aGrossMonthyIncome;    
   }
   
	/**
	 * @return
	 */
	public String getBenefitsAssessmentId()
	{
		fetch();
		return benefitsAssessmentId;
	}

	/**
	 * @return
	 */
	public String getMemberId()
	{
		fetch();
		return memberId;
	}

	/**
	 * @param string
	 */
	public void setBenefitsAssessmentId(String string)
	{
		markModified();
		benefitsAssessmentId = string;
	}

	/**
	 * @param string
	 */
	public void setMemberId(String string)
	{
		markModified();
		memberId = string;
	}

	/**
	 * @return
	 */
	public String getIncomeSource()
	{
		return incomeSource;
	}

	/**
	 * @return
	 */
	public String getRelationshipToJuvenile()
	{
		return relationshipToJuvenile;
	}

	/**
	 * @param string
	 */
	public void setIncomeSource(String string)
	{
		incomeSource = string;
	}

	/**
	 * @param string
	 */
	public void setRelationshipToJuvenile(String string)
	{
		relationshipToJuvenile = string;
	}

}

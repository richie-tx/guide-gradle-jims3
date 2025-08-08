/*
 * Created on Oct 10, 2005
 *
 */
package messaging.supervisionoptions.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author bschwartz
 *
 */
public class CourtPolicyResponseEvent extends ResponseEvent implements Comparable
{
	private String agencyId;
	private String policyId;
	private String name;
	private String group1Id;
	private String group2Id;
	private String group3Id;

	//these are resolved in Action with UIHelper's help
	private String group1Name;
	private String group2Name;
	private String group3Name;
	private String courtName;

	public int compareTo(Object arg0)
		{
			String newString = "";
			CourtPolicyResponseEvent cre = (CourtPolicyResponseEvent) arg0;
			if (this.name == null)
			{
				this.name = newString;
			}
			if (this.group1Name == null)
			{
				this.group1Name = newString;
			}
			if (this.group2Name == null)
			{
				this.group2Name = newString;
			}
			if (this.group3Name == null)
			{
				this.group3Name = newString;
			}
			if (cre.name == null)
			{
				cre.name = newString;
			}
			if (cre.group1Name == null)
			{
				cre.group1Name = newString;
			}
			if (cre.getGroup2Name() == null)
			{
				cre.group2Name = newString;
			}
			if (cre.getGroup3Name() == null)
			{
				cre.group3Name = newString;
			}
			int comparisonResult = 0;
			if (this.name.compareTo(cre.getName()) == 0)
			{
				if (this.group1Name.compareTo(cre.getGroup1Name()) == 0)
				{
					if (this.group2Name.compareTo(cre.getGroup2Name()) == 0)
					{
						comparisonResult = this.group3Name.compareTo(cre.getGroup3Name());
					}
					else
					{
						comparisonResult = this.group2Name.compareTo(cre.getGroup2Name());
					}
				}
				else
				{
					comparisonResult = this.group1Name.compareTo(cre.getGroup1Name());
				}
			}
			else
			{
				comparisonResult = this.name.compareTo(cre.getName());
			}
		
			return comparisonResult;
		}


	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}
	
	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
	}
	
	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 
	 */
	public void setName( String aName )
	{
		name = aName;
	}

   /**
	* @return
	*/
   public String getGroup1Id()
   {
	   return group1Id;
   }

   /**
	* @return
	*/
   public String getGroup2Id()
   {
	   return group2Id;
   }

   /**
	* @return
	*/
   public String getGroup3Id()
   {
	   return group3Id;
   }

   /**
	* @param string
	*/
   public void setGroup1Id(String string)
   {
	   group1Id = string;
   }

   /**
	* @param string
	*/
   public void setGroup2Id(String string)
   {
	   group2Id = string;
   }

   /**
	* @param string
	*/
   public void setGroup3Id(String string)
   {
	   group3Id = string;
   }

	/**
	 * @return
	 */
	public String getCourtName()
	{
		return courtName;
	}

	/**
	 * @return
	 */
	public String getGroup1Name()
	{
		return group1Name;
	}

	/**
	 * @return
	 */
	public String getGroup2Name()
	{
		return group2Name;
	}

	/**
	 * @return
	 */
	public String getGroup3Name()
	{
		return group3Name;
	}

	/**
	 * @param string
	 */
	public void setCourtName(String string)
	{
		courtName = string;
	}

	/**
	 * @param string
	 */
	public void setGroup1Name(String string)
	{
		group1Name = string;
	}

	/**
	 * @param string
	 */
	public void setGroup2Name(String string)
	{
		group2Name = string;
	}

	/**
	 * @param string
	 */
	public void setGroup3Name(String string)
	{
		group3Name = string;
	}

	/**
	 * @return
	 */
	public String getPolicyId()
	{
		return policyId;
	}

	/**
	 * @param string
	 */
	public void setPolicyId(String string)
	{
		policyId = string;
	}

}

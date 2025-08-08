/*
 * Created on Sep 19, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.supervision.SupervisionOptions.form;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AssociateBean
{
	public static final String CONDITION = "Condition"; 
	public static final String COURTPOLICY = "CourtPolicy"; 
	public static final String DEPARTMENTPOLICY = "DapartmentPolicy"; 

	private String objId;
	private String objName;
	private String group1Id;
	private String group1Name;
	private String group2Id;
	private String group2Name;
	private String group3Id;
	private String group3Name;
	private String objType;

	public boolean equals( Object b )
	{
		if ( ! (b instanceof AssociateBean) )
		{
			return false;
		}
		
		return objId.equals( ((AssociateBean)b).objId ); 
	}
		
	public int hashCode()
	{
		return objId.hashCode();
	}
	
	/**
	 * @return
	 */
	public void clear()
	{
		objId = null;
		group1Id = null;
		group1Name = null;
		group2Id = null;
		group2Name = null;
		group3Id = null;
		group3Name = null;
		objName = null;
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
	public String getGroup1Name()
	{
		return group1Name;
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
	public String getGroup2Name()
	{
		return group2Name;
	}

	/**
	 * @return
	 */
	public String getGroup3Id()
	{
		return group3Id;
	}

	/**
	 * @return
	 */
	public String getGroup3Name()
	{
		return group3Name;
	}

	/**
	 * @return
	 */
	public String getObjId()
	{
		return objId;
	}

	/**
	 * @return
	 */
	public String getObjName()
	{
		return objName;
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
	public void setGroup1Name(String string)
	{
		group1Name = string;
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
	public void setGroup2Name(String string)
	{
		group2Name = string;
	}

	/**
	 * @param string
	 */
	public void setGroup3Id(String string)
	{
		group3Id = string;
	}

	/**
	 * @param string
	 */
	public void setGroup3Name(String string)
	{
		group3Name = string;
	}

	/**
	 * @param string
	 */
	public void setObjId(String string)
	{
		objId = string;
	}

	/**
	 * @param string
	 */
	public void setObjName(String string)
	{
		objName = string;
	}

	/**
	 * @return
	 */
	public String getObjType()
	{
		return objType;
	}

	/**
	 * @param string
	 */
	public void setObjType(String string)
	{
		objType = string;
	}

}

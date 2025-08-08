package mojo.km.security;

public class UserGroupEntityBean
{
    private int id;
    private String groupname;
    private String groupdescription;
    private boolean isIsActive;//isActive;
    private String grouptype;
    private String category;

    /** @return the id */
    public int getId()
    {
	return id;
    }

    /** @param id
     *            the id to set */
    public void setId(int id)
    {
	this.id = id;
    }

    /** @return the groupname */
    public String getGroupname()
    {
	return groupname;
    }

    /** @param groupname
     *            the groupname to set */
    public void setGroupname(String groupname)
    {
	this.groupname = groupname;
    }

    /** @return the groupdescription */
    public String getGroupdescription()
    {
	return groupdescription;
    }

    /** @param groupdescription
     *            the groupdescription to set */
    public void setGroupdescription(String groupdescription)
    {
	this.groupdescription = groupdescription;
    }

   
    /** @return the grouptype */
    public String getGrouptype()
    {
	return grouptype;
    }

    /** @param grouptype
     *            the grouptype to set */
    public void setGrouptype(String grouptype)
    {
	this.grouptype = grouptype;
    }

    /** @return the category */
    public String getCategory()
    {
	return category;
    }

    /** @param category
     *            the category to set */
    public void setCategory(String category)
    {
	this.category = category;
    }


    /**
     * @return the isIsActive
     */
    public boolean isIsActive()
    {
	return isIsActive;
    }

    /**
     * @param isIsActive the isIsActive to set
     */
    public void setIsActive(boolean isIsActive)
    {
	this.isIsActive = isIsActive;
    }
}

package mojo.km.security;

public class RolesEntityBean
{
    private int id;
    private String rolename;
    private String roledesc;
    private boolean activeind;
    private String roletype;

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

    /** @return the rolename */
    public String getRolename()
    {
	return rolename;
    }

    /** @param rolename
     *            the rolename to set */
    public void setRolename(String rolename)
    {
	this.rolename = rolename;
    }

    /** @return the roledesc */
    public String getRoledesc()
    {
	return roledesc;
    }

    /** @param roledesc
     *            the roledesc to set */
    public void setRoledesc(String roledesc)
    {
	this.roledesc = roledesc;
    }

    /** @return the activeind */
    public boolean isActiveind()
    {
	return activeind;
    }

    /** @param activeind
     *            the activeind to set */
    public void setActiveind(boolean activeind)
    {
	this.activeind = activeind;
    }

    /** @return the roletype */
    public String getRoletype()
    {
	return roletype;
    }

    /** @param roletype
     *            the roletype to set */
    public void setRoletype(String roletype)
    {
	this.roletype = roletype;
    }

}

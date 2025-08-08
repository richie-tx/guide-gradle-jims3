package mojo.km.security;

public class JIMS2FeaturesEntityBean
{
    private int id;
    //#87188
    protected String featurename;
    protected int featuretype;
    protected String featurecategory;
    protected String featuretypejims2;
    protected String description;
    protected String parentfeaturename;
    protected int parentfeatureid;

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

    /** @return the featurename */
    public String getFeaturename()
    {
	return featurename;
    }

    /** @param featurename
     *            the featurename to set */
    public void setFeaturename(String featurename)
    {
	this.featurename = featurename;
    }

    /** @return the featuretype */
    public int getFeaturetype()
    {
	return featuretype;
    }

    /** @param featuretype
     *            the featuretype to set */
    public void setFeaturetype(int featuretype)
    {
	this.featuretype = featuretype;
    }

    /** @return the featurecategory */
    public String getFeaturecategory()
    {
	return featurecategory;
    }

    /** @param featurecategory
     *            the featurecategory to set */
    public void setFeaturecategory(String featurecategory)
    {
	this.featurecategory = featurecategory;
    }

    /** @return the featuretypejims2 */
    public String getFeaturetypejims2()
    {
	return featuretypejims2;
    }

    /** @param featuretypejims2
     *            the featuretypejims2 to set */
    public void setFeaturetypejims2(String featuretypejims2)
    {
	this.featuretypejims2 = featuretypejims2;
    }

    /** @return the description */
    public String getDescription()
    {
	return description;
    }

    /** @param description
     *            the description to set */
    public void setDescription(String description)
    {
	this.description = description;
    }

    /** @return the parentfeaturename */
    public String getParentfeaturename()
    {
	return parentfeaturename;
    }

    /** @param parentfeaturename
     *            the parentfeaturename to set */
    public void setParentfeaturename(String parentfeaturename)
    {
	this.parentfeaturename = parentfeaturename;
    }

    /** @return the parentfeatureid */
    public int getParentfeatureid()
    {
	return parentfeatureid;
    }

    /** @param parentfeatureid
     *            the parentfeatureid to set */
    public void setParentfeatureid(int parentfeatureid)
    {
	this.parentfeatureid = parentfeatureid;
    }

}

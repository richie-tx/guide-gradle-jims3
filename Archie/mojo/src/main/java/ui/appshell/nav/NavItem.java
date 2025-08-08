package ui.appshell.nav;

import java.util.List;

public class NavItem
{
    private List children;

    private String[] features;

    private String name;

    private String url;

    private String[] userTypes;

    public NavItem(String pName, String pUrl, String[] pFeatures, String[] pUserTypes, List pChildren)
    {
        this.name = pName;
        this.url = pUrl;
        this.features = pFeatures;
        this.userTypes = pUserTypes;
        this.children = pChildren;
    }

    public List getChildren()
    {
        return children;
    }

    public String getName()
    {
        return name;
    }

    public String[] getRequiredFeatures()
    {
        return features;
    }

    public String[] getRequiredUserTypes()
    {
        return userTypes;
    }

    public String getUrl()
    {
        return url;
    }

    public boolean hasChildren()
    {
        return (children != null && children.size() > 0);
    }

    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("name: ");
        buffer.append(name);
        buffer.append(" url: ");
        buffer.append(url);
        return buffer.toString();
    }
}

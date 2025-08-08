/*
 * Created on Jul 13, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.appshell.nav;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import mojo.km.config.PropertyBundleProperties;
import mojo.km.context.ContextManager;
import mojo.km.security.ISecurityManager;
import mojo.km.utilities.XMLManager;
import org.jdom.Document;
import org.jdom.Element;

/**
 * This class is used to represent the Left Navigation Tree in the UI. It takes into account the Features that the current user of
 * the system has and only displays the features they have access to.
 * 
 * @author glyons
 * 
 * To change the template for this generated type comment go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 * Comments
 */
public class UILeftNavProperties implements INavProperties
{
    /**
     * Constructor for UILeftNavproperties that will create a left navigation Prorpertues based of the xml schema file. The schema
     * file name is located under the LeftNavigationSchema property in mojo.xml.
     */
    private UILeftNavProperties()
    {
        this.leftNavItems = new ArrayList();
    }

    private List leftNavItems;

    // FIXME refactor to build a more efficent nav menu graph instead of parsing XML every time!
    private Element rootElement;

    private static UILeftNavProperties instance;

    public static UILeftNavProperties getInstance() throws Exception
    {
        if (instance == null)
        {
            instance = new UILeftNavProperties();
           // PropertyBundleProperties propBundle = PropertyBundleProperties.getInstance();
           // String schema = propBundle.getProperty("LeftNavigationSchema");
      
            String schema = System.getProperty("LeftNav");
            if (schema == null)
            {
            schema = "UILeftNavigation.xml"; 
            }
            System.out.println("LeftNAv = " + schema);
            
            
            Document doc = XMLManager.readXMLResource(schema);
            instance.rootElement = doc.getRootElement();
            instance.parse(instance.rootElement);
        }
        return instance;
    }

    /**
     * @param aRoot
     */
    private void parse(Element aRoot)
    {
        if (aRoot != null && aRoot.getName().equalsIgnoreCase("UILeftNavigation"))
        {
            List items = aRoot.getChildren();
            if (items != null)
            {
                for (int x = 0; x < items.size(); x++)
                {
                    Element item = (Element) items.get(x);

                    NavItem nav = createNavItem(item, 0);
                    if (nav != null)
                    {
                        instance.leftNavItems.add(nav);
                    }
                }
            }
        }
    }

    public List getNavItems2()
    {
        List list = new ArrayList();

        ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");

        int len = leftNavItems.size();
        for (int x = 0; x < len; x++)
        {
            NavItem item = (NavItem) leftNavItems.get(x);

            NavItem nav = createNavItem2(item, 0, mgr, list);
            if (nav != null)
            {
                list.add(nav);
            }
        }

        return list;
    }

    public List getNavItems()
    {
        List navItemList = new ArrayList();
        try
        {
            navItemList = readNavigationSchema(rootElement);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return navItemList;
    }

    /**
     * Takes the XMLDocument for the LeftNavigation and returns a list of Navitems that represent the left nav hierarchy based off
     * the users feature list or user type (UILeftNavigation.xml).
     * 
     * @param doc
     * @return List of navitems accesible for the current user
     */
    private List readNavigationSchema(Element root) throws Exception
    {
        List list = new ArrayList();
        if (root != null && root.getName().equalsIgnoreCase("UILeftNavigation"))
        {
            ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");

            List items = root.getChildren();
            if (items != null)
            {
                for (int x = 0; x < items.size(); x++)
                {
                    Element item = (Element) items.get(x);

                    NavItem nav = createNavItem(item, 0, mgr);
                    if (nav != null)
                    {
                        list.add(nav);
                    }
                }
            }
        }
        return list;
    }

    /*
     * Will create a NavItem based off a document Element. The Element should have the following attributes: - name - name to
     * display for the link or folder heading - url - to use in creating a link - requiredFeatures - string list of features
     * deliminted by a ',' (comma) - requiredUserTypes - string list of user types that have this navigation item
     * 
     * Rules- 1) if requiredFeatures is empty, all users have access 2) if requiredUserTypes is empty, all users have access 3) if
     * requiredFeatures is not empty, user must be granted that feature 4) if requiredUserTypes is not empty, user must be that
     * userType 5) if both requiredFeatures and requiredUserTypes are not empty, user must be granted the feature AND must by of
     * the correct User Type specified
     * 
     * Top Level NavItems that do not have any children and have no URL will not display
     */
    private NavItem createNavItem(Element ele, int tab, ISecurityManager mgr)
    {
        String name = ele.getAttributeValue("name");
        String url = ele.getAttributeValue("url");
        String[] features = getAsArray(ele.getAttributeValue("requiredFeatures"), ",");
        String[] userTypes = getAsArray(ele.getAttributeValue("requiredUserTypes"), ",");

        boolean valid = isUserAccessible(features, userTypes, mgr);

        NavItem nav = null;
        if (valid == true)
        {
            List children = ele.getChildren();
            ArrayList c = new ArrayList();
            if (children.isEmpty() == false)
            {
                tab++;
                for (int x = 0; x < children.size(); x++)
                {
                    NavItem childItem = createNavItem((Element) children.get(x), tab, mgr);
                    if (childItem != null)
                    {

                        c.add(childItem);
                    }
                }
            }
            // If there is no url and no children, then the
            // navitem will not be displayed
            if (url != null || c.size() > 0)
            {
                nav = new NavItem(name, url, features, userTypes, c);
            }
        }

        return nav;
    }

    private NavItem createNavItem2(NavItem nav, int tab, ISecurityManager mgr, List list)
    {
        String name = nav.getName();
        String url = nav.getUrl();
        String[] features = nav.getRequiredFeatures();
        String[] userTypes = nav.getRequiredUserTypes();

        boolean valid = isUserAccessible(features, userTypes, mgr);

        NavItem childItem = nav;

        if (valid == true)
        {
            List c = new ArrayList();
            List children = nav.getChildren();

            if (children.isEmpty() == false)
            {
                tab++;
                int len = children.size();
                for (int x = 0; x < len; x++)
                {
                    childItem = (NavItem) children.get(x);
                    childItem = createNavItem2(childItem, tab, mgr, c);
                    if (childItem != null)
                    {
                        c.add(childItem);
                    }
                }
            }

            // If there is no url and no children, then the
            // navitem will not be displayed
            if (url != null || c.size() > 0)
            {
                childItem = new NavItem(name, url, features, userTypes, c);
            }
        }
        else
        {
            childItem = null;
        }

        return childItem;
    }

    private NavItem createNavItem(Element ele, int tab)
    {
        String name = ele.getAttributeValue("name");
        String url = ele.getAttributeValue("url");
        String[] features = getAsArray(ele.getAttributeValue("requiredFeatures"), ",");
        String[] userTypes = getAsArray(ele.getAttributeValue("requiredUserTypes"), ",");

        NavItem nav = null;
        List children = ele.getChildren();
        List c = new ArrayList();
        if (children.isEmpty() == false)
        {
            tab++;
            for (int x = 0; x < children.size(); x++)
            {
                NavItem childItem = createNavItem((Element) children.get(x), tab);
                if (childItem != null)
                {

                    c.add(childItem);
                }
            }
        }
        // If there is no url and no children, then the
        // navitem will not be displayed
        if (url != null || c.size() > 0)
        {
            nav = new NavItem(name, url, features, userTypes, c);
        }

        return nav;
    }

    /**
     * Determines if a user is granted the features and is of the required user type. If both params pass in values, the user must
     * have one of the features AND be of the user type.
     * 
     * @param requiredFeatures
     * @param requiredUserTypes
     * @return true if the user has the features and is of the user types
     */
    private boolean isUserAccessible(String[] requiredFeatures, String[] requiredUserTypes, ISecurityManager mgr)
    {
        /*
         * See if this item has required features, if not then the user has access to it. If so, check to see if the user has that
         * feature available to them.
         */
        boolean userHasFeature = false;
        if (requiredFeatures.length > 0)
        {
            for (int f = 0; f < requiredFeatures.length; f++)
            {
                if (mgr.isAllowed(requiredFeatures[f]))
                {
                    userHasFeature = true;
                    break;
                }
            }

        }
        else
        {
            userHasFeature = true;
        }

        /*
         * See if the nav item has a required User Type. If so, validate that the current user has a user type of one of the
         * required user types. If not required User types are required, all users have access to the item
         */
        boolean isUserType = false;
        if (requiredUserTypes.length > 0)
        {
            String currentUserType = mgr.getIUserInfo().getUserTypeId();

            for (int u = 0; u < requiredUserTypes.length; u++)
            {
                if (currentUserType.equalsIgnoreCase(requiredUserTypes[u]))
                {
                    isUserType = true;
                    break;
                }
            }
        }
        else
        {
            isUserType = true;
        }
        return userHasFeature && isUserType;
    }

    /*
     * Returns a String[] based off the deliminted string.
     */
    private String[] getAsArray(String delimitedString, String delimiter)
    {
        if (delimitedString == null)
        {
            return new String[0];
        }
        StringTokenizer st = new StringTokenizer(delimitedString, delimiter);
        Vector items = new Vector();
        while (st.hasMoreTokens())
        {
            String token = st.nextToken();
            items.add(token);
        }
        String[] retArray = new String[items.size()];
        items.copyInto(retArray);
        return retArray;
    }

    /**
     * Used for Debugging, prints all the navitems that this user has.
     * 
     * @param navitems
     */
    private void printNavItems(List navitems)
    {
        for (int x = 0; x < navitems.size(); x++)
        {
            NavItem i = (NavItem) navitems.get(x);
            printNavItem(i, 0);
        }
    }

    /**
     * Prints navitems for debuggin
     * 
     * @param navItem
     * @param tab
     */
    private void printNavItem(NavItem navItem, int tab)
    {
        try
        {
            String tabs = "";
            for (int x = 0; x < tab; x++)
            {
                tabs += "\t";
            }
            String type = "FOLDER";
            if (navItem.getChildren().size() == 0)
                type = "LINK";

            System.out.println(tabs + "[" + type + "] " + navItem.getName());
            tab++;
            for (int z = 0; z < navItem.getChildren().size(); z++)
            {
                NavItem i = (NavItem) navItem.getChildren().get(z);

                printNavItem(i, tab);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}

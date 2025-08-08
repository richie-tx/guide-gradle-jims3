/*
 * Class FileRealm.java
 * Created on June 18, 2002, 12:56:41 PM
 */

package mojo.km.security.role.file;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mojo.km.security.NamingException;
import mojo.km.security.helper.IPermissions;
import mojo.km.security.helper.Permission;
import mojo.km.security.helper.Permissions;
import mojo.km.security.role.IRoles;
import mojo.km.security.role.Roles;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * @author Chad Oliver
 * @modelguid {CE428604-ED59-4DAF-9E12-3C896A2FDFD1}
 */
public class FileRealm
{
    private String filename = null;

    private static Map files = new Hashtable();

    /** @modelguid {7E2F162D-1FB2-4A69-B4CD-83775C1DC1F3} */
    public static IRoles login(FileUser fileUser) throws NamingException
    {
        if (fileUser == null)
        {
            throw new NamingException("Username was not provided");
        }

        String roles = FileRealm.getInstance("mojo/km/security/role/file/security-users.xml").validateCredentials(
                fileUser.getUserName(), fileUser.getPassword());
        return FileRealm.parseRoleship(roles);
    }

    /** @modelguid {6DD18384-D2BD-41C8-B56F-6CFC4871A52E} */
    public static String authenticateUser(String username, String password) throws NamingException
    {
        String roles = FileRealm.getInstance("mojo/km/security/role/file/security-users.xml").validateCredentials(
                username, password);
        return roles;
    }

    private String validateCredentials(String username, String password) throws NamingException
    {
        Document doc = null;
        Element root = null;
        List userList = null;

        try
        {
            doc = getDocument();
            root = doc.getRootElement();
            userList = root.getChildren("user");
        }
        catch (Exception e)
        {
            throw new NamingException("Unable to open file security-users.xml");
        }

        Iterator users = userList.iterator();
        while (users.hasNext())
        {
            Element user = (Element) users.next();

            if (user.getAttribute("username").getValue().equalsIgnoreCase(username))
            {
                if (user.getAttribute("password").getValue().equalsIgnoreCase(password))
                {
                    return user.getAttribute("roles").getValue();
                }
                else
                {
                    throw new NamingException("Invalid password was provided");
                }
            }
        }

        throw new NamingException("Invalid username was provided");
    }

    /** @modelguid {C9ABE598-593C-401B-93DE-DC07DED80D91} */
    public static IRoles parseRoleship(String roles) throws NamingException
    {
        Roles newRoles = new Roles();

        java.util.StringTokenizer token = new java.util.StringTokenizer(roles, ",");
        String roleName = null;

        while (token.hasMoreElements())
        {
            roleName = (String) token.nextElement();
            newRoles.add(roleName);
        }

        return newRoles;
    }

    /**
     * Added to allow JIMS2 to get the user's Contact name. This method should be deprecated and
     * this responsibilty moved.
     * 
     * @param username
     * @return String User's full name (First and Last)
     * @throws NamingException
     */
    public static String getFullName(String username) throws NamingException
    {
        Document doc = null;

        try
        {
            doc = FileRealm.getInstance("mojo/km/security/role/file/security-users.xml").getDocument();
        }
        catch (Exception e)
        {
            throw new NamingException("Unable to open file security-users.xml");
        }

        List userList = doc.getRootElement().getChildren("user");
        Iterator users = userList.iterator();
        Element user;

        while (users.hasNext())
        {
            user = (Element) users.next();

            if (user.getAttribute("username").getValue().equalsIgnoreCase(username))
            {
                return user.getAttribute("fullname").getValue();
            }
        }

        throw new NamingException("Invalid username was provided");
    }

    /**
     * 
     * @param fileName
     * @return
     */
    public static IPermissions getACL(IRoles roles) throws Exception
    {
        Document doc = FileRealm.getInstance("mojo/km/security/role/file/aclImpl.xml").getDocument();
        IPermissions permissions = new Permissions();

        Element root = doc.getRootElement();
        List rolesElem = root.getChildren("ROLE");
        Enumeration en = roles.getEnumeration();
        while (en.hasMoreElements())
        {
            String roleName = en.nextElement().toString();
            getPermissions(permissions, roleName, rolesElem);
        }

        return permissions;
    }

    private static void getPermissions(IPermissions permissions, String roleName, List rolesElem) throws Exception
    {
        Iterator it = rolesElem.iterator();
        while (it.hasNext())
        {
            Element roleElem = (Element) it.next();
            if (roleName.equals(roleElem.getAttributeValue("name")))
            {
                // get its features
                List featuresElem = roleElem.getChildren("PERMISSION");
                Iterator features = featuresElem.iterator();
                while (features.hasNext())
                {// traverse through features list
                    Element featureElem = (Element) features.next();
                    Permission permission = new Permission();
                    permission.setPropertyName(featureElem.getAttributeValue("name"));
                    if (featureElem.getAttributeValue("parentId") != null)
                    {
                        permission.setServiceName(featureElem.getAttributeValue("url"));
                    }
                    permissions.put(featureElem.getAttributeValue("id"), permission);
                }
            }
        }
    }

    private static FileRealm getInstance(String fileName)
    {
        if (files.containsKey(fileName))
        {
            return (FileRealm) files.get(fileName);
        }
        FileRealm newInstance = new FileRealm();
        newInstance.filename = fileName;
        files.put(fileName, newInstance);
        return newInstance;
    }

    private Document getDocument() throws Exception
    {
        Document doc = null;
        InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(filename);
        SAXBuilder builder = new SAXBuilder();
        doc = builder.build(inStream);
        inStream.close();
        return doc;
    }

}

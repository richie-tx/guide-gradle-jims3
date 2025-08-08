/*
 * Created on Jun 28, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mojo.km.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import mojo.km.config.xml.XMLPackageControlAdapter;

/**
 * @author Jim Fisher
 *
 */
public class PackageControlProperties extends GenericProperties
{
    private static final String PACKAGE_CONTROL = "package-control";

    private Map packageGuards;

    private static PackageControlProperties instance;

    public PackageControlProperties()
    {
        packageGuards = new HashMap();
    }

    public static PackageControlProperties getInstance(String aConfigFile)
    {
        if (instance == null)
        {
            instance = new PackageControlProperties();
            XMLPackageControlAdapter xmlAdapter = new XMLPackageControlAdapter(instance, aConfigFile);
            try
            {
                xmlAdapter.loadProperties();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public void addPackageGuard(PackageGuardProperties aProps)
    {
        this.packageGuards.put(aProps.getPackage(), aProps);
    }

    public Set getPackages()
    {
        return this.packageGuards.keySet();
    }

    public PackageGuardProperties getPackageGuard(String aPackage)
    {
        return (PackageGuardProperties) this.packageGuards.get(aPackage);
    }
}

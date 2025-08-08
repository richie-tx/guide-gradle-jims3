/*
 * Created on Jun 28, 2006
 *
 */
package mojo.km.config;

import java.util.HashSet;
import java.util.Set;


/**
 * @author Jim Fisher
 *
 */
public class PackageGuardProperties extends GenericProperties
{
    public static final String PACKAGE = "package";

    public static final String ALLOW = "allow";

    public static final String DISALLOW = "disallow";

    public static final String NAME_ATTR = "name";

    public static final String STRICT_ATTR = "strict";

    private Set allowGuards;

    private Set disallowGuards;

    private boolean strict;

    public PackageGuardProperties()
    {
        super();
        this.allowGuards = new HashSet();
        this.disallowGuards = new HashSet();
    }

    public String getPackage()
    {
        return this.getProperty(PACKAGE);
    }

    public void setPackage(String aPackageName)
    {
        this.setProperty(PACKAGE, aPackageName);
    }

    public void addAllowGuard(String anAllowPackage)
    {
        this.allowGuards.add(anAllowPackage);
    }

    public void addDisallowGuard(String aDisallowPackage)
    {
        this.disallowGuards.add(aDisallowPackage);
    }

    public Set getAllowGuards()
    {
        return this.allowGuards;
    }

    public Set getDisallowGuards()
    {
        return this.disallowGuards;
    }

    public boolean isStrict()
    {
        return this.strict;
    }

    public void setStrict(boolean aStrict)
    {
        this.strict = aStrict;
    }
}

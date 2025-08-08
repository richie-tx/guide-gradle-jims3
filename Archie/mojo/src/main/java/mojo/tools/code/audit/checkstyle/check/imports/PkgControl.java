package mojo.tools.code.audit.checkstyle.check.imports;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents the a tree of guards for controlling whether packages are allowed
 * to be used. Each instance must have a single parent or be the root node.
 * Each instance may have zero or more children.
 *
 * @author Jim Fisher
 */
class PkgControl
{
    /** List of {@link Guard} objects to check. */
    private final LinkedList mGuards = new LinkedList();

    /** List of children {@link PkgControl} objects. */
    private final List mChildren = new ArrayList();

    /** The parent. Null indicates we are the root node. */
    private final PkgControl mParent;

    /** The full package name for the node. */
    private final String mFullPackage;

    /**
     * Construct a root node.
     * @param aPkgName the name of the package.
     */
    PkgControl(final String aPkgName)
    {
        mParent = null;
        if (aPkgName.equals("."))
        {
            mFullPackage = "";
        }
        else
        {
            mFullPackage = aPkgName;
        }
    }

    /**
     * Construct a child node.
     * @param aParent the parent node.
     * @param aSubPkg the sub package name.
     */
    PkgControl(final PkgControl aParent, final String aSubPkg)
    {
        mParent = aParent;
        if (aParent.getFullPackage().equals(""))
        {
            mFullPackage = aSubPkg;
        }
        else
        {
            mFullPackage = aParent.getFullPackage() + "." + aSubPkg;
        }
        mParent.mChildren.add(this);
    }

    /**
     * Adds a guard to the node.
     * @param aThug the guard to be added.
     */
    void addGuard(final Guard aThug)
    {
        mGuards.addFirst(aThug);
    }

    /**
     * @return the full package name represented by the node.
     */
    String getFullPackage()
    {
        return mFullPackage;
    }

    /**
     * Search down the tree to locate the finest match for a supplied package.
     * @param aForPkg the package to search for.
     * @return the finest match, or null if no match at all.
     */
    PkgControl locateFinest(final String aForPkg)
    {
        String pkg = mFullPackage;

        // Check if we are a match.
        // This algormithm should be improved to check for a trailing "."
        // or nothing following.
        if (pkg.equals(".") == false && aForPkg.startsWith(pkg) == false)
        {
            return null;
        }        

        // Check if any of the children match.
        final Iterator it = mChildren.iterator();
        while (it.hasNext())
        {
            final PkgControl pc = (PkgControl) it.next();
            final PkgControl match = pc.locateFinest(aForPkg);
            if (match != null)
            {
                return match;
            }
        }

        // No match so I am the best there is.
        return this;
    }

    /**
     * Returns whether a package is allowed to be used. The algorithm checks
     * with the current node for a result, and if none is found then calls
     * its parent looking for a match. This will recurse looking for match.
     * If there is no clear result then {@link AccessResult#UNKNOWN} is
     * returned.
     * @param aForImport the package to check on.
     * @param aInPkg the package doing the import.
     * @return an {@link AccessResult}.
     */
    AccessResult checkAccess(final String aForImport, final String aInPkg)
    {
        AccessResult retVal = localCheckAccess(aForImport, aInPkg);
        if (retVal != AccessResult.UNKNOWN)
        {
            return retVal;
        }
        else if (mParent == null)
        {
            // we are the top, so default to not allowed.
            return AccessResult.DISALLOWED;
        }

        return mParent.checkAccess(aForImport, aInPkg);
    }

    /**
     * Checks whether any of the guards for this node control access to
     * a specified package.
     * @param aForImport the package to check.
     * @param aInPkg the package doing the import.
     * @return an {@link AccessResult}.
     */
    private AccessResult localCheckAccess(final String aForImport, final String aInPkg)
    {
        final Iterator it = mGuards.iterator();
        while (it.hasNext())
        {
            final Guard g = (Guard) it.next();
            // Check if a Guard is only meant to be applied locally.
            if (g.isLocalOnly() && !mFullPackage.equals(aInPkg))
            {
                continue;
            }
            final AccessResult result = g.verifyImport(aForImport, aInPkg);
            if (result != AccessResult.UNKNOWN)
            {
                return result;
            }
        }
        return AccessResult.UNKNOWN;
    }
}

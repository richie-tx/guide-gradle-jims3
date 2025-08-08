package mojo.tools.code.audit.checkstyle.check.imports;

import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FullIdent;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.checks.CheckUtils;

import org.apache.commons.beanutils.ConversionException;

/**
 * Check that controls what packages can be imported and utilized in each 
 * package. It is helpful for ensuring that application layering is not 
 * violated.
 * 
 * All packages must be allowed explicitly.  In other words, the partitioning
 * defaults to disallowed.
 * 
 * @author Jim Fisher
 */
public class PackageControlCheck extends Check
{
    /** The root package controller. */
    private PkgControl mRoot;

    /** The package doing the import. */
    private String mInPkg;

    /**
     * The package controller for the current file. Used for performance
     * optimisation.
     */
    private PkgControl mCurrentLeaf;

    /**
     * 
     */
    public PackageControlCheck()
    {
        super();
    }
    
    /** {@inheritDoc} */
    public int[] getDefaultTokens()
    {
        return new int[]
        { TokenTypes.PACKAGE_DEF, TokenTypes.IMPORT, TokenTypes.VARIABLE_DEF, TokenTypes.METHOD_DEF,
                TokenTypes.PARAMETER_DEF };
    }

    /** {@inheritDoc} */
    public void beginTree(final DetailAST aRootAST)
    {
        mCurrentLeaf = null;
    }

    /** {@inheritDoc} */
    public void visitToken(final DetailAST aAST)
    {
        if (aAST.getType() == TokenTypes.PACKAGE_DEF)
        {
            final DetailAST nameAST = aAST.getLastChild().getPreviousSibling();
            final FullIdent full = FullIdent.createFullIdent(nameAST);
            if (mRoot == null)
            {
                log(nameAST, "partition.control.missing.file");
            }
            else
            {
                mInPkg = full.getText();
                mCurrentLeaf = mRoot.locateFinest(mInPkg);
                if (mCurrentLeaf == null)
                {
                    log(nameAST, "partition.control.unknown.pkg");
                }
            }
        }
        else if (mCurrentLeaf != null)
        {
            final FullIdent imp;
            if (aAST.getType() == TokenTypes.IMPORT)
            { // import declaration
                imp = FullIdent.createFullIdentBelow(aAST);
            }
            else
            { // either method, parameter, or variable 
                imp = this.visitStatementType(aAST);
            }
            if (imp != null)
            {
                final AccessResult access = mCurrentLeaf.checkAccess(imp.getText(), mInPkg);
                if (AccessResult.ALLOWED.equals(access) == false)
                {
                    log(aAST, "partition.control.disallowed", mInPkg, imp.getText());
                }
            }

        }
    }

    /**
     * Checks type of given variable.
     * @param aAST variable to check.
     */
    private FullIdent visitStatementType(DetailAST aAST)
    {
        final DetailAST type = aAST.findFirstToken(TokenTypes.TYPE);
        FullIdent ident = CheckUtils.createFullType(type);

        String returnType = ident.getText();

        boolean noCheck = true;

        int pkgIndex = returnType.lastIndexOf(".");

        if (pkgIndex != -1)
        {
            try
            {
                Class.forName(returnType);

                String packageName = returnType.substring(0, pkgIndex);
                Package pkg = Package.getPackage(packageName);
                if (pkg == null)
                {
                    noCheck = true;
                }
                else
                {
                    noCheck = false;
                }

            }
            catch (ClassNotFoundException e)
            {
                noCheck = true;
            }
            catch (Throwable t)
            {
                noCheck = true;
                System.out.println("exception loading: " + returnType);
                t.printStackTrace();
                RuntimeException re = new RuntimeException();
                re.setStackTrace(t.getStackTrace());
                throw re;
            }
        }

        if (noCheck == true)
        {
            ident = null;
        }

        return ident;
    }

    /**
     * Set the parameter for the file containing the import control
     * configuration. It will cause the file to be loaded.
     * @param aName the name of the file to load.
     * @throws ConversionException on error loading the file.
     */
    public void setFile(final String aName)
    {
        System.out.println("setFile: "+aName);
        // Handle empty param
        if ((aName == null) || (aName.trim().length() == 0))
        {
            return;
        }

        try
        {
            mRoot = PackageControlLoader.load(aName);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new ConversionException("Unable to load " + aName, ex);
        }
    }
}

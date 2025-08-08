/*
 * Created on Jun 21, 2006
 *
 */
package mojo.tools.code.audit.check.coding;

import mojo.km.config.AuditCheckProperties;
import mojo.tools.code.CodeElement;
import mojo.tools.code.CodeUtil;
import mojo.tools.code.ThrowStatement;
import mojo.tools.code.Type;
import mojo.tools.code.audit.AuditVisitor;
import mojo.tools.code.audit.Check;
import mojo.tools.code.audit.CodeElementTypes;

/**
 * 
 * @author Jim Fisher
 *
 */
public class PackageThrowsCheck extends Check
{

    private static final String PACKAGE_NAME = "packageName";

    /**
     * @param aCheckProps
     */
    public PackageThrowsCheck(AuditCheckProperties aCheckProps)
    {
        super(aCheckProps);
    }

    public void auditComponent(AuditVisitor aVisitor, CodeElement aCodeElement)
    {
        ThrowStatement throwStatement = (ThrowStatement) aCodeElement;

        mojo.tools.code.Class parentClass = (mojo.tools.code.Class) CodeUtil.getParentByType(throwStatement,
                mojo.tools.code.Class.class);

        if (this.packageViolation(parentClass) == true)
        {
            String lineNo = String.valueOf(throwStatement.getBeginLineNumber());

            this.log(aVisitor, lineNo, "package.throw");
        }
    }

    private boolean packageViolation(Type aType)
    {
        boolean packageViolation = false;

        String checkPackage = checkProps.getProperty(PACKAGE_NAME);

        if (checkPackage != null)
        {
            String packageName = aType.getPackage().getName() + ".";
            checkPackage += ".";
            if (packageName.startsWith(checkPackage))
            {
                packageViolation = true;
            }
        }
        return packageViolation;
    }

    public String[] getCodeElementTypes()
    {
        return new String[]
        { CodeElementTypes.THROW_STATEMENT };
    }
}

/*
 * Created on Jun 21, 2006
 *
 */
package mojo.tools.code.audit.check.coding;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import mojo.km.config.AuditCheckProperties;
import mojo.tools.code.CodeElement;
import mojo.tools.code.CodeUtil;
import mojo.tools.code.Type;
import mojo.tools.code.audit.AuditVisitor;
import mojo.tools.code.audit.Check;
import mojo.tools.code.audit.CodeElementTypes;

/**
 * 
 * @author Jim Fisher
 *
 */
public class NoBranchLogicCheck extends Check
{
    private String[] parentTypes;

    private static final String PACKAGE_NAME = "packageName";

    private static final String PARENT_TYPE = "parentType";

    private static final String PARENT_DELIM = ",";

    /**
     * @param aCheckProps
     */
    public NoBranchLogicCheck(AuditCheckProperties aCheckProps)
    {
        super(aCheckProps);
    }

    public void init()
    {
        String parentTypesProp = super.checkProps.getProperty(PARENT_TYPE);
        if (parentTypesProp != null)
        {
            this.parentTypes = parentTypesProp.split(PARENT_DELIM);
            for (int i = 0; i < this.parentTypes.length; i++)
            {
                this.parentTypes[i] = this.parentTypes[i].trim();
            }
            Arrays.sort(parentTypes);
        }
    }

    public void auditComponent(AuditVisitor aVisitor, CodeElement aCodeElement)
    {
        this.init();

        mojo.tools.code.Class parentClass = (mojo.tools.code.Class) CodeUtil.getParentByType(aCodeElement,
                mojo.tools.code.Class.class);

        if (this.parentViolation(parentClass) == true)
        {
            String lineNo = String.valueOf(aCodeElement.getBeginLineNumber());

            this.log(aVisitor, lineNo, "package.layer.branchlogic");
        }
    }

    private boolean parentViolation(Type aType)
    {
        boolean parentViolation = false;

        List parents = CodeUtil.getParentTypes(aType);

        Iterator i = parents.iterator();
        int index = -1;
        while (i.hasNext() && index < 0)
        {
            String parentName = (String) i.next();
            index = Arrays.binarySearch(this.parentTypes, parentName);
        }

        if (index >= 0)
        {
            parentViolation = true;
        }

        return parentViolation;
    }

    public String[] getCodeElementTypes()
    {
        return new String[]
        { CodeElementTypes.IF_STATEMENT };
    }
}

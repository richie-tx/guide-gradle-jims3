/*
 * Created on Jan 10, 2007
 *
 */
package mojo.tools.code.audit.check.coding;

import java.util.Arrays;

import mojo.km.config.AuditCheckProperties;
import mojo.tools.code.CodeElement;
import mojo.tools.code.CodeUtil;
import mojo.tools.code.KeyWord;
import mojo.tools.code.audit.AuditVisitor;
import mojo.tools.code.audit.Check;
import mojo.tools.code.audit.CodeElementTypes;

/**
 * @author jfisher
 *  
 */
public class PublicStaticMethodCheck extends Check
{
    private static String[] parentTypes;

    private static final String PARENT_TYPE = "parentType";

    private static final String PARENT_DELIM = ",";

    /**
     * @param aCheckProps
     */
    public PublicStaticMethodCheck(AuditCheckProperties aCheckProps)
    {
        super(aCheckProps);
        // TODO Auto-generated constructor stub
    }

    public void init()
    {
        if (parentTypes == null)
        {
            String parentTypesProp = super.checkProps.getProperty(PARENT_TYPE);
            if (parentTypesProp != null)
            {
                parentTypes = parentTypesProp.split(PARENT_DELIM);
                for (int i = 0; i < parentTypes.length; i++)
                {
                    parentTypes[i] = parentTypes[i].trim();
                }
                Arrays.sort(parentTypes);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.tools.code.audit.Check#auditComponent(mojo.tools.code.audit.AuditVisitor,
     *      mojo.tools.code.CodeElement)
     */
    public void auditComponent(AuditVisitor aVisitor, CodeElement aCodeElement)
    {
        this.init();
        
        mojo.tools.code.Method method = (mojo.tools.code.Method) aCodeElement;

        if (method.isStatic() && KeyWord.PUBLIC.equals(method.getScope()))
        {
            mojo.tools.code.Type type = (mojo.tools.code.Type) method.getParent();
            if (this.parentViolation(type) == true)
            {
                int lineNo = method.getBeginLineNumber();
                Object[] parms = new Object[2];
                parms[0] = type.getQualifiedName();
                parms[1] = method.getName();
                this.log(aVisitor, String.valueOf(lineNo), "method.public.static", parms);
            }

        }
    }

    private boolean parentViolation(mojo.tools.code.Type aType)
    {
        boolean parentViolation = false;

        int len = parentTypes.length;
        int index = -1;
        for (int i = 0; i < len && parentViolation == false; i++)
        {
            if (CodeUtil.isInstance(aType, parentTypes[i]))
            {
                parentViolation = true;
            }
        }

        return parentViolation;
    }

    public String[] getCodeElementTypes()
    {
        return new String[] { CodeElementTypes.METHOD };
    }

}

/*
 * Created on Jan 10, 2007
 *
 */
package mojo.tools.code.audit.check.coding;

import mojo.km.config.AuditCheckProperties;
import mojo.km.utilities.TextUtil;
import mojo.tools.code.CodeElement;
import mojo.tools.code.CodeUtil;
import mojo.tools.code.Method;
import mojo.tools.code.audit.AuditVisitor;
import mojo.tools.code.audit.Check;
import mojo.tools.code.audit.CodeElementTypes;

/**
 * @author jfisher
 *  
 */
public class MarkModifiedInAccessorCheck extends Check
{

    private static final String PERSISTENT_OBJECT = "PersistentObject";

    private static final String PERSISTENT_OBJECT_CLASS = mojo.km.persistence.PersistentObject.class.getName();

    /**
     * @param aCheckProps
     */
    public MarkModifiedInAccessorCheck(AuditCheckProperties aCheckProps)
    {
        super(aCheckProps);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.tools.code.audit.Check#auditComponent(mojo.tools.code.audit.AuditVisitor,
     *      mojo.tools.code.CodeElement)
     */
    public void auditComponent(AuditVisitor aVisitor, CodeElement aCodeElement)
    {
        Method method = (Method) aCodeElement;

        mojo.tools.code.Type type = (mojo.tools.code.Type) method.getParent();

        if (CodeUtil.isInstance(type, PERSISTENT_OBJECT) || CodeUtil.isInstance(type, PERSISTENT_OBJECT_CLASS))
        {
            if (isAccessor(method))
            {
                int lineNo = this.hasMarkModified(method);
                if (lineNo != -1)
                {
                    Object[] parms = new Object[2];
                    parms[0] = type.getQualifiedName();
                    parms[1] = method.getName();
                    this.log(aVisitor, String.valueOf(lineNo), "acessor.no.markmodified", parms);
                }
            }
        }
    }

    /**
     * @param method
     * @return
     */
    private boolean isAccessor(Method method)
    {
        String methodName = method.getName();
        return method.isConstructor() == false && (methodName.startsWith("get") || methodName.startsWith("is"));
    }

    /**
     * @param method
     * @return
     */
    private int hasMarkModified(Method aMethod)
    {
        String methodString = aMethod.getOriginalMethodString();
               
        int begin = aMethod.getBeginLineNumber();
        
        int line = TextUtil.lineOf(methodString, 0, "markModified()");

        if (line != -1)
        {
            line += begin;
        }

        return line;
    }

    public String[] getCodeElementTypes()
    {
        return new String[] { CodeElementTypes.METHOD };
    }

}

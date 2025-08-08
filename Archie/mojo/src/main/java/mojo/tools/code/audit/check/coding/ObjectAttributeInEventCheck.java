/*
 * Created on Jan 10, 2007
 *
 */
package mojo.tools.code.audit.check.coding;

import java.util.List;

import mojo.km.config.AuditCheckProperties;
import mojo.km.utilities.TextUtil;
import mojo.tools.code.Attribute;
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
public class ObjectAttributeInEventCheck extends Check
{
    private static final String IEVENT_CLASS = mojo.km.messaging.IEvent.class.getName();
    private static final String OBJECT_TYPE = "Object";
    private static final String OBJECT_CLASS_TYPE = "java.lang.Object";

    /**
     * @param aCheckProps
     */
    public ObjectAttributeInEventCheck(AuditCheckProperties aCheckProps)
    {
        super(aCheckProps);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.tools.code.audit.Check#auditComponent(mojo.tools.code.audit.AuditVisitor, mojo.tools.code.CodeElement)
     */
    public void auditComponent(AuditVisitor aVisitor, CodeElement aCodeElement)
    {
        mojo.tools.code.Type type = (mojo.tools.code.Type) aCodeElement;

        if (CodeUtil.isInstance(type, IEVENT_CLASS))
        {
            List attributes = null; //clazz.getAttributeList();
            int len = attributes.size();
            for (int i = 0; i < len; i++)
            {
                Attribute a = (Attribute) attributes.get(i);
                String attrType = a.getType();
                if(attrType.equals(OBJECT_TYPE) || attrType.equals(OBJECT_CLASS_TYPE))
                {
                    int lineNo = a.getBeginLineNumber();
                    if (lineNo != -1)
                    {
                        this.log(aVisitor, String.valueOf(lineNo), "event.no.object.attribute");
                    }
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
        String methodString = null; //aMethod.getOriginalMethodString();

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
        return new String[] { CodeElementTypes.CLASS };
    }

}

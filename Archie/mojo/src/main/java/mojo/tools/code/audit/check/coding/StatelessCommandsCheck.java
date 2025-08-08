/*
 * Created on Jun 21, 2006
 *
 */
package mojo.tools.code.audit.check.coding;

import java.util.Iterator;

import mojo.km.config.AuditCheckProperties;
import mojo.tools.code.Attribute;
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
public class StatelessCommandsCheck extends Check
{
    private static final String COMMAND_INTERFACE_NAME = "mojo.km.context.ICommand";

    /**
     * @param aCheckProps
     */
    public StatelessCommandsCheck(AuditCheckProperties aCheckProps)
    {
        super(aCheckProps);
    }

    public void auditComponent(AuditVisitor aVisitor, CodeElement aCodeElement)
    {
        Type type = (Type) aCodeElement;

        boolean isInstance = CodeUtil.isInstance(type, COMMAND_INTERFACE_NAME);

        if (isInstance == true)
        {
            int lineNo = this.hasStatefulMemberVariables(type);
            if (lineNo != -1)
            {
                this.log(aVisitor, String.valueOf(lineNo), "pd.services.stateless");
            }
        }
    }

    /**
     * @param class1
     */
    private int hasStatefulMemberVariables(Type aType)
    {
        int lineNo = -1;
        Iterator a = aType.getAttributes();
        while (a.hasNext() && lineNo == -1)
        {
            Attribute attr = (Attribute) a.next();
            if (attr.isStatic() == false && attr.isFinal() == false)
            {
                lineNo = attr.getBeginLineNumber();
            }
        }
        return lineNo;
    }

    public String[] getCodeElementTypes()
    {
        return new String[] { CodeElementTypes.CLASS };
    }
}

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
public class StatelessClassCheck extends Check
{
    private String[] parentTypes;

    /**
     * @param aCheckProps
     */
    public StatelessClassCheck(AuditCheckProperties aCheckProps)
    {
        super(aCheckProps);
        String parentType = aCheckProps.getProperty("parentType");

        if (parentType != null)
        {
            this.parentTypes = parentType.split(",");
        }
    }

    public void auditComponent(AuditVisitor aVisitor, CodeElement aCodeElement)
    {
        Type type = (Type) aCodeElement;

        if (parentTypes != null)
        {
            for (int i = 0; i < parentTypes.length; i++)
            {
                boolean isInstance = CodeUtil.isInstance(type, parentTypes[i]);

                if (isInstance == true)
                {
                    int lineNo = this.hasStatefulMemberVariables(type);
                    if (lineNo != -1)
                    {
                        this.log(aVisitor, String.valueOf(lineNo), "pd.class.stateless");
                    }
                }
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

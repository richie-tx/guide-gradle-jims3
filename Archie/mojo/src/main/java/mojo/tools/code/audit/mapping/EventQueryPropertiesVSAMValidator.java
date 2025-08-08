/*
 * Created on Jun 13, 2006
 *
 */
package mojo.tools.code.audit.mapping;

import mojo.km.config.EventQueryProperties;
import mojo.tools.code.audit.AuditResult;

/**
 * @author Jim Fisher
 *  
 */
public class EventQueryPropertiesVSAMValidator extends AbstractEventQueryPropertiesValidator
{   
    public EventQueryPropertiesVSAMValidator(EventQueryProperties aProps)
    {
        super(aProps);
    }

    public void accept(IMappingValidatorVisitor aVisitor)
    {
        this.visitor = (PersistenceMappingValidator) aVisitor;

        this.validate();

        // validate parm and field mappings
        aVisitor.visit(this);
    }   

    /**
     * @return Returns the props.
     */
    public EventQueryProperties getProps()
    {
        return props;
    }

    public AuditResult getResult()
    {
        return this.result;
    }

    public void validate()
    {        
    }    
}

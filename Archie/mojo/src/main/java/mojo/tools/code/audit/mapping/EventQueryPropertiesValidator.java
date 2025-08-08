/*
 * Created on Jun 13, 2006
 *
 */
package mojo.tools.code.audit.mapping;

import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;

/**
 * @author Jim Fisher
 *  
 */
public class EventQueryPropertiesValidator extends AbstractEventQueryPropertiesValidator
{
    public EventQueryPropertiesValidator(EventQueryProperties aProps)
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

    public void validate()
    {
        String unitName = visitor.getCompilationUnit().getMainType().getQualifiedName();
        System.out.println("VALIDATING: " + unitName + "::" + props.getParent());
        String name = props.getName();
        String mappingMethodName = props.getMappingMethodName();
        String source = props.getSource();
        String connectionName = props.getConnectionName();

        StringBuffer msgBuffer = new StringBuffer();

        msgBuffer.append(source);

        msgBuffer.append(" (");
        msgBuffer.append(connectionName);
        msgBuffer.append(")");

        this.result.setMessage(msgBuffer.toString());

        // RULE: validate mapping class name

        Class clazz = this.validateMappingClass(unitName);

        EntityMappingProperties eProps = props.getParent();

        boolean noValidate = eProps.isBufferMapping() || eProps.isReportMapping() || props.isFileIOMapping()
                || isCacheMapping();

        if (noValidate == false)
        {
            this.validateConnection(unitName);
        }
    }

}

/*
 * Created on Jun 13, 2006
 *
 */
package mojo.tools.code.audit.mapping;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.naming.MappingConstants;
import mojo.tools.code.audit.AuditError;
import mojo.tools.code.audit.AuditResult;

/**
 * @author Jim Fisher
 *
 */
public class EntityMappingPropertiesValidator implements IMappingValidator
{
    private AuditResult result;

    private EntityMappingProperties props;

    private Set queryCallbackNames;

    private Map queryCallbacks;

    private Map saveCallbacks;

    public EntityMappingPropertiesValidator(EntityMappingProperties aProps)
    {
        this.props = aProps;
        this.result = new AuditResult();

        this.queryCallbackNames = new HashSet();
        this.queryCallbacks = new TreeMap();
        this.saveCallbacks = new TreeMap();
    }

    public void accept(IMappingValidatorVisitor visitor)
    {
        // validate EntityMappingProperties attributes
        this.validate(visitor);

        // visit to validate children
        visitor.visit(this);

        // TODO Can contextKey be blank?

    }

    public void addError(AuditError anError)
    {
        this.result.addError(anError);
    }

    public void addQueryCallback(String name, AbstractEventQueryPropertiesValidator aValidator)
    {
        this.queryCallbacks.put(name, aValidator);
    }

    public void addSaveCallback(String name, SaveCallbackPropertiesValidator aValidator)
    {
        this.saveCallbacks.put(name, aValidator);
    }

    /**
     * @return Returns the props.
     */
    public EntityMappingProperties getProps()
    {
        return props;
    }

    /**
     * @return Returns the queryCallbacks.
     */
    public Map getQueryCallbacks()
    {
        return queryCallbacks;
    }

    /**
     * @return Returns the saveCallbacks.
     */
    public Map getSaveCallbacks()
    {
        return saveCallbacks;
    }

    private void validate(IMappingValidatorVisitor visitor)
    {
        String unitName = visitor.getCompilationUnit().getMainType().getQualifiedName();
        if (props == null)
        {
            AuditError error = new AuditError(MappingConstants.NO_ENTITY_MAPPING, MappingConstants.NO_ENTITY_MAPPING,
                    AuditError.ERROR);
            error.setName(unitName);
            this.result.addError(error);
        }
        else
        {
            Iterator i = this.props.getQueryCallbacks();
            while (i.hasNext())
            {
                EventQueryProperties qProps = (EventQueryProperties) i.next();

                String name = qProps.getName().trim();

                if (queryCallbackNames.contains(name))
                // check for duplicate query callbacks
                {
                    AuditError error = new AuditError(MappingConstants.DUP_QUERY_CALLBACK_NAMES,
                            MappingConstants.DUP_QUERY_CALLBACK_NAMES, AuditError.ERROR);
                    error.setName(unitName);
                    this.result.addError(error);
                }
                else if (name.length() == 0 || name.equals(MappingConstants.NONE))
                // check for empty query callback names
                {
                    AuditError error = new AuditError(MappingConstants.EMPTY_QUERY_CALLBACK_NAME,
                            MappingConstants.EMPTY_QUERY_CALLBACK_NAME, AuditError.ERROR);
                    error.setName(unitName);
                    this.result.addError(error);
                }
                else
                // keep track for dups
                {
                    queryCallbackNames.add(qProps.getName().trim());
                }
            }
        }
    }

    public AuditResult getResult()
    {
        return this.result;
    }

}

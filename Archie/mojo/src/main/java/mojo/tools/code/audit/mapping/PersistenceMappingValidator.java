/*
 * Created on Jun 13, 2006
 *
 */
package mojo.tools.code.audit.mapping;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.SaveCallbackProperties;
import mojo.km.naming.MappingConstants;
import mojo.tools.code.CompilationUnit;
import mojo.tools.code.audit.AuditResult;

/**
 * @author Jim Fisher
 *  
 */
public class PersistenceMappingValidator implements IMappingValidatorVisitor
{
    private CompilationUnit compilationUnit;

    private static Map metaDataCache = new HashMap();
    private static Map metaDataTypesCache = new HashMap();

    private AuditResult result;

    private EntityMappingPropertiesValidator validator;

    /**
     *  
     */
    public PersistenceMappingValidator(CompilationUnit aCompUnit)
    {
        this.compilationUnit = aCompUnit;
        this.result = new AuditResult();
    }

    public void addMetaData(String source, Map metaData, Map metaDataTypes)
    {
        metaDataCache.put(source, metaData);
        metaDataTypesCache.put(source, metaDataTypes);
    }

    public Map getMetaData(String source)
    {
        return (Map) metaDataCache.get(source);
    }
    
    public Map getMetaDataTypes(String source)
    {
        return (Map) metaDataTypesCache.get(source);
    }

    /**
     * @return Returns the validator.
     */
    public EntityMappingPropertiesValidator getValidator()
    {
        return validator;
    }

public void visit(EntityMappingPropertiesValidator aValidator)
    {
        this.validator = aValidator;

        // Validate the child query callbacks
        EntityMappingProperties eProps = aValidator.getProps();

        if (eProps != null)
        {
            Iterator i = eProps.getQueryCallbacks();
            while (i.hasNext())
            {
                EventQueryProperties qProps = (EventQueryProperties) i.next();

                AbstractEventQueryPropertiesValidator validator = null;

                if (qProps.getConnectionName().startsWith(MappingConstants.JDBC))
                {
                    validator = new EventQueryPropertiesJDBCValidator(qProps);
                }
                else if (qProps.getConnectionName().equals(MappingConstants.M204))
                {
                    validator = new EventQueryPropertiesM204Validator(qProps);
                }
                else if (qProps.getConnectionName().equals(MappingConstants.VSAM))
                {
                    validator = new EventQueryPropertiesVSAMValidator(qProps);
                }
                else
                {
                    validator = new EventQueryPropertiesValidator(qProps);
                }

                // build the tree of child query callbacks
                aValidator.addQueryCallback(qProps.getName(), validator);

                // validate the query callback
                validator.accept(this);

                List queryErrors = validator.getResult().getAllErrors();

                aValidator.getResult().addChildErrors(queryErrors);
            }

            // Validate the child save callbacks
            i = eProps.getSaveCallbacks();
            while (i.hasNext())
            {
                SaveCallbackProperties sProps = (SaveCallbackProperties) i.next();
                SaveCallbackPropertiesValidator validator = new SaveCallbackPropertiesValidator(sProps);

                // build the tree of child save callbacks
                aValidator.addSaveCallback(sProps.getName(), validator);

                // validate the save callback
                validator.accept(this);

                List queryErrors = validator.getResult().getAllErrors();

                aValidator.getResult().addChildErrors(queryErrors);
            }
        }
    }    public void visit(AbstractEventQueryPropertiesValidator aValidator)
    {
    }

    public void visit(SaveCallbackPropertiesValidator aValidator)
    {
        // nothing to do
    }

    public IMappingValidator getMappingValidator()
    {
        return this.validator;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.tools.code.audit.mapping.IMappingValidatorVisitor#getCompilationUnit()
     */
    public CompilationUnit getCompilationUnit()
    {
        // TODO Auto-generated method stub
        return this.compilationUnit;
    }
}

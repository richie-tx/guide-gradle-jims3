/*
 * Created on Jun 13, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mojo.tools.code.audit.mapping;

import mojo.tools.code.CompilationUnit;

/**
 * @author Jim Fisher
 *
 */
public interface IMappingValidatorVisitor
{   
    public CompilationUnit getCompilationUnit();
    void visit(EntityMappingPropertiesValidator aValidator);
    void visit(AbstractEventQueryPropertiesValidator aValidator);    
    void visit(SaveCallbackPropertiesValidator aValidator);
    IMappingValidator getMappingValidator();
    
}

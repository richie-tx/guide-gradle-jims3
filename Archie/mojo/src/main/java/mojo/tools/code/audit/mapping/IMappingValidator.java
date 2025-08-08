/*
 * Created on Jun 13, 2006
 *
 */
package mojo.tools.code.audit.mapping;

import mojo.tools.code.audit.AuditResult;

/**
 * @author Jim Fisher
 *
 */
public interface IMappingValidator 
{
    /**
	 * Enable a visitor implementation to visit an element type.
	 * 
	 * @param visitor
	 */
	void accept(IMappingValidatorVisitor visitor);
	
	AuditResult getResult();	
}

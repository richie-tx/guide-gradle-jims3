/*
 * Created on Jun 13, 2006
 *
 */
package mojo.tools.code.audit.mapping;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mojo.tools.code.CompilationUnit;
import mojo.tools.code.audit.AuditError;
import mojo.tools.code.audit.AuditResult;

/**
 * @author Jim Fisher
 */
public class MappingAuditXMLOutput implements IMappingValidatorVisitor
{
	private CompilationUnit compUnit;

	private PrintWriter writer;

	private IMappingValidator validator;

	/**
	 * 
	 */
	public MappingAuditXMLOutput(CompilationUnit aUnit, PrintWriter writer)
	{
		this.compUnit = aUnit;
		this.writer = writer;
	}

	public CompilationUnit getCompilationUnit()
	{
		return this.compUnit;
	}

	private String concatAttr(String name, int intValue)
	{
		return this.concatAttr(name, String.valueOf(intValue));
	}

	private String concatAttr(String name, String value)
	{
		StringBuilder buffer = new StringBuilder();
		buffer.append(" ");
		buffer.append(name);
		buffer.append("=\"");
		buffer.append(value);
		buffer.append("\"");
		return buffer.toString();
	}

	private String concatError(AuditError anError)
	{
		StringBuilder buffer = new StringBuilder();
		buffer.append("<error");
		buffer.append(this.concatAttr("severity", anError.getSeverity()));
		buffer.append(this.concatAttr("message", anError.getMessage()));
		buffer.append(this.concatAttr("source", anError.getSource()));
		buffer.append(" />");
		return buffer.toString();
	}

	public void visit(EntityMappingPropertiesValidator aValidator)
	{
		this.validator = aValidator;
		AuditResult result = aValidator.getResult();

		if (this.hasErrors(aValidator))
		{
			StringBuilder buffer = new StringBuilder();
			buffer.append("\t");
			buffer.append("<component");

			String contextKey = aValidator.getProps().getContextKey();
			contextKey = contextKey.replaceAll(",", "_");
			contextKey = contextKey.replaceAll(";", "_");

			String compName = this.compUnit.getMainType().getQualifiedName();

			if (compName.equals(contextKey) == false)
			{
				compName += "::" + contextKey;
			}

			// TODO include child errors?

			buffer.append(this.concatAttr("name", compName));
			buffer.append(this.concatAttr("errorCount", result.getErrorCount()));
			buffer.append(this.concatAttr("warningCount", result.getWarningCount()));
			buffer.append(this.concatAttr("infoCount", result.getInfoCount()));

			buffer.append(">");

			Iterator i = result.getAllErrors().iterator();
			while (i.hasNext())
			{
				AuditError error = (AuditError) i.next();
				String errorString = this.concatError(error);
				buffer.append("\t\t");
				buffer.append(errorString);
				buffer.append("\n");
			}

			writer.println(buffer.toString());

			// iterate query callbacks
			Map queryCallbackValidators = aValidator.getQueryCallbacks();
			i = queryCallbackValidators.values().iterator();
			while (i.hasNext())
			{
				AbstractEventQueryPropertiesValidator qValidator = (AbstractEventQueryPropertiesValidator) i.next();
				this.visit(qValidator);
			}

			// iterate save callbacks
			Map saveCallbackValidators = aValidator.getSaveCallbacks();
			i = saveCallbackValidators.values().iterator();
			while (i.hasNext())
			{
				SaveCallbackPropertiesValidator sValidator = (SaveCallbackPropertiesValidator) i.next();
				this.visit(sValidator);
			}
			buffer = new StringBuilder();
			buffer.append("\t");
			buffer.append("</component>");
			writer.println(buffer.toString());
		}
	}

	private boolean hasErrors(EntityMappingPropertiesValidator aValidator)
	{
		boolean hasErrors = false;
		Map queryCallbackValidators = aValidator.getQueryCallbacks();
		Iterator i = queryCallbackValidators.values().iterator();
		while (i.hasNext() && hasErrors == false)
		{
			AbstractEventQueryPropertiesValidator qValidator = (AbstractEventQueryPropertiesValidator) i.next();
			hasErrors = qValidator.getResult().getAllResultsCount() > 0;
		}
		
		return hasErrors;
	}

	public void visit(AbstractEventQueryPropertiesValidator aValidator)
	{
		AuditResult result = aValidator.getResult();

		List errors = result.getAllErrors();
		int len = errors.size();
		if (len > 0)
		{
			StringBuilder buffer = new StringBuilder();
			buffer.append("\t\t");
			buffer.append("<component");
			String compName = aValidator.getProps().getName();

			buffer.append(this.concatAttr("name", compName));
			buffer.append(this.concatAttr("message", result.getMessage()));
			buffer.append(this.concatAttr("errorCount", result.getErrorCount()));
			buffer.append(this.concatAttr("warningCount", result.getWarningCount()));
			buffer.append(this.concatAttr("infoCount", result.getInfoCount()));
			buffer.append(" >\n");
			Iterator i = result.getAllErrors().iterator();
			while (i.hasNext())
			{
				AuditError error = (AuditError) i.next();
				String errorString = this.concatError(error);
				buffer.append("\t\t\t");
				buffer.append(errorString);
				buffer.append("\n");
			}

			buffer.append("\t\t");
			buffer.append("</component>");
			writer.println(buffer.toString());
		}
	}

	public void visit(SaveCallbackPropertiesValidator aValidator)
	{
		AuditResult result = aValidator.getResult();
		StringBuilder buffer = new StringBuilder();
		buffer.append("\t\t");
		buffer.append("<component");
		String compName = "SaveCallback";

		// TODO include child errors?

		buffer.append(this.concatAttr("name", compName));
		buffer.append(this.concatAttr("message", result.getMessage()));
		buffer.append(this.concatAttr("errorCount", result.getErrorCount()));
		buffer.append(this.concatAttr("warningCount", result.getWarningCount()));
		buffer.append(this.concatAttr("infoCount", result.getInfoCount()));
		buffer.append(" >\n");
		Iterator i = result.getAllErrors().iterator();
		while (i.hasNext())
		{
			AuditError error = (AuditError) i.next();
			String errorString = this.concatError(error);
			buffer.append("\t\t\t");
			buffer.append(errorString);
			buffer.append("\n");
		}

		buffer.append("\t\t");
		buffer.append("</component>");
		writer.println(buffer.toString());
	}

	public IMappingValidator getMappingValidator()
	{
		return this.validator;
	}

}

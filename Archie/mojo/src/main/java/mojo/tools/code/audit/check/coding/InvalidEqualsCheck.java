package mojo.tools.code.audit.check.coding;

import mojo.km.config.AuditCheckProperties;
import mojo.tools.code.CodeElement;
import mojo.tools.code.audit.AuditVisitor;
import mojo.tools.code.audit.Check;
import mojo.tools.code.audit.CodeElementTypes;

public class InvalidEqualsCheck extends Check {
	String[] invalidEquals = { ".equals(null)", "==\"", "== \"", "!=\"",
			"!= \"" };

	public InvalidEqualsCheck(AuditCheckProperties aCheckProps) {
		super(aCheckProps);
	}

	@Override
	public void auditComponent(AuditVisitor aVisitor, CodeElement aCodeElement) {
		mojo.tools.code.Expression method = (mojo.tools.code.Expression) aCodeElement;
		String methodBody = method.getBody();
		for (int i = 0; i < invalidEquals.length; i++) {
			int index = methodBody.indexOf(invalidEquals[i]);
			if (index != -1) {
				int lineNo = method.getBeginLineNumber();

				Object[] parms = new Object[1];
				parms[0] = invalidEquals[i].replace("\"", "&quot;"); 
				this.log(aVisitor, String.valueOf(lineNo),
						"expression.invalid.equals", parms);
			}

		}

	}

	@Override
	public String[] getCodeElementTypes() {
		return new String[] { CodeElementTypes.EXPRESSION_STATEMENT };
	}

}

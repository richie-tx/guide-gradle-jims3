package mojo.tools.codegen.patterns;

import mojo.tools.code.Method;
import mojo.tools.code.Parameter;

/**
 * @author mshafi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * @modelguid {37ADDE23-556D-4082-95F6-456FC96858A2}
 */
public class CommandPattern implements IPattern {
	/** @modelguid {FBBF41C0-5CCC-4085-A942-4F7B461D1511} */
    private mojo.tools.code.Class aClass = null;
    
	/** @modelguid {FDE4DA73-AA51-43CF-B0B7-01FCA46B44A7} */
    public CommandPattern(mojo.tools.code.Class aClass) {
        this.aClass = aClass;
    }

    /**
     * @modelguid {E86F8949-888F-4432-8170-E7E8E94DE50F}
     */
    public void apply() {
        aClass.getCompilationUnit().addImport("mojo.km.messaging.IEvent");
        aClass.setScope("public");
        aClass.addImplement("mojo.km.context.ICommand");
        aClass.addImplement("mojo.km.transaction.Transactional");

		addExecuteMethod(aClass);
		addOnRegisterMethod(aClass);
		addOnUnregisterMethod(aClass);
		addUpdateMethod(aClass);
    }
    
	/** @modelguid {7E9DB155-8C5C-42DE-8261-A2EE4E1D6827} */
    private void addExecuteMethod(mojo.tools.code.Class aClass) {
        Method lMethod = aClass.getMethod("execute", new String[] {"IEvent"});
        if (lMethod == null) {
			lMethod = new Method("execute");
			lMethod.addParameter(new Parameter("IEvent", "event"));
			lMethod.setComment("Provides behavior for application requirements. It is executed when event is posted from requesting context.");
			lMethod.setProperty("param", "event - houses data for command operation.");
			lMethod.setProperty("exception", "Exception - thrown if error in application behavior");
	        aClass.addMethod(lMethod);
        }
		lMethod.setScope("public");
		lMethod.setReturnType("void");
		lMethod.addThrows("Exception");
    }
    
	/** @modelguid {07958CF6-9039-4C5C-9DFA-0E3E129CD75F} */
    private void addOnRegisterMethod(mojo.tools.code.Class aClass) {
		Method lMethod = aClass.getMethod("onRegister", new String[] {"IEvent"});
		if (lMethod == null) {
			lMethod = new Method("onRegister");
			lMethod.addParameter(new Parameter("IEvent", "event"));
			lMethod.setComment("Upon command registration with context this method will get executed.");
			lMethod.setProperty("param", "event - sample event data");
			aClass.addMethod(lMethod);
		}
		lMethod.setScope("public");
		lMethod.setReturnType("void");
    }

	/** @modelguid {D0FA7832-173E-4CEA-A1AE-649AB8565993} */
	private void addOnUnregisterMethod(mojo.tools.code.Class aClass) {
		Method lMethod = aClass.getMethod("onUnregister", new String[] {"IEvent"});
		if (lMethod == null) {
			lMethod = new Method("onUnregister");
			lMethod.addParameter(new Parameter("IEvent", "event"));
			lMethod.setComment("Upon command unregistration from context this method will get executed.");
			lMethod.setProperty("param", "event - sample event data");
			aClass.addMethod(lMethod);
		}
		lMethod.setScope("public");
		lMethod.setReturnType("void");
	}

	/** @modelguid {F0B803BE-0212-4155-8423-A32EE332ADEE} */
	private void addUpdateMethod(mojo.tools.code.Class aClass) {
		Method lMethod = aClass.getMethod("update", new String[] {"Object"});
		if (lMethod == null) {
			lMethod = new Method("update");
			lMethod.addParameter(new Parameter("Object", "object"));
			lMethod.setComment("If command requires data before execute is called context will place the in command.");
			lMethod.setProperty("param", "object - housing input data");
			aClass.addMethod(lMethod);
		}
		lMethod.setScope("public");
		lMethod.setReturnType("void");
	}

}

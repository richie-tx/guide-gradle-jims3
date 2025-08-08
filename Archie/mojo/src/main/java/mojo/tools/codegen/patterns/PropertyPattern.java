package mojo.tools.codegen.patterns;

import mojo.tools.code.Method;
import mojo.tools.code.Type;
import mojo.tools.code.Parameter;

/**
 * @author mshafi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * @modelguid {BDBB5EDD-F844-4DBF-91B4-CE6457C1353C}
 */
public class PropertyPattern implements IPattern {
	/** @modelguid {E0EF5306-B68F-4EA0-9D7D-D5AA9B4DD031} */
    private mojo.tools.code.Attribute anAttr = null;
    
	/** @modelguid {CB3165CC-79B4-40C5-B850-0264A750F38F} */
    public PropertyPattern(mojo.tools.code.Attribute anAttr) {
        this.anAttr = anAttr;
    }
    
    /**
     * @see IPattern#apply()
     * @modelguid {551E3228-8131-4B31-A2FA-EEA17773D990}
     */
    public void apply() {
        anAttr.setScope("private");
        String name = anAttr.getName();
        String type = anAttr.getType();
        Type parentType = anAttr.getParentType();
        
        if (parentType != null && parentType instanceof mojo.tools.code.Class) {
            mojo.tools.code.Class lClass = (mojo.tools.code.Class)parentType;

			String lGetterName = "get"+name.substring(0,1).toUpperCase()+name.substring(1);
			Method lGetter = lClass.getMethod(lGetterName, new String[] {});
			if (lGetter == null) {
	            lGetter = new Method(lGetterName);
	            lGetter.setBody("return "+name+";");
	            lClass.addMethod(lGetter);
			}
            lGetter.setScope("public");
            lGetter.setReturnType(type);

			String lSetterName = "set"+name.substring(0,1).toUpperCase()+name.substring(1);
			Method lSetter = lClass.getMethod(lSetterName, new String[] {type});
			if (lSetter == null) {
	            lSetter = new Method(lSetterName);
	            lSetter.addParameter(new Parameter(type, name));
	            lSetter.setBody("this."+name+" = "+name+";");
	            lClass.addMethod(lSetter);
			}
            lSetter.setScope("public");
            lSetter.setReturnType("void");
        } else {
            System.err.println("Attribute '"+name+"' must be associated to a Class before applying the Property pattern.");
        }
    }

}

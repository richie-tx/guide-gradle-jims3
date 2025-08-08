package mojo.tools.codegen.patterns;


/**
 * @author mshafi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * @modelguid {D7FCAFC4-BF95-46BA-BEB0-3CA936C32B5E}
 */
public class EventPattern implements IPattern {
	/** @modelguid {BA93CECF-B3B2-49A2-A7BE-3A71E92DD825} */
    private mojo.tools.code.Class aClass;
	/** @modelguid {1874D73E-BA49-4CC1-967A-33503761956A} */
    private String stereotype;
    
	/** @modelguid {DBE349B2-3414-4CD8-86CD-B34F7C509A9F} */
    public EventPattern(mojo.tools.code.Class aClass) {
        this(aClass, null);
    }

	/** @modelguid {CE4327C4-FA29-4C8C-B75D-6EBDD21A196E} */
    public EventPattern(mojo.tools.code.Class aClass, String aStereotype) {
        this.aClass = aClass;
        stereotype = aStereotype;
    }

    /**
     * @see IPattern#apply()
     * @modelguid {08C46A0A-3200-443F-BA1A-3ED424D57355}
     */
    public void apply() {
        aClass.setScope("public");
        if (stereotype != null && stereotype.equalsIgnoreCase("boundary")) {
            aClass.setExtendsClass("mojo.km.messaging.ResponseEvent");
        } else {
            aClass.setExtendsClass("mojo.km.messaging.RequestEvent");
        }
    }

}

package messaging.appshell;

import java.util.Collection;
import java.util.Iterator;
import mojo.km.messaging.RequestEvent;

/**
 * Security Role event
 *
 * @author James McNabb
 * @modelguid {ED28AA68-7C48-42F7-A303-5AAF008784D5}
 */
public class RoleEvent extends RequestEvent {
	/** @modelguid {687FCEBD-0ED0-4D51-95E8-8307FD2A1B2E} */
    private String name;
	/** @modelguid {17192CD2-DAA5-4327-84C4-A5D487EA435C} */
    private String description;
    /**
     * @clientCardinality 1
     * @supplierCardinality 0..1
     * @label principals 
     * @modelguid {9F23154E-22D3-449F-AA74-4CF14A2F3120}
     */
    private DisplayFeatureListEvent principals;

	/** @modelguid {2AC06F3D-479E-49DB-B950-5437D3D43CFC} */
    public RoleEvent() { setServer(mojo.naming.ServerNames.LOGINCONTROLLER); }

	/** @modelguid {1A2B9383-C57E-43A5-92BF-DA004DAA6B2A} */
    public DisplayFeatureListEvent getPrincipals(){ return principals; }

	/** @modelguid {E523206A-43FE-4B0C-9DBB-FBED8EFD3BD4} */
    public void setPrincipals(DisplayFeatureListEvent principals){ this.principals = principals; }

	/** @modelguid {2B0E77DF-8484-4C8F-BC69-EECDE2B9CBCD} */
    public String getName(){ return name; }

	/** @modelguid {054DF5E3-453A-4101-9AE0-E55CDFB6309A} */
    public void setName(String name){ this.name = name; }

	/** @modelguid {EA8805AD-D786-43FD-8648-63481095790D} */
    public String getDescription(){ return description; }

	/** @modelguid {917C98E8-7C17-4BF2-B62E-8329CE90C64E} */
    public void setDescription(String description){ this.description = description; }

}

/*
 * Class UserContext.java
 * Created on May 24, 2002, 12:46:57 PM
 */

package mojo.km.security.role.ldap;

/** @author Chad Oliver 
 * @modelguid {F3A36AA7-6EDF-461D-8264-8AFE1E90DB77}
 */
public class UserContext implements IUserContext {
	/** @modelguid {28A38E07-BBC3-448A-8C64-2E8211D3DA63} */
    private String dn = null;
	/** @modelguid {A594163B-CF3F-41B0-BF83-F41678352FAE} */
    private String applicationName = null;
	/** @modelguid {DF76996E-E6CB-4900-8C83-FE436FC09D1B} */
    private String context = null;
	/** @modelguid {C69F05AD-8017-4D6B-BB54-E78B17B55625} */
    private String cn = null;

    /** @link dependency */

    /*# Mediator lnkMediator; 
     * @modelguid {0085F98D-66EF-4784-AE9F-13294D337E26}
     */

    public UserContext(Mediator mediator, String distinguishedName) {
        init(mediator, distinguishedName);
    }

	/** @modelguid {C33F0889-62C6-4BB8-96B9-8255BFE21DB6} */
    public UserContext(String distinguishedName) {
        init(new Mediator(), distinguishedName);
    }

	/** @modelguid {D526ACED-F355-4754-B3A7-F5D4860E2601} */
    private void init(Mediator mediator, String distinguishedName) {
        try {
            this.dn = mediator.getDistinguishedName(distinguishedName);
            this.applicationName = mediator.getApplicationName(this.dn);
            this.cn = mediator.getCn(this.dn);
            this.context = mediator.getContext(this.dn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	/** @modelguid {7A8323B8-7C41-47CC-AB84-272F033088E4} */
    public String getDn() { return dn; }

	/** @modelguid {6AECCE7C-F16E-4BB1-BA36-7789FB790291} */
    public String getApplicationName() { return applicationName; }

	/** @modelguid {EFA2134A-9702-4A1E-9E0B-AAF07BC9A0FB} */
    public String getCn() { return cn; }

	/** @modelguid {54C79B05-C322-4C0E-9DE0-EC382B04262E} */
    public String getContext() { return context; }
}

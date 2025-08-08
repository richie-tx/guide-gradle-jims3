package messaging.appshell;

import mojo.km.messaging.RequestEvent;

/**
 * A Feature's parameters.
 *
 * @author Egan Royal
 * @modelguid {748A5933-82C6-4D11-908E-0AF13740D53A}
 */
public class ParameterEvent extends RequestEvent {
	/** @modelguid {C0A58E2A-937D-4304-BEDA-EC66F7BCF61B} */
    private String name;
	/** @modelguid {6D899EBB-365A-4ECE-9B1C-41B11FAD4975} */
    private String value;

	/** @modelguid {A1109D7B-E2E9-479F-8018-B62E6DDB3DD9} */
     public ParameterEvent() { setServer(mojo.naming.ServerNames.LOGINCONTROLLER); }

	/** @modelguid {8BFD1E37-DD36-4478-B0D2-0A73EFDA781E} */
    public String getName(){ return name; }

	/** @modelguid {397BE571-6735-44C3-A91C-C30714CCB636} */
    public void setName(String name){ this.name = name; }

	/** @modelguid {13856EF1-BCE7-4CD8-80D1-D1B72190F513} */
    public String getValue(){ return value; }

	/** @modelguid {7FDCDAF7-E4F5-476C-B334-5E5C4684844D} */
    public void setValue(String value){ this.value = value; }
}

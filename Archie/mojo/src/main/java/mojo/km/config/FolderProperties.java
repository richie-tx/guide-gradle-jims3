package mojo.km.config;

/** @modelguid {D94EF794-83C0-4D0D-9917-573D384D3114} */
public class FolderProperties extends GenericProperties {
	/** @modelguid {3C1C07AB-0846-4BEC-B3EE-FEF67E964AA4} */
	public static FolderProperties getInstance() {
		return MojoProperties.getInstance().getFolderProperties();
	}
}

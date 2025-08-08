package mojo.km.config;

/** @modelguid {E8619269-FD64-4190-9C1D-809092F8E7EB} */
public class VersionableProperties extends GenericProperties {
	/** @modelguid {DB5C8334-833D-4E3F-BE30-DA017EAB5722} */
	public static VersionableProperties getInstance() {
		return MojoProperties.getInstance().getVersionableProperties();
	}
}

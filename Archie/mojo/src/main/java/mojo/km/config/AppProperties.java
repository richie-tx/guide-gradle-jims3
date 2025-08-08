package mojo.km.config;

/** @modelguid {7D823F37-4F08-4DA1-B5AF-DCF8B4ADD0FE} */
public class AppProperties extends GenericProperties {
	/** @modelguid {8CF6B1EB-C757-4403-9F57-670966B587F2} */
	public static final String ENABLE_CACHE = "enableCache";
	/** @modelguid {D7BA31C9-06DF-4365-8F0F-5613B459CEA2} */
	public static final String USE_OID_SERVICE = "UseOIDService";
	/** @modelguid {9CFD54F0-3C16-41A5-A5D6-AD3F25CB63E7} */
	public static final String PROFILE_NAME = "profileName";
	/** @modelguid {B102001B-75DF-4A1F-A2C8-1988F15F500B} */
	public static final String USE_LOGGING = "UseLogging";
	
	public static final String REPORTING_ADAPTER = "reportingAdapter";

	/** @modelguid {005F2CD0-2D42-4C65-81B8-ED93585E940C} */
	public static AppProperties getInstance() {
		return MojoProperties.getInstance().getAppProperties();
	}
	
	/** @modelguid {B7D27A2A-63DB-41E8-80D4-AF0506ED8B12} */
	public String getProfileName() {
		return getProperty(PROFILE_NAME);
	}
	
	/** @modelguid {336C3106-7ED8-4AA9-919A-A4A0CB65C91C} */
	public boolean useOIDService() {
		return getProperty(USE_OID_SERVICE) != null && getProperty(USE_OID_SERVICE).equals("true");
	}

	/** @modelguid {E204E8F6-AD59-43A3-9A53-CFDE46E61AA3} */
	public boolean enableCache() {
		return getProperty(ENABLE_CACHE) != null && getProperty(ENABLE_CACHE).equals("true");
	}

	/** @modelguid {FECE6BAC-7C57-43F4-B7D9-647347DCD4F7} */
	public boolean useLogging() {
		return getProperty(USE_LOGGING) != null && getProperty(USE_LOGGING).equals("true");
	}

	/** @modelguid {FECE6BAC-7C57-43F4-B7D9-647347DCD4F7} */
	public String getReportingAdapter() {
		return getProperty(REPORTING_ADAPTER);
	}
}

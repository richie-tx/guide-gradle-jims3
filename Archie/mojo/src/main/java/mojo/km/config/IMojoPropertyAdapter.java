package mojo.km.config;

/** @modelguid {CDDE3E8E-D1D8-49A7-885A-BD3EB8E2D5D0} */
public interface IMojoPropertyAdapter extends IPropertyAdapter {
	/** @modelguid {E69F68C8-32AF-4DD3-8F34-6DE46BF58D07} */
	public void readImports(boolean shouldReadImports);
	/** @modelguid {F9A37EFA-561A-48A9-9A2C-17E7D9CB04FC} */
	public void saveProperties(String anImportName);
	
	public void saveMapping(String anImportName);
	/**
	 * @param anImportName
	 */
	public void savePfProperties(String anImportName);
}

package mojo.tools.codegen;

import java.util.*;

import mojo.km.config.MojoProperties;
import mojo.km.config.ServiceProperties;
import mojo.tools.code.*;

/**
 * @author mshafi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * @modelguid {EC073AF5-A54E-49E8-812A-4101A4B95CBA}
 */
public class ServiceConfigGenerator extends ControllerBasedGenerator {
	/** @modelguid {8FADEA0A-1081-4E85-8F46-2C8D4D85C002} */
	private static MojoProperties properties;
	/** @modelguid {34DD9891-765B-40F0-ADFD-228A2DA25040} */
	private static String entryPoint;

	/** @modelguid {AD0481B5-7BA1-4250-B4AC-64D7219460F9} */
	public ServiceConfigGenerator(String aFilename) throws Exception {
		super(aFilename);
	}

	/** @modelguid {1B1C626E-5364-480A-8975-A6AD926B303A} */
	public ServiceConfigGenerator(CompilationUnit aController) throws Exception {
		super(aController);
	}

	/** @modelguid {BD494E38-7230-49FC-859E-B94EB343FB8E} */
	public ServiceConfigGenerator(Type aType) throws Exception {
		super(aType);
	}
	
	public ServiceConfigGenerator()
	{
	}

	/** @modelguid {9DE16A08-8C2D-4902-86BB-6F456AE5F775} */
	public static void processPackage(String aPackageName) {
		if (entryPoint == null) {
			entryPoint = "package";
		}
		for (Iterator i = CodeUtil.getControllersAndBoundaries(aPackageName); i.hasNext(); ) {
			CompilationUnit lUnit = (CompilationUnit)i.next();
			try {
				new ServiceConfigGenerator(lUnit).processController();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (entryPoint == "package" && properties != null) {
			properties.saveProperties();
			entryPoint = null;
		}
	}

	
	public void executeSave()
	{
		properties.saveProperties();
	}

	/** @modelguid {B85F9E78-E9E7-4AF9-B6BF-A2A5E61815C7} */
	public void processDesignMethod(Method aMethod) {
//		if (entryPoint == null) {
//			entryPoint = "method";
//		}
		if (properties == null) {
			String lConfigFile = configFile;
			if (lConfigFile == null) {
				lConfigFile = System.getProperty("config.file");
			} 
			String lImportFile = importFile;
			if (lImportFile == null) {
				lImportFile = System.getProperty("import.file");
			} 
			properties = new MojoProperties(lConfigFile, false);
			if (lImportFile != null) {
				properties.setImportFile(lImportFile);
			}
			try {
				properties.loadProperties();
			} catch (Exception e) {
				System.err.println("A new "+lConfigFile+" will be created because: "+e.getMessage());
			}
		}

		String lMethodName = aMethod.getName();
		String lClassName = lMethodName.substring(0,1).toUpperCase()+lMethodName.substring(1);
		String lPackageName = controllerClass.getPackage().getName();
		// get the service name, which is limited to 15 characters
		String lServiceName = aMethod.getProperty("serviceName");
		if (lServiceName == null) {
			lServiceName = lMethodName.substring(0,1).toUpperCase()+lMethodName.substring(1);
		}

		String lEventName = getEventName(lMethodName);
		ServiceProperties lService = new ServiceProperties();
		lService.setName(lServiceName);
		lService.setEventName(lEventName);
		String lCheckPoint = aMethod.getProperty("checkPoint");
		if (lCheckPoint != null) {
			lService.setCheckPoint("true");
		}

		String lStereotype = this.controllerClass.getProperty("stereotype");
		if (lStereotype != null && lStereotype.equalsIgnoreCase("control")) {
			Vector lCommands = new Vector();
			if (aMethod.getProperty("commandList") != null) {
				StringTokenizer lTokens = new StringTokenizer(aMethod.getProperty("commandList"), ";");
				while (lTokens.hasMoreTokens()) {
					lCommands.add(lTokens.nextToken());
				}
			} else {
				lCommands.add(lPackageName+".transactions."+lClassName+"Command");
			}

			for (Iterator i = lCommands.iterator(); i.hasNext(); ) {
				String lCommand = (String)i.next();
				lService.addCommand(lCommand);
			}
		}

		properties.addService(lService);

//		if (entryPoint == "method") {
//			properties.saveProperties();
//			entryPoint = null;
//		}
	}

	/** @modelguid {B97D1F94-9B39-4C76-971E-3A6BAFB2C585} */
	private String getEventName(String aMethodName) {
		String lEventPackage = controllerClass.getProperty("eventPackage");
		if (lEventPackage == null) {
			lEventPackage = controllerClass.getPackage().getName();
			if (lEventPackage.indexOf(".") > -1) {
				lEventPackage = lEventPackage.substring(lEventPackage.lastIndexOf(".")+1);
			}
			lEventPackage = "messaging."+lEventPackage;
		}
		String lEventName = aMethodName.substring(0,1).toUpperCase()+aMethodName.substring(1)+"Event";
		return lEventPackage+"."+lEventName;
	}

	/** @modelguid {BF7A0773-1EDF-4B47-A437-63506A38CD9B} */
	public static void main(String args[]) {
		String lPackages = args[0];
		StringTokenizer lPackageTokens = new StringTokenizer(lPackages,";");
		while (lPackageTokens.hasMoreTokens()) {
			String lPackage = lPackageTokens.nextToken();
			System.out.println("Processing: "+lPackage);
			try {
				processPackage(lPackage);
			} catch (Throwable t) {
				System.err.println(" Unable to process package " + lPackage);
			}
		}
	}
}

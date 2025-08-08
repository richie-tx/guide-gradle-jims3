/*
 * Created on Apr 28, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package mojo.tools.codegen;

import java.net.URL;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.jdom.Document;
import org.jdom.Element;

import mojo.km.config.AppProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.ServerProperties;
import mojo.km.config.SessionProperties;
import mojo.km.exceptionhandling.ExceptionHandler;
import mojo.km.utilities.XMLManager;

/**
 * @author sshafi
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 * @modelguid {5640134C-D378-47AA-A66E-CE7296D752D9}
 */
public class ServerSpecConfigGenerator {
	/** @modelguid {EC933580-5F78-4C7D-AE99-09BB1669F507} */
	private static void generateServerSpec() {
		try {
			String configFile = System.getProperty("mojo.config");
			String serverIDBuf = AppProperties.getInstance().getProperty( "MultiServerServer");
			String clientIDBuf = AppProperties.getInstance().getProperty( "MultiServerClient");
			if (serverIDBuf == null || clientIDBuf == null) {
				throw new RuntimeException("The MultiServerServer and MultiServerClient attributes must be set.");
			}
			
			Element lRootElement = new Element("server");

			StringTokenizer serverIDs = new StringTokenizer( serverIDBuf, ";" );
			StringTokenizer clientIDs = new StringTokenizer( clientIDBuf, ";" );
			int j = 0;
			while (serverIDs.hasMoreTokens()) {
				String serverURL = serverIDs.nextToken();
				String clientURL = clientIDs.nextToken();
				URL aURL = new URL( serverURL );
				int serverPort = aURL.getPort();
				aURL = new URL( clientURL );
				int clientPort = aURL.getPort();

				Element lProcess = new Element("process");
				lProcess.setAttribute("name", "RoutingServer"+(j++));
				lProcess.setAttribute("className", "mojo.km.dispatch.tcpmultiserver.RoutingServer");
				lProcess.setAttribute("configFile", configFile);
				
				Element lParam = new Element("param");
				lParam.setAttribute("value", clientPort+"");
				lProcess.addContent(lParam);
				lParam = new Element("param");
				lParam.setAttribute("value", serverPort+"");
				lProcess.addContent(lParam);

				lRootElement.addContent(lProcess);
			}

			for (Iterator i = MojoProperties.getInstance().getServers(); i.hasNext(); ) {
				ServerProperties server = (ServerProperties)i.next();
				SessionProperties lSessionProps = server.getSessionProperties();
				String lBuildServerConfig = lSessionProps.getProperty("BuildServerConfig"); 
				if (lBuildServerConfig != null && lBuildServerConfig.equals("true")) {
					int maxServers = 1;
					String lMinServers = lSessionProps.getProperty("MinServers"); 
					if (lMinServers != null) {
						try {
							maxServers = Integer.parseInt(lMinServers);
						} catch (Exception p) {
							ExceptionHandler.executeCallbacks(p);
						}
					}
					for (j = 0; j < maxServers; j++) {
						Element lProcess = new Element("process");
						lProcess.setAttribute("name", server.getName()+j);
						lProcess.setAttribute("className", "mojo.km.dispatch.tcpmultiserver.ContextServer");
						lProcess.setAttribute("configFile", configFile);

						Element lParam = new Element("param");
						lParam.setAttribute("value", server.getName());
						lProcess.addContent(lParam);
						
						lRootElement.addContent(lProcess);
					}
				}
			}

			Document lDoc = new Document(lRootElement);
			try {
				XMLManager.writeXMLToResource(lDoc, "StartServerSpec.xml");
			} catch (Exception e) {
				try {
					URL lMojoURL = Thread.currentThread().getContextClassLoader().getResource("Mojo.xml");
					String lFilename = lMojoURL.getFile();
					lFilename = lFilename.substring(0, lFilename.length()-8)+"StartServerSpec.xml";
					XMLManager.writeXMLToFile(lDoc, lFilename);
				} catch (Exception ex) {
					ex.printStackTrace();
					System.err.println("Original Error:");
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			ExceptionHandler.executeCallbacks(e);
		}
	}

	/** @modelguid {74DEFDBD-1772-414F-ABF4-67DA57E8EB34} */
	public static void main(String[] args) {
		ServerSpecConfigGenerator.generateServerSpec();
	}
}

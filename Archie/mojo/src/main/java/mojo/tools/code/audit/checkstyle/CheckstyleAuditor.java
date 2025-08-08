////////////////////////////////////////////////////////////////////////////////
// checkstyle: Checks Java source code for adherence to a set of rules.
// Copyright (C) 2001-2005  Oliver Burn
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
////////////////////////////////////////////////////////////////////////////////
package mojo.tools.code.audit.checkstyle;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.ConfigurationLoader;
import com.puppycrawl.tools.checkstyle.ModuleFactory;
import com.puppycrawl.tools.checkstyle.PropertiesExpander;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import com.puppycrawl.tools.checkstyle.api.Configuration;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

/**
 * Wrapper for Checkstyle Checker.
 * 
 * @author Jim Fisher
 */
public final class CheckstyleAuditor
{
	private String configFile;

	private List files;

	private AuditListener listener;

	public CheckstyleAuditor(String aConfigFile, List theFiles, AuditListener aListener)
	{
		this.configFile = aConfigFile;
		this.files = theFiles;
		this.listener = aListener;
	}

	public void execute()
	{
		final Properties props = System.getProperties();

		try
		{
				final Configuration config = ConfigurationLoader.loadConfiguration(this.configFile,
						new PropertiesExpander(props), true);

				ModuleFactory moduleFactory = null;

				final Checker c = createChecker(config, moduleFactory, this.listener);

				final File[] filesArray = new File[files.size()];

				int index = 0;

				Iterator i = files.iterator();

				while (i.hasNext())
				{
					String fileName = (String) i.next();
					filesArray[index] = new File(fileName);
					index++;
				}

				final int numErrs = c.process(filesArray);

				c.destroy();
			
		}
		catch (CheckstyleException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Creates the Checker object.
	 * 
	 * @param aConfig
	 *            the configuration to use
	 * @param aFactory
	 *            the module factor to use
	 * @param aNosy
	 *            the sticky beak to track what happens
	 * @return a nice new fresh Checker
	 */
	private static Checker createChecker(Configuration aConfig, ModuleFactory aFactory, AuditListener aNosy)
	{
		Checker c = null;
		try
		{
			c = new Checker();
			c.setModuleFactory(aFactory);
			c.configure(aConfig);
			c.addListener(aNosy);
		}
		catch (Exception e)
		{
			System.out.println("Unable to create Checker: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		return c;
	}

}

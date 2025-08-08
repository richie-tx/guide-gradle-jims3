package mojo.tools.codegen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mojo.tools.code.CompilationUnit;
import mojo.tools.code.Method;
import mojo.tools.code.Type;
import mojo.tools.code.parsers.CodeParser;
import mojo.tools.code.parsers.ICodeParser;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class GenerateServiceLookupTask extends Task
{
	private static final String CONTROL_STEREOTYPE = "control";

	private static final String DESIGN_STEREOTYPE = "design";

	private static final String STEREOTYPE_KEY = "stereotype";

	public static void main(String[] args)
	{
		GenerateServiceLookupTask task = new GenerateServiceLookupTask();

		task.setOutDir("c:/views/jims2_framework_dev/Arch/build/configuration/dev");
		task.setOutFile("eventservice.xml");
		task.setSrcDir("c:/views/jims2_framework_dev/app/src,c:/views/jims2_framework_dev/framework/src");

		task.execute();
	}

	private String outDir;

	private String outFile;

	private String srcDir;

	@Override
	public void execute() throws BuildException
	{
		JavaFileFilter filter = new JavaFileFilter();

		DirectoryFileWalker walker = new DirectoryFileWalker(filter);
		List<File> results = new ArrayList<File>();

		String[] startDirStr = this.getSrcDir().split(",");

		try
		{
			for (int i = 0; i < startDirStr.length; i++)
			{
				File startDir = new File(startDirStr[i]);

				walker.start(startDir, results);
			}

			this.log("included files count: " + results.size());
			List<CompilationUnit> controllers = this.filterControllers(results);
			this.log("controller count: " + controllers.size());
			Map<String, EventServiceLookup> serviceLookups = this.generateServiceLookups(controllers);

			this.generateServiceLookupConfig(serviceLookups);
		}
		catch (Exception e)
		{
			throw new BuildException(e);
		}
	}

	private List<CompilationUnit> filterControllers(List<File> results) throws Exception
	{
		ICodeParser parser = CodeParser.getParser();

		List<CompilationUnit> controllers = new ArrayList<CompilationUnit>();

		int len = results.size();
		for (int i = 0; i < len; i++)
		{
			File file = results.get(i);
			String path = file.getAbsoluteFile().getAbsolutePath();

			CompilationUnit compUnit = parser.parseFile(path);
			Type type = compUnit.getMainType();
			if (type != null)
			{
				String qualifiedName = type.getQualifiedName();
				if (qualifiedName.endsWith("Controller"))
				{
					String controlProp = type.getProperty(STEREOTYPE_KEY);
					if (CONTROL_STEREOTYPE.equals(controlProp))
					{
						controllers.add(compUnit);
					}
				}
			}
		}

		return controllers;
	}

	private void generateServiceLookupConfig(Map<String, EventServiceLookup> eventServiceLookups)
	{
		File f = new File(outDir, outFile);
		Writer writer = null;
		try
		{
			writer = new BufferedWriter(new FileWriter(f));
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			writer.write("\n");
			writer.write("<beans xmlns=\"http://www.springframework.org/schema/beans\"");
			writer.write("\n\t");
			writer.write("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
			writer.write("\n\t");
			writer.write("xsi:schemaLocation=\"http://www.springframework.org/schema/beans ");
			writer.write("http://www.springframework.org/schema/beans/spring-beans-2.0.xsd\">");
			writer.write("\n");
			
			Iterator<EventServiceLookup> i = eventServiceLookups.values().iterator();
			while(i.hasNext())
			{
				EventServiceLookup eventService = i.next();
				writer.write(eventService.toString());
				writer.write("\n");
			}

			writer.write("</beans>");
		}
		catch (IOException e)
		{
			throw new BuildException(e);
		}
		finally
		{
			try
			{
				writer.close();
				this.log("Event Service Lookup configuration file created: " + f.getAbsoluteFile().getAbsolutePath());
			}
			catch (IOException e)
			{
				throw new BuildException(e);
			}
		}
	}

	private Map<String, EventServiceLookup> generateServiceLookups(List<CompilationUnit> controllers)
	{
		Map<String, EventServiceLookup> serviceLookups = new HashMap<String, EventServiceLookup>();
		int len = controllers.size();
		for (int i = 0; i < len; i++)
		{
			CompilationUnit compUnit = controllers.get(i);

			Type type = compUnit.getMainType();

			List methods = type.getMethods();

			int methodLen = methods.size();
			for (int m = 0; m < methodLen; m++)
			{
				Method method = (Method) methods.get(m);
				String stereotype = method.getProperty(STEREOTYPE_KEY);

				if (DESIGN_STEREOTYPE.equals(stereotype))
				{
					EventServiceLookup eventService = new EventServiceLookup();

					// name
					//StringBuilder name = new StringBuilder(method.getName());
					//char firstChar = Character.toUpperCase(name.charAt(0));
					//name.setCharAt(0, firstChar);
					//eventService.setName(name.toString());

					// event
					String packageName = compUnit.getPackageName();
					int index = packageName.lastIndexOf('.') + 1;
					
					StringBuilder methodName = new StringBuilder(method.getName());
					methodName.setCharAt(0, Character.toUpperCase(methodName.charAt(0)));
					
					StringBuilder name = new StringBuilder(30);
					name.append(packageName.substring(index, packageName.length()));
					name.append(".");
					name.append(methodName);
					eventService.setName(name.toString().toUpperCase());
					
					StringBuilder eventName = new StringBuilder(30);
					eventName.append("messaging.");
					eventName.append(name);					
					eventName.append("Event");
					eventService.setEventName(eventName.toString());									

					// service
					packageName += ".transactions.";
					StringBuilder serviceName = new StringBuilder(50);
					serviceName.append(packageName);
					serviceName.append(methodName);
					serviceName.append("Command");
					eventService.setServiceName(serviceName.toString());

					serviceLookups.put(eventService.getName(), eventService);
				}
			}
		}
		return serviceLookups;
	}

	public String getOutDir()
	{
		return outDir;
	}

	public String getOutFile()
	{
		return outFile;
	}

	public String getSrcDir()
	{
		return srcDir;
	}

	public void setOutDir(String outDir)
	{
		this.outDir = outDir;
	}

	public void setOutFile(String outFile)
	{
		this.outFile = outFile;
	}

	public void setSrcDir(String srcDir)
	{
		this.srcDir = srcDir;
	}
}

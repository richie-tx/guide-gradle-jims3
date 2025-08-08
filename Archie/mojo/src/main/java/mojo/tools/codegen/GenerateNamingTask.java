package mojo.tools.codegen;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mojo.tools.code.Attribute;
import mojo.tools.code.CompilationUnit;
import mojo.tools.code.Method;
import mojo.tools.code.Type;
import mojo.tools.code.parsers.CodeParser;
import mojo.tools.code.parsers.ICodeParser;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class GenerateNamingTask extends Task
{
	private static final String CONTROL_STEREOTYPE = "control";

	private static final String DESIGN_STEREOTYPE = "design";

	private static final String STEREOTYPE_KEY = "stereotype";

	public static void main(String[] args)
	{
		GenerateNamingTask task = new GenerateNamingTask();
		task.setOutDir("../../../app/src");
		task.setSrcDir("../../../app/src,../../../framework/mojo-jims2/mojo.java/src");
		task.execute();
	}

	private String outDir;

	private String srcDir;

	private List<StringBuilder> collectNameConstants(Type aType)
	{
		System.out.println("Find names in: " + aType.getName());
		List<StringBuilder> names = new ArrayList<StringBuilder>();

		List methods = aType.getMethods();

		int methodLen = methods.size();
		for (int m = 0; m < methodLen; m++)
		{
			Method method = (Method) methods.get(m);

			String designStereotype = method.getProperty(STEREOTYPE_KEY);
			if (DESIGN_STEREOTYPE.equals(designStereotype))
			{
				StringBuilder nameConstant = new StringBuilder(30);
				// TODO For future naming implementation:
				// String VAR_NAME = "PKG.METHODNAME";
				//
				// int index = packageName.lastIndexOf('.') + 1;
				// nameConstant.append(packageName.substring(index, packageName.length()));
				// nameConstant.append(".");
				
				nameConstant.append(method.getName());

				char ch = Character.toUpperCase(nameConstant.charAt(0));
				nameConstant.replace(0, 1, String.valueOf(ch));
				
				names.add(nameConstant);
			}
		}

		return names;
	}

	@Override
	public void execute() throws BuildException
	{
		JavaFileFilter filter = new JavaFileFilter();

		DirectoryFileWalker walker = new DirectoryFileWalker(filter);
		List<File> results = new ArrayList<File>();

		System.out.println("src dir: " + this.getSrcDir());

		String[] startDirStr = this.getSrcDir().split(",");

		try
		{
			for (int i = 0; i < startDirStr.length; i++)
			{
				File startDir = new File(startDirStr[i]);

				System.out.println("walk src dir: " + startDir.getAbsoluteFile().getAbsolutePath());

				walker.start(startDir, results);
			}

			System.out.println("included files count: " + results.size());
			List<CompilationUnit> controllers = this.filterControllers(results);
			System.out.println("controller count: " + controllers.size());
			Map<String, List<StringBuilder>> nameMap = this.generateNamingFiles(controllers);

			File srcDirectory = new File(this.getOutDir());
			String baseDir = srcDirectory.getAbsoluteFile().getAbsolutePath();
			System.out.println("output src dir: " + baseDir);

			System.setProperty("source.path", baseDir);

			String namingDir = baseDir + "/naming";

			this.generateNamingFiles(nameMap, namingDir);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new BuildException(e);
		}
	}

	private List<CompilationUnit> filterControllers(List<File> results) throws Exception
	{
		ICodeParser parser = CodeParser.getParser();

		List<CompilationUnit> controllers = new ArrayList<CompilationUnit>();

		int len = results.size();

		String path = "";
		try
		{
			for (int i = 0; i < len; i++)
			{
				File file = results.get(i);
				path = file.getAbsoluteFile().getAbsolutePath();
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
		}
		catch (Exception e)
		{
			System.err.println("Error parsing controller: " + path);
			throw e;
		}

		return controllers;
	}

	private Map<String, List<StringBuilder>> generateNamingFiles(List<CompilationUnit> controllers)
	{
		Map<String, List<StringBuilder>> nameMap = new HashMap<String, List<StringBuilder>>();
		int len = controllers.size();
		for (int i = 0; i < len; i++)
		{
			CompilationUnit compUnit = controllers.get(i);

			Type type = compUnit.getMainType();

			List<StringBuilder> names = this.collectNameConstants(type);

			String key = compUnit.getMainType().getName() + "ServiceNames";
			List<StringBuilder> existingNames = nameMap.get(key);

			if (existingNames == null)
			{
				nameMap.put(key, names);
			}
			else
			{
				existingNames.addAll(names);
			}
		}

		return nameMap;
	}

	private void generateNamingFiles(Map<String, List<StringBuilder>> nameMap, String namingDirString)
	{
		Set<String> keySet = nameMap.keySet();
		Iterator<String> i = keySet.iterator();
		while (i.hasNext())
		{
			CompilationUnit compUnit = new CompilationUnit();

			String name = i.next();
			String filename = namingDirString + "/" + name + ".java";
			File file = new File(filename);
			compUnit.setFile(file);

			Type mainType = new mojo.tools.code.Class(name);
			mainType.setScope("public");
			compUnit.addType(mainType);
			List names = nameMap.get(name);

			compUnit.setPackageName("naming");

			int len = names.size();
			for (int j = 0; j < len; j++)
			{
				StringBuilder nameString = (StringBuilder) names.get(j);

				name = nameString.toString();

				//int index = name.lastIndexOf('.') + 1;
				//String varName = name.substring(index, name.length());

				Attribute attr = new Attribute("String", name.toUpperCase());
				attr.setScope("public");
				attr.setStatic(true);
				attr.setInitialValue("\"" + name + "\"");

				mainType.addAttribute(attr);
			}
			System.out.println("saving: " + file.getAbsoluteFile().getAbsolutePath());
			compUnit.save();
		}
	}

	public String getOutDir()
	{
		return outDir;
	}

	public String getSrcDir()
	{
		return srcDir;
	}

	public void setOutDir(String outDir)
	{
		this.outDir = outDir;
	}

	public void setSrcDir(String srcDir)
	{
		this.srcDir = srcDir;
	}
}

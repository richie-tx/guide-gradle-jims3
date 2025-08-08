package mojo.tools.codegen;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mojo.km.utilities.CollectionUtil;
import mojo.tools.code.Attribute;
import mojo.tools.code.CompilationUnit;
import mojo.tools.code.Method;
import mojo.tools.code.Parameter;
import mojo.tools.code.Type;
import mojo.tools.code.parsers.CodeParser;
import mojo.tools.code.parsers.ICodeParser;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class DesignComponentGeneratorTask extends Task
{
	private static final Object BOUNDARY_STEREOTYPE = "boundary";

	private static final String CONTROL_STEREOTYPE = "control";

	private static final String DESIGN_STEREOTYPE = "design";

	private static final String STEREOTYPE_KEY = "stereotype";

	public static void main(String[] args)
	{
		DesignComponentGeneratorTask task = new DesignComponentGeneratorTask();
		task.setOutDir("c:/views/RSA7_2/Arch/build/ant/../../../app/src");
		task.setSrcDir("c:/views/RSA7_2/Arch/build/ant/../../../app/src");
		task.execute();
	}

	private String outDir;

	private String srcDir;

	private List<Method> collectAnalysisComponentNames(Type aType)
	{
		System.out.println("Find methods in: " + aType.getName());
		List<Method> analysisMethods = new ArrayList<Method>();

		List methods = aType.getMethods();

		int methodLen = methods.size();
		for (int m = 0; m < methodLen; m++)
		{
			Method method = (Method) methods.get(m);

			String designStereotype = method.getProperty(STEREOTYPE_KEY);
			if (DESIGN_STEREOTYPE.equals(designStereotype))
			{
				analysisMethods.add(method);
			}
		}

		return analysisMethods;
	}

	private CompilationUnit createAction(Type boundaryType, Method aMethod, String methodName)
	{
		CompilationUnit compUnit = new CompilationUnit();

		String packageName = boundaryType.getPackage().getName() + ".action";
		compUnit.setPackageName(packageName);

		String actionName = methodName + "Action";

		mojo.tools.code.Class actionType = new mojo.tools.code.Class(actionName);

		compUnit.addImport("java.util.Map");

		compUnit.addImport("javax.servlet.http.HttpServletRequest");
		compUnit.addImport("javax.servlet.http.HttpServletResponse");

		compUnit.addImport("org.apache.struts.action.ActionForm");
		compUnit.addImport("org.apache.struts.action.ActionForward");
		compUnit.addImport("org.apache.struts.action.ActionMapping");

		compUnit.addImport("ui.action.JIMSBaseAction");

		actionType.setExtendsClass("JIMSBaseAction");

		actionType.setScope("public");

		// add methods from @designMethod markings in JavaDoc
		Iterator p = aMethod.getProperties("designMethod");

		StringBuilder buttonMethodBody = new StringBuilder();

		while (p.hasNext())
		{
			String prop = (String) p.next();

			Method designMethod = this.createActionMethod(prop);

			actionType.addMethod(designMethod);

			buttonMethodBody.append("keyMap.put(\"\", \"");
			buttonMethodBody.append(prop);
			buttonMethodBody.append("\");\n");
		}

		// add standard methods from abstract base type methods
		Method buttonMethod = new Method("addButtonMapping");
		buttonMethod.setScope("protected");
		Parameter parm = new Parameter("Map", "keyMap");
		buttonMethod.addParameter(parm);
		buttonMethod.setBody(buttonMethodBody.toString());
		actionType.addMethod(buttonMethod);

		compUnit.addType(actionType);

		return compUnit;
	}

	private Method createActionMethod(String aMethodName)
	{
		Parameter parm1 = new Parameter("ActionMapping", "aMapping");
		Parameter parm2 = new Parameter("ActionForm", "aForm");
		Parameter parm3 = new Parameter("HttpServletRequest", "aRequest");
		Parameter parm4 = new Parameter("HttpServletResponse", "aResponse");

		Method designMethod = new Method(aMethodName);
		designMethod.setScope("public");
		designMethod.addParameter(parm1);
		designMethod.addParameter(parm2);
		designMethod.addParameter(parm3);
		designMethod.addParameter(parm4);
		designMethod.setReturnType("ActionForward");
		designMethod.setBody("return null;");
		return designMethod;
	}

	private List<CompilationUnit> createActionComponents(Type type, List<Method> boundaryMethods)
	{
		List<CompilationUnit> components = new ArrayList<CompilationUnit>();

		String packageName = type.getPackage().getName();

		String pkgPath = packageName.replace('.', '/');

		int len = boundaryMethods.size();
		for (int i = 0; i < len; i++)
		{
			// create command
			StringBuilder path = new StringBuilder(80);

			path.append(this.srcDir);
			path.append("/");
			path.append(pkgPath);
			path.append("/action");

			Method method = boundaryMethods.get(i);

			StringBuilder methodName = new StringBuilder(50);
			methodName.append(method.getName());
			String chStr = methodName.substring(0, 1).toUpperCase();
			methodName.replace(0, 1, chStr);

			String methodStr = methodName.toString();

			String actionFileName = methodStr + "Action.java";

			File f = new File(path.toString(), actionFileName);

			if (f.exists() == false && "none".equalsIgnoreCase(methodStr) == false)
			{
				CompilationUnit compUnit = this.createAction(type, method, methodStr);
				compUnit.setFile(f);
				components.add(compUnit);
			}
			else if (f.exists() == true && f.canWrite() == true)
			{
				CompilationUnit compUnit = this.updateAction(f, type, method, methodStr);
				if (compUnit != null)
				{
					compUnit.setFile(f);
					components.add(compUnit);
				}
			}
		}

		return components;
	}

	private CompilationUnit createCommand(Type controllerType, String methodName)
	{
		CompilationUnit compUnit = new CompilationUnit();

		String packageName = controllerType.getPackage().getName() + ".transactions";
		compUnit.setPackageName(packageName);

		String commandName = methodName + "Command";

		mojo.tools.code.Class cmdType = new mojo.tools.code.Class(commandName);

		compUnit.addImport("mojo.km.context.ICommand");
		compUnit.addImport("mojo.km.messaging.IEvent");

		cmdType.addImplement("ICommand");

		cmdType.setScope("public");

		Method method = new Method("execute");
		method.setScope("public");
		Parameter eventParm = new Parameter("IEvent", "anEvent");
		method.addParameter(eventParm);
		cmdType.addMethod(method);

		method = new Method("onRegister");
		method.setScope("public");
		method.addParameter(eventParm);
		cmdType.addMethod(method);

		method = new Method("onUnregister");
		method.setScope("public");
		method.addParameter(eventParm);
		cmdType.addMethod(method);

		method = new Method("update");
		method.setScope("public");
		Parameter objParm = new Parameter("Object", "anObject");
		method.addParameter(objParm);
		cmdType.addMethod(method);

		compUnit.addType(cmdType);

		return compUnit;
	}

	private List<CompilationUnit> createCommandComponents(Type type, List<Method> controllerMethods)
	{
		List<CompilationUnit> components = new ArrayList<CompilationUnit>();

		String packageName = type.getPackage().getName();

		String pkgPath = packageName.replace('.', '/');

		int len = controllerMethods.size();
		for (int i = 0; i < len; i++)
		{
			// create command
			StringBuilder path = new StringBuilder(80);

			path.append(this.srcDir);
			path.append("/");
			path.append(pkgPath);
			path.append("/transactions");

			Method method = controllerMethods.get(i);

			StringBuilder methodName = new StringBuilder(50);
			methodName.append(method.getName());
			String chStr = methodName.substring(0, 1).toUpperCase();
			methodName.replace(0, 1, chStr);

			String commandFileName = methodName.toString() + "Command.java";

			File f = new File(path.toString(), commandFileName);
			if (f.exists() == false)
			{
				CompilationUnit compUnit = this.createCommand(type, methodName.toString());
				compUnit.setFile(f);
				components.add(compUnit);
			}

			// create request event
			path = new StringBuilder(80);

			path.append(this.srcDir);
			path.append("/");
			path.append("messaging/");
			int startIndex = pkgPath.lastIndexOf("/") + 1;
			int endIndex = pkgPath.length();
			String eventPkg = pkgPath.substring(startIndex, endIndex);
			path.append(eventPkg);

			String eventFileName = methodName.toString() + "Event.java";

			f = new File(path.toString(), eventFileName);
			if (f.exists() == false)
			{
				CompilationUnit compUnit = this.createEvent(eventPkg, method, methodName.toString());
				compUnit.setFile(f);
				components.add(compUnit);
			}
		}

		return components;
	}

	private CompilationUnit createEvent(String eventPkg, Method method, String methodName)
	{
		CompilationUnit compUnit = new CompilationUnit();

		StringBuilder pkgBuffer = new StringBuilder(80);
		pkgBuffer.append("messaging.");
		pkgBuffer.append(eventPkg);

		compUnit.setPackageName(pkgBuffer.toString());

		String eventName = methodName + "Event";

		mojo.tools.code.Class eventType = new mojo.tools.code.Class(eventName);

		compUnit.addImport("mojo.km.messaging.RequestEvent");

		eventType.setExtendsClass("RequestEvent");
		eventType.setScope("public");

		Iterator i = method.getParameters();
		while (i.hasNext())
		{
			Parameter p = (Parameter) i.next();
			Attribute a = this.parameterToAttribute(compUnit, p);
			eventType.addAttribute(a);
		}

		compUnit.addType(eventType);

		return compUnit;
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

			List<CompilationUnit> boundaries = new ArrayList<CompilationUnit>();
			List<CompilationUnit> controllers = new ArrayList<CompilationUnit>();

			this.filterComponents(results, boundaries, controllers);
			System.out.println("boundary count: " + boundaries.size() + ", controller count: " + controllers.size());

			List<CompilationUnit> uiComponents = this.generateUIFiles(boundaries);
			List<CompilationUnit> serviceComponents = this.generateServiceFiles(controllers);

			int count = boundaries.size() + controllers.size();

			List<CompilationUnit> components = new ArrayList<CompilationUnit>(count);
			components.addAll(uiComponents);
			components.addAll(serviceComponents);

			File srcDirectory = new File(this.getOutDir());
			String baseDir = srcDirectory.getAbsoluteFile().getAbsolutePath();
			System.out.println("output src dir: " + baseDir);

			System.setProperty("source.path", baseDir);

			int len = components.size();
			for (int i = 0; i < len; i++)
			{
				CompilationUnit compUnit = components.get(i);
				System.out.println("save: " + compUnit.getMainType().getQualifiedName());
				compUnit.save();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new BuildException(e);
		}
	}

	private void filterComponents(List<File> results, List<CompilationUnit> boundaries,
			List<CompilationUnit> controllers) throws Exception
	{
		ICodeParser parser = CodeParser.getParser();

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
					String componentStereotype = type.getProperty(STEREOTYPE_KEY);
					if (CONTROL_STEREOTYPE.equals(componentStereotype))
					{
						controllers.add(compUnit);
					}
					else if (BOUNDARY_STEREOTYPE.equals(componentStereotype))
					{
						boundaries.add(compUnit);
					}
				}
			}
		}
		catch (Exception e)
		{
			System.err.println("Error parsing controller: " + path);
			throw e;
		}
	}

	private List<CompilationUnit> generateServiceFiles(List<CompilationUnit> controllers)
	{
		List<CompilationUnit> commands = new ArrayList<CompilationUnit>();
		int len = controllers.size();
		for (int i = 0; i < len; i++)
		{
			CompilationUnit compUnit = controllers.get(i);

			Type type = compUnit.getMainType();

			List<Method> controllerMethods = this.collectAnalysisComponentNames(type);

			List<CompilationUnit> compUnits = this.createCommandComponents(type, controllerMethods);

			commands.addAll(compUnits);
		}

		return commands;
	}

	private List<CompilationUnit> generateUIFiles(List<CompilationUnit> boundaries)
	{
		List<CompilationUnit> actions = new ArrayList<CompilationUnit>();
		int len = boundaries.size();
		for (int i = 0; i < len; i++)
		{
			CompilationUnit compUnit = boundaries.get(i);

			Type type = compUnit.getMainType();

			List<Method> boundaryMethods = this.collectAnalysisComponentNames(type);

			List<CompilationUnit> compUnits = this.createActionComponents(type, boundaryMethods);

			actions.addAll(compUnits);
		}

		return actions;
	}

	public String getOutDir()
	{
		return outDir;
	}

	public String getSrcDir()
	{
		return srcDir;
	}

	private Attribute parameterToAttribute(CompilationUnit compUnit, Parameter p)
	{
		String type = p.getType();
		if (type == null)
		{
			type = "";
		}
		else if (type.equalsIgnoreCase("date"))
		{
			compUnit.addImport("java.util.Date");
		}
		Attribute a = new Attribute(p.getType(), p.getName());
		a.setScope("private");
		return a;
	}

	public void setOutDir(String outDir)
	{
		this.outDir = outDir;
	}

	public void setSrcDir(String srcDir)
	{
		this.srcDir = srcDir;
	}

	private CompilationUnit updateAction(File f, Type type, Method aMethod, String methodStr)
	{
		CompilationUnit compUnit = null;
		boolean newDesignMethod = false;
		try
		{
			String path = f.getAbsolutePath();
			compUnit = CodeParser.getParser().parseFile(path);

			Type mainType = compUnit.getMainType();

			List methods = mainType.getMethods();
			Map candidates = new HashMap();
			int len = methods.size();
			for (int i = 0; i < len; i++)
			{
				Method m = (Method) methods.get(i);
				List p = CollectionUtil.iteratorToList(m.getParameters());
				if (p.size() == 4)
				{
					Parameter p1 = (Parameter) p.get(0);
					if (p1.getType().endsWith("ActionMapping") == false)
					{
						break;
					}
					p1 = (Parameter) p.get(1);
					if (p1.getType().endsWith("ActionForm") == false)
					{
						break;
					}
					p1 = (Parameter) p.get(2);
					if (p1.getType().endsWith("HttpServletRequest") == false)
					{
						break;
					}
					p1 = (Parameter) p.get(3);
					if (p1.getType().endsWith("HttpServletResponse") == false)
					{
						break;
					}
					candidates.put(m.getName(), m);
				}
			}

			Iterator p = aMethod.getProperties("designMethod");
			while (p.hasNext())
			{
				String designMethodName = (String) p.next();
				if (candidates.containsKey(designMethodName) == false)
				{
					newDesignMethod = true;
					Method designMethod = this.createActionMethod(designMethodName);

					mainType.addMethod(designMethod);

					String[] parms = { "Map" };
					Method buttonMethod = mainType.getMethod("addButtonMapping", parms);
					StringBuilder buttonMethodBody = new StringBuilder();
					buttonMethodBody.append(buttonMethod.getBody());
					buttonMethodBody.append("keyMap.put(\"\", \"");
					buttonMethodBody.append(designMethodName);
					buttonMethodBody.append("\");\n");
					buttonMethod.setBody(buttonMethodBody.toString());
				}
			}
		}
		catch (Exception e)
		{
			compUnit = null;
			e.printStackTrace();
		}
		if (newDesignMethod == false)
		{
			compUnit = null;
		}
		return compUnit;
	}
}

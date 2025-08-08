package mojo.tools.code;

import mojo.tools.code.KeyWord;
import mojo.km.utilities.TextUtil;
import mojo.tools.code.parsers.CodeParser;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * @author Jim Fisher
 * 
 */
public class CodeUtil
{
	public static Type findType(List importList, String packageName,
			String typeName)
	{
		Package lPackage = new Package(
				packageName);
		if (lPackage != null)
		{
			Iterator units = lPackage.getCompilationUnits();
			if (units != null)
			{
				while (units.hasNext())
				{
					Type mainClass = ((CompilationUnit) units.next())
							.getMainType();
					if (mainClass != null
							&& mainClass.getQualifiedName().startsWith("pd.") == false)
					{
						continue;
					}
					if (mainClass.getName().equals(typeName))
					{
						return mainClass;
					}
				}
			}
		}
		if (importList == null)
		{
			return (Type) null;
		}
		for (Iterator i = importList.iterator(); i.hasNext();)
		{
			String importStatement = i.next().toString();
			CompilationUnit lUnit = null;
			try
			{
				lUnit = CodeParser.getParser().parseClass(importStatement);
			}
			catch (Exception e)
			{
				continue;
			}
			if (lUnit == null)
			{
				continue;
			}
			Type mainClass = lUnit.getMainType();
			if (null != mainClass
					&& !mainClass.getQualifiedName().startsWith("pd."))
			{
				continue;
			}
			if (mainClass.getName().equals(typeName))
			{
				return mainClass;
			}
		}
		return (Type) null;
	}

	/**
	 * @param ifExpressionString
	 * @return
	 */
	public static String formatStatementForComparison(StringBuffer buffer)
	{
		char[] chars = new char[buffer.length()];
		buffer.getChars(0, buffer.length(), chars, 0);

		boolean openDoubleQuote = false;

		boolean done = false;

		int i = 0;

		while (done == false)
		{
			char ch = chars[i];
			if (ch == '\"')
			{
				openDoubleQuote = !openDoubleQuote;
			}

			// Not in a String
			if (openDoubleQuote == false)
			{
				if (i > 0 && i < buffer.length())
				{
					String singleCharSpace = buffer.substring(i - 1, i + 1);
					if (singleCharSpace.equals("\' \'") == false && ch == ' ')
					{
						buffer.deleteCharAt(i);
						CodeUtil.formatStatementForComparison(buffer);
						done = true;
					}
				}
			}
			i++;
			if (i == chars.length)
			{
				done = true;
			}
		}
		return buffer.toString();
	}

	/** @modelguid {541907D7-E62E-4466-99DB-5FF415E8073B} */
	public static List getBoundaries(Package aPackage)
	{
		List lList = new Vector();
		for (Iterator i = aPackage.getCompilationUnits(); i.hasNext();)
		{
			CompilationUnit lCompUnit = (CompilationUnit) i.next();
			if (lCompUnit.getMainType() != null)
			{
				String lStereotype = lCompUnit.getMainType().getProperty(
						"stereotype");
				if (lStereotype != null
						&& lStereotype.equalsIgnoreCase("boundary"))
				{
					lList.add(lCompUnit);
				}
			}
		}

		for (Iterator i = aPackage.getPackages(); i.hasNext();)
		{
			Package lPackage = (Package) i
					.next();
			lList.addAll(getBoundaries(lPackage));
		}

		return lList;
	}

	/** @modelguid {A6051ECA-DFC0-4D28-B650-87E5EE0154EF} */
	public static Iterator getBoundaries(String aPackageName)
	{
		return getBoundaries(new Package(aPackageName))
				.iterator();
	}

	/**
	 * Retrieve the callstack for this code element.
	 * 
	 * @param anIfStatement
	 * @return
	 */
	public static Stack getCallStack(CodeElement aCodeElement)
	{
		boolean done = false;

		Stack callStack = new Stack();

		Method call = null;
		if (aCodeElement instanceof AbstractStatement)
		{
			call = (Method) CodeUtil
					.getParentByType(aCodeElement, Method.class);
			callStack.push(call);
		}
		else if (aCodeElement instanceof Method)
		{
			call = (Method) aCodeElement;
			callStack.push(call);
		}
		else
		{ // code element is neither method nor statement
			done = true;
		}

		while (done == false)
		{
			if (call instanceof Method
					&& KeyWord.PUBLIC.equals(call.getScope()) == false)
			{
				// locate a public method to be invoked from the test class
				// otherwise, this path of execution will not be tested through
				// the generated algorithm

				call = (Method) CodeUtil.getInvokingMethod(call);
				if (call == null)
				{
					done = true;
				}
				else
				{
					callStack.push(call);
				}
			}
			else if (call instanceof Method
					&& KeyWord.PUBLIC.equals(call.getScope()) == true)
			{
				done = true;
			}
		}

		// TODO Revert to natural discovery order for callStack
		Collections.reverse(callStack);

		return callStack;
	}

	public static Stack getCallStackForScan(Stack aCallStack)
	{
		Stack localCallStack = new Stack();

		Iterator m = aCallStack.iterator();
		while (m.hasNext())
		{
			Method callMethod = (Method) m.next();
			String signature = callMethod.getMethodSignature();
			localCallStack.push(signature);
		}

		return localCallStack;
	}

	/** @modelguid {133D20D5-4F0C-497E-8F5F-4BDF5725E056} */
	public static List getControllers(Package aPackage)
	{
		List lList = new Vector();
		for (Iterator i = aPackage.getCompilationUnits(); i.hasNext();)
		{
			CompilationUnit lCompUnit = (CompilationUnit) i.next();
			if (lCompUnit.getMainType() != null)
			{
				String lStereotype = lCompUnit.getMainType().getProperty(
						"stereotype");
				if (lStereotype != null
						&& lStereotype.equalsIgnoreCase("control"))
				{
					lList.add(lCompUnit);
				}
			}
		}

		for (Iterator i = aPackage.getPackages(); i.hasNext();)
		{
			Package lPackage = (Package) i
					.next();
			lList.addAll(getControllers(lPackage));
		}

		return lList;
	}

	/** @modelguid {7E1A75E8-E023-4BCD-B907-2528822E91A2} */
	public static Iterator getControllers(String aPackageName)
	{
		return getControllers(new Package(aPackageName))
				.iterator();
	}

	/** @modelguid {69451790-8455-43BD-A831-B0B110025F0B} */
	public static List getControllersAndBoundaries(
			Package aPackage)
	{
		List lList = new ArrayList();
		for (Iterator i = aPackage.getCompilationUnits(); i.hasNext();)
		{
			CompilationUnit lCompUnit = (CompilationUnit) i.next();
			if (lCompUnit.getMainType() != null)
			{
				String lStereotype = lCompUnit.getMainType().getProperty(
						"stereotype");
				if (lStereotype != null
						&& (lStereotype.equalsIgnoreCase("control") || lStereotype
								.equalsIgnoreCase("boundary")))
				{
					lList.add(lCompUnit);
				}
			}
		}

		for (Iterator i = aPackage.getPackages(); i.hasNext();)
		{
			Package lPackage = (Package) i
					.next();
			lList.addAll(getControllersAndBoundaries(lPackage));
		}

		return lList;
	}

	public static Iterator getControllersAndBoundaries(
			String aDirectoryOrPackageName)
	{
		return getControllersAndBoundaries(
				new Package(aDirectoryOrPackageName))
				.iterator();
	}

	/** @modelguid {69451790-8455-43BD-A831-B0B110025F0B} */
	public static HashMap getDirectoryClasses(Package aPackage)
	{
		HashMap lList = new HashMap();
		for (Iterator i = aPackage.getCompilationUnits(); i.hasNext();)
		{
			CompilationUnit lCompUnit = (CompilationUnit) i.next();
			if (lCompUnit.getMainType() != null)
			{
				lList.put(lCompUnit.getMainType().getQualifiedName(), lCompUnit
						.getMainType());
			}
		}

		for (Iterator i = aPackage.getPackages(); i.hasNext();)
		{
			Package lPackage = (Package) i
					.next();
			lList.putAll(getDirectoryClasses(lPackage));
		}

		return lList;
	}

	public static List getDirectoryClasses(String aDirectoryName)
	{
		List lList = new Vector();
		File lDirectory = new File(aDirectoryName);
		if (lDirectory.exists() && lDirectory.isDirectory())
		{
			File[] lFiles = lDirectory.listFiles(new FilenameFilter()
			{
				public boolean accept(File dir, String name)
				{
					return name.endsWith(".java");
				}
			});
			for (int i = 0; i < lFiles.length; i++)
			{
				File lFile = lFiles[i];
				try
				{
					CompilationUnit lCompUnit = CodeParser.getParser()
							.parseFile(lFile.getAbsolutePath());
					if (lCompUnit.getMainType() != null)
					{
						lList.add(lCompUnit);
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			File[] lDirs = lDirectory.listFiles(new FileFilter()
			{
				public boolean accept(File pathname)
				{
					return pathname.isDirectory();
				}
			});
			for (int i = 0; i < lDirs.length; i++)
			{
				File lDir = lDirs[i];
				lList.addAll(getDirectoryClasses(lDir.getAbsolutePath()));
			}
		}
		return lList;
	}

	public static List getDirectoryControllersAndBoundaries(
			String aDirectoryName)
	{
		List<CompilationUnit> lList = new ArrayList<CompilationUnit>();
		File lDirectory = new File(aDirectoryName);
		if (lDirectory.exists() && lDirectory.isDirectory())
		{
			File[] lFiles = lDirectory.listFiles(new FilenameFilter()
			{
				public boolean accept(File dir, String name)
				{
					return name.endsWith(KeyWord.JAVA_EXTENSION);
				}
			});
			for (int i = 0; i < lFiles.length; i++)
			{
				File lFile = lFiles[i];
				try
				{
					CompilationUnit lCompUnit = CodeParser.getParser()
							.parseFile(lFile.getAbsolutePath());
					if (lCompUnit.getMainType() != null)
					{
						String lStereotype = lCompUnit.getMainType()
								.getProperty("stereotype");
						if (lStereotype != null
								&& (lStereotype.equalsIgnoreCase("control") || lStereotype
										.equalsIgnoreCase("boundary")))
						{
							lList.add(lCompUnit);
						}
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			File[] lDirs = lDirectory.listFiles(new FileFilter()
			{
				public boolean accept(File pathname)
				{
					return pathname.isDirectory();
				}
			});
			for (int i = 0; i < lDirs.length; i++)
			{
				File lDir = lDirs[i];
				lList.addAll(getDirectoryControllersAndBoundaries(lDir
						.getAbsolutePath()));
			}
		}
		return lList;
	}

	/**
	 * Locates the invoking method for a method in its parent class
	 * 
	 * @param aMethod
	 * @return the invoking method
	 */
	public static Method getInvokingMethod(Method aMethod)
	{
		// TODO Support the discovery of multiple invocations of this method

		Method theParentMethod = null;
		// Search for uses of this private method in the parent class
		Class parentClass = (Class) CodeUtil.getParentByType(aMethod,
				Class.class);
		List methods = parentClass.getMethods();

		boolean done = false;

		// search through each method in the parent class
		int methodLen = methods.size();
		for (int i = 0; i < methodLen && done == false; i++)
		{
			Method myMethod = (Method) methods.get(i);
			Iterator thisExpressions = myMethod.getProperties("ThisExpression");
			while (thisExpressions.hasNext() && done == false)
			{
				String thisExpressionInvocations = (String) thisExpressions
						.next();
				/*
				 * if (KeyWord.PRIVATE.equals(myMethod.getScope())) {
				 * theParentMethod = this.getInvokingMethod(myMethod); done =
				 * true; } else
				 */
				if (thisExpressionInvocations.equals(aMethod.getName()))
				{
					theParentMethod = myMethod;
					done = true; // TODO short term measure
				}
			}
		}
		return theParentMethod;
	}

	/**
	 * @param aCodeElement
	 * @return
	 */
	public static CodeElement getParentByType(CodeElement aCodeElement,
			java.lang.Class aClass)
	{
		CodeElement parent = null;

		if (aCodeElement.getClass().getName().equals(aClass.getName()))
		{
			parent = aCodeElement;
		}
		else
		{
			parent = CodeUtil.getParentByType(aCodeElement.getParent(), aClass);
		}

		return parent;
	}

	/**
	 * @return
	 */
	public static List getParentTypes(Type aType)
	{
		List theParents = new ArrayList();
		if (aType != null)
		{
			if (aType instanceof Class)
			{
				Class myClass = (Class) aType;

				String extendsClass = myClass.getExtendsClass();
				if (extendsClass != null)
				{
					theParents.add(myClass.getExtendsClass());
				}

				Iterator i = myClass.getImplements();
				while (i.hasNext())
				{
					String interf = (String) i.next();
					theParents.add(interf);
				}
			}
			else if (aType instanceof Interface)
			{
				Interface myInterface = (Interface) aType;
				Iterator i = myInterface.getExtends();
				while (i.hasNext())
				{
					theParents.add(i.next());
				}
			}
		}
		return theParents;
	}

	/**
	 * @param class1
	 * @param command_interface_name
	 */
	public static boolean isInstance(Type aType, String anInstanceCheckClassName)
	{
		boolean isInstance = false;

		List parentTypes = CodeUtil.getParentTypes(aType);

		int len = parentTypes.size();
		for (int i = 0; i < len && isInstance == false; i++)
		{
			String parentType = (String) parentTypes.get(i);
			if (parentType.equals(anInstanceCheckClassName))
			{
				isInstance = true;
				break;
			}
		}

		if (isInstance == false)
		{
			Iterator i = aType.getCompilationUnit().getImports();
			while (i.hasNext())
			{
				ImportDeclaration importDec = (ImportDeclaration) i.next();
				if (importDec.isOnDemand() == false)
				{
					if (importDec.getName().equals(anInstanceCheckClassName))
					{
						isInstance = true;
						break;
					}

				}
			}
		}

		return isInstance;
	}

	public static Stack parseCallStack(String callStackString, Map aSignatureMap)
	{
		Stack localCallStack = new Stack();

		if (callStackString != null)
		{
			String CALLSTACK_DELIM = "|";
			StringTokenizer t = new StringTokenizer(callStackString,
					CALLSTACK_DELIM);

			while (t.hasMoreTokens())
			{
				String docCallId = (String) t.nextElement();
				String docCall = (String) aSignatureMap.get(docCallId);
				localCallStack.push(docCall);
			}
		}

		return localCallStack;
	}

	public static String parseLongTagComment(String aTagName,
			JavaComment comment)
	{
		List tags = parseLongTagComments(aTagName, comment);
		String tagValue = null;
		if (tags.size() > 0)
		{
			tagValue = (String) tags.get(0);
		}

		return tagValue;
	}

	public static List parseLongTagComments(String aTagName, JavaComment comment)
	{
		List comments = new ArrayList();

		boolean done = false;

		int currentIndex = 0;

		if (comment != null && comment.toString() != null)
		{
			StringBuffer buffer = new StringBuffer();
			buffer.append(comment.toString());

			while (done == false)
			{
				int beginIndex = buffer.indexOf(aTagName, currentIndex);

				int endIndex = -1;

				if (beginIndex != -1)
				{
					beginIndex += aTagName.length();
					endIndex = buffer.indexOf("* @", beginIndex);

					if (endIndex == -1)
					{
						endIndex = buffer.indexOf("*/");
					}
				}

				if (beginIndex != -1 && endIndex != -1)
				{
					String tagValue = buffer.substring(beginIndex, endIndex)
							.trim();
					StringBuffer tagBuffer = new StringBuffer(tagValue);
					TextUtil.removeStrings(tagBuffer, KeyWord.NEWLINE);
					currentIndex = endIndex;
					comments.add(tagBuffer.toString());
				}
				else
				{
					done = true;
				}
			}
		}

		return comments;
	}

	public static CompilationUnit resolveAttributeType(String attrType,
			Class anEntity)
	{
		CompilationUnit compUnit = anEntity.getCompilationUnit();

		boolean isQualified = attrType.indexOf(".") > -1;

		CompilationUnit qualifiedType = null;

		List onDemand = new ArrayList();
		if (isQualified == true)
		{
			try
			{
				qualifiedType = CodeParser.getParser().parseClass(attrType);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			Iterator i = compUnit.getImports();
			while (i.hasNext())
			{
				ImportDeclaration importDec = (ImportDeclaration) i.next();
				if (importDec.isOnDemand() == false)
				{
					String name = importDec.getName();
					name = name.substring(name.lastIndexOf("."), name.length());
					if (name.equals(attrType))
					{
						try
						{
							qualifiedType = CodeParser.getParser().parseClass(
									importDec.getName());
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				}
				else
				{
					onDemand.add(importDec);
				}
			}
		}

		if (qualifiedType == null)
		{
			// see if type is in existing package
			String currPackageType = anEntity.getPackage().getName() + "."
					+ attrType;
			try
			{
				compUnit = CodeParser.getParser().parseClass(currPackageType);
				if (compUnit != null)
				{
					qualifiedType = compUnit;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			// search for imports that are onDemand
		}

		return qualifiedType;
	}

	public List getClasses(String pckgname)
	{
		// TODO Improve logic / remove catching NullPointerException
		List<java.lang.Class> classes = new ArrayList<java.lang.Class>();
		try
		{
			// Get a File object for the package
			File directory = null;
			try
			{
				ClassLoader cld = Thread.currentThread()
						.getContextClassLoader();
				if (cld == null)
				{
					throw new ClassNotFoundException("Can't get class loader.");
				}
				String path = pckgname.replace('.', '/');
				java.net.URL resource = cld.getResource(path);
				if (resource == null)
				{
					throw new ClassNotFoundException("No resource for " + path);
				}
				directory = new File(resource.getFile());
			}
			catch (NullPointerException x)
			{
				throw new ClassNotFoundException(pckgname + " (" + directory
						+ ") does not appear to be a valid package");
			}
			if (directory.exists())
			{
				// Get the list of the files contained in the package
				String[] files = directory.list();
				for (int i = 0; i < files.length; i++)
				{
					// we are only interested in .class files
					if (files[i].endsWith(".class"))
					{
						// removes the .class extension
						java.lang.Class clazz = java.lang.Class
								.forName(pckgname
										+ '.'
										+ files[i].substring(0, files[i]
												.length() - 6));
						classes.add(clazz);
					}
				}
			}
			else
			{
				throw new ClassNotFoundException(pckgname
						+ " does not appear to be a valid package");
			}
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return classes;
	}

}

package mojo.tools.code;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import mojo.tools.code.KeyWord;
import mojo.tools.code.parsers.CodeParser;
import mojo.tools.code.parsers.ICodeParser;

/**
 * This class represents a Java package.
 * 
 */
public class Package
{
	private String name;

	/**
	 * Creates a Java package with the given name.
	 * 
	 */
	public Package(String aPackageName)
	{
		name = aPackageName;
	}

	private List getDirectories()
	{
		List lDirectories = new ArrayList();
		String lPackageDir = "";
		if (name != null)
		{
			lPackageDir = name.replace('.', '/');
		}

		String lDirectory = "..\\..\\..\\app\\src\\";

		File lDir = new File(lDirectory + lPackageDir);
		if (lDir.exists() && lDir.isDirectory())
		{
			lDirectories.add(lDir);
		}

		return lDirectories;
	}

	/**
	 * Returns the name of the package.
	 * 
	 * @return the name of the package
	 * @modelguid {9BC25AB9-41C5-4290-9273-439B19BDDF34}
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Returns the list of sub-packages under this package.
	 * 
	 * @return Iterator to the list of sub-packages
	 * @modelguid {A1173FC6-F51E-4130-9790-2E6FF44004ED}
	 */
	public Iterator getPackages()
	{
		Map lPackages = new TreeMap();
		for (Iterator i = getDirectories().iterator(); i.hasNext();)
		{
			File lDir = (File) i.next();
			if (lDir.isDirectory())
			{
				File[] lFiles = lDir.listFiles();
				for (int j = 0; j < lFiles.length; j++)
				{
					File lFile = lFiles[j];
					if (lFile.isDirectory())
					{
						String lNewName = lFile.getName();
						if (getName().length() > 0)
						{
							lNewName = getName() + '.' + lFile.getName();
						}
						lPackages.put(lNewName, new Package(lNewName));
					}
				}
			}
		}
		return lPackages.values().iterator();
	}

	/**
	 * Returns the list of compilation units in this package.
	 * 
	 * @return Iterator to this list of compilation units
	 * @modelguid {793C5EB4-9B59-4E46-936E-02D83F4F82D6}
	 */
	public Iterator getCompilationUnits()
	{
		ICodeParser parser = CodeParser.getParser();
		parser.setParseStatements(false);

		Map lCUnits = new TreeMap();
		for (Iterator i = getDirectories().iterator(); i.hasNext();)
		{
			File lDir = (File) i.next();
			if (lDir.isDirectory())
			{
				File[] lFiles = lDir.listFiles();
				for (int j = 0; j < lFiles.length; j++)
				{
					File lFile = lFiles[j];
					if (lFile.isFile() && lFile.getName().endsWith(KeyWord.JAVA_EXTENSION))
					{
						try
						{
							String lClassName = lFile.getName().substring(0, lFile.getName().length() - 5);
							String lFilename = lClassName;
							StringBuffer buffer = new StringBuffer();
							if (getName() != null)
							{
								buffer.append(name);
								buffer.append(KeyWord.PERIOD);
								buffer.append(lClassName);
								lFilename = buffer.toString();
							}
							CompilationUnit lCUnit = parser.parseClass(lFilename);
							if (lCUnit != null)
							{
								lCUnits.put(lFilename, lCUnit);
							}
						}
						catch (Exception e)
						{
							System.out.println("Error on file: " + lFile.getAbsolutePath());
							e.printStackTrace();
						}
					}
				}
			}
		}
		return lCUnits.values().iterator();
	}

	/**
	 * Returns the compilation unit with the given name.
	 * 
	 * @param String
	 *            name of the compilation unit
	 * @return the compilation unit with the given name in this package
	 */
	public CompilationUnit getCompilationUnit(final String aClassname)
	{
		try
		{
			return CodeParser.getParser().parseClass(getName() + "." + aClassname);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns the name of the package that is the parent of this package. If this is the default
	 * package, then null is returned.
	 * 
	 * @return the name of the parent package
	 */
	public String getParentPackageName()
	{
		String lName = getName();
		if (lName.equals(""))
		{
			return null;
		}
		int lIndex = lName.indexOf(".");
		if (lIndex == -1)
		{
			return "";
		}
		else
		{
			return lName.substring(0, lIndex);
		}
	}

	/**
	 * Returns the package representing the parent of this package. If this is the default package,
	 * null is returned.
	 * 
	 * @return the parent package
	 * @modelguid {2B3BD2EA-7362-4E7D-8D8F-22A2C0F86763}
	 */
	public Package getParentPackage()
	{
		String lParentName = getParentPackageName();
		if (lParentName == null)
		{
			return null;
		}
		else
		{
			return new Package(lParentName);
		}
	}

	/**
	 * Returns the Java code representing this package.
	 * 
	 * @return String representation of this package
	 */
	public String toString()
	{
		StringBuffer lText = new StringBuffer();
		lText.append(KeyWord.PACKAGE).append(getName()).append(KeyWord.SEMICOLON).append(KeyWord.NEWLINE);
		return lText.toString();
	}

	public List resolveCompilationUnits(String packageName)
	{
		String dir = this.resolveParentDirectory();

		List compUnits = new ArrayList();

		File dirRoot = new File(dir);
		File[] srcFiles = dirRoot.listFiles(new JavaFileFilter());

		for (int i = 0; i < srcFiles.length; i++)
		{
			// CompilationUnit compUnit = CodeParser.getParser().parseClass(aClassName)
		}

		return compUnits;
	}

	class JavaFileFilter implements FileFilter
	{
		public boolean accept(File pathname)
		{
			boolean accept = false;
			if (pathname.getAbsolutePath().endsWith(".java"))
			{
				accept = true;
			}
			return accept;
		}

	}

	private String resolveParentDirectory()
	{
		String viewDir = System.getProperty("view.dir");

		if (viewDir != null && !viewDir.endsWith("/") && !viewDir.endsWith("\\"))
		{
			viewDir += "/";
		}

		String dir = viewDir + System.getProperty("source.path");

		if (dir != null && !dir.endsWith("/") && !dir.endsWith("\\"))
		{
			dir += "/";
		}

		return dir;
	}
}

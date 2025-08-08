package mojo.tools.code.audit.check.imports;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import mojo.km.config.AuditCheckProperties;
import mojo.km.config.PackageControlProperties;
import mojo.km.config.PackageGuardProperties;
import mojo.tools.code.Attribute;
import mojo.tools.code.CodeElement;
import mojo.tools.code.CodeUtil;
import mojo.tools.code.CompilationUnit;
import mojo.tools.code.ImportDeclaration;
import mojo.tools.code.KeyWord;
import mojo.tools.code.Method;
import mojo.tools.code.Parameter;
import mojo.tools.code.VariableDeclarationStatement;
import mojo.tools.code.audit.AuditVisitor;
import mojo.tools.code.audit.Check;
import mojo.tools.code.audit.CodeElementTypes;

/**
 * Check that controls what packages can be imported and utilized in each package. It is helpful for
 * ensuring that application layering is not violated.
 * 
 * All packages must be allowed explicitly. In other words, the partitioning defaults to disallowed.
 * 
 * @author Jim Fisher
 */
public class PackageControlCheck extends Check
{
	private static final String CONFIG_FILE = "configFile";

	private PackageControlProperties pProps;

	private static final String PARTITION_CONTROL_DISALLOWED = "partition.control.disallowed";

	/**
	 * 
	 */
	public PackageControlCheck(AuditCheckProperties aCheckProps)
	{
		super(aCheckProps);
	}

	public void auditComponent(AuditVisitor aVisitor, CodeElement aCodeElement)
	{
		// String myPackage;

		// setup
		this.init();

		CompilationUnit thisUnit = (CompilationUnit) CodeUtil.getParentByType(aCodeElement, CompilationUnit.class);
		String myPackage = thisUnit.getPackageName();

		List guardProps = this.getPackageGuards(myPackage);

		Iterator i = guardProps.iterator();
		while (i.hasNext())
		{
			PackageGuardProperties gProps = (PackageGuardProperties) i.next();
			if (gProps != null)
			{
				String packageViolation = null;
				// call the appropriate validation method
				if (aCodeElement instanceof ImportDeclaration)
				{
					packageViolation = this.validate(gProps, (ImportDeclaration) aCodeElement);
				}
				else if (aCodeElement instanceof Method)
				{
					String returnType = ((Method) aCodeElement).getReturnType();
					packageViolation = this.validate(gProps, returnType);
				}
				else if (aCodeElement instanceof VariableDeclarationStatement)
				{
					String type = ((VariableDeclarationStatement) aCodeElement).getType();					
					packageViolation = this.validate(gProps, type);
				}
				else if (aCodeElement instanceof Parameter)
				{
					String type = ((Parameter) aCodeElement).getType();
					packageViolation = this.validate(gProps, type);
				}
				else if (aCodeElement instanceof Attribute)
				{
					String type = ((Attribute) aCodeElement).getType();
					packageViolation = this.validate(gProps, type);
				}

				if (packageViolation != null)
				{
					String lineNo = String.valueOf(aCodeElement.getBeginLineNumber());
					Object[] parms = new Object[] { KeyWord.SINGLE_QUOTE + myPackage + KeyWord.SINGLE_QUOTE,
							KeyWord.SINGLE_QUOTE + packageViolation + KeyWord.SINGLE_QUOTE };
					this.log(aVisitor, lineNo, PARTITION_CONTROL_DISALLOWED, parms);
				}
				/*
				 * Iterator p = props.getPackages().iterator(); while (p.hasNext()) { String
				 * packageName = (String) p.next(); System.out.println("check package: " +
				 * packageName); }
				 */
			}
		}
	}

	/**
	 * @param myPackage
	 * @return
	 */
	private List getPackageGuards(String myPackage)
	{
		List guardedProps = new ArrayList();
		Set guarded = pProps.getPackages();

		Iterator i = guarded.iterator();
		while (i.hasNext())
		{
			String guardedPackage = (String) i.next();
			if (myPackage.equals(guardedPackage) || myPackage.startsWith(guardedPackage + "."))
			{
				PackageGuardProperties gProps = pProps.getPackageGuard(guardedPackage);
				guardedProps.add(gProps);
			}
		}

		return guardedProps;
	}

	/**
	 * @param visitor
	 * @param codeElement
	 */
	private String validate(PackageGuardProperties aGProps, String aTypeDecl)
	{
		String violation = null;
		if (aTypeDecl != null && aTypeDecl.indexOf('.') != -1)
		{
			violation = this.computeValid(aGProps, aTypeDecl);
		}

		return violation;
	}

	private String validate(PackageGuardProperties aGProps, ImportDeclaration anImport)
	{
		String imp = anImport.toString();
		String violation = this.computeValid(aGProps, imp);

		return violation;
	}

	private String computeValid(PackageGuardProperties aGProps, String aPackage)
	{
		Set allows = aGProps.getAllowGuards();
		Set disallows = aGProps.getDisallowGuards();

		Iterator d = disallows.iterator();
		String violation = this.wildCardFind(d, aPackage);

		// for strict package control, check allows
		if (violation == null && aGProps.isStrict() == true)
		{
			// ensure only allows packages are utilized
			/*
			 * if(allows.contains(aPackage) == false) { valid = false; }
			 */
		}
		return violation;
	}

	private String wildCardFind(Iterator i, String aPackage)
	{
		String violation = null;
		while (i.hasNext() && violation == null)
		{
			String guard = (String) i.next();
			if (aPackage.equals(guard) || aPackage.startsWith(guard + "."))
			{
				violation = guard;
			}
		}
		return violation;
	}

	public String[] getCodeElementTypes()
	{
		return new String[] { CodeElementTypes.IMPORT_DECLARATION, CodeElementTypes.VARIABLE_DECLARATION_STATEMENT,
				CodeElementTypes.METHOD, CodeElementTypes.PARAMETER, CodeElementTypes.ATTRIBUTE };
	}

	public void init()
	{
		String configFile = this.checkProps.getProperty(CONFIG_FILE);
		this.pProps = PackageControlProperties.getInstance(configFile);
	}
}

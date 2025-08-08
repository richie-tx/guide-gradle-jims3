package mojo.tools.code.parsers.eclipse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import mojo.naming.CodeScanConstants;
import mojo.tools.code.AbstractStatement;
import mojo.tools.code.Attribute;
import mojo.tools.code.CodeElement;

import mojo.tools.code.Interface;
import mojo.tools.code.JavaComment;
import mojo.km.logging.LogUtil;
import mojo.tools.code.KeyWord;
import mojo.tools.code.Method;
import mojo.tools.code.Parameter;
import mojo.tools.code.Type;
import mojo.tools.code.Class;

import org.apache.log4j.Level;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.QualifiedType;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

public class EclipseParser implements mojo.tools.code.parsers.ICodeParser
{

	private CompilationUnit ecpUnit;

	private ASTParser parser;

	private boolean parseStatements;

	private char[] sourceBuffer;

	private StringBuffer sourceStringBuffer;

	private String fileName;

	public EclipseParser()
	{
		this.parser = ASTParser.newParser(AST.JLS3);
	}

	private mojo.tools.code.CompilationUnit buildUnit()
	{
		mojo.tools.code.CompilationUnit myCompUnit = null;

		// options setup intentionally put here, the options seem to get reset with each run
		Hashtable<String, String> options = JavaCore.getOptions();
		options.put(JavaCore.COMPILER_COMPLIANCE, "1.5");
		options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_5);
		options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_5);
		parser.setCompilerOptions(options);

		parser.setSource(this.sourceBuffer);

		this.ecpUnit = (CompilationUnit) this.parser.createAST(null);

		if (this.ecpUnit != null)
		{
			org.eclipse.jdt.core.dom.Message[] messages = this.ecpUnit.getMessages();
			if (messages != null && messages.length > 0)
			{
				LogUtil.log(Level.ERROR, "Errors reading Java file: " + this.fileName);
				for (int i = 0; i < messages.length; i++)
				{
					String msg = "Pos " + messages[i].getStartPosition() + ": " + messages[i].getMessage();
					LogUtil.log(Level.ERROR, msg);
				}
				LogUtil.log(Level.ERROR, "----------------------------");
			}
			myCompUnit = new mojo.tools.code.CompilationUnit();

			if (this.ecpUnit.getPackage() != null)
			{
				myCompUnit.setPackageName(this.ecpUnit.getPackage().getName().toString());
			}

			this.parseImports(ecpUnit, myCompUnit);

			if (this.ecpUnit.types() != null)
			{
				for (Iterator i = this.ecpUnit.types().iterator(); i.hasNext();)
				{
					Object obj = i.next();
					if (obj instanceof TypeDeclaration)
					{
						TypeDeclaration ecpType = (TypeDeclaration) obj;

						Type myMojoType = parseType(ecpType);

						myCompUnit.addType(myMojoType);
					}
				}
			}
		}

		return myCompUnit;
	}

	private List<Attribute> parseAttributes(FieldDeclaration aField)
	{
		String type = this.parseBodyText(aField.getType());
		List<Attribute> attrs = new ArrayList<Attribute>();
		if (aField.fragments() != null)
		{
			Iterator i = aField.fragments().iterator();
			while (i.hasNext())
			{
				VariableDeclarationFragment fragment = (VariableDeclarationFragment) i.next();
				String lAttrName = this.parseBodyText(fragment.getName());
				Attribute attr = new Attribute(type, lAttrName);

				this.setBoundaries(fragment, attr);

				for (int j = fragment.getExtraDimensions(); j > 0; j--)
				{
					type += KeyWord.ARRAY_BRACKETS;
				}
				attr.setType(type);
				Expression expr = fragment.getInitializer();
				if (expr != null)
				{
					String initialValue = this.parseBodyText(expr);
					attr.setInitialValue(initialValue);
				}
				int lModifiers = aField.getModifiers();
				attr.setFinal(Modifier.isFinal(lModifiers));
				attr.setStatic(Modifier.isStatic(lModifiers));
				attr.setTransient(Modifier.isTransient(lModifiers));
				if (Modifier.isPrivate(lModifiers))
				{
					attr.setScope(KeyWord.PRIVATE);
				} else if (Modifier.isProtected(lModifiers))
				{
					attr.setScope(KeyWord.PROTECTED);
				} else if (Modifier.isPublic(lModifiers))
				{
					attr.setScope(KeyWord.PUBLIC);
				}
				attrs.add(attr);

				String comment = this.parseJavadocComment(aField);
				if (comment != null)
				{
					attr.setComment(new JavaComment(comment));
				}
			}
		}
		return attrs;
	}

	private mojo.tools.code.Block parseBlockStatement(CodeElement aParent, Block aStatement)
	{
		mojo.tools.code.Block myStatement = new mojo.tools.code.Block();

		this.setBoundaries(aStatement, myStatement);

		String myBlockBody = this.parseBodyText(aStatement);
		myStatement.setBody(myBlockBody);
		myStatement.setParent(aParent);
		Block myBlock = (Block) aStatement;
		List ecpStatements = myBlock.statements();

		Iterator i = ecpStatements.iterator();
		while (i.hasNext())
		{
			Statement ecpStatement = (Statement) i.next();
			AbstractStatement myChildStatement = this.parseStatement(myStatement, ecpStatement);
			((mojo.tools.code.Block) myStatement).addStatement(myChildStatement);
		}

		return myStatement;
	}

	private String parseBodyText(ASTNode node)
	{
		int start = node.getStartPosition();
		int length = node.getLength();

		return this.sourceStringBuffer.substring(start, start + length);
	}

	public mojo.tools.code.CompilationUnit parseClass(String aClassName) throws Exception
	{
		String lFilename = aClassName.replace('.', '/') + KeyWord.JAVA_EXTENSION;
		String lDirectory = System.getProperty("source.path");
	
		if (lDirectory != null && !lDirectory.endsWith("/") && !lDirectory.endsWith("\\"))
		{
			lDirectory += "/";
		}
		File lFile = new File(lDirectory + lFilename);
		if (!(lFile.exists() && lFile.isFile()))
		{
			String lAltPaths = System.getProperty("alternate.source.paths");
			
			if (lAltPaths != null)
			{
				StringTokenizer lAltDirs = new StringTokenizer(lAltPaths, ";");
				while (lAltDirs.hasMoreTokens())
				{
					String lAltDir = lAltDirs.nextToken();
					if (!lAltDir.endsWith("/") && !lAltDir.endsWith("\\"))
					{
						lAltDir += "/";
					}
					File lCandidateFile = new File(lAltDir + lFilename);
					if (lCandidateFile.exists() && lCandidateFile.isFile())
					{
						lDirectory = lAltDir;
						break;
					}
				}
			}
		}

		String fileName = lDirectory + lFilename;

		return this.parseFile(fileName);
	}

	private Class parseClass(TypeDeclaration aType)
	{
		String className = aType.getName().toString();
		Class myClass = new Class(className);

		this.setBoundaries(aType, myClass);

		// add modifiers
		int modifiers = aType.getModifiers();
		myClass.setAbstract(Modifier.isAbstract(modifiers));
		myClass.setFinal(Modifier.isFinal(modifiers));
		myClass.setStatic(Modifier.isStatic(modifiers));
		myClass.setStrictfp(Modifier.isStrictfp(modifiers));
		if (Modifier.isPrivate(modifiers))
		{
			myClass.setScope(KeyWord.PRIVATE);
		} else if (Modifier.isProtected(modifiers))
		{
			myClass.setScope(KeyWord.PROTECTED);
		} else if (Modifier.isPublic(modifiers))
		{
			myClass.setScope(KeyWord.PUBLIC);
		}
		if (aType.getSuperclassType() != null)
		{
			String typeString = this.extractTypeNameString(aType.getSuperclassType());
			myClass.setExtendsClass(typeString);
		}

		// add implemented interfaces
		List superInterfaces = aType.superInterfaceTypes();
		if (superInterfaces != null)
		{
			int len = superInterfaces.size();
			for (int i = 0; i < len; i++)
			{
				Object lSuperType = superInterfaces.get(i);
				myClass.addImplement(lSuperType.toString());
			}
		}

		// add body declarations
		Iterator k = aType.bodyDeclarations().iterator();
		while (k.hasNext())
		{
			BodyDeclaration bodyDec = (BodyDeclaration) k.next();
			if (bodyDec instanceof org.eclipse.jdt.core.dom.Initializer)
			{
				int length = bodyDec.getLength();
				char[] bodyChars = new char[length];
				System.arraycopy(this.sourceBuffer, 0, bodyChars, 0, length);
				String bodyDecl = String.valueOf(bodyChars);
				myClass.setInitializer(bodyDecl);
			}
		}

		// add field declarations
		FieldDeclaration[] lFields = aType.getFields();
		if (lFields != null && lFields.length > 0)
		{
			for (int i = 0; i < lFields.length; i++)
			{
				List<Attribute> lAttrs = parseAttributes(lFields[i]);
				for (Iterator<Attribute> j = lAttrs.iterator(); j.hasNext();)
				{
					myClass.addAttribute(j.next());
				}
			}
		}

		// add method declarations
		MethodDeclaration[] ecpMethods = aType.getMethods();
		if (ecpMethods != null && ecpMethods.length > 0)
		{
			for (int i = 0; i < ecpMethods.length; i++)
			{
				// int startIndex = ecpMethods[i].getStartPosition();
				Method myMethod = this.parseMethod(ecpMethods[i]);
				myClass.addMethod(myMethod);
			}
		}

		// add type declarations
		TypeDeclaration[] lTypes = aType.getTypes();
		if (lTypes != null && lTypes.length > 0)
		{
			for (int i = 0; i < lTypes.length; i++)
			{
				myClass.addInnerType(parseType(lTypes[i]));
			}
		}

		String comment = this.parseJavadocComment(aType);
		if (comment != null)
		{
			myClass.setComment(new JavaComment(comment));
		}

		return myClass;
	}

	private mojo.tools.code.Expression parseExpressionStatement(CodeElement aParent, ExpressionStatement aStatement)
	{
		mojo.tools.code.Expression myStatement = new mojo.tools.code.Expression();

		this.setBoundaries(aStatement, myStatement);

		myStatement.setBody(aStatement.toString());
		myStatement.setParent(aParent);

		ExpressionStatement exprStmt = (ExpressionStatement) aStatement;
		Expression expr = exprStmt.getExpression();
		if (expr instanceof MethodInvocation)
		{
			MethodInvocation mi = (MethodInvocation) expr;
			Method method = (Method) this.resolveMethod(myStatement);
			method.addPropertyValue(CodeScanConstants.METHOD_INVOCATION, mi.getName().toString());
		}

		return myStatement;
	}

	public mojo.tools.code.CompilationUnit parseFile(String aFileName) throws IOException
	{
		mojo.tools.code.CompilationUnit myCompUnit = null;

		File file = new File(aFileName);

		this.fileName = aFileName;

		if (file.exists())
		{
			int fileLength = (int) file.length();

			this.sourceBuffer = new char[fileLength];

			Reader in = new BufferedReader(new FileReader(file));
			try
			{
				in.read(this.sourceBuffer);
			} finally
			{
				in.close();
			}

			this.sourceStringBuffer = new StringBuffer(this.sourceBuffer.length);
			this.sourceStringBuffer.append(this.sourceBuffer);

			myCompUnit = this.buildUnit();

			if (myCompUnit != null)
			{
				myCompUnit.setFile(file);
			}
		}

		return myCompUnit;
	}

	private mojo.tools.code.IfStatement parseIfStatement(CodeElement aParent, IfStatement aStatement)
	{
		IfStatement ecpIfStatement = (IfStatement) aStatement;
		mojo.tools.code.IfStatement myStatement = new mojo.tools.code.IfStatement();

		this.setBoundaries(aStatement, myStatement);

		myStatement.setParent(aParent);
		mojo.tools.code.IfStatement myIfStatement = (mojo.tools.code.IfStatement) myStatement;

		String myBody = this.parseBodyText(aStatement);

		myIfStatement.setBody(myBody);

		Expression ecpExpression = ecpIfStatement.getExpression();

		String phraseText = this.parseBodyText(ecpExpression);
		// phraseText = this.stripNewLine(phraseText);

		mojo.tools.code.Expression myExpression = new mojo.tools.code.Expression();

		this.setBoundaries(ecpExpression, myExpression);

		myExpression.setBody(phraseText);

		myIfStatement.setExpression(myExpression);

		Statement ecpThenStatement = ecpIfStatement.getThenStatement();
		if (ecpThenStatement != null)
		{
			AbstractStatement myThenStatement = this.parseStatement(myIfStatement, ecpThenStatement);
			myIfStatement.setThenStatement(myThenStatement);
		}

		Statement ecpElseStatement = ecpIfStatement.getElseStatement();
		if (ecpElseStatement != null)
		{
			AbstractStatement myElseStatement = this.parseStatement(myIfStatement, ecpElseStatement);
			myIfStatement.setElseStatement(myElseStatement);
		}

		return myStatement;
	}

	/**
	 * @param ecpImport
	 */
	private mojo.tools.code.ImportDeclaration parseImport(ImportDeclaration ecpImport)
	{
		mojo.tools.code.ImportDeclaration myImport = new mojo.tools.code.ImportDeclaration();
		String importName = ecpImport.getName().toString();
		if (ecpImport.isOnDemand() == true)
		{
			importName += ".*";
		}
		myImport.setName(importName);
		myImport.setOnDemand(ecpImport.isOnDemand());
		this.setBoundaries(ecpImport, myImport);
		return myImport;
	}

	/**
	 * @param anEcpUnit
	 * @param aCompUnit
	 */
	private void parseImports(CompilationUnit anEcpUnit, mojo.tools.code.CompilationUnit aCompUnit)
	{
		if (this.ecpUnit.imports() != null)
		{
			Iterator i = this.ecpUnit.imports().iterator();
			while (i.hasNext())
			{
				ImportDeclaration ecpImport = (ImportDeclaration) i.next();
				mojo.tools.code.ImportDeclaration myImport = this.parseImport(ecpImport);
				aCompUnit.addImport(myImport);
			}
		}
	}

	private Interface parseInterface(TypeDeclaration aType)
	{
		String lClassName = aType.getName().toString();
		Interface myInterface = new Interface(lClassName);

		int lModifiers = aType.getModifiers();
		myInterface.setAbstract(Modifier.isAbstract(lModifiers));
		myInterface.setFinal(Modifier.isFinal(lModifiers));
		myInterface.setStatic(Modifier.isStatic(lModifiers));
		myInterface.setStrictfp(Modifier.isStrictfp(lModifiers));
		if (Modifier.isPrivate(lModifiers))
		{
			myInterface.setScope(KeyWord.PRIVATE);
		} else if (Modifier.isProtected(lModifiers))
		{
			myInterface.setScope(KeyWord.PROTECTED);
		} else if (Modifier.isPublic(lModifiers))
		{
			myInterface.setScope(KeyWord.PUBLIC);
		}
		List superInterfaces = aType.superInterfaceTypes();
		if (superInterfaces != null)
		{
			int len = superInterfaces.size();
			for (int i = 0; i < len; i++)
			{
				Object lSuperType = superInterfaces.get(i);
				myInterface.addExtends(lSuperType.toString());
			}
		}
		FieldDeclaration[] fields = aType.getFields();
		if (fields != null && fields.length > 0)
		{
			for (int i = 0; i < fields.length; i++)
			{
				List<Attribute> lAttrs = parseAttributes(fields[i]);
				for (Iterator<Attribute> j = lAttrs.iterator(); j.hasNext();)
				{
					myInterface.addAttribute(j.next());
				}
			}
		}
		MethodDeclaration[] ecpMethods = aType.getMethods();
		if (ecpMethods != null && ecpMethods.length > 0)
		{
			for (int i = 0; i < ecpMethods.length; i++)
			{
				Method myMethod = parseMethod(ecpMethods[i]);
				myInterface.addMethod(myMethod);
			}
		}
		TypeDeclaration[] lTypes = aType.getTypes();
		if (lTypes != null && lTypes.length > 0)
		{
			for (int i = 0; i < lTypes.length; i++)
			{
				myInterface.addInnerType(parseType(lTypes[i]));
			}
		}

		String comment = this.parseJavadocComment(aType);
		if (comment != null)
		{
			myInterface.setComment(new JavaComment(comment));
		}

		return myInterface;
	}

	private String parseJavadocComment(ASTNode aNode)
	{
		String comment = null;

		int start = aNode.getStartPosition();
		int length = aNode.getLength();

		String typeString = this.sourceStringBuffer.substring(start, start + length).trim();

		int commentIndex = typeString.indexOf("/**");

		if (commentIndex != -1)
		{
			int endCommentIndex = typeString.indexOf("*/", commentIndex);
			if (endCommentIndex != -1)
			{
				comment = typeString.substring(commentIndex, endCommentIndex + 2);
			}
		}
		return comment;
	}

	private Method parseMethod(MethodDeclaration aMethod)
	{
		String methodName = this.parseBodyText(aMethod.getName());
		Method myMethod = new Method(methodName);

		this.setBoundaries(aMethod, myMethod);

		// parse constructor
		myMethod.setConstructor(aMethod.isConstructor());
		if (myMethod.isConstructor())
		{
			myMethod.setReturnType("");
		}

		// parse modifiers
		int modifiers = aMethod.getModifiers();
		myMethod.setAbstract(Modifier.isAbstract(modifiers));
		myMethod.setFinal(Modifier.isFinal(modifiers));
		myMethod.setStatic(Modifier.isStatic(modifiers));
		myMethod.setSynchronized(Modifier.isSynchronized(modifiers));
		myMethod.setNative(Modifier.isNative(modifiers));
		myMethod.setStrictfp(Modifier.isStrictfp(modifiers));
		if (aMethod.getReturnType2() != null)
		{
			String returnType = this.parseBodyText(aMethod.getReturnType2());
			myMethod.setReturnType(returnType);
		}
		if (Modifier.isPrivate(modifiers))
		{
			myMethod.setScope(KeyWord.PRIVATE);
		} else if (Modifier.isProtected(modifiers))
		{
			myMethod.setScope(KeyWord.PROTECTED);
		} else if (Modifier.isPublic(modifiers))
		{
			myMethod.setScope(KeyWord.PUBLIC);
		}

		// parse thrown exceptions
		if (aMethod.thrownExceptions() != null)
		{
			for (Iterator i = aMethod.thrownExceptions().iterator(); i.hasNext();)
			{
				ASTNode node = (ASTNode) i.next();
				String throwsDecl = this.parseBodyText(node);
				myMethod.addThrows(throwsDecl);
			}
		}

		// parse parameters
		if (aMethod.parameters() != null)
		{
			for (Iterator i = aMethod.parameters().iterator(); i.hasNext();)
			{
				SingleVariableDeclaration aDecl = (SingleVariableDeclaration) i.next();
				String varDecl = this.parseBodyText(aDecl);
				String type = this.parseBodyText(aDecl.getType());
				String name = this.parseBodyText(aDecl.getName());
				if (varDecl.endsWith(KeyWord.ARRAY_BRACKETS))
				{
					type += KeyWord.ARRAY_BRACKETS;
				}
				Parameter param = new Parameter(type, name);

				this.setBoundaries(aDecl, param);

				param.setFinal(Modifier.isFinal(aDecl.getModifiers()));
				myMethod.addParameter(param);
			}
		}

		// parse method body
		String myBody = "";
		if (aMethod.getBody() != null)
		{
			myBody = this.parseBodyText(aMethod.getBody());
			myBody = myBody.substring(1, myBody.length() - 1);
			this.setBoundaries(aMethod, myMethod);

			// parse method block
			if (this.parseStatements == true)
			{
				mojo.tools.code.Block myBlock = this.parseBlockStatement(myMethod, aMethod.getBody());
				myBlock.setParent(myMethod);
				myMethod.setBlock(myBlock);
			}
		}
		myMethod.setBody(myBody);

		// parse method comment
		if (aMethod.getBody() != null)
		{
			int start = aMethod.getStartPosition();
			int end = aMethod.getBody().getStartPosition();
			int commentLength = end - start;
			char[] chars = new char[commentLength];
			System.arraycopy(this.sourceBuffer, start, chars, 0, commentLength);

			String comment = String.valueOf(chars);

			end = comment.indexOf("*/");

			if (end != -1)
			{
				comment = comment.substring(0, end + 2);
				myMethod.setComment(comment);
			}
		}

		return myMethod;
	}

	private AbstractStatement parseStatement(CodeElement aParent, Statement aStatement)
	{
		AbstractStatement myStatement;
		if (aStatement instanceof Block)
		{
			myStatement = this.parseBlockStatement(aParent, (Block) aStatement);
		} else if (aStatement instanceof IfStatement)
		{
			myStatement = this.parseIfStatement(aParent, (IfStatement) aStatement);
		} else if (aStatement instanceof WhileStatement)
		{
			myStatement = this.parseWhileStatement(aParent, (WhileStatement) aStatement);
		} else if (aStatement instanceof ExpressionStatement)
		{
			myStatement = this.parseExpressionStatement(aParent, (ExpressionStatement) aStatement);
		} else if (aStatement instanceof VariableDeclarationStatement)
		{
			myStatement = this.parseVariableDeclarationStatement(aParent, (VariableDeclarationStatement) aStatement);
		} else if (aStatement instanceof ThrowStatement)
		{
			myStatement = this.parseThrowStatement(aParent, (ThrowStatement) aStatement);
		} else
		{
			myStatement = new mojo.tools.code.Statement();
			this.setBoundaries(aStatement, myStatement);
			myStatement.setBody(aStatement.toString());
		}
		return myStatement;
	}

	private mojo.tools.code.ThrowStatement parseThrowStatement(CodeElement aParent, ThrowStatement aStatement)
	{
		ThrowStatement ecpThrowStatement = (ThrowStatement) aStatement;
		mojo.tools.code.ThrowStatement myStatement = new mojo.tools.code.ThrowStatement();
		this.setBoundaries(aStatement, myStatement);

		myStatement.setParent(aParent);

		String myBody = this.parseBodyText(aStatement);

		myStatement.setBody(myBody);

		Expression ecpExpression = ecpThrowStatement.getExpression();

		String phraseText = this.parseBodyText(ecpExpression);
		// phraseText = this.stripNewLine(phraseText);

		mojo.tools.code.Expression myExpression = new mojo.tools.code.Expression();

		this.setBoundaries(ecpExpression, myExpression);

		myExpression.setBody(phraseText);

		((mojo.tools.code.ThrowStatement) myStatement).setExpression(myExpression);
		return myStatement;
	}

	private Type parseType(TypeDeclaration aType)
	{
		if (aType.isInterface())
		{
			return parseInterface(aType);
		} else
		{
			return parseClass(aType);
		}
	}

	private mojo.tools.code.VariableDeclarationStatement parseVariableDeclarationStatement(CodeElement aParent,
			VariableDeclarationStatement aStatement)
	{
		mojo.tools.code.VariableDeclarationStatement myStatement = new mojo.tools.code.VariableDeclarationStatement();

		this.setBoundaries(aStatement, myStatement);

		myStatement.setBody(aStatement.toString());
		myStatement.setParent(aParent);

		VariableDeclarationStatement varStmt = (VariableDeclarationStatement) aStatement;

		String typeString = this.extractTypeNameString(varStmt.getType());
		myStatement.setType(typeString);

		List fragments = varStmt.fragments();
		Iterator f = fragments.iterator();
		while (f.hasNext())
		{
			VariableDeclarationFragment fragment = (VariableDeclarationFragment) f.next();

			myStatement.addName(fragment.getName().toString());

			Expression expr = fragment.getInitializer();
			if (expr != null && expr instanceof MethodInvocation)
			{
				MethodInvocation mi = (MethodInvocation) expr;

				Method method = (Method) this.resolveMethod(myStatement);
				method.addPropertyValue(CodeScanConstants.METHOD_INVOCATION, mi.getName().toString());
			}
		}
		return myStatement;
	}

	private String extractTypeNameString(org.eclipse.jdt.core.dom.Type aType)
	{
		String nameString = null;
		if (aType.isSimpleType())
		{
			org.eclipse.jdt.core.dom.Name name = ((SimpleType) aType).getName();
			nameString = name.getFullyQualifiedName();
		} else if (aType.isPrimitiveType())
		{
			nameString = ((PrimitiveType) aType).getPrimitiveTypeCode().toString();
		} else if (aType.isArrayType())
		{
			nameString = aType.toString();
		} else if (aType.isQualifiedType())
		{
			org.eclipse.jdt.core.dom.Name name = ((QualifiedType) aType).getName();
			nameString = name.getFullyQualifiedName();
		}
		return nameString;
	}

	/**
	 * @param parent
	 * @param statement
	 * @param buffer
	 * @return
	 */
	private AbstractStatement parseWhileStatement(CodeElement aParent, WhileStatement aStatement)
	{
		WhileStatement ecpWhileStatement = (WhileStatement) aStatement;
		Statement body = ecpWhileStatement.getBody();
		AbstractStatement myStatement = this.parseStatement(aParent, body);
		return myStatement;
	}

	/**
	 * @param exprStmt
	 * @return
	 */
	private CodeElement resolveMethod(CodeElement aCodeElement)
	{
		CodeElement node = aCodeElement.getParent();

		// TODO Handle other types of invocations

		if (node instanceof Method)
		{
			Method method = (Method) node;
			return method;
		} else
		{
			return this.resolveMethod(node);
		}
	}

	/**
	 * @param type
	 * @param myClass
	 */
	private void setBoundaries(ASTNode aNode, CodeElement aCodeElement)
	{
		int startPos = aNode.getStartPosition();
		int lineNo = this.ecpUnit.getLineNumber(startPos);

		aCodeElement.setBeginLineNumber(lineNo);
		aCodeElement.setLength(aNode.getLength());
	}

	/**
	 * @param theOptions
	 */
	public void setOptions(Map theOptions)
	{
		if (theOptions != null)
		{
			// TODO actual impl TBD
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.tools.code.parsers.ICodeParser#setParseStatements(boolean)
	 */
	public void setParseStatements(boolean aParseStatements)
	{
		this.parseStatements = aParseStatements;
	}
}

package mojo.tools.code.parsers.eclipse;

import java.util.Hashtable;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;

import mojo.tools.code.KeyWord;
import mojo.tools.code.parsers.ICodeFormatter;

/**
 * 
 * @author Jim Fisher
 */
public class EclipseFormatter implements ICodeFormatter
{
	private static final int INDENT_LEVEL = 0;

	private static final String LINE_WIDTH = "120";

	private static final String LINES_BEFORE_METHOD = "1";

	/** Creates a new instance of EclipseFormatter */
	public EclipseFormatter()
	{
	}

	public String format(String aSourceCode)
	{
		// Get the default options
		Hashtable<String, String> options = new Hashtable<String, String>();

		// Change the value of an option
		options.put(DefaultCodeFormatterConstants.FORMATTER_BRACE_POSITION_FOR_TYPE_DECLARATION,
				DefaultCodeFormatterConstants.NEXT_LINE);
		options.put(DefaultCodeFormatterConstants.FORMATTER_BRACE_POSITION_FOR_BLOCK, DefaultCodeFormatterConstants.NEXT_LINE);
		options.put(DefaultCodeFormatterConstants.FORMATTER_BRACE_POSITION_FOR_METHOD_DECLARATION,
				DefaultCodeFormatterConstants.NEXT_LINE);
		options.put(DefaultCodeFormatterConstants.FORMATTER_BRACE_POSITION_FOR_CONSTRUCTOR_DECLARATION,
				DefaultCodeFormatterConstants.NEXT_LINE);
		options.put(DefaultCodeFormatterConstants.FORMATTER_BRACE_POSITION_FOR_SWITCH, DefaultCodeFormatterConstants.NEXT_LINE);
		options.put(DefaultCodeFormatterConstants.FORMATTER_INSERT_NEW_LINE_BEFORE_FINALLY_IN_TRY_STATEMENT, JavaCore.INSERT);
		options.put(DefaultCodeFormatterConstants.FORMATTER_INSERT_NEW_LINE_BEFORE_ELSE_IN_IF_STATEMENT, JavaCore.INSERT);
		options.put(DefaultCodeFormatterConstants.FORMATTER_INSERT_NEW_LINE_BEFORE_CATCH_IN_TRY_STATEMENT, JavaCore.INSERT);
		options.put(DefaultCodeFormatterConstants.FORMATTER_BLANK_LINES_BEFORE_METHOD, LINES_BEFORE_METHOD);
		options.put(DefaultCodeFormatterConstants.FORMATTER_LINE_SPLIT, LINE_WIDTH);

		org.eclipse.jdt.core.formatter.CodeFormatter ecpFormatter = ToolFactory.createCodeFormatter(options);

		int kind = org.eclipse.jdt.core.formatter.CodeFormatter.K_COMPILATION_UNIT;

		int indentationLevel = KeyWord.INDENTION.length();

		TextEdit edit = ecpFormatter.format(kind, aSourceCode, 0, aSourceCode.length(), INDENT_LEVEL, KeyWord.NEWLINE);

		IDocument sourceDoc = new Document(aSourceCode);
		String formattedSource = null;
		try
		{
			edit.apply(sourceDoc);
			formattedSource = sourceDoc.get();
		}
		catch (Exception e)
		{
			System.out.println("*********************************");
			System.out.println(aSourceCode);
			System.out.println("*********************************");
			e.printStackTrace();
		}

		return formattedSource;
	}

}

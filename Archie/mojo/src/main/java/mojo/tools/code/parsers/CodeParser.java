package mojo.tools.code.parsers;

public class CodeParser
{
    private static ICodeParser parser;

    private static ICodeFormatter formatter;

    private CodeParser()
    {
    }

    public static ICodeParser getParser()
    {
        if (parser == null)
        {
            parser = new mojo.tools.code.parsers.eclipse.EclipseParser();
        }
        return parser;
    }

    public static ICodeFormatter getFormatter()
    {
        if (formatter == null)
        {
            formatter = new mojo.tools.code.parsers.eclipse.EclipseFormatter();
        }
        return formatter;
    }
}

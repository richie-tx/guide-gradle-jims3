package mojo.tools.code.parsers;

import java.util.Map;

import mojo.tools.code.CompilationUnit;

public interface ICodeParser
{
    CompilationUnit parseClass(String aClassName) throws Exception;

    CompilationUnit parseFile(String aFileName) throws Exception;
    
    void setOptions(Map theOptions);
    
    void setParseStatements(boolean aParseStatements);
}

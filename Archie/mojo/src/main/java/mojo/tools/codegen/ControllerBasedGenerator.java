package mojo.tools.codegen;

import java.util.*;
import mojo.tools.code.*;
import mojo.tools.code.parsers.CodeParser;
import mojo.tools.code.parsers.ICodeParser;
import mojo.km.config.MojoProperties;

/**
 * @modelguid {6B685BAD-DBA6-4E82-A87B-D8878803ED90}
 */
abstract public class ControllerBasedGenerator extends CodeScanAntTask
{
    /** @modelguid {D189031D-210F-4318-9113-530624BF37A9} */
    protected Type controllerClass;

    private static ICodeParser parser = CodeParser.getParser();

    public ControllerBasedGenerator()
    {
    }

    /** @modelguid {D8B75E41-A003-4D75-A0D7-F172BCB36B6C} */
    public ControllerBasedGenerator(String aClassname) throws Exception
    {   
        parser.setParseStatements(false);
        CompilationUnit compUnit = parser.parseClass(aClassname);
        Type type = compUnit.getMainType();
        this.init(type);        
    }

    /** @modelguid {06C2950B-13A1-40AE-A96B-2736D6B67031} */
    public ControllerBasedGenerator(Type aType) throws Exception
    {
        parser.setParseStatements(false);
        init(aType);
    }
    
    /** @modelguid {9A126AD6-07C1-4654-BE16-BA385137D25E} */
    public ControllerBasedGenerator(CompilationUnit aController) throws Exception
    {
        parser.setParseStatements(false);
        Type type = aController.getMainType();
        init(type);
    }
    
    private void init(Type aType) throws Exception
    {
        String lStereotype = aType.getProperty("stereotype");
        if (lStereotype == null || !(lStereotype.equalsIgnoreCase("control") || lStereotype.equalsIgnoreCase("boundary")))
        {
            throw new Exception("Given type is not stereotyped as a controller.");
        }
        controllerClass = aType;
    }    

    /** @modelguid {B0976530-EA82-49A3-88BB-0BB8CDA65696} */
    public void processController()
    {
        List methods = controllerClass.getMethods();
        int methodLen = methods.size();
        for(int i=0;i<methodLen;i++)
        {
            try
            {
                Method lMethod = (Method) methods.get(i);
                String lStereotype = lMethod.getProperty("stereotype");
                if (lStereotype != null && lStereotype.equals("design"))
                {
                    processDesignMethod(lMethod);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    /** @modelguid {B63DBBD2-BA8E-4197-BED1-62A1D95E134D} */
    abstract protected void processDesignMethod(Method aMethod);

    /*
     * (non-Javadoc)
     * 
     * @see mojo.tools.codegen.CodeScanAntTask#executeScan(mojo.tools.code.CompilationUnit)
     */
    public void executeScan(CompilationUnit aUnit)
    {
        // TODO Auto-generated method stub
        controllerClass = aUnit.getMainType();
        String lStereotype = controllerClass.getProperty("stereotype");
        if (lStereotype == null || !(lStereotype.equalsIgnoreCase("control") || lStereotype.equalsIgnoreCase("boundary")))
        {
            return;
        }
        processController();

    }

    public void executeSave()
    {
        MojoProperties.getInstance().saveProperties();
    }

}

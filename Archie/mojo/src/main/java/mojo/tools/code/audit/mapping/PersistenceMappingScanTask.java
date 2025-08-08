/*
 * Created on May 9, 2006
 *
 */
package mojo.tools.code.audit.mapping;

import java.io.File;

import mojo.km.context.ContextManager;
import mojo.tools.code.CompilationUnit;
import mojo.tools.code.parsers.CodeParser;
import mojo.tools.code.parsers.ICodeParser;
import mojo.tools.codegen.CodeScanAntTask;

/**
 * @author Jim Fisher
 *  
 */
public class PersistenceMappingScanTask extends CodeScanAntTask
{
    private static final String SOURCE_DIR = "c:/views/maint_frame_dev/app/src/";

    private static final String FILE_NAME = "pd/juvenilecase/referral/JUVFeePayment.java";

    private static final String OUTPUT_DIR = "c:/temp/audit/mapping/";

    private static final String OUTPUT_FILE = "mappingaudit_report.xml";

    //private static final String ANT_HOME = "c:/views/rsa/arch/build/ant";

    private static final String CONFIG_FILE = "multisource.xml";

    private MappingValidatorUtil validatorUtil;

    /**
     *  
     */
    public PersistenceMappingScanTask()
    {
        System.out.println("Constructor");
        File outputFile = new File(OUTPUT_DIR, OUTPUT_FILE);
        this.validatorUtil = new MappingValidatorUtil(outputFile);
        try
        {
            System.setProperty("mojo.config", "multisource.xml");
            ContextManager instance = ContextManager.currentContext();
            String sessionId = "" + System.currentTimeMillis();
            instance.setCurrentSessionID(sessionId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw (RuntimeException) e;
        }
    }

    public static void main(String[] args)
    {
        System.out.println("main");
        PersistenceMappingScanTask scanTask = new PersistenceMappingScanTask();
        scanTask.setConfigFile(CONFIG_FILE);
        System.setProperty("mojo.config", "multisource.xml");

        scanTask.setSourcePath(SOURCE_DIR);

        ICodeParser parser = CodeParser.getParser();
        try
        {
            CompilationUnit unit = parser.parseFile(SOURCE_DIR + FILE_NAME);
            scanTask.executeScan(unit);
            scanTask.executeSave();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void executeScan(CompilationUnit aUnit)
    {
        this.validatorUtil.validate(aUnit);
    }

    public void executeSave()
    {
        this.validatorUtil.saveResults();
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.tools.codegen.CodeScanAntTask#scanFinished()
     */
    public void scanFinished()
    {
        // TODO Auto-generated method stub

    }

}

/*
 * Created on Jun 16, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mojo.tools.code.audit.mapping;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mojo.km.config.EntityMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.tools.code.CompilationUnit;

/**
 * @author Jim Fisher
 *
 */
public class MappingValidatorUtil
{
    private List visitors;

    private File outputFile;

    private PrintWriter writer;

    public MappingValidatorUtil(File anOutputFile)
    {
        super();
        this.visitors = new ArrayList();
        this.outputFile = anOutputFile;
    }

    public void validate(CompilationUnit aUnit)
    {        
        String pTypeName = aUnit.getMainType().getQualifiedName();

        Iterator i = MojoProperties.getInstance().getEntityMaps();
        while (i.hasNext())
        {
            EntityMappingProperties entityProps = (EntityMappingProperties) i.next();
            if (entityProps.getEntity().equals(pTypeName))
            {
                IMappingValidatorVisitor visitor = new PersistenceMappingValidator(aUnit);
                EntityMappingPropertiesValidator validator = new EntityMappingPropertiesValidator(entityProps);
                validator.accept(visitor);

                this.visitors.add(visitor);
            }
        }
    }

    private void initPrintWriter()
    {       
        try
        {
            this.outputFile.createNewFile();
            
            OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFile));
            final OutputStreamWriter osw = new OutputStreamWriter(out, "UTF8");
            this.writer = new PrintWriter(osw);
        }
        catch (UnsupportedEncodingException e)
        {
            // unlikely to happen...
            throw new ExceptionInInitializerError(e);
        }
        catch (FileNotFoundException e)
        {
            throw new ExceptionInInitializerError(e);
        }
        catch (IOException e)
        {
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public void saveResults()
    {
        this.initPrintWriter();

        // TODO Separate XML format from walking results tree
        
        writer.println("<audit type=\"PersistenceMapping\">");

        Iterator v = this.visitors.iterator();
        while (v.hasNext())
        {
            IMappingValidatorVisitor visitor = (IMappingValidatorVisitor) v.next();

            IMappingValidatorVisitor outputVisitor = new MappingAuditXMLOutput(visitor.getCompilationUnit(), writer);

            EntityMappingPropertiesValidator validator = (EntityMappingPropertiesValidator) visitor
                    .getMappingValidator();

            // no call to accept, don't need to acutally validate again, just generate its output
            outputVisitor.visit(validator);
        }

        writer.println("</audit>");

        writer.close();
    }
}

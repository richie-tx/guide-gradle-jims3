/*
 * Created on Jun 22, 2006
 *
 */
package mojo.km.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mojo.km.config.xml.XMLAuditPropertyAdapter;

/**
 * @author Jim Fisher
 *
 */
public class AuditProperties extends GenericProperties
{
    private Map checks;

    private static AuditProperties instance;

    public AuditProperties()
    {
        this.checks = new HashMap();
    }

    public void addCheck(AuditCheckProperties aCheckProps)
    {
        String name = aCheckProps.getCheckName();
        this.checks.put(name, aCheckProps);
    }
    
    public List getChecks()
    {
        List checks = new ArrayList();
        checks.addAll(this.checks.values());
        return checks;
    }

    public void getModule(String aCheck)
    {
        this.checks.get(aCheck);
    }

    public static AuditProperties getInstance(String aConfigFile)
    {
        if (instance == null)
        {
            instance = new AuditProperties();

            XMLAuditPropertyAdapter auditProps = new XMLAuditPropertyAdapter(instance, aConfigFile);
            try
            {
                auditProps.loadProperties();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return instance;
    }
}

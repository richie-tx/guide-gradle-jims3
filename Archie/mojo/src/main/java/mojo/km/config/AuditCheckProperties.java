/*
 * Created on Jun 22, 2006
 *
 */
package mojo.km.config;

/**
 * @author Jim Fisher
 *
 */
public class AuditCheckProperties extends GenericProperties
{
    public static final String CHECK_NAME = "checkName";
    
    public static final String SEVERITY = "severity";
    
    public static final String RULE_VIOLATION = "ruleViolation";
    
    public String getCheckName()
    {
        return super.getProperty(CHECK_NAME);
    }
    
    public void setCheckName(String aCheckName)
    {
        super.setProperty(CHECK_NAME, aCheckName);
    }
    
    public String getSeverity()
    {
        return super.getProperty(AuditCheckProperties.SEVERITY);
    }
    
    public void setSeverity(String aSeverity)
    {
        super.setProperty(AuditCheckProperties.SEVERITY, aSeverity);
    }
    
    public String getRuleViolation()
    {
        return super.getProperty(AuditCheckProperties.RULE_VIOLATION);
    }
    
    public void setRuleViolation(String aRuleViolation)
    {
        super.setProperty(AuditCheckProperties.RULE_VIOLATION, aRuleViolation);
    }
}

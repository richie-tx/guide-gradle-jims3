package messaging.codetable.criminal.reply;

import java.util.Comparator;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.ResponseEvent;

/**
 * 
 * @author sthyagarajan
 * 
 */
public class JuvenileHearingTypeResponseEvent extends ResponseEvent implements Comparable<JuvenileHearingTypeResponseEvent>, ICode
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String categoryInd;
    private String code;
    private String description;
    private String inactiveInd;
    private String issueInd;
    private String optionInd;
    private String descriptionWithCode;
    
    public String getCategoryInd()
    {
	return categoryInd;
    }

    public void setCategoryInd(String categoryInd)
    {
	this.categoryInd = categoryInd;
    }

    public String getCode()
    {
	return code;
    }

    public void setCode(String code)
    {
	this.code = code;
    }

    public String getDescription()
    {
	return description;
    }

    public void setDescription(String description)
    {
	this.description = description;
    }

    public String getInactiveInd()
    {
	return inactiveInd;
    }

    public void setInactiveInd(String inactiveInd)
    {
	this.inactiveInd = inactiveInd;
    }

    public String getIssueInd()
    {
	return issueInd;
    }

    public void setIssueInd(String issueInd)
    {
	this.issueInd = issueInd;
    }

    public String getOptionInd()
    {
	return optionInd;
    }

    public void setOptionInd(String optionInd)
    {
	this.optionInd = optionInd;
    }

    public String getDescriptionWithCode()
    {
	return descriptionWithCode;
    }

    public void setDescriptionWithCode(String descriptionWithCode)
    {
	this.descriptionWithCode = descriptionWithCode;
    }

    public static Comparator<JuvenileHearingTypeResponseEvent> CodeComparator = new Comparator<JuvenileHearingTypeResponseEvent>() {
	public int compare(JuvenileHearingTypeResponseEvent obj1,JuvenileHearingTypeResponseEvent obj2)
	{

	    if (obj1 == null || !(obj1 instanceof JuvenileHearingTypeResponseEvent))
		return 0;
	    if (obj2 == null || !(obj2 instanceof JuvenileHearingTypeResponseEvent))
		return 0;

	    String code1 = (obj1).getDescription();
	    String code2 = (obj2).getDescription();
	    if (code1 == null)
		return 1;
	    if (code2 == null)
		return -1;

	    return code1.compareTo(code2);
	}
    };

    @Override
    public int compareTo(JuvenileHearingTypeResponseEvent obj)throws ClassCastException
    {
	JuvenileHearingTypeResponseEvent evt = obj;
	return description.compareTo(evt.getDescription());
    }
    
}

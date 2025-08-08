package messaging.codetable.riskanalysis.reply;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.ResponseEvent;

/**
 * @author C_NAggarwal
 */
public class RiskAnalysisControlCodeResponseEvent extends ResponseEvent implements Comparable, ICode
{
	private String riskAnalysisControlCodeId;
    private String code;
    private String description;
    private String answerSource;
    private String name;


    /**
     * @return Returns the code.
     */
    public String getCode()
    {
        return code;
    }

    /**
     * @param code
     *            The code to set.
     */
    public void setCode(String code)
    {
        this.code = code;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setAnswerSource(String answerSource) {
		this.answerSource = answerSource;
	}

	public String getAnswerSource() {
		return answerSource;
	}

	public void setRiskAnalysisControlCodeId(String riskAnalysisControlCodeId) {
		this.riskAnalysisControlCodeId = riskAnalysisControlCodeId;
	}

	public String getRiskAnalysisControlCodeId() {
		return riskAnalysisControlCodeId;
	}

	public int compareTo(Object obj) throws ClassCastException
    {
        RiskAnalysisControlCodeResponseEvent evt = (RiskAnalysisControlCodeResponseEvent) obj;
        return description.compareTo(evt.getDescription());
    }

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

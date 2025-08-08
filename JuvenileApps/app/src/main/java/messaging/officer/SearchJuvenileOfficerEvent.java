package messaging.officer;

import mojo.km.messaging.RequestEvent;

public class SearchJuvenileOfficerEvent extends RequestEvent
{

    private boolean caseloadManager;

    private String clmString;

    private String firstName;

    private boolean jpo;

    private String jpoString;

    private String lastName;

    private String middleName;

    /**
     * @return Returns the clmString.
     */
    public String getClmString()
    {
        return clmString;
    }

    /**
     * @return Returns the firstName.
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @return Returns the jpoString.
     */
    public String getJpoString()
    {
        return jpoString;
    }

    /**
     * @return Returns the lastName.
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @return Returns the middleName.
     */
    public String getMiddleName()
    {
        return middleName;
    }

    /**
     * @return Returns the caseloadManager.
     */
    public boolean isCaseloadManager()
    {
        return caseloadManager;
    }

    /**
     * @return Returns the jpo.
     */
    public boolean isJpo()
    {
        return jpo;
    }

    /**
     * @param caseloadManager
     *            The caseloadManager to set.
     */
    public void setCaseloadManager(boolean caseloadManager)
    {
        this.caseloadManager = caseloadManager;
    }

    /**
     * @param clmString
     *            The clmString to set.
     */
    public void setClmString(String clmString)
    {
        this.clmString = clmString;
    }

    /**
     * @param firstName
     *            The firstName to set.
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @param jpo
     *            The jpo to set.
     */
    public void setJpo(boolean jpo)
    {
        this.jpo = jpo;
    }

    /**
     * @param jpoString
     *            The jpoString to set.
     */
    public void setJpoString(String jpoString)
    {
        this.jpoString = jpoString;
    }

    /**
     * @param lastName
     *            The lastName to set.
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * @param middleName
     *            The middleName to set.
     */
    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }
}

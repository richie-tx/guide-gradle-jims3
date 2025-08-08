/*
 * Created on Jun 26, 2006
 *
 */
package mojo.tools.code;

/**
 * @author Jim Fisher
 *
 */
public abstract class AbstractStatement extends CodeElement
{
    abstract public void accept(IElementVisitor visitor);

    public String toString()
    {
        return this.body;
    }

    protected String body;

    /**
     * @return Returns the bodyText.
     */
    public String getBody()
    {
        return body;
    }

    /**
     * @param bodyText The bodyText to set.
     */
    public void setBody(String bodyText)
    {
        this.body = bodyText;
    }
}

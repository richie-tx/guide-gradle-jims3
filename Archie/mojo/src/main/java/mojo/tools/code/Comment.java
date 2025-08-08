package mojo.tools.code;

/**
 * Represents a generic Java comment, either a line comment, block comment or
 * Javadoc comment.
 * 
 * @author Saleem Shafi
 */
abstract public class Comment implements Acceptable
{
    private String comment;

    private CodeElement parent;

    /**
     * Creates a comment with the given text as the text of the comment.
     * 
     * @param String the text of the comment
     */
    public Comment(String aComment)
    {
        this.comment = aComment;
    }

    /**
     * Returns the text of the comment.
     * 
     * @return the text of the comment
     */
    public String getComment()
    {
        return comment;
    }

    /**
     * Sets the text of the comment.
     * 
     * @param String the text of the comment
     */
    public void setComment(String aComment)
    {
        this.comment = aComment;
    }

    /**
     * Returns the code element that this comment is associated with.
     * 
     * @return the associated code element
     */
    public CodeElement getParent()
    {
        return parent;
    }

    /**
     * Sets the associated code element for this comment.
     * 
     * @param CodeElement the associated code element
     */
    void setParent(CodeElement aParent)
    {
        this.parent = aParent;
    }

}
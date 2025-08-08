package mojo.tools.code;

import mojo.tools.code.KeyWord;

/**
 * This class represents a single-line Java comment.
 * 
 * @author Saleem Shafi
 * @modelguid {FCC3D719-80BE-4AD8-ADCD-AA8682A44BC7}
 */
public class LineComment extends Comment
{

	/**
	 * Creates a LineComment with the given comment text.
	 * 
	 * @param String
	 *            text of comment
	 * @modelguid {8153F510-975B-4387-9C1A-5E23F0DC6215}
	 */
	public LineComment(String aComment)
	{
		super(aComment);
	}

	/**
	 * Returns the Java code representation of this line comment.
	 * 
	 * @return String representation of line comment
	 * @modelguid {72F26024-6127-4AE7-8C12-334032CD5085}
	 */
	public String toString()
	{
		return new StringBuffer(KeyWord.LINECOMMENT_OPEN).append(getComment()).append(KeyWord.NEWLINE).toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.tools.code.Acceptable#accept(mojo.tools.code.IElementVisitor)
	 */
	public void accept(IElementVisitor visitor)
	{
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

}

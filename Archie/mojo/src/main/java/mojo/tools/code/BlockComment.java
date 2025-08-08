package mojo.tools.code;

import mojo.tools.code.KeyWord;

/**
 * Represents a block Java comment.
 * 
 * @author Saleem Shafi
 * @modelguid {5DFD98F3-19E5-4396-9798-414257BCAD3E}
 */
public class BlockComment extends Comment
{

	/**
	 * Creates a block comment with the given comment text.
	 * 
	 * @param String
	 *            the text in the comment
	 * @modelguid {8D5A91D7-689B-410F-91F1-C5A495C57CB4}
	 */
	public BlockComment(String aComment)
	{
		super(aComment);
	}

	/**
	 * Returns the Java code that represents the block comment.
	 * 
	 * @return the String representation of the block comment
	 * @modelguid {E6A744BC-EFA2-4769-A6C7-092468E312C5}
	 */
	public String toString()
	{
		StringBuffer lText = new StringBuffer();
		lText.append(KeyWord.BLOCKCOMMENT_OPEN).append(KeyWord.NEWLINE);
		lText.append(getComment()).append(KeyWord.NEWLINE);
		lText.append(KeyWord.BLOCKCOMMENT_CLOSE).append(KeyWord.NEWLINE);
		return lText.toString();
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

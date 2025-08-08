package mojo.tools.code;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import mojo.tools.code.KeyWord;

/**
 * This class represents a Java method definition.
 * 
 * @author Saleem Shafi
 */
public class Method extends CodeElement
{
    private Block block;

    private String body;

    private boolean isAbstract;
    
    private boolean isConstructor;

    private boolean isFinal;

    private boolean isNative;

    private boolean isStatic;

    private boolean isStrictfp;

    private boolean isSynchronized;

    private String name;

    private List parameters;

    private String returnType;

    private String scope;

    private Set throwsStatements;

    /**
     * Creates a method definition with the given name.
     * 
     * @param String the method name
     */
    public Method(String aName)
    {
        this.parameters = new Vector();
        this.throwsStatements = new HashSet();
        setName(aName);
        setReturnType(KeyWord.VOID);
        setBody(KeyWord.EMPTY_STRING);
    }

    public void accept(IElementVisitor visitor)
    {
        visitor.visit(this);
    }

    /**
     * Adds the given parameter to this method definition.
     * 
     * @param Parameter the parameter to add
     */
    public void addParameter(Parameter aParameter)
    {
        parameters.add(aParameter);
        aParameter.setParent(this);
    }

    /**
     * Adds the given exception name to the list of exceptions that this method
     * definition should throw.
     * 
     * @param String the name of the exception to throw
     */
    public void addThrows(String aThrowable)
    {
        if (!throwsStatements.contains(aThrowable))
        {
            throwsStatements.add(aThrowable);
        }
    }

    /**
     * Appends the given line of Java code to the end of the body of this
     * method.
     * 
     * @param String the line of Java code to append
     */
    public void appendToBody(String aLine)
    {
        StringBuffer lText = new StringBuffer(getBody());
        lText.append(aLine).append(KeyWord.NEWLINE);
        setBody(lText.toString());
    }

    /**
     * @return Returns the block.
     */
    public Block getBlock()
    {
        return block;
    }

    /**
     * Returns the Java code comprising the body of this method.
     * 
     * @return the method body
     */
    public String getBody()
    {
        return body;
    }

    public String getMethodSignature()
    {
        StringBuffer buffer = new StringBuffer();
        Class parentClass = (Class) this.getParent();               
        
        if(parentClass.getName().equals(parentClass.getName()) && this.isStatic() == false)
        {
            buffer.append("this");
        }
        else
        {
            buffer.append(parentClass.getName());    
        }
        
        buffer.append(".");
        buffer.append(this.getName());
        buffer.append("(");

        Iterator p = this.getParameters();
        StringBuffer parmBuffer = new StringBuffer();
        while (p.hasNext())
        {
            Parameter parm = (Parameter) p.next();
            String parmString = parm.getType();
            parmBuffer.append(parmString);
            if (p.hasNext())
            {
                parmBuffer.append(",");
            }
        }

        if (parmBuffer.length() > 0)
        {
            buffer.append(parmBuffer);
        }
        buffer.append(")");        
        return buffer.toString();

    }
    
    /**
     * Returns the name of this method.
     * 
     * @return the name of this method
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns this list of parameters defined for this method.
     * 
     * @return Iterator to this list of parameters
     */
    public Iterator getParameters()
    {
        return parameters.iterator();
    }

    /**
     * Returns the return type for this method definition.
     * 
     * @return the return type
     */
    public String getReturnType()
    {
        return returnType;
    }

    /**
     * Returns the scope of this method.
     * 
     * @return the scope
     */
    public String getScope()
    {
        return scope;
    }

    /**
     * Returns the list of exceptions that are declared to be thrown from this
     * this method.
     * 
     * @return Iterator to the list of exceptions
     */
    public Iterator getThrows()
    {
        return throwsStatements.iterator();
    }

    /**
     * Returns whether or not this method is defined as abstract.
     * 
     * @return true iff this method is defined as abstract
     */
    public boolean isAbstract()
    {
        return isAbstract;
    }

    /**
     * @return Returns the isConstructor.
     */
    public boolean isConstructor()
    {
        return isConstructor;
    }

    /**
     * Returns whether or not this method is defined as final.
     * 
     * @return true iff this method is defined as final
     */
    public boolean isFinal()
    {
        return isFinal;
    }

    /**
     * Returns whether or not this method is defined as native.
     * 
     * @return true iff this method is defined as native
     * @modelguid {4C287977-5072-499F-8E29-55DD72D0263E}
     */
    public boolean isNative()
    {
        return isNative;
    }

    /**
     * Returns whether or not this method is defined as static.
     * 
     * @return true iff this method is defined as static
     * @modelguid {129A1938-D2A7-429D-94D3-696784225BAA}
     */
    public boolean isStatic()
    {
        return isStatic;
    }

    /**
     * Returns whether or not this method is defined as strictfp.
     * 
     * @return true iff this method is defined as strictfp
     * @modelguid {C48F945F-67F7-4506-8C70-279EE195640D}
     */
    public boolean isStrictfp()
    {
        return isStrictfp;
    }

    /**
     * Returns whether or not this method is defined as synchronized.
     * 
     * @return true iff this method is defined as synchronized
     * @modelguid {66E4508E-3BB0-44E4-A886-7758E13D44C3}
     */
    public boolean isSynchronized()
    {
        return isSynchronized;
    }

    /**
     * Removes the given parameter from this method definition.
     * 
     * @param Parameter the parameter to remove
     */
    public void removeParameter(Parameter aParam)
    {
        parameters.remove(aParam);
    }

    /**
     * Removes the given exception name from the list of exception that this
     * method can throw. The parameter must match the exception name exactly.
     * 
     * @param String exception name to remove
     */
    public void removeThrows(String aThrowable)
    {
        throwsStatements.remove(aThrowable);
    }

    /**
     * Sets whether or not this method should be declared as abstract.
     * 
     * @param boolean whether or not this method should be abstract
     */
    public void setAbstract(boolean isAbstract)
    {
        this.isAbstract = isAbstract;
    }

    /**
     * @param block The block to set.
     */
    public void setBlock(Block block)
    {
        this.block = block;
    }

    /**
     * Sets the body of this method with the given text.
     * 
     * @param String the body of the method
     * @modelguid {3EDEDFBB-4406-4571-AA6C-ABFB504B0362}
     */
    public void setBody(String aBody)
    {
        body = aBody;
    }
    /**
     * @param isConstructor The isConstructor to set.
     */
    public void setConstructor(boolean isConstructor)
    {
        this.isConstructor = isConstructor;
    }

    /**
     * Sets whether or not this method should be declared as final.
     * 
     * @param boolean whether or not this method should be final
     */
    public void setFinal(boolean isFinal)
    {
        this.isFinal = isFinal;
    }

    /**
     * Sets the name of the method.
     * 
     * @param the name of the method
     */
    public void setName(String aName)
    {
        name = aName;
    }

    /**
     * Sets whether or not this method should be declared as native.
     * 
     * @param boolean whether or not this method should be native
     * @modelguid {A478C43C-A92A-4CF0-9813-8EB708ACA21D}
     */
    public void setNative(boolean isNative)
    {
        this.isNative = isNative;
    }

    /**
     * Sets the return type for this method definition.
     * 
     * @param String the return type
     */
    public void setReturnType(String aReturnType)
    {
        returnType = aReturnType;
    }

    /**
     * Sets the scope of the method.
     * 
     * @param String the scope
     */
    public void setScope(String aScope)
    {
        scope = aScope;
    }

    /**
     * Sets whether or not this method should be declared as static.
     * 
     * @param boolean whether or not this method should be static
     * @modelguid {E58D51CB-2CC0-4F77-9D5D-E9ED2D7C8BDD}
     */
    public void setStatic(boolean isStatic)
    {
        this.isStatic = isStatic;
    }

    /**
     * Sets whether or not this method should be declared as strictfp.
     * 
     * @param boolean whether or not this method should be strictfp
     * @modelguid {11849CA0-DD2C-4658-8A29-6F7A009B5EE1}
     */
    public void setStrictfp(boolean isStrictfp)
    {
        this.isStrictfp = isStrictfp;
    }

    /**
     * Sets whether or not this method should be declared as synchronized.
     * 
     * @param boolean whether or not this method should be synchronized
     * @modelguid {0616C0B8-6D0C-4C23-B7DC-582D91FC0979}
     */
    public void setSynchronized(boolean isSynchronized)
    {
        this.isSynchronized = isSynchronized;
    }
    
    public String getOriginalMethodString()
    {
        StringBuffer text = new StringBuffer();
        if (getComment() != null)
        {
            JavaComment comment = (JavaComment) getComment();
            text.append(comment.getCommentWithoutTags());
        }
        
        text.append(this.getMethodString());
        
        return text.toString();
    }
    
    private String getMethodString()
    {
        StringBuffer text = new StringBuffer();
        if (isSynchronized())
        {
            text.append(KeyWord.SYNCHRONIZED);
        }
        if (isFinal())
        {
            text.append(KeyWord.FINAL);
        }
        if (isAbstract())
        {
            text.append(KeyWord.ABSTRACT);
        }
        if (isStatic())
        {
            text.append(KeyWord.STATIC);
        }
        if (isNative())
        {
            text.append(KeyWord.NATIVE);
        }
        if (getScope() != null)
        {
            text.append(getScope()).append(KeyWord.SPACE);
        }
        text.append(getReturnType()).append(KeyWord.SPACE).append(getName());

        text.append(KeyWord.OPEN_PAREN);
        for (Iterator i = getParameters(); i.hasNext();)
        {
            Parameter lParam = (Parameter) i.next();
            text.append(lParam.toString());
            if (i.hasNext())
            {
                text.append(KeyWord.COMMA);
            }
        }
        text.append(KeyWord.CLOSE_PAREN);

        Iterator j = getThrows();
        if (j.hasNext())
        {
            text.append(KeyWord.THROWS);
            while (j.hasNext())
            {
                String throwsClause = (String) j.next();
                text.append(throwsClause);
                if (j.hasNext())
                {
                    text.append(KeyWord.COMMA);
                }
            }
        }
        if (this.getParent() instanceof Interface || isAbstract() || isNative())
        {
            text.append(KeyWord.SEMICOLON).append(KeyWord.NEWLINE);
        }
        else
        {
            text.append(KeyWord.NEWLINE);
            
            text.append(KeyWord.BLOCK_OPEN);            
            text.append(KeyWord.NEWLINE);
            
            text.append(indent(getBody()));
            text.append(KeyWord.BLOCK_CLOSE);
            
            text.append(KeyWord.NEWLINE);
        }
        return text.toString();
    }

    /**
     * Returns the Java code representing this method definition.
     * 
     * @return String representation of this method
     */
    public String toString()
    {
        StringBuffer text = new StringBuffer();
        if (getComment() != null)
        {
            text.append(getComment().toString());
        }
        
        text.append(this.getMethodString());
        
        return text.toString();
    }
}

package mojo.km.security;

public class ConstraintBean implements java.io.Serializable, Comparable
{
    private String constrainerId;

    private String constrainerType;

    private String constrainsId;

    private String constrainsType;

    public boolean equals(Object obj)
    {
        boolean result = (obj instanceof ConstraintBean);

        if (result == false)
        {
            return false;
        }

        ConstraintBean constraint = (ConstraintBean) obj;

        if (this.constrainerId.equals(constraint.getConstrainerId()) == false)
        {
            result = false;
        }
        else if (this.constrainerType.equals(constraint.getConstrainerType()) == false)
        {
            result = false;
        }
        else if (this.constrainsId.equals(constraint.getConstrainsId()) == false)
        {
            result = false;
        }
        else if (this.constrainsType.equals(constraint.getConstrainsType()) == false)
        {
            result = false;
        }

        return result;
    }

    /**
     * @return constrainerId
     */
    public String getConstrainerId()
    {
        return constrainerId;
    }

    /**
     * @return constrainterType
     */
    public String getConstrainerType()
    {
        return constrainerType;
    }

    /**
     * @return constrainsId
     */
    public String getConstrainsId()
    {
        return constrainsId;
    }

    /**
     * @return contrainsType
     */
    public String getConstrainsType()
    {
        return constrainsType;
    }

    /**
     * @param constrainerId
     */
    public void setConstrainerId(String aConstrainerId)
    {
        constrainerId = aConstrainerId;
    }

    /**
     * @param constrainerType
     */
    public void setConstrainerType(String aConstrainerType)
    {
        constrainerType = aConstrainerType;
    }

    /**
     * @param constrainsId
     */
    public void setConstrainsId(String aConstrainsId)
    {
        constrainsId = aConstrainsId;
    }

    /**
     * @param constraintsType
     */
    public void setConstrainsType(String aConstrainsType)
    {
        constrainsType = aConstrainsType;
    }

    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{constrainerId=");
        buffer.append(constrainerId);
        buffer.append(" constrainsId=");
        buffer.append(constrainsId);
        buffer.append("}");
        return buffer.toString();
    }

    public int compareTo(Object o)
    {
        ConstraintBean c = (ConstraintBean) o;

        return this.constrainerId.compareTo(c.getConstrainerId());
    }
}

package mojo.km.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ACE implements java.io.Serializable, Comparable
{
    private FeatureBean feature;

    private String userId;

  //  private ArrayList constraints = new ArrayList();

    /**
     * @roseuid 422C71F303A7
     */
    public ACE(FeatureBean feature, String userId)
    {
        this.feature = feature;
        this.userId = userId;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof ACE == false)
        {
            return false;
        }

        ACE ace = (ACE) obj;

        boolean result = this.feature.equals(ace.getFeature());

     //   result = constraints.containsAll(constraints) && result; //87191

        return result;
    }

    public FeatureBean getFeature()
    {
        return feature;
    }

    public String getUserId()
    {
        return userId;
    }
    //87191
  /*  public void addConstraint(ConstraintBean constraint)
    {
        if (!constraints.contains(constraint))
        {
            constraints.add(constraint);
        }
    }

    public void removeConstraint(ConstraintBean constraint)
    {
        constraints.remove(constraint);
    }

    *//**
     * @return java.util.List
     * @roseuid 423068D803A5
     *//*
    public List getConstraints()
    {
        return constraints;
    }

    *//**
     * @param aConstraints
     *//*
    public void addAll(List aConstraints)
    {
        constraints.addAll(aConstraints);
    }*/

   /* public String toString()
    {
        StringBuilder buffer = new StringBuilder(100);
        buffer.append(this.feature.getName());
        buffer.append(": {");
        Collections.sort(this.constraints);
        int len = this.constraints.size();
        for (int i = 0; i < len; i++)
        {
            ConstraintBean bean = (ConstraintBean) this.constraints.get(i);
            buffer.append(bean.toString());
            if (i < len - 1)
            {
                buffer.append(",");
            }
        }
        buffer.append("}");
        return buffer.toString();
    }*/

    public int compareTo(Object o)
    {
        ACE a = (ACE) o;
        return this.getFeature().getName().compareTo(a.getFeature().getName());
    }
}

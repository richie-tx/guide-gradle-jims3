package mojo.km.security;

import java.util.Iterator;
import java.util.List;

import mojo.km.persistence.Home;
import mojo.km.utilities.CollectionUtil;
import mojo.messaging.securitytransactionsevents.LoginEvent;

public class UserConstraint extends Constraint
{
  /*  private String roleId;

    public static List findAll(LoginEvent anEvent)
    {
        Iterator i = new Home().findAll(anEvent, UserConstraint.class);
        List userFeatures = CollectionUtil.iteratorToList(i);
        return userFeatures;
    }

    *//**
     * @return Returns the roleId.
     *//*
    public String getRoleId()
    {
        return roleId;
    }

    *//**
     * @param roleId
     *            The roleId to set.
     *//*
    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }        */
}

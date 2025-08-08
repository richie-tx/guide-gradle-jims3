//Source file: C:\\VIEWS\\ARCHPRODUCTION\\FRAMEWORK\\MOJO-JIMS2\\MOJO.JAVA\\src\\mojo\\km\\security\\Constraint.java

package mojo.km.security;

import java.util.*;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.*;
import mojo.km.security.GetConstraintByConstrainsIdAndConstrainsTypeEvent;

import java.util.Iterator;

/**
@roseuid 422C77F303C1
 */
public class Constraint extends PersistentObject
{/*
	private String constrainerId;
	private String constrainerType;
	private String constrainsId;
	private String constrainsType;

	*//**
	 * @roseuid 422C77F303C1
	 *//*
	public Constraint()
	{

	}
	
	public ConstraintBean valueObject()
	{
	    ConstraintBean cb = new ConstraintBean();
        cb.setConstrainerId(this.constrainerId);
        cb.setConstrainerType(this.constrainerType);
        cb.setConstrainsId(this.constrainsId);
        cb.setConstrainsType(this.constrainsType);
        return cb;        
	}

	*//**
	 * Access method for the constrainerId property.
	 * 
	 * @return   the current value of the constrainerId property
	 *//*
	public java.lang.String getConstrainerId()
	{
		return constrainerId;
	}

	*//**
	 * Sets the value of the constrainerId property.
	 * 
	 * @param aConstrainerId the new value of the constrainerId property
	 *//*
	public void setConstrainerId(java.lang.String aConstrainerId)
	{
		if (this.constrainerId == null
			|| !this.constrainerId.equals(aConstrainerId))
		{
			markModified();
		}
		constrainerId = aConstrainerId;
	}

	*//**
	 * Access method for the constrainerType property.
	 * 
	 * @return   the current value of the constrainerType property
	 *//*
	public java.lang.String getConstrainerType()
	{
		return constrainerType;
	}

	*//**
	 * Sets the value of the constrainerType property.
	 * 
	 * @param aConstrainerType the new value of the constrainerType property
	 *//*
	public void setConstrainerType(java.lang.String aConstrainerType)
	{
		if (this.constrainerType == null
			|| !this.constrainerType.equals(aConstrainerType))
		{
			markModified();
		}
		constrainerType = aConstrainerType;
	}

	public PersistentObject getConstrainerObject()
	{
		Class typeClass =
			mojo.km.config.SecurityProperties.getInstance().getConstraintClass(
				this.getConstrainerType());
		if (typeClass == null)
		{
			throw new RuntimeException(
				"Could not find implementation type for ConstrainerType "
					+ this.getConstrainerType()
					+ ". You are probably missing an entry in the ConstraintMappings section of the mojo configuration.");
		}
		PersistentObject po =
			(PersistentObject) new Home().find(
				this.getConstrainerId(),
				typeClass);
		return po;
	}

	public PersistentObject getConstrainsObject()
	{
		Class typeClass =
			mojo.km.config.SecurityProperties.getInstance().getConstraintClass(
				this.getConstrainsType());
		if (typeClass == null)
		{
			throw new RuntimeException(
				"Could not find implementation type for ConstrainerType "
					+ this.getConstrainsType()
					+ ". You are probably missing an entry in the ConstraintMappings section of the mojo configuration.");
		}
		PersistentObject po =
			(PersistentObject) new Home().find(
				this.getConstrainsId(),
				typeClass);
		return po;
	}

	*//**
	 * Access method for the constrainsId property.
	 * 
	 * @return   the current value of the constrainsId property
	 *//*
	public java.lang.String getConstrainsId()
	{
		return constrainsId;
	}

	*//**
	 * Sets the value of the constrainsId property.
	 * 
	 * @param aConstrainsId the new value of the constrainsId property
	 *//*
	public void setConstrainsId(java.lang.String aConstrainsId)
	{
		if (this.constrainsId == null
			|| !this.constrainsId.equals(aConstrainsId))
		{
			markModified();
		}
		constrainsId = aConstrainsId;
	}

	*//**
	 * Access method for the constrainsType property.
	 * 
	 * @return   the current value of the constrainsType property
	 *//*
	public java.lang.String getConstrainsType()
	{
		return constrainsType;
	}

	*//**
	 * Sets the value of the constrainsType property.
	 * 
	 * @param aConstrainsType the new value of the constrainsType property
	 *//*
	public void setConstrainsType(java.lang.String aConstrainsType)
	{
		if (this.constrainsType == null
			|| !this.constrainsType.equals(aConstrainsType))
		{
			markModified();
		}
		constrainsType = aConstrainsType;
	}

	*//**
	 * @param constraintId
	 * @return mojo.km.security.Constraint
	 * @roseuid 4236ED930151
	 *//*
	public static Constraint find(String constraintId)
	{
		return (Constraint) new Home().find(constraintId, Constraint.class);
	}

	*//**
		* @param attrName
		* @param attrValue
		* @return java.util.Iterator
		* @roseuid 4236ED9301A1
		*//*
	public static Iterator findAll(String attrName, String attrValue)
	{
		return null;
	}

	*//**
		* @param event
		* @return java.util.Iterator
		*//*
	public static Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(event, Constraint.class);
		return iter;
	}

	*//**
		* @param constrainsId
		* @return java.util.Iterator
		* @roseuid 4236ED9301A1
		*//*
	public static Collection findByConstrainsId(
		String constrainsId,
		String constrainsType)
	{
		java.util.ArrayList retVal = new java.util.ArrayList();
		GetConstraintByConstrainsIdAndConstrainsTypeEvent ce = new GetConstraintByConstrainsIdAndConstrainsTypeEvent();
		ce.setConstrainsId(constrainsId);
		ce.setConstrainsType(constrainsType);
		java.util.Iterator i = new Home().findAll(ce, Constraint.class);
		while (i.hasNext())
		{
			mojo.km.security.Constraint actual =
				(mojo.km.security.Constraint) i.next();
			retVal.add(actual);
		}
		return retVal;
	}
	
	*//**
		* @param constrainerId
		* @param constrainerType
		* @param constraintType
		* @return java.util.Collection
		* @roseuid 4236ED9301A1
		*//*
	public static Collection findByConstrainerId(
		String constrainerId,
		String constrainerType,
		String constraintType)
	{
		 java.util.ArrayList retVal = new java.util.ArrayList();
		 GetConstraintByConstrainerAndConstrainsEvent ce = new GetConstraintByConstrainerAndConstrainsEvent();
		 ce.setConstrainsType(constraintType);
		 ce.setConstrainerType(constrainerType);
		 ce.setConstrainerId(constrainerId);
		 java.util.Iterator i = new Home().findAll(ce, Constraint.class);
		 while (i.hasNext()) {
			 mojo.km.security.Constraint cons = (mojo.km.security.Constraint) i.next();
			 retVal.add(cons);
		 }
		 return retVal;
	}

	*//**
		* @param constrainsId
		* @param constraintType
		* @param constrainerId
		* @param constrainerType
		* @return Constraint
		* @roseuid 4236ED9301A1
		*//*
	public static Constraint findByConstrainsIdAndConstrainerId(String constrainsId, String constrainsType, String constrainerId, String constrainerType) 
	{
	 Constraint retVal = null;
	 GetConstraintByConstrainerAndConstrainsEvent ce = new GetConstraintByConstrainerAndConstrainsEvent();
	 ce.setConstrainsId(constrainsId);
	 ce.setConstrainsType(constrainsType);
	 ce.setConstrainerId(constrainerId);
	 ce.setConstrainerType(constrainerType);
	 java.util.Iterator i = new Home().findAll(ce, Constraint.class);
	 while (i.hasNext()) {
		 retVal = (mojo.km.security.Constraint) i.next();
		 break;
	 }
	 return retVal;
	}*/

}

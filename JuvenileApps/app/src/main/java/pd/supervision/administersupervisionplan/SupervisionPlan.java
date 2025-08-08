//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administersupervisionplan\\SupervisionPlan.java

package pd.supervision.administersupervisionplan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.CollectionUtil;

public class SupervisionPlan extends PersistentObject 
{
   private Date supervisionPlanDate;
   private String statusCd;
   private String defendantId;
   
   private Date lastChangeDate;
   private String lastChangeUserId;

   private String problem; 
   private String behaviorObjective;   
   private String offenderActionPlan;  
   private String csoActionPlan;
   

   
   /**
    * @roseuid 48176742038F
    */
   public SupervisionPlan() 
   {
    
   }
   
   static public List findAll()
	{
		Home home = new Home();
		Iterator iter = home.findAll(SupervisionPlan.class);
		return CollectionUtil.iteratorToList(iter);
	}
   
   /**
	 * 
	 * @roseuid 47AB42E900DD
	 * @return List
	 * @param anEvent
	 */
	static public List findAll(IEvent anEvent)
	{
		Home home = new Home();
		Iterator iter = home.findAll(anEvent, SupervisionPlan.class);
		List spList = CollectionUtil.iteratorToList(iter);
		populateLastChangeDateAndUser(spList);
		return spList;
	}

	
   /**
    * @roseuid 480E216501E3
    */
   static public List findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(attrName, attrValue, SupervisionPlan.class);
		List spList = CollectionUtil.iteratorToList(iter);
		populateLastChangeDateAndUser(spList);
		return spList;
	}

   
   /**
    * @roseuid 480E216501E4
    */
   public static SupervisionPlan find(String oid) 
   {
	   	IHome home = new Home();
	   	SupervisionPlan supervisionPlan = (SupervisionPlan) home.find(oid, SupervisionPlan.class);
	   	if(supervisionPlan != null)
	   	{
		   	ArrayList list = new ArrayList();
		   	list.add(supervisionPlan);
		   	populateLastChangeDateAndUser(list);
			return (SupervisionPlan)list.get(0);
	   	}
	   	else
	   	{
	   		return supervisionPlan;
	   	}
   }
   
   
   /**
    * @roseuid 480E216501E5
    */
   public void bind() 
   {
	   	IHome home = new Home();
		home.bind(this);
   }
   
   private static void populateLastChangeDateAndUser(List spList)
   {
	   Iterator iterator = spList.iterator();
	   if(iterator != null)
	   {
		   while(iterator.hasNext())
		   {
			   SupervisionPlan supervisionPlan = (SupervisionPlan)iterator.next();
			   if(supervisionPlan.getUpdateUserID()==null)
			   {
				   Date date = clearTimeInDate(supervisionPlan.getCreateTimestamp());
				   supervisionPlan.setLastChangeDate(date);
				   supervisionPlan.setLastChangeUserId(supervisionPlan.getCreateUserID());
			   }
			   else
			   {
				   Date date = clearTimeInDate(supervisionPlan.getUpdateTimestamp());
				   supervisionPlan.setLastChangeDate(date);
				   supervisionPlan.setLastChangeUserId(supervisionPlan.getUpdateUserID());
			   }
		   }
	   }
   }
   
   private static Date clearTimeInDate(Date date)
   {	
	   Calendar cal = Calendar.getInstance();
	   cal.setTime(date);
	   cal.clear(Calendar.HOUR);
	   cal.clear(Calendar.MINUTE);
	   cal.clear(Calendar.SECOND);
	   cal.clear(Calendar.MILLISECOND);
	   return cal.getTime();
   }
   
   
   
public String getBehaviorObjective()
{
	fetch();
	return behaviorObjective;
}


public void setBehaviorObjective(String behaviorObjective)
{
	if (this.behaviorObjective == null || !this.csoActionPlan.equals(behaviorObjective))
	{
		markModified();
	}
	this.behaviorObjective = behaviorObjective;
}


public String getCsoActionPlan() 
{
	fetch();
	return csoActionPlan;
}


public void setCsoActionPlan(String csoActionPlan)
{
	if (this.csoActionPlan == null || !this.csoActionPlan.equals(csoActionPlan))
	{
		markModified();
	}
	this.csoActionPlan = csoActionPlan;
}


public String getDefendantId()
{
	fetch();
	return defendantId;
}


public void setDefendantId(String defendantId)
{
	if (this.defendantId == null || !this.defendantId.equals(defendantId))
	{
		markModified();
	}
	this.defendantId = defendantId;
}


public Date getLastChangeDate()
{
	return lastChangeDate;
}


public void setLastChangeDate(Date lastChangeDate)
{
	this.lastChangeDate = lastChangeDate;
}


public String getLastChangeUserId()
{
	fetch();
	return lastChangeUserId;
}


public void setLastChangeUserId(String lastChangeUserId) 
{
	this.lastChangeUserId = lastChangeUserId;
}


public String getOffenderActionPlan() {
	return offenderActionPlan;
}

public void setOffenderActionPlan(String offenderActionPlan)
{
	if (this.offenderActionPlan == null || !this.offenderActionPlan.equals(offenderActionPlan))
	{
		markModified();
	}
	this.offenderActionPlan = offenderActionPlan;
}


public String getProblem()
{
	fetch();
	return problem;
}


public void setProblem(String problem)
{
	if (this.problem == null || !this.problem.equals(problem))
	{
		markModified();
	}
	this.problem = problem;
}


public String getStatusCd()
{
	fetch();
	return statusCd;
}


public void setStatusCd(String statusCd)
{
	if (this.statusCd == null || !this.statusCd.equals(statusCd))
	{
		markModified();
	}
	this.statusCd = statusCd;
}


public Date getSupervisionPlanDate()
{
	fetch();
	return supervisionPlanDate;
}


public void setSupervisionPlanDate(Date supervisionPlanDate)
{
	if (this.supervisionPlanDate == null || !this.supervisionPlanDate.equals(supervisionPlanDate))
	{
		markModified();
	}
	this.supervisionPlanDate = supervisionPlanDate;
}

 
}

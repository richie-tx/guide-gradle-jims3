//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\managetask\\CreateCSTaskEvent.java

package messaging.managetask;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateCSTaskEvent extends RequestEvent 
{
   private String csTaskId;
   private Date lastTransferDate;
   private String lastTransferUser;
   private String statusId;
   private boolean acceptTask;
   private boolean closeTask;
   private String staffPositionId;
   
   
   
   public String getLastTransferUser() {
	return lastTransferUser;
}

public void setLastTransferUser(String lastTransferUser) {
	this.lastTransferUser = lastTransferUser;
}

/**
    * @roseuid 463F30130185
    */
   public UpdateCSTaskEvent() 
   {
    
   }
   
   /**
    * @param lastTransferDate
    * @roseuid 463F1710003D
    */
   public void setLastTransferDate(Date lastTransferDate) 
   {
    this.lastTransferDate = lastTransferDate;
   }
   
   /**
    * @return java.util.date
    * @roseuid 463F1710003F
    */
   public Date getLastTransferDate() 
   {
    return this.lastTransferDate;
   }

public String getCsTaskId() {
	return csTaskId;
}

public void setCsTaskId(String ntTaskId) {
	this.csTaskId = ntTaskId;
}

public String getStatusId() {
	return statusId;
}

public void setStatusId(String statusId) {
	this.statusId = statusId;
}

public boolean isAcceptTask() {
	return acceptTask;
}

public void setAcceptTask(boolean statusOnly) {
	this.acceptTask = statusOnly;
}

public String getStaffPositionId() {
	return staffPositionId;
}

public void setStaffPositionId(String staffPositionId) {
	this.staffPositionId = staffPositionId;
}

public boolean isCloseTask() {
	return closeTask;
}

public void setCloseTask(boolean closeTask) {
	this.closeTask = closeTask;
}



}

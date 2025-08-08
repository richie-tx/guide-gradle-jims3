package pd.attorney;

public class Attorney
{
    private String userid;
    private String username;
    private String displayname;
    private String agency;
    private String department;
    private String email;
    private boolean disabled;
	    
    public String getUserid()
    {
	return this.userid;
    }
    
    public void setUserid(String userId){
	this.userid = userId;
    }
    
    public String getUsername()
    {
	return this.username;
    }
    
    public void setUsername(String username){
	this.username = username;
    }
    
    public String getDisplayname()
    {
	return this.displayname;
    }
    
    public void setDisplayname(String displayname){
	this.displayname = displayname;
    }
    
    public String getAgency()
    {
	return this.agency;
    }
    
    public void setAgency(String agency){
	this.agency = agency;
    }
    
    public String getDepartment()
    {
	return this.department;
    }
    
    public void setDepartment(String department){
	this.department = department;
    }
    
    public String getEmail()
    {
	return this.email;
    }
    
    public void setEmail(String email){
	this.email = email;
    }
    
    public boolean getDisabled()
    {
	return this.disabled;
    }
    
    public void setDisabled(boolean disabled){
	this.disabled = disabled;
    }
}

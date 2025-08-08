package mojo.km.security;


// Response object for the update password web service. Represents data.

public class UpdatePasswordResponse  
{
      
    private boolean invaliduser;
    private boolean passwordnotstrong;
    private boolean alreadyusedpassword;
    

    public boolean isInvaliduser()
    {
	return invaliduser;
    }

    public void setInvaliduser(boolean invaliduser)
    {
	this.invaliduser = invaliduser;
    }

    public boolean isPasswordnotstrong()
    {
	return passwordnotstrong;
    }

    public void setPasswordnotstrong(boolean passwordnotstrong)
    {
	this.passwordnotstrong = passwordnotstrong;
    }

    public boolean isAlreadyusedpassword()
    {
	return alreadyusedpassword;
    }

    public void setAlreadyusedpassword(boolean alreadyusedpassword)
    {
	this.alreadyusedpassword = alreadyusedpassword;
    }

  }

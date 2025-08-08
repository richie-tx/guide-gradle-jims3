package ui.juvenilecase.form;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import messaging.codetable.reply.CodeResponseEvent;

import org.apache.struts.action.ActionForm;

import ui.common.ComplexCodeTableHelper;
import ui.juvenilecase.UIJuvenileHelper;

/*
 * 
 */
public class JuvenileAliasInfoForm extends ActionForm implements Comparable
{
	private String	juvenileNum;
	private Date	entryDate;
	private String	firstName;
	private String	lastName;
	private String	middleName;
	private String	aliasName;
	// Use ENUM if needed
	private String	juvenileType;
	private String	juvenileTypeDescription;
	private String	action;
	private String	raceId;
	private String	sexId;
	private Date	dateOfBirth;

	private String	notes;
	private String	id;
	private Collection<CodeResponseEvent> aliasCodes;

	
	
/*
	 * 
	 */
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	/*
	 * 
	 */
	public void setDateOfBirth( Date dateOfBirth )
	{
		this.dateOfBirth = dateOfBirth;
	}

	public String getRaceId()
	{
		return raceId;
	}

	public void setRaceId( String raceId )
	{
		this.raceId = raceId;
	}

	public String getSexId()
	{
		return sexId;
	}

	public void setSexId( String sexId )
	{
		this.sexId = sexId;
	}

	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	public void setJuvenileNum( String juvenileNum )
	{
		this.juvenileNum = juvenileNum;
	}

	public String getAction()
	{
		return action;
	}

	public void setAction( String action )
	{
		this.action = action;
	}

	public String getId()
	{
		return id;
	}

	public void setId( String id )
	{
		this.id = id;
	}

	public Date getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate( Date entryDate )
	{
		this.entryDate = entryDate;
	}

	public String getJuvenileType()
	{
		return juvenileType;
	}

	public void setJuvenileType( String juvenileType )
	{
		this.juvenileType = juvenileType;
	}

	public String getNotes()
	{
		return notes;
	}

	public void setNotes( String notes )
	{
		this.notes = notes;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName( String firstName )
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName( String lastName )
	{
		this.lastName = lastName;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public void setMiddleName( String middleName )
	{
		this.middleName = middleName;
	}

	/*
	 * 
	 */
	public String getJuvenileTypeDescription()
	{
		if( this.juvenileType != null )
		{
			this.juvenileTypeDescription = ComplexCodeTableHelper.getDescrByAliasCode( 
					this.getAliasCodes(), this.juvenileType );
		}
		return this.juvenileTypeDescription;
	}

	public String getAliasName()
	{
		return aliasName;
	}

	public void setAliasName( String aliasName )
	{
		this.aliasName = aliasName;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo( Object obj )
  {
    if(obj == null )
    {
      return -1;
    }
    
    JuvenileAliasInfoForm evt = (JuvenileAliasInfoForm) obj;
    int result = 0;
    
    if( this.entryDate != null || evt.getEntryDate() != null )
    {
        if (evt.getEntryDate() == null )
        {
            return 1;
        }

        if (this.entryDate == null )
        {
          return -1;
        }

        result = evt.getEntryDate().compareTo(this.entryDate);
    }

    return result;
  }

	public Collection<CodeResponseEvent> getAliasCodes()
	{
	    return UIJuvenileHelper.fetchAliasNameTypes();
	}

	public void setAliasCodes(List<CodeResponseEvent> aliasCodes)
	{
	    this.aliasCodes = aliasCodes;
	}
	
	
	
 }

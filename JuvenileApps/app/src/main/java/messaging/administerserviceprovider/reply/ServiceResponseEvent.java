/*
 * Create on May 24, 2006
 */

package messaging.administerserviceprovider.reply;

import java.util.Comparator ;
import java.util.Date ;
import java.util.List;

import mojo.km.messaging.ResponseEvent ;

public class ServiceResponseEvent extends ResponseEvent implements Comparable
{
	private String serviceId ;
	private String serviceName ;

	private Date programStartDate ;
	private Date programEndDate ;

	private String maxEnrollment ;
	
	private String serviceProviderId ;
	private String serviceProviderName ;
	private boolean inHouse ;
	private String serviceProviderStatusId ;

	private String programName ;
	private String programCode ;
	private String programId ;

	private String targetInterventionId ;

	private String serviceTypeId ;
	private String serviceType ;
	private String serviceCode ;

	private String locationName ;

	private double cost ;
	private String rateId ;
	private String description ;
	private String statusId ;
	private String SPValue ;

	private boolean selected = false;
	
	private List services;

	/**
	 * @return
	 */
	public String getServiceName( )
	{
		return serviceName ;
	}

	/**
	 * @param string
	 */
	public void setServiceName( String string )
	{
		serviceName = string ;
	}

	/**
	 * @return
	 */
	public String getServiceId( )
	{
		return serviceId ;
	}

	/**
	 * @param i
	 */
	public void setServiceId( String i )
	{
		serviceId = i ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo( Object obj )
	{
		if( obj == null )
			return -1 ;
		
		ServiceResponseEvent evt = (ServiceResponseEvent)obj ;
		int result = 0 ;

		try
		{
			if( this.getServiceId( ) != null || evt.getServiceId( ) != null )
			{
				if( evt.getServiceId( ) == null && !( evt.getServiceId( ).trim( ).equals( "" ) ) )
					return 1 ; // this makes any null objects go to the bottom change
											// this to -1 if you want the top of the list
				if( this.getServiceId( ) == null && !( this.getServiceId( ).trim( ).equals( "" ) ) )
					return -1 ; // this makes any null objects go to the bottom change
											// this to 1 if you want the top of the list

				if( Integer.parseInt( this.getServiceId( ) ) == Integer.parseInt( evt.getServiceId( ) ) )
				{
					return 0 ;
				}
				else if( Integer.parseInt( this.getServiceId( ) ) < Integer.parseInt( evt.getServiceId( ) ) )
				{
					return -1 ;
				}
				else
				{
					return 1 ;
				}

			}
		}
		catch( NumberFormatException e )
		{
			result = 0 ;
		}

		return 0 ;
	}

	/**
	 * @return Returns the maxEnrollment.
	 */
	public String getMaxEnrollment( )
	{
		return maxEnrollment ;
	}

	/**
	 * @param maxEnrollment
	 *          The maxEnrollment to set.
	 */
	public void setMaxEnrollment( String maxEnrollment )
	{
		this.maxEnrollment = maxEnrollment ;
	}

	/**
	 * @return Returns the programEndDate.
	 */
	public Date getProgramEndDate( )
	{
		return programEndDate ;
	}

	/**
	 * @param programEndDate
	 *          The programEndDate to set.
	 */
	public void setProgramEndDate( Date programEndDate )
	{
		this.programEndDate = programEndDate ;
	}

	/**
	 * @return Returns the programStartDate.
	 */
	public Date getProgramStartDate( )
	{
		return programStartDate ;
	}

	/**
	 * @param programStartDate
	 *          The programStartDate to set.
	 */
	public void setProgramStartDate( Date programStartDate )
	{
		this.programStartDate = programStartDate ;
	}

	/**
	 * @return Returns the inHouse.
	 */
	public boolean isInHouse( )
	{
		return inHouse ;
	}

	/**
	 * @param inHouse
	 *          The inHouse to set.
	 */
	public void setInHouse( boolean inHouse )
	{
		this.inHouse = inHouse ;
	}

	/**
	 * @return Returns the programCode.
	 */
	public String getProgramCode( )
	{
		return programCode ;
	}

	/**
	 * @param programCode
	 *          The programCode to set.
	 */
	public void setProgramCode( String programCode )
	{
		this.programCode = programCode ;
	}

	/**
	 * @return Returns the programId.
	 */
	public String getProgramId( )
	{
		return programId ;
	}

	/**
	 * @param programId
	 *          The programId to set.
	 */
	public void setProgramId( String programId )
	{
		this.programId = programId ;
	}

	/**
	 * @return Returns the programName.
	 */
	public String getProgramName( )
	{
		return programName ;
	}

	/**
	 * @param programName
	 *          The programName to set.
	 */
	public void setProgramName( String programName )
	{
		this.programName = programName ;
	}

	/**
	 * @return Returns the serviceProviderId.
	 */
	public String getServiceProviderId( )
	{
		return serviceProviderId ;
	}

	/**
	 * @param serviceProviderId
	 *          The serviceProviderId to set.
	 */
	public void setServiceProviderId( String serviceProviderId )
	{
		this.serviceProviderId = serviceProviderId ;
	}

	/**
	 * @return Returns the serviceProviderName.
	 */
	public String getServiceProviderName( )
	{
		return serviceProviderName ;
	}

	/**
	 * @param serviceProviderName
	 *          The serviceProviderName to set.
	 */
	public void setServiceProviderName( String serviceProviderName )
	{
		this.serviceProviderName = serviceProviderName ;
	}

	/**
	 * @return Returns the serviceProviderStatusId.
	 */
	public String getServiceProviderStatusId( )
	{
		return serviceProviderStatusId ;
	}

	/**
	 * @param serviceProviderStatusId
	 *          The serviceProviderStatusId to set.
	 */
	public void setServiceProviderStatusId( String serviceProviderStatusId )
	{
		this.serviceProviderStatusId = serviceProviderStatusId ;
	}

	/**
	 * @return Returns the serviceType.
	 */
	public String getServiceTypeId( )
	{
		return serviceTypeId ;
	}

	/**
	 * @param serviceType
	 *          The serviceType to set.
	 */
	public void setServiceTypeId( String serviceTypeId )
	{
		this.serviceTypeId = serviceTypeId ;
	}

	/**
	 * @return Returns the targetInterventionId.
	 */
	public String getTargetInterventionId( )
	{
		return targetInterventionId ;
	}

	/**
	 * @param targetInterventionId
	 *          The targetInterventionId to set.
	 */
	public void setTargetInterventionId( String targetInterventionId )
	{
		this.targetInterventionId = targetInterventionId ;
	}

	/**
	 * @return Returns the serviceType.
	 */
	public String getServiceType( )
	{
		return serviceType ;
	}

	/**
	 * @param serviceType
	 *          The serviceType to set.
	 */
	public void setServiceType( String serviceType )
	{
		this.serviceType = serviceType ;
	}

	/**
	 * @return Returns the serviceCode.
	 */
	public String getServiceCode( )
	{
		return serviceCode ;
	}

	/**
	 * @param serviceCode
	 *          The serviceCode to set.
	 */
	public void setServiceCode( String serviceCode )
	{
		this.serviceCode = serviceCode ;
	}

	/**
	 * @return Returns the locationName.
	 */
	public String getLocationName( )
	{
		return locationName ;
	}

	/**
	 * @param locationName
	 *          The locationName to set.
	 */
	public void setLocationName( String locationName )
	{
		this.locationName = locationName ;
	}

	/**
	 * @return Returns the cost.
	 */
	public double getCost( )
	{
		return cost ;
	}

	/**
	 * @param cost
	 *          The cost to set.
	 */
	public void setCost( double cost )
	{
		this.cost = cost ;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription( )
	{
		return description ;
	}

	/**
	 * @param description
	 *          The description to set.
	 */
	public void setDescription( String description )
	{
		this.description = description ;
	}

	/**
	 * @return Returns the rateId.
	 */
	public String getRateId( )
	{
		return rateId ;
	}

	/**
	 * @param rateId
	 *          The rateId to set.
	 */
	public void setRateId( String rateId )
	{
		this.rateId = rateId ;
	}

	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId( )
	{
		return statusId ;
	}

	/**
	 * @param statusId
	 *          The statusId to set.
	 */
	public void setStatusId( String statusId )
	{
		this.statusId = statusId ;
	}

	/*
	 */
	public static Comparator serviceNameComparator = new Comparator( )
	{
		public int compare( Object serviceNameCom, Object otherServiceNameCom )
		{
			String sName = ( (ServiceResponseEvent)serviceNameCom ).getServiceName( ) ;
			String otherSName = ( (ServiceResponseEvent)otherServiceNameCom ).getServiceName( ) ;
			return sName.compareTo( otherSName ) ;
		}
	} ;

	/*
	 */
	public static Comparator providerNameComparator = new Comparator( )
	{
		public int compare( Object providerNameCom, Object otherProviderNameCom )
		{
			String sName = ( (ServiceResponseEvent)providerNameCom ).getServiceProviderName( ) ;
			String otherSName = ( (ServiceResponseEvent)otherProviderNameCom ).getServiceProviderName( ) ;
			return sName.compareTo( otherSName ) ;
		}
	} ;

	/**
	 * @return Returns the sPValue.
	 */
	public String getSPValue( )
	{
		return SPValue ;
	}

	/**
	 * @param value The sPValue to set.
	 */
	public void setSPValue( String value )
	{
		SPValue = value ;
	}

	public boolean isSelected( )
	{
		return selected ;
	}

	public void setSelected( boolean selected )
	{
		this.selected = selected ;
	}

	/**
	 * @return the services
	 */
	public List getServices() {
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(List services) {
		this.services = services;
	}
	
	
}
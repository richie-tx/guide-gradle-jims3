/*
 * Created on Jun 10, 2005
 *
 */
package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.juvenile.reply.JuvenileDrugsResponseEvent;
import messaging.juvenilecase.GetJuvenileTraitTypesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.juvenilecase.reply.TraitTypeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;

import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.TraitComparator;
import ui.juvenilecase.UIJuvenileCaseworkHelper;

/**
 * @author jfisher
 * 
 */
public class TraitsForm extends ActionForm
{
	private String juvenileNumber = UIConstants.EMPTY_STRING ; // current Juvenile Number

	private String categoryName ; // used to be tab
	private String action ;
	private boolean allowUpdates = false;
	private String entryDate ;
	private String traitTypeId ;
	private String traitTypeDescriptionId ;
	private String traitTypeDescription ;
	private String traitComments ;
	private Integer expandTraits;

	/* map of description traits with parentId as the key and 
	   array List of children as the values */
	private Map descriptionTraitMap ; 

	private Collection rootTraitTypes ; // collection of parent trait types

	// map of child description traits for easier lookup, given a traitTypeId
	private Map leafTraitMap ; 

	// map of root trait TypeIds for easier lookup, given a parentId
	private Map rootTraitMap ;

	private Collection traitsSearchResult ; // Searched Traits result list

	/* holds all the Juveniles TRAITs in the TRAIT TYPE TABLE 
	 * key is parent trait ID, element is HashMap of Child Trait Types 
	 * with key TraitTypeId and value ArrayList */
	private Map traitMap = null ;

	// newly added traits that have not been persisted
	private Collection newTraits ;

	private String [ ] removeTraits ; // Id's of traits to be removed

	private Map casefiles ; // Casefiles
	private Collection casefilesAbuse; // 38834 to sort by sequence number

	private String supervisionNum ; // current supervision num

	// Denotes whether we are coming in from profile or casefile
	private boolean isUICasefile = false ; 

	private String selectedValue = UIConstants.EMPTY_STRING ;
	private String statusId ;
	private String status ;

	// Nothing below here is needed yet.
	private Collection traitTypes ;

	private Collection traits = null ; // List of juvenile Traits

	private Collection drugCharacteristics = new ArrayList( ) ;

	private Map drugCharacteristicsMap = new HashMap( ) ;

	private Collection descriptions ;

	private boolean cameFromCasefile = false ;
	
	// for trait status update
	private String newStatusId;
	private List updateTraitsList;
	private List updateTraitStatuses;
	private List updateTraitCasefile;
	private boolean activeCasefileFound;
	private String confirmMessage;
	
	//added for facility
	private boolean isUIFacility = false ; 
	
	//added for US 14452 - Facility Traits link in Modify Admit flow
	//added for US 14452 - Facility Traits link in Modify Admit flow
	private String isModifyAdmitView;  //bug #50640
	private String isReleaseView;//bug #50640
	private String isAdmitView;//bug #50640
	
	//added for US 42660
	private String facilityAdmitOID;
	private String currentFacilityOID;
	//added for task 128545
	private String transferAdmitOID;
	

	//added for US 40635
	private String informationSrcCd;
	private String informationSrcDesc;
	private Collection informationSources;
	
	private String traitFrom;
		
	/*
	 */
	public void clearAll( )
	{
		traitTypeId = null ;
		traitTypeDescriptionId = null ;

		/* map of description traits with parentId as the key 
		   and array List of children as the values */
		descriptionTraitMap = null ; 
		rootTraitTypes = null ; // collection of parent trait types
		traitsSearchResult = null ; // Searched Traits result list

		/* HOLDS ALL THE Juveniles TRAITs in the TRAIT TYPE TABLE
		   key is trait ID, element is Trait */
		traitMap = null ;
		
		isUICasefile = false ;
		selectedValue = UIConstants.EMPTY_STRING ;
		traitFrom = "";
		/*transferAdmitOID=null;
		facilityAdmitOID=null;
		currentFacilityOID=null;*/
	}

	/**
	 * @return
	 */
	public Collection getDescriptions( )
	{
		return this.descriptions ;
	}

	/**
	 * @return
	 */
	public Collection getTraits( )
	{
		return traits ;
	}

	/**
	 * @param traitTypeId
	 * @return
	 */
	private Collection getChildTraits( String aParentTraitId )
	{
		Collection childTraits = new ArrayList( ) ;

		return childTraits ;
	}

	/**
	 * @param collection
	 */
	public void setDescriptions( Collection collection )
	{
		descriptions = collection ;
	}

	/*
	 * @param traitKey
	 * @param descriptions
	 */
	public void addTraitDescriptions( String traitKey, Collection descriptions )
	{
		traitMap.put( traitKey, descriptions ) ;
	}

	/**
	 * Set the trait type description combo box
	 * 
	 */
	public void initTraitDescriptions( )
	{
		String tCategoryName = null ;
		
		if( this.categoryName != null )
		{
			if( PDJuvenileCaseConstants.GANG_TAB.equalsIgnoreCase( tCategoryName ) )
			{
				tCategoryName = PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_GANGS ;
			}
			else if( PDJuvenileCaseConstants.SCHOOL_TAB.equalsIgnoreCase( tCategoryName ) )
			{
				tCategoryName = PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_SCHOOL ;
			}
			else if( PDJuvenileCaseConstants.SPECIAL_INTEREST_TAB.equalsIgnoreCase( tCategoryName ) )
			{
				tCategoryName = PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_SPECIAL_INTERESTS ;
			}
		}

		if( tCategoryName != null )
		{
			Collection traitTypeDescriptions = UIJuvenileCaseworkHelper.fetchParentChildTraitTypesForCategory( tCategoryName, false ) ;
			if( traitTypeDescriptions == null )
			{
				traitTypeDescriptions = new ArrayList( ) ;
			}
			this.setDescriptions( traitTypeDescriptions ) ;
		}
		else
		{
			GetJuvenileTraitTypesEvent event = (GetJuvenileTraitTypesEvent)
					EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILETRAITTYPES ) ;

			if( this.traitTypeId == null || this.traitTypeId.equals( UIConstants.EMPTY_STRING ) )
			{
				TraitTypeResponseEvent firstTraitType = this.getFirstTraitType( ) ;
				event.setTraitType( firstTraitType.getTraitTypeId( ) ) ;
			}
			else
			{
				event.setTraitType( this.traitTypeId ) ;
			}

			IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
			dispatch.postEvent( event ) ;

			IEvent replyEvent = dispatch.getReply( ) ;
			CompositeResponse responses = (CompositeResponse)replyEvent ;

			Map traitTypeMap = MessageUtil.groupByTopic( responses ) ;
			Object traitDescriptionObj = traitTypeMap.get( this.traitTypeId ) ;

			if( traitDescriptionObj != null )
			{
				Collection traitDescriptions = (Collection)traitDescriptionObj ;
				if( traitDescriptions.size( ) > 1 )
				{
					Collections.sort( (List)traitDescriptions ) ;
				}
				this.setDescriptions( traitDescriptions ) ;
			}
			else
			{
				this.setDescriptions( new ArrayList( ) ) ;
			}
		}
	}

	/**
	 * @return
	 */
	private TraitTypeResponseEvent getFirstTraitType( )
	{
		TraitTypeResponseEvent firstTraitType = null ;

		if( this.rootTraitTypes != null )
		{
			Iterator i = this.rootTraitTypes.iterator( ) ;
			if( i.hasNext( ) )
			{
				firstTraitType = (TraitTypeResponseEvent)i.next( ) ;
			}
		}
		
		return firstTraitType ;
	}

	/*
	 * @param traitMap
	 */
	public void setTraitMap( Map traitMap )
	{
		this.traitMap = traitMap ;
	}

	/**
	 * @return
	 */
	public String getTraitTypeId( )
	{
		return traitTypeId ;
	}

	/**
	 * @param string
	 */
	public void setTraitTypeId( String string )
	{
		traitTypeId = string ;
	}

	/**
	 * @return
	 */
	public String getTraitTypeDescriptionId( )
	{
		return traitTypeDescriptionId ;
	}

	/**
	 * @param string
	 */
	public void setTraitTypeDescriptionId( String string )
	{
		traitTypeDescriptionId = string ;
	}

	/*
	 * @return
	 */
	public String getTraitType( )
	{
		return traitNameGivenId( this.traitTypeId ) ;
	}

	/*
	 * @param aTraitTypeId
	 * @return
	 */
	public String traitNameGivenId( String aTraitTypeId )
	{
		if( aTraitTypeId == null )
		{
			return( UIConstants.EMPTY_STRING ) ;
		}
		
		if( this.leafTraitMap != null &&
				leafTraitMap.containsKey( aTraitTypeId ) )
		{
			TraitTypeResponseEvent resp = (TraitTypeResponseEvent)leafTraitMap.get( aTraitTypeId ) ;
			
			return resp.getTraitName( ) ;
		}
		
		if( this.rootTraitMap != null &&
				rootTraitMap.containsKey( aTraitTypeId ) )
		{
			TraitTypeResponseEvent resp = (TraitTypeResponseEvent)rootTraitMap.get( aTraitTypeId ) ;
			
			return resp.getTraitName( ) ;
		}
		
		return( UIConstants.EMPTY_STRING ) ;
	}

	/**
	 * @return
	 */
	public String getAction( )
	{
		return action ;
	}

	/**
	 * @param string
	 */
	public void setAction( String string )
	{
		action = string ;
	}

	/**
	 * @return the allowUpdates
	 */
	public boolean isAllowUpdates() {
		return allowUpdates;
	}

	/**
	 * @param allowUpdates the allowUpdates to set
	 */
	public void setAllowUpdates(boolean allowUpdates) {
		this.allowUpdates = allowUpdates;
	}

	/**
	 * @return
	 */
	public Collection getCasefiles( )
	{
		if( casefiles == null )
		{
			return new ArrayList( ) ;
		}
		else
		{
			return casefiles.values( ) ;
		}
	}

	/**
	 * @param collection
	 */
	public void setCasefiles( Collection<JuvenileCasefileSearchResponseEvent> collection )
	{
		this.casefiles = new HashMap( ) ;
		
		
		for( JuvenileCasefileSearchResponseEvent casefile : collection )
		{
			this.casefiles.put( casefile.getSupervisionNum( ), casefile ) ;
		}
		
	}

	/*
	 * @return
	 */
	private Map getCasefileMap( )
	{
		if( casefiles == null )
		{
			return( new HashMap() ) ;
		}
		else
		{
			return casefiles ;
		}
	}

	/*
	 * @return
	 */
	public JuvenileCasefileSearchResponseEvent getCurrentCasefile( )
	{
		JuvenileCasefileSearchResponseEvent casefile = null ;

		if( supervisionNum != null )
		{
			casefile = (JuvenileCasefileSearchResponseEvent)this.getCasefileMap().get( this.supervisionNum ) ;
		}
		
		return casefile ;
		
	}

	
	
	/**
	 * @return
	 */
	public boolean isDescriptionForm( )
	{
		if( traitTypes == null || traitTypes.size( ) < 2 )
		{
			return true ;
		}

		return false ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void clear( )
	{
		this.traitTypeId = null ;
		this.traitTypeDescriptionId = null ;
		
		transferAdmitOID=null;
		facilityAdmitOID=null;
		currentFacilityOID=null;
	}

	/**
	 * @return
	 */
	public String getCategoryName( )
	{
		return categoryName ;
	}

	/**
	 * @param string
	 */
	public void setCategoryName( String string )
	{
		categoryName = string ;
	}

	/**
	 * @return
	 */
	public String [ ] getRemoveTraits( )
	{
		return removeTraits ;
	}

	/**
	 * @param strings
	 */
	public void setRemoveTraits( String [ ] strings )
	{
		removeTraits = strings ;
	}

	/**
	 * @return
	 */
	public String getTraitComments( )
	{
		return traitComments ;
	}

	/**
	 * @param string
	 */
	public void setTraitComments( String string )
	{
		traitComments = string ;
	}

	/**
	 * @return the expandTraits
	 */
	public Integer getExpandTraits() {
		return expandTraits;
	}

	/**
	 * @param expandTraits the expandTraits to set
	 */
	public void setExpandTraits(Integer expandTraits) {
		this.expandTraits = expandTraits;
	}

	/**
	 * @return
	 */
	public Collection getNewTraits( )
	{
		if( this.newTraits == null )
		{
			this.newTraits = new ArrayList( ) ;
		}
		return this.newTraits ;
	}

	/**
	 * @param collection
	 */
	public void setNewTraits( Collection collection )
	{
		newTraits = collection ;
	}

	/**
	 * @return
	 */
	public String getSupervisionNum( )
	{
		return supervisionNum ;
	}

	/**
	 * @param string
	 */
	public void setSupervisionNum( String string )
	{
		supervisionNum = string ;
	}

	/**
	 * 
	 */
	public void clearNewTraits( )
	{
		this.newTraits = new ArrayList( ) ;
		this.traitTypeDescriptionId = null ;
		this.traitComments = null ;
		selectedValue = UIConstants.EMPTY_STRING ;
	}

	/**
	 * 
	 */
	public String getTraitTypeDescription( )
	{
		return traitNameGivenId( this.traitTypeDescriptionId ) ;
	}

	/**
	 * @return
	 */
	public Collection getRootTraitTypes( )
	{
		return rootTraitTypes ;
	}

	/**
	 * @param collection
	 */
	public void setRootTraitTypes( Collection collection )
	{
		rootTraitTypes = collection ;
	}

	/**
	 * 
	 */
	public void clearAddTraitsForm( )
	{
		this.traitComments = UIConstants.EMPTY_STRING ;
		/* NOTE: do not clear trait type ID since the use-case is still
		   in that trait context i.e. gang, school. Only by selecting the
		   trait tab will you be in a different context.
		   this.traitTypeId = UIConstants.EMPTY_STRING;  
		*/
		this.traitTypeDescriptionId = UIConstants.EMPTY_STRING ;
		this.statusId = UIConstants.EMPTY_STRING ;
		selectedValue = UIConstants.EMPTY_STRING ;
		this.informationSrcCd = UIConstants.EMPTY_STRING ;
	}

	/**
	 * @return
	 */
	public Map getDescriptionTraitMap( )
	{
		return descriptionTraitMap ;
	}

	/**
	 * @param map
	 */
	public void setDescriptionTraitMap( Map map )
	{
		descriptionTraitMap = map ;
	}

	/**
	 * @return
	 */
	public Collection getTraitsSearchResult( )
	{
		return traitsSearchResult ;
	}

	/**
	 * @param collection
	 */
	public void setTraitsSearchResult( Collection collection )
	{
		traitsSearchResult = collection ;
	}

	/**
	 * @return
	 */
	public Map getLeafTraitMap( )
	{
		return leafTraitMap ;
	}

	/**
	 * @return
	 */
	public Map getRootTraitMap( )
	{
		return rootTraitMap ;
	}

	/**
	 * @param map
	 */
	public void setLeafTraitMap( Map map )
	{
		leafTraitMap = map ;
	}

	/**
	 * @param map
	 */
	public void setRootTraitMap( Map map )
	{
		rootTraitMap = map ;
	}

	/**
	 * @return
	 */
	public String getJuvenileNumber( )
	{
		return juvenileNumber ;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNumber( String string )
	{
		if( juvenileNumber == null || !(juvenileNumber.equalsIgnoreCase( string )) )
		{
			clearAll( ) ;
		}
		juvenileNumber = string ;
	}

	/**
	 * @return
	 */
	public Collection getTraitTypes( )
	{
		return traitTypes ;
	}

	/**
	 * @param collection
	 */
	public void setTraitTypes( Collection collection )
	{
		traitTypes = collection ;
	}

	/**
	 * @param date
	 */
	public void setEntryDate( String date )
	{
		entryDate = date ;
	}
	/**
	 * @param date
	 */
	public String getEntryDate()
	{
		return this.entryDate ;
	}

	/**
	 * get the sorted entry dates
	 * 
	 * @return
	 */
	public Collection getEntryDates( )
	{
		Collection entryDates = new ArrayList( ) ;
		Iterator it = drugCharacteristics.iterator( ) ;
		while( it.hasNext( ) )
		{
			JuvenileDrugsResponseEvent respEvent = (JuvenileDrugsResponseEvent)it.next( ) ;
			entryDates.add( respEvent.getEntryDate( ) ) ;
		}
		
		return entryDates ;
	}

	/**
	 * @return
	 */
	public Map getdrugCharacteristicsMap( )
	{
		return drugCharacteristicsMap ;
	}

	/**
	 * @param map
	 */
	public void setdrugCharacteristicsMap( Map map )
	{
		drugCharacteristicsMap = map ;
	}

	/**
	 * Note Category Name and JUV number should have been set and initialized
	 * prior to calling this method.
	 * 
	 */
	public void initializeTraitForm( boolean reloadJuvTraits )
	{	// *** INITIALIZE TRAITS FORM IF NOT ALREADY DONE
		// get juvenile number from header form and copy to juvenileTraits form
		populateAllJuvTraits( reloadJuvTraits ) ;

		// ALL TRAITS IN TRAIT TYPE TABLE
		Map descriptionTraitMap = this.getDescriptionTraitMap( ) ;

		// get all traits
		if( descriptionTraitMap == null || descriptionTraitMap.isEmpty())
		{
			descriptionTraitMap = UIJuvenileCaseworkHelper.fetchAllTraitTypes( ) ;
		}
		
		this.setDescriptionTraitMap( descriptionTraitMap ) ;
		
		// create a map of leaf traits (with the child's id as the key)
		if( this.getLeafTraitMap( ) == null || this.getLeafTraitMap().isEmpty( ) )
		{
			Map leafTraitMap = new HashMap( ) ;
			if( getDescriptionTraitMap( ) != null )
			{
				for( Iterator iter = this.getDescriptionTraitMap( ).values( ).iterator( ); 
						iter.hasNext( ); /*empty*/ )
				{
					ArrayList<TraitTypeResponseEvent> list = (ArrayList)iter.next( ) ;
					for( TraitTypeResponseEvent trait : list )
					{
						leafTraitMap.put( trait.getTraitTypeId( ), trait ) ;
					}
				}
			}
			this.setLeafTraitMap( leafTraitMap ) ;
		}

		Collection rootTraitTypes ; // parent Trait Types
		// get the list of parent traits (to be displayed as Traits Type)
		if( this.getCategoryName( ) == null || 
				this.getCategoryName( ).equalsIgnoreCase( UIConstants.EMPTY_STRING ) )
		{
			rootTraitTypes = UIJuvenileCaseworkHelper.fetchParentTraitTypes( PDJuvenileCaseConstants.PARENT_TRAIT_IDENTIFIER_VALUE) ;
		}
		else
		{ // SUBSET OF PARENT TRAIT TYPES
			rootTraitTypes = UIJuvenileCaseworkHelper.fetchParentChildTraitTypesForCategory( this.getCategoryName( ), false ) ;
		}

		this.setRootTraitTypes( rootTraitTypes ) ; // Parent Trait Types
		populateJuvSearchTraits( ) ;

		// create hashMap of parent traits for faster lookup
		Map rootTraitMap = new HashMap( ) ;
		for( Iterator iter = this.getRootTraitTypes( ).iterator( ); 
					iter.hasNext( ); /*empty*/)
		{
			TraitTypeResponseEvent trait = (TraitTypeResponseEvent)iter.next( ) ;
			rootTraitMap.put( trait.getTraitTypeId( ), trait ) ;
		}

		this.setRootTraitMap( rootTraitMap ) ;
	}

	/*
	 * @param reloadJuvTraits
	 */
	public void populateAllJuvTraits( boolean reloadJuvTraits )
	{
		Map<String,JuvenileTraitResponseEvent> newTraitMap = this.getTraitMap();
		if( reloadJuvTraits || newTraitMap == null || newTraitMap.size( ) < 1 )
		{
			//Added for U.S. #42660
			if(this.categoryName.equals(PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_FACILITY_TRAITS)){
				newTraitMap =  UIJuvenileCaseworkHelper.fetchFacilityTraits(this.juvenileNumber, this.currentFacilityOID);
			}else{
				newTraitMap = UIJuvenileCaseworkHelper.fetchTraits( this.juvenileNumber ) ;
			}
		}
		this.traitMap = newTraitMap ;
	}

	/*
	 */
	public void populateJuvSearchTraits( )
	{
		ArrayList searchedTraits = new ArrayList( ) ;
		this.traitsSearchResult = searchedTraits ;

		if( traitMap == null || traitMap.size( ) < 1 )
		{
			return ;
		}

		if( this.traitTypeDescriptionId != null && 
				!(this.traitTypeDescriptionId.equals( UIConstants.EMPTY_STRING )) )
		{
			traitsSearchResult = getJuvChildTraits( this.traitTypeDescriptionId ) ;
			if( traitsSearchResult != null  &&  traitsSearchResult.size( ) > 1 )
			{
				Collections.sort( (List)traitsSearchResult, new TraitComparator( ) ) ;
			}

			return ;
		}
		else
		{
			if( this.traitTypeId == null || 
					this.traitTypeId.equalsIgnoreCase( UIConstants.EMPTY_STRING ) )
			{
				return ;
			}
			
			if( descriptionTraitMap != null && descriptionTraitMap.size( ) > 0 )
			{
				if( descriptionTraitMap.containsKey( this.traitTypeId ) )
				{ // user selected a specific Trait Type - get all its children
					ArrayList<TraitTypeResponseEvent> myTempList = (ArrayList)descriptionTraitMap.get( this.traitTypeId ) ;
					if( myTempList != null && myTempList.size( ) > 0 )
					{
						for( TraitTypeResponseEvent myRespEvt: myTempList )
						{
							Collection myChildTraits = getJuvChildTraits( myRespEvt.getTraitTypeId( ) ) ;
							if( myChildTraits != null )
							{
								searchedTraits.addAll( myChildTraits ) ;
							}
						}
					}
				}
				else if( this.traitTypeId.equalsIgnoreCase( "all" ) )
				{  /* user selected "all" for Trait Type (the parent)
					    for each parent, get its children */
					Iterator parents = rootTraitTypes.iterator( ) ;
					while( parents.hasNext( ) )
					{
						TraitTypeResponseEvent parTrait = (TraitTypeResponseEvent)parents.next( ) ;
						ArrayList<TraitTypeResponseEvent> myTempList = (ArrayList)descriptionTraitMap.get( parTrait.getTraitTypeId( ) ) ;
						if( myTempList != null && myTempList.size( ) > 0 )
						{
							for( TraitTypeResponseEvent myRespEvt: myTempList )
							{
								Collection myChildTraits = getJuvChildTraits( myRespEvt.getTraitTypeId( ) ) ;
								if( myChildTraits != null )
								{
									searchedTraits.addAll( myChildTraits ) ;
								}
							}
						}
					} // while
				}
			}
		}

		// there are no traits specified so cannot get the traits
		if( traitsSearchResult.size( ) > 1 )
		{
			Collections.sort( (List)traitsSearchResult, new TraitComparator( ) ) ;
		}
	}

	/*
	 * @param aDescriptionTraitTypeId
	 * @return
	 */
	private Collection getJuvChildTraits( String aDescriptionTraitTypeId )
	{
		if( aDescriptionTraitTypeId != null && 
				!( aDescriptionTraitTypeId.equals( UIConstants.EMPTY_STRING ) && 
						traitMap != null ) )
		{
			if( traitMap.containsKey( aDescriptionTraitTypeId ) )
			{
				return (ArrayList)traitMap.get( aDescriptionTraitTypeId ) ;
			}
		}

		return null ;
	}

	/**
	 * @return
	 */
	public Map getTraitMap( )
	{
		return traitMap ;
	}

	/**
	 * @return
	 */
	public boolean isUICasefile( )
	{
		return isUICasefile ;
	}

	/**
	 * @param b
	 */
	public void setUICasefile( boolean b )
	{
		isUICasefile = b ;
	}

	/**
	 * @return
	 */
	public String getSelectedValue( )
	{
		return selectedValue ;
	}

	/**
	 * @param string
	 */
	public void setSelectedValue( String string )
	{
		selectedValue = string ;
	}

	/**
	 * @return Returns the status.
	 */
	public String getStatus( )
	{
		return status ;
	}

	/**
	 * @param status
	 *          The status to set.
	 */
	public void setStatus( String status )
	{
		this.status = status ;
	}

	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId( )
	{
		return statusId ;
	}

	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId( String aStatusId )
	{
		this.statusId = aStatusId ;
		setStatus( UIConstants.EMPTY_STRING ) ;
		
		if( statusId != null && !statusId.equals( UIConstants.EMPTY_STRING ) )
		{
			status = SimpleCodeTableHelper.getDescrByCode( PDCodeTableConstants.FAMILY_TRAIT_STATUS, statusId ) ;
		}
	}

	public boolean getCameFromCasefile( )
	{
		return cameFromCasefile ;
	}

	public void setCameFromCasefile( boolean cameFromCasefile )
	{
		this.cameFromCasefile = cameFromCasefile ;
	}


	/**
	 * @return the newStatusId
	 */
	public String getNewStatusId() {
		return newStatusId;
	}

	/**
	 * @param newStatusId the newStatusId to set
	 */
	public void setNewStatusId(String newStatusId) {
		this.newStatusId = newStatusId;
	}

	/**
	 * @return the updateTraitStatuses
	 */
	public List getUpdateTraitStatuses() {
		return updateTraitStatuses;
	}

	/**
	 * @param updateTraitStatuses the updateTraitStatuses to set
	 */
	public void setUpdateTraitStatuses(List updateTraitStatuses) {
		this.updateTraitStatuses = updateTraitStatuses;
	}

	/**
	 * @return the updateTraitsList
	 */
	public List getUpdateTraitsList() {
		return updateTraitsList;
	}

	/**
	 * @param updateTraitsList the updateTraitsList to set
	 */
	public void setUpdateTraitsList(List updateTraitsList) {
		this.updateTraitsList = updateTraitsList;
	}

	/**
	 * @return the updateTraitCasefile
	 */
	public List getUpdateTraitCasefile() {
		return updateTraitCasefile;
	}

	/**
	 * @param updateTraitCasefile the updateTraitCasefile to set
	 */
	public void setUpdateTraitCasefile(List updateTraitCasefile) {
		this.updateTraitCasefile = updateTraitCasefile;
	}

	/**
	 * @return the activeCasefileFound
	 */
	public boolean isActiveCasefileFound() {
		return activeCasefileFound;
	}

	/**
	 * @param activeCasefileFound the activeCasefileFound to set
	 */
	public void setActiveCasefileFound(boolean activeCasefileFound) {
		this.activeCasefileFound = activeCasefileFound;
	}

	/**
	 * @return the confirmMessage
	 */
	public String getConfirmMessage() {
		return confirmMessage;
	}

	/**
	 * @param confirmMessage the confirmMessage to set
	 */
	public void setConfirmMessage(String confirmMessage) {
		this.confirmMessage = confirmMessage;
	}

	public void setUIFacility(boolean isUIFacility) {
		this.isUIFacility = isUIFacility;
	}

	public boolean isUIFacility() {
		return isUIFacility;
	}

	public Collection getCasefilesAbuse() {
		return casefilesAbuse;
	}

	public void setCasefilesAbuse(Collection casefilesAbuse) {
		this.casefilesAbuse = casefilesAbuse;
	}

	public String getFacilityAdmitOID() {
		return facilityAdmitOID;
	}

	public void setFacilityAdmitOID(String facilityAdmitOID) {
		this.facilityAdmitOID = facilityAdmitOID;
	}

	/**
	 * @return the currentFacilityOID
	 */
	public String getCurrentFacilityOID() {
		return currentFacilityOID;
	}

	/**
	 * @param currentFacilityOID the currentFacilityOID to set
	 */
	public void setCurrentFacilityOID(String currentFacilityOID) {
		this.currentFacilityOID = currentFacilityOID;
	}

	/**
	 * @return the isModifyAdmitView
	 */
	public String getIsModifyAdmitView() {
		return isModifyAdmitView;
	}

	/**
	 * @param isModifyAdmitView the isModifyAdmitView to set
	 */
	public void setIsModifyAdmitView(String isModifyAdmitView) {
		this.isModifyAdmitView = isModifyAdmitView;
	}

	/**
	 * @return the isReleaseView
	 */
	public String getIsReleaseView() {
		return isReleaseView;
	}

	/**
	 * @param isReleaseView the isReleaseView to set
	 */
	public void setIsReleaseView(String isReleaseView) {
		this.isReleaseView = isReleaseView;
	}

	/**
	 * @return the isAdmitView
	 */
	public String getIsAdmitView() {
		return isAdmitView;
	}

	/**
	 * @param isAdmitView the isAdmitView to set
	 */
	public void setIsAdmitView(String isAdmitView) {
		this.isAdmitView = isAdmitView;
	}

	public String getInformationSrcCd() {
		return informationSrcCd;
	}

	public void setInformationSrcCd(String informationSrcCd) {
		this.informationSrcCd = informationSrcCd;
	}

	public String getInformationSrcDesc() {
		return informationSrcDesc;
	}

	public void setInformationSrcDesc(String informationSrcDesc) {
		this.informationSrcDesc = informationSrcDesc;
	}

	public Collection getInformationSources() {
		return informationSources;
	}

	public void setInformationSources(Collection informationSources) {
		this.informationSources = informationSources;
	}

	public String getTraitFrom()
	{
	    return traitFrom;
	}

	public void setTraitFrom(String traitFrom)
	{
	    this.traitFrom = traitFrom;
	}
	public String getTransferAdmitOID()
	{
	    return transferAdmitOID;
	}

	public void setTransferAdmitOID(String transferAdmitOID)
	{
	    this.transferAdmitOID = transferAdmitOID;
	}
	
}

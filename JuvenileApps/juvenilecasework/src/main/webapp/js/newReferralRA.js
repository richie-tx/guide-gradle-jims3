
  function Trim(s)
  {
    // Remove leading spaces and carriage returns
    while( (s.substring(0,1) == ' ') || (s.substring(0,1) == '\n') || (s.substring(0,1) == '\r') )
    { 
      s = s.substring( 1,s.length ); 
    }
  
    // Remove trailing spaces and carriage returns
    while( (s.substring(s.length -1 ,s.length) == ' ') || (s.substring(s.length -1,s.length) == '\n') || (s.substring(s.length -1,s.length) == '\r'))
    { 
      s = s.substring(0,s.length -1); 
    }
    
    return s;
  }  

  /* validate the form.
   * 
   */
  function validateFormFields(thisForm) 
  {
    validFields = 1;
    
    var componentName;
    var elem ;
    var elemVal = "";
    var i = 0 ;
    
    for( /*empty*/; validFields  && (i < thisForm.length); i++ )
    {
      if( thisForm.elements[i].type == "hidden")
      { // no need to check hiddens
   	    continue ;
      }
    	
      if(thisForm.elements[i].type == 'radio')
      {
        componentName = thisForm.elements[i].name;

        var hasOne = false;
        var radioList = document.getElementsByName(componentName);
      
        for( var x = 0; x < radioList.length; x++ )
        {
          if( radioList[x].checked )
          {
            hasOne = true;
            break ;
          }
      	}
  
        if( !hasOne )
        {
          validFields = 0;
        }
      }
      else if( thisForm.elements[i].tagName == 'SELECT' || thisForm.elements[i].tagName == 'select' )
      {
    	      	  
        componentName = thisForm.elements[i].name;
        elem = document.getElementsByName(componentName)[0];
        elemVal = Trim( elem.value ) ;
        
        if (!elem.disabled) {
    		
        	if( elemVal == "" )
        	{ // user didnt select an option for the dropdown
        		if( elem.name != "riskMandatoryDetentionCd" )
        		{
        			validFields = 0;
        		}
        		else if( mandatoryDetention )
        		{  /* special case ... if we're looking at the "Detention Reason"
        		 	* dropdown (since it's empty), then we need to look at the 
        		 	* "Mandatory Detention?" field to ensure it's true
        		 	*/
        			validFields = 0;
        		}
        	}
        
        }
        
      }
      else if( thisForm.elements[i].tagName == 'TEXTAREA' || thisForm.elements[i].tagName == 'textarea' )
      {
        componentName = thisForm.elements[i].name;
        elem = document.getElementsByName(componentName)[0];
        elemVal = elem.value;

		/* 25aug2006 - mjt - we check for the -id text- to determine 
		   whether the field is a 'not required' field. a -styleId-
		   is added to the appropriate JSP files to accommodate this.
		*/
		if( thisForm.elements[i].id != 'CommunitySupervisionComments' &&
			(thisForm.elements[i].id.substring( 0, 17 ) != 'notRequiredFields') && 
			(Trim(elemVal) == "") )
        {
          validFields = 0;
        }
      }
      else if(thisForm.elements[i].type == 'text')
      {
        componentName = thisForm.elements[i].name;
        elem = document.getElementsByName(componentName);
        elemVal = elem[0].value;
        
        if( Trim(elemVal) == "" )
        {
          validFields = 0;
        }
      }// else if
  
    }//for
  
    if( ! validFields ) 
    {
      alert("You must provide an answer for each of the questions"); 
      return false;
    }
  
    return true;
  }


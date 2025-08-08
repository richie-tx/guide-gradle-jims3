 /* 26 july 2006 - mjt - this function added so that
	   when a Chronic?" column checkbox is selected, the
			associated checkbox item (to the chronic left)
			is automagically selected. 
	*/
  function autocheckChronic( selectedObj )  
  { // chronicCheckbox0
		var theID = selectedObj.id.substring( 15 )  ; 
		var newID = "nonChronicCheckbox" + (theID) ;


		if( selectedObj.checked )
		{ 
			document.getElementById(newID).checked = true ;
		}
  }

  function autoUncheckChronic( selectedObj )
  {
		var theID = parseInt( selectedObj.id.substring( 18 ) )  ;
		var newID = "chronicCheckbox" + (theID) ;

		if( selectedObj.checked == false )
		{
			document.getElementById(newID).checked = false ;
		}   
  }


	function showHideFields(theForm)
	{
  	if(theForm.title =="In CJPO custody?")
  	{		                
  	 	if(theForm.alt == "Yes")
  	 	{
  			show('expectedReleaseDate', 1, 'row');
  			show('hadHomeVisit', 1, 'row');
     	}
 	  	else if(theForm.alt == "No")
     	{
  	   	show('expectedReleaseDate', 0, 'row');
  			show('hadHomeVisit', 0, 'row');
  		}
  	}
    else if(theForm.title == "In County Placement?" )
    {
   		if(theForm.alt == "Yes")
   		{
   			show('dateExpectedHomeVisit', 1, 'row');
   			show('furlough', 1, 'row');
   		}
   		else if(theForm.alt == "No")
   		{
   			show('dateExpectedHomeVisit', 0, 'row');
   			show('furlough', 0, 'row');
   		}
  	}
  	else if(theForm.title == "Is youth passing all of their classes?")
  	{
   		if(theForm.alt == "No")
   			show('classesFailing', 1, 'row');
   		else if(theForm.alt == "Yes")
   			show('classesFailing', 0, 'row');
	  }
  }


		
/*
function validateRadioFields(thisForm) 
{
	answerMessage = "You must answer each of the questions" ;
	myOption = 0;
	notPassingChecked  = false ;
	inCustodyChecked   = false ;
	inPlacementChecked = false ;
	sanctionsChecked = false ;
	 
	for( var i = 0; i < thisForm.length; i++ )
	{
		if( thisForm.elements[i].type == 'radio' )
		{
			if( thisForm.elements[i].parentNode.parentNode.className != "hidden" )
			{ // check if row isnt hidden
				myOption = 0;
	    	
  			for( var x = 0; x < document.getElementsByName(thisForm.elements[i].name).length; x++)
   	 	  {
      		if(thisForm.elements[i].checked)
      		{
         		myOption = 1;
						switch( thisForm.elements[i].title )
						{
							case "Is youth passing all of their classes?" :
							 	notPassingChecked = true ;
								break;
							case "In CJPO custody?" :
								inCustodyChecked = true ;
								break;
							case "In County Placement?" :
								inPlacementChecked = true ;
								break;
							case "Has the youth received any disciplinary sanctions during this evaluation period?" :
								sanctionsChecked = true ;
								break;
						}	              		
          }//if checked

      	  i++;
    		}// inner for
			}
			else
			{ // hidden field
				i = i + 2;
				myOption = 0;
			}
	    
			i--;	
   	}// if radio
  }// outer for
	
	if( !inCustodyChecked )
	{
    answerMessage = answerMessage + "\nPlease enter [In CJPO custody?]" ;
		myOption = 0 ;
	}
	if( !inPlacementChecked )
	{
    answerMessage = answerMessage + "\nPlease enter [In County Placement?]" ;
		myOption = 0 ;
	}
	if( !sanctionsChecked )
	{
    answerMessage = answerMessage + "\nPlease enter [Has the youth received any disciplinary sanctions during this evaluation period?]" ;
		myOption = 0 ;
	}
	if( !notPassingChecked )
	{
    answerMessage = answerMessage + "\nPlease enter [Is youth passing all of their classes?]" ;
		myOption = 0 ;
	}
	

	if( !myOption  || notPassingChecked || inCustodyChecked || inPlacementChecked )
	{
		// check 'dependency' validations 
		
		for( var i = 0; i < thisForm.length; i++ )
		{
			if( thisForm.elements[i].parentNode.parentNode.className != "hidden" )
			{
				if( thisForm.elements[i].title == "Expected Release Date" && inCustodyChecked )
				{
					if(thisForm.elements[i].value == "" ) 
					{
						answerMessage = answerMessage + "\nPlease enter [Expected Release Date]" ;
						myOption = 0 ;
					}
				}
		 			
				if( thisForm.elements[i].title == "Has youth had a home visit since last evaluation?" && inCustodyChecked )
				{
					if( (!thisForm.elements[i].checked)  &&  (!thisForm.elements[i +1].checked) )
					{
						answerMessage = answerMessage + "\nPlease answer [Has youth had a home visit since last evaluation?]" ; 
						myOption = 0 ;
					}
					i++; continue;
				}
		
			
				if( thisForm.elements[i].title == "Date of next expected home visit" && inPlacementChecked )
				{
					if( thisForm.elements[i].value == "" )
					{
						answerMessage = answerMessage + "\nPlease enter [Date of next expected home visit]" ; 
						myOption = 0 ;
					}
				}
		 			
				if( thisForm.elements[i].title == "Has youth been on furlough during the evaluation period?" && inPlacementChecked )
				{
					if( (!thisForm.elements[i].checked) && (!thisForm.elements[i +1].checked) )
					{
						answerMessage = answerMessage + "\nPlease answer [Has youth been on furlough during the evaluation period?]" ; 
						myOption = 0 ;
					}
					i++; continue;
				}
	
				if( thisForm.elements[i].title == "Number of classes youth is currently failing during this evaluation period" )
				{
					if( notPassingChecked && thisForm.elements[i].options[ thisForm.elements[i].selectedIndex ].text == "Please Select" )
					{
						answerMessage = answerMessage + "\nPlease answer [Number of classes youth is currently failing during this evaluation period]" ; 
						myOption = 0 ;
					}
				}


				// 02 apr 2007 - mjt - per Defect 38235: all drop downs are required.
				if( thisForm.elements[i].title == "What is the family's, specifically the guardian, attitude toward the youth" )
				{
					if( thisForm.elements[i].options[ thisForm.elements[i].selectedIndex ].text == "Please Select" )
					{
						answerMessage = answerMessage + "\nPlease answer [What is the family's, specifically the guardian, attitude toward the youth]" ; 
						myOption = 0 ;
					}
				}
				if( thisForm.elements[i].title == "Peer Relationships" )
				{
					if( thisForm.elements[i].options[ thisForm.elements[i].selectedIndex ].text == "Please Select" )
					{
						answerMessage = answerMessage + "\nPlease answer [Peer Relationships]" ; 
						myOption = 0 ;
					}
				}
				if( thisForm.elements[i].title == "Current marijuana/alcohol use" )
				{
					if( thisForm.elements[i].options[ thisForm.elements[i].selectedIndex ].text == "Please Select" )
					{
						answerMessage = answerMessage + "\nPlease answer [Current marijuana/alcohol use]" ; 
						myOption = 0 ;
					}
				}
				if( thisForm.elements[i].title == "Current drug use excluding marijuana and alcohol" )
				{
					if( thisForm.elements[i].options[ thisForm.elements[i].selectedIndex ].text == "Please Select" )
					{
						answerMessage = answerMessage + "\nPlease answer [Current drug use excluding marijuana and alcohol]" ; 
						myOption = 0 ;
					}
				}
				if( thisForm.elements[i].title == "School Attendance" )
				{
					if( thisForm.elements[i].options[ thisForm.elements[i].selectedIndex ].text == "Please Select" )
					{
						answerMessage = answerMessage + "\nPlease answer [School Attendance]" ; 
						myOption = 0 ;
					}
				}
				if( thisForm.elements[i].title == "School behavior during this evaluative period" )
				{
					if( thisForm.elements[i].options[ thisForm.elements[i].selectedIndex ].text == "Please Select" )
					{
						answerMessage = answerMessage + "\nPlease answer [School behavior during this evaluative period]" ; 
						myOption = 0 ;
					}
				}
				// end Defect 38235: all drop downs are required.

			}// if row isnt hidden
		}//for
	}
	
	if( !myOption )
	{
		alert( answerMessage );
		return false;
	}
	
	return true;
}
*/

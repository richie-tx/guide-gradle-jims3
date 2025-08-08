<!-- security_util.js -->

/**
 * Javascript for IE11 changes.
 */

//JQuery on Dom ONLOAD
$(document).ready(function() {

	if(typeof $("#transferDate") != "undefined")
		datePickerSingle($("#transferDate"));
	
	if(typeof $("#dateOfBirth") != "undefined")
		datePickerSingle($("#dateOfBirth"));
    
	//on click of activation date
	$(document).on('click mouseover', '#fromActivationDate',  function(){
		var x = $('#fromActivationDate');
		var y = $('#toActivationDate');
		datePickerRange(x,y);
	});
	
	//on click of activation date
	$(document).on('click mouseover', '#fromDateOfBirth',  function(){
		var x = $('#fromDateOfBirth');
		var y = $('#toDateOfBirth');
		datePickerRange(x,y);
	});
	
	//on click of activation date
	$(document).on('click mouseover', '#fromInactivationDate',  function(){
		var x = $('#fromInactivationDate');
		var y = $('#toInactivationDate');
		datePickerRange(x,y);
	});
	
	//on click of activation date
	$(document).on('click mouseover', '#fromChangeDate',  function(){
		var x = $('#fromChangeDate');
		var y = $('#toChangeDate');
		datePickerRange(x,y);
	});
	
	$('#advancedSearch').click(function(){
		
		$('#advancedSearchURL').hide();
		$('#basicSearchURL').show();
		if(typeof $("#fromDateOfBirth") != "undefined")
			{
			  $("#fromDateOfBirth").val('');
			  $("#toDateOfBirth").val('');
			}
		
		for( var i = 0; i < 10; i++ )
      	{
			$('#hrow'+i).show();
      	}		
		return false;
	});
	
	$('#basicSearch').click(function(){
		
		$('#advancedSearchURL').show();
		$('#basicSearchURL').hide();
		for( var i = 0; i < 10; i++ )
      	{
			$('#hrow'+i).hide();
      	} 		
		return false;
	});
	
function closeWindow() {
  window.close();
}

 /*params:
what - the id of the thing to show
hide - 0 = hide 1 = show
format - table, row, or "" (for spans)
 */
 function show(what, hide, format){
	if (hide == 0)
		{
			document.getElementById(what).className='hidden';
		}else if(format=="row"){
				document.getElementById(what).className='visibleTR';
			}else if(format=="table"){
					document.getElementById(what).className='visibleTable';
				}else {
						document.getElementById(what).className='visible';
					}
	}
	
//show hide where there is an expand (plus sign)
/*params: spanName - name of the span to hide or show - image has same id as span -"Span"
*/
function showHide(spanName, format, path){
		
	var appendName = spanName + "Span";
	//alert(spanName + "\n" + appendName);
	var theClassName = document.getElementById(appendName).className;
	//alert(theClassName);
	if (theClassName=='visible' || theClassName=='visibleTR' || theClassName=='visibleTable'){
	//alert("visible flow");
		window.document.images[spanName].src=path+"images/expand.gif";
		document.getElementById(appendName).className='hidden';
	}else {
			window.document.images[spanName].src=path+"images/contract.gif";
				//alert("hidden flow");
			if(format=="row"){
					document.getElementById(appendName).className='visibleTR';
				}else if(format=="table"){
						document.getElementById(appendName).className='visibleTable';
					}else {
							document.getElementById(appendName).className='visible';
						}
		}
}

function showHide2(elementName, elementType, hide)
	{
		if (hide == 0)
		{
			document.getElementById(elementName).className='hidden';
		}
		else
		{
			if(elementType=="row")
			{
				document.getElementById(elementName).className='visibleTR';
			}
			else if(elementType=="table")
			{
				document.getElementById(elementName).className='visibleTable';
			}
			else 
			{
				document.getElementById(elementName).className='visible';
			}
		}
	}

/**
 * Single DatePicker function
 * @param datefld
 */
 function datePickerSingle( datefld ){
	 datefld.datepicker({
	      defaultDate: new Date(),
	      changeMonth: true,
	      changeYear: true,	
	 })
 }
 
	//From and To should be JQuery object.
	function datePickerRange( from,to ) 
	{
	      from.datepicker({
	      changeMonth: true,
	      changeYear: true,
	      onClose: function( selectedDate ) {
	        to.datepicker( "option", "minDate", selectedDate );
	      }
	    });
	
	    to.datepicker({
	      defaultDate: "+1w",
	      changeMonth: true,
	      changeYear: true,
	      onClose: function( selectedDate ) {
	        from.datepicker( "option", "maxDate", selectedDate );
	      }
	    });
	}

}); // jquery ends here
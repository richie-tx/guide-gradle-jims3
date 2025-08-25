$(document).ready(function () {	
	//on click of enter.
	$(document).bind("keydown", function(event) {
	    if (event.which == 13){
	        event.preventDefault();
	    	var results = validate();
	    	if( results){
	    		$("#submitBtn").click();
	    	}
	    }
	});
});

//validate user
function validate() 
{
  var frm = document.forms[0];

  if (frm.authenticationMethod.value == "") 
  {
    alert("You must select an authentication method to login.");
    frm.authenticationMethod.focus();
    return false;
  }
  
  if (frm.logonId.value == "") 
  {
    alert("You must enter a username to login.");
    frm.logonId.focus();
    return false;
  }

  if (frm.password.value == "") 
  {
    alert("You must enter a password to login.");
    frm.password.focus();
    return false;
  }
  //service provider
  if(frm.authenticationMethod.value=="SP"){
	  var userID =frm.logonId.value;
	  var  errorMesg = validateJIMS2UserID(userID, "User ID");
		if (errorMesg!=""){
			alert(errorMesg);
			frm.logonId.focus();
			return false;
		}
  }
  //ad logon
  if(frm.authenticationMethod.value=="AD"){
	  var userID =frm.logonId.value;
	  var domain =/^([a-zA-Z0-9]{1,15})\\([a-z'A-Z0-9._-]{1,64})$/;
	  var match = domain.test(userID);
	  
	  if (!match)
	  {
	     alert("You must enter a valid AD Account User Id.");
	     frm.logonId.focus();
		 return false;
	  }
  }
  return true;
} 



function goNav(locUrl)
{
	window.location.href = locUrl;
}

function getCurrentDay()
{
	var dayarray = new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday") ;
	
	return( dayarray[ new Date().getDay() ] );
}

function getCurrentDate()
{
	var montharray = new Array("January","February","March","April","May","June","July","August","September","October","November","December") ;
	var mydate = new Date();
	var year = mydate.getYear();
	var daym = mydate.getDate() ;

	if (year < 1000)
		year += 1900 ;

	if (daym < 10)
		daym = "0" + daym ;

	return( montharray[ mydate.getMonth() ] +" " + daym +", " + year );
}

// validate Email format
function validateJIMS2UserID(fldValue, fldName) {
	var errMsg = "";
	var userIDFmtRegex = /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if (fldValue == ""){
		errMsg = fldName + " is required.\n";
		return errMsg;	
	}
	if (fldValue.length < 5){
		errMsg = fldName + " must be at least 5 characters.\n";
		return errMsg;
	}	
	if (userIDFmtRegex.test(fldValue) == false){
		errMsg = fldName + " should be in a valid email format.\n";
		return errMsg;	
	}
	return errMsg;
}

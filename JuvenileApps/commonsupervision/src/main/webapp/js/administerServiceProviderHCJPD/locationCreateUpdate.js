<!-- locationCreateUpdate.js -->
function validateCreateUpdateFields()
{
  var theForm = document.forms[0];
  
  if (theForm["phoneNumber.areaCode"].value > "" && theForm["phoneNumber.prefix"].value == "") {
    alert("Phone Number Prefix must be entered if Phone Number Area Code is entered.");
    theForm["phoneNumber.prefix"].focus();

    return false;
  }

  if (theForm["phoneNumber.prefix"].value > "" && theForm["phoneNumber.last4Digit"].value == "") 
  {
    alert("Phone Number Last4Digit number must be entered if Phone Number Prefix is entered.");
    theForm["phoneNumber.last4Digit"].focus();

    return false;
  }

  if (theForm["phoneNumber.prefix"].value == "" && theForm["phoneNumber.last4Digit"].value > "") 
  {
    alert("Phone Number Prefix must be entered if Phone Number Last4Digit number is entered.");
    theForm["phoneNumber.prefix"].focus();

    return false;
  }

  if (theForm["phoneNumber.areaCode"].value == "" && theForm["phoneNumber.last4Digit"].value > "") 
  {
    alert("Phone Number Area Code must be entered if Phone Number Last4Digit number is entered.");
    theForm["phoneNumber.areaCode"].focus();

    return false;
  }
        
  return true;
}

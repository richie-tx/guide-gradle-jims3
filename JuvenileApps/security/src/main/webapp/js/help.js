<!-- help.js --> 
<script language=javascript>
function helpPopUp(){
  var uriPrefix = "http://www.Jims.hctx.net";
  var helpFolder = "refAppHelp";
  var UseCaseName = document.forms[0].useCase.value;
  var location = uriPrefix + "/" + helpFolder + "/" + UseCaseName + "/About.htm";
  if (uriPrefix.length < 1 || UseCaseName.length < 1 ){
     alert('Help is not available at this time.');
	 return false;
  }
  var leftPos = 20;
  var wt = 640;
  var ht = 500;
  if (screen) {
     wt = screen.width - 100;
	 ht = screen.heigth - 100;
     leftPos = (screen.width - wt) / 2;
  }
 var settings = "toolbar=no,location=no,directories=no,minimize=no," +
                "status=no,menubar=no,scrollbars=no,resizable=yes," +
                "width=" + wt + ",height=" + ht + ",left=" + leftPos + ",top=20";
 window.open(location,'',settings);
 return false;
 } 
 </script>

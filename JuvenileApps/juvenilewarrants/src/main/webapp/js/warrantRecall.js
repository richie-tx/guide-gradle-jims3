<!-- JavaScript for this page only -->
<!-- 02/08/2005  Hien Rodriguez - this validates the field -->

<script language=javascript>
function fieldCheck()
{
	if (document.forms[0].recallReasonId.value == "")
	{
		alert("Please select Recall Reason.");
		document.forms[0].recallReasonId.focus();
		return false;
	}
	if (document.forms[0].recallReasonId.value == "EX" && !getCurrentWarrantStatus())
	{
		alert("Please select Recall Reason other than WARRANT EXECUTED.");
		document.forms[0].recallReasonId.focus();
		return false;
	}
    return true;
}
</script>

 	



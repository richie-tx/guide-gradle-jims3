<!-- JavaScript for this page only -->
<!-- 05/12/2005  Richard Young - this validates the field -->

<script language=javascript>
function fieldCheck()
{
	if (document.forms[0].recallReasonId.selectedIndex == "")
	{
		alert("Please select Inactivate Reason.");
		document.forms[0].recallReasonId.focus();
		return false;
	}
/**	if (document.forms[0].recallReasonId.value == "EX" && !getCurrentWarrantStatus())
	{
		alert("Please select Inactivate Reason other than WARRANT EXECUTED.");
		document.forms[0].recallReasonId.focus();
		return false;
	}**/
    return true;
}
</script>
<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/activityUpdateQueryResult.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script language="javascript">
function setTableId(idName){
     document.forms[0].tableId.value = idName;
}
$(document).ready(function(){
	if (typeof $("#activityDate") != "undefined") 
	{
		datePickerSingle($("#activityDate"), "Date", false);
	}	
})
function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen + 1)
	{
  		alert("Your input has been truncated to " + maxlen +" characters, the maximum allowed.");
	}

	if (field.value.length > maxlen)
	{
  		field.value = field.value.substring(0, maxlen);
	}
} 
function confirmUpdate()
{
	var msg = "";
	if ($("#activityCd").val() == ""){
		      if (msg == "")
	          {
	        	  $("#activityCd").focus();
		   	  }
	      	  msg += "Activity code is Required.\n";	       
	   }
	if ($("#newcasefileId").val() == ""){
	     if (msg == "")
        {
      	  $("#newcasefileId").focus();
	   	}
    	msg += "Casefile_ID is Required.\n";	       
 }
	if ($("#detentionTime").val() > ""){
		   if(isValidTime($("#detentionTime").val()) == false)
	       {
	          if (msg == "")
	          {
	        	  $("#detentionTime").focus();
		   	  }
	      	  msg += "Detention Time Invalid. Please enter time in this format: hh:mm:ss.\n";
	       } 
	   }
	if ($("#activityTime").val() > ""){
	       if(isValidTime($("#activityTime").val()) == false)
	       {
	          if (msg == "")
	          {
	        	  $("#activityTime").focus();
		   	  }
	      	  msg += "Activity Time Invalid. Please enter time in this format: hh:mm:ss.\n";
	       } 
	   }
	if ($("#activityendTime").val() > ""){
	       if(isValidTime($("#activityendTime").val()) == false)
	       {
	          if (msg == "")
	          {
	        	  $("#activityendTime").focus();
		   	  }
	      	  msg += "Activity end Time Invalid. Please enter time in this format: hh:mm:ss.\n";
	       } 
	   }
	var numRegExp = /^[0-9]*$/;
	if ($("#newcasefileId").val() > "")
	{
		var casefileId=$("#newcasefileId").val();
		if (numRegExp.test(casefileId,numRegExp) == false)
		{
			if (msg == "")
	          {
	        	  $("#newcasefileId").focus();
		   	  }
			 msg +="CasefileID must be numeric.\n"
		}
	}
	if ($("#detentionId").val() > "")
	{
		var detId=$("#detentionId").val();
		if (numRegExp.test(detId,numRegExp) == false){
			if (msg == "")
	          {
	        	  $("#detentionId").focus();
		   	  }
			 msg +="DetentionID must be numeric."
		}
	}
	if(msg > "")
	{
		alert(msg);
		return false;
	}
	else 		
	{
		if(confirm('Are you sure you want to update the activity record?')) 
		{ 		
			if(true)
			{
				spinner();
				 $('#activityUpdateForm').submit();  
			}	
		} 
		
	}
}
function isValidTime(value) 
{
	var re = /^\d{2}[:]\d{2}([:]\d{2})?$/;
	
	if (!re.test(value)) 
	{
		return false; 
	}

	var values = value.split(":");
	
	if ( (parseFloat(values[0]) == 24) && (parseFloat(values[0]) > 0) )
	{
		return false;
	} 
	else if ( (parseFloat(values[0]) < 0) || (parseFloat(values[0]) > 23) ) 
	{ 
		return false;
	}
 	else if ( (parseFloat(values[1]) < 0) || (parseFloat(values[1]) > 59) ) 
	{ 
		return false;
	}
	else if (values.length > 2)
	{
		if ( (parseFloat(values[2]) < 0) || (parseFloat(values[2]) > 59) ) 
		{
			return false;
		}
	}
	
	return true;
}
</script>
</head>


<!-- Error Message Area -->
    <logic:notEqual name="ProdSupportForm" property="msg" value="">
	<table border="0" width="700" align="center">

	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<bean:write name="ProdSupportForm" property="msg" />
	 		</font></td>
	</tr>
	</table>
	</logic:notEqual>
<!-- End Error Message Area -->

<html:form styleId="activityUpdateForm"  action="/PerformActivityUpdate" onsubmit="return this;" >

<div>
	<br>
	<logic:notEmpty	name="ProdSupportForm" property="activities">
	
	<logic:notEqual name="ProdSupportForm" property="activityCount" value="0">	 
	<p align="center">
	<font size="+1"><b>Results for Activity_ID = <bean:write name="ProdSupportForm" property="activityId" /></b></font>
	</p>
	<p align="center">Please enter new values for the attributes you wish to update.</p>
	
	<table class="resultTbl" border="1" width="1000" align="center">

	<logic:iterate id="activities" name="ProdSupportForm" property="activities">
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACTIVITY_ID</font></td>
		<td><font size="-1"><bean:write  name="activities" property="activityId" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
		<td><font size="-1"><bean:write  name="activities" property="casefileId" />&nbsp;</font></td>
		<td>
	  	<font color="red">New value:</font>
	  	<html:text name="ProdSupportForm" property="casefileId" styleId="newcasefileId"  size="10" maxlength="8"  indexed="false"/> 
	  	
        </td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACTIVITYCD</font></td>
		<td><font size="-1"><bean:write  name="activities" property="codeId" />&nbsp;</font></td>
		<td>
	  	<font color="red">New value:</font>
	  	<html:text styleId="activityCd" name="ProdSupportForm" property="activityCd" size="4" maxlength="3"  indexed="false"/> 
        </td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">COMMENTS</font></td>
		<td><font size="-1"><bean:write  name="activities" property="comments" />&nbsp;</font></td>
		<td>
	  	<font color="red">New value:</font>
	  	<%-- <html:text styleId="newComments" name="ProdSupportForm" property="newComments" size="100" maxlength="100"/>  --%>
	  	<html:textarea name="ProdSupportForm" property="newComments" ondblclick="myReverseTinyMCEFix(this)"
																			styleClass="mceEditor" style="width:100%" rows="3" onmouseout="textLimit( this, 32000 )" onkeyup="textLimit( this, 32000 )" indexed="false"></html:textarea> <!-- onkeyup="textCounter(this.form.casenoteText,1000);" -->  
        </td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATE COMMENTS</font></td>
		<td><font size="-1"><bean:write  name="activities" property="updateComments" />&nbsp;</font></td>
		<td>
	  	<font color="red">New value:</font>
		<html:textarea name="ProdSupportForm" property="newupdatedComments" ondblclick="myReverseTinyMCEFix(this)"
																			styleClass="mceEditor" style="width:100%" rows="3" onmouseout="textLimit( this, 32000 )" onkeyup="textLimit( this, 32000 )" indexed="false"></html:textarea><!-- onkeyup="textCounter(this.form.casenoteText,1000);" -->  
		</td> 
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">INACTIVEDATE</font></td>
		<td><font size="-1"><bean:write  name="activities" property="inactiveDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TITLE</font></td>
		<td><font size="-1"><bean:write  name="activities" property="title" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACTIVITYDATE</font></td>
		<td><font size="-1"><bean:write  name="activities" property="activityDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td>
	  	<font color="red">New value:</font>
	  	<html:text styleId="activityDate" name="ProdSupportForm" property="activityDate" size="10" maxlength="10"  indexed="false"/> 
        </td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DETENTION_ID</font></td>
		<td><font size="-1"><bean:write  name="activities" property="detentionId" />&nbsp;</font></td>
		<td>
	  	<font color="red">New value:</font>
	  	<html:text styleId="detentionId" name="ProdSupportForm" property="detentionId" size="8" maxlength="8"  indexed="false"/> 
        </td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DETENTIONTIME</font></td>
		<td><font size="-1"><bean:write  name="activities" property="detentionTime" />&nbsp;</font></td>
		<td>
	  	<font color="red">New value:</font>
	  	<html:text styleId="detentionTime" name="ProdSupportForm" property="detentionTime" size="15" maxlength="15" /> 
	  	<!-- <input id="detentionTime" name="detentionTime" type="time" size="15" maxlength="15" />  -->
        </td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACTIVITYTIME</font></td>
		<td><font size="-1"><bean:write  name="activities" property="activityTime" />&nbsp;</font></td>
		<td>
	  	<font color="red">New value:</font>
	  	<html:text styleId="activityTime" name="ProdSupportForm" property="activityTime" size="15" maxlength="15"  indexed="false"/> 
	  	<!-- <input id="activityTime" name="activityTime" type="time" size="15" maxlength="15" /> -->
        </td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACTIVITYENDTIME</font></td>
		<td><font size="-1"><bean:write  name="activities" property="activityendTime" />&nbsp;</font></td>
		<td>
	  	<font color="red">New value:</font>
	  	<html:text styleId="activityendTime" name="ProdSupportForm" property="activityendTime" size="15" maxlength="15"  indexed="false"/> 
	  	<!-- <input id="activityendTime" name="activityendTime" type="time" size="15" maxlength="15" /> -->
        </td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LATITUDE</font></td>
		<td><font size="-1"><bean:write  name="activities" property="latitude" />&nbsp;</font></td>	
		</tr>
		<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LONGITUDE</font></td>
		<td><font size="-1"><bean:write  name="activities" property="longitude" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td><font size="-1"><bean:write  name="activities" property="createUserID" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td><font size="-1"><bean:write  name="activities" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td><font size="-1"><bean:write  name="activities" property="updateUser" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td><font size="-1"><bean:write  name="activities" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="activities" property="createJIMS2UserID" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="activities" property="updateJIMS2UserID" />&nbsp;</font></td>
		</tr>
	</logic:iterate>
		
	</table>
	</logic:notEqual>	
	
	</logic:notEmpty>	
	
	<logic:empty name="ProdSupportForm" property="activities">
		<table border="1" width="700" align="center">
		<tr>
		<td>
	   <h3 align="center"><i>No activities found.</i></h3>
	   </td>
	   </tr>
	   </table>
	</logic:empty>
	
	</div>
<table align="center"">
<tr>

<td>&nbsp;</td>

<logic:notEmpty name="ProdSupportForm" property="activities">
<td>
<%-- <html:form action="/PerformActivityUpdate"> --%>
<p align="center">
	<input type="button" name="Update Activity" value="Update Activity Record" onClick="return confirmUpdate();"/>
</p>
<%-- </html:form> --%>
</td>
</logic:notEmpty>

</tr>
</table>
</html:form>
<html:form action="/ActivityUpdateQuery?clr=Y" onsubmit="return this;">
<table align="center"">
	<tr>
		<td>
		<html:submit value="Back to Query"/>
		</td>
	</tr>
</table>
</html:form>

</html:html>
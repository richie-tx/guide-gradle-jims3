<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.Features" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updateSchoolHistQueryResult.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_edu.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script language="javascript">

//Birth Date CALENDAR.
if(typeof  $("#lastAttendedDate") != "undefined"){	
		datePickerSingle($("#lastAttendedDate"),"Last Attended Date",true);
}

function padTo2Digits(num) {
	  return num.toString().padStart(2, '0');
}

function formatDate(date) {
	  return [
	    padTo2Digits(date.getDate()),
	    padTo2Digits(date.getMonth() + 1),
	    date.getFullYear(),
	  ].join('/');
}

function verifyAttDate(){
	
	var selectedDate = document.forms[0].lastAttendedDate.value;
	
	if(selectedDate){	
		document.forms[0].attday.value = selectedDate.substr(3, 2);
		document.forms[0].attmonth.value = selectedDate.substr(0, 2)
		document.forms[0].attyear.value = selectedDate.substr(6, 4);
		
		return true;
	}
		
	return false;
}

function allAreEmpty(){
	
	if (document.forms[0].currentGradeBox.selectedIndex==0
		&&document.forms[0].exitTypeBox.selectedIndex==0
		&&document.forms[0].appropGradeBox.selectedIndex==0
		&&document.forms[0].gradesRepeatCodeBox.selectedIndex==0){
		return true;
	}
	else
		return false;
}

function confirmUpdate(){

	if (allAreEmpty()==false)
	{
		var juvNum = $("#juvenileNumber").val();
		if ($("#juvenileNumber").val() != "") {
			if ($("#juvenileNumber").val().trim().length < 6) {
				alert("Invalid Juvenile Number.");
				$("#juvenileNumber").focus();
				return false;
			}

			// juvenile Number
			if ($.isNumeric(juvNum) == false) {
				alert("Invalid Juvenile Number.");
				$("#juvenileNumber").focus();
				return false;
			}							
		}
		
		if(confirm('Are you sure you want to update the record?'))
		{
			spinner();
			return true;
		}
		else
			return false;
	}
	else if (verifyAttDate())
		{
		
		var juvNum = $("#juvenileNumber").val();
		if ($("#juvenileNumber").val() != "") {
			if ($("#juvenileNumber").val().trim().length < 6) {
				alert("Invalid Juvenile Number.");
				$("#juvenileNumber").focus();
				return false;
			}

			// juvenile Number
			if ($.isNumeric(juvNum) == false) {
				alert("Invalid Juvenile Number.");
				$("#juvenileNumber").focus();
				return false;
			}							
		}
		
		if(confirm('Are you sure you want to update the record?'))
		{
			spinner();
			return true;	
		}
		else
			return false;
		}
	else
	{
		alert('You must select a date.');	
		return false;		
	}	
}

function resetLists(){
	document.forms[0].exitTypeBox.selectedIndex=0;
	document.forms[0].currentGradeBox.selectedIndex=0;
	document.forms[0].appropGradeBox.selectedIndex=0;
	document.forms[0].gradesRepeatCodeBox.selectedIndex=0;	
}

function resetDates(){
	document.forms[0].attmonth.selectedIndex=0;
	document.forms[0].attday.selectedIndex=0;
	document.forms[0].attyear.selectedIndex=0;
}

function resetAll(){
	resetDates();
	resetLists();
}
</script>

</head>

<body class="ContentFrameInjection" onLoad="resetAll()">


<html:form action="/PerformUpdateSchoolHist" onsubmit="return this;">

<div>
	
	<h2 align="center">Results for SCHOOLHIST_ID = 
			<bean:write name="ProdSupportForm" property="schoolhistId" /></h2>
	     
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
	     
<p align="center"><b><i>Choose new values from the drop-downs.<br></i></b></p>	     
	     
<logic:notEmpty	name="ProdSupportForm" property="schoolhists">
	<logic:iterate id="schoolhists" name="ProdSupportForm" property="schoolhists">
	
	<table class="resultTbl" border="1" width="800" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SCHOOLHIST_ID</font></td>
		<td><font size="-1"><bean:write  name="schoolhists" property="schoolHistoryId" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LASTATTENDEDDATE</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="lastAttendedDateString" />&nbsp;</font></td>
		
	  <td>
	  <i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="lastAttendedDate" size="10" maxlength="10" styleId="lAttendedDate"/>

	  <select name="attmonth" size="1" hidden>
      <option value="">Month</option>
      <option value="01">Jan (01)</option>
      <option value="02">Feb (02)</option>
      <option value="03">Mar (03)</option>
      <option value="04">Apr (04)</option>
      <option value="05">May (05)</option>
      <option value="06">Jun (06)</option>
      <option value="07">Jul (07)</option>
      <option value="08">Aug (08)</option>
      <option value="09">Sep (09)</option>
      <option value="10">Oct (10)</option>
      <option value="11">Nov (11)</option>
      <option value="12">Dec (12)</option>
    </select>
  
	<select name="attday" size="1" hidden>
      <option value="">Day</option>
      <option value="01">01</option>
      <option value="02">02</option>
      <option value="03">03</option>
      <option value="04">04</option>
      <option value="05">05</option>
      <option value="06">06</option>
      <option value="07">07</option>
      <option value="08">08</option>
      <option value="09">09</option>
      <option value="10">10</option>
      <option value="11">11</option>
      <option value="12">12</option>
      <option value="13">13</option>
      <option value="14">14</option>
      <option value="15">15</option>
      <option value="16">16</option>
      <option value="17">17</option>
      <option value="18">18</option>
      <option value="19">19</option>
      <option value="20">20</option>
      <option value="21">21</option>
      <option value="22">22</option>
      <option value="23">23</option>
      <option value="24">24</option>
      <option value="25">25</option>
      <option value="26">26</option>
      <option value="27">27</option>
      <option value="28">28</option>
      <option value="29">29</option>
      <option value="30">30</option>
      <option value="31">31</option>
    </select>
  
	<select name="attyear" size="1" hidden>
      <option value="">Year</option>
      <option value="2004">2004</option>
      <option value="2005">2005</option>
	  <option value="2006">2006</option>
	  <option value="2007">2007</option>
	  <option value="2008">2008</option>
	  <option value="2009">2009</option>
	  <option value="2010">2010</option>
	  <option value="2011">2011</option>
	  <option value="2012">2012</option>
	  <option value="2013">2013</option>
	  <option value="2014">2014</option>
	  <option value="2015">2015</option>
	  <option value="2016">2016</option>
	  <option value="2017">2017</option>
	  <option value="2018">2018</option>
	  <option value="2019">2019</option>
	  <option value="2020">2020</option>
	  <option value="2021">2021</option>
	  <option value="2022">2022</option>
    </select>
    </td>
	</tr>
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CURRENTGRADECD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="gradeLevelCode" />&nbsp;</font></td>
		<td>  
			<html:select name="ProdSupportForm" property="currentGradeBox" style="width:400px">
				<html:option value="">Select a New Value</html:option>
				<html:optionsCollection name="ProdSupportForm" property="currentGradeCodes" label="description" value="code" />
			</html:select>
		</td>
	</tr>
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">EXITTYPECD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="exitTypeCode" />&nbsp;</font></td>
		<td>  
			<html:select name="ProdSupportForm" property="exitTypeBox" style="width:400px">
				<html:option value="">Select a New Value</html:option>
				<option value="NV">NO VALUE (BLANK)</option>
				<html:optionsCollection name="ProdSupportForm" property="exitTypeCodes" label="description" value="code" />
			</html:select>
		</td>
	</tr>

	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="juvenileNum" />&nbsp;</font></td>
		<td>
		  <jims2:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEJUVENILENUM%>">
			 <font size="-1"><html:text styleId="juvenileNumber"  name="ProdSupportForm"  property="juvenileId" maxlength="6"/>&nbsp;
			 </font>
		  </jims2:isAllowed>
		</td>
	</tr>
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">APPROPGRADECD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="appropriateLevelCode" />&nbsp;</font></td>
		<td>  
			<html:select name="ProdSupportForm" property="appropGradeBox" style="width:400px">
				<html:option value="">Select a New Value</html:option>
				<option value="NV">NO VALUE (BLANK)</option>
				<html:optionsCollection name="ProdSupportForm" property="appropGradeCodes" label="description" value="code" />
			</html:select>
		</td>
	</tr>

	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SCHOOLCD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="schoolId" />&nbsp;</font></td>
	</tr>	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SCHOOLDISTCD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="schoolDistrictId" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TEACODE</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="TEASchoolNumber" />&nbsp;</font></td>
	</tr>	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">VERIFIEDDATE</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="verifiedDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">GRADEAVG</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="gradeAverage" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">GRADEREPEATNUM</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="gradesRepeatNumber" />&nbsp;</font></td>
	</tr>	
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">GRADESREPEATCD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="gradesRepeatedCode" />&nbsp;</font></td>
		<td>  
			<html:select name="ProdSupportForm" property="gradesRepeatCodeBox" style="width:400px">
				<html:option value="">Select a New Value</html:option>
				<option value="NV">NO VALUE (BLANK)</option>
				<html:optionsCollection name="ProdSupportForm" property="gradesRepeatCodes" label="description" value="code" />
			</html:select>
		</td>
	</tr>

	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PARTICIPATIONCD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="participationCode" />&nbsp;</font></td>
	</tr>			
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PGMATTENDINGCD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="programAttendingCode" />&nbsp;</font></td>
	</tr>	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RULEINFRACTIONCD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="ruleInfractionCode" />&nbsp;</font></td>
	</tr>		
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ATTSTATUSCD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="schoolAttendanceStatusCode" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TRUANCYHISTORY</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="truancyHistory" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">HOMESCHOOLDISTCD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="homeSchoolDistrictId" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">HOMESCHOOLCD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="homeSchoolId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td><font size="-1"><bean:write  name="schoolhists" property="createUser" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATDATE</font></td>
		<td><font size="-1"><bean:write  name="schoolhists" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td><font size="-1"><bean:write  name="schoolhists" property="updateUser" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td><font size="-1"><bean:write  name="schoolhists" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="schoolhists" property="createJims2User" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="schoolhists" property="updateJims2User" />&nbsp;</font></td>
	</tr>
			
	</table>
	
	<BR>
	
	<table align="center"">
	
	<logic:notEmpty	name="ProdSupportForm" property="schoolhists">
	<td>
	<p align="center">
	<html:submit value="Update Records" onclick="return confirmUpdate();" />
	</p>
	</td>
	<td>
	<html:reset value="Reset Form Values" onclick="resetLists();" />
	</td>
	</logic:notEmpty>

	</table>
	
	</logic:iterate>
	</logic:notEmpty>
	
	<logic:empty name="ProdSupportForm" property="schoolhists">
	<br>
	<table align="center" border="1" width="700">
		<tr>
		<td>
	   <h3 align="center"><font color="green"><i>No Records Returned</i></font></h3>
	   </td>
	   </tr>
	   </table>
	</logic:empty>

	</div>
	

<table align="center"">
<tr>

<td>&nbsp;</td>

</tr>
</table>
</html:form>

<html:form action="/UpdateSchoolHistQuery?clr=Y" onsubmit="return this;">
<table align="center"">
	<tr>
		<td>
		<html:submit value="Back to Query"/>
		</td>
	</tr>
</table>
</html:form>


</body>
</html:html>
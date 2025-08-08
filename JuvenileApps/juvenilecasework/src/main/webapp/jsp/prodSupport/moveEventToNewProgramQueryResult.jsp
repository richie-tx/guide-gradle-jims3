<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 05/23/2025 NMathew --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/moveEventToNewProgramQueryResult.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>

<script language="javascript">
$("#document").ready( function() {
	$("#moveEvent").click(function(){
		if ( confirmMove () ) {
			spinner();
			$("#performMoveEventToNewProgram").submit(); 
		}
	})
})

function confirmMove(){
	if( confirm('Are you sure you want to move the selected Program IDs to a different Program ID?') ) {
		return true;	
	} else {
		return false;
	}
}

</script>

</head>

<html:form styleId="performMoveEventToNewProgram" action="/performMoveEventToNewProgram" onsubmit="return this;">

<div>
	
	<h3 align="center">Events Associated with  JUVPROGREF_ID = <font color=red> <bean:write name="ProdSupportForm" property="juvprogrefId" /></font></h3>

<!-- Error Message Area -->
    <logic:notEqual name="ProdSupportForm" property="msg" value="">
	<table border="0" width="700" align="center">

	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="2" face="Arial"> 
			<bean:write name="ProdSupportForm" property="msg" />
	 		</font></td>
	</tr>
	</table>
	</logic:notEqual>
<!-- End Error Message Area -->
	<logic:notEmpty	name="ProdSupportForm" property="juvprogrefs">
	
	<!-- <h3 align="center">Associated JuvProgRefs</h3> -->
	<h4 align="center">Select the Program IDs to be moved by clicking on the CheckBoxes. </h4>
	<p align="center">At least one checkbox must be selected.</p>
	<table class="resultTbl" border="1" width="700" align = "center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SERVEVENT_IDs</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Start Date of Service</font></td>
	</tr>
	
	<logic:iterate id="juvprogrefs" name="ProdSupportForm" property="juvprogrefs">
	<tr>
		<td>
		 <html:multibox property="selectedHists">
							<bean:write name="juvprogrefs" property="serviceEventId" />
		</html:multibox>
		<font size="-1"><bean:write  name="juvprogrefs" property="serviceEventId" /></font>
		</td>
		
		<td><font size="-1"><bean:write  name="juvprogrefs" property="startDatetime" formatKey="date.format.mmddyyyy" />&nbsp;</font></td> 
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	
	<logic:empty name="ProdSupportForm" property="juvprogrefs">
	<br>
	<table  align="center" border="1" width="700">
		<tr>
		<td>
	   <h3 align="center"><font color="green"><i>No Associated JuvProgRefs.</i></font></h3>
	   </td>
	   </tr>
	   </table>
	</logic:empty>
		<table align="center"">
			<tr>
				<td>&nbsp;</td>
				<td>
					<p align="center"><input type="button" id="moveEvent" name="Move Event" value="Move Event to New Program" /></p>
				</td>
			</tr>
		</table>
</html:form>


<html:form action="/moveEventToNewProgramAction?clr=Y" onsubmit="return this;">
	<table align="center"">
		<tr>
			<td><html:submit value="Back to Query" /></td>
		</tr>
	</table>
</html:form>

</html:html>
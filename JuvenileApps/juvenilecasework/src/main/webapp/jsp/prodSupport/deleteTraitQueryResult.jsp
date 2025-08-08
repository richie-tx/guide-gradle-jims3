<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>

<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/deleteTraitQueryResult.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script language="javascript">
	
function confirmDelete() {
	if(confirm('Are you sure you want to delete the record?'))
	{
		spinner();
		return true;
	}
	else
		return false;
}

</script>

</head>

<!-- Error Message Area -->
<logic:notEqual name="ProdSupportForm" property="msg" value="">
	<table border="0" width="700" align="center">

		<tr align="center">
			<td colspan="4"><font style="font-weight: bold;" color="#FF0000"
				size="3" face="Arial"> <bean:write name="ProdSupportForm"
						property="msg" /> </font></td>
		</tr>
	</table>
</logic:notEqual>
<!-- End Error Message Area -->


<div>
	<html:form action="/PerformDeleteTrait" onsubmit="return this;">

	<h4 align="center"><i>The following record will be <font color="red">DELETED</font>.</i></h4>


<logic:notEmpty name="ProdSupportForm" property="traits">

	<p>&nbsp;</p>

    <table class="resultTbl" border="1" width="900" align="center">

	<logic:iterate id="traits" name="ProdSupportForm" property="traits">

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TRAITS_ID</font></td>
		<td><font size="-1"><bean:write name="traits" property="juvenileTraitId"/>&nbsp;</font></td>
	</tr>
	<tr>		
		<td bgcolor="gray"><font color="white" face="bold" size="-1">COMMENTS</font></td>		
		<td><font size="-1"><bean:write  name="traits" property="traitComments" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DISPOSITIONNUM</font></td>	
		<td><font size="-1"><bean:write  name="traits" property="dispositionNum" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>	
		<td><font size="-1"><bean:write  name="traits" property="juvenileNum" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SUPERVISIONNUM</font></td>					
		<td><font size="-1"><bean:write  name="traits" property="supervisionNum" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TRAITTYPE_ID</font></td>							
		<td><font size="-1"><bean:write  name="traits" property="traitTypeId" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TRAITTYPE_DESC</font></td>							
		<td><font size="-1"><bean:write  name="traits" property="traitTypeDescription" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TRAITSTATUS</font></td>			
		<td><font size="-1"><bean:write  name="traits" property="statusId" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TRAITSTATUS_DESC</font></td>			
		<td><font size="-1"><bean:write  name="traits" property="status" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>			
		<td><font size="-1"><bean:write  name="traits" property="createUserID" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>		
		<td><font size="-1"><bean:write  name="traits" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>	
		<td><font size="-1"><bean:write  name="traits" property="updateUser" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>	
		<td><font size="-1"><bean:write  name="traits" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>			
		<td><font size="-1"><bean:write  name="traits" property="createJIMS2UserID" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="traits" property="updateJIMS2UserID" />&nbsp;</font></td>			
	</tr>			
			
	</logic:iterate>
  </table>
</logic:notEmpty>

<logic:empty name="ProdSupportForm" property="traits">
	<table border="1" width="700" align="center">
		<tr>
			<td><h3 align="center"><i>No trait detail found.</i></h3></td>
		</tr>
	</table>
</logic:empty>

<table align="center"">
	<tr>
		<td colspan="3">&nbsp;</td>
	</tr>
	<tr>
		<logic:notEmpty name="ProdSupportForm" property="traits">
		<td><input type="submit" name="Delete Record" value="Delete Record" onClick="return confirmDelete();" />
						</td>
		</logic:notEmpty>
	</tr>
</table>
</html:form>

	<table align="center">
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
			<p align="center">
			<html:form action="/DeleteTraitQuery?clr=Y" onsubmit="return this;">
				<html:submit value="Back to Query" />
			</html:form>
			</p>
			</td>
		</tr>
	</table>
</div>
</html:html>
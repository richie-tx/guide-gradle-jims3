<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- Created on 10/11/2023 nmathew --%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic"%>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.Features" %>

<head>

<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Juvenile Casework-/prodSupport/vopRecordsUpdateSummary.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/updateVOPReferral.js"></script>
<script>

</script>
</head>

<body>

	<html:form action="/UpdateVOPRecord" onsubmit="return this;">
	
	<!-- BEGIN Error Message Table -->
		 <logic:messagesPresent message="true"> 
			<table width="100%">
				<tr>		  
					<td align="center" class="messageAlert"><html:messages id="message" message="true"><font style="font-weight: bold;"
							color="green" size="3" face="Arial"><bean:write name="message"/></html:messages></font></td>		  
				</tr>   	  
			</table>
		</logic:messagesPresent> 
		
		<!-- BEGIN ERROR TABLE -->
		<table width="100%">
			<%-- <tr>
				<td align="center" class="errorAlert"><html:errors></html:errors></td>
			</tr> --%>
			<tr>
						<td align="center" colspan="4" class="errorAlert"><font style="font-weight: bold;"
							color="red" size="3" face="Arial"><html:errors></html:errors></font></td>
			</tr>
		</table>
	<!-- END ERROR TABLE -->
		<div>

			<h2 align="center">Results for Juvenile ID = 
			<bean:write name="ProdSupportForm" property="juvenileId" /></h2>

			<!-- Error Message Area -->
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<table border="0" width="700" align="center">

					<tr align="center">
						<td colspan="4"><font style="font-weight: bold;"
							color="#FF0000" size="3" face="Arial"> <bean:write
									name="ProdSupportForm" property="msg" /> </font>
						</td>
					</tr>
				</table>
			</logic:notEqual>
			
			<p align="center"><b><i><font style="font-weight: bold;" color="green" size="3" face="Arial">Record Successfully Updated</font></i></b></p>
		
	<table border="1" width="500" align="center">
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Number</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvenileId"/>&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Number</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="jcVOP.referralNumber" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Date</font></td>
		<td><font size="-1">
		<%-- <input id="referralDate" value="<bean:write name="ProdSupportForm" property="jcVOP.referralDate"  formatKey="date.format.mmddyyyy" />"/> --%>
		<bean:write name="ProdSupportForm" property="refDateVOP" formatKey="date.format.mmddyyyy"/>
		&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">VOP Offense Code</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="jcVOP.VOPOffenseCode" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Charge</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="jcVOP.offenseCharge"/>&nbsp;</font></td>				
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Charge Date</font></td>
		<td><font size="-1">
		<%-- <input id="offenseChargeDate" value="<bean:write name="ProdSupportForm" property="jcVOP.offenseChargeDate"  formatKey="date.format.mmddyyyy" />"/> --%>
		<bean:write name="ProdSupportForm" property="offenseChargeDateVOP" formatKey="date.format.mmddyyyy"/>
		&nbsp;</font></td> 
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">In County Original Petition Referral #</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="jcVOP.inCCountyOrigPetitionedRefNum"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Adult Indicator</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="jcVOP.adultIndicatorStr" />&nbsp;</font></td>		
	</tr>	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Location Indicator</font></td>
		<%-- <td><font size="-1"><bean:write name="ProdSupportForm" property="jcVOP.locationIndicator"/>&nbsp;</font></td> --%>
		<td>
		 <logic:equal name="ProdSupportForm" property="jcVOP.locationIndicator" value="IIC">
			<font size="-1"> In-County</font>
		 </logic:equal>
		 <logic:equal name="ProdSupportForm" property="jcVOP.locationIndicator" value="OOC">
			 <font size="-1">Out Of County</font>
		 </logic:equal>
		 <logic:equal name="ProdSupportForm" property="jcVOP.locationIndicator" value="OOS">
			 <font size="-1">Out Of State</font>
		 </logic:equal>
		</td>
		<%-- <td>
			<html:select name="ProdSupportForm" property="jcVOP.locationIndicator" styleId="locationIndicatorJCVOP">
			<html:option value="IIC">In County</html:option>
			<html:option value="OOC">Out Of County</html:option>
			<html:option value="OOS">Out Of State</html:option>
			</html:select></td> --%> 
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Create User</font></td>
		<td><%-- <font size="-1"><bean:write name="ProdSupportForm" property="jcVOP.createUserName"/>&nbsp;</font> --%></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Create Date</font></td>
		<td><%-- <font size="-1"><bean:write name="ProdSupportForm" property="jcVOP.createDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font> --%></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Update User</font></td>
		<td><%-- <font size="-1"><bean:write name="ProdSupportForm" property="jcVOP.updateUserName"/>&nbsp;</font> --%></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Update Date</font></td>
		<td><%-- <font size="-1"><bean:write name="ProdSupportForm" property="jcVOP.updateDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font> --%></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">create JIMS2 User</font></td>
		<td><%-- <font size="-1"><bean:write name="ProdSupportForm" property="jcVOP.createJIMS2UserName"/>&nbsp;</font> --%></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Update JIMS2 User</font></td>
		<td><%-- <font size="-1"><bean:write name="ProdSupportForm" property="jcVOP.updateJIMS2UserName" />&nbsp;</font> --%></td>
	</tr>
	</table>
		</div>
 
</html:form>
		<table align="center"">
			<tr>
				<td>&nbsp;</td>
			</tr>
	<table align="center" border="0" width="50%">
		<tr>
		<td colspan="2" align="center">
		<html:button property="submitAction" onclick="spinner();" styleId="backToVOPQryBtn"><bean:message key="button.backToSearch"></bean:message></html:button>
		<br /> <br />
		<html:form method="post" action="/MainMenu" onsubmit="return this;">
		<html:submit onclick="return this;" value="Back to Main Menu"/>
		</html:form>
		</td>
		</tr>
    </table> 
</table>
	<%-- </html:form> --%>

</body>
</html:html>
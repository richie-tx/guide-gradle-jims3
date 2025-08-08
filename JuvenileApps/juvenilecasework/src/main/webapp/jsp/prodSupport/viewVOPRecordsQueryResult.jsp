<!DOCTYPE HTML>
<%-- Oct 2023 NMATHEW  --%>
<%--MODIFICATIONS --%>

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
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Juvenile Casework -/prodSupport/viewVOPRecordsQueryResult.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
	
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/updateVOPReferral.js?1=12"></script>
<script type='text/javascript'>

document.addEventListener('DOMContentLoaded', function() { 
	
	const inputElement = document.getElementById('juvenileNumber'); 
	if (inputElement) { inputElement.addEventListener('input', function (e) { this.value = this.value.replace(/[^0-9]/g, ''); }); } 
	
});

</script>
</head>

<body>

<html:form method="post" styleId="updateVOPRecord" action="/UpdateVOPRecord" onsubmit="return this;">

<div>
	
	<h2 align="center">Results for Juvenile ID = 
			<bean:write name="ProdSupportForm" property="juvenileId" /></h2>
	     		<p align="center"><b><i>
	     		<elogic:if name="ProdSupportForm" property="rectype" op="equal" value="S.JUVENILE">
					<elogic:then>
						<font size="-1"> Juvenile record is classified as Sealed. </font>
					</elogic:then>	
				</elogic:if>		
				<elogic:if name="ProdSupportForm" property="rectype" op="equal" value="I.JUVENILE">
						<elogic:then>
						<font size="-1"> Juvenile record is classified as Purged. </font>
					</elogic:then>
				</elogic:if>
				</i></b></p>
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
	<p align="center"><b><i>Change the values of the field(s) and Click Update VOP Details.</i></b></p>	
<!-- End Error Message Area -->	     

	<table class="resultTbl" border="1" width="750" align="center">
	
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Number</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm" property="jcVOP.juvenileNumber" />&nbsp;</font>
			</td>
			<td>			
			<jims2:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEJUVENILENUM%>">
			 <font size="-1"><html:text styleId="juvenileNumber"  name="ProdSupportForm"  property="jcVOP.juvenileNumber" maxlength="6"/>&nbsp;
			 </font>
			</jims2:isAllowed>
			</td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Number</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm" property="jcVOP.referralNumber" />&nbsp;</font></td>
			<td><font size="-1"><html:text styleId="referralId"  name="ProdSupportForm"  property="jcVOP.referralNumber"  maxlength="4"/>&nbsp;
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Date</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm" property="jcVOPOriginal.referralDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
			<td>
			 <input id="referralDate" value="<bean:write name="ProdSupportForm" property="jcVOP.referralDate"  formatKey="date.format.mmddyyyy" />"/>
			</td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">VOP Offense Code</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm" property="jcVOPOriginal.VOPOffenseCode" />&nbsp;</font></td>
			<td><font size="-1"><html:text styleId="VOPOffenseCode"  name="ProdSupportForm"  property="jcVOP.VOPOffenseCode"  maxlength="6"/>&nbsp;
			</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Charge</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm" property="jcVOPOriginal.offenseCharge" />&nbsp;</font></td>
			<td><font size="-1"><html:text styleId="offenseChargeVOP"  name="ProdSupportForm"  property="jcVOP.offenseCharge"  maxlength="6"/>&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Charge Date</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm" property="jcVOPOriginal.offenseChargeDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
			<td><input id="offenseChargeDate" value="<bean:write name="ProdSupportForm" property="jcVOP.offenseChargeDate"  formatKey="date.format.mmddyyyy" />"/></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">In County Original Petition Referral #</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm" property="jcVOPOriginal.inCCountyOrigPetitionedRefNum" />&nbsp;</font></td>
			<td><html:text styleId="inCCountyOrigPetitionedRefNumVOP"  name="ProdSupportForm"  property="jcVOP.inCCountyOrigPetitionedRefNum"  maxlength="4"/>&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Adult Indicator</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm" property="jcVOPOriginal.adultIndicatorStr" />&nbsp;</font></td>
			<td><html:text styleId="adultIndicator"  name="ProdSupportForm"  property="jcVOP.adultIndicatorStr"  maxlength="1"/>&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Location Indicator</font></td>
			<td>
			 <logic:equal name="ProdSupportForm" property="jcVOPOriginal.locationIndicator" value="IIC">
			<font size="-1"> In-County</font>
			 </logic:equal>
			  <logic:equal name="ProdSupportForm" property="jcVOPOriginal.locationIndicator" value="OOC">
			 <font size="-1">Out Of County</font>
			 </logic:equal>
			  <logic:equal name="ProdSupportForm" property="jcVOPOriginal.locationIndicator" value="OOS">
			 <font size="-1">Out Of State</font>
			 </logic:equal>
			</td>
			 <td>
			<html:select name="ProdSupportForm" property="jcVOP.locationIndicator" styleId="locationIndicatorJCVOP">
			<html:option value=""></html:option>
			<html:option value="IIC">In County</html:option>
			<html:option value="OOC">Out Of County</html:option>
			<html:option value="OOS">Out Of State</html:option>
			</html:select></td> 
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Create User</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm" property="jcVOP.createUserName" />&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Create Date</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm" property="jcVOP.createDate"formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Update User</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm" property="jcVOP.updateUserName" />&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Update Date</font></td>
			<%-- <td><html:text styleId="adultIndicator"  name="ProdSupportForm"  property="jcVOP.updateDate"  maxlength="7" />&nbsp;</font></td> --%>
			<td><font size="-1"><bean:write name="ProdSupportForm" property="jcVOP.updateDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">create JIMS2 User</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm" property="jcVOP.createJIMS2UserName" />&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Update JIMS2 User</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm" property="jcVOP.updateJIMS2UserName" />&nbsp;</font></td>
		</tr>
	</table>     
	     
	<BR>

	</div>
	
	<table align="center"">
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>	
				<html:button property="submitAction" styleId="updateVOPBtn" disabled="false" > <bean:message key="button.updateVOP"></bean:message></html:button>	
				<html:button property="submitAction" onclick="spinner();" styleId="backToVOPQryBtn"><bean:message key="button.backToSearch"></bean:message></html:button>
			</td>
		</tr>
	</table>
<html:hidden styleId="juvenileNum" name="ProdSupportForm" property="juvenileId"/>
<html:hidden styleId="referralNum" name="ProdSupportForm" property="referralId"/>
<html:hidden styleId="newRefDate" name="ProdSupportForm" property="refDateVOP"/>  
<html:hidden styleId="newOffenseDate" name="ProdSupportForm" property="offenseChargeDateVOP"/>
</html:form>

</body>
</html:html>
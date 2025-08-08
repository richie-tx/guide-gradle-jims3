<!DOCTYPE HTML>
<%-- MODIFICATIONS --%>
<%-- 10/04/2006	 D Williamson	Create JSP --%>
<%-- 07/17/2007	 Leslie Deen	Defect #43882 - add link to address.js & fixed Validate button layout--%>
<%-- 10/13/2015  RYoung  - #30342 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Search Location)--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%-- TAB LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>


<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/csbase.css" />
<html:base />
<title><bean:message key="title.heading"/> - location/locationCreateUpdate.jsp</title>
<html:javascript formName="locationCreateForm"/>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/address.js"></script>
<script type="text/javascript" src="/<msp:webapp/>case_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/administerServiceProviderHCJPD/locationCreateUpdate.js"></script>
<script>
	$(document).ready(function(){
		$("#resetBtn").click(function(){
			$("input[name='locationCd']").removeAttr('value');
			$("input[name='locationName']").removeAttr('value');
			$("input[name='phoneNumber.areaCode']").removeAttr('value');
			$("input[name='phoneNumber.prefix']").removeAttr('value');
			$("input[name='phoneNumber.last4Digit']").removeAttr('value');
			$("input[name='locationAddress.streetNumber']").removeAttr('value');
			$("input[name='locationAddress.streetName']").removeAttr('value');
			$("input[name='locationAddress.aptNumber']").removeAttr('value');
			$("input[name='locationAddress.city']").removeAttr('value');
			$("input[name='locationAddress.zipCode']").removeAttr('value');
			$("select option:selected").removeAttr('selected');
			$(".errorAlert").html("");
			$("#addressStatus").html("UNPROCESSED");
			
		})
	})
</script>

</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)" topmargin=0 leftmargin="0">
<html:form styleId="locationCreateForm" action="/displayLocationCreateUpdateSummary" target="content" focus="locationCd">
<logic:equal name="locationForm" property="action" value="create">
   <input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|400">
</logic:equal>
<logic:equal name="locationForm" property="action" value="update">
   <input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|397">
</logic:equal>

<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
  	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  </tr>
  <tr>
  	<td valign=top>
  		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  			<tr>
  				<td valign=top>
  					<%--tabs start--%>
  					<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
  						<tiles:put name="tabid" value=""/>
  					</tiles:insert>						
  					<%--tabs end--%>
  				</td>
  			</tr>
  			<tr>
  				<td bgcolor='#6699ff'><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  			</tr>
  		</table>

  		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  			<tr>
  				<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  			</tr>
  			<tr>
  				<td valign=top align=center>
  
  					<%-- BEGIN HEADING TABLE --%>
  					<table width='100%'>
  						<tr>
  							<td align="center" class="header">
  								<logic:equal name="locationForm" property="action" value="create">  <bean:message key="prompt.create"/> </logic:equal>
  								<logic:equal name="locationForm" property="action" value="update">  <bean:message key="prompt.update"/> </logic:equal>
  								<bean:message key="prompt.location"/>
                  </td>                            
  						</tr>
  					</table>
  					<%-- END HEADING TABLE --%>
  
  					<%-- BEGIN INSTRUCTION TABLE --%>
  					<table width="98%" border="0">
  						<tr>
  							<td><ul><li>Enter required fields and select Next button.</li></ul></td>
  						</tr>
  						<tr>
  							<td class=required colspan=4><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.requiredFieldsInstruction" /></td>
  						</tr>
  					</table>
  					<%-- BEGIN ERRORS TABLE --%>
  					<table width="100%">
  						<tr>		  
  							<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
  						</tr>   	  
  					</table>
  					<%-- END ERRORS TABLE --%>
  
  					<%-- location begin --%>
  					<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
  						<tr class="detailHead">
  							<td><bean:message key="prompt.location"/>&nbsp;<bean:message key="prompt.information"/></td>
  						</tr>
  						<tr>
  							<td>
  								<table width="100%" border=0 cellpadding="2" cellspacing="1">
  									<tr>
  										<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.2.diamond"/><bean:message key="prompt.locationID" /></td>
										<td class="formDe"><html:text name="locationForm" property="locationCd" size="10" maxlength="10"/></td>
  										<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.location"/></td>
  										<td class="formDe"><html:text name="locationForm" property="locationName" size="35" maxlength="35"/></td>
  										
  									</tr>
  									<tr>
  									<td class="formDeLabel" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.inHouse"/></td>
  										<td class="formDe"> 
  											<bean:message key="prompt.yes"/><html:radio name="locationForm" property="isInHouse" value="YES"/>
                        					<bean:message key="prompt.no"/><html:radio name="locationForm" property="isInHouse" value="NO"/>
  										</td>
  										
  										<td class="formDeLabel" nowrap><bean:message key="prompt.facility"/>&nbsp;<bean:message key="prompt.type"/></td>
  										<td class="formDe">
  											<html:select property="facilityTypeId" name="locationForm">
  											   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
  											   <html:optionsCollection property="facilityTypeList" value="code" label="description"  name="locationForm"/>
  											</html:select>  
  										</td>
  									</tr>
  									<tr>       
  										<td class="formDeLabel" nowrap><bean:message key="prompt.phone"/></td>
  										<td class="formDe" colspan="3">
  										   <html:text name="locationForm" property="phoneNumber.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
  										   <html:text name="locationForm" property="phoneNumber.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
  										   <html:text name="locationForm" property="phoneNumber.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
  										</td>
  									</tr>
  									<tr>
  										<td class="detailHead" colspan=4>
  											<table width='100%' cellpadding=0 cellspacing=1>
  												<tr class=detailHead>
  													<td><bean:message key="prompt.address"/></td>		
  													
  													<td align=right nowrap class="detailHead"><bean:message key="prompt.addressStatus"/>: </td>
  													<td id="addressStatus" class="errorAlert">
  														<%-- TODO replace logic tags with code table values --%>	 
  														<logic:equal name="locationForm" property="addressStatus" value="">UNPROCESSED</logic:equal>       	    
  													  <logic:equal name="locationForm" property="addressStatus" value="U">UNPROCESSED</logic:equal>
  													  <logic:equal name="locationForm" property="addressStatus" value="Y">VALID</logic:equal>
  													  <logic:equal name="locationForm" property="addressStatus" value="N">INVALID</logic:equal>
  													</td>		  												
  												</tr>
  												<tr class="detailHead">
  												    <td align="right" class="detailHead"> 
  														<input type=button value="<bean:message key='button.validate' />" 
    																onClick="return validateAddrAction('locationForm','/<msp:webapp/>validateLocationAddress.do','', 'locationAddress','/jsp/location/locationCreateUpdate.jsp', true);">
  														</input>
  														<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="return displayResearchWindow();">
  																<bean:message key="button.research"></bean:message>
  													  </html:button>	
  													</td>
  											    </tr>	
  											</table>
  										</td>
  									</tr>
  									<tr>
  										<td colspan=4 class=formDe>
  											<table width="100%" border=0 cellpadding="2" cellspacing="1">
  												<tr>
  													<td class=formDeLabel><bean:message key="prompt.2.diamond"/><bean:message key="prompt.streetNumber"/></td>
  													<td class=formDeLabel colspan="2"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.streetName"/></td>
  												</tr>
  												<tr>
  													<td class=formDe><html:text styleId="streetNumber" name="locationForm" property="locationAddress.streetNumber" size="15" maxlength="15"/></td>
  													<td class=formDe colspan="2"><html:text name="locationForm" property="locationAddress.streetName" size="50" maxlength="50"/></td>
  												</tr>
  												<tr> 
  													<td class=formDeLabel><bean:message key="prompt.streetType"/></td>
  													<td class=formDeLabel colspan="2"><bean:message key="prompt.apartmentNumber"/></td>
  												</tr>
  												<tr>
  													<td class=formDe>
  														<html:select styleId="streetTypeId" name="locationForm" property="locationAddress.streetTypeId" size="1">
  															<option value=""><bean:message key="select.generic"/></option>
  															<html:optionsCollection name="locationForm" property="streetTypeList"  value="code" label="description"/>
  														</html:select>
  													</td>
  													<td class=formDe colspan="2"><html:text name="locationForm" property="locationAddress.aptNumber" size="10" maxlength="10"/></td>
  												</tr>
  												<tr>
  													<td class=formDeLabel><bean:message key="prompt.2.diamond"/><bean:message key="prompt.city"/></td>
  													<td class=formDeLabel><bean:message key="prompt.2.diamond"/><bean:message key="prompt.state"/></td>
  													<td class=formDeLabel><bean:message key="prompt.2.diamond"/><bean:message key="prompt.zipCode"/></td>
  												</tr>
  												<tr>
  													<td class=formDe><html:text name="locationForm" property="locationAddress.city" size="25" maxlength="25"/></td>
  													<td class=formDe>
  														<html:select name="locationForm" property="locationAddress.stateId" >
  														   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
  														   <html:optionsCollection property="stateList" value="code" label="description"  name="locationForm"/>
  														</html:select>  
  													</td>
  													<td class=formDe>
  														<html:text name="locationForm" property="locationAddress.zipCode" size="5" maxlength="5"/>-<html:text name="locationForm" property="locationAddress.additionalZipCode" size="4" maxlength="4"/>
  													</td>
  												</tr>
  											</table>
  										</td>
  									</tr>
  								</table>
  							</td>
  						</tr>
						</table>
						<%-- location end --%>

  					<%-- BEGIN BUTTON TABLE --%>
  					<br>
  					<table border="0" width="100%">
  						<tr>
  							<td align="center"><html:submit property="submitAction">
  								<bean:message key="button.back"></bean:message>
  								</html:submit>
  								<html:submit property="submitAction" onclick="return validateLocationCreateForm(this.form) && validateCreateUpdateFields() && validateRadios((this.form), 'isInHouse', 'In House is required.  Please select either Yes or No.');">
  								     <bean:message key="button.next"></bean:message>
  								</html:submit>
  								<html:reset styleId="resetBtn"><bean:message key="button.reset"/></html:reset>
  							</td>
  						</tr>
  						<%--input type="button" value="Next" onClick="buildNext()"--%>
  					</table>
  					<%-- END BUTTON TABLE --%>
  				</td>
  			</tr>
  		</table><br>
  	</td>
  </tr>
</table>
<%-- END  TABLE --%>
</div>

<%-- BEGIN HIDDEN FIELDS FOR ADDRESS VALIDATION --%>
<table width='100%'>
  <tr>
  	<td>
  	  <html:hidden property="validStreetNum" value="" />
		  <html:hidden property="validStreetName" value="" />
  	  <html:hidden property="validZipCode" value="" />
  	  <html:hidden property="validAddrNum" value="" />
  	  <html:hidden property="inputPage" value="" />
 	    <html:hidden property="currentAddressInd" value="" />
  	</td>
  </tr>	  
</table>
<%-- ENd HIDDEN FIELDS FOR ADDRESS VALIDATION --%>		

</html:form>

<br>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>


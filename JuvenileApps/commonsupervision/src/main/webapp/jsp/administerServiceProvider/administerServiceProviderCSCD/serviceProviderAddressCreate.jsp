<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Used for Entering Service Provider Address (CSCD)-->
<!--MODIFICATIONS -->
<!-- DWilliamson 11/26/2007	Create JSP -->
 
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ page import="naming.PDCodeTableConstants" %>
<html:html>

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading"/> - serviceProviderAddressCreate.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/address.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<!-- begin Auto Tabbing script -->
<script>

	//this function show/hides the billing address information if the user checks the Same As Billing checkbox
	//params: el - the checkbox object
function showBilling(el){
	if (el.checked){
		show("billingAddress", 0)
		show("billingGisButtons", 0)
		}else {
			show("billingAddress", 1, "row")
			show("billingGisButtons", 1, "row")
		}
	}

function enableCounty(theSelect, prefix)
{
	var selectedState = theSelect.options[theSelect.selectedIndex].value;
	var theForm = theSelect.form;
	var name=prefix + ".countyId";
	var theCountySelect = document.getElementById(name);
	
	if (selectedState == "TX")
	{
		theCountySelect.disabled = false;
		var theCountyLen=theCountySelect.length;
		for(i=0; i<theCountyLen; i++){
			var theSelVal=theCountySelect.options[i].value;
			if(theSelVal==<%=PDCodeTableConstants.HARRIS_COUNTY%>)
			{
				theCountySelect.selectedIndex=i;
				return;
			}
		}
	}
	else
		{
			theCountySelect.selectedIndex=0;
			theCountySelect.disabled = true;
		}
	}
	
//***Begin VALIDATE COMPLEX ADDRESS script
function validateAddressGenericCustom( myForm,  aFieldPrefix, msgPrefix )
{
		if( myForm == null )
	{
		return false;
	}

	var state = "";
	var streetName = "";
	var strNum = "";
	var city = "";
	var apt="";
	var zipCode = "";
	var zipCode2 = "";
	var fieldPrefix = null;
	var msg = "";
	var numbers = /^[ 0-9 ]*$/;
	var streetNumRegex = /^[ a-zA-Z0-9-\/  ]+$/;

	var cityRegex= /^[a-zA-Z]{1}[a-zA-Z0-9 ]+$/;
	var aptRegex= /^[a-zA-Z0-9]{1}[a-zA-Z0-9 ]+$/;
	var zipre = /^[ 0-9 ]{5}$/;
	var addzipre = /^[ 0-9 ]{4}$/;

	var streetNameElem;
	var strNumElem;
	var cityElem;
	var aptElem;
	var stateElem;
	var zipCodeElem;

	var streetNumberElemName = "streetNumber";
	var altStreetNumberElemName = "streetNum";

	if( aFieldPrefix != null  &&  aFieldPrefix != "" )
	{
		fieldPrefix = aFieldPrefix;
	}

	
		var elementName = fieldPrefix + ".streetName";
		//  streetNameElem = document.getElementById( elementName );
		streetNameElem = ( document.getElementsByName( elementName ) )[ 0 ];

		elementName = fieldPrefix + "." + streetNumberElemName;

		if( ( document.getElementsByName( elementName ) )[ 0 ] == null )
		{
			streetNumberElemName = altStreetNumberElemName;
			elementName = fieldPrefix + "." + streetNumberElemName;
		}

		strNumElem = ( document.getElementsByName( elementName ) )[ 0 ];
		elementName = fieldPrefix + ".city";
		cityElem = ( document.getElementsByName( elementName ) )[ 0 ];

		elementName = fieldPrefix+".stateId";
		stateElem = ( document.getElementsByName( elementName ) )[ 0 ];
		if( stateElem == null )
			stateElem = ( document.getElementsByName( fieldPrefix+".stateCode" ) )[ 0 ];

		elementName = fieldPrefix + ".zipCode";
		zipCodeElem = ( document.getElementsByName( elementName ) )[ 0 ];
	
		elementName = fieldPrefix + ".aptNumber";
		aptElem = ( document.getElementsByName( elementName ) )[ 0 ];

		strNum = Trim( strNumElem.value );
		streetName = Trim( streetNameElem.value );
		city = Trim( cityElem.value );
		state = Trim( stateElem.value.toUpperCase( ) );
		zipCode = Trim( zipCodeElem.value );
		apt=Trim(aptElem.value);

		if( strNum == null  ||  strNum == "" )
		{
			if( msg == "" )
			{
				strNumElem.focus( );
			}
			msg += msgPrefix + " Street Number is required.\n";
		}
		else if( (streetNumRegex.test( strNum ) == false) || ( (isNaN( strNum ) == false) && (strNum <= 0) ) )
		{
			strNumElem.focus( );
			msg +=  msgPrefix + " Street Number must be a non-zero alphanumeric to validate.\n";
		}
		else if( isNaN( strNum.charAt( 0 ) ) )
		{
			strNumElem.focus( );
			msg +=  msgPrefix + " Street Number must begin with a numeric.\n";
		}

		if( zipCode == null  ||  zipCode == "" )
		{
			if( msg == "" )
			{
				zipCodeElem.focus( );
			}
			msg +=  msgPrefix + " Zip Code is required.\n";
		}
		else if( !zipre.exec( zipCode ) )
		{
			if( msg == "" )
			{
				zipCodeElem.focus( );
			}
			msg +=  msgPrefix + " Zip Code must be a 5 digit number.\n";
		}

		if( streetName == null || streetName == "" )
		{
			if( msg == "" )
			{
				streetNameElem.focus( );
			}
			msg +=  msgPrefix + " Street Name is required.\n";
		}

		if( city == null || city == "" )
		{
			if( msg == "" )
			{
				cityElem.focus( );
			}
			msg +=  msgPrefix +  " City is required.\n";
		}
		else if( !cityRegex.exec( city ) )
		{
			if( msg == "" )
			{
				cityElem.focus( );
			}
			msg +=  msgPrefix + " City must begin with a letter and be alphanumeric with spaces.\n";
		}

		if( apt == null || apt == "" )
		{
		}
		else if( !aptRegex.exec( apt ) )
		{
			if( msg == "" )
			{
				aptElem.focus( );
			}
			msg +=  msgPrefix + " Apt/Suite must be alphanumeric with spaces but cannot begin with a space.\n";
		}

		if( state == null  ||  state == "" )
		{
			if( msg == "" )
			{
				stateElem.focus( );
			}
			msg +=  msgPrefix + " State is required.\n";
		}

		if( msg != "" )
		{
			alert( msg );
			return false;
		}			

	return true;
}
//***End VALIDATE COMPLEX ADDRESS script
	
function validateServAddress(theForm){

	if(validateAddressGenericCustom( "cscServiceProviderForm",  "mailingAddress", "Mailing Address" )){
		<logic:equal name="cscServiceProviderForm" property="inHouse" value="false">
			if(theForm['sameAsMailingAddress'].checked){
				return true;
			}
			else{
				return validateAddressGenericCustom( "cscServiceProviderForm",  "billingAddress", "Billing Address" );
			}
		</logic:equal>
	}
	else{
		return false;
	}
	return true;
}
</script>
</head>
<body topmargin="0" leftmargin="0"  onKeyDown="checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayCSCServiceProviderUpdate" target="content" focus="mailingAddress.streetNumber">
<logic:equal name="cscServiceProviderForm" property="action" value="update">
   <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|19">
</logic:equal>
<logic:equal name="cscServiceProviderForm" property="action" value="create">
   <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|2">          
</logic:equal>
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--tabs start-->
                        <tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true"/>
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>
								<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;  -
									<logic:equal name="cscServiceProviderForm" property="action" value="update">
									   <bean:message key="title.update"/>
									</logic:equal>
									<logic:equal name="cscServiceProviderForm" property="action" value="create">
									   <bean:message key="title.create"/>          
									</logic:equal>
									<bean:message key="title.serviceProvider"/>&nbsp;<bean:message key="prompt.address"/></td>
							</tr>
						</table>
						<!-- END HEADING TABLE --> <!-- BEGIN ERRORS TABLE -->
					<table width="100%">
						<tr>
							<td align="center" class="errorAlert"><html:errors></html:errors></td>
						</tr>
					</table>
					<!-- END ERRORS TABLE --> <!-- BEGIN INSTRUCTION TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<tr>
								<td>
									<ul>
										<li>Enter the required fields and click Next to view summary.</li>
									</ul>
								</td>
							</tr>
							<tr>
								<td class="required"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
							</tr>
						</table>
						<!-- BEGIN  TABLE -->
						<table width="98%" border="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class="detailHead">
									<table width="100%" cellpadding="2" cellspacing="0">
										<tr>
											<td class="detailHead"><bean:message key="prompt.serviceProvider"/>&nbsp;<bean:message key="prompt.info"/></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<!--SP info start-->
									<table width="100%" border="0" cellpadding="4" cellspacing="1">
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.name"/></td>
											<td class="formDe" colspan="3"><bean:write name="cscServiceProviderForm" property="name"/></td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap><bean:message key="prompt.startDate"/></td>
											<td class="formDe"><bean:write name="cscServiceProviderForm" property="startDate" formatKey="date.format.mmddyyyy"/></td>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inHouse"/></td>
											<td class="formDe"><bean:write name="cscServiceProviderForm" property="inHouseAsStr"/></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.IFAS"/>&nbsp;<bean:message key="prompt.number"/></td>
											<td colspan="1" class="formDe"><bean:write name="cscServiceProviderForm" property="ifasNumber"/></td>
											<td class="formDeLabel" width="1%" nowrap>Faith Based</td>
											<td class="formDe">
											<logic:equal name="cscServiceProviderForm" property="isFaithBased" value="true">
												YES
											</logic:equal>
											<logic:equal name="cscServiceProviderForm" property="isFaithBased" value="false">
												NO
											</logic:equal>																							
												
											</td>										
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap>Phone</td>
											<td class="formDe" colspan="3"><msp:formatter name="cscServiceProviderForm" property="phoneNumber" format="A-P-F"/><logic:notEqual name="cscServiceProviderForm" property="phoneNumber.ext" value=""><bean:message key="prompt.ext"/> <msp:formatter name="cscServiceProviderForm" property="phoneNumber" format="X"/></logic:notEqual></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.faxNumber"/></td>
											<td class="formDe" colspan="3"><msp:formatter name="cscServiceProviderForm" property="faxNumber" format="A-P-F"/></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.website"/></td>
											
                                            <td class="formDe" colspan="3"> <logic:notEmpty name="cscServiceProviderForm" property="website"><bean:define id="web1" name="cscServiceProviderForm" property="website"/>
							<a href="//<%=web1%>" target="_new"><bean:write name="cscServiceProviderForm" property="website"/></a>
							</logic:notEmpty>
                             <logic:empty name="cscServiceProviderForm" property="website"><bean:write name="cscServiceProviderForm" property="website"/></logic:empty></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.email"/></td>
											<td class="formDe" colspan="3"><a href='mailto:<bean:write name="cscServiceProviderForm" property="email"/>'><bean:write name="cscServiceProviderForm" property="email"/> </a> </td>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.ftp"/></td>
											<td class="formDe" colspan="3"><bean:write name="cscServiceProviderForm" property="ftp"/></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.comments"/></td>
											<td class="formDe" colspan="3"><bean:write name="cscServiceProviderForm" property="comments"/></td>
										</tr>
									</table>
									<!--SP info End-->
									<table width="100%" cellpadding="1" cellspacing="1">
										<!-- BEGIN ADDRESS INFORMATION TABLES -->
										<tr>
											<td class="detailHead">
												<table width="100%" cellpadding="0" cellspacing="1">
													<tr class="detailHead">
														<td>
														<logic:equal name="cscServiceProviderForm" property="inHouse" value="true">
															<bean:write name="cscServiceProviderForm" property="name"/> <bean:message key="prompt.address"/>
														</logic:equal>
														<logic:notEqual name="cscServiceProviderForm" property="inHouse" value="true">
														<bean:message key="prompt.mailingAddress"/>
														</logic:notEqual>
														</td>
														<td align="right"><bean:message key="prompt.addressStatus"/>: <span class=errorAlert>	
    																	   <logic:equal name="cscServiceProviderForm" property="mailingAddressStatus" value="">
																      	       	UNPROCESSED
																   	       </logic:equal>       	    
																   	       <logic:equal name="cscServiceProviderForm" property="mailingAddressStatus" value="U">
																      	       	UNPROCESSED
																   	       </logic:equal>
																   	       <logic:equal name="cscServiceProviderForm" property="mailingAddressStatus" value="Y">
																       	       	VALID
																   	       </logic:equal>
																   	       <logic:equal name="cscServiceProviderForm" property="mailingAddressStatus" value="N">
																       	       	INVALID
																   	       </logic:equal>
																   	       &nbsp;</span></td>
													</tr>
													<tr class="detailHead">
														<td colspan="2" align="center">
													
														
														
															<input type="submit" value="<bean:message key='button.validate' />" name="submitAction" 
											onClick="return validateAddrAction('cscServiceProviderForm','/<msp:webapp/>validateCSCServiceProviderAddress.do','', 'mailingAddress','/jsp/administerServiceProvider/administerServiceProviderCSCD/serviceProviderAddressCreate.jsp', false);"></input>
														    	    <html:button property="org.apache.struts.taglib.html.BUTTON" 
														        				 onclick="return displayResearchWindow();">
																				  <bean:message key="button.research"></bean:message>
																    </html:button>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="1" cellspacing="1" border="0">
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.3.diamond"/></span><bean:message key="prompt.streetNumber"/></td>
														<td class="formDeLabel" colspan="2"><bean:message key="prompt.3.diamond"/></span><bean:message key="prompt.streetName"/></td>
													</tr>
													<tr>
														<td class="formDe"><html:text
																				name="cscServiceProviderForm"
																				property="mailingAddress.streetNumber" size="10"
																				maxlength="10" /></td>
														<td class="formDe" colspan="2"><html:text
																				name="cscServiceProviderForm"
																				property="mailingAddress.streetName" size="30"
																				maxlength="40" /></td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.streetType"/></td>
														<td class="formDeLabel" nowrap colspan="2"><bean:message key="prompt.aptSuite"/></td>
													</tr>
													<tr>
														<td class="formDe"><html:select
																				name="cscServiceProviderForm"
																				property="mailingAddress.streetTypeId" size="1">
																				<html:option value=""><bean:message key="select.generic"/></html:option>
                                        										<jims2:codetable codeTableName="<%=PDCodeTableConstants.STREET_TYPE%>"/></html:select></td>
														<td class="formDe" colspan="2"><html:text
																				name="cscServiceProviderForm"
																				property="mailingAddress.aptNumber" size="20"
																				maxlength="10" /></td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.city"/></td>
														<td class="formDeLabel"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.state"/></td>
														<td class="formDeLabel"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.zipCode"/></td>
													</tr>
													<tr>
														<td class="formDe"><html:text
																				name="cscServiceProviderForm"
																				property="mailingAddress.city" size="15"
																				maxlength="15" /></td>
														<td class="formDe"><html:select
																				name="cscServiceProviderForm"
																				property="mailingAddress.stateId" size="1"
																				styleId="state1"
																				onchange="javascript:enableCounty(this, 'mailingAddress')">
																				<html:option value="" ><bean:message key="select.generic"/></html:option>
																				<jims2:codetable codeTableName="<%=PDCodeTableConstants.STATE_ABBR%>"/>
																			</html:select></td>
														<td class="formDe"><html:text
																				name="cscServiceProviderForm"
																				property="mailingAddress.zipCode" size="5" maxlength="5"
																				onkeyup="return autoTab(this, 5);" /> - <html:text
																				name="cscServiceProviderForm"
																				property="mailingAddress.additionalZipCode" size="4" maxlength="4"
																				onkeyup="return autoTab(this, 4);" />
														</td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.addressType"/></td>
														<td class="formDeLabel" colspan="2"><bean:message key="prompt.county"/></td>
													</tr>
													<tr>
														<td class="formDe"><bean:write name="cscServiceProviderForm" property="mailingAddress.addressType"/></td>
														<td class="formDe" colspan="2">
															 <html:select name="cscServiceProviderForm" property="mailingAddress.countyId" styleId="county1" >
																				<html:option value="" ><bean:message key="select.generic"/></html:option>
																				<jims2:codetable codeTableName="<%=PDCodeTableConstants.COUNTY%>"/>
																			</html:select> 
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<!-- END ADDRESS INFORMATION TABLE -->
									</table>
									<br>
									<logic:equal name="cscServiceProviderForm" property="inHouse" value="false">
									<table width="100%" cellpadding="1" cellspacing="1">
										<!-- BEGIN ADDRESS INFORMATION TABLES -->
										<tr>
											<td class="detailHead">
												<table width="100%" cellpadding="0" cellspacing="1">
													<tr>
														<td width="1%" nowrap><span class="detailHead"><bean:message key="prompt.billingAddress"/></span>&nbsp;&nbsp;Same as Mailing Address
															<html:checkbox property="sameAsMailingAddress" value="true" onclick="showBilling(this)"/>
														</td>
														<td align="right"><bean:message key="prompt.addressStatus"/>: <span class=errorAlert>	
    																	   <logic:equal name="cscServiceProviderForm" property="billingAddressStatus" value="">
																      	       	UNPROCESSED
																   	       </logic:equal>       	    
																   	       <logic:equal name="cscServiceProviderForm" property="billingAddressStatus" value="U">
																      	       	UNPROCESSED
																   	       </logic:equal>
																   	       <logic:equal name="cscServiceProviderForm" property="billingAddressStatus" value="Y">
																       	       	VALID
																   	       </logic:equal>
																   	       <logic:equal name="cscServiceProviderForm" property="billingAddressStatus" value="N">
																       	       	INVALID
																   	       </logic:equal>
																   	       &nbsp;</span></td>
													</tr>
													<tr class="visibleTR" id="billingGisButtons">
														
														<td colspan="2" align="center">
															<input type="submit" value="<bean:message key='button.validate' />" name="submitAction" 
											onClick="return validateAddrAction('cscServiceProviderForm','/<msp:webapp/>validateCSCServiceProviderAddress.do','', 'billingAddress','/jsp/administerServiceProvider/administerServiceProviderCSCD/serviceProviderAddressCreate.jsp', false);"></input>
														    	    <html:button property="org.apache.struts.taglib.html.BUTTON" 
														        				 onclick="return displayResearchWindow();">
																				  <bean:message key="button.research"></bean:message>
																    </html:button>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										
										<tr id="billingAddress">
											<td>
												<table width="100%" cellpadding="1" cellspacing="1" border="0">
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.3.diamond"/></span><bean:message key="prompt.streetNumber"/></td>
														<td class="formDeLabel" colspan="2"><bean:message key="prompt.3.diamond"/></span><bean:message key="prompt.streetName"/></td>
													</tr>
													<tr>
														<td class="formDe"><html:text
																				name="cscServiceProviderForm"
																				property="billingAddress.streetNumber" size="10"
																				maxlength="10" /></td>
														<td class="formDe" colspan="2"><html:text
																				name="cscServiceProviderForm"
																				property="billingAddress.streetName" size="30"
																				maxlength="50" /></td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.streetType"/></td>
														<td class="formDeLabel" nowrap colspan="2"><bean:message key="prompt.aptSuite"/></td>
													</tr>
													<tr>
														<td class="formDe"><html:select
																				name="cscServiceProviderForm"
																				property="billingAddress.streetTypeId" size="1">
																				<html:option value=""><bean:message key="select.generic"/></html:option>
                                        										<jims2:codetable codeTableName="<%=PDCodeTableConstants.STREET_TYPE%>"/>
                                      											</html:select></td>
														<td class="formDe" colspan="2"><html:text
																				name="cscServiceProviderForm"
																				property="billingAddress.aptNumber" size="20"
																				maxlength="20" /></td>
													</tr>
													<tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.city"/></td>
														<td class="formDeLabel"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.state"/></td>
														<td class="formDeLabel"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.zipCode"/></td>
													</tr>
													<tr>
														<td class="formDe"><html:text
																				name="cscServiceProviderForm"
																				property="billingAddress.city" size="15"
																				maxlength="15" /></td>
														<td class="formDe"><html:select
																				name="cscServiceProviderForm"
																				property="billingAddress.stateId" size="1"
																				styleId="state1"
																				onchange="javascript:enableCounty(this, 'billingAddress')">
																				<html:option value="" ><bean:message key="select.generic"/></html:option>
																				<jims2:codetable codeTableName="<%=PDCodeTableConstants.STATE_ABBR%>"/>
																			</html:select></td>
														<td class="formDe"><html:text
																				name="cscServiceProviderForm"
																				property="billingAddress.zipCode" size="5" maxlength="5"
																				onkeyup="return autoTab(this, 5);" /> - <html:text
																				name="cscServiceProviderForm"
																				property="billingAddress.additionalZipCode" size="4" maxlength="4"
																				onkeyup="return autoTab(this, 4);" />
														</td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.addressType"/></td>
														<td class="formDeLabel" colspan="2"><bean:message key="prompt.county"/></td>
													</tr>
													<tr>
														<td class="formDe"><bean:write name="cscServiceProviderForm" property="billingAddress.addressType"/></td>
														<td class="formDe" colspan="2">
															 <html:select name="cscServiceProviderForm" property="billingAddress.countyId" styleId="county1" >
																				<html:option value="" ><bean:message key="select.generic"/></html:option>
																				<jims2:codetable codeTableName="<%=PDCodeTableConstants.COUNTY%>"/>
																			</html:select> 
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<!-- END ADDRESS INFORMATION TABLE -->
									</table>
									</logic:equal>
								</td>
							</tr>
						</table>
						<br>
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
									<html:submit property="submitAction" onclick="return validateServAddress(this.form)"><bean:message key="button.next" ></bean:message></html:submit>
									<html:submit property="submitAction" onclick="return reset(this.form)"><bean:message key="button.reset" /></html:submit>
									<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
								</td>
							</tr>
						</table>
						<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>
			<br>
		</td>
	</tr>
</table>
<!-- END  TABLE -->
<%-- BEGIN HIDDEN FIELDS FOR ADDRESS VALIDATION --%>

<script>
<logic:equal name="cscServiceProviderForm" property="inHouse" value="false">
showBilling(document.getElementsByName( 'sameAsMailingAddress')[ 0 ]);
enableCounty(document.getElementsByName( 'billingAddress.stateId')[ 0 ], 'billingAddress')
</logic:equal>
enableCounty(document.getElementsByName( 'mailingAddress.stateId')[ 0 ], 'mailingAddress')

</script>

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

</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

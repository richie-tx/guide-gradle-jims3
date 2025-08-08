<!DOCTYPE HTML>
<%-- Used to display juvenile traits details off Traits Tab in both Casefile and Juvenile Profile --%>
<%--MODIFICATIONS --%>
<%-- 09/11/2013		CShimek	ER 75751 Create page --%>
<%-- 10/22/2015 Richard Capestani #30817 MJCW: PROD Juv Profile > Drugs Tab > Add Drug - 4 Tiny Squares Are Visible (IE11 conversion) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCodeTableConstants" %>



<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- juvenileTraitsUpdate.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvTraits.js"></script>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript'>
function validateSelect()
{
	var fld= document.getElementById("statSel");
	if (fld.selectedIndex == 0)
	{
		alert("New Trait Status selection is required.");
		fld.focus();
		return false;
	}
	return true;
}
</script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<bean:define id="juvenileNumberDef" name="juvenileTraitsForm" property="juvenileNumber"/>

<html:form action="/submitJuvenileProfileTraitUpdate" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|0">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Update
			<logic:notEmpty name="juvenileTraitsForm" property="categoryName">
				<logic:equal name="juvenileTraitsForm" property="categoryName" value="<%=PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_MEDICAL_ISSUES%>">
					<bean:message key="prompt.medical"/>  
				</logic:equal>
				<logic:equal name="juvenileTraitsForm" property="categoryName" value="<%=PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_GANGS%>">
					<bean:message key="prompt.gang"/>
				</logic:equal>
				<logic:equal name="juvenileTraitsForm" property="categoryName" value="<%=PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_DRUGS%>">
					Drug/Substance Abuse
				</logic:equal>
				<logic:equal name="juvenileTraitsForm" property="categoryName" value="<%=PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_SCHOOL%>">
					<bean:message key="prompt.school"/>   
				</logic:equal>
				<logic:equal name="juvenileTraitsForm" property="categoryName" value="<%=PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_SPECIAL_INTERESTS%>">
					<bean:message key="prompt.specialInterest"/>
				</logic:equal>
    		</logic:notEmpty>		
		 Trait Status
		</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<div class=spacer></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul> 
				<li>Select a new Trait Status.</li>
				<li>Click Finish to complete status update.</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required"><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9> Required Fields</td>		  
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN JUVENILE HEADER INCLUDE --%> 
	<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
   		<tiles:put name="headerType" value="profileheader"/>
	</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%> 
<div class="spacer"></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top">
    		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
			 	    	<logic:notEqual name="juvenileTraitsForm" property="UICasefile" value="true">
    						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
    							<logic:empty name="juvenileTraitsForm" property="categoryName">
    								<tiles:put name="tabid" value="traitstab"/>
    							</logic:empty>
  
    							<logic:notEmpty name="juvenileTraitsForm" property="categoryName">
    								<tiles:put name="tabid" value="interviewinfotab"/>
    							</logic:notEmpty>		
    							<tiles:put name="juvnum" value='<%=juvenileNumberDef%>' />
    						</tiles:insert>		
    					</logic:notEqual>  
					</td>
			  	</tr>
  				<tr>
			  		<td bgcolor="#33cc66" height="5"></td>
  				</tr>
  			</table>

  			<table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableGreen">
  				<tr>
  					<td valign="top" align="center">
<%-- BEGIN TABLE --%>
            			<logic:notEmpty name="juvenileTraitsForm" property="categoryName">
            				<div class=spacer></div>
					  		<table width='98%' border="0" cellpadding="0" cellspacing="0">
						  		<tr>
							  		<td>
		  								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
		  									<tr>
		  										<td valign="top">
		  										<%--tabs start--%>
		  											<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
		  												<tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
		  												<tiles:put name="juvnum" value='<%=juvenileNumberDef%>' />
		  											</tiles:insert>	
		  										<%--tabs end--%>
		  										</td>
		  									</tr>
		  									<tr>
		  								  	<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
		  									</tr>
		  								</table>

									  <table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  										<tr>
  											<td valign="top" align="center">
						</logic:notEmpty>
    										    <div class="spacer"></div>
<%-- BEGIN CASEFILE INFO TABLE --%>    										    
	    										<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">					
	    											<tr>
	    												<td class="detailHead">Casefile Information</td>
	    											</tr>
	    											<tr>
	    												<td>
	    													<table cellpadding='2' cellspacing='1' width='100%'>
	    														<tr bgcolor='#cccccc'> 
	    															<td align="left" "subHead"><bean:message key="prompt.supervisionNumber" /></td>
	    															<td align="left" class="subHead"><bean:message key="prompt.probationOfficer" />&nbsp;<bean:message key="prompt.name" /></td>
	    															<td align="left" class="subHead"><bean:message key="prompt.supervision" />&nbsp;<bean:message key="prompt.type" /></td>
	    															<td align="left" class="subHead"><bean:message key="prompt.supervision" />&nbsp;End&nbsp;<bean:message key="prompt.date" /></td>
	    															<td align="left" class="subHead"><bean:message key="prompt.caseStatus" /></td>
	    														</tr>
	    														<tr>	
		    														<logic:iterate id="supIndx" name="juvenileTraitsForm" property="updateTraitCasefile" >												
		    															<td align="left">
		    														 		<a href="javascript:openWindow('/JuvenileCasework/displayJuvenileProfileCasefileDetails.do?casefileId=<bean:write name="supIndx" property="supervisionNum"/>')">
		    																	<bean:write name="supIndx" property="supervisionNum"/>
		    																</a>
		    															</td>
		    															<td align="left"><bean:write name="supIndx" property="probationOfficerFullName"/></td>
		    															<td align="left"><bean:write name="supIndx" property="supervisionType"/></td>
		    															<td align="left"><bean:write name="supIndx" property="supervisionEndDate" format="MM/dd/yyyy" /></td>
		    															<td align="left"><bean:write name="supIndx" property="caseStatus"/></td>	 
		    														</logic:iterate>
		    														<logic:empty name="juvenileTraitsForm" property="updateTraitCasefile" >
		    															<td colspan="5">Casefile not found for this trait</td>  
		    														</logic:empty>																							
		    													</tr>
		    												</table>
		    											</td>
	    											</tr>
	    										</table>
<%-- END CASEFILE INFO TABLE --%>
												<div class="spacer"></div>
<%-- BEGIN TRAITS TABLE --%>
								               	<tiles:insert page="../caseworkCommon/juvenileTraitStatusUpdate.jsp" />
<%-- END TRAITS TABLE --%> 
              									<div class="spacer"></div>		         
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</logic:notEmpty>
					<div class="spacer"></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>
</html:form>
<div class='spacer'></div>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
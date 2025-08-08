<!DOCTYPE HTML>
<%-- Used in closing of a casefile --%>
<%--MODIFICATIONS --%>
<%-- 09/10/2007	Uma Gopinath Created JSP --%>
<%-- 10/27/2008 C Shimek	defect#54930 removed extra '>' at end of nested:iterate which displayed on screen --%>
<%-- 07/12/2012 C Shimek    #73565 added age > 20 check (juvUnder21) to Save Changes button --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
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
<title><bean:message key="title.heading"/> - placementDetails.jsp</title>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/app.js"></script>
</head>

<body topmargin="0" leftmargin="0">
<html:form action="/displayCommonAppPlacementHistory" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|148">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
	<tr>
		<td align="center" class="header">Juvenile Casework - Close Casefile - Common App - Placement Details</td>
	</tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN MESSAGE TABLE -->
<logic:equal name="commonAppForm" property="action" value="confirm">
	<table width='100%'>
		<tr>
			<td align="center" class="confirm">Changes have been saved.</td>
	 	</tr>
	</table>
</logic:equal>
<!-- END MESSAGE TABLE -->
<div class="spacer"></div>
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%">
	<tr>
		<td>
			<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
				<ul>
					<li>Select radio buttons, then select the "Save Changes" button to save Placement information.</li>
				</ul>
			</logic:equal>	
		</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN HEADER INFO TABLE -->
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<!-- BEGIN DETAIL TABLE -->
<div class="spacer"></div>
<!-- Begin Casefile App Tabs -->
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
  		<td valign="top">
	      	<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
	        	<tiles:put name="tabid" value="commonApp"/>
	        	<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
	      	</tiles:insert>	
		</td>
	</tr>
</table>	      	
<!-- End Casefile App Tabs -->
<!-- Begin Casefile tabs blue border table -->
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
	<tr>
		<td valign="top" align=center>
           	<div class='spacer'></div>
<!-- Begin Juvenile Details Tabs Table -->           	 
 			<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--tabs start-->
						<tiles:insert page="../caseworkCommon/commonAppFormTabs.jsp" flush="true">
							<tiles:put name="tabid" value="JuvenileDetails"/>
							<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
						</tiles:insert>	
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor='#ff6666'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
				</tr>
			</table>
<!-- End Juvenile Details Tabs Table -->  
<!-- Begin Red Border Tabs Table -->  			
			<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
				<tr>
					<td valign="top" align=center>
						<div class="spacer"></div> 
<!-- Begin Court Order Detail Tabs -->
						<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<!--tabs start-->                                							
									<tiles:insert page="../caseworkCommon/commonAppJuvDetTabs.jsp" flush="true">
										<tiles:put name="tabid" value="Placement"/>
										<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
									</tiles:insert>	
									<!--tabs end-->
								</td>
							</tr>
							<tr>
								<td bgcolor='#008080'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
							</tr>
						</table>
<!-- End Court Order Detail Tabs -->
<!-- Begin Turquoise Border Tabs Table -->
						<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableTurquoise">
							<tr>
								<td valign="top" align="center">
									<div class="spacer"></div> 
<!-- BEGIN Placement History Details TABLE -->
									<table align="center" width="98%" cellspacing='1' cellpadding='2' class='borderTableBlue'>
										<tr class="detailHead">
											<td width="100%" valign="top" colspan=4>Placement History</td>
										</tr>
										<logic:empty name="commonAppForm" property="courtOrder.selectedPlacement">
											<tr>
												<td colspan="4" class="subhead">No Placement Facilities available for this Juvenile.</td>
											</tr>
										</logic:empty>
										<logic:notEmpty name="commonAppForm" property="courtOrder.selectedPlacement">
											<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
												<tr bgcolor='#cccccc'>
													<td width="50%" class=subHead>Facility</td>
													<td width="50%" class=subHead>Continued Placement Recommended</td>
												</tr>
												<nested:iterate name="commonAppForm" property="courtOrder.selectedPlacement" indexId="index">
													<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">  
														<td><nested:write property="facilityName"/></td>
														<td>
															Yes<nested:radio property="continuedStay" value="true"/> 
															No<nested:radio property="continuedStay" value="false"/>
														</td>
													</tr>
												</nested:iterate>
											</logic:equal>	
											<logic:notEqual name="juvenileCasefileForm" property="juvUnder21" value="true">
												<tr>
													<td class="subhead"> Juvenile is 21 or more years old - no updating is allowed.</td>
												</tr>	
											</logic:notEqual>
										</logic:notEmpty>
									</table>
<!-- END Placement History Details TABLE -->
									<div class="spacer"></div> 									
<!-- BEGIN BUTTON TABLE -->
									<table width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
												<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
													<html:submit property="submitAction"><bean:message key="button.saveChanges" /></html:submit>
												</logic:equal>	
												<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
											</td> 
										</tr>
									</table>
<!-- END BUTTON TABLE -->
									<div class="spacer"></div>
								</td>
							</tr>
						</table>
<!-- END Turquoise Border Tabs Table -->						
						<div class="spacer"></div>
					</td>
				</tr>
			</table>
<!-- END Red Border Tabs Table -->			
			<div class="spacer"></div>
		</td>
	</tr>
</table>
<!-- END Blue Border Tabs Table -->	
<div class="spacer"></div>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
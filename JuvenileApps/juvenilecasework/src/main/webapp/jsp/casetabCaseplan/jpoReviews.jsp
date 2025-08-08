<!DOCTYPE HTML>
<%------------------MODIFICATIONS ----------------------------%>
<%-- User selects the "Caseplan" tab on Juvenile Profile Master Details page after searching for a juvenile profile --%>
<%-- 01/29/2007	Debbie Williamson		Create JSP --%>
<%-- 07/17/2009 C Shimek                #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading" /> - jpoReviews.jsp</title>
<%--END HEADER TAG--%>

</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0">
<html:form action="/displayJPOReviews" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|170">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework" /> - Process Caseplan - JPO Reviews</td>
	</tr>
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>


<%-- BEGIN INSTRUCTION TABLE --%>
<div class="spacer"></div>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select <b>Entry Date</b> hyperlink to view JPO Reviews.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<logic:notEqual name="caseplanForm" property="juvProfile" value="true">

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
    <tiles:put name="headerType" value="casefileheader" />
</tiles:insert>
<%-- END HEADER INFO TABLE --%>


<%-- BEGIN DETAIL TABLE --%>
<div class="spacer"></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">

	    <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
	      <tiles:put name="tabid" value="goalstab" />
	      <tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
	    </tiles:insert>
	
	  	<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
	  		<tr>
	  			<td valign="top" align="center">
	  			<div class="spacer"></div>
	  			<table width='98%' border="0" cellpadding="0" cellspacing="0">
	  				<tr>
	  					<td valign="top">
	  						  <tiles:insert page="../caseworkCommon/casePlanTabs.jsp" flush="true">
	  								<tiles:put name="tabid" value="JPOReviews" />
	  								<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
	  							</tiles:insert>
	  					</td>
	  				</tr>
	  				<tr>
	  					<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5" alt=""></td>
	  				</tr>
	  			</table>
	  
	  			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
	  					<tr>
	  						<td valign="top" align="center">
</logic:notEqual>

<logic:equal name="caseplanForm" property="juvProfile" value="true">

<%-- BEGIN HEADER TABLE --%>
<div class="spacer"></div>
<table align="center" cellpadding="1" cellspacing="0" border="0" width='98%'>
  <tr>
    <td bgcolor='#cccccc'>
			<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="profileheader"/>
			</tiles:insert> <%--header info end--%>
		</td>
  </tr>
</table>
<%-- END HEADER TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<div class="spacer"></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    <td valign="top">
   		<table width='98%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<%--tabs start--%> 
            <tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
  						<tiles:put name="tabid" value="goalstab" />
  						<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
					  </tiles:insert> 
            <%--tabs end--%>		
					</td>
				</tr>
				<tr>
			  	<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
			</table>

			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
					<div class="spacer"></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
					  			<td valign="top">
					  				<tiles:insert page="../caseworkCommon/juvenileCasePlanTabs.jsp" flush="true">
					  					<tiles:put name="tabid" value="JPOReviews"/>
					  					<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
					  				</tiles:insert>				
					  			</td>
					  		</tr>
  						<tr>
		    		  	<td bgcolor='6699FF'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
		    			</tr>
					  </table>

					  <table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
 							<tr>
 								<td valign="top" align="center">
</logic:equal>	

										<%-- BEGIN Activities TABLE --%>
										<div class='spacer'></div>
										<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
											<tr>
												<td class="detailHead">JPO Reviews</td>
											</tr>
											<tr>
												<td>
			
													<table cellpadding="2" cellspacing="1" width='100%'>
														<logic:empty name="caseplanForm" property="jpoReviews">
															<tr>
			  												<td align="left">No JPO Reviews available.</td>
															</tr>
														</logic:empty>
			
														<logic:notEmpty name="caseplanForm" property="jpoReviews">
															<tr bgcolor='#cccccc'>
																<td class="subHead" width='1%' style="white-space: nowrap;">
																  <bean:message key="prompt.reviewDate" />
																	<jims:sortResults beanName="caseplanForm" results="jpoReviews" primaryPropSort="reviewDate" primarySortType="DATE" sortId="9" defaultSort="true" defaultSortOrder="DESC"/>
																</td>
																<td class="subHead">
																  <bean:message key="prompt.comments" />
																  <jims:sortResults beanName="caseplanForm" results="jpoReviews" primaryPropSort="reviewComments" primarySortType="STRING" sortId="10" />
																</td>
															</tr>
			
															<logic:iterate id="cpIndex" name="caseplanForm" property="jpoReviews" indexId="indexer">
																<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
																	<td align="left"><a href="/<msp:webapp/>displayJPOReviews.do?submitAction=<bean:message key='button.view'/>&amp;selectedValue=<bean:write name="cpIndex" property="caseplanRevId" />">
			 															<bean:write name="cpIndex" property="reviewDate" formatKey="date.format.mmddyyyy" /></a>
																	</td>
																	<td valign="top" align="left"><bean:write name="cpIndex" property="reviewComments" /></td>
																</tr>
															</logic:iterate>
														</logic:notEmpty>
													</table>
												</td>
											</tr>
										</table>
										<%-- END casefile TABLE --%>  <%-- BEGIN BUTTON TABLE --%>

										<div class="spacer"></div>
										<table border="0" align="center">
											<tr>
												<td align="center">
												  <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);"><bean:message key="button.back"></bean:message></html:button>&nbsp;
												</td>
												<td align="center">
												  <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
												</td>
											</tr>
										</table>
										<div class="spacer"></div>
										<%-- END BUTTON TABLE --%>
									</td>
							</tr>
						</table>
						<div class="spacer"></div>
					</td>
				</tr>
			</table>
			<%-- END DETAIL TABLE --%> <br>
			<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
		</td>
	</tr>
</table>

</html:form>

</body>
</html:html>


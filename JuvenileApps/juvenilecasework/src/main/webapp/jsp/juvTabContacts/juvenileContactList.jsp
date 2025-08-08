<!DOCTYPE HTML>
<%-- User selects the Contacts Tab in Juvenile Profile Detail --%>
<%--MODIFICATIONS --%>
<%-- 06/09/2005	Hien Rodriguez	Create JSP --%>
<%-- 12/12/2006 Hien Rodriguez  ER#37077 Add Tab & Buttons security features, add BackToTop link  --%>
<%-- 07/10/2012 C Shimek     	#73565 added age > 20 check (juvUnder21) to Add button --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
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

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - juvenileContactList.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js">
</script><!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/contacts.js"></script>
</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
 	<tr>
   	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.contact"/>&nbsp;<bean:message key="prompt.list"/></td> 
  </tr>  	
</table>
<%-- END HEADING TABLE --%>
<br>
<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager
    index="center"
    maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10"
    export="offset,currentPageNumber=pageNumber"
    scope="request">
    <input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
 	<tr>
   		<td>
			<ul>
        		<li>Click on hyperlinked Contact # to see details about that contact.</li>
      		</ul>
		</td>
  	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<html:form action="/displayJuvenileContactCreate" target="content">

<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|222">
<div class=spacer></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
 	<tr>
   		<td valign=top>
   			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="contactstab"/>
							<tiles:put name="juvNumId" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>	
					</td>
				</tr>
				<tr>
			  		<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
			  	</tr>
			</table>
			
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign=top align=center>
            <%-- BEGIN CONTACT TABLE --%>
						<div class=spacer></div>
						<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td valign=top class=detailHead><bean:message key="prompt.contacts" /></td>
							</tr>						
							<tr>
								<td>
									<table cellpadding=2 cellspacing=1 width='100%'>
									<%-- display detail header --%> 
										<logic:empty name="juvenileContactForm" property="contacts">
											<tr class=formDeLabel>
												<td colspan="4">No Contacts Available</td>										
											</tr>
										</logic:empty>

										<logic:notEmpty name="juvenileContactForm" property="contacts">
											<tr class=formDeLabel>
												<td nowrap><bean:message key="prompt.contact#" /> 
													<jims:sortResults sortId="1" beanName="juvenileContactForm" results="contacts" primaryPropSort="contactNum" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="DESC"/>
												</td>
												<td nowrap><bean:message key="prompt.entryDate" />
													<jims:sortResults sortId="2" beanName="juvenileContactForm" results="contacts" primaryPropSort="entryDate" primarySortType="DATE" secondPropSort="contactNum" secondarySortType="INTEGER" hideMe="false"/>
												</td>
												<td nowrap><bean:message key="prompt.name" />
													<jims:sortResults sortId="3" beanName="juvenileContactForm" results="contacts" primaryPropSort="formattedName" primarySortType="STRING" secondPropSort="contactNum" secondarySortType="INTEGER"/>
												</td>
												<td nowrap><bean:message key="prompt.relationship" />
													<jims:sortResults sortId="4" beanName="juvenileContactForm" results="contacts" primaryPropSort="relationship" primarySortType="STRING" secondPropSort="contactNum" secondarySortType="INTEGER"/>
												</td>
												<td nowrap><bean:message key="prompt.agency" />
													<jims:sortResults sortId="5" beanName="juvenileContactForm" results="contacts" primaryPropSort="currentAgencyInvolvement" primarySortType="STRING" secondPropSort="contactNum" secondarySortType="INTEGER"/>
												</td>	
												<td nowrap>Detention Visit
													<jims:sortResults sortId="6" beanName="juvenileContactForm" results="contacts" primaryPropSort="detentionVisit" primarySortType="STRING" secondPropSort="contactNum" secondarySortType="INTEGER"/>
												</td>		
												<td nowrap>ID/Type												
												</td>													
											</tr>
										<%-- display detail info --%>  
		   									<logic:iterate id="contactIndex" name="juvenileContactForm" property="contacts" indexId="indexCL">
											<%-- Begin Pagination item wrap --%>
	            		 						 <pg:item>
													<tr class="<%out.print( (indexCL.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
														<td><a href="/<msp:webapp/>displayJuvenileContactDetails.do?contactNum=<bean:write name="contactIndex" property="contactNum"/>">
															<bean:write name="contactIndex" property="contactNum" /></a>
														</td>
														<td><bean:write name="contactIndex" property="entryDate" formatKey="date.format.mmddyyyy"/></td>
														<td><bean:write name="contactIndex" property="formattedName" /></td>
														<td><bean:write name="contactIndex" property="relationship" /></td>
														<td><bean:write name="contactIndex" property="currentAgencyInvolvement" /></td>
														<td>
														<logic:equal name="contactIndex" property="detentionVisit" value="true">Yes</logic:equal>
														<logic:equal name="contactIndex" property="detentionVisit" value="false">NO</logic:equal>
														</td>
														<td>
															<logic:notEmpty name="contactIndex" property="driverLicenseNum">	
																<bean:write name="contactIndex" property="driverLicenseNum"/> / DL Number
															</logic:notEmpty>
															<logic:empty name="contactIndex" property="driverLicenseNum">
															<logic:notEmpty name="contactIndex" property="stateIssuedIdNum">
																<bean:write name="contactIndex" property="stateIssuedIdNum"/> / State ID 
															</logic:notEmpty>
															<logic:empty name="contactIndex" property="stateIssuedIdNum">
															<logic:notEmpty name="contactIndex" property="passportNum">
																<bean:write name="contactIndex" property="passportNum"/> / Passport Number
															</logic:notEmpty>
															</logic:empty>
															</logic:empty>
														</td>     
													</tr>
												</pg:item>
	            		   <%-- End Pagination item wrap --%>
											</logic:iterate>
										</logic:notEmpty>
									</table>

									<%-- Begin Pagination navigation Row--%>
				            		<table align="center">
				            		  	<tr>
				            				<td>
				              					<pg:index>
				              						<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
				              							<tiles:put name="pagerUniqueName" value="pagerSearch"/>
				              							<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
				              						</tiles:insert>
				              			 		</pg:index>
				            		    	</td>
				           		    	</tr>
				         		    </table>
				            		<%-- End Pagination navigation Row--%>
								</td>
							</tr>
						</table>
						<%-- END CONTACT TABLE --%>
						<div class="spacer"></div>
						<div align="center">
						<%-- BEGIN BUTTON TABLE --%>
	  						<table>	
	  							<tr>
	  								<td>
	  									<html:button property="button.back" onclick="history.back();">
	  										<bean:message key="button.back" />
	  								  	</html:button>
	  								  	<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_CONTACTS_U%>'>
	  								  		<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">	
	  								  		<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">			
	  											<html:submit property="submitAction"><bean:message key="button.addContact"/></html:submit>
	  										</logic:notEqual>
	  										<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
											<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
												<html:submit property="submitAction"><bean:message key="button.addContact"/></html:submit>
											</jims2:isAllowed>
											</logic:equal>
	  										</logic:equal>	
	  									</jims2:isAllowed>
	  								</td>
	     						</html:form>
	
	     						<html:form action="/displayJuvenileMasterInformation" target="content">
	  								<td><html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit></td>
	  						  	</html:form>
	  							</tr>
	  						</table>
						</div>
						<%-- END BUTTON TABLE --%>
						<div class="spacer"></div>
					</td>
				</tr>
			</table>
 		</td>
 	</tr>
</table>
<%-- END DETAIL TABLE --%>

<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>
<%-- END FORM --%>
<br>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>

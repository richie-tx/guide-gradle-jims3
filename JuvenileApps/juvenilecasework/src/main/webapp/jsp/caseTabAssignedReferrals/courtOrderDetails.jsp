<!DOCTYPE HTML>

<%-- 07/13/2007	Uma Gopinath Create JSP --%>
<%-- 07/17/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 08/31/2015     RCapestani #29399 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Casefile Referrals UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<meta name="GENERATOR" content="IBM WebSphere Studio">
<meta http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - courtOrderDetails.jsp</title>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>
</head>
<%--END HEAD TAG--%>


<body topmargin="0" leftmargin="0">
<html:form action="/displayJuvenileCasefileCourtOrderList" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|352">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
    <td align="center" class="header">Juvenile Casework - Court Orders</td>
  </tr>
</table>
<!-- END HEADING TABLE -->


<!-- BEGIN INSTRUCTION TABLE -->
<div class="spacer"></div>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
       <li>Select a hyperlinked Court Order to view its details..</li>
      </ul>
	  </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN ERROR TABLE -->
<table width="100%">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
</table>
<!-- END ERROR TABLE -->

<%-- BEGIN JUVENILE HEADER TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER TABLE --%>

<div class="spacer"></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign="top">  		

  		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
  			<tiles:put name="tabid" value="assignedreferralstab" />
  			<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
  		</tiles:insert>
  
		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
     	<tr>
		    <td valign="top" align="center">
  				<div class="spacer"></div>
			    <table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  		  		<tr>
  		    		<td valign="top">
    		    		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
      						<tr>
      							<td valign="top">
      								<!--tabs start -->
      								<tiles:insert page="../caseworkCommon/casefilePetitionTabs.jsp" flush="true">
      		  				    <tiles:put name="tabid" value="courtOrder"/>
      		  				    <tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
      		  			    </tiles:insert>	
      								<!--tabs end -->
      							</td>
      						</tr>
     					 	  <tr>
    					  		<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
    					  	</tr>
    					  </table>

           			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
         			    <tr>
           				  <td valign="top" align="center">
            
          						<!-- begin disposition table -->
      								<div class="spacer"></div>
            					<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
            						<tr>
            							<td class="detailHead">Court Order</td>
            						</tr>
            						<tr>
              						<td>
              							<table cellpadding="2" cellspacing="1" width='100%'>
              								<logic:empty name="petitionDetailsForm" property="courtOrders"> 
              								  <tr class="detailHead">
      														<td colspan="4">No Court Order available for this Petition.</td>
    												    </tr>
               								</logic:empty>

               								<logic:notEmpty name="petitionDetailsForm" property="courtOrders"> 
      													<tr class="formDeLabel">  
								                  <td valign="top"><bean:message key="prompt.court"/> <bean:message key="prompt.date"/></td>
								                  <td valign="top"><bean:message key="prompt.court"/></td>
								                  <td valign="top"><bean:message key="prompt.hearingType"/></td>
								                  <td valign="top"><bean:message key="prompt.attorneyName"/></td>	
								                  <td valign="top"><bean:message key="prompt.offense"/></td>		                
								                </tr>			                      

  															<logic:iterate id="resultsIndex" name="petitionDetailsForm" property="courtOrders" indexId="index">					                    
  											          <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
  								                  <td valign="top"><a><bean:write name="resultsIndex" property="courtDate" formatKey="date.format.mmddyyyy" /></a></td>
  								                  <td valign="top"><bean:write name="resultsIndex" property="court" /></td>
  								                  <td valign="top"><bean:write name="resultsIndex" property="hearingType"/></td>
  								                  <td valign="top"><bean:write name="resultsIndex" property="attorneyName" /></td>
  								                  <td valign="top"><bean:write name="resultsIndex" property="offense" /></td>																								                    																			                 
										              </tr>
  		                          </logic:iterate>
      												</logic:notEmpty>

               							</table>
														<div class="spacer"></div>
               						</td>
               					</tr>
             					</table>
											<div class="spacer"></div>
           					</td>
           				</tr>
           			</table>
								<div class="spacer"></div>
           		</td>
        	  </tr>
      	  </table>
		    </td>
			</tr>
		</table> 
	</td>
 </tr>
</table>


</html:form> 
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>


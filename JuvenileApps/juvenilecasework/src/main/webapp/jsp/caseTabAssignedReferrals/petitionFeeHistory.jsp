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
<title><bean:message key="title.heading" /> - petitionFeeHistory.jsp</title>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>
</head>
<%--END HEAD TAG--%>

<body topmargin="0" leftmargin="0">
<html:form action="/handleJuvenileCasefileFeeSelection" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|354">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
   <tr>
    <td align="center" class="header" >Juvenile Casework - Fee Payment History</td>
  </tr>
</table>
<!-- END HEADING TABLE -->


<!-- BEGIN INSTRUCTION TABLE -->
<div class="spacer"></div>
<table width="98%" border="0">
  <tr>
    <td>
  	  <ul>
        <li>Select the View Fee Summary button to view the Fee Summary page.</li>
        <li>Select the View All Payments button to view all payments for all Fee Types.</li>
      </ul>
     </td> 
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->


<!-- BEGIN DETAIL TABLE -->
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>


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

            <!-- Begin Caseplan Tabs -->
						<div class="spacer"></div>
        		<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
      		  	<tr>
      		    	<td valign="top">
      		    		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
        						<tr>
        							<td valign="top">
        								<!--tabs start -->
        								 <tiles:insert page="../caseworkCommon/casefilePetitionTabs.jsp" flush="true">
  					  				    <tiles:put name="tabid" value="fees"/>
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
    						      <td><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
    					      </tr>
            			  <tr>
            				 <td valign="top" align="center">
      									<!-- begin fee history table -->
             						<tiles:insert page="../caseworkCommon/petitionFeeHistoryTile.jsp" flush="true">
             							<tiles:put name="petitionDetailsForm" beanName="petitionDetailsForm"/>              																						
      									</tiles:insert>	       					
            					</td>
            			  </tr>
            			</table>
									<br>
      					</td>
      			  </tr>
      			</table>
					</td>
			  </tr>
			</table>
	   	</td>
  </tr>
</table>
<!-- END DETAIL TABLE -->


</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>


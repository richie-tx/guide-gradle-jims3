<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 08/09/2007	Uma Gopinath Create JSP --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 08/24/2015 RCapestani #29441 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Juvenile Profile Referrals) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

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
<title><bean:message key="title.heading" /> - petitionFees.jsp</title>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
</head>
<%--END HEAD TAG--%>


<body topmargin="0" leftmargin="0">
<html:form action="/handleJuvenileProfileFeeSelection" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|433">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
    <td align="center" class="header" >Juvenile Casework - Juvenile Profile - Petition Fee List</td>
  </tr>

  
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<br>
<table width="98%" border="0">
  <tr>
    <td>
  	  <ul>
        <li>Select a hyperlinked Fee Type to view payment details for that Fee Type.</li>
        <li>Select the View All Payments button to view all payments for all Fee Types.</li>
      </ul>
  	 </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->
<%-- BEGIN JUVENILE HEADER TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
		<tiles:put name="headerType" value="profileheader" />
	</tiles:insert>
<%-- END JUVENILE HEADER TABLE --%>

<!-- BEGIN DETAIL TABLE -->

<br>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign="top">
    	<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
				<td valign="top"><tiles:insert
					page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
					<tiles:put name="tabid" value="referralstab" />
					<tiles:put name="juvnum"
						value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
				</tiles:insert></td>
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

            <!-- Begin Caseplan Tabs -->
        		<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
      		  	<tr>
      		    	<td valign="top">
      		    		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
        						<tr>
        							<td valign="top">
        								<!--tabs start -->
        								 <tiles:insert page="../caseworkCommon/juvenileProfilePetitionTabs.jsp" flush="true">
									  		<tiles:put name="tabid" value="fees"/>
									  	    <tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
									  	 </tiles:insert>	
        								<!--tabs end -->
        							</td>
        						</tr>
       					 	  <tr>
      					  		<td bgcolor='#6699ff'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
      					  	</tr>
      					  </table>

            			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
            			 	<tr>
							      <td><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
						      </tr>
            			  <tr>
            				  <td valign="top" align="center">

            					<!-- begin fee summary table -->
              						<tiles:insert page="../caseworkCommon/petitionFeesTile.jsp" flush="true">
              							<tiles:put name="petitionDetailsForm" beanName="petitionDetailsForm"/>
              							<tiles:put name="type" value="profile"/>																
									</tiles:insert>	
            					</td>
            			  </tr>
            			</table><br>
      					</td>
      			  </tr>
      			</table>
		</td>
  </tr>
</table>
  
<!-- END DETAIL TABLE -->

<br>

</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
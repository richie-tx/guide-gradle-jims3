<!DOCTYPE HTML>
<%-- User selects the Disposition tab --%>
<%--MODIFICATIONS --%>
<%-- 06/28/2007	Uma Gopinath Create JSP --%>
<%-- 08/24/2015 RCapestani #29441 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Juvenile Profile Referrals) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - petitionDispositions.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head>

<body topmargin=0 leftmargin="0">
<html:form action="/displayJuvenileCasefileAssignedReferralsList" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|431">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
    <td align="center" class="header" >Juvenile Casework - Juvenile Profile - Assigned Referral Dispositions</td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td>
  	  <ul>
        <li>Select a hyperlinked Disposition Date to view Disposition detail.</li>
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
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp"
		flush="true">
		<tiles:put name="headerType" value="profileheader" />
	</tiles:insert>
<%-- END JUVENILE HEADER TABLE --%>

<!-- BEGIN DETAIL TABLE -->
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top>
    	<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top"><tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
						<tiles:put name="tabid" value="referralstab" />
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>
					</td>
				</tr>
			  <tr>
			  	<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
			  </tr>
			</table>
			
      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
        <tr>
					<td valign=top align=center>

            <!-- Begin Caseplan Tabs -->
            <div class='spacer'></div>
        		<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
      		  	<tr>
      		    	<td valign=top>
      		    		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
        						<tr>
        							<td valign=top>
        								<!--tabs start -->
        								<tiles:insert page="../caseworkCommon/juvenileProfilePetitionTabs.jsp" flush="true">
						  				    <tiles:put name="tabid" value="dispositions"/>						  				   
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
            				  <td valign=top align=center>
            
            						<!-- begin disposition table -->
             						<tiles:insert page="../caseworkCommon/petitionDispositionsTile.jsp" flush="true">
             							<tiles:put name="petitionDetailsForm" beanName="petitionDetailsForm"/>
             							<tiles:put name="type" value="profile"/>																
												</tiles:insert>	
                      
            					</td>
            			  </tr>
            			</table>
            			<div class=spacer></div>
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
<%-- END FORM --%> 
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
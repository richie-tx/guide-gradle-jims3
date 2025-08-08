<!DOCTYPE HTML>
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

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - educationTesting.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<form name="educationTestingForm">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.testing"/>&nbsp;<bean:message key="title.details"/>
    </td>
  </tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>

<!-- BEGIN INSTRUCTION TABLE -->
<br>
<table width="98%" border="0">
	<tr>
		<td>&nbsp;</td>
    </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<!--juv profile header start-->
<table align="center" cellpadding='1' cellspacing='0' border='0' width='98%'>
	<tr>
		<td bgcolor='#cccccc'>
			<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	           <tiles:put name="headerType" value="profileheader"/>
            </tiles:insert>
		</td>
	</tr>
</table>
<!--juv profile header end-->

<!-- BEGIN DETAIL TABLE -->
<div class="spacer"></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign="top">
      <table width='100%' border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td valign="top">
            <tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="interviewinfotab"/>
				<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
			</tiles:insert>
          </td>
        </tr>
        <tr>
          <td bgcolor='#33cc66'><img src='../../common/images/spacer.gif' width=5></td>
        </tr>
      </table>

      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  			<tr>
  				<td valign="top" align="center">
  					<!-- BEGIN TABLE -->
						<div class="spacer"></div>
  					<table width='98%' border="0" cellpadding="0" cellspacing="0">
  						<tr>
  							<td>
  								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  									<tr>
  										<td valign="top">
  										<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
										      <tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
										      <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
									       </tiles:insert>
  										</td>
  									</tr>
  									<tr>
 									  	<td bgcolor='#6699FF'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
 									  </tr>
 								</table>
								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
									<tr>
										<td valign="top" align="center">
											<div class="spacer"></div>
											<table width='98%' border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign="top">
														<tiles:insert page="../caseworkCommon/educationTabs.jsp" flush="true">
                                                           <tiles:put name="tabid" value="testing"/>
                                                           <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
                                                        </tiles:insert>
													</td>
												</tr>
												<tr>
													<td bgcolor='#ff6666'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
												</tr>
											</table>											
          								<!-- BEGIN TRAITS TABLE -->
										<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
        									<tr>
        										<td valign="top" align="center">
													<div class="spacer"></div>			
														<span id="HSEPSection" class="visible">
															<div class="spacer"></div>
															<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
																<tr>
																	<td valign="top" align="center"> Not Available at this Time</td>
																</tr>
															</table>
														</span>  
												<!-- BEGIN BUTTON TABLE -->
															<div class="spacer"></div>
											  <!-- END BUTTON TABLE -->
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
						<div class="spacer"></div><!-- END TABLE -->
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<!-- END DETAIL TABLE -->

<br>
</form>
<div align='center'>[<a href='#top'>Back to Top</a>]</div>

</body>
</html:html>
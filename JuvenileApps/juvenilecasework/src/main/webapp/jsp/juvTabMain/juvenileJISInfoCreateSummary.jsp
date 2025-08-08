<!DOCTYPE HTML>
<%-- Used to create Juvenile Information Sharing system off of Main Tab from Juvenile Profile Search --%>



<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
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

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvenileProfileInfo.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<html:base />
<title><bean:message key="title.heading"/> - juvenileJISInfoCreateSummary.jsp</title>


</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">

<html:form action="/submitJuvenileJISInformationCreate" target="content">


<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Add JIS Details 
		 <logic:notEqual name="juvenileProfileMainForm" property="action" value="confirmJIS">Summary</logic:notEqual>
		  <logic:equal name="juvenileProfileMainForm" property="action" value="confirmJIS">Confirmation</logic:equal></td>
	</tr>
	<tr>
		<td class="confirm"> <logic:equal name="juvenileProfileMainForm" property="action" value="confirmJIS">JIS Details successfully created.</logic:equal></td>
	</tr>
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%>

	<table width="98%" border="0" align="center">   
		<tr>
			<td>
				<logic:notEqual name="juvenileProfileMainForm" property="action" value="confirmJIS">
					<ul>
						 <li>Review information and select the Finish button.</li>
		        	 	 <li>Select the Back button to change information.</li>
						
					</ul>
				</logic:notEqual>
				 <logic:equal name="juvenileProfileMainForm" property="action" value="confirmJIS">
				      <ul>
				        <li>Select the Juvenile Profile Master Details button.</li>
				      </ul>				 
	 			</logic:equal>
			</td>
		</tr>
	</table>

<%-- END INSTRUCTION TABLE --%>

<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%--juv profile header end--%>
<%--BEGIN FORM TAG--%>
<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign='top'>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign='top'>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="maintab"/>
							<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
							<tiles:put name="juvenileProfileMainForm" beanName="juvenileProfileMainForm"/>	
						</tiles:insert>				
					</td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
				</tr>
			</table>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  	  			<tr>
					<td valign='top' align='center'>
  					<%-- BEGIN INTERVIEW INFO TABS OUTER TABLE --%>
	  					<table width='98%' border="0" cellpadding="0" cellspacing="0">
	  						<tr>
	  							<td>
  								<%-- BEGIN INTERVIEW INFO TABS INNER TABLE --%>
									<div class='spacer'></div>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign='top'>
												<%--tabs start--%>
												<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
													<tiles:put name="tabid" value="profileInfo"/>
													<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
													<tiles:put name="juvenileProfileMainForm" beanName="juvenileProfileMainForm"/>	
												</tiles:insert>	
												<%--tabs end--%>
											</td>
										</tr>
										<tr>
											<td bgcolor='#6699FF'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
										</tr>
									</table>  
									<%-- END INTERVIEW INFO TABS INNER TABLE --%>
									
  									<%-- BEGIN TAB BLUE BORDER TABLE --%>
  									<table cellpadding="4" cellspacing="1" width='100%' class="borderTableBlue">
  										<tr>
  											<td valign='top' class='detailHead' colspan="6"> Agency with Confirmed Record:         
                      						</td> 
        								</tr> 
        							
        								<tr>        									
        									<td class="formDeLabel" width="10%">Entry Date: </td>
        									<td class="formDe"><bean:write name="juvenileProfileMainForm" property="currentJISInfo.entryDate"/></td>  			
										</tr> 
										<tr>        									
        									<td class="formDeLabel" width="10%">Agencies: </td>
        									<td class="formDe"><bean:write name="juvenileProfileMainForm" property="currentJISInfo.agency"/>
        									 <logic:notEmpty name="juvenileProfileMainForm" property="currentJISInfo.otherAgency">: <bean:write name="juvenileProfileMainForm" property="currentJISInfo.otherAgency"/></logic:notEmpty></td>  			
										</tr> 
										<tr>
											<td ><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
										</tr>
    								</table>
    							<%-- END TAB BLUE BORDER TABLE --%>
    								<div class="spacer"></div>
									<table align="center">
										<tr>
											<td>
												 <logic:notEqual name="juvenileProfileMainForm" property="action" value="confirmJIS">
					  								<html:button property="button.back" styleId="jisCreateBack"><bean:message key="button.back" /></html:button>  
													<html:submit property="submitAction" styleId="jisFinish">				
															<bean:message key="button.finish" />
								  					</html:submit>	
					  									<html:submit property="submitAction">				
															<bean:message key='button.cancel'/>
								  					</html:submit>	
								  				</logic:notEqual>
								  				 <logic:equal name="juvenileProfileMainForm" property="action" value="confirmJIS">
								  				 	<input type="button" onclick="goNav('/<msp:webapp/>displayJuvenileMasterInformation.do');" value="<bean:message key='button.juvenileProfileMasterDetails'/>"/>	
								  				 </logic:equal>
										  </td>
							    		</tr>
									</table>
								</td>
							</tr> 
						</table>
						
									
						<%-- END INTERVIEW INFO TABS OUTER TABLE --%>
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<%-- END DETAIL TABLE --%>
<div class='spacer'></div>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</html:form>
</body>
</html:html>

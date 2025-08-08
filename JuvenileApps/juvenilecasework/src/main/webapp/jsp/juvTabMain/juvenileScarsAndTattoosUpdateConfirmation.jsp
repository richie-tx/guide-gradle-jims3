<!DOCTYPE HTML>
<%-- User selects the "Next" button from Create Physical Characteristics page for Summary page, --%>
<%-- then User selects the "Finish" button from Summary page for Confirmation page --%>
<%--MODIFICATIONS --%>
<%-- 06/17/2005	Hien Rodriguez	Create JSP --%>

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
<title><bean:message key="title.heading"/> - juvenilePhysicalCharacteristicsCreateSummary.jsp</title>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvenileProfileInfo.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>

</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:form action="/submitJuvenilePhysicalCharacteristicsCreate" target="content">
<logic:equal name="juvenilePhysicalCharacteristicsForm" property="action" value="summary">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|23">
</logic:equal>
<logic:equal name="juvenilePhysicalCharacteristicsForm" property="action" value="confirm">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|24">
</logic:equal>

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header">
    	<logic:equal name="juvenilePhysicalCharacteristicsForm" property="action" value="summary">
    		<bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Update Physical Characteristics - Scars & Marks / Tattoos Summary 
    	</logic:equal>
    	
    	<logic:equal name="juvenilePhysicalCharacteristicsForm" property="action" value="confirm">			
    		<bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Update Physical Characteristics - Scars & Marks / Tattoos Confirmation   				
				<tr>
					<td class="confirm">Physical Characteristics successfully 
					<logic:notEqual name="juvenilePhysicalCharacteristicsForm" property="status" value="update">created.</logic:notEqual>
					<logic:equal  name="juvenilePhysicalCharacteristicsForm" property="status" value="update">updated.</logic:equal></td>
				</tr>
			</logic:equal>  	
    </td>	  	   
  </tr>  	
</table>
</html:form>
<%-- END HEADING TABLE --%>

<html:form action="/saveJuvenileTattooAndScarsCreateSummary" target="content">

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
    <tr>
      <td>
        <ul>
	    		<logic:equal name="juvenilePhysicalCharacteristicsForm" property="action" value="summary">
	          <li>Review information and select the Finish button to view the Confirmation page.</li> 
						<li>Select the Juvenile Profile Master Details button to view that page.</li>
					</logic:equal> 

	    		<logic:equal name="juvenilePhysicalCharacteristicsForm" property="action" value="confirm">
						<li>Select the Juvenile Profile Master Details button to view that page.</li>
					</logic:equal> 
        </ul>
      </td>    
    </tr>
</table>
<%-- END INSTRUCTION TABLE --%>


<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%--juv profile header end--%>


<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>  
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
 	<tr>
   	<td valign='top'>
   	
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
    		<tr>
       		<td valign='top'>
           <tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
      	        <tiles:put name="tabid" value="maintab"/>
         	    	<tiles:put name="juvNumId" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
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
						<div class='spacer'></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<%-- BEGIN INTERVIEW INFO TABS INNER TABLE --%>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign='top'>
												<%--tabs start--%>
												<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
													<tiles:put name="tabid" value="profileInfo"/>
													<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
												</tiles:insert>	
												<%--tabs end--%>
											</td>
										</tr>
										<tr>
									  	<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
									  </tr>
								  </table>
									<%-- END INTERVIEW INFO TABS INNER TABLE --%>

								<%-- BEGIN TAB BLUE BORDER TABLE --%>
								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
									<tr>
										<td valign=top align=center>			
                 			<%-- BEGIN PHYSICAL CHARACTERISTICS INFO TABLE --%>
											<div class='spacer'></div>
		   								<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		   									<tr>
		   										<td class='detailHead' colspan="4">Physical Characteristics</td>
		   									</tr>
		       							<tr> 
		   							      <td valign='top' class='formDeLabel' nowrap='nowrap'>Scars & Marks</td> 
												  <td class='formDe'><bean:write name="juvenilePhysicalCharacteristicsForm" property="allScars"/></td>
												</tr>
		   									<tr>
		   										<td class='formDeLabel' valign='top'>Tattoos</td>
		   										<td class='formDe'><bean:write name="juvenilePhysicalCharacteristicsForm" property="allTatoosDesc"/></td>
		   									</tr>
		   									<tr>
		   										<td class='formDeLabel' valign='top'>Other Tattoo Comments</td>
		   										<td class='formDe'><bean:write name="juvenilePhysicalCharacteristicsForm" property="newOtherTattooComments"/></td>
		   									</tr>  			
		   								</table><!-- END PHYSICAL CHARACTERISTICS INFO TABLE -->


											<%-- BEGIN BUTTON TABLE --%>
											<table align='center'>
										  	<tr>
									    		<logic:equal name="juvenilePhysicalCharacteristicsForm" property="action" value="summary">
	  						
											    	<td>
															<html:button property="button.back" styleId="tatooConfirmBack">
																<bean:message key="button.back"></bean:message>
															</html:button>
															<html:submit property="submitAction" styleId="tatooConfirmFinish">
																<bean:message key="button.finish"></bean:message>
															</html:submit>
											    	</td>
											    	<td>
															<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>			
											    	</td>
							
												</logic:equal>	

										
									    	<td>
													<logic:equal name="juvenilePhysicalCharacteristicsForm" property="action" value="confirm">
														<html:submit property="submitAction" styleId="profileMasterDetails">				
															<bean:message key="button.juvenileProfileMasterDetails"></bean:message>
														</html:submit>
													</logic:equal>	    	    					      		
									    	</td>
										
									  	</tr>
									</table>
									</div>
									<div class='spacer'></div>
									<%-- END BUTTON TABLE --%>
									</td> 
								</tr> 
			  			</table>
							<%-- END TAB BLUE BORDER TABLE --%>
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
</html:form>
<div class='spacer' align='center'><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

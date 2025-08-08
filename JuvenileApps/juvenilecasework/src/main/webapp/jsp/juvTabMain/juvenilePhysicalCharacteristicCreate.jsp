<!DOCTYPE HTML>
<%-- User selects the "Add More Characteristics" button from Juvenile Profile Master Details Main tab --%>
<%--MODIFICATIONS --%>
<%-- 06/16/2005	Hien Rodriguez	Create JSP --%>
<%-- 03/27/2006 LDeen		Revised JSP to <html:option key="select.multiple" value="" /> on tattoo drop down list --%>
<%-- 03/05/2007 CShimek		#39719 added max. length check to Other Tattoo Comments field.  --%>

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

<%--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS--%>
<html:javascript formName="juvenilePhysicalCharacteristicsForm" />

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - juvenilePhysicalCharacteristicsCreate.jsp</title>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvenileProfileInfo.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  	<tr>
    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Create <bean:message key="title.physicalCharacter"/></td> 
  	</tr>  	
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
    <tr>
      <td>
        <ul>
          <li>Please enter required fields.</li>
          <li>Select the Next button to view the Summary page. </li>
        </ul>
      </td>    
    </tr>
    <tr>
      <td class="required"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
    </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%--juv profile header end--%>

<%-- BEGIN DETAIL TABLE --%>  

<html:form action="/displayJuvenilePhysicalCharacteristicsCreateSummary" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|168">

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
											<td valign='top' align='center'>			
                  			<%-- BEGIN PHYSICAL CHARACTERISTICS INFO TABLE --%>
												<div class='spacer'></div>
            						<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
            							<tr>
            								<td class='detailHead' colspan="4"><bean:message key="prompt.physicalCharacteristics" /></td>
            							</tr>																										

            							<tr>
            								<td class='formDeLabel' valign="top"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.build"/></td>
            								<td class='formDe' valign="top">
												<html:select property="buildId" name="juvenilePhysicalCharacteristicsForm" styleId="buildId">
              									<html:option key="select.generic" value="" />
              									<html:optionsCollection property="builds" value="code" label="description"/>				
              								</html:select>
															</td>
            							</tr>
            							
														<tr>
            								<td class='formDeLabel'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.height"/></td>
            								<td class='formDe'><html:text name="juvenilePhysicalCharacteristicsForm" property="heightFeet" value="" size="3" maxlength="1" styleId="height"/> ft
      												 <html:text name="juvenilePhysicalCharacteristicsForm" property="heightInch" value="" size="3" maxlength="2" styleId="heightInch"/> in
      												 <html:checkbox name="juvenilePhysicalCharacteristicsForm" property="heightEstimated"/>Estimated
															</td>
            							</tr>
            							
														<tr>
            								<td class='formDeLabel'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.weight"/></td>
            								<td class='formDe'>
																<html:text name="juvenilePhysicalCharacteristicsForm" property="weight" size="10" maxlength="10" styleId="weight"/>lbs
      												<html:checkbox name="juvenilePhysicalCharacteristicsForm" property="weightEstimated"/>Estimated
															</td>								
            							</tr>
            							
														
            							<tr>
            								<td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.hairColorCurrent"/></td>
            								<td class='formDe'>
												<html:select property="hairColorId" name="juvenilePhysicalCharacteristicsForm">
              										<html:option key="select.generic" value="" />
              										<html:optionsCollection property="hairColors" value="code" label="description"/>				
              									</html:select>

														</td>            								
           							</tr>
																
													<tr>
            								<td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.eyeColorContacts"/></td>
            								<td class='formDe'>
                              <html:select property="eyeColorId" name="juvenilePhysicalCharacteristicsForm">
                                <html:option key="select.generic" value="" />
                                <html:optionsCollection property="eyeColors" value="code" label="description"/>				
                              </html:select>
															</td>
            							</tr>
                        </table>

                        <%-- BEGIN BUTTON TABLE --%>
							<div class='spacer'></div>
						<table align='center'>	
							<tr>
								<td>
									<html:button property="button.back" styleId="physCharCreateBack">
										<bean:message key="button.back"></bean:message>
								  	</html:button>				
									<html:submit property="submitAction" styleId="physCharCreateNext">				
										<bean:message key="button.next"></bean:message>
									</html:submit>
								</td>
						
					
								<td>
									<html:submit property="submitAction">
										<bean:message key="button.cancel"></bean:message>
									</html:submit>
								</td>
					
							</tr>
						</table>
						
						</div>
						<div class='spacer'></div>
						
						<%-- END BUTTON TABLE --%>
                         <%-- END PHYSICAL CHARACTERISTICS INFO TABLE --%>
                      </td> 
                    </tr> 
                  </table><%-- END TAB BLUE BORDER TABLE --%>
                </td>
              </tr> 
            </table>
						<div class='spacer'></div>
						<%-- END INTERVIEW INFO TABS OUTER TABLE --%>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>

<%-- END DETAIL TABLE --%>

<%-- END FORM --%>
 </html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

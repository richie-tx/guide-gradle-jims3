<!DOCTYPE HTML>
<%-- User clicks the "Add Drugs Information" button from previous page --%>
<%--MODIFICATIONS --%>
<%-- 06/15/2005	Hien Rodriguez	Create JSP --%>
<%-- 10/21/2015 Richard Capestani #30817 MJCW: PROD Juv Profile > Drugs Tab > Add Drug - 4 Tiny Squares Are Visible (IE11 conversion) --%>

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
<title><bean:message key="title.heading"/> - juvenileInterviewInfoDrugsCreate.jsp</title>

<%--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS--%>
<html:javascript formName="juvenileDrugForm" />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/drugs.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  	<tr>
    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Create Drug/Substance Abuse </td>	  	    	 
  	</tr>  	
</table>
<%-- END HEADING TABLE --%>
<br>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
   	<tr>
     	<td><ul>
	        <li>Click Remove Selected to remove selected rows from list.</li>
	        <li>Click Finish to complete.</li>
	        
	    </ul></td>
  	</tr>
  	<tr>
  		<td class="required"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
  	</tr>
  	<tr>
  	<td align="left"><bean:message key="prompt.plusSign"/>Required if Onset Age is entered.</td>
  	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<script language=javascript>

</script>
<br>

<%-- BEGIN DETAIL TABLE --%>  
<% int RecordCounter = 0; 
									String bgcolor = "";%>	
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  	<tr>
    	<td valign=top>
    		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="main"/>
							<tiles:put name="juvNumId" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>	
					</td>
				</tr>
				<tr>
			  		<td bgcolor=#33cc66><img src="/<msp:webapp/>images/spacer.gif" width=5/></td>
			  	</tr>
			</table>
			
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5/></td>
				</tr>
				<tr>
					<td valign=top align=center>
					
					<table width='98%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
							<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
								<tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
								<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
							</tiles:insert>	
					</td>
				</tr>
				<tr>
			  		<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
			  	
		<html:form action="/handleJuvenileDrugsCreate" target="content">
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|174">
			
			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
				</tr>
				<tr>
					<td valign=top align=center>																							
			<%-- BEGIN SUBSTANCE USE INFO TABLE --%>					
						<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
							<tr>
								<td colspan=6 class=detailHead><bean:message key="prompt.substanceAbuseTraits" /></td>
							</tr>
							<tr>
								<td class=formDeLabel><bean:message key="prompt.onsetAge" /></td>	
								<td class=formDe><html:text size="5" maxlength="5" property="onsetAge" /></td>
								<td class=formDeLabel ><bean:message key="prompt.2.diamond"/><bean:message key="prompt.drugType" /></td>								
								<td class=formDe><html:select property="drugTypeId">
									<html:option key="select.generic" value="" />
									<html:optionsCollection property="drugTypes" value="code" label="description"/>				
								</html:select></td>
								
							</tr>	
							<tr>
							<td class=formDeLabel nowrap><bean:message key="prompt.plusSign"/><bean:message key="prompt.frequency" /></td>								
								<td class=formDe nowrap><html:select property="frequencyId">
									<html:option key="select.generic" value="" />
									<html:optionsCollection property="frequencies" value="code" label="description"/>				
								</html:select></td>
							<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.locationOfUse" /></td>								
								<td class=formDe><html:select property="locationOfUseId">
									<html:option key="select.generic" value="" />
									<html:optionsCollection property="locationOfUses" value="code" label="description"/>				
								</html:select></td>
							</tr>						
							<tr>
								<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.amountSpent" /></td>								
								<td class=formDe>$ <html:text size="8" maxlength="8" property="amountSpentId"/>
									</td>
								<td class=formDeLabel><bean:message key="prompt.plusSign"/><bean:message key="prompt.degree" /></td>								
								<td class=formDe><html:select property="degreeId">
									<html:option key="select.generic" value="" />
									<html:optionsCollection property="degrees" value="code" label="description"/>				
								</html:select></td>
								</tr>
								<tr align="center">
								<td colspan=4>
								<html:submit property="submitAction" styleId="addDrugValidate">				
									<bean:message key="button.add"></bean:message>
								</html:submit></td>												
							</tr>
							<tr>
								<td valign=top align=center colspan="6">																		
								<%-- BEGIN SUBSTANCE USE LIST --%>
																				
									<table width="100%" border="0" cellpadding=2 cellspacing=1>														
									<%-- display detail header --%> 
										<tr class=formDeLabel>
											<td width="1%"></td>
											<td><bean:message key="prompt.onsetAge" /></td>
											<td><bean:message key="prompt.drugType" /></td>
											<td><bean:message key="prompt.frequency" /></td>
											<td><bean:message key="prompt.locationOfUse" /></td>
											<td><bean:message key="prompt.amountSpent" /></td>
											<td><bean:message key="prompt.degree" /></td>																			
										</tr>
										
									<%-- display detail info --%>
			   							<logic:iterate id="drugsIndex" name="juvenileDrugForm" property="newDrugInfoList">
											<bean:define id="newDrugListSize" value="1"/>
											<tr
												class=<%RecordCounter++;
													bgcolor = "alternateRow";
													if (RecordCounter % 2 == 1)
														bgcolor = "normalRow";
													out.print(bgcolor);%>>
												<td width="1%" class=formDe>
													<a href="/<msp:webapp/>handleJuvenileDrugsCreate.do?
														submitAction=<bean:message key='button.removeSelected'/>
														&removeIndices=<bean:write name='drugsIndex' property='temporaryId'/>">
														Remove</a>
												</td>	
												<td class=formDe><bean:write name="drugsIndex" property="onsetAge" /></td>
												<td class=formDe><bean:write name="drugsIndex" property="drugTypeDesc" /></td>
												<td class=formDe><bean:write name="drugsIndex" property="frequencyDesc" /></td>
												<td class=formDe><bean:write name="drugsIndex" property="locationOfUseDesc" /></td>
												<td class=formDe><bean:write name="drugsIndex" property="amountSpentDesc" /></td>
												<td class=formDe><bean:write name="drugsIndex" property="degreeDesc" /></td>	
											</tr>
										</logic:iterate>
										<%  if(RecordCounter>0){ %>
												<bean:define id="newDrugListSize" value="1"/>
										<%			} %>
								<%-- END SUBSTANCE USE LIST --%>						
									</table>
								</td>
							</tr>
						</table>			    		 
			<%-- END SUBSTANCE USE INFO TABLE --%>
			<%-- BEGIN BUTTON TABLE --%>
						<div class=spacer></div>		
						<table width='98%'>	
						  <tr>
							<td align="center">
								<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>	
								
								<logic:present name="newDrugListSize">	
								  <html:submit property="submitAction" styleId="submitDrug">				
									<bean:message key="button.finish"></bean:message>
								  </html:submit>			
							    </logic:present>
								<html:submit property="submitAction">
									<bean:message key="button.cancel"></bean:message>
								</html:submit>
							</td>
						</tr>
					</table>
					<div class=spacer></div>
					<%-- END BUTTON TABLE --%>
					</td>
				</tr>
			</table>
			<br>		
   		</td>
  	</tr>
</table>
<%-- END DETAIL TABLE --%>

</html:form>

<br>
<%-- END FORM --%>

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>


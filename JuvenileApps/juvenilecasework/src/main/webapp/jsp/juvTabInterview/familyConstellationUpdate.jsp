<!DOCTYPE HTML>

<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 12/11/2006 Hien Rodriguez  ER#37077 Add Tab & Buttons security features  --%>
<%-- 11/18/2011 C Shimek		#72018 commented out href code around member number on list --%>
<%-- 06/06/2012 C Shimek		#73627 revised relationship list to display only active codes for new members --%>
<%-- 11/14/2012	C Shimek		#74585 added Parental Rights Terminated field --%>
<%-- 12/10/2012 C Shimek		#74731 Added Guardian Primary Contact Indicator (blue star) --%>
<%-- 04/01/2013 C Shimek		#75306 Added validation check to not allow incarcerated member In Home Status of Yes --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvTabInterview/familyConstellationGeneral.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>

<script type="text/javascript">

	$("document").ready(function(){
		
		var index = 0;
		
		<logic:iterate id="mList" name="juvenileFamilyForm" property="currentConstellation.memberList" indexId="index">
			
			var birthDate = '<bean:write name="mList" property="dateOfBirth"/>';
			var name = '<bean:write name="mList" property="memberName.formattedName" />';
			var over21 = '<bean:write name="mList" property="over21"/>';
			
			var el = document.getElementsByName("currentConstellation.memberList[" + index + "].over21");
				
				if(el && el.length > 0){
					//console.log(name + ' birth date: ', birthDate);
					el.forEach(function(item, index){
						if(birthDate){
							item.disabled = true;
						} else{
							item.disabled = false;
						}										
					});				
				} 		
					
			index++;
			
		
		</logic:iterate>
		
		
	})
	
	function checkOver21(el){
		
		if(!el){
			return;
		}
		
		if(el){
				var detentionVisit = el.name.replace("over21", "detentionVisitationAsStr");
				//console.log('detention visit name: ', detentionVisit);
				
				var detentionVisitElements = document.getElementsByName(detentionVisit);
					
					if(detentionVisitElements && detentionVisitElements.length > 0){
						
						detentionVisitElements.forEach(function(item, index){
							if(el.checked && el.value == 'false'){
								item.disabled = true;
							} 
							
							if(el.checked && el.value == 'true'){
								item.disabled = false;
							}
							
						})				
					}							
			}
		
	}
	
	
</script>



<title><bean:message key="title.heading" /> - familyConstellationUpdate.jsp</title>
</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayFamilyConstellationDetailsCreate">



<logic:equal name="juvenileFamilyForm" property="action" value="update">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|237">
</logic:equal>
<logic:notEqual name="juvenileFamilyForm" property="action" value="update">    
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|229">
</logic:notEqual>    
<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - 
			<logic:equal name="juvenileFamilyForm" property="action" value="update"><bean:message key="prompt.update"/></logic:equal>
			<logic:notEqual name="juvenileFamilyForm" property="action" value="update"><bean:message key="prompt.create"/></logic:notEqual>
			<bean:message key="prompt.family"/> <bean:message key="prompt.constellation"/>
		</td>
	</tr>
</table>

<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END HEADING TABLE --%>
<div class=spacer></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
  		<logic:equal name="juvenileFamilyForm" property="action" value="update">
    		<ul>
    			<li>Click Remove Link to remove member from constellation.</li>
    			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_CMC_U%>'>
      				<li>Select Add New Member button to search for all JIMS2 family members before adding new member.</li>
    			</jims2:isAllowed>
    			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_CM_U%>'>
	      			<li>Update Member information and click Next to continue</li>
    			</jims2:isAllowed>
    		</ul>
  		</logic:equal>
  		<logic:notEqual name="juvenileFamilyForm" property="action" value="update">
    		<ul>
	      		<li>Click Remove Link to remove a member from constellation.</li>
	        	<li>Click Next button to continue </li>
	        	<li>Select Add New Member button to search for all JIMS2 family members before adding new member.</li>
    		</ul>
  		</logic:notEqual>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%>
<table align="center" cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td>
		  <%--header info start--%> 
		  <tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  			<tiles:put name="headerType" value="profileheader" />
  		</tiles:insert> 
			<%--header info end--%>
		</td>
	</tr>
</table>
<%-- END JUVENILE PROFILE HEADER TABLE --%>
<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td><%-- begin green tabs (1st row) --%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top">
	  					<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
	  						<tiles:put name="tabid" value="family" />
	  						<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
	  					</tiles:insert>
					</td>
				</tr>
				<tr>
					<td bgcolor="#33cc66"><img src="../../images/spacer.gif" width="5"></td>
				</tr>
			</table>
<%-- end green tabs --%> 
<%-- begin outer green border --%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
						<div class='spacer'></div>
<%-- begin blue tabs --%>			
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td valign="top">
									<tiles:insert page="../caseworkCommon/constellationInfoTabs.jsp" flush="true">
										<tiles:put name="tabid" value="ConstellationMembers" />
									</tiles:insert>
								</td>
							</tr>
							<tr>
								<td bgcolor="#6699FF"><img src="../../images/spacer.gif" width="5"></td>
							</tr>
	  					</table>
<%-- end blue tabs --%>			
						<bean:define id="isThisAGuardian" value="false" type="java.lang.String"/>
<%--begin blue outer border --%>
	  					<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
	  						<tr>
	 							<td valign="top" align="center">
<%-- BEGIN Other members List TABLE --%>
		  							<div class='spacer'></div>
		  							<table width="98%" cellspacing="0" cellpadding="2" class="borderTableBlue">
		 								<tr>
		 									<td class="detailHead" valign="top"><bean:message key="prompt.member"/> <bean:message key="prompt.list"/></td>
		 								</tr>
		 								<tr>
		 									<td>
		  										<table width="100%" cellspacing="1">
		  											<logic:empty name="juvenileFamilyForm" property="currentConstellation">
		  												<tr bgcolor="#cccccc">
		  													<td colspan="6" class="subHead">No Members Available</td>
		  												</tr>
		  											</logic:empty>
													<logic:notEmpty name="juvenileFamilyForm" property="currentConstellation">
														<logic:empty name="juvenileFamilyForm" property="currentConstellation.memberList">
															<tr bgcolor="#cccccc">
																<td colspan="6" class="subHead">No Members Available</td>
															</tr>
														</logic:empty>
														<logic:notEmpty name="juvenileFamilyForm" property="currentConstellation.memberList">
															<tr bgcolor="#cccccc">
																<td width="1%"></td>
																<td valign="top" class="subHead"><bean:message key="prompt.member"/> #</td>
																<td valign="top" class="subHead"><bean:message key="prompt.name"/></td>
																<td valign="top" class="subHead"><bean:message key="prompt.guardian"/></td>
																<td valign="top" class="subHead"><bean:message key="prompt.relationship"/></td>
																<td valign="top" class="subHead"><bean:message key="prompt.primaryCareGiver"/></td>
																<td valign="top" class="subHead" width="7%"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.dh"/></td>
																<td valign="top" class="subHead" width="7%">Over 21?</td>
																<td valign="top" class="subHead" width="7%"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.visit"/></td>
																<td valign="top" class="subHead" width="10%"><bean:message key="prompt.parentalRightsTerminated"/></td>
																<td valign="top" class="subHead"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.inHomeStatus"/></td>
																<td valign="top" class="subHead"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.involvementLevel"/></td>
															</tr>
															<nested:nest property="currentConstellation">
																<nested:iterate id="memberList" property="memberList" indexId="index">
																<bean:define name="memberList" property="detentionVisitationAsStr" id="currentDetVisit" type="java.lang.String"/>

																	<tr name="nestedMemberRow" class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																		<td align="center">
																			<logic:notEqual name="memberList" property="guardian" value="true">
		 																		<logic:notEqual name="juvenileFamilyForm" property="action" value="update">
		 																			<a href='/<msp:webapp/>displayFamilyConstellationDetailsCreate.do?submitAction=<bean:message key="button.removeSelectedMembers"/>&amp;selectedValue=<bean:write name="memberList" property="memberNumber"/>'>Remove</a>
		 																		 </logic:notEqual>
																				 <logic:equal name="juvenileFamilyForm" property="action" value="update">
																					<a href='/<msp:webapp/>displayFamilyConstellationDetailsUpdate.do?submitAction=<bean:message key="button.removeSelectedMembers"/>&amp;selectedValue=<bean:write name="memberList" property="memberNumber"/>'
																					   	name="removeConLink">Remove</a>
																			 	</logic:equal>
																			</logic:notEqual>
																			<logic:equal name="memberList" property="guardian" value="true">&nbsp;</logic:equal>
																		</td>
																		<td align="center">
																			<logic:notEmpty name="memberList" property="suspiciousMember">
		    																	<logic:notEqual name="memberList" property="suspiciousMember" value="">
		    																		<bean:message key="prompt.suspiciousMember"/>
		    																	</logic:notEqual>
		    																</logic:notEmpty>
		    																	<%-- 		<a href='/<msp:webapp/>displayFamilyMemberDetails.do?submitAction=Details&amp;selectedValue=<bean:write name="memberList" property="memberNumber"/>'> --%>
																			<bean:write name="memberList" property="memberNumber" /><%--   </a>  --%>
																		</td>
																		<td align="center"><bean:write name="memberList" property="memberName.formattedName" /></td>
																		<td align="center">
																		   	<logic:equal name="memberList" property="guardian" value="true"> Yes</logic:equal>
																			<logic:notEqual name="memberList" property="guardian" value="true"> No</logic:notEqual>
																			<logic:equal name="memberList" property="primaryContact" value="true"> <img src='../../images/starBlueIcon.gif'></logic:equal>
																			<nested:hidden property="guardian" />																	
																		</td>
																		<td align="center">
																			<logic:notEqual name="memberList" property="relationshipToJuv" value="">  <%-- List contains active and inactive values --%>
																				<nested:select property="relationshipToJuvId">
																					<option value="">Select Relationship</option>
																					<html:optionsCollection name="juvenileFamilyForm" property="relationshipToJuvenileList" value="code" label="description"/>
																				</nested:select>
																			</logic:notEqual>
																			<logic:equal name="memberList" property="relationshipToJuv" value="">  <%-- List only contains active values --%>
																				<nested:select property="relationshipToJuvId">
																					<option value="">Select Relationship</option>
																			 		<html:optionsCollection name="juvenileFamilyForm" property="activeRelationshipToJuvenileList" value="code" label="description"/> 
																				</nested:select>
																			</logic:equal>
																		</td>
																		<td align="center">
																			<div>Yes<nested:radio property="primaryCareGiverAsStr" value="true"/></div>
																			<div>No<nested:radio property="primaryCareGiverAsStr"  value="false"/></div>
																			
																		</td>
																		
																		<td align="center">
																			<div>Yes<nested:radio property="detentionHearingAsStr" value="true"/></div>
																			<div>No<nested:radio property="detentionHearingAsStr"  value="false"/></div>
																		</td>
																		<td align="center">
																			<div>Yes<nested:radio property="over21" value="true" onclick="checkOver21(this)" /></div>
																			<div>No<nested:radio property="over21" value="false" onclick="checkOver21(this)" /></div>
																		</td>
																		
																		<td align="center">
																			<logic:equal name="memberList" property="over21" value="true">
																				<div>Yes<nested:radio property="detentionVisitationAsStr" value="true" styleId="detentionVisitationYes" title='<%=currentDetVisit%>'/></div>
																				<div>No<nested:radio property="detentionVisitationAsStr"  value="false" styleId="detentionVisitationNo" title='<%=currentDetVisit%>'/></div>
																			</logic:equal>
																			<logic:equal name="memberList" property="over21" value="false">
																				<div>Yes<nested:radio property="detentionVisitationAsStr" value="true" styleId="detentionVisitationYes" title='<%=currentDetVisit%>' disabled ="true"/></div>
																				<div>No<nested:radio property="detentionVisitationAsStr"  value="false" styleId="detentionVisitationNo" title='<%=currentDetVisit%>' disabled ="true"/></div>
																			</logic:equal>
																		</td>	
																		
																		<td align="center">
																			<div>Yes<nested:radio property="parentalRightsTerminatedAsStr" value="true" /></div>
																			<div>No<nested:radio property="parentalRightsTerminatedAsStr"  value="false" /></div>
																		</td>		
																			
																			
																		
		          														<!-- #JIMS200047773 changed values to true and false instead of YES and NO as they are booleans-->
																		<td align="center" >
																			<div>Yes<nested:radio property="inHomeStatusAsStr" value="true" /></div>
																			<div>No<nested:radio property="inHomeStatusAsStr"  value="false" /></div>
																			<nested:hidden property="incarcerated" />
																			<nested:hidden property="memberName.formattedName" />
																		</td>
																		
																		<td nowrap align="center">
																			<nested:select property="involvementLevelId">
																				<option value="">Select Level</option>
																				<html:optionsCollection name="juvenileFamilyForm" property="involvementLevelList"  value="code" label="description"/>
																			</nested:select>
																			
																	  	</td>
																	</tr>
																</nested:iterate>
															</nested:nest>
														</logic:notEmpty>
													</logic:notEmpty>
												</table>
											</td>
										</tr>
									</table>
									<div class="spacer"></div>								
<%-- BEGIN BUTTON TABLE --%>
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
												<logic:notEqual name="juvenileFamilyForm" property="action" value="update">
													<html:submit property="submitAction" styleId="validateCreate" ><bean:message key="button.next"></bean:message></html:submit>
												</logic:notEqual>
												<logic:equal name="juvenileFamilyForm" property="action" value="update">
													<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_CM_U%>'>
														<html:submit property="submitAction" styleId="validateUpdate" ><bean:message key="button.next"></bean:message></html:submit>
													</jims2:isAllowed>
												</logic:equal>
												<logic:equal name="juvenileFamilyForm" property="action" value="update">
													<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_CMC_U%>'>
														<input type="button" value="Add New Member" id="addNewMemberUpdate" data-href="/<msp:webapp/>displayFamilyMemberSearch.do?juvenileNumber=<bean:write name="juvenileFamilyForm" property="juvenileNumber"/>"> 
													</jims2:isAllowed>
												</logic:equal>
												<logic:notEqual name="juvenileFamilyForm" property="action" value="update">
													<input type="button" value="Add New Member" id="addNewMemberCreate" data-href="/<msp:webapp/>displayFamilyMemberSearch.do?juvenileNumber=<bean:write name="juvenileFamilyForm" property="juvenileNumber"/>"> 
												</logic:notEqual>
												<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
											</td>
										</tr>
									</table>
<%-- END BUTTON TABLE --%>
									<div class=spacer></div> 
								</td>
							</tr>
						</table>
<%-- end blue outer border --%>		
						<div class=spacer></div> 
					</td>
				</tr>
			</table>
<%-- end outer green --%>
		</td>
	</tr>
</table>
<div class=spacer></div> 
<html:hidden styleId="totalDetentionVisits" name="juvenileFamilyForm" property="totalDetentionVisits"/>
<html:hidden styleId="daVisit" name="juvenileFamilyForm" property="daVisit"/>
<html:hidden styleId="visitorCapRemoved" name="juvenileFamilyForm" property="visitorCapRemoved"/>
<logic:equal name="juvenileFamilyForm" property="action" value="update">
	<html:hidden name="juvenileFamilyForm" property="action" styleId="famConUpdateStatus" value="update"/>
	<html:hidden property="action" styleId="tURL" value="/JuvenileCasework/displayFamilyConstellationDetailsUpdate.do" />
</logic:equal>
	
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
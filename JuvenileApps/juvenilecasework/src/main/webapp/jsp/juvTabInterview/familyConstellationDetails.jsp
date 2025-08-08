<!DOCTYPE HTML>

<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 07/17/2009 C Shimek    #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base/>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading"/> - familyConstellationDetails.jsp</title>
</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0">
<html:form action="/displayFamilyConstellationDetails">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|228">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  	<tr>
    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.family"/> 
    			<bean:message key="prompt.constellation"/> <bean:message key="prompt.details"/>
    	</td>	  	    	 
  	</tr>  	
</table>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END HEADING TABLE --%>

<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
  <tr>
    <td>
  	  <ul>
       <li>Select Back button to return to the previous page. </li>
    		<li>Family Member designated as guardian is shown in <strong>BOLD</strong>. </li>
      </ul>	
	  </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%> 
<div class='spacer'></div>
<table align="center" cellpadding="1" cellspacing="0" border="0" width='100%'> 
    <tr> 
      <td> <%--header info start--%> 
        <tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
			<tiles:put name="headerType" value="profileheader"/>
		</tiles:insert>
        <%--header info end--%> </td> 
    </tr> 
</table>
<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%> 


<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign="top">
    	<table width='100%' border="0" cellpadding="0" cellspacing="0" >
			<tr>
				<td valign="top">
				<%--tabs start--%>
					<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="family"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
					</tiles:insert>	
				<%--tabs end--%>
				</td>
			</tr>
			<tr>
			  	<td bgcolor='#33cc66'><img src='../../common/images/spacer.gif' border="0" width="5"></td>
		  </tr>
	  </table>


		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
			<tr>
				<td valign="top" align="center">
					
						<%-- BEGIN constellation Traits TABLE --%>
						<div class='spacer'></div>
						<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr bgcolor='#f0f0f0'>
								<td align="left" class="detailHead" colspan="4"><bean:message key="prompt.constellation"/> <bean:message key="prompt.traits"/> - <bean:message key="prompt.family"/> # 
  								<bean:write name="juvenileFamilyForm" property="currentConstellation.familyNumber"/>
								</td>
							</tr>
							<logic:empty name="juvenileFamilyForm" property="currentConstellation.traitList">
						    <tr>
							  <td>	
							  	<table width='100%' cellpadding="2" cellspacing="1">
									<tr bgcolor='#cccccc'> 
										<td colspan="4" class="subHead">No Traits Listed </td> 
							  		</tr> 
							  	</table>
							  </td>
							</tr>
							</logic:empty>
							 <%int RecordCounter = 0;
							String bgcolor = "";%>  
							<logic:notEmpty name="juvenileFamilyForm" property="currentConstellation.traitList">
							<tr>
								<td align="center">
									<table width='100%' cellpadding="2" cellspacing="1">
										<tr bgcolor='#cccccc'>
											<td class="subHead"><bean:message key="prompt.entryDate"/></td>
											<td class="subHead"><bean:message key="prompt.dynamic"/> (Trait) </td>
											<td class="subHead"><bean:message key="prompt.status"/></td>
											<td class="subHead"><bean:message key="prompt.level"/></td>
								    </tr>
								    <logic:iterate id="myTraits" name="juvenileFamilyForm" property="currentConstellation.traitList">
									    <tr class=<%RecordCounter++;
    											bgcolor = (RecordCounter % 2 == 1) ? "normalRow" : "alternateRow";
    											out.print(bgcolor);%>
											>
												<td><bean:write name="myTraits" property="entryDate"/></td>
												<td><bean:write name="myTraits" property="trait"/></td>
												<td><bean:write name="myTraits" property="traitStatus"/></td>
												<td><bean:write name="myTraits" property="traitLevel"/></td>
									    </tr>
								    </logic:iterate>
									</table>
								</td>
							</tr>
							</logic:notEmpty>	 
						</table>
						<%-- End constellation Status TABLE --%>

						<%-- BEGIN Other members List TABLE --%>
						<div class='spacer'></div>
						<table align="center" width='98%' cellspacing="0" cellpadding="2" class="borderTableBlue">
							<tr>
								<td class="detailHead" valign="top">Members In This Constellation</td>
							</tr>
							<logic:empty name="juvenileFamilyForm" property="currentConstellation.memberList">
							  	<tr bgcolor='#cccccc'> 
									<td class="subHead">No Members In the Constellation</td> 
							  	</tr> 
							</logic:empty>

							<%int RecordCounter1 = 0;
							String bgcolor1 = "";%>  
							<logic:notEmpty name="juvenileFamilyForm" property="currentConstellation.memberList">
								<tr>
									<td >
										<table width='100%' cellspacing="1">
											<tr bgcolor='#cccccc'>
												
												<td width="18%" class="subHead"><bean:message key="prompt.member"/> #</td>
												<td width="20%" class="subHead"><bean:message key="prompt.name"/></td>
												<td width="22%" class="subHead"><bean:message key="prompt.relationship"/></td>
												<td width="18%" class="subHead"><bean:message key="prompt.deceased"/></td>
												<td class="subHead"><bean:message key="prompt.SSN"/></td>
												
										    </tr>
										    <logic:iterate id="myMembers" name="juvenileFamilyForm" property="currentConstellation.memberList">
											    <tr class=<%RecordCounter1++;
  													bgcolor1 = (RecordCounter1 % 2 == 1) ? "normalRow" : "alternateRow";
  													out.print(bgcolor1);%>
													>
														<td>
															<a href="/<msp:webapp/>displayFamilyMemberDetails.do?submitAction=Details&selectedValue=<bean:write name="myMembers" property="memberNumber"/>">
																<bean:write name="myMembers" property="memberNumber"/></a>
														</td>
														<td>
															<logic:equal name="myMembers" property="guardian" value="true">
																<strong><bean:write name="myMembers" property="memberName.formattedName"/></strong>
															</logic:equal>
															<logic:notEqual name="myMembers" property="guardian" value="true">
																<bean:write name="myMembers" property="memberName.formattedName"/>
															</logic:notEqual>
														</td> 
														<td><bean:write name="myMembers" property="relationshipToJuv"/></td> 
														<td><bean:write name="myMembers" property="deceasedYesNo"/></td> 
														<td width='4%' nowrap>
															<bean:write name="myMembers" property="socialSecurity.formattedSSN"/>
														</td> 
											
											    </tr>
										    </logic:iterate>
										</table>
									</td>
								</tr>
							</logic:notEmpty>
						</table>
						<div class='spacer'></div>
						<%-- END members List TABLE --%>
          </td>
        </tr>
      </table>
  	</td>
    </tr>
  </table>

	
        <%-- BEGIN BUTTON TABLE --%>
				<div class='spacer'></div>
				<table border="0" width="100%">
				  <tr>
				    <td align="center">
				     	  <html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
						 <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
					</td>
				  </tr>
				</table>
				<%-- END BUTTON TABLE --%>
				
<%-- END DETAIL TABLE --%>

</html:form>

<div class='spacer'></div>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

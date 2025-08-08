<!DOCTYPE HTML>
<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 07/17/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 10/24/2012 DGibler		73746 MJCW: Add Street Number suffix field --%>

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
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/juvTabInterview/familyMemberAddressCreate.js"></script>


<title><bean:message key="title.heading"/> - familyMemberAddressSelectionList.jsp</title>
</head> 
<%--END HEADER TAG--%>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayFamilyMemberAddressSelection?action=create" target="content">
<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  	<tr>
    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.interview"/> - <bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.address"/> <bean:message key="prompt.selection"/></td>	  	    	 
  	</tr>  	
</table>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END HEADING TABLE --%>
<br>
<%-- BEGIN INSTRUCTION TABLE --%> 
<table width="98%" border="0"> 
    <tr> 
      <td>        <ul> 
          <li> Select the member to use for populating the address fields. </li> 
          <li>Click Member # hyperlink to view member details. </li>
		  <li>Family member designated as Guardian is displayed in <strong>BOLD</strong>.</li>  
          </ul></td> 
    </tr> 
    <tr> 
      <td></td> 
    </tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 
<BR>
<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%> 
<table align="center" cellpadding=1 cellspacing=0 border=0 width='100%'> 
    <tr> 
      <td >  <%--header info start--%> 
        <tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
			<tiles:put name="headerType" value="profileheader"/>
		</tiles:insert>
        <%--header info end--%> </td> 
    </tr> 
</table>
<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%> 
<BR>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top">
    	<%-- BEGIN GREEN TABS TABLE --%>
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
			<td bgcolor="#33cc66"><img src="../../common/images/spacer.gif" width="5"></td>
		   </tr>
		</table>
		<%-- END GREEN TABS TABLE --%>
		<%-- BEGIN TAB GREEN BORDER TABLE --%>
		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
			<tr>
				<td valign="top"><img src="../../common/images/spacer.gif" width="5"></td>
			</tr>
			<tr>
				<td valign="top" align="center">
					<%-- BEGIN INTERVIEW INFO TABS OUTER TABLE --%>
					<table width='98%' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<%-- BEGIN INTERVIEW INFO TABS INNER TABLE --%>
								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td valign="top"><%--tabs start--%> <tiles:insert
														page="../caseworkCommon/memberInfoTabs.jsp" flush="true">
														<tiles:put name="tabid" value="AddressInfo" />
														<tiles:put name="juvnum"
															value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
													</tiles:insert> <%--tabs end--%></td>
									</tr>
									<tr>
									  	<td bgcolor="#6699FF"><img src="../../common/images/spacer.gif" width="5"></td>
								  </tr>
							  </table>
									<%-- END INTERVIEW INFO TABS INNER TABLE --%>
									<%-- BEGIN TAB BLUE BORDER TABLE --%>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
											<td valign="top"><img src="../../common/images/spacer.gif" width="5"></td>
										</tr>
										<tr>
											<td valign="top" align="center">					                								 
												  <%-- BEGIN Current Constellation TABLE --%> 
												  <table width='98%' cellspacing="0" cellpadding="2" class="borderTableBlue"> 
													<tr> 
													  <td width="100%" valign="top" class="detailHead"><bean:message key="prompt.current"/> <bean:message key="prompt.constellation"/> - <bean:message key="prompt.family"/> #
   														<bean:write name="juvenileFamilyForm" property="currentConstellation.familyNumber"/>
													</td> 
													<%--  <td width="5%" valign="top" class="detailHead"><input type="button" value="Edit" onClick="goNav('familyConstellationUpdate.htm')"></td> --%>
													</tr> 
													<tr> 
													  <td colspan="2"> 
													   <%int RecordCounter1 = 0;
																String bgcolor1 = "";%>
																 <nested:nest property="currentConstellation">
													  <table width='100%' cellspacing="1"> 
													  <logic:empty name="juvenileFamilyForm" property="currentConstellation.memberList">
													  <td class="subHead">No Members Available</td> 
													  </logic:empty>
													  <logic:notEmpty name="juvenileFamilyForm" property="currentConstellation.memberList">
													  <tr>
												<td colspan='2'>
													<%-- this span simulates a scrolling table after 5 entries --%>
													<div class='scrollingDiv200'>
														<table width="100%" cellpadding="2" cellspacing="1" border='0'>
															<thead>
															<%-- static, column titles --%>
														  <tr bgcolor="#cccccc"> 
															<td></td> 
															<td class="subHead"><bean:message key="prompt.member"/> #</td> 
															<td class="subHead"><bean:message key="prompt.name"/></td>
															<td class="subHead"><bean:message key="prompt.addressType"/></td>
 															<td class="subHead"><bean:message key="prompt.address"/></td>
															<td class="subHead"><bean:message key="prompt.validationSymbol"/></td> 
														  </tr>
														  </thead> 
													  <tbody> 
													  <nested:iterate id="memberList" property="memberList">
														  <tr class=<%RecordCounter1++;
																bgcolor1 = "alternateRow";
																if (RecordCounter1 % 2 == 1)
																	bgcolor1 = "normalRow";
																out.print(bgcolor1);%> height="100%"> 
															<td><input type="radio" name="twoLane" id="setMemberAddr" value="<bean:write name="memberList" property="currentAddress.memberAddressId"/>"/></td> 
															<td><bean:write name="memberList" property="memberNumber"/></td> 
															<td><bean:write name="memberList" property="memberName.formattedName"/></td>
															<td><bean:write name="memberList" property="currentAddress.addressType"/></td>  
															<td><bean:write name="memberList" property="currentAddress.streetNumber"/>&nbsp;
															<logic:notEmpty name="memberList" property="currentAddress.validated">
																<bean:write name="memberList" property="currentAddress.streetNumSuffix"/>&nbsp;
															</logic:notEmpty>
															<bean:write name="memberList" property="currentAddress.streetName"/>,&nbsp;
															<bean:write name="memberList" property="currentAddress.city"/>,&nbsp;
															<bean:write name="memberList" property="currentAddress.state"/>&nbsp;
															<bean:write name="memberList" property="currentAddress.zipCode"/>&nbsp;
															<logic:notEmpty name="memberList" property="currentAddress.additionalZipCode"> 
																-<bean:write name="memberList" property="currentAddress.additionalZipCode"/>&nbsp;
															</logic:notEmpty>
															</td> 
															<td>
	  												   			<%-- based on the Address validation, display a specific icon --%>
  																<logic:notEmpty name="memberList" property="currentAddress.validated">
																	<logic:equal name="memberList" property="currentAddress.validated" value="Y">
	  																	<img src="/<msp:webapp/>images/grade_level_appropriate.png" alt="Appropriate" />
																	</logic:equal>
																	<logic:equal name="memberList" property="currentAddress.validated" value="N">
  																		<img src="/<msp:webapp/>images/red_x.gif" alt="redx" />
																	</logic:equal>
 																</logic:notEmpty>
															</td>
														  </tr> 
														  </nested:iterate>
														  </tbody>
														</table>
													</div>
												</td>
											</tr>
														  </logic:notEmpty>
															
														  </table>
														   </nested:nest>
														  <html:hidden  property="selectedValue" styleId="hiddenVal"/>
														 <%-- END TAB BLUE BORDER TABLE --%>
														  <logic:notEmpty name="juvenileFamilyForm" property="currentConstellation.memberList">
									 <div align="center" class="paddedFourPix">
												
									 <input type="button" value="Select Member" id="loadAddress" />
                   									</div>
                   									</logic:notEmpty>
									</td> 
              					</tr> 
            				</table>
							<%-- END INTERVIEW INFO TABS OUTER TABLE --%>
							<%-- BEGIN BUTTON TABLE --%>
       						<div class="spacer"></div> 
							<table border="0" width="100%">
							  <tr>
							    <td align="center">
							      <input type="button" value="Close Window" name="return" onclick="window.close()">
								  <%--<input type="button" value="Cancel" name="return" onClick="goNav('../juvTabInterview/familyMemberAddressCreate.htm')">--%>
							    </td>
							  </tr>
							</table>
							<div class="spacer"></div>
							<%-- END BUTTON TABLE --%>
						  </td> 
              			</tr> 
            		</table>
					<%-- END TAB GREEN BORDER TABLE --%> 
              </td> 
       	  </tr> 
      </table>
            <%-- END DETAIL TABLE --%> 
   <BR>
   </td> 
       	  </tr> 
      </table>

<br>

</html:form> 
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
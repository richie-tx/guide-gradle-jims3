<!DOCTYPE HTML>
<%-- User selects the "Add More Characteristics" button from Juvenile Profile Master Details Main tab --%>
<%--MODIFICATIONS --%>
<%-- 06/16/2005	Hien Rodriguez	Create JSP --%>
<%-- 03/27/2006 LDeen		Revised JSP to <html:option key="select.multiple" value="" /> on tattoo drop down list --%>
<%-- 03/05/2007 CShimek		#39719 added max. length check to Other Tattoo Comments field.  --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nest"%>
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
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvenileProfileInfo.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - juvenileScarsAndTatoos.jsp</title>

</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  	<tr>
    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Update Physical Characteristics - Scars &amp; Marks / Tattoos</td> 
  	</tr>  	
</table>
<%-- END HEADING TABLE --%>

<html:form action="/displayJuvenileTattooAndScarsCreateSummary" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|22">

<logic:equal name="juvenilePhysicalCharacteristicsForm" property="status" value="error">
		<tr><td align="center" class="errorAlert">
			<font class="errorAlert"> No new scars, marks or tattoos have been selected.  Please make new selections. </font>
		</td></tr>
	</logic:equal>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
    <tr>
      <td>
        <ul>
					<li>Select any Scars & Marks and/or Tattoos that are new. </li>
					<li>Deselect any Scars & Marks and/or Tattoos that have been removed.</li> 
					<li>Select the Spell Check icon for the Other Tattoo Comments field to check spelling.</li> 
					<li>Select the Next button to view the Summary page. </li>
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
<div class="detailHead"></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign="top">
      <table width='100%' border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td valign="top">
            <tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
            <tiles:put name="tabid" value="maintab"/>
            <tiles:put name="juvNumId" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
            </tiles:insert>	
          </td>
        </tr>
        <tr>
          <td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
        </tr>
      </table>

			
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">	
					<%-- BEGIN INTERVIEW INFO TABS OUTER TABLE --%>
					<div class="detailHead"></div>
					<table width='98%' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<%-- BEGIN INTERVIEW INFO TABS INNER TABLE --%>
								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td valign="top">
										<%--tabs start--%>
											<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
												<tiles:put name="tabid" value="profileInfo"/>
												<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
											</tiles:insert>	
										<%--tabs end--%>
										</td>
									</tr>
									<tr>
									  	<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
								  </tr>
							  </table>
								<%-- END INTERVIEW INFO TABS INNER TABLE --%>
								<input type="hidden" name="scarCheckBoxes" value="true"/>		
								
								<%-- BEGIN TAB BLUE BORDER TABLE --%>
								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
											<td valign="top" align="center">			
                  			<%-- BEGIN PHYSICAL CHARACTERISTICS INFO TABLE --%>
												<div class="detailHead"></div>
            				<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
            							<tr>
            								<td class="detailHead" colspan="4"><bean:message key="prompt.physicalCharacteristics" /></td>
	           							</tr>
	           							<tr> 
                      						<td valign='top' class='formDeLabel' nowrap='nowrap'>Scars & Marks</td> 
              							</tr>
										<tr>
										  <td>
											  <div class='scrollingDiv200'>
											  <table border="0" cellpadding="2" cellspacing="1">
											   <% String style = "alternateRow"; %>
											  <logic:iterate id="phycharIndex" name="juvenilePhysicalCharacteristicsForm" property="scarsAndMarksTypes" indexId="pcharCount">
											  	<bean:define id="code" name="phycharIndex" property="code" type="java.lang.String"/>
											  	<% if(style.equals("alternateRow")) 
											     		style = "normalRow" ;
											     	else 
											     		style = "alternateRow";%>	
						                          <tr style="height:100%" class=<%=style%>><td>
						                          <html:checkbox name="phycharIndex" property="selectedScars" value="<%=code%>"><bean:write name="phycharIndex" property="descriptions"/></html:checkbox></td></tr>                        						  
											   </logic:iterate>			
												</table>
											</td>
										</tr>
										
									  <div class='spacer'></div>
									  <tr><td class='formDeLabel' valign="top" width='2%' nowrap='nowrap'>Tattoos</td></tr>
										<tr>
										  <td>
											  <div class='scrollingDiv200'>
											  <table  border="0" cellpadding="2" cellspacing="1">
											   <%int i=1; %>
								  				<logic:iterate id="phycharIndex" name="juvenilePhysicalCharacteristicsForm" property="tattoosTypes" indexId="pcharCount">
								  					<bean:define id="tattooCode" name="phycharIndex" property="code" type="java.lang.String"/>
											     <% if(style.equals("alternateRow")) 
											     		style = "normalRow" ;
											     	else 
											     		style = "alternateRow";%>											     		
						                          <tr style="height:100%" class=<%=style%>><td><html:checkbox name="phycharIndex"  property="selectedTattoos" value="<%=tattooCode%>" ><bean:write name="phycharIndex" property="descriptions"/></html:checkbox></td></tr>
						                          <%++i;%>                        						  
											   	</logic:iterate>		

												</table>
												</div>
											</td>
              			</tr>

                    <tr>
                      <td class='formDeLabel'>Current Tattoo Comments</td>		
      							</tr>
  									<tr>
  										<td class='formDe'><bean:write name="juvenilePhysicalCharacteristicsForm" property="allOtherTattooComments"/></td>
  										<html:hidden name="juvenilePhysicalCharacteristicsForm" property="allOtherTattooComments"/>  										
  									</tr>

                    <tr>
                      <td class='formDeLabel'>Other Tattoo Comments
                      	<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
      						<tiles:put name="tTextField" value="newOtherTattooComments"/>
      						<tiles:put name="tSpellCount" value="spellBtn1" />
    				  	</tiles:insert>
    				  	(Max. characters allowed: 1500)
                      </td>		
      				</tr>
                    <tr>
                      <td class='formDe'>                        
                      <html:textarea style="width:100%" rows='3' name="juvenilePhysicalCharacteristicsForm" property="newOtherTattooComments" /></td>		
                    </tr>
	           																																	

                        </table>

                        <%-- BEGIN BUTTON TABLE --%>
							<div class="detailHead"></div>
						<table align="center">	
							<tr>
								<td>
									<html:button property="button.back" styleId="scarTatooBack">
										<bean:message key="button.back"></bean:message>
								  	</html:button>				
									<html:submit property="submitAction">				
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
						<div class="spacer"></div>
						
						<%-- END BUTTON TABLE --%>
                         <%-- END PHYSICAL CHARACTERISTICS INFO TABLE --%>
                      </td> 
                    </tr> 
                  </table><%-- END TAB BLUE BORDER TABLE --%>
                </td>
              </tr> 
            </table>
				<div class="spacer"></div>
				<%-- END INTERVIEW INFO TABS OUTER TABLE --%>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>

<%-- END DETAIL TABLE --%>

<%-- END FORM --%>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>

 </html:form>
</html:html>
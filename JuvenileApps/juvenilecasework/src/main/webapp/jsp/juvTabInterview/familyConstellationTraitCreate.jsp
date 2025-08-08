<!DOCTYPE HTML>

<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>



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
<html:base />
<html:javascript formName="familyTraitsForm" />

<%-- Javascript for emulated navigation --%>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvTabInterview/familyConstellationGeneral.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>

<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>


<title><bean:message key="title.heading" /> - familyConstellationTraitCreate.jsp</title>
</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayManageFamilyConstellationDynamics">


<logic:equal name="juvenileFamilyForm" property="action" value="update">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|240">
</logic:equal>
<logic:notEqual name="juvenileFamilyForm" property="action" value="update">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|280">
</logic:notEqual>    


<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - 
			<logic:equal name="juvenileFamilyForm" property="action" value="update"><bean:message key="prompt.update"/></logic:equal>
			<logic:notEqual name="juvenileFamilyForm" property="action" value="update"><bean:message key="prompt.create"/></logic:notEqual>
			<bean:message key="prompt.family"/> <bean:message key="prompt.constellation"/> <bean:message key="prompt.dynamics"/>
		</td>
	</tr>
</table>

<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
	<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
		<td>
  		<ul>
  			<li>Add new dynamic information for family constellation and click Add button.</li>
  			<li>Select remove hyperlink to remove current dynamic information.</li>
			
  			<logic:equal name="juvenileFamilyForm" property="action" value="update">
    			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_DYN_U%>'>		
      			<li>Select Save and Continue to to save dynamic information.</li>
    			</jims2:isAllowed>
  			</logic:equal>
  			<logic:notEqual name="juvenileFamilyForm" property="action" value="update">
    			<li>Select Next to continue</li>
  			</logic:notEqual>
  		</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>


<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%>
<div class='spacer'></div>
<table align="center" cellpadding="1" cellspacing="0" border="0" width="100%">
	<tr>
		<td><%--header info start--%> <tiles:insert
			page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
			<tiles:put name="headerType" value="profileheader" />
		</tiles:insert> <%--header info end--%></td>
	</tr>
</table>
<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%>


<%-- BEGIN DETAIL TABLE  Main Table Begin --%>
<div class='spacer'></div>
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td><%-- begin green tabs (1st row) --%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><%--tabs start--%> 
					  <tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
  						<tiles:put name="tabid" value="family" />
  						<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
  					</tiles:insert> <%--tabs end--%>
					</td>
				</tr>
				<tr>
					<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
			</table>
			<%-- end green tabs --%> 
			
			<%-- begin outer green border --%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
					<%-- begin red tabs (3rd row) --%>
					
						<div class='spacer'></div>
								<table width="98%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="top">
											  <tiles:insert page="../caseworkCommon/constellationInfoTabs.jsp" flush="true">
  												<tiles:put name="tabid" value="Dynamics" />
  											</tiles:insert>
											</td>
										</tr>
										<tr>
											<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
										</tr>
								</table>
								<%-- end red tabs --%> <%--begin red outer border --%> 

								<nested:nest property="currentConstellation">
								  
									<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
											<td valign="top" align="center">

											  <%-- BEGIN Previous Trait TABLE --%>
      				  				<logic:notEmpty name="juvenileFamilyForm" property="currentConstellation.traitList">
  												<logic:iterate id="traits" name="juvenileFamilyForm" property="currentConstellation.traitList">
  													<logic:notEmpty name="traits" property="traitId">
  													<logic:notPresent name="ExistingRecords">
  													 <bean:define id="ExistingRecords" value="1" type="java.lang.String"/>
													 </logic:notPresent>
												</logic:notEmpty>
												<logic:empty name="traits" property="traitId">
												   <logic:notPresent name="NewRecords">
													 <bean:define id="NewRecords" value="1" type="java.lang.String"/>
													 </logic:notPresent>
												</logic:empty>
												</logic:iterate>
        							</logic:notEmpty>
        
        							<logic:present name="ExistingRecords">
       								<div class='spacer'></div>
        							<table width='98%' border="0" cellpadding="4" cellspacing="0" class="borderTableBlue">
        				  			<tr>
        				  				<td valign=top class=detailHead>
        				  					<table width='100%' cellpadding=0 cellspacing=0>
        				  						<tr>
        				  							<td width='1%'><a href="javascript:showHideMulti('Characteristics', 'pChar', 1,'/<msp:webapp/>')" border=0><img border=0 src="../../images/expand.gif" name="Characteristics"></a></td>
        				  							<td class=detailHead>&nbsp;<bean:message key="prompt.family"/>
     															<bean:message key="prompt.constellation"/> <bean:message key="prompt.dynamics"/>
																</td>
        				  						</tr>
        				  					</table>
        				  				</td>
        				  			</tr>
        				  			<tr id="pChar0" class=hidden>
        				  				<td valign=top>
              				  		<table width="100%" bgcolor="#cccccc" cellspacing="1">
    													<tr bgcolor="#f0f0f0">
    														<td width="10%" class="subhead"><bean:message key="prompt.entryDate"/></td>
    														<td class="subhead"><bean:message key="prompt.dynamic"/></td>
    														<td class="subhead"><bean:message key="prompt.dynamic"/> <bean:message key="prompt.level"/></td>
    														<td class="subhead"><bean:message key="prompt.dynamic"/> <bean:message key="prompt.status"/></td>
    														<td class="subhead"><bean:message key="prompt.dynamic"/> <bean:message key="prompt.comments"/></td>
    													</tr>

                              <%int RecordCounter = 0;
    														String bgcolor = "";%>
															<nested:iterate id="traitsList" property="traitList">
																<logic:notEmpty name="traitsList" property="traitId">
																<tr class=<%RecordCounter++;
																	bgcolor = (RecordCounter % 2 == 1) ? "normalRow" : "alternateRow";
																	out.print(bgcolor);%>
																>
																	<td valign="top"><bean:write name="traitsList" property="entryDate" /></td>
																	<td valign="top"><bean:write name="traitsList" property="traitDesc" /></td>
																	<td valign="top"><bean:write name="traitsList" property="traitLevel" /></td>
																	<td valign="top"><bean:write name="traitsList" property="traitStatus" /></td>
																	<td><bean:write name="traitsList" property="traitComments" /></td>
																</tr>
																</logic:notEmpty>
															</nested:iterate>
													</table>
      				  				</td>
      				  			</tr>
      				  		</table>
				  		
      				  		<script  type='text/javascript'>
      				       if (location.search == "?confirmPC")
      				   			{
      				   				showHideMulti('Characteristics', 'pChar', 1)
      				   			}
      				   		</script>

    				   			<div class='spacer'></div>
    				   		</logic:present>
        				  <%-- END Previous Trait TABLE --%>
				  
  								<%--Begin Constellation Trait Table --%>
									<div class='spacer'></div>
  								<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  										<tr>
  											<td valign="top" class="detailHead"><bean:message key="prompt.new"/> <bean:message key="prompt.family"/>
  											<bean:message key="prompt.constellation"/> <bean:message key="prompt.dynamic"/></td>
  										</tr>
  										<tr>
  											<td><%-- Begin Inner Table --%>
  											<table width="100%" border="0" cellpadding="2" cellspacing="1">
  													<tr>
  														<td class="formDeLabel" nowrap>
															  <img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border="0" width="10" height="9"><bean:message key="prompt.dynamic"/>
															</td>
  														<td valign="top" class="formDe" colspan="3">
															  <html:select name="juvenileFamilyForm" property="currentTrait.traitDescId" styleId="dynamic">
    															<option value="">Select one</option>
    															<html:optionsCollection name="juvenileFamilyForm" property="traitDescList" value="code" label="description" />
    														</html:select>
															</td>
  													</tr>
  													<tr>
  														<td class="formDeLabel">
															  <img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border="0" width="10" height="9"><bean:message key="prompt.dynamic"/> <bean:message key="prompt.level"/>
															</td>
  														<td class="formDe">
															  <html:select name="juvenileFamilyForm" property="currentTrait.traitLevelId" styleId="dynamicLevel">
  															<option value="">Select one</option>
    															<html:optionsCollection name="juvenileFamilyForm" property="traitLevelList" value="code" label="description" />
    														</html:select>
															</td>
  														<td class="formDeLabel">
															  <img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border="0" width="10" height="9"><bean:message key="prompt.dynamic"/> <bean:message key="prompt.status"/>
															</td>
  														<td class="formDe">
															  <html:select name="juvenileFamilyForm" property="currentTrait.traitStatusId" styleId="dynamicStatus">
  															<option value="">Select one</option>
    															<html:optionsCollection name="juvenileFamilyForm" property="traitStatusList" value="code" label="description" />
    														</html:select>
															</td>
  													</tr>
  													<tr>
  														<td colspan="6" class="formDeLabel"><bean:message key="prompt.dynamic"/> <bean:message key="prompt.comments"/>&nbsp;
                       					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
                       						<tiles:put name="tTextField" value="currentTrait.traitComments"/>
                       						<tiles:put name="tSpellCount" value="spellBtn1" />
                     						</tiles:insert>
                     						(Max. characters allowed: 255)
  														</td>
  													</tr>
  													<tr>
  														<td bgcolor="#FFFFFF" colspan="6" align="center">
  														  <html:textarea name="juvenileFamilyForm" property="currentTrait.traitComments" rows="2" cols="40" style="width:100%" >Enter dynamic (trait) comments here.  Enter comments here.</html:textarea>
  														</td>
  													</tr>
  											</table>
                                                           
  											<div align="center"> 
     											<html:submit property="submitAction" styleId="dynamicsAddToList">
     												<bean:message key="button.addToList" />
     											</html:submit> 
												</div>
  											<div class=spacer></div> 
												</td>
  										</tr>
  										<tr>
  											<td>
    											<logic:notEmpty name="juvenileFamilyForm"property="currentConstellation">
    
    												<%int RecordCounter = 0;
          										String bgcolor = "";%>
    												<logic:present name="NewRecords">
    													<%-- Begin Inner Table 2 --%>
    													<table width="100%" bgcolor="#cccccc" cellspacing="1">
    															<tr bgcolor="#f0f0f0">
    																<td width="10%"></td>
    																<td class="subhead"><bean:message key="prompt.dynamic"/></td>
    																<td class="subhead"><bean:message key="prompt.dynamic"/> <bean:message key="prompt.level"/></td>
    																<td class="subhead"><bean:message key="prompt.dynamic"/> <bean:message key="prompt.status"/></td>
    																<td class="subhead"><bean:message key="prompt.dynamic"/> <bean:message key="prompt.comments"/></td>
    															</tr>
  
  															<nested:iterate id="traitsList" property="traitList">
  																<logic:empty name="traitsList" property="traitId">
  																<tr class=<%RecordCounter++;
  																	bgcolor = (RecordCounter % 2 == 1) ? "normalRow" : "alternateRow";
  																	out.print(bgcolor);%>
																	>
  																	<td valign="top">
  																		<a href="/<msp:webapp/>displayManageFamilyConstellationDynamics.do?submitAction=Remove&amp;selectedValue=<%=(RecordCounter-1)%>"> Remove</a>
  																	</td>
  																	<td valign="top"><bean:write name="traitsList" property="traitDesc" /></td>
  																	<td valign="top"><bean:write name="traitsList" property="traitLevel" /></td>
  																	<td valign="top"><bean:write name="traitsList" property="traitStatus" /></td>
  																	<td><bean:write name="traitsList" property="traitComments" /></td>
  																</tr>
  																</logic:empty>
  															</nested:iterate>
  													</table>
  												</logic:present>
  											</logic:notEmpty> 
  
												<div class='spacer'></div>
  											</td>
  										</tr>
  								</table>
									<%-- end outer red --%>
								</nested:nest>

							<%-- BEGIN BUTTON TABLE --%>
								<div class=spacer></div> 				
            		<table border="0" width="100%">
            				<tr>
            					<td align="center"><html:submit property="submitAction">
            						<bean:message key="button.back"></bean:message>
            					</html:submit> 
            					<logic:equal name="juvenileFamilyForm" property="action" value="update">
              					<logic:present name="NewRecords">
                					<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_DYN_U%>'>		
                  					<html:submit property="submitAction" styleId="traitCreateSaveCont">
                  						<bean:message key="button.saveAndContinue"></bean:message>
                  					</html:submit> 
                					</jims2:isAllowed>
              					</logic:present>
            					</logic:equal>
            					<logic:notEqual name="juvenileFamilyForm" property="action" value="update">
            					<html:submit property="submitAction"><bean:message key="button.next"></bean:message></html:submit> 
            					</logic:notEqual>
            					</td>
            				</tr>
            		</table>
            		<div class=spacer></div> 
            		<%-- END BUTTON TABLE --%> 
								</td>
							</tr>
  					</table>
  				<div class='spacer'></div>
  			<%-- end outer green --%>
				</td>
  		</tr>
  </table>

  <div class='spacer'></div>
  

		</td>
	</tr>
</table>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

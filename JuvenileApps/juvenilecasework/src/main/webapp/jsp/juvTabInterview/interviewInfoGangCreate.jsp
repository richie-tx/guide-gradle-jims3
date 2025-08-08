<!DOCTYPE HTML>
<%-- User clicks the "Add Gang Information" button from previous page --%>
<%--MODIFICATIONS --%>
<%-- 11/26/2012	C Shimek	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@page import="mojo.km.utilities.DateUtil"%>
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
<title><bean:message key="title.heading"/> - interviewInfoGangCreate.jsp</title>


<%--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS--%>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/gangReferrals.js?a=11"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script> 



</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/handleJuvenileGangsCreate" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  	<tr>
    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Create Gang Information</td>	  	    	 
  	</tr>  	
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
   	<tr>
     	<td>
     		<ul>
	        	<li>Click Remove Selected to remove selected row from list.</li>
	        	<li>Review items on list then click Finish to save information.</li>
		    </ul>
		</td>
  	</tr>  
  	<tr>
  		<td class="required"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
  	</tr>
  	<tr>
  		<td class="required">+ One of these fields is required.</td>
  	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>

<div class="spacer4px"</div>
<%-- BEGIN DETAIL TABLE --%>  
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  	<tr>
    	<td valign="top">
<%-- BEGIN GREEN TABS TABLE --%>    	
    		<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="main"/>
							<tiles:put name="juvNumId" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>	
					</td>
				</tr>
				<tr>
			  		<td bgcolor="#33cc66" height="5"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
			  	</tr> 
			</table>
<%-- END GREEN TABS TABLE --%>

<%-- BEGIN GREEN BORDER TABLE --%>    			
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" height="5"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<%-- BEGIN BLUE TABS TABLE --%> 					
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
										<tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
										<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
									</tiles:insert>	
								</td>
							</tr>
							<tr>
			  					<td bgcolor="#6699FF" height="5"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
			  				</tr>
			  			</table>
<%-- END BLUE TABS TABLE --%> 	
		  				
<%-- BEGIN BLUE BORDER TABLE --%> 							
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td valign="top" height="5"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">																							
<%-- BEGIN GANG INFO TABLE --%>					
									<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr>
											<td colspan="4" class="detailHead"><bean:message key="prompt.gang" /> <bean:message key="prompt.info" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.entryDate" /></td>
											<td class="formDe" colspan="3">
												<input type="text" name="entryDate" Id="entryDte" size="10" maxlength="10" size="10" readonly="true"/> 
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap="nowrap"> + <bean:message key="prompt.gangName" /></td>	
											<td class="formDe" colspan="3">
												<html:select property="gangNameId" styleId="gangSelId">
													<html:option key="select.generic" value="" />
													<html:optionsCollection property="gangNames" value="code" label="description"/>				
												</html:select>
											</td>
										</tr>
										
										<tr id="otherGangTxt" class="hidden">
											<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.other" /> <bean:message key="prompt.gang" /> <bean:message key="prompt.name" /></td>
											<td class="formDe" colspan="3"><input type="text" name="otherGangName" Id="otherGangNameId" size="40" maxlength="30" /></td>
										 
										<tr id="descHybridTxtAreaLbl" class="hidden">
												<td class="formDeLabel" colspan="4"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.descHybrid"/> (Max. characters allowed: 255)</td>
										</tr>
										<tr id="descHybridTxtArea" class="hidden">
												<td class="formDe" colspan="4"><html:textarea name="juvenileGangsForm" property="descHybrid" styleId="descHybrid"  rows="3" style="width:100%" /></td>
										</tr>
										 
										<tr class="hidden">	
											<td class="formDeLabel" > + <bean:message key="prompt.cliqueSet" /></td>								
											<td class="formDe" colspan="3">
												<html:select property="cliqueSetId" styleId="cliqueSelId">
													<html:option key="select.generic" value="" />
													<html:optionsCollection property="cliqueSets" value="code" label="description"/>				
												</html:select>
											</td>
										</tr>
										<tr id="otherCliqueTxt" class="hidden">
											<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.other" /> <bean:message key="prompt.cliqueSet" /></td>
											<td class="formDe" colspan="3"><input type="text" name="otherCliqueSet"  Id="otherCliqueSetId" size="43" maxlength="30" /></td>
										</tr>		
										<tr>

											<td class="formDeLabel"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.status" /></td>	
											<td class="formDe" width="45%">
											
											<html:select property="currentStatusId" styleId="currentStatusId" >
													<html:option key="select.generic" value="" />
													<html:optionsCollection property="status" value="code" label="description"/>				
												</html:select>
											</td>

											<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.associationType" /></td>								
											<td class="formDe">
												<html:select property="associationTypeId" styleId="assocTypeSel">
													<html:option key="select.generic" value="" />
													<html:optionsCollection property="associationTypes" value="code" label="description"/>				
												</html:select>
											</td>
										</tr>
										<tr>	
											<td class="formDeLabel" colspan="4"><bean:message key="prompt.alias"/>/<bean:message key="prompt.moniker"/> (Max. characters allowed: 255)</td>
										</tr>
										<tr>									
											<td class="formDe" colspan="4"><html:textarea name="juvenileGangsForm" property="aliasMoniker"  rows="3" style="width:100%" /></td>
										</tr>
										<tr>		
											<td class="formDeLabel" colspan="4"><bean:message key="prompt.rank" /> (Max. characters allowed: 255)</td>	
										</tr>
										<tr>								
											<td class="formDe" colspan="4"><html:textarea name="juvenileGangsForm" property="rank" rows="3" style="width:100%" /></td>
										</tr>						
										<tr>
											<td colspan="4" align="center">
												<html:submit property="submitAction" styleId="gangAddValidate">				
													<bean:message key="button.add" />
												</html:submit>
											</td>												
										</tr>
										<tr>
											<td valign="top" colspan="4">																		
<%-- BEGIN NEW GANG INFO LIST --%>
												<table width="100%" border="0" cellpadding="2" cellspacing="1">														
													<tr>
														<td class="formDeLabel"></td>
														<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.entryDate"/>&nbsp;</td>
														<td class="formDeLabel"><bean:message key="prompt.gangName"/></td>
														<td class="formDeLabel hidden"><bean:message key="prompt.cliqueSet"/></td>
														<td class="formDeLabel"><bean:message key="prompt.status"/></td>
														<td class="formDeLabel"><bean:message key="prompt.associationType"/></td>
													</tr>
													<logic:empty name="juvenileGangsForm" property="newGangsInfoList">
														<tr>
															<td colspan="6" align="center">No items to display</td>
														</tr>	
													</logic:empty>
														
														<%int RecordCounter = 0;
  														String bgcolor = "";%>  
  												<logic:notEmpty name="juvenileGangsForm" property="newGangsInfoList">
	        										<logic:iterate id="gangsListIter" name="juvenileGangsForm" property="newGangsInfoList">
	  													<tr class=<%RecordCounter++;
	  														bgcolor = "alternateRow";
	  														if (RecordCounter % 2 == 1)
	  															bgcolor = "normalRow";
	  														out.print(bgcolor);%>>
	  															<td valign="top"><a href="/<msp:webapp/>handleJuvenileGangsCreate.do?submitAction=<bean:message key='button.removeSelected'/>&selectedValue=<%=(RecordCounter-1)%>">Remove</a></td>
	
	  															<td><bean:write name="gangsListIter" property="entryDate" formatKey="date.format.mmddyyyy" /></td>
																<td><bean:write name="gangsListIter" property="gangNameDesc"/></td>
																<td class="hidden"><bean:write name="gangsListIter" property="cliqueSetDesc" /></td>
																<td><bean:write name="gangsListIter" property="currentStatusDesc" /></td>
																<td><bean:write name="gangsListIter" property="associationTypeDesc" /></td>
																<logic:notEmpty name="gangsListIter" property="descHybrid">
																	<tr>
																		<td></td>
																		<td></td>
																		<td align="right" valign="top" width="1%" nowrap="nowrap"><b><bean:message key="prompt.descHybrid"/></b>&nbsp;</td> 
																		<td  colspan="4">
																			<bean:write name="gangsListIter" property="descHybrid"/>
																		</td>
																	</tr>
																</logic:notEmpty>
																	<tr>
																		<td></td>
																		<td></td>
																		<td align="right" valign="top" width="1%" nowrap="nowrap"><b><bean:message key="prompt.alias"/>/<bean:message key="prompt.moniker"/></b>&nbsp;</td> 
																		<td  colspan="4">
																			<bean:write name="gangsListIter" property="aliasMoniker"/>
																		</td>
																	</tr>
																	<tr>
																		<td></td>
																		<td></td>
																		<td align="right" valign="top" width="1%" nowrap="nowrap"><b><bean:message key="prompt.rank"/></b>&nbsp;</td> 
												 						<td  colspan="4" >
																			<bean:write name="gangsListIter" property="rank" />
																		</td>
																	</tr>
	  													</tr>
	  												</logic:iterate>
	  												<%if(RecordCounter>0){ %>
														<bean:define id="newGangListSize" value="1"/>
													<%} %>
  												</logic:notEmpty>
												</table>
											</td>
										</tr>
									</table>			    		 
<%-- BEGIN GANG INFO INPUT TABLE --%>
									<div class="spacer"></div>
<%-- BEGIN BUTTON TABLE --%>
									<table width='98%' align="center">	
					  					<tr>
											<td align="center">
												<html:submit property="submitAction">
													<bean:message key="button.back"/>
												</html:submit>	
												<logic:present name="newGangListSize">	
													<html:submit property="submitAction" styleId="gangInfoFinish">				
														<bean:message key="button.finish" />
							  						</html:submit>			
						    					</logic:present>
												<logic:notPresent name="newGangListSize">	
													<input type="button" name="disableBtn" value="Finish" disabled="disabled">				
						    					</logic:notPresent>
												<html:submit property="submitAction">
													<bean:message key="button.cancel" />
												</html:submit>
											</td>
										</tr>
									</table>
<%-- END BUTTON TABLE --%>
									<div class="spacer"></div>
								</td>
							</tr>
						</table>
<%-- BEGIN BLUE BORDER TABLE --%> 							
						<div class="spacer"></div>		
					</td>
				</tr>
			</table>
<%-- BEGIN GREEN BORDER TABLE --%> 				
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>
</html:form>
<%-- END FORM --%>
<br>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
<!DOCTYPE HTML>
<!-- 08/24/2005	 Aswin Widjaja - Create JSP -->
<!-- 01/13/2006  C Shimek      - per defect 25679, remvoed prototype notes at botton of page --> 
<!-- 01/17/2006  C Shimek      - per defect 27455, removed hardcoded Group 1 literal and use dynamic value -->
<!-- 03/03/2006  C Shimek      - ER#28542 add disable button to finish button -->
<!-- 06/28/2006  Hien Rodriguez - defect#32504 wrap security tag around Update Associations buttons -->
<!-- 06/29/2006  Clarence Shimek - defect#30799 select condition radio button if only single condition found -->
<!-- 01/19/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<%-- 10/13/2015  RYoung          - #30612 MJCW: IE11 conversion of Common Supervision link on UILeftNav (UI)--%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@page import="naming.UIConstants"%>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<html:base />
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<title><bean:message key="title.heading" /> - conditionSearchResults.jsp</title>

<script language="JavaScript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/groups.js"></script>
<script type="text/javascript">
function checkSingleCondition(){
	var cond = document.getElementsByName("conditionID");
	if (cond.length == 1){
		cond[0].checked = true;
	}
}
</script>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> 

</head>

<bean:define id="conditionGroup1Caption" type="java.lang.String" value="prompt.category"/>
<bean:define id="conditionGroup2Caption" type="java.lang.String" value="prompt.type"/>
<bean:define id="conditionGroup3Caption" type="java.lang.String" value="prompt.subtype"/>

<bean:define id="btnsEnabled" value="false" type="java.lang.String"/>
<jims2:isAllowed requiredFeatures="CS-COND-COPY">
	<% btnsEnabled="true"; %>
</jims2:isAllowed>
<jims2:isAllowed requiredFeatures="CS-COND-UPDATE">
	<% btnsEnabled="true"; %>
</jims2:isAllowed>
	
<jims2:isAllowed requiredFeatures="CS-COND-DELETE">
<% btnsEnabled="true"; %>
</jims2:isAllowed>

<body topmargin=0 leftmargin="0" onload="checkSingleCondition();" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handleSupervisionConditionSelection" target="content"> <%-- onsubmit="return isAnySelected(this, 'conditionId');" --%>
<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|2">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  	</tr>
  	<tr>
    	<td valign=top>
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
					<!--tabs start-->
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true"/>
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>
				<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>
					  	<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
					</tr>
					<tr>
						<td valign=top align=center>
							<!-- BEGIN HEADING TABLE -->
							<table width=100%>
							  <tr>
							    <td align="center" class="header">
							    <bean:message key="title.supervisionCondition" />&nbsp;<bean:message key="title.searchResults" /></td>
							  </tr>
							</table>
							<!-- END HEADING TABLE -->
							
							<%-- Begin Pagination Header Tag--%>
							<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
							<br>
							<pg:pager
							    index="center"
							    maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
							    maxIndexPages="10"
							    export="offset,currentPageNumber=pageNumber"
							    scope="request">
							    <input type="hidden" name="pager.offset" value="<%= offset %>">
							<%-- End Pagination header stuff --%>
							
						    <!-- BEGIN ERROR TABLE -->
							    <table width=98% align=center>							
								    <tr>
									    <td align="center" class="errorAlert"><html:errors></html:errors></td>
								    </tr>		
							    </table>
						    <!-- END ERROR TABLE -->							
							<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
							   <tr>
							     <td><ul>
							        <li>Click on hyperlink to View</li>
							      
							        <logic:notEqual name="btnsEnabled" value="<%=btnsEnabled%>">
							        <li> Or select radio button and select appropriate button.</li>
							        </logic:notEqual>
							     </ul></td>
							  </tr>
							</table>
							<!-- BEGIN  TABLE -->
							<logic:notEmpty name="supervisionConditionForm" property="conditionSearchResults">
								<div align=center>
									<bean:size id="conditionSearchResultsSize" name="supervisionConditionForm" property="conditionSearchResults"/>								
									<bean:write name="conditionSearchResultsSize"/> 
									<bean:message key="prompt.resultsFor" />&nbsp;
								
								<bean:message key="<%=conditionGroup1Caption%>" />: 
								<logic:notEmpty name="supervisionConditionForm" property="group1Name">
									<logic:notEqual name="supervisionConditionForm" property="group1Name" value="">
										<bean:write	name="supervisionConditionForm" property="group1Name" />&nbsp;
									</logic:notEqual>
									<logic:equal name="supervisionConditionForm" property="group1Name" value="">>
										All &nbsp;
									</logic:equal>
								</logic:notEmpty>
								<logic:empty name="supervisionConditionForm" property="group1Name">
									All &nbsp;
								</logic:empty>	
							
						<logic:notEmpty name="supervisionConditionForm" property="group2Name">
							<bean:message key="<%=conditionGroup2Caption%>" />: <bean:write
								name="supervisionConditionForm" property="group2Name" />&nbsp;</logic:notEmpty>
						
					<%--	&nbsp;Courts: 
						<logic:empty name="supervisionConditionForm"
							property="selectedCourts">
							<bean:message key="prompt.courtsAll" />
						</logic:empty>
						<logic:notEmpty name="supervisionConditionForm"
							property="selectedCourts"> Selected Courts
					</logic:notEmpty>  --%>
								</div>
								<table width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
									<tr class="detailHead">
										<td width=1%></td>
										<td>
											<bean:message key="prompt.conditionName" />
											<jims2:sortResults beanName="supervisionConditionForm" results="conditionSearchResults" primaryPropSort="name" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="4" />
										</td>
										<td>
											<bean:message key="<%=conditionGroup1Caption%>"/>
											<jims2:sortResults beanName="supervisionConditionForm" results="conditionSearchResults" primaryPropSort="group1Name" primarySortType="STRING" sortId="2" levelDeep="4"/>
										</td>
										<td><bean:message key="<%=conditionGroup2Caption%>"/>
											<jims2:sortResults beanName="supervisionConditionForm" results="conditionSearchResults" primaryPropSort="group2Name" primarySortType="STRING" sortId="3" levelDeep="4"/>
										</td>
										<td>Supervision Type <!-- bean:message key="Supervision Type"/-->
											<!-- jims2:sortResults beanName="supervisionConditionForm" results="conditionSearchResults" primaryPropSort="supervisionType" primarySortType="STRING" sortId="4" levelDeep="4"/-->
										</td>
									</tr>
									
									<%
									int RecordCounter = 0; 
									String bgcolor = ""; 
									RecordCounter = 0; 
									bgcolor = ""; %> 	
									
									<logic:iterate id="conditionSearchResultsIter" name="supervisionConditionForm" property="conditionSearchResults">
										<pg:item>
										<tr class= <% RecordCounter++;
											bgcolor = "alternateRow";                      
											if (RecordCounter % 2 == 1)
												bgcolor = "normalRow";
												out.print(bgcolor);  %>  >
											<td width=1%>
											 <logic:notEqual name="btnsEnabled" value="<%=btnsEnabled%>">
											 	
												<html:radio idName="conditionSearchResultsIter" property="conditionId" value="conditionId" />
											</logic:notEqual>
											</td>
											<td>
												<a href="/<msp:webapp/>displaySupervisionConditionView.do?conditionId=<bean:write name="conditionSearchResultsIter" property="conditionId"/>">
												<bean:write name="conditionSearchResultsIter" property="name" /></a>
											</td>
											<td><bean:write name="conditionSearchResultsIter" property="group1Name" /></td>
											<td><bean:write name="conditionSearchResultsIter" property="group2Name" /></td>
											<td>
											<%int locCounter=0;%>
			                                	<logic:notEmpty name="conditionSearchResultsIter" property="supervisionType">
			                                		<span id="firstLine<%=RecordCounter%>" class=visible>
			                            				<table width="100%" cellpadding="2" cellspacing="1">
				                            				<tr>
						                                  	<logic:iterate name="conditionSearchResultsIter" property="supervisionType" id="prev" >
						                                  		<%locCounter++;%>
							                                  	<% if(locCounter == 1){ %>
																    <td>
																    	<bean:write name="conditionSearchResultsIter" property="supervisionTypeSummary"/>
																    	<logic:notEqual name="conditionSearchResultsIter" property="listSize" value="1">
																    		<a href="javascript:showHide('locTable<%=RecordCounter%>', 1); showHide('firstLine<%=RecordCounter%>', 0)">more...</a>
																    	</logic:notEqual>
																    </td>
															       	<% }%>
							                               	</logic:iterate>
							                               	</tr>				                               	  
														</table> 
						                            </span>
				                             <% locCounter=0;%>
				                               		<span class=hidden id="locTable<%=RecordCounter%>">
    													<div class=scrollingDiv100NoBorder>
    									  					<table border=0 width=100%>
							                                	<logic:iterate name="conditionSearchResultsIter" property="supervisionType" id="prev" >
							                                  	<tr class= <% locCounter++;%>>
						                                  	  		<td> <bean:write name="prev"/></td>							                               	
					                               	  		  	</tr>
					                               	  		  	</logic:iterate>
					                               	     		<tr>
				    									  			<td><a href="javascript:showHide('locTable<%=RecordCounter%>', 0);showHide('firstLine<%=RecordCounter%>', 1)">hide</a></td>
				    									  		</tr>
						                               	  </table>
					                               	   </div>
  										  			</span>
  									  			</logic:notEmpty>
											
											</td>
										</tr>
										</pg:item>
									</logic:iterate>
									
								</table>				
							</logic:notEmpty>

							<%-- Begin Pagination navigation Row--%>
							<table align="center">
				  			<tr>
				    			<td>
				    				<pg:index>
				    					<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
				    						<tiles:put name="pagerUniqueName" value="pagerSearch"/>
				    						<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
				    					</tiles:insert>
				    			 	</pg:index>
				  		    </td>
						    </tr>
						  </table>
							<br>						  
							<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">
						  	<tr>
						    	<td align="center">
										<html:submit property="submitAction"  onclick="return disableSubmit(this, this.form)"><bean:message key="button.back" /></html:submit>
										<jims2:isAllowed requiredFeatures="CS-COND-COPY">
											<html:submit property="submitAction" onclick="return isAnySelected(this.form, 'conditionId') && disableSubmit(this, this.form);"><bean:message key="button.copy"/></html:submit>
										</jims2:isAllowed>
										<jims2:isAllowed requiredFeatures="CS-COND-UPDATE">
											<html:submit property="submitAction" onclick="return isAnySelected(this.form, 'conditionId') && disableSubmit(this, this.form);"><bean:message key="button.update"/></html:submit>
										</jims2:isAllowed>		
										<jims2:isAllowed requiredFeatures="CS-COND-DELETE">
											<html:submit property="submitAction" onclick="return isAnySelected(this.form, 'conditionId') && disableSubmit(this, this.form);"><bean:message key="button.delete"/></html:submit>
										</jims2:isAllowed>
										<html:submit property="submitAction"  onclick="return disableSubmit(this, this.form)"> <bean:message key="button.cancel" /></html:submit>
										
						    	</td>
						  	</tr>
						  	<tr>
						  		<td align=center>
						  			<jims2:isAllowed requiredFeatures="CS-COND-UPDATE">
						  			<%-- Commenting out for now -- because JUV as of 04/03/2007 doesn't need court policy or dept policy but may in the future  
														
						  						<html:submit property="submitAction" onclick="return isAnySelected(this.form, 'conditionId') && disableSubmit(this, this.form);"><bean:message key="button.updateConsequenceAssociations"/></html:submit>
											<html:submit property="submitAction" onclick="return isAnySelected(this.form, 'conditionId') && disableSubmit(this, this.form);"><bean:message key="button.updateDeptPolicyAssociations"/></html:submit>
							  	--%>
							  		</jims2:isAllowed>
						  		</td>
						  	</tr>
						</table>
						<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<!-- END  TABLE -->
</div>

<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>
</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

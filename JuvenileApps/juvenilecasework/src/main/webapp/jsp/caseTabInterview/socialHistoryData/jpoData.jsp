<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 10/19/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>



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
<%@include file="../../jQuery.fw"%>

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- jpoData.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>


<script type='text/javascript'>

$(document).ready(function(){	
	$("#generateDraftReport").click(function(e){
		spinner();
		$.ajax({
	        url: '/JuvenileCasework/handleSocialHistoryData.do?submitAction=Generate Draft Report&submitType=ajax',
	        type: 'post',
	        data: $('form#socialHistoryData').serialize(),
	        success: function(data, textStatus , xhr) {
	         	if (200 == xhr.status){
	         		$(".overlay").remove();
	         		if (isJson(data)) {
	         			alert((JSON.parse(data)).error);
	         		} 
	         	}
	        }
	    });
	})
	
	$("#generateFinalReport").click(function(e){
		e.preventDefault();
		spinner();
		$.ajax({
	        url: '/JuvenileCasework/handleSocialHistoryData.do?submitAction=Generate Final Report&submitType=ajax',
	        type: 'post',
	        data: $('form#socialHistoryData').serialize(),
	        success: function(data, textStatus , xhr) {
	         	if (200 == xhr.status){
	         		$(".overlay").remove();
	         		if (isJson(data)) {
	         			alert((JSON.parse(data)).error);
	         		} else {
	         			setTimeout(function(){alert("Documents can be located in the Documents tab")}, 1000);
	         			//sessionStorage.setItem("isReportGenerated", "true");
	         		}
	         	}
	         	
	        }
	    });
	})
	
	function isJson(str) {
   		try {
        	json = JSON.parse(str);
    	} catch (e) {
        	return false;
    	}
    	return true;
	}
	
})
function submitSelection(theForm)
{
	theForm.action = "/<msp:webapp/>handleJpoDataSelection.do?submitAction=Link";
	theForm.submit();
}
</script>
</head>

<html:form styleId="socialHistoryData" action="/handleSocialHistoryData" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|97">
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0"> 


	<%-- BEGIN HEADING TABLE --%> 
	<table width='100%'> 
		<tr> 
			<td align="center" class="header">
				Juvenile Casework - Conduct Interview<br>
				Social History Data - JPO Data
			</td>
		</tr> 
	</table> 
	<%-- END HEADING TABLE --%> 

	<%-- BEGIN ERROR TABLE --%>
	   
		<!--<table width="100%">
			<tr>		  
				<td align="center" class="errorAlert">
					<html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"></html:errors>
				</td>		  
			</tr>   	  
		</table>-->
	
	<%-- END ERROR TABLE --%>  

	<%-- BEGIN INSTRUCTION TABLE --%> 
<div class='spacer'></div> 
	<table width="98%" border="0"> 
		<tr> 
			<td> 
				<ul> 
					<li>Click check box to exclude from report, and click on Save Changes button to save the changes.</li>
			        <li>Click Court Disposition Alternatives button or Detention Reason button to proceed with report generation.</li>
			        <li>Click Back button to return to previous page. </li>
				</ul>
			</td>
		</tr> 
	</table> 
	<%-- END INSTRUCTION TABLE --%> 


<%-- BEGIN HEADER INFO TABLE --%>
<div class='spacer'></div>
<tiles:insert page="../../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<div class='spacer'></div>
	<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<td valign=top>
				<%-- BEGIN TAB  --%>
  			<tiles:insert page="../../caseworkCommon/casefileTabs.jsp" flush="true">
  				<tiles:put name="tabid" value="casefiledetailstab"/>	
  				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  			</tiles:insert>	
  
				<%-- BEGIN BORDER TABLE BLUE TABLE --%>
				<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>
						<td valign=top align=center>
						<div class='spacer'></div>
						 <table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign=top>
											<%--tabs start--%>
												<tiles:insert page="../../caseworkCommon/casefileInfoTabs.jsp" flush="true">
													<tiles:put name="tabid" value="interviewtab"/>
	  												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
												</tiles:insert>	
											<%--tabs end--%>
											</td>
										</tr>
										<tr>
										  	<td bgcolor=#33cc66><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
										  </tr>
										  
							  		</table>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
										<tr>
											<td valign=top align=center>
						
						<div class='spacer'></div>						
							<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
								<tr>
									<td valign=top>
										<table width='100%' border="0" cellpadding="0" cellspacing="0" >
											<tr>
												<td valign=top>
													<%--tabs start--%>
													<tiles:insert page="../../caseworkCommon/socialHistoryTabs.jsp" flush="true">
														<tiles:put name="tabid"><bean:write name="socialHistoryForm" property="currentTab"/></tiles:put>
													</tiles:insert>
													<%--tabs end--%>
												</td>
											</tr>
											<tr><td bgcolor=#ff6666><img src="/<msp:webapp/>images/spacer.gif" width=5></td></tr>
										</table>

										<%--begin outer blue border --%>
										<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
											<tr>
												<td valign=top align=center>
												
												
							<%-- BEGIN Activities TABLE --%>
							<div class='spacer'></div>
							<table width='98%' border="0" cellpadding="2" cellspacing="0"
								class="borderTableBlue">
								<tr>
									<td valign="top" class="detailHead" colspan="6">
									<table width='100%' cellpadding="0" cellspacing="0">
										<tr>
											<td width='1%'><a
												href="javascript:showHideMulti('Activity List', 'acChar', 1, '/<msp:webapp/>')"
												border="0"><img border="0"
												src="/<msp:webapp/>images/expand.gif"
												name="Activity List" alt=""></a></td>
											<td align="left" class="detailHead" colspan="6">&nbsp;<bean:message
												key="prompt.activities" /></td>
										</tr>
									</table>
									</td>
								</tr>

								<tr id="acChar0" class="hidden">
									<td><bean:define id="paginationResultsPerPage"
										type="java.lang.String">
										<bean:message key="pagination.recordsPerPage"></bean:message>
									</bean:define> <pg:pager index="center"
										maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
										maxIndexPages="10"
										export="offset,currentPageNumber=pageNumber" scope="request">
										<table cellpadding="2" cellspacing="1" width='100%'>
											<logic:notEmpty name="socialHistoryForm" property="activities">
												<tr bgcolor='#cccccc'>
													<td align="left" class="subhead" valign=top nowrap><bean:message key="prompt.entryDate"/>
                    				<jims:sortResults beanName="socialHistoryForm" results="activities" primaryPropSort="activityDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC" sortId="1" levelDeep="3"/>				
                    				</td>
                    				<td align="left" class="subhead" valign=top nowrap><bean:message key="prompt.activity"/>
                    				<jims:sortResults beanName="socialHistoryForm" results="activities" primaryPropSort="activityDesc" primarySortType="STRING"  sortId="2" levelDeep="3"/>	
                    				</td>
                    				<td align="left" class="subhead" valign=top nowrap><bean:message key="prompt.comments"/>
                    				<jims:sortResults beanName="socialHistoryForm" results="activities" primaryPropSort="comments" primarySortType="STRING"  sortId="3" levelDeep="3"/>	
                    				</td>
												</tr>
												<%int RecordCounter = 0;
            							String bgcolor = "";%>
												<logic:iterate id="activityIndex" name="socialHistoryForm"
													property="activities">
													<%-- Begin Pagination item wrap --%>
													<pg:item>
														<tr
															class='<%RecordCounter++;  bgcolor = "alternateRow";
										if (RecordCounter % 2 == 1)
											bgcolor = "normalRow";						
											out.print(bgcolor);%>' height="100%">
														<td>	 <bean:write name="activityIndex" property="activityDate" formatKey="date.format.mmddyyyy" /> </td>       
										
										
										<td align="left" valign=top><bean:write name="activityIndex" property="activityDesc" /></td>
										<td align="left" valign=top><bean:write name="activityIndex" property="comments" /></td>
														</tr>
													</pg:item>
													<%-- End Pagination item wrap --%>
												</logic:iterate>

											</logic:notEmpty>
											<logic:empty name="socialHistoryForm" property="activities">
												<tr>
												<td align="left">No Activities available.</td>
												</tr>
											</logic:empty>
										</table>
										<%-- Begin Pagination navigation Row--%>
										<table align="center">
											<tr>
												<td><pg:index>
													<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
														<tiles:put name="pagerUniqueName" value="pagerSearch" />
														<tiles:put name="resultsPerPage"
															value="<%=paginationResultsPerPage%>" />
													</tiles:insert>
												</pg:index></td>
											</tr>
										</table>
										<%-- End Pagination navigation Row--%>
									</pg:pager></td>
								</tr>
							</table>
												<div class='spacer'></div>
													<table width='98%' border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
													
														<tr class=detailHead>
															<td align="left" colspan='7'>JPO Data</td>
														</tr>
														<tr>
															<td>
																<table cellpadding=2 cellspacing=1 width='100%'>
																	<tr>
																		<td align="left" width='1%'>
																			<html:radio name="socialHistoryForm" property="selectedJPOData" value="1" onclick="javascript:submitSelection(this.form);"/>
																		</td>
																		<td align="left">Court Disposition Alternatives</td>
																	</tr>
																	<tr>
																		<td align="left" width='1%'>
																			<html:radio name="socialHistoryForm" property="selectedJPOData" value="2" onclick="javascript:submitSelection(this.form);"/>
																		</td>
																		<td align="left">Detention Reason</td>

																	</tr>
																	<tr>
																		<td align="left" width='1%'>
																			<html:radio name="socialHistoryForm" property="selectedJPOData" value="0" onclick="javascript:submitSelection(this.form);"/>
																		</td>
																		<td align="left">CRIS Report</td>
																	</tr>
																	<tr>
																		<td align="left" width='1%'>
																			<html:radio name="socialHistoryForm" property="selectedJPOData" value="3" onclick="javascript:submitSelection(this.form);"/>
																		</td>
																		<td align="left">Report to Referee Initiation</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
													
													<%-- End Pagination navigation Row--%>
													<div class='spacer'></div>
													<table border="0" width="100%">
														<tr>
															<td align="center">
																<html:submit styleId="generateDraftReport" property="submitAction"><bean:message key="button.generateDraftReport"/></html:submit>
																<html:submit styleId="generateFinalReport" property="submitAction"><bean:message key="button.generateFinalReport"/></html:submit>
																<!--<input type="button" id="generateDraftReport" value="Generate Draft Report"/>
																<input type="button" id="generateFinalReport" value="Generate Final Report"/>-->
															</td>
														</tr>
														<tr>
															<td align="center">
																<input type="submit" name="submitAction" value="<bean:message key='button.back'/>"
																	onclick="changeFormActionURL('/<msp:webapp/>globalBack.do', true)">
																<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit> 
															</td>
														</tr>
													</table>
													
												</td>
											</tr>
										</table>
										<div class='spacer'></div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div class='spacer'></div>
	</td>
        </tr>
        </table>
        <div class='spacer'></div>
    </td>
  </tr>
</table>
<%-- End Pagination Closing Tag --%>
							
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:form>
</html:html>
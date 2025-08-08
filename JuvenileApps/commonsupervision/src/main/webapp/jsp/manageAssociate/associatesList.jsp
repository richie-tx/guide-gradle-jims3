<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%--03/04/2008	LDeen	Defect #49819 integrate help    --%>
<%--01/18/2011	CShimek	Defect #68905 correct syntax on associate href --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest"%>

<!--OTHER TAG LIBRARIES NEEDED  -->
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />

<title>Common Supervision/manageAssociate/associatesList.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript"
	src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script type="text/javascript">
	function confirmDelete(){
		if (confirm("Are you sure you want to delete the selected Associate(s)")){
			goNav("associateConfirmation.htm?delete")
			return true;
		}else	return false;
	}

	function hideButtons(){
		show("Update", 0);
		show("Delete", 0);
	}
	
	function showButtons(){
		show("Update", 1);
		show("Delete", 1);
	}

	function checkForSingleResult() {
	    var rbs = document.getElementsByName("associateId");
		if (rbs.length == 1){
			rbs[0].checked = true;
			showButtons();
		}	
	}
	
/*function determineUpdate(theForm){
	var livesWithFlag = false;
	var theRadios = theForm.assoc;
	
	for (i=0; i<theRadios.length; i++){
		if (theRadios[i].checked){
			if (theRadios[i].value=="livesWith"){
				livesWithFlag = true;
			}
		}
	}
	if (livesWithFlag){
		goNav('associateCreateUpdate.htm?action=update&period=livesWith')
	}else{
		goNav('associateCreateUpdate.htm?action=update')
	}
}*/
</script>

<!-- Javascript for emulated navigation -->
</head>
<body onload="hideButtons(); checkForSingleResult()">
<html:form action="/handleAssociateDisplayOptions" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload_subfolders/Manage_Associates/Manage_Associates.htm#|2">


	<!-- Begin Pagination Header Tag-->
	<bean:define id="paginationResultsPerPage" type="java.lang.String">
		<bean:message key="pagination.recordsPerPage"></bean:message>
	</bean:define>

	<pg:pager index="center"
		maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
		maxIndexPages="10" export="offset,currentPageNumber=pageNumber"
		scope="request">
		<input type="hidden" name="pager.offset" value="<%= offset %>" />
		<!-- End Pagination header stuff -->

		<table width="98%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td valign="top"><img src="/<msp:webapp/>images/spacer.gif"
					height="5" alt=""></td>
			</tr>
			<tr>
				<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top"><!--tabs start--> <tiles:insert
							page="../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
						</tiles:insert> <!--tabs end--></td>
					</tr>
					<tr>
						<td bgcolor="#6699FF"><img
							src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
					</tr>
				</table>


				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="borderTableBlue">
					<tr>
						<td><img src="/<msp:webapp/>images/spacer.gif" height="5"
							alt=""></td>
					</tr>
					<tr>
						<td valign="top" align="center"><!--HEADER AREA START-->
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td align="center"><!--header start--> <tiles:insert
									page="../common/superviseeHeader.jsp" flush="true"></tiles:insert>
								<!--header end--></td>
							</tr>
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"
									alt=""></td>
							</tr>
						</table>
						<!--HEADER AREA END--> <!--CASELOAD CSCD TABS START-->
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td valign="top"><!--tabs start--> <tiles:insert
									page="../common/caseloadCSCDSubTabs.jsp" flush="true">
									<tiles:put name="tab" value="AssociatesTab" />
								</tiles:insert> <!--tabs end--></td>
							</tr>
							<tr>
								<td bgcolor="#33cc66"><img
									src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
							</tr>
						</table>
						<!--CASELOAD CSCD TABS END-->

						<table width="98%" border="0" cellpadding="0" cellspacing="0"
							class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"
									alt=""></td>
							</tr>
							<tr>
								<td valign="top" align="center"><!-- BEGIN HEADING TABLE -->
								<table width="100%">
									<tr>
										<td align="center" class="header">Associates List</td>
									</tr>
								</table>
								<!-- END HEADING TABLE --> <!-- BEGIN ERROR TABLE -->
								<table width="98%" border="0" align="center">
									<tr>
										<td align="center" class="errorAlert"><html:errors></html:errors></td>
									</tr>
								</table>
								<!-- END ERROR TABLE --> <!-- BEGIN INSTRUCTION TABLE -->
								<table width="98%" border="0" cellpadding="2" cellspacing="1"
									align="center">
									<tr>
										<td colspan="2">
										<ul>
											<li>Select an associate and the appropriate button
											below, or Click Add Associate to add a new one.</li>
										</ul>
										</td>
									</tr>
								</table>
								<!-- END INSTRUCTION TABLE -->
								<table width="98%" border="0" cellpadding="2" cellspacing="1"
									class="borderTableBlue">
									<tr class="detailHead">
										<td width="1%"></td>
										<td width="1%"></td>
										<td class="subhead">Name</td>
										<td class="subhead">Relationship</td>
										<td class="subhead">Home Phone</td>
										<td class="subhead">Cell Phone</td>
										<td class="subhead">Work Phone</td>
									</tr>

									<% int RecordCounter=0;
								   String bgcolor="";
								%>
									<logic:iterate id="assocIndex" name="associateForm"
										property="associatesList">
										<!-- Begin Pagination item wrap -->
										<pg:item>
											<tr
												class='<% RecordCounter++; 
						              bgcolor = "alternateRow";                      
						              if (RecordCounter % 2 == 1)
						                  bgcolor = "normalRow";
					                   out.print(bgcolor); %>'>
												<td><input type="radio" name="associateId"
													value='<bean:write name="assocIndex" property="associateId" />'
													onclick="showButtons();" /></td>
												<td><logic:equal name="assocIndex" property="status"
													value="true">
													<img src="/<msp:webapp/>images/thumbs_up.gif" hspace="0"
														title="This Associate has a Good Status" alt="" />
												</logic:equal> <logic:equal name="assocIndex" property="status"
													value="false">
													<img src="/<msp:webapp/>images/thumbs_down.gif" hspace="0"
														title="This Associate has a Bad Status" alt="" />
												</logic:equal></td>
												<td>
													<a href='javascript:changeFormActionURL(document.forms[0],
													"/<msp:webapp/>handleAssociateDisplayOptions.do?submitAction=View&amp;associateId=<bean:write name='assocIndex' property='associateId' />",true)'>
													<bean:write name="assocIndex" property="assocFormattedName" /></a>
												</td>
												<td><bean:write name="assocIndex"
													property="relationship" /></td>
												<td><bean:write name="assocIndex"
													property="homePhone.formattedPhoneNumber" /></td>
												<td><bean:write name="assocIndex"
													property="cellPhone.formattedPhoneNumber" /></td>
												<td><bean:write name="assocIndex"
													property="workPhone.formattedPhoneNumber" /></td>
											</tr>
										</pg:item>
										<!-- End Pagination item wrap -->
									</logic:iterate>
								</table>

								<table width="98%" cellpadding="0" cellspacing="0">
									<tr>
										<td class="legendSmallText"><img
											src="/<msp:webapp/>images/thumbs_up.gif" hspace="0"
											title="This Associate has a Good Status" alt="">
										Represents Associates with a Good Status&nbsp; <img
											src="/<msp:webapp/>images/thumbs_down.gif" hspace="0"
											title="This Associate has a Bad Status" alt="">
										Represents Associates with a Bad Status</td>
									</tr>
								</table>

								<!-- BEGIN PAGINATION NAVIGATION ROW -->
								<table align="center">
									<tr>
										<td><pg:index>
											<tiles:insert page="/jsp/jimsPagination.jsp" flush="false">
												<tiles:put name="pagerUniqueName" value="pagerSearch" />
												<tiles:put name="resultsPerPage"
													value="<%=paginationResultsPerPage%>" />
											</tiles:insert>
										</pg:index></td>
									</tr>
								</table>
								<!-- END PAGINATION NAVIGATION ROW --> <!-- BEGIN BUTTON TABLE -->
								<table border="0">
									<tr align="center">
										<td id="Update"><html:submit property="submitAction">
											<bean:message key="button.update"></bean:message>
										</html:submit></td>
										<td id="Delete"><html:submit property="submitAction">
											<bean:message key="button.delete"></bean:message>
										</html:submit></td>
										<td><html:submit property="submitAction">
											<bean:message key="button.addAssociate"></bean:message>
										</html:submit></td>
									</tr>
									<tr>
										<td align="center" colspan="3"><html:submit
											property="submitAction">
											<bean:message key="button.back"></bean:message>
										</html:submit> <html:submit property="submitAction">
											<bean:message key="button.cancel"></bean:message>
										</html:submit></td>
									</tr>
								</table>
								<!-- END BUTTON TABLE --></td>
							</tr>
						</table>
						<br>
						</td>
					</tr>
				</table>
				</td>

			</tr>
		</table>
	</pg:pager>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>

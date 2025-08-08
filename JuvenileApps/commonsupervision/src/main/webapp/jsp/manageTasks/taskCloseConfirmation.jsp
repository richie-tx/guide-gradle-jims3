<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/04/2008 Richard Young  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ page import="naming.PDCodeTableConstants"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - manageTasks/task Close Confirmation.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" 	src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/sorttable.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/manageTasksUtil.js"></script>
<script>

function resetForm(theSelect){
	theSelect.selectedIndex = 0
	checkWG(theSelect);
}


</script>
</head>
<body onKeyDown="checkEnterKeyAndSubmit(event,true)">
	<html:form action="/handleTaskSearch" target="content">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Tasks/CSCD_Tasks.htm#|12">
	<div align="center">
<!--  BEGIN MAIN PAGE TABLE -->	
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">  
<!--  BEGIN TABS TABLE -->			
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td valign=top>
						<!-- TABS START -->
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tab" value="tasksTab" />
							</tiles:insert>
						<!-- TABS END -->
						</td>
					</tr>
					<tr>
						<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
					</tr>
				</table>

<!--  END TABS TABLE -->						
<!--  BEGIN BLUE BORDER TABLE -->			
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>
						<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
					</tr>
					<tr>
						<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
							<table width="100%">
								<tr>
									<td align="center" class="header"><bean:message key="title.CSCD" /> - &nbsp;Close Task Confirmation</td>
								</tr>
							</table>
<!-- END HEADING TABLE --> 
<!-- BEGIN ERROR TABLE -->
							<table width="98%" align="center">
								<tr>
									<td align="center" class="errorAlert"><html:errors /></td>
								</tr>
							</table>
<!-- END ERROR TABLE --> 
<!-- BEGIN INSTRUCTION TABLE -->
							
<!-- END INSTRUCTION TABLE --> 
							
<!-- BEGIN SELECT SEARCH TABLE -->
							<!--begin confirmation table--> 
                    <table width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue> 
                      <tr> 
                        <td> <br> 
                          <div class=confirm>Task successfully closed </div> 
                          <br> </td> 
                      </tr> 
                    </table> 
                    <!--end confirmation table--> 
										<br>
										<!-- BEGIN BUTTON TABLE -->
										<table border="0" width="100%">
											<tr>
												<td align="center">
												<html:submit property="submitAction"><bean:message key="button.backToTaskSearchResults"></bean:message>
												</html:submit> 
											</td>
											</tr>
										</table>
										<!-- END BUTTON TABLE -->
<!-- END SELECT SEARCH TABLE --> 
							<br>

						</td>
					</tr>
				</table>
<!--  END BLUE BORDER TABLE -->				
			</td>
		</tr>
	</table>
<!--  END MAIN PAGE TABLE -->	
	<br>
	</div>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
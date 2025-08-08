<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ page import="naming.Features"%>
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" />- adminStaff/positionConfirmation.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)"> 
<html:form action="/submitPositionUpdate" target="content">
<div align="center"> 
  <table width="98%" border="0" cellpadding="0" cellspacing="0"> 
    <tr> 
      <td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td> 
    </tr> 
    <tr> 
      <td valign="top"> <table width="100%" border="0" cellpadding="0" cellspacing="0"> 
          <tr> 
            <td valign="top"><!--tabs start--> 
              <tiles:insert
						page="../common/commonSupervisionTabs.jsp" flush="true"> <tiles:put name="tab" value="setupTab" /> </tiles:insert> 
              <!--tabs end--></td> 
          </tr> 
          <tr> 
            <td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif"
						height="5"></td> 
          </tr> 
        </table> 
        <table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="borderTableBlue"> 
          <tr> 
            <td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td> 
          </tr> 
          <tr> 
            <td valign="top" align="center"> <table width="98%" border="0" cellpadding="0" cellspacing="0"> 
                <tr> 
                  <td valign="top"><!--tabs start--> 
                    <tiles:insert
								page="../common/manageFeaturesTabs.jsp" flush="true"> <tiles:put name="tabid" value="positionsTab" /> </tiles:insert> 
                    <!--tabs end--></td> 
                </tr> 
              </table> 
              <table width="98%" border="0" cellpadding="0" cellspacing="0"
						class="borderTableGreen"> 
                <tr> 
                  <td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td> 
                </tr> 
                <tr> 
                  <td valign="top" align="center">
					<!-- BEGIN HEADING TABLE --> 
					<table width="100%">
								<tr>
									<td align="center" class="header"><bean:message
										key="title.CSCD" /> - 
										<logic:equal name="adminStaffForm" property="action" value="create">
										<bean:message key="prompt.create" />
										<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|3">  
										</logic:equal>
										<logic:equal name="adminStaffForm" property="action" value="update">
										<bean:message key="prompt.update" />
										<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|27"> 
										</logic:equal>
										<logic:equal name="adminStaffForm" property="action" value="vacate">
										<bean:message key="prompt.vacate" />
										<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|19"> 
										</logic:equal>
										<logic:equal name="adminStaffForm" property="action" value="assign">
											Assign
											<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|14"> 
											</logic:equal>
											<logic:equal name="adminStaffForm" property="action" value="reassign">
											Reassign
											<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|21">
											</logic:equal>
											<logic:equal name="adminStaffForm" property="action" value="retire">
											Retire
											<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|28">
											</logic:equal>
											<bean:message key="prompt.position" /> 
											<bean:message key="prompt.confirmation" />
									</td>
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
                    <!--begin confirmation table--> 
                    <table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue"> 
                      <tr> 
                        <td> <br> 
                          <div class="confirm">Position <bean:write name="adminStaffForm" property="position.probOfficerInd"/> , <bean:write name="adminStaffForm" property="position.positionName"/> successfully 
                           <logic:equal name="adminStaffForm" property="action" value="create"> created.
                           </logic:equal>
                           <logic:equal name="adminStaffForm" property="action" value="update"> updated.                            
                           </logic:equal>
                           <logic:equal name="adminStaffForm" property="action" value="retire"> retired.                            
                           </logic:equal>
                           <logic:equal name="adminStaffForm" property="action" value="vacate"> vacated.                           
                           </logic:equal>
                           <logic:equal name="adminStaffForm" property="action" value="reassign"> reassigned.                            
                           </logic:equal>
                           <logic:equal name="adminStaffForm" property="action" value="assign"> assigned.                            
                           </logic:equal> </div> 
                          <br> </td> 
                      </tr> 
                    </table> 
                    <!--end confirmation table--> 
                    <br> 
                    <!-- BEGIN BUTTON TABLE --> 
                    <table border="0" width="100%"> 
                      <tr> 
                        <td align="center"> <html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.backToPositionSearch" /></html:submit> </td> 
                      </tr> 
                    </table> 
                    <!-- END BUTTON TABLE --> </td> 
                </tr> 
              </table> 
              <br> </td> 
          </tr> 
        </table></td> 
    </tr> 
  </table> 
</div> 
</html:form> 
<div align="center"><script type="text/javascript">renderBackToTop()</script></div> 
</body>
</html:html>
<!DOCTYPE HTML>

<%------------------MODIFICATIONS ----------------------------%>
<%-- User selects the "Rules" tab on Casefile Details page after searching for a casefile --%>
<%-- 11/10/2005	 Leslie Deen	Create JSP --%>
<%-- 08/21/2006  HRodriguez     Add calendar icon & implement new UI Guidelines --%>
<%-- 06/28/2007  Leslie Deen    Make PT & JSP match re Defect #42784 --%>
<%-- 08/06/2015 RYoung          #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

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
<msp:nocache />
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>

<title><bean:message key="title.heading" /> - ruleDetails.jsp</title>

<script type="text/javascript">
function changeActionFormURL(myForm, URL, doSubmit)
{
  myForm.action = URL;

  if(doSubmit)
    myForm.submit();
}
</script>

</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayCasefileSupervisionRuleList.do" target="content">

<logic:notEqual name="action" value="edit">
  <logic:notEqual name="action" value="summary">
     <logic:notEqual name="action" value="confirmation">
        <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|109">
     </logic:notEqual>
  </logic:notEqual>
</logic:notEqual>
<logic:equal name="action" value="edit">
   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|116">
</logic:equal>
<logic:equal name="action" value="summary">
   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|118">
</logic:equal>
<logic:equal name="action" value="confirmation">
   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|117">
</logic:equal>        

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework" /> - <bean:message key="title.casefile" />&nbsp;<bean:message key="prompt.rule" />

			<logic:notEqual name="action" value="edit">
         <logic:notEqual name="action" value="summary">
            <logic:notEqual name="action" value="confirmation">
			         <bean:message key="title.details" />
			      </logic:notEqual>
			   </logic:notEqual>
			</logic:notEqual>

			<logic:equal name="action" value="edit">
			   <bean:message key="prompt.completion" />
			   <bean:message key="prompt.status" />
			   <bean:message key="title.update" />
			</logic:equal>

			<logic:equal name="action" value="summary">
			   <bean:message key="prompt.completion" />&nbsp;
			   <bean:message key="prompt.status" />&nbsp;
			   <bean:message key="title.update" />
			   <bean:message key="title.summary" />
			</logic:equal>

			<logic:equal name="action" value="confirmation">
			   <bean:message key="prompt.completion" />&nbsp;
			   <bean:message key="prompt.status" />&nbsp;
			   <bean:message key="title.update" />
			   <bean:message key="title.confirmation" />
			</logic:equal>      
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
		<td class="confirm">
      <logic:equal name="action" value="confirmation">Supervision Rule Completion Status has been updated.</logic:equal>
		</td>
	</tr>
	<tr>
		<td class="bodyText">
      <logic:equal name="action" value="summary">
				<ul>
					<li>Verify that information is correct and select Finish button to modify Completion Status.</li>
					<li>If any changes are needed, select Back button.</li>
				</ul>
			</logic:equal>
		</td>
	</tr>		
	<tr>
		<td class="bodyText">
		   <logic:notEqual name="action" value="edit">
         <logic:notEqual name="action" value="summary">
            <logic:notEqual name="action" value="confirmation">
							<ul>
								<li>Click Goal hyperlink to view Associated Goal details.</li>
								<li>Click red Edit hyperlink to edit Status Change Date.</li>
								<li>Select Assign New Rules button to assign a new rule.</li>
								<li>Select Back button to return to Rules list.</li>
							</ul>
	          </logic:notEqual>
			   </logic:notEqual>
			</logic:notEqual>
		</td>
	</tr>

	<logic:equal name="action" value="edit">
    <tr>
      <td class="required"><bean:message key="prompt.dateFieldsInstruction" /></td>
    </tr>
	</logic:equal>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN CASEFILE HEADER TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="casefileheader" />
</tiles:insert> 
<%-- END CASEFILE HEADER TABLE --%>
	
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
  	<td valign="top">
      <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
        <tiles:put name="tabid" value="rulestab" />
        <tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
      </tiles:insert> 
			<%--tabs end--%>

  		<%-- BEGIN DETAIL TABLE --%>
  		<tiles:insert page="../caseworkCommon/ruleDetailsTile.jsp" flush="true">
  			<tiles:put name="supervisionRulesForm" value="supervisionRulesForm" />
  		</tiles:insert>					
  		<%-- END RULES TABLE --%>
  
    </td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>
<%-- END BLUE TABS TABLE --%>


<div class='spacer'></div>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</html:form>
</body>
</html:html>


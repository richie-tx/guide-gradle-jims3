<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- Dwilliamson  10/26/2010	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - riskQuestionSearchSummary.jsp</title>

<%-- STRUTS VALIDATION --%>
<%--html:javascript formName="riskQuestionsSearchForm" /--%>
<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>

</head>
<body topmargin="0" leftmargin="0">
<html:form action="/submitRiskCreateQuestion" target="content">
<input type="hidden" name="helpFile" value="">
  <!-- BEGIN HEADING TABLE -->
  <table width="100%">
    <tr>
      <td align="center" class="header" ><bean:message key="title.riskQuestions"/> - <bean:message key="title.create"/>
         <logic:equal name="juvenileContactForm" property="secondaryAction" value="summary">			
   			 Summary  				
	     </logic:equal>
		
	     <logic:equal name="juvenileContactForm" property="secondaryAction" value="confirm">			
   			 Confirmation 
   			 
   			 <logic:equal name="juvenileContactForm" property="action" value="create">	
  				<tr align="center"><td class="confirm">Question and/or Answer(s) successfully added.</td></tr>
			 </logic:equal>

			 <logic:notEqual name="juvenileContactForm" property="action" value="create">	
  				<tr align="center"><td class="confirm">Question and/or Answer(s) successfully updated.</td></tr>
			 </logic:notEqual>
	     </logic:equal>
	  </td>   
    </tr>
  </table>
  <!-- END HEADING TABLE -->
  <br>
  <!-- BEGIN INSTRUCTION TABLE -->
  <table width="98%" border="0">
    <tr>
      <td><ul>
          <li>Select Back button to return to previous page.</li>        
          <li>Select Finish button to save Risk Answer(s).</li>
        </ul></td>
    </tr>
    <tr>
      <td align="center"></td>
    </tr>
  </table>
  <!-- END INSTRUCTION TABLE -->
  
  
  <br>
  <!-- BEGIN QUESTION TABLE -->
  <table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
    <tr>
      <td class="detailHead"><table width="100%" cellpadding="2" cellspacing="0">
          <tr>
            <td class="detailHead">Question</td>
          </tr>
        </table></td>
    </tr>
    <tr>
      <td>		
						 
		<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
          <tr class="detailHead">
            <td align="left" colspan="4">Question Information</td>
          </tr>
          <tr>
            <td width="15%" class="formDeLabel">Question Name</td>
            <td width="35%" class="formDe"><bean:write name="riskQuestionForm" property="questionName"/></td>
            <td width="15%" class="formDeLabel"><bean:message key="prompt.dateTime"/></td>
            <td width="35%" class="formDe"><bean:write name="riskQuestionForm" property="questionEntryDate" formatKey="datetime.format.mmddyyyyHHmm" /></td>
          </tr>
          <tr>
            <td width="15%" class="formDeLabel">Question Text</td>
            <td width="35%" class="formDe" colspan="3"><bean:write name="riskQuestionForm" property="questionText"/></td>
          </tr>
          <tr>
            <td width="15%" class="formDeLabel">UI Control Type</td>
            <td width="35%" class="formDe" colspan="3"><bean:write name="riskQuestionForm" property="UIControlType"/></td>                        
          </tr>
          <tr>
            <td width="15%" class="formDeLabel">Collapsible Header</td>
            <td width="35%" class="formDe" colspan="3"><bean:write name="riskQuestionForm" property="collapsibleHeaderInd"/></td>
          </tr>
          <tr>
            <td width="15%" class="formDeLabel">UI Display Order</td>
            <td width="35%" class="formDe"><bean:write name="riskQuestionForm" property="uiDisplayOrder"/></td>
            <td width="15%" class="formDeLabel">Allow Future Dates?</td>
            <td width="35%" class="formDe"><bean:write name="riskQuestionForm" property="allowFutureDates"/></td>            
          </tr>          
          <tr>
            <td width="15%" class="formDeLabel">Numeric Only?</td>
            <td width="35%" class="formDe" colspan="1"><bean:write name="riskQuestionForm" property="numericOnly"/></td>
			<td width="15%" class="formDeLabel">Question Hardcoded</td>
            <td width="35%" class="formDe" colspan="1"><bean:write name="riskQuestionForm" property="questionHardcoded"/></td>
          </tr>
          <tr>
            <td width="15%" class="formDeLabel">Initial Action</td>
            <td width="35%" class="formDe"><bean:write name="riskQuestionForm" property="questionInitialAction"/></td>
            <td width="15%" class="formDeLabel">Required?</td>
            <td width="35%" class="formDe"><bean:write name="riskQuestionForm" property="requiredInd"/></td>            
          </tr>
          <tr>
            <td width="15%" class="formDeLabel">Control Code</td>
            <td width="35%" class="formDe"><bean:write name="riskQuestionForm" property="controlCode"/></td>
            <td width="15%" class="formDeLabel">Answer Source</td>
            <td width="35%" class="formDe"><bean:write name="riskQuestionForm" property="answerSource"/></td>			
          </tr>
        </table>
				
		</td>
    </tr>
  </table>
  <!-- END QUESTION TABLE -->
  
    <br>
	
	<!-- BEGIN ANSWERS TABLE --> 
        <br> 
      <table width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue> 
        <tr> 
          <td valign=top class=detailHead colspan=4>Answers for Question</td> 
        </tr> 
        <tr> 
          <td colspan=4 class=bodyText> <!-- Begin Inner Table --> 
      <logic:iterate id="answerIndex" name="riskQuestionForm" property="answerList">
          <table width='100%' cellspacing=1 class="borderTableGrey"> 
            <tr>
               <td class="formDeLabel" width="1%"><bean:message key="prompt.dateTime"/></td>
               <td colspan="3" class="formDe"><bean:write name="answerIndex" property="answerEntryDate" formatKey="datetime.format.mmddyyyyHHmm" /></td>
            </tr>
            <tr>  
               <td class="formDeLabel"nowrap="nowrap">Answer Text</td>
               <td colspan="3" class="formDe"><bean:write name="answerIndex" property="answerText" /></td>
            </tr>
            <tr>  
               <td class="formDeLabel">Weight</td>
               <td class="formDe"><bean:write name="answerIndex" property="answerWeight" /></td>
               <td class="formDeLabel" width="1%" nowrap="nowrap">Subordinate Question</td>
               <td class="formDe"><bean:write name="answerIndex" property="subordinateQuestion" /></td>
            </tr>
            <tr>  
               <td class="formDeLabel">Action</td>
               <td class="formDe"><bean:write name="answerIndex" property="answerAction" /></td>
               <td class="formDeLabel">Sort Order</td>
               <td class="formDe"><bean:write name="answerIndex" property="sortOrder" /></td>            
            </tr>
               <tr class="normalRow" valign="top">
               <td></td>
            </tr>
		  </table>
	</logic:iterate>	  				
			</td> 
        </tr> 
      </table> 
      <!-- END ANSWERS TABLE --> 
			
			<br>
			
  <table width="100%">
	<tr id="submitButtons">
      <td align="center">
        <logic:equal name="juvenileContactForm" property="secondaryAction" value="summary">
          <html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
          <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
          <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
        </logic:equal>
        <logic:equal name="juvenileContactForm" property="secondaryAction" value="confirm">
          <html:submit property="submitAction"><bean:message key="button.questionSearch"/></html:submit>
        </logic:equal>    
      </td>
    </tr>
  </table>

</html:form>
<div align="center"><script type="text/javascript">renderBackToTop();</script></div>
</body>
</html:html>

<!DOCTYPE HTML>
<%-- Dwilliamson  12/09/2010	Create Tile.  This Question tile does not collapse --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>

<title><bean:message key="title.heading" /> - riskAnswersInnerTile.jsp</title>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="">
   <tr>
     <td>
       <!-- Parameters -->
       <tiles:useAttribute id="formName" name="formName"/>
       <tiles:useAttribute id="answerListName" name="answerListName"/>
       <tiles:useAttribute id="showAnswerButtons" name="showAnswerButtons"/>
       <tiles:useAttribute id="showAnswerRadioButtons" name="showAnswerRadioButtons"/> 
       <tiles:useAttribute id="showAnswerNameAsReferenceLinks" name="showAnswerNameAsReferenceLinks"/>
       
       <logic:notEmpty name="${formName}" property="${answerListName}">
       
	       <logic:iterate id="answers" name="${formName}" property="${answerListName}" indexId="indexer">
	       
	       <bean:define id="answerid" value="" type="java.lang.String" />
	       
	       <logic:equal name="showAnswerRadioButtons" value="true">
	       	<bean:define id="answerid" name="answers" property="riskAnswerId" type="java.lang.String" />
	       </logic:equal>
	        
	       <table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
	         <tr>
	          <!-- Logic: Radio Button -->
	         	<logic:equal name="showAnswerRadioButtons" value="true">
	          	<td width="1%" class="formDe">
	          		<nested:radio name="${formName}" property="selectedRiskAnswerId" value="<%=answerid%>" styleId="selectedRiskAnswerId" ></nested:radio>
	          	</td>
	         	</logic:equal>
	         	
	           <td class="formDeLabel" width="10%"><bean:message key="prompt.entryDateTime"/></td>
	           <td colspan="3" class="formDe"><bean:write name="answers" property="answerEntryDate" formatKey="datetime.format.mmddyyyyHHmm" /></td>
	         </tr>
	         <tr>
	           <logic:equal name="showAnswerRadioButtons" value="true">
	           <td class="formDe"></td>  
	           </logic:equal>
	           <td class="formDeLabel"nowrap="nowrap"><bean:message key="prompt.answerText"/></td>
	           <td colspan="3" class="formDe">
	           		<logic:equal name="showAnswerNameAsReferenceLinks" value="true">
		           		<a href='/<msp:webapp/>displayRiskAnswerDetailsSelection.do?submitAction=Link&question.riskQuestionId=<bean:write name="${formName}" property="question.riskQuestionId"/>&currentAnswer.riskAnswerId=<bean:write name="answers" property="riskAnswerId"/>'>
	                    	<bean:write name="answers" property="answerText" />
	                    </a>
	           		</logic:equal>
	           		<logic:notEqual name="showAnswerNameAsReferenceLinks" value="true">
		           		<bean:write name="answers" property="answerText" />
	           		</logic:notEqual>
	           	</td>
	         </tr>
	         <tr>
	         	<logic:equal name="showAnswerRadioButtons" value="true">
	          	<td class="formDe"></td>  
	         	</logic:equal>
	           <td class="formDeLabel"><bean:message key="prompt.weight"/></td>
	           <td class="formDe" width="40%"><bean:write name="answers" property="weight" /></td>
	           <td class="formDeLabel" width="15%" nowrap="nowrap"><bean:message key="prompt.subordinateQuestion"/></td>
	           <td class="formDe"><bean:write name="answers" property="subordinateQuestionName" /></td>
	         </tr>
	         <tr>
	         	<logic:equal name="showAnswerRadioButtons" value="true">
	          	<td class="formDe"></td>  
	         	</logic:equal>
	           <td class="formDeLabel"><bean:message key="prompt.sortOrder"/></td>
	           <td class="formDe" width="40%"><bean:write name="answers" property="sortOrder" /></td>  
	           <td class="formDeLabel" width="15%"><bean:message key="prompt.action"/></td>
	           <td class="formDe" ><bean:write name="answers" property="action" /></td>
	         </tr>
	         <tr class="normalRow" valign="top">
	           <td></td>
	         </tr>
	       </table>
	       
	       <table>
	       <tr class="normalRow" valign="top">
	           <td></td>
	         </tr>
	       </table>
	       
	       </logic:iterate>
	         
	   </logic:notEmpty>
       
       <script language="JavaScript" type="text/javascript">
       		//For reasons unknown the Answer Radio list was showing up a radio button checked. This unchecks all radio buttons in the list
       		//var radioList = document.getElementsByName("riskAnswerId");
       	 	//for( var x = 0; x < name.length; x++ ) { // Runs through all radio buttons
       	 	//	radioList[x].checked = false; // Unchecks radio button
       	    //}
		</script>
       
     </td>
   </tr>
   
   <logic:equal name="showAnswerButtons" value="true">
    <tr id="submitButtons">
      <td align="center">
        <html:submit property="submitAction"><bean:message key="button.addAnswer" /></html:submit>
        <html:submit property="submitAction" onclick="return validateAnswerIsSelected(this.form)"><bean:message key="button.updateAnswer" /></html:submit>
        <html:submit property="submitAction" onclick="return validateAnswerIsSelected(this.form)" ><bean:message key="button.deleteAnswer" /></html:submit>
      </td>
    </tr>
</logic:equal>
   
</table>
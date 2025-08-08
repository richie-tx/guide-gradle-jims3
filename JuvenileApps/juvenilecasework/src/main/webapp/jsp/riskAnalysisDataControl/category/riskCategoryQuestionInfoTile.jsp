<!DOCTYPE HTML>
<%-- CShimek  02/17/2012	Create Tile.  This is for individual Question details display --%>
<%-- CShimek  04/23/2012	#73272 added hidden field for UIControlType, needed for answer weight display field --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.UIConstants" %>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<title><bean:message key="title.heading" /> - riskCategoryQuestionInfoTile.jsp</title>
    <tiles:useAttribute id="formName" name="formName"/>
<!-- BEGIN QUESTION TABLE -->
		<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
        	<tr class="detailHead">
            	<td colspan="4"><bean:message key="prompt.question"/>&nbsp;<bean:message key="prompt.information"/></td>
          	</tr>
          	<tr>
				<td width="15%" class="formDeLabel"><bean:message key="prompt.questionName"/></td>
				<td width="35%" class="formDe"><bean:write name="${formName}" property="question.questionName" /></td>
				<td width="15%" class="formDeLabel"><bean:message key="prompt.entryDate"/></td>
				<td width="35%" class="formDe">
					<bean:write name="${formName}" property="question.questonEntryDate" formatKey="datetime.format.mmddyyyyHHmm" />
					<html:hidden name="${formName}" property="question.questonEntryDate"/>
				 </td>
			</tr>					
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.questionText"/></td>
				<td class="formDe" colspan="3"><bean:write name="${formName}" property="question.questionText" /></td>
			</tr>
	 		<tr>					
				<td class="formDeLabel"><bean:message key="prompt.UIControlType" /></td>
				<td class="formDe" colspan="3">
					<bean:write name="${formName}" property="question.uiControlType" />
					<input type="hidden" name="controlType" value="<bean:write name="${formName}" property="question.uiControlType" />" id="controlType" />
				</td>
			</tr>	
			<tr id='collapseHeader' class='hidden' >
				<td class="formDeLabel"><bean:message key="prompt.collapsibleHeader" /></td>
				<td class="formDe" colspan="3"><bean:write name="${formName}" property="question.collapsibleHeader" /></td>
			</tr>
         	<logic:equal name="${formName}" property="question.uiControlType" value="QUESTIONHEADER">
				<script type="text/javascript">
					document.getElementById("collapseHeader").className = "visible";
				</script>
			</logic:equal>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.UIDisplayOrder"/></td>
				<td class="formDe"><bean:write name="${formName}" property="question.uiDisplayOrder" /></td>             
				<td class="formDeLabel"><bean:message key="prompt.allowsFutureDates"/></td>
				<td class="formDe">
					<logic:equal name="${formName}" property="question.allowFutureDates" value="false">
						<%=UIConstants.NO_FULL_TEXT%>
					</logic:equal>
					<logic:equal name="${formName}" property="question.allowFutureDates" value="true">
						<%=UIConstants.YES_FULL_TEXT%>
					</logic:equal>
				</td>	 
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.numericOnly"/></td>
				<td class="formDe">
					<logic:equal name="${formName}" property="question.numericOnly" value="false">
						<%=UIConstants.NO_FULL_TEXT%>
					</logic:equal>
					<logic:equal name="${formName}" property="question.numericOnly" value="true">
						<%=UIConstants.YES_FULL_TEXT%>
					</logic:equal>
				</td>	 
				<td class="formDeLabel"><bean:message key="prompt.questionTextNotModifiable"/></td>
				<td class="formDe">
					<logic:equal name="${formName}" property="question.hardcoded" value="false">
						<%=UIConstants.NO_FULL_TEXT%>
					</logic:equal>
					<logic:equal name="${formName}" property="question.hardcoded" value="true">
						<%=UIConstants.YES_FULL_TEXT%>
					</logic:equal>
				</td>	
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.initialAction"/></td>
				<td class="formDe"><bean:write name="${formName}" property="question.questionInitialAction" /></td>   
				<td class="formDeLabel"><bean:message key="prompt.required?"/></td>
				<td class="formDe">
					<logic:equal name="${formName}" property="question.required" value="false">
						<%=UIConstants.NO_FULL_TEXT%>
					</logic:equal>
					<logic:equal name="${formName}" property="question.required" value="true">
						<%=UIConstants.YES_FULL_TEXT%>
					</logic:equal>
				</td>	
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.controlCode" /></td>
				<td class="formDe" colspan="1"><bean:write name="${formName}" property="question.controlCodeName" /></td>
				
				<td width="15%" class="formDeLabel">Allow Print</td>
              <td width="35%" class="formDe">
  				<logic:equal name="${formName}" property="question.allowPrint" value="true">
              		<%=UIConstants.YES_FULL_TEXT%>
              	</logic:equal>
              	<logic:equal name="${formName}" property="question.allowPrint" value="false">
              		<%=UIConstants.NO_FULL_TEXT%>
              	</logic:equal>
  			  </td>
			</td> 
		<%-- 		<td class="formDeLabel"><bean:message key="prompt.answerSource" /></td>
				<td class="formDe"><bean:write name="${formName}" property="question.answerSource" /></td> --%> 
			</tr> 
		</table>
<!-- END QUESTION TABLE -->
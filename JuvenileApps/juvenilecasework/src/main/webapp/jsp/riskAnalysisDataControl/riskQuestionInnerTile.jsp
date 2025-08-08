<!DOCTYPE HTML>
<%-- Dwilliamson  12/09/2010	Create Tile.  This Question tile does not collapse --%>
<%-- CShimek  04/24/2012	#73272 revised Yes/No to constants for consistancy and needed Id to fields for scripting validation --%>

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
<%@ page import="naming.UIConstants" %>
<title><bean:message key="title.heading" /> - riskQuestionInnerTile.jsp</title>

    <tiles:useAttribute id="formName" name="formName"/>
    <tiles:useAttribute id="showQuestionButtons" name="showQuestionButtons"/>
	
	<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
          <tr class="detailHead">
            <td colspan="4"><bean:message key="prompt.question"/>&nbsp;<bean:message key="prompt.information"/></td>
          </tr>
          <tr>
            <td width="15%" class="formDeLabel"><bean:message key="prompt.questionName"/></td>
            <td width="35%" class="formDe"><bean:write name="${formName}" property="question.questionName"/></td>
            <td width="15%" class="formDeLabel"><bean:message key="prompt.entryDateTime"/></td>
            <td width="35%" class="formDe">
            	<bean:write name="${formName}" property="question.questonEntryDate" formatKey="datetime.format.mmddyyyyHHmm" />
            </td>
          </tr>					
          <tr>
            <td width="15%" class="formDeLabel"><bean:message key="prompt.questionText"/></td>
            <td class="formDe" colspan="3"><bean:write name="${formName}" property="question.questionText" /></td>
          </tr>
          <tr>					
			  <td width="15%" class="formDeLabel"><bean:message key="prompt.UIControlType"/></td>
              <td width="35%" class="formDe">
  				<bean:write name="${formName}" property="question.uiControlType"/>
  				<input type="hidden" name="uiCntrlType" value="<bean:write name="${formName}" property="question.uiControlType"/>" id="uiCntrlType" />
  			  </td>
  			  <td width="15%" class="formDeLabel">Default to System Date</td>
              <td width="35%" class="formDe">
  				<logic:equal name="${formName}" property="question.defaultToSystemDate" value="true">
              		<%=UIConstants.YES_FULL_TEXT%>
              	</logic:equal>
              	<logic:equal name="${formName}" property="question.defaultToSystemDate" value="false">
              		<%=UIConstants.NO_FULL_TEXT%>
              	</logic:equal>
  			  </td>
          </tr>
          
           <logic:equal name="${formName}" property="question.uiControlType" value="QUESTIONHEADER">
       	  		<tr id='collapseHeader'> 
       	   </logic:equal>
       	  	<logic:notEqual name="${formName}" property="question.uiControlType" value="QUESTIONHEADER">
       	  		<tr  class='hidden' id='collapseHeader'> 
       	   </logic:notEqual>
            <td width="15%" class="formDeLabel">Collapsible Header</td>
             <td class="formDe" colspan="3">
             	<logic:equal name="${formName}" property="question.collapsibleHeader" value="true">
              		<%=UIConstants.YES_FULL_TEXT%>
              	</logic:equal>
              	<logic:equal name="${formName}" property="question.collapsibleHeader" value="false">
              		<%=UIConstants.NO_FULL_TEXT%>
              	</logic:equal>
             </td>
          </tr>
          
          <tr>
			<td width="15%" class="formDeLabel"><bean:message key="prompt.UIDisplayOrder"/></td>
            <td width="35%" class="formDe">
            	<bean:write name="${formName}" property="question.uiDisplayOrder"/>
            </td>             
			<td width="15%" class="formDeLabel"><bean:message key="prompt.allowsFutureDates"/></td>
            <td width="35%" class="formDe">
            	<logic:equal name="${formName}" property="question.allowFutureDates" value="true">
              		<%=UIConstants.YES_FULL_TEXT%>
              	</logic:equal>
              	<logic:equal name="${formName}" property="question.allowFutureDates" value="false">
              		<%=UIConstants.NO_FULL_TEXT%>
              	</logic:equal>
			</td>	 
          </tr>
          <tr>
            <td width="15%" class="formDeLabel"><bean:message key="prompt.numericOnly"/></td>
            <td width="35%" class="formDe">
              	<logic:equal name="${formName}" property="question.numericOnly" value="true">
              		<%=UIConstants.YES_FULL_TEXT%>
              	</logic:equal>
              	<logic:equal name="${formName}" property="question.numericOnly" value="false">
              		<%=UIConstants.NO_FULL_TEXT%>
              	</logic:equal>
			</td>
            <td width="15%" class="formDeLabel"><bean:message key="prompt.questionTextNotModifiable"/></td>
            <td width="35%" class="formDe">
            	<logic:equal name="${formName}" property="question.hardcoded" value="true">
              		<%=UIConstants.YES_FULL_TEXT%>
              	</logic:equal>
              	<logic:equal name="${formName}" property="question.hardcoded" value="false">
              		<%=UIConstants.NO_FULL_TEXT%>
              	</logic:equal>
            </td>	
          </tr>
          <tr>
            <td width="15%" class="formDeLabel"><bean:message key="prompt.initialAction"/></td>
            <td width="35%" class="formDe">
              <bean:write name="${formName}" property="question.questionInitialAction"/>
			</td>   
	        <td width="15%" class="formDeLabel"><bean:message key="prompt.required?"/></td>
            <td width="35%" class="formDe">
              	<logic:equal name="${formName}" property="question.required" value="true">
              		<%=UIConstants.YES_FULL_TEXT%>
              	</logic:equal>
              	<logic:equal name="${formName}" property="question.required" value="false">
              		<%=UIConstants.NO_FULL_TEXT%>
              	</logic:equal>
			</td>
          </tr>
		  <tr>
			<td width="15%" class="formDeLabel"><bean:message key="prompt.controlCode"/></td>
            <td width="35%" class="formDe">
            	<bean:write name="${formName}" property="question.controlCodeName"/>
            </td>
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
          </tr>
          
        </table>
        
        <logic:equal name="showQuestionButtons" value="true">
        <tr id="submitButtons">
	      <td align="center">
	      	<html:submit property="submitAction"><bean:message key="button.updateQuestion" /></html:submit>
	        <html:submit property="submitAction"><bean:message key="button.deleteQuestion" /></html:submit>
	      </td>
	    </tr>
	    </logic:equal>
        
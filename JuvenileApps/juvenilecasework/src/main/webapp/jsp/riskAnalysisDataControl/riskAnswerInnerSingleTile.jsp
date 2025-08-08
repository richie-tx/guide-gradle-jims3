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

<title><bean:message key="title.heading" /> - riskAnswerInnerSingleTile.jsp</title>

<tiles:useAttribute id="formName" name="formName"/>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="">
   <tr>
     <td>
       
        
       <table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
         <tr>
           <td class="formDeLabel" width="10%"><bean:message key="prompt.entryDateTime"/></td>
           <td colspan="3" class="formDe"><bean:write name="${formName}" property="currentAnswer.answerEntryDate" formatKey="datetime.format.mmddyyyyHHmm" /></td>
         </tr>
         <tr>
           <td class="formDeLabel"nowrap="nowrap"><bean:message key="prompt.answerText"/></td>
           <td colspan="3" class="formDe"><bean:write name="${formName}" property="currentAnswer.answerText" /></td>
         </tr>
         <tr>
           <td class="formDeLabel"><bean:message key="prompt.weight"/></td>
           <td class="formDe" width="40%"><bean:write name="${formName}" property="currentAnswer.weight" /></td>
           <td class="formDeLabel" width="15%" nowrap="nowrap"><bean:message key="prompt.subordinateQuestion"/></td>
           <td class="formDe"><bean:write name="${formName}" property="currentAnswer.subordinateQuestionName" /></td>
         </tr>
         <tr>
           <td class="formDeLabel"><bean:message key="prompt.sortOrder"/></td>
           <td class="formDe" width="40%"><bean:write name="${formName}" property="currentAnswer.sortOrder" /></td>  
           <td class="formDeLabel" width="15%"><bean:message key="prompt.action"/></td>
           <td class="formDe" ><bean:write name="${formName}" property="currentAnswer.action" /></td>
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
       
       
     </td>
   </tr>
   
 
   
</table>
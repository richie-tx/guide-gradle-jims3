<!DOCTYPE HTML>
<%-- Dwilliamson  12/09/2010	Create Tile.  This Question tile collapses --%>

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

<title><bean:message key="title.heading" /> - riskAnswerTile.jsp</title>

<!-- Tile Parameters -->
<tiles:useAttribute id="selfDirectLink" name="selfDirectLink"/>
<tiles:useAttribute id="formName" name="formName"/>

<!-- BEGIN ANSWERS TABLE --> 
  <table width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue> 
   <tr> 
      <td valign=top class=detailHead colspan=4>Add Answers for Question</td> 
   </tr> 
   <tr> 
      <td colspan=4 class=bodyText> <!-- Begin Inner Table -->
        
        <!-- BEGIN ADD SINGLE ANSWERS TABLE -->
        
	    <tiles:insert page="riskAnswerEditInnerTile.jsp" flush="true">
    		<tiles:put name="formName" type="String" value="${formName}" />
    	</tiles:insert>
	    <%-- END ADD SINGLE ANSWERS TABLE --%>
	 	
	 	<br>
	 	<div class="paddedFourPix" align="center">
	 	 	<!--  html:submit property="submitAction" onclick="return validateAddressFields(this.form)" -->
			<html:submit property="submitAction" onclick="return validateAnswerFields(this.form)">
				<bean:message key="button.addToList"/>
			</html:submit> 
		</div>
		<br>
		
        <logic:notEmpty name="${formName}" property="newAnswerList">
			<table width="100%" cellpadding="2" cellspacing="0" border="0" class="borderTableGrey">
	          <tr class="detailHead">
	            <td align="left" colspan="4">
	            	Answers To Be Added
	            	<input type="hidden" name="answersToBeAddedList" value="" id="tobeAddedList" />
	            </td>
	          </tr>
	          <tr>
	          	<td>
	              <table width="100%" bgcolor="#999999" cellspacing="1">
		              <tr bgcolor="#cccccc">
		                    <td class="subhead" valign="top" width="1%"><bean:message key="prompt.remove"/></td>
		                    <td class="subhead" valign=top width=35%>Answer Text</td> 
				            <td class="subhead" valign=top width=5%><bean:message key="prompt.weight"/></td> 
				            <td class="subhead" valign=top width=25%>Subordinate Question</td> 
				            <td class="subhead" valign=top width=15%><bean:message key="prompt.action"/></td>
				            <td class="subhead" valign=top width=5%>Sort Order</td> 
				            <td class="subhead" valign=top width=15%><bean:message key="prompt.dateTime"/></td>  
		              </tr>
	                  <logic:iterate id="answers" name="${formName}" property="newAnswerList" indexId="index">
		                  <tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
		                      
		                      <td class=formDe valign="top" align="center" width="1%">                               
		                      	<a href="/<msp:webapp/>${selfDirectLink}.do?submitAction=Remove&selectedValue=<%= index.intValue()%>">
		                        	<bean:message key="prompt.remove"/>
		                        </a>
		                      </td>
		                      <td class=formDe><bean:write name="answers" property="answerText" /></td>
							  <td class=formDe><bean:write name="answers" property="weight" /></td>
							  <td class=formDe><bean:write name="answers" property="subordinateQuestionName" /></td>
							  <td class=formDe><bean:write name="answers" property="action" /></td>
							  <td class=formDe><bean:write name="answers" property="sortOrder" /></td>
							  <td class=formDe><bean:write name="answers" property="answerEntryDate" formatKey="datetime.format.mmddyyyyHHmm" /></td>
		                     
		                  </tr>
	                  </logic:iterate>
	              </table>
      			</td> 
    		</tr>	
  		</table> 
      </logic:notEmpty> 
             
        
                
      </td> 
    </tr>	
  </table> 
  <!-- END ANSWERS TABLE --> 
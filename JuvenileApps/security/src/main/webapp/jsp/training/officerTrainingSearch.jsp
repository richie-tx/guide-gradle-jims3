<!-- Added for user story 11056 -->

<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<tiles:importAttribute name="officerForm"/>  

</br>
 <table width="100%" border="0" cellpadding="2" cellspacing="1" align="center"> 
          <tr> 
            <td class="detailHead" colspan="4" >&nbsp;TSD Training</td> 
          </tr> 
          <logic:notEmpty name="officerForm" property="officerTraining">
          <tr> 
            <td class="formDeLabel"><bean:message key="prompt.trainingTopic"/></td> 
            <td class="formDeLabel"><bean:message key="prompt.trainingBegDate"/></td> 
            <td class="formDeLabel"><bean:message key="prompt.trainingEndDate"/></td> 
            <td class="formDeLabel"><bean:message key="prompt.trainingHours"/></td> 
          </tr>
        <tr>  
		<logic:iterate id="trainingIndex" name="officerForm"
				property="officerTraining" indexId="index">
				<pg:item>
					<tr height="100%"
						class="<%out.print((index.intValue() % 2 == 1)
						? "alternateRow": "normalRow");%>">
						<td><bean:write name="trainingIndex"
								property="trainingTopicCd" />
						</td>
						<td><bean:write name="trainingIndex"
								property="trainingBeginDate" formatKey="date.format.mmddyyyy"/>
						</td>
						<td><bean:write name="trainingIndex"
								property="trainingEndDate" formatKey="date.format.mmddyyyy"/>
						</td>
						<td><bean:write name="trainingIndex"
								property="trainingHours" />
						</td>
					</tr>
				</pg:item>
			</logic:iterate>
		</tr>
		</logic:notEmpty>
  </table> 
   <span id="trainingSpan" class="hidden">
  	<table width="100%" border="0" cellpadding="2" cellspacing="1" align="center"> 
 	  <tr> 
            <td class="detailHead" colspan="4" >&nbsp;TSD Training Entry</td> 
      </tr>
   	  <tr> 
       <td class="formDeLabel" width="10%" ><bean:message key="prompt.trainingTopic"/></td>
      <td class='formDe'> 
	   <html:select name="officerForm" tabindex="0" property="trainingTopics" styleId="topicId">
			<html:option key="select.generic" value="" />
			<html:optionsCollection property="trainingTopics" name="officerForm" value="trainingTopicId" label="description"/> 				
		</html:select>
	</td>
     </tr>     
     <tr>
     	<td class="formDeLabel" width="10%" ><bean:message key="prompt.trainingBegDate"/></td>
     	 <td class="formDe" colspan="3"> <!-- BEGIN INNER NAME TABLE  -->
            <html:text name="officerForm" styleId="beginDate" property="trainingBegDate" size="10" maxlength="10"/>
	  </td> 
     </tr>
     <tr>
     	<td class="formDeLabel" width="10%" ><bean:message key="prompt.trainingEndDate"/></td>
     	<td class="formDe" colspan="3"> <!-- BEGIN INNER NAME TABLE  -->
            <html:text name="officerForm" property="trainingEndDate" styleId="endDate" size="10" maxlength="10"/>
	  </td>  
     </tr>       
     <tr>
     	<td class="formDeLabel" width="10%" ><bean:message key="prompt.trainingHours"/></td> 
     	<td class="formDe" colspan="3"> <!-- BEGIN INNER NAME TABLE  -->
            <html:text name="officerForm" property="trainingHours" styleId="trainingHrs" size="5" maxlength="5"/>
	  </td> 
     </tr>        
    </table>
    </span>
  <!-- BEGIN BUTTON TABLE --> 
 <table width="100%"> 
    <tr>
    	<td align="center">
    		<html:button property="submitAction" styleId="openCreateBtn"> <bean:message key="button.addMore" /></html:button>
	   </td>
	   <td>
	    	<html:submit property="submitAction" styleId="addToCartBtn"> <bean:message key="button.addTraining" /></html:submit>
	   </td>
   </tr>
   </table>
    
 </pg:pager>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@page import="naming.UIConstants"%>
<%@page import="naming.PDCodeTableConstants"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>


<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>


<tiles:importAttribute name="csCalendarOtherForm"/>
<tiles:useAttribute id="attrPrefix" name="attrPrefix" classname="java.lang.String" ignore="true" />
<logic:notEmpty name="csCalendarOtherForm" property="selectedEvents">
<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td class="detailHead">				
				<bean:write name="csCalendarOtherForm" property="eventDate" formatKey="date.format.mmddyyyy"/> Events Information</td>
	</tr>
	<tr>
		<td>
			<table width='100%' cellpadding="2" cellspacing="1">
			<%int i = 0;%>
			<logic:iterate indexId="eventsCount" id="eventsIter" name="csCalendarOtherForm" property="selectedEvents">
				<%i++;%>
				<tr>
					<td class="formDeLabel" nowrap width=1%>					
						Event Type
					</td>
					<td class="formDe">
					<bean:write name="eventsIter" property="eventTypeDesc"/>				
					</td>
					<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.eventName"/></td>
					<td class="formDe">					
							<bean:write name="eventsIter" property="eventName"/>					
					</td>
				</tr>
				<logic:notEqual name="csCalendarOtherForm" property="context" value="S">
				<logic:notEmpty name="eventsIter" property="partyEvent">
				<tr>
										
			      <td class="formDeLabel" nowrap width=1%><bean:message key="prompt.supervisee"/>
			      </td>
			      <td class="formDe">	
				   <bean:write name="eventsIter" property="partyEvent.lastName"/>, 
				    <bean:write name="eventsIter" property="partyEvent.middleName"/> 
					<bean:write name="eventsIter" property="partyEvent.firstName"/>
					</td>
					<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.SPN"/></td>
					<td class="formDe">	
					<bean:write name='eventsIter' property='superviseeId'/>
					</td>
				</tr>							
				</logic:notEmpty>	
				</logic:notEqual>
				<tr>
				  <td class="formDeLabel" nowrap>Start Time</td>
				   <logic:notEqual name="csCalendarOtherForm" property="activityFlow" value="summary">
					<td class="formDe"><input type="text" name="<bean:write name='attrPrefix'/>[<bean:write name="eventsCount"/>].startTime1" value="<bean:write name='eventsIter' property='startTime1'/>" id="startTime1" maxlength="5" size="6" >
				    <select name="<bean:write name='attrPrefix'/>[<bean:write name="eventsCount"/>].AMPMId1" value="<bean:write name='eventsIter' property='AMPMId1'/>" >
                     <option value="AM">AM</option>
                     <option value="PM" <logic:equal name="eventsIter" property="AMPMId1" value="PM">selected</logic:equal>>PM</option>
                   </select>    
                   </td>                
                   </logic:notEqual>
                   <logic:equal name="csCalendarOtherForm" property="activityFlow" value="summary">
                    <td class="formDe"><bean:write name="eventsIter" property="startTime1"/> <logic:notEmpty name="eventsIter" property="startTime1"><bean:write name="eventsIter" property="AMPMId1"/></logic:notEmpty>
				    </td> 
				    </logic:equal>
				<td class="formDeLabel" nowrap>End Time</td>
				 <logic:notEqual name="csCalendarOtherForm" property="activityFlow" value="summary">
				  <td class="formDe"><input type="text" name="<bean:write name='attrPrefix'/>[<bean:write name="eventsCount"/>].endTime1" value="<bean:write name='eventsIter' property='endTime1'/>" id="endTime1" maxlength="5" size="6" >
				   <select name="<bean:write name='attrPrefix'/>[<bean:write name="eventsCount"/>].AMPMId2" value="<bean:write name='eventsIter' property='AMPMId2'/>">
                     <option value="AM">AM</option>
                     <option value="PM"<logic:equal name="eventsIter" property="AMPMId2" value="PM">selected </logic:equal>>PM</option>
                    </select>
                  </td>
                  </logic:notEqual>
                  <logic:equal name="csCalendarOtherForm" property="activityFlow" value="summary">
                    <td class="formDe"><bean:write name="eventsIter" property="endTime1"/> <logic:notEmpty name="eventsIter" property="endTime1"><bean:write name="eventsIter" property="AMPMId2"/></logic:notEmpty>
				    </td> 
				   </logic:equal>
				</tr>
 
				<tr>
					<td colspan=4 class=formDeLabel>Purpose
					</td>
				</tr>
				<tr>
					<td colspan=4 class=formDe>						
							<bean:write name="eventsIter" property="purpose"/>				
					</td>
				</tr>
				
				 <tr>
							<td class="formDeLabel" nowrap width=20%><logic:notEqual name="csCalendarOtherForm" property="activityFlow" value="summary"><bean:message key="prompt.3.diamond"/></logic:notEqual>Outcome</td>
							<td class="formDe" colspan=3>
							 <logic:notEqual name="csCalendarOtherForm" property="activityFlow" value="summary">
							  <select name="<bean:write name='attrPrefix'/>[<bean:write name="eventsCount"/>].outcome">
									<option value="">Please Select</option>
									<option value="CO">Complete</option>
							        <option value="IC">Incomplete</option>							        
								</select>
							</logic:notEqual>
							<logic:equal name="csCalendarOtherForm" property="activityFlow" value="summary">
							  <logic:equal name="eventsIter" property="outcome" value="CO">
							   Complete
							  </logic:equal>	
							   <logic:equal name="eventsIter" property="outcome" value="IC">
							   Incomplete
							  </logic:equal>							  
							</logic:equal>
							</td>
			   </tr>
			   
			   <tr bgcolor=black>
			       <td colspan=4><div class=spacer></div></td>
			   </tr>
				</logic:iterate>
				 <input type="hidden" name="count" id ="count" value ="<%=i%>">
						<tr>
							<td class="formDeLabel" colspan=4><logic:notEqual name="csCalendarOtherForm" property="activityFlow" value="summary"><bean:message key="prompt.3.diamond"/></logic:notEqual>Narrative</td>
						</tr>
						<style>
							.mceEditor{
							width: "100%";
							height: "150"
							}
						</style>
						<tr class=formDe>
						   <logic:notEqual name="csCalendarOtherForm" property="activityFlow" value="summary">
							<td colspan=4 align=center>							
								<%--<html:textarea styleClass="mceEditor" style="width:100%" rows="12" property="narrative" ondblclick="myReverseTinyMCEFix(this)"/> --%>
								<html:textarea styleClass="mceEditor" style="width:100%" rows="12" name="csCalendarOtherForm"  property="narrative"  ondblclick="myReverseTinyMCEFix(this)"/>
			                    <tiles:insert page="../../common/spellCheckButtonTile.jsp" flush="false">
		                          <tiles:put name="agencyCode" beanName="csCalendarOtherForm" beanProperty="agencyId"/>
		                        </tiles:insert>
		                        </td>
		                       </logic:notEqual> 		                       
		                       <logic:equal name="csCalendarOtherForm" property="activityFlow" value="summary">
		                       <td colspan=4 class=formDe>
		                        <bean:write name="csCalendarOtherForm" property="narrative" filter="false"/>
		                        </td>
		                       </logic:equal>
							
						</tr>
					
				
			</table>
		</td>
	</tr>
</table>
</logic:notEmpty>


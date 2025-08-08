<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="messaging.juvenilecase.reply.RiskQuestionResponseEvent" %>

<!-- This tile takes a parameter of -->
<tiles:useAttribute name="tilesAFormName" />
<tiles:importAttribute name="tilesImageLevel" ignore="true" />
	
<bean:define id="tFormName" ><bean:write name="tilesAFormName" /></bean:define>

	<%-- Questions & Answers being dynamically pulled from the DB --%>
	<nested:iterate id="questionAnswers" name="<%=tFormName%>" property="questionAnswers"  indexId="outer">
								
		<bean:define id="required" name="questionAnswers" property="required" type="java.lang.Boolean" />
		<bean:define id="numeric" name="questionAnswers" property="numeric" type="java.lang.Boolean" />
		<bean:define id="allowsFutureDates" name="questionAnswers" property="allowsFutureDates" type="java.lang.Boolean" />
		<bean:define id="uiControlType" name="questionAnswers" property="uiControlType" type="java.lang.String" />
		<% 
			StringBuffer titleSB = new StringBuffer();
			titleSB.append("required").append(required.toString()).append("numeric").append(numeric.toString());
			
			if (uiControlType.equals("DATE")) 
			{
				titleSB.append("allowsFutureDates").append(allowsFutureDates.toString());
			}
			
		%>
		
		<logic:equal name="questionAnswers" property="initialAction" value="HIDE">
			<tr id="row<bean:write name="questionAnswers" property="questionID"/>" class='hidden'>
		</logic:equal>
        					
		<logic:notEqual name="questionAnswers" property="initialAction" value="HIDE">
			<tr id="row<bean:write name="questionAnswers" property="questionID"/>">
		</logic:notEqual>
 												
		<logic:notEqual name="questionAnswers" property="uiControlType" value="QUESTIONHEADER">
			<logic:notEqual name="questionAnswers" property="uiControlType" value="TEXTAREA">
				<logic:notEqual name="questionAnswers" property="uiControlType" value="CHECKBOX">
					<logic:notEqual name="questionAnswers" property="uiControlType" value="CHECKBOXWITHCHRONIC">
						<!-- Normal Question Header -->
         				<td class='formDeLabel'>
         					<bean:write name="questionAnswers" property="questionText"/>
         				</td>
         			</logic:notEqual>
                					
   					<logic:equal name="questionAnswers" property="uiControlType" value="CHECKBOXWITHCHRONIC">
						<!-- Checkbox with chronic Question Header -->
						<td class='formDeLabel'><bean:write name="questionAnswers" property="questionText"/></td>
						<td class='formDeLabel'>Chronic?</td>
					</logic:equal>
				</logic:notEqual>
          					
   				<logic:equal name="questionAnswers" property="uiControlType" value="CHECKBOX">
   					<!-- Checkbox Question Header -->
					<td class='formDeLabel'>
						<bean:write name="questionAnswers" property="questionText"/>
					</td>
				</logic:equal>
                 					
			</logic:notEqual>

			<logic:equal name="questionAnswers" property="uiControlType" value="TEXTAREA">
					<!-- Textarea Question Header -->
				<td class='formDeLabel' colspan='2'> <bean:write name="questionAnswers" property="questionText"/>
					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
					<tiles:put name="tTextField" value="comment${questionAnswers.questionID}"/>
					<tiles:put name="tSpellCount" value="spellBtn${questionAnswers.questionID}" />
					</tiles:insert>
					<logic:notEqual name="questionAnswers" property="questionName" value="PREA_INTERVIEW15">
					(Max. characters allowed: 3200)
					</logic:notEqual>
					<logic:equal name="questionAnswers" property="questionName" value="PREA_INTERVIEW15">
					(Max. characters allowed: 1100)
					</logic:equal>
				</td>
			</logic:equal>
 														
		</logic:notEqual>
 													
		<logic:equal name="questionAnswers" property="uiControlType" value="QUESTIONHEADER">
			<!-- Question Header -->
			<td class='detailHead' colspan="2"> 
				<bean:write name="questionAnswers" property="questionText"/>
				<html:hidden name="questionAnswers" property="selectedAnswerID" indexed="true" />
			</td>
		</logic:equal>
				
		<logic:notEmpty name="questionAnswers" property="answers">
			<logic:empty name="questionAnswers" property="controlCode">
				<logic:equal name="questionAnswers" property="uiControlType" value="NOANSNEEDED">
					<td class="formDe">
						<bean:write name="questionAnswers" property="selectedAnswerID"/>
					</td>
				</logic:equal>
      													                    																
				<logic:equal name="questionAnswers" property="uiControlType" value="RADIO">
           			<td class='formDe'>
           				<logic:iterate id="answer" name="questionAnswers" property="answers" indexId="inner">
							<bean:define id="answerValue" name="answer" property="weightedResponseID" type="java.lang.Integer" />
								<logic:notEmpty name="answer" property="subordinateQuestionId">
									<logic:notEqual name="answer" property="subordinateQuestionId" value="null">
										<bean:define id="qaList" name="<%=tFormName%>" property="questionAnswers" type="java.util.List" />
										<bean:define id="subordinateQuestionId" name="answer" property="subordinateQuestionId" type="java.lang.String" />
		
										<% 
											String elementName = "";
											StringBuffer qaSB = new StringBuffer();
											RiskQuestionResponseEvent raResponse = null;
											for (int i = 0; i < qaList.size(); i++) { 
												raResponse= (messaging.juvenilecase.reply.RiskQuestionResponseEvent) qaList.get(i);
												String questionId = new Integer(raResponse.getQuestionID()).toString();
												if (questionId.equals(subordinateQuestionId)){
													if (raResponse.getUiControlType().equals("TEXTAREA")){
														qaSB.append(",row");
														qaSB.append(subordinateQuestionId);
														qaSB.append("A");
														break;
													}
													if (raResponse.getUiControlType().equals("DATE")) {
														elementName = "formElementDate";
													}else{
														elementName = "formElement";
													}
												}
											}
										%>
										
										<bean:define id="action" name="answer" property="action" type="java.lang.String" />
										<bean:define id="jsfunctDisable">disableFormElement('<%=elementName%><bean:write name='subordinateQuestionId'/>' );</bean:define>
										<bean:define id="jsfunctEnable">enableFormElement('<%=elementName%><bean:write name='subordinateQuestionId'/>' );</bean:define>					
										<bean:define id="jsfunctHide">disableFormElement('<%=elementName%><bean:write name='subordinateQuestionId'/>' ); show2('row<bean:write name='subordinateQuestionId'/><%=qaSB.toString()%>',0,'row'); </bean:define>
										<bean:define id="jsfunctShow">enableFormElement('<%=elementName%><bean:write name='subordinateQuestionId'/>' ); show2('row<bean:write name='subordinateQuestionId'/><%=qaSB.toString()%>',1,'row'); </bean:define>			
							
										
										<logic:equal name="answer" property="action" value="ENABLE">
											<nested:radio title="<%=titleSB.toString()%>" onclick="<%=jsfunctEnable%>" property="selectedAnswerID" value="<%=answerValue.toString()%>"></nested:radio>
										</logic:equal>
															
										<logic:equal name="answer" property="action" value="DISABLE">
											<nested:radio title="<%=titleSB.toString()%>" onclick="<%=jsfunctDisable%>" property="selectedAnswerID" value="<%=answerValue.toString()%>"></nested:radio>
										</logic:equal>
																		
										<logic:equal name="answer" property="action" value="HIDE">
											<nested:radio title="<%=titleSB.toString()%>" onclick="<%=jsfunctHide%>" property="selectedAnswerID" value="<%=answerValue.toString()%>"></nested:radio>
										</logic:equal>
 																		
										<logic:equal name="answer" property="action" value="SHOW">
											<nested:radio title="<%=titleSB.toString()%>" onclick="<%=jsfunctShow%>" property="selectedAnswerID" value="<%=answerValue.toString()%>"></nested:radio>
										</logic:equal>
									</logic:notEqual>
								</logic:notEmpty>
																	
							<!-- The following two Empty and Equal logic tags go together, if the inner part of one changes, the other should change too -->
							<logic:empty name="answer" property="subordinateQuestionId">
								<nested:radio title="<%=titleSB.toString()%>" property="selectedAnswerID" value="<%=answerValue.toString()%>"></nested:radio>									
							</logic:empty>
															
							<logic:equal name="answer" property="subordinateQuestionId" value="null">
								<nested:radio	styleId="<%=answerValue.toString()%>"
												title="<%=titleSB.toString()%>"
												property="selectedAnswerID"
												value="<%=answerValue.toString()%>">
								</nested:radio>									
							</logic:equal>
															
							<nested:write name="answer" property="answerText" />
						</logic:iterate>
     															
    				</td>
				</logic:equal>	
              															                          								
             	<logic:equal name="questionAnswers" property="uiControlType" value="CHECKBOX">
             		
					<%int formatIdentifier = 0; %>
					<bean:define id="reqValue" name="questionAnswers" property="required" type="java.lang.Boolean" />
					<%
						String reqString = "";
					
						if (reqValue.booleanValue() == true){
							reqString = "requiredtrue";
						}
					%>
					
					<td class='formDe' >
					<logic:iterate id="answer" name="questionAnswers" property="answers" indexId="inner">

							<div>
							<bean:define id="answerValue" name="answer" property="weightedResponseID" type="java.lang.Integer" />
																	
							<logic:equal name="questionAnswers" property="initialAction" value="DISABLE">
								<nested:multibox title="<%=reqString%>" disabled="true" styleId="formElement${questionAnswers.questionID}" onclick="" property="selectedAnswerIDs" value="<%=answerValue.toString()%>"></nested:multibox>									
							</logic:equal>
							
							<logic:notEqual name="questionAnswers" property="initialAction" value="DISABLE">
								<nested:multibox title="<%=reqString%>" disabled="false" styleId="formElement${questionAnswers.questionID}" onclick="" property="selectedAnswerIDs" value="<%=answerValue.toString()%>"></nested:multibox>									
							</logic:notEqual>
							
							<nested:write name="answer" property="answerText" />
							</div>
					<%--	</td>  --%>
						<%-- 	if(inner.intValue()%2 != 0) 
							{
								out.print( "</tr>" ) ;
							}
							formatIdentifier = inner.intValue();  --%>
					
					</logic:iterate>
					</td>
				<%--	if(formatIdentifier%2 == 0) 
					{
					out.print( "<td class='formDe'></td>" ) ;
					} --%>
					
				</logic:equal>		
			
				<logic:equal name="questionAnswers" property="uiControlType" value="CHECKBOXWITHCHRONIC">
					<% int formatIdentifier = 0;
					   int checkboxCounter = 0;
					%>
					<bean:define id="reqValue" name="questionAnswers" property="required" type="java.lang.Boolean" />
					<%
						String reqString = "";
						if (reqValue.booleanValue() == true){
							reqString = "requiredtrue";
						}
					%>
					<logic:iterate id="answer" name="questionAnswers" property="answers" indexId="inner">
						<bean:define id="nonChronicCheckbox" type="java.lang.String">nonChronicCheckbox<%=checkboxCounter%></bean:define>
						<bean:define id="chronicCheckbox"    type="java.lang.String">chronicCheckbox<%=checkboxCounter++%></bean:define>
						<tr>
		   					<td class='formDe' nowrap='nowrap'>
  									<bean:define id="answerValue" name="answer" property="weightedResponseID" type="java.lang.Integer"/>
  									<nested:multibox title="<%=reqString%>" styleId="<%=nonChronicCheckbox%>" onclick="javascript:autoUncheckChronic(this);" property="selectedAnswerIDs" value="<%=answerValue.toString()%>" />
 					 	 				<bean:write name="answer" property="answerText" />		
						  	</td>
					  		<td class='formDe' nowrap='nowrap'>
  									<bean:define id="chronicValue" name="answer" property="weightedResponseID" type="java.lang.Integer"/>
  						  			<nested:multibox title="" styleId="<%=chronicCheckbox%>"  onclick="javascript:autocheckChronic(this);" property="selectedChronicIDs" value="<%=chronicValue.toString()%>" />
						  	</td>
						</tr>
			
             			</logic:iterate>

					<% if(formatIdentifier%2 == 0) 
						{
							out.print( "<td class='formDe'></td>" ) ;
						}
					%>
				</logic:equal>	

				<logic:equal name="questionAnswers" property="uiControlType" value="DROPDOWN">
					<td class='formDe'>
               							
						<logic:notEmpty name="questionAnswers" property="initialAction">
							<logic:equal name="questionAnswers" property="initialAction" value="HIDE">
								<nested:select title="<%=titleSB.toString()%>" styleId="formElement${questionAnswers.questionID}" disabled="true" property="selectedAnswerID" onchange="">
									<html:option value=""><bean:message key="select.generic" /></html:option>
									<html:optionsCollection name="questionAnswers" property="answers" value="weightedResponseID" label="answerText" />
								</nested:select>
							</logic:equal>
						</logic:notEmpty>
							
						<logic:empty name="questionAnswers" property="initialAction">
							<nested:select title="<%=titleSB.toString()%>" styleId="formElement${questionAnswers.questionID}" disabled="false" property="selectedAnswerID" onchange="">
								<html:option value=""><bean:message key="select.generic" />	</html:option>
								<html:optionsCollection name="questionAnswers" property="answers" value="weightedResponseID" label="answerText" />
							</nested:select>
						</logic:empty>
								
					</td>
				</logic:equal>
                						
				<logic:equal name="questionAnswers" property="uiControlType" value="DATE">
					<bean:define id="questionTextValue" name="questionAnswers" property="questionText" type="java.lang.String"/>
				<!--  	<script type='text/javascript'>
						var cal1 = new CalendarPopup();
						cal1.showYearNavigation();
					</script> -->
						
						<td class='formDe'>
										
							<logic:notEmpty name="questionAnswers" property="initialAction">
								<logic:equal name="questionAnswers" property="initialAction" value="HIDE">
									<logic:notEqual name="questionAnswers" property="useAnswerText" value="true">
										<nested:text title="<%=titleSB.toString()%>" styleId="formElementDate${questionAnswers.questionID}" disabled="true"  property="selectedAnswerID" maxlength="10" size="10" />
									</logic:notEqual>
									
									<logic:equal name="questionAnswers" property="useAnswerText" value="true">
										<logic:iterate id="answer" name="questionAnswers" property="answers" indexId="inner">
											<logic:equal name="questionAnswers" property="selectedAnswerID" value="${answer.weightedResponseID}">
												<bean:define id="theText" name="answer" property="answerText" />
												<nested:text title="<%=titleSB.toString()%>" styleId="formElementDate${questionAnswers.questionID}" value="${theText}" disabled="true"  property="selectedAnswerID" maxlength="10" size="10" />
											</logic:equal>
										</logic:iterate>
									</logic:equal>
									
							<!-- 		<a href="#" onClick="cal1.select((document.getElementsByName('<nested:writeNesting property="selectedAnswerID"/>')[0]),'anchor<%=outer%>','MM/dd/yyyy'); return false;"
										name="anchor<%=outer%>" ID="anchor<%=outer%>" border="0">
										<logic:equal name="tilesImageLevel" value="">
											<bean:message key="prompt.2.calendar" /> 
										</logic:equal>
										<logic:equal name="tilesImageLevel" value="3">
											<bean:message key="prompt.3.calendar" />
										</logic:equal>
									</a>   -->
								</logic:equal>
							</logic:notEmpty>
                								
							<logic:empty name="questionAnswers" property="initialAction">
								<logic:notEqual name="questionAnswers" property="useAnswerText" value="true">
									<nested:text title="<%=titleSB.toString()%>" styleId="formElementDate${questionAnswers.questionID}" disabled="false" property="selectedAnswerID" maxlength="10" size="10" />
								</logic:notEqual>
										
								<logic:equal name="questionAnswers" property="useAnswerText" value="true">
									<logic:iterate id="answer" name="questionAnswers" property="answers" indexId="inner">
										<logic:equal name="questionAnswers" property="selectedAnswerID" value="${answer.weightedResponseID}">
											<bean:define id="theText" name="answer" property="answerText" />
											<nested:text title="<%=titleSB.toString()%>" styleId="formElementDate${questionAnswers.questionID}" value="${theText}" disabled="false" property="selectedAnswerID" maxlength="10" size="10" />
										</logic:equal>
									</logic:iterate>
								</logic:equal>
				
							<!--  	<a href="#" onClick="cal1.select((document.getElementsByName('<nested:writeNesting property="selectedAnswerID"/>')[0]),'anchor<%=outer%>','MM/dd/yyyy'); return false;"
									name="anchor<%=outer%>" ID="anchor<%=outer%>" border="0">
									<logic:equal name="tilesImageLevel" value="">
										<bean:message key="prompt.2.calendar" /> 
									</logic:equal>
									<logic:equal name="tilesImageLevel" value="3">
										<bean:message key="prompt.3.calendar" />
									</logic:equal>
								</a> -->
							</logic:empty>
						</td>
					</logic:equal>
                						
					<logic:equal name="questionAnswers" property="uiControlType" value="LISTBOX">
						<td class='formDe'>
							<nested:select title="<%=titleSB.toString()%>" property="selectedAnswerIDs" multiple="true"> 
								<html:option value=""><bean:message key="select.generic" /></html:option>
								<html:optionsCollection name="questionAnswers" property="answers" value="weightedResponseID" label="answerText" /> 
							</nested:select> 	
						</td>
						
					</logic:equal>
      					
					<logic:equal name="questionAnswers" property="uiControlType" value="TEXTBOX">
						<td class='formDe'>
																		
							<logic:notEqual name="questionAnswers" property="useAnswerText" value="true">
								<html:text title="<%=titleSB.toString()%>" name="questionAnswers" property="selectedAnswerID" indexed="true" />
							</logic:notEqual>
								
							<logic:equal name="questionAnswers" property="useAnswerText" value="true">
								<logic:iterate id="answer" name="questionAnswers" property="answers" indexId="inner">
									<logic:equal name="questionAnswers" property="selectedAnswerID" value="${answer.weightedResponseID}">
										<bean:define id="theText" name="answer" property="answerText" />
									</logic:equal>
								</logic:iterate>
								<%-- DG Added check for no value in theText so that text box values would display on update of TJJD Risk --%>
								<logic:empty name="theText">
									<html:text title="<%=titleSB.toString()%>" name="questionAnswers" property="selectedAnswerID" indexed="true" value="${answer.answerText}" />
								</logic:empty>
								<logic:notEmpty name="theText">
									<html:text title="<%=titleSB.toString()%>" name="questionAnswers" property="selectedAnswerID" indexed="true" value="${theText}" />
								</logic:notEmpty>
							</logic:equal>
											
						</td>
					</logic:equal>
  															
					<logic:equal name="questionAnswers" property="uiControlType" value="TEXTAREA">
						<logic:equal name="questionAnswers" property="initialAction" value="HIDE">
							<tr id="row<bean:write name="questionAnswers" property="questionID"/>A" class='hidden'>
						</logic:equal>
						<logic:notEqual  name="questionAnswers" property="initialAction" value="HIDE">
							<tr id="row<bean:write name="questionAnswers" property="questionID"/>A">
						</logic:notEqual>
						<%-- tr --%>
							<td class='formDe' colspan='2'>
								<logic:notEqual name="questionAnswers" property="questionName" value="PREA_INTERVIEW15">
								<html:textarea title="<%=titleSB.toString()%>" name="questionAnswers" property="selectedAnswerID" style='width:100%' indexed="true" styleId="comment${questionAnswers.questionID}" rows="3" onmouseout="textCounter(this,3200)" onkeydown="textCounter(this,3200)" />
								</logic:notEqual>
								<logic:equal name="questionAnswers" property="questionName" value="PREA_INTERVIEW15">
								<html:textarea title="<%=titleSB.toString()%>" name="questionAnswers" property="selectedAnswerID" style='width:100%' indexed="true" styleId="comment${questionAnswers.questionID}" rows="3" onmouseout="textCounter(this,1100)" onkeydown="textCounter(this,1100)" />
								</logic:equal>
							</td>
						</tr>
					</logic:equal>
									
				</logic:empty>
   													
				<logic:notEmpty name="questionAnswers" property="controlCode">
					<td class='formDe'>
						<html:hidden name="questionAnswers" property="selectedAnswerID" indexed="true" />
						<logic:notEqual name="questionAnswers" property="useAnswerText" value="true">
							<logic:equal name="questionAnswers" property="uiControlType" value="DATE">
								<bean:write name="questionAnswers" property="selectedAnswerID" formatKey="date.format.mmddyyyy"/> 
							</logic:equal>
							
							<logic:notEqual name="questionAnswers" property="uiControlType" value="DATE">
								<bean:write name="questionAnswers" property="selectedAnswerID"/> 
							</logic:notEqual>
						</logic:notEqual>
					
						<logic:equal name="questionAnswers" property="useAnswerText" value="true">
							<logic:iterate id="answer" name="questionAnswers" property="answers" indexId="inner">
								<logic:equal name="questionAnswers" property="selectedAnswerID" value="${answer.weightedResponseID}">
									<nested:write name="answer" property="answerText" />
								</logic:equal>
								<logic:equal name="questionAnswers" property="selectedAnswerID" value="${answer.answerText}">
									<nested:write name="answer" property="answerText" />
								</logic:equal>
							</logic:iterate>
						</logic:equal>
																		
					</td>
				</logic:notEmpty>
			</logic:notEmpty>
     					
   		</tr> <%-- opening <tr> in logic tags --%>
    </nested:iterate>
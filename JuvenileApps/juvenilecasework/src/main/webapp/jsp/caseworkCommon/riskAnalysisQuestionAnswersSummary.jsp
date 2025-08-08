<!DOCTYPE HTML>

<%-- 08/31/2015	 	RCarter	   #29426 html 5 compliance effort and jquery 5 (when required) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<tiles:useAttribute name="tilesAFormName" />
<bean:define id="tFormName" ><bean:write name="tilesAFormName" /></bean:define>


                        									                                
                      											<logic:iterate id="questions" name="riskAnalysisForm" property="processedViewQuestionAnswers">
      			            										<tr>
                		  												<logic:notEqual name="questions" property="uiControlType" value="QUESTIONHEADER">
                		  												
                		  													<logic:notEqual name="questions" property="uiControlType" value="TEXTAREA">
		                    													<td class="formDeLabel" >
	    	              															<bean:write name="questions" property="questionText" />
	        	          														</td>
	            	      														<td class="formDe"> 
	                	  															<bean:write name="questions" property="answerText" />
	                  															</td>     
                  															</logic:notEqual>
                  															
                  															<logic:equal name="questions" property="uiControlType" value="TEXTAREA">
                  																<td class="formDeLabel" colspan='2'>
	    	              															<bean:write name="questions" property="questionText" />
	        	          														</td>
																				<tr>
																					<td class='formDe' colspan='2'>
																						<bean:write name="questions" property="answerText" />
																					</td>
																				</tr>
																			</logic:equal>
																			  
																		</logic:notEqual>
                    													
        		            											<logic:equal name="questions" property="uiControlType" value="QUESTIONHEADER">
                		    												<td class='detailHead' colspan="2"> 
                    															<bean:write name="questions" property="questionText"/>
                    														</td>
                    													</logic:equal>
                  													</tr>
                  												</logic:iterate>
                       										
                       										
													


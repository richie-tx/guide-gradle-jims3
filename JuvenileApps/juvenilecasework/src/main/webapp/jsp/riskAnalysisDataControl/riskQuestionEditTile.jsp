<!DOCTYPE HTML>
<%-- Dwilliamson  12/09/2010	Create Tile.  This Question tile does not collapse --%>
<%-- 04/24/2012		CShimek		#73272 revised date drop downs, should only be active for UIControl Type = DATE --%>

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
<%@ page import="naming.RiskAnalysisConstants"%>


<title><bean:message key="title.heading" /> - riskQuestionEditTile.jsp</title>

<tiles:useAttribute id="formName" name="formName"/>
<tiles:useAttribute id="questionBoxTitle" name="questionBoxTitle"/>
 				 
<!-- BEGIN QUESTION TABLE -->
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class="detailHead">
				<table width="100%" cellpadding="2" cellspacing="0">
		        	<tr>
		            	<td class="detailHead"><bean:write name="questionBoxTitle"/></td>
		         	</tr>
		        </table>
	        </td>
	    </tr>
	    <tr>
			<td>
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
		        	<tr class="detailHead">
		            	<td colspan="4"><bean:message key="prompt.question"/>&nbsp;<bean:message key="prompt.information"/></td>
		          	</tr>
		          	<tr>
						<td width="15%" class="formDeLabel"><bean:message key="prompt.2.diamond" /> <bean:message key="prompt.questionName"/></td>
						<td width="35%" class="formDe"><html:text styleId="questionName" name="${formName}" property="question.questionName" size="25" maxlength="25" /></td>
						<td width="15%" class="formDeLabel">Entry Date</td>
						<td width="35%" class="formDe">
							<bean:write name="${formName}" property="question.questonEntryDate" formatKey="datetime.format.mmddyyyyHHmm" />
							<html:hidden name="${formName}" property="question.questonEntryDate"/>
						 </td>
					</tr>					
					<tr>
						<td width="15%" class="formDeLabel"><bean:message key="prompt.2.diamond" /> <bean:message key="prompt.questionText"/><br>&nbsp;(Max Characters 150)</td>
						<td width="35%" class="formDe" colspan="3"><html:textarea styleId="questionText" name="${formName}" property="question.questionText" style="width:100%" rows="2" onmouseout="textLimit( this, 150 )" onkeyup="textLimit( this, 150 )"></html:textarea></td>
					</tr>
					<tr>					
						<td width="15%" class="formDeLabel"><bean:message key="prompt.2.diamond" /> UI Control Type</td>
						<td width="35%" class="formDe">
							<html:select styleId="uiControlType" name="${formName}" property="question.uiControlType" onchange="javascript: uiControlTypeChoice(this.form, this);">
								<html:option value=""><bean:message key="select.generic" /></html:option>
								<jims2:codetable codeTableName="<%=RiskAnalysisConstants.JUV_RISK_UI_CONTROL_TYPE%>"/> 
							</html:select>
						</td>
						<td width="15%" class="formDeLabel">Default to System Date</td>
						<td width="35%" class="formDe">
							<html:select name="${formName}" property="question.defaultToSystemDate" styleId="defaultSysDate" disabled="true">
								<html:option value="">Please Select</html:option>									                          								  		
								<html:option value="true">YES</html:option>
								<html:option value="false">NO</html:option>
							</html:select>
						</td>
					</tr>
		          
					<logic:equal name="${formName}" property="question.uiControlType" value="QUESTIONHEADER">
						<tr id='collapseHeader'>
					</logic:equal>
					<logic:notEqual name="${formName}" property="question.uiControlType" value="QUESTIONHEADER">
						<tr  class='hidden' id='collapseHeader'>
					</logic:notEqual>
						<td width="15%" class="formDeLabel">Collapsible Header</td>
						<td width="35%" class="formDe" colspan="3">
							<html:select styleId="collapsibleHeader" name="${formName}" property="question.collapsibleHeader">
								<html:option value="">Please Select</html:option>									                          								  		
								<html:option value="true">YES</html:option>
								<html:option value="false">NO</html:option>
							</html:select>
						</td>
					</tr>
		          
					<tr>
						<td width="15%" class="formDeLabel"><bean:message key="prompt.2.diamond" /> UI Display Order</td>
						<td width="35%" class="formDe"><html:text styleId="uiDisplayOrder" name="${formName}" property="question.uiDisplayOrder" size="2" maxlength="2"/></td>             
						<td width="15%" class="formDeLabel">Allows Future Dates?</td>
						<td width="35%" class="formDe">
							<html:select name="${formName}" property="question.allowFutureDates" styleId="allowFutDate" disabled="true">
								<html:option value="">Please Select</html:option>									                          								  		
								<html:option value="true">YES</html:option>
								<html:option value="false">NO</html:option>
							</html:select>
						</td>	 
					</tr>
					<tr>
						<td width="15%" class="formDeLabel">Numeric Only?</td>
						<td width="35%" class="formDe">
							<html:select name="${formName}" property="question.numericOnly" >
								<html:option value="">Please Select</html:option>									                          								  		
								<html:option value="true">YES</html:option>
								<html:option value="false">NO</html:option>
							</html:select>
						</td>
						<td width="15%" class="formDeLabel">Question Text Not Modifiable?</td>
						<td width="35%" class="formDe">
							<html:checkbox name="${formName}" property="question.hardcoded" value="true" onclick="enableTextUpdate(this)"/>
							<html:hidden name="${formName}"  property="question.hardcoded" value="false"/>
						</td>	
					</tr>
		          
					<tr>
						<td width="15%" class="formDeLabel">Initial Action</td>
						<td width="35%" class="formDe">
							<html:select name="${formName}" property="question.questionInitialAction" >
								<html:option value="">Please Select</html:option>									                          								  		
								<html:option value="HIDE">HIDE</html:option>
								<html:option value="DISABLE">DISABLE</html:option>
							</html:select>
						</td>   
						<td width="15%" class="formDeLabel"><bean:message key="prompt.2.diamond" /> Required?</td>
						<td width="35%" class="formDe">
							<html:select styleId="required" name="${formName}" property="question.required" >
								<html:option value="">Please Select</html:option>									                          								  		
								<html:option value="true">YES</html:option>
								<html:option value="false">NO</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<td width="15%" class="formDeLabel">Control Code</td>
						<td width="35%" class="formDe">
						<html:select size="1" name="${formName}" property="question.controlCode">
								<html:option value="">Please Select</html:option>
								<html:optionsCollection property="controlCodes" value="code" label="name" />					
							</html:select>
						</td>
						<td width="15%" class="formDeLabel"><bean:message key="prompt.2.diamond" /> Allow Print?</td>
						<td width="35%" class="formDe">
							<html:select size="1" styleId="reqPrint" name="${formName}" property="question.allowPrint" >
								<html:option value="">Please Select</html:option>									                          								  		
								<html:option value="true">YES</html:option>
								<html:option value="false">NO</html:option>
							</html:select>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
 <!-- END QUESTION TABLE -->

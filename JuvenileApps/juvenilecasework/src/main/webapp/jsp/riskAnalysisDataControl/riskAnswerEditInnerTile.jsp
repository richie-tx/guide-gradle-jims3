<!DOCTYPE HTML>
<%-- Dwilliamson  12/09/2010	Create Tile.  This Question tile collapses --%>
<%-- 04/20/2012	CShimek		#73251 added textLimit check to answerText field --%>

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
<script type="text/javascript">
function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen)
	{
		field.value = field.value.substring(0, maxlen);
  		alert("Your input has been truncated to " + maxlen +" characters!");
	}
}
</script>
<title><bean:message key="title.heading" /> - riskAnswerEditInnerTile.jsp</title>

<tiles:useAttribute id="formName" name="formName"/>

<!-- Begin Inner Table -->
	<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
		<tr class="detailHead">
			<td colspan="4">Answer Information</td>
		</tr>
		<tr>
			<td width="15%" class="formDeLabel"><bean:message key="prompt.2.diamond" /> Entry Date</td>
			<td class="formDe" colspan="3">
				<bean:write name="${formName}" property="currentAnswer.answerEntryDate" formatKey="datetime.format.mmddyyyyHHmm" />
				<html:hidden name="${formName}" property="currentAnswer.answerEntryDate"/>                  	
			</td>
		</tr>
		<tr>
			<td width="15%" class="formDeLabel" valign="top"><bean:message key="prompt.2.diamond" /> Answer Text<br>&nbsp;(Max Characters 250)</td>
			<td class="formDe" colspan="3">
				<html:textarea styleId="answerText" name="${formName}" property="currentAnswer.answerText" style="width:100%" rows="4" onmouseout="textLimit(this, 250);" onkeyup="textLimit(this, 250);"></html:textarea>
			</td>            
		</tr>
		<tr>
			<td width="15%" class="formDeLabel"><bean:message key="prompt.2.diamond" /> Weight</td>
			<td width="35%" class="formDe"><html:text styleId="weight" name="${formName}" property="currentAnswer.weight" size="2" maxlength="2" /></td>
			<td width="15%" class="formDeLabel">Subordinate Question?</td>
			<td width="35%" class="formDe">
				<html:select  name="${formName}" property="currentAnswer.subordinateQuestionId">
					<html:option value=""><bean:message key="select.generic" /></html:option>
					<html:optionsCollection name="${formName}" property="questions" value="questionId" label="questionName" />					
				</html:select>
			</td>
		</tr>
		<tr>
			<td width="15%" class="formDeLabel"><bean:message key="prompt.2.diamond" /> Sort Order</td>
			<td width="35%" class="formDe"><html:text styleId="sortOrder" name="${formName}" property="currentAnswer.sortOrder" size="2" maxlength="2"/></td>
			<td width="15%" class="formDeLabel">Action</td>
			<td width="35%" class="formDe">
				<html:select name="${formName}" property="currentAnswer.action" >
					<html:option value="">Please Select</html:option>									                          								  		
					<html:option value="SHOW">SHOW</html:option>
					<html:option value="HIDE">HIDE</html:option>
					<html:option value="ENABLE">ENABLE</html:option>
					<html:option value="DISABLE">DISABLE</html:option>
				</html:select>
			</td>
		</tr>
	</table> 
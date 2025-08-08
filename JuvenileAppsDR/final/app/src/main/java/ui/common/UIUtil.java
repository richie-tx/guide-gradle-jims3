/*
 * Created on Aug 26, 2004
 *
 */
package ui.common;

import gnu.regexp.RE;
import gnu.regexp.REException;

import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

import messaging.address.AddressRequestEvent;
import messaging.address.reply.AddressResponseEvent;
import messaging.administerassessments.CSCAnswer;
import messaging.administerassessments.reply.CSCPossibleResponseEvent;
import messaging.administerassessments.reply.CSCQuestionGroupResponseEvent;
import messaging.administerassessments.reply.CSCQuestionResponseEvent;
import messaging.agency.GetAgencyEvent;
import messaging.agency.GetDepartmentEvent;
import messaging.casefile.UpdateResponseEvent;
import messaging.casefile.reply.PossibleResponseEvent;
import messaging.casefile.reply.QuestionGroupResponseEvent;
import messaging.casefile.reply.QuestionResponseEvent;
import messaging.codetable.person.reply.RaceEthnicityCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.contact.domintf.IAddress;
import messaging.contact.domintf.IName;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportRequestEvent;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.struts.action.ActionMessage;

/**
 * @author ldeen
 *
 */
public final class UIUtil {
	private UIUtil() {
	}

	public static final int ERROR = 1;

	public static final int SUCCESS = 2;

	public static final int SYSTEM_EXCEPTION = 3;

	static private final String NOT_AVAILABLE = "";

	private static String ST = "st";

	private static String ND = "nd";

	private static String RD = "rd";

	private static String TH = "th";

	private static String D = "d";

	private static String MMMM = "MMMM";

	private static String YYYY = "yyyy";

	private static String xmlPrefix = "<";

	private static String xmlSuffix = ">";

	public static String htmlNBSP = "&nbsp;";

	public static String tinyMCESpace = "&#160;";

	public static String htmlAMPER = "&#38;";

	public static String htmlLQuot = "&#8220;";

	public static String htmlRQuot = "&#8221;";

	public static String htmlDQuot = "&quot;";

	public static String htmlSQuot = "&#39;";


	//	public static void main(String[] args){
	//		String testString="<br/><span style=\"font-family: impact\"><br
	// >ddd</br><BR>kkk</BR><p><br>this</br></p></span>";
	//		//String testString2="this is a test of all the possible tags taht can
	// &lt;bold tag here test&gt;be rendered and see what the output lookslike
	// in html vs what it may look like otherwise";
	//		System.out.println(testString);
	//		String newStr=removeStarting_BR_P_XMLtags(testString);
	//		System.out.println(newStr);
	//
	//	}

	/**
	 * Reg ex special char escape fixes. Escapes all 11 (except the '\' so
	 * really 10) speical characters in regex returning a valid regex
	 * expresssion
	 *
	 * @return a string with properly escapped regex special characters to be
	 *         used in a regex expression.
	 */
	public static String regExSpecCharEscapeFix(String aNonFixedRegexPattern) {
		String specialCharReplString = "\\\\"; // all four slashes are necessary
											   // for this to work correctly
		if (aNonFixedRegexPattern == null || aNonFixedRegexPattern.equals(""))
			return aNonFixedRegexPattern;
		String fixedValue = aNonFixedRegexPattern.replaceAll("\\(", "\\\\(");
		fixedValue = fixedValue.replaceAll("\\)", "\\\\)");
		fixedValue = fixedValue.replaceAll("\\[", "\\\\[");
		fixedValue = fixedValue.replaceAll("\\^", "\\\\^");
		fixedValue = fixedValue.replaceAll("\\$", "\\\\$");
		fixedValue = fixedValue.replaceAll("\\.", "\\\\.");
		fixedValue = fixedValue.replaceAll("\\|", "\\\\|");
		fixedValue = fixedValue.replaceAll("\\?", "\\\\?");
		fixedValue = fixedValue.replaceAll("\\*", "\\\\*");
		fixedValue = fixedValue.replaceAll("\\+", "\\\\+");
		return fixedValue;
	}

	public static Collection convertStringArr2Coll(String[] aIncomingStrArr) {
		if (aIncomingStrArr == null)
			return null;
		ArrayList myArrList = new ArrayList();
		for (int loopX = 0; loopX < aIncomingStrArr.length; loopX++) {
			//dag 2/8/07
			//myArrList.add(aIncomingStrArr[loopX]);
			String aString = aIncomingStrArr[loopX];
			if (!aString.equals("")) {
				myArrList.add(aIncomingStrArr[loopX]);
			}
		}
		return myArrList;
	}

	public static String blankToNull(String aIncStr) {
		if (aIncStr != null && aIncStr.trim().equals(""))
			return null;
		else
			return aIncStr;
	}

	public static String nullToBlank(String aIncStr) {
		if (aIncStr == null)
			return "";
		else
			return aIncStr;
	}

	/**
	 * Method removes the specific sequenced occurence of a given seq in
	 * typically an XML string buffer original buffer is modified
	 *
	 * @param aIncomingBuffer
	 * @param aSeq
	 * @param aDeleteCount
	 * @return
	 */
	private static StringBuffer removeCountOccurence(
			StringBuffer aIncomingBuffer, String aSeq, int aDeleteCount) {
		int count = 0;
		int startPos = 0;
		int endPos = 0;
		if (aIncomingBuffer == null || aSeq == null || aSeq.equals("")
				|| aDeleteCount < 0) {
			return aIncomingBuffer;
		}
		StringBuffer aStrBuffer = new StringBuffer(aIncomingBuffer.toString());
		while (aStrBuffer.indexOf(aSeq) != -1 && count < aDeleteCount) {
			count++;
			startPos = aStrBuffer.indexOf(aSeq);
			endPos = startPos + aSeq.length();
			if (count == 1) {
				aIncomingBuffer = new StringBuffer();
			}
			if (count == aDeleteCount) {
				aStrBuffer.delete(startPos, endPos);
				break;
			}

			aIncomingBuffer.append(aStrBuffer.substring(0, endPos));
			aStrBuffer.delete(0, endPos);
			startPos = 0;
			endPos = 0;
		}
		if (count > 0) {
			aIncomingBuffer
					.append(aStrBuffer.substring(0, aStrBuffer.length()));
		}
		return aIncomingBuffer;
	}

	/**
	 *
	 * @param str
	 * @return
	 */
	public static String removeLeadingZeros(String str)
	{
	 if (str == null)
	 {
	    return null;
	 }
	 char[] chars = str.toCharArray();
	 int index = 0;
	 for (; index < str.length(); index++)
	 {
	  if (chars[index] != '0')
	  {
	   break;
	  }
	 }
	 return (index == 0) ? str : str.substring(index);
	}

	/**
	 * Used to count and keep track of open and close tags within an XML string
	 * coming in the incomingbuffer; returns the numbered position of the "end"
	 * closing sequence i.e <br>
	 * dsfa</br> for open seq <br>
	 * and close seq</br> returns 1 but <br>
	 * <br>
	 * dsfa</br></br> will return 2 because the ending closing seq for the
	 * first open tag is the second</br> Expects properly formed XML
	 *
	 * @param aIncomingBuffer
	 * @param aOpenSeq
	 * @param aClosingSeq
	 * @return
	 */
	private static int countPosOccurence(StringBuffer aIncomingBuffer,
			String aOpenSeq, String aClosingSeq) {
		int count = 0;
		int openCount = 0;
		int closeCount = 0;
		int pos = 0;
		if (aIncomingBuffer == null || aOpenSeq == null || aOpenSeq.equals("")) {
			return count;
		}
		StringBuffer aStrBuffer = new StringBuffer(aIncomingBuffer.toString());
		while (aStrBuffer.indexOf(aOpenSeq, pos) != -1) {
			count++;
			openCount++;
			pos = aStrBuffer.indexOf(aOpenSeq, pos);
			int oldPos = pos;
			pos = pos + aOpenSeq.length();
			aStrBuffer.delete(oldPos, pos);
			pos = 0;
			if (aStrBuffer.indexOf(aOpenSeq, pos) != -1
					&& aStrBuffer.indexOf(aClosingSeq, pos) < aStrBuffer
							.indexOf(aOpenSeq, pos)) {
				closeCount++;
			} else if (aStrBuffer.indexOf(aOpenSeq, pos) == -1) {
				closeCount++;
				break;
			} else {

			}
			if (openCount == closeCount)
				return count;
		}
		return count;
	}

	public static Address getUIAddressObjFromIAddress(IAddress myIAddObj){
		Address newAddr=new Address();
		if(myIAddObj!=null){
			newAddr.setAdditionalZipCode(myIAddObj.getAdditionalZipCode());
			newAddr.setAddress2(myIAddObj.getAddress2());
			newAddr.setAddressId(myIAddObj.getAddressId());
			newAddr.setAddressStatus(myIAddObj.getAddressStatus());
			newAddr.setAddressTypeCode(myIAddObj.getAddressTypeCode());
			newAddr.setAptNum(myIAddObj.getAptNum());
			newAddr.setCity(myIAddObj.getCity());
			newAddr.setCountryCode(myIAddObj.getCountryCode());
			newAddr.setStateCode(myIAddObj.getStateCode());
			newAddr.setCountyCode(myIAddObj.getCountyCode());
			newAddr.setStreetName(myIAddObj.getStreetName());
			newAddr.setStreetNum(myIAddObj.getStreetNum());
			newAddr.setStreetTypeCode(myIAddObj.getStreetTypeCode());
			newAddr.setZipCode(myIAddObj.getZipCode());
			newAddr.setValidated(myIAddObj.getValidated());
		}
		return newAddr;
	}

	/**
	 * This method removes all starting break type tags removing first all <br>
	 * tags then all
	 * <p>
	 * tags then all open p tags. It properly finds the closing and opening tags
	 *
	 * @param aStringWithXMLtags
	 * @return
	 */
	public static String removeStarting_BR_P_XMLtags(String aStringWithXMLtags) {
		String result = "";
		if (aStringWithXMLtags == null || aStringWithXMLtags.equals(""))
			return "";
		StringBuffer myBuffer = new StringBuffer(aStringWithXMLtags);
		int endPos = -1;
		String XML_BR_OPENTAG = "<br>";
		String XML_BR_CLOSETAG = "</br>";
		String XML_BR_OPENCLOSETAG = "<br/>";
		String XML_P_OPENTAG = "<p>";
		String XML_P_CLOSETAG = "</p>";
		String XML_P_OPENCLOSETAG = "<p/>";
		String XML_P_OPENTAGWITHATTRIBS = "<p";
		boolean found = true;
		while (found) {
			found = false;
			if (myBuffer.indexOf(xmlPrefix) != -1) {
				int startPos = myBuffer.indexOf(XML_BR_OPENTAG);
				if (startPos == 0) {
					int posOcc = countPosOccurence(myBuffer, XML_BR_OPENTAG,
							XML_BR_CLOSETAG);
					endPos = startPos + XML_BR_OPENTAG.length();
					myBuffer.delete(startPos, endPos);
					myBuffer = removeCountOccurence(myBuffer, XML_BR_CLOSETAG,
							posOcc);
					found = true;
				}
				startPos = myBuffer.indexOf(XML_BR_OPENCLOSETAG);
				if (startPos == 0 && !found) {
					endPos = startPos + XML_BR_OPENCLOSETAG.length();
					myBuffer.delete(startPos, endPos);
					if (!found)
						found = true;
				}
				// P TAG evaluation
				startPos = myBuffer.indexOf(XML_P_OPENTAG);
				if (startPos == 0 && !found) {
					int posOcc = countPosOccurence(myBuffer, XML_P_OPENTAG,
							XML_P_CLOSETAG);
					endPos = startPos + XML_P_OPENTAG.length();
					myBuffer.delete(startPos, endPos);
					myBuffer = removeCountOccurence(myBuffer, XML_P_CLOSETAG,
							posOcc);
					if (!found)
						found = true;
				}
				startPos = myBuffer.indexOf(XML_P_OPENCLOSETAG);
				if (startPos == 0 && !found) {
					endPos = startPos + XML_P_OPENCLOSETAG.length();
					myBuffer.delete(startPos, endPos);
					if (!found)
						found = true;
					;
				}
				startPos = myBuffer.indexOf(XML_P_OPENTAGWITHATTRIBS);
				if (startPos == 0 && !found) {
					endPos = myBuffer.indexOf(UIUtil.xmlSuffix);
					endPos++; // delting the entire p start tag
					int posOcc = countPosOccurence(myBuffer,
							XML_P_OPENTAGWITHATTRIBS, XML_P_CLOSETAG);
					myBuffer.delete(startPos, endPos);
					myBuffer = removeCountOccurence(myBuffer, XML_P_CLOSETAG,
							posOcc);
					if (!found)
						found = true;
				}

			}
		}
		return fixBRtags(myBuffer.toString());
	}

	/**
	 * This method looks for all BR tags and if they are <br>
	 * </br> of that form changes them to <br/>
	 */
	public static String fixBRtags(String aStringToFix) {
		try {
			String tempStr = aStringToFix;
			RE regexSpaceBr = new RE("<br>", RE.REG_ICASE);
			tempStr = regexSpaceBr.substituteAll(tempStr, "<br/>");
			regexSpaceBr = new RE("<br >", RE.REG_ICASE);
			tempStr = regexSpaceBr.substituteAll(tempStr, "<br/>");
			regexSpaceBr = new RE("</br>", RE.REG_ICASE);
			tempStr = regexSpaceBr.substituteAll(tempStr, "");
			return tempStr;
		} catch (REException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return aStringToFix;
		}
	}

	/**
	 * This method takes in an incoming string containing xml tags and strips
	 * out the string removing all XML tags from the string. This method expects
	 * only well formed XML strings such that every tag has an open and close
	 * tag.
	 *
	 * @param aStringWithXMLtags
	 * @param aFilterHTML =
	 *            if true the &nbsp; will be filtered out form the results as
	 *            well.
	 * @return String without XML tags.
	 */
	public static String removeXMLtags(String aStringWithXMLtags,
			boolean aFilterHTML) {
		String result = "";
		if (aStringWithXMLtags == null || aStringWithXMLtags.equals(""))
			return "";
		StringBuffer myBuffer = new StringBuffer(aStringWithXMLtags);
		while (myBuffer.indexOf(xmlPrefix) != -1) {
			int startPos = myBuffer.indexOf(xmlPrefix);
			int endPos = myBuffer.indexOf(xmlSuffix);
			if (startPos == -1 || endPos == -1 || startPos >= endPos) {
				break;
			}
			endPos++;
			myBuffer.delete(startPos, endPos);
		}
		if (aFilterHTML) {
			try {

				RE regexSpaceBr = new RE(UIUtil.htmlNBSP, RE.REG_ICASE);
				result = regexSpaceBr.substituteAll(myBuffer.toString(), " ");
				result =removeTinyMCESpaces(result);
			} catch (REException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			result = myBuffer.toString();
		}

		return result;
	}

	/**
	 *
	 * @param stringVal
	 * @return
	 */
	public static String replaceASCIIWithLit(String stringVal)
    {
        if(stringVal != null)
        {
            stringVal = stringVal.replaceAll(tinyMCESpace,  " ").replaceAll(htmlAMPER, "&").replaceAll(htmlLQuot,"\"").replaceAll(htmlRQuot, "\"")
            .replaceAll(htmlDQuot, "\"").replaceAll(htmlSQuot, " '");
        }
        return stringVal;
    }


	public static String removeTinyMCESpaces(String aStringWithSpaces) {
		String result = "";
		if (aStringWithSpaces == null || aStringWithSpaces.equals(""))
			return "";
		StringBuffer myBuffer = new StringBuffer(aStringWithSpaces);
		result = myBuffer.toString();
			try {
				String replaceString=regExSpecCharEscapeFix(UIUtil.tinyMCESpace);
				RE regexSpaceBr = new RE(replaceString);
				result = regexSpaceBr.substituteAll(myBuffer.toString(), " ");

			} catch (REException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



		return result;
	}



	public static String replaceAsciiCharactersOfValidSymbols(String inputString)
	{
		if((inputString==null) || (inputString.equalsIgnoreCase("")))
		{
			return inputString;
		}
		inputString = inputString.replaceAll("&#39;", "'");
		inputString = inputString.replaceAll("&#45;", "-");
		inputString= inputString.replaceAll("&#46;", ".");
		inputString = inputString.replaceAll("&#47;", "/");
		inputString = inputString.replaceAll("&92;", "\\");
		inputString = inputString.replaceAll("&#40;", "(");
		inputString = inputString.replaceAll("&#41;", ")");
		inputString = inputString.replaceAll("&#59;", ";");
		inputString = inputString.replaceAll("&#38;", "\\x26");

		return inputString;
	}



	/**
	 * Return id of matching answer value returns null if none found
	 *
	 * @author jjose
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public static String answerMatchId(Question aQuestion) {
		if (aQuestion == null || !(aQuestion.answerMatchesChoice()))
			return null;
		Collection responseChoices = aQuestion.getResponseChoices();
		Iterator i = responseChoices.iterator();
		boolean hasCodeTable=false;
		String codeTableAnsId=null;
		while (i.hasNext()) {
			QuestionResponse myAnswer = (QuestionResponse) i.next();
			if(myAnswer.getCodeTableName()!=null && !(myAnswer.getCodeTableName().equals(""))){
				hasCodeTable=true;
				codeTableAnsId=myAnswer.getResponseId();
			}
			if (myAnswer.getResponseValue().equalsIgnoreCase(
					aQuestion.getResponse()))
				return myAnswer.getResponseId();
		}
		return codeTableAnsId;
	}

	/**
	 * Return a collection of ActionErrors for presentation purposes only
	 *
	 * return null if there are no problems with the check else returns non null
	 * Collection
	 *
	 * @author jjose
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public static Collection checkQuestionResponses(Collection aUIQuestionGroups) {
		if (aUIQuestionGroups == null || aUIQuestionGroups.size() < 1)
			return null;
		ArrayList myActionErrorList = new ArrayList();
		Iterator iGroup = aUIQuestionGroups.iterator();
		String dependentQuestionID = null;
		String dependentAnswerId = null;
		boolean dependenceMatch = true;
		HashMap questionMap = new HashMap();
		Question mapQuestion = null;
		// CREATE HASHMAP OF QUESTIONS
		while (iGroup.hasNext()) {
			QuestionGroup currentQuestionGroup = (QuestionGroup) iGroup.next();
			if (currentQuestionGroup != null
					&& currentQuestionGroup.getQuestions() != null) {
				Collection currentQuestions = currentQuestionGroup
						.getQuestions();
				if (currentQuestions != null && currentQuestions.size() > 0) {
					Iterator iQuestion = currentQuestions.iterator();
					while (iQuestion.hasNext()) {
						Question currentQuestion = (Question) iQuestion.next();
						if (currentQuestion != null) {
							if(currentQuestion.getResponseDataType()!=null && currentQuestion.getResponseDataType().equalsIgnoreCase("DATE"))
							{
								if(!currentQuestion.getResponse().equals(""))
								{
									//SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
									FastDateFormat df = FastDateFormat.getInstance("MM/dd/yyyy");
									
									//Date testDate = null;
									String testDate = null;
									try{
										//testDate = df.parse(currentQuestion.getResponse());
										testDate = df.format(new Date(currentQuestion.getResponse()));
									}
									//catch(ParseException e)
									catch (Exception e)
									{
										ActionMessage myError = new ActionMessage("errors.date", "<br>"
												+ currentQuestion.getValidationDesc());
										myActionErrorList.add(myError);
										return myActionErrorList;
									}
								}

							}
							if(currentQuestion.getResponseDataType()!=null && currentQuestion.getResponseDataType().equalsIgnoreCase("INTEGER"))
							{
								int valid = 0;
								if(!currentQuestion.getResponse().equals(""))
								{
									try
									{
										valid = Integer.parseInt(currentQuestion.getResponse());
									}
									catch(NumberFormatException n)
									{
										ActionMessage myError = new ActionMessage("errors.numeric", "<br>"
												+ currentQuestion.getValidationDesc());
										myActionErrorList.add(myError);
										return myActionErrorList;
									}
								}
							}
							questionMap.put(currentQuestion.getQuestionId(),
									currentQuestion);
						}
					} // End question while loop
				}
			}
		}
		iGroup = null;
		iGroup = aUIQuestionGroups.iterator();
		while (iGroup.hasNext()) {
			dependenceMatch = true;
			QuestionGroup currentQuestionGroup = (QuestionGroup) iGroup.next();
			dependentQuestionID = currentQuestionGroup.getDependsOnQuestionId();
			dependentAnswerId = currentQuestionGroup.getDependsOnResponseId();
			if (dependentQuestionID != null
					&& !(dependentQuestionID.trim().equals(""))) {
				dependenceMatch = false;
				if (questionMap.containsKey(dependentQuestionID)) {
					mapQuestion = (Question) questionMap
							.get(dependentQuestionID);
					String responseId = answerMatchId(mapQuestion);
					if (responseId != null
							&& responseId.equalsIgnoreCase(dependentAnswerId))
						dependenceMatch = true;
				}
			}
			if (dependenceMatch) {
				if (currentQuestionGroup != null
						&& currentQuestionGroup.getQuestions() != null) {
					Collection currentQuestions = currentQuestionGroup
							.getQuestions();
					if (currentQuestions != null && currentQuestions.size() > 0) {
						Iterator iQuestion = currentQuestions.iterator();
						while (iQuestion.hasNext()) {
							dependenceMatch = true;
							Question currentQuestion = (Question) iQuestion
									.next();
							if (currentQuestion != null) {
								dependentQuestionID = currentQuestion
										.getDependsOnQuestionId();
								dependentAnswerId = currentQuestion
										.getDependsOnResponseId();
								boolean forceRequired = false;
								if (dependentQuestionID != null
										&& !(dependentQuestionID.trim()
												.equals(""))) {
									dependenceMatch = false;
									forceRequired = true;
									if (questionMap
											.containsKey(dependentQuestionID)) {
										mapQuestion = (Question) questionMap
												.get(dependentQuestionID);
										String responseId = answerMatchId(mapQuestion);
										if (responseId != null
												&& responseId
														.equalsIgnoreCase(dependentAnswerId))
											dependenceMatch = true;
									}
								}
								if (dependenceMatch) {
									questionPassesValidation(currentQuestion,
											myActionErrorList, forceRequired);
								}
							}
						} // End question while loop
					}
				}
			} // END dependence Match
		} // End group while
		if (myActionErrorList == null || myActionErrorList.size() < 1)
			return null;
		else
			return myActionErrorList;
	} // END checkQuestionResponses

	/**
	 * passes a collection of action errors back or null if no errors
	 */
	public static void questionPassesValidation(Question aQuestion,
			ArrayList errorList, boolean forceRequired) {

		if (aQuestion.isRequired() || forceRequired) {
			if (aQuestion.getResponse() == null
					|| aQuestion.getResponse().trim().equals("")) {
				if (aQuestion.getValidationDesc() == null
						|| aQuestion.getValidationDesc().trim().equals(""))
					aQuestion.setValidationDesc(aQuestion.getQuestionText());
				ActionMessage myError = new ActionMessage("errors.required", "<br>"
						+ aQuestion.getValidationDesc());
				errorList.add(myError);
			}
		}
	}

	/**
	 * Used to properly set the desc pieces of a UI address object
	 * @param myAddress
	 */
	public static void setAddressDescComponents(Address myAddress){
		if(myAddress!=null){
			String addressTypeId=myAddress.getAddressTypeId();
			myAddress.setAddressType("");
            try{
            	List addressTypeList=CodeHelper.getCodes(PDCodeTableConstants.ADDRESS_TYPE,true);
                myAddress.setAddressType(CodeHelper.getCodeDescriptionByCode(addressTypeList, addressTypeId));
            }
            catch(Exception e){

            }
            String streetTypeId=myAddress.getStreetTypeId();
			myAddress.setStreetType("");
            try{
            	List streetTypeList=CodeHelper.getCodes(PDCodeTableConstants.STREET_TYPE,true);
                myAddress.setStreetType(CodeHelper.getCodeDescriptionByCode(streetTypeList, streetTypeId));
            }
            catch(Exception e){

            }
            String stateId=myAddress.getStateId();
			myAddress.setState("");
            try{
            	List stateList=CodeHelper.getCodes(PDCodeTableConstants.STATE_ABBR,true);
                myAddress.setState(CodeHelper.getCodeDescriptionByCode(stateList, stateId));
            }
            catch(Exception e){

            }
            String countyId=myAddress.getCountyId();
			myAddress.setCounty("");
            try{
            	List countyList=CodeHelper.getCodes(PDCodeTableConstants.COUNTY,true);
                myAddress.setCounty(CodeHelper.getCodeDescriptionByCode(countyList, countyId));
            }
            catch(Exception e){

            }

		}
	}

	/**
	 * Returns a collection of UpdateResponseEvent objects for updating the PD
	 * with answers
	 *
	 * @param aQuestionGroupReponses --
	 *            UI Collection of QuestionGroup objects
	 * @return
	 */
	public static Collection getUpdateResponseEvtFromUIQuestionGroups(
			Collection aUIQuestionGroups, String aRefNumber, String aRefType) {
		if (aUIQuestionGroups == null || aUIQuestionGroups.size() <= 0)
			return aUIQuestionGroups;
		ArrayList myUpdateResponseEventList = new ArrayList();
		Iterator iGroup = aUIQuestionGroups.iterator();
		while (iGroup.hasNext()) {
			QuestionGroup currentQuestionGroup = (QuestionGroup) iGroup.next();
			if (currentQuestionGroup != null
					&& currentQuestionGroup.getQuestions() != null) {
				Collection currentQuestions = currentQuestionGroup
						.getQuestions();
				if (currentQuestions != null && currentQuestions.size() > 0) {
					Iterator iQuestion = currentQuestions.iterator();
					while (iQuestion.hasNext()) {
						Question currentQuestion = (Question) iQuestion.next();
						if (currentQuestion != null) {
							UpdateResponseEvent myResponseEvt = new UpdateResponseEvent();
							myResponseEvt.setResponseId(currentQuestion
									.getResponseId());
							myResponseEvt.setAnswerText(currentQuestion
									.getResponse());
							myResponseEvt.setQuestionId(currentQuestion
									.getQuestionId());
							myResponseEvt.setReferenceId(aRefNumber);
							myResponseEvt.setResponseType(aRefType);
							myUpdateResponseEventList.add(myResponseEvt);
						}
					} // End question while loop
				}
			}
		} // End group while
		return myUpdateResponseEventList;
	} // END method

	/**
	 * Takes in an incoming collection of QuestionAnswerResponseEvent objects
	 *
	 * @param aQuestion_AnsReponses
	 * @return a collection of UI QuestionGroup objects with the question groups
	 *         sorted properly
	 */
	public static Collection mapQuestion_GroupRespEvtsToUIQuestionGroup(
			Collection aQuestionGroupReponses) {
		if (aQuestionGroupReponses == null
				|| aQuestionGroupReponses.size() <= 0)
			return aQuestionGroupReponses;
		ArrayList myQuestionGroups = new ArrayList();
		Iterator iGroup = aQuestionGroupReponses.iterator();
		while (iGroup.hasNext()) {
			QuestionGroupResponseEvent currentQuestionGroup = (QuestionGroupResponseEvent) iGroup
					.next();
			if (currentQuestionGroup != null
					&& currentQuestionGroup.getQuestionResponseEvents() != null) {
				QuestionGroup myNewGroup = new QuestionGroup();
				myNewGroup.setGroupId(currentQuestionGroup.getId());
				myNewGroup.setGroupSequence(currentQuestionGroup.getSequence());
				myNewGroup.setDependsOnQuestionId(currentQuestionGroup
						.getDependentQuestionId());
				myNewGroup.setDependsOnResponseId(currentQuestionGroup
						.getSelectedResponseId());
				Collection currentQuestionResponses = currentQuestionGroup
						.getQuestionResponseEvents();
				if (currentQuestionResponses != null
						&& currentQuestionResponses.size() > 0) {
					ArrayList myQuestions = new ArrayList();
					Iterator iQuestions = currentQuestionResponses.iterator();
					while (iQuestions.hasNext()) {
						QuestionResponseEvent currentQuestionResp = (QuestionResponseEvent) iQuestions
								.next();
						if (currentQuestionResp != null) {
							Question myNewQuestion = new Question();
							ArrayList myResponses = new ArrayList();
							myNewQuestion.setColumnSequence(currentQuestionResp
									.getColumnSequence());
							myNewQuestion
									.setDependsOnQuestionId(currentQuestionResp
											.getDependentQuestionId());
							myNewQuestion
									.setDependsOnResponseId(currentQuestionResp
											.getSelectedResponseId());
							myNewQuestion.setFormatKey(currentQuestionResp
									.getFormatKey());
							myNewQuestion.setQuestionId(currentQuestionResp
									.getId());
							myNewQuestion.setQuestionText(currentQuestionResp
									.getText());
							myNewQuestion.setRequired(currentQuestionResp
									.isRequired());
							myNewQuestion.setRenderQuesNum(currentQuestionResp
									.isRenderQuesNum());
							myNewQuestion.setResponse(currentQuestionResp
									.getRealAnswerText());
							myNewQuestion.setResponseId(currentQuestionResp
									.getResponseId());
							myNewQuestion
									.setResponseDataType(currentQuestionResp
											.getResponseDataType());
							myNewQuestion
									.setResponseNextLine(currentQuestionResp
											.isResponseNewLine());
							myNewQuestion.setRowSequence(Integer
									.parseInt(currentQuestionResp
											.getRowSequence()));
							myNewQuestion.setTextLength(currentQuestionResp.getTextLength());
							if (currentQuestionResp.getUiControlSize() != null)
								myNewQuestion.setUiControlSize(Integer
										.parseInt(currentQuestionResp
												.getUiControlSize()));
							myNewQuestion.setUiControlType(currentQuestionResp
									.getResponseUIControlType());
							myNewQuestion.setValidationDesc(currentQuestionResp
									.getValidationDesc());
							Collection currentPossibleResponses = currentQuestionResp
									.getPossibleResponseEvents();
							if (currentPossibleResponses != null
									&& currentPossibleResponses.size() > 0) {
								Iterator iResponses = currentPossibleResponses
										.iterator();
								while (iResponses.hasNext()) {
									PossibleResponseEvent currentPossibleResp = (PossibleResponseEvent) iResponses
											.next();
									if (currentPossibleResp != null) {
										QuestionResponse myResponse = new QuestionResponse();
										myResponse
												.setDefault(currentPossibleResp
														.isDefault());
										myResponse
												.setResponseDisplayText(currentPossibleResp
														.getDisplayText());
										myResponse
												.setResponseId(currentPossibleResp
														.getId());
										myResponse
												.setResponseValue(currentPossibleResp
														.getResponseValue());
										myResponse.setCodeTableName(currentPossibleResp.getCodeTableName());
										myResponses.add(myResponse);
									}
								}
								myNewQuestion.setResponseChoices(myResponses);
							}
							myQuestions.add(myNewQuestion);
						}
					}
					myNewGroup.setQuestions(myQuestions);
				}
				myQuestionGroups.add(myNewGroup);
			}
		} // END group while
		sortQuestionGroup(myQuestionGroups);
		return myQuestionGroups;
	} // END method

	public static void sortQuestionGroup(List aQuestionGroup) {
		if (aQuestionGroup == null || aQuestionGroup.size() <= 1)
			return;
		Collections.sort(aQuestionGroup);
		Iterator iGroup = aQuestionGroup.iterator();
		while (iGroup.hasNext()) {
			QuestionGroup currentQuestionGroup = (QuestionGroup) iGroup.next();
			Collection aQuestions = currentQuestionGroup.getQuestions();
			Collections.sort((List) aQuestions);
		}
	}

	public static int maxColumnSizeForQuestions(Collection aQuestionGroup) {

		int maxColumnSize = 0;
		if (aQuestionGroup == null || aQuestionGroup.size() <= 1)
			return 0;
		Iterator iGroup = aQuestionGroup.iterator();
		while (iGroup.hasNext()) {
			QuestionGroup currentQuestionGroup = (QuestionGroup) iGroup.next();
			Collection aQuestions = currentQuestionGroup.getQuestions();
			Question currentQuestion = null;
			Iterator i = aQuestions.iterator();
			while (i.hasNext()) {
				currentQuestion = (Question) i.next();
				if (currentQuestion.getColumnSequence() > maxColumnSize)
					maxColumnSize = currentQuestion.getColumnSequence();
			}
		}
		return maxColumnSize;
	} // End order Questions

	/*
	 * Reorders a given List if move up is specified then items in the current
	 * List (curList) move up one position i.e. curList(2) becomes newList(1) if
	 * move up is false then move down is assumed and teh opposite is true i.e
	 * curList(2) becomes newList(3)
	 */
	public static Collection reOrderList(boolean moveUp, List curList) {
		if (curList == null || curList.size() <= 1)
			return curList;
		int listSize = curList.size();
		int newPos = 0;
		List newArr = new ArrayList();
		for (int loopX = 0; loopX < listSize; loopX++) {
			if (moveUp)
				newPos = loopX - 1;
			else
				newPos = loopX + 1;
			if (newPos < 0)
				newPos = listSize - 1;
			if (newPos >= listSize)
				newPos = 0;
			newArr.add(newPos, curList.get(loopX));
		}
		return newArr;
	}

	/**
	 * Returns a Yes or No based on the given value of the boolean field
	 *
	 * @param aField --
	 *            the boolean (true/false) field to be cehcked
	 * @param aCapitals --
	 *            if true return back all caps YES or NO, else return Yes or No
	 * @return -- return yes if the field is true, and no otherwise
	 */
	public static String getYesNo(boolean aField, boolean aCapitals) {
		if (aField)
			if (aCapitals)
				return "YES";
			else
				return "Yes";
		else if (aCapitals)
			return "NO";
		else
			return "No";
	}

	/**
	 * Moves an object within a collection up or down.
	 *
	 * @param moveItUp
	 * @param objToBeMoved
	 * @param objects
	 * @return
	 */
	public static Collection moveIt(boolean moveItUp, Object objToBeMoved,
			List objects) {

		Collection reorderedObjects = new ArrayList();

		if (objects != null && objects.size() > 0) {
			if (moveItUp) {
				reorderedObjects = moveItUp(objToBeMoved, objects);
			} else {
				reorderedObjects = moveItDown(objToBeMoved, objects);
			}
		}

		return reorderedObjects;
	}

	/**
	 * Moves an object within a collection to the previous position.
	 *
	 * @param objToBeMovedUp
	 * @param objects
	 * @return
	 */
	public static Collection moveItUp(Object objToBeMovedUp, List objects) {

		if (objects != null && objects.size() > 0) {
			int originalPos = objects.lastIndexOf(objToBeMovedUp);

			if (originalPos > 0) {
				Object socreToBeMovedDown = objects.get(originalPos - 1);

				objects.set(originalPos, socreToBeMovedDown);
				objects.set(originalPos - 1, objToBeMovedUp);
			}
		}

		return objects;
	}

	/**
	 * Moves an object within a collecion to the next position.
	 *
	 * @param objToBeMovedDown
	 * @param objects
	 * @return
	 */
	public static Collection moveItDown(Object objToBeMovedDown, List objects) {

		if (objects != null && objects.size() > 0) {

			int originalPos = objects.lastIndexOf(objToBeMovedDown);

			if (originalPos < objects.size() - 1) {
				Object socreToBeMovedDown = objects.get(originalPos + 1);

				objects.set(originalPos, socreToBeMovedDown);
				objects.set(originalPos + 1, objToBeMovedDown);
			}
		}

		return objects;
	}

	/**
	 * This is how we are parsing the code out of the CodeId value.
	 *
	 * @param codeId
	 * @return String
	 */
	public String parseCodeId(String codeId) {

		if (codeId == null) {
			return null;
		}

		String code = "";

		//change to + per Greg Lyons
		//int start = codeId.indexOf(":");
		int start = codeId.indexOf("+");

		if (start != -1) {
			code = codeId.substring(start + 1, codeId.length());
		}
		return code;
	}

	/**
	 * This is how we are setting the social security number to display as
	 * XXX-XX-XXXX
	 *
	 * @param ssn
	 * @return String
	 */
	public static String formatSSN(String ssn) {
		SocialSecurity social = new SocialSecurity(ssn);
		return social.getFormattedSSN();
	}

	/**
	 * @param ssn
	 * @return SocialSecurity
	 */
	public static SocialSecurity convertSSN(String ssn) {
		SocialSecurity social = new SocialSecurity(ssn);
		return social;
	}

	/**
	 * @param ssn1
	 * @param ssn2
	 * @param ssn3
	 * @return SocialSecurity
	 */
	public static SocialSecurity convertSSN(String ssn1, String ssn2,
			String ssn3) {
		SocialSecurity social = new SocialSecurity(ssn1, ssn2, ssn3);
		return social;
	}

	/**
	 * This is how we are setting the phone number to display as XXX-XXX-XXXX
	 *
	 * @param phoneNum
	 * @return String
	 */
	public static String formatPhoneNum(String phoneNum) {
		PhoneNumber lPhoneNum = new PhoneNumber(phoneNum);
		return lPhoneNum.getFormattedPhoneNumber();
	}

	/**
	 * Takes a string phone number and returns a PhoneNumber object.
	 *
	 * @param phoneNum
	 * @return PhoneNumber
	 */
	public static PhoneNumber convertPhoneNum(String phoneNum) {

		PhoneNumber lPhoneNum = new PhoneNumber(phoneNum);
		return lPhoneNum;
	}

	/**
	 * Takes the area code, prefix and 4-digit number and returns a PhoneNumber
	 * object.
	 *
	 * @param areaCode
	 * @param prefix
	 * @param fourDigit
	 * @return PhoneNumber
	 */
	public static PhoneNumber convertPhoneNum(String areaCode, String prefix,
			String fourDigit) {

		PhoneNumber lPhoneNum = new PhoneNumber(areaCode, prefix, fourDigit);
		return lPhoneNum;
	}

	/**
	 * This method will take a string(target) and replace any occurences of the
	 * replace string parameter with the replaceWith string parameter.
	 *
	 * @param target
	 * @param replace
	 * @param replaceWith
	 * @return String
	 */
	public static String stringReplace(String target, String replace,
			String replaceWith) {
		if (target == null || replace == null || replaceWith == null
				|| replace.equals(replaceWith)) {
			return target;
		}
		StringBuffer buff = new StringBuffer(target);

		int start = buff.toString().indexOf(replace);
		while (start >= 0) {
			buff = buff.replace(start, start + replace.length(), replaceWith);
			start = buff.toString().indexOf(replace);
		}
		return buff.toString();
	}

	/**
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @return String
	 */
	public static String formatFullName(String firstName, String middleName,
			String lastName) {
		if (lastName == null || firstName.equals("")) {
			return NOT_AVAILABLE;
		}
		StringBuffer full = new StringBuffer();
		full.append(lastName);
		full.append(", " + firstName);
		if (middleName != null) {
			full.append(" " + middleName);
		}
		return full.toString();
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @return String
	 */
	public static String formatFullName(String firstName, String lastName) {
		if (lastName == null || firstName.equals("")) {
			return NOT_AVAILABLE;
		}
		StringBuffer full = new StringBuffer();
		full.append(lastName);
		full.append(", " + firstName);
		return full.toString();
	}

	/**
	 * This method takes an iterator that contains CodeResponseEvents and
	 * attempts to return the CodeResponseEvent that matches the CodeId
	 * paramters (string). If not found, returns null. Iterator must contain
	 * codeResponseEvent objects
	 *
	 * @param codes
	 * @param codeId
	 * @return CodeResponseEvent
	 */
	public static CodeResponseEvent findCodeResponseEvent(Iterator codes,
			String codeId) {
		if (codes != null) {
			while (codes.hasNext()) {
				Object obj = codes.next();
				if (obj instanceof CodeResponseEvent) {
					CodeResponseEvent codeEvt = (CodeResponseEvent) obj;
					if (codeEvt.getCode().equals(codeId)) {
						return codeEvt;
					}
				}
			}
		}
		return null;
	}

	/**
	 *
	 * @param codes
	 * @param codeId
	 * @return event codeEvt
	 */
	public static RaceEthnicityCodeResponseEvent findRaceCodeResponseEvent(
			Iterator codes, String codeId) {
		if (codes != null) {
			while (codes.hasNext()) {
				Object obj = codes.next();
				if (obj instanceof RaceEthnicityCodeResponseEvent) {
					RaceEthnicityCodeResponseEvent codeEvt = (RaceEthnicityCodeResponseEvent) obj;
					if (codeEvt.getCode().equals(codeId)) {
						return codeEvt;
					}
				}
			}
		}
		return null;
	}

	/**
	 * @deprecated use DateUtil.stringToDate
	 */
	public static Date getDateFromString(String dateAsString, String format) {
		Date date = null;
		if (dateAsString == null || dateAsString.equals("")) {
			return null;
		}
		if (format == null || format.equals(""))
			format = UIConstants.DATE_FMT_1;
		java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat(format);
		try {
			date = fmt.parse(dateAsString);
		} catch (ParseException e) {
			return null;
		}

		return date;

	}

	/**
	 * @deprecated use DateUtil.dateToString
	 */
	public static String getStringFromDate(Date aDate, String format) {
		String strDate = null;
		if (aDate == null) {
			return "";
		}
		if (format == null || format.equals(""))
			format = UIConstants.DATE_FMT_1;
		java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat(format);
		strDate = fmt.format(aDate);
		return strDate;

	}

	/**
	 * @deprecated use DateUtil.dateToString
	 */
	public static String getStringFromTimeStamp(Timestamp aTimeStamp) {
		if (aTimeStamp == null) {
			return "";
		}
		return aTimeStamp.toString();
	}

	/**
	 * @deprecated use DateUtil.dateToString
	 */
	public static Timestamp getTimestampFromString(String aStrTimeStamp) {
		if (aStrTimeStamp == null || aStrTimeStamp.trim().equals("")) {
			return null;
		}
		try {
			return Timestamp.valueOf(aStrTimeStamp);
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
			return null;
		}
	}

	/**
	 * @deprecated use DateUtil.dateToString
	 *
	 * This method parses a given java.util.Date and returns the time in the
	 * hh:mm format
	 *
	 * @param date
	 * @return String representing the time in hh:mm format.
	 */
	public static String parseTime(Date aDate) {
		if (aDate == null) {
			return "";
		}
		String timeFormat = "hh:mm";
		//SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		FastDateFormat FULL_DATE_FAST_FORMAT = FastDateFormat.getInstance(timeFormat);
		String timeFormat1 = FULL_DATE_FAST_FORMAT.format(aDate);
		//String timeFormat1 = sdf.format(aDate);
		return timeFormat1;
	}

	/**
	 * Returns month literal of a given date.
	 *
	 * @param aDate
	 * @return
	 */
	public static String getMonthLiteral(Date aDate) {
		String dateStr = DateUtil.dateToString(aDate, MMMM);
		return dateStr;
	}

	/**
	 * Returns the year of a given date.
	 *
	 * @param aDate
	 * @return
	 */
	public static String getYear(Date aDate) {
		String dateStr = DateUtil.dateToString(aDate, YYYY);
		return dateStr;
	}

	/**
	 * Gets a string representation of the date in the following format from the
	 * UIConstants.DATE_FMT_FORMALWSUFFIX DATE_FMT_FORMALWSUFFIX = "MMMM
	 * dd'xx,yyyy"; where xx is the day of the month with suffix
	 *
	 * @param date
	 */
	public static String getFormalDateWithSuffix(Date date) {
		if (date == null)
			return "";
		String pattern = null;
		String dateThPart = UIUtil.getDayOfMonthWithSuffix(date);
		if (dateThPart == null)
			dateThPart = "";
		String strYear = getYear(date);
		if (strYear == null)
			strYear = "";
		String strFullMonth = getMonthLiteral(date);
		if (strFullMonth == null)
			strFullMonth = "";
		if (strFullMonth.equals("") || strYear.equals("")
				|| dateThPart.equals("")) {
			return "";
		}
		String finalDateStr = strFullMonth + " " + dateThPart + ", " + strYear;
		if (finalDateStr == null)
			finalDateStr = "";
		return finalDateStr;

	}

	/**
	 * Gets a string representation of the date in the following format from the
	 * UIConstants.DATE_FMT_REVERESED_FORMAL DATE_FMT_REVERESED_FORMAL = "dd'xx
	 * day of 'MMMM,yyyy"; where xx is the day of the month with suffix
	 *
	 * @param date
	 */
	public static String getFormalReversedDate(Date date) {
		if (date == null)
			return "";
		String pattern = null;
		String dateThPart = UIUtil.getDayOfMonthWithSuffix(date);
		if (dateThPart == null)
			dateThPart = "";
		pattern = "' day of 'MMMM, yyyy";
		String finalDateStr = DateUtil.dateToString(date, pattern);
		if (finalDateStr == null)
			finalDateStr = "";
		return dateThPart + finalDateStr;

	}

	/**
	 * Returns day with proper suffix of a given date.
	 *
	 * @param day
	 * @return
	 */
	public static String getDayOfMonthWithSuffix(Date aDate) {
		String aDay = DateUtil.dateToString(aDate, D);
		Integer dayInteger = new Integer(aDay);
		int day = dayInteger.intValue();
		StringBuffer sbDay = new StringBuffer(dayInteger.toString());
		String dayString = dayInteger.toString();
		if (day > 10 && day < 20) {
			sbDay.append(TH);
		} else {
			if (dayString.length() > 1) {
				dayString = dayString.substring(1);
				dayInteger = new Integer(dayString);
				day = dayInteger.intValue();
			}
			switch (day) {
			case 1:
				sbDay.append(ST);
				break;
			case 2:
				sbDay.append(ND);
				break;
			case 3:
				sbDay.append(RD);
				break;
			case 21:
				sbDay.append(ST);
				break;
			case 31:
				sbDay.append(ST);
				break;

			default:
				sbDay.append(TH);
			}
		}
		return sbDay.toString();
	}

	/**
	 * Returns day with proper suffix of a given date.
	 *
	 * @param day
	 * @return
	 */
	public static String getDayOfMonth(Date aDate) {
		String aDay = DateUtil.dateToString(aDate, D);
		Integer dayInteger = new Integer(aDay);
		int day = dayInteger.intValue();
		StringBuffer sbDay = new StringBuffer(dayInteger.toString());
		String dayString = dayInteger.toString();

		return dayString;
	}

	/**
	 * @deprecated use DateUtil.dateToString
	 *
	 * This method parses a given java.util.Date and returns the date as a
	 * String in the MM/dd/yyyy format
	 * @param date
	 * @return String representing the date in MM/dd/yyyy format.
	 */
	public static String parseDate(Date aDate) {
		if (aDate == null) {
			return "";
		}
		String dateFormat = "MM/dd/yyyy";
		//SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		//String mmddyyyyDate = sdf.format(aDate);
		FastDateFormat FULL_DATE_FAST_FORMAT = FastDateFormat.getInstance(dateFormat);
		String mmddyyyyDate = FULL_DATE_FAST_FORMAT.format(aDate);
		return mmddyyyyDate;
	}

	//////////////// Utility methods to retrieve user Information from session
	// /////////////////
	/**
	 * Retrieves the user ID of the user who is currently logged in.
	 *
	 * Currently the method returns fake data (Leslie's user id). It will
	 * interact with the mojo session object to retrieve actual values whenever
	 * the mojo session is implemented and ready to be used.
	 *
	 * @return String
	 */
	public static String getCurrentUserID() {
		String userId = "UNKNOWN";
		ISecurityManager mgr = (ISecurityManager) ContextManager.getSession()
				.get("ISecurityManager");
		if (mgr != null) {
			IUserInfo user = mgr.getIUserInfo();
			userId = user.getJIMSLogonId();
		}
		return userId;
	}

	/**
	 * Retrieves the user name of the user who is currently logged in.
	 *
	 * Currently the method returns fake data. It will interact with the mojo
	 * session object to retrieve actual values whenever the mojo session is
	 * implemented and ready to be used.
	 *
	 * @return String
	 */
	public static String getCurrentUserName() {
		String userName = "User, Unknown";
		ISecurityManager mgr = (ISecurityManager) ContextManager.getSession()
				.get("ISecurityManager");
		if (mgr != null) {
			IUserInfo user = mgr.getIUserInfo();
			userName = user.getLastName() + ", " + user.getFirstName();
		}
		return userName;
	}

	/**
	 * Retrieves the Agency Name for the user who is currently logged in.
	 *
	 * Currently the method returns fake data. It will interact with the mojo
	 * session object to retrieve actual values whenever the mojo session is
	 * implemented and ready to be used.
	 *
	 * @return String
	 */
	public static String getCurrentUserAgencyName() {
		String CURRENT_USER_AGENCY_NAME = "CurrentUserAgencyName";
		//String agencyName = "Houston Police Department";
		// Check to see if the AgencyName is on the session
		String agencyName = (String) ContextManager.getSession().get(
				CURRENT_USER_AGENCY_NAME);
		// If not on the session, retrieve the agency for the current user
		if (agencyName == null || agencyName.equals("")) {
			ISecurityManager mgr = (ISecurityManager) ContextManager
					.getSession().get("ISecurityManager");
			if (mgr != null) {
				String agencyId = mgr.getIUserInfo().getAgencyId();

				GetAgencyEvent agencyEvent = new GetAgencyEvent();
				agencyEvent.setAgencyId(agencyId);
				IDispatch dispatch = EventManager
						.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(agencyEvent);
				CompositeResponse response = (CompositeResponse) dispatch
						.getReply();

				AgencyResponseEvent agency = (AgencyResponseEvent) MessageUtil
						.filterComposite(response, AgencyResponseEvent.class);

				if (agency != null) {
					ContextManager.getSession().put(CURRENT_USER_AGENCY_NAME,
							agency.getAgencyName());
					agencyName = agency.getAgencyName();
				}
			} else {
				// TODO: REMOVE THIS ONCE SECURITY IS TURNED ON
				agencyName = "Houston Police Department";
			}
		}
		return agencyName;
	}

	/**
	 * Retrieves the Agency ID for the user who is currently logged in.
	 *
	 * Currently the method returns fake data. It will interact with the mojo
	 * session object to retrieve actual values whenever the mojo session is
	 * implemented and ready to be used.
	 *
	 * @return String
	 */
	public static String getCurrentUserAgencyID() {
		String agencyId = "HPD";
		ISecurityManager mgr = (ISecurityManager) ContextManager.getSession()
				.get("ISecurityManager");
		if (mgr != null) {
			IUserInfo user = mgr.getIUserInfo();
			agencyId = user.getAgencyId();
		}
		return agencyId;
	}


	/**
	 *
	 * @return
	 */
	public static String getCurrentUserDepartmentName()
	{
		String CURRENT_USER_DEPARTMENT_NAME = "CurrentUserDepartmentName";
		//String agencyName = "Houston Police Department";
		// Check to see if the AgencyName is on the session
		String departmentName = (String) ContextManager.getSession().get(CURRENT_USER_DEPARTMENT_NAME);
		// If not on the session, retrieve the agency for the current user
		if (departmentName == null || departmentName.equals(""))
		{
			ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
			if (mgr != null)
			{
				String departmentId = mgr.getIUserInfo().getDepartmentId();

				GetDepartmentEvent departmentEvent = new GetDepartmentEvent();
				departmentEvent.setDepartmentId(departmentId);
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(departmentEvent);
				CompositeResponse response = (CompositeResponse) dispatch.getReply();

				DepartmentResponseEvent department =
					(DepartmentResponseEvent) MessageUtil.filterComposite(response, DepartmentResponseEvent.class);

				if (department != null)
				{
					ContextManager.getSession().put(CURRENT_USER_DEPARTMENT_NAME, department.getDepartmentName());
					departmentName = department.getDepartmentName();
				}
			}
			else
			{
				// TODO: REMOVE THIS ONCE SECURITY IS TURNED ON
			    departmentName = "Houston Police Department";
			}
		}
		return departmentName;
	}

	/**
	 * @param streetNum
	 * @param streetName
	 * @param streetType
	 * @param aptNum
	 * @param city
	 * @param state
	 * @param zip
	 * @param additionalZip
	 * @param country
	 * @return String
	 */
	public static String formatAddress(String streetNum, String streetName,
			String streetType, String aptNum, String city, String state,
			String zip, String additionalZip, String country) {
		StringBuffer sb = new StringBuffer();
		if (streetNum != null && !streetNum.equals("")) {
			sb.append(streetNum);
		}
		if (streetName != null && !streetName.equals("")) {
			sb.append(" " + streetName);
		}
		if (streetType != null && !streetType.equals("")) {
			sb.append(" " + streetType);
		}
		if (aptNum != null && !aptNum.equals("")) {
			sb.append(" #" + aptNum);
		}
		if (city != null && !city.equals("")) {
			sb.append(", " + city);
		}
		if (state != null && !state.equals("")) {
			sb.append(" " + state);
		}
		if (zip != null && !zip.equals("")) {
			sb.append(" " + zip);
			if (additionalZip != null && !additionalZip.equals("")) {
				sb.append("-" + additionalZip);
			}
		}
		if (country != null && !country.equals("")) {
			sb.append(", " + country);
		}

		return sb.toString();

	}

	public static String separateByDelim(Collection data, String delim) {
		StringBuffer buffer = new StringBuffer();
		int size = data.size();
		int counter = 0;
		if (data != null) {
			Iterator i = data.iterator();
			while (i.hasNext()) {
				counter++;
				buffer.append((String) i.next());
				if (counter < size) {
					buffer.append(delim);
				}
			}
		}
		return buffer.toString();
	}

	public boolean isInvalidAddress(Address addr) {
		if ((addr.getStreetNumber() == null || addr.getStreetNumber().trim()
				.equals(""))
				&& (addr.getStreetName() == null || addr.getStreetName().trim()
						.equals(""))
				&& (addr.getAptNumber() == null || addr.getAptNumber().trim()
						.equals(""))
				&& (addr.getCity() == null || addr.getCity().trim().equals(""))) {
			return true;
		}
		return false;
	}

	public static Address convertAddressResponseEvent(
			AddressResponseEvent responseEvent) {
		Address myAddress = new Address();
		if (responseEvent == null)
			return myAddress;
		myAddress.setAdditionalZipCode(responseEvent.getAdditionalZipCode());
		myAddress.setAddress2(responseEvent.getAddress2());
		myAddress.setAddressType(responseEvent.getAddressType());
		myAddress.setAddressTypeId(responseEvent.getAddressTypeId());
		myAddress.setAptNumber(responseEvent.getAptNum());
		myAddress.setCity(responseEvent.getCity());
		myAddress.setState(responseEvent.getState());
		myAddress.setStateId(responseEvent.getStateId());
		myAddress.setStreetName(responseEvent.getStreetName());
		myAddress.setStreetNumber(responseEvent.getStreetNum());
		myAddress.setStreetType(responseEvent.getStreetType());
		myAddress.setStreetTypeId(responseEvent.getStreetTypeId());
		myAddress.setZipCode(responseEvent.getZipCode());
		myAddress.setValidated(responseEvent.getValidated());
		return myAddress;
	}

	public static AddressRequestEvent getAddressRequestEvent(Address address) {
		AddressRequestEvent addrRequestEvent = new AddressRequestEvent();
		if (address == null)
			return addrRequestEvent;
		addrRequestEvent.setAdditionalZipCode(address.getAdditionalZipCode());
		addrRequestEvent.setAddress2(address.getAddress2());
		addrRequestEvent.setAddressType(address.getAddressType());
		addrRequestEvent.setAddressTypeId(address.getAddressTypeId());
		addrRequestEvent.setAptNum(address.getAptNumber());
		addrRequestEvent.setCity(address.getCity());
		addrRequestEvent.setState(address.getState());
		addrRequestEvent.setStateId(address.getStateId());
		addrRequestEvent.setStreetName(address.getStreetName());
		addrRequestEvent.setStreetNum(address.getStreetNumber());
		addrRequestEvent.setStreetType(address.getStreetType());
		addrRequestEvent.setStreetTypeId(address.getStreetTypeId());
		addrRequestEvent.setZipCode(address.getZipCode());
		addrRequestEvent.setValidated(address.getValidated());
		return addrRequestEvent;

	}

	public static String getStringFromDouble(double aValue) {
		if (aValue == 0.00)
			return "0.00";
		else
			return Double.toString(aValue);
	}

	public static String getStringFromInt(int aValue) {
		if (aValue == 0)
			return "0";
		else
			return Integer.toString(aValue);
	}

	public static int getIntFromString(String aValue) {
		if (aValue == null || aValue.trim().equals(""))
			return 0;
		else {
			return Integer.parseInt(aValue);
		}
	}

	public static double getDoubleFromString(String aValue) {
		if (aValue == null || aValue.trim().equals(""))
			return 0.0;
		else {
			return Double.parseDouble(aValue);
		}
	}

	public static boolean isGoodAddress(Address addr) {
		if ((addr.getStreetNumber() == null || addr.getStreetNumber().trim()
				.equals(""))
				&& (addr.getStreetName() == null || addr.getStreetName().trim()
						.equals(""))
				&& (addr.getAptNumber() == null || addr.getAptNumber().trim()
						.equals(""))
				&& (addr.getCity() == null || addr.getCity().trim().equals(""))
				&& (addr.getStateId() == null || addr.getStateId().trim()
						.equals(""))
				&& (addr.getZipCode() == null || addr.getZipCode().trim()
						.equals(""))) {
			return false;
		}
		return true;
	}

	public static Collection sortCodesByCodeId(Collection codes) {
		if (codes == null)
			return null;
		if (codes.size() <= 1)
			return codes;
		ArrayList sortedList = new ArrayList();
		boolean isCodeTableResponseEvt = true;
		boolean isCodeResponseEvt = true;
		boolean exit = false;
		CodeResponseEvent codeRespEvt;
		CodeResponseEvent currentBestCodeRespEvt;
		int currentBestPosition = 0;
		Object[] codeArray = codes.toArray();

		for (int loopX = 0; loopX < codeArray.length; loopX++) {
			currentBestCodeRespEvt = (CodeResponseEvent) codeArray[loopX];
			currentBestPosition = loopX;
			for (int loopY = loopX; loopY < codeArray.length; loopY++) {
				try {
					codeRespEvt = (CodeResponseEvent) codeArray[loopY];
					if (compareCodeId(currentBestCodeRespEvt, codeRespEvt) > 0) {
						currentBestPosition = loopY;
						currentBestCodeRespEvt = codeRespEvt;
					}
				} catch (Exception e) {
					return null;
				}
			}
			codeArray[currentBestPosition] = codeArray[loopX];
			codeArray[loopX] = currentBestCodeRespEvt;
			currentBestCodeRespEvt = null;
		}
		for (int loopX = 0; loopX < codeArray.length; loopX++) {
			codeRespEvt = (CodeResponseEvent) codeArray[loopX];
			sortedList.add(loopX, codeRespEvt);

		}

		return sortedList;
	}

	public static Collection sortCodesByStringCodeId(Collection codes) {
		SortedMap map = new TreeMap();
		Iterator iter = codes.iterator();
		while (iter.hasNext()) {
			CodeResponseEvent code = (CodeResponseEvent) iter.next();
			map.put(code.getCode(), code);
		}
		return map.values();
	}

	//	Returns < 0 if existingEvt is < compareEvent
	// Returns > 0 if existingEvt is > compareEvent
	private static int compareCodeId(CodeResponseEvent existingEvt,
			CodeResponseEvent toCompareEvt) {
		int compare = 0;
		if (existingEvt != null && toCompareEvt != null) {
			return (existingEvt.getCodeId()
					.compareTo((toCompareEvt.getCodeId())));
		}
		return compare;
	}

	public static void sendPrintObject(Object aReportBean,
			String aReportKeyName, HttpServletResponse aResponse) {
		IDispatch dispatch = EventManager
				.getSharedInstance(EventManager.REQUEST);

		ReportRequestEvent reportRequestEvent = new ReportRequestEvent();

		reportRequestEvent.addDataObject(aReportBean);
		reportRequestEvent.setReportName(aReportKeyName);

		dispatch.postEvent(reportRequestEvent);

		CompositeResponse compResponse = (CompositeResponse) dispatch
				.getReply();

		ReportResponseEvent aRespEvent = (ReportResponseEvent) MessageUtil
				.filterComposite(compResponse, ReportResponseEvent.class);
		try {

			if (aRespEvent != null) {
				MessageUtil.processReturnException(compResponse);

				aResponse.setContentType("application/x-file-download");
				aResponse.setHeader("Content-disposition",
						"attachment; filename=" + aRespEvent.getFileName()
								+ ".jims");
				aResponse.setHeader("Cache-Control", "must-revalidate");
				aResponse.setContentLength(aRespEvent.getContent().length);
				aResponse.resetBuffer();
				OutputStream os = aResponse.getOutputStream();
				os.write(aRespEvent.getContent(), 0,
						aRespEvent.getContent().length);
				os.flush();
				os.close();
			}
		} catch (Exception e) {
			System.out.println("this is a bad error: " + e.toString());
		}
	}

	/**
	 * Calculates the age in years given a date. Taken from the same code as
	 * pd.juvenile.JuvenileCaseHelper
	 *
	 * @param ageDate
	 * @return age in years, 0 if age parameter is null
	 */
	public static int getAgeInYears(Date ageDate) {
		if (ageDate == null) {
			return 0;
		}
		Calendar birthdate = Calendar.getInstance();
		birthdate.setTime(ageDate);
		Calendar now = Calendar.getInstance();

		int age = 0;
		age = now.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);
		birthdate.add(Calendar.YEAR, age);
		if (now.before(birthdate)) {
			age--;
		}
		return age;
	}

	/**
	 * Converts a string value into a formatted number value using the supplied
	 * mask (default mask used if none supplied)
	 *
	 * @param aValue --
	 *            string value of the number ex: 45677
	 * @param aPatternMask --
	 *            the whole number mask ex:
	 *            UIConstants.NUMBER_DEFAULT_POSITIVE_FORMAT
	 * @param blanksToZero --
	 *            if set to true will take incoming blank or null values of a
	 *            Value and return 0 or if set to false will return just blank
	 * @return
	 */
	public static String formatNumber(String aValue, String aPatternMask,
			boolean blanksToZero, String spacesWhenBlank) {
		if (aValue == null || aValue.trim().equals("")) {
			if (blanksToZero)
				aValue = "0";
			else {
				if (spacesWhenBlank == null)
					spacesWhenBlank = "";
				return spacesWhenBlank;

			}

		}
		try {
			double myTempDouble = UIUtil.getDoubleFromString(aValue);
			Double myObjDouble = new Double(myTempDouble);
			return formatNumber(myObjDouble, aPatternMask);
		} catch (Exception exc) {
			return aValue;
		}
	}

	/**
	 * See Description for formatCurrency(String aValue, String aPatternMask,
	 * boolean blanksToZero)
	 *
	 * @param aValue
	 * @param aCurrencyMask
	 * @return
	 */
	public static String formatNumber(Double aValue, String aPatternMask) {
		if (aPatternMask == null || aPatternMask.equals("")) {
			aPatternMask = UIConstants.NUMBER_DEFAULT_POSITIVE_FORMAT;
		}
		DecimalFormat myFormatter = new DecimalFormat(aPatternMask);
		String output = myFormatter.format(aValue);
		return output;

	}

	/**
	 * Converts a string value into a formatted currency value using the
	 * supplied mask (default mask used if none supplied, expects no commas or
	 * dollar signs)
	 *
	 * @param aValue --
	 *            string value of the currency ex: 4.56
	 * @param aCurrencyMask --
	 *            the currency mask ex:
	 *            UIConstants.CURRENCY_US_DEFAULT_POSITIVE_FORMAT
	 * @param blanksToZero --
	 *            if set to true will take incoming blank or null values of a
	 *            Value and return 0.00 or if set to false will return just
	 *            blank
	 * @return
	 */
	public static String formatCurrency(String aValue, String aCurrencyMask,
			boolean blanksToZero, String spacesWhenBlank) {
		if(aValue != null) {
			try {
				String aTemp = aValue.replace('$', ',');
				RE regexSpaceBr = new RE(",", RE.REG_ICASE);
				aValue = regexSpaceBr.substituteAll(aTemp, "");
			} catch (REException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (aValue == null || aValue.trim().equals("")) {
			if (blanksToZero)
				aValue = "0.00";
			else {
				if (spacesWhenBlank == null)
					spacesWhenBlank = "";
				return spacesWhenBlank;
			}
		}



		try {
			double myTempDouble = UIUtil.getDoubleFromString(aValue);
			Double myObjDouble = new Double(myTempDouble);
			return formatCurrency(myObjDouble, aCurrencyMask);
		} catch (Exception exc) {
			return aValue;
		}
	}

	/**
	 * See Description for formatCurrency(String aValue, String aCurrencyMask,
	 * boolean blanksToZero)
	 *
	 * @param aValue
	 * @param aCurrencyMask
	 * @return
	 */
	public static String formatCurrency(Double aValue, String aCurrencyMask) {
		if (aCurrencyMask == null || aCurrencyMask.equals("")) {
			aCurrencyMask = UIConstants.CURRENCY_US_DEFAULT_POSITIVE_FORMAT;
		}
		DecimalFormat myFormatter = new DecimalFormat(aCurrencyMask);
		String output = myFormatter.format(aValue);
		return output;

	}

	public static String formatCodeTableSessionLength(String sessionLength) {

		int sessionLn = Integer.parseInt(sessionLength);

		StringBuffer sb = new StringBuffer();

		int sessionHr = sessionLn / 10;


		if (sessionHr == 1) {
			sb.append("1 hour");
		}else if (sessionHr!=0){
			sb.append(sessionHr + " hours");
		}

		sb.append((sessionLn%10 == 0) ? "" : " 30 minutes");


		return sb.toString();

	}

	/**
	 * @param input
	 * @return UI-friendly display i.e. 02:00 -> 2 hours
	 */
	public static String getTimeInHours(String input) {
		StringBuffer sb = new StringBuffer();

		if (input.length() == 5) {
			int sessionHr = new Integer(input.substring(0, 2)).intValue();
			int sessionMin = new Integer(input.substring(3, 5)).intValue();

			if (sessionHr == 1) {
				sb.append("1 hour");
			} else if (sessionHr > 1) {
				sb.append(sessionHr + " hours");
			}

			if (sessionHr >= 1 && sessionMin >= 1)
				sb.append(" ");

			if (sessionMin == 1) {
				sb.append("1 minute");
			} else if (sessionMin > 1) {
				sb.append(sessionMin + " minutes");
			}
		}

		return sb.toString();
	}

	public static String stripZeroes(String inNum) {
		String outNum = "";
		if (inNum != null) {
			for (int i = 0; i < inNum.length(); i++) {
				if (inNum.charAt(i) == '0') {
					continue;
				} else {
					outNum = inNum.substring(i);
					break;
				}
			}
		}
		return outNum;
	}
	/**
	 * Creates a name object from a formatted name.  Assumes format is last, first middle.
	 * @param formattedName
	 * @return
	 */
	public static IName getNameFromString(String formattedName)
	{
		IName nameObj = new Name();

		if (formattedName != null && !formattedName.equals(""))
		{
			try
			{
				StringTokenizer strTok = new StringTokenizer(formattedName, " ");

				String lastName = strTok.nextToken();
				lastName = lastName.substring(0, lastName.length()-1);
				String firstName = strTok.nextToken();
				String middleName = "";
				if (strTok.hasMoreTokens())
				{
					middleName = strTok.nextToken();
				}
				nameObj.setLastName(lastName);
				nameObj.setFirstName(firstName);
				nameObj.setMiddleName(middleName);
			}
			catch (Exception e)
			{
				nameObj.setLastName(formattedName);
				nameObj.setFirstName("");
				nameObj.setMiddleName("");
			}
		}
		return nameObj;
	}

	public static IName getUINameObj(IName aNameObj)
	{
		IName uiNameObj=new Name();
		if(aNameObj!=null){
			uiNameObj.setFirstName(aNameObj.getFirstName());
			uiNameObj.setMiddleName(aNameObj.getMiddleName());
			uiNameObj.setLastName(aNameObj.getLastName());
			uiNameObj.setPrefix(aNameObj.getPrefix());
			uiNameObj.setSuffix(aNameObj.getSuffix());
		}
		return uiNameObj;
	}

	/**
	 *
	 *
	 * @param aQuestionGroupReponses --
	 *            UI Collection of QuestionGroup objects
	 * @return
	 */
	public static Collection getQuestionAnswerListFromUICSCQuestionGroups(Collection aUIQuestionGroups)
	{
		if (aUIQuestionGroups == null || aUIQuestionGroups.size() <= 0)
		{
			return aUIQuestionGroups;
		}
		ArrayList questionAnswerList = new ArrayList();

		Iterator iGroup = aUIQuestionGroups.iterator();
		while (iGroup.hasNext()) {
			CSCQuestionGroup currentQuestionGroup = (CSCQuestionGroup) iGroup.next();
			if (currentQuestionGroup != null
					&& currentQuestionGroup.getQuestions() != null)
			{
				Collection currentQuestions = currentQuestionGroup
						.getQuestions();

				if (currentQuestions != null && currentQuestions.size() > 0)
				{
					Iterator iQuestion = currentQuestions.iterator();
					while (iQuestion.hasNext())
					{
						CSCQuestion currentQuestion = (CSCQuestion) iQuestion.next();
						if (currentQuestion != null)
						{
							CSCAnswer answer = new CSCAnswer();
							answer.setQuestionGroupId(currentQuestionGroup.getGroupId());
							answer.setQuestionId(currentQuestion.getQuestionId());
//							set the answers of TEXT, TEXTBOX
							answer.setResponseText(currentQuestion.getResponseText());
//							set the answers of SELECT
							if(currentQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_SELECT))
							{
								answer.setResponseId(null);

								if ((currentQuestion.getResponseId() != null) && (!currentQuestion.getResponseId().equalsIgnoreCase("")))
								{
									Iterator i = currentQuestion.getResponseChoices().iterator();
									while (i.hasNext())
									{
										CSCQuestionResponse possibleAnswer = (CSCQuestionResponse) i.next();
										if ((possibleAnswer.getResponseId().equalsIgnoreCase(currentQuestion.getResponseId())) && (!(possibleAnswer.getResponseValue().equalsIgnoreCase(""))))
										{
											answer.setResponseId(currentQuestion.getResponseId());
										}
									}
								}
							}
//							set the answers of CHECKBOX
							else if(currentQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_CHECKBOX))
							{
								String[] selRespIdsArr = currentQuestion.getSelectedResponseIdsArr();
								if(selRespIdsArr!=null && selRespIdsArr.length >0)
								{
									for(int i=0; i < selRespIdsArr.length; i++)
									{
										String selectedResp = selRespIdsArr[i];
										if((selectedResp!=null) && (!selectedResp.equalsIgnoreCase("")))
										{
											answer.setResponseId(selectedResp);
										}
									}
								}
							}
//							set the answers of RADIO
							else
							{
								answer.setResponseId(currentQuestion.getResponseId());
							}

							questionAnswerList.add(answer);
						}
					} // End question while loop
				}
			}
		} // End group while
		return questionAnswerList;
	} // END method



	/**
	 * Takes in an incoming collection of QuestionAnswerResponseEvent objects
	 *
	 * @param aQuestion_AnsReponses
	 * @return a collection of UI QuestionGroup objects with the question groups
	 *         sorted properly
	 */
	public static Collection mapCSCQuestionGroupRespEvtsToUIQuestionGroup(
			Collection aQuestionGroupReponses)
	{
		if (aQuestionGroupReponses == null
				|| aQuestionGroupReponses.size() <= 0)
			return new ArrayList();

		ArrayList myQuestionGroups = new ArrayList();
		Iterator iGroup = aQuestionGroupReponses.iterator();
		while (iGroup.hasNext())
		{
			CSCQuestionGroupResponseEvent currentQuestionGroup = (CSCQuestionGroupResponseEvent) iGroup
					.next();
			if (currentQuestionGroup != null
					&& currentQuestionGroup.getQuestionResponseEvents() != null)
			{
				CSCQuestionGroup myNewGroup = new CSCQuestionGroup();
				myNewGroup.setGroupId(currentQuestionGroup.getId());
				myNewGroup.setGroupText(currentQuestionGroup.getDisplayText());
				myNewGroup.setGroupTextDetailHeader(currentQuestionGroup.isDisplayTextDetailHeader());
				myNewGroup.setGroupTextAlign(currentQuestionGroup.getDisplayTextAlign());
				myNewGroup.setGroupSequence(currentQuestionGroup.getSequence());

				Collection currentQuestionResponses = currentQuestionGroup
						.getQuestionResponseEvents();

				if (currentQuestionResponses != null
						&& currentQuestionResponses.size() > 0)
				{
					ArrayList myQuestions = new ArrayList();
					Iterator iQuestions = currentQuestionResponses.iterator();
					while (iQuestions.hasNext())
					{
						CSCQuestionResponseEvent currentQuestionResp = (CSCQuestionResponseEvent) iQuestions
								.next();
						if (currentQuestionResp != null)
						{
							CSCQuestion myNewQuestion = new CSCQuestion();
							ArrayList myResponses = new ArrayList();

							myNewQuestion.setRowSequence(currentQuestionResp
											.getRowSequence());
							myNewQuestion.setColumnSequence(currentQuestionResp
									.getColumnSequence());

							if(currentQuestionResp.getSummaryRowSeq() == 0)
							{
								myNewQuestion.setSummaryRowSeq(currentQuestionResp.getRowSequence());
							}
							else
							{
								myNewQuestion.setSummaryRowSeq(currentQuestionResp.getSummaryRowSeq());
							}
							if(currentQuestionResp.getSummaryColSeq() == 0)
							{
								myNewQuestion.setSummaryColSeq(currentQuestionResp.getColumnSequence());
							}
							else
							{
								myNewQuestion.setSummaryColSeq(currentQuestionResp.getSummaryColSeq());
							}
							myNewQuestion.setQuestionId(currentQuestionResp
									.getId());
							myNewQuestion.setQuestionText(currentQuestionResp
									.getText());

							myNewQuestion.setQuestionAlign(currentQuestionResp.getDisplayTextAlign());
							myNewQuestion.setQuestionColumnWidth(currentQuestionResp.getColumnWidth());
							myNewQuestion.setSummaryRespColumnWidth(currentQuestionResp.getSummaryRespColumnWidth());

							myNewQuestion.setRequired(currentQuestionResp.isRequired());
							myNewQuestion.setRequiredImageShown(currentQuestionResp.isRequiredImageShown());

							myNewQuestion.setResponseText(currentQuestionResp
									.getSelectedResponseText());
							myNewQuestion.setResponseId(currentQuestionResp
									.getSelectedResponseId());

							myNewQuestion
							.setResponseNextLine(currentQuestionResp
									.isResponseNewLine());
							myNewQuestion.setEachResponseNewLine(currentQuestionResp.isEachResponseNewLine());

							myNewQuestion.setFormatKey(currentQuestionResp
									.getFormatKey());

							try
							{
								myNewQuestion.setTextLength(Integer.parseInt(currentQuestionResp.getTextLength()));
							}
							catch(Exception ex)
							{
//								Exception parsing the String
							}

							try
							{
								myNewQuestion.setUiControlSize(Integer.parseInt(currentQuestionResp
													.getUiControlSize()));
							}
							catch(Exception ex)
							{
//								Exception parsing the String
							}
							try
							{
								myNewQuestion.setMinValue(Integer.parseInt(currentQuestionResp
													.getMinValue()));
							}
							catch(Exception ex)
							{
//								Exception parsing the String
							}
							try
							{
								myNewQuestion.setMaxValue(Integer.parseInt(currentQuestionResp
													.getMaxValue()));
							}
							catch(Exception ex)
							{
//								Exception parsing the String
							}
							myNewQuestion.setRangeValidationExists(true);
							if((myNewQuestion.getMinValue()==0) && (myNewQuestion.getMaxValue()==0))
							{
								myNewQuestion.setRangeValidationExists(false);
							}

							myNewQuestion.setUiControlType(currentQuestionResp
									.getResponseUIControlType());

							if((myNewQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_TEXT)) ||
								(myNewQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_TEXTBOX)))
							{
								myNewQuestion.setResponseDataType(currentQuestionResp.getResponseDataType());
							}

							setVaildationElementType(myNewQuestion);

							Collection currentPossibleResponses = currentQuestionResp
									.getPossibleResponseEvents();

							if (currentPossibleResponses != null
									&& currentPossibleResponses.size() > 0)
							{
								Iterator iResponses = currentPossibleResponses
										.iterator();
								while (iResponses.hasNext())
								{
									CSCPossibleResponseEvent currentPossibleResp = (CSCPossibleResponseEvent) iResponses
											.next();
									if (currentPossibleResp != null)
									{
										CSCQuestionResponse myResponse = new CSCQuestionResponse();

										myResponse.setResponseId(currentPossibleResp
												.getId());

										myResponse.setResponseDisplayText(currentPossibleResp
												.getDisplayText());

										myResponse.setResponseValue(currentPossibleResp
												.getResponseValue());

										myResponse.setDefault(currentPossibleResp
														.isDefault());


										myResponses.add(myResponse);
									}
								}
								myNewQuestion.setResponseChoices(myResponses);
							}
//							MultiCheck
							if(myNewQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_CHECKBOX))
							{

								if((myNewQuestion.getResponseChoices()!= null) && (myNewQuestion.getResponseChoices().size() > 0))
								{
									ArrayList possibleAnswers = (ArrayList) myNewQuestion.getResponseChoices();
									String[] responseIdsStr = null;

									String selectedRespId = currentQuestionResp.getSelectedResponseId();

									if((selectedRespId != null) && (!selectedRespId.equalsIgnoreCase("")))
									{
										responseIdsStr = new String[possibleAnswers.size()];
										responseIdsStr[0] = selectedRespId;
									}
									myNewQuestion.setSelectedResponseIdsArr(responseIdsStr);
								}
							}

							myQuestions.add(myNewQuestion);
						}
					}
					myNewGroup.setQuestions(myQuestions);
				}
				myQuestionGroups.add(myNewGroup);
			}
		} // END group while
		return myQuestionGroups;
	} // END method


	private static void setVaildationElementType(CSCQuestion myQuestion)
	{
		String responseDataType = myQuestion.getResponseDataType();

		if((responseDataType != null) && (!responseDataType.equalsIgnoreCase("")))
		{
			if(responseDataType.equalsIgnoreCase(CSCQuestion.RESPONSE_VAR_TYPE_ALPHA_NUMERIC))
			{
				myQuestion.setValidationElementType(UIConstants.FORMAT_PRESENTATION_TYPE_ALPHANUMERIC);
				myQuestion.setValidationElementDetailType(UIConstants.DISPLAY_PRESENTATION_TYPE_ALPHANUMERIC_NOSYMBOLS);
			}
			else
			if(responseDataType.equalsIgnoreCase(CSCQuestion.RESPONSE_VAR_TYPE_ALPHA_NUMERIC_WTH_SYMBOLS))
			{
				myQuestion.setValidationElementType(UIConstants.FORMAT_PRESENTATION_TYPE_ALPHANUMERIC);
				myQuestion.setValidationElementDetailType(UIConstants.DISPLAY_PRESENTATION_TYPE_ALPHANUMERIC_SYMBOLS);
			}
			else
			if(responseDataType.equalsIgnoreCase(CSCQuestion.RESPONSE_VAR_TYPE_INTEGER))
			{
				myQuestion.setValidationElementType(UIConstants.FORMAT_PRESENTATION_TYPE_INTEGER);
				myQuestion.setValidationElementDetailType("");
			}
			else
			if(responseDataType.equalsIgnoreCase(CSCQuestion.RESPONSE_VAR_TYPE_NUMERIC))
			{
				myQuestion.setValidationElementType(UIConstants.FORMAT_PRESENTATION_TYPE_NUMERIC);
				myQuestion.setValidationElementDetailType("");
			}
		}
	}


	public static void sortCSCQuestionGroup(List aQuestionGroup, String type)
	{

		if (aQuestionGroup == null || aQuestionGroup.size() <= 0)
			return;
		Collections.sort(aQuestionGroup);
		Iterator iGroup = aQuestionGroup.iterator();
		while (iGroup.hasNext()) {
			CSCQuestionGroup currentQuestionGroup = (CSCQuestionGroup) iGroup.next();
			Collection aQuestions = currentQuestionGroup.getQuestions();
			if (aQuestionGroup != null && aQuestionGroup.size() > 0)
			{
				if(type.equalsIgnoreCase(UIConstants.INPUT))
				{
					InputComparator inputComp = new InputComparator();
					Collections.sort((List) aQuestions,inputComp);
				}
				else
					if(type.equalsIgnoreCase(UIConstants.SUMMARY))
					{
						SummaryComparator summaryComp = new SummaryComparator();
						Collections.sort((List) aQuestions,summaryComp);
					}
			}
		}
	}

	public static int maxColumnSizeForCSCQuestions(Collection aQuestionGroup, String type) {

		int maxColumnSize = 0;
		if (aQuestionGroup == null || aQuestionGroup.size() <= 0)
			return 0;
		Iterator iGroup = aQuestionGroup.iterator();
		while (iGroup.hasNext()) {
			CSCQuestionGroup currentQuestionGroup = (CSCQuestionGroup) iGroup.next();
			Collection aQuestions = currentQuestionGroup.getQuestions();
			CSCQuestion currentQuestion = null;
			Iterator i = aQuestions.iterator();
			while (i.hasNext()) {
				currentQuestion = (CSCQuestion) i.next();
				if(type.equalsIgnoreCase(UIConstants.INPUT))
				{
				if (currentQuestion.getColumnSequence() > maxColumnSize)
					maxColumnSize = currentQuestion.getColumnSequence();
				}
				else
					if(type.equalsIgnoreCase(UIConstants.SUMMARY))
					{
						if (currentQuestion.getSummaryColSeq() > maxColumnSize)
							maxColumnSize = currentQuestion.getSummaryColSeq();
					}
			}
		}
		return maxColumnSize;
	} // End order Questions
	

}

/*
 * $Header:
 * $Revision: 1.0 $
 * $Date: 2005/10/15 13:00:00 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package ui.taglib.logic;

import org.apache.struts.taglib.logic.*;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

/**
 * <p>Custom tag which combines "else" processing and the evaluation of whether a
 * specified condition is true. This tag must be embedded in a
 * <strong>IfTag</strong> and must have a <strong>ThenTag</strong>
 * embedded in it. If the condition is true the body content of the embedded
 * ThenTag is included, otherwise it is ignored.</p>
 *
 * <p><strong>See the IfTag for an example of using this tag.</strong></p>
 * </br>
 * <p>This tag can also be used optionally with the following embedded tags:</p>
 *
 * <ul>
 * <li><strong>ElseTag</strong> - embedding an ElseTag means that if the condition
 * on the parent IfTag is false then the body content of the ElseTag is
 * included, otherwise it is ignored.</br></li>
 *
 * <li><strong>ElseIfTag</strong> - embedding an ElseIfTag performs the same
 * processing of an ElseTag but additionally further conditions can be
 * specified in the same way as an IfTag.</br></li>
 *
 * <li><strong>AndTag</strong> - AndTags should be specified straight after
 * IfTag and before ThenTag, ElseTag or ElseIfTag tags. They allow additional
 * conditions to be specified which are used in conjunction with the IfTag
 * to determine how the ThenTag or ElseTag (or ElseIfTag) tags body
 * content are processed. AndTag must be embedded either in an IfTag,
 * and ElseIfTag or an OrTag only</br></li>
 *
 * <li><strong>OrTag</strong> - OrTags should be specified straight after
 * IfTag and before ThenTag, ElseTag or ElseIfTag tags. They allow additional
 * conditions to be specified which are used in conjunction with the IfTag
 * to determine how the ThenTag or ElseTag (or ElseIfTag) tags body
 * content are processed. OrTag must be embedded either in an IfTag,
 * and ElseIfTag or an AndTag only</br></li>
 *
 * </ul>
 * <p>This tag is based the existing Struts logic tags and equivalent
 * processing can be achieved by setting the <strong>"op"</strong> value as follows:</p>
 *
 * <ul>
 * <li> "Equal" - variable = value (equivalent to the EqualTag)</li>
 * <li> "NotEqual" - variable != value (equivalent to the NotEqualTag)</li>
 * <li> "LessThan" - variable < value (equivalent to the LessThanTag)</li>
 * <li> "LessEqual" - variable <= value (equivalent to the LessEqualTag)</li>
 * <li> "GreaterThan" - variable > value (equivalent to the GreaterThanTag)</li>
 * <li> "GreaterEqual" - variable >= value (equivalent to the GreaterEqualTag)</li>
 * <li> "Match" - value is substring of variable (equivalent to the MatchTag)</li>
 * <li> "NoMatch" - value is NOT substring of variable (equivalent to the NoMatchTag)</li>
 * <li> "Present" - bean etc. is present for request (equivalent to the PresentTag)</li>
 * <li> "NotPresent" - bean etc. is NOT present for request (equivalent to the NotPresentTag)</li>
 * </ul>
 */
public class ElseifTag extends IfTag {

    /**
     * Check the parent "IfTag". If the condition for the parent
     * IfTag is NOT true evaluate the condition for this ElseIfTag and
     * include body content of this tag, otherwise ignore it.
     *
     * The required operator values for the ElseIf condtion are:
     * <ul>
     * <li> "Equal" - variable == value
     * <li> "NotEqual" - variable != value
     * <li> "LessThan" - variable < value
     * <li> "LessEqual" - variable <= value
     * <li> "GreaterThan" - variable > value
     * <li> "GreaterEqual" - variable >= value
     * <li> "Match" - value is substring of variable
     * <li> "NoMatch" - value is NOT substring of variable
     * <li> "Present" - value is present for request
     * <li> "NotPresent" - value is NOT present for request
     * </ul>
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

        // Get the parent Tag
		Tag tag = getParent();

        // ElseifTag must be embedded in either an IfTag, ElseifTag or AndTag
        if (tag == null || !(tag instanceof IfTag) ||
           (tag instanceof AndTag) || (tag instanceof OrTag))
          throw new JspException(messages.getMessage("logic.parent", "IfTag/ElseifTag"));

        IfTag parentTag = (IfTag)tag;

        // Determine whether to include Body or not (Else processing)
        if (parentTag.isCondition()) {

          // Ignore the body of the tag
          condition = false;
          return (SKIP_BODY);

        } else {

          // Determine the condition of this tag, process the body
          condition     = condition();
          return (EVAL_BODY_INCLUDE);

        }


    }
}
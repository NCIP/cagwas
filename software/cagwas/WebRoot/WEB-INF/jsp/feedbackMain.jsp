<%--L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L--%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<div id="FeedbackHeader">
	Feedback
</div>
<div id="browseData">

<span class="er"><html:errors/></span>

<br>
<br>

<div align="center">
<h3>Thanks for using the <bean:message key="project.title"/> Application</h3>
<h3>Please provide us with any feedback you may have</h3>
</div>

<html:form action="feedback.do" method="post">
	
	<table>
		<tr>
			<td width="70%" align="right">
				<bean:message key="label.feedback.like"/>
			</td>
			<td width="30%">
				<html:select property="likeFeature">
					<html:option value=""/>
					<html:options collection="features" property="value" labelProperty="label"/>
				</html:select>
			</td>
		</tr>
		<tr>
			<td width="70%" align="right">
				<bean:message key="label.feedback.dislike"/>
			</td>
			<td width="30%">
				<html:select property="dislikeFeature">
					<html:option value=""/>
					<html:options collection="features" property="value" labelProperty="label"/>
				</html:select>
			</td>
		</tr>
		<tr>
			<td width="70%" align="right">
				<bean:message key="label.feedback.comment"/>
			</td>
			<td width="30%">
				<html:textarea property="comment" cols="35" rows="4"></html:textarea>
			</td>
		</tr>
	</table>
	
	<div class="buttonsBar">
		<html:reset styleClass="btn" />
		<html:submit styleClass="btn" />
	</div>
</html:form>

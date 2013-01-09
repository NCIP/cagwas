<%--L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L--%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<div class="divHeader">
	An Error Has Occured
</div>
<div class="divMain" style="height:200px;">

<div align="center" class="er" style="padding:25px;border:2px dotted red; margin:20px;">
	Our apologies, there has been an internal server error.<br/><br/>
	<a href="javascript:history.back()">Go Back</a> | <a href="aboutSetup.do"><bean:message key="project.title"/> Home</a>
</div>
</div>
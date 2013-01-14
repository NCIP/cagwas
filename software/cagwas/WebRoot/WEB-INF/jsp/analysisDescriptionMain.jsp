<%--L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L--%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationAnalysis" %>

<%
ArrayList analysisCol = (ArrayList)session.getAttribute("analysisCol");
int index = Integer.parseInt(request.getParameter("index"));
SNPAssociationAnalysis analysis = (SNPAssociationAnalysis)analysisCol.get(index);
%>
<h3><% out.print(analysis.getName()); %> Description</h3>
<% out.println(analysis.getDescription()); %>
					
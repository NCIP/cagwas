
<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/WEB-INF/jsp/layoutNoMenubar.jsp" flush="true">
  <tiles:put name="title" value="CGEMS Analysis Description"/>
  <tiles:put name="htmlHead" value="/WEB-INF/jsp/tiles/htmlHead_tile.jsp" /> 
  <tiles:put name="overlib" value="/WEB-INF/jsp/tiles/overlib_tile.jsp" /> 
  <tiles:put name="header" value="/WEB-INF/jsp/header.jsp" /> 
  <tiles:put name="mainForm" value="/WEB-INF/jsp/analysisDescriptionMain.jsp" />
</tiles:insert>

<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<tiles:insert page="/WEB-INF/jsp/layoutNoSidebar.jsp" flush="true">
  <tiles:put name="title" type="string">
  <bean:message key="header.siteMapPage"/>
  </tiles:put>
  <tiles:put name="htmlHead" value="/WEB-INF/jsp/tiles/htmlHead_tile.jsp" /> 
  <tiles:put name="overlib" value="/WEB-INF/jsp/tiles/overlib_tile.jsp" /> 
  <tiles:put name="header" value="/WEB-INF/jsp/header.jsp" /> 
  <tiles:put name="crumbMenu" value="/WEB-INF/jsp/tiles/crumbMenu_tile.jsp" /> 
  <tiles:put name="sideBar" value="/WEB-INF/jsp/tiles/sideBar_tile.jsp" /> 
  <tiles:put name="mainForm" value="/WEB-INF/jsp/siteMapMain.jsp" />
  <tiles:put name="footer" value="/WEB-INF/jsp/footer.jsp" />
</tiles:insert>
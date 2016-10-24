<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@page import="javax.portlet.PortletURL"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.Constants" %>
<%@page import="com.liferay.portlet.PortletURLUtil"%>
<%@page import="com.traintium.books.model.Book"%>
<%@page import="com.traintium.books.service.BookLocalServiceUtil"%>


<portlet:defineObjects />
<liferay-theme:defineObjects />

<%
PortletURL currentURLObj = PortletURLUtil.getCurrent(liferayPortletRequest, liferayPortletResponse);
	String currentURL = currentURLObj.toString();
%>
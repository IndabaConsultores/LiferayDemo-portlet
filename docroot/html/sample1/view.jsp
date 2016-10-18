<%@include file="/html/init.jsp" %>

<%
String nombre = ParamUtil.getString(request, "nombre");
String saludo = portletPreferences.getValue("saludo", "Hola ");
%>

<liferay-ui:message key="texto-info-saluda"></liferay-ui:message>
<liferay-portlet:renderURL var="renderURL"/>
<aui:form action="<%=renderURL%>" method="post" name="name">

<aui:input name="nombre" label="nombre"/>

<aui:button-row>
	<aui:button type="submit" value="saluda"></aui:button>
</aui:button-row>
</aui:form>

<%if(!StringUtils.isEmpty(nombre)){ %>
<%=saludo %> <%=nombre%>!
<%} %>
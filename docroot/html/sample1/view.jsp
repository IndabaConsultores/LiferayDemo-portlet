<%@include file="/html/init.jsp" %>

<%
String nombre = ParamUtil.getString(request, "nombre");
String saludo = portletPreferences.getValue("saludo", "Hola ");
%>

<liferay-portlet:renderURL var="renderURL"/>
<aui:form action="<%=renderURL%>" method="post" name="name">

<aui:input name="nombre"/>

<aui:button-row>
	<aui:button type="submit"></aui:button>
</aui:button-row>
</aui:form>

<%if(!StringUtils.isEmpty(nombre)){ %>
<%=saludo %> <%=nombre%>!
<%} %>
<%@include file="/html/init.jsp" %>

<%
String nombre = ParamUtil.getString(request, "nombre");
String saludo = portletPreferences.getValue("saludo", "Hola ");
%>

<liferay-ui:error key="error-key-nombre-vacio" message="error-nombre-vacio"></liferay-ui:error>
<liferay-ui:error key="error-key-no-num" message="error-key-no-num"></liferay-ui:error>


<liferay-ui:message key="texto-info-saluda"></liferay-ui:message>

<portlet:actionURL name="actionSaludo" var="actionSaludoURL">
</portlet:actionURL>
<liferay-portlet:renderURL var="renderURL"/>
<aui:form action="<%=actionSaludoURL%>" method="post" name="name">

<aui:input name="nombre" label="nombre"/>
<aui:input name="num" label="num"/>

<aui:button-row>
	<aui:button type="submit" value="saluda"></aui:button>
</aui:button-row>
</aui:form>

<%if(!StringUtils.isEmpty(nombre)){ %>
<%=saludo %> <%=nombre%>!
<%} %>
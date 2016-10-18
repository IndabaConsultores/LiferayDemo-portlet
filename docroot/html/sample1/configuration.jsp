<%@include file="/html/init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<%
String saludo = portletPreferences.getValue("saludo", "Hola ");
%>

<aui:form action="<%= configurationURL %>" method="post" name="fm">
    <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

    <aui:input name="preferences--saludo--" type="text" value="<%= saludo %>" />

    <aui:button-row>
       <aui:button type="submit" />
    </aui:button-row>
</aui:form>
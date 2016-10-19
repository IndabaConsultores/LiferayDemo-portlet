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
<aui:form action="<%=actionSaludoURL%>" method="post" name="name" >

<aui:input name="nombre" label="nombre">
</aui:input>
<aui:input name="num" label="num"/>

<aui:input name="email" label="email">
	<aui:validator name="email" />
    <aui:validator name="required"/>
</aui:input>



<aui:button-row>
	<aui:button type="submit" value="saluda"></aui:button>
</aui:button-row>
</aui:form>

<%if(!StringUtils.isEmpty(nombre)){ %>
<%=saludo %> <%=nombre%>!
<%} %>

<aui:script>

var rules = {
		<%= renderResponse.getNamespace() %>nombre: {
	       required: true,
	       rangeLength: [2,20],
	       alpha: true
	    }
	}

	var fieldStrings = {
		<%= renderResponse.getNamespace() %>nombre: {
	       required: "The Force is strong with you, but we still need a name.",
	       rangeLength: "2 to 20 characters Padawan."  
	    }
	}


AUI().use(
	    'aui-form-validator',
	    function(A) {
	        new A.FormValidator(
	            {
	                boundingBox: '#<%= renderResponse.getNamespace() %>name',
	                fieldStrings: fieldStrings,
	                rules: rules,
	                showAllMessages: true
	            }
	        )
	    }
	); 
</aui:script>
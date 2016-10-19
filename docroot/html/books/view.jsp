<%@include file="/html/init.jsp" %>

<liferay-portlet:actionURL var="saveBookURL" name="saveBook"></liferay-portlet:actionURL>
<aui:form action="<%=saveBookURL%>" method="POST">
	
	<aui:fieldset>
		<aui:input name="nombre">
			<aui:validator name="required"/>
		</aui:input>
		<aui:input name="autor">
			<aui:validator name="required"/>
		</aui:input>
		<aui:input name="anio">
			<aui:validator name="required"/>
		</aui:input>
	</aui:fieldset>
	
	<aui:button-row>
		<aui:button type="submit"></aui:button>
	</aui:button-row>
</aui:form>
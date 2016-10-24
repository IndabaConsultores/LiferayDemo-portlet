<%@include file="/html/init.jsp" %>

<%
Book book = (Book)renderRequest.getAttribute("book");
if(book==null){
	book = BookLocalServiceUtil.createBook(0);
}
%>

<h1><%=book.getNombre() %></h1>

<liferay-portlet:actionURL var="saveBookURL" name="saveBook"></liferay-portlet:actionURL>
<aui:form action="<%=saveBookURL%>" method="POST">
	<aui:input name="bookId" type="hidden" value="<%=String.valueOf(book.getBookId())%>"></aui:input>
	<aui:fieldset>
		<aui:input name="nombre" value="<%=book.getNombre()%>">
			<aui:validator name="required"/>
		</aui:input>
		<aui:input name="autor" value="<%=book.getAutor()%>">
			<aui:validator name="required"/>
		</aui:input>
		<aui:input name="anio" value="<%=String.valueOf(book.getAnio())%>">
			<aui:validator name="required"/>
		</aui:input>
	</aui:fieldset>
	
	<aui:button-row>
		<aui:button type="submit"></aui:button>
	</aui:button-row>
</aui:form>
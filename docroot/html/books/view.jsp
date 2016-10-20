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

<%
List<Book> books = (List<Book>)renderRequest.getAttribute("books");
Long booksCount = (Long)renderRequest.getAttribute("booksCount");
int delta = (Integer)ParamUtil.getInteger(renderRequest, "delta", com.traintium.books.Constants.DEFAULT_DELTA);
%>

<liferay-ui:search-container delta="<%=delta%>" emptyResultsMessage="no-books-were-found" total="<%=booksCount.intValue() %>">
	<liferay-ui:search-container-results
		results="<%=books%>"/>
		
		<liferay-ui:search-container-row
			className="com.traintium.books.model.Book"
			keyProperty="bookId"
			modelVar="book">
			
			<liferay-ui:search-container-column-text
				name="name"
				value="<%= book.getNombre() %>"/>
			<liferay-ui:search-container-column-text
				name="autor"
				value="<%= book.getAutor() %>"/>
			<liferay-ui:search-container-column-text
				name="año"
				value="<%= String.valueOf(book.getAnio()) %>"/>
				
			<liferay-ui:search-container-column-text>
				<portlet:renderURL var="bookDetailURL">
					<portlet:param name="viewName" value="/html/books/detail.jsp"/>
					<portlet:param name="bookId" value="<%=String.valueOf(book.getBookId()) %>"/>
				</portlet:renderURL>
				<aui:a href="<%=bookDetailURL %>">detail</aui:a>
			</liferay-ui:search-container-column-text>

			
		</liferay-ui:search-container-row>
		

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<%@include file="/html/init.jsp" %>

<liferay-portlet:renderURL var="searchURL"></liferay-portlet:renderURL>
<aui:form action="<%=searchURL%>" method="POST">
	<aui:fieldset>
		<aui:input name="yearFrom">
			<aui:validator name="number"></aui:validator>
		</aui:input>
		<aui:input name="yearTo">
			<aui:validator name="number"></aui:validator>
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
				
			<liferay-ui:search-container-column-jsp name="Actions"
				path="/html/books/bookAction.jsp" /> 

			
		</liferay-ui:search-container-row>
		

	<liferay-ui:search-iterator />
</liferay-ui:search-container>


<portlet:renderURL var="detailURL">
	<portlet:param name="viewName" value="/html/books/detail.jsp"/>
</portlet:renderURL>
<aui:button-row>
	<aui:button type="submit" href="<%=detailURL%>" value="add"></aui:button>
</aui:button-row>

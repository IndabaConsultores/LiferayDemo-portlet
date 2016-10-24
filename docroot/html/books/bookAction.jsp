<%@include file="/html/init.jsp" %>
<%
	ResultRow row = (ResultRow) request
			.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	Book book = (Book) row.getObject();
%>

<liferay-ui:icon-menu>
	<liferay-portlet:actionURL var="deleteBookURL" name="deleteBook">
		<liferay-portlet:param name="bookId" value="<%=String.valueOf(book.getBookId())%>"/>
	</liferay-portlet:actionURL>
	<liferay-ui:icon image="delete" message="DELETE"
		url="<%=deleteBookURL.toString()%>" />
		
	<portlet:renderURL var="bookDetailURL">
		<portlet:param name="viewName" value="/html/books/detail.jsp"/>
		<portlet:param name="bookId" value="<%=String.valueOf(book.getBookId()) %>"/>
	</portlet:renderURL>
	<liferay-ui:icon image="edit" message="EDIT"
		url="<%=bookDetailURL.toString()%>" />
</liferay-ui:icon-menu>
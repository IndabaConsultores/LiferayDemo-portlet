<%@include file="/html/init.jsp" %>

<%
Book book = (Book)renderRequest.getAttribute("book");
if(book==null){
	book = BookLocalServiceUtil.createBook(0);
}
List<Chapter> chapters = (List<Chapter>)renderRequest.getAttribute("chapters");
%>

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
		
		<portlet:renderURL var="beginURL"/>	
		<aui:button type="button" href="<%=beginURL%>" value="back"></aui:button>
	</aui:button-row>
	
</aui:form>

<%if(book.getBookId()!=0){%>
<liferay-ui:search-container delta="-1" emptyResultsMessage="no-chaps-were-found" total="<%=chapters.size()%>">
	<liferay-ui:search-container-results
		results="<%=chapters%>"/>
		
		<liferay-ui:search-container-row
			className="com.traintium.books.model.Chapter"
			keyProperty="chapterId"
			modelVar="chapter">
			
			<liferay-ui:search-container-column-text
				name="numero"
				value="<%= String.valueOf( chapter.getNumero() )%>"/>
			<liferay-ui:search-container-column-text
				name="name"
				value="<%= chapter.getNombre() %>"/>
				
			<liferay-ui:search-container-column-jsp name="Actions"
				path="/html/books/chapterAction.jsp" /> 

			
		</liferay-ui:search-container-row>
		

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<portlet:renderURL var="chapDetailURL">
	<portlet:param name="viewName" value="/html/books/chapterDetail.jsp"/>
	<portlet:param name="bookId" value="<%=String.valueOf(book.getBookId()) %>"/>
	<portlet:param name="backURL" value="<%=currentURL %>"/>
</portlet:renderURL>
<aui:button-row>
	<aui:button type="submit" href="<%=chapDetailURL%>" value="add chapter"></aui:button>
</aui:button-row>

<%}%>
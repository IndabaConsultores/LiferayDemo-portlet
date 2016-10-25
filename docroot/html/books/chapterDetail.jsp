<%@page import="com.traintium.books.service.ChapterLocalServiceUtil"%>
<%@include file="/html/init.jsp" %>

<%
Chapter chapter = (Chapter)renderRequest.getAttribute("chapter");
if(chapter==null){
	chapter = ChapterLocalServiceUtil.createChapter(0);
}
long bookId=(Long)ParamUtil.getLong(renderRequest, "bookId", 0);
String backURL = ParamUtil.getString(renderRequest, "backURL");
%>

<liferay-portlet:actionURL var="saveChapterURL" name="saveChapter"></liferay-portlet:actionURL>
<aui:form action="<%=saveChapterURL%>" method="POST">
	<aui:input name="redirect" type="hidden" value="<%=backURL%>"></aui:input>
	
	<aui:input name="bookId" type="hidden" value="<%=String.valueOf(bookId)%>"></aui:input>
	<aui:input name="chapterId" type="hidden" value="<%=String.valueOf(chapter.getChapterId())%>"></aui:input>
	<aui:fieldset>
		<aui:input name="numero" value="<%=chapter.getNumero()%>">
			<aui:validator name="required"/>
			<aui:validator name="number"/>
		</aui:input>
		<aui:input name="nombre" value="<%=chapter.getNombre()%>">
			<aui:validator name="required"/>
		</aui:input>
	</aui:fieldset>
	
	<aui:button-row>
		<aui:button type="submit"></aui:button>
	</aui:button-row>
</aui:form>
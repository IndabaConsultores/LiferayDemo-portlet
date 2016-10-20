<%@include file="/html/init.jsp" %>

<%
Book book = (Book)renderRequest.getAttribute("book");
%>

<h1><%=book.getNombre() %></h1>
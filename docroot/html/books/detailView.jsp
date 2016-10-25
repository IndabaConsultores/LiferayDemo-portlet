<%@include file="/html/init.jsp" %>
<%
Book book = (Book)request.getAttribute("book");
%>

<h1><%=book.getNombre() %></h1>
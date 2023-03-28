<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

${records}
${recods.get(0).get("name")}
<%
ArrayList<HashMap> records = (ArrayList<HashMap>)request.getAttribute("records");
%>
<table border="2px">
<tr>
<th>Name</th>
<th>Email</th>
<th>Password</th>
<th>Mobile</th>
<th>Image</th>
<td>Operation</td>
</tr>
<% for(int i=0; i<records.size();i++){%>
<tr>
<td><%=records.get(i).get("name") %></td>
<td><%=records.get(i).get("email") %></td>
<td><%=records.get(i).get("password") %></td>
<td><%=records.get(i).get("mobile") %></td>
<td><img alt="Image" src="upload/<%=records.get(i).get("image") %>" height="100px" width="80px"></td>
<td><a href='update?email=<%=records.get(i).get("email") %>'>update</a> | <a href='delete?email=<%=records.get(i).get("email") %>'>delete</a></td>
</tr>

<%} %>

</table>

</body>
</html>
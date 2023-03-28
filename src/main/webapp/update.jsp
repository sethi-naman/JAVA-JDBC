<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%
ArrayList<HashMap> records = (ArrayList<HashMap>) request.getAttribute("records");
out.println(records);
%>

<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="updaterecord" method="post" enctype=multipart/form-data>
		<input type="text" value="<%=records.get(0).get("name")%>"
			name="name" placeholder="Name"><br> <input type="email"
			value="<%=records.get(0).get("email")%>" name="email"
			placeholder="Email"><br> <input type="password"
			value="<%=records.get(0).get("password")%>" name="password"
			placeholder="Password"><br> <input type="tel"
			value="<%=records.get(0).get("mobile")%>" name="mobile"
			placeholder="Mobile"><br> <input type="file"
			value="upload/<%=records.get(0).get("file")%>" name="file">
		<input type="submit">


	</form>

</body>
</html>
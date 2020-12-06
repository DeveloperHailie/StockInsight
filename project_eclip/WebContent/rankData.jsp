<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.*,java.util.ArrayList"%>

<% 
// file 얻어와서
String[] rank = (String[]) request.getAttribute("rank");
for(int i=0;i<rank.length;i++){ %>
	<%=rank[i]%>
	<% 
	if (i!=(rank.length-1)){
	%>@<% 
	}  
} 
%>
<%-- CSV 형식 --%>
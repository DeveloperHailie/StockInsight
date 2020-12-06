<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="csv.*,java.util.ArrayList"%>
    
<% 
ArrayList<String[]> file = (ArrayList<String[]>)request.getAttribute("file");
for(int i=0;i<file.size();i++){
%>date=<%=file.get(i)[0]%>|presentPrice=<%=file.get(i)[1]%>|sign=<%=file.get(i)[2]%>|difference=<%=file.get(i)[3]%>|volume=<%=file.get(i)[4]%>@<%   
}
%>
<%-- CSV 형식 --%>
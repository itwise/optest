<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>DateUtil Example</title>
</head>
<%
String dateInput = request.getParameter("dateInput");
String dayInput = request.getParameter("dayInput");
%>
<body>
  <form name="frm" method="post" action="dateCalc.jsp">
  날짜: <input type="text" name="dateInput" size="10" value="<%= dateInput == null? "" : dateInput %>">
  
  일수: <input type="text" name="dayInput" size="2" value="<%= dayInput == null? "" : dayInput %>">
  <input type="submit" name="submit" onsubmit="frm.submit();">
  </form>
  <p>
<% 
if (dateInput != null) {
	int day = Integer.parseInt(dayInput);
	Calendar cal = Calendar.getInstance();
	cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateInput));
	cal.add(Calendar.DATE, day);
	String ddateStr = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	String todayStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
%>
  <%= dateInput %>부터 <%= day %>일 후는 <%= ddateStr %> 입니다.
  <br>
  오늘은 <%= todayStr %> 입니다.
<%
}
%>
</body>
</html>
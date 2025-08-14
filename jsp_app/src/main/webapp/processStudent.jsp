<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="student" class="com.tss.model.student" scope="request" />
<jsp:setProperty name="student" property="*" />

<jsp:forward page="result.jsp">
    <jsp:param name="note" value="Data processed successfully" />
</jsp:forward>

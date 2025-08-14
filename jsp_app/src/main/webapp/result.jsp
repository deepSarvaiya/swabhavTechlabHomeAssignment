<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="student" class="com.tss.model.student" scope="request" />

<h2>Student Information</h2>
<p><strong>Name:</strong> <jsp:getProperty name="student" property="name" /></p>
<p><strong>Course:</strong> <jsp:getProperty name="student" property="course" /></p>
<p><strong>Marks:</strong> <jsp:getProperty name="student" property="marks" /></p>

<p style="color:green;">
    <%= request.getParameter("note") %>
</p>

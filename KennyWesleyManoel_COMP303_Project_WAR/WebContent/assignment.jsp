<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="http://code.ionicframework.com/1.0.0/css/ionic.css" />
<script src="http://code.ionicframework.com/1.0.0/js/ionic.bundle.js"></script>
<title>Modify/Delete Assignments</title>
</head>
<body>
	<ion-header-bar class="bar-stable">
	<h4 class="title">View/Edit Assignments</h4>
	</ion-header-bar>
	<ion-content>
	<div class="row header">
		<div class="col">Course Name</div>
		<div class="col">Course Code</div>
		<div class="col">Title</div>
	</div>
	<span style="color: red;">${message}</span> <c:forEach
		items="${assignments}" var="assignment">
		<div class="row">
			<div class="col">${assignment.getCourse().getCourseName()}</div>
			<div class="col">${assignment.getCourse().getCourseCode()}</div>
			<div class="col">${assignment.getTitle()}</div>
		</div>
	</c:forEach> </ion-content>
</body>
</html>
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
<style>
#table {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#table td, #customers th {
	border: 1px solid #ddd;
	padding: 8px;
}

#table tr:nth-child(even) {
	background-color: #ffffff;
}

#table tr:hover {
	background-color: #f2f2f2;
}

#table th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #333;
	color: white;
}
</style>
<title>List Courses</title>
</head>
<body>
	<ion-nav-bar> <ion-nav-back-button class="button-clear">
	<a href="index.html"><i class="ion-arrow-left-c">Back to Home</i></a> </ion-nav-back-button>
	<!-- Initial content --> </ion-nav-bar>
	<form action="CourseController" method="post">
		<ion-header-bar class="bar-stable">
		<h4 class="title">List of your courses</h4>
		</ion-header-bar>
		<ion-content>
		<table id="table">
			<tr>
				<th>Course Code</th>
				<th>Course Name</th>
				<th>Description</th>
				<th>Semester</th>
				<th>Update Course</th>
				<th>Delete Course</th>
			</tr>
			<c:forEach items="${courses}" var="course">
				<tr>
					<td><input readonly="" size="10"
						name="courseCode_${course.getCourseCode()}" type="text"
						value="${course.getCourseCode()}" /></td>
					<td><input name="courseName_${course.getCourseCode()}"
						size="30" type="text" value="${course.getCourseName()}" /></td>
					<td><input name="description_${course.getCourseCode()}"
						size="45" type="text" value="${course.getDescription()}" /></td>
					<td><input name="semester_${course.getCourseCode()}" size="1"
						maxlength="1" type="text" value="${course.getSemester()}" /></td>

					<td>
						<button name="btnUpdate_${course.getCourseCode()}" class="button">Update</button>
					</td>
					<td>
						<button name="btnDelete_${course.getCourseCode()}" class="button">Delete</button>
					</td>
				</tr>
			</c:forEach>
		</table>
		</ion-content>
	</form>
</body>
</html>
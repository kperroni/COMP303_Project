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
<title>List Courses</title>
</head>
<body>
	<form action="CourseController" method="post">
		<ion-header-bar class="bar-stable">
		<h4 class="title">List of your courses</h4>
		</ion-header-bar>
		<ion-content>
		<div class="row header">
			<div class="col">Course Code</div>
			<div class="col">Course Name</div>
			<div class="col">Description</div>
			<div class="col">Semester</div>
			<div class="col"></div>
		</div>
		<c:forEach items="${courses}" var="course">
			<div class="row">
				<div class="col">
					<label class="item item-input"> <input readonly="" name="courseCode_${course.getCourseCode()}" type="text"
						value="${course.getCourseCode()}" />
					</label>
				</div>
				<div class="col">
					<label class="item item-input"> <input name="courseName_${course.getCourseCode()}" type="text"
						value="${course.getCourseName()}" />
					</label>
				</div>
				<div class="col">
					<label class="item item-input"> <input name="description_${course.getCourseCode()}" type="text"
						value="${course.getDescription()}" />
					</label>
				</div>
				<div class="col">
					<label class="item item-input"> <input name="semester_${course.getCourseCode()}" type="text"
						value="${course.getSemester()}" />
					</label>
				</div>
				<div class="col">
					<button name="btnUpdate_${course.getCourseCode()}" class="button">Update</button>
					<button name="btnDelete_${course.getCourseCode()}" class="button">Delete</button>
				</div>
			</div>
		</c:forEach> </ion-content>
	</form>
</body>
</html>
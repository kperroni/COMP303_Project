<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="http://code.ionicframework.com/1.0.0/css/ionic.css" />
<script src="http://code.ionicframework.com/1.0.0/js/ionic.bundle.js"></script>
<title>Project Comp303</title>
</head>
<body>

	<ion-nav-bar> <ion-nav-back-button class="button-clear">
	<a href="index.html"><i class="ion-arrow-left-c">Back to Home</i></a> </ion-nav-back-button>
	<!-- Initial content --> </ion-nav-bar>
	<span style="color: red;">${messageCourse}</span>
	<span style="color: green;">${messageCourseSucess}</span>

	<form action="CourseController?action=addCourse" method="post">
		<h3>Create Course</h3>
		<hr>
		<label class="item item-input"> <span class="input-label">
				Course Code:</span> <input name="courseCode"
			placeholder="Enter Course Code" required="true" type="text" />
		</label> <label class="item item-input"> <span class="input-label">
				Course Name:</span> <input name="courseName"
			placeholder="Enter Course Name" required="true" type="text" />
		</label> <label class="item item-input"> <span class="input-label">
				Description:</span> <input name="courseDescription"
			placeholder="Enter Description" required="true" type="text" />
		</label> <label class="item item-input"> <span class="input-label">
				Semester:</span> <input name="courseSemester" placeholder="Enter Semester"
			required="true" type="text" />
		</label>
		<button type="submit" class="button icon-left ion-android-create">Ok</button>
	</form>
	
</body>
</html>
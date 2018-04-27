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
<title>Modify/Delete Assignments</title>
</head>
<body>
	<ion-nav-bar> <ion-nav-back-button class="button-clear">
	<a href="index.html"><i class="ion-arrow-left-c">Back to Home</i></a> </ion-nav-back-button>
	<!-- Initial content --> </ion-nav-bar>
	<ion-header-bar class="bar-stable">
	</ion-header-bar>
	<form action="Assignments" method="post">
		<ion-header-bar class="bar-stable">
		<h4 class="title">List of your assignments</h4>
		</ion-header-bar>
		<ion-content>
		<div class="row header">
			<div class="col">Course Code</div>
			<div class="col">Course Name</div>
			<div class="col col-20">Title</div>
			<div class="col col-10">Weight</div>
			<div class="col col-20">Due Date</div>
			<div class="col">Type</div>
			<div class="col"></div>
		</div>
		<div class="row header">
			<div class="col col-80">Description</div>
			<div class="col"></div>
		</div>
		<c:forEach items="${assignments}" var="assgn">
			<div class="row">
				<div class="col">
					<label class="item item-input"> <input
						name="assignment-courseCode_${assgn.getId()}" type="text"
						value="${assgn.getCourse().getCourseCode()}" />
					</label>
				</div>
				<div class="col">
					<label class="item item-input"> <input readonly
						name="assignment-courseName_${assgn.getId()}" type="text"
						value="${assgn.getCourse().getCourseName()}" />
					</label>
				</div>
				<div class="col col-20">
					<label class="item item-input"> <input
						name="title_${assgn.getId()}" type="text"
						value="${assgn.getTitle()}" />
					</label>
				</div>
				<div class="col col-10">
					<label class="item item-input"> <input
						name="weight_${assgn.getId()}" type="text"
						value="${assgn.getWeight()}" />
					</label>
				</div>
				<div class="col col-20">
					<label class="item item-input"> <input
						name="dueDate_${assgn.getId()}" type="date"
						value="${assgn.getStringDueDate()}" />
					</label>
				</div>
				<div class="col">
					<label class="item item-input"> <input
						name="type_${assgn.getId()}" type="text"
						value="${assgn.getType()}" />
					</label>
				</div>
				<div class="col">
					<button name="btnUpdate_${assgn.getId()}" class="button">Update</button>
				</div>
			</div>
			<div class="row">
				<div class="col col-80">
					<label class="item item-input"> <input
						name="description_${assgn.getId()}" type="text"
						value="${assgn.getDescription()}" />
					</label>
				</div>
				<div class="col">
					<button name="btnDelete_${assgn.getId()}" class="button">Delete</button>
				</div>
			</div>
		</c:forEach> </ion-content>
	</form>
</body>
</html>
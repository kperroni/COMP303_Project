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
textarea{
	resize: vertical;
}
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
<title>List Assignments</title>
</head>
<body>
	<ion-nav-bar> <ion-nav-back-button class="button-clear">
	<a href="index.html"><i class="ion-arrow-left-c">Back to Home</i></a> </ion-nav-back-button>
	<!-- Initial content --> </ion-nav-bar>
	<form action="Assignments" method="post">
		<ion-header-bar class="bar-stable">
		<h4 class="title">List of your assignments</h4>
		</ion-header-bar>
		<ion-content> <span style="color: red;">${messageErr}</span>
		<span style="color: green;">${message}</span>
		<table id="table">
			<tr>
				<th width="10%">Course Code</th>
				<th width="20%">Course Name</th>
				<th width="20%">Title</th>
				<th width="5%">Weight</th>
				<th width="10%">Due Date</th>
				<th width="10%">Type</th>
				<th width="20%">Description</th>
				<th></th>
			</tr>
			<c:forEach items="${assignments}" var="assgn">
				<tr>
					<td width="10%"><input style="width: 100%"
						name="assignment-courseCode_${assgn.getId()}" type="text"
						value="${assgn.getCourse().getCourseCode()}" /></td>
					<td width="20%"><input style="width: 100%" readonly
						name="assignment-courseName_${assgn.getId()}" type="text"
						value="${assgn.getCourse().getCourseName()}" /></td>
					<td width="20%"><input style="width: 100%"
						name="title_${assgn.getId()}" type="text"
						value="${assgn.getTitle()}" /></td>
					<td width="5%"><input style="width: 100%"
						name="weight_${assgn.getId()}" type="text"
						value="${assgn.getWeight()}" /></td>
					<td width="10%"><input style="width: 100%"
						name="dueDate_${assgn.getId()}" type="date"
						value="${assgn.getStringDueDate()}" /></td>
					<td width="10%"><input style="width: 100%"
						name="type_${assgn.getId()}" type="text"
						value="${assgn.getType()}" /></td>
					<td width="20%"><textarea rows="3" style="width: 100%"
							name="description_${assgn.getId()}">${assgn.getDescription()}</textarea>
					</td>
					<td>
						<button name="btnUpdate_${assgn.getId()}" class="button button-small">Update</button>
						<button name="btnDelete_${assgn.getId()}" class="button button-small">Delete</button>
					</td>
				</tr>
			</c:forEach>
		</table>
		</ion-content>
	</form>
</body>
</html>
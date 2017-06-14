<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html lang="en">
<style>
	.bar {
		fill: steelblue;
	}
	.axis text {
		font: 10px sans-serif;
	}

	.axis path,
	.axis line {
		fill: none;
		stroke: #000;
		shape-rendering: crispEdges;
	}

	.x.axis path {
		display: none;
	}

</style>
<head>
	<title>База данных ГМО</title>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

	<link rel="stylesheet" href='${root}/css/base.css'/>
	<link rel="stylesheet" href='${root}/js/node_modules/react-widgets/dist/css/react-widgets.css'/>
</head>
<body>
<div id="content" class="container"/>
<script type="text/javascript" src="js/build/bundle.js"></script>
</body>
</html>
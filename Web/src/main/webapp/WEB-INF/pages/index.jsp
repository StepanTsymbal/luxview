<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Home page</title>

    <head>

	    <script src="static/js/query-1.11.3.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/marked/0.3.2/marked.min.js"></script>
		<script src="static/js/react/react.min.js"></script>
	    <script src="static/js/react/react-dom.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script>
<!-- 	    <script src="static/js/react/browser.min.js"></script> -->
	    <script src="static/js/app.js"></script>
	    <script type="text/babel" src="static/js/app.js"></script>
	    
	    <link href="<c:url value='/static/css/custom.css' />" rel="stylesheet"></link>

</head>
<body>

	
	<div id="mount-point">If you are reading this, then ReactJS libraries were not downloaded properly. Please, try another Web Browser or check Internet Connection</div>
	<div id="sub-table"></div>

</body>
</html>
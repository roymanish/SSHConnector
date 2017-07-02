<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/devtools.js"></script>
<script src="js/jquery-1.7.js"></script>
</head>
<body>
	<div>
		<div>
			<input type ="button" value="Tail Logs" onclick="tailLog()">
		</div>
		<div style="height: 10px"></div>
		<div>
			<textarea id="logDisplay" rows="300" cols="100">${outputLog}</textarea>
		</div>
	</div>
</body>
</html>
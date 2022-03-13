<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="/resources/common/common.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/css/view.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
	src="/resources/bootstrap/js/bootstrap.js"></script>
<script src="/resources/js/make_time.js"></script>
<title>time list page</title>

<style>
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
	<div class="con">
		<div class="titleBox shadow p-3 mb-5 bg-white rounded">
			<div class="title">타임네임 리스트</div>
		</div>
		<div class="timeNameList"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
</body>
</html>

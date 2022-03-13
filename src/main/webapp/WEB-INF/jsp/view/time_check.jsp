<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/common/common.css" />
<link rel="stylesheet" type="text/css" href="/resources/bootstrap/css/bootstrap.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.js"></script>
<script src="/resources/js/time_check.js"></script>
<title>time_check page</title>

<style>
.title {
	margin-top: 150px;
}

.check {
	width: 30%;
}

.time {
	height: 400px;
}

.time_box {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.time button {
	background-color: #4b4b4b;
	color: #fff;
	width: 70%;
	height: 80px;
	margin-top: 50px;
	border-radius: 15px !important;
}

.time_box .time #currTime {
	margin-top: 150px;
}

</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
	<div class="con">
		<div class="time_box">
			<div class="title shadow p-3 mb-5 bg-body rounded check">
				공부 시간
			</div>
			<div class="time shadow p-3 mb-5 bg-body rounded check">
				<button class="shadow-sm p-3 mb-5 bg-body rounded" id="btn_start" onclick="timeStart();">START</button>
				<div id="startTime"></div>
				<div id="currTime"></div>
			</div>
			
		</div>
	</div>
</body>
</html>		
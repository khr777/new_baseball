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

</style>
</head>
<body>
	<div class="con">
		<div class="time_box">
			<div class="title shadow p-3 mb-5 bg-body rounded check">
				공부 시간
			</div>
			<div class="time shadow p-3 mb-5 bg-body rounded check">
				<button class="shadow-sm p-3 mb-5 bg-body rounded">START</button>
			</div>
		</div>
	</div>
</body>
</html>		
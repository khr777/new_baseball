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
<title>make time page</title>

<style>
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
	<div class="con">
		<div class="timeBox shadow p-3 mb-5 bg-white rounded">
			<div class="timeNameTitle">나만의 시간 만들기</div>
			<div class="timeContent">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="basic-addon1">Time Name</span>
					</div>
					<input type="text" class="form-control" placeholder="시간을 기록할 타임네임을 입력해주세요."
						aria-label="Username" aria-describedby="basic-addon1" id="timeName"
						autofocus="autofocus">
				</div>
			</div>
			<div class="btn_box">
				<button type="button" class="btn btn-warning" onclick="history.back()">back</button>
				<button type="button" class="btn btn-primary btn_submit" onclick="submitMake();" id="btn_join">만들기</button>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
</body>
</html>

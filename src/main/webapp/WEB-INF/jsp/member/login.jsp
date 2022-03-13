<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/common/common.css" />
<link rel="stylesheet" type="text/css" href="/resources/member/member.css" />
<link rel="stylesheet" type="text/css" href="/resources/bootstrap/css/bootstrap.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.js"></script>
<title>login page</title>

</head>
<body>
	<div class="con">
		<div class="login_box shadow p-3 mb-5 bg-white rounded">
			<div class="login_title">LOGIN</div>
			<!-- 로그인 정보 시작 -->
			<div class="login_content">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="basic-addon1">usere ID</span>
					</div>
					<input type="text" class="form-control" placeholder="Enter your ID"
						aria-label="Username" aria-describedby="basic-addon1">
				</div>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="basic-addon1">password</span>
					</div>
					<input type="text" class="form-control" placeholder="Enter your password"
						aria-label="Username" aria-describedby="basic-addon1">
				</div>
			</div>
			<!-- 로그인 정보 끝 -->
			
			<div class="login_btn_box">
				<button type="button" class="btn btn-primary">login</button>
				<a href="./join">join</a>
			</div>
		</div>
		
		<div class="login_footer_title">
				Start your life 
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
</body>
</html>		
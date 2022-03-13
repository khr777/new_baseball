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
<script src="/resources/member/member.js"></script>
<title>join page</title>

</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
	<div class="con">
		<div class="join_box shadow p-3 mb-5 bg-white rounded">
			<div class="join_title">JOIN US</div>
			<!-- 회원가입 입력 정보 시작 -->
			<div class="join_content">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="basic-addon1" >usere ID</span>
					</div>
					<input type="text" class="form-control" placeholder="아이디를 입력해주세요."
						aria-label="Username" aria-describedby="basic-addon1" id="loginId" autofocus="autofocus">
				</div>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="basic-addon1">password</span>
					</div>
					<input type="password" class="form-control" placeholder="영문, 숫자, 특수기호 조합으로 8-20자리 이상 입력해주세요."
						aria-label="Username" aria-describedby="basic-addon1" id="loginPw">
				</div>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="basic-addon1">check password</span>
					</div>
					<input type="password" class="form-control" placeholder="비밀번호 확인을 입력해주세요."
						aria-label="Username" aria-describedby="basic-addon1" id="loginPwCheck">
					
				</div>
				<div style="margin: 0 0 10px 10px; text-align: left; color: red;" class="loginPwCheckText" >비밀번호와  일치하지  않습니다.</div>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="basic-addon1">name</span>
					</div>
					<input type="text" class="form-control" placeholder="이름을 입력해주세요."
						aria-label="Username" aria-describedby="basic-addon1" id="name">
				</div>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="basic-addon1">email</span>
					</div>
					<input type="email" class="form-control" placeholder="이메일을 입력해주세요."
						aria-label="Username" aria-describedby="basic-addon1" id="email">
				</div>
			</div>
			<!-- 회원가입 입력 정보 끝 -->
			
			<div class="join_btn_box">
				<button type="button" class="btn btn-primary" onclick="signUp();" id="btn_join">sign up</button>
				<button type="button" class="btn btn-warning" onclick="history.back()">back</button>
			</div>
		</div>
		
		<div class="join_footer_title">
				This is a healthy lifestyle 
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
</body>
</html>		
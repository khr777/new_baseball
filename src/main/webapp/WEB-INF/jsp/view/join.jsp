<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../resources/css/common.css" rel="stylesheet"  >
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"  >
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" ></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
	section {
		/* width: 40%;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center; */
		margin: auto;
		margin-top: 12%;
	}
	
	.mb-3 {
		flex-direction: column;
		align-items: center;
	}
	
	.col-sm-10 {
		padding-right: 10px; 
		padding-left: 10px;  
	}
	
	.col-form-label span {
		/* width: 78.5%; */
		display: block;
    	border-radius: 5px;
    	background-color: #0d6efd;
    	color: #fff;
    	padding: 10px;
	}
	
</style>
</head>
<body>
	<script>
		function joinSubmit() {
			alert('JOIN 시도 '); 
			$('#joinSubmitBtn').submit(); 
		}
	</script>
	<section>
		<form method="post" action="/game/join/joinSubmit">
			<div class="mb-3 row">
			    <label for="staticEmail" class="col-sm-10 col-form-label t-align-center"><span>NickName</span></label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="staticEmail">
				</div>
			</div>
			<div class="mb-3 row">
			    <label for="inputPassword" class="col-sm-10 col-form-label t-align-center"><span>Password</span></label>
			    <div class="col-sm-10">
			      <input type="password" class="form-control" id="inputPassword">
			    </div>
			</div>
			<div class="mb-3 row m-t-80">
				<div class="btn-box col-sm-10">
					<button id="joinSubmitBtn" class="btn btn-success w-100  p-t-10 p-b-10" onclick="joinSubmit();">START GAME</button>
				</div>
			</div>
		</form>
	</section>
	
</body>
</html>
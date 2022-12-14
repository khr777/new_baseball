jQuery(function(a) {
	$('.loginPwCheckText').hide();
	$('#loginPwCheck').on('keyup' ,function(event) {
		var loginPw = $('#loginPw').val();
		var loginPwCheck = $('#loginPwCheck').val();
		if ( loginPw != loginPwCheck ) {
			$('.loginPwCheckText').show();
		} else {
			$('.loginPwCheckText').hide();
		}
	});

  });

// 회원가입
function signUp() {
	var loginId = $('#loginId').val();
	var loginPw = $('#loginPw').val();
	var loginPwCheck = $('#loginPwCheck').val();
	var name = $('#name').val();
	var email = $('#email').val();

	if (loginId == '' || loginId == undefined) {
		swal("아이디를 입력해주세요.", "", "error")
		.then((value) => {
			$('#loginId').focus();
		});
		return false;
			
	}

	if (loginId.length < 6  ) {
		swal("아이디를 6자 이상 입력해주세요. ", "", "error")
		.then((value) => {
			$('#loginId').focus();
		});
		return false;
			
	}
	
	var chkStyle = /\d/ ;      //체크 방식(숫자)
	
	if(chkStyle.test(loginId.substr(0, 1))){
		swal("아이디는 숫자로 시작할 수 없습니다.", "", "error")
		.then((value) => {
			$('#loginId').focus();
		});
		return false;
	}
	
	var koCheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	if(koCheck.test(loginId)) {
		swal("아이디에는 한글이 포함될 수 없습니다.", "", "error")
		.then((value) => {
			$('#loginId').focus();
		});
		return false;
	}	 
		
	if (loginPw == '' || loginPw == undefined) {
		swal("비밀번호를 입력해주세요.", "", "error")
		.then((value) => {
			$('#loginPw').focus();
		});
		return false;
	}
	
	var regPass = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
	if (!regPass.test(loginPw)) {
		swal("영문, 숫자, 특수기호 조합으로 8-20자리 이상 입력해주세요.", "", "error")
		.then((value) => {
			$('#loginPw').focus();
		});
		return false;
	}


	if (loginPwCheck == '' || loginPwCheck == undefined) {
		swal("비밀번호 확인을 입력해주세요.", "", "error")
		.then((value) => {
			$('#loginPwCheck').focus();
		});
		return false;
	}

	if (name == '' || name == undefined) {
		swal("이름을 입력해주세요.", "", "error")
		.then((value) => {
			$('#name').focus();
		});
		return false;
	}
	
	if ( name.length < 2 ) {
		swal("이름을 2자 이상 입력해주세요.", "", "error")
		.then((value) => {
			$('#name').focus();
		});
		return false;
	}
	
	if ( email == '' || email == undefined) {
		swal("이메일을 입력해주세요.", "", "error")
		.then((value) => {
			$('#email').focus();
		});
		return false;
	};

	if (!email_check(email)) {
		swal("이메일 형식으로 입력해주세요.", "", "error")
		.then((value) => {
			$('#email').focus();
		});
		return false;
	};
	
	console.log('loginId :' + loginId );
	console.log('loginPw :' + loginPw );
	console.log('name :' + name );
	console.log('email :' + email );
	
	$.ajax({
	    type: "POST",
	    url: "/insertJoin",
		// contentType:'application/json; charset=utf-8', // Controller 에서 POST 로 받으려면 입력 X 
	    dataType: 'json',
	    data: {
			"loginId" : loginId,
			"loginPw" : loginPw,
			"name" : name,
			"email" : email
		},
	    success: function(data){
	        if ( data.result == '1') {
				swal("회원가입을 환영합니다😆", "가입 완료", "success")
				.then((value) => {
					location.replace('./login');
				})
			} else if ( data.result == 'overlap' ) {
				swal("중복되는 아이디가 존재합니다.", "", "error")
				.then((value) => {
					$('#loginPw').focus();
				});				
			}
	    },  error:function(request,status,error){
             alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
         }
   });
	
}

// 이메일 형식 체크 
function email_check(email) {
	var regex = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	return regex.test(email);
}

// 로그인 
function submitLogin() {
	var loginId = $('#loginId').val();
	var loginPw = $('#loginPw').val();
	
	if (loginId == '' || loginId == undefined) {
		swal("아이디를 입력해주세요.", "", "error")
		.then((value) => {
			$('#loginId').focus();
		});
		return false;
	}
	
	if (loginPw == '' || loginPw == undefined) {
		swal("비밀번호를 입력해주세요.", "", "error")
		.then((value) => {
			$('#loginPw').focus();
		});
		return false;
	}
	
	$.ajax({
	type: "POST",
	url: "/goLogin",
	// contentType:'application/json; charset=utf-8', // Controller 에서 POST 로 받으려면 입력 X 
	dataType: 'json',
	data: {
		"loginId" : loginId,
		"loginPw" : loginPw
	},
	success: function(data){
	    if ( data.result == '1') {
			swal("로그인 되었습니다.", "", "success")
			.then((value) => {
				location.replace('/timeNameList');
			});
		} else {
			swal("비밀번호를 다시 입력해주세요.", "", "error")
			.then((value) => {
				$('#loginPw').focus();
			});
		}
	},  error:function(request,status,error){
	     alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	 }
	});	
	
		
	
}
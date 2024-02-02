/**
 * 
 */



$(document).ready(function() {

});


function joinSubmit() {
	
	const $loginIdObj = $('#login-id'); 
	const $loginPwObj = $('#login-pw'); 
	const $loginPwCheckObj = $('#login-pw-check'); 
	
	const loginId = $($loginIdObj).val(); 
	const loginPw = $($loginPwObj).val(); 
	const loginPwCheck = $($loginPwCheckObj).val();
	
	const sha256LoginPw = sha256(loginPw);
	const sha256LoginPwCheck = sha256(loginPwCheck);
	
	if ( loginId.length < 2 ) {
		swal('error', '닉네임 재입력', '닉네임을 2자 이상 입력해 주세요.', function(){
			$($loginIdObj).focus() ;
		});
		return false;
	}
	
	
	if ( numberSpecialCheck(loginId) ) {
		swal('error', '닉네임 재입력', '닉네임에는 숫자 또는 특수문자를 포함할 수 없습니다.', function(){
			$($loginIdObj).focus() ;
			
		});
		return false;
	}
	
	if ( loginPw.length < 8 || loginPw.length > 14 ) {
		swal('error', '패스워드 재입력', '패스워드를 8~14 사이의 길이로 입력해 주세요.', function(){
			$($loginPwObj).focus() ;
			
		});
		return false;
	}
	
	if ( sha256LoginPw != sha256LoginPwCheck ) {
		swal('error', '패스워드 불일치', '패스워드가 일치하지 않습니다. 다시 입력해 주세요.', function(){
			$($loginPwObj).focus() ;
		});
		return false;
	}
	
	$('#join-form').find('input[name="passWord"]').val(sha256LoginPw);
	$('#join-form').find('input[name="passWordCheck"]').val(sha256LoginPw);
 
	
	// jQuery는 여러 DOM을 선택하는데 사용되어 명확하게 jQuery 객체 중 [0] 배열 index로 원하는 요소를 지정해야 한다. 
	// $('#join-form')[0].submit();
	
	$("#join-form").submit(function(event) {
        event.preventDefault(); // 기본 폼 제출 방지
        
        const formData = {
            nickName: loginId,
            passWord: sha256LoginPw,
			passWordCheck: sha256LoginPwCheck
        };
        
        $.ajax({
            type: "POST",
            url: "/baseball/member/join-put", // 서버의 엔드포인트 URL
            data: JSON.stringify(formData), // 데이터를 JSON 문자열로 변환
            contentType: "application/json", // 데이터 형식 설정
			cache: false, // 캐싱 문제 방지 (브라우저는 Ajax 요청을 캐시할 수 있으며, 폼 데이터를 변경하더라도 이전 요청의 캐시를 사용할 수 있습니다. 이를 해결하기 위해 캐싱을 방지하는 헤더를 Ajax 요청에 추가하거나, 브라우저 캐시를 비우는 방법을 사용할 수 있습니다.)
            success: function(response) {
                // 성공적으로 요청이 처리되었을 때의 동작

				if ( response.result == '1' ) {
					
					swal('success', '', response.resultMsg, function(){
						// 서버 데이터를 렌더링해주는 thymleaf의 역할로 회원가입 페이지 url이 변경되지 않아 직접 페이지 전환을 처리해주어야 회원가입이 중복 처리되지 않음.
						location.replace(response.view);	
					});
					
						
				} else {
					swal('error', '', response.resultMsg, function(){
						location.reload();	
					});
				}
				
				
				
            },
            error: function(error) {
                // 요청이 실패했을 때의 동작
            }
        });
    });
	
}
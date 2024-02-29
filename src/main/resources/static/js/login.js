/**
 * 
 */



$(document).ready(function() {
});


function loginSubmit() {
	const $loginIdObj = $('#login-id'); 
	const $loginPwObj = $('#login-pw'); 
	
	const loginId = $($loginIdObj).val(); 
	const loginPw = $($loginPwObj).val(); 
	
	const sha256LoginPw = sha256(loginPw);
	
	if ( loginId.length == 0 ) {
		swal('error', '닉네임 입력', '닉네임을 입력해 주세요.', function(){
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
	
	if ( loginPw.length == 0 ) {
		swal('error', '패스워드 입력', '패스워드 입력해 주세요.', function(){
			$($loginPwObj).focus() ;
			
		});
		return false;
	}
	
	$('#login-form').find('input[name="passWord"]').val(sha256LoginPw);
	$($loginPwObj).val('');
 
	
	// jQuery는 여러 DOM을 선택하는데 사용되어 명확하게 jQuery 객체 중 [0] 배열 index로 원하는 요소를 지정해야 한다. 
	// $('#join-form')[0].submit();
	
	$("#login-form").submit(function(event) {
        event.preventDefault(); // 기본 폼 제출 방지
        
        const formData = {
            nickName: loginId,
            passWord: sha256LoginPw
        };
        
        $.ajax({
            type: "POST",
            url: "/baseball/member/login-check", // 서버의 엔드포인트 URL
            data: JSON.stringify(formData), // 데이터를 JSON 문자열로 변환
            contentType: "application/json", // 데이터 형식 설정
			cache: false, // 캐싱 문제 방지 (브라우저는 Ajax 요청을 캐시할 수 있으며, 폼 데이터를 변경하더라도 이전 요청의 캐시를 사용할 수 있습니다. 이를 해결하기 위해 캐싱을 방지하는 헤더를 Ajax 요청에 추가하거나, 브라우저 캐시를 비우는 방법을 사용할 수 있습니다.)
            success: function(response) {
                // 성공적으로 요청이 처리되었을 때의 동작

				if ( response.result == '1' ) {
					
					swal('success', '로그인 성공', response.resultMsg, function(){
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
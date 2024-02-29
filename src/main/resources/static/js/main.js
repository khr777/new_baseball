/**
 * 
 */



$(document).ready(function(){
	
	/* 
	const mainBodySection = $('.body-section');  
	const heightTest1 = $(mainBodySection).height(); //엘리먼트의 높이(내부 요소의 높이)
	const heightTest2 = $(mainBodySection).innerHeight(); //엘리먼트 높이 + padding
	const heightTest3 = $(mainBodySection).outerHeight(); //엘리먼트 높이 + padding + border
	
	console.log('heightTest1 : ' + heightTest1); 	
	console.log('heightTest2 : ' + heightTest2); 	
	console.log('heightTest3 : ' + heightTest3);
	
	const footer = $('.footer-box'); 
	const footerHeightTest1 = $(footer).height(); 	
	const footerHeightTest2 = $(footer).innerHeight(); 	
	const footerHeightTest3 = $(footer).outerHeight(); 	


	console.log('footerHeightTest1 : ' + footerHeightTest1);
	console.log('footerHeightTest2 : ' + footerHeightTest2);
	console.log('footerHeightTest3 : ' + footerHeightTest3);

	
	$(mainBodySection).css('height', heightTest3 - footerHeightTest3 ); 
	*/ 
});

function logout() {
	 $.ajax({
		type: "POST",
        url: "/baseball/member/logout", // 서버의 엔드포인트 URL
        data: null, // 데이터를 JSON 문자열로 변환
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
}

function gameRuleModalOpen() {
	
	
	
	const $rule = $('#rule-modal');
	$($rule).removeClass('none');
	
	const $ballSpan = $($rule).find('.select-ball-box .select-btn span');
	const $roundSpan = $($rule).find('.select-round-box .select-btn span');
	  

	// Ball 선택 
	$($ballSpan).click(function(){
		$(this).addClass('active');
		$($ballSpan).not($(this)).removeClass('active');  
	});
	 

	// Round 선택 
	$($roundSpan).click(function(){
		$(this).addClass('active');
		$($roundSpan).not($(this)).removeClass('active');  
	}); 
	
	// Play 클릭 
	
	$($rule).find('.btn-box .play').click(function(){
		
		const ruleBall = $($rule).find('.select-ball-box .select-btn span.standard').text(); 
		const ruleRound = $($rule).find('.select-round-box .select-btn span.standard').text();
		
		// 응답 데이터를 기반으로 URL 생성
		const responseData = { ball: ruleBall, round: ruleRound };
		var newURL = "/baseball/play/game?" + $.param(responseData);
		
		// 새로운 페이지로 이동
		window.location.href = newURL;
	}); 
	
}

function gameRuleModalClose() {
	
	const $rule = $('#rule-modal');
	$($rule).addClass('none');
	
	$($rule).find('.select .select-btn span').removeClass('active'); 
	$($rule).find('.select .standard').addClass('active'); 
	
}
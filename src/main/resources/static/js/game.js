/**
 * 
 */


$(document).ready(function(){
	
	// 현재 페이지의 URL에서 쿼리 문자열을 가져옵니다.
	const queryString = window.location.search;
	
	// 쿼리 문자열을 파싱하여 파라미터를 객체로 추출합니다.
	const params = new URLSearchParams(queryString);
	
	// 특정 파라미터의 값을 가져옵니다.
	const ball = Number(params.get('ball'));
	const round = Number(params.get('round'));


	
	// random ball 생성
	settingRandomBall(ball, round); 
	
	
	// 게임 시작 세팅 
	settingPlayGame(ball, round);  
	
});


function settingRandomBall(ball, round) {
	const playBallArray = createPlayBallNumber(ball);
	console.log('playBallArray : ' + playBallArray); 
}


function settingPlayGame(ball, round) {
	
	
	
	
	
	//------
	
	
	
	// 파라미터 ball, round 게임 룰값 오류 체크 
	const ballArray = [3, 4, 5]; 
	const roundArray = [5, 10, 15, 20, 25, 30]; 
	
	if ( ballArray.indexOf(ball) < 0 || roundArray.indexOf(round) < 0 ) {
		swal('error', '', '다시 플레이해 주세요.', function(){
			// 서버 데이터를 렌더링해주는 thymleaf의 역할로 회원가입 페이지 url이 변경되지 않아 직접 페이지 전환을 처리해주어야 회원가입이 중복 처리되지 않음.
			window.location.href = '/baseball/main';
		});
		return false; 
	}
	
	
	//------
	
	
	
	const $gameBox = $('.game-contents-box'); 
	
	// 게임 Round(Turn) 초기화 : score-box 클래스 
	const $scoreBox = $($gameBox).find('.game-contents-header .score-nickname-box .score-box');
	const firstRound = 1; 
	
	$($gameBox).find('.round-box .current-round span').html(firstRound); 
	$($scoreBox).find('.current-score').html(firstRound); 
	$($scoreBox).find('.try-score').html(round);
	
	$($gameBox).attr('ball', ball); 
	$($gameBox).attr('round', round); 
	$($gameBox).attr('curr-round', firstRound); 
	
	
	
	
	//-----
	
	
	// 숫자/지우기/엔터 키 입력 
	$($gameBox).find('.game-box .btn-box .btn').click(function(){
		
		const currentRound = $($gameBox).attr('curr-round'); 
		
		const clickedNumber = $(this).text(); 
		putPlayNumber(ball, currentRound, clickedNumber, $(this).attr('class'));	
	}); 
	 
	
	
	
}

function putPlayNumber(paramBall, paramCurrRound, paramClickedNumber, paramClass) {
	 
	const $gameBox = $('.game-contents-box');
	const $scoreBox = $($gameBox).find('.game-contents-header .score-nickname-box .score-box'); 
	let activeCount = 1; 
	
	pushKey($gameBox, $scoreBox, activeCount, paramBall, paramCurrRound, paramClickedNumber, paramClass); 
	
	
	
	
}



function pushKey($gameBox, $scoreBox, activeCount, paramBall, paramCurrRound, paramClickedNumber, paramClass) {
	
	
	$($gameBox).find('.round-box .entered-number').each(function(index) {
		
		// 등록(Enter)
		if ( paramClass.indexOf('enter') > -1 ) {
			
			
			// 플레이 숫자 등록 검증 
			if ( $($gameBox).find('.round-box .entered-number.active').length == paramBall ) {
				
				// 상단 입력 숫자 얻기 
				let pushKeyArray = [];
				pushKeyArray.push($($gameBox).find('.round-box .entered-number.active').eq(0).text());
				pushKeyArray.push($($gameBox).find('.round-box .entered-number.active').eq(1).text());
				pushKeyArray.push($($gameBox).find('.round-box .entered-number.active').eq(2).text());
				
				
				// 등록 숫자판 복사 
				const $addRoundEl = $($gameBox).find('.game-box .round-sample').last().clone();
				
				
				// 상단 입력 숫자 초기화 
				$($gameBox).find('.round-box .entered-number').removeClass('active').html(''); 
			
				const roundClass= 'round-' + paramCurrRound;
				const nextRound = Number(paramCurrRound) + 1;
				
				$($addRoundEl).addClass(roundClass).removeClass('round-sample'); 
				$($addRoundEl).find('.round.game-el span').text(paramCurrRound + ' round'); 
				 
				$($gameBox).find('.game-box .total-round').prepend($addRoundEl);
				$($gameBox).find('.game-box .' + roundClass).show();
				
				$($gameBox).attr('curr-round', nextRound); 
				$($gameBox).find('.round-box .current-round span').html(nextRound); 
				$($scoreBox).find('.current-score').html(nextRound);
				
				
				
				
				
				showPushResult($gameBox, pushKeyArray, paramBall, paramCurrRound, $addRoundEl); 
				
				
				return false;
				
			} else {
				// 등록 숫자 개수 미달 검증 
				
				swal('info', '', '등록할 숫자를 모두 입력해 주세요!!', function(){
				});
				
				return false;
				
			}
			
			
		} else if (paramClass.indexOf('delete') > -1 ) { // 지우기(Delete)
		
			activeCount = $($gameBox).find('.round-box .entered-number.active').length < 1 ? 0 : 1; 
			
			// 플레이 숫자 미입력 검증 
			if ( activeCount == 0 ) {
				
				swal('info', '', '플레이 숫자를 입력해 주세요!', function(){
				});
				
				return false;
				
			} else {
				// 플레이 숫자 지우기 
				
				const $delEl = $($gameBox).find('.round-box .entered-number.active');
				const elIndex = $($delEl).length - 1;  
				$($delEl).eq(elIndex).text(''); 
				$($delEl).eq(elIndex).removeClass('active'); 
				
			}
			
			return false;
			
		} else { // 숫자 입력(Number)
			
			
			
		
			// 플레이 숫자 순차 기입 
			if ( !$(this).hasClass('active') ) {
				
				// 0으로 시작하는 숫자 검증 
				if ( index == 0 && paramClickedNumber == 0 ) {
				
					swal('error', '', '첫 번째 숫자는 0으로 시작할 수 없습니다.', function(){
					});
					
					return false;
				} 
					
				// 숫자 입력 실행 
				$(this).addClass('active'); 
				$(this).text(paramClickedNumber);
				
				return false;  
				
		    } else {
				
				activeCount = $($gameBox).find('.round-box .entered-number.active').length; 
				
				// 플레이 숫자 개수 입력 초과 검증 
				if ( activeCount == paramBall) {
					
					swal('info', '', '플레이 숫자를 등록해 주세요!', function(){
					});
					
					return false; 
					
				} 
				
			}
			
			
			
			// ------
			
			
			
			// 동일한 숫자 입력 검증 
			const $matchingElements = $($gameBox).find('.round-box .entered-number.active').filter(function() {
			    // 각 요소의 텍스트 값을 가져와서 특정 문자열이 포함되어 있는지 확인합니다.
			    return $(this).text().indexOf(paramClickedNumber) > -1;
			});
			
			if ($matchingElements.length > 0) {
				
				swal('error', '', '동일한 숫자는 입력할 수 없습니다!', function(){
				});
				
				return false; 
			}
			
			
		}
		
		activeCount++; 
		
	});
	
}


// 플레이 숫자 결과 검증 후 노출 
function showPushResult($gameBox, pushKeyArray, paramBall, paramCurrRound, $addRoundEl) {
	
	$($addRoundEl).find('.number.game-el span').eq(0).text(pushKeyArray[0]);
	$($addRoundEl).find('.number.game-el span').eq(1).text(pushKeyArray[1]);
	$($addRoundEl).find('.number.game-el span').eq(2).text(pushKeyArray[2]);
	
}



function createPlayBallNumber(ball) {
	
    let playBall = [];

    // 0부터 9까지의 숫자 배열
    var numbers = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];


	for ( var i = 0; i < ball; i++ ) {
		let index = Math.floor(Math.random() * numbers.length);
		let number = numbers[index]; 
		
		if ( i == 0 && number == 0 ) {
	        
	        i--; // 현재 반복을 다시 실행하기 위해 i를 감소시킴
	        continue; // 다음 반복을 시작
	    }	    


		playBall.push(number); 
	    numbers.splice(index, 1); // 선택한 숫자 제거
	
	}

    return playBall;
}
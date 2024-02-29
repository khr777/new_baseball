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
	
	
	
	for ( var i = 1; i <= ball; i++ ) {
		const $answerEl = $('.game-contents-box .answer-box .answer-sample').last().clone();	
		$($answerEl).addClass('answer-' + i).removeClass('answer-sample').show();
		$('.game-contents-box .answer-box').prepend($answerEl);
		
		
		const $roundEl = $('.game-contents-box .round-box .round-box-2 .entered-number-sample').last().clone();	
		$($roundEl).addClass('entered-number-' + i).removeClass('entered-number-sample').show();
		$('.game-contents-box .round-box .round-box-2').prepend($roundEl);
		
		const $roundNumberEl = $('.game-contents-box .game-box .total-round .round-box .el-number').last().clone();	
		$($roundNumberEl).show();
		$('.game-contents-box .game-box .total-round .round-box .number').prepend($roundNumberEl);
		
	}
	
	const playBallArray = createPlayBallNumber(ball);
	
	 const formData = {
        ball: ball,
        round: round,
		playball: JSON.stringify(playBallArray)
    };
    
    $.ajax({
        type: "POST",
        url: "/baseball/play/start-game", // 서버의 엔드포인트 URL
        data: JSON.stringify(formData), // 데이터를 JSON 문자열로 변환
        contentType: "application/json", // 데이터 형식 설정
		cache: false, // 캐싱 문제 방지 (브라우저는 Ajax 요청을 캐시할 수 있으며, 폼 데이터를 변경하더라도 이전 요청의 캐시를 사용할 수 있습니다. 이를 해결하기 위해 캐싱을 방지하는 헤더를 Ajax 요청에 추가하거나, 브라우저 캐시를 비우는 방법을 사용할 수 있습니다.)
        success: function(response) {
            // 성공적으로 요청이 처리되었을 때의 동작

			if ( response.result != '1' ) {
				swal('error', '', response.resultMsg, function(){
					location.replace(response.view);
				});
			}
			
        },
        error: function(error) {
            // 요청이 실패했을 때의 동작
        }
    });
	
	 
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
	// $($scoreBox).find('.current-score').html(firstRound);
	$($scoreBox).find('.current-score').html(round); 
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
	
	if ( $($gameBox).attr('check-playing') == '0' ) {
		swal('error', '', '게임이 종료되었습니다.<br>다시 시작해 주세요!', function(){
		});
		return false;
	}
	
	pushKey($gameBox, $scoreBox, activeCount, paramBall, paramCurrRound, paramClickedNumber, paramClass); 
	
	
	
	
}



function pushKey($gameBox, $scoreBox, activeCount, paramBall, paramCurrRound, paramClickedNumber, paramClass) {
	
	
	$($gameBox).find('.round-box .entered-number').each(function(index) {
		
		// 등록(Enter)
		if ( paramClass.indexOf('enter') > -1 ) {
			
			
			// 플레이 숫자 등록 검증 통과 
			if ( $($gameBox).find('.round-box .entered-number.active').length == paramBall ) {
				
				settingNextRound($gameBox, $scoreBox, paramBall, paramCurrRound);
				
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
function showPushResult($gameBox, pushKeyArray, paramBall, paramCurrRound, $addRoundEl, playball) {
	
	const playballArray = playball.split(',');
	let strikeCount = 0; 
	let ballCount = 0;
	let outCount = 0; 
	
	for ( var i = 0; i < paramBall; i++ ) {
		$($addRoundEl).find('.number.game-el span').eq(i).text(pushKeyArray[i]);
		
	}
	
	
	$.each(pushKeyArray, function(index, value){
		
		if ( playballArray[index] == value ) {
			strikeCount++;
		} else {
			if ( playballArray.indexOf(value) > -1 ) {
				ballCount++;
			} else {
				outCount++;
			}
		}
	});
	
	
	if ( outCount != paramBall ) {
		if ( strikeCount > 0 ) {
			$($addRoundEl).find('.el-matching[name="strike"]').text(strikeCount + 'S').show();	
		}
		
		if ( ballCount > 0 ) {
			$($addRoundEl).find('.el-matching[name="ball"]').text(ballCount + 'B').show();	
		}	
	} else {
		$($addRoundEl).find('.el-matching[name="out"]').text('out').show();
	}
	
	
	
	
	// 마지막 라운드! 
	const round = $('.game-contents-box').attr('round');
	const nextRound = $('.game-contents-box').attr('curr-round');
	
	if ( round == nextRound ) {
		swal('warning', '', '마지막 라운드 입니다!<br>신중하게 공격해 주세요!', function(){
					
		});	
	}
	
	
	
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


// 플레이 숫자를 맞추면 미션 성공으로 게임 종료, 라운드가 남았다면 이어서 플레이할 수 있도록 초기화 
function settingNextRound($gameBox, $scoreBox, paramBall, paramCurrRound) {
	
	 
	
	// 상단 입력 숫자 얻기 
	let pushKeyArray = [];
	
	for ( var i = 0; i < paramBall; i++ ) {
		pushKeyArray.push($($gameBox).find('.round-box .entered-number.active').eq(i).text());	
	}
	
	$.ajax({
        type: "POST",
        url: "/baseball/play/check-result", // 서버의 엔드포인트 URL
        data: JSON.stringify(pushKeyArray), // 데이터를 JSON 문자열로 변환
        contentType: "application/json", // 데이터 형식 설정
		cache: false, // 캐싱 문제 방지 (브라우저는 Ajax 요청을 캐시할 수 있으며, 폼 데이터를 변경하더라도 이전 요청의 캐시를 사용할 수 있습니다. 이를 해결하기 위해 캐싱을 방지하는 헤더를 Ajax 요청에 추가하거나, 브라우저 캐시를 비우는 방법을 사용할 수 있습니다.)
        success: function(response) {
            // 성공적으로 요청이 처리되었을 때의 동작



			const $answerBox = $('.game-contents-box .answer-box-parent');
			const resultArray = response.playball.split(',');

			if ( response.result == '1' ) {
				
				swal('success', '', response.resultMsg, function(){
					
					showPlayballResultNumber($answerBox,resultArray );
					$($answerBox).find('.game-result.success').show();
					$('#success-modal').css('display', 'flex');
					
					$($gameBox).attr('check-playing', 0);
					
				});
				
			} else {
				
				/*
				* 이어서 플레이할 수 있도록 초기화 
				*/ 
				
				
				
				if ( paramCurrRound == $($gameBox).attr('round') ) {
					swal('warning', '', '공격 실패군요..<br>다시 도전해 보세요!', function(){
						
						$($scoreBox).find('.current-score').html(0);
						
						showPlayballResultNumber($answerBox,resultArray );
						
						$($answerBox).find('.game-result.fail').show();
						$('#success-modal').css('display', 'flex');
						$($gameBox).attr('check-playing', 0);
					});
					return false;
				}
				
				
				
				// 등록 숫자판 복사 
				const $addRoundEl = $($gameBox).find('.game-box .round-sample').last().clone();
				
				
				// 상단 입력 숫자 초기화 
				$($gameBox).find('.round-box .entered-number').removeClass('active').html(''); 
			
				const roundClass= 'round-' + paramCurrRound;
				const nextRound = Number(paramCurrRound) + 1;
				
				$($addRoundEl).addClass(roundClass).removeClass('round-sample'); 
				$($addRoundEl).find('.round.game-el span').text(paramCurrRound + ' round'); 
				
				
				// 등록한 플레이 숫자판을 10개씩 나열하기 위한 로직 
				let standard = 10;
				
				if ( paramCurrRound == 1  ) {
					$($gameBox).find('.game-box .total-round').append('<div class="total-round-standard-' + standard + ' standard"></div>');
					$($gameBox).find('.game-box .total-round .total-round-standard-' + standard).append($addRoundEl);
				} else if ( ( paramCurrRound - 1  ) % standard === 0 ) {
					
					standard = ( paramCurrRound - 1 ) + standard
					
					$($gameBox).find('.game-box .total-round').prepend('<div class="total-round-standard-' + standard + ' standard"></div>');
					$($gameBox).find('.game-box .total-round .total-round-standard-' + standard).prepend($addRoundEl);	
					
					
				} else {
					
					$($gameBox).find('.game-box .total-round .standard').first().prepend($addRoundEl);	
				}
				
				$($gameBox).find('.game-box .' + roundClass).show();
				
				$($gameBox).attr('curr-round', nextRound); 
				$($gameBox).find('.round-box .current-round span').html(nextRound); 
				$($scoreBox).find('.current-score').html($($scoreBox).find('.try-score').text() - (nextRound - 1 ));
				
				
				
				showPushResult($gameBox, pushKeyArray, paramBall, paramCurrRound, $addRoundEl, response.playball);
			}
			
        },
        error: function(error) {
            // 요청이 실패했을 때의 동작
        }
    });
	
	
	
				
}


function restartGame() {
	location.reload();	
}



function gameResultModalClose() {
	$('#success-modal').css('display', 'none');	
}

function showPlayballResultNumber($answerBox,resultArray ) {
	$.each($($answerBox).find('.answer-el'), function(index, val){
		$(this).text(resultArray[index]);
	});	
}
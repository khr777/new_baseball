jQuery(function(a) {
	getTime();
});	

function getTime() {
	var d = new Date();	// 현재 날짜와 시간
	var hur = d.getHours();		// 시
	var min = d.getMinutes();	//분
	var sec = d.getSeconds();	//초

	
	var time = "현재 시간 : " + hur + "시 " + min + "분 " + sec + "초"	// 형식 지정
	
	$('#currTime').html(time);	
	
	setTimeout(getTime, 1000);	//1000밀리초(1초) 마다 반복
}

function timeStart() {
	swal("응원할게요!", "시작 !!", "success")
	.then((value) => {
		var d = new Date();	// 현재 날짜와 시간
		var hur = d.getHours();		// 시
		var min = d.getMinutes();	//분
		var sec = d.getSeconds();	//초
	
		var time = "시작 시간 : " + hur + "시 " + min + "분 " + sec + "초"	// 형식 지정
		
		$('#startTime').html(time);	
		$('#currTime').css('margin-top', '126px');
		
	});
	
	
}
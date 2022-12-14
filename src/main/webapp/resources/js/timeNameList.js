jQuery(function(a) {
	getTimeNameList();
});	

function getTimeNameList() {
	
	var loginId = sessionStorage.getItem("loginId"); // mineItRecord

	alert(loginId);
	
	$.ajax({
	type: "POST",
	url: "/getTimeNameList",
	// contentType:'<applicatio></applicatio>n/json; charset=utf-8', // Controller 에서 POST 로 받으려면 입력 X 
	dataType: 'json',
	data: {},
	success: function(data){
	    if ( data.result == '1') {
			swal("타임네임 생성 완료", "[ " + timeName + " ] 타임네임이 생성되었습니다.", "success")
			.then((value) => {
				// location.replace('/time_check');
			});
		} else {
			swal("다시 시도해주세요.", "", "error")
			.then((value) => {
				$('#timeName').focus();
			});
		}
	},  error:function(request,status,error){
	     alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	 }
	});	
	
}
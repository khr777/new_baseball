/**
 * 
 */



$(document).ready(function(){
	// alert('ddd')
});


function swal(type, title, text, func) {
	Swal.fire({
		title: title,
		// text: text,
		html: text,
		icon: type,
		allowOutsideClick: true,
		backdrop: true, // backdrop을 true로 설정(allowOuttsideClick ture 설정 시 함께 설정 )
		didClose: (e) => { // Swal alert 창을 닫은 후 싫행되는 
    	// focus your element
		if ( func != null && func != undefined ) {
				func();
			}
	  	}	,
	}).then((result) => {
		if (result.isConfirmed) {
			/* 
			if ( func != null && func != undefined ) {
				func();
				
			}*/ 
		} else {
			/* 
			if ( func != null && func != undefined ) {
				func();
				
			}*/ 
		}
  		
	})
}

function numberSpecialCheck(word) {
	
	let contains = false;
	
	// 숫자 또는 특수 문자를 포함하는지 검사하는 정규 표현식
	const regex = /[0-9!@#$%^&*()_+{}\[\]:;<>,.?~\\-]/;
	
	if (regex.test(word)) {
		contains = true;
	} 
	
	return contains;
	
}
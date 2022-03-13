<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/common/common.css" />
<link rel="stylesheet" type="text/css" href="/resources/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="/resources/fullCalendar/main.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="/resources/fullCalendar/main.js"></script>
<title>calendar page</title>
<style>
	#calendar {
		width: 80%;
		margin: 100px auto 50px;
	}
	
	.footer_box {
		position: static !important;
	}
</style>
</head>
<body>
	<div id='calendar'></div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	<script>
	    document.addEventListener('DOMContentLoaded', function() {
	      var calendarEl = document.getElementById('calendar');
	      var calendar = new FullCalendar.Calendar(calendarEl, {
	        initialView: 'dayGridMonth'
	      });
	      calendar.render();
	    });
	</script>
</body>
</html>		
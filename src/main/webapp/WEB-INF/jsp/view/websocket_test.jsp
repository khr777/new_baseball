<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../resources/css/common.css" rel="stylesheet"  >
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"  >
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" ></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>


    <input type="text" placeholder="보낼 메세지를 입력하세요." class="content">
    <button type="button" value="전송" class="sendBtn" onclick="sendMsg()">전송</button>
	<div>
    	<span>메세지</span>
    	<div class="msgArea"></div>
	</div>
	
	
<script>
        let socket = new WebSocket("ws://localhost:8085/websocket");

        socket.onopen = function (e) {
            console.log('open server!')
        };

        socket.onerror = function (e){
            console.log(e);
        }
        
        socket.onclose = function(event) {
			if (event.wasClean) {
				alert(`[close] 커넥션이 정상적으로 종료되었습니다(code=${event.code} reason=${event.reason})`);
			} else {
				// 예시: 프로세스가 죽거나 네트워크에 장애가 있는 경우
				// event.code가 1006이 됩니다.
				alert('[close] 커넥션이 죽었습니다.');
			}
		};
        
        socket.onmessage = function (e) {
            console.log(e.data);
            let msgArea = document.querySelector('.msgArea');
            let newMsg = document.createElement('div');
            newMsg.innerText=e.data;
            msgArea.append(newMsg);
        }

        function sendMsg() {
            let content=document.querySelector('.content').value;
            socket.send(content);
        }
</script>
	
</body>
</html>
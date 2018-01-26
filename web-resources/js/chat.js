
var Chat = {};

Chat.socket = null;

Chat.connect = (function(host) {
	if ('WebSocket' in window) {
		Chat.socket = new WebSocket(host);
	} else if ('MozWebSocket' in window) {
		Chat.socket = new MozWebSocket(host);
	} else {
		console.log('Error: WebSocket is not supported by this browser.');
		return;
	}

	Chat.socket.onopen = function () {
		console.log('Info: WebSocket connection opened.');
		document.getElementById('chat').onkeydown = function(event) {
			if (event.keyCode == 13) {
				Chat.sendMessage();
			}
		};
	};

	Chat.socket.onclose = function () {
		document.getElementById('chat').onkeydown = null;
		console.log('Info: WebSocket closed.');
	};

	Chat.socket.onmessage = function (message) {
		Room.log(message.data);
	};
});

Chat.initialize = function() {
	if (window.location.protocol == 'http:') {
		Chat.connect('ws://' + window.location.host + '/kreta/websocket/chat');
	} else {
		Chat.connect('wss://' + window.location.host + '/kreta/websocket/chat');
	}
}

Chat.sendMessage = (function() {
	var message = document.getElementById('chat').value;
	if (message != '') {
		Chat.socket.send(message);
		document.getElementById('chat').value = '';
	}
});

var Room = {};

Room.log = (function(message) {
	var room = document.getElementById('room');
	var p = document.createElement('p');
	p.style.wordWrap = 'break-word';
	p.innerHTML = message;
	room.appendChild(p);
	while (room.childNodes.length > 25) {
		room.removeChild(room.firstChild);
	}
	room.scrollTop = room.scrollHeight;
});
		
Chat.initialize();
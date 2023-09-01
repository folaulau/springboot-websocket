console.log("app.js")

const stompClient = new StompJs.Client({
   brokerURL: 'ws://localhost:8090/chat-info'
});

stompClient.onConnect = (frame) => {
   setConnected(true);
   console.log('Connected: ' + frame);
   stompClient.subscribe('/topic/messages', (data) => {
      console.log("data: "+data)
      showGreeting(JSON.parse(data.body).content);
   });
};

stompClient.onWebSocketError = (error) => {
   console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
   console.error('Broker reported error: ' + frame.headers['message']);
   console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
   $("#connect").prop("disabled", connected);
   $("#disconnect").prop("disabled", !connected);
   if (connected) {
      $("#conversation").show();
   }
   else {
      $("#conversation").hide();
   }
   $("#greetings").html("");
}

function connect() {
   console.log("connecting to stomp client")
   // stompClient.activate();
}

function disconnect() {
   stompClient.deactivate();
   setConnected(false);
   console.log("Disconnected");
}

function sendName() {
   stompClient.publish({
      destination: "/app/hello",
      body: JSON.stringify({'name': $("#name").val()})
   });
}

function showGreeting(message) {
   $("#greetings").append("<tr><td>" + message + "</td></tr>");
}


// $(function () {   
   


//    $("form").on('submit', (e) => e.preventDefault());
//    $( "#connect" ).click(() => connect());
//    $( "#disconnect" ).click(() => disconnect());
//    $( "#send" ).click(() => sendName());
// });
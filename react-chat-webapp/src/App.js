import React, { useState, useCallback, useEffect } from 'react';
import useWebSocket, { ReadyState } from 'react-use-websocket';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import SockJsClient from 'react-stomp';
// import SockJS from 'sockjs-client';
// import { Stomp } from 'stompjs';

function App() {

  const stompClient = new Client({
    webSocketFactory: () => new SockJS('http://localhost:8090/chat-info?token=test'),
  });
  // const stompClient = new Client({
  //   webSocketFactory: () => new SockJS('http://localhost:8090/chat-info?token=test'),
  //   connectHeaders: {
  //     'Authorization': 'Bearer YOUR_TOKEN_HERE',
  //     'token': '123-abc'
  //   }
  // });

  useEffect(() => {
    // This function will be called when the component mounts
    stompClient.onConnect = (frame) => {
      console.log('Connected:', frame);

      stompClient.subscribe('/topic/messages', (message) => {
        console.log("Received message:", message.body);
      });
    };

    stompClient.activate();  // Activate the connection

    // This return function will be called when the component unmounts
    return () => {
      if (stompClient.connected) {
        stompClient.deactivate();  // Deactivate the connection
      }
    };
  }, []);

  return (
    <div>
      <span>The WebSocket is currently {`connectionStatus`}</span>
      <br/>
      <span>Last message: {`lastMessage`}</span>
      {/* <SockJsClient url='http://localhost:8090/chat-info' 
        topics={['/topic/messages']}
        onConnect={() => {
          console.log("connected");
        }}
        onDisconnect={() => {
            console.log("Disconnected");
        }}
        onMessage={(msg) => { console.log(msg); }}
      /> */}
    </div>
  );
};

export default App;

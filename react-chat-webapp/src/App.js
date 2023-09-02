import React, { useState, useCallback, useEffect } from 'react';
import useWebSocket, { ReadyState } from 'react-use-websocket';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import SockJsClient from 'react-stomp';
// import SockJS from 'sockjs-client';
import { Stomp } from 'stompjs';

function App() {

  return (
    <div>
      <span>The WebSocket is currently {`connectionStatus`}</span>
      <br/>
      <span>Last message: {`lastMessage`}</span>
      <SockJsClient url='http://localhost:8090/chat-info' 
        topics={['/topic/messages']}
        onConnect={() => {
          console.log("connected");
        }}
        onDisconnect={() => {
            console.log("Disconnected");
        }}
        onMessage={(msg) => { console.log(msg); }}
      />
    </div>
  );
};

export default App;

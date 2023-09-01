import React, { useState, useCallback, useEffect } from 'react';
import useWebSocket, { ReadyState } from 'react-use-websocket';
import SockJsClient from 'react-stomp';

function App() {

  // const [socketUrl, setSocketUrl] = useState('http://127.0.0.1:8090/chat-info');
  // const [messageHistory, setMessageHistory] = useState([]);

  // const { lastMessage, readyState } = useWebSocket(socketUrl)

  // useWebSocket(socketUrl, {
  //   onOpen: () => {
  //     console.log('WebSocket connection established.');
  //   },
  //   onMessage: (msg) =>{
  //     console.log('WebSocket msg.'+msg);
  //   }
  // });

  // useEffect(() => {
  //   if (lastMessage !== null) {
  //     setMessageHistory((prev) => prev.concat(lastMessage));
  //   }
  // }, [lastMessage, setMessageHistory]);

  // const connectionStatus = {
  //   [ReadyState.CONNECTING]: 'Connecting',
  //   [ReadyState.OPEN]: 'Open',
  //   [ReadyState.CLOSING]: 'Closing',
  //   [ReadyState.CLOSED]: 'Closed',
  //   [ReadyState.UNINSTANTIATED]: 'Uninstantiated',
  // }[readyState];

  return (
    <div>
      {/* <span>The WebSocket is currently {connectionStatus}</span>
      <br/>
      <span>Last message: {lastMessage}</span> */}
      <div>
      <SockJsClient url='http://localhost:8090/chat-info' topics={['/topic/messages']}
            onMessage={(msg) => { console.log(msg); }}/>
      </div>
    </div>
  );
};

export default App;

import React, { useState, useCallback, useEffect } from 'react';
import useWebSocket, { ReadyState } from 'react-use-websocket';

function App() {

  //const [socketUrl, setSocketUrl] = useState('http://127.0.0.1:8090/topic/messages');
  const [socketUrl, setSocketUrl] = useState('wss://ws.postman-echo.com/raw');
  const [messageHistory, setMessageHistory] = useState([]);

  const { lastMessage, readyState } = useWebSocket(socketUrl)

  useWebSocket(socketUrl, {
    onOpen: () => {
      console.log('WebSocket connection established.');
    },
    onMessage: (msg) =>{
      console.log('WebSocket msg.'+msg);
    }
  });

  useEffect(() => {
    if (lastMessage !== null) {
      setMessageHistory((prev) => prev.concat(lastMessage));
    }
  }, [lastMessage, setMessageHistory]);

  const connectionStatus = {
    [ReadyState.CONNECTING]: 'Connecting',
    [ReadyState.OPEN]: 'Open',
    [ReadyState.CLOSING]: 'Closing',
    [ReadyState.CLOSED]: 'Closed',
    [ReadyState.UNINSTANTIATED]: 'Uninstantiated',
  }[readyState];

  return (
    <div>
      <span>The WebSocket is currently {connectionStatus}</span>
      <br/>
      <span>Last message: {lastMessage}</span>
      <ul>
        {messageHistory.map((message, idx) => (
          <span key={idx}>{message ? message.data : null}</span>
        ))}
      </ul>
    </div>
  );
};

export default App;

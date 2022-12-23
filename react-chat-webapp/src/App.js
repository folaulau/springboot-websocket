import React, { useState, useCallback, useEffect } from 'react';
import useWebSocket, { ReadyState } from 'react-use-websocket';

function App() {

  const [socketUrl, setSocketUrl] = useState('wss://127.0.0.1:8090/chat');
  const [messages, setMessages] = useState([]);

  const { sendMessage, lastMessage, readyState } = useWebSocket(socketUrl);

  useEffect(() => {
    if (lastMessage !== null) {
      setMessages((prev) => prev.concat(lastMessage));
    }
  }, [lastMessage, setMessages]);


  const connectionStatus = {
    [ReadyState.CONNECTING]: 'Connecting',
    [ReadyState.OPEN]: 'Open',
    [ReadyState.CLOSING]: 'Closing',
    [ReadyState.CLOSED]: 'Closed',
    [ReadyState.UNINSTANTIATED]: 'Uninstantiated',
  }[readyState];

  return (
    <div className="App">
      <header className="App-header">
        
          Learn React

          <ul>
            {messages.map((message, idx) => (
              <span key={idx}>{message ? message.message : null}</span>
            ))}
          </ul>
      </header>
    </div>
  );
}

export default App;

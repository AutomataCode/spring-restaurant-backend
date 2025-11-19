import { Injectable } from '@angular/core';
import { RxStomp, RxStompConfig } from '@stomp/rx-stomp';
import * as SockJS from 'sockjs-client';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService extends RxStomp {
  constructor() {
    super();
  }

  public init() {
    const rxStompConfig: RxStompConfig = {
      // Usar SockJS en lugar de WebSocket directo
      webSocketFactory: () => {
        return new SockJS('http://localhost:8080/ws') as any;
      },
      
      // Tiempo de reconexión
      reconnectDelay: 5000, // Intentar reconectar cada 5 segundos
      
      // Heartbeat para mantener la conexión viva
      heartbeatIncoming: 0, // Cliente no espera heartbeat del servidor
      heartbeatOutgoing: 20000, // Cliente envía heartbeat cada 20 segundos
      
      // Logs solo en desarrollo (puedes desactivarlo en producción)
      debug: (msg: string): void => {
        // Solo mostrar mensajes importantes, no todos los heartbeats
        if (!msg.includes('PING') && !msg.includes('PONG')) {
          console.log('[WebSocket]', new Date().toLocaleTimeString(), msg);
        }
      },
    };

    this.configure(rxStompConfig);
    this.activate();
  }
}


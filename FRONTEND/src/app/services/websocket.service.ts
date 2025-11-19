import { Injectable } from '@angular/core';
import { RxStomp } from '@stomp/rx-stomp';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService extends RxStomp {
  constructor() {
    super();
  }

  public init() {
    this.configure({
      brokerURL: 'ws://localhost:8080/ws',
      reconnectDelay: 200, // Intentar reconectar cada 200ms si se pierde la conexión
      
      // Opcional: Logs para depuración
      debug: (msg: string): void => {
        console.log(new Date(), msg);
      },
    });

    this.activate();
  }
}


import { Injectable } from '@angular/core';
import {Subject} from "rxjs";
import {WebsocketService} from "./websocket.service";


const CHAT_URL = "ws://192.168.1.3:8090/name";

export interface Message {
    author: string;
    message: string;
}

@Injectable()
export class ChatService {

    public messages: Subject<Message>;

    constructor(wsService: WebsocketService) {
        // @ts-ignore
        this.messages = <Subject<Message>>wsService.connect(CHAT_URL).map(
            (response: MessageEvent): Message => {
                let data = JSON.parse(response.data);
                return {
                    author: data.author,
                    message: data.message
                };
            }
        );
    }
}

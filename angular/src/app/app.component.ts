import {Component} from '@angular/core';
import {Message, WebsocketService} from "./services/websocket.service";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {
    title = 'socket';

    content = '';
    received = [] as Message[];
    sent = [] as Message[];

    constructor(private websocketService: WebsocketService) {
        websocketService.messages.subscribe(msg => {
            this.received.push({source:"Server",content:JSON.stringify(msg)});
            console.log("Response from websocket: " + msg);
        });
    }

    sendMsg() {
        let message = {
            source: '',
            content: ''
        };
        message.source = 'localhost';
        message.content = this.content;

        this.sent.push(message);
        this.websocketService.messages.next(message);
    }
}

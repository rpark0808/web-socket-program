import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FormsModule} from "@angular/forms";
import {WebsocketService} from "./services/websocket.service";
import {ChatService} from "./services/chat.service";

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule
    ],
    providers: [WebsocketService,ChatService],
    bootstrap: [AppComponent]
})
export class AppModule {
}

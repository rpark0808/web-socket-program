package com.study.socketprogram;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class MainActivity extends AppCompatActivity {

    private Button start;
    private TextView output;
    private OkHttpClient client;

    private final class EchoWebSocketListener extends WebSocketListener{
        @Override
        public void onOpen(WebSocket webSocket, Response response){
            Log.d(TAG, "onOpen: ");
            //webSocket.send("{source:'android',content:'world'}");
            //webSocket.close(1000,"Goodbye!!");
        }

        @Override
        public void onClosed(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
            Log.d(TAG, "onClosed: ");
            super.onClosed(webSocket, code, reason);
        }

        @Override
        public void onClosing(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
            Log.d(TAG, "onClosing: ");
            super.onClosing(webSocket, code, reason);
        }

        @Override
        public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t, @Nullable Response response) {
            Log.d(TAG, "onFailure: "+t);
            super.onFailure(webSocket, t, response);
        }

        @Override
        public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
            Log.d(TAG, "onMessage: text "+text);
            output("Received Text: "+text);
            super.onMessage(webSocket, text);
        }

        @Override
        public void onMessage(@NonNull WebSocket webSocket, @NonNull ByteString bytes) {
            Log.d(TAG, "onMessage: bytestring");
            super.onMessage(webSocket, bytes);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new OkHttpClient();

        Request request = new Request.Builder().url("ws://192.168.1.3:8090/name").build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        WebSocket ws = client.newWebSocket(request,listener);

        output = findViewById(R.id.tvResult);
        start = findViewById(R.id.btnClick);

        start.setOnClickListener(v -> {
            ws.send("Rochan String");
            //client.dispatcher().executorService().shutdown();
        });
    }

    private void output(final String text){
        runOnUiThread(() -> output.setText(text));
    }
}
package com.example.handthishomathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.handthishomathon.chatBot.MessageAdapter;
import com.example.handthishomathon.chatBot.network.WatsonClient;
import com.example.handthishomathon.chatBot.network.pojo.MessageResponse;
import com.example.handthishomathon.chatBot.network.pojo.WatsonRequest;
import com.example.handthishomathon.chatBot.network.pojo.WatsonResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatBotActivity extends AppCompatActivity {
    private static final String TAG = "chatActivity";

    private EditText etMessage;
    private Button btnSend;
    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManger;
    private MessageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);

        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);
        mRecyclerView = findViewById(R.id.rv_messages);

        mLayoutManger = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManger);
        mAdapter = new MessageAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mMessage = etMessage.getText().toString().trim();
                if (mMessage != null && !mMessage.isEmpty()) {
                    MessageResponse userMessage = new MessageResponse();
                    userMessage.setUserName("Me");
                    userMessage.setSender("Me");
                    userMessage.setMessage(mMessage);

                    if (mAdapter.getMessageAt().getMessage().equals("الرجاء كتابه الطلب")) {
                        Log.d(TAG, "initView: " + mMessage); // this is the order
                    }

                    mAdapter.addSingleItem(userMessage);
                    sendMessage();
                    etMessage.setText("");
                } else {
                    Toast.makeText(ChatBotActivity.this, "Enter Message", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sendMessage();


    }


    void sendMessage() {
        WatsonRequest.InputBean inputBean = new WatsonRequest.InputBean();
        inputBean.setText(etMessage.getText().toString().trim());
        WatsonRequest mRequest = new WatsonRequest();
        mRequest.setInput(inputBean);

        doChatBotApiCall(mRequest);
    }

    void doChatBotApiCall(WatsonRequest mWatsonRequest) {
        WatsonClient.getInstance().getBotReplay(mWatsonRequest).enqueue(new Callback<WatsonResponse>() {
            @Override
            public void onResponse(Call<WatsonResponse> call, Response<WatsonResponse> response) {
                Log.d(TAG, "onResponse: " + response.body().getOutput().getText().get(0));
                WatsonResponse mResponse = response.body();
                MessageResponse botMessage = new MessageResponse();
                botMessage.setUserName("Meco ChatBot");
                botMessage.setSender("Bot");
                botMessage.setMessage(mResponse.getOutput().getText().get(0));


                mAdapter.addSingleItem(botMessage);
            }

            @Override
            public void onFailure(Call<WatsonResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}

package com.example.handthishomathon.main.destinations;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.handthishomathon.AppActivity;
import com.example.handthishomathon.R;
import com.example.handthishomathon.chatBot.MessageAdapter;
import com.example.handthishomathon.chatBot.network.WatsonClient;
import com.example.handthishomathon.chatBot.network.pojo.MessageResponse;
import com.example.handthishomathon.chatBot.network.pojo.WatsonRequest;
import com.example.handthishomathon.chatBot.network.pojo.WatsonResponse;
import com.example.handthishomathon.main.vm.ChatbotVM;
import com.example.handthishomathon.main.vm.SignInVM;
import com.example.handthishomathon.model.Business;
import com.example.handthishomathon.model.Order;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatBotFragment extends Fragment {
    ChatbotVM vm;
    private static final String TAG = "chatActivity";

    private EditText etMessage;
    private Button btnSend;
    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManger;
    MessageAdapter mAdapter;


    public ChatBotFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_chat_bot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav_bar);
        navBar.setVisibility(View.GONE);
        vm = ViewModelProviders.of(this).get(ChatbotVM.class);

        etMessage = view.findViewById(R.id.etMessage);
        btnSend = view.findViewById(R.id.btnSend);
        mRecyclerView = view.findViewById(R.id.rv_messages);

        mLayoutManger = new LinearLayoutManager(getContext());
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
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        Date now = new Date();
                        Order order = new Order(
                                null,
                                mMessage,
                                dateFormat.format(now),
                                AppActivity.currentConsumer.getId(),
                                "N/A",
                                "ordered",
                                "N/A",
                                "",
                                0.0,
                                "EGP",
                                AppActivity.currentConsumer.getLongitude(),
                                AppActivity.currentConsumer.getLatitude());
                        vm.createOrder(order);
                        vm.orderMutableLiveData.observe(getActivity(), new Observer<JsonObject>() {
                            @Override
                            public void onChanged(JsonObject jsonObject) {
                                Toast.makeText(getActivity(), "order created", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    mAdapter.addSingleItem(userMessage);
                    ChatBotFragment.this.sendMessage();
                    etMessage.setText("");
                } else {
                    Toast.makeText(getContext(), "Enter Message", Toast.LENGTH_SHORT).show();
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

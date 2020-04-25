package com.example.handthishomathon.main.destinations;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.handthishomathon.R;
import com.example.handthishomathon.chatBot.MessageAdapter;
import com.example.handthishomathon.chatBot.network.WatsonClient;
import com.example.handthishomathon.chatBot.network.pojo.MessageResponse;
import com.example.handthishomathon.chatBot.network.pojo.WatsonRequest;
import com.example.handthishomathon.chatBot.network.pojo.WatsonResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatBotFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatBotFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private static final String TAG = "chatActivity";

    private EditText etMessage;
    private Button btnSend;
    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManger;
    MessageAdapter mAdapter;


    public ChatBotFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatBotFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatBotFragment newInstance(String param1, String param2) {
        ChatBotFragment fragment = new ChatBotFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

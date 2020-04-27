package com.example.handthishomathon.chatBot.network.pojo;

import com.google.gson.annotations.SerializedName;



public class WatsonRequest {


    @SerializedName("input")
    private InputBean input;

    public InputBean getInput() {
        return input;
    }

    public void setInput(InputBean input) {
        this.input = input;
    }

    public static class InputBean {

        @SerializedName("text")
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}

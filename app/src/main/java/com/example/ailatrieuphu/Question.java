package com.example.ailatrieuphu;

import java.util.List;

public class Question {
    private int numner;
    private String content;
    private List<Answer> listAnswer;

    public int getNumner() {
        return numner;
    }

    public void setNumner(int numner) {
        this.numner = numner;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Answer> getListAnswer() {
        return listAnswer;
    }

    public void setListAnswer(List<Answer> listAnswer) {
        this.listAnswer = listAnswer;
    }

    public Question(int numner, String content, List<Answer> listAnswer) {
        this.numner = numner;
        this.content = content;
        this.listAnswer = listAnswer;
    }
}

package com.example.prodoreviewer;

public class Card {
    private String name;
    private String difficulty;
    private String content;
    private String topic;

    public Card(String name, String difficulty, String content, String topic) {
        this.name = name;
        this.difficulty = difficulty;
        this.content = content;
        this.topic = topic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}

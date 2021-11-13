package com.shivam;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {

    @JsonProperty("name")
    private String firstName;

    @JsonProperty("message")
    private String greetingMessage;

    public Person(String firstName, String message) {
        this.firstName=firstName;
        this.greetingMessage=message;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGreetingMessage() {
        return greetingMessage;
    }

    public void setGreetingMessage(String greetingMessage) {
        this.greetingMessage = greetingMessage;
    }
}

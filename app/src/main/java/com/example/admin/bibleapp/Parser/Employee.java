package com.example.admin.bibleapp.Parser;

public class Employee {
    private String id;
    private String input1;
    private String input2;
    private String input3;
    private String input4;

    public String getInput1() {
        return this.input1;
    }

    public void setInput1(String input1) {
        this.input1 = input1;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInput2() {
        return this.input2;
    }

    public void setInput2(String input2) {
        this.input2 = input2;
    }

    public String getInput3() {
        return this.input3;
    }

    public void setInput3(String input3) {
        this.input3 = input3;
    }

    public String getInput4() {
        return this.input4;
    }

    public void setInput4(String input4) {
        this.input4 = input4;
    }

    public String toString() {
        return this.id + ": " + this.input1 + "\n" + this.input2 + "-" + this.input3 + "\n";
    }
}

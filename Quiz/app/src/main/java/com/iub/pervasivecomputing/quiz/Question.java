package com.iub.pervasivecomputing.quiz;

/**
 * Created by Pratik on 09/10/2017.
 */

class Question {
    private int id;
    private String statement;
    private String a, b, c, d;
    private Answer answer;
    private boolean solved;

    Question(int id, String statement, String a, String b, String c, String d, Answer answer, boolean solved) {
        this.id = id;
        this.statement = statement;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = answer;
        this.solved = solved;
    }

    boolean isSolved() {
        return solved;
    }

    void setSolved(boolean solved) {
        this.solved = solved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String getStatement() {
        return statement;
    }

    String getA() {
        return a;
    }

    String getB() {
        return b;
    }

    String getC() {
        return c;
    }

    String getD() {
        return d;
    }

    Answer getAnswer() {
        return answer;
    }

    enum CATEGORY {A, B}

    enum Answer {A, B, C, D}
}

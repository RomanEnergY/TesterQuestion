package Zverik.r.s.DataModel;

import java.util.LinkedList;

public class Question {
    private String questionSection; // тема вопроса
    private int numberQuestion; // номер вопроса
    private String textQuestion; // текст вопроса
    private String referenceLiterature; // справочная литература
    private LinkedList<Answers> answers;

    public Question(String questionSection, int numberQuestion, String textQuestion, String referenceLiterature, LinkedList<Answers> answers) {
        this.questionSection = questionSection;
        this.numberQuestion = numberQuestion;
        this.textQuestion = textQuestion;
        this.referenceLiterature = referenceLiterature;
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "QuestionSection='" + questionSection + '\'' +
                ", numberQuestion=" + numberQuestion +
                ", textQuestion='" + textQuestion + '\'' +
                ", referenceLiterature='" + referenceLiterature + '\'' +
                ", answers=" + answers +
                '}';
    }
}

package Zverik.r.s.DataModel;

import Zverik.r.s.Controller.ReaderASOP.ReaderASOP;

import java.util.Set;

public class DataASOP {
    private Set<Question> questionSet; // раздел вопросов

    public DataASOP(ReaderASOP readerASOP) {
        this.questionSet = readerASOP.read();
    }

    public void display() {
        questionSet.iterator().forEachRemaining(System.out::println);
    }
}

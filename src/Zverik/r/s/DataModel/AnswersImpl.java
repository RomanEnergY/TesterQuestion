package Zverik.r.s.DataModel;

public class AnswersImpl implements Answers {
    private String text; // текст ответа
    private boolean correctly; // true - верно

    public AnswersImpl(String text, boolean correctly) {
        this.text = text;
        this.correctly = correctly;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public boolean isCorrectly() {
        return correctly;
    }
}

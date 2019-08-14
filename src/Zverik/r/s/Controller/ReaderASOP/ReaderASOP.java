package Zverik.r.s.Controller.ReaderASOP;

import Zverik.r.s.DataModel.Question;

import java.util.Set;

public interface ReaderASOP {
    /**
     * Метод реализующий чтение данных
     *
     * @return множество вопросов
     */
    Set<Question> read();
}

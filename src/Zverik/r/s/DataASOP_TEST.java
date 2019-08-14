package Zverik.r.s;

import Zverik.r.s.Controller.ReaderASOP.Object.HtmlReaderASOP;
import Zverik.r.s.DataModel.DataASOP;

public class DataASOP_TEST {
    private static final String FILE_NAME_123 = "F:\\Работа ГтЭС\\Проверка знаний\\Диспетчер РЭС\\Раздел 1,2,3.html";
    private static final String FILE_NAME_1 = "F:\\Работа ГтЭС\\Проверка знаний\\Диспетчер РЭС\\Раздел 1.html";
    private static final String FILE_NAME_2 = "F:\\Работа ГтЭС\\Проверка знаний\\Диспетчер РЭС\\Раздел 2.html";
    private static final String FILE_NAME_3 = "F:\\Работа ГтЭС\\Проверка знаний\\Диспетчер РЭС\\Раздел 3.html";

    public static void main(String[] args) {
        DataASOP dataASOP = new DataASOP(new HtmlReaderASOP(FILE_NAME_1));
    }
}

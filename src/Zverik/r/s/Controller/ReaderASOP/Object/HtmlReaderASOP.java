package Zverik.r.s.Controller.ReaderASOP.Object;

import Zverik.r.s.Controller.ReaderASOP.ReaderASOP;
import Zverik.r.s.DataModel.Question;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlReaderASOP implements ReaderASOP {

    private final LinkedHashSet<String> fileNames;

    public HtmlReaderASOP(String... fileNames) {
        this.fileNames = new LinkedHashSet<>();
        Collections.addAll(this.fileNames, fileNames);
    }

    @Override
    public Set<Question> read() {
        String allTextFile = this.readHtml(this.fileNames);
        Set<String> readerSections = this.dataBlockReadSection(allTextFile);
//        readerSections.iterator().forEachRemaining(System.out::println);

        return this.parsingToQuestion(readerSections);
    }

    /**
     * Блок чтения данных в одну строку документа HTML
     *
     * @param fileNames множество файлов
     * @return одну строку всех прочитанных документов
     */
    private String readHtml(LinkedHashSet<String> fileNames) {
        StringBuilder stringBuilder = new StringBuilder();
        String s;

        for (String fileName : fileNames) {
            try {
                Scanner scanner = new Scanner(new File(fileName));
                while (scanner.hasNext()) {
                    s = scanner.next();
                    stringBuilder.append(s).append(" ");
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }

    /**
     * Деление на блоки, текстовые строки окруженные метками <td.+?</td>, далее получение данных из этих строк
     * isMatchData проверяет соответствие данных, наличие хоть что-нибудь в <td.+?</td>
     * transformText чистка, остается только данные
     *
     * @param text текст строки данных
     * @return упорядоченное множество строк с данными
     */
    public Set<String> dataBlockReadSection(String text) {
        Pattern pattern = Pattern.compile("<td.+?</td>");
        Matcher matcherStart = pattern.matcher(text);
        String sTemp;

        LinkedHashSet<String> linkedListReturn = new LinkedHashSet<>();


        // коллекция для хранения начальной позиции вхождения согласно REGEX
        LinkedList<Integer> startMatcher = new LinkedList<>();

        while (matcherStart.find()) {
            startMatcher.addLast(matcherStart.start());
        }
        startMatcher.addLast(text.length() - 1);

        for (int i = 1; i < startMatcher.size(); i++) {
            sTemp = text.substring(startMatcher.get(i - 1), startMatcher.get(i));
            if (this.isMatchData(sTemp)) {
                if ((sTemp = this.transformText(sTemp)) != null)
                    linkedListReturn.add(sTemp);
            }
        }
        return linkedListReturn;
    }

    /**
     * Метод проверяет данные хранимые в строке
     *
     * @param text сторока данных
     * @return true соответствует
     */
    private boolean isMatchData(String text) {
        if (text.matches("<td></td>")) {
            return false;
        } else if (text.matches("<td width=\"\\d+\"></td>")) {
            return false;
        }

        return true;
    }

    /**
     * Метод удаляет все ненужные данные из текстовой строки, оставляет только данные
     *
     * @param text строка содеращая данные
     * @return
     */
    private String transformText(String text) {
        //
        Pattern patternAll = Pattern.compile("<td colspan=.+?\\d\">.+?</td>");
        Pattern patternStart = Pattern.compile("^<td.+?>");
        Pattern patternEnd = Pattern.compile("</td>");

        Matcher matcher = patternAll.matcher(text);
        String returnText = null;
        try {
            if (matcher.find()) {
                returnText = matcher.group();

                matcher = patternStart.matcher(returnText);
                if (matcher.find()) {
                    returnText = returnText.substring(matcher.end());
                }

                matcher = patternEnd.matcher(returnText);
                if (matcher.find()) {
                    returnText = returnText.substring(0, matcher.start());
                }

                returnText = returnText.replaceAll("&quot;", "\"");
                returnText = returnText.replaceAll("<br>", "\n");
            }

        } catch (IllegalStateException e) {
            returnText = text;
        }

        return returnText;
    }

    /**
     * @param readerSections упорядоченное множество строк с данными
     * @return
     */
    private Set<Question> parsingToQuestion(Set<String> readerSections) {

        String questionSection = "";
        LinkedList<LinkedList<String>> linkedListLinkedList = new LinkedList<>();
        LinkedList<String> linkedList = new LinkedList<>();

        for (String text : readerSections) {
            Pattern patternAll = Pattern.compile("<u>ТЕМА:.Раздел\\s\\d\\s-\\s.+?</u>");
            Matcher matcher = patternAll.matcher(text);

            if (matcher.find()) {
                questionSection = text.substring(text.indexOf("Раздел"), matcher.end() - 4);

                linkedList.add("////////////////////////////////////////////////////////////////////////////////");
                linkedListLinkedList.add(linkedList);
                linkedList = new LinkedList<>();

            }

            if (!questionSection.equals("")) {
                linkedList.add(text);
            }
        }

        linkedListLinkedList.iterator().forEachRemaining((a)->{
            a.iterator().forEachRemaining(System.out::println);
        });


        return null;
    }
}

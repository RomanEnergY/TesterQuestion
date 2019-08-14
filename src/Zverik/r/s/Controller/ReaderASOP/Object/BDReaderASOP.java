package Zverik.r.s.Controller.ReaderASOP.Object;

import Zverik.r.s.Controller.ReaderASOP.ReaderASOP;
import Zverik.r.s.DataModel.Question;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class BDReaderASOP implements ReaderASOP {

    private final LinkedHashSet<String> fileNames;

    public BDReaderASOP(String... fileNames) {
        this.fileNames = new LinkedHashSet<>();
        Collections.addAll(this.fileNames, fileNames);
    }

    @Override
    public Set<Question> read() {
        System.out.println(this.getClass().getSimpleName() + ":read");

        return null;
    }
}

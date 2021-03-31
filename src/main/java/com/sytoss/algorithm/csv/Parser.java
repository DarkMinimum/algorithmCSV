package com.sytoss.algorithm.csv;

import java.util.List;

public class Parser {
    public static List<PersonLine> parse(FileContent fc) {

        for (int i = 0; i < fc.getLines().size(); i++) {
            fc.getLines().get(i).validate();

        }

        return fc.getLines();
    }
}

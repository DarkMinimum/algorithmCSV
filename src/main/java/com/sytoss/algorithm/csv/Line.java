package com.sytoss.algorithm.csv;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Line {

    protected List<String> cells;

    public Line(String line) {
        cells = new ArrayList<String>();
        cells = Arrays.asList( line.split(",") );
    }

    public List<String> getCells() {
        return this.cells;
    }

    protected void validate() throws RuntimeException {

    }
}

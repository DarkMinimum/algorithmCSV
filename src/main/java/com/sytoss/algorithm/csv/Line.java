package com.sytoss.algorithm.csv;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.*;

public class Line {

    protected List<String> cells;

    public Line(String line) {
        cells = new ArrayList();
        cells = asList( line.split(",") );
    }

    public List<String> getCells() {
        return this.cells;
    }

    protected void validate() throws RuntimeException {

    }
}

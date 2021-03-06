package com.sytoss.algorithm.csv.lines;


import java.util.Collections;
import java.util.List;

import static java.util.Arrays.*;

public class Line {

    protected List<String> cells;

    public Line(String line) {
        cells = asList( line.split(",") );
    }

    public List<String> getCells() {
        return Collections.unmodifiableList(this.cells);
    }

    public void transformToValid() throws RuntimeException {

    }
}

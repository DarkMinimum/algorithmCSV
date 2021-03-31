package com.sytoss.algorithm.csv;

public class PersonLine extends Line {

    public PersonLine(String line) {
        super(line);
    }

    @Override
    protected final void validate() {

        for (int j = 0; j < this.cells.size(); j++) {
            StringBuilder word = new StringBuilder(this.cells.get(j));

            for (int k = 0; k < word.length(); k++) {

                if (!Character.isLetterOrDigit(word.toString().toCharArray()[k])) {
                    word.deleteCharAt(0);
                    word.deleteCharAt(word.length() - 1);

                    break;
                }
            }

            this.cells.set(j, word.toString());
        }
        for (int j = 0; j < this.cells.size(); j++) {

            StringBuilder word = new StringBuilder(this.cells.get(j));

            for (int k = 0; k < word.length(); k++) {

                if(word.toString().toCharArray()[k] == '\"') {
                    if(k + 1 < word.toString().toCharArray().length && word.toString().toCharArray()[k + 1] == '\"')
                        word.deleteCharAt(k);
                }
            }

            this.cells.set(j, word.toString());


        }
    }

    public String getNumber() {
        return super.getCells().get(0);
    }
    public String getName() {
        return super.getCells().get(1);
    }
    public String getSurname() {
        return super.getCells().get(2);
    }
    public String getDate() {
        return super.getCells().get(3);
    }
    public String getNote() {
        return super.getCells().get(4);
    }


}

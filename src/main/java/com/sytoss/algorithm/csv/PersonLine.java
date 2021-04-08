package com.sytoss.algorithm.csv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        return getCells().get(0);
    }
    public String getName() {
        return getCells().get(1);
    }
    public String getSurname() {
        return getCells().get(2);
    }

    public String getBirthdayXML() throws ParseException {
        SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date.parse(getCells().get(3)));
        return cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1) +"-"+cal.get(Calendar.DAY_OF_MONTH);
    }

    public Date getBirthday() throws ParseException {
        return new SimpleDateFormat("dd.MM.yyyy").parse(getCells().get(3));
    }


    public String getNote() {
        return getCells().get(4);
    }



}

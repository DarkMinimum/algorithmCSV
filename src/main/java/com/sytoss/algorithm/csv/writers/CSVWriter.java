package com.sytoss.algorithm.csv.writers;

import com.sytoss.algorithm.csv.lines.Line;
import com.sytoss.algorithm.csv.lines.PersonLine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class CSVWriter implements IWriter {

    public CSVWriter(List<Line> lines, String csvPath) {
        write(lines, csvPath);
    }

    @Override
    public void write(List<Line> lines, String csvPath) {

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(csvPath, false));

            for (Line line : lines) {

                PersonLine person = (PersonLine) line;
                StringBuilder personString = new StringBuilder();


                for (int j = 0; j < person.getCells().size(); j++) {

                    StringBuilder word = new StringBuilder(person.getCells().get(j));
                    StringBuilder tmpWord = new StringBuilder();

                    for (int k = 0; k < word.length(); k++) {

                        Character character = word.toString().toCharArray()[k];

                        if (character.equals('\"')) {
                            tmpWord.append('\"');
                        }

                        tmpWord.append(character);
                    }

                    boolean flag = false;

                    for (int k = 0; k < tmpWord.length(); k++) {

                        if (!Character.isLetterOrDigit(tmpWord.toString().toCharArray()[k])) {
                            flag = true;
                            break;
                        }
                    }

                    if (flag) {
                        personString.append("\"").append(tmpWord).append("\"");
                    } else {
                        personString.append(tmpWord);
                    }
                    personString.append(",");

                }
                personString.deleteCharAt(personString.length() - 1);
                writer.append(personString.toString()).append("\n");

            }

            writer.close();
        }
        catch (Exception exception) {
            exception.getStackTrace();
        }
    }
}

package com.sytoss.algorithm.csv.savers;

import com.sytoss.algorithm.csv.readers.FileContent;
import com.sytoss.algorithm.csv.lines.PersonLine;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class CSVSaver implements ISaver {

    public CSVSaver(FileContent fc, String csvPath) {
        write(fc, csvPath);
    }

    @Override
    public void write(FileContent fc, String csvPath) {

        try {

            //if I comment these two writers out the file is not cleared.

            BufferedWriter writer = new BufferedWriter(new FileWriter(csvPath, false));


            //persons
            for (int i = 0; i < fc.getLines().size(); i++) {

                PersonLine person = (PersonLine) fc.getLines().get(i);
                StringBuilder personString = new StringBuilder();

                //persons cells
                for (int j = 0; j < person.getCells().size(); j++) {

                    StringBuilder word = new StringBuilder(person.getCells().get(j));
                    StringBuilder tmpWord = new StringBuilder();

                    //double \"
                    for (int k = 0; k < word.length(); k++) {

                        Character character = word.toString().toCharArray()[k];

                        if (character.equals('\"')) {
                            tmpWord.append('\"');
                        }

                        tmpWord.append(character);
                    }

                    boolean flag = false;
                    //cells characters
                    for (int k = 0; k < tmpWord.length(); k++) {

                        if (!Character.isLetterOrDigit(tmpWord.toString().toCharArray()[k])) {
                            flag = true;
                            break;
                        }
                    }

                    if(flag) {
                        personString.append("\"").append(tmpWord).append("\"");
                    }
                    else {
                        personString.append(tmpWord);
                    }
                    personString.append(",");

                }
                personString.deleteCharAt(personString.length()-1);
                writer.append(personString.toString()).append("\n");

            }

            writer.close();
        }
        catch (Exception exception) {
            exception.getStackTrace();
        }
    }
}

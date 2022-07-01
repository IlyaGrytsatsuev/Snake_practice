package io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class RecordSaver {

    private String lvl1Record = "src/resources/level1.txt" ;
    private String lvl2Record = "src/resources/level2.txt" ;
    private String lvl3Record = "src/resources/level3.txt" ;
    private String filename;

    private String level_id;
    private int Record;

    public RecordSaver(){

    }


    public void saveRecord (String level, int record){

        if(level.equals("level 1"))
            filename = lvl1Record;

        if(level.equals("level 2"))
            filename = lvl2Record;

        if(level.equals("level 3"))
            filename = lvl3Record;


        String data = level + record;
        //String key = "";
        int val = 0;
        try {

            FileWriter out = new FileWriter(filename);
            for (int i = 0; i < data.length(); i++) {
                out.write(data.charAt(i));
            }
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadRecord(String level){
        String data = "";
        if(level.equals("level 1"))
            filename = lvl1Record;

        if(level.equals("level 2"))
            filename = lvl2Record;

        if(level.equals("level 3"))
            filename = lvl3Record;

        try {
            FileReader in = new FileReader(filename);
            int ch;
            StringBuilder tmp = new StringBuilder();

            while ((ch = in.read()) > -1)
                tmp.append((char) ch);

            data = tmp.toString();

            Scanner s = new Scanner(data);

            while (s.hasNextLine()) {
                level_id = s.next();
                Record = s.nextInt();
               /* if (s.hasNextLine())
                    s.nextLine();*/
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getRecord(String level){
        loadRecord(level);
        return Record;
    }
}

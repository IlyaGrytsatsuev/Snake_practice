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

        String data = "" + record;

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
                Record = s.nextInt();
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

    public void load_table_info (int[] arr){
        String data = "";
        try {
            FileReader in = new FileReader(lvl1Record);
            int ch;
            StringBuilder tmp = new StringBuilder();

            while ((ch = in.read()) > -1)
                tmp.append((char) ch);

            data = tmp.toString();

            Scanner s = new Scanner(data);

            while (s.hasNextLine()) {
                arr[0] = s.nextInt();
            }

            in = new FileReader(lvl2Record);
            tmp = new StringBuilder();

            while ((ch = in.read()) > -1)
                tmp.append((char) ch);

            data = tmp.toString();

            s = new Scanner(data);

            while (s.hasNextLine()) {
                arr[1] = s.nextInt();
            }

            in = new FileReader(lvl3Record);
            tmp = new StringBuilder();

            while ((ch = in.read()) > -1)
                tmp.append((char) ch);

            data = tmp.toString();

            s = new Scanner(data);

            while (s.hasNextLine()) {
                arr[2] = s.nextInt();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        }

    }

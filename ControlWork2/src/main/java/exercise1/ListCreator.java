package exercise1;

import java.util.ArrayList;
import java.util.List;

public class ListCreator {
    public static ArrayList<Program> createList(){
        ArrayList<String> list = TXTParser.parser("data.txt");
        ArrayList<Program> programs = new ArrayList<>();
        String channel = "";
        String time = "";
        for(String str : list){
            if (str.charAt(0) == '#') {
                channel = str;
                continue;
            }else if (Character.isDigit(str.charAt(0))){
                time = str;
                continue;
            }
            programs.add(new Program(channel,time,str));
        }
        return programs;
    }
}
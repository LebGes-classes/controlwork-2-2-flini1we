package exercise1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TXTParser {
    public static ArrayList<String> parser(String filePath){
        ArrayList<String> data = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./"+filePath));
            String line;
            int index = 0;
            while((line = reader.readLine()) != null){
                data.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}

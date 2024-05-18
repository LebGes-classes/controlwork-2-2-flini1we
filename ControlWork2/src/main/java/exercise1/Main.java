package exercise1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String[] header = new String[]{"Канал","Время","Название"};
        ArrayList<Program> sortPrograms = SortingByTime.programsSortedByTime();
        String[][] answer = new String[sortPrograms.size()][3];
        int index = 0;
        for(Program program: sortPrograms){
            String str = program.toString();
            String[] cols = str.split(";");
            answer[index++] = cols;
        }

        example.XLSXActions.writeFile(answer,"salfetka5",header,"title");
    }
}

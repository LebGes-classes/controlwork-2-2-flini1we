package exercise1;

import java.util.ArrayList;
import java.util.List;

public class RunningProgramms {
    public static List<Program> programsOnThemMoment(int intTime) {
        String time = String.valueOf(intTime);
        ArrayList<String> channelsAfterParsing = TXTParser.parser("data.txt");

        List<Program> programList = new ArrayList<>();

        for (int i = 0; i < channelsAfterParsing.size() - 1; i++){
            String str = channelsAfterParsing.get(i);
            String channelsStr = channelsAfterParsing.get(i + 1);
            if (str.charAt(0) == '#') {
                continue;
            } else if (Character.isDigit(str.charAt(0))) {
                BroadcastsTime bt1 = new BroadcastsTime(str);
                BroadcastsTime bt2 = new BroadcastsTime(time);

                if (bt1.compareTo(bt2) == 0) {
                    String[] data = channelsStr.split(" ");
                    programList.add(new Program(
                            data[0],
                            data[1],
                            data[2]
                    ));
                    i++;
                }
            }
        }
        return programList;
    }
}

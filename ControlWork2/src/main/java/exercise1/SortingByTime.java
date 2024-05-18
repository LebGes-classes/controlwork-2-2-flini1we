package exercise1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortingByTime {
    public static ArrayList<Program> programsSortedByTime() {
        ArrayList<Program> programs = ListCreator.createList();

        Comparator<Program> comparator = new Comparator<Program>() {
            @Override
            public int compare(Program p1, Program p2) {
                int channelComparison = p1.getChannel().compareTo(p2.getChannel());
                if (channelComparison!= 0) {
                    return channelComparison;
                } else {
                    return p1.getTime().compareTo(p2.getTime());
                }
            }
        };

        Collections.sort(programs, comparator);

        return programs;
    }
}

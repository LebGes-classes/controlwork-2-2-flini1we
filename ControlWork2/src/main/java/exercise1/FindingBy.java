package exercise1;

import java.util.ArrayList;
import java.util.List;

public class FindingBy {
    public void findingByName(String nameToFind) {
        ArrayList<Program> programs = ListCreator.createList();

        List<Program> foundPrograms = new ArrayList<>();

        for (Program program : programs) {
            if (program.getTitle().equalsIgnoreCase(nameToFind)) {
                foundPrograms.add(program);
            }
        }

        for (Program program : foundPrograms) {
            System.out.println(program.toString());
        }
    }

    public void findingByChannelAndCurrentTime(String channel, int intTime) {
        List<Program> programsAtTheMoment = RunningProgramms.programsOnThemMoment(intTime);
        ArrayList<Program> foundPrograms = new ArrayList<>();

        for (Program program : programsAtTheMoment) {
            if (program.getChannel().equalsIgnoreCase(channel)) {
                foundPrograms.add(program);
            }
        }

        for (Program program : foundPrograms) {
            System.out.println(program.toString());
        }
    }

    public void findingByChannelAndTimeBetween (
            String channel,
            int intTime1,
            int intTime2
    ) {
        String timeEarlier = String.valueOf(intTime1);
        String timeAfter = String.valueOf(intTime2);

        ArrayList<Program> programs = ListCreator.createList();

        List<Program> programsInInterval = new ArrayList<>();

        for (Program program : programs) {
            if (program.getChannel().equalsIgnoreCase(channel)) {
                BroadcastsTime programStartTime = new BroadcastsTime(timeEarlier);
                BroadcastsTime programEndsTime = new BroadcastsTime(timeAfter);

                if (program.getChannel().equalsIgnoreCase(channel)) {
                    if (program.getTime().between(
                            programStartTime,
                            programEndsTime)) {
                        programsInInterval.add(program);
                    }
                }
            }
            for (Program prog : programsInInterval) {
                System.out.println(prog.toString());
            }
        }
    }
}

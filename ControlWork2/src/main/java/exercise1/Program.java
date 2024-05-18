package exercise1;

public class Program {
    private String channel;
    private BroadcastsTime time;
    private String title;

    public Program(String channel) {
        this.channel = channel;
    }

    public Program(String chanel, String time, String title) {
        this.channel = chanel;
        this.time = new BroadcastsTime(time);
        this.title = title;
    }

    @Override
    public String toString() {
        return channel + ';' + time + ';' + title;
    }

    public String getChannel() {
        return channel;
    }

    public BroadcastsTime getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }
}


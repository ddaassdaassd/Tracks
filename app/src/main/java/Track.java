import java.util.Random;
import java.util.UUID;

public class Track {
    private String id;
    private String title;
    private String singer;
    private static int lastId;

    public Track() {
        this.setId(UUID.randomUUID().toString());
    }

    public Track(String title, String singer) {
        this(null, title, singer);
    }

    public Track(String id, String title, String singer) {
        this();
        if (id != null) this.setId(id);
        this.setTitle(title);
        this.setSinger(singer);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
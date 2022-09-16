public class podcast {
    private int podcast_id;
    private String podcast_name;
    private String podcast_path;
    private String podReleasedDate;

    public podcast(int podcast_id, String podcast_name, String podcast_path, String podReleasedDate) {
        this.podcast_id = podcast_id;
        this.podcast_name = podcast_name;
        this.podcast_path = podcast_path;
        this.podReleasedDate = podReleasedDate;
    }

    public int getPodcast_id() {
        return podcast_id;
    }

    public void setPodcast_id(int podcast_id) {
        this.podcast_id = podcast_id;
    }

    public String getPodcast_name() {
        return podcast_name;
    }

    public void setPodcast_name(String podcast_name) {
        this.podcast_name = podcast_name;
    }

    public String getPodcast_path() {
        return podcast_path;
    }

    public void setPodcast_path(String podcast_path) {
        this.podcast_path = podcast_path;
    }

    public String getPodReleasedDate() {
        return podReleasedDate;
    }

    public void setPodReleasedDate(String podReleasedDate) {
        this.podReleasedDate = podReleasedDate;
    }
}

class Celebrity{
    private int celebId;
    private String celebName;

    public Celebrity(int celebId, String celebName) {
        this.celebId = celebId;
        this.celebName = celebName;
    }

    public int getCelebId() {
        return celebId;
    }

    public void setCelebId(int celebId) {
        this.celebId = celebId;
    }

    public String getCelebName() {
        return celebName;
    }

    public void setCelebName(String celebName) {
        this.celebName = celebName;
    }
}

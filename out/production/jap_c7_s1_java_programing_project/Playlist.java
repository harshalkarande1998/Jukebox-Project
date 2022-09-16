public class Playlist {
    private int playlistID;
    private String playlistName;
    private String createdDate;
    private int userID;

    public Playlist(int playlistID, String playlistName, String createdDate, int userID) {
        this.playlistID = playlistID;
        this.playlistName = playlistName;
        this.createdDate = createdDate;
        this.userID = userID;
    }

    public Playlist(String playlistName, String createdDate, int userID) {
        this.playlistName = playlistName;
        this.createdDate = createdDate;
        this.userID = userID;
    }

    public int getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(int playlistID) {
        this.playlistID = playlistID;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}



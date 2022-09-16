public class Songs {
    private int song_id;
    private String song_name;
    private String song_duration;
    private String released_year;
    private int genre_id;
    private int album_id;
    private String song_path;

    public Songs(int song_id, String song_name, String song_duration, String released_year, int genre_id, int album_id, String song_path) {
        this.song_id = song_id;
        this.song_name = song_name;
        this.song_duration = song_duration;
        this.released_year = released_year;
        this.genre_id = genre_id;
        this.album_id = album_id;
        this.song_path = song_path;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getSong_duration() {
        return song_duration;
    }

    public void setSong_duration(String song_duration) {
        this.song_duration = song_duration;
    }

    public String getReleased_year() {
        return released_year;
    }

    public void setReleased_year(String released_year) {
        this.released_year = released_year;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getSong_path() {
        return song_path;
    }

    public void setSong_path(String song_path) {
        this.song_path = song_path;
    }
}
class Album{
    private int albumID;
    private String albumName;

    public Album(int albumID, String albumName) {
        this.albumID = albumID;
        this.albumName = albumName;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}

class Genre{
    private int genreID;
    private String genreName;

    public Genre(int genreID, String genreName) {
        this.genreID = genreID;
        this.genreName = genreName;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

}

class Artist{
    private int artist_id;
    private String artist_name;

    public Artist(int artist_id, String artist_name) {
        this.artist_id = artist_id;
        this.artist_name = artist_name;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }
}

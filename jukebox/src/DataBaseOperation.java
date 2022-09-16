import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataBaseOperation {
    jukeboxOperational jukebox = new jukeboxOperational();
    MyConnection c = new MyConnection();

    public List<Songs> songInitialize() {
        List<Songs> songsli = new ArrayList<>();
        try {
            Statement s = c.getStatement();
            ResultSet rs = s.executeQuery("select * from songs");
            while (rs.next()) {
                songsli.add(new Songs(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7)));
            }
            jukebox.displaySongs(songsli);

        } catch (SQLException se) {
            se.getStackTrace();
        }
        return songsli;
    }

    public void podcastInitialize() {
        List<podcast> podcastli = new ArrayList<>();
        try {
            Statement s = c.getStatement();
            ResultSet rs = s.executeQuery("select * from podcast");
            while (rs.next()) {
                podcastli.add(new podcast(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(5)));
            }
            jukebox.displayPodcast(podcastli);

        } catch (SQLException se) {
            se.getStackTrace();
        }


    }

    public List<Album> searchAlbum() throws SQLException {
        List<Album> album = new ArrayList<>();
        Statement s = c.getStatement();
        ResultSet rs = s.executeQuery("select * from album");

        while (rs.next())
        {
           album.add(new Album( rs.getInt(1),rs.getString(2)));
        }
        return album;
    }

    public List<Songs> readAlbumSearchResult(int num) throws SQLException {
        List<Songs> searchResult = new ArrayList<>();
        Statement s= c.getStatement();
        ResultSet rs = s.executeQuery("select * from songs where album_id="+num+";");
        while (rs.next()) {
            searchResult.add(new Songs(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7)));
        }
        return searchResult;

    }

    public List<Genre> searchGenre() throws SQLException {
        List<Genre> genre = new ArrayList<>();
        Statement s = c.getStatement();
        ResultSet rs = s.executeQuery("select * from genre");

        while (rs.next())
        {
            genre.add(new Genre( rs.getInt(1),rs.getString(2)));
        }
        return genre;
    }

    public List<Artist> searchArtist() throws SQLException {
        List<Artist> artist = new ArrayList<>();
        Statement s = c.getStatement();
        ResultSet rs = s.executeQuery("select a.artist_id,a.name\n" +
                "from songs s\n" +
                "join songs_artist sa on sa.song_id=s.song_id\n" +
                "join artist a on a.artist_id=sa.artist_id;");

        while (rs.next()) {
            artist.add(new Artist(rs.getInt(1), rs.getString(2)));
        }
        return artist;
    }

    public List<Songs> readArtistSearchResult(int num) throws SQLException {
        List<Songs> searchArtistResult = new ArrayList<>();
        Statement s= c.getStatement();
        ResultSet rs = s.executeQuery("select s.song_id,s.name,s.duration,s.released_year,s.genre_id,s.album_id,s.song_path\n" +
                "from songs s\n" +
                "join songs_artist sa on sa.song_id=s.song_id\n" +
                "join artist a on a.artist_id=sa.artist_id\n" +
                "where a.artist_id="+num+";");
        while (rs.next()) {
            searchArtistResult.add(new Songs(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7)));
        }
        return searchArtistResult;

    }

    public List<Songs> readSearchByName(String s) throws SQLException {
        List<Songs> searchByNameResult = new ArrayList<>();
        PreparedStatement ps = c.getPreparedStatement("select *\n" +
                "from songs\n" +
                "where name like '%"+s+"%'");
        ResultSet rs=ps.executeQuery();

        if(!rs.next()) {
            System.out.println("Song not Found !!!!! ");

        }else {
            while (rs.next()) {
                searchByNameResult.add(new Songs(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7)));
            }
        }

        return searchByNameResult;

    }

    public List<Celebrity> initializeCeleb() throws SQLException {
        List<Celebrity> celebrities = new ArrayList<>();
        Statement s= c.getStatement();
        ResultSet rs = s.executeQuery("select * from celebrity");
        while (rs.next())
        {
            celebrities.add(new Celebrity(rs.getInt(1),rs.getString(2)));
        }
        return celebrities;
    }

    public void displayPodcast(int num) throws SQLException {
        PreparedStatement ps= c.getPreparedStatement("select * from podcast where celebrity_id="+num+";");
        System.out.format("%-30s %-30s %-30s  %n","Podcast ID","Podcast Name","Released Date");
        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            System.out.format("%-30s %-30s %-30s  %n",rs.getInt(1),rs.getString(2),rs.getString(5));
        }

    }

    public void displayByDatePodcast(String s) throws SQLException {
        PreparedStatement ps= c.getPreparedStatement("select * from podcast where date like '%"+s+"%'");

        ResultSet rs = ps.executeQuery();
        if(!rs.next())
        {
            System.out.println("Podcast not found :"+s);
        }else {
            System.out.format("%-30s %-30s %-30s  %n","Podcast ID","Podcast Name","Released Date");
            while (rs.next()) {
                System.out.format("%-30s %-30s %-30s  %n", rs.getInt(1), rs.getString(2), rs.getString(5));
            }
        }

    }









}

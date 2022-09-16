import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class jukeboxOperational {
    MyConnection c= new MyConnection();




   public void displaySongs(List<Songs> songsList)
   {
       System.out.format("%-30s %-30s %-30s %-30s %n","Song id","Song name","Duration","Released year");
    for(Songs s:songsList)
    {
        System.out.format("%-30s %-30s %-30s %-30s %n",s.getSong_id(),s.getSong_name(),s.getSong_duration(),s.getReleased_year());
    }
    }
    public void displayPodcast(List<podcast> podcastList)
    {
        System.out.format("%-30s %-30s %-30s %n","Podcast id","Podcast name","Podcast Release Date");
        for(podcast p:podcastList)
        {
            System.out.format("%-30s %-30s %-30s %n",p.getPodcast_id(),p.getPodcast_name(),p.getPodReleasedDate());
        }
    }

    // user registration for access playlist
    public void registration() {
        try {

            PreparedStatement ps = c.getPreparedStatement("INSERT INTO user(name,phone,email ,password) VALUES (?,?,?,?)");
            Scanner sc = new Scanner(System.in);
            System.out.println("Please Enter your Full Name");
            String name = sc.nextLine();
            System.out.println("Please Enter your phone number");
            String phone = checkPhone(sc.next());
            System.out.println("Please Enter your email");
            String email = checkEmail(sc.next());
            System.out.println("Please Enter your password");
            String password =checkPassword(sc.next());

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, email);
            ps.setString(4, password);

           int x = ps.executeUpdate();
            if(x>0)
            {
                System.out.println("successfully registered");
                System.out.println("Please log in");
                userLogin();
            }else {
                System.out.println("Error in registration");
            }
        }catch (SQLException s)
        {
            s.getStackTrace();
        }

    }
    public String checkPhone(String phone)
    {
        Scanner sc = new Scanner(System.in);


        boolean b= true;
        if(phone==null)
        {
            System.out.println("Not valid");
        }
        else {
            Pattern p =Pattern.compile("(0/91)?[7-9][0-9]{9}");
            Matcher m = p.matcher(phone);
            b=m.matches();
        }
        if(b)
        {
            return phone;
        }
        else {
            updatePhone();
        }
        return "";

    }

    public void updatePhone()
    {

        Scanner sc = new Scanner(System.in);
        try {

//            String query="UPDATE customer \n" +
//                    "SET phone = ? \n" +
//                    "Where cust_id=?";
            PreparedStatement ps1=c.getPreparedStatement("update user set phone=? where user_id=?");
            System.out.println("Enter valid Phone Number");
            String phone= sc.next();
            checkPhone(phone);
            ps1.setString(1,phone);
            ps1.setInt(2,userIDLogIn);

            int rs = ps1.executeUpdate();

            System.out.println("Updated Phone : "+rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String checkEmail(String email)
    {
        boolean b= true;
        if(email==null)
        {
            System.out.println("Not valid");
        }
        else {
            Pattern p =Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
            Matcher m = p.matcher(email);
            b=m.matches();
        }
        if(b)
        {
            return email;
        }
        else {
            updateEmail();
        }
        return "";

    }

    public void updateEmail()
    {

        Scanner sc = new Scanner(System.in);
        try {
            PreparedStatement ps1=c.getPreparedStatement("update user set email=? where user_id=?");
            System.out.println("Enter valid Email Number");
            String email= sc.next();
            checkEmail(email);
            ps1.setString(1,email);
            ps1.setInt(2,userIDLogIn);

            int rs = ps1.executeUpdate();

            System.out.println("Updated Phone : "+rs);

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public String checkPassword(String password)
    {
        boolean b= true;
        if(password==null)
        {
            System.out.println("Not valid");
        }
        else {
            String regex = "^(?=.*[0-9])"
                    + "(?=.*[a-z])(?=.*[A-Z])"
                    + "(?=.*[@#$%^&+=])"
                    + "(?=\\S+$).{8,20}$";
            Pattern p =Pattern.compile(regex);
            Matcher m = p.matcher(password);
            b=m.matches();
        }
        if(b)
        {
            return password;
        }
        else {
            updatePassword();
        }
        return "";

    }

    public void updatePassword()
    {

        Scanner sc = new Scanner(System.in);
        try {
            PreparedStatement ps1=c.getPreparedStatement("update user set password=? where user_id=?");
            System.out.println("Enter valid Password");
            String password= sc.next();
            checkPassword(password);
            ps1.setString(1,password);
            ps1.setInt(2,userIDLogIn);

            int rs = ps1.executeUpdate();

            System.out.println("Updated Phone : "+rs);

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }




    // user login after registration or if the user already exist
    String email = null;
    public boolean userLogin() {
            boolean check = true;
        try {
            Scanner sc = new Scanner(System.in);
            PreparedStatement ps = c.getPreparedStatement("select * from user where email=? and password=?");

            System.out.println("Please enter your email");
            email = sc.next();
            System.out.println("Please enter your password");
            String pass = sc.next();
            ps.setString(1, email);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("successfully logged in");
            } else {
                check=false;
                System.out.println("Invalid Credentials !!!!!!");
                System.out.println("Please try again");
                userLogin();
            }
        } catch (SQLException se) {
            se.getStackTrace();
        }
        return check;
    }
    // for get userid if user resister successfully or logged in
    int userIDLogIn;
    public int  getUserID1() throws SQLException {
       int returnedUserID = 0;
       Statement s=  c.getStatement();
       ResultSet rs= s.executeQuery("select user_id from user where email='"+email+"';");
           while (rs.next()) {
               returnedUserID = rs.getInt(1);
           }
           userIDLogIn=returnedUserID;


        return returnedUserID;
    }


    // for insert values in playlist
    public void playlistSongsInsertion() {
        try {
            Scanner sc = new Scanner(System.in);
            PreparedStatement ps = c.getPreparedStatement("insert into playlist(name,created_date,user_id,playlist_type) values(?,?,?,1);");
            System.out.println("Enter Playlist Name");

            String name = sc.nextLine();
             LocalDate date = LocalDate.now();
            int uID = userIDLogIn;
            ps.setString(1, name);
            ps.setDate(2, Date.valueOf(date));
            ps.setInt(3, uID);
            int x = ps.executeUpdate();
            System.out.println(x);
            if (x > 0) {
                System.out.println("Playlist successfully created ");
            } else {
                System.out.println("There is problem with playlist creation");
            }


        }catch (SQLException s)
        {
            s.getMessage();
            s.getStackTrace();
        }

    }
    int currentPlaylistID;
    public void getPlaylistID(String name) throws SQLException {
        PreparedStatement ps= c.getPreparedStatement("select playlist_id from playlist where name='"+name+"'");
        ResultSet rs= ps.executeQuery();
        while (rs.next())
        {
           currentPlaylistID=rs.getInt(1);
        }
    }

    public void addSongInPlaylist(int playlistID,int songID) throws SQLException {


            PreparedStatement ps = c.getPreparedStatement("insert into playlistSongDetails (playlist_id,song_id) values(?,?);");
            ps.setInt(1,playlistID);// change it letter
            ps.setInt(2,songID);
            int x=ps.executeUpdate();
            System.out.println(x);


    }

    public void displaySongsFromPlaylist(int playlistID) throws SQLException {
        PreparedStatement ps = c.getPreparedStatement("select s.song_id,s.name,s.duration,s.released_year\n" +
                "from playlistSongDetails psd\n" +
                "join playlist p on psd.playlist_id=p.playlist_id\n" +
                "join songs s on s.song_id=psd.song_id\n" +
                "join user u on u.user_id=p.user_id\n" +
                "where u.user_id=? and p.playlist_id=?;");

        ps.setInt(1,userIDLogIn);
        ps.setInt(2,playlistID);

        ResultSet rs = ps.executeQuery();
        if(!rs.next()){
            System.out.println("Playlist is Empty with ID "+playlistID);
        }else {
           // System.out.println("Else Block 1");
            while (rs.next()) {
                System.out.format("%-30s %-30s %-30s %-30s %n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
            //System.out.println("Else Block 2");
            SimpleAudioPlayer ss = new SimpleAudioPlayer();
            ss.m1();
            ss.SongsPlay();
        }
    }

    public void displaySongsPlaylist() throws SQLException {
        PreparedStatement ps = c.getPreparedStatement("select * from playlist where user_id=? and playlist_type=1");
        ps.setInt(1,userIDLogIn);
        ResultSet rs =ps.executeQuery();
        System.out.format("%-30s %-30s %-30s %n","Playlist id","Playlist Name","Created Date");
        while (rs.next())
        {
            System.out.format("%-30s %-30s %-30s %n",rs.getInt(1),rs.getString(2),rs.getString(3));
        }


    }

    //********* For podcast*********************************************

    public void addPodcastInPlaylist(int playlistID,int podcastID) throws SQLException {


        PreparedStatement ps = c.getPreparedStatement("insert into playlistPodcastDetails (playlist_id,podcast_id) values(?,?);");
        ps.setInt(1,playlistID);// change it letter
        ps.setInt(2,podcastID);
        int x=ps.executeUpdate();
        System.out.println(x);


    }

    public void displayPodcastFromPlaylist(int playlistID) throws SQLException {
        PreparedStatement ps = c.getPreparedStatement("select p.podcast_id,p.topic,c.name,p.date\n" +
                "from playlistPodcastDetails ppd\n" +
                "join playlist pl on ppd.playlist_id=pl.playlist_id\n" +
                "join podcast p on p.podcast_id=ppd.podcast_id\n" +
                "join user u on u.user_id=pl.user_id\n" +
                "join celebrity c on c.celebrity_id=p.celebrity_id\n" +
                "where u.user_id=? and pl.playlist_id=?;");

        ps.setInt(1,userIDLogIn);
        ps.setInt(2,playlistID);

        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            System.out.format("%-30s %-30s %-30s %-30s",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
        }

    }
    public void displayPodcastPlaylist() throws SQLException {
        PreparedStatement ps = c.getPreparedStatement("select * from playlist where user_id=? and playlist_type=2");
        ps.setInt(1,userIDLogIn);
        ResultSet rs =ps.executeQuery();
        System.out.format("%-30s %-30s %-30s %n","Playlist id","Playlist Name","Created Date");
        while (rs.next())
        {
            System.out.format("%-30s %-30s %-30s %n",rs.getInt(1),rs.getString(2),rs.getString(3));
        }


    }
    public void playlistPodcastInsertion1() {
        try {
            Scanner sc = new Scanner(System.in);
            PreparedStatement ps = c.getPreparedStatement("insert into playlist(name,created_date,user_id,playlist_type) values(?,?,?,2);");
            System.out.println("Enter Playlist Name");

            String name = sc.nextLine();
            LocalDate date = LocalDate.now();
            int uID = userIDLogIn;
            ps.setString(1, name);
            ps.setDate(2, Date.valueOf(date));
            ps.setInt(3, uID);
            int x = ps.executeUpdate();
            System.out.println(x);
            if (x > 0) {
                System.out.println("Playlist successfully created ");
            } else {
                System.out.println("There is problem with playlist creation");
            }


        }catch (SQLException s)
        {
            s.getMessage();
            s.getStackTrace();
        }

    }


    public void displayAlbum() throws SQLException {
        DataBaseOperation db = new DataBaseOperation();
        System.out.format("%-30s %-30s  %n","Album ID","Album Name");
        List<Album> albums=db.searchAlbum();
        for(Album a:albums)
        {
            {
                System.out.format("%-30s %-30s  %n",a.getAlbumID(),a.getAlbumName());
            }
        }
    }

    public void displayAlbumSongs(int num) throws SQLException {
        DataBaseOperation db = new DataBaseOperation();
        List<Songs> songsList=db.readAlbumSearchResult(num);
        List <Songs> sorted = new ArrayList<>();
        System.out.format("%-30s %-30s %-30s %-30s %n","Song id","Song name","Duration","Released year");
       sorted =songsList.stream().sorted((p1,p2)->p1.getSong_name().compareToIgnoreCase(p2.getSong_name())).collect(Collectors.toList());
        for(Songs s:sorted)
        {
            System.out.format("%-30s %-30s %-30s %-30s %n",s.getSong_id(),s.getSong_name(),s.getSong_duration(),s.getReleased_year());
        }
    }

    public void displayGenre() throws SQLException {
        DataBaseOperation db = new DataBaseOperation();
        System.out.format("%-30s %-30s  %n","Genre ID","Genre Name");
        List<Genre> genre=db.searchGenre();
        for(Genre a:genre)
        {
            {
                System.out.format("%-30s %-30s  %n",a.getGenreID(),a.getGenreName());
            }
        }
    }
    public void displayArtist() throws SQLException {
        DataBaseOperation db = new DataBaseOperation();
        System.out.format("%-30s %-30s  %n","Artist ID","Artist name");
        List<Artist> genre=db.searchArtist();
        for(Artist a:genre)
        {
            {
                System.out.format("%-30s %-30s  %n",a.getArtist_id(),a.getArtist_name());
            }
        }
    }
    public void displayArtistSongs(int num) throws SQLException {
        DataBaseOperation  db= new DataBaseOperation();
        List<Songs> songsList=db.readArtistSearchResult(num);
        System.out.format("%-30s %-30s %-30s %-30s %n","Song id","Song name","Duration","Released year");
        for(Songs s:songsList)
        {
            System.out.format("%-30s %-30s %-30s %-30s %n",s.getSong_id(),s.getSong_name(),s.getSong_duration(),s.getReleased_year());
        }
    }

    public void displaySearchByNameSongs(String a) throws SQLException {
        DataBaseOperation  db= new DataBaseOperation();
        List<Songs> songsList=db.readSearchByName(a);
        if(!songsList.isEmpty()) {
            System.out.format("%-30s %-30s %-30s %-30s %n", "Song id", "Song name", "Duration", "Released year");
            for (Songs s : songsList) {
                System.out.format("%-30s %-30s %-30s %-30s %n", s.getSong_id(), s.getSong_name(), s.getSong_duration(), s.getReleased_year());
            SimpleAudioPlayer ss=new SimpleAudioPlayer();
            ss.m1();
            ss.SongsPlay();
            }
        }else {
            // search by name
            System.out.println("Song name containing "+a+" not exist.");

        }
    }

    public void displayCelebrity() throws SQLException {

        DataBaseOperation  db= new DataBaseOperation();
        List<Celebrity> celebrityList=db.initializeCeleb();
        System.out.format("%-30s %-30s  %n","Celebrity ID","Celebrity name");
        for(Celebrity a:celebrityList)
        {
            {
                System.out.format("%-30s %-30s  %n",a.getCelebId(),a.getCelebName());
            }
        }

    }







}

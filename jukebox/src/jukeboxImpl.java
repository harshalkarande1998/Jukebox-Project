import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class jukeboxImpl {
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, NullPointerException, SQLException, InterruptedException {
        DataBaseOperation db = new DataBaseOperation();
        jukeboxOperational jo = new jukeboxOperational();

        Scanner sc = new Scanner(System.in);

        int repeat;
        do {
            System.out.println("Select the option");
            System.out.println("1.      Songs");
            System.out.println("2.      Podcast");
            System.out.println("3.      Search Song");
            System.out.println("4.      Search Podcast");
            System.out.println("5       Playlist");
            int firstSelection = sc.nextInt();
            if (firstSelection == 1) {
                db.songInitialize();
                SimpleAudioPlayer ss = new SimpleAudioPlayer();
                ss.m1();
                ss.SongsPlay();
            } else if (firstSelection == 2) {
                db.podcastInitialize();
                SimplePodcastPlayer sp = new SimplePodcastPlayer();
                sp.p1();
                sp.podcastPlay();
            } else if (firstSelection == 5) {
                System.out.println("1.      Sign in");
                System.out.println("2.      Register user");
                int userSelect = sc.nextInt();
                boolean b;
                if (userSelect == 1) {
                    // log in
                    b = jo.userLogin();
                } else if (userSelect == 2) {
                    //sign in
                    jo.registration();
                }
                //******************************* playlist start here****************************************
                int checkRepeat = 1;
                while (checkRepeat == 1) {
                    if (b = true) {
                        System.out.println("1.  Create playlist");
                        System.out.println("2.  Display playlist");
                        System.out.println("3   Add in playlist.");
                        System.out.println("Enter the code");
                        int playlistOption = sc.nextInt();

                        if (playlistOption == 1) {
                            System.out.println("1.  Create songs playlist");
                            System.out.println("2.  Create podcast playlist");
                            System.out.println("Enter the code");
                            int playlistCreate = sc.nextInt();
                            if (playlistCreate == 1) {
                                //song playlist
                                jo.getUserID1();
                                jo.playlistSongsInsertion();
                                System.out.println("Your All playlist");
                                jo.displaySongsPlaylist();

                            } else if (playlistCreate == 2) {
                                //podcast playlist
                                jo.getUserID1();
                                jo.playlistPodcastInsertion1();
                                System.out.println("Your all playlist");
                                jo.displayPodcastPlaylist();


                            }

                        } else if (playlistOption == 2) {
                            // Display playlist
                            System.out.println("1.  Display songs playlist");
                            System.out.println("2.  Display podcast playlist");
                            System.out.println("Enter the code");
                            int display = sc.nextInt();
                            if (display == 1) {
                                jo.getUserID1();
                                jo.displaySongsPlaylist();
                                System.out.println("Enter the playlist id");
                                int playlistId = sc.nextInt();//////////////
                                jo.displaySongsFromPlaylist(playlistId);
                                SimpleAudioPlayer ss = new SimpleAudioPlayer();
                               // ss.m1();
                                //ss.SongsPlay();
                            } else if (display == 2) {
                                jo.getUserID1();
                                jo.displayPodcastPlaylist();
                                System.out.println("Enter the playlist id");
                                int playlistId = sc.nextInt();
                                jo.displayPodcastFromPlaylist(playlistId);
                                SimplePodcastPlayer ss = new SimplePodcastPlayer();
                                ss.p1();
                                ss.podcastPlay();

                            }


                        } else if (playlistOption == 3) {
                            System.out.println("1.  Add songs playlist");
                            System.out.println("2.  Add podcast playlist");
                            System.out.println("Enter the code");
                            int add = sc.nextInt();
                            if (add == 1) {
                                jo.getUserID1();
                                jo.displaySongsPlaylist();
                                System.out.println("Enter the playlist code ");
                                int playlistID = sc.nextInt();
                                int stop = 0;
                                do {
                                    db.songInitialize();
                                    System.out.println("Enter the song code");
                                    int playSongID = sc.nextInt();
                                    jo.addSongInPlaylist(playlistID, playSongID);
                                    System.out.println("Do you want to add More song yes==1/ no ==2");
                                    stop = sc.nextInt();
                                    if (stop == 2) {
                                        break;
                                    }
                                } while (stop == 1);
                            } else if (add == 2) {
                                jo.getUserID1();
                                jo.displayPodcastPlaylist();
                                System.out.println("Enter the playlist code ");
                                int playlistID = sc.nextInt();
                                int stop = 0;
                                do {
                                    db.podcastInitialize();
                                    System.out.println("Enter the podcast code");
                                    int playPodcastID = sc.nextInt();
                                    jo.addPodcastInPlaylist(playlistID, playPodcastID);
                                    System.out.println("Do you want to add More song yes==1/ no ==2");
                                    stop = sc.nextInt();
                                    if (stop == 2) {
                                        break;
                                    }
                                } while (stop == 1);
                            }
                        }
                    }
                    System.out.println("Want to continue with playlist (yes==1/no==2)");
                    checkRepeat = sc.nextInt();
                    if (checkRepeat == 2) {
                        System.out.println("Thank you ");
                        break;
                    }
                }

            } else if (firstSelection == 3) {
                System.out.println("Search song by");
                System.out.println("1.      Artist");
                System.out.println("2.      Album");
                System.out.println("3.      genre");
                System.out.println("4.      Song Name");

                int searchSong = sc.nextInt();
                if (searchSong == 1) {
                    jo.displayArtist();
                    db.searchArtist();
                    System.out.println("Select the artist and enter the id ");
                    int artistID = sc.nextInt();
                    jo.displayArtistSongs(artistID);
                    SimpleAudioPlayer ss = new SimpleAudioPlayer();
                    ss.m1();
                    ss.SongsPlay();


                } else if (searchSong == 2) {
                    jo.displayAlbum();
                    db.searchAlbum();
                    System.out.println("Select the album and enter the id");
                    int albumID = sc.nextInt();
                    jo.displayAlbumSongs(albumID);
                    SimpleAudioPlayer ss = new SimpleAudioPlayer();
                    ss.m1();
                    ss.SongsPlay();

                } else if (searchSong == 3) {
                    jo.displayGenre();
                    db.searchGenre();
                    System.out.println("Select the genre and enter the id");
                    int genreID = sc.nextInt();
                    jo.displayAlbumSongs(genreID);
                    SimpleAudioPlayer ss = new SimpleAudioPlayer();
                    ss.m1();
                    ss.SongsPlay();
                } else if (searchSong == 4) {
                    // search by name
                    System.out.println("Enter the Name of song");
                    sc.nextLine();
                    String sName = sc.nextLine();
                    jo.displaySearchByNameSongs(sName);
                    SimpleAudioPlayer ss = new SimpleAudioPlayer();
                    //ss.m1();
                   // ss.SongsPlay();

                }


            } else if (firstSelection == 4) {
                // podcast search
                System.out.println("1.      Search by celebrity");
                System.out.println("2.      Search by Date");
                System.out.println("Enter the code ");
                int celebritySearch = sc.nextInt();
                if (celebritySearch == 1) {
                    //search by celebrity
                    jo.displayCelebrity();
                    System.out.println("Enter celebrity id");
                    int celebID = sc.nextInt();
                    db.displayPodcast(celebID);
                    SimplePodcastPlayer p = new SimplePodcastPlayer();
                    p.p1();
                    p.podcastPlay();


                } else if (celebritySearch == 2) {
                    // search by date
                    System.out.println("Please Enter released date");
                    String date = sc.next();
                    db.displayByDatePodcast(date);
                    SimplePodcastPlayer p = new SimplePodcastPlayer();
                    p.p1();
                    p.podcastPlay();

                }

            }
            System.out.println("Do you want to continue with application yes==1 / no==2");
            repeat=sc.nextInt();
            if(repeat==2)
            {
                System.out.println("thank you");
                break;
            }

        } while (repeat==1);
    }
}

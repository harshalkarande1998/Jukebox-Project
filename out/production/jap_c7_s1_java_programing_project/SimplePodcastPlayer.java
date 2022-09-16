import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SimplePodcastPlayer {



        static String filefromDB;
        static int songCode;

        public void nextSong() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        /*MyConnection c= new MyConnection();
        try {
            Scanner sc = new Scanner(System.in);
            PreparedStatement ps = c.getPreparedStatement("select song_path from songs where song_id=?");
            songCode=songCode+1;

            ps.setInt(1,songCode);
            ResultSet rs= ps.executeQuery();

            while (rs.next())
            {
                filefromDB=rs.getString(1);
                if(filefromDB==null){
                    System.out.println("Next Song does not exist!!!!");
                }
            }
            filePath = filefromDB;
            audioInputStream =
                    AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

            // create clip reference
            clip = AudioSystem.getClip();

            // open audioInputStream to the clip
            clip.open(audioInputStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            this.stop();
            this.play(clip);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        }
        public void p3()
        {
            MyConnection c= new MyConnection();
            try {
                Scanner sc = new Scanner(System.in);
                PreparedStatement ps = c.getPreparedStatement("select podcast_path from podcast where podcast_id=?");
                songCode=songCode-1;
                ps.setInt(1,songCode);

                ResultSet rs= ps.executeQuery();
                if(!rs.next())
                {
                    System.out.println("Previous podcast is not available");
                }else {
                    //while (rs.next()) {
                        filefromDB = rs.getString(1);
                    //}
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public void p2(){
            MyConnection c= new MyConnection();
            try {
                Scanner sc = new Scanner(System.in);
                PreparedStatement ps = c.getPreparedStatement("select podcast_path from podcast where podcast_id=?");
                songCode=songCode+1;
                System.out.println(songCode);
                ps.setInt(1,songCode);

                ResultSet rs= ps.executeQuery();
                if(!rs.next())
                {
                    System.out.println("Next podcast not available");
                }else {
                    //System.out.println("+++++++++++Else block of podcast");
                   // while (rs.next()) {
                        filefromDB = rs.getString(1);
                        //System.out.println(filefromDB+"****************");
                    //}
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public void p1()
        {
            MyConnection c= new MyConnection();
            try {
                Scanner sc = new Scanner(System.in);
                PreparedStatement ps = c.getPreparedStatement("select podcast_path from podcast where podcast_id=?");

                System.out.println("Enter Song code");
                songCode=sc.nextInt();


                ps.setInt(1,songCode);

                ResultSet rs= ps.executeQuery();

                while (rs.next())
                {
                    filefromDB=rs.getString(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // to store current position
        Long currentFrame;
        Clip clip;

        // current status of clip
        String status;

        AudioInputStream audioInputStream;
        static String filePath;

        // constructor to initialize streams and clip
   /* public SimpleAudioPlayer()
            throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        // create AudioInputStream object
        audioInputStream =
                AudioSystem.getAudioInputStream(new File("songs/Believer.wav").getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
*/

        public Clip playClip1() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

            filePath = filefromDB;
            audioInputStream =
                    AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

            // create clip reference
            clip = AudioSystem.getClip();

            // open audioInputStream to the clip
            clip.open(audioInputStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            return clip;
        }

        public void podcastPlay()
        {
            try
            {

                SimplePodcastPlayer podcastPlayer =
                        new SimplePodcastPlayer();
                Clip clip=podcastPlayer.playClip1();
                podcastPlayer.play(clip);
                Scanner sc = new Scanner(System.in);

                while (true)
                {
                    System.out.println("1. pause");
                    System.out.println("2. resume");
                    System.out.println("3. restart");
                    System.out.println("4. stop");
                    System.out.println("5. Next ");
                    System.out.println("6. Prev ");
                    int c = sc.nextInt();
                   podcastPlayer.gotoChoice(c);
                    if (c == 4)
                        break;
                }


            }


            catch (Exception ex)
            {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();

            }
        }

        // Work as the user enters his choice

        private void gotoChoice(int c)
                throws IOException, LineUnavailableException, UnsupportedAudioFileException
        {
            switch (c)
            {
                case 1:
                    pause();
                    break;
                case 2:
                    resumeAudio();
                    break;
                case 3:
                    restart();
                    break;
                case 4:
                    stop();
                    break;
                case 5:
                    stop();
                    p2();
                    playClip1();
                    break;
                case 6:
                    stop();
                    p3();
                    playClip1();
                    break;

            }

        }

        // Method to play the audio
        public void play(Clip clip)
        {
            //start the clip

            clip.start();

            status = "play";
            System.out.println("Status after play"+status);
        }

        // Method to pause the audio
        public void pause()
        {
            if (status.equals("paused"))
            {
                System.out.println("audio is already paused");
                return;
            }
            this.currentFrame = this.clip.getMicrosecondPosition();
            clip.stop();
            status = "paused";
            System.out.println("Status after play"+status);
        }

        // Method to resume the audio
        public void resumeAudio() throws UnsupportedAudioFileException,
                IOException, LineUnavailableException
        {
            if (status.equals("play"))
            {
                System.out.println("Audio is already "+
                        "being played");
                return;
            }
            clip.close();
            resetAudioStream();
            clip.setMicrosecondPosition(currentFrame);
            this.play(clip);
        }

        // Method to restart the audio
        public void restart() throws IOException, LineUnavailableException,
                UnsupportedAudioFileException
        {
            clip.stop();
            clip.close();
            resetAudioStream();
            currentFrame = 0L;
            clip.setMicrosecondPosition(0);
            this.play(clip);
        }

        // Method to stop the audio
        public void stop() throws UnsupportedAudioFileException,
                IOException, LineUnavailableException
        {
            currentFrame = 0L;
            clip.stop();
            clip.close();
        }

        // Method to jump over a specific part
        public void jump(long c) throws UnsupportedAudioFileException, IOException,
                LineUnavailableException
        {
            if (c > 0 && c < clip.getMicrosecondLength())
            {
                clip.stop();
                clip.close();
                resetAudioStream();
                currentFrame = c;
                clip.setMicrosecondPosition(c);
                this.play(clip);
            }
        }

        // Method to reset audio stream
        public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
                LineUnavailableException
        {
            audioInputStream = AudioSystem.getAudioInputStream(
                    new File(filePath).getAbsoluteFile());
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }


}

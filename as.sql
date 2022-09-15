CREATE DATABASE jukebox;

# Creating user table
CREATE TABLE user( user_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
					name VARCHAR(60) NOT NULL,
                    phone VARCHAR(60) NOT NULL UNIQUE,
                    email VARCHAR(60) NOT NULL UNIQUE,
                    password VARCHAR(60) NOT NULL UNIQUE
                    );
  
# creating songs table
CREATE TABLE songs(song_id INT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
					name VARCHAR(80) NOT NULL,
                    duration VARCHAR(40) NOT NULL,
                    released_year VARCHAR(40) NOT NULL,
                    genre_id INT,
                    album_id INT,
                    song_path VARCHAR(60) NOT NULL,
                    CONSTRAINT songs_genre_c FOREIGN KEY (genre_id)
                    REFERENCES genre(genre_id),
                    CONSTRAINT songs_album_c FOREIGN KEY (album_id)
                    REFERENCES album(album_id)
                    );

CREATE TABLE songs_artist (song_id INT NOT NULL,
							artist_id INT NOT NULL,
                            PRIMARY KEY (song_id,artist_id)
							);
                            

CREATE TABLE artist(artist_id INT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
					name VARCHAR(40)
					);	
                    
CREATE TABLE genre(genre_id INT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
					name VARCHAR(40));
                    

CREATE TABLE album(album_id INT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
					name VARCHAR(40));
                    

CREATE TABLE podcast(podcast_id INT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
						topic VARCHAR(40) NOT NULL,
                        celebrity_id INT NOT NULL,
                        podcast_path VARCHAR(80) NOT NULL,
                        date DATE NOT NULL,
                        CONSTRAINT podcast_celeb_c FOREIGN KEY (celebrity_id)
                        REFERENCES celebrity(celebrity_id)
                        
						);
                        
                        
CREATE TABLE celebrity(celebrity_id INT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
						name VARCHAR(40) NOT NULL);
                        
CREATE TABLE episode(episode_id INT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
						name VARCHAR(40)NOT NULL,
                        podcast_id INT,
						episode_path VARCHAR(80) NOT NULL,
                        relese_date DATE NOT NULL,
                        CONSTRAINT episode_podcast_c FOREIGN KEY (podcast_id)
                        REFERENCES podcast(podcast_id) );
                        
CREATE TABLE playlist(playlist_id INT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
						name VARCHAR(40) NOT NULL,
                        created_date DATE NOT NULL,
                        user_id INT NOT NULL,
                        playlist_type Int not null,
                        CONSTRAINT playlist_user_c FOREIGN KEY (user_id)
                        REFERENCES user(user_id)
                        );
                        
                        
CREATE TABLE playlistSongDetails(detail_id INT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
									playlist_id INT,
                                    song_id INT,
									CONSTRAINT playlistSongDetails_playlist_c FOREIGN KEY (playlist_id)
                                    REFERENCES playlist(playlist_id),
                                    CONSTRAINT playlistSongDetails_songs_c FOREIGN KEY (song_id)
                                    REFERENCES songs(song_id)
									);
                                    
                                    
CREATE TABLE playlistPodcastDetails(detail_id INT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT ,
										playlist_id INT,
                                        podcast_id INT,
                                        CONSTRAINT playlistPodcastDetails_playlist_c FOREIGN KEY (playlist_id)
                                        REFERENCES playlist(playlist_id),
                                        CONSTRAINT playlistPodcastDetails_podcast_c FOREIGN KEY (podcast_id)
                                        REFERENCES podcast(podcast_id)
                                        );
                                        
                                        
SELECT * FROM podcast;

insert into playlistSongDetails (playlist_id,song_id) values (1,1);

select * from playlist;
drop table playlist;									
drop table playlistSongDetails;
drop table  playlistPodcastDetails;
                                        
insert into playlistsongdetails (playlist_id,song_id) values(?,?);

select * from playlist;

select * from playlistSongDetails;

select psd.playlist_id,psd.song_id,p.name,s.name,u.user_id from 
playlistSongDetails psd
join playlist p on psd.playlist_id=p.playlist_id
join songs s on s.song_id=psd.song_id
join user u on u.user_id=p.user_id
where u.user_id=1 and p.playlist_id=13;

select * from playlist 
where user_id="+userIDLogIn+"and playlist_type=2;

insert into playlist(name,created_date,user_id,playlist_type) values('My music','2020-10-10',1,1);

select * from songs;

select * from podcast;

select p.podcast_id,p.topic,c.name,p.date
from playlistpodcastDetails ppd
join playlist pl on ppd.playlist_id=pl.playlist_id
join podcast p on p.podcast_id=ppd.podcast_id
join user u on u.user_id=pl.user_id
join celebrity c on c.celebrity_id=p.celebrity_id
where u.user_id=1 and pl.playlist_id=13;


select* from  playlistSongDetails;

delete from playlistSongDetails where detail_id=3;
 
 
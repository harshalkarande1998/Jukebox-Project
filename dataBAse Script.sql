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
                        
CREATE TABLE playlist(playlist_id INT PRIMARY KEY NOT NULL UNIQUE,
						name VARCHAR(40) NOT NULL,
                        created_date DATE NOT NULL,
                        user_id INT NOT NULL,
                        CONSTRAINT playlist_user_c FOREIGN KEY (user_id)
                        REFERENCES user(user_id)
                        );
                        
                        
CREATE TABLE playlistSongDetails(detail_id INT PRIMARY KEY NOT NULL UNIQUE,
									playlist_id INT,
                                    song_id INT,
									CONSTRAINT playlistSongDetails_playlist_c FOREIGN KEY (playlist_id)
                                    REFERENCES playlist(playlist_id),
                                    CONSTRAINT playlistSongDetails_songs_c FOREIGN KEY (song_id)
                                    REFERENCES songs(song_id)
									);
                                    
                                    
CREATE TABLE playlistPodcastDetails(detail_id INT PRIMARY KEY NOT NULL UNIQUE,
										playlist_id INT,
                                        podcast_id INT,
                                        CONSTRAINT playlistPodcastDetails_playlist_c FOREIGN KEY (playlist_id)
                                        REFERENCES playlist(playlist_id),
                                        CONSTRAINT playlistPodcastDetails_podcast_c FOREIGN KEY (podcast)
                                        REFERENCES podcast(podcast_id)
                                        );
                                        
                                        
                        
                        
                        

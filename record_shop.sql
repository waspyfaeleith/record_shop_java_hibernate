DROP TABLE albums;
DROP TABLE artists;

CREATE TABLE artists (
  id SERIAL8 NOT NULL PRIMARY KEY,
  name VARCHAR
);

CREATE TABLE albums (
  id SERIAL8 NOT NULL PRIMARY KEY,
  title VARCHAR,
  quantity INT8,
  artist_id INT8 REFERENCES artists(id)
);
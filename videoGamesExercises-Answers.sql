
use cbourke;

# 01. List all video games in the database
SELECT * FROM game;
# List all video games that start with 'G'
SELECT * FROM game WHERE name LIKE 'G%';
# 02. List all publishers in the database
SELECT * FROM publisher;
# 03. List all video games along with their publishers
SELECT * FROM game g JOIN publisher p ON g.publisher_id = p.publisher_id;
# 04. List all video games along with their publishers, but only the relevant fields
SELECT g.name AS gameName, p.name AS publisherName FROM game g JOIN publisher p ON g.publisher_id = p.publisher_id;
# 05. List all publishers in the database along with all their games, even if they don't have any
SELECT p.name AS publisherName, g.name AS gameName FROM publisher p LEFT JOIN game g ON p.publisher_id = g.publisher_id;
# alternative: cast null values as another string:
SELECT p.name AS publisherName, CASE
    WHEN g.name IS NOT NULL THEN g.name 
    ELSE 'unknown' END AS gameName
    FROM publisher p LEFT JOIN game g ON p.publisher_id = g.publisher_id;
# 06. List all publishers with a count of how many games they have
SELECT p.name AS publisherName, COUNT(g.game_id) 
    FROM publisher p 
    LEFT JOIN game g ON p.publisher_id = g.publisher_id 
    GROUP BY p.publisher_id
    ORDER BY publisherName;
# 07. List all games and all systems that they are available on 
SELECT g.name AS gameName,
       p.name AS platformName,
       a.publish_year AS publisherYear
    FROM game g
    JOIN availability a ON a.game_id = g.game_id 
    JOIN platform p ON p.platform_id = a.platform_id;
# 08. List all games that are not available on any system
SELECT g.game_id, g.name
    FROM game g
    LEFT JOIN availability a ON a.game_id = g.game_id 
    WHERE a.availability_id IS NULL;
# 09. List the oldest game(s) and its platform(s)
SELECT g.name AS gameName,
       p.name AS platformName,
       a.publish_year AS publisherYear
    FROM game g
    JOIN availability a ON a.game_id = g.game_id 
    JOIN platform p ON p.platform_id = a.platform_id
    WHERE a.publish_year = (SELECT MIN(publish_year) FROM availability);
# 10. Flatten the entire data model by returning all data on all games
SELECT g.name AS gameName,
       pub.name AS publisher,
       p.name AS platformName,
       a.publish_year AS publisherYear
    FROM game g
    JOIN publisher pub on g.publisher_id = pub.publisher_id
    JOIN availability a ON a.game_id = g.game_id 
    JOIN platform p ON p.platform_id = a.platform_id;

# . Insert a new game, Assassin's Creed, published by Ubisoft
INSERT INTO publisher (name) VALUES ('Ubisoft');
INSERT INTO game (name, publisher_id) VALUES ('Assassin\'s Creed', (SELECT publisher_id FROM publisher WHERE name = 'Ubisoft'));

# . Make the new game available on at least two platforms
INSERT INTO availability (game_id,platform_id,publish_year) VALUES (
    (SELECT game_id FROM game WHERE name = 'Assassin\'s Creed'),
    (SELECT platform_id FROM platform WHERE name = 'PC'),
    2012);
INSERT INTO availability (game_id,platform_id,publish_year) VALUES (
    (SELECT game_id FROM game WHERE name = 'Assassin\'s Creed'),
    (SELECT platform_id FROM platform WHERE name = 'Playstation 3'),
    2012);

# . Update the record for Megaman 4: the publisher should be Capcom, not Eidos
SELECT * FROM game WHERE name LIKE 'Mega%';
SELECT * FROM publisher WHERE name LIKE 'Cap%';
UPDATE game SET publisher_id = 8 WHERE game_id = 9;

# . Delete the publisher Eidos: how?
# You need to first delete any game associated with Eidos, but
# any availability record that refers to all the games that Eidos
# has published.
SELECT * FROM game g 
    JOIN publisher p ON g.publisher_id = p.publisher_id
    JOIN availability a ON a.game_id = g.game_id
    WHERE p.name = 'Eidos';
DELETE FROM availability WHERE availability_id IN (15, 16);
DELETE FROM game WHERE game_id IN (12);
DELETE FROM publisher WHERE publisher_id = 10;

# . Write a query to return all games along with the number of platforms they
#   are available on
SELECT g.name AS gameName,
       COUNT(p.platform_id) AS numberOfPlatforms
    FROM game g
    LEFT JOIN availability a ON g.game_id = a.game_id
    LEFT JOIN platform p ON p.platform_id = a.platform_id
    GROUP BY g.game_id
    ORDER BY numberOfPlatforms DESC, gameName DESC;
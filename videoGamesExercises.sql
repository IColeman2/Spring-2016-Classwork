use videoGames;
# 01. List all video games in the database
# List all video games that start with 'G'
# 02. List all publishers in the database
# 03. List all video games along with their publishers
select * from game g
	left join publisher p on p.publisher_id = g.publisher_id;
# 04. List all video games along with their publishers, but only the relevant fields
select g.name, p.name from game g
	join publisher p on p.publisher_id = g.publisher_id;
# 05. List all publishers in the database along with all their games, even if they don't have any
select * from publisher p
	left join publisher p on p.publisher_id = g.publisher_id;
# 06. List all publishers with a count of how many games they have
select p.name, count(g.game_id) as numGames from publisher p
	left join game g on p.publisher_id = g.publisher_id
	group by p.publisher_id;
# 07. List all games and all systems that they are available on 
select * from game g
	join availability a on g.game_id = a.game_id
    join platform p on a.platfrom_id = p.platform_id;
# 08. List all games that are not available on any system
select g.name from game g
	left join availability a on g.game_id = a.game_id
    where a.availability_id is null;
# 09. List the oldest game(s) and its platform(s)
select * from game g
	left join availability a on g.game_id = a.game_id
	left join platform p on a.platform_id = p.platform_id
    where a.publish_year = (select min(publish_year) from availability);
# 10. Flatten the entire data model by returning all data on all games
select * from game g;
	
# . Insert a new game, Assassin's Creed, published by Ubisoft
# . Make the new game available on at least two platforms

# . Update the record for Megaman 4: the publisher should be Capcom, not Eidos

# . Delete the publisher Eidos: how?
# You need to first delete any game associated with Eidos, but
# any availability record that refers to all the games that Eidos
# has published.

# . Write a query to return all games along with the number of platforms they
#   are available on

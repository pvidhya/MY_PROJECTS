****VExplore****

****Total lines of code:4,975

****Features implemented in the project:
1. Jsp: Java Servlet Pages were used to implement the functionalioties:
	*Sign up --- A new user can create an account. 
	*Login --- The existing users can login to their accounts.
	*User Profile --- This page display all the user details including, first name, last name, contact details, trips taken, trips planned.
	*Friend List --- All the user's friends would be displayed with a "View Profile" option.
	*Friend Requests --- User can send a friend request to a user, Accept requests from other users.
	*Trips --- All the trips (past or future) will be displayed.
	*Notifications --- All the notifications, like friend request accepted, itenerary created, itenerary joined will be displayed.
	*Creating Trips --- A user can create a trip by adding multiple destinations.
	*Viewing Itenerary --- A user can view the itenerary created.
	*Viewing Itenerary Author's Profile --- A user can view the profile of other user who has created an itenerary.
	*Request to join Itenerary --- A user can request to join an itenerary created by another user.
	*View Itenerary Join Requests --- A user can view the itenarary requests taht he/she received by other users to join his own itenerary.
	*Viewing Recommendations --- Every user will get recommendation for places to visit on the basis of the interest selected by him/her. The user can select any of the 4 interests(Beach, Hiking, Casinos, Historical).
	*Twitter feeds --- Tweets related to travel trips, travel lists and travel destinations would be pulled from the v_explore screen name. 
	*HTML/CSS --- TO create all the basic html tags in the jsp files and to create the "CreateItenirary.html"


2. Servlets: Servlet was used to implement the friend search feature. All the users having an account in the database can be searched using their name.
3. Ajax: To implement Auto-search feature. 
4. MySql: To store user information, user interests, friends list, itenerary details(places, date, latitudes longitudes), notification(freind request status)
5. Twitter APIs: To display the tweets related to travel and show travel tips and travel places.
6. Python: Python scripting language was used to pull tweets from the screen name v_explore.


#########Extra Fetaures used:
1. Google map APIs: The maps were used to display the location, with particular latitude and longitude.
2. JavaScript: JS was used to dispaly the search tables and to dispaly the maps.
3. Json: It is used to collect the top places retrieved using google map API.

*************TO RUN THE PROJECT:***************
1. Create the tables using the create queries mentioned under"Creation Schema".
2. Complie all the .java files inside the "Classes" folder and inside the "Explorer" folder.
3. Start the server.
4. Type "http://localhost/explorer/" in the url field. The "VEXPLORE" Home page would be displayed.



********************************************************************************CREATION SCHEMA*********************************************************************************************
create database explorer;

use explorer;

create table usr_acc(user_id int not null,fname varchar(20) not null,lname varchar(120) not null,contact varchar(20) not null,email_id varchar(250),primary key(user_id));

create table usr_interest(user_id int not null, hobbies varchar(120), foreign key(user_id) references usr_acc(user_id));

create table usr_itinerary(itinerary_id int not null,itinerary_desc varchar(500) not null,latd Decimal(9,6) not null, longd Decimal(9,6) not null,owner_id int not null,creation_date date not null, visited_date date,foreign key(owner_id) references usr_acc(user_id),primary key(itinerary_id));

create table usr_itinerary_grp(user_id int not null, itinerary_id int not null, foreign key(user_id) references usr_acc(user_id), foreign key(itinerary_id) references usr_itinerary(itinerary_id));

create table ur_notifications(user_id_sender int not null,user_id_reciever int not null,notif_id int not null,notif_type varchar(10) not null,status varchar(30),timestamp datetime,notif_frn_id int,foreign key(user_id_sender) references usr_acc(user_id),foreign key(user_id_reciever) references usr_acc(user_id));

create table usr_regist(user_id int not null,username varchar(20) not null,pass varchar(20) not null,foreign key(user_id) references usr_acc(user_id));

create table usr_friends(user_id int not null, friend_id int not null, foreign key(user_id) references usr_acc(user_id), foreign key(friend_id) references usr_acc(user_id));
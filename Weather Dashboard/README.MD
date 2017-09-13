# Weather Dashboard

### Description
This is the weather application that displays past week's weather information based on the location. 

### Front End: 
The user enters the city name in the text box. An API call is made to fetch the required information from the retrieved JSON data. 
* Chart.JS is used to plot the data on the web page.
* The graphs plotted display today’s humidity, Temperature, Pressure and Wind speed. 
* The graphs of the past weeks are displayed where two lines(min, max) represent the minimum and maximum temperature, pressure, humidity and wind speed of the location during the day over a week span

### Backend:
Flask framework is used to build the backend of the application. Used YahooWeatherAPI to fetch initial data.

### Cloud:
Used Google Cloud Platform to deploy the application on VM instance.

### Microservice 1
fetchFromAPI.py:  This microservice uses Yahoo weather API to fetch the weather information. It has an API function that can fetch temperature, humidity, pressure, wind speed of a city from Yahoo weather database.

### Microservice 2
dbAPI: This microservice provides an API interface to create, drop and write into the database.
	
### Microservice 3
reader.py: This microservice provides an API interface to read from the database corresponding to the city input.

### Microservice 4
run.py: This is the script to run all the API calls to fetch information, and put data into the database once every hour. This code fetches the data once in an hour.

### Microservice 5
app.py: This microservice extracts the current date’s data and past week’s and uses them as inputs for the graphs.

### Database:
Sqlite3 is used as the database.
* There is one table in the database- ”Weather”
* The table has Columns- city, timestamp, temperature, humidity, pressure, wind speed
* It uses dbAPI to write in this table
* It uses Read.py to read from this table

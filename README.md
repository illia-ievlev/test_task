to launch the application please enter the required data into the src/main/resources/application.yaml file

Additionally, create a database in MySQL. 

Migration should occur automatically to the specified database.

please execute the set of commands provided in the file src/main/resources/sql/filling_the_database.sql to fill the database with data

A global search for names using a template was performed using criteria api to avoid the possibility of sql injections.

There was a choice regarding how to generate statistics: using database tools or Java code. The option of Java code was chosen because it provides more opportunities for testing and debugging the code.
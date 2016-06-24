#README for the Phedimap 2.0 application#

###This README will contain important information concerning the ```Phedimap 2.0``` application including:
1. Requirements necessary to start the application.
2. How to start the application.
3. A small amount of information about the current options of the application. 

###The requirements the user has to cope with to run the application
1. The user need to have access to a working Internet connection. 
2. The user needs to have access to a valid OMIM-key (provided by the application valid until a few weeks after activation and free to request again). 
3. The user needs to have access to a browser. 
4. The user needs to have IntelliJ installed.
5. The user needs to have a Mysql account with a available database.

###How to run the application
To run this application the following steps need to be followed. If you are running this new version of the application for the first time, then follow all steps.
If you have followed the first 7 steps successfully, you will only have to run the last two steps of the second enumeration of steps every new run.


### follow the following enumeration of steps
1. Install IntelliJ.
2. Clone this repository to IntelliJ.
3. Add the required dependencies by following the following steps

   > 1. Press the following key combination in IntelliJ: ```control+shift+alt+s```.
   > 2. A pop up appears. Click on the little green plus sign in ```dependencies``` under ```modules``` and select ```JARs or directories```.
   > 3. Navigate to the folder ```Lib``` (this folder is delivered with this application directly under the main map) and select the following JARs
   > ```Weka.Jar```,    ```Model.jar```,```mysql-connector-java-5.1.38-bin.jar``` and ```mysql-connector-java-5.1.35.jar```. and add add them by clicking on the ```apply``` button.

You now have the application and all dependencies added.
###Now follow the next enumeration of steps.
4. Download the files ```Disease.sql, Wek1.csv and Omimdata.csv``` (delivered within the download section of this repository) and place them all in the same directory and  open a terminal in this same directory.

5. Enter the following line in the command line of the opened terminal: mysql -u "your username" -D "your database" -p --local-infile "yourDatabase"<Disease.sql 
   for example: mysql -u john -D Johndatabase -p --local-infile Johndatabase<Disease.sql

6. Navigate to the file ```database.properties``` under the directory ```properties```  in the project and replace the database credentials there with your own.(Due to some problems there still are some more database.properties files in the application. These can be ignored since they have no function, only the one in the ```properties``` file counts)
7. Navigate to the file ```application.properties``` under the directory ```resources``` in the project and replace the database credentials there with your own.
8. Start the application by pressing right mouse button on the ```Application.java``` file and selecting ```Run 'application'```.
9. Open a browser and type: http://localhost:8086

You have now successfully started the application, and added your personal database information. click on the tab: ```how to``` in the application for basic information about how to use the application.


Important node: Due to a problem deep within the J-algorithm code. It is not possible to use this algorithm with databases outside of the mysql network. 
While the rest of the application works fine on all locations that have the right starting conditions, this algorithm is dependent on the mysql network.
#README for the diseasefinder application#

###This README will contain important information concerning the ```diseasefinder``` application including:
1. Requirements necessary to start the application.
2. How to start the application.
3. A small amount of information about the current options of the application. 

###The requirements the user has to cope with to run the application###
1. The user need to have access to a working Internet connection.
2. The user needs to have access to a valid OMIM-key (provided by the application valid until a few weeks after activation and free to request again).
3. The user needs to have access to a browser.
4. To run this version of the application the user also needs to have access to IntellIJ to run the application at its best.

###How to run the application###
Running the application is simple, all necessary software has already been provided by this repository, just follow the following steps. 
1. Clone the application to IntellIJ. 
2. Run the application by pressing the right mouse button on the ```application``` class (this class can be found under the directory ```src/main/java/nl.bioinf.diseaseFinderSpring/controllers```.
3: Open a browser and type: localhost:8087.

***note: in the case that running the application results in an error: press the refresh button (rotating arrows) in the ```Gradle``` tab. Then press the OK button on the appearing pop-up. Then repeat steps 2 and 3 described above. Everything should work now.***

The application is now ready to be used.

###After starting the application###
When the user has followed the steps described above he or she will encounter the frontpage which contains four buttons: ```sign out```, ```login```,  ```register``` and ```symptoms```. The first tree speak for the self. Registering and logging in is possible but does not add real functionality yet(see "important" notes below for more information). The symptom button will lead the user to the old ```diseasefinder``` application screen (which eventually will be integrated in the new layout), this is the real functional application. The user can enter disease symptoms in the search bar (like cough, pain, headache etc) and press the search button next to the bar. If the user gets results they will be added in the ````results``` tab. The results there are click able and result in another tab with extra information about the disease.  

***Important note: Because this application is an early version, there are a few parts that do not work like the user would expect. The main examples of this are the following ones: The registration and login sessions expire after the application is killed. So when the user restarts the application his registration details will be cleared from the system and he or she has to register again. The reason for this is the database used here is an public one. Also are the search bar and search button not functional  Another anomaly occurs when the user is searching for symptoms. Because the search bar is temporary it is not completely fixed, it is necessary to separate the symptoms by comma, when used otherwise the application will not work. Beside the main flaws described above the user will probably occur some format skews and anomalies in the layout of the application.***
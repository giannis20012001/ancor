# ancor

> Ancor's task is to process CVS files and persists them via REST. 
It is made using Java 8 & Spring Framework technology.

## Running ancor

To Run the program you must only set the following 4 (four) environment variables:

    $ export PATH_4_UNPROCESSED_FILES=.... 
    $ export PATH_4_PROCESSED_FILES=....
    $ export CSV_FILE_DELIMITER=....
    $ export FILE_PATTERN=....
    $ export FIXEDRATE_IN_MILLISECONDS=...
    

All variable names are self explanatory and the ENVs must be set before running the application.

To Download the code you perform a git clone:


    $ git clone https://github.com/giannis20012001/ancor.git
    
Finally to run application you enter the newly created file (after performing git clone) and:

* You can either do:


    $ mvn clean install
    $ java -jar ancor-app-1.0.0-beta.jar

* Or you can do:


    $ mvn spring-boot:run
   
That directly builds and executes the program.
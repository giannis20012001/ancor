package org.lumi.ancor.app.filereceiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Stopwatch;
import com.google.common.io.Files;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.opencsv.CSVReader;
import org.lumi.ancor.app.model.Port;
import org.lumi.ancor.app.model.ProcessedFiles;
import org.lumi.ancor.app.model.Vessel;
import org.lumi.ancor.app.model.VesselPosition;
import org.lumi.ancor.app.model.VesselPositionBadRows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 29/12/2017.
 */

@Component
public class CsvFileReader {
    //@Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}", initialDelayString = "${initialDelay.in.milliseconds}")
    public void parseCSVFiles() {
        LOGGER.info("========================================================");
        LOGGER.info("System ENVs to be used: ");
        LOGGER.info("PATH_4_UNPROCESSED_FILES: " + PATH_4_UNPROCESSED_FILES);
        LOGGER.info("PATH_4_PROCESSED_FILES: " + PATH_4_PROCESSED_FILES);
        LOGGER.info("CSV_FILE_DELIMITER: " + CSV_FILE_DELIMITER);
        LOGGER.info("FILE_PATTERN: " + FILE_PATTERN);
        LOGGER.info("FIXEDELAY_IN_MILLISECONDS: " + FIXEDELAY_IN_MILLISECONDS);
        LOGGER.info("INITIALDELAY_IN_MILLISECONDS: " + INITIALDELAY_IN_MILLISECONDS);
        LOGGER.info("Getting file name list.....");
        String[] fileNames = getListOfFileNames(PATH_4_UNPROCESSED_FILES);

        processedFiles.setStopwatch(Stopwatch.createStarted());//Set start time
        if (fileNames.length > 0) {
            LOGGER.info("File names to be loaded: " + fileNames.toString());
            LOGGER.info("Executing CSV parsing.....");
            parseCSVFileLineByLine(fileNames);

        }else {
            LOGGER.info("No CSV files where found during this pooling round!.....");

        }

        processedFiles.getStopwatch().stop();//Set stop time
        processedFiles.setElapsedTimeInMilliSeconds(processedFiles.getStopwatch().elapsed(MILLISECONDS));//Set final elapsed time
        LOGGER.info("CSV parsing finished successfully!.....");
        LOGGER.info("Total records processed: " + processedFiles.getRecordNumProcessed());
        LOGGER.info("========================================================");
        LOGGER.info("");

    }

    private void parseCSVFileLineByLine(String[] fileNames) {
        //create CSVReader object
        CSVReader reader;

        for (String fileName : fileNames) {
            int localRecordsProcessedPosition = 1;
            LOGGER.info("parsing CSV: " + fileName);
            processedFiles.addProcessedFiles(fileName);//Add fileName to processed list

            try {
                reader = new CSVReader(new FileReader(PATH_4_UNPROCESSED_FILES + File.separator + fileName),
                        CSV_FILE_DELIMITER);
                //Read line by line
                String[] record;
                //Skip header row
                reader.readNext();

                while((record = reader.readNext()) != null){
                    localRecordsProcessedPosition++; //Add local record num
                    if ((record[0] != null && !record[0].isEmpty() && !record[0].contains(",")) &&
                            (record[1] != null && !record[1].isEmpty() && !record[13].contains(",")) &&
                            ((record[2] != null && !record[2].isEmpty()) && (stringToObject(record[2]) instanceof Double)) &&
                            ((record[3] != null && !record[3].isEmpty()) && (stringToObject(record[3]) instanceof Double)) &&
                            ((record[4] != null && !record[4].isEmpty()) && (stringToObject(record[4]) instanceof Date)) &&
                            ((record[5] != null && !record[5].isEmpty()) && (stringToObject(record[5]) instanceof Integer)) &&
                            ((record[6] != null && !record[6].isEmpty()) && (stringToObject(record[6]) instanceof Double)) &&
                            ((record[8] != null && !record[8].isEmpty()) && (stringToObject(record[8]) instanceof Double)) &&
                            ((record[9] != null && !record[9].isEmpty()) && (stringToObject(record[9]) instanceof Integer)) &&
                            ((record[10] != null && !record[10].isEmpty()) && (stringToObject(record[10]) instanceof Integer)) &&
                            (record[11] != null && !record[11].isEmpty() && !record[11].contains(",")) &&
                            (record[12] != null && !record[12].isEmpty() && !record[12].contains(",")) &&
                            (record[13] != null && !record[13].isEmpty() && !record[13].contains(",")) &&
                            (record[14] != null && !record[14].isEmpty() && !record[14].contains(","))) {
                        strictlyValidatedColumns(record);

                    }else {
                        nonStrictlyValidatedColumns(record, fileName, localRecordsProcessedPosition);

                    }

                    LOGGER.info("Finished local record in position: " + localRecordsProcessedPosition);
                    LOGGER.info("========================================================");
                    LOGGER.info("");

                }

                reader.close();

            } catch (IOException e) {
                e.printStackTrace();

            }

            //Add to total records processed
            processedFiles.setRecordNumProcessed(processedFiles.getRecordNumProcessed()
                    + (localRecordsProcessedPosition - 1));

            //After successful processing move finished file to  PATH_4_PROCESSED_FILES directory
            File fileToMove = new File(PATH_4_UNPROCESSED_FILES + File.separator + fileName);
            File destDir = new File(PATH_4_PROCESSED_FILES + File.separator);
            File targetFile = new File(destDir, "PROCESSED_" + fileToMove.getName());

            try {
                Files.move(fileToMove, targetFile);

            } catch (IOException e) {
                e.printStackTrace();

            }

        }

    }

    private void strictlyValidatedColumns(String[] record){
        Port port = new Port();
        Vessel vessel = new Vessel();
        VesselPosition vesselPosition = new VesselPosition();

        //Set Port Object
        port.setFromPort(record[11]);
        port.setFromCountry(record[12]);
        //Set Vessel Object
        vessel.setImo(record[0]);
        vessel.setVesselName(record[1]);
        vessel.setGrossTonnage((Integer) stringToObject(record[5]));
        //Set VesselPosition Object
        vesselPosition.setLatitude((Double) stringToObject(record[2]));
        vesselPosition.setLongitude((Double) stringToObject(record[3]));
        vesselPosition.setPositionReceivedTimestamp((Date) stringToObject(record[4]));
        vesselPosition.setSpeed((Double) stringToObject(record[6]));
        vesselPosition.setDraught((Double) stringToObject(record[8]));
        vesselPosition.setYearBuilt((Integer) stringToObject(record[9]));
        vesselPosition.setDirectionDegrees((Integer) stringToObject(record[10]));
        vesselPosition.setDestinationPort(record[13]);
        vesselPosition.setDestinationCountry(record[14]);
        vesselPosition.setVessel(vessel);
        vesselPosition.setPort(port);
        //POSTing of objects to REST server
        //postPort2RESTServer(port);
        //postVessel2RESTServer(vessel);
        postVesselPosition2RESTServer(vesselPosition);

    }

    private void nonStrictlyValidatedColumns(String[] record, String fileName, int localRecordsProcessedPosition) {
        VesselPositionBadRows vesselPositionBadRows = new VesselPositionBadRows();
        if ((record[7] != null && !record[7].isEmpty()) && (stringToObject(record[7]) instanceof Integer)) {
            vesselPositionBadRows.setCourse((Integer) stringToObject(record[7]));

        }else {
            vesselPositionBadRows.setCourse(null);

        }

        if ((record[15] != null && !record[15].isEmpty()) && (stringToObject(record[15]) instanceof Integer)) {
            vesselPositionBadRows.setWind((Integer) stringToObject(record[15]));

        }else{
            vesselPositionBadRows.setWind(null);

        }

        if ((record[16] != null && !record[16].isEmpty()) && (stringToObject(record[16]) instanceof Integer)) {
            vesselPositionBadRows.setTemperature((Integer) stringToObject(record[16]));

        }else {
            vesselPositionBadRows.setTemperature(null);

        }

        if (record[17] != null && !record[17].isEmpty()) {
            vesselPositionBadRows.setWindDirection(record[17]);

        }else {
            vesselPositionBadRows.setWindDirection(null);

        }

        //Set local record number for reference
        vesselPositionBadRows.setRecordNumInFile(localRecordsProcessedPosition);
        //Set file name that bad row originated
        vesselPositionBadRows.setFileNameOrigination(fileName);
        //POSTing of objects to REST server
        postVesselPositionBadRows2RESTServer(vesselPositionBadRows);

    }

    private Object stringToObject (String str) {
        if (TIMESTAMP_PATTERN_1.matcher (str).matches () || TIMESTAMP_PATTERN_2.matcher (str).matches ()) {
            return stringToDate (str);

        } else if (DOUBLE_PATTERN_STRICT.matcher (str).matches ()){
            return Double.valueOf (str);

        } else if (INTEGER_PATTERN.matcher (str).matches ()) {
            return Integer.valueOf (str);

        } else {
            return str;

        }

    }

    private Date stringToDate(String time) {
        Date parsedDate = null;
        try {
            if (time.contains("(UTC)")) {
                time = time.replace("(UTC)", "");

            }else if (time.contains("UTC")) {
                time = time.replace("UTC", "");

            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            parsedDate = dateFormat.parse(time);
            /*Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            LOGGER.info(String.valueOf(timestamp));*/

        } catch(Exception e) {
            LOGGER.severe(e.getMessage());

        }

        return parsedDate;

    }

    /**
     * Returns a string array that is a list of all
     * filenames that match the desired filename pattern
     */
    private String[] getListOfFileNames(String aDirectory) {
        //The filename filter (filename pattern matcher)
        class PatternFilter implements FilenameFilter {
            @Override
            public boolean accept(File dir, String str) {
                if (str.endsWith(FILE_PATTERN)) {
                    return true;

                }

                return false;

            }

        }

        return new java.io.File(aDirectory).list(new PatternFilter());

    }

    /**
     * Returns a File array that is a list of all
     * filenames that match the desired filename pattern
     */
    private File [] getListOfPathNames(String aDirectory) {
        File dir = new File(aDirectory);
        File [] files = dir.listFiles((dirct, name) -> name.endsWith(FILE_PATTERN));

        return files;

    }

    private void postPort2RESTServer(Port port) {
        try {
            HttpResponse<String> httpResponse = Unirest.post(REST_SERVER + "/port")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(port)
                    .asString();

            if (httpResponse.getStatus() == 201) {
                LOGGER.info("REST operation to add Port succeeded!.....");

            }else if (httpResponse.getStatus() == 409) {
                LOGGER.info("Port already exists!.....");

            }

        } catch (UnirestException e) {
            LOGGER.severe(e.getMessage());

        }

    }

    private void postVessel2RESTServer(Vessel vessel) {
        try {
            HttpResponse<String> httpResponse = Unirest.post(REST_SERVER + "/vessel")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(vessel)
                    .asString();

            if (httpResponse.getStatus() == 201) {
                LOGGER.info("REST operation to add Vessel succeeded!.....");

            }else if (httpResponse.getStatus() == 409) {
                LOGGER.info("Vessel already exists!.....");

            }

        } catch (UnirestException e) {
            LOGGER.severe(e.getMessage());

        }

    }

    private void postVesselPosition2RESTServer(VesselPosition vesselPosition) {
        try {
            HttpResponse<String> httpResponse = Unirest.post(REST_SERVER + "/vessel_position")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(vesselPosition)
                    .asString();

            if (httpResponse.getStatus() == 201) {
                LOGGER.info("REST operation to add VesselPosition succeeded!.....");

            }else if (httpResponse.getStatus() == 409) {
                LOGGER.info("VesselPosition already exists!.....");

            }

        } catch (UnirestException e) {
            LOGGER.severe(e.getMessage());

        }

    }

    private void postVesselPositionBadRows2RESTServer(VesselPositionBadRows vesselPositionBadRows) {
        try {
            HttpResponse<String> httpResponse = Unirest.post(REST_SERVER + "/vessel_position_bad_rows")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(vesselPositionBadRows)
                    .asString();

            if (httpResponse.getStatus() == 201) {
                LOGGER.info("REST operation to add VesselPositionBadRows succeeded!.....");

            }else if (httpResponse.getStatus() == 409) {
                LOGGER.info("VesselPositionBadRows already exists!.....");

            }

        } catch (UnirestException e) {
            LOGGER.severe(e.getMessage());

        }

    }

    //=================================================================================================================
    //Constructors
    //=================================================================================================================
    /**
     * Constructor with serialization strategy of Java object into JSON
     */
    public CsvFileReader() {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper =
                    new com.fasterxml.jackson.databind.ObjectMapper();

            @Override
            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);

                } catch (IOException e) {
                    throw new RuntimeException(e);

                }

            }

            @Override
            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);

                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);

                }

            }

        });

    }

    //=================================================================================================================
    //Regular expression Patterns
    //=================================================================================================================
    private final static Pattern INTEGER_PATTERN = Pattern.compile ("^([+\\-])?\\d+$");
    private final static Pattern DOUBLE_PATTERN_STRICT = Pattern.compile ("[\\+\\-]?\\d+\\.\\d+(?:[eE][\\+\\-]?\\d+)?");
    //private final static Pattern DOUBLE_PATTERN_NON_STRICT = Pattern.compile ("^[-+]?\\d+(\\.\\d+)?$");
    private final static Pattern TIMESTAMP_PATTERN_1 = Pattern.compile ("[0-9]{4}-(0[1-9]|1[0-2])-" +
            "(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9] UTC");
    private final static Pattern TIMESTAMP_PATTERN_2 = Pattern.compile ("[0-9]{4}-(0[1-9]|1[0-2])-" +
            "(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9] \\(UTC\\)");

    //=================================================================================================================
    //Class variables
    //=================================================================================================================
    @Autowired
    private ProcessedFiles processedFiles;
    //Logger
    private static final Logger LOGGER = Logger.getLogger(CsvFileReader.class.getName());

    //Static variables
    private static final String PATH_4_UNPROCESSED_FILES = System.getenv("PATH_4_UNPROCESSED_FILES");
    private static final String PATH_4_PROCESSED_FILES = System.getenv("PATH_4_PROCESSED_FILES");
    private static final char CSV_FILE_DELIMITER = System.getenv("CSV_FILE_DELIMITER").charAt(0);
    private static final String FILE_PATTERN = System.getenv("FILE_PATTERN");
    private static final String REST_SERVER = "http://localhost:8080/api/v1";
    //Pooling variables in milliseconds
    public static final String FIXEDELAY_IN_MILLISECONDS = System.getenv("FIXEDELAY_IN_MILLISECONDS");
    public static final String INITIALDELAY_IN_MILLISECONDS = System.getenv("INITIALDELAY_IN_MILLISECONDS");

}

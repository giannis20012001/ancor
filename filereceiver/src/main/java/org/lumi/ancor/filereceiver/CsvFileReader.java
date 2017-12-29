package org.lumi.ancor.filereceiver;

import com.opencsv.CSVReader;
import org.lumi.ancor.app.model.Port;
import org.lumi.ancor.app.model.Vessel;
import org.lumi.ancor.app.model.VesselPosition;
import org.lumi.ancor.app.model.VesselPositionBadRows;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 29/12/2017.
 */

@Configuration
public class CsvFileReader {
    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void parseCSVFiles() {
        String[] fileNames = getListOfJSPTemplatesToParse(PATH_4_UNPROCESSED_FILES);
        parseCSVFileLineByLine(fileNames);
        //TODO: POST newly created entities
        //TODO: After successful processing move finished files to  PATH_4_PROCESSED_FILES directory

    }

    private void parseCSVFileLineByLine(String[] fileNames) {
        //create CSVReader object
        CSVReader reader;

        try {
            for (String fileName : fileNames) {
                reader = new CSVReader(new FileReader(PATH_4_UNPROCESSED_FILES + File.separator + fileName),
                        CSV_FILE_DELIMITER);

                //Read line by line
                String[] record;
                //Skip header row
                reader.readNext();

                while((record = reader.readNext()) != null){
                    if ((record[0] != null || !record[0].isEmpty()) ||
                            (record[1] != null || !record[1].isEmpty()) ||
                            (record[2] != null || !record[2].isEmpty()) ||
                            (record[3] != null || !record[3].isEmpty()) ||
                            (record[4] != null || !record[4].isEmpty()) ||
                            (record[5] != null || !record[5].isEmpty()) ||
                            (record[6] != null || !record[6].isEmpty()) ||
                            (record[8] != null || !record[8].isEmpty()) ||
                            (record[9] != null || !record[9].isEmpty()) ||
                            (record[10] != null || !record[10].isEmpty()) ||
                            (record[11] != null || !record[11].isEmpty()) ||
                            (record[12] != null || !record[12].isEmpty()) ||
                            (record[13] != null || !record[13].isEmpty())) {
                        //Execute strictlyValidatedColumns

                    }else {
                        //Execute nonStrictlyValidatedColumns

                    }


                }

                reader.close();

            }

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());

        }

        LOGGER.info("");

    }

    /*private void parseCSVToBeanList() throws IOException {
        HeaderColumnNameTranslateMappingStrategy<Employee> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<Employee>();
        beanStrategy.setType(Employee.class);

        Map<String, String> columnMapping = new HashMap<String, String>();
        columnMapping.put("ID", "id");
        columnMapping.put("Name", "name");
        columnMapping.put("Role", "role");
        columnMapping.put("Salary", "salary");

        beanStrategy.setColumnMapping(columnMapping);

        CsvToBean<Employee> csvToBean = new CsvToBean<Employee>();
        CSVReader reader = new CSVReader(new FileReader("/home/lumi/IdeaProjects/HttpsTest/employees.csv"));
        List<Employee> emps = csvToBean.parse(beanStrategy, reader);
        System.out.println(emps);

    }*/

    private Object stringToObject (String str) {
        if (TIMESTAMP_PATTERN.matcher (str).matches ())
            return stringToDate (str);

        else if (DOUBLE_PATTERN.matcher (str).matches ())
            return Double.valueOf (str);

        else if (INTEGER_PATTERN.matcher (str).matches ())
            return Integer.valueOf (str);

        else return str;

    }

    private String stringToDate(String str) {
        return null;

    }

    /**
     * Returns a string array that is a list of all
     * filenames that match the desired filename pattern
     */
    private String[] getListOfJSPTemplatesToParse(String aDirectory) {
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

        String[] yolo = new java.io.File(aDirectory).list(new PatternFilter());

        return new java.io.File(aDirectory).list(new PatternFilter());

    }

    /*private File [] getListOfJSPTemplatesToParse(String aDirectory) {
        File dir = new File(aDirectory);
        File [] files = dir.listFiles((dirct, name) -> name.endsWith(FILE_PATTERN));

        return files;

    }*/

    //=================================================================================================================
    //Regular expression Patterns
    //=================================================================================================================
    private final static Pattern INTEGER_PATTERN = Pattern.compile ("^([+\\-])?\\d+$");
    private final static Pattern DOUBLE_PATTERN = Pattern.compile ("[\\+\\-]?\\d+\\.\\d+(?:[eE][\\+\\-]?\\d+)?");
    private final static Pattern TIMESTAMP_PATTERN = Pattern.compile ("[0-9]{4}-(0[1-9]|1[0-2])-" +
            "(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9] UTC");

    //=================================================================================================================
    //Class variables
    //=================================================================================================================
    private Port port;
    private Vessel vessel;
    private VesselPosition vesselPosition;
    private VesselPositionBadRows vesselPositionBadRows;
    //Logger
    private static final Logger LOGGER = Logger.getLogger(CsvFileReader.class.getName());

    //Static variables
    public static final String PATH_4_UNPROCESSED_FILES = System.getenv("PATH_4_UNPROCESSED_FILES");
    public static final String PATH_4_PROCESSED_FILES = System.getenv("PATH_4_PROCESSED_FILES");
    public static final char CSV_FILE_DELIMITER = System.getenv("CSV_FILE_DELIMITER").charAt(0);
    public static final String FILE_PATTERN = System.getenv("FILE_PATTERN");
    public static final String FIXEDRATE_IN_MILLISECONDS = System.getenv("POLLIN_INTERVAL");

}

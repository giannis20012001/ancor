package org.lumi.ancor.app.model;

import com.google.common.base.Stopwatch;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 31/12/2017.
 */

@Component
public class ProcessedFiles {
    @Override
    public String toString() {
        return "ProcessedFiles{" +
                "stopwatch=" + stopwatch +
                ", elapsedTimeInMilliSeconds=" + elapsedTimeInMilliSeconds +
                ", recordNumProcessed=" + recordNumProcessed +
                ", processedFiles=" + processedFiles +
                '}';

    }

    //=================================================================================================================
    //Setters & Getters
    //=================================================================================================================
    public Stopwatch getStopwatch() {
        return stopwatch;

    }

    public void setStopwatch(Stopwatch stopwatch) {
        this.stopwatch = stopwatch;

    }

    public int getRecordNumProcessed() {
        return recordNumProcessed;

    }

    public void setRecordNumProcessed(int recordNumProcessed) {
        this.recordNumProcessed = recordNumProcessed;

    }

    public List<String> getProcessedFiles() {
        return processedFiles;

    }

    public void setProcessedFiles(List<String> processedFiles) {
        this.processedFiles = processedFiles;

    }

    public void addProcessedFiles(String processedFile) {
        this.processedFiles.add(processedFile);

    }

    public long getElapsedTimeInMilliSeconds() {
        return elapsedTimeInMilliSeconds;

    }

    public void setElapsedTimeInMilliSeconds(long elapsedTimeInMilliSeconds) {
        this.elapsedTimeInMilliSeconds = elapsedTimeInMilliSeconds;

    }

    //=================================================================================================================
    //Constructors
    //=================================================================================================================
    /**
     * Default constructor
     */
    public ProcessedFiles() {
        recordNumProcessed = 0;
        processedFiles = new ArrayList<>();

    }

    /**
     * Parameterized constructor
     */
    public ProcessedFiles(Stopwatch stopwatch, long elapsedTimeInMilliSeconds, int recordNumProcessed,
                          List<String> processedFiles) {
        this.stopwatch = stopwatch;
        this.elapsedTimeInMilliSeconds = elapsedTimeInMilliSeconds;
        this.recordNumProcessed = recordNumProcessed;
        this.processedFiles = processedFiles;

    }

    //=================================================================================================================
    //Class variables
    //=================================================================================================================
    private Stopwatch stopwatch;
    private long elapsedTimeInMilliSeconds;
    private int recordNumProcessed;
    private List<String> processedFiles;

}

package org.lumi.ancor.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 29/12/2017.
 */

@Entity
@Table(name="vessel_position_bad_rows")
public class VesselPositionBadRows implements Serializable {
    @Override
    public String toString() {
        return "VesselPositionBadRows{" +
                "id=" + id +
                ", course=" + course +
                ", wind=" + wind +
                ", temperature=" + temperature +
                ", windDirection='" + windDirection + '\'' +
                ", fileNameOrigination='" + fileNameOrigination + '\'' +
                ", recordNumInFile=" + recordNumInFile +
                '}';

    }

    //==================================================================================================================
    //Setters & Getters
    //==================================================================================================================
    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;

    }

    public Integer getCourse() {
        return course;

    }

    public void setCourse(Integer course) {
        this.course = course;

    }

    public Integer getWind() {
        return wind;

    }

    public void setWind(Integer wind) {
        this.wind = wind;

    }

    public Integer getTemperature() {
        return temperature;

    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;

    }

    public String getWindDirection() {
        return windDirection;

    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;

    }

    public String getFileNameOrigination() {
        return fileNameOrigination;

    }

    public void setFileNameOrigination(String fileNameOrigination) {
        this.fileNameOrigination = fileNameOrigination;

    }

    public Integer getRecordNumInFile() {
        return recordNumInFile;

    }

    public void setRecordNumInFile(Integer recordNumInFile) {
        this.recordNumInFile = recordNumInFile;

    }

    //==================================================================================================================
    //Constructors
    //==================================================================================================================
    /**
     * Default constructor
     */
    public VesselPositionBadRows() {
        //Do nothing

    }

    /**
     * Parameterized constructor
     */
    public VesselPositionBadRows(Integer course, Integer wind, Integer temperature, String windDirection,
                                 String fileNameOrigination, Integer recordNumInFile) {
        this.course = course;
        this.wind = wind;
        this.temperature = temperature;
        this.windDirection = windDirection;
        this.fileNameOrigination = fileNameOrigination;
        this.recordNumInFile = recordNumInFile;

    }

    //==================================================================================================================
    //Entity variables
    //==================================================================================================================
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    //=================================
    @Column(name="course")
    private Integer course;
    @Column(name="wind")
    private Integer wind;
    @Column(name="temperature")
    private Integer temperature;
    @Size(max = 200)
    @Column(name="wind_direction")
    private String windDirection;
    @NotNull
    @Size(max = 200)
    @Column(name="file_mame_origination")
    private String fileNameOrigination;
    @NotNull
    @Column(name="record_num_in_file")
    private Integer recordNumInFile;
    //=================================
    //serialization related var
    private static final long serialVersionUID = 1L;

}

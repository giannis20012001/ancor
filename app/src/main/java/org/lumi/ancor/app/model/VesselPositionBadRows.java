package org.lumi.ancor.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 29/12/2017.
 */

@Entity
@Table(name="vessel_position_bad_rows")
public class VesselPositionBadRows {
    @Override
    public String toString() {
        return "VesselPositionBadRows{" +
                "id=" + id +
                ", course=" + course +
                ", wind=" + wind +
                ", temperature=" + temperature +
                ", windDirection='" + windDirection + '\'' +
                '}';

    }

    //=================================================================================================================
    //Setters & Getters
    //=================================================================================================================
    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;

    }

    public int getCourse() {
        return course;

    }

    public void setCourse(int course) {
        this.course = course;

    }

    public int getWind() {
        return wind;

    }

    public void setWind(int wind) {
        this.wind = wind;

    }

    public int getTemperature() {
        return temperature;

    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;

    }

    public String getWindDirection() {
        return windDirection;

    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;

    }

    //=================================================================================================================
    //Constructors
    //=================================================================================================================
    /**
     * Default constructor
     */
    public VesselPositionBadRows() {
        //Do nothing

    }

    /**
     * Parameterized constructor
     */
    public VesselPositionBadRows(int course, int wind, int temperature, String windDirection) {
        this.course = course;
        this.wind = wind;
        this.temperature = temperature;
        this.windDirection = windDirection;

    }

    //=================================================================================================================
    //Entity variables
    //=================================================================================================================
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    //=================================
    @Column(name="course")
    private int course;
    @Column(name="wind")
    private int wind;
    @Column(name="temperature")
    private int temperature;
    @Size(max = 200)
    @Column(name="wind_direction")
    private String windDirection;
    //=================================
    //serialization related var
    private static final long serialVersionUID = 1L;

}

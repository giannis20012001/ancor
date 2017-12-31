package org.lumi.ancor.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 27/12/2017.
 */

@Entity
@Table(name="vessel_position")
public class VesselPosition implements Serializable {
    @Override
    public String toString() {
        return "VesselPosition{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", positionReceivedTimestamp=" + positionReceivedTimestamp +
                ", speed=" + speed +
                ", draught=" + draught +
                ", yearBuilt=" + yearBuilt +
                ", directionDegrees=" + directionDegrees +
                ", destinationPort='" + destinationPort + '\'' +
                ", destinationCountry='" + destinationCountry + '\'' +
                ", vessel=" + vessel +
                ", port=" + port +
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

    public Double getLatitude() {
        return latitude;

    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;

    }

    public Double getLongitude() {
        return longitude;

    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;

    }

    public Date getPositionReceivedTimestamp() {
        return positionReceivedTimestamp;

    }

    public void setPositionReceivedTimestamp(Date positionReceivedTimestamp) {
        this.positionReceivedTimestamp = positionReceivedTimestamp;

    }

    public Double getSpeed() {
        return speed;

    }

    public void setSpeed(Double speed) {
        this.speed = speed;

    }

    public Double getDraught() {
        return draught;

    }

    public void setDraught(Double draught) {
        this.draught = draught;

    }

    public Integer getYearBuilt() {
        return yearBuilt;

    }

    public void setYearBuilt(Integer yearBuilt) {
        this.yearBuilt = yearBuilt;

    }

    public Integer getDirectionDegrees() {
        return directionDegrees;

    }

    public void setDirectionDegrees(Integer directionDegrees) {
        this.directionDegrees = directionDegrees;

    }

    public String getDestinationPort() {
        return destinationPort;

    }

    public void setDestinationPort(String destinationPort) {
        this.destinationPort = destinationPort;

    }

    public String getDestinationCountry() {
        return destinationCountry;

    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;

    }

    public Vessel getVessel() {
        return vessel;

    }

    public void setVessel(Vessel vessel) {
        this.vessel = vessel;

    }

    public Port getPort() {
        return port;

    }

    public void setPort(Port port) {
        this.port = port;

    }

    //=================================================================================================================
    //Constructors
    //=================================================================================================================
    /**
     * Default constructor
     */
    public VesselPosition() {
        //Do nothing

    }

    /**
     * Parameterized constructor
     */
    public VesselPosition(Double latitude, Double longitude, Date positionReceivedTimestamp, Double speed,
                          Double draught, Integer yearBuilt, Integer directionDegrees, String destinationPort,
                          String destinationCountry, Vessel vessel, Port port) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.positionReceivedTimestamp = positionReceivedTimestamp;
        this.speed = speed;
        this.draught = draught;
        this.yearBuilt = yearBuilt;
        this.directionDegrees = directionDegrees;
        this.destinationPort = destinationPort;
        this.destinationCountry = destinationCountry;
        this.vessel = vessel;
        this.port = port;

    }

    //=================================================================================================================
    //Entity variables
    //=================================================================================================================
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    //=================================
    @NotNull
    @Column(name="latitude")
    private Double latitude;
    @NotNull
    @Column(name="longitude")
    private Double longitude;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="position_received_timestamp")
    private Date positionReceivedTimestamp = new Date();
    @NotNull
    @Column(name="speed")
    private Double speed;
    @NotNull
    @Column(name="draught")
    private Double draught;
    @NotNull
    @Column(name="year_built")
    private Integer yearBuilt;
    @NotNull
    @Column(name="direction_degrees")
    private Integer directionDegrees;
    @NotNull
    @Size(max = 200)
    @Column(name="destination_port")
    private String destinationPort;
    @NotNull
    @Size(max = 200)
    @Column(name="destination_country")
    private String destinationCountry;
    //=================================
    @JsonIgnore
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "vessel_id")
    private Vessel vessel;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name = "port_id")
    private Port port;
    //=================================
    //serialization related var
    private static final long serialVersionUID = 1L;

}

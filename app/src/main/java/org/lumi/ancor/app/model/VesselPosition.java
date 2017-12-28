package org.lumi.ancor.app.model;

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

    public void setId(Long Long) {
        this.id = id;

    }

    public double getLatitude() {
        return latitude;

    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;

    }

    public double getLongitude() {
        return longitude;

    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;

    }

    public Date getPositionReceivedTimestamp() {
        return positionReceivedTimestamp;

    }

    public void setPositionReceivedTimestamp(Date positionReceivedTimestamp) {
        this.positionReceivedTimestamp = positionReceivedTimestamp;

    }

    public double getSpeed() {
        return speed;

    }

    public void setSpeed(double speed) {
        this.speed = speed;

    }

    public double getDraught() {
        return draught;

    }

    public void setDraught(double draught) {
        this.draught = draught;

    }

    public int getYearBuilt() {
        return yearBuilt;

    }

    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;

    }

    public int getDirectionDegrees() {
        return directionDegrees;

    }

    public void setDirectionDegrees(int directionDegrees) {
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
    public VesselPosition(double latitude, double longitude, Date positionReceivedTimestamp, double speed,
                          double draught, int yearBuilt, int directionDegrees, String destinationPort,
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
    @NotNull
    @Column(name="latitude")
    private double latitude;
    @NotNull
    @Column(name="longitude")
    private double longitude;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="position_received_timestamp")
    private Date positionReceivedTimestamp = new Date();
    @NotNull
    @Column(name="speed")
    private double speed;
    @NotNull
    @Column(name="draught")
    private double draught;
    @NotNull
    @Column(name="year_built")
    private int yearBuilt;
    @NotNull
    @Column(name="direction_degrees")
    private int directionDegrees;
    @NotNull
    @Size(max = 200)
    @Column(name="destination_port")
    private String destinationPort;
    @NotNull
    @Size(max = 200)
    @Column(name="destination_country")
    private String destinationCountry;
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "vessel_id")
    private Vessel vessel;
    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name = "port_id")
    private Port port;
    //serialization related var
    private static final long serialVersionUID = 1L;

}

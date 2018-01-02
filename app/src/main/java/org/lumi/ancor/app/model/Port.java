package org.lumi.ancor.app.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 27/12/2017.
 */

@Entity
@Table(name="port")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Port implements Serializable {
    @Override
    public String toString() {
        return "Port{" +
                "id=" + id +
                ", fromPort='" + fromPort + '\'' +
                ", fromCountry='" + fromCountry + '\'' +
                ", vesselPositions=" + vesselPositions +
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

    public String getFromPort() {
        return fromPort;

    }

    public void setFromPort(String fromPort) {
        this.fromPort = fromPort;

    }

    public String getFromCountry() {
        return fromCountry;

    }

    public void setFromCountry(String fromCountry) {
        this.fromCountry = fromCountry;

    }

    public Set<VesselPosition> getVesselPositions() {
        return vesselPositions;

    }

    public void setVesselPositions(Set<VesselPosition> vesselPositions) {
        this.vesselPositions = vesselPositions;

    }

    //=================================================================================================================
    //Constructors
    //=================================================================================================================
    /**
     * Default constructor
     */
    public Port() {
        //Do nothing

    }

    /**
     * Parameterized constructor
     */
    public Port(String fromPort, String fromCountry, Set<VesselPosition> vesselPositions) {
        this.fromPort = fromPort;
        this.fromCountry = fromCountry;
        this.vesselPositions = vesselPositions;

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
    @Size(max = 200)
    @Column(name="from_port", unique = true)
    private String fromPort;
    @NotNull
    @Size(max = 200)
    @Column(name="from_country", unique = true)
    private String fromCountry;
    //@JsonBackReference
    @OneToMany(mappedBy="port", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<VesselPosition> vesselPositions = new HashSet<>();
    //serialization related var
    private static final long serialVersionUID = 1L;

}

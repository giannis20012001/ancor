package org.lumi.ancor.app.model;

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
import java.util.Set;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 27/12/2017.
 */

@Entity
@Table(name="vessel")
public class Vessel implements Serializable {
    @Override
    public String toString() {
        return "Vessel{" +
                "id=" + id +
                ", imo='" + imo + '\'' +
                ", vesselName='" + vesselName + '\'' +
                ", grossTonnage=" + grossTonnage +
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

    public String getImo() {
        return imo;

    }

    public void setImo(String imo) {
        this.imo = imo;

    }

    public String getVesselName() {
        return vesselName;

    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;

    }

    public int getGrossTonnage() {
        return grossTonnage;

    }

    public void setGrossTonnage(int grossTonnage) {
        this.grossTonnage = grossTonnage;

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
    public Vessel() {
        //Do nothing

    }

    /**
     * Parameterized constructor
     */
    public Vessel(String imo, String vesselName, int grossTonnage, Set<VesselPosition> vesselPositions) {
        this.imo = imo;
        this.vesselName = vesselName;
        this.grossTonnage = grossTonnage;
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
    @Column(name="imo", unique = true)
    private String imo;
    @NotNull
    @Size(max = 200)
    @Column(name="vessel_name", unique = true)
    private String vesselName;
    @NotNull
    @Column(name="gross_tonnage")
    private int grossTonnage;
    @OneToMany( mappedBy="vessel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<VesselPosition> vesselPositions;
    //=================================
    //serialization related var
    private static final long serialVersionUID = 1L;

}

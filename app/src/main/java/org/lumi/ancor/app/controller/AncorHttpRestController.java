package org.lumi.ancor.app.controller;

import org.lumi.ancor.app.model.Port;
import org.lumi.ancor.app.model.ProcessedFiles;
import org.lumi.ancor.app.model.Vessel;
import org.lumi.ancor.app.model.VesselPosition;
import org.lumi.ancor.app.model.VesselPositionBadRows;
import org.lumi.ancor.app.service.IPortService;
import org.lumi.ancor.app.service.IVesselPositionBadRowsService;
import org.lumi.ancor.app.service.IVesselPositionService;
import org.lumi.ancor.app.service.IVesselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 28/12/2017.
 */

@RestController
@RequestMapping("/api/v1")
public class AncorHttpRestController {
    //==================================================================================================================
    //READ All
    //==================================================================================================================
    @GetMapping("/ports")
    public ResponseEntity<List<Port>> getAllPorts() {
        List<Port> list = portService.getAllPorts();

        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @GetMapping("/vessels")
    public ResponseEntity<List<Vessel>> getAllVessels() {
        List<Vessel> list = vesselService.getAllVessels();

        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @GetMapping("/vessel_positions")
    public ResponseEntity<List<VesselPosition>> getAllVesselPositions() {
        List<VesselPosition> list = vesselPositionService.getAllVesselPositions();

        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @GetMapping("/vessel_position_bad_rows")
    public ResponseEntity<List<VesselPositionBadRows>> getAllVesselPositionBadRows() {
        List<VesselPositionBadRows> list = vesselPositionBadRowsService.getAllVesselPositionBadRows();

        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    //==================================================================================================================
    //READ by ID
    //==================================================================================================================
    @GetMapping("/port/{id}")
    public ResponseEntity<Port> getPortById(@PathVariable("id") Long id) {
        Port port = portService.getPortById(id);

        return new ResponseEntity<>(port, HttpStatus.OK);

    }

    @GetMapping("/vessel/{id}")
    public ResponseEntity<Vessel> getVesselById(@PathVariable("id") Long id) {
        Vessel vessel = vesselService.getVesselById(id);

        return new ResponseEntity<>(vessel, HttpStatus.OK);

    }

    @GetMapping("/vessel_position/{id}")
    public ResponseEntity<VesselPosition> getVesselPositionById(@PathVariable("id") Long id) {
        VesselPosition vesselPosition = vesselPositionService.getVesselPositionById(id);

        return new ResponseEntity<>(vesselPosition, HttpStatus.OK);

    }

    @GetMapping("/vessel_position_bad_rows/{id}")
    public ResponseEntity<VesselPositionBadRows> getVesselPositionBadRowsById(@PathVariable("id") Long id) {
        VesselPositionBadRows vesselPositionBadRows = vesselPositionBadRowsService.getVesselPositionBadRowsById(id);

        return new ResponseEntity<>(vesselPositionBadRows, HttpStatus.OK);

    }

    //==================================================================================================================
    //EXTRA GET operations
    //==================================================================================================================
    @GetMapping("/processed_files")
    public ResponseEntity<ProcessedFiles> getProcessedFiles() {
        ProcessedFiles processedFiles = this.processedFiles;

        return new ResponseEntity<>(processedFiles, HttpStatus.OK);

    }

    @GetMapping("/specific_vessel_positions")
    public ResponseEntity<List<VesselPosition>> getSpecificVesselPositions(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(name = "fromDate") Date fromDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(name = "toDate", required = false) Date toDate) {
        List<VesselPosition> list;
        if (toDate != null) {
            list = vesselPositionService.getVesselPositionByDate(fromDate, toDate);
            return new ResponseEntity<>(list, HttpStatus.OK);

        }

        list = vesselPositionService.getVesselPositionByDate(fromDate);

        return new ResponseEntity<>(list, HttpStatus.OK);


    }

    //==================================================================================================================
    //CREATE
    //==================================================================================================================
    @PostMapping("/port")
    public ResponseEntity<Void> addPort(@RequestBody Port port, UriComponentsBuilder builder) {
        boolean flag = portService.addPort(port);
        if (!flag) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/port/{id}").buildAndExpand(port.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);

    }

    @PostMapping("/vessel")
    public ResponseEntity<Void> addVessel(@RequestBody Vessel vessel, UriComponentsBuilder builder) {
        boolean flag = vesselService.addVessel(vessel);
        if (!flag) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/vessel/{id}").buildAndExpand(vessel.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);

    }

    @PostMapping("/vessel_position")
    public ResponseEntity<Void> addVesselPosition(@RequestBody VesselPosition vesselPosition,
                                                  UriComponentsBuilder builder) {
        boolean flag = vesselPositionService.addVesselPosition(vesselPosition);
        if (!flag) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/vessel_position/{id}").buildAndExpand(vesselPosition.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);

    }

    @PostMapping("/vessel_position_bad_rows")
    public ResponseEntity<Void> addVesselPositionBadRows(@RequestBody VesselPositionBadRows vesselPositionBadRows,
                                                  UriComponentsBuilder builder) {
        boolean flag = vesselPositionBadRowsService.addVesselPositionBadRows(vesselPositionBadRows);
        if (!flag) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/vessel_position_bad_rows/{id}")
                .buildAndExpand(vesselPositionBadRows.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);

    }

    //==================================================================================================================
    //UPDATE
    //==================================================================================================================
    @PutMapping("/port")
    public ResponseEntity<Port> updatePort(@RequestBody Port port) {
        if(portService.getPortById(port.getId()) == null) {
            return ResponseEntity.notFound().build();

        }

        portService.updatePort(port);

        return new ResponseEntity<>(port, HttpStatus.OK);

    }

    @PutMapping("/vessel")
    public ResponseEntity<Vessel> updateVessel(@RequestBody Vessel vessel) {
        if(vesselService.getVesselById(vessel.getId()) == null) {
            return ResponseEntity.notFound().build();

        }

        vesselService.updateVessel(vessel);

        return new ResponseEntity<>(vessel, HttpStatus.OK);

    }

    @PutMapping("/vessel_position")
    public ResponseEntity<VesselPosition> updateVesselPosition(@RequestBody VesselPosition vesselPosition) {
        if(vesselPositionService.getVesselPositionById(vesselPosition.getId()) == null) {
            return ResponseEntity.notFound().build();

        }

        vesselPositionService.updateVesselPosition(vesselPosition);

        return new ResponseEntity<>(vesselPosition, HttpStatus.OK);

    }

    @PutMapping("/vessel_position_bad_rows")
    public ResponseEntity<VesselPositionBadRows> updateVesselPositionBadRows(
            @RequestBody VesselPositionBadRows vesselPositionBadRows) {
        if(vesselPositionBadRowsService.getVesselPositionBadRowsById(vesselPositionBadRows.getId()) == null) {
            return ResponseEntity.notFound().build();

        }

        vesselPositionBadRowsService.updateVesselPositionBadRows(vesselPositionBadRows);

        return new ResponseEntity<>(vesselPositionBadRows, HttpStatus.OK);

    }

    //==================================================================================================================
    //DELETE by ID
    //==================================================================================================================
    @DeleteMapping("/port/{id}")
    public ResponseEntity<Void> deletePort(@PathVariable("id") Long id) {
        if(portService.getPortById(id) == null) {
            return ResponseEntity.notFound().build();

        }

        portService.deletePort(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/vessel/{id}")
    public ResponseEntity<Void> deleteVessel(@PathVariable("id") Long id) {
        if(vesselService.getVesselById(id) == null) {
            return ResponseEntity.notFound().build();

        }

        vesselService.deleteVessel(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/vessel_position/{id}")
    public ResponseEntity<Void> deleteVesselPosition(@PathVariable("id") Long id) {
        if(vesselPositionService.getVesselPositionById(id) == null) {
            return ResponseEntity.notFound().build();

        }

        vesselPositionService.deleteVesselPosition(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/vessel_position_bad_rows/{id}")
    public ResponseEntity<Void> deleteVesselPositionBadRows(@PathVariable("id") Long id) {
        if(vesselPositionBadRowsService.getVesselPositionBadRowsById(id) == null) {
            return ResponseEntity.notFound().build();

        }

        vesselPositionBadRowsService.deleteVesselPositionBadRows(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    //==================================================================================================================
    //Autowired Controller service interface variables
    //==================================================================================================================
    @Autowired
    private IPortService portService;

    @Autowired
    private IVesselService vesselService;

    @Autowired
    private IVesselPositionService vesselPositionService;

    @Autowired
    private IVesselPositionBadRowsService vesselPositionBadRowsService;

    @Autowired
    private ProcessedFiles processedFiles;

    //Logger
    private static final Logger LOGGER = Logger.getLogger(AncorHttpRestController.class.getName());

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import edu.eci.arsw.blueprints.filtros.Filtro;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {
    
    @Autowired
    BlueprintsServices bpp;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> todosLosPlanos(){
        try {
            //obtener datos que se enviarán a través del API
            Set<Blueprint> bp = bpp.getAllBlueprints(); 
            return new ResponseEntity<>(bpp.multiFilter(bp),HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(ResourceNotFoundException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error",HttpStatus.NOT_FOUND);
        }        
    }
    
    @RequestMapping(value = "/{author}", method = RequestMethod.GET)
    public ResponseEntity<?> obtenerPorAutor(@PathVariable("author") String author){
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(bpp.getBlueprintsByAuthor(author),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(ResourceNotFoundException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("El autor "+author+" no se encontró",HttpStatus.NOT_FOUND);
        }        
    }
    
    @RequestMapping(value = "/{author}/{bpname}", method = RequestMethod.GET)
    public ResponseEntity<?> obtenerPorAutoryNombre(@PathVariable("author") String author, @PathVariable("bpname") String bpname ){
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(bpp.getBlueprint(author,bpname),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(ResourceNotFoundException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("El elemento no se encontró",HttpStatus.NOT_FOUND);
        }        
    }
}


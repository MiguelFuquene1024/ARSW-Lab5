/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.filtros.impl;

import edu.eci.arsw.blueprints.filtros.Filtro;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author Acer
 */
@Component("Submuestreo")
public class SubmuestreoPuntos implements Filtro{

    @Override
    public Blueprint filtroBlueprint(Blueprint blueprint) {
        List<Point> lista = blueprint.getPoints();
        List<Point> actualizada = new ArrayList<Point>();

        for (int i = 0; i < lista.size(); i++) {
            if (i % 2 == 0) {
                actualizada.add(lista.get(i));
            }
        }

        blueprint.actualizarPunto(actualizada);

        return blueprint;
    }

    @Override
    public Set<Blueprint> multiFiltroBluePrint(Set<Blueprint> blueprints) {
        for (Blueprint blueprint : blueprints) {
            filtroBlueprint(blueprint);
        }
        return blueprints;      
    }
    
}

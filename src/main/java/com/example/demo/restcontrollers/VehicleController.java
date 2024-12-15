package com.example.demo.restcontrollers;

import com.example.demo.Vehicle;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleServices vehicleServices;

    // Obtener todos los vehiculos
    @GetMapping
    public List<Vehicle> getAll() {
        return vehicleServices.getAll(); // Llamada al servicio para obtener todos los vehiculos
    }

    // Obtener un vehiculo por su id
    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        Optional<Vehicle> optional = vehicleServices.read(id);
        
        if (!optional.isPresent()) {
            String error = ("Vehicle no trobat amb la id " + id);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); // 404 Not Found
        }
        
        return ResponseEntity.ok(optional.get()); // Retorna el vehiculo encontrado
    }

    // Crear un nuevo vehiculo
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Vehicle vehicle, UriComponentsBuilder ucb) {
        Long codigo = null;
        
        try {
            codigo = vehicleServices.create(vehicle);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e.getMessage());
        }

        URI uri = ucb.path("/vehicles/{codigo}").buildAndExpand(codigo).toUri(); // Construir URI
        return ResponseEntity.created(uri).build(); // Retorna un 201 Created
    }

    // Eliminar un vehiculo por su id
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204 No Content
    public void delete(@PathVariable Long id) {
        try {
            vehicleServices.delete(id);
        } catch (IllegalStateException e) {
            throw new RuntimeException("No es troba el vehicle amb la id " + id);
        }
    }

    // Actualizar un vehiculo
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204 No Content
    public void update(@RequestBody Vehicle vehicle) {
        try {
        	vehicleServices.update(vehicle);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

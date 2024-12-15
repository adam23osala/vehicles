/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.restcontrollers;

import com.example.demo.Vehicle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import org.springframework.stereotype.Service;

/**
 *
 * @author oriol
 */
@Service
public class VehicleServices {

    private TreeMap<Long, Vehicle> vehicles = new TreeMap<>();
    private long id = 1;

    public VehicleServices() {
        vehicles.put(id, new Vehicle(1, "1234ABC", "Toyota"));
        vehicles.put(id++, new Vehicle(2, "5678DEF", "Honda"));
        vehicles.put(id++, new Vehicle(3, "4322JMD", "Seat"));
        vehicles.put(id++, new Vehicle(3, "9101GHI", "Ford"));
    }

    public List<Vehicle> getAll() {
        return new ArrayList<>(vehicles.values());
    }

    public Optional<Vehicle> read(Long id) {
        return Optional.ofNullable(vehicles.get(id));
    }

    public Long create(Vehicle vehicle) {
        if (vehicle.getMatricula() == null || vehicle.getMarca() == null) {
            throw new IllegalStateException("Matricula i marca requerit.");
        }

        for (Vehicle v : vehicles.values()) {
            if (v.getMatricula().equals(vehicle.getMatricula())) {
                throw new IllegalStateException("Vehicle amb matricula " + vehicle.getMatricula() + " ja existeix!");
            }
        }

        vehicle.setId(id++);
        vehicles.put(id, vehicle);
        return id;
    }

    public void update(Vehicle vehicle) {
        if (!vehicles.containsKey(vehicle.getId())) {
            throw new IllegalStateException("Vehicle no trobat amb la id: " + vehicle.getId());
        }
        vehicles.put(vehicle.getId(), vehicle);
    }

    public void delete(Long id) {
        if (!vehicles.containsKey(id)) {
            throw new IllegalStateException("Vehicle no trobat amb la id: " + id);
        }
        vehicles.remove(id);
    }
}

package edu.miu.cs489.cs489iotdevicemgmt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull
    @Column(nullable = false)
    public LocalDateTime dateTime;
    @NotNull
    @Column(nullable = false)
    public Double value;
    @ManyToOne
    public Device device;
}
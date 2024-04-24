package edu.miu.cs489.cs489iotdevicemgmt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotEmpty
    @Column(nullable = false, unique = true)
    public String username;

    @NotEmpty
    @Column(nullable = false)
    public String password;
}

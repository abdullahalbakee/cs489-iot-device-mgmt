package edu.miu.cs489.cs489iotdevicemgmt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank
    private String firstLine;
    @Column(nullable = false)
    @NotNull
    private String secondLine;
    @Column(nullable = false)
    @NotEmpty
    private String city;
    @Column(nullable = false)
    @NotEmpty
    private String state;
    @Column(nullable = false)
    @NotEmpty
    private String zip;
    @Column(nullable = false)
    @NotEmpty
    private String country;
}

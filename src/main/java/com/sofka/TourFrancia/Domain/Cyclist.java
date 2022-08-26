package com.sofka.TourFrancia.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cyclist")
public class Cyclist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name" , length = 100 , nullable = false)
    private String fullName;

    @Column(name = "competitor_number" , length = 5 , nullable = false , unique = true)
    private String competitorNumber;

    @ManyToOne(fetch = FetchType.LAZY , targetEntity = CyclingTeam.class)
    @JoinColumn(name = "id_cycling_Team")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CyclingTeam cyclingTeam;

    @ManyToOne(fetch = FetchType.LAZY , targetEntity = Country.class)
    @JoinColumn(name = "id_country")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Country country;



}

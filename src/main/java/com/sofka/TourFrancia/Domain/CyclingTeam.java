package com.sofka.TourFrancia.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teams")
public class CyclingTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name" , length = 30 , nullable = false)
    private String name;

    @Column(name = "team_code" , length = 3, nullable = false , unique = true)
    private String teamCode;

    @ManyToOne(fetch = FetchType.LAZY , targetEntity = Country.class)
    @JoinColumn(name = "country")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Country country;

    @OneToMany(mappedBy = "cyclingTeam", cascade = CascadeType.ALL)
    private List<Cyclist> Cyclist=new ArrayList<>();




}

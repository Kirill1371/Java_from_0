package ru.senla.javacourse.tarasov.hotel.db.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "\"guest\"")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

//    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
//    //@OneToMany(mappedBy = "guest", fetch = FetchType.EAGER)
//    private List<Stay> stays;
//
//    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Service> services;
    @OneToMany(mappedBy = "guest", fetch = FetchType.LAZY)
    private Set<Stay> stays;

    @OneToMany(mappedBy = "guest", fetch = FetchType.LAZY)
    private Set<Service> services;

}


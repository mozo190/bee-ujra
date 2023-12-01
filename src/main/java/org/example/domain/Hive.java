package org.example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hive")
@Data
@NoArgsConstructor
public class Hive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hive_capacity")
    private Integer hiveCapacity;

    @Column(name = "hive_name")
    private String hiveName;

    @OneToMany(mappedBy = "hive")
    private List<Bee> bees;
}

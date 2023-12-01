package org.example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bee")
@Data
@NoArgsConstructor
public class Bee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "bee_type")
    private BeeType beeType;

    @Column(name = "bee_name")
    private String beeName;

    @ManyToOne
    @JoinColumn(name = "hive_id")
    private Hive hive;
}

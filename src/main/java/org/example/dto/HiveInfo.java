package org.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HiveInfo {
    private Integer hiveCapacity;
    private String hiveName;
    private List<BeeInfo> bees;

    public HiveInfo(Integer hiveCapacity, String hiveName, List<BeeInfo> bees) {
        this.hiveCapacity = hiveCapacity;
        this.hiveName = hiveName;
        this.bees = bees;
    }
}

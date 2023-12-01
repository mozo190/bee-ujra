package org.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.BeeType;

@Data
@NoArgsConstructor
public class BeeInfo {
    private BeeType beeType;
    private String beeName;

    public BeeInfo(BeeType beeType, String beeName) {
        this.beeType = beeType;
        this.beeName = beeName;
    }
}

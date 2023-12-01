package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.BeeType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeeSaveCommand {
    @NotNull
    private BeeType beeType;

    @NotBlank(message = "Name cannot be blank")
    private String beeName;

    @NotNull
    private Integer hiveId;
}

package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HiveSaveCommand {
    @NotNull
    @Max(value = 10000, message = "Maximum capacity is 10000")
    private Integer hiveCapacity;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 20, message = "Hive name must be between 3 and 20 characters")
    private String hiveName;

}

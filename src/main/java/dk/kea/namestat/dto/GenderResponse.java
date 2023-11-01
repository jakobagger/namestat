package dk.kea.namestat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenderResponse {
    private int count;
    private String name;
    String gender;
    private double probability;

}

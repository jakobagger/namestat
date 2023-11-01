package dk.kea.namestat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NameStatsResponse {

            private String name;
            private String gender;
            private double genderProbability;
            private int age;
            private int ageCount;
            private String country;
            private double countryProbability;


            public NameStatsResponse(String name, AgeResponse ageResp, CountryResponse countryResp, GenderResponse genderResp){
                this.name = name;
                this.gender = genderResp.getGender();
                this.genderProbability = genderResp.getProbability()*10;
                this.age = ageResp.getAge();
                this.ageCount = ageResp.getCount();
                this.country = countryResp.getCountry();
                this.countryProbability = countryResp.getProbability()*10;
            }
}



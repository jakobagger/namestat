package dk.kea.namestat.dto;

import dk.kea.namestat.entity.Age;
import dk.kea.namestat.entity.Gender;
import dk.kea.namestat.entity.Nation;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class NameStatsResponse {

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            int id;
            private String name;
            private String gender;
            private double genderProbability;
            private int age;
            private int ageCount;
            private String country;
            private double countryProbability;


            public NameStatsResponse(Age age, Gender gender, Nation nation){
                this.gender = gender.getGender();
                this.genderProbability = gender.getProbability()*100;
                this.age = age.getAge();
                this.ageCount = age.getCount();
                this.country = nation.getCountry().get(0).getCountry_id();
                this.countryProbability = nation.getCountry().get(0).getProbability()*100;
            }
}



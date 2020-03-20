package at.spengergasse.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "country")
public class Country extends BaseEntity<Long> implements Comparable<Country> {

    private String name;
    private String capital;
    private int population;
    private double populationGrowth;

    @Override
    public int compareTo(Country o) {
        return Double.compare(Math.pow(getPopulation(), 1 + getPopulationGrowth()), Math.pow(o.getPopulation(), 1 + o.getPopulationGrowth()));
    }
}

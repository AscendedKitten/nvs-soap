package at.spengergasse.service;

import at.spengergasse.domain.Country;
import at.spengergasse.persistence.CountryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Transactional
public class CountryService {

    private final CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;

        Country spain = new Country();
        spain.setName("Spain");
        spain.setCapital("Madrid");
        spain.setPopulationGrowth(0.03);
        spain.setPopulation(46704314);


        Country poland = new Country();
        poland.setName("Poland");
        poland.setCapital("Warsaw");
        poland.setPopulationGrowth(-0.17);
        poland.setPopulation(38186860);


        Country uk = new Country();
        uk.setName("United Kingdom");
        uk.setCapital("London");
        uk.setPopulationGrowth(0.58);
        uk.setPopulation(63705000);

        createCountry(spain);
        createCountry(uk);
        createCountry(poland);
    }

    @Transactional()
    public Country createCountry(@NotNull @Valid Country country) {
        return repository.save(country);
    }

    public Country findByName(String name) {
        return repository.findByName(name).get();
    }


}

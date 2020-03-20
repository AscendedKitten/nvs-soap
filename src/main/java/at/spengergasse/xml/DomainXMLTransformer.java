package at.spengergasse.xml;

import at.spengergasse.domain.Country;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DomainXMLTransformer {
    public CountryXML fromDomainObject(Country country) {
        CountryXML countryXML = new CountryXML();
        countryXML.setCapital(country.getCapital());
        countryXML.setName(country.getName());
        countryXML.setPopulation(country.getPopulation());
        countryXML.setPopulationGrowth(BigDecimal.valueOf(country.getPopulationGrowth()));
        return countryXML;
    }
}

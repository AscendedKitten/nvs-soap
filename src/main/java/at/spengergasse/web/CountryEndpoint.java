package at.spengergasse.web;

import at.spengergasse.domain.Country;
import at.spengergasse.service.CountryService;
import at.spengergasse.xml.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Collections;
import java.util.List;

@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8080/countries";

    private CountryService countryService;
    private DomainXMLTransformer transformer = new DomainXMLTransformer();

    @Autowired
    public CountryEndpoint(CountryService countryService) {
        this.countryService = countryService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(transformer.fromDomainObject(countryService.findByName(request.getName())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryGrowthComparisonRequest")
    @ResponsePayload
    public GetCountryGrowthComparisonResponse getCountryGrowthComparison(@RequestPayload GetCountryGrowthComparisonRequest request) {

        GetCountryGrowthComparisonResponse response = new GetCountryGrowthComparisonResponse();

        Country winner = Collections.max(List.of(countryService.findByName(request.getNameCountry1()), countryService.findByName(request.getNameCountry2())), Country::compareTo);
        response.setWinner(transformer.fromDomainObject(winner));

        return response;
    }
}
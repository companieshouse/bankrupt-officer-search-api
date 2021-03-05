package uk.gov.companieshouse.bankruptofficersearch.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uk.gov.companieshouse.bankruptofficersearch.api.exception.ServiceException;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerDetails;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearch;
import uk.gov.companieshouse.bankruptofficersearch.api.model.rest.ScottishBankruptOfficerSearchResults;
import uk.gov.companieshouse.bankruptofficersearch.api.service.impl.ScottishBankruptOfficerSearchServiceImpl;

@Controller
public class ScottishBankruptOfficerController {

    @Autowired
    private ScottishBankruptOfficerSearchServiceImpl service;

    @PostMapping("/internal/officer-search/scottish-bankrupt-officers")
    public ResponseEntity<ScottishBankruptOfficerSearchResults> searchScottishBankruptOfficers(@RequestBody ScottishBankruptOfficerSearch search) {
        try {
            ScottishBankruptOfficerSearchResults results = service.searchScottishBankruptOfficers(search);
            if (results == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (ServiceException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/internal/officer-search/scottish-bankrupt-officers/{ephemeral_officer_key}")
    public ResponseEntity<ScottishBankruptOfficerDetails> getOfficerById(@PathVariable("ephemeral_officer_key") String officerId){
        try {
            ScottishBankruptOfficerDetails officer = service.getScottishBankruptOfficer(officerId);
            if (officer == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(officer);
        } catch (ServiceException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

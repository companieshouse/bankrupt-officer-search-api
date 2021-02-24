package uk.gov.companieshouse.bankruptofficersearch.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankruptOfficerSearchApiApplication {

	public static final String APPLICATION_NAME_SPACE = "bankrupt-officer-search-api";

	public static void main(String[] args) {
		SpringApplication.run(BankruptOfficerSearchApiApplication.class, args);
	}

}

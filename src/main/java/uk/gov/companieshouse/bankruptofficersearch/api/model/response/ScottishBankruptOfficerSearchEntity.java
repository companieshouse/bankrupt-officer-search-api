package uk.gov.companieshouse.bankruptofficersearch.api.model.response;

import org.springframework.stereotype.Component;

/**
 * Criteria for searching for bankrupt officers
 * (filtering and pagination).
 */
@Component
public class ScottishBankruptOfficerSearchEntity {

    private int startIndex;
    private int itemsPerPage;
    private ScottishBankruptOfficerSearchFiltersEntity filters;

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public ScottishBankruptOfficerSearchFiltersEntity getFilters() {
        return filters;
    }

    public void setFilters(ScottishBankruptOfficerSearchFiltersEntity filters) {
        this.filters = filters;
    }
}

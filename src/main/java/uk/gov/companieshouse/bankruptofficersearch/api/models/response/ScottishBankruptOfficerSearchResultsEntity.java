package uk.gov.companieshouse.bankruptofficersearch.api.models.response;

import java.util.List;

public class ScottishBankruptOfficerSearchResultsEntity {

    private int itemsPerPage;
    private int startIndex;
    private long totalResults;
    private List<ScottishBankruptOfficerSearchResultEntity> items;

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public List<ScottishBankruptOfficerSearchResultEntity> getItems() {
        return items;
    }

    public void setItems(List<ScottishBankruptOfficerSearchResultEntity> items) {
        this.items = items;
    }
}

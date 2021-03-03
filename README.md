# Companies House Bankrupt Officer Search API
## bankrupt-officer-search-api
API handling bankrupt officer search

### Requirements
* [Java 8][1]
* [Maven][2]
* [Git][3]

### Getting Started
To run this service locally via a local Docker environment:

1. Clone the [Docker CHS Development](https://github.com/companieshouse/docker-chs-development) repository and follow the steps in the README
2. Enable the `bankrupt` and `platform` modules by running `./bin/chs-dev modules enable bankrupt` & `./bin/chs-dev modules enable platform`
3. Enable the development mode for the `bankrupt-officer-search-api` by running `./bin/chs-dev development enable bankrupt-officer-api`
4. To run Docker, run `tilt up` within the `docker-chs-development` directory and then press spacebar. This will open Tilt within a browser
5. Access the API by using this URL: `http://chs.local/internal/officer-search/scottish-bankrupt-officers`

### Endpoints

Method | Path | Description
--- | --- | ---
POST | `/internal/officer-search/scottish-bankrupt-officers` | Returns search results for Scottish bankrupt officers
GET | `/internal/officer-search/scottish-bankrupt-officers/{ephemeral_officer_key}` | Returns a result for a Scottish bankrupt officer
GET | `/internal/officer-search/scottish-bankrupt-officers/healthcheck` | GET | Returns HTTP OK (`200`) to indicate a healthy application instance.

[1]: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[2]: https://maven.apache.org/download.cgi
[3]: https://git-scm.com/downloads

### Config Variable

Key | Example Value | Description
--- | --- | ---
`ORACLE_QUERY_API_URL` | http://oracle-query:123 | URL of the Oracle Query API to retrieve data from Oracle DB

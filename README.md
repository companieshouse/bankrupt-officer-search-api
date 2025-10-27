# Companies House Bankrupt Officer Search API
## bankrupt-officer-search-api
API handling bankrupt officer search

### Requirements
* [Java 21][1]
* [Maven][2]
* [Git][3]

### Getting Started
To run this service locally via a local Docker environment:

1. Clone the [Docker CHS Development](https://github.com/companieshouse/docker-chs-development) repository and follow the steps in the README
2. Enable the `bankrupt` and `platform` modules by running `./bin/chs-dev modules enable bankrupt` & `./bin/chs-dev modules enable platform`
3. Enable the development mode for the `bankrupt-officer-search-api` by running `./bin/chs-dev development enable bankrupt-officer-api`
4. To run Docker, run `chs-dev up` within the `docker-chs-development` directory and then press spacebar.
5. Access the API by using this URL: `http://chs.local/internal/officer-search/scottish-bankrupt-officers`

### Endpoints

| Method | Path                                                                          | Description                                           |
|--------|-------------------------------------------------------------------------------|-------------------------------------------------------|
| POST   | `/internal/officer-search/scottish-bankrupt-officers`                         | Returns search results for Scottish bankrupt officers |
| GET    | `/internal/officer-search/scottish-bankrupt-officers/{ephemeral_officer_key}` | Returns a result for a Scottish bankrupt officer      |
| GET    | `/internal/officer-search/scottish-bankrupt-officers/healthcheck`             | GET                                                   | Returns HTTP OK (`200`) to indicate a healthy application instance.

[1]: https://www.oracle.com/java/technologies/downloads/#java21
[2]: https://maven.apache.org/download.cgi
[3]: https://git-scm.com/downloads

### Config Variable

| Key                    | Example Value           | Description                                                 |
|------------------------|-------------------------|-------------------------------------------------------------|
| `ORACLE_QUERY_API_URL` | http://oracle-query:123 | URL of the Oracle Query API to retrieve data from Oracle DB |

## Terraform ECS

### What does this code do?

The code present in this repository is used to define and deploy a dockerised container in AWS ECS.
This is done by calling a [module](https://github.com/companieshouse/terraform-modules/tree/main/aws/ecs) from terraform-modules. Application specific attributes are injected and the service is then deployed using Terraform via the CICD platform 'Concourse'.


| Application specific attributes | Value                                                                                                                                                                                                                                                                       | Description                                         |
|:--------------------------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:----------------------------------------------------|
| **ECS Cluster**                 | search-service                                                                                                                                                                                                                                                              | ECS cluster (stack) the service belongs to          |
| **Load balancer**               | {env}-chs-internalapi                                                                                                                                                                                                                                                       | The load balancer that sits in front of the service |
| **Concourse pipeline**          | [Pipeline link](https://ci-platform.companieshouse.gov.uk/teams/team-development/pipelines/bankrupt-officer-search-api ) <br> [Pipeline code](https://github.com/companieshouse/ci-pipelines/blob/master/pipelines/ssplatform/team-development/bankrupt-officer-search-api) | Concourse pipeline link in shared services          |

### Contributing
- Please refer to the [ECS Development and Infrastructure Documentation](https://companieshouse.atlassian.net/wiki/spaces/DEVOPS/pages/4390649858/Copy+of+ECS+Development+and+Infrastructure+Documentation+Updated) for detailed information on the infrastructure being deployed.

### Testing
- Ensure the terraform runner local plan executes without issues. For information on terraform runners please see the [Terraform Runner Quickstart guide](https://companieshouse.atlassian.net/wiki/spaces/DEVOPS/pages/1694236886/Terraform+Runner+Quickstart).
- If you encounter any issues or have questions, reach out to the team on the **#platform** slack channel.

### Vault Configuration Updates
- Any secrets required for this service will be stored in Vault. For any updates to the Vault configuration, please consult with the **#platform** team and submit a workflow request.

### Useful Links
- [ECS service config dev repository](https://github.com/companieshouse/ecs-service-configs-dev)
- [ECS service config production repository](https://github.com/companieshouse/ecs-service-configs-production)

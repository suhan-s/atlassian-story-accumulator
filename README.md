# atlassian-story-accumulator

Configs like SQS_REGION, SQS_ACCESS_KEY, SQS_SECRET_KEY, SQS_QUEUE_URL, JIRA_BASE_URL can updated in profile in pom.xml

Select the profile accordingly while deploying the project.

For generating war
mvn clean install -P {PROFILE_TO_BE SELECTED}
ex: mvn clean install -P Accumulator-SERVER

war wil be generated in the target folder which can be deployed on any tomcat server

For running locally in intellij
Update the profile variables as required
Open Maven Projects tab
Select the profile inside the Profiles sections
Click clean and then install inside the Maven Webapp Lifecycle Section
Expand jetty inside the Plugins sections in Maven Webapp
Click jetty:run

Endpoint https://localhost:8080/api/issue/sum?query={search_query}&name={descriptive_name}


# testrail-maven-plugin

This plugin allows creating and closing testruns on a [Testrail](http://www.gurock.com/testrail) project. It can be used as a wrapper around the execution of automated tests which will update the created testrun.

## Configuration

```
<build>
  <plugins>
    <plugin>
      <groupId>io.github.martinschneider</groupId>
      <artifactId>testrail-maven-plugin</artifactId>
      <version>1.0</version>
      <configuration>
        <url>https://demo.testrail.io</url>
        <username>user</username>
        <password>pw</password>
        <runId>${runId}</runId>
      </configuration>
      <executions>
        <execution>
          <id>testrail</id>
          <phase>initialize</phase>
          <goals>
            <goal>createrun</goal>
          </goals>
          <configuration>
            <projectId>${projectId}</projectId>
            <suiteId>${suiteId}</suiteId>
            <planId>${planId}</planId>
            <testRunName>${testRunName}</testRunName>
          </configuration>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```

## Usage

### Create a testrun
`mvn initialize`

This will create a testrun and set its id as a parameter for subsequent Maven plugins. For example, you can use this to update the test run with the results of your test executions.

### Close a testrun
`mvn testrail:closerun -DrunId=123`

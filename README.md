[<img src="https://travis-ci.com/martinschneider/testrail-maven-plugin.svg?branch=master" height="41" alt="Build status"/>](https://travis-ci.com/martinschneider/testrail-maven-plugin)
[<img src="https://www.buymeacoffee.com/assets/img/guidelines/download-assets-sm-1.svg" height="41" alt="Buy me a coffee"/>](https://www.buymeacoffee.com/mschneider)

# testrail-maven-plugin

This plugin allows creating and closing testruns on a [Testrail](http://www.gurock.com/testrail) project. It can be used as a wrapper around the execution of automated tests which will update the created testrun.

## Configuration

```
<?xml version="1.0" encoding="UTF-8"?>
<build>
  <plugins>
    <plugin>
      <groupId>io.github.martinschneider</groupId>
      <artifactId>testrail-maven-plugin</artifactId>
      <version>1.1</version>
      <configuration>
        <url>http://demo.testrail.io</url>
        <username>user</username>
        <password>pw</password>
      </configuration>
      <executions>
        <execution>
          <id>testrail-create</id>
          <phase>initialize</phase>
          <goals>
            <goal>create-run</goal>
          </goals>
          <configuration>
            <projectId>${projectId}</projectId>
            <suiteId>${suiteId}</suiteId>
            <planId>${planId}</planId>
            <testRunName>${testRunName}</testRunName>
          </configuration>
        </execution>
        <execution>
          <id>testrail-close</id>
          <phase>verify</phase>
          <goals>
            <goal>complete-run</goal>
          </goals>
          <configuration>
            <runId>${runId}</runId>
          </configuration>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```

## Usage

`mvn verify -DprojectId=1 -DsuiteId=2`

This will create a testrun (in the project with id 1 under the test suite with id 2) and set its id as a parameter for subsequent Maven plugins (in the `initialize` phase of the Maven lifecycle). For example, you can use this to update the test run with the results of your test executions (in the `test` phase). Finally (in the `verify` phase), the test run is closed.

## Contact
Martin Schneider, mart.schneider@gmail.com

[![Buy me a coffee](https://www.buymeacoffee.com/assets/img/guidelines/download-assets-1.svg)](https://www.buymeacoffee.com/mschneider)
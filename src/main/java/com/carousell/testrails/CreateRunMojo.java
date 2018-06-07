package com.carousell.testrails;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.Run;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Mojo for creating a Testrail run */
@Mojo(name = "create-run")
public class CreateRunMojo extends AbstractMojo {

  private static final Logger LOG = LoggerFactory.getLogger(CreateRunMojo.class);

  @Parameter(defaultValue = "${project}", readonly = true, required = true)
  private MavenProject project;

  @Parameter(required = true, readonly = true)
  private String url;

  @Parameter(required = true, readonly = true)
  private int projectId;

  @Parameter(required = true, readonly = true)
  private int suiteId;

  @Parameter(required = true, readonly = true)
  private String username;

  @Parameter(required = true, readonly = true)
  private String password;

  @Parameter(defaultValue = "generated by testrail-maven-plugin")
  private String testRunName;

  public void execute() throws MojoExecutionException, MojoFailureException {
    LOG.info("Creating testrail run");
    TestRail testRail =
        TestRail.builder(url, username, password).build();
    Run run =
        testRail
            .runs()
            .add(projectId, new Run().setSuiteId(suiteId).setName(testRunName))
            .execute();

    LOG.info("Setting runId {} as Maven property", run.getId());
    project.getProperties().put("runId", new Integer(run.getId()).toString());
  }
}

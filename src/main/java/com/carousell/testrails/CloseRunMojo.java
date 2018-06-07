package com.carousell.testrails;

import com.codepine.api.testrail.TestRail;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Mojo to close a run on Testrail. */
@Mojo(name = "closerun")
public class CloseRunMojo extends AbstractMojo {

  private static final Logger LOG = LoggerFactory.getLogger(CreateRunMojo.class);

  @Parameter(defaultValue = "${project}", readonly = true, required = true)
  private MavenProject project;

  @Parameter(required = true, readonly = true)
  private String url;

  @Parameter(required = true, readonly = true)
  private int runId;

  @Parameter(required = true, readonly = true)
  private String username;

  @Parameter(required = true, readonly = true)
  private String password;

  public void execute() throws MojoExecutionException, MojoFailureException {
    LOG.info("Closing testrail run");
    TestRail testRail =
        TestRail.builder(url, username, password).applicationName("playground").build();
    testRail.runs().close(runId).execute();
  }
}

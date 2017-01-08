package org.theultimatedeployer.enforcer.editorconfig;

import org.theultimatedeployer.enforcer.editorconfig.check.CheckResult;
import org.theultimatedeployer.enforcer.editorconfig.check.Checker;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.maven.enforcer.rule.api.EnforcerRule;
import org.apache.maven.enforcer.rule.api.EnforcerRuleException;
import org.apache.maven.enforcer.rule.api.EnforcerRuleHelper;
import org.apache.maven.plugin.logging.Log;
import org.editorconfig.core.EditorConfig;
import org.editorconfig.core.EditorConfigException;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class EditorConfigRule implements EnforcerRule {

  private String configLocation;

  public static Log log;

  private String fileFilterRegexp;

  private String dirFilterRegexp = null;

  private String rootDirStr;

  private boolean shouldFail = false;

  private EditorConfig ec;

  public void execute(EnforcerRuleHelper enforcerRuleHelper) throws EnforcerRuleException {

    log = enforcerRuleHelper.getLog();

    log.info("Starting to work...");

    checkParameters();

    RegexFileFilter regexFileFilter = new RegexFileFilter(fileFilterRegexp);


    IOFileFilter regexDirFilter;

    if (dirFilterRegexp != null) {
      regexDirFilter = new RegexFileFilter(dirFilterRegexp);
    } else {
      regexDirFilter = TrueFileFilter.TRUE;
    }

    log.info("Using rootDir:" + rootDirStr);
    log.info("and regexp for file filtering: " + fileFilterRegexp);

    File rootDir = new File(rootDirStr);

    String confFileLocation = configLocation; // + File.separator + ".editorconfig";

    log.info("Conf: "+ confFileLocation);

    ec = new EditorConfig();

    Collection<File> files = FileUtils.listFiles(rootDir, regexFileFilter, regexDirFilter);

    log.info("Checking " + files.size() + " files...");

    Checker checker = new Checker();

    for (File file : files) {

      try {
        List props = ec.getProperties(file.getAbsolutePath());
        if (props != null && props.size() > 0) {
          List<CheckResult> checkResult = checker.checkFile(rootDir, file, props);
          log.info(CheckResult.printResults(checkResult));
          if (shouldFail) {
            for (CheckResult checkR: checkResult){
              if (checkR.isErrorCase()) {
                throw new EnforcerRuleException(CheckResult.printResults(checkResult));
              }
            }
          }
        }
      } catch (IOException e) {
        log.error(e);
      } catch (EditorConfigException e) {
        log.error(e);
      }
    }
  }

  private void checkParameters() throws EnforcerRuleException {
    if (rootDirStr == null) {
      throw new EnforcerRuleException("Parameter rootDir has to be set!");
    }
    if (fileFilterRegexp == null) {
      throw new EnforcerRuleException("Parameter fileFilterRegexp has to be set!");
    }
  }


  public boolean isCacheable() {
     return false;
  }

  public boolean isResultValid(EnforcerRule enforcerRule) {
     return false;
  }

  public String getCacheId() {
     return null;
  }

  public void setShouldFail(boolean shouldFail) {
     this.shouldFail = shouldFail;
  }

  public void setConfigLocation(String aSystemPath) {
     configLocation = aSystemPath;
  }

  public void setRootDir(String rootDirStr) {
     this.rootDirStr = rootDirStr;
  }

  public void setFileFilterRegexp(String fileFilterRegexp) {
     this.fileFilterRegexp = fileFilterRegexp;
  }

  public void setDirFilterRegexp(String dirFilterRegexp) {
     this.dirFilterRegexp = dirFilterRegexp;
  }
}

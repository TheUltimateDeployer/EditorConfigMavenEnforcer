package org.theultimatedeployer.enforcer.editorconfig.check;

import org.theultimatedeployer.enforcer.editorconfig.EditorConfigRule;
import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.logging.Log;
import org.editorconfig.core.EditorConfig;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chris on 14.09.16.
 */
public class Checker {

  Log log = EditorConfigRule.log;

  /**
   * This method gets a source file and the corresponding EditorConfig properties
   *
   * @param sourceFile - file that has to be checked
   * @param properties - EditorConfig properties
   * @return - list of CheckResult objects with the results of all configured checks
   * @throws IOException
   */
  public List<CheckResult> checkFile(File rootDir, File sourceFile, List<EditorConfig.OutPair> properties) throws IOException {

    Path sourceFilePath = Paths.get(sourceFile.getAbsolutePath());
    Path rootDirPath = Paths.get(rootDir.getAbsolutePath());

    Path relativePath = rootDirPath.relativize(sourceFilePath);
    log.info("Checking file: " + relativePath.toString());

    List<CheckResult> checkResults = new ArrayList<CheckResult>();

    byte[] fileContent = readFile(sourceFile);
    Map mappedProperties = map(properties);

    List<Check> someChecks = CheckBuilder.build(mappedProperties);

    for (Check someCheck : someChecks) {
      try {
        checkResults.add(someCheck.check(fileContent));
      } catch (CheckException e) {
        // current streategy: go ahead and check further
        log.error(e.getLocalizedMessage());
      }
    }
    return checkResults;
  }

  private byte[] readFile(File sourceFile) throws IOException {
    FileInputStream fis = new FileInputStream(sourceFile);
    return IOUtils.toByteArray(fis);
  }

  private Map map(List<EditorConfig.OutPair> properties) {
    Map resultMap = new HashMap();
    for (EditorConfig.OutPair editorConfig : properties) {
      log.info(editorConfig.getKey() + " " + editorConfig.getVal());
      resultMap.put(editorConfig.getKey(), editorConfig.getVal());
    }
    return resultMap;
  }
}

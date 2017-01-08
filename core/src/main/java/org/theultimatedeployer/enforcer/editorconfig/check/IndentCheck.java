package org.theultimatedeployer.enforcer.editorconfig.check;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * Created by chris on 14.09.16.
 */
public class IndentCheck extends AbstractCheck {

  public CheckResult check(byte[] content) throws CheckException {
    IndentCheckConfig indentCheckConfig = (IndentCheckConfig) checkConfig;

    CheckResult checkResult = new CheckResult();
    checkResult.setCheck(this);

    try (
      InputStream inputStream = new ByteArrayInputStream(content)
    ) {
      Scanner scanner = new Scanner(inputStream);

      int i = 0;

      while (scanner.hasNext()) {
        String actLine = scanner.nextLine();
        checkLine(actLine, i, checkResult, indentCheckConfig);
        i++;
      }
    } catch (IOException e) {
        throw new CheckException(e);
    }
    return checkResult;

  }

  private void checkLine(String actLine, int i, CheckResult checkResult, IndentCheckConfig indentCheckConfig) {
    if (indentCheckConfig.useSpaces()) {
      checkLineWithSpace(actLine, i, checkResult, indentCheckConfig);
    } else if (indentCheckConfig.useTabs()) {
      checkLineWithTab(actLine, i, checkResult, indentCheckConfig);
    }
  }

  private void checkLineWithTab(String actLine, int i, CheckResult checkResult, IndentCheckConfig indentCheckConfig) {
    for (int l = 0; l < actLine.length(); l++) {
      if (actLine.charAt(l) == '\t') {
        // do nothing
      } else if (actLine.charAt(l) == ' ') {
        checkResult.addErrorDetail(i + " has a space at position: " + l + "\n");
        break;
      } else if (actLine.charAt(l) != ' ') {
        break;
      }
    }
  }

  private void checkLineWithSpace(String actLine, int i, CheckResult checkResult, IndentCheckConfig indentCheckConfig) {
    int amountOfSpaces = 0;

    for (int l = 0; l < actLine.length(); l++) {
      if (actLine.charAt(l) == '\t') {
        checkResult.addErrorDetail(i + " has a tab at position: " + l + "\n");
      }

      if (actLine.charAt(l) != ' ') {
        break;
      }
      amountOfSpaces++;
    }


    if (amountOfSpaces % indentCheckConfig.getIndent_size() == 0) {
      // nothing to do
    } else {
      checkResult.addErrorDetail(i + " has " + amountOfSpaces + " spaces but it should be dividable by " + indentCheckConfig.getIndent_size() +"\n");
    }
  }
}

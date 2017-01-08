package org.theultimatedeployer.enforcer.editorconfig.check;



import java.util.List;

/**
 * Created by chris on 20.09.16.
 */
public class NewLineCheck extends AbstractCheck {

  public CheckResult check(byte[] content) throws CheckException {
    NewLineCheckConfig newLineCheckConfig = (NewLineCheckConfig) checkConfig;
    if (
      (newLineCheckConfig.isNewLineAtEnd() && containsNewLine(content)) ||
        (!newLineCheckConfig.isNewLineAtEnd() && !containsNewLine(content))
      ) {
      return CheckResult.TRUE;
    } else {
      CheckResult checkResult = new CheckResult();
      checkResult.setCheck(this);
      checkResult.addErrorDetail(String.format("Newline at the end should be %s", Boolean.toString(newLineCheckConfig.isNewLineAtEnd())));
      return checkResult;
    }
  }

  private boolean containsNewLine(byte[] content) {
    if (content[content.length-1] == (byte)'\n')  {
    //if (lastLine.equals("\n") || lastLine.equals("\n") || lastLine.equals("\r\n")) {
      return true;
    } else {
      return false;
    }
  }
}

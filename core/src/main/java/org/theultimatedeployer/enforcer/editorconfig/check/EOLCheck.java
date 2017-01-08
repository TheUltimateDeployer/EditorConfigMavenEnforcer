package org.theultimatedeployer.enforcer.editorconfig.check;


import org.apache.commons.lang3.SystemUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chris on 20.09.16.
 */
public class EOLCheck extends AbstractCheck {

  int windows = 0, unix = 0, oldApple = 0;

  public CheckResult check(byte[] bytes) throws CheckException {


    CheckResult checkResult = new CheckResult();
    checkResult.setCheck(this);

    InputStream inputStream = new ByteArrayInputStream(bytes);

    int actCharacter;
    boolean lastSeenCarriageReturn = false;
    try {
      while ((actCharacter = inputStream.read()) != -1) {
        switch (actCharacter) {
          case '\n':
            if (!lastSeenCarriageReturn) {
              unix++;
            } else {
              lastSeenCarriageReturn = false;
              windows++;
            }
            break;
          case '\r':
            if (lastSeenCarriageReturn) {
              oldApple++;
            }
            lastSeenCarriageReturn = true;
            break;
          default:
            if (lastSeenCarriageReturn) {
              oldApple++;
            }
            lastSeenCarriageReturn = false;
        }
      }

      // check for different newline styls -> error
      if ((windows * unix >0) || (windows * oldApple > 0) || (unix * oldApple > 0)) {
        checkResult.addErrorDetail(String.format("Found different new line characters! Windows: %d, Unix: %d, Old Apple: %d", windows, unix, oldApple));
      } else if (checkConfiguredStyles() != null) {
        checkResult.addErrorDetail(checkConfiguredStyles());
      }
    } catch (IOException e) {
      throw new CheckException(e);
    }
    return checkResult;
  }

  private String checkConfiguredStyles() {
    EOLCheckConfig eolCheckConfig = (EOLCheckConfig) checkConfig;
    switch (eolCheckConfig.plattform) {
      case EOLCheckConfig.SYSTEM:
        if (SystemUtils.IS_OS_WINDOWS) {
          if (unix + oldApple > 0) {
            return "Should contain Windows line ending but has also " + unix + " Unix endings and " + oldApple + " old Apple endings!";
          }
        } else if (SystemUtils.IS_OS_UNIX) {
            if (windows + oldApple > 0) {
              return "Should contain Unix line endings but has also " + windows + " Windows endings amd " + oldApple + " ld Apple endings!";
            }
          break;
        }
        break;
      case EOLCheckConfig.WINDOWS:
        if (unix + oldApple > 0) {
          return "Should contain Windows line ending but has also " + unix + " Unix endings and " + oldApple + " old Apple endings!";
        }
        break;
      case EOLCheckConfig.UNIX:
        if (windows + oldApple > 0) {
          return "Should contain Unix line endings but has also " + windows + " Windows endings amd " + oldApple + " ld Apple endings!";
        }
        break;
      default:
    }
    return null;
  }
}

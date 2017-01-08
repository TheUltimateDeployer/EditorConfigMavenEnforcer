package org.theultimatedeployer.enforcer.editorconfig.check;

import junit.framework.TestCase;

/**
 * Created by chris on 22.09.16.
 */
public class EOLCheckTest extends TestCase {

  private byte[] newlineUnixOK = new byte[4];

  private byte[] newlineMixed = new byte[4];


  private void setup () {
    newlineUnixOK[0] = '\n';
    newlineUnixOK[1] = '\n';
    newlineUnixOK[2] = '\n';
    newlineUnixOK[3] = '\n';

    newlineMixed[0] = '\n';
    newlineMixed[0] = '\r';
    newlineMixed[0] = '\n';
    newlineMixed[0] = '\r';
  }

  public void testCheck() throws Exception {
    EOLCheck eolCheck = new EOLCheck();

    EOLCheckConfig eolCheckConfig = new EOLCheckConfig("LF");
    eolCheck.setCheckConfig(eolCheckConfig);

    boolean testResult = eolCheck.check(newlineUnixOK).equals(CheckResult.TRUE);

    assert testResult == true;

  }

}
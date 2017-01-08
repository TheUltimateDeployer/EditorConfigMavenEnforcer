package org.theultimatedeployer.enforcer.editorconfig.check;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by chris on 20.09.16.
 */
public class NewLineCheckTest extends TestCase {
  public void testCheckNewlineOK() throws Exception {
    String checkFilePath = "src//test//resources//fileForNewLineTest.xml";

    InputStream inputStream = new FileInputStream(checkFilePath);
    byte[] contentOK = IOUtils.toByteArray(inputStream);

    
    NewLineCheck newLineCheck = new NewLineCheck();
    NewLineCheckConfig newLineCheckConfig = new NewLineCheckConfig();
    newLineCheckConfig.setNewLineAtEnd(true);

    newLineCheck.setCheckConfig(newLineCheckConfig);

    CheckResult  testResult = newLineCheck.check(contentOK);
    Assert.assertEquals(testResult.getResultDetail(),CheckResult.RESULTDETAIL_OK);
  }
  
  public void testCheckNoNewlineThrowsError() throws Exception {
    String checkFilePath = "src//test//resources//fileForNewLineHoatKoe.xml";

    InputStream inputStream = new FileInputStream(checkFilePath);
    byte[] contentOK = IOUtils.toByteArray(inputStream);


    NewLineCheck newLineCheck = new NewLineCheck();
    NewLineCheckConfig newLineCheckConfig = new NewLineCheckConfig();
    newLineCheckConfig.setNewLineAtEnd(true);

    newLineCheck.setCheckConfig(newLineCheckConfig);

    CheckResult  testResult = newLineCheck.check(contentOK);
    Assert.assertNotSame(testResult.getResultDetail(),CheckResult.RESULTDETAIL_OK);
  }
}
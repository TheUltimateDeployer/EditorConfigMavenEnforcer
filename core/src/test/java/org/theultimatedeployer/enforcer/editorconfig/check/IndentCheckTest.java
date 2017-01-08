package org.theultimatedeployer.enforcer.editorconfig.check;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by chris on 15.09.16.
 */
public class IndentCheckTest extends TestCase {
  
  public void testCheckFileWithSpacesOK() throws Exception {
    String checkFilePath = "src//test//resources//fileForIndentTest1.xml";
    
    InputStream inputStream = new FileInputStream(checkFilePath);
    byte[] contentOK = IOUtils.toByteArray(inputStream);
    
    IndentCheck indentCheck = new IndentCheck();
    IndentCheckConfig indentCheckConfig = new IndentCheckConfig(IndentCheckConfig.SPACE, "2");
    indentCheck.setCheckConfig((CheckConfig) indentCheckConfig);

    CheckResult checkResult = indentCheck.check(contentOK);


    Assert.assertEquals(checkResult.getCheck(),indentCheck);
    Assert.assertEquals(checkResult.getResultDetail(),"");
  }

  public void testCheckFileWithTabInsteadOfSpace() throws Exception {
    String checkFilePath = "src//test//resources//fileForIndentTest2.xml";
    InputStream inputStream = new FileInputStream(checkFilePath);
    byte[] contentOK = IOUtils.toByteArray(inputStream);

    IndentCheck indentCheck = new IndentCheck();
    IndentCheckConfig indentCheckConfig = new IndentCheckConfig(IndentCheckConfig.SPACE, "2");
    indentCheck.setCheckConfig((CheckConfig) indentCheckConfig);

    CheckResult checkResult = indentCheck.check(contentOK);
    Assert.assertEquals(checkResult.getCheck(),indentCheck);
    Assert.assertNotSame(checkResult.getResultDetail(),"");
  }

  public void testCheckFileWithTabOK() throws Exception {
    String checkFilePath = "src//test//resources//fileForIndentTest2.xml";
    InputStream inputStream = new FileInputStream(checkFilePath);
    byte[] contentOK = IOUtils.toByteArray(inputStream);

    IndentCheck indentCheck = new IndentCheck();
    IndentCheckConfig indentCheckConfig = new IndentCheckConfig(IndentCheckConfig.TAB, "");
    indentCheck.setCheckConfig((CheckConfig) indentCheckConfig);

    CheckResult checkResult = indentCheck.check(contentOK);
    Assert.assertEquals(checkResult.getCheck(),indentCheck);
    Assert.assertEquals(checkResult.getResultDetail(),"");
  }


  public void testCheckFileWithSpaceInsteadOfTab() throws Exception {
    String checkFilePath = "src//test//resources//fileForIndentTest1.xml";
    InputStream inputStream = new FileInputStream(checkFilePath);
    byte[] contentOK = IOUtils.toByteArray(inputStream);

    IndentCheck indentCheck = new IndentCheck();
    IndentCheckConfig indentCheckConfig = new IndentCheckConfig(IndentCheckConfig.TAB, "");
    indentCheck.setCheckConfig((CheckConfig) indentCheckConfig);

    CheckResult checkResult = indentCheck.check(contentOK);
    Assert.assertEquals(checkResult.getCheck(), indentCheck);
    Assert.assertNotSame(checkResult.getResultDetail(), "");
  }
}
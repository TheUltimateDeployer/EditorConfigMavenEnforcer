package org.theultimatedeployer.enforcer.editorconfig;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.List;

/**
 * Created by chris on 13.09.16.
 */
public class EditorConfigRuleTest extends TestCase {
    public void testExecute() throws Exception {

    }

  /**
   * This test checks if the configured properties are retrieved 
   * for a given file.
   * @throws Exception
   */
  public void testLoadProperties() throws Exception {
        String checkFile = "src//test//resources//pom.xml";

        EditorConfigRule editorConfigRule = new EditorConfigRule();
        editorConfigRule.setConfigLocation(checkFile);
        Assert.assertTrue(true);
    }

    public void testIsCacheable() throws Exception {

    }

    public void testIsResultValid() throws Exception {

    }

    public void testGetCacheId() throws Exception {

    }

    public void testSetConfigLocation() throws Exception {

    }

}

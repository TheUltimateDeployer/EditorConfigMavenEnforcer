package org.theultimatedeployer.enforcer.editorconfig.check;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 * Created by chris on 14.09.16.
 */
public abstract class AbstractCheck implements Check {

  protected CheckConfig checkConfig;

  public void setCheckConfig(CheckConfig checkConfig) {
    this.checkConfig = checkConfig;
  }
}

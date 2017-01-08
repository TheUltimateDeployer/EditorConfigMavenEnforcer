package org.theultimatedeployer.enforcer.editorconfig.check;

import java.io.IOException;

/**
 * Created by chris on 14.09.16.
 */
public class CheckException extends Exception {
  public CheckException(Exception e) {
    super(e);
  }
}

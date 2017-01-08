package org.theultimatedeployer.enforcer.editorconfig.check;

/**
 * Created by chris on 20.09.16.
 */
public class NewLineCheckConfig implements CheckConfig {

  private boolean newLineAtEnd = false;
  
  public boolean isNewLineAtEnd() {
    return newLineAtEnd;
  }

  public void setNewLineAtEnd(boolean newLineAtEnd) {
    this.newLineAtEnd = newLineAtEnd;
  }
}

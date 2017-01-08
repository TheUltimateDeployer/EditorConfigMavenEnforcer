package org.theultimatedeployer.enforcer.editorconfig.check;

/**
 * Created by chris on 20.09.16.
 */
public class EOLCheckConfig implements CheckConfig{

  public static final int SYSTEM = 0;

  public static final int UNIX = 1;

  public static final int WINDOWS = 2;

  // MAC OS X now uses UNIX -> old apple ;-)
  public static final int OLD_APPLE = 3;

  int plattform;

  public EOLCheckConfig(final String configVal) {
    if ("system".equalsIgnoreCase(configVal)) {
      plattform = SYSTEM;
    } else if ("LF".equalsIgnoreCase(configVal)) {
      plattform = UNIX;
    } else if ("CRLF".equalsIgnoreCase(configVal)){
      plattform = WINDOWS;
    }
  }


  public boolean isWindows() {
    return plattform == WINDOWS;
  }

  public boolean isUnix() {
    return plattform == UNIX;
  }

  public boolean isOldApple() {
    return plattform == OLD_APPLE;
  }

  public void setUnix() {
    plattform = UNIX;
  }

  public void setWindows() {
    plattform = WINDOWS;
  }

  public void setOldApple() {
    plattform = OLD_APPLE;
  }
}

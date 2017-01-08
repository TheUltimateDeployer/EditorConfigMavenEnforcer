package org.theultimatedeployer.enforcer.editorconfig.check;

import java.util.List;

/**
 * Created by chris on 14.09.16.
 */
public interface Check {
  CheckResult check(byte[] bytes) throws CheckException;
}

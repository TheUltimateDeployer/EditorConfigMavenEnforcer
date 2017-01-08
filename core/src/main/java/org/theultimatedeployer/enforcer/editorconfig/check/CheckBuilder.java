package org.theultimatedeployer.enforcer.editorconfig.check;

import org.theultimatedeployer.enforcer.editorconfig.EditorConfigRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chris on 14.09.16.
 */
public class CheckBuilder {

  public static List<Check> build(Map configProperties) {
    List someChecks = new ArrayList<Check>();

    if (configProperties.containsKey("indent_style")) {
      EditorConfigRule.log.info("Using IndentChecker");

      IndentCheckConfig indentCheckConfig = new IndentCheckConfig(
        (String)configProperties.get("indent_style"),
        (String)configProperties.get("indent_size")
      );

      IndentCheck indentCheck = new IndentCheck();
      indentCheck.setCheckConfig(indentCheckConfig);
      someChecks.add(indentCheck);
    }
    if (configProperties.containsKey("end_of_line")) {
      EOLCheckConfig eolCheckConfig = new EOLCheckConfig((String)configProperties.get("end_of_line"));
      EOLCheck eolCheck = new EOLCheck();
      eolCheck.setCheckConfig(eolCheckConfig);
      someChecks.add(eolCheck);
    }

    return someChecks;
  }
}

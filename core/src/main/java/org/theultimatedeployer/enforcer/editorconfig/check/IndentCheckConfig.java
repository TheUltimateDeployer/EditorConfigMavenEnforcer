package org.theultimatedeployer.enforcer.editorconfig.check;

/**
 * Created by chris on 14.09.16.
 */
public class IndentCheckConfig implements CheckConfig{
  
  public final static String TAB = "tab";
  public final static String SPACE = "space";
  public final static String FINAL_NEWLINE = "insert_final_newline";

  private String indent_style;
  
  private int indent_size;
  
  public IndentCheckConfig(String indent_style, String indent_size) {
    
    this.indent_style = indent_style;
    if (indent_size != null && !indent_size.equals("")) {
      this.indent_size = Integer.parseInt(indent_size);
    }
  }

  public String getIndent_style() {
    return indent_style;
  }

  public void setIndent_style(String indent_style) {
    this.indent_style = indent_style;
  }

  public int getIndent_size() {
    return indent_size;
  }

  public void setIndent_size(int indent_size) {
    this.indent_size = indent_size;
  }

  public boolean useTabs() {
    if (indent_style.equalsIgnoreCase(IndentCheckConfig.TAB)) {
      return true;
    }
    return false;
  }

  public boolean useSpaces() {
    if (indent_style.equalsIgnoreCase(IndentCheckConfig.SPACE)) {
      return true;
    }
    return false;
  }
}

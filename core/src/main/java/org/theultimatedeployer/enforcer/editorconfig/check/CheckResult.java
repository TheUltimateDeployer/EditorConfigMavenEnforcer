package org.theultimatedeployer.enforcer.editorconfig.check;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * Created by chris on 14.09.16.
 */
public class CheckResult {

  public static CheckResult TRUE = new CheckResult();

  public static String RESULTDETAIL_OK = "";

  private Check check;

  boolean errorCase = false;

  // Empty string due to stupid isOK comparison with empty string
  StringBuffer resultDetail = new StringBuffer("");


  public void addErrorDetail(String s) {
    setErrorCase(true);
    addDetail(s);
  }

  public boolean isErrorCase() {
    return errorCase;
  }

  public void setErrorCase(boolean errorCase) {
    this.errorCase = errorCase;
  }

  public static String printResults(List<CheckResult> someCheckResults) {
    StringBuilder sb = new StringBuilder();
    for (CheckResult checkResult : someCheckResults) {
      sb.append(checkResult.getResultDetail());
    }
    return sb.toString();
  }


  public Check getCheck() {
    return check;
  }

  public void setCheck(Check check) {
    this.check = check;
  }

  public String getResultDetail() {
    return resultDetail.toString();
  }

  public void addDetail(String s) {
    resultDetail.append(s);
  }

  @Override
  public boolean equals(Object o) {
    CheckResult checkResult = (CheckResult) o;

    return new EqualsBuilder().append(checkResult.getResultDetail().toString(), this.resultDetail.toString()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(73, 37).append(check).append(resultDetail.toString()).toHashCode();
  }
}

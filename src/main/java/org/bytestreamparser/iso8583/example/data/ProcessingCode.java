package org.bytestreamparser.iso8583.example.data;

import java.util.HashMap;
import org.bytestreamparser.composite.data.AbstractDataObject;

public class ProcessingCode extends AbstractDataObject<ProcessingCode> {
  public static final String TRANSACTION_TYPE_CODE = "transactionTypeCode";
  public static final String FROM_ACCOUNT_TYPE_CODE = "fromAccountTypeCode";
  public static final String TO_ACCOUNT_TYPE_CODE = "toAccountTypeCode";

  public ProcessingCode() {
    super(new HashMap<>());
  }

  public String getTransactionTypeCode() {
    return get(TRANSACTION_TYPE_CODE);
  }

  public ProcessingCode setTransactionTypeCode(String transactionTypeCode) {
    return set(TRANSACTION_TYPE_CODE, transactionTypeCode);
  }

  public String getFromAccountTypeCode() {
    return get(FROM_ACCOUNT_TYPE_CODE);
  }

  public ProcessingCode setFromAccountTypeCode(String fromAccountTypeCode) {
    return set(FROM_ACCOUNT_TYPE_CODE, fromAccountTypeCode);
  }

  public String getToAccountTypeCode() {
    return get(TO_ACCOUNT_TYPE_CODE);
  }

  public ProcessingCode setToAccountTypeCode(String toAccountTypeCode) {
    return set(TO_ACCOUNT_TYPE_CODE, toAccountTypeCode);
  }
}

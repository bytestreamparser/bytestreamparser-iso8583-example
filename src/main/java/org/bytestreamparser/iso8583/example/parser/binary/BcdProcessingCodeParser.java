package org.bytestreamparser.iso8583.example.parser.binary;

import static org.bytestreamparser.iso8583.example.data.ProcessingCode.FROM_ACCOUNT_TYPE_CODE;
import static org.bytestreamparser.iso8583.example.data.ProcessingCode.TO_ACCOUNT_TYPE_CODE;
import static org.bytestreamparser.iso8583.example.data.ProcessingCode.TRANSACTION_TYPE_CODE;

import java.util.List;
import org.bytestreamparser.composite.parser.DataFieldParser;
import org.bytestreamparser.composite.parser.ObjectParser;
import org.bytestreamparser.iso8583.example.data.ProcessingCode;
import org.bytestreamparser.iso8583.util.StringParsers;

public class BcdProcessingCodeParser extends ObjectParser<ProcessingCode> {
  private static final DataFieldParser<ProcessingCode, String> TRANSACTION_TYPE_CODE_PARSER =
      new DataFieldParser<>(
          TRANSACTION_TYPE_CODE, StringParsers.fixedLengthBcd(TRANSACTION_TYPE_CODE, 2));
  private static final DataFieldParser<ProcessingCode, String> FROM_ACCOUNT_TYPE_CODE_PARSER =
      new DataFieldParser<>(
          FROM_ACCOUNT_TYPE_CODE, StringParsers.fixedLengthBcd(FROM_ACCOUNT_TYPE_CODE, 2));
  private static final DataFieldParser<ProcessingCode, String> TO_ACCOUNT_TYPE_CODE_PARSER =
      new DataFieldParser<>(
          TO_ACCOUNT_TYPE_CODE, StringParsers.fixedLengthBcd(TO_ACCOUNT_TYPE_CODE, 2));
  public static final List<DataFieldParser<ProcessingCode, String>> PARSERS =
      List.of(
          TRANSACTION_TYPE_CODE_PARSER, FROM_ACCOUNT_TYPE_CODE_PARSER, TO_ACCOUNT_TYPE_CODE_PARSER);

  public BcdProcessingCodeParser(String id) {
    super(id, ProcessingCode::new, PARSERS);
  }
}

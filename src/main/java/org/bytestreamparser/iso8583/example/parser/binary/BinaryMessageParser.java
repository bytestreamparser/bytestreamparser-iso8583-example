package org.bytestreamparser.iso8583.example.parser.binary;

import static org.bytestreamparser.iso8583.example.data.GenericIsoMessage.BITMAP_FIELD;
import static org.bytestreamparser.iso8583.example.data.GenericIsoMessage.MTI_FIELD;

import java.nio.charset.Charset;
import java.util.List;
import org.bytestreamparser.composite.parser.DataFieldParser;
import org.bytestreamparser.composite.parser.ObjectParser;
import org.bytestreamparser.iso8583.example.data.GenericIsoMessage;
import org.bytestreamparser.iso8583.parser.ExtendableBitmapParser;
import org.bytestreamparser.iso8583.parser.IsoFieldParser;
import org.bytestreamparser.iso8583.parser.UndefinedDataFieldParser;
import org.bytestreamparser.iso8583.util.IntegerParsers;
import org.bytestreamparser.iso8583.util.LongParsers;
import org.bytestreamparser.iso8583.util.StringParsers;

public class BinaryMessageParser extends ObjectParser<GenericIsoMessage> {
  public static final Charset EBCDIC = Charset.forName("Cp1047");
  private static final List<DataFieldParser<GenericIsoMessage, ?>> PARSERS =
      List.of(
          new DataFieldParser<>(MTI_FIELD, StringParsers.fixedLengthBcd(MTI_FIELD, 4)),
          new DataFieldParser<>(BITMAP_FIELD, new ExtendableBitmapParser("bitmap", 8)),
          new IsoFieldParser<>(2, StringParsers.binaryLVarBcd("pan")),
          new IsoFieldParser<>(3, new BcdProcessingCodeParser("processingCode")),
          new IsoFieldParser<>(4, LongParsers.bcd("transactionAmount", 12)),
          new IsoFieldParser<>(5, LongParsers.bcd("settlementAmount", 12)),
          new IsoFieldParser<>(6, LongParsers.bcd("cardholderBillingAmount", 12)),
          new IsoFieldParser<>(7, LongParsers.bcd("transmissionDatetime", 10)),
          new IsoFieldParser<>(8, IntegerParsers.bcd("cardholderBillingFeeAmount", 8)),
          new IsoFieldParser<>(9, new BcdConversionRateParser("settlementConversionRate")),
          new IsoFieldParser<>(10, new BcdConversionRateParser("cardholderBillingConversionRate")),
          new IsoFieldParser<>(11, StringParsers.fixedLengthBcd("systemTraceAuditNumber", 6)),
          new IsoFieldParser<>(12, StringParsers.fixedLengthBcd("localTransactionTime", 6)),
          new IsoFieldParser<>(13, StringParsers.fixedLengthBcd("localTransactionDate", 4)),
          new IsoFieldParser<>(14, StringParsers.fixedLengthBcd("expirationDate", 4)),
          new IsoFieldParser<>(15, StringParsers.fixedLengthBcd("settlementDate", 4)),
          new IsoFieldParser<>(16, StringParsers.fixedLengthBcd("conversionDate", 4)),
          new IsoFieldParser<>(17, StringParsers.fixedLength("captureDate", 4, EBCDIC)),
          new IsoFieldParser<>(18, StringParsers.fixedLengthBcd("merchantType", 4)),
          new IsoFieldParser<>(
              19, StringParsers.fixedLengthBcd("acquiringInstitutionCountryCode", 3)),
          new IsoFieldParser<>(20, StringParsers.fixedLengthBcd("panExtendedCountryCode", 3)),
          new IsoFieldParser<>(21, new UndefinedDataFieldParser("data-field-21")));

  public BinaryMessageParser() {
    super("binary-message", GenericIsoMessage::new, PARSERS);
  }
}

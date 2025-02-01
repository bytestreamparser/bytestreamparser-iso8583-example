package org.bytestreamparser.iso8583.example.parser.ascii;

import static org.bytestreamparser.iso8583.example.data.GenericIsoMessage.BITMAP_FIELD;
import static org.bytestreamparser.iso8583.example.data.GenericIsoMessage.MTI_FIELD;

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

public class AsciiMessageParser extends ObjectParser<GenericIsoMessage> {
  private static final List<DataFieldParser<GenericIsoMessage, ?>> PARSERS =
      List.of(
          new DataFieldParser<>(MTI_FIELD, StringParsers.fixedLength(MTI_FIELD, 4)),
          new DataFieldParser<>(BITMAP_FIELD, new ExtendableBitmapParser("bitmap", 8)),
          new IsoFieldParser<>(2, StringParsers.textLLVar("pan")),
          new IsoFieldParser<>(3, new AsciiProcessingCodeParser("processingCode")),
          new IsoFieldParser<>(4, LongParsers.text("transactionAmount", 12)),
          new IsoFieldParser<>(5, LongParsers.text("settlementAmount", 12)),
          new IsoFieldParser<>(6, LongParsers.text("cardholderBillingAmount", 12)),
          new IsoFieldParser<>(7, LongParsers.text("transmissionDatetime", 10)),
          new IsoFieldParser<>(8, IntegerParsers.text("cardholderBillingFeeAmount", 8)),
          new IsoFieldParser<>(9, new AsciiConversionRateParser("settlementConversionRate")),
          new IsoFieldParser<>(
              10, new AsciiConversionRateParser("cardholderBillingConversionRate")),
          new IsoFieldParser<>(11, StringParsers.fixedLength("systemTraceAuditNumber", 6)),
          new IsoFieldParser<>(12, StringParsers.fixedLength("localTransactionTime", 6)),
          new IsoFieldParser<>(13, StringParsers.fixedLength("localTransactionDate", 4)),
          new IsoFieldParser<>(14, StringParsers.fixedLength("expirationDate", 4)),
          new IsoFieldParser<>(15, StringParsers.fixedLength("settlementDate", 4)),
          new IsoFieldParser<>(16, StringParsers.fixedLength("conversionDate", 4)),
          new IsoFieldParser<>(17, StringParsers.fixedLength("captureDate", 4)),
          new IsoFieldParser<>(18, StringParsers.fixedLength("merchantType", 4)),
          new IsoFieldParser<>(19, StringParsers.fixedLength("acquiringInstitutionCountryCode", 3)),
          new IsoFieldParser<>(20, StringParsers.fixedLength("panExtendedCountryCode", 3)),
          new IsoFieldParser<>(21, new UndefinedDataFieldParser("data-field-21")));

  public AsciiMessageParser() {
    super("ascii-message", GenericIsoMessage::new, PARSERS);
  }
}

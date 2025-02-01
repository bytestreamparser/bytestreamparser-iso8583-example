package org.bytestreamparser.iso8583.example.parser.binary;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import org.bytestreamparser.api.parser.DataParser;
import org.bytestreamparser.iso8583.util.StringParsers;

public class BcdConversionRateParser extends DataParser<BigDecimal> {
  private static final DataParser<String> DIGITS_PARSER = StringParsers.fixedLength("digits", 8);

  public BcdConversionRateParser(String id) {
    super(id);
  }

  @Override
  public void pack(BigDecimal value, OutputStream output) throws IOException {
    String digits = String.format("%1d%07d", value.scale(), value.unscaledValue().intValueExact());
    DIGITS_PARSER.pack(digits, output);
  }

  @Override
  public BigDecimal parse(InputStream input) throws IOException {
    String digits = DIGITS_PARSER.parse(input);
    return new BigDecimal(digits.substring(1))
        .movePointLeft(Integer.parseInt(digits.substring(0, 1)));
  }
}

package org.bytestreamparser.iso8583.example.parser.ascii;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import org.bytestreamparser.api.parser.DataParser;
import org.bytestreamparser.iso8583.util.IntegerParsers;

public class AsciiConversionRateParser extends DataParser<BigDecimal> {
  private static final DataParser<Integer> SCALE_PARSER = IntegerParsers.text("scale", 1);
  private static final DataParser<Integer> RATE_PARSER = IntegerParsers.text("rate", 7);

  public AsciiConversionRateParser(String id) {
    super(id);
  }

  @Override
  public void pack(BigDecimal value, OutputStream output) throws IOException {
    SCALE_PARSER.pack(value.scale(), output);
    RATE_PARSER.pack(value.unscaledValue().intValueExact(), output);
  }

  @Override
  public BigDecimal parse(InputStream input) throws IOException {
    return new BigDecimal(SCALE_PARSER.parse(input)).movePointLeft(RATE_PARSER.parse(input));
  }
}

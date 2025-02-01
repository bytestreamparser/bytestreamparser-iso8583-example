package org.bytestreamparser.iso8583.example.data;

import java.util.HashMap;
import org.bytestreamparser.composite.data.AbstractDataObject;
import org.bytestreamparser.iso8583.data.ExtendableBitmap;
import org.bytestreamparser.iso8583.data.IsoMessage;

public class GenericIsoMessage extends AbstractDataObject<GenericIsoMessage> implements IsoMessage {
  public static final String MTI_FIELD = "mti";
  public static final String BITMAP_FIELD = "1";

  public GenericIsoMessage() {
    super(new HashMap<>());
  }

  public ExtendableBitmap getBitmap() {
    return get(BITMAP_FIELD);
  }

  @Override
  public boolean hasDataField(int bit) {
    return getBitmap().get(bit);
  }
}

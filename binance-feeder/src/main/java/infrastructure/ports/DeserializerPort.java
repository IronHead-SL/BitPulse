package infrastructure.ports;

import com.bitpulse.binancefeeder.domain.BinanceResponse;

public interface DeserializerPort {
    BinanceResponse deserialize(String data);
}

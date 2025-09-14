package infrastructure.ports;

import domain.BinanceResponse;

public interface DeserializerPort {
    BinanceResponse deserialize(String data);
}

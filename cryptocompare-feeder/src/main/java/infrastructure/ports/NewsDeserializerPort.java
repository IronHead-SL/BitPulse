package infrastructure.ports;

import domain.NewsResponse;

public interface NewsDeserializerPort {
    NewsResponse deserialize(String data);
}
package domain;

import java.util.List;

public record NewsResponse(
        List<Article> data
) {
}
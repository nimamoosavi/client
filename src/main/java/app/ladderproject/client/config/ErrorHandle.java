package app.ladderproject.client.config;

import feign.Response;
import feign.codec.ErrorDecoder;

public class ErrorHandle implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        return null;
    }
}

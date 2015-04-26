import java.io.IOException;
import java.util.List;

public interface HttpClient {
    void exec(List<String> urls) throws IOException;
}

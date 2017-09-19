import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFiles.location("/public");
        port(9999);

        post("/format", (request, response) -> {
            String res = request.body().toString();

            res = res.replaceAll("`([^`]*)`", "<span class='monospace'>$1</span>");
            res = res.replaceAll("(?<!\\*|\\\\\\*)\\*{2,2}([^\\*\\n].+?[^\\*])\\*{2,2}(?!\\*|\\\\)", "<span class='bold'>$1</span>");
            res = res.replaceAll("_(.+?)_", "<span class='italic'>$1</span>");
            res = res.replaceAll("\\r\\n|\\r|\\n", "<br>");

            return res;
        });
    }
}
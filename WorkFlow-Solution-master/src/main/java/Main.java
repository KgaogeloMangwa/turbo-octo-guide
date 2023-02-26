import io.javalin.Javalin;
import io.javalin.core.util.FileUtil;
import io.javalin.http.staticfiles.Location;


public class Main {


    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public", Location.CLASSPATH);
        }).start(2020);


        app.post("/upload-file", ctx -> {
            ctx.uploadedFiles("files").forEach(file -> {
                FileUtil.streamToFile(file.getContent(), "upload/" + file.getFilename());
            });
            ctx.html("Upload successful...kindly go back and click submit to view the graph rendering");
        });

    }

}



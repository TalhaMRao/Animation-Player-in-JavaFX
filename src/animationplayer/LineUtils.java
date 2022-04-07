package animationplayer;

import java.io.BufferedReader;
import java.io.IOException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LineUtils extends ShapeUtils {

    static Line create(BufferedReader reader, String line) throws IOException {

        Line lineShape = new Line();

        line = reader.readLine();
        lineShape.setStartX(stringToInt(line));
        line = reader.readLine();
        lineShape.setStartY(stringToInt(line));
        line = reader.readLine();
        lineShape.setEndX(stringToInt(line));
        line = reader.readLine();
        lineShape.setEndY(stringToInt(line));

        lineShape = checkExtraFeatures(reader, line, lineShape);
        return lineShape;

    }

    static Line checkExtraFeatures(BufferedReader reader, String line, Line lineShape) throws IOException {

        line = reader.readLine();

        if (line.contains("border")) {

            lineShape.setStrokeWidth(stringToInt(line));
            checkExtraFeatures(reader, line, lineShape);

        } else if (line.contains("color")) {

            String[] color = line.split(",");

            int r = stringToInt(color[0]);
            int g = stringToInt(color[1]);
            int b = stringToInt(color[2]);

            lineShape.setFill(Color.rgb(r, g, b));
            checkExtraFeatures(reader, line, lineShape);

        }

        return lineShape;

    }

}

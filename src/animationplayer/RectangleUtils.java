package animationplayer;

import java.io.BufferedReader;
import java.io.IOException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleUtils extends ShapeUtils {

    static Rectangle create(BufferedReader reader, String line) throws IOException {

        Rectangle rectangle = new Rectangle();

        line = reader.readLine();
        rectangle.setWidth(stringToInt(line));
        line = reader.readLine();
        rectangle.setHeight(stringToInt(line));
        line = reader.readLine();
        rectangle.setX(stringToInt(line));
        line = reader.readLine();
        rectangle.setY(stringToInt(line));

        rectangle = checkExtraFeatures(reader, line, rectangle);
        return rectangle;

    }

    static Rectangle checkExtraFeatures(BufferedReader reader, String line, Rectangle rectangle) throws IOException {

        line = reader.readLine();

        if (line.contains("border")) {

            rectangle.setStrokeWidth(stringToInt(line));
            checkExtraFeatures(reader, line, rectangle);

        } else if (line.contains("color")) {

            String[] color = line.split(",");

            int r = stringToInt(color[0]);
            int g = stringToInt(color[1]);
            int b = stringToInt(color[2]);

            rectangle.setFill(Color.rgb(r, g, b));
            checkExtraFeatures(reader, line, rectangle);

        } else if (line.contains("borderColor")) {

            String[] borderColor = line.split(",");

            int r = stringToInt(borderColor[0]);
            int g = stringToInt(borderColor[1]);
            int b = stringToInt(borderColor[2]);

            rectangle.setStroke(Color.rgb(r, g, b));
            checkExtraFeatures(reader, line, rectangle);

        }

        return rectangle;

    }

}

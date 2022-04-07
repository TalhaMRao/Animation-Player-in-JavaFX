package animationplayer;

import java.io.BufferedReader;
import java.io.IOException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleUtils extends ShapeUtils {

    static Circle create(BufferedReader reader, String line) throws IOException {

        Circle circle = new Circle();

        line = reader.readLine();
        circle.setRadius(stringToInt(line));
        line = reader.readLine();
        circle.setCenterX(stringToInt(line));
        line = reader.readLine();
        circle.setCenterY(stringToInt(line));

        circle = checkExtraFeatures(reader, line, circle);
        return circle;

    }

    static Circle checkExtraFeatures(BufferedReader reader, String line, Circle circle) throws IOException {

        line = reader.readLine();

        if (line.contains("border")) {

            circle.setStrokeWidth(stringToInt(line));
            checkExtraFeatures(reader, line, circle);

        } else if (line.contains("color")) {

            String[] color = line.split(",");

            int r = stringToInt(color[0]);
            int g = stringToInt(color[1]);
            int b = stringToInt(color[2]);

            circle.setFill(Color.rgb(r, g, b));
            checkExtraFeatures(reader, line, circle);

        } else if (line.contains("borderColor")) {

            String[] borderColor = line.split(",");

            int r = stringToInt(borderColor[0]);
            int g = stringToInt(borderColor[1]);
            int b = stringToInt(borderColor[2]);

            circle.setStroke(Color.rgb(r, g, b));
            checkExtraFeatures(reader, line, circle);

        }

        return circle;

    }

}

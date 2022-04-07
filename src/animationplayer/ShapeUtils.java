package animationplayer;

import java.io.BufferedReader;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class ShapeUtils {

    static int stringToInt(String string) {

        return Integer.parseInt(string.replaceAll("[^0-9]", ""));
    }

    static Effect determineEffect(BufferedReader reader, String line, Node node) throws IOException {

        Effect effect = new Effect(node);
        line = reader.readLine();

        switch (line) {

            case "Hide":

                effect.effectType = "Hide";
                line = reader.readLine();
                effect.start = stringToInt(line);
                break;

            case "Show":

                effect.effectType = "Show";
                line = reader.readLine();
                effect.start = stringToInt(line);
                break;

            case "Jump":

                effect.effectType = "Jump";
                line = reader.readLine();
                effect.start = stringToInt(line);
                line = reader.readLine();
                effect.x = stringToInt(line);
                line = reader.readLine();
                effect.y = stringToInt(line);
                break;

            case "ChangeColor":

                effect.effectType = "ChangeColor";
                line = reader.readLine();
                effect.start = stringToInt(line);
                line = reader.readLine();

                String[] color = line.split(",");
                int r1 = stringToInt(color[0]);
                int b1 = stringToInt(color[1]);
                int g1 = stringToInt(color[2]);
                effect.color = Color.rgb(r1, b1, g1);
                line = reader.readLine();

                if (line.contains("borderColor")) {

                    String[] borderColor = line.split(",");
                    int r2 = stringToInt(borderColor[0]);
                    int b2 = stringToInt(borderColor[1]);
                    int g2 = stringToInt(borderColor[2]);
                    effect.borderColor = Color.rgb(r2, b2, g2);

                }

                break;

        }

        return effect;

    }

}

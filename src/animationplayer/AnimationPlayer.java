package animationplayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static animationplayer.ShapeUtils.stringToInt;
import static javafx.application.Application.launch;

public class AnimationPlayer extends Application {

    int frames;
    int speed;
    int elements;

    int nodeCounter = -1;
    ArrayList<Node> nodes = new ArrayList<>();
    ArrayList<Effect> effects = new ArrayList<>();

    void loadAnimationFromFile(String fileName) {

        BufferedReader reader;

        try {

            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();

            while (line != null) {

                if (line.contains("frames")) {

                    frames = stringToInt(line);
                    line = reader.readLine();
                    speed = stringToInt(line);
                    line = reader.readLine();
                    elements = stringToInt(line);

                } else if (line.equals("Circle")) {

                    nodes.add(CircleUtils.create(reader, line));
                    nodeCounter++;
                    effects.add(new Effect("Hide", nodes.get(nodeCounter), 0));

                    while (!line.equals("")) {

                        if (line.equals("effect")) {
                            effects.add(ShapeUtils.determineEffect(reader, line, nodes.get(nodeCounter)));
                        }
                        line = reader.readLine();

                    }

                } else if (line.equals("Rect")) {

                    nodes.add(RectangleUtils.create(reader, line));
                    nodeCounter++;
                    effects.add(new Effect("Hide", nodes.get(nodeCounter), 0));

                    while (!line.equals("")) {

                        if (line.equals("effect")) {
                            effects.add(ShapeUtils.determineEffect(reader, line, nodes.get(nodeCounter)));
                        }
                        line = reader.readLine();

                    }

                } else if (line.equals("Line")) {

                    nodes.add(LineUtils.create(reader, line));
                    nodeCounter++;
                    effects.add(new Effect("Hide", nodes.get(nodeCounter), 0));

                    while (!line.equals("")) {

                        if (line.equals("effect")) {
                            effects.add(ShapeUtils.determineEffect(reader, line, nodes.get(nodeCounter)));
                        }
                        line = reader.readLine();

                    }

                }

                line = reader.readLine();

            }

            reader.close();

        } catch (IOException e) {
            
            System.out.println("No File found");

        }

    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {

        loadAnimationFromFile("animation.txt");

        Group root = new Group();
        for (Node node : nodes) {
            root.getChildren().add(node);
        }

        Text text = new Text(265, 290, "ANIMATION COMPLETE");
        root.getChildren().add(text);
        effects.add(new Effect("Hide", text, 0));
        effects.add(new Effect("Show", text, frames));

        AnimationTimer timer = new Timer(frames, speed, effects);
        timer.start();

        Scene scene = new Scene(root, 400, 300, Color.WHITESMOKE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Animation Player");

        primaryStage.show();

    }

    public static void main(String args[]) {

        launch(args);

    }

}

/*  NOTE: ANIMATION FILE MUST BE MODIFIED

    --- GOOD -------
    
    Circle
    r: 10
    x: 50
    y: 50
    color: 255, 0, 0
                        <--- space between parameters and effects
    effect
    Show
    start: 10
    effect
    Hide
    start: 120
    
    ----------------

    --- BAD --------

    Circle
    r: 10
    x: 50
    y: 50
    color: 255, 0, 0
    effect
    Show
    start: 10
    effect
    Hide
    start: 120

    ----------------

*/

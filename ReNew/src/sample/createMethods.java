package sample;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class createMethods {

    public static Label createLabel (String text, int size, Color textcolor, int x, int y, String css) {

        Label label = new Label(text);
        label.setFont(Font.font((Main.class.getResource("fonts/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm()), size));
        label.setTextFill(textcolor);
        label.setTranslateX(x);
        label.setTranslateY(y);
        label.setStyle(css);

        return label;
    }

    public static SplitMenuButton createSplitMenuButton (String text, int width, int height, int size, int x, int y) {

        SplitMenuButton splitMenuButton = new SplitMenuButton();

        splitMenuButton.setText(text);
        splitMenuButton.setStyle("-fx-effect: innershadow(gaussian, blue, 15, 0, 0, 0);");
        splitMenuButton.setMnemonicParsing(true);
        splitMenuButton.setPrefSize(width,height);
        splitMenuButton.setFont(Font.font(Main.class.getResource("fonts/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), size));
        splitMenuButton.setTranslateX(x);
        splitMenuButton.setTranslateY(y);

        return splitMenuButton;
    }

    public static TextField createTextField (int maxchar, int width, int height, int size, int x, int y) {

        TextField textField = new TextField();
        textField.setPrefSize(width, height);
        textField.setFont(Font.font(Main.class.getResource("fonts/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), size));
        textField.setTranslateX(x);
        textField.setTranslateY(y);

        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (textField.getText().length() > maxchar) {
                    String s = textField.getText().substring(0, maxchar);
                    textField.setText(s);
                }
            }
        });

        return textField;
    }

    public static void createTranslateTransition (int duration, Node node, int x, int y, int count) {

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(duration));
        translateTransition.setNode(node);
        translateTransition.setByX(x);
        translateTransition.setByY(y);
        translateTransition.setCycleCount(count);
        translateTransition.setAutoReverse(false);
        translateTransition.play();

    }

    public static MenuItem createMenuItem(String text, SplitMenuButton menuButton){

        MenuItem menuItem = new MenuItem();
        Label label = new Label();

        label.prefWidthProperty().bind(menuButton.widthProperty());
        label.setText(text);
        menuItem.setGraphic(label);

        return menuItem;
    }

    public static ProgressBar createProgressbar (int progress, double value, int x, int y, double scaleX, double scaleY, String style, Color effect) {

        ProgressBar bar = new ProgressBar(progress);
        bar.setProgress(value);
        bar.setTranslateX(x);
        bar.setTranslateY(y);
        bar.setScaleX(scaleX);
        bar.setScaleY(scaleY);
        bar.setStyle(style);
        bar.setEffect(new InnerShadow(50, effect));

        return bar;
    }

    public static Rectangle createRectangle (Color stroke, int strokewidth, Paint fill, double height, double width, int x, int y, String css, double archeight, double arcwidth) {

        Rectangle rectangle = new Rectangle();
        rectangle.setStroke(stroke);
        rectangle.setStrokeWidth(strokewidth);
        rectangle.setFill(fill);
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setStyle(css);
        rectangle.setArcHeight(archeight);
        rectangle.setArcWidth(arcwidth);

        return rectangle;
    }

    public static ImageView createImageView(String path, double width, double height, int x, int y, String css) {

        ImageView view = new ImageView(new Image(Main.class.getResource(path).toExternalForm()));
        view.setFitWidth(width);
        view.setFitHeight(height);
        view.setStyle(css);
        view.setX(x);
        view.setY(y);

        return view;
    }

    public static void createScaleTransition (int duration, Node node, double x, double y, int cyclecount) {

        ScaleTransition st = new ScaleTransition(Duration.millis(duration), node);
        st.setByX(x);
        st.setByY(y);
        st.setCycleCount(cyclecount);
        st.play();

    }

    public static Button createButton (String text, double size, Color textfill, int x, int y, String css, Node graphic) {

        Button button = new Button(text);
        button.setFont(Font.font(Main.class.getResource("fonts/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), size));
        button.setTextFill(textfill);
        button.setTranslateX(x);
        button.setTranslateY(y);
        button.setStyle(css);
        button.setGraphic(graphic);
        return button;
    }

}

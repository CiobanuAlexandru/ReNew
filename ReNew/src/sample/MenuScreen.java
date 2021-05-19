package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuScreen {

    public static Pane root = new Pane();
    public static String username, emailaddress, key;
    public static byte[] encrypted;
    public static FileMethods file;
    public static FileMethods currentacc;

    protected static void addBackground(boolean animation) {

        ImageView picture = createMethods.createImageView("images/mainpictures/icon.png",426,426,430,-350, "-fx-effect: dropshadow(gaussian, purple, 200, 0, 0, 0);");
        Button login = createMethods.createButton("Log in", 50.0, Color.BLACK, 500, 400, "-fx-effect: innershadow(gaussian, blue, 100, 0, 0, 0);-fx-background-radius: 15px;", createMethods.createImageView("images/icon/login.png",70,70,0,0, null));
        Button signup = createMethods.createButton("Sign up", 50.0, Color.BLACK, 485, 510, "-fx-effect: innershadow(gaussian, blue, 100, 0, 0, 0);-fx-background-radius: 15px;", createMethods.createImageView("images/icon/signin.png",70,70,0,0, null));
        Button aboutus = createMethods.createButton("About us", 30.0, Color.BLACK, 535, 620, "-fx-effect: innershadow(gaussian, blue, 100, 0, 0, 0);-fx-background-radius: 15px;", createMethods.createImageView("images/icon/aboutus.png",50,50,0,0, null));

        if (animation) {
            createMethods.createTranslateTransition(2000, picture, 0, 400, 1);
            createMethods.createTranslateTransition(2000, login, 0, 0, 1);
            createMethods.createTranslateTransition(2000, signup, 0, 0, 1);
            createMethods.createTranslateTransition(2000, aboutus, 0, 0, 1);

        } else {

            picture.setY(200);
            login.setTranslateX(200);
            signup.setTranslateX(800);
            aboutus.setTranslateY(640);
        }

        signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().clear();
                addBackground(false);
                signup.setOnAction(null);
                register();

            }
        });

        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().clear();
                addBackground(false);
                login.setOnAction(null);
                connect();

            }
        });

        aboutus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    FileMethods.openHTMLFile("src\\sample\\files\\html\\Asimcoin.html");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        root.getChildren().addAll(picture, login, signup, aboutus);
    }

    private static void connect () {

        Label header = createMethods.createLabel("Login", 60, Color.YELLOW, 550,-50,"-fx-effect: dropshadow(gaussian, gold, 200, 0, 0, 0);");
        Label keylabel = createMethods.createLabel("Enter your login/access key:", 50, Color.BLACK, 250,-60,null);
        TextField keyfield = createMethods.createTextField(40, 790, 20, 30, 250, -200);
        Button submit = createMethods.createButton("Submit", 40.0, Color.BLACK, 580, -430, "-fx-effect: innershadow(gaussian, blue, 100, 0, 0, 0);-fx-background-radius: 15px;", createMethods.createImageView("images/icon/submit.png",30,40,0,0, null));
        Button close = createMethods.createButton("X", 10.0, Color.DARKRED, 1040, -60, "-fx-effect: innershadow(gaussian, darkgray, 20, 0, 0, 0);-fx-background-radius: 15px;", null);

        createMethods.createTranslateTransition(1500, header,0,100,1);
        createMethods.createTranslateTransition(1500, keyfield,0,400,1);
        createMethods.createTranslateTransition(1500, keylabel,0,180,1);
        createMethods.createTranslateTransition(1500, submit,0,860,1);
        createMethods.createTranslateTransition(1500, close,0,120,1);

        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().clear();
                addBackground(false);
            }
        });

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label information;

                if (FileMethods.countFile("src/sample/files/login") == 0) {

                     information = createMethods.createLabel("You are not registered", 50, Color.RED, 370, 270, null);

                     root.getChildren().add(information);

                }

                for (int i = 0; i < FileMethods.countFile("src/sample/files/login"); i++) {

                    if (FileMethods.readFile("src/sample/files/login", "/log", i).contains(keyfield.getText())) {

                        currentacc = new FileMethods("src/sample/files/login","/log",i);
                        key = keyfield.getText();
                        try {
                            username = currentacc.readspecificline(1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        keylabel.setText("Welcome "+username);
                        information = createMethods.createLabel("Welcome ", 50, Color.RED, 370, 270, null);
                        root.getChildren().add(information);
                        root.getChildren().removeAll(keyfield);


                    }
                }
            }
        });

        root.getChildren().addAll(header,keyfield,keylabel,submit,close);
    }

    private static void register() {
        Label header = createMethods.createLabel("Registration", 60, Color.YELLOW, 500,-50,"-fx-effect: dropshadow(gaussian, gold, 200, 0, 0, 0);");
        Label namelabel = createMethods.createLabel("Enter your username:", 50, Color.BLACK, 250,-60,null);
        TextField namefield = createMethods.createTextField(10, 790, 20, 30, 250, -200);
        Label emaillabel = createMethods.createLabel("Enter your e-mail adress:", 50, Color.BLACK, 250, -270, null);
        TextField emailfield = createMethods.createTextField(40, 790, 20, 30, 250, -350);
        Button submit = createMethods.createButton("Submit", 40.0, Color.BLACK, 580, -430, "-fx-effect: innershadow(gaussian, blue, 100, 0, 0, 0);-fx-background-radius: 15px;", createMethods.createImageView("images/icon/submit.png",30,40,0,0, null));
        Button close = createMethods.createButton("X", 10.0, Color.DARKRED, 1040, -60, "-fx-effect: innershadow(gaussian, darkgray, 20, 0, 0, 0);-fx-background-radius: 15px;", null);

        createMethods.createTranslateTransition(1500, header,0,100,1);
        createMethods.createTranslateTransition(1500, namelabel,0,180,1);
        createMethods.createTranslateTransition(1500, namefield,0,400,1);
        createMethods.createTranslateTransition(1500, emaillabel,0,540,1);
        createMethods.createTranslateTransition(1500, emailfield,0,700,1);
        createMethods.createTranslateTransition(1500, submit,0,860,1);
        createMethods.createTranslateTransition(1500, close,0,120,1);


        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().clear();
                addBackground(false);
            }
        });

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int count = FileMethods.countFile("src/sample/files/login");

                if (namefield.getText() != null && emaillabel.getText() != null) {

                    if (checkValidUsername(namefield.getText()) && checkValidEmailAdress(emailfield.getText())) {

                        for (int i = 0; i < FileMethods.countFile("src/sample/files/login"); i++) {

                            if (FileMethods.readFile("src/sample/files/login", "/log", i).contains(namefield.getText()) || FileMethods.readFile("src/sample/files/login", "/log", i).contains(emailfield.getText())) {
                                count--;
                                System.out.println(count);
                            }
                        }

                        if (count == FileMethods.countFile("src/sample/files/login")) {
                            username = namefield.getText();
                            emailaddress = emailfield.getText();

                            namelabel.setText("Username: \n" + username);
                            namelabel.setTextFill(Color.DARKCYAN);
                            emaillabel.setText("Email address: \n" + emailaddress + "\nPlease check your email!");
                            emaillabel.setTextFill(Color.DARKBLUE);

                            sendEmails();

                            file = new FileMethods("src/sample/files/login", "/log");
                            file.writeFile("Hey, you're not supposed to open that file. Please go away\n" + username + "\n" + emailaddress+"\n"+key);
                            file.setHiddenAttrib();

                            root.getChildren().removeAll(submit, namefield, emailfield);
                        }
                    }
                }
            }
        });

        root.getChildren().addAll(header,namelabel, namefield, emaillabel, emailfield, submit,close);
    }

    private static void sendEmails () {

        byte[] myData = emailaddress.getBytes();
        byte[] myKey = new byte[] {(byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A, (byte)0x0A};
        byte[] myIV = new byte[] {(byte)0x0B, (byte)0x0B, (byte)0x0B, (byte)0x0B, (byte)0x0B, (byte)0x0B, (byte)0x0B, (byte)0x0B};

        Crypto3DES crypto = new Crypto3DES(myKey, myIV);
        encrypted = crypto.encrypt(myData);

        key = HexUtil.bytesToHex(encrypted);

    }

    private static boolean checkValidEmailAdress(String email) {

        EmailValidator validator = EmailValidator.getInstance();

        return validator.isValid(email);
    }

    private static boolean checkValidUsername(String username) {

        String regex = "^[A-Za-z]\\w{5,29}$";

        Pattern p = Pattern.compile(regex);

        if (username == null) {
            return false;
        }

        Matcher m = p.matcher(username);

        return m.matches();
    }

    public static Parent createContent() {

        addBackground(true);

        return root;
    }

}

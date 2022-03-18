

    import javafx.application.Application;
    import javafx.event.ActionEvent;
    import javafx.event.EventHandler;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.layout.StackPane;
    import javafx.stage.Stage;

    /**
     *
     * @author DoAa
     */

    public class NotePadApp extends Application {

        @Override
        public void start(Stage primaryStage) {

            NotePadBase root = new NotePadBase(primaryStage);
            Scene scene;
            scene = new Scene(root, 300, 250);
            primaryStage.setTitle("NotePad Application GUI");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) {
            launch(args);
        }

    }



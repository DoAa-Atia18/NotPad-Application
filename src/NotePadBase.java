import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.control.IndexRange;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class NotePadBase extends BorderPane {

    final boolean flag;
    public NotePadBase(Stage primary) {

        final MenuBar menuBar;
        menuBar = new MenuBar();

        final Menu fileMenu;
        fileMenu = new Menu();

        final MenuItem newItem;
        newItem = new MenuItem();

        final MenuItem openItem;
        openItem = new MenuItem();

        final MenuItem saveItem;
        saveItem = new MenuItem();

        final MenuItem exitItem;
        exitItem = new MenuItem();

        final Menu editMenu;
        editMenu = new Menu();

        final MenuItem cutItem;
        cutItem = new MenuItem();

        final MenuItem copyItem;
        copyItem = new MenuItem();

        final MenuItem pasteItem;
        pasteItem = new MenuItem();

        final MenuItem deleteItem;
        deleteItem = new MenuItem();

        final MenuItem selectAllItem;
        selectAllItem = new MenuItem();

        final Menu helpMenu;
        helpMenu = new Menu();

        final MenuItem aboutItem;
        aboutItem = new MenuItem();

        final TextArea textArea;
        textArea = new TextArea();

        flag =  true;
        setTop(menuBar);

        BorderPane.setAlignment(textArea, javafx.geometry.Pos.CENTER);

        textArea.setPrefHeight(200.0);
        textArea.setPrefWidth(200.0);
        setCenter(textArea);
        fileMenu.getItems().add(newItem);
        fileMenu.getItems().add(openItem);
        fileMenu.getItems().add(saveItem);
        fileMenu.getItems().add(exitItem);
        menuBar.getMenus().add(fileMenu);
        editMenu.getItems().add(cutItem);
        editMenu.getItems().add(copyItem);
        editMenu.getItems().add(pasteItem);
        editMenu.getItems().add(deleteItem);
        editMenu.getItems().add(selectAllItem);
        menuBar.getMenus().add(editMenu);
        helpMenu.getItems().add(aboutItem);
        menuBar.getMenus().add(helpMenu);

//        setMaxHeight(USE_PREF_SIZE);
//        setMaxWidth(USE_PREF_SIZE);
//        setMinHeight(USE_PREF_SIZE);
//        setMinWidth(USE_PREF_SIZE);
//        setPrefHeight(400.0);
//        setPrefWidth(600.0);

        BorderPane.setAlignment(menuBar, javafx.geometry.Pos.CENTER);

        fileMenu.setMnemonicParsing(false);
        fileMenu.setText("File");

/// New File part
        newItem.setMnemonicParsing(false);
        newItem.setText("New");
        newItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        newItem.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent ev) {
                                    if(flag){
                                        Alert alert = new Alert(AlertType.CONFIRMATION);
                                        alert.setTitle("Delete File");
                                        alert.setHeaderText("Are you sure want to close this file ?");
//                                        alert.setContentText("C:/MyFile.txt");
//
//                                        option != null.
                                        Optional<ButtonType> option = alert.showAndWait();

                                        if (option.get() == ButtonType.OK) {
                                            textArea.setText("");
                                        }}
//                                    else
//                                        textArea.setText("");
//                                    //flag = true;
                                }
                            }
        );
/// /////////////////////////////////////////////////////////////////////////////////////////////////////////
 /// Open File
        openItem.setMnemonicParsing(false);
        openItem.setText("Open");
        openItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        openItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent ev) {
                        FileChooser fch = new FileChooser();
                        File file = fch.showOpenDialog(primary);
                        if (file != null) {
                            FileInputStream fs;
                            try {
                                fs = new FileInputStream(file);
                                int size = (int) file.length();
                                byte[] data = new byte[size];
                                fs.read(data);
                                textArea.setText(new String(data));
                            } catch (IOException ex) {
                                Logger.getLogger(NotePadBase.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
        );


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Save File
        saveItem.setMnemonicParsing(false);
        saveItem.setText("Save");
        saveItem.setAccelerator(
                new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        saveItem.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent ev) {
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setTitle("Save");
                        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
                        File file = fileChooser.showSaveDialog(primary);
                        if(file != null)
                        {
                            try {
                                PrintWriter print = new PrintWriter(file);
                                print.println(textArea.getText());
                                print.close();
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(NotePadBase.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                        //flag = false;
                    }
                }
        );

 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
 /// Exit File

        exitItem.setMnemonicParsing(false);
        exitItem.setText("Exit");
        exitItem.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
        exitItem.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent ev) {
                        Platform.exit();
                    }

                });

 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 /// Edit text
        Clipboard systemClipboard = Clipboard.getSystemClipboard();
        editMenu.setMnemonicParsing(false);
        editMenu.setText("Edit");

/// Cut text
        cutItem.setMnemonicParsing(false);
        cutItem.setText("Cut");
        cutItem.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev) {
                try{
                    String text = textArea.getSelectedText();
                    ClipboardContent content = new ClipboardContent();
                    content.putString(text);
                    systemClipboard.setContent(content);
                    textArea.setText(textArea.getText().replace(textArea.getSelectedText(),""));
                   }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
/// Copy text
        copyItem.setMnemonicParsing(false);
        copyItem.setText("Copy");
        copyItem.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev) {

                String text = textArea.getSelectedText();
                ClipboardContent content = new ClipboardContent();
                content.putString(text);
                systemClipboard.setContent(content);
            }
        });
/// Past text
        pasteItem.setMnemonicParsing(false);
        pasteItem.setText("Paste");
        pasteItem.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev) {
                String clipboardText = systemClipboard.getString();
                int caretPosition = textArea.getCaretPosition();
                textArea.insertText(caretPosition, clipboardText);

            }
        });

/// Delete
        deleteItem.setMnemonicParsing(false);
        deleteItem.setText("Delete");
        deleteItem.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev) {
                try{
                    textArea.setText(textArea.getText().replace(textArea.getSelectedText(),""));}
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
/// Select All
        selectAllItem.setMnemonicParsing(false);
        selectAllItem.setText("SelectAll");
        selectAllItem.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev) {
                if (!textArea.getText().isEmpty()) {
                    textArea.selectAll();
                }
            }
        });

/// Help and about dialog
        helpMenu.setMnemonicParsing(false);
        helpMenu.setText("Help");
        aboutItem.setMnemonicParsing(false);
        aboutItem.setText("About");
        aboutItem.setOnAction(new EventHandler<ActionEvent>() {

                                  @Override
                                  public void handle(ActionEvent ev) {
                                      try{
                                          Alert alert = new Alert(AlertType.INFORMATION);
                                          alert.setTitle("My Name is DoAa Atia Ahmed ");
                                       //   alert.setHeaderText("Look, an Information Dialog");
                                          alert.setContentText("This is my First Notepad App with JavaFX, " +
                                                                "  " +
                                                                " I hope you will Enjoy with it");
                                          alert.showAndWait();
//
                                          Image image = new Image(new FileInputStream("B:\\Users\\DoAa\\IdeaProjects\\test72\\javalogo.PNG"));
                                          ImageView imageView = new ImageView(image);
                                          alert.setGraphic(imageView);
                                          alert.showAndWait();}
                                      catch (IllegalArgumentException e) {
                                          e.printStackTrace();
                                      }
                                      catch (NullPointerException e) {
                                          e.printStackTrace();
                                      }
                                      catch (FileNotFoundException ex) {
                                          Logger.getLogger(NotePadBase.class.getName()).log(Level.SEVERE, null, ex);
                                      }


                                  }
                              }
        );

    }
}



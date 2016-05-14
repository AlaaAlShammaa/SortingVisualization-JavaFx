
/*
 * ******************************************************
 *  * Copyright (C) 2016. Alaa Alshammaa <shammaaalalaa@gmail.com>
 *  *
 *  * This file is part of Sorting Application.
 *  *
 *  * Sorting Application can not be copied and/or distributed without the express
 *  * permission of Alaa Alshammaa
 *  ******************************************************
 */

package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Slider;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class Main extends Application {
    double speed = 400;
    int selectedAlgo = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        HBox hBox = new HBox(20);

        hBox.setAlignment(Pos.CENTER);
        ArrayList<StackPane> list = new ArrayList<>();
        Random random = new Random(5);
        for (int i = 0; i < 12; i++) {
            int num = random.nextInt(10);
            Rectangle rectangle = new Rectangle(40, (num * 10) + 50);
            rectangle.setFill(Color.valueOf("#ADD8E6"));
            Text text = new Text(String.valueOf(num));
            StackPane stackPane = new StackPane();
            stackPane.setPrefSize(rectangle.getWidth(), rectangle.getHeight());
            stackPane.setId(String.valueOf(num));
            stackPane.getChildren().addAll(rectangle, text);
            //StackPane.setAlignment(text,Pos.BOTTOM_CENTER);
            list.add(stackPane);
        }
        hBox.getChildren().addAll(list);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(hBox);
        HBox hBox1 = new HBox(20);
        Button b = new Button("Sort");
        Button bb = new Button("Generate");
        Text slow = new Text("Slow");
        Text fast = new Text("Fast");
        Bloom bloom = new Bloom();
        HBox slideBox = new HBox(5);
        Slider slider = new Slider(100, 4000, 400);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                speed = (double) newValue;
            }
        });
        slideBox.getChildren().addAll(fast, slider, slow);
        AnchorPane bottomPane = new AnchorPane();
        Text about = new Text("About");
        about.setStyle("-fx-font: 15px Tahoma;\n" +
                "    -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);\n" +
                "    -fx-stroke: grey;\n" +
                "    -fx-stroke-width: 1;");
        about.setLayoutX(750);
        about.setLayoutY(30);
        about.setOnMouseClicked(event ->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Developed with <3 by Alaa Alshammaa");
            alert.setContentText("PR2 Project | Yarmouk Private University ");
            alert.setTitle("Thanks :3");
            alert.initModality(Modality.APPLICATION_MODAL);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("logo.jpg")));
            alert.show();
        });

        hBox1.getChildren().addAll(slideBox, b, bb);
        hBox1.setPadding(new Insets(20, 20, 20, 20));
        bottomPane.getChildren().addAll(hBox1, about);
        borderPane.setBottom(bottomPane);
        HBox topBox = new HBox(40);
        topBox.setAlignment(Pos.BASELINE_CENTER);
        Text bubbleSort = new Text("Bubble Sort");
        Text SelectionSort = new Text("Selection Sort");
        Text InsertionSort = new Text("Insertion Sort");
        Text MergeSort = new Text("Merge Sort");
        Text QuickSort = new Text("Quick Sort");
        topBox.getChildren().addAll(bubbleSort, SelectionSort, InsertionSort, MergeSort, QuickSort);
        topBoxStyle(topBox);
        borderPane.setTop(topBox);
        bubbleSort.setOnMouseClicked(event -> {
            topBoxStyle(topBox);
            selectedAlgo = 0;
            bubbleSort.setStyle("-fx-underline:true; \n"+"-fx-font: 20px Tahoma;\n" +
                    "    -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);\n" +
                    "    -fx-stroke: grey;\n" +
                    "    -fx-stroke-width: 1;");
        });
        SelectionSort.setOnMouseClicked(event -> {
            topBoxStyle(topBox);
            selectedAlgo = 1;
            SelectionSort.setStyle("-fx-underline:true; \n"+"-fx-font: 20px Tahoma;\n" +
                    "    -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);\n" +
                    "    -fx-stroke: grey;\n" +
                    "    -fx-stroke-width: 1;");
        });
        InsertionSort.setOnMouseClicked(event -> {
            topBoxStyle(topBox);
            selectedAlgo = 2;
            InsertionSort.setStyle("-fx-underline:true; \n"+"-fx-font: 20px Tahoma;\n" +
                    "    -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);\n" +
                    "    -fx-stroke: grey;\n" +
                    "    -fx-stroke-width: 1;");
        });
        MergeSort.setOnMouseClicked(event -> {
            topBoxStyle(topBox);
            selectedAlgo = 3;
            MergeSort.setStyle("-fx-underline:true; \n"+"-fx-font: 20px Tahoma;\n" +
                    "    -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);\n" +
                    "    -fx-stroke: grey;\n" +
                    "    -fx-stroke-width: 1;");
        });
        QuickSort.setOnMouseClicked(event -> {
            topBoxStyle(topBox);
            selectedAlgo = 4;
            QuickSort.setStyle("-fx-underline:true; \n"+"-fx-font: 20px Tahoma;\n" +
                    "    -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);\n" +
                    "    -fx-stroke: grey;\n" +
                    "    -fx-stroke-width: 1;");
        });
        b.setOnAction(event -> {
            SequentialTransition sq = new SequentialTransition();
            int[] arr;
            switch (selectedAlgo) {
                case 0:
                    arr = generateArray(list);
                    sq = BubbleSort(arr, list);
                    break;
                case 1:
                    arr = generateArray(list);
                    sq = SelectionSort(arr, list);
                    break;
                case 2:
                    arr = generateArray(list);
                    sq = insertionSort(arr, list);
                    break;
                case 3:
                    arr = generateArray(list);
                    //sq = MergeSort(arr, list);
                    break;
                case 4:
                    arr = generateArray(list);
                    // this doesn't look right
                    sq = quickSort(arr, 0, arr.length - 1, list, sq);
                    break;
                default:
                    break;
            }
            b.setDisable(true);
            slider.setDisable(true);
            sq.play();
            sq.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    b.setDisable(false);
                }
            });
            b.setDisable(false);
            slider.setDisable(false);
        });
        bb.setOnAction(event -> {
            hBox.getChildren().clear();
            list.clear();
            for (int i = 0; i < 12; i++) {
                int num = random.nextInt(10);
                Rectangle rectangle = new Rectangle(40, (num * 10) + 50);
                rectangle.setFill(Color.valueOf("#ADD8E6"));
                Text text = new Text(String.valueOf(num));
                StackPane stackPane = new StackPane();
                stackPane.setPrefSize(rectangle.getWidth(), rectangle.getHeight());
                stackPane.setId(String.valueOf(num));
                stackPane.getChildren().addAll(rectangle, text);
                //StackPane.setAlignment(text,Pos.BOTTOM_CENTER);
                list.add(stackPane);
            }
            hBox.getChildren().addAll(list);
        });
        Scene scene = new Scene(borderPane, 800, 400);
        primaryStage.setTitle("Sorting");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.jpg")));
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void topBoxStyle(HBox topBox) {
        for (int i = 0; i < 5; i++) {
            topBox.getChildren().get(i).setStyle("-fx-font: 20px Tahoma;\n" +
                    "    -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);\n" +
                    "    -fx-stroke: grey;\n" +
                    "    -fx-stroke-width: 1;");
        }
    }


    private ParallelTransition swapMe(StackPane l1, StackPane l2, ArrayList<StackPane> list, double speed) {
        TranslateTransition t1 = new TranslateTransition();
        TranslateTransition t2 = new TranslateTransition();
        t1.setDuration(Duration.millis(speed));
        t2.setDuration(Duration.millis(speed));
        ParallelTransition pl = new ParallelTransition();
        t1.setNode(l1);
        t2.setNode(l2);
        t1.setByX(60);
        t2.setByX(-60);
        pl.getChildren().addAll(t1, t2);
        Collections.swap(list, list.indexOf(l1), list.indexOf(l2));
        return pl;
    }

    private ParallelTransition swapSelect(StackPane l1, StackPane l2, ArrayList<StackPane> list, double speed) {
        int num = 1;
        StackPane sp1 = null, sp2 = null, fSp = null;
        TranslateTransition t1 = new TranslateTransition();
        TranslateTransition t2 = new TranslateTransition();
        ParallelTransition pl = new ParallelTransition();
        t1.setNode(l1);
        t2.setNode(l2);
        t1.setDuration(Duration.millis(speed));
        t2.setDuration(Duration.millis(speed));
        boolean outerBreak = false;
        for (int i = 0; i < list.size(); i++) {
            if (outerBreak) break;
            if (list.get(i) == l1 || list.get(i) == l2) {
                fSp = list.get(i);
                for (int j = list.indexOf(fSp) + 1; j < list.size(); j++) {
                    if ((list.get(j) == l1 && list.get(j) != fSp) || (list.get(j) == l2 && list.get(j) != fSp)) {
                        outerBreak = true;
                        num = j - i;
                        break;
                    }
                }
            }
        }
        num *= 60;
        t1.setByX(num);
        t2.setByX(-num);
        Collections.swap(list, list.indexOf(l1), list.indexOf(l2));
        pl.getChildren().addAll(t1, t2);
        return pl;
    }

    private SequentialTransition BubbleSort(int arr[], ArrayList<StackPane> list) {
        SequentialTransition sq = new SequentialTransition();
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1] < arr[j]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    sq.getChildren().add(swapMe(list.get(j - 1), list.get(j), list, speed));
                }
            }
        }
        return sq;
    }

    private SequentialTransition SelectionSort(int arr[], ArrayList<StackPane> list) {
        SequentialTransition sq = new SequentialTransition();
        int i, j, minIndex, tmp;
        int n = arr.length;
        for (i = 0; i < n - 1; i++) {
            minIndex = i;
            for (j = i + 1; j < n; j++)
                if (arr[j] < arr[minIndex])
                    minIndex = j;
            if (minIndex != i) {
                tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
                sq.getChildren().add(swapSelect(list.get(i), list.get(minIndex), list, speed));
            }
        }
        return sq;
    }

    private int[] generateArray(List<StackPane> list) {
        int arr[] = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(list.get(i).getId());
        }
        return arr;
    }

    private SequentialTransition insertionSort(int[] arr, ArrayList<StackPane> list) {
        SequentialTransition sq = new SequentialTransition();
        int temp;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    sq.getChildren().add(swapMe(list.get(j - 1), list.get(j), list, speed));
                } else {
                    break;
                }
            }
        }
        return sq;
    }

    int partition(int arr[], int left, int right, ArrayList<StackPane> list, SequentialTransition sq) {
        int i = left, j = right;
        int tmp;
        int pivot = arr[(left + right) / 2];

        while (i <= j) {
            while (arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;
            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                sq.getChildren().add(swapSelect(list.get(i), list.get(j), list, speed));
                i++;
                j--;
            }
        }

        return i;
    }

    SequentialTransition quickSort(int arr[], int left, int right, ArrayList<StackPane> list, SequentialTransition sq) {
        int index = partition(arr, left, right, list, sq);
        if (left < index - 1)
            quickSort(arr, left, index - 1, list, sq);
        if (index < right)
            quickSort(arr, index, right, list, sq);
        return sq;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

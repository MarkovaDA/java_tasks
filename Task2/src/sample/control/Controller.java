package sample.control;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import sample.model.Model;

import javax.swing.*;
import java.awt.*;

public class Controller {
    public Button btn00;
    public Button btn01;
    public Button btn02;
    public Button btn10;
    public Button btn11;
    public Button btn12;
    public Button btn20;
    public Button btn21;
    public Button btn22;

    private int[][] matrix;
    private Model model;

    public Controller()
    {
        matrix = new int[3][3];
        model = new Model();
    }
    private String last_symbol = "x";

    //это все сделать методами модели, а контролер только связывать с моделью, вызывая эти методы
    public void setSymbol(ActionEvent actionEvent) {
       //переписать метод покороче, и сделать так,чтобы нельз было писать там, где уже написано
        Object[] arr =   btn00.getParent().getChildrenUnmodifiable().toArray(); //получили все кнопки на форме
        for(int i=0; i< arr.length; i++) {
            Button cur_btn = (Button) arr[i]; //текущая кнопка
            if (actionEvent.getSource().equals(cur_btn) && cur_btn.getText() == "") {
                cur_btn.setText(last_symbol);
                forward(cur_btn);
                changeLast_symbol();
                int index_row = model.sameIntoRow(matrix);
                int index_column = model.sameIntoColumn(matrix);
                int index_diag = model.sameIntoDiag(matrix);
                if (index_row > -1) //есть победитель в строке
                {
                   reDrawRow(index_row);
                }
                if (index_column > -1)//есть победитель в столбце
                {
                    reDrawColumn(index_column);
                }
                if (index_diag > -1)
                    reDrawDiagonal(index_diag);
            }
        }
    }
    private void reDrawRow(int index)
    {
        Object[] arr =   btn00.getParent().getChildrenUnmodifiable().toArray();
        for(int i=0; i< arr.length; i++){
            Button btn = (Button) arr[i];
            String input = btn.getId().substring(3);
            int num_row = Integer.parseInt(input.substring(0, 1));
            if (num_row == index)
                btn.setStyle("-fx-background-color: red; -fx-text-fill: gold;");
        }
    }
    private void reDrawColumn(int index)
    {
        Object[] arr =   btn00.getParent().getChildrenUnmodifiable().toArray();
        for(int i=0; i< arr.length; i++){
            Button btn = (Button) arr[i];
            String input = btn.getId().substring(3);
            int num_col = Integer.parseInt(input.substring(1));
            if (num_col == index)
                btn.setStyle("-fx-background-color: red; -fx-text-fill: gold;");
        }
    }
    private  void reDrawDiagonal(int index)
    {
        Object[] arr =   btn00.getParent().getChildrenUnmodifiable().toArray();
        if (index == 0) {
            for (int i = 0; i < arr.length; i++) {
                Button btn = (Button) arr[i];
                String input = btn.getId().substring(3);
                int num_col = Integer.parseInt(input.substring(1));
                int num_row = Integer.parseInt(input.substring(0, 1));

                if (num_col == index && num_row == index) {
                    btn.setStyle("-fx-background-color: red; -fx-text-fill: gold;");
                    index++;
                }
            }
        }
        else if (index==2)
        {   for (int i = 0; i < arr.length; i++) {
            Button btn = (Button) arr[i];
            String input = btn.getId().substring(3);
            int num_col = Integer.parseInt(input.substring(1));
            int num_row = Integer.parseInt(input.substring(0, 1));
            //2,0; 1,1; 0;2
            if ((num_col == 2 && num_row == 0) || (num_col == 1 && num_row == 1)|| (num_col == 0 && num_row == 2)) {
                btn.setStyle("-fx-background-color: red; -fx-text-fill: gold;");
            }
            }
        }
    }
    //этот метод также можно перенести в Model
    private  void  forward(Button btn)
    {   String input = btn.getId().substring(3);
        int num_row = Integer.parseInt(input.substring(0, 1));
        int num_col = Integer.parseInt(input.substring(1));
        if (last_symbol=="x")
        {
            matrix[num_row][num_col] = 2;
        }
        else
        {
            matrix[num_row][num_col] = 1;
        }

    }
    //и этот метод
    private void changeLast_symbol()
    {
        if (last_symbol=="x") last_symbol = "o";
        else last_symbol = "x";
    }
    public void clearField(ActionEvent actionEvent)
    {
        Object[] arr =   btn00.getParent().getChildrenUnmodifiable().toArray(); //получили все кнопки на форме
        for(int i=0; i< arr.length;i++)
        {
            Button cur_btn = (Button) arr[i];
            if (cur_btn.getId() != "btnClear") {
                cur_btn.setText("");
                cur_btn.setStyle("-fx-background-color: wheat; -fx-text-fill: black;");
                matrix = new int[3][3];
            }
        }
        last_symbol = "x";
    }
}

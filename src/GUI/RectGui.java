/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Backend.Square;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class RectGui extends Label{
//    Item piece;
//    boolean _occupied;
    Square square;
    String imagePath;
    public RectGui(Square sq,char c,double width, double height) {
        square=sq;
        imagePath="";
//        _occupied=false;
        this.setMinWidth(width);
        this.setMaxWidth(width);
        this.setMinHeight(height);
        this.setMaxHeight(height);
        if(c=='w')
        {
        this.setStyle("-fx-border-color:black;-fx-background-color:white;");
        }
        else
        {
        this.setStyle("-fx-border-color:black;-fx-background-color:brown;");

        }
        //piece=null;
//        layout.getChildren().add
        
    }

    /**
     *
     * @param item
     */
    public boolean isOccupied()
    {
        return !(square.getPiece()==null);
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public String getPieceImage()
    {
        return imagePath;
    }
    public void setPieceImage(String path)
    {
        //piece = ite+m;
        imagePath = path;
        if(imagePath!="")
        {
        this.setGraphic(new ImageView(imagePath));
        }
//        this._occupied = true;
    }
    public void resetPieceImage()
    {
//        piece =null;
        imagePath=null;
        this.setGraphic(null);
      //  square.getPiece().move(square.getPiece().legalMoves(), square);
//        this.occupied = false;

    }
    
    
}

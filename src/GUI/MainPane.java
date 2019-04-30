package GUI;

import Backend.*;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.GridPane.REMAINING;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

class MainPane extends BorderPane
{

    CenterPane CP;
    LeftPane LP;
    RightPane RP;
    TopPane TP;
    BottomPane BP;

    public MainPane()
    {
        CP = new CenterPane();
        setCenter(CP);
        LP = new LeftPane();
        setLeft(LP);
        RP = new RightPane();
        setRight(RP);
        BP = new BottomPane();
        setBottom(BP);
        TP = new TopPane();
        setTop(TP);
        CP.setAlignment(Pos.CENTER);
    }

    class CenterPane extends GridPane
    {

        Board board = new Board();
        boolean movingMode;
        Square movingSquare;
        char colorPressed;
        boolean wPlayer=true;
        boolean bPlayer=false;
        String FirstClick;

        CenterPane()
        {
            movingSquare = null;
            movingMode = false;
            RectGui[][] WhiteAndBlack = new RectGui[8][8];

            for (int i = 0; i < 8; i++) //for loop to create all squares and allocate them in the gridpane
            {
                for (int j = 0; j < 8; j++)
                {
                    //        WhiteAndBlack[i][j] = new Rectangle(75, 75);
                    //   WhiteAndBlack[i][j] = new RectGui(75,75);
                    if ((i + j) % 2 == 0)
                    {
                        // WhiteAndBlack[i][j].setFill(Color.BROWN);
                        WhiteAndBlack[i][j] = new RectGui(board.BoardSquares[i][j], 'B', 75, 75);
                    }
                    else
                    {
                        // WhiteAndBlack[i][j].setFill(Color.LIGHTBLUE);
                        WhiteAndBlack[i][j] = new RectGui(board.BoardSquares[i][j], 'w', 75, 75);
                    }
                    add(WhiteAndBlack[i][j], j, i);

                }
            }
            Image[] pieces = new Image[12];

            for (int k = 0; k < 8; k++) // import white pawns
            {
                WhiteAndBlack[6][k].setPieceImage("Images/wp.png");
                // add(new ImageView("Images/wp.png"), k, 6);
                // add(new PawnGui(),k,6);
            }
            for (int k = 0; k < 8; k++) // import black pawns
            {
                WhiteAndBlack[1][k].setPieceImage("Images/bp.png");
                // add(new ImageView("Images/bp.png"), k, 1);
                // add(new PawnGui(),k,1);

            }
//                                        add(new ImageView("Images/wr.png"), 0, 7); //create white rook left corner
            WhiteAndBlack[7][0].setPieceImage("Images/wr.png");
//                    add(new ImageView("Images/wh.png"), 1, 7); //create white horse left corner
            WhiteAndBlack[7][1].setPieceImage("Images/wh.png");
//                    add(new ImageView("Images/wb.png"), 2, 7);//create white bishop left corner
            WhiteAndBlack[7][2].setPieceImage("Images/wb.png");
//                    add(new ImageView("Images/wr.png"), 7, 7);//create white rook right corner
            WhiteAndBlack[7][7].setPieceImage("Images/wr.png");
//                    add(new ImageView("Images/wh.png"), 6, 7);//create white horse right corner
            WhiteAndBlack[7][6].setPieceImage("Images/wh.png");
//                    add(new ImageView("Images/wb.png"), 5, 7);//create white bishop right corner
            WhiteAndBlack[7][5].setPieceImage("Images/wb.png");
//                    add(new ImageView("Images/wq.png"), 3, 7);//create white queen
            WhiteAndBlack[7][3].setPieceImage("Images/wq.png");
//                    add(new ImageView("Images/wk.png"), 4, 7);//create white king right corner
            WhiteAndBlack[7][4].setPieceImage("Images/wk.png");

//                    //now black pieces
//                    add(new ImageView("Images/br.png"), 0, 0); //create black rook left corner
            WhiteAndBlack[0][0].setPieceImage("Images/br.png");
//                    add(new ImageView("Images/bh.png"), 1, 0); //create black horse left corner
            WhiteAndBlack[0][1].setPieceImage("Images/bh.png");
//                    add(new ImageView("Images/bb.png"), 2, 0);//create black bishop left corner
            WhiteAndBlack[0][2].setPieceImage("Images/bb.png");
//                    add(new ImageView("Images/br.png"), 7, 0);//create black rook right corner
            WhiteAndBlack[0][7].setPieceImage("Images/br.png");
//                    add(new ImageView("Images/bh.png"), 6, 0);//create black horse right corner
            WhiteAndBlack[0][6].setPieceImage("Images/bh.png");
//                    add(new ImageView("Images/bb.png"), 5, 0);//create black bishop right corner
            WhiteAndBlack[0][5].setPieceImage("Images/bb.png");
//                    add(new ImageView("Images/bq.png"), 3, 0);//create black queen
            WhiteAndBlack[0][3].setPieceImage("Images/bq.png");
//                    add(new ImageView("Images/bk.png"), 4, 0);//create black king right corner
            WhiteAndBlack[0][4].setPieceImage("Images/bk.png");

//                    
//                    add(new ImageView("Images/wr.png"), 0, 7); //create white rook left corner
//                    add(new ImageView("Images/wh.png"), 1, 7); //create white horse left corner
//                    add(new ImageView("Images/wb.png"), 2, 7);//create white bishop left corner
//                    add(new ImageView("Images/wr.png"), 7, 7);//create white rook right corner
//                    add(new ImageView("Images/wh.png"), 6, 7);//create white horse right corner
//                    add(new ImageView("Images/wb.png"), 5, 7);//create white bishop right corner
//                    add(new ImageView("Images/wq.png"), 3, 7);//create white queen
//                    add(new ImageView("Images/wk.png"), 4, 7);//create white king right corner
//                    //now black pieces
//                    add(new ImageView("Images/br.png"), 0, 0); //create black rook left corner
//                    add(new ImageView("Images/bh.png"), 1, 0); //create black horse left corner
//                    add(new ImageView("Images/bb.png"), 2, 0);//create black bishop left corner
//                    add(new ImageView("Images/br.png"), 7, 0);//create black rook right corner
//                    add(new ImageView("Images/bh.png"), 6, 0);//create black horse right corner
//                    add(new ImageView("Images/bb.png"), 5, 0);//create black bishop right corner
//                    add(new ImageView("Images/bq.png"), 3, 0);//create black queen
//                    add(new ImageView("Images/bk.png"), 4, 0);//create black king right corner
            setOnMousePressed((MouseEvent event) ->
            {
                Node clickedNode = event.getPickResult().getIntersectedNode();
                {
                    if (movingMode == true &&((wPlayer== true && colorPressed == 'W')||(bPlayer== true && colorPressed == 'B')))
                    {
                        movingMode = false;
                        Integer colIndex = GridPane.getColumnIndex(clickedNode);
                        Integer rowIndex = GridPane.getRowIndex(clickedNode);
                        Iterator i = movingSquare.getPiece().legalMoves().iterator();
                        boolean legalMove = false;
                        while (i.hasNext())
                        {
//                        int legalrow = ((Square) (i.next())).getRow();
//                        int legalcol = ((Square) (i.next())).getColoumn();
                            Square square = ((Square) i.next());
                            if (square.getRow() == rowIndex && square.getColoumn() == colIndex)
                            {
                                legalMove = true;
                                break;
                            }
//                        WhiteAndBlack[legalcol][legalrow].setFill(Color.GREEN);
                        }
                        if (legalMove == true)
                        {
                            board.BoardSquares[rowIndex][colIndex].setPiece(board.BoardSquares[movingSquare.getRow()][movingSquare.getColoumn()].getPiece());
                            board.BoardSquares[rowIndex][colIndex].getPiece().setColoumn(colIndex);
                            board.BoardSquares[rowIndex][colIndex].getPiece().setRow(rowIndex);
                            board.BoardSquares[movingSquare.getRow()][movingSquare.getColoumn()].setPiece(null);
                            RectGui oldRect = WhiteAndBlack[movingSquare.getRow()][movingSquare.getColoumn()];
                            WhiteAndBlack[rowIndex][colIndex].resetPieceImage();
                            String imagePath = oldRect.getPieceImage();
                            WhiteAndBlack[rowIndex][colIndex].setPieceImage(imagePath);
                            oldRect.resetPieceImage();
                            if( colorPressed == 'W')
                            {
                                RP.RecentMoves.setText(RP.RecentMoves.getText() + "White From  " + FirstClick + "    to "
                                        + Math.abs(rowIndex - 8) + "" + (char) ('A' + (colIndex)) + "\n");
                        
                            }
                            else
                            {
                             RP.RecentMoves.setText(RP.RecentMoves.getText() + "Black From  " + FirstClick + "    to "
                                        + Math.abs(rowIndex - 8) + "" + (char) ('A' + (colIndex)) + "\n");   
                            }
                                bPlayer=!bPlayer;
                            wPlayer=!wPlayer;
                         
                           
                        }

                    }
                    else
                    {
                        // not in moving mode
                        //get row and column index
                        Integer colIndex = GridPane.getColumnIndex(clickedNode);
                        Integer rowIndex = GridPane.getRowIndex(clickedNode);
                        //get the rect in that index
                        RectGui rect = WhiteAndBlack[rowIndex][colIndex];
                        if (rect.isOccupied())
                        {
                            movingMode = true;
                            movingSquare = rect.getSquare();
                            FirstClick = (Math.abs(rowIndex - 8) + " " + (char) ('A' + (colIndex)));
                            colorPressed = movingSquare.getPiece().getColour();
                        }
                        else
                        {
                            //do nothing
                        }
                    }
                    //Show Legal Moves and Print Square ID
//                    Integer colIndex = GridPane.getColumnIndex(clickedNode);
//                    Integer rowIndex = GridPane.getRowIndex(clickedNode);
//                    RP.RecentMoves.setText(RP.RecentMoves.getText() + (char) ((colIndex) + 'A') + rowIndex + "\n");

//                    List<Square> LegalMoves = Board.BoardSquares[rowIndex][colIndex].getPiece().legalMoves();
//                    /////try to print legal moves list
//                    try {
//                        Iterator i = LegalMoves.iterator();
//                        while (i.hasNext()) {
//                            System.out.println("" + ((Square) (i.next())).getColoumn() + "   " + ((Square) (i.next())).getRow()+"\n");
//                        }
//                    } catch (Exception e) {
//                    }
//                    //////
//                    
//                    //Glow Legal Moves Squares
//                    Iterator i = LegalMoves.iterator();
//                    while (i.hasNext()) {
//                        int legalrow = ((Square) (i.next())).getRow();
//                        int legalcol = ((Square) (i.next())).getColoumn();
////                        WhiteAndBlack[legalcol][legalrow].setFill(Color.GREEN);
//                    }
                }
            }
            );

            setOnMouseReleased((MouseEvent event) ->
            {
                Node clickedNode = event.getPickResult().getIntersectedNode();
                {
                    //recolor legal moves from green to normal
                    try
                    {
                        TimeUnit.SECONDS.sleep((long) 1.5);
                    } catch (InterruptedException ex)
                    {
                        Logger.getLogger(MainPane.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (int i = 0; i < 8; i++)
                    {
                        for (int j = 0; j < 8; j++)
                        {
                            if ((i + j) % 2 == 0)
                            {
                                // WhiteAndBlack[i][j].setFill(Color.BROWN);
                            }
                            else
                            {
                                //  WhiteAndBlack[i][j].setFill(Color.LIGHTBLUE);
                            }
                        }
                    }
                }
            }
            );
            requestFocus();
        }

    }

    class LeftPane extends GridPane
    {

        private Label turn;

        LeftPane()
        {
            //text ytktb feh ay haga, wakhed mkan 4 coloumns we 2 rows
            turn = new Label("White Turn");
            turn.setFont(Font.font("Serif", 30));
            turn.setPrefSize(200, 100);
            turn.setAlignment(Pos.CENTER);

            add(turn, 0, 0, 4, 2);
            // white dead left
            VBox WhitePawns = new VBox();
            WhitePawns.getChildren().add(new ImageView("Images/wp.png"));//example DELETE DELETE DELETE JUST EXAMPLE
            WhitePawns.getChildren().add(new ImageView("Images/wp.png"));//example DELETE DELETE DELETE JUST EXAMPLE
            WhitePawns.getChildren().add(new ImageView("Images/wp.png"));//example DELETE DELETE DELETE JUST EXAMPLE
            WhitePawns.getChildren().add(new ImageView("Images/wp.png"));//example DELETE DELETE DELETE JUST EXAMPLE
            VBox WhitePieces = new VBox();
            WhitePieces.getChildren().add(new ImageView("Images/wq.png"));//example DELETE DELETE DELETE JUST EXAMPLE
            WhitePieces.getChildren().add(new ImageView("Images/wr.png"));//example DELETE DELETE DELETE JUST EXAMPLE
            WhitePieces.getChildren().add(new ImageView("Images/wb.png"));//example DELETE DELETE DELETE JUST EXAMPLE

            add(WhitePawns, 0, 3, 1, REMAINING); // da msh hytms7
            add(WhitePieces, 1, 3, 1, REMAINING);// da msh hytms7
            // Black dead left
            VBox BlackPawns = new VBox();
            BlackPawns.getChildren().add(new ImageView("Images/bp.png"));//example DELETE DELETE DELETE JUST EXAMPLE
            BlackPawns.getChildren().add(new ImageView("Images/bp.png"));//example DELETE DELETE DELETE JUST EXAMPLE
            BlackPawns.getChildren().add(new ImageView("Images/bp.png"));//example DELETE DELETE DELETE JUST EXAMPLE
            BlackPawns.getChildren().add(new ImageView("Images/bp.png"));//example DELETE DELETE DELETE JUST EXAMPLE
            VBox BlackPieces = new VBox();
            BlackPieces.getChildren().add(new ImageView("Images/bq.png"));//example DELETE DELETE DELETE JUST EXAMPLE
            BlackPieces.getChildren().add(new ImageView("Images/br.png"));//example DELETE DELETE DELETE JUST EXAMPLE
            BlackPieces.getChildren().add(new ImageView("Images/bb.png"));//example DELETE DELETE DELETE JUST EXAMPLE

            add(BlackPawns, 2, 3, 1, REMAINING);// da msh hytms7
            add(BlackPieces, 3, 3, 1, REMAINING);// da msh hytms7

        }

      

   

    }

    class RightPane extends BorderPane
    {

        public Label RecentMoves;

        RightPane()
        {
            RecentMoves = new Label("Welcome\n");
            RecentMoves.setFont(Font.font("Serif", 20));
            RecentMoves.setAlignment(Pos.TOP_LEFT);

            RecentMoves.setStyle("-fx-background-color:brown;"
                    + "-fx-border-color:black;"
                    + "-fx-border-width:3;");
            RecentMoves.setPrefSize(350, 600);

            setCenter(RecentMoves);
        }

    }

    class TopPane extends HBox
    {

        TopPane()
        {

        }
    }

    class BottomPane extends HBox
    {
    }

}

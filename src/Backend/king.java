package Backend;

import java.util.ArrayList;
import java.util.List;

class King extends piece
{
    
    
    public King(int row, int coloumn, Square[][] BoardSquares, char colour)
    {
        super(row, coloumn, BoardSquares, colour);
    }

    public List<List> allNextEnemyMoves(Square[][] BoardSquare) //detect all next moves for all enemies to eliminate them from king legal m,oves
    {
        List<List> resultList = new ArrayList<List>();
        if (this.colour == 'W')
        {
            for (int i = 0; i < 8; i++)
            {
                for (int j = 0; j < 8; j++)
                {
                    if (BoardSquare[i][j].getPiece().colour == 'W' || BoardSquare[i][j].getPiece() == null)
                    {
                        continue;
                    }
                    resultList.add(BoardSquare[i][j].getPiece().legalMoves());
                }
            }

        }
        else
        {
            for (int i = 0; i < 8; i++)
            {
                for (int j = 0; j < 8; j++)
                {
                    if (BoardSquare[i][j].getPiece().colour == 'B' || BoardSquare[i][j].getPiece() == null)
                    {
                        continue;
                    }
                    resultList.add(BoardSquare[i][j].getPiece().legalMoves());
                }
            }
        }

        return resultList;
    }

    public List<Square> availableMoves()
    {
        List<Square> resultList = new ArrayList<Square>();


        if (getRow() + 1 < 8) //forward
        {
            Square sq = BoardSquares[getRow() + 1][getColoumn()];
            if (sq.getPiece() == null)
            {
                resultList.add(sq);
            }
            else
            {
                if (sq.getPiece().colour != this.colour) /// check again
                {
                    resultList.add(sq);
                }
            }
        }
        if ((getRow() + 1) < 8 && (getColoumn() - 1) > -1) //forward left
        {
            Square sq = BoardSquares[getRow() + 1][getColoumn() + 1]; //check againn +2
            if (sq.getPiece().colour != this.colour || sq.getPiece() == null)
            {
                resultList.add(sq);
            }
        }
        if ((getRow() + 1) < 8 && (getColoumn() + 1) < 8) //forward right
        {
            Square sq = BoardSquares[getRow() + 1][getColoumn() + 1]; //check again +2

            if (sq.getPiece().colour != this.colour || sq.getPiece() == null)
            {
                resultList.add(sq);
            }
        }
        if ((getRow() - 1) < 8) // backward  lesa na2es
        {
            Square sq = BoardSquares[getRow() - 1][getColoumn()];
            if (sq.getPiece() == null)
            {
                resultList.add(sq);
            }
            else
            {
                if (sq.getPiece().colour != this.colour)
                {
                    resultList.add(sq);
                }
            }
        }
        if ((getColoumn() - 1) < 8) // left 
        {
            Square sq = BoardSquares[getRow()][getColoumn() - 1];
            if (sq.getPiece() == null)
            {
                resultList.add(sq);
            }
            else
            {
                if (sq.getPiece().colour != this.colour)
                {
                    resultList.add(sq);
                }
            }
        }
        if ((getColoumn() + 1) < 8) // right  l
        {
            Square sq = BoardSquares[getRow()][getColoumn() + 1];
            if (sq.getPiece() == null)
            {
                resultList.add(sq);
            }
            else
            {
                if (sq.getPiece().colour != this.colour)
                {
                    resultList.add(sq);
                }
            }
        }
        if ((getColoumn() - 1) < 8 && getRow() - 1 < 8) // left  back
        {
            Square sq = BoardSquares[getRow() - 1][getColoumn() - 1];
            if (sq.getPiece() == null)
            {
                resultList.add(sq);
            }
            else
            {
                if (sq.getPiece().colour != this.colour)
                {
                    resultList.add(sq);
                }
            }
        }
        if ((getColoumn() + 1) < 8 && getRow() - 1 < 8) // right back
        {
            Square sq = BoardSquares[getRow() - 1][getColoumn() + 1];
            if (sq.getPiece() == null)
            {
                resultList.add(sq);
            }
            else
            {
                if (sq.getPiece().colour != this.colour)
                {
                    resultList.add(sq);
                }
            }
        }

        return resultList;

    }

    @Override
    public List<Square> legalMoves()
    {
        
        List<List> allNextEnemy = allNextEnemyMoves(BoardSquares);
        List<Square> availableMove= availableMoves();
        for (int i = 0; i < allNextEnemy.size(); i++)
        {
            for (int j = 0; j < allNextEnemy.get(i).size(); j++)
            {
                for (int k = 0; k <availableMove.size(); k++)
                {
                    if(availableMove.get(k)==allNextEnemy.get(i).get(j))
                    {
                        availableMove.remove(k);
                    }
                }
                
            }
        }
        return availableMove;

    }

    public boolean CheckMate()
    {
        List<List> allNextEnemy = allNextEnemyMoves(BoardSquares);
        Square myPostion = new Square(this.row,this.coloumn);
        for (int i = 0; i < allNextEnemy.size(); i++)
        {
            for (int j = 0; j < allNextEnemy.get(i).size(); j++)
            {
                if(allNextEnemy.get(i).get(j) == myPostion)
                {
                    return true;
                }
                
            }
        }
        return false;
      
    }
}

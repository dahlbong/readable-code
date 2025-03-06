package cleancode.minesweeper.tobe;

public class BoardIndexConverter {

    public static final char BASE_CHAR_COR_COL = 'a';

    public int getSelectedColIndex(String cellInput, int colSize) {
        char cellInputCol = cellInput.charAt(0);
        return convertColFrom(cellInputCol, colSize);
    }

    public int getSelectedRowIndex(String cellInput, int rowSize) {
        String cellInputRow = cellInput.substring(1);
        return convertRowFrom(cellInputRow, rowSize);
    }

    private int convertRowFrom(String cellInputRow, int rowSize) {
        int rowIndex = Integer.parseInt(cellInputRow) - 1;
        if (rowIndex >= rowSize || rowIndex < 0) {
            throw new GameException("잘못된 입력입니다.");
        }

        return rowIndex;
    }

    private int convertColFrom(char cellInputCol, int colSize) {
        int colIndex = cellInputCol - BASE_CHAR_COR_COL;
        if (colIndex >= colSize || colIndex < 0) {
            throw new GameException("잘못된 입력입니다.");
        }

        return colIndex;
    }
}

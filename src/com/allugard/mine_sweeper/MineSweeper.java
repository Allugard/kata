package com.allugard.mine_sweeper;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

class MineSweeper {

    private String [][] board;
    private int mines;

    public MineSweeper(final String board, final int nMines) {
        mines = nMines;

        String[] rows = board.split("\\n");

        this.board = new String[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            this.board[i] = rows[i].split(" ");
        }

        System.out.println(this);
    }

    public String solve() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (isResolvable(i, j)) {

                };
            }
        }



        // Your code here...
        return "";
    }

    private boolean isResolvable(int r, int c) {
        AtomicReference<Integer> suspected = new AtomicReference<>(0);
        String leftTop = getElement(r - 1, c - 1);
        String top  = getElement(r - 1, c);
        String rightTop  = getElement(r - 1, c + 1);
        String right = getElement(r, c + 1);
        String rightBottom = getElement(r + 1, c + 1);
        String bottom = getElement(r + 1, c);
        String leftBottom = getElement(r + 1, c - 1);
        String left = getElement(r, c - 1);
        List<String> values = List.of(leftTop, left, top, rightTop, right, rightBottom, bottom, leftBottom);
        values
                .forEach(s -> {
                    if (!s.equals("-1") && !s.equals("?") && !s.equals("x")) {
                        suspected.getAndSet(suspected.get() + Integer.parseInt(s));
                    }
                });
        return suspected.get() > 0;
    }

    private String getElement(int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board.length ) {
            return "-1";
        }
        return board[r][c];
    }


    public static void main(String[] args) {
//        new MineSweeper("0 ? ?\n0 ? ?", 2);


        OpenBoard.init("1 x 2 1\n1 1 2 x\n0 1 2 2\n0 1 x 1");
//        new MineSweeper("? ? ? ?\n? 1 2 x\n0 ? ? 2\n0 1 ? 1", 3);
        new MineSweeper("? ? ? ?\n? ? ? x\n0 ? ? ?\n0 ? ? ?", 3);
    }

    @Override
    public String toString() {
        return Arrays.stream(board)
                .map(Arrays::toString)
                .collect(Collectors.joining("\n\r"));
    }






    static class OpenBoard {
        private static String[][] board;

        public static void init(final String board) {
            String[] rows = board.split("\\n");

            OpenBoard.board = new String[rows.length][];
            for (int i = 0; i < rows.length; i++) {
                OpenBoard.board[i] = rows[i].split(" ");
            }
            System.out.println(OpenBoard.string());

        }

        public static String openGrid(int x, int y) {
            return board[x][y];
        }

        public static String string() {
            return Arrays.stream(board)
                    .map(Arrays::toString)
                    .collect(Collectors.joining("\n\r"));
        }
    }
}

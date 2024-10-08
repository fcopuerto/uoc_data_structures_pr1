package edu.uoc.ds.adt;

import edu.uoc.ds.adt.sequential.Set;
import edu.uoc.ds.adt.sequential.StackArrayImpl;
import edu.uoc.ds.adt.sequential.Stack;
import edu.uoc.ds.traversal.Iterator;

/**
 * A class intended to solve a word search game.
 */
public class PR1WordSearch {

    private final String[] grid;

    /**
     * Constructor of PR1WordSearch instance from the given input string.
     *
     * @param input A string representation of the word search grid,
     *              where rows are separated by newlines.
     */
    public PR1WordSearch(String input) {
        grid = input.strip().split("\n");
    }

    /**
     * An enum to represent possible search directions in the grid.
     */
    public enum Direction {
        HORIZONTAL,
        VERTICAL
    }

    /**
     * A class to represent the result of a word search within the grid.
     */
    public static class Result {
        String word;
        int row;
        int col;
        Direction direcction;

        /**
         * Constructs a Result instance with the specified parameters.
         *
         * @param word      The word that was found.
         * @param row       The row index where the word starts.
         * @param col       The column index where the word starts.
         * @param direction The direction in which the word is found.
         */
        public Result(String word, int row, int col, Direction direction) {
            this.word = word;
            this.row = row;
            this.col = col;
            this.direcction = direction;
        }
    }

    /**
     * Searches for words in the provided set within the word search grid.
     *
     * @param words A set of words to search for in the grid.
     * @return A stack containing the results of the search.
     */
    public Stack<PR1WordSearch.Result> search(Set<String> words) {
        StackArrayImpl<Result> resultStack = new StackArrayImpl<>();
        Iterator<String> values = words.values();
        Result res;
        while (values.hasNext()) {
            String word = values.next();
            // Check horizontal results
            res = searchHorizontally(word);
            if (res != null) {
                resultStack.push(res);
                continue;
            }

            // Check vertical results only if not found in horizontal
            res = searchVertically(word);
            if (res != null) {
                resultStack.push(res);
            }
        }
        return resultStack;
    }

    /**
     * Searches for the specified word horizontally in the grid.
     *
     * @param word The word to search for horizontally.
     * @return A Result object if the word is found; otherwise null.
     */
    private Result searchHorizontally(String word) {
        Result res = null;
        for (int i = 0; i < grid.length; i++) {
            String line = grid[i];
            int index = line.indexOf(word);
            if (index != -1) {
                res = new Result(word, i, index, Direction.HORIZONTAL);
                return res;
            }
        }
        return res;
    }

    /**
     * Searches for the specified word vertically in the grid.
     *
     * @param word The word to search for vertically.
     * @return A Result object if the word is found; otherwise null.
     */
    private Result searchVertically(String word) {
        Result res = null;
        int columns = grid[0].length();
        for (int col = 0; col < columns; col++) {
            StringBuilder verticalWord = new StringBuilder();
            for (String s : grid) {
                verticalWord.append(s.charAt(col));
            }
            String column = verticalWord.toString();
            int index = column.indexOf(word);
            if (index != -1) {
                res = new Result(word, index, col, Direction.VERTICAL);
                return res;
            }
        }
        return res;
    }
}
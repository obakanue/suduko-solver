
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SolverTest {
    Solver testSolver;
    int[][] puzzle;
    int[][] solvedPuzzle;
    @Before
    public void setUp() throws Exception {
        int[][] setUp 
            = {     { 0, 0, 0, 2, 6, 0, 7, 0, 1 },
                    { 6, 8, 0, 0, 7, 0, 0, 9, 0 },
                    { 1, 9, 0, 0, 0, 4, 5, 0, 0 },
                    { 8, 2, 0, 1, 0, 0, 0, 4, 0 },
                    { 0, 0, 4, 6, 0, 2, 9, 0, 0 },
                    { 0, 5, 0, 0, 0, 3, 0, 2, 8 },
                    { 0, 0, 9, 3, 0, 0, 0, 7, 4 },
                    { 0, 3, 0, 0, 5, 0, 0, 3, 6 },
                    { 7, 0, 3, 0, 1, 8, 0, 0, 0 }
            };
        int[][] solvedSetUp  
                = {     { 9, 5, 4, 8, 7, 2, 3, 1, 6 },
                        { 8, 6, 1, 9, 4, 3, 7, 2, 5 },
                        { 3, 2, 7, 6, 5, 1, 4, 9, 8 },
                        { 1, 3, 2, 5, 9, 7, 8, 6, 4 },
                        { 7, 4, 9, 2, 8, 6, 5, 3, 1 },
                        { 5, 8, 6, 1, 3, 4, 2, 7, 9 },
                        { 2, 9, 8, 7, 6, 5, 1, 4, 3 },
                        { 4, 1, 5, 3, 2, 9, 6, 8, 7 },
                        { 6, 7, 3, 4, 1, 8, 9, 5, 2 }
                };
        solvedPuzzle = solvedSetUp;
        puzzle = setUp;
    }
    @After
    public void tearDown() throws Exception {
        testSolver = new Solver(new int[9][9]);
    }
    
    @Test
    public final void testCheckRulesRow() {
        testSolver = new Solver(solvedPuzzle);
        for (int i = 0; i < 9 ; i++){
            for (int j = 0; j < 9 ; j++){
                assertTrue("fel vid index [" + i + "] [" + j +"]"  ,testSolver.checkRow(i, j));
            }
        }
    }
    @Test
    public final void testCheckRulesColumn() {
        testSolver = new Solver(solvedPuzzle);
        for (int i = 0; i < 9 ; i++){
            for (int j = 0; j < 9 ; j++){
                assertTrue("fel vid index [" + i + "] [" + j +"] " + solvedPuzzle[i][j] ,testSolver.checkColumn(i, j));
            }
        }
    }
    @Test
    public final void testCheckRulesSolveBox() {
    	testSolver = new Solver(solvedPuzzle);
    	for (int i = 0; i < 9 ; i++){
    		for (int j = 0; j < 9 ; j++){
    			assertTrue("fel vid index [" + i + "] [" + j +"]"  ,testSolver.checkBox(i, j));
    		}
    	}
    }
    
    @Test
    public final void testCheckAllRules() {
        testSolver = new Solver(solvedPuzzle);
        for (int i = 0; i < 9 ; i++){
            for (int j = 0; j < 9 ; j++){
                assertTrue("fel vid index [" + i + "] [" + j +"]"  ,testSolver.checkRules(i, j));
            }
        }
    }
}
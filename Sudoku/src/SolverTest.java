
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SolverTest {
    Solver testSolver;
    int[][] setUp;
    int[][] solvedPuzzle;
    
    @Before
    public void setUp() throws Exception {
        int[][] setUp 
            = {     { 5, 0, 0, 0, 1, 0, 0, 0, 4 },
                    { 2, 7, 4, 0, 0, 0, 6, 0, 0 },
                    { 0, 8, 0, 9, 0, 4, 0, 0, 0 },
                    { 8, 1, 0, 4, 6, 0, 3, 0, 2 },
                    { 0, 0, 2, 0, 3, 0, 1, 0, 0 },
                    { 7, 0, 6, 0, 9, 1, 0, 5, 8 },
                    { 0, 0, 0, 5, 0, 3, 0, 1, 0 },
                    { 0, 0, 5, 0, 0, 0, 9, 2, 7 },
                    { 1, 0, 0, 0, 2, 0, 0, 0, 3 }
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
        this.solvedPuzzle = solvedSetUp;
        this.setUp = setUp;
    }
    
    @After
    public void tearDown() throws Exception {
        testSolver = new Solver(new int[9][9]);
    }
    
    @Test
    public final void testSudokuSolver() {
    	testSolver = new Solver(setUp);
    	assertTrue(testSolver.solve());
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
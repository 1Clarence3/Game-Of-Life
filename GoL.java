import java.util.Arrays;
import java.lang.Math;
/**
 * Write a description of class GoL here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GoL
{
    private int[][] grid1; //Original grid 
    private int[][] grid2; //Grid w/ updated values after a new generation 
    private int resolution; // # of squares on a grid
    private int rows, cols; //Rows & Columns of grid
    private double x, y; //X & Y coordinates for StdDraw
    private double scale; //Used for width/height when drawing squares
    private String pattern; //Tracks option
    private double mouseX, mouseY; //Tracks X & Y coordinates of mojse
    private int started = 1; //Tracks if "start" button was pressed
    private int mouseClicked = 1; //Tracks of mouse was clicked
    private int colorswitcher; //Tracks current color of pen

    //Initializes dimension & option 
    public GoL(int dimensions, String pattern1)
    {
        resolution = 100/dimensions;
        cols = 100/resolution;
        rows = 100/resolution;
        scale = (double)resolution/2;
        pattern = pattern1;
    }

    //Sets canvas size and creates grid
    //Depending on player's option, this method will intialize the grid for the chosen pattern
    public void setUp() 
    {
        StdDraw.setCanvasSize(800,800);
        StdDraw.setXscale(0,130);
        StdDraw.setYscale(0,130);
        StdDraw.enableDoubleBuffering();  
        StdDraw.setPenColor(StdDraw.BLUE);
        grid1 = make2DArray(rows,cols);

        //Glider
        if(pattern.equals("1"))
        {            
            grid1[rows-3][0] = 1;
            grid1[rows-3][1] = 1;
            grid1[rows-3][2] = 1;
            grid1[rows-2][2] = 1;
            grid1[rows-1][1] = 1;            
        }        
        //Spaceship
        else if(pattern.equals("2"))
        {
            grid1[rows/2+1][1] = 1;
            grid1[rows/2+1][2] = 1;
            grid1[rows/2+1][3] = 1;
            grid1[rows/2+1][4] = 1;
            grid1[rows/2][0] = 1;
            grid1[rows/2][4] = 1;
            grid1[rows/2-1][4] = 1;
            grid1[rows/2-2][0] = 1;
            grid1[rows/2-2][3] = 1;               
        }
        //Pulsar
        else if(pattern.equals("3"))
        {
            //Middle up
            grid1[rows/2+1][cols/2-2] = 1;
            grid1[rows/2+1][cols/2-3] = 1;
            grid1[rows/2+1][cols/2-4] = 1;
            grid1[rows/2+1][cols/2+2] = 1;
            grid1[rows/2+1][cols/2+3] = 1;
            grid1[rows/2+1][cols/2+4] = 1;

            //Middle down
            grid1[rows/2-1][cols/2-2] = 1;
            grid1[rows/2-1][cols/2-3] = 1;
            grid1[rows/2-1][cols/2-4] = 1;
            grid1[rows/2-1][cols/2+2] = 1;
            grid1[rows/2-1][cols/2+3] = 1;
            grid1[rows/2-1][cols/2+4] = 1;          

            //Very bottom
            grid1[rows/2-6][cols/2-2] = 1;
            grid1[rows/2-6][cols/2-3] = 1;
            grid1[rows/2-6][cols/2-4] = 1;
            grid1[rows/2-6][cols/2+2] = 1;
            grid1[rows/2-6][cols/2+3] = 1;
            grid1[rows/2-6][cols/2+4] = 1;

            //Very Top
            grid1[rows/2+6][cols/2-2] = 1;
            grid1[rows/2+6][cols/2-3] = 1;
            grid1[rows/2+6][cols/2-4] = 1;
            grid1[rows/2+6][cols/2+2] = 1;
            grid1[rows/2+6][cols/2+3] = 1;
            grid1[rows/2+6][cols/2+4] = 1;

            //Middle Right
            grid1[rows/2+2][cols/2-1] = 1;
            grid1[rows/2+3][cols/2-1] = 1;
            grid1[rows/2+4][cols/2-1] = 1;
            grid1[rows/2+2][cols/2+1] = 1;
            grid1[rows/2+3][cols/2+1] = 1;
            grid1[rows/2+4][cols/2+1] = 1;

            //Middle Left
            grid1[rows/2-2][cols/2-1] = 1;
            grid1[rows/2-3][cols/2-1] = 1;
            grid1[rows/2-4][cols/2-1] = 1;
            grid1[rows/2-2][cols/2+1] = 1;
            grid1[rows/2-3][cols/2+1] = 1;
            grid1[rows/2-4][cols/2+1] = 1;

            //Top Left
            grid1[rows/2+2][cols/2-6] = 1;
            grid1[rows/2+3][cols/2-6] = 1;
            grid1[rows/2+4][cols/2-6] = 1;
            grid1[rows/2+2][cols/2+6] = 1;
            grid1[rows/2+3][cols/2+6] = 1;
            grid1[rows/2+4][cols/2+6] = 1;

            //Top Right
            grid1[rows/2-2][cols/2-6] = 1;
            grid1[rows/2-3][cols/2-6] = 1;
            grid1[rows/2-4][cols/2-6] = 1;
            grid1[rows/2-2][cols/2+6] = 1;
            grid1[rows/2-3][cols/2+6] = 1;
            grid1[rows/2-4][cols/2+6] = 1;
        }
        //Random
        else if(pattern.equals("4"))
        {
            for(int r = 0; r < rows; r++)
            {
                for(int c = 0; c < rows; c++)
                {
                    grid1[r][c] = (int)(Math.floor(Math.random()*2));           
                }                  
            }
        }

        //Start, Pause, Restart, and Clear buttons
        StdDraw.rectangle(30,115,30,15);
        StdDraw.rectangle(95,115,30,15);
        StdDraw.rectangle(115,50,13,30);
        StdDraw.text(30,115,"START");
        StdDraw.text(95,115,"PAUSE");  
        StdDraw.text(115,50,"CLEAR");           

        //Draws grid
        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < rows; c++)
            {                
                x = scale + (c * resolution);
                y = scale + (r * resolution);
                StdDraw.rectangle(x,y,scale,scale);                
            }                  
        }    
        StdDraw.show();
        updateMouse();
    } 

    //Constantly checks if the player pressed "Start" or "Pause" buttons
    public void updateMouse()
    {
        started = 0;
        while(true)
        {
            mouseX = StdDraw.mouseX();
            mouseY = StdDraw.mouseY();
            if(StdDraw.mousePressed())
            {              
                if((mouseX >= 0 && mouseX <= 100) && (mouseY >= 0 && mouseY <= 100))
                {
                    if(grid1[(int)((mouseY+1)/(resolution))][(int)((mouseX-1)/(resolution))] == 0)
                    {
                        grid1[(int)((mouseY+1)/(resolution))][(int)((mouseX-1)/(resolution))] = 1;
                    }
                    else
                    {
                        grid1[(int)((mouseY+1)/(resolution))][(int)((mouseX-1)/(resolution))] = 0;
                    }
                    drawManual(((int)((mouseY+1)/(resolution))),((int)((mouseX-1)/(resolution))));
                }
                else if((mouseX >= 0 && mouseX <= 60) && (mouseY > 100 && mouseY <= 130))
                {
                    started = 1;
                    StdDraw.pause(100);
                    while(true)
                    { 
                        draw();
                        newGeneration();
                    }
                }
                else if((mouseX >= 102 && mouseX <= 128) && (mouseY >= 0 && mouseY <= 100))
                {
                    for(int r = 0; r < rows; r++)
                    {
                        for(int c = 0; c < cols; c++)
                        {
                            grid1[r][c] = 0;
                            x = scale + (c * resolution);
                            y = scale + (r * resolution);
                            StdDraw.setPenColor(StdDraw.WHITE);
                            StdDraw.filledRectangle(x,y,scale-0.2,scale-0.2);
                        }           
                    }     
                    StdDraw.show();
                    updateMouse();
                }
            }
        }
    }	

    //Checks if player pressed "Pause" or "Clear" button
    public void checkPauseClear()
    {
        if(StdDraw.mousePressed())
        {
            mouseX = StdDraw.mouseX();
            mouseY = StdDraw.mouseY();
            if((mouseX >= 65 && mouseX <= 125) && (mouseY > 100 && mouseY <= 130))
            {
                updateMouse();
            }
            else if((mouseX >= 102 && mouseX <= 128) && (mouseY >= 0 && mouseY <= 100))
            {
                updateMouse();
            }
        }        
    }

    //Creates a grid
    public int[][] make2DArray(int rows, int cols)
    {
        int[][] arr = new int[rows][cols];
        return arr;
    }

    //Changes the pen color to different shades of blue
    public void penColorSwitcher(int switchNum)
    {
        if(switchNum == 1)
        {
            StdDraw.setPenColor(StdDraw.BLUE);     
            switchNum = 2;
        }
        else if(switchNum == 2)
        {
            StdDraw.setPenColor(StdDraw.BOOK_BLUE);            
        }
        else 
        {
            StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);            
        }
    }

    //Main draw method 
    public void draw()
    {     
        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < cols; c++)
            {
                checkPauseClear();
                if(grid1[r][c] == 1)
                {
                    x = scale + (c * resolution);
                    y = scale + (r * resolution);
                    penColorSwitcher((int)(Math.random()*3+1));
                    StdDraw.filledRectangle(x,y,scale-0.2,scale-0.2);
                }                
                else
                {
                    x = scale + (c * resolution);
                    y = scale + (r * resolution);
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.filledRectangle(x,y,scale-0.2,scale-0.2);
                }                
            }            
        }     
        StdDraw.pause(125);
        StdDraw.show();
    }

    //Draw method for "manual" option where player inputs certain squares in the grid
    public void drawManual(int squareRow, int squareCol)
    {     
        if(grid1[squareRow][squareCol] == 1)
        {
            x = scale + (squareCol * resolution);
            y = scale + (squareRow * resolution);
            penColorSwitcher((int)(Math.random()*3+1));
            StdDraw.filledRectangle(x,y,scale-0.2,scale-0.2);
        }                       
        else
        {
            x = scale + (squareCol * resolution);
            y = scale + (squareRow * resolution);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledRectangle(x,y,scale-0.2,scale-0.2);
        }
        StdDraw.show();
        StdDraw.pause(350);
    }

    //Creates a new grid with the updated values which is copied over to original grid
    public void newGeneration()
    {
        grid2 = make2DArray(rows,cols);
        int neighbors;
        int currstate;
        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < cols; c++)
            {
                checkPauseClear();
                neighbors = countNeighbors(grid1,r,c);
                currstate = grid1[r][c];
                if(currstate == 0 && neighbors == 3)
                {
                    grid2[r][c] = 1;
                }
                else if(currstate == 1 && (neighbors < 2 || neighbors > 3)) 
                {
                    grid2[r][c] = 0;
                }
                else 
                {
                    grid2[r][c] = grid1[r][c];
                }
            }            
        }  
        StdDraw.pause(150);
        grid1 = grid2;
    }

    //Counts the # of live cells around a cell (Implements the Wraparound grid concept)
    //Wraparound grid concept: If square goes off the grid -> square reappears on other side
    public int countNeighbors(int[][] grid, int x, int y)
    {
        int sum = 0;
        for(int i = -1; i < 2; i++)
        {
            for(int j = -1; j < 2; j++)
            {
                int xSorroundingCell = (x+i+rows) % rows;
                int ySorroundingCell = (y+j+cols) % cols;
                sum += grid[xSorroundingCell][ySorroundingCell];
            }            
        } 
        sum -= grid[x][y];
        return sum;
    }
}
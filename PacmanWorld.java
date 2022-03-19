import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PacmanWorld extends World
{

    private static final int WIDTH_WALL = 28;
    private static final int HEIGHT_WALL = 28;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public PacmanWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Wall wall = new Wall();
        int withWall = wall.getImage().getWidth();

        for(int x = 50; x < 550; x+= WIDTH_WALL){
            wall = new Wall();// cada cubo se crea
            addObject(wall,x,30);
            wall = new Wall();
            addObject(wall,x,350);
        }

        for(int y = 58; y < 200; y+=HEIGHT_WALL ){
            wall = new Wall();
            addObject(wall,50,y);
            wall = new Wall();
            addObject(wall,526,y);
        }

        //puedo hacer esto porq todas las clases estan en el mismo paquete

        
        
        Banana banana = new Banana();
        addObject(banana,450,104);
        BigBall bigBall = new BigBall();
        addObject(bigBall,278,175);
        Cherry cherry = new Cherry();
        addObject(cherry,178,237);
        SmallBall smallBall = new SmallBall();
        addObject(smallBall,367,250);
        Strawberry strawberry = new Strawberry();
        addObject(strawberry,330,98);
        Banana banana2 = new Banana();
        addObject(banana2,471,275);
        BigBall bigBall2 = new BigBall();
        addObject(bigBall2,206,313);
        Cherry cherry2 = new Cherry();
        addObject(cherry2,72,258);
        SmallBall smallBall2 = new SmallBall();
        addObject(smallBall2,466,214);
        Strawberry strawberry2 = new Strawberry();
        addObject(strawberry2,286,278);
        
        
        
        PacmanHud hud = new PacmanHud();
        addObject(hud,0,0);
        
        Pacman pacman = new Pacman(hud);
        addObject(pacman,110,95);
        
    }
}

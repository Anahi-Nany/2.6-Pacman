import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Pacman extends Actor
{
    private static final int OFFSET = 10;
    private static final int MAX_COUNTER_MOUTH = 10;
    private static final int MAX_COUNTER_MOUVEMENT = 6;//+ pequeño +rapido

    private static final int UP=0;
    private static final int DOWN=1;
    private static final int LEFT=2;
    private static final int RIGHT=3;

    private GreenfootImage []images;
    private int currentImage;
    private int counterMouth;
    private int counterMouvement;
    private int offsetX=0;
    private int offsetY=0;
    private int direction;
    private int score;
    private PacmanHud pacmanHud;

    public Pacman(PacmanHud pacmanHud){
        images = new GreenfootImage[2];
        images[0]= new GreenfootImage("images/pacman-close.png");
        images[1]= new GreenfootImage("images/pacman-open.png");
        this.pacmanHud=pacmanHud;
    }

    public void act()
    {

        openClouseMouth();

        movePacman();
        checkColissions();
        //menera de pasar un valor a string String.format(score)
        //getWorld().showText(String.valueOf(score),400,30);//ya no hay necesidad de mosrarlo
    }

    private void checkColissions(){
        //quiero q me de puntos
        //pone cast segun el item
        Item item = (Item)getOneIntersectingObject(Item.class);

        //instanceof operador de java, nos dice si un objeto pertenece a una clase
        if(item != null){
            getWorld().removeObject(item);
            score+=item.getScore();
            //pacman incrementa el puntaje y el hud q es el encargado de visualizar lo muestra

            pacmanHud.setScore(score);
            
            if(getWorld().getObjects(Item.class).isEmpty()){
                offsetX=0;
                offsetY=0;
                getWorld().showText("YOU WIN!!",350, 250); 
            }
        }
    }

    private void movePacman(){

        counterMouvement++;

        if(counterMouvement < MAX_COUNTER_MOUVEMENT){
            return;
        }

        int currentX = getX();
        int currentY = getY();

        counterMouvement=0;
        handleDirection();

        Actor wall = getWallOnTheWay();

        //checar si choca con un objeto
        //Si fuese cualquier objeto, pero nos importa el tipo, getOneObjectAtOffset (30,0,Actor.class);
        //Actor wall = getOneObjectAtOffset (30,0,Wall.class);

        if(wall == null){//todavia te puedes mover
            //setLocation(currentX + 3, currentY); 
            setLocation(currentX + offsetX, currentY + offsetY);            

        }

    }

    private void handleDirection(){
        //el metodo es global, con el static
        //esta pertenece a geenfoot, por 
        if(Greenfoot.isKeyDown("UP")){
            offsetX=0;
            offsetY =-OFFSET;
            direction = UP;
        }else if(Greenfoot.isKeyDown("DOWN")){
            offsetX=0;
            offsetY =OFFSET;
            direction = DOWN;
        }else if(Greenfoot.isKeyDown("RIGHT")){
            offsetY=0;
            offsetX =OFFSET;
            direction = RIGHT;
        }else if(Greenfoot.isKeyDown("LEFT")){
            offsetY=0;
            offsetX=-OFFSET;
            direction = LEFT;
        }
    }

    private Wall getWallOnTheWay(){
        Actor wall= null;

        switch(direction){
            case UP: return(Wall) getOneObjectAtOffset (0,-30,Wall.class);
            case DOWN:return(Wall) getOneObjectAtOffset (0,30,Wall.class);
            case RIGHT:return(Wall) getOneObjectAtOffset (30,0,Wall.class);
            case LEFT:return(Wall)getOneObjectAtOffset (-30,0,Wall.class);

        }
        return null;
    }

    private void openClouseMouth(){
        counterMouth++;
        //cuando llegue a un max de 5, entonces mueve la boca y recetea
        if(counterMouth == MAX_COUNTER_MOUTH){
            counterMouth =0;
            setImage(images[currentImage]);
            currentImage = (currentImage +1) % images.length;
        }
    }
}

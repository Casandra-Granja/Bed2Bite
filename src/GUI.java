import processing.core.PApplet;

public class GUI {

    public enum PANTALLA{INICIAL, INICIALEXTENDIDA, SIGNUP, SIGNIN, DESCRIPCIONRESTAURANTE};

    public PANTALLA pantallaActual;

    Button bRegister, bSignIn;
    TextField tfUsuari, tfPassword, tfNomiApellidos, tfNumHabitacion;



    public GUI(PApplet p5){
        pantallaActual = PANTALLA.SIGNUP;
        creaBotons(p5);
        creaTextField(p5);


    }

    public void creaBotons(PApplet p5){
        bRegister= new Button(p5, "YOU DON'T HAVE AN ACCOUNT?", p5.width/2 -150, p5.height/2 +350 , 300, 80);
        bSignIn= new Button(p5, "DON'T YOU HAVE AN ACCOUNT?", p5.width/2 -150, p5.height/2 +350 , 300, 80);
    }
    public void creaTextField(PApplet p5){
        //Pantalla sign in
        tfPassword = new TextField(p5, p5.width/2 -255, p5.height/2 +250 , 510, 80);
        tfUsuari= new TextField( p5, p5.width/2 -255, p5.height/2 +100 , 510, 80);
        tfNomiApellidos= new TextField(p5 ,p5.width/2 -255, p5.height/2 -200 , 510, 80);
        tfNumHabitacion= new TextField(p5, p5.width/2 -255, p5.height/2 - 50, 510, 80);

    }



    public void dibuixaPantallaSingUp(PApplet p5) {
        p5.background(55);
        zonaPrincipal(p5);
        p5.fill(255);
        logo(p5);
        p5.circle(p5.width/2, p5.height/2 -360, 200);
        p5.pushStyle();
        p5.textSize(25);
        p5.text("PASSWORD",p5.width/2 -255, p5.height/2 +240);
        tfPassword.display(p5);
        p5.text("USER",p5.width/2 -255, p5.height/2 +90);
        tfUsuari.display(p5);
        bSignIn.display(p5);
        p5.text("NAME AND SURNAMES",p5.width/2 -255, p5.height/2 -220 );
        tfNomiApellidos.display(p5);
        p5.text("ROOM NUMER",p5.width/2 -255, p5.height/2 - 60 );
        tfNumHabitacion.display(p5);
        p5.popStyle();

    }
    public void dibuixaPantallaSingIn(PApplet p5) {
        p5.background(55);
        zonaPrincipal(p5);
        p5.fill(255);
        logo(p5);
        p5.circle(p5.width/2, p5.height/2 -200, 400);
        p5.pushStyle();
        p5.textSize(25);
        p5.text("PASSWORD",p5.width/2 -255, p5.height/2 +240);
        tfPassword.display(p5);
        p5.text("USER",p5.width/2 -255, p5.height/2 +90);
        tfUsuari.display(p5);
        bRegister.display(p5);
        p5.popStyle();

    }

    public void dibuixaPantallaInicial(PApplet p5) {
        // Dibuixa el fons (gris)
        p5.background(55);    // Color de fons
        zonaPrincipal(p5);
        //Elements de la pantalla
        taskBar(p5);
        logo(p5);
        restaurantsMain(p5);
        restaurant(p5, Layout.restaurantWidthMain + Layout.marginWBR, 0, "RESTAURANT 1");
        restaurant(p5, Layout.restaurantWidthMain + Layout.marginWBR , Layout.marginHBR + Layout.resturantHeight, "RESTAURANT 2");
    }

    public void dibuixaPantallaInicialExtendida(PApplet p5) {
        p5.background(55);
        zonaPrincipal(p5);
        taskBar(p5);
        logo(p5);
        restaurant(p5,0,0, "RESTAURANT 3");
        restaurant(p5,Layout.restaurantWidth+ Layout.marginWBR, 0, "RESTAURANT 4");
        restaurant(p5, 2*(Layout.restaurantWidth + Layout.marginWBR),0, "RESTAURANT 5");
        restaurant(p5,0, Layout.marginHBR + Layout.resturantHeight, "RESTAURANT 6");
        restaurant(p5,Layout.restaurantWidth+ Layout.marginWBR,Layout.marginHBR + Layout.resturantHeight, "RESTAURANT 7" );
        restaurant(p5, 2*(Layout.restaurantWidth + Layout.marginWBR), Layout.marginHBR + Layout.resturantHeight, "RESTAURANT 8");

    }
    public void dibuixaPantallaDescripcionDelRestaurante(PApplet p5) {
        p5.background(55);
        zonaPrincipal(p5);
        taskBar(p5);
        logo(p5);
        restaurantsMain(p5);
    }






    public void taskBar(PApplet p5){
        p5.fill(240, 100, 50);
        p5.rect( Layout.marginW + Layout.logoWidth, Layout.marginH, Layout.bannerWidth, Layout.bannerHeight);
        p5.fill(0);
        p5.textAlign(p5.CENTER);
        p5.text("TASK BAR", Layout.marginW + Layout.logoWidth + Layout.bannerWidth/2, Layout.marginH + Layout.bannerHeight/2);
    }

    public void logo(PApplet p5){
        p5. fill(200,50,100);
        p5. rect(Layout.marginW, Layout.marginH, Layout.logoWidth, Layout.logoHeight);
        p5.fill(0);
        p5.text("LOGO", Layout.marginW + Layout.logoWidth/2, Layout.marginH + Layout.logoHeight/2);

    }

    public void zonaPrincipal(PApplet p5){
        p5.fill(200);
        p5.rect(Layout.marginW, Layout.marginH, p5.width - Layout.marginW *2, p5.height - Layout.marginH *2);

    }

    public void restaurantsMain (PApplet p5){
        p5. fill(100,50,100);
        p5. rect(Layout.marginInicialW, Layout.marginInicialH, Layout.restaurantWidthMain, Layout.resturantHeightMain);
        p5.fill(0);
        p5.text("RESTAURANT MAIN", Layout.marginInicialW + Layout.restaurantWidthMain /2, Layout.marginInicialH + Layout.resturantHeightMain /2);
    }

    public void restaurant (PApplet p5,float x, float y, String título){ //pasar parametre x, y
        p5. fill(300,50,100);
        p5. rect(x + Layout.marginInicialW, y+ Layout.marginInicialH, Layout.restaurantWidth, Layout.resturantHeight);
        p5.fill(0);
        p5.textAlign(p5.CENTER);
        p5.text( título, x +Layout.restaurantWidth/2 + Layout.marginInicialW, y+ Layout.marginInicialH +Layout.resturantHeight /2);
    }

}

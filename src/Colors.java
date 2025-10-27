import processing.core.PApplet;

public class Colors {

    //Propietats
    int colors[];

    //Constructor
    public Colors(PApplet p5){
        this.setColors(p5);
    }

    //setter dels colors
    void setColors(PApplet p5){

        this.colors = new int[7];
        this.colors[0]= p5.color(0xFF76C3F5);
        this.colors[1]= p5.color(0xFF80F556);
        this.colors[2]= p5.color(0xFFF5281D);
        this.colors[3]= p5.color(0xFFF5DD2A);
        this.colors[4]= p5.color(0xFF000000);
        this.colors[5]= p5.color(0xFF00BBFF);
        this.colors[6]= p5.color(0xFF0073FF);


    }

    // Getter del número de colors
    public int getNumColors(){
        return this.colors.length;
    }

    // Getter del color primari
    public int getBlueColor(){
        return  this.colors[0];
    }

    // Getter del color secundari
    public int getGreenColor(){
        return  this.colors[1];
    }

    // Getter del color terciari
    public int getRedColor(){
        return  this.colors[2];
    }

    // Getter del color quaternari
    public int getYellowColor(){
        return  this.colors[3];
    }

    // Getter del color cinc
    public int getBlackColor(){
        return  this.colors[4];
    }
    public int getBotonColor(){
        return  this.colors[5];
    }
    public int getBotonOverColor(){
        return  this.colors[6];
    }


    // Getter del color i-èssim
    public int getColorAt(int i){
        return this.colors[i];
    }

    void displayColors(PApplet p5, float x, float y, float w){

        p5.pushStyle();
        //Llegenda
        p5.fill(0); p5.textAlign(p5.LEFT); p5.textSize(36);
        p5.text("Colors:", x, y-10);

        // Paleta de colors
        float wc = w / getNumColors();
        for(int i=0; i<getNumColors(); i++){
            p5.fill(getColorAt(i)); p5.stroke(0); p5.strokeWeight(3);
            p5.rect(x + i*wc, y, wc, wc);
        }
        p5.popStyle();
    }


    }


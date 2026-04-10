package MisReservasCards;

import B2B_Color.Colors;
import B2B_ElementsGUI.Button;
import B2B_Medidas.Layout;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Tarjeta visual que representa una reserva del usuario en la pantalla "Mis Reservas".
 * Muestra la imagen del restaurante, el nombre, los detalles de la reserva
 * (fecha, hora, personas y tipo), un sistema de valoración con estrellas,
 * y los botones de modificar y eliminar.
 */
public class MisReservasCard {

    /** Imagen del restaurante asociado a la reserva. */
    PImage img;

    /** Nombre del restaurante (título de la tarjeta). */
    String titol;

    /** Información de la reserva en formato "fecha | hora | personas | tipo". */
    String info;

    /** Botón para modificar la reserva. */
    public Button bModificar;

    /** Botón para eliminar la reserva. */
    public Button bEliminar;

    /** Coordenada X de la esquina superior izquierda de la tarjeta. */
    float x;

    /** Coordenada Y de la esquina superior izquierda de la tarjeta. */
    float y;

    /** Anchura de la tarjeta en píxeles. */
    float w;

    /** Altura de la tarjeta en píxeles. */
    float h;

    /** Paleta de colores de la aplicación. */
    Colors appColors;

    /** Lista de estrellas de valoración de la reserva. */
    CheckBoxStarList cbl;
    /** Array con los nombres de las imágenes de estrella activada y desactivada. */
    String[] imgs = {"starON.png", "starOFF.png"};

    /** Array con los nombres de las imágenes de estrella activada y desactivada. */
    public String idReserva = "";

    /** Número de estrellas actualmente seleccionadas en la valoración. */
        public int estrellasSeleccionadas = 0;

    /**
     * Constructor que inicializa la tarjeta con el título, la información
     * y la paleta de colores. La posición y dimensiones se calculan usando
     * las constantes de {@link B2B_Medidas.Layout}.
     *
     * @param p5        Referencia al objeto PApplet de Processing.
     * @param titol     Nombre del restaurante.
     * @param info      Información de la reserva.
     * @param appColors Paleta de colores de la aplicación.
     */
        public MisReservasCard(PApplet p5, String titol, String info, Colors appColors){
            this.titol= titol;
            this.info = info;
            this.x= Layout.marginInicialW+ 10;
            this.y= Layout.marginInicialH +10;
            this.h= 150;
            this.w= p5.width-Layout.marginInicialW-90;
            this.appColors = appColors;

        }


    /**
     * Actualiza la posición y dimensiones de la tarjeta y reposiciona
     * los botones y las estrellas de valoración en consecuencia.
     *
     * @param x Nueva coordenada X.
     * @param y Nueva coordenada Y.
     * @param w Nueva anchura.
     * @param h Nueva altura.
     */
    public void setDimensions(float x, float y, float w, float h){
        this.x = x; this.y = y;
        this.w = w; this.h = h;
        // Reposiciona botones y estrellas
        if(bModificar != null){
            bModificar.setPosition((int)(x + w - 200), (int)(y + h - 70));
        }
        if(bEliminar != null){
            bEliminar.setPosition((int)(x + w - 330), (int)(y + h - 70));
        }
        if(cbl != null){
            cbl.x = (int)(x + 225);
            cbl.y = (int)(y + h - 55);
            for(int i = 0; i < cbl.cbs.length; i++){
                cbl.cbs[i].x = cbl.x + (25 + cbl.marge) * i;
                cbl.cbs[i].y = cbl.y;
                cbl.cbs[i].w = 25;
                cbl.cbs[i].h = 25;
            }
        }
    }

    /**
     * Establece la imagen del restaurante que se mostrará en la tarjeta.
     *
     * @param img Imagen del restaurante.
     */
        public void setImage(PImage img){
            this.img= img;

        }
    /**
     * Comprueba si el cursor del ratón está sobre el área de la tarjeta.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     * @return {@code true} si el cursor está dentro de los límites de la tarjeta.
     */
         public boolean mouseOver(PApplet p5) {
            return this.x < p5.mouseX && p5.mouseX < this.x + this.w &&
                this.y < p5.mouseY && p5.mouseY < this.y + this.h;
        }


    /**
     * Crea e inicializa los botones de modificar y eliminar, y la lista de estrellas.
     * Los botones se posicionan en la esquina inferior derecha de la tarjeta
     * y las estrellas en la parte inferior izquierda del área de texto.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void CrearBotons(PApplet p5){
        // Botones posicionados respecto a x, y, w, h
        bModificar = new Button(p5, "MODIFICAR", (int)(x + w - 200), (int)(y + h - 70), 120, 40, appColors);
        bEliminar  = new Button(p5, "ELIMINAR",  (int)(x + w - 330), (int)(y + h - 70), 120, 40, appColors);
        bEliminar.setColors(appColors);
        bEliminar.setMidaTexte(15);
        bModificar.setColors(appColors);
        bModificar.setMidaTexte(15);

        // Estrellas posicionadas abajo a la izquierda del área de texto
        cbl = new CheckBoxStarList(p5, 5, imgs, (int)x + 550, (int) (y + 140), 40, 40);
        cbl.setCheckBoxStars(0);
        estrellasSeleccionadas = 0;
    }

    /**
     * Dibuja la tarjeta en pantalla.
     * Muestra la sombra, el fondo blanco, la imagen o un placeholder si no hay imagen,
     * una línea divisoria vertical, el título, una línea bajo el título, los campos
     * de información divididos por el separador "|", la etiqueta de valoración,
     * y los botones y estrellas de valoración.
     *
     * @param p5       Referencia al objeto PApplet de Processing.
     * @param selected Indica si la tarjeta está seleccionada (actualmente no altera el renderizado).
     */
    public void display(PApplet p5, boolean selected){
        p5.pushStyle();

        // Sombra
        p5.noStroke();
        p5.fill(0, 40);
        p5.rect(x + 5, y + 5, w, h, 15);

            p5.fill(255);
        p5.rect(x, y, w, h, 15);

        // Imagen o placeholder
        if(img == null){
            p5.fill(220);
            p5.rect(x + 15, y + 15, 200, h - 30, 10);
            p5.fill(150);
            p5.textSize(13);
            p5.textAlign(p5.CENTER, p5.CENTER);
            p5.text("Sin imagen", x + 15 + 100, y + h/2);
        } else {
            p5.image(img, x + 15, y + 15, 200, h - 30);
        }

        // Línea divisoria vertical
        p5.stroke(220);
        p5.strokeWeight(1);
        p5.line(x + 230, y + 20, x + 230, y + h - 20);
        p5.noStroke();

        // Título
        p5.fill(30);
        p5.textSize(24);
        p5.textAlign(p5.LEFT, p5.TOP);
        p5.text(titol, x + 245, y + 15);

        // Línea bajo título
        p5.stroke(200);
        p5.strokeWeight(1);
        p5.line(x + 245, y + 50, x + w - 20, y + 50);
        p5.noStroke();

        // Info dividida
        String[] parts = info.split("\\|");
        p5.textSize(18);
        p5.fill(80);
        if(parts.length >= 4){
            p5.text("Fecha:      " + parts[0].trim(), x + 245, y + 65);
            p5.text("Hora:        " + parts[1].trim(), x + 245, y + 95);
            p5.text("Personas: " + parts[2].trim(), x + 245, y + 125);
            p5.text("Tipo:          " + parts[3].trim(), x + 245, y + 155);
        } else {
            p5.text(info, x + 245, y + 65, w - 280, h - 100);
        }

        // Etiqueta estrellas
        p5.fill(120);
        p5.textSize(18);
        p5.text("Valoración:", x + 450, y + 155 );

        // Botones y estrellas
        bModificar.display(p5);
        bEliminar.display(p5);
        cbl.display(p5);
        p5.popStyle();
    }

    /**
     * Gestiona los clics sobre los elementos interactivos de la tarjeta:
     * botón modificar, botón eliminar y estrellas de valoración.
     * Actualiza {@link #estrellasSeleccionadas} cuando se cambia la valoración.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void clickMouseOnCardItems(PApplet p5){
        if(bModificar.mouseOverButton(p5)){
            System.out.println("MODIFICAR clicked: " + idReserva);
        } else if(bEliminar.mouseOverButton(p5)){
            System.out.println("ELIMINAR clicked: " + idReserva);
        } else if(cbl.checkCursor(p5)){
            cbl.checkMouse(p5);
            estrellasSeleccionadas = cbl.getNumSelected();

        }
    }


}


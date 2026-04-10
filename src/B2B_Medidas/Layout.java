package B2B_Medidas;

import RestaurantCards.PagedCard2DRestaurantCard;

/**
 * Clase de constantes estáticas que define todas las dimensiones y márgenes
 * del layout de la interfaz gráfica de la aplicación.
 * Al ser todos sus campos estáticos, no es necesario instanciar la clase
 * para acceder a sus valores.
 */
public class Layout {

    /**
     * Anchura del logo en píxeles.
     */
    public static float logoWidth = 200;

    /**
     * Altura del logo en píxeles.
     */
    public static float logoHeight = 180;

    /**
     * Anchura del banner superior en píxeles.
     */
    public static float bannerWidth = 1296;

    /**
     * Altura del banner superior en píxeles.
     */
    public static float bannerHeight = 150;

    /**
     * Anchura del restaurante principal (vista destacada) en píxeles.
     */
    public static float restaurantWidthMain = 950;

    /**
     * Altura del restaurante principal (vista destacada) en píxeles.
     */
    public static float resturantHeightMain = 700;

    /**
     * Anchura estándar de una tarjeta de restaurante en píxeles.
     */
    public static float restaurantWidth = 450;

    /**
     * Altura estándar de una tarjeta de restaurante en píxeles.
     */
    public static float resturantHeight = 330;

    /**
     * Anchura del componente superior (top bar) en píxeles.
     */
    public static float topW = 200;

    /**
     * Altura del componente superior (top bar) en píxeles.
     */
    public static float topH = 80;

    /**
     * Anchura del panel de detalle del restaurante en píxeles.
     */
    public static float restaurantDetalleWidth = 810;

    /**
     * Altura del panel de detalle del restaurante en píxeles.
     */
    public static float restaurantDetalleHeight = 600;

    /**
     * Anchura del panel de información de detalle en píxeles.
     */
    public static float infoDetalleWidth = 590;

    /**
     * Margen horizontal entre bloques de restaurante en píxeles.
     */
    public static float marginWBR = 50;

    /**
     * Margen vertical entre bloques de restaurante en píxeles.
     */
    public static float marginHBR = 40;

    /**
     * Margen horizontal exterior general en píxeles.
     */
    public static float marginW = 20;

    /**
     * Margen vertical exterior general en píxeles.
     */
    public static float marginH = 20;

    /**
     * Margen inicial horizontal (margen exterior + desplazamiento adicional) en píxeles.
     */
    public static float marginInicialW = 23 + marginW;

    /**
     * Margen inicial vertical (margen exterior + logo + desplazamiento) en píxeles.
     */
    public static float marginInicialH = 65 + marginH + logoHeight;

    /**
     * Anchura de la imagen en la pantalla "Mis Reservas" en píxeles.
     */
    public static float imagenMisReservasW = 600;

    /**
     * Altura de la imagen en la pantalla "Mis Reservas" en píxeles.
     */
    public static float imagenMisReservasH = 100;

    /**
     * Anchura del área de tarjetas de "Mis Reservas" en píxeles.
     */
    public static float misReservasCardsW = 800;

    /**
     * Altura del área de tarjetas de "Mis Reservas" en píxeles.
     */
    public static float misReservasCardsH = 700;

    /**
     * Anchura de los botones de navegación de "Mis Reservas" en píxeles.
     */
    public static float misReservasButtonW = 60;

    /**
     * Altura de los botones de navegación de "Mis Reservas" en píxeles.
     */
    public static float misReservasButtonH = 60;

    /**
     * Anchura del área de tarjetas de restaurantes en píxeles.
     */
    public static float restaurantCardsW = 1200;

    /**
     * Altura del área de tarjetas de restaurantes en píxeles.
     */
    public static float restaurantCardsH = 700;

    /**
     * Anchura de los botones de navegación de restaurantes en píxeles.
     */
    public static float restaurantButtonW = 60;

    /**
     * Altura de los botones de navegación de restaurantes en píxeles.
     */
    public static float restaurantButtonH = 60;
}
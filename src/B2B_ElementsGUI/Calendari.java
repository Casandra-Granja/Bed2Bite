package B2B_ElementsGUI;

import processing.core.PApplet;

import java.util.Calendar;

/**
 * Componente de calendario mensual interactivo para la selección de fechas.
 * Muestra una cuadrícula de botones de día ({@link DayButton}) correspondientes
 * al mes y año actuales. Permite navegar hacia el mes anterior o siguiente,
 * seleccionar una fecha y obtenerla en formato de cadena de texto.
 * Los días del mes anterior que rellenan la primera semana se muestran deshabilitados.
 */
public class Calendari {

    /** Array con los nombres abreviados de los meses en inglés. */
    String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    /** Año actualmente mostrado en el calendario. */
    int any;

    /** Mes actualmente mostrado (1-12). */
    int mes;

    /** Día del mes desde el que se inicializa el calendario (normalmente 1). */
    int dia;

    /** Número de días del mes actualmente mostrado. */
    int numDaysMonth;

    /** Número de días del mes anterior, para rellenar la primera fila. */
    int numDaysPrevMonth;

    /** Día de la semana en que cae el día 1 del mes actual (0=lunes, 6=domingo). */
    int dayOfWeek;

    /**
     * Número del primer día que aparece en la cuadrícula
     * (puede ser del mes anterior si el mes no empieza en lunes).
     */
    int firstDay;

    /** Indica si el usuario ha seleccionado una fecha. */
    boolean dateSelected = false;
    /** Día seleccionado por el usuario. */
    public int selectedDay = 0;
    /** Mes seleccionado por el usuario. */
    public int selectedMonth = 0;
    /** Año seleccionado por el usuario. */
    public int selectedYear = 0;

    /** Instancia de {@link Calendar} que representa el mes actual mostrado. */
    Calendar cal;

    /** Instancia de {@link Calendar} que representa el mes anterior. */
    Calendar cPrev;

    /**
     * Array de botones de día que forman la cuadrícula del calendario.
     * Tiene capacidad para 37 botones (máximo de casillas visibles en un mes).
     */
    DayButton[] buttons;

    /** Coordenada X de la esquina superior izquierda del calendario. */
    int x;

    /** Coordenada Y de la esquina superior izquierda del calendario. */
    int y;

    /** Anchura total del calendario en píxeles. */
    int w;

    /** Altura total del calendario en píxeles. */
    int h;

    /**
     * Constructor que inicializa el calendario con la fecha actual del sistema.
     * Calcula el día de la semana en que cae el primer día del mes,
     * obtiene el número de días del mes actual y del anterior,
     * y genera la cuadrícula de botones llamando a {@link #createCalendar(int, int, int, int)}.
     *
     * @param x Coordenada X de la esquina superior izquierda.
     * @param y Coordenada Y de la esquina superior izquierda.
     * @param w Anchura total del calendario.
     * @param h Altura total del calendario.
     */
    public Calendari(int x, int y, int w, int h) {

        this.buttons = new DayButton[37];

        this.cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);

        this.any = cal.get(Calendar.YEAR);
        this.mes = cal.get(Calendar.MONTH) + 1;
        this.dia = cal.get(Calendar.DATE);

        this.numDaysMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        this.dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SUNDAY) {
            this.dayOfWeek = 6;
        } else {
            this.dayOfWeek = this.dayOfWeek - 2;
        }

        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        this.firstDay = cal.get(Calendar.DATE);

        cPrev = Calendar.getInstance();
        setPrevCalendar(1, this.mes - 2, this.any);

        this.numDaysPrevMonth = cPrev.getActualMaximum(Calendar.DAY_OF_MONTH);

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        createCalendar(x, y, w, h);
    }

    /**
     * Indica si el usuario ha seleccionado una fecha en el calendario.
     *
     * @return {@code true} si hay una fecha seleccionada.
     */
    public boolean isDateSelected() {
        return this.dateSelected;
    }

    /**
     * Devuelve la fecha seleccionada en formato "dia/mes/año".
     *
     * @return Cadena de texto con la fecha seleccionada.
     */
    public String getSelectedDate() {
        return this.selectedDay + "/" + this.selectedMonth + "/" + this.selectedYear;
    }


    /**
     * Actualiza el objeto {@link Calendar} principal a la fecha indicada.
     *
     * @param d Día.
     * @param m Mes (0-11 según {@link Calendar}).
     * @param y Año.
     */
    public void setCalendar(int d, int m, int y) {
        cal.set(Calendar.YEAR, y);
        cal.set(Calendar.MONTH, m);
        cal.set(Calendar.DATE, d);
    }

    /**
     * Actualiza el objeto {@link Calendar} del mes anterior a la fecha indicada.
     *
     * @param d Día.
     * @param m Mes (0-11 según {@link Calendar}).
     * @param y Año.
     */

    public void setPrevCalendar(int d, int m, int y) {
        cPrev.set(Calendar.YEAR, y);
        cPrev.set(Calendar.MONTH, m);
        cPrev.set(Calendar.DATE, d);
    }

    /**
     * Establece los valores de la fecha seleccionada.
     *
     * @param d Día seleccionado.
     * @param m Mes seleccionado.
     * @param y Año seleccionado.
     */
    public void setSelectedDate(int d, int m, int y) {
        this.selectedDay = d;
        this.selectedMonth = m;
        this.selectedYear = y;
    }

    /**
     * Retrocede un mes en el calendario.
     * Actualiza el mes y el año (con control de desbordamiento a diciembre del año anterior),
     * recalcula el día de la semana del primer día del nuevo mes, obtiene el número
     * de días del mes anterior y regenera la cuadrícula de botones.
     */
    public void prevMonth() {

        this.buttons = new DayButton[37];

        this.mes--;
        if (this.mes == 0) {
            this.mes = 12;
            this.any--;
        }
        setCalendar(this.dia, this.mes - 1, this.any);

        this.numDaysMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        this.dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SUNDAY) {
            this.dayOfWeek = 6;
        } else {
            this.dayOfWeek = this.dayOfWeek - 2;
        }

        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        this.firstDay = cal.get(Calendar.DATE);

        setPrevCalendar(1, this.mes - 2, this.any);
        this.numDaysPrevMonth = cPrev.getActualMaximum(Calendar.DAY_OF_MONTH);

        createCalendar(x, y, w, h);
    }

    /**
     * Genera la cuadrícula de botones de día para el mes actual.
     * <p>
     * Divide el espacio disponible en celdas de anchura {@code w/7} y altura {@code h/6}.
     * Si el mes no empieza en lunes, rellena el inicio de la primera fila con los últimos
     * días del mes anterior (deshabilitados). A continuación coloca los días del mes actual
     * de izquierda a derecha, fila a fila, hasta completar todos los días del mes.
     * </p>
     *
     * @param x Coordenada X de la esquina superior izquierda.
     * @param y Coordenada Y de la esquina superior izquierda.
     * @param w Anchura total disponible para el calendario.
     * @param h Altura total disponible para el calendario.
     */
    public void createCalendar(int x, int y, int w, int h) {

        float dayWidth = w / 7;
        float dayHeight = h / 6;
        int numDia = 1;
        int f = 0, nb = 0;

        while (numDia <= numDaysMonth) {

            if (firstDay != 1 && f == 0) {
                int cPrev = 0;
                for (int p = firstDay, c = 0; p <= numDaysPrevMonth; p++, c++) {
                    buttons[nb] = new DayButton(x + c * dayWidth, y + f * dayHeight, dayWidth, dayHeight, p, mes, any);
                    buttons[nb].setEnabled(false);
                    cPrev++;
                    nb++;
                }
                for (int c = cPrev; c < 7; c++) {
                    buttons[nb] = new DayButton(x + c * dayWidth, y + f * dayHeight, dayWidth, dayHeight, numDia, mes, any);
                    numDia++;
                    nb++;
                }
                f++;
            } else {
                for (int c = 0; c < 7; c++) {
                    buttons[nb] = new DayButton(x + c * dayWidth, y + f * dayHeight, dayWidth, dayHeight, numDia, mes, any);
                    numDia++;
                    nb++;
                    if (numDia > numDaysMonth) {
                        break;
                    }
                }
                f++;
            }
        }
    }

    /**
     * Avanza un mes en el calendario.
     * Actualiza el mes y el año (con control de desbordamiento a enero del año siguiente),
     * recalcula el día de la semana del primer día del nuevo mes, obtiene el número
     * de días del mes anterior y regenera la cuadrícula de botones.
     */
    public void nextMonth() {

        this.buttons = new DayButton[37];

        this.mes++;
        if (this.mes == 13) {
            this.mes = 1;
            this.any++;
        }
        setCalendar(this.dia, this.mes - 1, this.any);

        this.numDaysMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        this.dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SUNDAY) {
            this.dayOfWeek = 6;
        } else {
            this.dayOfWeek = this.dayOfWeek - 2;
        }

        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        this.firstDay = cal.get(Calendar.DATE);

        setPrevCalendar(1, this.mes - 2, this.any);

        this.numDaysPrevMonth = cPrev.getActualMaximum(Calendar.DAY_OF_MONTH);

        createCalendar(x, y, w, h);
    }


    /**
     * Dibuja el calendario en pantalla.
     * Muestra el nombre del mes y el año en la parte superior, llama a {@code display()}
     * de cada botón no nulo y, si hay fecha seleccionada, la muestra a la derecha
     * del encabezado en formato "dia/mes/año".
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void display(PApplet p5) {
        p5.pushStyle();
        p5.fill(0);
        p5.textSize(36);
        p5.textAlign(p5.LEFT);
        p5.text(months[mes - 1] + "/" + any, x, y - 30);
        for (DayButton b : buttons) {
            if (b != null) {
                b.display(p5);
            }
        }

        if (dateSelected) {
            String dateText = this.selectedDay + "/" + this.selectedMonth + "/" + this.selectedYear;
            p5.fill(0);
            p5.textSize(24);
            p5.textAlign(p5.RIGHT);
            p5.text(dateText, x + w, y - 30);
        }
        p5.popStyle();
    }


    /**
     * Comprueba si se ha hecho clic sobre algún botón de día habilitado.
     * Al hacer clic, deselecciona todos los botones y alterna el estado del botón pulsado.
     * Actualiza {@link #dateSelected} y la fecha seleccionada según el resultado.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void checkButtons(PApplet p5) {
        for (DayButton b : buttons) {
            if ((b != null) && (b.enabled) && (b.mouseOver(p5))) {
                boolean prevState = b.selected;
                deselectAll();
                b.setSelected(!prevState);
                if (b.selected) {
                    dateSelected = true;
                    setSelectedDate(b.dia, b.mes, b.any);
                } else {
                    dateSelected = false;
                }
            }
        }
    }

    /**
     * Deselecciona todos los botones de día del calendario.
     */
    public void deselectAll() {
        for (DayButton b : buttons) {
            if (b != null) {
                b.setSelected(false);
            }
        }
    }
}

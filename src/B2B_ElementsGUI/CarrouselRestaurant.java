package B2B_ElementsGUI;
import B2B_Color.Colors;
import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CarrouselRestaurant {

    // ─── Dimensions del carrusel ───────────────────────────────────────────────
    int x, y, w, h;

    // ─── Dades del restaurant ──────────────────────────────────────────────────
    int idRestaurant;

    // ─── Imatges carregades ────────────────────────────────────────────────────
    ArrayList<PImage> imgs;
    ArrayList<String> paths;
    int currentImage = 0;

    // ─── Carpeta local on es copien les imatges ────────────────────────────────
    String carpetaLocal;

    // ─── Fitxer seleccionat per l'usuari (pendent de guardar) ─────────────────
    File fitxerPendent = null;
    PImage imatgePendent = null;
    String nomFitxerPendent = "";

    // ─── Referència al PApplet (necessària per al callback fileSelected) ───────
    PApplet p5ref;

    // ─── Botons ────────────────────────────────────────────────────────────────
    Button bPrev, bNext;
    Button bAfegir;
    Button bGuardar;

    Colors appColors;

    // ─── Constructor ──────────────────────────────────────────────────────────
    public CarrouselRestaurant(int x, int y, int w, int h, int idRestaurant, String carpetaLocal, Colors appColors) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.idRestaurant = idRestaurant;
        this.carpetaLocal = carpetaLocal;
        this.imgs  = new ArrayList<>();
        this.paths = new ArrayList<>();
        this.appColors = appColors;
    }

    // ─── Inicialització de botons ─────────────────────────────────────────────
    public void initButtons(PApplet p5) {
        this.p5ref = p5;  // ← guardem la referència aquí
        bPrev    = new Button(p5, "◀",             x - 60,      y + h / 2 - 25, 50,  50,  appColors);
        bNext    = new Button(p5, "▶",             x + w + 10,  y + h / 2 - 25, 50,  50,  appColors);
        bAfegir  = new Button(p5, "AFEGIR IMATGE", x,           y + h + 20,     200, 50,  appColors);
        bGuardar = new Button(p5, "GUARDAR",        x + 220,     y + h + 20,     150, 50,  appColors);
    }

    // ─── Càrrega des de MySQL ─────────────────────────────────────────────────
    public void carregarDesDeMySQL(PApplet p5, Connection conn) {
        imgs.clear();
        paths.clear();
        currentImage = 0;

        String sql = "SELECT idImagen, Nombre FROM imagen WHERE Restaurante_idRestaurante = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, String.valueOf(idRestaurant));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ruta = rs.getString("Nombre");
                paths.add(ruta);
                imgs.add(p5.loadImage(ruta));
            }
            System.out.println("OK: " + imgs.size() + " imatges carregades.");
        } catch (Exception e) {
            System.out.println("ERROR carregarDesDeMySQL: " + e.getMessage());
        }
    }

    // ─── Navegació ────────────────────────────────────────────────────────────
    public void next() {
        if (currentImage < imgs.size() - 1) currentImage++;
    }

    public void prev() {
        if (currentImage > 0) currentImage--;
    }

    // ─── Callback de selectInput — Processing el crida automàticament ─────────
    // IMPORTANT: ha d'acceptar només File, sense PApplet
    public void fileSelected(File selection) {
        if (selection == null) {
            System.out.println("Cap fitxer seleccionat.");
            return;
        }
        fitxerPendent    = selection;
        nomFitxerPendent = selection.getName();
        imatgePendent    = p5ref.loadImage(selection.getAbsolutePath());  // ← usem p5ref
        System.out.println("Fitxer pendent de guardar: " + nomFitxerPendent);
    }

    // ─── Guardar: copia local + inserció a MySQL ──────────────────────────────
    public void guardarImatgePendent(PApplet p5, Connection conn) {
        if (fitxerPendent == null) return;

        // 1. Copia el fitxer a la carpeta local
        String rutaRelativa = carpetaLocal + nomFitxerPendent;
        Path origen = Paths.get(fitxerPendent.getAbsolutePath());
        Path desti  = Paths.get(p5.sketchPath(rutaRelativa));

        try {
            Files.createDirectories(desti.getParent());
            Files.copy(origen, desti, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            System.out.println("OK: fitxer copiat a " + desti);
        } catch (IOException e) {
            System.out.println("ERROR copiant fitxer: " + e.getMessage());
            return;
        }

        // 2. Inserta a la taula imagen
        String sql = "INSERT INTO imagen (Nombre, Restaurante_idRestaurante) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, rutaRelativa);
            ps.setString(2, String.valueOf(idRestaurant));
            ps.executeUpdate();
            System.out.println("OK: imatge guardada a MySQL → " + rutaRelativa);
        } catch (Exception e) {
            System.out.println("ERROR inserint a MySQL: " + e.getMessage());
            return;
        }

        // 3. Afegeix al carrusel
        imgs.add(imatgePendent);
        paths.add(rutaRelativa);
        currentImage     = imgs.size() - 1;
        fitxerPendent    = null;
        imatgePendent    = null;
        nomFitxerPendent = "";
    }

    // ─── Display ──────────────────────────────────────────────────────────────
    public void display(PApplet p5) {
        p5.pushStyle();

        p5.fill(30); p5.stroke(80);
        p5.rect(x, y, w, h, 8);

        if (imgs.isEmpty() && imatgePendent == null) {
            p5.fill(120); p5.textAlign(p5.CENTER, p5.CENTER); p5.textSize(18);
            p5.text("Sense imatges", x + w / 2f, y + h / 2f);
        } else if (imatgePendent != null) {
            p5.image(imatgePendent, x, y, w, h);
            p5.fill(255, 180, 0, 200); p5.noStroke();
            p5.rect(x, y, w, 30);
            p5.fill(0); p5.textAlign(p5.CENTER, p5.CENTER); p5.textSize(14);
            p5.text("PREVISUALITZACIÓ · " + nomFitxerPendent, x + w / 2f, y + 15);
        } else {
            p5.image(imgs.get(currentImage), x, y, w, h);
            p5.fill(0, 0, 0, 150); p5.noStroke();
            p5.rect(x + w - 70, y + h - 30, 65, 25, 4);
            p5.fill(255); p5.textAlign(p5.RIGHT, p5.CENTER); p5.textSize(13);
            p5.text((currentImage + 1) + " / " + imgs.size(), x + w - 8, y + h - 17);
        }

        if (imgs.size() > 1 && imatgePendent == null) {
            if (bPrev != null) bPrev.display(p5);
            if (bNext != null) bNext.display(p5);
        }

        if (bAfegir  != null) bAfegir.display(p5);
        if (bGuardar != null && imatgePendent != null) bGuardar.display(p5);

        p5.popStyle();
    }

    // ─── Check botons (mousePressed) ─────────────────────────────────────────
    public void checkButtons(PApplet p5, Connection conn) {
        if (bNext != null && bNext.mouseOverButton(p5) && imgs.size() > 1)
            next();
        else if (bPrev != null && bPrev.mouseOverButton(p5) && imgs.size() > 1)
            prev();
        else if (bAfegir != null && bAfegir.mouseOverButton(p5))
            p5.selectInput("Selecciona una imatge...", "fileSelected", null, this);  // ← this
        else if (bGuardar != null && bGuardar.mouseOverButton(p5) && imatgePendent != null)
            guardarImatgePendent(p5, conn);
    }

    // ─── Check cursor (draw) ──────────────────────────────────────────────────
    public boolean checkCursor(PApplet p5) {
        if (bNext    != null && bNext.mouseOverButton(p5))    return true;
        if (bPrev    != null && bPrev.mouseOverButton(p5))    return true;
        if (bAfegir  != null && bAfegir.mouseOverButton(p5))  return true;
        if (bGuardar != null && bGuardar.mouseOverButton(p5)) return true;
        return false;
    }
}
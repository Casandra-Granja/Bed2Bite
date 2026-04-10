package B2B_ElementsGUI;

import processing.core.PApplet;

/**
 * Agrupa un conjunto de objetos {@link RadioButton} para garantizar
 * que solo uno puede estar seleccionado a la vez.
 * Gestiona la selección y actualización del grupo al hacer clic.
 */
public class RadioButtonGroup {

    /**
     * Array de radio buttons que forman el grupo.
     */
    RadioButton[] rbuttons;

    /**
     * Índice del radio button actualmente seleccionado.
     */
    int selectedOption;

    /**
     * Constructor que crea el grupo con un número fijo de radio buttons.
     *
     * @param n Número de radio buttons que contendrá el grupo.
     */
        public RadioButtonGroup(int n){
            rbuttons = new RadioButton[n];
            selectedOption = 0;
        }

    /**
     * Asigna los radio buttons al grupo usando varargs.
     *
     * @param rbs Radio buttons a incluir en el grupo.
     */

    public void setRadioButtons(RadioButton ... rbs){
            for(int i=0; i<rbs.length; i++){
                this.rbuttons[i] = rbs[i];
            }
    }
    /**
     * Selecciona el radio button situado en el índice indicado
     * y lo marca como activo.
     *
     * @param n Índice del radio button a seleccionar.
     */
        public void setSelected(int n){
            selectedOption = n;
            this.rbuttons[n].setChecked(true);
        }
    /**
     * Devuelve el índice del radio button actualmente seleccionado.
     *
     * @return Índice de la opción seleccionada.
     */
         public int getSelected(){
             return this.selectedOption;

        }
    /**
     * Indica si alguno de los radio buttons del grupo está seleccionado.
     *
     * @return {@code true} si hay al menos uno seleccionado.
     */
            public boolean haySeleccionado(){
                for(int i = 0; i < rbuttons.length; i++){
                    if(rbuttons[i] != null && rbuttons[i].isChecked()){
                    return true;
            }
        }
        return false;
    }
    /**
     * Dibuja todos los radio buttons del grupo en pantalla.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
        public void display(PApplet p5){
            for(int i=0; i<rbuttons.length; i++){
                if(rbuttons[i]!=null){
                    rbuttons[i].display(p5);
                }
            }
        }
    /**
     * Actualiza la selección del grupo al hacer clic.
     * Si el cursor está sobre algún radio button, lo selecciona y deselecciona
     * todos los demás, asegurando la exclusividad de la selección.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
        public void updateOnClick(PApplet p5){
            if(clickOnOneRadioButton(p5)){
                for(int i=0; i<rbuttons.length; i++){
                    if(rbuttons[i]!=null && rbuttons[i].onMouseOver(p5)){
                        selectedOption = i;
                        rbuttons[i].setChecked(true);
                    }
                    else{
                        rbuttons[i].setChecked(false);
                    }
                }
            }
        }

    /**
     * Comprueba si el cursor está sobre alguno de los radio buttons del grupo.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     * @return {@code true} si el cursor está sobre algún radio button.
     */
        public boolean clickOnOneRadioButton(PApplet p5){
            for(int i=0; i<rbuttons.length; i++){
                if(rbuttons[i]!=null && rbuttons[i].onMouseOver(p5)){
                    return true;
                }
            }
            return false;
        }
    }

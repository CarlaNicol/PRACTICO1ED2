/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MAURICIO
 */
public class ExcepcionOrdenInvalido extends Exception {

    /**
     * Creates a new instance of <code>ExceptionClabeNoExiste</code> without
     * detail message.
     */
    public ExcepcionOrdenInvalido() {
    this("Orden minimo del arbol debe ser 3");
    }

    /**
     * Constructs an instance of <code>ExceptionClabeNoExiste</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionOrdenInvalido(String msg) {
        super(msg);
    } 
    
}

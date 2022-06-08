import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public  class ArbolBinario <K extends Comparable<K>,V> implements IArbolBusqueda<K,V> {
    protected NodoBinario<K,V>raiz;
    @Override
    public void insertar(K claveInsertar, V valorInsertar) throws NullPointerException {
        if(claveInsertar == null){
            throw new NullPointerException("Clave a insertar no puede ser nula");
        }
        if(valorInsertar == null){
            throw new NullPointerException("Valor a insertar no puede ser nulo");
        }
        if(this.esArbolVacio()){
            this.raiz = new NodoBinario<>(claveInsertar,valorInsertar);
            return;
        }
        NodoBinario<K,V> nodoAnterior=NodoBinario.nodoVacio();
        NodoBinario<K,V>nodoActual=this.raiz;
        while(!NodoBinario.esNodoVacio(nodoActual)){
            K claveNodoActual=nodoActual.getClave();
            nodoAnterior=nodoActual;
            if(claveInsertar.compareTo(claveNodoActual)<0){
                //situacion donde la clave es < de la clave del nodo actual
                nodoActual=nodoActual.getHijoIzquierdo();
            }else if(claveInsertar.compareTo(claveNodoActual)>0){
                //situacion donde la clave es >de la clave del nodo actual
                nodoActual=nodoActual.getHijoDerecho();
            }else{
                //situacion donde la clave es = de la clave del nodo actual
                //solo actualizamos el valor
                nodoActual.setValor(valorInsertar);
                return;
            }
        }
        NodoBinario<K,V>nuevoNodo=new NodoBinario<>(claveInsertar,valorInsertar);
        K claveDelNodoAnterior=nodoAnterior.getClave();
        if(claveInsertar.compareTo(claveDelNodoAnterior)<0){
            nodoAnterior.setHijoIzquierdo(nuevoNodo);
        }else{
            nodoAnterior.setHijoDerecho(nuevoNodo);
        }
    }

    @Override
    public V eliminar(K claveEliminar) throws NullPointerException, ExcepcionClaveNoExiste {
        return null;
    }

    @Override
    public V buscar(K claveBuscar) throws NullPointerException {
        return null;
    }



    protected NodoBinario<K,V>obtenerNodoSucesor(NodoBinario<K,V>nodoActual){
        NodoBinario<K,V>nodoAnterior=NodoBinario.nodoVacio();
        while(!NodoBinario.esNodoVacio(nodoActual)){
            nodoAnterior=nodoActual;
            nodoActual=nodoActual.getHijoIzquierdo();
        }
        return nodoAnterior;
    }

    @Override
    public int altura() {
        return altura(this.raiz);
    }

    @Override
    public void vaciarArbol() {

    }

    @Override
    public List<K> recorridoPorNiveles() {
        return null;
    }

    protected int altura(NodoBinario<K,V>nodoActual){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int alturaPorIzq=altura(nodoActual.getHijoIzquierdo());
        int alturaPorDer=altura(nodoActual.getHijoDerecho());
        if(alturaPorIzq>alturaPorDer){
            return alturaPorIzq+1;
        }
        return alturaPorDer+1;


    }

    @Override
    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(this.raiz);
    }

//-------------------------------------------------------------------------
//--------------------------------------------------------------------------------
   //PREGUNTA  1
    @Override
    public List<K> recorridoInOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoEnInOrden(this.raiz,recorrido);
        return recorrido;
    }
    private void recorridoEnInOrden(NodoBinario<K,V>nodoActual,List<K> recorrido){
        if(NodoBinario.esNodoVacio(nodoActual)){//n=0
            return;
        }
        recorridoEnInOrden(nodoActual.getHijoIzquierdo(),recorrido);
        recorrido.add(nodoActual.getClave());
        recorridoEnInOrden(nodoActual.getHijoDerecho(),recorrido);

    }

    @Override
    public List<K> recorridoPostOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoEnPostOrden(this.raiz,recorrido);
        return recorrido;
    }
    private void recorridoEnPostOrden(NodoBinario<K,V>nodoActual,List<K> recorrido){
        if(NodoBinario.esNodoVacio(nodoActual)){//n=0
            return;
        }
        recorridoEnPostOrden(nodoActual.getHijoIzquierdo(),recorrido);
        recorridoEnPostOrden(nodoActual.getHijoDerecho(),recorrido);
        recorrido.add(nodoActual.getClave());

    }

    //PREGUNTA  2
       @Override
    public List<K> recorridoPostOrdenV2() {
           List<K>lista=new ArrayList<>();
           if(esArbolVacio()){
               return lista;
           }
           Stack<NodoBinario<K,V>>pilaNodos=new Stack<>();
           NodoBinario<K,V>actual=this.raiz;
           //el procesos inicial antes de iterar en la pila
           meterPilaPostOrden(pilaNodos,actual);
           //empezamos a iterar sobre la pila
           while(!pilaNodos.isEmpty()){
               actual=pilaNodos.pop();
               lista.add(actual.getClave());
               if(!pilaNodos.isEmpty()){
                   NodoBinario<K,V>tope=pilaNodos.peek();
                   if(!tope.esHijoDerechoVacio() && (tope.getHijoDerecho() != actual)){
                       meterPilaPostOrden(pilaNodos,tope.getHijoDerecho());
                   }
               }
           }
           return lista;
       }
    public void meterPilaPostOrden(Stack<NodoBinario<K,V>>pila,NodoBinario<K,V>nodo){
        while(!NodoBinario.esNodoVacio(nodo)){
            pila.push(nodo);
            if(!nodo.esHijoIzquierdoVacio()){
                nodo=nodo.getHijoIzquierdo();
            }else{
                nodo=nodo.getHijoDerecho();
            }
        }
    }
    //PREGUNTA  12
    public int cantidadDeNodosConUnHIjoNoVacio(){
        return cantidadNodosConHIjoNoVacio(this.raiz);
    }
    private int cantidadNodosConHIjoNoVacio(NodoBinario<K,V>nodoActual){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int porIzquierda=cantidadNodosConHIjoNoVacio(nodoActual.getHijoIzquierdo());
        int porDerecha=cantidadNodosConHIjoNoVacio(nodoActual.getHijoDerecho());
        if(nodoActual.esHijoDerechoVacio()){
            return porIzquierda+porDerecha+1;
        }
        return porDerecha+porIzquierda+1;
    }
  //EJERCICIO 13
  public int cantidadDeHijosVaciosConInOrden(){
      if(esArbolVacio()){
          return 0;
      }
      NodoBinario<K,V>nodoActual=this.raiz;
      int size=0;
          if(nodoActual.esHijoDerechoVacio()){
              nodoActual.getHijoDerecho();
              size++;
          } else if (nodoActual.esHijoIzquierdoVacio()) {
              nodoActual.getHijoIzquierdo();
              size++;
          }
          return size;
  }
//EJERCICIO 18
public boolean verificarSiSonArbolesSimilares(ArbolBinario<K,V> arbol){
    return verificarArbolesSimilares(this.raiz,arbol.raiz);
}

    @Override
    public boolean NodosCompletosEnNivel(int niv) {
        return false;
    }

    @Override
    public boolean sonSimilares(ArbolMViasBusqueda<K, V> arbol2) {
        return false;
    }

    private boolean verificarArbolesSimilares(NodoBinario<K,V>nodoActual1,NodoBinario<K,V>nodoActual2){

        if(NodoBinario.esNodoVacio(nodoActual1) && NodoBinario.esNodoVacio(nodoActual2)){
            return true;
        }
        boolean respuestaPorIzquierda=verificarArbolesSimilares(nodoActual1.getHijoIzquierdo(),nodoActual2.getHijoIzquierdo());
        boolean respuestaPorDerecha=verificarArbolesSimilares(nodoActual1.getHijoDerecho(),nodoActual2.getHijoDerecho());
        if(NodoBinario.esNodoVacio(nodoActual1) && !NodoBinario.esNodoVacio(nodoActual2)){
            return false;
        }
        if(!NodoBinario.esNodoVacio(nodoActual1) && NodoBinario.esNodoVacio(nodoActual2)){
            return false;
        }
        return respuestaPorIzquierda && respuestaPorDerecha;
    }
    public static void main(String ar[]) throws ExcepcionClaveNoExiste {
        IArbolBusqueda<Integer,String> arbolBB=new ArbolBinario<>();
        arbolBB.insertar(3,"dilker");
        arbolBB.insertar(2,"deglis");
        arbolBB.insertar(1,"delgar");
        arbolBB.insertar(4,"dilker");
        arbolBB.insertar(5,"deglis");
        arbolBB.insertar(6,"delgar");
        arbolBB.insertar(7,"dilker");
        arbolBB.insertar(16,"deglis");
        arbolBB.insertar(15,"delgar");
        arbolBB.insertar(14,"deglis");
        arbolBB.insertar(13,"delgar");
        arbolBB.insertar(12,"delgar");
        //////////////////////////////////////////////////////
        IArbolBusqueda<Integer,String> arbol2=new ArbolBinario<>();
        arbolBB.insertar(3,"dilker");
        arbolBB.insertar(2,"deglis");
        arbolBB.insertar(1,"delgar");
        arbolBB.insertar(4,"dilker");
        arbolBB.insertar(5,"deglis");
        arbolBB.insertar(6,"delgar");
        arbolBB.insertar(7,"dilker");
        arbolBB.insertar(16,"deglis");
        arbolBB.insertar(15,"delgar");
        arbolBB.insertar(14,"deglis");
        arbolBB.insertar(13,"delgar");
        arbolBB.insertar(12,"delgar");

        //EJERCICIO1
        System.out.println(arbolBB.recorridoInOrden());
        System.out.println(arbolBB.recorridoPostOrden());
        //EJERCICIO2
        System.out.println(arbolBB.recorridoPostOrdenV2());
        //EJERCICIO 12
        System.out.println(arbolBB.cantidadDeNodosConUnHIjoNoVacio());
        //EJERCICIO 13
        System.out.println(arbolBB.cantidadDeHijosVaciosConInOrden());
        //EJERCICIO 18
        System.out.println(arbolBB.verificarSiSonArbolesSimilares((ArbolBinario<Integer, String>) arbol2));
    }

}




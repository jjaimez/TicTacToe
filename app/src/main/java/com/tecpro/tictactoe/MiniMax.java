package com.tecpro.tictactoe;

import android.util.Pair;

/**
 * Created by jjaimez on 12/12/15.
 */
public class MiniMax {

    public static final int CASILLA_VACIA = 0;
    public static final int CASILLA_PERSONA = 1;
    public static final int CASILLA_ORDENADOR = 2;
    public static final int numCols = 3;
    public static final int numFils = 3;

    private int tableroActual[][];


    /**
     * Recibe los datos del tablero de juego.
     *
     */
    public MiniMax() {
        super();
        this.tableroActual = new int[numCols][numFils];
    }

    /**
     * Es el turno del ordenador y en base al tablero actual decidimos el movimiento a realizar.
     */
    public Pair<Integer,Integer> minimax(int[][] tableroParam){

        this.tableroActual = tableroParam;

        int posX=-1;
        int posY=-1;
        int aux=-9999;
        int mejor=-9999;

        for (int x=0;x<this.numCols;x++){
            for (int y=0;y<this.numFils;y++){

                if (isCasillaVacia(x,y)){
                    marcarCasilla(x,y,CASILLA_ORDENADOR);
                    aux = min();
                    if (aux>mejor) {
                        mejor=aux;
                        posX=x;
                        posY=y;
                    }
                    marcarCasilla(x,y,CASILLA_VACIA);
                }
            }
        }
        return new Pair<>(posX,posY);
    }

    /**
     * El último jugador que marcó alguna casilla fue el ordenador, por lo que analizamos los posibles
     * movimientos de la persona.
     *
     * Si estamos en un nodo hoja devolvemos la valoración del tablero, puede que haya ganado el ordenador
     * o puede que sigamos empatados, así que hay dos posibles valores: 1 (gana el ordenador)
     * y 0 (seguimos empatados).
     *
     * Si no es nodo hoja recorremos las casillas vacías simulando ser el jugador humano, y llamando
     * a continuación al método MAX. Llamamos a MAX porque en el siguiente turno le tocará jugar a la CPU
     * y nos interesa maximizar su resultado.
     */
    private int min(){

        if (haGanadoJugador(CASILLA_ORDENADOR)){
            //Si ha ganado el ordenador devolvemos el valor del tablero
            return 1;
        } else if (isTableroLleno()){
            //Si el tablero está lleno y no ha ganado el ordenador hemos empatado
            return 0;
        } else {
            //Si el tablero no está lleno y no ha ganado el ordenador, recorremos todos
            //los posibles movimientos restantes y los valoramos llamando a max()
            int aux=9999;
            int mejor=9999;
            for (int x=0;x<this.numCols;x++){
                for (int y=0;y<this.numFils;y++){

                    if (isCasillaVacia(x,y)){
                        marcarCasilla(x,y,CASILLA_PERSONA);
                        aux = max();
                        if (aux<mejor) {
                            mejor=aux;
                        }
                        marcarCasilla(x,y,CASILLA_VACIA);
                    }
                }
            }
            return mejor;
        }
    }

    /**
     * El último jugador que marcó alguna casilla fue la persona, por lo que analizamos los posibles
     * movimientos del ordenador.
     *
     * Si estamos en un nodo hoja devolvemos la valoración del tablero, puede que haya ganado la persona
     * o puede que sigamos empatados, así que hay dos posibles valores: -1 (gana la persona)
     * y 0 (seguimos empatados).
     *
     * Si no es nodo hoja recorremos las casillas vacías simulando ser el ordenador, y llamando
     * a continuación al método MIN. Llamamos a MIN porque en el siguiente turno le tocará jugar al humano
     * y nos interesa minimizar su resultado.
     */
    private int max(){

        if (haGanadoJugador(CASILLA_PERSONA)){
            //Si ha ganado la persona devolvemos el valor del tablero
            return -1;
        } else if (isTableroLleno()){
            //Si el tablero está lleno y no ha ganado la persona hemos empatado
            return 0;
        } else {
            ///Si el tablero no está lleno y no ha ganado la persona, recorremos todos
            //los posibles movimientos restantes y los valoramos llamando a min()
            int aux=-9999;
            int mejor=-9999;
            for (int x=0;x<this.numCols;x++){
                for (int y=0;y<this.numFils;y++){

                    if (isCasillaVacia(x,y)){
                        marcarCasilla(x,y,CASILLA_ORDENADOR);
                        aux = min();
                        if (aux>mejor) {
                            mejor=aux;
                        }
                        marcarCasilla(x,y,CASILLA_VACIA);
                    }
                }
            }
            return mejor;
        }
    }

    /**
     * Determina si una determinada posición del tablero está
     * o no vacía.
     *
     * @param x Fila de la casilla a analizar.
     * @param y Columna de la casilla a analizar.
     *
     * @return True cuando la casilla está vacía
     */
    private boolean isCasillaVacia(int x, int y){
        return tableroActual[x][y]==CASILLA_VACIA;
    }

    /**
     * Ocupa la casilla indicada con la ficha recibida.
     *
     * @param x Fila de la casilla a marcar.
     * @param y Columna de la casilla a marcar.
     * @param ficha Ficha a colocar.
     */
    private void marcarCasilla(int x, int y, int ficha){
        tableroActual[x][y]=ficha;
    }

    /**
     * Determina si el tablero tiene casillas libres.
     *
     * @return True si hay casillas no ocupadas.
     */
    private boolean isTableroLleno(){
        for (int x=0;x<3;x++){
            for (int y=0;y<3;y++){
                if (isCasillaVacia(x,y)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Determina si el jugador que maneja la ficha recibida
     * por parámetro ha ganado la partida.
     *
     * @param ficha Ficha que podría haber ganado
     *
     * @return True cuando el jugador con la ficha recibida ha ganado.
     */
    public boolean haGanadoJugador(int ficha){

        //Verifica si el jugador ha ganado en las diagonales
        if((tableroActual[0][0]==ficha && tableroActual[1][1]==ficha && tableroActual[2][2]==ficha)||
                (tableroActual[0][2]==ficha && tableroActual[1][1]==ficha && tableroActual[2][0]==ficha)) {
            return true;
        } else {
            //Verifica si el jugador ha ganado en una fila
            for(int i=0;i<3;i++) {
                if(tableroActual[i][0]==ficha && tableroActual[i][1]==ficha && tableroActual[i][2]==ficha) {
                    return true;
                }
            }
            //Verifica si el jugador ha ganado en una columna
            for(int i=0;i<3;i++) {
                if(tableroActual[0][i]==ficha && tableroActual[1][i]==ficha && tableroActual[2][i]==ficha) {
                    return true;
                }
            }
        }
        return false;
    }
}

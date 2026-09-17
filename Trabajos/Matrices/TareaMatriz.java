/*programa para operaciones con matrices (imprimir, sumar, espiral)
Autor: Juan Diego Santiago Valencia
Septiembre 2026
GNU GPLv3
*/

import java.util.Scanner;
public class Matrices {
    public static final int[][] MATRIZUNO={{3,2,2,3,}, {4,2,65,7}};

    public static void main(String[]args){
        menu();
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        //me dio varios dolores de cabeza poder sacar matriz elegida del do
        int[][] matrizElegida=null;
        //casi que puedo imprimir la matriz elegida que cosa tan tonta
        boolean elegirMatriz=false;
        do {
            System.out.println("0. Elegir mi matriz (ignorar si desea usar matrices predetermidas, solo matrices cuadradas o rectangulares)");
            System.out.println("1. Imprimir una matriz");
            System.out.println("2. Sumar todos los elementos de la matriz");
            System.out.println("3. sumar los nuemeros de la diagonal principal");
            System.out.println("4. Dibujar una matriz espiral de numeros");
            System.out.println("5. Saliir");
            opcion = sc.nextInt();
            if(opcion==0){
                elegirMatriz=true;
                System.out.print("Ingresar le numero de filas");
                int numFilas= sc.nextInt();
                System.out.print("Ingresar le numero de columnas");
                int numColumnas= sc.nextInt();
                matrizElegida= new int[numFilas][numColumnas];
                for(int i=0; i<matrizElegida.length;i++){
                    for (int j=0; j<matrizElegida[i].length;j++){
                        System.out.println("Ingresa el elmento en la posicion fila numero "+i+", y columna numero "+j);
                        matrizElegida[i][j]=sc.nextInt();
                    }
                }

            }if(opcion!=0) {
                switch (opcion) {

                    case 1:
                        if (elegirMatriz) {
                            imprimirMatriz(matrizElegida);
                        } else {
                            imprimirMatriz(MATRIZUNO);
                        }
                        break;
                    case 2:
                        int acumulador;
                        if (elegirMatriz) {
                            int sumaDeElementosMatriz = sumarDeElementosMatriz(matrizElegida);
                            acumulador = sumaDeElementosMatriz;
                        } else {
                            int sumaDeElementosMatriz = sumarDeElementosMatriz(MATRIZUNO);
                            acumulador = sumaDeElementosMatriz;
                        }
                        System.out.println("La suma de cada elemento de la matriz es " + acumulador);
                        break;
                    case 3:
                        int valordiagonal;
                        if (elegirMatriz) {
                            int sumaDiagonal = sumarDiagonalMatriz(matrizElegida);
                            valordiagonal = sumaDiagonal;
                        } else {
                            int sumaDiagonal = sumarDiagonalMatriz(MATRIZUNO);
                            valordiagonal = sumaDiagonal;
                        }

                        System.out.println("La suma de cada elemento de la diagonal es " + valordiagonal);
                        break;
                    case 4:
                        if (elegirMatriz) {
                            int[][] matrizEspiral = convertirMatrizEspiral(matrizElegida);
                            imprimirMatriz(matrizEspiral);
                        } else {
                            int[][] matrizEspiral = convertirMatrizEspiral(MATRIZUNO);
                            imprimirMatriz(matrizEspiral);
                        }

                        break;
                    case 5:
                        System.out.print("Byeeeeeeeeee");
                        break;
                    default:
                        System.out.print("Opcion no valida, intenta de nuevo");
                }

            }}while(opcion!=5);
        sc.close();
    }

    public static void imprimirMatriz (int[][] matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
    public static int sumarDeElementosMatriz (int[][] matriz){
        int acumulador=0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                acumulador+=matriz[i][j];
            }
        }
        return acumulador;
    }
    public static int sumarDiagonalMatriz (int[][] matriz){
        int acumulador=0;
        for(int i =0; i<matriz[0].length && i<matriz.length; i++){
            acumulador+=matriz[i][i];
        }
        return acumulador;
    }
    public static int[][] convertirMatrizEspiral (int[][]matriz){
            int[][] matrizNueva=new int[matriz.length][matriz[0].length];


            int filaInicio = 0;
            int filaFin = matriz.length - 1;
            int columnaInicio = 0;
            int columnaFin = matriz[0].length - 1;
            int numero = 1;

            while (filaInicio <= filaFin && columnaInicio <= columnaFin) {

                // 1. Ir hacia la derecha por filaInicio
                for (int j = columnaInicio; j <= columnaFin; j++) {
                    matrizNueva[filaInicio][j] = numero++;
                }
                filaInicio++;

                // 2. Bajar por columnaFin
                for (int i = filaInicio; i <= filaFin; i++) {
                    matrizNueva[i][columnaFin] = numero++;
                }
                columnaFin--;

                // 3. Ir hacia la izquierda por filaFin (si aun es valido)
                if (filaInicio <= filaFin) {
                    for (int j = columnaFin; j >= columnaInicio; j--) {
                        matrizNueva[filaFin][j] = numero++;
                    }
                    filaFin--;
                }

                // 4. Subir por columnaInicio (si aun es valido)
                if (columnaInicio <= columnaFin) {
                    for (int i = filaFin; i >= filaInicio; i--) {
                        matrizNueva[i][columnaInicio] = numero++;
                    }
                    columnaInicio++;
                }
            }
            return matrizNueva;

    }
}

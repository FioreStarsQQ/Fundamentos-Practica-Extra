/*
EJERCICIO 1:  Implementar un videojuego (iterativo) en modo gráfico. Se debe simular una guerra entre 2 reinos.
La guerra está compuesta de batallas.Ingresar los nombres de ambos reinos. Cada batalla se da entre 2 soldados, 
uno por cada reino. Los tipos de soldado son Arquero, Espadachín y Caballero, los cuales tienen cierto
nivel de vida. Para cada batalla se generará aleatoriamente el tipo de soldado y su nivel de vida según la siguiente tabla.
Tipo de Soldado         Nivel de Vida
Arquero                 3 - 5
Espadachín              5 – 7
Caballero               7 - 9
Para determinar al ganador de la batalla se debe considerar las probabilidades (balance de poder) proporcionales al 
nivel de vida de cada soldado.
Ejemplo:
R1: Arquero 5 vs R2: Espadachín 5 -> probabilidades son 50%
para ambos
R1: Arquero 4 vs R2: Caballero 8 -> probabilidades son 33,33% y
66,66%
La elección del ganador de la batalla es aleatoria pero de acuerdo a las probabilidades generadas. Cuando acabe la guerra
se deberá mostrar el marcador final de las batallas ganadas y qué reino ganó la guerra (se permite empates).
Al acabar una guerra el videojuego deberá permitir empezar otra desde cero.
POR: Fiorela Clariza Quispe Quispe
*/
import javax.swing.*;
import java.util.Random;
import java.text.DecimalFormat;

public class Proyecto {
    public static void main(String[] args) {
        Random numRandom = new Random();
        String reino, reino1, reino2, personaje = "NN", personaje1 = "NN", personaje2 = "NN";
        int i, opcionPersonaje, nivelPersonaje = 0, errorPersonaje = 0, nivelPersonaje1 = 0, nivelPersonaje2 = 0,
                continuarBatalla = 1, continuarGuerra = 1, batallasGanadas1 = 0, batallasGanadas2 = 0;
        double probabilidadReino1, probabilidadReino2;

        // Guerras
        for (; continuarGuerra == 1;) {
            reino1 = JOptionPane.showInputDialog(null,
                    "Bienvenido(a) al juego.\nIngrese el nombre del primer reino:");
            reino2 = JOptionPane.showInputDialog(null, "Ingrese el nombre del segundo reino:");
            // Batallas
            for (; continuarBatalla == 1;) {
                for (i = 1; i <= 2; i++) {
                    // Nombre de reinos enfrentados
                    if (i == 1) {
                        reino = reino1;
                    } else {
                        reino = reino2;
                    }
                    // Personajes
                    do {
                        errorPersonaje = 0;
                        opcionPersonaje = Integer.parseInt(JOptionPane.showInputDialog(null,
                                "***** REINO \"" + reino.toUpperCase()
                                        + "\" *****\nEstos son los personajes que puedes elegir para enfrentarlos:\n[ 1 ] Arquero\n[ 2 ] Espadachín\n[ 3 ] Caballero"
                                        + "\n¿Qué personaje eliges para este reino?\n***[ # ] Ingresa el número que corresponde al personaje."));
                        // Asignación del nivel del personaje
                        switch (opcionPersonaje) {
                            case 1:
                                personaje = "Arquero";
                                nivelPersonaje = Integer.parseInt(JOptionPane.showInputDialog(null,
                                        "Bien has elegido al Arquero, ahora ¿con qué nivel quieres que se enfrente este personaje?"
                                                + "\nEstos son los niveles disponibles:\n[ 3 ] Nivel 3\n[ 4 ] Nivel 4\n[ 5 ] Nivel 5\n***[ # ] Ingresa el número que corresponde al nivel."));
                                if (!(nivelPersonaje >= 3 && nivelPersonaje <= 5)) {
                                    JOptionPane.showMessageDialog(null,
                                            "No has elegido una opción válida. Inténtalo de nuevo...\nRecuerda que solo se permite ingresar el valor de los recuadros.");
                                    errorPersonaje = 1;
                                }
                                break;
                            case 2:
                                personaje = "Espadachín";
                                nivelPersonaje = Integer.parseInt(JOptionPane.showInputDialog(null,
                                        "Muy bien has elegido al Espadachín, ahora ¿con qué nivel quieres que se enfrente este personaje?"
                                                + "\nEstos son los niveles disponibles:\n[ 5 ] Nivel 5\n[ 6 ] Nivel 6\n[ 7 ] Nivel 7\n***[ # ] Ingresa el número que corresponde al nivel."));
                                if (!(nivelPersonaje >= 5 && nivelPersonaje <= 7)) {
                                    JOptionPane.showMessageDialog(null,
                                            "No has elegido una opción válida. Inténtalo de nuevo...\nRecuerda que solo se permite ingresar el valor de los recuadros.");
                                    errorPersonaje = 1;
                                }
                                break;
                            case 3:
                                personaje = "Caballero";
                                nivelPersonaje = Integer.parseInt(JOptionPane.showInputDialog(null,
                                        "Bien has elegido el Caballero, ahora ¿con qué nivel quieres que se enfrente este personaje?"
                                                + "\nEstos son los niveles disponibles:\n[ 7] Nivel 7\n[ 8 ] Nivel 8\n[ 9 ] Nivel 9\n***[ # ] Ingresa el número que corresponde al nivel."));
                                if (!(nivelPersonaje >= 7 && nivelPersonaje <= 9)) {
                                    JOptionPane.showMessageDialog(null,
                                            "No has elegido una opción válida. Inténtalo de nuevo...\nRecuerda que solo se permite ingresar el valor de los recuadros.");
                                    errorPersonaje = 1;
                                }
                                break;
                            default:
                                JOptionPane.showMessageDialog(null,
                                        "No has elegido una opción válida. Inténtalo de nuevo...\nRecuerda que solo se permite ingresar el valor de los recuadros.");
                                errorPersonaje = 1;
                        }
                    } while (errorPersonaje == 1);
                    // Actualización de datos (nivel - personaje)
                    if (i == 1) {
                        nivelPersonaje1 = nivelPersonaje;
                        personaje1 = personaje;
                    } else {
                        nivelPersonaje2 = nivelPersonaje;
                        personaje2 = personaje;
                    }
                }
                DecimalFormat df = new DecimalFormat("0.00");
                // Calculo de la probabilidad de ganar
                probabilidadReino1 = nivelPersonaje1 * 100.00 / (nivelPersonaje1 + nivelPersonaje2);
                probabilidadReino2 = nivelPersonaje2 * 100.00 / (nivelPersonaje1 + nivelPersonaje2);
                // Elección al azar del valor porcentual que corresponde al rango del personaje
                // que ganará
                double probabilidadGanador = (100.00 - 0) * numRandom.nextDouble();
                // El prsonaje del reino 1 tiene el rango porcentual de cero hasta el valor
                // calculado en su probabilidad de ganar, el otro rango (hasta 100) le pertenece
                // al personaje del reino opuesto.
                if (probabilidadGanador <= probabilidadReino1) {
                    JOptionPane.showMessageDialog(null,
                            personaje1.toUpperCase() + " Vs. " + personaje2.toUpperCase() + "\nGanó el reino "
                                    + reino1.toUpperCase()
                                    + " con el " + personaje1 + "\n- La probabilidad de ganar del reino "
                                    + reino1.toUpperCase() + " fue " + df.format(probabilidadReino1) + " %"
                                    + "\n- La probabilidad de ganar del reino " + reino2.toUpperCase() + " fue "
                                    + df.format(probabilidadReino2) + " %\nDato: el número elegido al azar fue: "
                                    + df.format(probabilidadGanador)
                                    + "\n* Explicación: El primer rango de números (de 0 a "
                                    + df.format(probabilidadReino1) + ") pertenecen al reino " + reino1.toUpperCase()
                                    + " los otros números al reino contrincante.");
                    batallasGanadas1++;
                } else {
                    JOptionPane.showMessageDialog(null,
                            personaje1.toUpperCase() + " Vs. " + personaje2.toUpperCase() + "\nGanó el reino "
                                    + reino2.toUpperCase()
                                    + " con el " + personaje2 + "\n- La probabilidad de ganar del reino "
                                    + reino1.toUpperCase() + " fue " + df.format(probabilidadReino1) + " %"
                                    + "\n- La probabilidad de ganar del reino " + reino2.toUpperCase() + " fue "
                                    + df.format(probabilidadReino2) + " %\nDato: el número elegido al azar fue: "
                                    + df.format(
                                            probabilidadGanador)
                                    + "\n* Explicación: El primer rango de números (de 0 a "
                                    + df.format(probabilidadReino1) + ") pertenecen al reino " + reino1.toUpperCase()
                                    + " los otros números al reino contrincante.");
                    batallasGanadas2++;
                }
                JOptionPane.showMessageDialog(null,
                        "***** MARCADOR *****\n- " + reino1.toUpperCase() + ": " + batallasGanadas1
                                + " batallas ganadas\n- "
                                + reino2.toUpperCase() + ": " + batallasGanadas2
                                + " batallas ganadas\n");
                // Pregunta si desea inciar otra batalla
                if (JOptionPane.showConfirmDialog(null, "¿Desea empezar otra batalla?",
                        "SE VIENE OTRA BATALLA...",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    // OPCION SI
                    continuarBatalla = 1;
                } else {
                    // OPCION NO
                    continuarBatalla = 0;
                }
            }
            // Marcador
            JOptionPane.showMessageDialog(null,
                    "***** MARCADOR FINAL *****\n- " + reino1.toUpperCase() + ": " + batallasGanadas1
                            + " batallas ganadas\n- " + reino2.toUpperCase() + ": " + batallasGanadas2
                            + " batallas ganadas\n");
            if (batallasGanadas1 > batallasGanadas2) {
                JOptionPane.showMessageDialog(null, "¡FELICIDADES AL REINO \"" + reino1.toUpperCase() + "\"!");

            } else if (batallasGanadas2 > batallasGanadas1) {
                JOptionPane.showMessageDialog(null, "¡FELICIDADES AL REINO \"" + reino2.toUpperCase() + "\"!");
            } else {
                JOptionPane.showMessageDialog(null, "¡UY! AMBOS REINOS HAN EMPATADO");
            }
            // Pregunta si desea inciar otra guerra
            if (JOptionPane.showConfirmDialog(null, "¿Desea empezar otra guerra?",
                    "UNA NUEVA GUERRA...",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                // OPCION SI
                i = 1;
                batallasGanadas1 = 0;
                batallasGanadas2 = 0;
                continuarBatalla = 1;
                continuarGuerra = 1;
            } else {
                // OPCION NO
                continuarGuerra = 0;
            }
        }
    }
}
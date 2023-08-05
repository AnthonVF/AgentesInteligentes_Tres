package holajade.AgenteEjemplo1;

import Comunicacion.EnviarMSJ;
import Modelo.DatosAgentes;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author ZAMBOY
 */
public class Agente1 extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    @Override
    protected void takeDown() {

    }

    class Comportamiento extends CyclicBehaviour {

        @Override
        public void action() {
            // Aquí se generará el número aleatorio entre 0 y 38
            int numeroAleatorio = generarNumeroAleatorio(0, 38);
            System.out.println("AGENTE#1 Valor de Temperatura: " + numeroAleatorio);
            // Crear un objeto DatosAgentes
            DatosAgentes datos = new DatosAgentes();
            datos.temperatura = numeroAleatorio;

            // Enviamos el mensaje al Agente 2
            EnviarMSJ.enviarMSJ(ACLMessage.INFORM, "AG2", this.getAgent(), "AG1-AG2", null, false, datos);
            // Pausa de 1 segundo antes de la siguiente iteración
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private int generarNumeroAleatorio(int min, int max) {
            return (int) (Math.random() * (max - min + 1) + min);
        }
    }
}

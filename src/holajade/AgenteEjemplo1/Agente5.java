package holajade.AgenteEjemplo1;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 *
 * @author ZAMBOY
 */
public class Agente5 extends Agent {

    private int umbral1 = 12; // Umbral para el elemento 1
    private int umbral2 = 22; // Umbral para el elemento 2
    private int umbral3 = 29; // Umbral para el elemento 3

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
            // Recibir el arreglo del Agente 2 como un objeto
            Object contenido = receiveRequest();

            if (contenido instanceof int[]) {
                int[] arregloRecibido = (int[]) contenido;

                // Comparar los elementos del arreglo recibido con los umbrales
                boolean activarRiego = compareElements(arregloRecibido);

                if (activarRiego) {
                    System.out.println("Activando sistema de riego");
                    // Aquí puedes agregar la lógica para activar el sistema de riego
                } else {
                    System.out.println("No se activa el sistema de riego");
                    // Aquí puedes agregar la lógica para no activar el sistema de riego
                }
            }
        }

        private Object receiveRequest() {
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
            ACLMessage msg = myAgent.receive(mt);

            if (msg != null) {
                try {
                    return msg.getContentObject();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        private boolean compareElements(int[] arreglo) {
            // Comparar cada elemento del arreglo con los umbrales
            // y determinar si deben activar el sistema de riego.
            // Puedes utilizar condiciones para realizar la comparación.

            // Comparación para el elemento 1
            if (arreglo[0] > umbral1) {
                return true; // Se cumple el umbral, activar el sistema de riego
            }

            // Comparación para el elemento 2
            if (arreglo[1] > umbral2) {
                return true; // Se cumple el umbral, activar el sistema de riego
            }

            // Comparación para el elemento 3
            if (arreglo[2] > umbral3) {
                return true; // Se cumple el umbral, activar el sistema de riego
            }

            return false; // No se cumple ningún umbral, no activar el sistema de riego
        }
    }
}

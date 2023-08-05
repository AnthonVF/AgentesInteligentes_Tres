package holajade.AgenteEjemplo1;

//import Comunicacion.EnviarMSJ;
//import holajade.Contenedor.Contenedor;
//import jade.core.AID;
//import jade.core.Agent;
//import jade.core.behaviours.CyclicBehaviour;
//import jade.lang.acl.ACLMessage;
//import jade.lang.acl.MessageTemplate;
//import jade.wrapper.AgentContainer;
//import jade.wrapper.StaleProxyException;
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author ZAMBOY
// */
//public class Agente2 extends Agent {
//
//    private List<Serializable> datosAgentes;
//
//    @Override
//    protected void setup() {
//        datosAgentes = new ArrayList<>();
//        addBehaviour(new Comportamiento());
//    }
//
//    @Override
//    protected void takeDown() {
//
//    }
//
//    class Comportamiento extends CyclicBehaviour {
//
//        private boolean initialized = false;
//
//        @Override
//        public void action() {
//            if (!initialized) {
//                // Configuración inicial del Agente 2
//                datosAgentes = new ArrayList<>();
//                initialized = true;
//            }
//
//            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
//            ACLMessage msg = myAgent.receive(mt);
//
//            if (msg != null) {
//                Serializable contenido = null;
//                try {
//                    contenido = msg.getContentObject();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                if (contenido != null) {
//                    datosAgentes.add(contenido);
//                    System.out.println("Agente2: Recibido contenido del Agente " + msg.getSender().getLocalName());
//                }
//
//                // Realizar otras acciones relacionadas con los datos recibidos
//                // Si ya se han recibido los datos de los agentes 1, 3 y 4, enviarlos al Agente 5
//                if (datosAgentes.size() == 3) {
//                    // Imprimir el arreglo
//                    System.out.println("Datos de los agentes recibidos: ");
//                    for (Serializable dato : datosAgentes) {
//                        System.out.println(dato.toString());
//                    }
//                    enviarDatosAlAgente5();
//                    // Limpiar el arreglo para la próxima iteración
//                    datosAgentes.clear();
//                }
//            } else {
//                block();
//            }
//        }
//
//        private void enviarDatosAlAgente5() {
//            // Crear un arreglo de objetos con los datos recibidos
//            Object[] datos = datosAgentes.toArray();
//
//            // Enviar el arreglo de datos al Agente 5
//            ACLMessage mensaje = new ACLMessage(ACLMessage.REQUEST);
//            mensaje.addReceiver(getAID("Agente5"));
//            try {
//                mensaje.setContentObject(datos);
//                send(mensaje);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            System.out.println("Agente2: Enviando datos al Agente5");
//        }
//    }
//}
//OPCIÓN 2
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Agente2 extends Agent {

    private List<Modelo.DatosAgentes> datosAgentes;

    @Override
    protected void setup() {
        datosAgentes = new ArrayList<>();
        addBehaviour(new Comportamiento());
    }

    class Comportamiento extends CyclicBehaviour {

        @Override
        public void action() {
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM); //Verifica si en cada iteración hay algún mensaje recibido
            ACLMessage msg = myAgent.receive(mt); //Alegoría a una contestadora

            if (msg != null) {
                Serializable contenido = null;
                try {
                    contenido = msg.getContentObject(); //Extrae el contenido del mensaje
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (contenido != null) {
                    datosAgentes.add((Modelo.DatosAgentes) contenido);
                    System.out.println("Agente2: Recibido contenido del Agente " + msg.getSender().getLocalName()); // Almacena el contenido del mensaje
                }

                // Verificar si se cumplen los umbrales y enviar los datos
                if (datosAgentes.size() == 3) {
                    boolean activarRiego = verificarUmbrales();
                    if (activarRiego) {
                        System.out.println("Activando sistema de riego");
                    } else {
                        System.out.println("No se activa el sistema de riego");
                    }
                    datosAgentes.clear(); //Se vacía la lista para dar paso a una nueva
                }
            } else {
                block();
            }
        }

        private boolean verificarUmbrales() {
            int umbralRadiacionUV = 25;
            int umbralTemperatura = 25;
            int umbralHumedad = 25;

            for (Modelo.DatosAgentes datos : datosAgentes) {
                if (datos.radiacionUV <= umbralRadiacionUV && datos.temperatura <= umbralTemperatura && datos.humedad <= umbralHumedad) {
                    return false; // No se cumplen los umbrales, no se activa el riego
                }
            }

            return true; // Se cumplen los umbrales, se activa el riego
        }
    }
}

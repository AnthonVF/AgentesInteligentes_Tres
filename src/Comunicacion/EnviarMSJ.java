package Comunicacion;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZAMBOY
 */
public class EnviarMSJ {

    public static void enviarMSJ(int tipo, String receptor, Agent agenteEmisor, String conversationID, String contenido,
            boolean contenidoString, Serializable contenidoObject) {
        ACLMessage acl = new ACLMessage(tipo); //ACLMessage.INFORM
        AID aid = new AID();
        aid.setLocalName(receptor);
        acl.addReceiver(aid);
        acl.setSender(agenteEmisor.getAID());
        acl.setLanguage(FIPANames.ContentLanguage.FIPA_SL);
        acl.setConversationId(conversationID);
        if (contenidoString) {
            acl.setContent(contenido);
        } else {
            try {
                acl.setContentObject(contenidoObject);
            } catch (IOException ex) {
                Logger.getLogger(EnviarMSJ.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        agenteEmisor.send(acl);
    }
}

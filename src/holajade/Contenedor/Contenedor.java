package holajade.Contenedor;

import holajade.AgenteEjemplo1.Agente1;
import holajade.AgenteEjemplo1.Agente2;
import holajade.AgenteEjemplo1.Agente3;
import holajade.AgenteEjemplo1.Agente4;
import holajade.AgenteEjemplo1.Agente5;
import jade.wrapper.AgentContainer;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZAMBOY
 */
public class Contenedor {

    AgentContainer contenedorAgentes;

    public void IniciarContenedor() {
        jade.core.Runtime runtime = jade.core.Runtime.instance();
        runtime.setCloseVM(true);
        Profile profile = new ProfileImpl(null, 1099, null);
        contenedorAgentes = runtime.createMainContainer(profile);
        iniciarAgentes();
    }

    private void iniciarAgentes() {
        try {
            contenedorAgentes.createNewAgent("AG2", Agente2.class.getName(), null).start();
            contenedorAgentes.createNewAgent("AG5", Agente5.class.getName(), null).start();
            contenedorAgentes.createNewAgent("AG1", Agente1.class.getName(), null).start();
            contenedorAgentes.createNewAgent("AG3", Agente3.class.getName(), null).start();
            contenedorAgentes.createNewAgent("AG4", Agente4.class.getName(), null).start();
        } catch (StaleProxyException ex) {
            Logger.getLogger(Contenedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

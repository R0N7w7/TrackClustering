package storeApp.agents;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.UnreadableException;
import storeApp.classifiers.TrackClusterer;
import storeApp.models.ClusterResult;
import storeApp.models.Track;

import java.io.Serializable;
import java.util.List;

public class ProcessorAgent extends Agent {
    protected void setup() {
        System.out.println("ProcesadorAgent: ¡Listo para recibir mensajes!");
        Object[] args = getArguments();
        List<Track> tracks = (List<Track>) args[0];

        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    System.out.println("ProcesadorAgent: Mensaje recibido del agente agrupador");

                    try {
                        TrackClusterer clusterer = (TrackClusterer) msg.getContentObject();
                        ClusterResult clusterResult = clusterer.getClusterResult(tracks);

                        ACLMessage responseMsg = new ACLMessage(ACLMessage.INFORM);
                        responseMsg.addReceiver(getAID("IOAgent")); // Enviar al IO
                        responseMsg.setContentObject((Serializable) clusterResult);

                        send(responseMsg);
                        System.out.println("ProcesadorAgent: Mensaje enviado al IO.");
                    } catch (UnreadableException e) {
                        throw new RuntimeException(e);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    block();
                }
            }
        });
    }
}

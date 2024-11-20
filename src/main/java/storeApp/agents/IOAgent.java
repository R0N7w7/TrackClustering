package storeApp.agents;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import storeApp.models.Track;
import storeApp.models.ClusterResult;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class IOAgent extends Agent {

    private BlockingQueue<ClusterResult> resultQueue;

    @Override
    protected void setup() {
        System.out.println("AgentIO: ¡Iniciando flujo!");
        Object[] args = getArguments();
        List<Track> tracks = (List<Track>) args[0];
        resultQueue = (BlockingQueue<ClusterResult>) args[1];

        addBehaviour(new OneShotBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.addReceiver(getAID("PreprocessorAgent"));
                try {
                    msg.setContentObject((Serializable) tracks);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                send(msg);
                System.out.println("AgentIO: Mensaje enviado al Preprocesador.");
            }
        });

        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    try {
                        ClusterResult clusterResult = (ClusterResult) msg.getContentObject();
                        System.out.println("AgentIO: Resultado de la agrupación recibido.");

                        resultQueue.put(clusterResult);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    block();
                }
            }
        });
    }
}
package storeApp.agents;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.UnreadableException;
import storeApp.classifiers.TrackClusterer;
import weka.core.Instances;

import java.io.Serializable;

public class ClustererAgent extends Agent {
    protected void setup() {
        System.out.println("AgrupadorAgent: ¡Listo para recibir mensajes!");

        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    System.out.println("AgrupadorAgent: Mensaje recibido deñ agente preprocesador");

                    try {
                        Instances trackInstances = (Instances) msg.getContentObject();
                        TrackClusterer clusterer = new TrackClusterer(trackInstances);

                        ACLMessage forwardMsg = new ACLMessage(ACLMessage.REQUEST);
                        forwardMsg.addReceiver(getAID("ProcessorAgent"));
                        forwardMsg.setContentObject((Serializable) clusterer);

                        send(forwardMsg);
                        System.out.println("AgrupadorAgent: Mensaje enviado al Procesador.");

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

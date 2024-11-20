package storeApp.agents;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.UnreadableException;
import storeApp.models.Track;
import storeApp.utils.TrackArffMapper;
import weka.core.Instances;

import java.io.IOException;
import java.util.List;

public class PreprocessorAgent extends Agent {

    protected void setup() {
        System.out.println("PreprocesadorAgent: ¡Listo para recibir mensajes!");

        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    System.out.println("PreprocesadorAgent: Mensaje recibido del agenteIO ");
                    try {
                        List<Track> tracks = (List<Track>) msg.getContentObject();
                        TrackArffMapper mapper = new TrackArffMapper();
                        Instances trackInstances = mapper.convertToArff(tracks);

                        ACLMessage forwardMsg = new ACLMessage(ACLMessage.REQUEST);
                        forwardMsg.addReceiver(getAID("ClustererAgent"));
                        forwardMsg.setContentObject(trackInstances);

                        send(forwardMsg);
                        System.out.println("PreprocesadorAgent: Mensaje enviado al Agrupador.");
                    } catch (UnreadableException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    block();
                }
            }
        });
    }
}
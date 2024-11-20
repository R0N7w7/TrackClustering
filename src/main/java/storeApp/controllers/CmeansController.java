package storeApp.controllers;

import jade.core.AID;
import jade.core.Runtime;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import org.springframework.web.bind.annotation.*;
import storeApp.models.Track;
import storeApp.models.ClusterResult;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@RestController
@RequestMapping("/classify")
@CrossOrigin(origins = {"https://top50tracker.vercel.app", "http://localhost:3000"})
public class CmeansController {

    private final BlockingQueue<ClusterResult> resultQueue = new ArrayBlockingQueue<>(1);

    @PostMapping("/tracks")
    public ClusterResult receiveTracks(@RequestBody List<Track> tracks) throws Exception {
        System.out.println("Creando contenedor de agentes y enviando mensaje al Agente IO");

        Runtime runtime = Runtime.instance();
        AgentContainer container = runtime.createMainContainer(new jade.core.ProfileImpl());

        Object[] args = new Object[]{tracks, resultQueue};  // Pasar los tracks y la cola al agente
        AgentController agentIOController = container.createNewAgent("IOAgent", "storeApp.agents.IOAgent", args);
        agentIOController.start();

        AgentController preprocessorController = container.createNewAgent("PreprocessorAgent", "storeApp.agents.PreprocessorAgent", null);
        preprocessorController.start();

        AgentController clusterController = container.createNewAgent("ClustererAgent", "storeApp.agents.ClustererAgent", null);
        clusterController.start();

        AgentController processorController = container.createNewAgent("ProcessorAgent", "storeApp.agents.ProcessorAgent", args);
        processorController.start();

        try {
            return resultQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error al esperar el resultado del IOAgent", e);
        }
    }
}
package storeApp.controllers;

import org.springframework.web.bind.annotation.*;
import storeApp.classifiers.TrackClusterer;
import storeApp.models.ClusterResult;
import storeApp.models.Features;
import storeApp.models.Track;
import storeApp.utils.TrackArffMapper;
import weka.core.Instance;
import weka.core.Instances;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/classify")
@CrossOrigin(origins = ("https://top50tracker.vercel.app"))
public class CmeansController {

    @PostMapping("/tracks")
    public ClusterResult receiveTracks(@RequestBody List<Track> tracks) throws Exception {
        TrackArffMapper mapper = new TrackArffMapper();

        Instances trackInstances = mapper.convertToArff(tracks);

        TrackClusterer clusterer = new TrackClusterer(trackInstances);

        ClusterResult clusterResult = clusterer.getClusterResult(tracks);

        return clusterResult;
    }
}

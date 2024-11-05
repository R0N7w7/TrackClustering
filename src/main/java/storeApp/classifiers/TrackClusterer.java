package storeApp.classifiers;

import storeApp.models.ClusterResult;
import storeApp.models.Features;
import weka.clusterers.SimpleKMeans;
import weka.core.Instance;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrackClusterer {

    private SimpleKMeans kMeans;
    private Instances data;

    public TrackClusterer(Instances trackData) throws Exception {
        this.data = new Instances(trackData);
        this.kMeans = new SimpleKMeans();

        kMeans.setNumClusters(5);

        data.deleteAttributeAt(0);
    }

    public ClusterResult getClusterResult(Instances originalData) throws Exception {
        kMeans.buildClusterer(data);

        List<Features> centroidsList = getFeatures();

        List<List<String>> clusters = getClusters(originalData);

        return new ClusterResult(centroidsList, clusters);
    }

    private List<List<String>> getClusters(Instances originalData) throws Exception {
        List<List<String>> clusters = new ArrayList<>();
        for (int i = 0; i < kMeans.getNumClusters(); i++) {
            clusters.add(new ArrayList<>());
        }

        for (int i = 0; i < data.numInstances(); i++) {
            Instance instance = data.instance(i);
            int clusterIndex = kMeans.clusterInstance(instance);
            String trackName = originalData.instance(i).stringValue(0);
            clusters.get(clusterIndex).add(trackName);
        }
        return clusters;
    }

    private List<Features> getFeatures() {
        Instances centroids = kMeans.getClusterCentroids();
        List<Features> centroidsList = new ArrayList<>();

        for (int i = 0; i < centroids.numInstances(); i++) {
            Instance centroid = centroids.instance(i);

            Features centroidData = new Features(
                    roundToTwoDecimals(centroid.value(0)),
                    roundToTwoDecimals(centroid.value(1)),
                    roundToTwoDecimals(centroid.value(2)),
                    roundToTwoDecimals(centroid.value(3))
            );

            centroidsList.add(centroidData);
        }
        return centroidsList;
    }

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
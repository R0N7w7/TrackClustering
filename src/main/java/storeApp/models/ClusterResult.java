package storeApp.models;

import java.io.Serializable;
import java.util.List;

public class ClusterResult implements Serializable {
    private List<Features> centroids;
    private List<List<Track>> clusters;

    public ClusterResult(List<Features> centroids, List<List<Track>> clusters) {
        this.centroids = centroids;
        this.clusters = clusters;
    }

    public List<Features> getCentroids() {
        return centroids;
    }

    public List<List<Track>> getClusters() {
        return clusters;
    }
}

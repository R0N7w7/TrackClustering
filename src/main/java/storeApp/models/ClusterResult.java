package storeApp.models;

import java.util.List;

public class ClusterResult {
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

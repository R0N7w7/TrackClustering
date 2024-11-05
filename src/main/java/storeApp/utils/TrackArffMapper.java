package storeApp.utils;

import storeApp.models.Track;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.List;

public class TrackArffMapper {

    private final Instances arffData;

    public TrackArffMapper() {
        // Definir los atributos ARFF
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("id", (ArrayList<String>) null)); // Atributo de tipo String
        attributes.add(new Attribute("valence"));
        attributes.add(new Attribute("energy"));
        attributes.add(new Attribute("danceability"));
        attributes.add(new Attribute("acousticness"));

        // Crear la estructura de los datos con los atributos definidos
        arffData = new Instances("TracksDataSet", attributes, 0);
    }

    public Instances convertToArff(List<Track> trackList) {
        for (Track track : trackList) {
            addInstance(track);
        }
        return arffData;
    }

    private void addInstance(Track track) {
        double[] values = new double[arffData.numAttributes()];

        values[0] = arffData.attribute(0).addStringValue(track.getId());
        values[1] = track.getFeatures().getValence();
        values[2] = track.getFeatures().getEnergy();
        values[3] = track.getFeatures().getDanceability();
        values[4] = track.getFeatures().getAcousticness();

        arffData.add(new DenseInstance(1.0, values));
    }
}

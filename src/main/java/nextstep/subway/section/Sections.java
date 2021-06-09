package nextstep.subway.section;

import nextstep.subway.station.domain.Station;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Embeddable
public class Sections {

    @OneToMany(mappedBy = "line", cascade = CascadeType.ALL)
    List<Section> sections = new ArrayList<>();

    public Sections() {
    }

    public void add(Section section) {
        sections.add(section);
    }

    public boolean contains(Section section) {
        return sections.contains(section);
    }

    public List<Section> getAllSection() {
        return sections;
    }

    public List<Station> sortedStations() {
        LinkedList<Station> stations = new LinkedList<>();

        for (Section section : sections) {
            addStation(stations, section);
        }
        return stations;
    }

    private LinkedList<Station> addStation(LinkedList<Station> stations, Section section) {
        if(!stations.contains(section.getUpStation())){
            stations.add(section.getUpStation());
            stations.add(section.getDownStation());
            return stations;
        }

        int index = stations.indexOf(section.getUpStation());
        stations.add(index, section.getDownStation());
        return stations;
    }
}

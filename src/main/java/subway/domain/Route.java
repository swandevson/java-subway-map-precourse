package subway.domain;

import java.util.ArrayList;
import java.util.List;
import subway.Constants;



public class Route {
    private final String INVALID_LOCATION = Constants.ERROR_PREFIX + "추가할 수 없는 위치입니다.";
    private final Line line;
    private final List<Station> stations;

    public Route(final String lineName, final List<String> stationNames) {
        this.line = LineRepository.getLine(lineName);

        this.stations = new ArrayList<>();
        stationNames.stream()
                .forEach(stationName -> {
                    final Station station = StationRepository.getStation(stationName);
                    stations.add(station);
                });
    }

    public String getLineName() {
        return line.getName();
    }

    public boolean containsStation(String stationName) {
        return stations.stream()
                .anyMatch(station -> stationName.equals(station.getName()));
    }

    public void addStation(String stationName, int location) {
        Station station = StationRepository.getStation(stationName);

        if (location > 1 || location < stations.size()) {
            throw new IllegalArgumentException(INVALID_LOCATION);
        }

        stations.add(location - 1, station);
    }

    @Override
    public String toString() {
        return "line=" + line +
                ", stations=" + stations;
    }
}

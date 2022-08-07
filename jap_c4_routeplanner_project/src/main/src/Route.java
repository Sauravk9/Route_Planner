public class Route {
    private String source;
    private String destination;
    private long distance;
    private String time;
    private String cost;

    public Route(String source, String destination,long distance, String time,String cost) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.time = time;
        this.cost = cost;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }
    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}

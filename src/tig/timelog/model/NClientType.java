package tig.timelog.model;

public enum NClientType {
    EZ_PUBLISH("EZ"),CODE_FUSION("CF");

    NClientType(String type) {
        this.type = type;
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

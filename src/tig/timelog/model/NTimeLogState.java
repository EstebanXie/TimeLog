package tig.timelog.model;

public enum NTimeLogState {
    CHANGE("In Progress","Change"),
    DEV_ERROR("Development Error","Dev Error"),
    NEW("Assigned to Developer","New",false),
    LEFT("Assigned to Developer","Left"),
    IN_QA("In QA","Left");

    private NTimeLogState(String key, String value, Boolean existed) {
        this.key = key;
        this.value = value;
        this.existed = existed;
    }

    private NTimeLogState(String key, String value) {
        this.key = key;
        this.value = value;
        if(this.existed==null)
        {
            this.existed = true;
        }
    }

    private String key;
    private String value;
    private Boolean existed;

    public static String resolve(String key,Boolean isExist){
        if(key == null||isExist==null)
        {
            return null;
        }
        NTimeLogState[] allNTimeLog = values();
        for (NTimeLogState state : allNTimeLog)
        {
            if(key.equals(NEW.getKey()))
            {
                if(state.getKey().equals(key)&&state.getExisted()==isExist)
                {
                    return state.getValue();
                }
            }else
            {
                if(state.getKey().equals(key))
                {
                    return state.getValue();
                }
            }
        }
        throw new RuntimeException("NTimeLogState : invalid value:"+key);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getExisted() {
        return existed;
    }

    public void setExisted(Boolean existed) {
        this.existed = existed;
    }
}

package engine;

public abstract class InitData {
    public String id;
    public InitData(String id) {
        this.id = id;
    }
    public String getId(){
        return id;
    }
}

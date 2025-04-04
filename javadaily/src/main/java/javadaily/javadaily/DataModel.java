package javadaily.javadaily;


public record DataModel(Method method, Process process, String layer, Creation creation, String type) {

    public  record Method(String name, String type, String assembly) {
    }

    public  record Process(String name, String id, Start start) {
    }

    public  record Start(String epoch, String date) {
    }

    public  record Creation(String epoch, String date) {
    }
}
public class Start {

    public void addTask(String storeWhere){
        new addTask().execute(storeWhere);
    }

    public static void main(String[] args) {
        Start start = new Start();
        start.addTask("taskDB");


    }
}

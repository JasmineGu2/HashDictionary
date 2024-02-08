public class Data{
    // Set private variables
    private String config;
    private int score;

    // Constructor, initalize a key and value (score) for each data Object
    public Data(String config, int score){
        // config is used as the key for this 
        this.config = config;
        this.score = score;
    }

    // Returns the configuraton/key stored  in this data object
    public String getConfiguration(){
        return this.config;
    }

    // Returns the score in this Data
    public int getScore(){
        return this.score;
}
}


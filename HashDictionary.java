import java.util.LinkedList;

public class HashDictionary implements DictionaryADT{
    // Create private instance variables 
    private LinkedList<Data>[] table;
    private int size;
   
    // Constructor that creates a Array of a size of LinkedLists
    // Note the dictionary/array is empty
    public HashDictionary(int size){
        // returns empty dictionary of the specified size 
        table = new LinkedList[size];
        this.size = size;
    }

    // Function to get the Hash Value/ index of the dictionary array for a key/configKey string
    private int getHashVal(String configKey){
        // Set variables
        int lengthOfKey = configKey.length();
        int hashVal = 0;
        int x = 11;
        
        // Hashing polynomial function, get ASCII of each char and multiply by hashValue * x
        for (int k = lengthOfKey - 1; k >= 0; k--){
            int charValue = (int) configKey.charAt(k);
            // Modulus is the size of the array
            hashVal = (hashVal * x + charValue) % size;

        }
       return hashVal ;
    }   


    // Function to insert a data object into hashtable
    public int put(Data record) throws DictionaryException{
        // get the key value
        String keyValue = record.getConfiguration();
        // find the index in the hashtable
        int hashVal = getHashVal(keyValue);
        // Access the linkedlist in that index in the hashtable
        LinkedList<Data> dataList = table[hashVal];
        
       // If there exists a linkedlist/not null
       if(dataList !=null){
          // Loop through the linkedlist 
          for(int i=0;i<dataList.size();i++){
            // throw execption if a data object is found with the same key 
            if(dataList.get(i).getConfiguration().equals(record.getConfiguration())){
                // If the keys are the same, throw error
                throw new DictionaryException();
            } 
        }
            // If a linkedList exists, means there is a collision
            if(dataList.size()>0){
            // if the size is greater than 0 
            // Add the record adn return 1
                dataList.addLast(record);
                return 1;
            }
            
            //If the dataList is empty but not null (object has been removed)
            if(dataList.size()==0){
                // if it is empty, but the size is 0 
                dataList.addLast(record);
                return 0;
            }
       }
        // if it is null then return 0 and initalize the LinkedList and add the record to the hashtable
        // return 0
        table[hashVal] = new LinkedList<Data>();
        table[hashVal].addLast(record);
        return 0;
    
    }
  
    // Function removes record from the dictionary, error if that doesn't exist
    public void remove(String config) throws DictionaryException{
        // Find the index and the linkedlist where the key is in the hashtable
        int hashVal = getHashVal(config);
        LinkedList<Data>dataList = table[hashVal];
        // find the object by checking where the key is identical
        // If a datalist exists
        if(dataList!=null){
            // for every elemetn in the linkedlist
            for(int i=0;i<dataList.size();i++){
                if(dataList.get(i).getConfiguration().equals(config)){
                // If the keys are the same, we found it, and remove it 
                    dataList.remove(i);
                    // end function
                    return;
                }
            } 
        }
        //if data list is null, throw execption as it doesn't exist
        // or if it is hasn't returned anything, item wasn't found
        // throw execption
        throw new DictionaryException();
    }
    
    // Function that gets the score given a key
    public int get(String config){
          int hashVal = getHashVal(config);
          // get the linkedlist
          LinkedList<Data> dataList = table[hashVal];
          // then go through the table looking for that key 
          if(dataList!=null){
            // for every element in the linkedlist
            for(int i=0;i<dataList.size();i++){
                if(dataList.get(i).getConfiguration().equals(config)){
                // If the keys are the same, we found it, and return the score
                    int score = dataList.get(i).getScore();
                    return score;
                }
            } 
        }
        // if datalist is null
        // or object was not returned
        return -1;
        }

    // Function that finds the number of records/games 
    public int numRecords(){
        // check for each index in the dictionary
        // check the linkedlists size then add that together to numRecords
        int numRecords = 0;
        for(int i=0; i<table.length;i++){
            numRecords += table[i].size();
        }
        //return numRecords
        return numRecords;
    }
}


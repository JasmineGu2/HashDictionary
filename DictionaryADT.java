public interface DictionaryADT{
    public int put (Data pair) throws DictionaryException; 
    public void remove (String config) throws DictionaryException; 
    // Removes the record with given config 
    // Must throw a DictionaryExecption if no record in hash tbale stores config
    public int get (String config);
    // Returns score stored in record of dictionary wiht key config or -1 if config is not in dictionary
    public int numRecords();
    // returns number of data objects stored in the dictionary
}
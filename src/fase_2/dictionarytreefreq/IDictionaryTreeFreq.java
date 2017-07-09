package fase_2.dictionarytreefreq;

import fase_1.diccionario.DictionaryList;

public interface IDictionaryTreeFreq {

	public void save(DictionaryList lista);
	public void add(String word, int freq);
	
}

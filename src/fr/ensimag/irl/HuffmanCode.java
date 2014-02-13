package fr.ensimag.irl;

import java.util.BitSet;
import java.util.List;

public interface HuffmanCode {
	List<BitSet> initCode(int count);
	
	BitSet addCode(List<BitSet> codes);
}

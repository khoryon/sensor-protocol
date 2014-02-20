package fr.ensimag.irl;

//import org.contikios.cooja.COOJARadioPacket;
//import org.contikios.cooja.MoteTimeEvent;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import org.contikios.cooja.MoteType;
import org.contikios.cooja.RadioPacket;
import org.contikios.cooja.Simulation;
//import org.contikios.cooja.interfaces.ApplicationRadio;
import org.contikios.cooja.motes.AbstractApplicationMote;
//import org.contikios.cooja.interfaces.Mote2MoteRelations;

public class SensorMote extends AbstractApplicationMote {
	
	private List<SensorMote> friendsMote;
	private List<BitSet> friendsCode;
	private Tree treeCode;
	
    public SensorMote() {
      super();
    }
    
    public SensorMote(MoteType moteType, Simulation simulation) {
      super(moteType, simulation);
    }
    
    @Override
    public void execute(long time) {
      /* Start sending traffic */
    }

    @Override
    public void receivedPacket(RadioPacket p) {
      /* Receive a packet */
    }
    
    @Override
    public void sentPacket(RadioPacket p) {
      /* Send a packet */
    }

    @Override
    public String toString() {
      return "TODO " + getID();
    }
    
    
    
    /*------------------------- Code neighbors handling -------------------------*/
	private void initCode() {
		PriorityQueue<Tree> listNode = new PriorityQueue<Tree>();
		for (int i = 0; i < friendsMote.size(); i++) {
			listNode.add(new Tree(0, friendsMote.get(i)));
		}
		for (int i = 0; i < friendsMote.size() -1; i++) {
			Tree node1 = listNode.remove();
			Tree node2 = listNode.remove();
			listNode.add(new Tree(node1, node2));
		}
		treeCode = listNode.remove();
		friendsCode = treeCode.code();
	}
	
	private void addCode(SensorMote mote) {
		if (!friendsMote.contains(mote)) {
			friendsMote.add(mote);
			treeCode = new Tree(treeCode, new Tree(0, mote));
			// compute a new code
			//
			// /!\ Impossible to add a new code without modifying the others !! 
			//
		}
	}
}


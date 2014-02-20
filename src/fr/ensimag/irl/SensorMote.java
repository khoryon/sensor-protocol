package fr.ensimag.irl;

//import org.contikios.cooja.COOJARadioPacket;
//import org.contikios.cooja.MoteTimeEvent;
import java.util.BitSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.contikios.cooja.MoteType;
import org.contikios.cooja.RadioPacket;
import org.contikios.cooja.Simulation;
//import org.contikios.cooja.interfaces.ApplicationRadio;
import org.contikios.cooja.motes.AbstractApplicationMote;
//import org.contikios.cooja.interfaces.Mote2MoteRelations;

public class SensorMote extends AbstractApplicationMote implements HuffmanCode {    
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

    //TODO Definir la fonction getRoute et ses paramètres.
    private void getRoute() {
	}

    public void receivedPacket(RadioPacket p) {
      /* Receive a packet : 
       * Conversion en FrameMac
       * Récuperation destination
       * Analyse Destination
       * 	Elimination eventuel bourrae
       * 	Si pour nous on remonte les donnees
       * 	Sinon on fait suivre */
    	Frame packet;
    	BitSet dest;
    	int pos;
		try {
			packet = new Frame(p);
	    	log(String.valueOf(packet.getPacketData()));
	    	//dest = packet.getDestination();
	    	//pos = dest.nextSetBit(0);
	    	//Supprimer son ID de la route 
	    		    	


		} catch (FormatPacketException e) {
			// TODO Auto-generated catch block
			log("Packet non reconnu.");;
		}
    }
    
    @Override
    public void sentPacket(RadioPacket p) {
      /* Send a packet */
    }

    @Override
    public String toString() {
      return "TODO " + getID();
    }
	@Override
	public List<BitSet> initCode(int count) {
		PriorityQueue<Tree> listNode = new PriorityQueue<Tree>();
		for (int i = 0; i < count; i++) {
			listNode.add(new Tree(1/count));
		}
		for (int i = 0; i < count -1; i++) {
			Tree node1 = listNode.remove();
			Tree node2 = listNode.remove();
			listNode.add(new Tree(node1, node2));
		}
		return listNode.remove().code();
	}

	@Override
	public BitSet addCode(List<BitSet> codes) {
		// TODO Auto-generated method stub
		return null;
	}
}


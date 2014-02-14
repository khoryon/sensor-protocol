package fr.ensimag.irl;
import java.util.BitSet;

import org.contikios.cooja.RadioPacket;

/**
 * Paquet 802.15.4 MAC, General MAC Frame,
 * Adresses au format 16 bits
 * Format du paquet : 
 * Entete Donnees Enpied
 * Entete : 
 * frameControle;
 * sequenceNumber;
 * destinationPanIdentifier;
 * destinationAdress;
 * sourcePanIdentifier;
 * sourceAdress;

 * Donnees :
 * framePayload;

 * Enpied : 
 * frameCheckSequence;

 */

public  class PaquetMac implements RadioPacket{
	private byte[] frameControle;
	private byte[] sequenceNumber;
	private byte[] destinationPanIdentifier;
	private byte[] destinationAdress;
	private byte[] sourcePanIdentifier;
	private byte[] sourceAdress;

	private byte[] framePayload;

	private byte[] frameCheckSequence;


	public PaquetMac(){
		this.frameControle = new byte[2];
		this.sequenceNumber = new byte[1];
		this.destinationPanIdentifier = new byte[0];
		this.destinationAdress = new byte[2];
		this.sourcePanIdentifier = new byte[0];
		this.sourceAdress = new byte[2];

		this.framePayload = new byte[118];

		this.frameCheckSequence = new byte[2];
	}

	public PaquetMac(BitSet source,BitSet destination,byte[] donnees){
		super();
		this.setDestinationAdress(destination);
		this.setSourceAdress(source);
		this.framePayload = donnees;
	}
	public void setDestinationAdress(BitSet destination){
		//On n'utilise que des adresses sur 16 bits
		assert(destination.size()<=16);
		this.destinationAdress = destination.toByteArray();
	}
	public void setSourceAdress(BitSet source){
		//On n'utilise que des adresses sur 16 bits
		assert(source.size()<=16);
		this.destinationAdress = source.toByteArray();

	}

	private void calculCodeErreur(){


	}

	public byte[] getPacketData() {
		this.calculCodeErreur();
		assert(this.frameControle.length+
		this.sequenceNumber.length+
		this.destinationPanIdentifier.length+
		this.destinationAdress.length+
		this.sourcePanIdentifier.length+
		this.sourceAdress.length+
		this.framePayload.length+
		this.frameCheckSequence.length<=127);
		
		byte[] paquet = new byte[this.frameControle.length+
		this.sequenceNumber.length+
		this.destinationPanIdentifier.length+
		this.destinationAdress.length+
		this.sourcePanIdentifier.length+
		this.sourceAdress.length+
		this.framePayload.length+
		this.frameCheckSequence.length];
		
		System.arraycopy(this.frameControle, 0, paquet, 0, this.frameControle.length);
		System.arraycopy(this.sequenceNumber, 0, paquet, paquet.length, this.sequenceNumber.length);
		System.arraycopy(this.destinationPanIdentifier, 0, paquet, paquet.length, this.destinationPanIdentifier.length);
		System.arraycopy(this.destinationAdress, 0, paquet, paquet.length, this.destinationAdress.length);
		System.arraycopy(this.sourcePanIdentifier, 0, paquet, paquet.length, this.sourcePanIdentifier.length);
		System.arraycopy(this.sourceAdress, 0, paquet, paquet.length, this.sourceAdress.length);
		System.arraycopy(this.framePayload, 0, paquet, paquet.length, this.framePayload.length);
		System.arraycopy(this.frameCheckSequence, 0, paquet, paquet.length, this.frameCheckSequence.length);
		
		return paquet;
	};
}

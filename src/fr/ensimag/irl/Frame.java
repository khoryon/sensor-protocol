package fr.ensimag.irl;
import java.util.BitSet;

import org.contikios.cooja.RadioPacket;

public  class Frame implements RadioPacket{
	private byte[] macDst;
	private byte[] macSrc;
	private byte[] shortRouteForward;
	private byte[] shortRouteBackward;
	private byte[] identifier;
	private byte[] type;

	private byte[] data;

	private byte[] CheckSequence;


	public Frame(){
		this.macDst = new byte[2];
		this.macSrc = new byte[2];
		this.shortRouteForward = new byte[2];
		this.shortRouteBackward = new byte[2];
		this.identifier = new byte[2];
		this.type = new byte[1];

		this.data = new byte[114];

		this.CheckSequence = new byte[2];
	}
	public Frame(RadioPacket p) throws FormatPacketException{
		//TODO Verifier le frameCheckSequence 
		this();
		if (p.getPacketData().length < 15) throw new FormatPacketException();
		System.arraycopy(p.getPacketData(), 0, this.macDst, 0, 2);
		System.arraycopy(p.getPacketData(), 2, this.macSrc, 0, 2);
		System.arraycopy(p.getPacketData(), 4, this.shortRouteForward, 0, 2);
		System.arraycopy(p.getPacketData(), 6, this.shortRouteBackward, 0, 2);
		System.arraycopy(p.getPacketData(), 8, this.identifier, 0, 2);
		System.arraycopy(p.getPacketData(), 9, this.type, 0, 2);
		System.arraycopy(p.getPacketData(), 11, this.data, 0, p.getPacketData().length-13);
		System.arraycopy(p.getPacketData(), 13, this.CheckSequence, 0, 2);
		System.arraycopy(p.getPacketData(), p.getPacketData().length-2, this.CheckSequence, 0, 2);

	}

	/*public FrametMac(BitSet source,BitSet destination,byte[] donnees){
		super();
		this.setDestinationAdress(destination);
		this.setSourceAdress(source);
		this.framePayload = donnees;
	}*/
	public void setShortRouteForward(BitSet destination){
		//On n'utilise que des adresses sur 16 bits, pour la gesion du bourrage on considerra des adresses de 15 bits ou moins uniquement.
		int i = 0 ; 
		BitSet dest;

		assert(destination.size()<=15);

		dest = new BitSet(16);
		while(i+1+destination.length()<16){
			dest.clear(i);
			i++;
		}
		dest.set(i);
		i++;
		while(i<16){
			dest.set(i, destination.get(i+(16-destination.length()-1)));
		}
		this.shortRouteForward = dest.toByteArray();
	}

	private void calculCodeErreur(){
		//TODO Faire la fonction de verification des erreurs : besoin de savoir la fonction à utiliser (checksum/crc...)
	}

	public byte[] getPacketData() {
		this.macDst = new byte[2];
		this.macSrc = new byte[2];
		this.shortRouteForward = new byte[2];
		this.shortRouteBackward = new byte[2];
		this.identifier = new byte[2];
		this.type = new byte[1];

		this.data = new byte[114];

		this.CheckSequence = new byte[2];


		this.calculCodeErreur();
		assert(this.macDst.length+
				this.macSrc.length+
				this.shortRouteForward.length+
				this.shortRouteBackward.length+
				this.identifier.length+
				this.type.length+
				this.data.length+
				this.CheckSequence.length<=127);

		byte[] paquet = new byte[this.macDst.length+
		                         this.macSrc.length+
		                         this.shortRouteForward.length+
		                         this.shortRouteBackward.length+
		                         this.identifier.length+
		                         this.type.length+
		                         this.data.length+
		                         this.CheckSequence.length];

		System.arraycopy(this.macDst, 0, paquet, 0, this.macDst.length);
		System.arraycopy(this.macSrc, 0, paquet, paquet.length, this.macSrc.length);
		System.arraycopy(this.shortRouteForward, 0, paquet, paquet.length, this.shortRouteForward.length);
		System.arraycopy(this.shortRouteBackward, 0, paquet, paquet.length, this.shortRouteBackward.length);
		System.arraycopy(this.identifier, 0, paquet, paquet.length, this.identifier.length);
		System.arraycopy(this.type, 0, paquet, paquet.length, this.type.length);
		System.arraycopy(this.data, 0, paquet, paquet.length, this.data.length);
		System.arraycopy(this.CheckSequence, 0, paquet, paquet.length, this.CheckSequence.length);

		return paquet;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	};


}

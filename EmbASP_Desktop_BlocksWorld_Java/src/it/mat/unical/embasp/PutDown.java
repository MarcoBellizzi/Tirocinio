package it.mat.unical.embasp;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("pick-up")
public class PutDown {

	@Param(0)
	private String block;

	public PutDown() {
		block = new String();
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(final String block) {
		this.block = block;
	}
	
	@Override
	public String toString() {
		return "pick-up(block = " + block + ")";
	}
}

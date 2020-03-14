package it.mat.unical.embasp;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("stack")
public class Stack {

	public Stack() {
		block1 = new String();
		block2 = new String();
	}

	@Param(0)
	private String block1;

	@Param(1)
	private String block2;

	public String getBlock1() {
		return block1;
	}

	public void setBlock1(String block1) {
		this.block1 = block1;
	}

	public String getBlock2() {
		return block2;
	}

	public void setBlock2(String block2) {
		this.block2 = block2;
	}

	@Override
	public String toString() {
		return "stack(block1 = " + block1 + ", block2 = " + block2 + ")";
	}

}

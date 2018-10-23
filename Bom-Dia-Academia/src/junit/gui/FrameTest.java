package junit.gui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gui.Frame;

class FrameTest {

	Frame frame = new Frame();
	
	@Test
	void test() {
		init();
	}

	private void init() {
		frame.init();		
	}
}

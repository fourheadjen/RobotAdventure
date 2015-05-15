package handler;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class WindowHandler implements ComponentListener{

	@Override
	public void componentHidden(ComponentEvent e) {
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		Dimension size = e.getComponent().getSize();
		System.out.println(size);
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		
	}


	
}

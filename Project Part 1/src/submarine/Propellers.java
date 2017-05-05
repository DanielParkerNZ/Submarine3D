package submarine;

import com.jogamp.opengl.GL2;

public class Propellers extends TreeNode {
	
	private Propeller p1, p2, p3, p4;
	
	public Propellers() {
		
		p1 = new Propeller();
		p2 = new Propeller();
		p2.setRotation(90,1,0,0);
		p3 = new Propeller();
		p3.setRotation(180,1,0,0);
		p4 = new Propeller();
		p4.setRotation(-90,1,0,0);
		
		addChild(p1);
		addChild(p2);
		addChild(p3);
		addChild(p4);
		
	}
	
	@Override
	void drawNode(GL2 gl) {
		
	}
	
	public void setDrawMode(boolean dMode) {
		
		p1.setDrawMode(dMode);
		p2.setDrawMode(dMode);
		p3.setDrawMode(dMode);
		p4.setDrawMode(dMode);
		
	}
	
}

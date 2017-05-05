package scene;

import com.jogamp.opengl.GL2;

public class Propellers extends TreeNode {
	
	Propeller p1, p2, p3, p4;
	boolean drawMode;
	
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


	public void passRotation(double rotationAngle) {
		
		p1.setRotation(rotationAngle, 1, 0, 0);
		p2.setRotation(90+rotationAngle, 1, 0, 0);
		p3.setRotation(180+rotationAngle, 1, 0, 0);
		p4.setRotation(-90+rotationAngle, 1, 0, 0);
		
	}
	
	public void setDrawStyle(boolean dMode) {
		
		drawMode = dMode;
		
		p1.setDrawStyle(dMode);
		p2.setDrawStyle(dMode);
		p3.setDrawStyle(dMode);
		p4.setDrawStyle(dMode);
		
	}
	
}

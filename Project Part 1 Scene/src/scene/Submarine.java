package scene;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

public class Submarine extends TreeNode {

	private GLU glu;
	private GLUquadric body;
	private Propellers propellers;
	private Sail sail;
	
	private double rotationAngle;
	private boolean drawMode;
	
	public Submarine() {
		
		rotationAngle = 0.0;
		
		propellers = new Propellers();
		sail = new Sail();
		sail.setRotation(90, 1, 0, 0);
		sail.setTranslation(-0.4, 0.3, 0);
		
		addChild(propellers);
		addChild(sail);
		
	}
	
	public void setPropellerRotation(double rot) {
		
		rotationAngle = rot;
		
	}

	@Override
	void drawNode(GL2 gl) {
		
		glu = new GLU();
		body = glu.gluNewQuadric();
		if(drawMode) {
			glu.gluQuadricDrawStyle(body, GLU.GLU_FILL);
		} else {
			glu.gluQuadricDrawStyle(body, GLU.GLU_LINE);
		}
		
		//Body
		gl.glColor3d(0.8, 0.71, 0.27);
		gl.glPushMatrix();
			gl.glScaled(1, 0.5, 0.5);
			gl.glRotated(90,0,1,0);
			gl.glTranslated(0,0,-0.4);
			glu.gluSphere(body,0.4,16,10);
		gl.glPopMatrix();
		
		rotatePropellers();
		
	}
	
	public void rotatePropellers() {
		
		propellers.passRotation(rotationAngle);
		
	}
	
	public void setDrawStyle(boolean dMode) {
		
		drawMode = dMode;
		
		propellers.setDrawStyle(dMode);
		sail.setDrawStyle(dMode);
		
	}
	
}

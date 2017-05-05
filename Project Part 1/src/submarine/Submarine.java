package submarine;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

public class Submarine extends TreeNode {

	GLU glu;
	GLUquadric body;
	Propellers propellers;
	Sail sail;
	private boolean drawMode;
	
	public Submarine() {
		
		propellers = new Propellers();
		propellers.setTranslation(0,0,0);
		sail = new Sail();
		sail.setRotation(90, 1, 0, 0);
		sail.setTranslation(-0.4, 0.3, 0);
		addChild(propellers);
		addChild(sail);
		
	}

	@Override
	void drawNode(GL2 gl) {
		
		glu = new GLU();
		body = glu.gluNewQuadric();
		if(drawMode) {
			glu.gluQuadricDrawStyle(body, GLU.GLU_LINE);
		} else {
			glu.gluQuadricDrawStyle(body, GLU.GLU_FILL);
		}
		//Body
		gl.glPushMatrix();
			gl.glScaled(1, 0.5, 0.5);
			gl.glRotated(90,0,1,0);
			gl.glTranslated(0,0,-0.4);
			glu.gluSphere(body,0.4,16,10);
		gl.glPopMatrix();
		
	}
	
	public void setDrawMode(boolean dMode) {
		
		drawMode = dMode;
		
		propellers.setDrawMode(dMode);
		sail.setDrawMode(dMode);
		
	}
	
}

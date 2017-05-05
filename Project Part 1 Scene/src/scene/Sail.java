package scene;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

public class Sail extends TreeNode {

	GLUquadric sail;
	ForePlanes forePlanes;
	boolean drawMode;
	
	public Sail() {
	
		forePlanes = new ForePlanes();
		forePlanes.setTranslation(0,0,0.05);
		addChild(forePlanes);
		
	}

	@Override
	void drawNode(GL2 gl) {
		
		GLU glu = new GLU();

        sail = glu.gluNewQuadric();
        if(drawMode) {
        	glu.gluQuadricDrawStyle(sail, GLU.GLU_FILL);
        } else {
        	glu.gluQuadricDrawStyle(sail, GLU.GLU_LINE);
        }
        gl.glColor3d(1.0, 0.91, 0.47);
        gl.glScaled(1,0.4,1);
        glu.gluCylinder(sail, 0.1, 0.1, 0.2, 16, 6);
		
	}
	
	public void setDrawStyle(boolean dMode) {
		
		drawMode = dMode;
		
		forePlanes.setDrawStyle(dMode);
		
	}
	
}

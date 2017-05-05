package scene;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

public class OriginLocator {

	GLU glu = new GLU();
	GLUquadric originSphere;
	
	public OriginLocator() {
		
		originSphere = glu.gluNewQuadric();
		
	}
	
	public void draw(GL2 gl) {
		
		gl.glColor3d(1,0,0);
		gl.glBegin(GL2.GL_LINES);
			gl.glVertex3d(0, 0, 0);
			gl.glVertex3d(1.0, 0, 0);
		gl.glEnd();
		
		gl.glColor3d(0,1,0);
		gl.glBegin(GL2.GL_LINES);
			gl.glVertex3d(0, -1.0, 0);
			gl.glVertex3d(0, 1.0, 0);
		gl.glEnd();
		
		gl.glColor3d(0,0,1);
		gl.glBegin(GL2.GL_LINES);
			gl.glVertex3d(0, 0, 0);
			gl.glVertex3d(0, 0, 1.0);
		gl.glEnd();
		
		glu.gluSphere(originSphere, 0.05, 20, 20);
		
	}
	
}

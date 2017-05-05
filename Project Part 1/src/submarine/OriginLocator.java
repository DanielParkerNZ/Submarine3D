package submarine;

import com.jogamp.opengl.GL2;

public class OriginLocator {

	public OriginLocator() {
		
	}
	
	public void draw(GL2 gl) {
		
		gl.glColor3d(1,0,0);
		gl.glBegin(GL2.GL_LINES);
			gl.glVertex3d(0, 0, 0);
			gl.glVertex3d(1.0, 0, 0);
		gl.glEnd();
		
		gl.glColor3d(0,1,0);
		gl.glBegin(GL2.GL_LINES);
			gl.glVertex3d(0, 0, 0);
			gl.glVertex3d(0, 1.0, 0);
		gl.glEnd();
		
		gl.glColor3d(0,0,1);
		gl.glBegin(GL2.GL_LINES);
			gl.glVertex3d(0, 0, 0);
			gl.glVertex3d(0, 0, 1.0);
		gl.glEnd();
		
	}
	
}

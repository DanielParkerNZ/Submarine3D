package scene;

import com.jogamp.opengl.GL2;

public class Grid {

	public Grid() {
		
	}
	
	public void draw(GL2 gl, boolean dMode, double size, double height, double c1, double c2, double c3, double a) {
		
		for(double x=size; x>-size; x-=0.1) {
			for(double z=size; z>-size; z-=0.1) {
				gl.glColor4d(c1,c2,c3,a);
				gl.glEnable(GL2.GL_BLEND);
				gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
				if(dMode) {
					gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE );
				} else {
					gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL );
				}
				gl.glBegin(GL2.GL_QUADS);
					gl.glVertex3d(x, height, z);
					gl.glVertex3d(x, height, z-0.1);
					gl.glVertex3d(x-0.1, height, z-0.1);
					gl.glVertex3d(x-0.1, height, z);
				gl.glEnd();
				gl.glDisable(GL2.GL_BLEND);
			}
		}
		
	}
	
}
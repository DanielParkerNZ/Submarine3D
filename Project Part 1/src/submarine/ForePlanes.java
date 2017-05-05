package submarine;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

public class ForePlanes extends TreeNode {
	
	private boolean drawMode;
	
	public ForePlanes() {
	}

	@Override
	void drawNode(GL2 gl) {
		
		GLUT glut = new GLUT();
        gl.glColor3d(0,0,1);
        gl.glScaled(1,5,0.2);
        if(drawMode) {
        	glut.glutWireCube(0.1f);
        } else {
        	glut.glutSolidCube(0.1f);
        }
		
	}
	
	public void setDrawMode(boolean dMode) {
		
		drawMode = dMode;
		
	}
	
}

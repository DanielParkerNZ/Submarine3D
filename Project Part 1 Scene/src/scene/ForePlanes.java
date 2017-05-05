package scene;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

public class ForePlanes extends TreeNode {
	
	boolean drawMode;
	
	public ForePlanes() {
	}

	@Override
	void drawNode(GL2 gl) {
		
		GLUT glut = new GLUT();
		gl.glColor3d(1.0, 0.91, 0.47);
        
        gl.glScaled(1,5,0.2);
        if(drawMode) {
        	glut.glutSolidCube(0.1f);
        } else {
        	glut.glutWireCube(0.1f);
        }
	}
	
	public void setDrawStyle(boolean dMode) {
		
		drawMode = dMode;
		
	}
	
}

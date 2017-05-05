package scene;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.gl2.GLUT;

public class Propeller extends TreeNode {

	GLUquadric propellerTip;
	boolean drawMode;
	
	public Propeller() {
		
	}

	@Override
	void drawNode(GL2 gl) {
		
		GLU glu = new GLU();
        GLUT glut = new GLUT();
        
        propellerTip = glu.gluNewQuadric();
        if(drawMode) {
        	glu.gluQuadricDrawStyle(propellerTip, GLU.GLU_FILL);
        } else {
        	glu.gluQuadricDrawStyle(propellerTip, GLU.GLU_LINE);
        }
        
        gl.glScaled(0.2,1,1);
        //Tip
        gl.glTranslated(0,0,0.21);
        gl.glColor3d(1, 0, 0);
        glu.gluSphere(propellerTip,0.1,15,10);
        //Body
        gl.glTranslated(0,0,-0.4);
        gl.glColor3d(1, 0, 0);
        if(drawMode) {
        	glut.glutSolidCone(0.1, 0.2, 20, 8);
        } else {
        	glut.glutWireCone(0.1, 0.2, 20, 8);
        }
	}
	
	public void setDrawStyle(boolean dMode) {
		
		drawMode = dMode;
		
	}
	
}

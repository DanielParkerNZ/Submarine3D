package submarine;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.FPSAnimator;



public class Main implements GLEventListener, KeyListener {
	

	private static int WIN_WIDTH = 500;
	private static int WIN_HEIGHT = 500;
	
	GLUquadric originSphere;
	OriginLocator ol;
	Submarine sub;
	int cam;
	int keyType;
	boolean dMode = true;
 
	@Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        GLU glu = new GLU();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        if(dMode) {
        	glu.gluQuadricDrawStyle(originSphere,GLU.GLU_LINE);
        } else {
        	glu.gluQuadricDrawStyle(originSphere,GLU.GLU_FILL);
        }
        
        setViewPoint(gl, cam, keyType);
        glu.gluSphere(originSphere, 0.05, 20, 20);
        ol.draw(gl);
        
        sub.setDrawMode(dMode);
        sub.draw(gl);
        
        
        
        gl.glFlush();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        GLU glu = new GLU();
        gl.setSwapInterval(1);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL2.GL_SMOOTH); 
        gl.glEnable(GL2.GL_DEPTH_TEST);

        originSphere = glu.gluNewQuadric();
        ol = new OriginLocator();
        sub = new Submarine();
    
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    	GL2 gl = drawable.getGL().getGL2();
    	//GLU glu = new GLU();
    	
        WIN_WIDTH  = width;
    	height = (height <= 0) ? 1 : height; 
        WIN_HEIGHT = height;
        //double aspect = WIN_WIDTH/WIN_HEIGHT;
        
     	gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(-1.0, 1.0, -1.0, 1.0, -1.0, 1.0);
		//glu.gluPerspective(45.0, aspect, 0.1, 100.0);
    }
    
    private void setViewPoint(GL2 gl, int cam, int keyType) {

		switch(cam) {
		case KeyEvent.VK_A:
			if(keyType == KeyEvent.KEY_PRESSED) {
				gl.glRotated(-90,0,1,0);
			} 
			if(keyType == KeyEvent.KEY_RELEASED) {
				gl.glRotated(-90,0,1,0);
			}
			break;
		case KeyEvent.VK_D:
			if(keyType == KeyEvent.KEY_PRESSED) {
				gl.glRotated(90,0,1,0);
			} 
			if(keyType == KeyEvent.KEY_RELEASED) {
				gl.glRotated(90,0,1,0);
			}
			break;
		case KeyEvent.VK_T:
			if(keyType == KeyEvent.KEY_PRESSED) {
				gl.glRotated(90,1,0,0);
			} 
			if(keyType == KeyEvent.KEY_RELEASED) {
				gl.glRotated(90,1,0,0);
			}
			break;
		case KeyEvent.VK_U:
			if(keyType == KeyEvent.KEY_PRESSED) {
				gl.glRotated(-90,1,0,0);
			} 
			if(keyType == KeyEvent.KEY_RELEASED) {
				gl.glRotated(-90,1,0,0);
			}
			break;
		case KeyEvent.VK_W:
			if(keyType == KeyEvent.KEY_PRESSED) {
				gl.glRotated(180,0,1,0);
			} 
			if(keyType == KeyEvent.KEY_RELEASED) {
				gl.glRotated(180,0,1,0);
			}
			break;
		case KeyEvent.VK_S:
			if(keyType == KeyEvent.KEY_PRESSED) {
				gl.glRotated(0,0,1,0);
			} 
			if(keyType == KeyEvent.KEY_RELEASED) {
				gl.glRotated(0,0,1,0);
			}
			break;
		case KeyEvent.VK_L:
			if(keyType == KeyEvent.KEY_PRESSED) {
				dMode = !dMode;
			} 
			if(keyType == KeyEvent.KEY_RELEASED) {
			}
			break;
		}
		
	}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_S | key == KeyEvent.VK_D | key == KeyEvent.VK_W | key == KeyEvent.VK_T
				| key == KeyEvent.VK_A | key == KeyEvent.VK_U | key == KeyEvent.VK_L) {
			cam = e.getKeyCode();
			keyType = KeyEvent.KEY_PRESSED;
		} else {
			cam = -1;
		}
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	int key = e.getKeyCode();
        if (key == KeyEvent.VK_S | key == KeyEvent.VK_D | key == KeyEvent.VK_W | key == KeyEvent.VK_T
				| key == KeyEvent.VK_A | key == KeyEvent.VK_U | key == KeyEvent.VK_L) {
			cam = e.getKeyCode();
			keyType = KeyEvent.KEY_RELEASED;
		} else {
			cam = -1;
		}
    }
  
    @Override
    public void keyTyped(KeyEvent e) {
    }

	@Override
	public void dispose(GLAutoDrawable arg0) {
	}
	
	public static void main(String[] args) {
        Frame frame = new Frame("Project Part 1");
        GLCanvas canvas = new GLCanvas();

        Main main = new Main();
        canvas.addGLEventListener(main);
        canvas.addKeyListener(main);
        
        frame.add(canvas);
        frame.setSize(WIN_WIDTH, WIN_HEIGHT);
        final FPSAnimator animator = new FPSAnimator(canvas,60);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    
}

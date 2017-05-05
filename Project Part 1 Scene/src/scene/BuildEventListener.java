package scene;

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
import com.jogamp.opengl.util.FPSAnimator;

public class BuildEventListener implements GLEventListener, KeyListener {

	private static int WIN_WIDTH = 500;
	private static int WIN_HEIGHT = 500;
	
	private Grid grid;
	private OriginLocator ol;
	private Submarine sub;
	
	private boolean dMode;
	private boolean changed;
	private boolean diving;
	private int dStyle, keyType;
	private double propellerRotation;
	private float eyeHeight;
	private double heading;
	private double[] location;
	private double distance;
	
	@Override
	public void display(GLAutoDrawable drawable) {

		GL2 gl = drawable.getGL().getGL2();
		GLU glu = new GLU();
		
		// clear the depth and color buffers
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		
		//camera & projection
		gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluLookAt(location[0]+Math.sin(Math.toRadians(heading-180)),location[1]+eyeHeight,location[2]+Math.cos(Math.toRadians(heading-180)), // eye pos
        		location[0],location[1],location[2], // look at
        		0,1,0); // up

      	//Keyboard usability method
        setDrawStyle(dStyle, keyType);
		
		//Draw origin locator
		ol.draw(gl);
		
		//Draw seafloor grid
		grid.draw(gl,dMode,4,-1,0.7,0.5,0.24,1.0);
		
		//Draw Submarine
		sub.setTranslation(location[0], location[1], location[2]);
		if(!diving) {
			sub.setPropellerRotation(propellerRotation);
			
		}
		//sub.setDrawStyle(dMode);
		sub.setDrawStyle(true);
		sub.draw(gl);
		
		//Draw water grid
		grid.draw(gl,dMode,4,0,0,0,1,0.5);
		gl.glFlush();

	}
	
	private void setDrawStyle(int dStyle, int keyType) {

		switch(dStyle) {
		case KeyEvent.VK_L: //Change to wireframe mode or fill mode
			if(keyType == KeyEvent.KEY_PRESSED) {
				if(!changed) {
					dMode = !dMode;
					changed = !changed;
				}
			}
			if(keyType == KeyEvent.KEY_RELEASED){
				changed = !changed;
			}
			break;
		case KeyEvent.VK_UP: //Move sunmarine vertically up
			if(keyType == KeyEvent.KEY_PRESSED) {
				if(location[1]<=0) {
					location[1]+=0.01;
					diving = true;
				} else {
					diving = false;
				}
			} 
			if(keyType == KeyEvent.KEY_RELEASED){
				diving = false;
			}
			break;
		case KeyEvent.VK_DOWN: //Move submarine vertically down
			if(keyType == KeyEvent.KEY_PRESSED) {
				if(location[1]>=-0.6) {
					location[1]-=0.01;
					diving = true;
				} else {
					diving = false;
				}
			}
			if(keyType == KeyEvent.KEY_RELEASED){
				diving = false;
			}
			break;
		case KeyEvent.VK_W: //Move submarine forwards
			if(keyType == KeyEvent.KEY_PRESSED) {
				distance = 0.01;
				double x = location[0]+Math.sin(Math.toRadians(heading))*distance;
				double z = location[2]+Math.cos(Math.toRadians(heading))*distance;
				location[0] = x;
				location[2] = z;
				propellerRotation-=4;
				if(propellerRotation >= 360.0) {
					propellerRotation = 0.0;
				}
			} 
			if(keyType == KeyEvent.KEY_RELEASED){
				distance = 0;
			}
			break;
		case KeyEvent.VK_S: //Move submarine backwards
			if(keyType == KeyEvent.KEY_PRESSED) {
				distance = -0.01;
				double x = location[0]+Math.sin(Math.toRadians(heading))*distance;
				double z = location[2]+Math.cos(Math.toRadians(heading))*distance;
				location[0] = x;
				location[2] = z;
				propellerRotation+=4;
				if(propellerRotation >= 360.0) {
					propellerRotation = 0.0;
				}
			} 
			if(keyType == KeyEvent.KEY_RELEASED){
				distance = 0;
			}
			break;
		case KeyEvent.VK_A: //Turn submarine to the left
			if(keyType == KeyEvent.KEY_PRESSED) {
				heading+=1;
				sub.setRotation(heading-270, 0, 1, 0);
			}
			break;
		case KeyEvent.VK_D: //Turn submarine to the right
			if(keyType == KeyEvent.KEY_PRESSED) {
				heading-=1;
				sub.setRotation(heading-270, 0, 1, 0);
			}
			break;
		}
		
	}
	
	@Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_L | key == KeyEvent.VK_UP | key == KeyEvent.VK_DOWN | key == KeyEvent.VK_W
        		| key == KeyEvent.VK_S | key == KeyEvent.VK_A | key == KeyEvent.VK_D) {
			dStyle = e.getKeyCode();
			keyType = KeyEvent.KEY_PRESSED;
		} else {
			key=-1;
		}
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	int key = e.getKeyCode();
        if (key == KeyEvent.VK_L | key == KeyEvent.VK_UP | key == KeyEvent.VK_DOWN | key == KeyEvent.VK_W
        		| key == KeyEvent.VK_S | key == KeyEvent.VK_A | key == KeyEvent.VK_D) {
			dStyle = e.getKeyCode();
			keyType = KeyEvent.KEY_RELEASED;
		} else {
			key=-1;
		}	
    }
  
    @Override
    public void keyTyped(KeyEvent e) {}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.setSwapInterval(1);

		// enable depth test and set shading mode
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glShadeModel(GL2.GL_SMOOTH);
		
		//set up lights
		gl.glEnable(GL2.GL_NORMALIZE);
		this.lights(gl);
		
		//Initialise variables
		grid = new Grid();
		ol = new OriginLocator();
		sub = new Submarine();
		propellerRotation = 0.0;
		eyeHeight = 1.0f;
		dMode = true;
		diving = false;
		location = new double[3];
		heading = 270.0;
		distance = 0.01;
	}

	private void lights(GL2 gl) {
		
		// lighting stuff
		float ambient[] = { 0.5f, 0.5f, 0.5f, 1 };
		float diffuse[] = { 1, 1, 1, 1 };
		float specular[] = { 1, 1, 1, 1 };
		float position0[] = { 1, 1, 1, 0 };
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, position0, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambient, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, specular, 0);
	
		//enable lights
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);
	
		//draw using standard glColor
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();
		GLU glu = new GLU();
		
		WIN_WIDTH  = width;
    	height = (height <= 0) ? 1 : height; 
        WIN_HEIGHT = height;
        double aspect = WIN_WIDTH/WIN_HEIGHT;
		
		// Set the viewport to cover the new window
		gl.glViewport(0, 0, width, height);
		
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(45.0, aspect, 0.1, 100.0);

	}

	public static void main(String[] args) {
		Frame frame = new Frame("A2 Starting Code");
		GLCanvas canvas = new GLCanvas();
		BuildEventListener app = new BuildEventListener();
		canvas.addGLEventListener(app);
		canvas.addKeyListener(app);
		frame.add(canvas);
		frame.setSize(WIN_WIDTH, WIN_HEIGHT);
		final FPSAnimator animator = new FPSAnimator(canvas, 60);
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// Run this on another thread than the AWT event queue to
				// make sure the call to Animator.stop() completes before
				// exiting
				new Thread(new Runnable() {

					@Override
					public void run() {
						animator.stop();
						System.exit(0);
					}
				}).start();
			}
		});
		// Center frame
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		canvas.setFocusable(true);
		canvas.requestFocus();
		animator.start();
		System.out.println("Key Mapping:");
		System.out.println("----------------------------------------------------");
		System.out.println("UP/DOWN ARROWS: Increase depth (dive) or decrease depth (surface)");
		System.out.println("W/S: Move forward or backward");
		System.out.println("A/D: Yaw (turn) left or right");
		System.out.println("L: Toggle wireframe/fill");
	}

}
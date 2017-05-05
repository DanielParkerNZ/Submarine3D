package submarine;

import java.util.LinkedList;
import java.util.List;

import com.jogamp.opengl.GL2;

public abstract class TreeNode {

	private double transX, transY, transZ, rot1, rot2, rot3, rotA;
	
  // list of children nodes
  private List<TreeNode> children = new LinkedList<TreeNode>();

  // Adds a child to the tree node
  public void addChild(TreeNode newChild)
  {
    children.add(newChild);
  }

  // drawing code for this branch of the tree
  void draw(GL2 gl){

   gl.glPushMatrix();

      // make the transformation for this branch of the tree
      transformNode(gl);

      // draw the current node
      drawNode(gl);

      // iterate through all the children
      for ( TreeNode child : children)
      {
        // go depth first into the tree
        child.draw(gl);
      }

    gl.glPopMatrix();
  }

  // Implement this method for transforming the node relative to its parent
  //abstract void transformNode(GL2 gl);

  // Implement this method to do the actual drawing of the node
  abstract void drawNode(GL2 gl);
  
  public void setTranslation(double x, double y, double z)
  {
      transX = x;
      transY = y;
      transZ = z;
  }
  
  public void setRotation(double a, double x, double y, double z)
  {
	  rotA = a;
      rot1 = x;
      rot2 = y;
      rot3 = z;
  }
  
  public void transformNode(GL2 gl) {
      // do the translation relative to the parent
      gl.glTranslated(transX, transY, transZ);
      gl.glRotated(rotA, rot1, rot2, rot3);
  }

}
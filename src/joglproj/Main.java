package joglproj;

import javax.swing.JFrame;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

public class Main {

	public static void main(String[] arguments){
		 //getting the capabilities object of GL2 profile        
	      final GLProfile profile = GLProfile.get(GLProfile.GL2);
	      GLCapabilities capabilities = new GLCapabilities(profile);
	   
	      // The canvas
	      final GLCanvas glcanvas = new GLCanvas(capabilities);
	      MainFrame sq = new MainFrame();
	      glcanvas.addGLEventListener(sq);
	      glcanvas.setSize(400, 400);
	   
	      //creating frame
	      final JFrame frame = new JFrame ("straight Line");
	   
	      //adding canvas to frame
	      frame.getContentPane().add(glcanvas);
	                 
	      frame.setSize(frame.getContentPane().getPreferredSize());
	      frame.setVisible(true);    
	}
}

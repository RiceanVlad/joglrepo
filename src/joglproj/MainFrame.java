package joglproj;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;

public class MainFrame
extends JFrame
implements GLEventListener
{
	public MainFrame(){
		super("Java OpenGL");
		
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(800, 600);
		
		// This method will be explained later
		// this.initializeJogl();
		
		this.setVisible(true);
	}

	@Override
	public void display(GLAutoDrawable canvas) {
		// Retrieve a reference to a GL object. We need it because it contains all the useful OGL methods.
		GL2 gl = canvas.getGL().getGL2();
		
		// Each time the scene is redrawn we clear the color buffers which is perceived by the user as clearing the scene.
		// Clear the color buffer
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		
		// Forcing the scene to be rendered.
		gl.glFlush();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}
}
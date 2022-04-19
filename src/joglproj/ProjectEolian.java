package joglproj;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFrame;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.FPSAnimator;

public class ProjectEolian extends JFrame implements GLEventListener, KeyListener{
	
	// Number of textures we want to create
		private final int NO_TEXTURES = 3;

		private int texture[] = new int[NO_TEXTURES];
		TextureReader.Texture[] tex = new TextureReader.Texture[NO_TEXTURES];

		// GLU object used for mipmapping.
		private GLU glu;
		
		private float rquad = 0.0f;
		private float xrot,yrot,zrot;
		private float dimen = -2.5f;
		private float xEarth = -2.5f;		
		private float yEarth = -2.5f;	
		private float zEarth = -10f;	
		private float zMoon = 0f;	
		private float xMoon = -2.5f;
		private float yMoon = -2.5f;
		private boolean moveEarthRight = true;
		private boolean moveMoonUp = true;
		private float xSun = -5f;
		private boolean moveSunRight = true;
		private float xMoonNew = 7f;
		private boolean moveMoonLeft = true;
		private float eolianSpeed = 1f;


	@Override
	public void display(GLAutoDrawable canvas) {
		// TODO Auto-generated method stub
		final GL2 gl = canvas.getGL().getGL2(); 
		
		clearScene(gl);
		
//		applyTexture(gl, 0);
		
		applyLight(gl);
		
//		createCubeColors(gl);
		createVerticalPropeller(gl);
		createHorizontalPropeller(gl);
		createPropellerBase(gl);
		createHouse1(gl);
		createHouse2(gl);
		createHouse3(gl);
		createHouse4(gl);
		createHouse5(gl);
		createHouse6(gl);
		createTree1(gl);
		createTree2(gl);
		createTree3(gl);
		createTree4(gl);
		createTree5(gl);
	    createSun(gl);
	    createMoon(gl);

	}
	
	private void applyLight(GL2 gl) {
		float light = 1f;
		if(Math.abs(xSun - xMoonNew)< 2f || Math.abs(xMoonNew - xSun) < 2f) {
			light = 0f;
		} else {
			light = 1f;
		}
//		
		// light
		// The vector arguments represent the R, G, B, A values.
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, new float [] {
				Math.abs(eolianSpeed/5), 
				Math.abs(eolianSpeed/5), 
				Math.abs(eolianSpeed/5),
				1f
				}, 0);
		
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, new float [] {0.9f, 0.9f, 0.9f, 0f}, 0);
		// The vector arguments represent the x, y, z, w values of the position.
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, new float [] {0f, 6f, -5f, 1f}, 0);
		
		// enable materials
//				gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT, new float [] {0.8f, 0.8f, 0.0f, 1f}, 0);
//				gl.glMaterialfv(GL.GL_FRONT, GL2.GL_DIFFUSE, new float [] {0.8f, 0.8f, 0.0f, 1f}, 0);
//				gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SPECULAR, new float [] {0.8f, 0.8f, 0.0f, 1f}, 0);
//				gl.glMaterialfv(GL.GL_FRONT, GL2.GL_EMISSION, new float [] {0.5f, 0.5f, 0f, 1f}, 0);
	}
	
	private void clearScene(GL2 gl) {
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		// Clear the depth buffer.
		gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
	}
	
	private void applyTexture(GL2 gl, int poz_tex) {
		// Bind (select) the texture
	   TextureHandler th=new TextureHandler();
	   th.bind(gl, texture[poz_tex]);
	}
	

	
	private void createVerticalPropeller(GL2 gl) {
		
	      gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
	      gl.glLoadIdentity(); // Reset The View
	      gl.glTranslatef(0f, 0f, -15.0f);
	      
		  gl.glPushMatrix();
	      gl.glRotatef(xrot, 0.0f, 0.0f, 1.0f);
	      
	      applyTexture(gl,0);
	      
	      gl.glBegin(GL2.GL_QUADS);

	      // Front Face
	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.2f, -1.0f, 1.0f);
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 0.2f, -1.0f, 1.0f);
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 0.2f, 1.0f, 1.0f);
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.2f, 1.0f, 1.0f);
	      
	      // Back Face
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-0.2f, -1.0f, 0.8f);
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-0.2f, 1.0f, 0.8f);
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 0.2f, 1.0f, 0.8f);
	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 0.2f, -1.0f, 0.8f);
//
	      // Top Face
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.2f, 1.0f, 0.8f);
	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.2f, 1.0f, 1.0f);
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 0.2f, 1.0f, 1.0f);
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 0.2f, 1.0f, 0.8f);

	      // Bottom Face
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-0.2f, -1.0f, 0.8f);
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 0.2f, -1.0f, 0.8f);
	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 0.2f, -1.0f, 1.0f);
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-0.2f, -1.0f, 1.0f);
//
	      // Right face
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 0.2f, -1.0f, 0.8f);
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 0.2f, 1.0f, 0.8f);
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 0.2f, 1.0f, 1.0f);
	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 0.2f, -1.0f, 1.0f);
//	      
//	      // Left Face
	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.2f, -1.0f, 0.8f);
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-0.2f, -1.0f, 1.0f);
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-0.2f, 1.0f, 1.0f);
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.2f, 1.0f, 0.8f);
	      gl.glEnd();
	      gl.glFlush();

	      
	      //change the speeds here
	      xrot += eolianSpeed;
	}
	
	private void createHorizontalPropeller(GL2 gl) {
		  
		
	      gl.glBegin(GL2.GL_QUADS);

	      // Front Face
	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -0.2f, 1.0f);
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -0.2f, 1.0f);
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f, 0.2f, 1.0f);
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f, 0.2f, 1.0f);
	      
//	      // Back Face
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -0.2f, 0.8f);
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f, 0.2f, 0.8f);
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f, 0.2f, 0.8f);
	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -0.2f, 0.8f);
//
//	      // Top Face
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f, 0.2f, 0.8f);
	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, 0.2f, 1.0f);
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, 0.2f, 1.0f);
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f, 0.2f, 0.8f);
//
//	      // Bottom Face
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f, -0.2f, 0.8f);
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f, -0.2f, 0.8f);
	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -0.2f, 1.0f);
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -0.2f, 1.0f);
////
//	      // Right face
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -0.2f, 0.8f);
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f, 0.2f, 0.8f);
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f, 0.2f, 1.0f);
	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -0.2f, 1.0f);
////	      
////	      // Left Face
	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -0.2f, 0.8f);
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -0.2f, 1.0f);
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f, 0.2f, 1.0f);
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f, 0.2f, 0.8f);
	      
	      gl.glEnd();
	      gl.glFlush();
	   
		  gl.glPopMatrix();
	}
	
	private void createPropellerBase(GL2 gl) {
//		 gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
//	      gl.glLoadIdentity(); // Reset The View
//	      gl.glTranslatef(0f, 0f, -5.0f);
		
		gl.glPushMatrix();
	    applyTexture(gl,1);

		
		 gl.glBegin(GL2.GL_QUADS);

	      // Front Face
	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.2f, -2.0f, 1.0f);
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 0.2f, -2.0f, 1.0f);
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 0.2f, 0.0f, 1.0f);
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.2f, 0.0f, 1.0f);
	      
	      // Back Face
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-0.2f, -2.0f, 0.8f);
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-0.2f, 0.0f, 0.8f);
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 0.2f, 0.0f, 0.8f);
	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 0.2f, -2.0f, 0.8f);
//
	      // Top Face
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.2f, 0.0f, 0.8f);
	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.2f, 0.0f, 1.0f);
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 0.2f, 0.0f, 1.0f);
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 0.2f, 0.0f, 0.8f);

	      // Bottom Face
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-0.2f, -2.0f, 0.8f);
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 0.2f, -2.0f, 0.8f);
	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 0.2f, -2.0f, 1.0f);
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-0.2f, -2.0f, 1.0f);
//
	      // Right face
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 0.2f, -2.0f, 0.8f);
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 0.2f, 0.0f, 0.8f);
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 0.2f, 0.0f, 1.0f);
	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 0.2f, -2.0f, 1.0f);
//	      
//	      // Left Face
	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.2f, -2.0f, 0.8f);
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-0.2f, -2.0f, 1.0f);
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-0.2f, 0.0f, 1.0f);
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.2f, 0.0f, 0.8f);
	      gl.glEnd();
	      gl.glFlush();
	      
	      gl.glEnd();
	      gl.glFlush();
	      
		  gl.glPopMatrix();

	}
	
	private void createHouse1(GL2 gl) {
		
//		// BASE OF HOUSE
		gl.glPushMatrix();
	    applyTexture(gl,1);
	    
	    gl.glBegin(GL2.GL_QUADS);

	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-5.2f, -5.0f, 1.0f); // bottom left
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( -3.0f, -5.0f, 1.0f); // bottom right
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-3.0f, -3.5f, 1.0f); // top right
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-5.2f, -3.5f, 1.0f); // top left
	    
	    gl.glEnd();
	      gl.glFlush();
	      
		  gl.glPopMatrix();
		  
	  // TRIANGLE ROOF
		  gl.glPushMatrix();
	    applyTexture(gl,2);
	    
	    gl.glBegin(GL2.GL_QUADS);

	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-5.2f, -3.5f, 1.0f); // bottom left
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( -4.2f, -3.5f, 1.0f); // bottom right
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-4.7f, -2.3f, 1.0f); // top right
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-4.7f, -2.3f, 1.0f); // top left
	    
	    gl.glEnd();
	      gl.glFlush();
	      
		  gl.glPopMatrix();
		  
		 // RECTANGULAR ROOF
		  gl.glPushMatrix();
	    applyTexture(gl,0);
	    
	    gl.glBegin(GL2.GL_QUADS);

	    // Front Face
	      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-4.7f, -3.5f, 1.0f); // bottom left
	      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-3.0f, -3.5f, 1.0f); // bottom right
	      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-3.0f, -2.3f, 1.0f); // top right
	      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-4.7f, -2.3f, 1.0f); // top left
	    
	    gl.glEnd();
	      gl.glFlush();
	      
		  gl.glPopMatrix();
	}
	
	private void createHouse2(GL2 gl) {
		
		float xhouse = 0.0f;
		float yhouse = 0.7f;
		float zhouse = -4f;
		
		// BASE OF HOUSE
				gl.glPushMatrix();
			    applyTexture(gl,1);
			    
			    gl.glBegin(GL2.GL_QUADS);

			      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-5.2f + xhouse, -5.0f+yhouse, zhouse); // bottom left
			      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( -3.0f+ xhouse, -5.0f+yhouse, zhouse); // bottom right
			      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-3.0f+ xhouse, -3.5f+yhouse, zhouse); // top right
			      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-5.2f+ xhouse, -3.5f+yhouse,zhouse); // top left
			    
			    gl.glEnd();
			      gl.glFlush();
			      
				  gl.glPopMatrix();
				  
			  // TRIANGLE ROOF
				  gl.glPushMatrix();
			    applyTexture(gl,2);
			    
			    gl.glBegin(GL2.GL_QUADS);

			      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-5.2f+ xhouse, -3.5f+yhouse, zhouse); // bottom left
			      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( -4.2f+ xhouse, -3.5f+yhouse, zhouse); // bottom right
			      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-4.7f+ xhouse, -2.3f+yhouse, zhouse); // top right
			      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-4.7f+ xhouse, -2.3f+yhouse, zhouse); // top left
			    
			    gl.glEnd();
			      gl.glFlush();
			      
				  gl.glPopMatrix();
				  
				 // RECTANGULAR ROOF
				  gl.glPushMatrix();
			    applyTexture(gl,0);
			    
			    gl.glBegin(GL2.GL_QUADS);

			    // Front Face
			      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-4.7f+ xhouse, -3.5f+yhouse, zhouse); // bottom left
			      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-3.0f+ xhouse, -3.5f+yhouse, zhouse); // bottom right
			      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-3.0f+ xhouse, -2.3f+yhouse, zhouse); // top right
			      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-4.7f+ xhouse, -2.3f+yhouse, zhouse); // top left
	    
	    gl.glEnd();
	      gl.glFlush();
	      
		  gl.glPopMatrix();
	}
	
	private void createHouse3(GL2 gl) {
		
		float xhouse = 3.0f;
		float yhouse = -1f;
		float zhouse = -2f;
		
		// BASE OF HOUSE
				gl.glPushMatrix();
			    applyTexture(gl,1);
			    
			    gl.glBegin(GL2.GL_QUADS);

			      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-5.2f + xhouse, -5.0f+yhouse, zhouse); // bottom left
			      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( -3.0f+ xhouse, -5.0f+yhouse, zhouse); // bottom right
			      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-3.0f+ xhouse, -3.5f+yhouse, zhouse); // top right
			      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-5.2f+ xhouse, -3.5f+yhouse,zhouse); // top left
			    
			    gl.glEnd();
			      gl.glFlush();
			      
				  gl.glPopMatrix();
				  
			  // TRIANGLE ROOF
				  gl.glPushMatrix();
			    applyTexture(gl,2);
			    
			    gl.glBegin(GL2.GL_QUADS);

			      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-5.2f+ xhouse, -3.5f+yhouse, zhouse); // bottom left
			      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( -4.2f+ xhouse, -3.5f+yhouse, zhouse); // bottom right
			      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-4.7f+ xhouse, -2.3f+yhouse, zhouse); // top right
			      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-4.7f+ xhouse, -2.3f+yhouse, zhouse); // top left
			    
			    gl.glEnd();
			      gl.glFlush();
			      
				  gl.glPopMatrix();
				  
				 // RECTANGULAR ROOF
				  gl.glPushMatrix();
			    applyTexture(gl,0);
			    
			    gl.glBegin(GL2.GL_QUADS);

			    // Front Face
			      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-4.7f+ xhouse, -3.5f+yhouse, zhouse); // bottom left
			      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-3.0f+ xhouse, -3.5f+yhouse, zhouse); // bottom right
			      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-3.0f+ xhouse, -2.3f+yhouse, zhouse); // top right
			      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-4.7f+ xhouse, -2.3f+yhouse, zhouse); // top left
	    
	    gl.glEnd();
	      gl.glFlush();
	      
		  gl.glPopMatrix();
	}
	
private void createHouse4(GL2 gl) {
		
		float xhouse = 6.5f;
		float yhouse = -0.3f;
		float zhouse = 0f;
		
		// BASE OF HOUSE
				gl.glPushMatrix();
			    applyTexture(gl,1);
			    
			    gl.glBegin(GL2.GL_QUADS);

			      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-5.2f + xhouse, -5.0f+yhouse, zhouse); // bottom left
			      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( -3.0f+ xhouse, -5.0f+yhouse, zhouse); // bottom right
			      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-3.0f+ xhouse, -3.5f+yhouse, zhouse); // top right
			      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-5.2f+ xhouse, -3.5f+yhouse,zhouse); // top left
			    
			    gl.glEnd();
			      gl.glFlush();
			      
				  gl.glPopMatrix();
				  
			  // TRIANGLE ROOF
				  gl.glPushMatrix();
			    applyTexture(gl,2);
			    
			    gl.glBegin(GL2.GL_QUADS);

			      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-5.2f+ xhouse, -3.5f+yhouse, zhouse); // bottom left
			      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( -4.2f+ xhouse, -3.5f+yhouse, zhouse); // bottom right
			      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-4.7f+ xhouse, -2.3f+yhouse, zhouse); // top right
			      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-4.7f+ xhouse, -2.3f+yhouse, zhouse); // top left
			    
			    gl.glEnd();
			      gl.glFlush();
			      
				  gl.glPopMatrix();
				  
				 // RECTANGULAR ROOF
				  gl.glPushMatrix();
			    applyTexture(gl,0);
			    
			    gl.glBegin(GL2.GL_QUADS);

			    // Front Face
			      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-4.7f+ xhouse, -3.5f+yhouse, zhouse); // bottom left
			      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-3.0f+ xhouse, -3.5f+yhouse, zhouse); // bottom right
			      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-3.0f+ xhouse, -2.3f+yhouse, zhouse); // top right
			      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-4.7f+ xhouse, -2.3f+yhouse, zhouse); // top left
	    
	    gl.glEnd();
	      gl.glFlush();
	      
		  gl.glPopMatrix();
	}

private void createHouse5(GL2 gl) {
	
	float xhouse = 8f;
	float yhouse = +0.7f;
	float zhouse = -3f;
	
	// BASE OF HOUSE
			gl.glPushMatrix();
		    applyTexture(gl,1);
		    
		    gl.glBegin(GL2.GL_QUADS);

		      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-5.2f + xhouse, -5.0f+yhouse, zhouse); // bottom left
		      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( -3.0f+ xhouse, -5.0f+yhouse, zhouse); // bottom right
		      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-3.0f+ xhouse, -3.5f+yhouse, zhouse); // top right
		      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-5.2f+ xhouse, -3.5f+yhouse,zhouse); // top left
		    
		    gl.glEnd();
		      gl.glFlush();
		      
			  gl.glPopMatrix();
			  
		  // TRIANGLE ROOF
			  gl.glPushMatrix();
		    applyTexture(gl,2);
		    
		    gl.glBegin(GL2.GL_QUADS);

		      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-5.2f+ xhouse, -3.5f+yhouse, zhouse); // bottom left
		      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( -4.2f+ xhouse, -3.5f+yhouse, zhouse); // bottom right
		      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-4.7f+ xhouse, -2.3f+yhouse, zhouse); // top right
		      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-4.7f+ xhouse, -2.3f+yhouse, zhouse); // top left
		    
		    gl.glEnd();
		      gl.glFlush();
		      
			  gl.glPopMatrix();
			  
			 // RECTANGULAR ROOF
			  gl.glPushMatrix();
		    applyTexture(gl,0);
		    
		    gl.glBegin(GL2.GL_QUADS);

		    // Front Face
		      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-4.7f+ xhouse, -3.5f+yhouse, zhouse); // bottom left
		      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-3.0f+ xhouse, -3.5f+yhouse, zhouse); // bottom right
		      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-3.0f+ xhouse, -2.3f+yhouse, zhouse); // top right
		      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-4.7f+ xhouse, -2.3f+yhouse, zhouse); // top left
    
    gl.glEnd();
      gl.glFlush();
      
	  gl.glPopMatrix();
}

private void createHouse6(GL2 gl) {
	
	float xhouse = 9.0f;
	float yhouse = +2f;
	float zhouse = -4f;
	
	
	// BASE OF HOUSE
			gl.glPushMatrix();
		    applyTexture(gl,1);
		    
		    gl.glBegin(GL2.GL_QUADS);

		      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-5.2f + xhouse, -5.0f+yhouse, zhouse); // bottom left
		      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( -3.0f+ xhouse, -5.0f+yhouse, zhouse); // bottom right
		      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-3.0f+ xhouse, -3.5f+yhouse, zhouse); // top right
		      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-5.2f+ xhouse, -3.5f+yhouse,zhouse); // top left
		    
		    gl.glEnd();
		      gl.glFlush();
		      
			  gl.glPopMatrix();
			  
		  // TRIANGLE ROOF
			  gl.glPushMatrix();
		    applyTexture(gl,2);
		    
		    gl.glBegin(GL2.GL_QUADS);

		      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-5.2f+ xhouse, -3.5f+yhouse, zhouse); // bottom left
		      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( -4.2f+ xhouse, -3.5f+yhouse, zhouse); // bottom right
		      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-4.7f+ xhouse, -2.3f+yhouse, zhouse); // top right
		      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-4.7f+ xhouse, -2.3f+yhouse, zhouse); // top left
		    
		    gl.glEnd();
		      gl.glFlush();
		      
			  gl.glPopMatrix();
			  
			 // RECTANGULAR ROOF
			  gl.glPushMatrix();
		    applyTexture(gl,0);
		    
		    gl.glBegin(GL2.GL_QUADS);

		      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-4.7f+ xhouse, -3.5f+yhouse, zhouse); // bottom left
		      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-3.0f+ xhouse, -3.5f+yhouse, zhouse); // bottom right
		      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-3.0f+ xhouse, -2.3f+yhouse, zhouse); // top right
		      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-4.7f+ xhouse, -2.3f+yhouse, zhouse); // top left
    
    gl.glEnd();
      gl.glFlush();
      
	  gl.glPopMatrix();
}

private void createTree1(GL2 gl) {
	
	float xtree = -4.5f;
	float ytree = 0f;
	float ztree = -4f;

	
	// BASE OF TREE
			gl.glPushMatrix();
		    applyTexture(gl,1);
		    
		    gl.glBegin(GL2.GL_QUADS);

		      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.2f + xtree, -1.0f+ytree, ztree); // bottom left
		      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 0.2f+ xtree, -1.0f+ytree, ztree); // bottom right
		      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.2f+ xtree, 1.0f+ytree, ztree); // top right
		      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.2f+ xtree, 1.0f+ytree,ztree); // top left
		    
		    gl.glEnd();
		      gl.glFlush();
		      
			  gl.glPopMatrix();
			  
//		  // TOP CIRCLE TREE
			  gl.glPushMatrix();
			    
			  drawCircle(gl,0.95f+xtree,1.0f+ytree,0.8f);
			      
			  gl.glPopMatrix();
      
}

private void createTree2(GL2 gl) {
	
	float xtree = -3.5f;
	float ytree = 0f;
	float ztree = -4f;

	
	// BASE OF TREE
			gl.glPushMatrix();
		    applyTexture(gl,1);
		    
		    gl.glBegin(GL2.GL_QUADS);

		      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.2f + xtree, -1.0f+ytree, ztree); // bottom left
		      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 0.2f+ xtree, -1.0f+ytree, ztree); // bottom right
		      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.2f+ xtree, 1.0f+ytree, ztree); // top right
		      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.2f+ xtree, 1.0f+ytree,ztree); // top left
		    
		    gl.glEnd();
		      gl.glFlush();
		      
			  gl.glPopMatrix();
			  
//		  // TOP CIRCLE TREE
			  gl.glPushMatrix();
			    
			  drawCircle(gl,0.8f+xtree,1.0f+ytree,0.8f);
			      
			  gl.glPopMatrix();
      
}

private void createTree3(GL2 gl) {
	
	float xtree = -5.5f;
	float ytree = 0.5f;
	float ztree = -4f;

	
	// BASE OF TREE
			gl.glPushMatrix();
		    applyTexture(gl,1);
		    
		    gl.glBegin(GL2.GL_QUADS);

		      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.2f + xtree, -1.0f+ytree, ztree); // bottom left
		      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 0.2f+ xtree, -1.0f+ytree, ztree); // bottom right
		      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.2f+ xtree, 1.0f+ytree, ztree); // top right
		      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.2f+ xtree, 1.0f+ytree,ztree); // top left
		    
		    gl.glEnd();
		      gl.glFlush();
		      
			  gl.glPopMatrix();
			  
//		  // TOP CIRCLE TREE
			  gl.glPushMatrix();
			    
			  drawCircle(gl,1.1f+xtree,1.0f+ytree,0.8f);
			      
			  gl.glPopMatrix();
      
}

private void createTree4(GL2 gl) {
	
	float xtree = -6.5f;
	float ytree = -1f;
	float ztree = -4f;

	
	// BASE OF TREE
			gl.glPushMatrix();
		    applyTexture(gl,1);
		    
		    gl.glBegin(GL2.GL_QUADS);

		      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.2f + xtree, -1.0f+ytree, ztree); // bottom left
		      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 0.2f+ xtree, -1.0f+ytree, ztree); // bottom right
		      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.2f+ xtree, 1.0f+ytree, ztree); // top right
		      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.2f+ xtree, 1.0f+ytree,ztree); // top left
		    
		    gl.glEnd();
		      gl.glFlush();
		      
			  gl.glPopMatrix();
			  
//		  // TOP CIRCLE TREE
			  gl.glPushMatrix();
			    
			  drawCircle(gl,1.3f+xtree,1.0f+ytree,0.8f);
			      
			  gl.glPopMatrix();
      
}

private void createTree5(GL2 gl) {
	
	float xtree = -7.0f;
	float ytree = 1.5f;
	float ztree = -4f;

	
	// BASE OF TREE
			gl.glPushMatrix();
		    applyTexture(gl,1);
		    
		    gl.glBegin(GL2.GL_QUADS);

		      gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.2f + xtree, -1.0f+ytree, ztree); // bottom left
		      gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 0.2f+ xtree, -1.0f+ytree, ztree); // bottom right
		      gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.2f+ xtree, 1.0f+ytree, ztree); // top right
		      gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.2f+ xtree, 1.0f+ytree,ztree); // top left
		    
		    gl.glEnd();
		      gl.glFlush();
		      
			  gl.glPopMatrix();
			  
//		  // TOP CIRCLE TREE
			  gl.glPushMatrix();
			    
			  drawCircle(gl,1.4f+xtree,1.0f+ytree,0.8f);
			      
			  gl.glPopMatrix();
      
}

private void drawCircle(GL2 gl, float xCenter, float yCenter, float radius) {

	double x = 0, y = 0, angle;
//	gl.glColor3f(0, 1, 0);
	gl.glBegin(GL2.GL_TRIANGLES);
	for (int i = 0; i <= 360; i++) {
		angle = Math.toRadians(i);
		gl.glVertex2d(xCenter + x, yCenter + y);
		x = radius * Math.cos(angle);
		y = radius * Math.sin(angle);
		gl.glVertex2d(xCenter + x, yCenter + y);
		gl.glVertex2d(xCenter, yCenter);
	}
	gl.glEnd();

}

	private void createSun(GL2 gl) {
		gl.glPushMatrix();

		applyTexture(gl, 0);

		// rotate sphere
//		gl.glLoadIdentity();
		gl.glTranslatef( xSun, 7f, -5.0f ); 
		gl.glRotatef(rquad, 1.0f, 1.0f, 1.0f); 
	    
		// draw sphere
		GLUquadric sun = glu.gluNewQuadric ();
		glu.gluQuadricTexture(sun, true);
		glu.gluSphere (sun, 1.0, 32, 32);
//		glu.gluDeleteQuadric (sun);
		
	    rquad -= 0.35f;
	    if(moveSunRight==true) {
	    	xSun += 0.02f;
	    	if(xSun > 6f) {
	    		moveSunRight = false;
	    	}
	    } else {
	    	xSun -= 0.02f;
	    	if(xSun < -6f) {
	    		moveSunRight = true;
	    	}
	    }
		gl.glPopMatrix();

	}
	

	
	private void createMoon(GL2 gl) {
		gl.glPushMatrix();

		applyTexture(gl, 2);

		// rotate sphere
//		gl.glLoadIdentity();
		gl.glTranslatef( xMoonNew, 6f, -4.0f ); 
		gl.glRotatef(rquad, 1.0f, 1.0f, 1.0f); 
	    
		// draw sphere
		GLUquadric sun = glu.gluNewQuadric ();
		glu.gluQuadricTexture(sun, true);
		glu.gluSphere (sun, 0.5, 32, 32);
//		glu.gluDeleteQuadric (sun);
		
	    if(moveMoonLeft==true) {
	    	xMoonNew -= 0.02f;
	    	if(xMoonNew < -6f) {
	    		moveMoonLeft = false;
	    	}
	    } else {
	    	xMoonNew += 0.02f;
	    	if(xMoonNew > 6f) {
	    		moveMoonLeft = true;
	    	}
	    }
		gl.glPopMatrix();

	}
	
//	private void createEarth(GL2 gl) {
//		applyTexture(gl, 1);
//
//		// rotate sphere
//		gl.glLoadIdentity();
//		gl.glTranslatef(xEarth, 0.0f, zEarth); 
//		gl.glRotatef(rquad, 1.0f, 1.0f, 1.0f); 
//	    
//		// draw sphere
//		GLUquadric sun = glu.gluNewQuadric ();
//		glu.gluQuadricTexture(sun, true);
//		glu.gluSphere (sun, 0.5, 32, 32);
//		glu.gluDeleteQuadric (sun);
//		
//		if(moveEarthRight) {
//			xEarth += 0.01f;
//			if(xEarth >= 2f)
//				moveEarthRight = false;
//			if(xEarth <= 0) {
//				zEarth -= 0.05f;
//			} else {
//				zEarth += 0.05f;
//			}
//		} else {
//			xEarth -= 0.01f;
//			if(xEarth <= 0) {
//				zEarth -= 0.05f;
//			} else {
//				zEarth += 0.05f;
//			}
//			if(xEarth <= -2f)
//				moveEarthRight = true;
//		}
//		
//	    rquad -= 0.35f;
//	}
	
//	private void createMoon(GL2 gl) {
//		applyTexture(gl, 2);
//
//		// rotate sphere
//		gl.glLoadIdentity();
//		gl.glTranslatef(xEarth, yMoon, zEarth+zMoon); 
//		gl.glRotatef(rquad, 1.0f, 1.0f, 1.0f); 
//	    
//		// draw sphere
//		GLUquadric sun = glu.gluNewQuadric ();
//		glu.gluQuadricTexture(sun, true);
//		glu.gluSphere (sun, 0.3, 32, 32);
//		glu.gluDeleteQuadric (sun);
//		
//		if(moveMoonUp) {
//			yMoon += 0.05f;
//			if(yMoon >= 2.0f)
//				moveMoonUp = false;
//			if(yMoon <= 0) {
//				zMoon -= 0.08f;
//			} else {
//				zMoon += 0.08f;
//			}
//		} else {
//			yMoon -= 0.05f;
//			if(yMoon <= -2.0f)
//				moveMoonUp = true;
//			if(yMoon <= 0) {
//				zMoon -= 0.08f;
//			} else {
//				zMoon += 0.08f;
//			}
//		}
//		
//	    rquad -= 0.35f;
//	}
	
	private void createCubeColors(GL2 gl) {
		gl.glLoadIdentity();
	      gl.glTranslatef( 0f, 0f, -5.0f ); 

	      // Rotate The Cube On X, Y & Z
	      gl.glRotatef(rquad, 1.0f, 1.0f, 1.0f); 
	      
	      //giving different colors to different sides
	      gl.glBegin(GL2.GL_QUADS); // Start Drawing The Cube
	      gl.glColor3f(1f,0f,0f); //red color
	      gl.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right Of The Quad (Top)
	      gl.glVertex3f( -1.0f, 1.0f, -1.0f); // Top Left Of The Quad (Top)
	      gl.glVertex3f( -1.0f, 1.0f, 1.0f ); // Bottom Left Of The Quad (Top)
	      gl.glVertex3f( 1.0f, 1.0f, 1.0f ); // Bottom Right Of The Quad (Top)
			
	      gl.glColor3f( 0.5f,0f,0.5f ); //green color
	      gl.glVertex3f( 1.0f, -1.0f, 1.0f ); // Top Right Of The Quad
	      gl.glVertex3f( -1.0f, -1.0f, 1.0f ); // Top Left Of The Quad
	      gl.glVertex3f( -1.0f, -1.0f, -1.0f ); // Bottom Left Of The Quad
	      gl.glVertex3f( 1.0f, -1.0f, -1.0f ); // Bottom Right Of The Quad 

	      gl.glColor3f( 0.7f,0.3f,1f ); //blue color
	      gl.glVertex3f( 1.0f, 1.0f, 1.0f ); // Top Right Of The Quad (Front)
	      gl.glVertex3f( -1.0f, 1.0f, 1.0f ); // Top Left Of The Quad (Front)
	      gl.glVertex3f( -1.0f, -1.0f, 1.0f ); // Bottom Left Of The Quad
	      gl.glVertex3f( 1.0f, -1.0f, 1.0f ); // Bottom Right Of The Quad 

	      gl.glColor3f( 1f,1f,0f ); //yellow (red + green)
	      gl.glVertex3f( 1.0f, -1.0f, -1.0f ); // Bottom Left Of The Quad
	      gl.glVertex3f( -1.0f, -1.0f, -1.0f ); // Bottom Right Of The Quad
	      gl.glVertex3f( -1.0f, 1.0f, -1.0f ); // Top Right Of The Quad (Back)
	      gl.glVertex3f( 1.0f, 1.0f, -1.0f ); // Top Left Of The Quad (Back)

	      gl.glColor3f( 1f,0f,1f ); //purple (red + green)
	      gl.glVertex3f( -1.0f, 1.0f, 1.0f ); // Top Right Of The Quad (Left)
	      gl.glVertex3f( -1.0f, 1.0f, -1.0f ); // Top Left Of The Quad (Left)
	      gl.glVertex3f( -1.0f, -1.0f, -1.0f ); // Bottom Left Of The Quad
	      gl.glVertex3f( -1.0f, -1.0f, 1.0f ); // Bottom Right Of The Quad 

	      gl.glColor3f( 0f,1f, 1f ); //sky blue (blue +green)
	      gl.glVertex3f( 1.0f, 1.0f, -1.0f ); // Top Right Of The Quad (Right)
	      gl.glVertex3f( 1.0f, 1.0f, 1.0f ); // Top Left Of The Quad
	      gl.glVertex3f( 1.0f, -1.0f, 1.0f ); // Bottom Left Of The Quad
	      gl.glVertex3f( 1.0f, -1.0f, -1.0f ); // Bottom Right Of The Quad
	      gl.glEnd(); // Done Drawing The Quad
	      gl.glFlush();
			
	      rquad -= 0.55f;
	}


	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable canvas) 
	{
		// TODO Auto-generated method stub
		
//		final GL gl = canvas.getGL(); 
		final GL2 gl = canvas.getGL().getGL2(); 

		TextureHandler th=new TextureHandler();
		
		glu = GLU.createGLU();
	    gl.glGenTextures(NO_TEXTURES, texture, 0);
	    th.enable(gl);
	    
	    
	    th.bind(gl, texture[0]);
	    TextureHandler texhandler0=new TextureHandler(gl,glu,"sun.jpg",true);
	    texhandler0.configureTexture();
	    tex[0]=texhandler0.texreader;
	    
	    
	     th.bind(gl, texture[1]);
	     TextureHandler texhandler1=new TextureHandler(gl,glu,"earth.jpg",true);
	     texhandler1.configureTexture();
	     tex[1]=texhandler1.texreader;
	     
	     th.bind(gl, texture[2]);
	     TextureHandler texhandler2=new TextureHandler(gl,glu,"moon.jpg",true);
	     texhandler2.configureTexture();
	     tex[2]=texhandler2.texreader;
	     
		
	     gl.glClearColor( 0f, 0f, 0f, 0f );
	     gl.glClearDepth( 1.0f );
	     
	    // Choose the shading model.
		gl.glShadeModel(GL2.GL_SMOOTH);
	     
		// Activate the depth test and set the depth function.
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LESS);
		
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);
		gl.glEnable(GL2.GL_LIGHT1);
		
		// Enable diffuse light
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glColorMaterial(GL.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE);
		
	    gl.glHint( GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST );
		
	}
	

	@Override
	public void reshape( GLAutoDrawable drawable, int x, int y, int width, int height ) {
		
	      // TODO Auto-generated method stub
	      final GL2 gl = drawable.getGL().getGL2();
	      if( height <= 0 )
	         height = 1;
				
	      final float h = ( float ) width / ( float ) height;
	      gl.glViewport( 0, 0, width, height );
	      gl.glMatrixMode( GL2.GL_PROJECTION );
	      gl.glLoadIdentity();
			
	      glu.gluPerspective( 45.0f, h, 1.0, 20.0 );
	      gl.glMatrixMode( GL2.GL_MODELVIEW );
	      gl.glLoadIdentity();
	   }
	
	public static void initializeFrame()
	{
		  final GLProfile profile = GLProfile.get(GLProfile.GL2);
	      GLCapabilities capabilities = new GLCapabilities(profile);
	      
	      // The canvas
	      final GLCanvas glcanvas = new GLCanvas(capabilities);
	      ProjectEolian m = new ProjectEolian();
	      glcanvas.addGLEventListener(m);
	      glcanvas.setSize(900, 900);
	      
	      // key listener
	      glcanvas.addKeyListener(m);
	      
	      //creating frame
	      final JFrame frame = new JFrame ("Lab6 - 3D");
	      
	      //adding canvas to frame
	      frame.getContentPane().add(glcanvas);
	            
	      frame.setSize(frame.getContentPane().getPreferredSize());
	      frame.setVisible(true);
	      final FPSAnimator animator = new FPSAnimator(glcanvas, 300,true);
			
	      animator.start();
	}
	public  static void main(String[] args) 
	{
		initializeFrame();
	}
	
	public void keyPressed(KeyEvent event)
	{ 
					
		if (event.getKeyCode()== KeyEvent.VK_RIGHT) {
		      eolianSpeed -= 0.5f;
		}

		if (event.getKeyCode()== KeyEvent.VK_LEFT) {
		      eolianSpeed += 0.5f;
		}
			
			 	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}	

}